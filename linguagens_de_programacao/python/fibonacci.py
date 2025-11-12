# Programa que gera os primeiros N números da sequência de Fibonacci.
# Exemplo: se N = 6 → 1, 1, 2, 3, 5, 8

# Solicita que o usuário digite um número inteiro
n = int(input("Digite um número inteiro N: "))

# Declara os dois primeiros números da sequência
primeiro = 1
segundo = 1

# Caso o usuário digite 1
if n == 1:
    print(primeiro)
# Caso o usuário digite 2 ou mais
elif n >= 2:
    print(primeiro, segundo, end=" ")  # Imprime os dois primeiros números
    # Calcula os próximos termos da sequência
    for i in range(3, n + 1):
        proximo = primeiro + segundo  # Soma dos dois anteriores
        print(proximo, end=" ")  # Exibe o número sem quebra de linha
        primeiro = segundo  # Atualiza os valores
        segundo = proximo
    print()  # Quebra de linha ao final
# Caso N seja inválido
else:
    print("Por favor, digite um número maior que zero.")
