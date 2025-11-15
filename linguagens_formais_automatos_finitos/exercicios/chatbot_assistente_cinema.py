#!/usr/bin/env python3
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
        self._reset_session()

    # ---------- Normalização (acentos -> sem acento) ----------
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
            "à": "a", "è": "e", "ì": "i", "ò": "o", "ù": "u",
            "º": "", "nº": ""
        }
        for k, v in replacements.items():
            s = s.replace(k, v)
        # mantém ':' para horários; remove outra pontuação
        s = re.sub(r"[^\w\s:]", " ", s)
        s = re.sub(r"\s+", " ", s).strip()
        return s

    # ---------- Extração de quantidade (robusta) ----------
    def extract_quantity(self, user_input: str):
        if not user_input:
            return None

        s = self.normalize(user_input)

        # 1) procura números arábicos
        m = re.search(r"\b(\d{1,2})\b", s)
        if m:
            try:
                n = int(m.group(1))
                if n <= 0 or n > 30:
                    return None
                return n
            except ValueError:
                pass

        # 2) palavras por extenso
        words_to_nums = {
            "zero": 0, "um": 1, "uma": 1, "dois": 2, "duas": 2, "tres": 3,
            "quatro": 4, "cinco": 5, "seis": 6, "sete": 7, "oito": 8,
            "nove": 9, "dez": 10, "onze": 11, "doze": 12, "treze": 13,
            "quatorze": 14, "catorze": 14, "quinze": 15, "dezesseis": 16,
            "dezessete": 17, "dezoito": 18, "dezenove": 19, "vinte": 20
        }
        for word, num in words_to_nums.items():
            if re.search(rf"\b{word}\b", s):
                if num <= 0 or num > 30:
                    return None
                return num

        return None

    # ---------- Extrair filme/horario ----------
    def extract_movie_id(self, user_input: str):
        if not user_input:
            return None
        # "filme 2" ou "2" (mantemos busca sem normalizar para números)
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
        # casar por nome parcial (usando normalização para ignorar acentos)
        s_norm = self.normalize(user_input)
        for mid, title in self.movies.items():
            title_norm = self.normalize(title)
            if title_norm in s_norm or s_norm in title_norm:
                return mid
        return None

    def extract_showtime(self, user_input: str):
        if not user_input:
            return None
        # formato HH:MM
        m = re.search(r"\b(\d{1,2}:\d{2})\b", user_input)
        if m:
            hhmm = m.group(1)
            parts = hhmm.split(":")
            h = int(parts[0])
            mnt = int(parts[1])
            return f"{h:02d}:{mnt:02d}"
        # formato "16h" ou "16 h"
        m2 = re.search(r"\b(\d{1,2})\s*h\b", user_input.lower())
        if m2:
            h = int(m2.group(1))
            return f"{h:02d}:00"
        return None

    # ---------- Mensagens ----------
    def show_movies(self):
        out = ["Filmes em cartaz:"]
        for mid, title in self.movies.items():
            out.append(f"  {mid}) {title}")
        out.append("Digite o número do filme (ex.: '1' ou 'Filme 1') ou escreva o nome do filme.")
        return "\n".join(out)

    def show_showtimes(self, movie_id: int):
        times = self.showtimes.get(movie_id, [])
        if not times:
            return "Desculpe, não há sessões cadastradas para esse filme."
        out = [f"Horários para '{self.movies[movie_id]}':"]
        for i, t in enumerate(times, start=1):
            out.append(f"  {i}) {t}")
        out.append("Informe o horário (ex.: '16:30' ou 'opção 2').")
        return "\n".join(out)

    # ---------- Detect (apenas auxílio) ----------
    def detect_state(self, user_input: str):
        s = self.normalize(user_input)
        # usa string normalizada aqui para garantir aceitação com/sem acento
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

    # ---------- Handlers dirigidos pelo estado atual ----------
    def handle_saudacao(self, user_input: str):
        self.current_state = State.ESCOLHER_FILME
        return self.show_movies()

    def handle_escolher_filme(self, user_input: str):
        mid = self.extract_movie_id(user_input)
        if mid and mid in self.movies:
            self.selected_movie_id = mid
            self.current_state = State.HORARIO
            return self.show_showtimes(mid)
        # se não identificou, pede novo input mantendo estado
        self.current_state = State.ESCOLHER_FILME
        return self.show_movies()

    def handle_horario(self, user_input: str):
        if self.selected_movie_id is None:
            self.current_state = State.ESCOLHER_FILME
            return "Primeiro escolha o filme.\n" + self.show_movies()

        # se user informou "opcao N"
        m = re.search(r"\bopcao\s*(\d{1,2})\b", user_input, re.IGNORECASE)
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

        time = self.extract_showtime(user_input)
        if time and time in self.showtimes.get(self.selected_movie_id, []):
            self.selected_showtime = time
            self.current_state = State.INGRESSO
            return f"Sessão escolhida: {self.movies[self.selected_movie_id]} às {time}. Quantos ingressos deseja?"
        # não reconheceu
        self.current_state = State.HORARIO
        return self.show_showtimes(self.selected_movie_id)

    def handle_ingresso(self, user_input: str):
        qty = self.extract_quantity(user_input)
        if qty is None:
            self.current_state = State.INGRESSO
            return "Quantos ingressos você quer? Informe um número (ex.: '2') ou por extenso ('dois')."
        if qty <= 0:
            self.current_state = State.INGRESSO
            return "Número inválido de ingressos. Informe um número positivo (ex.: '2')."
        if qty > 20:
            self.current_state = State.INGRESSO
            return "Escolha no máximo 20 ingressos por compra. Informe uma quantidade menor."

        self.selected_quantity = qty
        self.current_state = State.PAGAMENTO
        total = self.selected_quantity * self.ticket_price
        return f"Você escolheu {self.selected_quantity} ingresso(s). Total: R$ {total:.2f}. Deseja prosseguir para pagamento? (digite 'pagar' para confirmar)"

    def handle_pagamento(self, user_input: str):
        norm = self.normalize(user_input)
        if re.search(r"\b(pagar|confirmar|sim)\b", norm):
            total = self.selected_quantity * self.ticket_price
            result = (f"Pagamento de R$ {total:.2f} confirmado. "
                      f"Seus ingressos para '{self.movies[self.selected_movie_id]}' às {self.selected_showtime} foram reservados. Bom filme!")
            self._reset_session()
            self.current_state = State.SAUDACAO
            return result
        if re.search(r"\b(cancelar|nao|nao)\b", norm):
            self._reset_session()
            self.current_state = State.SAUDACAO
            return "Compra cancelada. Posso ajudar com outro filme?"
        self.current_state = State.PAGAMENTO
        return "Deseja confirmar o pagamento? Digite 'pagar' para confirmar ou 'cancelar' para abortar."

    # ---------- Dispatcher (sempre usa current_state) ----------
    def respond(self, user_input: str):
        raw = user_input or ""
        if self.current_state == State.SAUDACAO:
            return self.handle_saudacao(raw)
        if self.current_state == State.ESCOLHER_FILME:
            return self.handle_escolher_filme(raw)
        if self.current_state == State.HORARIO:
            return self.handle_horario(raw)
        if self.current_state == State.INGRESSO:
            return self.handle_ingresso(raw)
        if self.current_state == State.PAGAMENTO:
            return self.handle_pagamento(raw)
        # fallback seguro
        self.current_state = State.SAUDACAO
        return "Desculpe, não entendi. Vamos recomeçar."

    def _reset_session(self):
        self.selected_movie_id = None
        self.selected_showtime = None
        self.selected_quantity = 0

    def run(self):
        print("Bem-vindo ao Assistente de Cinema 🎬! (digite 'sair' para encerrar)")
        print(self.respond(""))

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

            reply = self.respond(user_input)
            print("Bot:", reply)

            if self.current_state == State.SAUDACAO:
                print("\n--- Pronto para novo atendimento ---")

if __name__ == "__main__":
    assistant = CinemaAssistant()
    assistant.run()
