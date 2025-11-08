"""

Versão do CinemaAssistant com extração e validação de quantidade de ingressos corrigida.
Roda com: python cinema_assistant_fixed_quantity.py
"""

import re
import sys

class State:
    SAUDACAO = "SAUDACAO"
    ESCOLHER_FILME = "ESCOLHER_FILME"
    HORARIO = "HORARIO"
    INGRESSO = "INGRESSO"
    PAGAMENTO = "PAGAMENTO"
    FINALIZAR = "FINALIZAR"

class CinemaAssistant:
    def __init__(self):
        self.current_state = State.SAUDACAO
        self.movies = {
            1: "Aventura nas Estrelas",
            2: "Comédia do Bairro",
            3: "Drama do Silêncio",
            4: "Animação: Amigos do Parque",
        }
        self.showtimes = {
            1: ["13:00", "16:30", "20:00"],
            2: ["12:45", "15:15", "18:00", "21:00"],
            3: ["14:00", "19:30"],
            4: ["10:30", "13:30", "17:00"],
        }
        self.ticket_price = 25.00
        self.selected_movie_id = None
        self.selected_showtime = None
        self.selected_quantity = 0

    # ---------- Normalização ----------
    def normalize(self, s: str) -> str:
        if s is None:
            return ""
        s = s.lower()
        replacements = {
            "á": "a", "ã": "a", "â": "a",
            "é": "e", "ê": "e",
            "í": "i",
            "ó": "o", "õ": "o", "ô": "o",
            "ú": "u", "ç": "c",
            "º": "", "nº": ""
        }
        for k, v in replacements.items():
            s = s.replace(k, v)
        s = re.sub(r"[^\w\s:]", " ", s)  # remove pontuação exceto dois-pontos (horario)
        s = re.sub(r"\s+", " ", s).strip()
        return s

    # ---------- Extração de quantidade (robusta) ----------
    def extract_quantity(self, user_input: str):
        """
        Extrai quantidade de ingressos da entrada do usuário.
        Retorna um inteiro válido (>0) ou None se não conseguir extrair.
        Suporta:
          - números arábicos: "2", "10", "quero 3 ingressos"
          - por extenso (pt): "um", "dois", "tres", ... até vinte
          - variações com palavras (ex.: "2 ingressos", "dois", "mais 3")
        """
        if not user_input:
            return None

        s_orig = user_input
        s = self.normalize(user_input)

        # 1) procura primeiro por números arábicos (prioriza valores explícitos)
        m = re.search(r"\b(\d{1,2})\b", s)
        if m:
            try:
                n = int(m.group(1))
                if n <= 0:
                    return None
                # limite prático (evita números absurdos) -- ajustável
                if n > 30:
                    return None
                return n
            except ValueError:
                pass

        # 2) mapeamento de palavras por extenso (pt-BR) até 20 (pode expandir)
        words_to_nums = {
            "zero": 0, "um": 1, "uma": 1, "dois": 2, "duas": 2, "tres": 3, "tres": 3, "tres": 3,
            "quatro": 4, "cinco": 5, "seis": 6, "sete": 7, "oito": 8, "nove": 9, "dez": 10,
            "onze": 11, "doze": 12, "treze": 13, "quatorze": 14, "catorze": 14, "quinze": 15,
            "dezesseis": 16, "dezessete": 17, "dezoito": 18, "dezenove": 19, "vinte": 20
        }
        # verifica cada palavra-chave por extenso no texto normalizado
        for word, num in words_to_nums.items():
            # procura correspondência isolada ou seguida por "ingresso(s)"
            if re.search(rf"\b{word}\b", s):
                if num <= 0:
                    return None
                return num

        # 3) tenta extrair padrões escritos ("um ingresso", "quero dois ingressos")
        # (já coberto por map above, mas mantemos fallback para segurança)
        m2 = re.search(r"\b(um|uma|dois|duas|tres|tres|quatro|cinco|seis|sete|oito|nove|dez)\b", s)
        if m2:
            token = m2.group(1)
            return words_to_nums.get(token, None)

        # 4) se não detectou nada, retorna None (fluxo pedirá clarificação)
        return None

    # ---------- Outras helpers (extrair filme/horario) ----------
    def extract_movie_id(self, user_input: str):
        if not user_input:
            return None
        # "filme 2" ou só "2"
        m = re.search(r"\bfilme\s*(?:no\s*)?(\d{1,2})\b", user_input, re.IGNORECASE)
        if m:
            try:
                return int(m.group(1))
            except:
                pass
        m2 = re.match(r"^\s*(\d{1,2})\s*$", user_input)
        if m2:
            try:
                return int(m2.group(1))
            except:
                pass
        # casar por nome parcial
        s_norm = self.normalize(user_input)
        for mid, title in self.movies.items():
            if self.normalize(title) in s_norm or s_norm in self.normalize(title):
                return mid
        return None

    def extract_showtime(self, user_input: str):
        if not user_input:
            return None
        m = re.search(r"\b(\d{1,2}:\d{2})\b", user_input)
        if m:
            return m.group(1)
        m2 = re.search(r"\b(\d{1,2})h\b", user_input.lower())
        if m2:
            h = int(m2.group(1))
            return f"{h:02d}:00"
        return None

    # ---------- Mensagens ----------
    def show_movies(self):
        print("Filmes em cartaz:")
        for mid, title in self.movies.items():
            print(f"  {mid}) {title}")
        print("Digite o número do filme (ex.: '1' ou 'Filme 1') ou escreva o nome do filme.")

    def show_showtimes(self, movie_id: int):
        times = self.showtimes.get(movie_id, [])
        if not times:
            print("Desculpe, não há sessões cadastradas para esse filme.")
            return
        print(f"Horários disponíveis para '{self.movies[movie_id]}':")
        for i, t in enumerate(times, start=1):
            print(f"  {i}) {t}")
        print("Informe o horário (ex.: '16:30' ou 'opção 2').")

    # ---------- Resposta central ----------
    def detect_state(self, user_input: str):
        s = self.normalize(user_input)
        if re.search(r"\b(pagar|pagamento|confirmar)\b", s):
            return State.PAGAMENTO
        if re.search(r"\b(ingresso|ingressos|assento|assentos|comprar)\b", s):
            return State.INGRESSO
        if re.search(r"\b(horario|horarios|hora|h)\b", s) or re.search(r"\d{1,2}:\d{2}", user_input):
            return State.HORARIO
        if re.search(r"\b(filme|filmes|opcao|opcao)\b", s) or re.match(r"^\s*\d{1,2}\s*$", user_input):
            return State.ESCOLHER_FILME
        if re.search(r"\b(oi|ola|bom dia|boa tarde|boa noite)\b", s):
            return State.SAUDACAO
        return None

    def respond(self, detected_state, user_input):
        raw = user_input or ""
        norm = self.normalize(raw)

        if self.current_state == State.SAUDACAO:
            self.current_state = State.ESCOLHER_FILME
            self.show_movies()
            return "Olá! Posso ajudar a escolher um filme e comprar ingressos. Qual filme você quer?"

        if detected_state == State.ESCOLHER_FILME or self.current_state == State.ESCOLHER_FILME:
            mid = self.extract_movie_id(raw)
            if mid and mid in self.movies:
                self.selected_movie_id = mid
                self.current_state = State.HORARIO
                self.show_showtimes(mid)
                return f"Filme selecionado: {self.movies[mid]}. Qual horário prefere?"
            self.current_state = State.ESCOLHER_FILME
            self.show_movies()
            return "Não identifiquei o filme. Informe o número ou escreva o nome do filme."

        if detected_state == State.HORARIO or self.current_state == State.HORARIO:
            if self.selected_movie_id is None:
                self.current_state = State.ESCOLHER_FILME
                self.show_movies()
                return "Primeiro escolha o filme (ex.: 'Filme 1')."
            time = self.extract_showtime(raw)
            if time and time in self.showtimes.get(self.selected_movie_id, []):
                self.selected_showtime = time
                self.current_state = State.INGRESSO
                return f"Sessão escolhida: {self.movies[self.selected_movie_id]} às {time}. Quantos ingressos deseja?"
            m = re.search(r"\bopcao\s*(\d{1,2})\b", raw, re.IGNORECASE)
            if m:
                try:
                    opt = int(m.group(1))
                    times = self.showtimes.get(self.selected_movie_id, [])
                    if 1 <= opt <= len(times):
                        self.selected_showtime = times[opt - 1]
                        self.current_state = State.INGRESSO
                        return f"Sessão escolhida: {self.movies[self.selected_movie_id]} às {self.selected_showtime}. Quantos ingressos deseja?"
                except:
                    pass
            self.current_state = State.HORARIO
            self.show_showtimes(self.selected_movie_id)
            return "Não reconheci o horário. Informe no formato '16:30' ou 'opção 2'."

        if detected_state == State.INGRESSO or self.current_state == State.INGRESSO:
            # Usa a nova função robusta
            qty = self.extract_quantity(raw)
            if qty is not None:
                # validações adicionais
                if qty <= 0:
                    self.current_state = State.INGRESSO
                    return "Número inválido de ingressos. Informe um número positivo (ex.: '2')."
                if qty > 20:
                    # limite arbitrário para evitar erros; ajuste conforme necessário
                    self.current_state = State.INGRESSO
                    return "Escolha no máximo 20 ingressos por compra. Informe uma quantidade menor."
                self.selected_quantity = qty
                self.current_state = State.PAGAMENTO
                total = self.selected_quantity * self.ticket_price
                return f"Você escolheu {self.selected_quantity} ingresso(s). Total: R$ {total:.2f}. Deseja prosseguir para pagamento? (digite 'pagar' para confirmar)"
            else:
                # não conseguiu extrair quantidade -> pede clarificação
                self.current_state = State.INGRESSO
                return "Quantos ingressos você quer? Informe um número (ex.: '2') ou por extenso ('dois')."

        if detected_state == State.PAGAMENTO or self.current_state == State.PAGAMENTO:
            if re.search(r"\b(pagar|confirmar|sim)\b", norm):
                total = self.selected_quantity * self.ticket_price
                result = f"Pagamento de R$ {total:.2f} confirmado. Seus ingressos para '{self.movies[self.selected_movie_id]}' às {self.selected_showtime} foram reservados. Bom filme!"
                self._reset_session()
                self.current_state = State.SAUDACAO
                return result
            else:
                self.current_state = State.PAGAMENTO
                return "Deseja confirmar o pagamento? Digite 'pagar' para confirmar ou 'cancelar' para abortar."

        # fallback
        if self.selected_movie_id is None:
            self.current_state = State.ESCOLHER_FILME
            self.show_movies()
            return "Desculpe, não entendi. Escolha um filme pelo número ou nome."
        if self.selected_showtime is None:
            self.current_state = State.HORARIO
            self.show_showtimes(self.selected_movie_id)
            return "Qual horário prefere? Informe '16:30' ou 'opção 2'."
        if self.selected_quantity == 0:
            self.current_state = State.INGRESSO
            return "Quantos ingressos você deseja?"
        self.current_state = State.PAGAMENTO
        return "Deseja efetuar o pagamento agora? (digite 'pagar')"

    def _reset_session(self):
        self.selected_movie_id = None
        self.selected_showtime = None
        self.selected_quantity = 0

    def run(self):
        print("Bem-vindo ao Assistente de Cinema 🎬! (digite 'sair' para encerrar)")
        print(self.respond(None, ""))

        while True:
            try:
                user_input = input("\nVocê: ")
            except (KeyboardInterrupt, EOFError):
                print("\nBot: Encerrando... Até logo!")
                sys.exit(0)

            if user_input is None:
                user_input = ""

            low = self.normalize(user_input)
            if low in ("sair", "finalizar", "encerrar", "fim", "exit"):
                print("Bot: Obrigado! Até a próxima.")
                break

            detected = self.detect_state(user_input)
            reply = self.respond(detected, user_input)
            print("Bot:", reply)

            if self.current_state == State.SAUDACAO:
                print("\n--- Pronto para novo atendimento ---")

if __name__ == "__main__":
    assistant = CinemaAssistant()
    assistant.run()
