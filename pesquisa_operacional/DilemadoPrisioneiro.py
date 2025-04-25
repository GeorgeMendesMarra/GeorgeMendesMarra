import random

def dilema_do_prisioneiro(jogador1, jogador2):
    """
    Simula uma rodada do Dilema do Prisioneiro entre dois jogadores.

    Args:
        jogador1: Uma função que representa a estratégia do jogador 1.
                  Deve retornar 'colaborar' ou 'trair'.
        jogador2: Uma função que representa a estratégia do jogador 2.
                  Deve retornar 'colaborar' ou 'trair'.

    Returns:
        Um dicionário contendo as escolhas de cada jogador e as sentenças resultantes.
    """
    # Obtém a escolha do jogador 1 chamando a função de sua estratégia
    escolha1 = jogador1()
    # Obtém a escolha do jogador 2 chamando a função de sua estratégia
    escolha2 = jogador2()

    # Cria um dicionário para armazenar as escolhas dos jogadores
    resultados = {
        'jogador1': escolha1,
        'jogador2': escolha2
    }

    # Determina as sentenças com base nas escolhas de ambos os jogadores
    if escolha1 == 'colaborar' and escolha2 == 'colaborar':
        resultados['sentenca_jogador1'] = 2  # Ambos colaboram: 2 anos para cada
        resultados['sentenca_jogador2'] = 2
        print("\nAmbos colaboraram. Ambos recebem 2 anos de prisão.")
    elif escolha1 == 'colaborar' and escolha2 == 'trair':
        resultados['sentenca_jogador1'] = 10 # Jogador 1 colabora, Jogador 2 trai: 10 anos para Jogador 1, 0 para Jogador 2
        resultados['sentenca_jogador2'] = 0
        print(f"\nJogador 1 colaborou e Jogador 2 traiu. Jogador 1 recebe 10 anos e Jogador 2 sai livre.")
    elif escolha1 == 'trair' and escolha2 == 'colaborar':
        resultados['sentenca_jogador1'] = 0  # Jogador 1 trai, Jogador 2 colabora: 0 anos para Jogador 1, 10 para Jogador 2
        resultados['sentenca_jogador2'] = 10
        print(f"\nJogador 1 traiu e Jogador 2 colaborou. Jogador 2 recebe 10 anos e Jogador 1 sai livre.")
    else:  # escolha1 == 'trair' and escolha2 == 'trair'
        resultados['sentenca_jogador1'] = 5  # Ambos traem: 5 anos para cada
        resultados['sentenca_jogador2'] = 5
        print("\nAmbos traíram. Ambos recebem 5 anos de prisão.")

    # Retorna um dicionário contendo as escolhas e as sentenças
    return resultados

def jogador_humano():
    """
    Permite que um humano faça sua escolha através da entrada do teclado.
    """
    while True:
        escolha = input("Você deseja colaborar (c) ou trair (t)? ").lower()
        # Verifica se a entrada é 'c' ou 't'
        if escolha in ['c', 't']:
            return 'colaborar' if escolha == 'c' else 'trair'
        else:
            print("Escolha inválida. Digite 'c' para colaborar ou 't' para trair.")

def jogador_aleatorio():
    """
    Faz uma escolha aleatória entre colaborar e trair.
    """
    return random.choice(['colaborar', 'trair'])

def jogador_sempre_colabora():
    """
    Implementa uma estratégia onde o jogador sempre escolhe colaborar.
    """
    return 'colaborar'

def jogador_sempre_trai():
    """
    Implementa uma estratégia onde o jogador sempre escolhe trair.
    """
    return 'trair'

# Bloco principal de execução do script
if __name__ == "__main__":
    print("Bem-vindo ao Dilema do Prisioneiro!")

    # Loop para permitir que o jogo seja jogado várias vezes
    while True:
        print("\nOpções de jogadores:")
        print("1: Humano")
        print("2: Aleatório")
        print("3: Sempre Colabora")
        print("4: Sempre Trai")

        try:
            # Solicita ao usuário que escolha a estratégia para o Jogador 1
            jogador1_opcao = int(input("Escolha o Jogador 1: "))
            # Solicita ao usuário que escolha a estratégia para o Jogador 2
            jogador2_opcao = int(input("Escolha o Jogador 2: "))

            # Cria um dicionário para mapear as opções numéricas às funções de estratégia
            jogadores = {
                1: jogador_humano,
                2: jogador_aleatorio,
                3: jogador_sempre_colabora,
                4: jogador_sempre_trai
            }

            # Verifica se as opções escolhidas são válidas
            if jogador1_opcao in jogadores and jogador2_opcao in jogadores:
                # Obtém as funções de estratégia com base nas opções
                jogador1 = jogadores[jogador1_opcao]
                jogador2 = jogadores[jogador2_opcao]
                # Simula uma rodada do dilema do prisioneiro
                resultados = dilema_do_prisioneiro(jogador1, jogador2)
                # Imprime os resultados da rodada
                print("\n--- Resultados da Rodada ---")
                print(f"Jogador 1 escolheu: {resultados['jogador1']}")
                print(f"Jogador 2 escolheu: {resultados['jogador2']}")
                print(f"Sentença Jogador 1: {resultados['sentenca_jogador1']} anos")
                print(f"Sentença Jogador 2: {resultados['sentenca_jogador2']} anos")
            else:
                print("Opção de jogador inválida.")

            # Pergunta ao usuário se deseja jogar novamente
            jogar_novamente = input("\nDeseja jogar novamente? (s/n): ").lower()
            # Se a resposta não for 's', o loop é interrompido
            if jogar_novamente != 's':
                break

        # Trata a exceção caso o usuário digite algo que não seja um número
        except ValueError:
            print("Por favor, digite um número para escolher o jogador.")

    print("\nObrigado por jogar!")
