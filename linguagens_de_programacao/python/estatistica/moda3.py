dados = [1, 2, 3, 4, 2, 2, 3, 5, 2]

# Cria um dicionário de frequências
frequencia = {}
for item in dados:
    frequencia[item] = frequencia.get(item, 0) + 1

# Encontra a chave com o maior valor
moda = max(frequencia, key=frequencia.get)

print(f"A moda calculada manualmente é: {moda}")
