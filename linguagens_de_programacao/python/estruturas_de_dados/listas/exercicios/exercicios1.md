# 📚 Lista de Exercícios Resolvidos — Listas em Python

---

# 🟢 NÍVEL 1 — Básico

---

## ✅ 1) Criar uma lista com 5 números e mostrar o maior e o menor

```python
numeros = [10, 5, 30, 2, 18]

print("Maior:", max(numeros))
print("Menor:", min(numeros))
```

✔ Funções nativas eficientes
✔ Complexidade: O(n)

---

## ✅ 2) Calcular a média dos elementos

```python
numeros = [10, 20, 30, 40]

media = sum(numeros) / len(numeros)

print("Média:", media)
```

---

## ✅ 3) Adicionar um elemento ao final da lista

```python
lista = [1, 2, 3]

lista.append(4)

print(lista)
```

---

## ✅ 4) Inserir elemento em posição específica

```python
lista = [10, 20, 30]

lista.insert(1, 15)

print(lista)
```

---

# 🟡 NÍVEL 2 — Intermediário

---

## ✅ 5) Remover todos os números pares da lista

```python
numeros = [1,2,3,4,5,6,7,8]

numeros = [n for n in numeros if n % 2 != 0]

print(numeros)
```

✔ Uso de list comprehension
✔ Código mais elegante

---

## ✅ 6) Inverter uma lista sem usar reverse()

```python
lista = [1, 2, 3, 4]

invertida = lista[::-1]

print(invertida)
```

---

## ✅ 7) Contar quantas vezes um número aparece

```python
lista = [1,2,3,2,4,2,5]

quantidade = lista.count(2)

print("O número 2 aparece:", quantidade, "vezes")
```

---

## ✅ 8) Encontrar o segundo maior número

```python
numeros = [10, 5, 30, 25, 18]

numeros.sort()

print("Segundo maior:", numeros[-2])
```

⚠️ Alternativa sem modificar a lista:

```python
segundo = sorted(numeros)[-2]
```

---

# 🔵 NÍVEL 3 — Estrutura e Algoritmo

---

## ✅ 9) Implementar busca linear

```python
def busca(lista, valor):
    for i in range(len(lista)):
        if lista[i] == valor:
            return i
    return -1

lista = [10, 20, 30, 40]

print(busca(lista, 30))
```

✔ Complexidade: O(n)

---

## ✅ 10) Implementar ordenação manual (Bubble Sort)

```python
def bubble_sort(lista):
    n = len(lista)

    for i in range(n):
        for j in range(0, n-i-1):
            if lista[j] > lista[j+1]:
                lista[j], lista[j+1] = lista[j+1], lista[j]

    return lista

numeros = [5, 1, 4, 2, 8]

print(bubble_sort(numeros))
```

✔ Complexidade: O(n²)

---

## ✅ 11) Remover duplicados mantendo a ordem

```python
lista = [1,2,3,2,4,1,5]

nova_lista = []

for item in lista:
    if item not in nova_lista:
        nova_lista.append(item)

print(nova_lista)
```

---

# 🔴 NÍVEL 4 — Desafio

---

## ✅ 12) Intercalar duas listas

Entrada:
[1,2,3]
[4,5,6]

Saída:
[1,4,2,5,3,6]

```python
lista1 = [1,2,3]
lista2 = [4,5,6]

resultado = []

for i in range(len(lista1)):
    resultado.append(lista1[i])
    resultado.append(lista2[i])

print(resultado)
```

---

## ✅ 13) Rotacionar lista para direita

Exemplo:
[1,2,3,4] → [4,1,2,3]

```python
lista = [1,2,3,4]

lista = [lista[-1]] + lista[:-1]

print(lista)
```

---

## ✅ 14) Verificar se a lista é palíndromo

```python
lista = [1,2,3,2,1]

if lista == lista[::-1]:
    print("É palíndromo")
else:
    print("Não é palíndromo")
```

---

# 🧠 Questões Teóricas para Prova

1. Qual a complexidade do método append()?
2. Qual a diferença entre lista e tupla?
3. Quando usar list comprehension?
4. Por que remover itens enquanto percorre a lista pode causar erro?

---

# 🎯 Proposta Avaliativa

Projeto prático:

Criar sistema simples de:

* Cadastro de alunos
* Armazenar em lista
* Buscar aluno
* Remover aluno
* Listar ordenado

---

Qual formato o senhor prefere?

