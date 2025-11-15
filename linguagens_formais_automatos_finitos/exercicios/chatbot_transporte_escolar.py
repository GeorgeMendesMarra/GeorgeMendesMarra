#!/usr/bin/env python3
import re
import sys
import unicodedata

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

        self.last_route = None

        self.driver_name_map = {}
        for rk, drv_text in self.route_drivers.items():
            name = drv_text.split("—")[0].replace("Motorista:", "").strip()
            norm = self._normalize(name)
            self.driver_name_map[norm] = rk

        self.driver_name_tokens = {}
        for norm_name, rk in self.driver_name_map.items():
            tokens = [t for t in re.split(r"\s+", norm_name) if t]
            self.driver_name_tokens[rk] = tokens

    # ---------------- NORMALIZAÇÃO ----------------
    def _normalize(self, s: str) -> str:
        if not s:
            return ""
        s = s.strip().lower()
        s = unicodedata.normalize("NFKD", s)
        s = "".join(ch for ch in s if not unicodedata.combining(ch))
        s = re.sub(r"[^\w\s:]", " ", s)
        s = re.sub(r"\brota(\d)\b", r"rota \1", s)
        s = re.sub(r"\s+", " ", s).strip()
        return s

    # ---------------- EXTRAÇÕES ----------------
    def extract_route_key(self, text: str):
        if not text:
            return None
        s = self._normalize(text)

        m = re.search(r"\brota\s*(\d{1,2})\b", s)
        if m:
            return f"rota {m.group(1)}"

        m2 = re.search(r"\brt\s*(\d{1,2})\b", s)
        if m2:
            return f"rota {m2.group(1)}"

        if text.strip().isdigit():
            return f"rota {text.strip()}"

        for k in self.routes.keys():
            if k in s:
                return k

        return None

    def extract_showtime(self, text: str):
        if not text:
            return None

        s = text.strip()

        m = re.search(r"\b(\d{1,2}:\d{2})\b", s)
        if m:
            return m.group(1)

        m2 = re.search(r"\b(\d{1,2})h(\d{1,2})?\b", s.lower())
        if m2:
            hh = int(m2.group(1))
            mm = int(m2.group(2)) if m2.group(2) else 0
            return f"{hh:02d}:{mm:02d}"

        return None

    def extract_driver_by_name(self, text: str):
        if not text:
            return None
        s = self._normalize(text)

        for rk, tokens in self.driver_name_tokens.items():
            for tok in tokens:
                if re.search(rf"\b{tok}\b", s):
                    return rk
        return None

    # ---------------- HANDLERS ----------------
    def handle_saudacao(self, user_input: str):
        self.current_state = State.INFORMAR_ROTA
        return ("Olá! Bem-vindo ao transporte escolar. "
                "Qual rota deseja consultar? (Rota 1, Rota 2, Rota 3)")

    def handle_informar_rota(self, user_input: str):
        rk = self.extract_route_key(user_input)
        drv = self.extract_driver_by_name(user_input)
        time = self.extract_showtime(user_input)
        norm = self._normalize(user_input)

        if drv and not rk:
            self.last_route = drv
            self.current_state = State.HORARIO
            return f"{self.route_drivers[drv]} — Deseja saber também o horário?"

        if rk:
            self.last_route = rk
            desc = self.routes[rk]

            if "motorista" in norm:
                self.current_state = State.HORARIO
                return f"{self.route_drivers[rk]} — Deseja saber também os horários?"

            if time:
                # se o usuário explicitou um horário, validar e responder, mas NÃO finalizar
                times_for_route = self.route_times.get(rk, "")
                if time in times_for_route:
                    self.current_state = State.HORARIO
                    return f"{desc} — Horário solicitado: {time}. Deseja algo mais?"
                else:
                    self.current_state = State.HORARIO
                    return f"{desc} — Horários disponíveis: {times_for_route}. Qual horário prefere?"
            self.current_state = State.HORARIO
            return f"{desc} — Deseja saber o horário ou o motorista?"

        return "Não identifiquei a rota. Tente: Rota 1, Rota 2 ou Rota 3."

    def handle_horario(self, user_input: str):
        s = self._normalize(user_input)
        rk = self.extract_route_key(user_input)
        drv = self.extract_driver_by_name(user_input)
        time = self.extract_showtime(user_input)

        # se usuário escreveu só "motorista" enquanto estamos em HORARIO, responder com motorista da last_route
        if "motorista" in s:
            if self.last_route:
                self.current_state = State.HORARIO
                return f"{self.route_drivers[self.last_route]} — Precisa de mais alguma coisa?"
            return "Informe a rota para consultar o motorista."

        # determinar rota alvo: prioriza rota extraída, depois nome do motorista, depois contexto
        target = rk or drv or self.last_route

        if target:
            times = self.route_times.get(target, "Horários indisponíveis.")
            # se usuário forneceu horário específico e este existe, confirmar, mas manter estado HORARIO
            if time:
                if time in times:
                    self.current_state = State.HORARIO
                    return f"Horário {time} registrado para {target}. Deseja mais algo?"
                else:
                    self.current_state = State.HORARIO
                    return f"O horário {time} não consta para {target}. Horários disponíveis: {times}."
            # se usuário apenas pediu "horario" ou a rota, mostrar horários e permanecer no estado HORARIO
            self.current_state = State.HORARIO
            return f"Horários da {target}: {times}. Deseja mais alguma informação ou quer finalizar?"
        # sem contexto: pedir a rota
        self.current_state = State.INFORMAR_ROTA
        return "Para qual rota você quer o horário? Informe 'Rota 1', 'Rota 2' ou 'Rota 3'."

    def handle_driver(self, user_input: str):
        rk = self.extract_route_key(user_input)
        drv = self.extract_driver_by_name(user_input)

        route = rk or drv or self.last_route

        if route:
            self.current_state = State.HORARIO
            return f"{self.route_drivers.get(route, 'Motorista não encontrado.')} — Deseja o horário também?"
        return "Informe a rota para consultar o motorista."

    def handle_finalizar(self, user_input: str):
        self.current_state = State.SAUDACAO
        self.last_route = None
        return "Atendimento finalizado. Obrigado!"

    # ---------------- DISPATCHER ----------------
    def respond(self, user_input: str):
        if self.current_state == State.SAUDACAO:
            return self.handle_saudacao(user_input)
        if self.current_state == State.INFORMAR_ROTA:
            return self.handle_informar_rota(user_input)
        if self.current_state == State.HORARIO:
            return self.handle_horario(user_input)
        if self.current_state == State.DRIVER:
            return self.handle_driver(user_input)
        if self.current_state == State.FINALIZAR:
            return self.handle_finalizar(user_input)
        return "Não entendi. Tente novamente."

    # ---------------- LOOP ----------------
    def run(self):
        print("Atendimento de Transporte Escolar — digite 'sair' para encerrar.")
        print("Bot:", self.respond(""))

        while True:
            try:
                user_input = input("\nVocê: ").strip()
            except (KeyboardInterrupt, EOFError):
                print("\nBot: Encerrando. Até logo!")
                sys.exit(0)

            if user_input.lower() in ("sair", "tchau", "finalizar", "exit"):
                print("Bot: Atendimento encerrado.")
                break

            if not user_input:
                print("Bot: Por favor, digite algo.")
                continue

            # manter fluxo guiado pelo estado atual
            print("Bot:", self.respond(user_input))

        print("Bot: Sessão encerrada.")

if __name__ == "__main__":
    SchoolTransportChatbot().run()
