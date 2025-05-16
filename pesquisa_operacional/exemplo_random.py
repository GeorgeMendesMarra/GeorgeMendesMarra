import random  # Importa o módulo 'random' que fornece funções para gerar números aleatórios.

def exemplo_random_inteiro():
    """
    Exemplos de como gerar números inteiros aleatórios em Python usando o módulo 'random'.
    """
    # Cria um gerador de números aleatórios. Embora não seja estritamente necessário
    # criar uma instância separada para casos simples, é uma boa prática para
    # maior controle ou para usar diferentes geradores com sementes distintas.
    gerador = random.Random()

    # -------------------- Gerando um número inteiro aleatório (sem limites específicos) --------------------
    # A função random.randint(a, b) retorna um inteiro aleatório N tal que a <= N <= b.
    # Para simular o nextInt() do Java sem um limite, podemos usar a função random.getrandbits(k)
    # para obter um inteiro com k bits aleatórios. No entanto, para um comportamento mais
    # semelhante ao nextInt() sem argumentos (que pode ser negativo), podemos simplesmente
    # gerar um número aleatório grande dentro da faixa de inteiros do Python.
    numero_aleatorio1 = gerador.randint(-1000000, 1000000)  # Exemplo de faixa grande
    print(f"Número inteiro aleatório (faixa ampla): {numero_aleatorio1}")

    # -------------------- Gerando um número inteiro aleatório dentro de um limite superior --------------------
    # A função random.randrange(stop) retorna um inteiro aleatório no intervalo
    # [0, stop). 'stop' não é incluído.
    limite_superior = 10
    numero_aleatorio2 = gerador.randrange(limite_superior)
    print(f"Número inteiro aleatório (0 até {limite_superior - 1}): {numero_aleatorio2}")

    # -------------------- Gerando um número inteiro aleatório dentro de uma faixa específica (min e max inclusivos) --------------------
    # A função random.randint(a, b) retorna um inteiro aleatório N tal que a <= N <= b.
    min_valor = 5
    max_valor = 15
    numero_aleatorio_na_faixa = gerador.randint(min_valor, max_valor)
    print(f"Número inteiro aleatório ({min_valor} até {max_valor}): {numero_aleatorio_na_faixa}")

def exemplo_random_float():
    """
    Exemplos de como gerar números de ponto flutuante aleatórios em Python usando o módulo 'random'.
    """
    gerador = random.Random()

    # -------------------- Gerando um número float aleatório entre 0.0 (inclusive) e 1.0 (exclusive) --------------------
    # A função random.random() retorna o próximo número de ponto flutuante aleatório no intervalo [0.0, 1.0).
    numero_aleatorio1 = gerador.random()
    print(f"Número float aleatório (0.0 até 1.0): {numero_aleatorio1}")

    # -------------------- Gerando um número float aleatório dentro de uma faixa específica (min inclusive, max exclusive) --------------------
    min_valor = 1.0
    max_valor = 5.0
    # Para gerar um float em uma faixa [min, max), podemos escalar e deslocar o resultado de random().
    numero_aleatorio_na_faixa = min_valor + (max_valor - min_valor) * gerador.random()
    print(f"Número float aleatório ({min_valor} até {max_valor}): {numero_aleatorio_na_faixa}")

def exemplo_random_booleano():
    """
    Exemplo de como gerar um valor booleano aleatório em Python usando o módulo 'random'.
    """
    gerador = random.Random()

    # -------------------- Gerando um valor booleano aleatório (True ou False) com probabilidade aproximadamente igual --------------------
    # Podemos gerar um inteiro aleatório (0 ou 1) e mapeá-lo para um booleano,
    # ou simplesmente verificar se um número aleatório entre 0 e 1 é menor que 0.5.
    valor_aleatorio = gerador.choice([True, False])
    print(f"Valor booleano aleatório: {valor_aleatorio}")

if __name__ == "__main__":
    print("--- Exemplos de Números Inteiros Aleatórios ---")
    exemplo_random_inteiro()
    print("\n--- Exemplos de Números de Ponto Flutuante Aleatórios ---")
    exemplo_random_float()
    print("\n--- Exemplo de Booleano Aleatório ---")
    exemplo_random_booleano()
