# Programa que lê 5 números inteiros, armazena em uma lista
# e imprime os números na ordem original e na ordem inversa.

# Cria uma lista vazia
numeros = []

# Lê os 5 números
for i in range(5):
    numero = int(input(f"Digite o número {i + 1}: "))
    numeros.append(numero)  # Adiciona o número à lista

# Exibe os números na ordem original
print("\nNúmeros na ordem original:")
for n in numeros:
    print(n, end=" ")

# Exibe os números na ordem inversa
print("\n\nNúmeros na ordem inversa:")
for n in reversed(numeros):
    print(n, end=" ")

print()  # Quebra de linha final
