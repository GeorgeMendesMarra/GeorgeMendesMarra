import random  # Módulo para geração de números aleatórios (embaralhar baralho)

# Definição dos naipes das cartas
NAIPES = ["Paus", "Ouros", "Copas", "Espadas"]

# Definição dos valores das cartas, com seus respectivos símbolos para impressão
VALORES = {
    2: "2", 3: "3", 4: "4", 5: "5", 6: "6", 7: "7", 8: "8", 9: "9",
    10: "10", 11: "J", 12: "Q", 13: "K", 14: "A"
}

class Carta:
    """Representa uma única carta de baralho."""
    def __init__(self, valor, naipe):
        self.valor = valor
        self.naipe = naipe

    def __str__(self):
        """Retorna uma representação legível da carta."""
        return f"{VALORES[self.valor]} de {self.naipe}"

class Baralho:
    """Representa um baralho de 52 cartas."""
    def __init__(self):
        self.cartas = self._criar_baralho()

    def _criar_baralho(self):
        """Cria um baralho completo de 52 cartas."""
        baralho = []
        for naipe in NAIPES:
            for valor in VALORES:
                baralho.append(Carta(valor, naipe))
        return baralho

    def embaralhar(self):
        """Embaralha as cartas do baralho aleatoriamente."""
        random.shuffle(self.cartas)

    def distribuir_carta(self):
        """Distribui a carta do topo do baralho."""
        if self.cartas:
            return self.cartas.pop(0)
        return None  # Retorna None se o baralho estiver vazio

class Jogador:
    """Representa um jogador no jogo de poker."""
    def __init__(self, nome, fichas_iniciais):
        self.nome = nome
        self.mao = []  # Lista para armazenar as cartas na mão do jogador
        self.fichas = fichas_iniciais

    def receber_carta(self, carta):
        """Adiciona uma carta à mão do jogador."""
        self.mao.append(carta)

    def apostar(self, valor):
        """Remove fichas do jogador para fazer uma aposta."""
        if valor <= self.fichas:
            self.fichas -= valor
            return valor
        else:
            print(f"{self.nome} não tem fichas suficientes para apostar {valor}.")
            return 0  # Retorna 0 se a aposta não puder ser feita

    def ganhar_fichas(self, valor):
        """Adiciona fichas ao jogador."""
        self.fichas += valor

    def mostrar_mao(self):
        """Exibe as cartas na mão do jogador."""
        print(f"{self.nome} tem na mão: {[str(carta) for carta in self.mao]}")

    def limpar_mao(self):
        """Remove todas as cartas da mão do jogador."""
        self.mao = []

def fase_de_apostas(jogadores, pote, aposta_atual):
    """Simula uma rodada de apostas."""
    scanner = input  # Usa a função input para obter a ação do jogador
    jogadores_ativos = [jogador for jogador in jogadores if jogador.mao]
    jogadores_que_apostaram = set()
    ultima_aposta = aposta_atual

    # Determina o índice do primeiro jogador a agir (após o dealer na primeira rodada)
    primeiro_a_agir_indice = 0
    if jogadores:
        primeiro_a_agir = jogadores[0] # Assumindo um dealer fixo por simplicidade
        if primeiro_a_agir in jogadores_ativos:
            primeiro_a_agir_indice = jogadores_ativos.index(primeiro_a_agir)

    indice_atual = primeiro_a_agir_indice
    rodada_terminou = False

    while not rodada_terminou and jogadores_ativos:
        jogador_atual = jogadores_ativos[indice_atual % len(jogadores_ativos)]

        if jogador_atual not in jogadores_que_apostaram or ultima_aposta > 0:
            para_cobrir = ultima_aposta - (jogador_atual.aposta_atual if hasattr(jogador_atual, 'aposta_atual') else 0)
            print(f"\nVez de {jogador_atual.nome} (fichas: {jogador_atual.fichas}), Pote: {pote}, Aposta atual: {ultima_aposta}, Precisa cobrir: {para_cobrir}")
            acao = scanner("(c)heck/(a)postar/(f)old: ").lower()

            if acao == 'c':
                if para_cobrir == 0:
                    jogadores_que_apostaram.add(jogador_atual)
                else:
                    print("Você precisa cobrir a aposta para dar check.")
                    continue
            elif acao == 'a':
                valor_aposta = int(scanner("Valor da aposta: "))
                if valor_aposta >= para_cobrir and jogador_atual.fichas >= valor_aposta:
                    aposta_feita = jogador_atual.apostar(valor_aposta)
                    pote += aposta_feita
                    jogador_atual.aposta_atual = (jogador_atual.aposta_atual if hasattr(jogador_atual, 'aposta_atual') else 0) + aposta_feita
                    ultima_aposta = jogador_atual.aposta_atual
                    jogadores_que_apostaram.add(jogador_atual)
                    # Reinicia o conjunto de jogadores que apostaram para a nova aposta
                    jogadores_que_apostaram = {jogador_atual}
                else:
                    print("Aposta inválida.")
                    continue
            elif acao == 'f':
                print(f"{jogador_atual.nome} foldou.")
                jogadores_ativos.remove(jogador_atual)
            else:
                print("Ação inválida.")
                continue

        indice_atual += 1
        if all(jogador in jogadores_que_apostaram for jogador in jogadores_ativos) and all((jogador.aposta_atual if hasattr(jogador, 'aposta_atual') else 0) == ultima_aposta for jogador in jogadores_ativos):
            rodada_terminou = True
        elif not jogadores_ativos:
            rodada_terminou = True

    # Limpa o valor da aposta atual para a próxima rodada de apostas
    for jogador in jogadores_ativos:
        if hasattr(jogador, 'aposta_atual'):
            del jogador.aposta_atual

    return pote, ultima_aposta, jogadores_ativos

