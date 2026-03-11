# criando uma pilha vazia
pilha = []

# empilhando elementos (push)
pilha.append(10)
pilha.append(20)
pilha.append(30)

print("Pilha após inserções:", pilha)

# removendo o elemento do topo (pop)
elemento = pilha.pop()

print("Elemento removido:", elemento)
print("Pilha após remoção:", pilha)

# visualizando o topo da pilha
topo = pilha[-1]
print("Topo da pilha:", topo)
