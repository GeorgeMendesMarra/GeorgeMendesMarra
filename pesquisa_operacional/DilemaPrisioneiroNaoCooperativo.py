import random  # Importa o módulo random para gerar escolhas aleatórias.

# Define as possíveis escolhas para os prisioneiros.
ESCOLHAS = ["COOPERAR", "TRAIR"]

class PrisioneiroNaoCooperativo:
    """Representa um prisioneiro que toma decisões de forma não cooperativa."""
    def __init__(self, nome):
        """
        Inicializa um prisioneiro com um nome.

        Args:
            nome (str): O nome do prisioneiro.
        """
        self.nome = nome

    def escolher(self, escolha_outro=None):
        """
        Realiza a escolha do prisioneiro sem considerar comunicação ou acordos.

        Args:
            escolha_outro (str, optional): A escolha do outro prisioneiro (pode ser None na primeira rodada).
                                           Não é usado nesta estratégia simples.

        Returns:
            str: A escolha do prisioneiro ("COOPERAR" ou "TRAIR").
        """
        # Estratégia simples: escolher aleatoriamente entre cooperar e trair.
        return random.choice(ESCOLHAS)

class DilemaDoPrisioneiroNaoCooperativo:
    """Simula uma única rodada do Dilema do Prisioneiro não cooperativo."""
    def __init__(self, prisioneiro1, prisioneiro2):
        """
        Inicializa o jogo com dois prisioneiros.

        Args:
            prisioneiro1 (PrisioneiroNaoCooperativo): O primeiro prisioneiro.
            prisioneiro2 (PrisioneiroNaoCooperativo): O segundo prisioneiro.
        """
        self.prisioneiro1 = prisioneiro1
        self.prisioneiro2 = prisioneiro2

    def jogar(self):
        """
        Simula uma rodada do dilema, onde cada prisioneiro faz sua escolha.

        Returns:
            tuple: Uma tupla contendo as escolhas do prisioneiro 1 e do prisioneiro 2.
        """
        # Cada prisioneiro faz sua escolha independentemente.
        escolha1 = self.prisioneiro1.escolher()
        escolha2 = self.prisioneiro2.escolher()
        return escolha1, escolha2

    def determinar_sentencas(self, escolha1, escolha2):
        """
        Determina a sentença de cada prisioneiro com base nas suas escolhas.

        Args:
            escolha1 (str): A escolha do prisioneiro 1 ("COOPERAR" ou "TRAIR").
            escolha2 (str): A escolha do prisioneiro 2 ("COOPERAR" ou "TRAIR").

        Returns:
            tuple: Uma tupla contendo a sentença do prisioneiro 1 e a sentença do prisioneiro 2 (em anos).
        """
        if escolha1 == "COOPERAR" and escolha2 == "COOPERAR":
            return 2, 2  # Ambos cooperam: sentença menor para ambos.
        elif escolha1 == "COOPERAR" and escolha2 == "TRAIR":
            return 10, 0 # Um coopera, o outro trai: quem coopera pega a sentença maior, quem trai é livre.
        elif escolha1 == "TRAIR" and escolha2 == "COOPERAR":
            return 0, 10 # Um trai, o outro coopera: quem trai é livre, quem coopera pega a sentença maior.
        else:  # escolha1 == "TRAIR" and escolha2 == "TRAIR"
            return 5, 5  # Ambos traem: sentença moderada para ambos.

if __name__ == "__main__":
    # Cria dois prisioneiros não cooperativos.
    prisioneiro_a = PrisioneiroNaoCooperativo("Prisioneiro A")
    prisioneiro_b = PrisioneiroNaoCooperativo("Prisioneiro B")

    # Cria uma instância do jogo do Dilema do Prisioneiro não cooperativo.
    jogo = DilemaDoPrisioneiroNaoCooperativo(prisioneiro_a, prisioneiro_b)

    # Simula uma rodada do jogo.
    escolha_a, escolha_b = jogo.jogar()

    # Determina as sentenças com base nas escolhas.
    sentenca_a, sentenca_b = jogo.determinar_sentencas(escolha_a, escolha_b)

    # Exibe as escolhas e as sentenças.
    print(f"{prisioneiro_a.nome} escolheu: {escolha_a}")
    print(f"{prisioneiro_b.nome} escolheu: {escolha_b}")
    print(f"{prisioneiro_a.nome} recebeu {sentenca_a} anos de prisão.")
    print(f"{prisioneiro_b.nome} recebeu {sentenca_b} anos de prisão.")
