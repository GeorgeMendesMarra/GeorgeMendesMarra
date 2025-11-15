"""

Chatbot de FAQ de biblioteca implementado como um autômato de estados finitos.

Cada categoria (horário, renovar livro, multas, localização, empréstimo) é tratada
como um estado. O chatbot detecta o tipo de pergunta, responde de acordo e
retorna ao estado inicial (IDLE) após cada resposta.

Uso:
    python chatbot_faq_biblioteca.py

Autor:
Data: 2025
"""

# Importa o módulo padrão para leitura de entrada do usuário
import sys

# Enumeração simples de estados
class State:
    IDLE = "IDLE"           # Estado inicial
    HOURS = "HOURS"         # Horário de funcionamento
    RENEW = "RENEW"         # Renovar livro
    FINES = "FINES"         # Multas
    LOCATION = "LOCATION"   # Localização / setores
    LOAN_INFO = "LOAN_INFO" # Informações de empréstimo
    UNKNOWN = "UNKNOWN"     # Pergunta não reconhecida


class LibraryFAQChatbot:
    """Classe principal que representa o chatbot de FAQ de biblioteca."""

    def __init__(self):
        # Estado atual começa em IDLE
        self.current_state = State.IDLE

    def detect_state(self, user_input: str) -> str:
        """
        Detecta o estado (categoria) com base na entrada do usuário.
        Utiliza correspondência simples por palavras-chave.

        :param user_input: texto digitado pelo usuário
        :return: estado detectado (string)
        """
        s = user_input.lower()

        # Identificação de estado por palavras-chave
        if any(word in s for word in ["horário", "aberto", "fech", "funcion"]):
            return State.HOURS
        if any(word in s for word in ["renov", "renovar", "prorroga"]):
            return State.RENEW
        if any(word in s for word in ["multa", "multas", "pagar"]):
            return State.FINES
        if any(word in s for word in ["onde", "localiza", "localização", "setor"]):
            return State.LOCATION
        if any(word in s for word in ["emprest", "prazo", "limite", "devolver"]):
            return State.LOAN_INFO

        # Caso nenhuma palavra-chave seja reconhecida
        return State.UNKNOWN

    def respond(self, state: str, user_input: str) -> str:
        """
        Retorna uma resposta baseada no estado detectado.
        Após responder, o estado retorna para IDLE.

        :param state: estado atual detectado
        :param user_input: texto original do usuário
        :return: resposta textual
        """
        if state == State.HOURS:
            response = ("Horário de funcionamento: segunda a sexta, das 08:00 às 18:00. "
                        "Aos sábados, das 08:00 às 12:00. Fechamos aos domingos e feriados.")
        elif state == State.RENEW:
            response = ("Para renovar um livro: acesse sua conta no portal da biblioteca "
                        "ou leve o exemplar ao balcão. Renovações online são permitidas até "
                        "2 dias antes do vencimento e se não houver reservas.")
        elif state == State.FINES:
            response = ("Multas: podem ser pagas no caixa da biblioteca ou via boleto online. "
                        "Consulte seu extrato no portal para ver valores e prazos.")
        elif state == State.LOCATION:
            response = ("Localização: a biblioteca fica no Bloco B, 2º andar. "
                        "Setor de Ciências Exatas no corredor A e Humanas no corredor B.")
        elif state == State.LOAN_INFO:
            response = ("Informações de empréstimo: o prazo é de 14 dias para alunos "
                        "e 28 dias para professores. O limite é 5 livros por usuário.")
        else:
            response = ("Desculpe, não entendi bem. Posso ajudar com: "
                        "horário, renovar livro, multas, localização ou empréstimos.")

        # Retorna o estado ao inicial após a resposta
        self.current_state = State.IDLE
        return response

    def run(self):
        """Loop principal de interação com o usuário (modo texto)."""
        print("Olá! Sou o assistente FAQ da biblioteca. Pergunte algo (digite 'sair' para encerrar).")

        while True:
            try:
                user_input = input("\nVocê: ").strip()
            except (KeyboardInterrupt, EOFError):
                print("\nBot: Encerrando... Até logo!")
                sys.exit(0)

            # Verifica se o usuário quer sair
            if user_input.lower() in ("sair", "exit", "fim"):
                print("Bot: Obrigado! Até mais.")
                break

            if not user_input:
                print("Bot: Por favor, digite uma pergunta.")
                continue

            # Detecta o estado e responde
            self.current_state = self.detect_state(user_input)
            response = self.respond(self.current_state, user_input)
            print("Bot:", response)


# Execução principal
if __name__ == "__main__":
    bot = LibraryFAQChatbot()
    bot.run()
