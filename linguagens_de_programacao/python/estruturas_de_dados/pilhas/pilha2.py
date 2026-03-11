pilha = []

def push(elemento):
    pilha.append(elemento)

def pop():
    if len(pilha) == 0:
        print("Pilha vazia")
    else:
        return pilha.pop()

push(5)
push(10)
push(15)

print("Pilha:", pilha)

print("Removido:", pop())

print("Pilha final:", pilha)
