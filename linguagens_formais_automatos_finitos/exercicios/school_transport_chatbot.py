"""

Melhorias:
- Extração de rota com regex que cobre muitos formatos: 'rota1', 'rota 1', 'rota nº 1', 'rota-1', 'rota.1', etc.
- Normalização (remoção de pontuação que atrapalha a extração).
- Detect_state prioriza pedidos por motorista.
- Testes embutidos com exemplos que demonstram respostas corretas.
"""

import re
import sys

# Estados do autômato (strings simples)
class State:
    SAUDACAO = "SAUDACAO"
    INFORMAR_ROTA = "INFORMAR_ROTA"
    HORARIO = "HORARIO"
    DRIVER = "DRIVER"
    FINALIZAR = "FINALIZAR"
    UNKNOWN = "UNKNOWN"


class SchoolTransportChatbot:
    def __init__(self):
        self.current_state = State.SAUDACAO
        # Dados de exemplo
        self.routes = {
            "rota 1": "Rota 1: Bairro A → Bairro B → Escola Central",
            "rota 2": "Rota 2: Condomínio X → Rua 5 → Escola Central",
            "rota 3": "Rota 3: Zona Rural (Ponto Fazenda) → Escola Central",
        }
        self.route_times = {
            "rota 1": "06:30 (ida), 12:30 (volta)",
            "rota 2": "06:45 (ida), 12:45 (volta)",
            "rota 3": "06:00 (ida), 13:00 (volta)",
        }
        self.route_drivers = {
            "rota 1": "Motorista: João Silva — (99) 91234-0001",
            "rota 2": "Motorista: Maria Oliveira — (99) 91234-0002",
            "rota 3": "Motorista: Carlos Pereira — (99) 91234-0003",
        }

    # Normaliza a string: deixa em minúsculas e remove pontuação que atrapalha
    def _normalize(self, s: str) -> str:
        s_low = s.lower()
        # substitui caracteres comuns que podem aparecer (nº, no., etc.) por espaço
        s_low = s_low.replace("nº", " ").replace("º", " ").replace("no.", " ").replace("n.", " ")
        # remove acentuação simples se necessário? (omito para manter nomes)
        # remove pontuação que pode juntar tokens (ponto, vírgula, interrogação, exclamação)
        s_low = re.sub(r"[.,;:?!()\"']", " ", s_low)
        # normaliza hífens e barras para espaços
        s_low = re.sub(r"[-/\\]", " ", s_low)
        # colapsa múltiplos espaços
        s_low = re.sub(r"\s+", " ", s_low).strip()
        return s_low

    # Extração mais poderosa de "rota N" com regex
    def extract_route_key(self, s: str):
        """
        Tenta extrair 'n' de 'rota n' em várias formas:
        - "rota1", "rota 1", "rota nº 1", "rota-1", "rota.1"
        Retorna chave padronizada "rota N" (ex.: "rota 1") ou None.
        """
        if not s:
            return None

        s_norm = self._normalize(s)

        # busca por "rota <numero>"
        m = re.search(r"\brota\s*(?:n[oº\.\s]*)?(\d{1,2})\b", s_norm)
        if m:
            num = m.group(1)
            return f"rota {int(num)}"  # normaliza (sem zeros à esquerda)

        # também aceita "rta 1" ou abreviações comuns (opcional)
        m2 = re.search(r"\brt[ao]?\s*(\d{1,2})\b", s_norm)
        if m2:
            num = m2.group(1)
            return f"rota {int(num)}"

        # verifica se alguma chave cadastrada aparece no texto (p.ex. "zona rural")
        for k in self.routes.keys():
            if k in s_norm:
                return k

        return None

    # Detecção de intenção (prioriza motorista)
    def detect_state(self, user_input: str) -> str:
        s_norm = self._normalize(user_input)

        # prioridade para pedido de motorista
        if re.search(r"\bmotorista\b|\bcondutor\b|\bquem.*motorista\b", s_norm):
            return State.DRIVER

        # horário
        if re.search(r"\bhorário\b|\bhorario\b|\bhora\b|\bhoras\b", s_norm):
            return State.HORARIO

        # rota (qualquer menção)
        if re.search(r"\brota\b|\brt\b", s_norm) or self.extract_route_key(user_input) is not None:
            return State.INFORMAR_ROTA

        return State.UNKNOWN

    # Resposta central
    def respond(self, state: str, user_input: str) -> str:
        s = user_input or ""
        s_norm = self._normalize(s)

        if state == State.SAUDACAO:
            self.current_state = State.INFORMAR_ROTA
            return ("Olá! Bem-vindo ao atendimento de transporte escolar. "
                    "Qual rota você precisa — por exemplo 'Rota 1', 'Rota 2' ou 'Rota 3'?")

        if state == State.INFORMAR_ROTA:
            rk = self.extract_route_key(s)
            if rk:
                # se a frase também contém palavra motorista, devolve motorista direto
                if re.search(r"\bmotorista\b|\bcondutor\b", s_norm):
                    drv = self.route_drivers.get(rk, "Motorista não cadastrado para essa rota.")
                    self.current_state = State.HORARIO
                    return f"{drv} — Deseja também o horário dessa rota?"
                # caso contrário, devolve a descrição e pergunta se quer horário/motorista
                desc = self.routes.get(rk, "Descrição da rota indisponível.")
                self.current_state = State.HORARIO
                return f"{desc} — Deseja saber o horário ou o nome do motorista?"
            else:
                self.current_state = State.INFORMAR_ROTA
                return "Não identifiquei a rota. Informe como 'Rota 1', 'Rota 2' ou 'Rota 3'."

        if state == State.HORARIO:
            rk = self.extract_route_key(s)
            if rk:
                time = self.route_times.get(rk, "Horário não cadastrado para essa rota.")
                self.current_state = State.FINALIZAR
                return f"Horário da {rk}: {time}. Precisa de mais alguma informação ou deseja finalizar?"
            else:
                self.current_state = State.HORARIO
                return "Para qual rota você quer o horário? Informe 'Rota 1', 'Rota 2' ou 'Rota 3'."

        if state == State.DRIVER:
            rk = self.extract_route_key(s)
            if rk:
                drv = self.route_drivers.get(rk, "Motorista não cadastrado para essa rota.")
                self.current_state = State.HORARIO
                return f"{drv} — Deseja também o horário dessa rota?"
            else:
                # pede a rota se não informada
                self.current_state = State.INFORMAR_ROTA
                return "Qual rota você quer saber o motorista? 'Rota 1', 'Rota 2' ou 'Rota 3'?"

        if state == State.FINALIZAR:
            self.current_state = State.SAUDACAO
            return "Atendimento finalizado. Obrigado! Se precisar novamente, estou à disposição."

        # UNKNOWN fallback: tenta ainda detectar motorista/rota
        # se frase contém 'motorista' tenta extrair rota; senão pede clarificação
        if state == State.UNKNOWN:
            if re.search(r"\bmotorista\b|\bcondutor\b", s_norm):
                rk = self.extract_route_key(s)
                if rk:
                    drv = self.route_drivers.get(rk, "Motorista não cadastrado para essa rota.")
                    self.current_state = State.HORARIO
                    return f"{drv} — Deseja o horário também?"
                else:
                    self.current_state = State.INFORMAR_ROTA
                    return "Qual rota você quer saber o motorista? 'Rota 1', 'Rota 2' ou 'Rota 3'?"
            # respostas afirmativas
            if s_norm in ("sim", "ok", "claro", "pode"):
                self.current_state = State.INFORMAR_ROTA
                return "Perfeito — informe a rota (ex.: 'Rota 1') para que eu possa ajudar."
            self.current_state = State.INFORMAR_ROTA
            return ("Desculpe, não entendi. Posso ajudar com 'rota', 'horário' ou 'motorista'. "
                    "Qual você deseja?")

        # fallback final
        self.current_state = State.INFORMAR_ROTA
        return "Algo inesperado ocorreu — por favor informe a rota (ex.: 'Rota 1')."

    # Modo interativo
    def run(self):
        print("Atendimento de Transporte Escolar — digite 'sair' para encerrar.")
        print("Bot:", self.respond(State.SAUDACAO, ""))

        while True:
            try:
                user_input = input("\nVocê: ").strip()
            except (KeyboardInterrupt, EOFError):
                print("\nBot: Encerrando atendimento. Até logo!")
                sys.exit(0)

            if not user_input:
                print("Bot: Por favor, digite sua pergunta ou 'sair' para encerrar.")
                continue

            if user_input.lower() in ("sair", "finalizar", "tchau", "fim", "exit"):
                print("Bot: Obrigado! Atendimento encerrado.")
                break

            detected = self.detect_state(user_input)
            reply = self.respond(detected, user_input)
            print("Bot:", reply)

        print("Bot: Sessão finalizada.")


# ---------- Pequeno bloco de testes / demonstração ----------
def run_samples():
    bot = SchoolTransportChatbot()
    samples = [
        "Quem é o motorista da Rota 1?",
        "Motorista rota2",
        "Qual o horário da rota 3?",
        "rota1 motorista",
        "Quem dirige a rota nº 2?",
        "Motorista da rota-3?",
        "Rota 2",
        "horario rota1",
        "Quem é o motorista?",        # pede rota de volta
        "motorista da escola",        # sem rota: pede clarificação
    ]
    print("\n=== Testes automáticos ===")
    print("Bot:", bot.respond(State.SAUDACAO, ""))  # saudação
    for q in samples:
        state = bot.detect_state(q)
        print(f"\nVocê: {q}")
        print("detected state:", state)
        print("Bot:", bot.respond(state, q))


if __name__ == "__main__":
    # se quiser modo interativo: descomente a linha abaixo
    SchoolTransportChatbot().run()

    # Por padrão executamos os exemplos de teste para você ver as respostas
    #run_samples()
