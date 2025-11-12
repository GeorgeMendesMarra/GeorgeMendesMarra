# Programa que verifica se um número inteiro é primo.
# Um número primo só é divisível por 1 e por ele mesmo.

# Solicita que o usuário digite um número inteiro
numero = int(input("Digite um número inteiro: "))

# Assume que o número é primo (até que se prove o contrário)
eh_primo = True

# Números menores ou iguais a 1 não são primos
if numero <= 1:
    eh_primo = False
else:
    # Verifica divisores de 2 até a metade do número
    for i in range(2, numero // 2 + 1):
        if numero % i == 0:
            eh_primo = False  # Encontrou um divisor
            break  # Não precisa verificar mais

# Exibe o resultado
if eh_primo:
    print("É primo")
else:
    print("Não é primo")