def determinar_vencedor(jogadores_ativos, mesa):
    """Determina o vencedor da rodada (implementação básica)."""
    if not jogadores_ativos:
        print("Nenhum jogador ativo para determinar o vencedor.")
        return None

    # Implementação muito simplificada: o jogador com a mão não vazia vence
    vencedor = jogadores_ativos[0]
    print(f"\n--- Showdown ---")
    for jogador in jogadores_ativos:
        jogador.mostrar_mao()
    print(f"Mesa: {[str(carta) for carta in mesa]}")
    print(f"{vencedor.nome} vence a rodada (mão não avaliada).")
    return vencedor

def jogar_rodada(jogadores):
    """Simula uma única rodada de poker."""
    baralho = Baralho()
    baralho.embaralhar()
    mesa = []
    pote = 0
    aposta_atual = 0

    # Limpa as mãos dos jogadores
    for jogador in jogadores:
        jogador.limpar_mao()

    # Distribui 2 cartas para cada jogador
    for _ in range(2):
        for jogador in jogadores:
            carta = baralho.distribuir_carta()
            if carta:
                jogador.receber_carta(carta)
            else:
                print("Erro: Baralho acabou.")
                return

    # Fase de apostas pré-flop
    pote, aposta_atual, jogadores_ativos = fase_de_apostas(jogadores, pote, aposta_atual)
    if len(jogadores_ativos) <= 1:
        return jogadores_ativos[0] if jogadores_ativos else None, pote

    # Flop (3 cartas comunitárias)
    print("\n--- Flop ---")
    for _ in range(3):
        carta = baralho.distribuir_carta()
        if carta:
            mesa.append(carta)
        else:
            print("Erro: Baralho acabou.")
            return
    print(f"Mesa: {[str(carta) for carta in mesa]}")
    pote, aposta_atual, jogadores_ativos = fase_de_apostas(jogadores_ativos, pote, aposta_atual)
    if len(jogadores_ativos) <= 1:
        return jogadores_ativos[0] if jogadores_ativos else None, pote

    # Turn (1 carta comunitária)
    print("\n--- Turn ---")
    carta = baralho.distribuir_carta()
    if carta:
        mesa.append(carta)
    else:
        print("Erro: Baralho acabou.")
        return
    print(f"Mesa: {[str(carta) for carta in mesa]}")
    pote, aposta_atual, jogadores_ativos = fase_de_apostas(jogadores_ativos, pote, aposta_atual)
    if len(jogadores_ativos) <= 1:
        return jogadores_ativos[0] if jogadores_ativos else None, pote

    # River (1 carta comunitária)
    print("\n--- River ---")
    carta = baralho.distribuir_carta()
    if carta:
        mesa.append(carta)
    else:
        print("Erro: Baralho acabou.")
        return
    print(f"Mesa: {[str(carta) for carta in mesa]}")
    pote, aposta_atual, jogadores_ativos = fase_de_apostas(jogadores_ativos, pote, aposta_atual)

    # Determinar o vencedor
    vencedor = determinar_vencedor(jogadores_ativos, mesa)
    return vencedor, pote

def main():
    """Função principal para iniciar o jogo."""
    scanner = input
    num_jogadores = int(scanner("Digite o número de jogadores: "))
    jogadores = []
    for i in range(num_jogadores):
        nome = scanner(f"Digite o nome do Jogador {i+1}: ")
        fichas = 100  # Fichas iniciais para cada jogador
        jogadores.append(Jogador(nome, fichas))

    while any(jogador.fichas > 0 for jogador in jogadores) and len([j for j in jogadores if j.fichas > 0]) > 1:
        vencedor, pote_da_rodada = jogar_rodada(jogadores)
        if vencedor:
            vencedor.ganhar_fichas(pote_da_rodada)
            print(f"{vencedor.nome} ganhou {pote_da_rodada} fichas nesta rodada.")
        else:
            print("Nenhum vencedor nesta rodada.")

        print("\n--- Fim da Rodada ---")
        for jogador in jogadores:
            print(f"{jogador.nome} tem {jogador.fichas} fichas.")

        jogar_novamente = scanner("Jogar outra rodada? (s/n): ").lower()
        if jogar_novamente != 's':
            break

    print("\n--- Fim do Jogo ---")
    for jogador in jogadores:
        print(f"{jogador.nome} terminou com {jogador.fichas} fichas.")

if __name__ == "__main__":
    main()
