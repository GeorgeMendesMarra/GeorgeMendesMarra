# Programa que conta o número de vezes que a letra 'a' (maiúscula ou minúscula)
# aparece em uma frase digitada pelo usuário.

# Solicita que o usuário digite uma frase
frase = input("Digite uma frase: ")

# Inicializa o contador
contador = 0

# Percorre cada caractere da frase
for c in frase:
    # Verifica se o caractere é 'a' ou 'A'
    if c == 'a' or c == 'A':
        contador += 1  # Incrementa o contador

# Exibe o resultado final
print(f"A letra 'a' aparece {contador} vezes na frase.")
