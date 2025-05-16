import random

def monte_carlo_simples():
    """
    Realiza uma simulação simples de Monte Carlo para estimar a probabilidade
    de um evento com uma dada probabilidade de sucesso.

    A ideia básica é repetir um experimento aleatório muitas vezes e observar
    a frequência com que um determinado resultado ocorre. Essa frequência
    relativa é então usada como uma estimativa da probabilidade do evento.
    """
    num_simulacoes = 10000  # Define o número de vezes que a simulação será executada.
    # Um número maior de simulações tende a fornecer uma estimativa mais precisa
    # da probabilidade real, seguindo a Lei dos Grandes Números.

    eventos_favoraveis = 0  # Inicializa um contador para o número de vezes que o
    # evento de interesse (que definimos como "sucesso") ocorre durante as simulações.

    probabilidade_sucesso = 0.6  # Define a probabilidade teórica de sucesso para cada
    # tentativa individual na simulação. Neste caso, há uma chance de 60% de sucesso.

    print("Simulação de Monte Carlo Simples em Python")
    print(f"Número de Simulações: {num_simulacoes}")
    print(f"Probabilidade de Sucesso em cada tentativa: {probabilidade_sucesso}")
    print("-" * 30)

    # Inicia o loop principal da simulação. Cada iteração representa uma tentativa
    # independente do evento que estamos modelando.
    for _ in range(num_simulacoes):
        # Gera um número de ponto flutuante aleatório no intervalo [0.0, 1.0).
        # Este número aleatório simula o resultado de uma única tentativa.
        resultado_aleatorio = random.random()

        # Verifica se o resultado aleatório é menor que a probabilidade de sucesso.
        # Se for, consideramos que a tentativa foi um "evento favorável" (sucesso).
        if resultado_aleatorio < probabilidade_sucesso:
            eventos_favoraveis += 1  # Incrementa o contador de eventos favoráveis.

    # Após a conclusão de todas as simulações, calculamos a probabilidade estimada
    # do evento favorável ocorrer. Esta estimativa é a proporção do número de
    # eventos favoráveis observados em relação ao número total de simulações realizadas.
    probabilidade_estimada = eventos_favoraveis / num_simulacoes

    # Imprime os resultados finais da simulação para o usuário.
    # Isso inclui o número total de eventos favoráveis que foram contados
    # e a probabilidade estimada do evento com base na simulação de Monte Carlo.
    print(f"Número de Eventos Favoráveis: {eventos_favoraveis}")
    print(f"Probabilidade Estimada do Evento Favorável: {probabilidade_estimada}")

# Este bloco de código garante que a função 'monte_carlo_simples()' seja chamada
# apenas quando o script Python é executado diretamente (e não quando é importado
# como um módulo em outro script).
if __name__ == "__main__":
    monte_carlo_simples()
