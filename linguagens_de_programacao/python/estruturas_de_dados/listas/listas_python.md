# 📚 Listas em Python

## 🎯 1. O que é uma Lista?

Lista é uma estrutura de dados:

* ✅ Ordenada
* ✅ Mutável
* ✅ Permite elementos duplicados
* ✅ Pode armazenar diferentes tipos de dados

Ela é uma das estruturas mais usadas em Python.

---

# 🟢 2. Criando Listas

## 📌 Lista simples

```python
numeros = [10, 20, 30, 40]
```

## 📌 Lista com tipos diferentes

```python
dados = ["Ana", 20, 1.75, True]
```

## 📌 Lista vazia

```python
lista = []
# ou
lista = list()
```

---

# 🔵 3. Acessando Elementos

Listas são indexadas (começam no índice 0).

```python
numeros = [10, 20, 30, 40]

print(numeros[0])  # 10
print(numeros[2])  # 30
```

## 📌 Índices negativos

```python
print(numeros[-1])  # último elemento
print(numeros[-2])  # penúltimo
```

---

# 🟡 4. Fatiamento (Slicing)

```python
lista = [1,2,3,4,5]

print(lista[1:4])  # [2,3,4]
print(lista[:3])   # [1,2,3]
print(lista[2:])   # [3,4,5]
```

---

# 🟠 5. Principais Métodos

## ✅ append() → adiciona no final

```python
lista = [1,2,3]
lista.append(4)
```

---

## ✅ insert() → insere em posição específica

```python
lista.insert(1, 10)
```

---

## ✅ remove() → remove pelo valor

```python
lista.remove(2)
```

---

## ✅ pop() → remove pelo índice

```python
lista.pop()      # remove o último
lista.pop(0)     # remove o índice 0
```

---

## ✅ sort() → ordena

```python
lista = [3,1,4,2]
lista.sort()
```

---

## ✅ reverse() → inverte

```python
lista.reverse()
```

---

## ✅ count() → conta ocorrências

```python
lista.count(2)
```

---

## ✅ index() → retorna posição

```python
lista.index(4)
```

---

# 🔴 6. Percorrendo Listas

## 📌 Usando for

```python
for numero in lista:
    print(numero)
```

---

## 📌 Usando índice

```python
for i in range(len(lista)):
    print(lista[i])
```

---

# 🟣 7. List Comprehension (Forma Moderna)

Forma elegante e eficiente:

```python
quadrados = [x**2 for x in range(5)]
```

Com condição:

```python
pares = [x for x in range(10) if x % 2 == 0]
```

---

# ⚫ 8. Operações com Listas

## ➕ Concatenação

```python
a = [1,2]
b = [3,4]

c = a + b
```

## ✖ Repetição

```python
lista = [1,2] * 3
```

---

# 🧠 9. Complexidade (Importante para Engenharia)

| Operação          | Complexidade |
| ----------------- | ------------ |
| Acesso por índice | O(1)         |
| append()          | O(1)         |
| insert()          | O(n)         |
| remove()          | O(n)         |
| busca             | O(n)         |

Python usa internamente **vetores dinâmicos (arrays dinâmicos)**.

---

# 🟤 10. Listas Aninhadas (Matrizes)

```python
matriz = [
    [1,2,3],
    [4,5,6],
    [7,8,9]
]

print(matriz[1][2])  # 6
```

---

# 🔶 11. Cópia de Listas

⚠️ Cuidado com referência:

```python
a = [1,2,3]
b = a  # mesma referência
```

Forma correta:

```python
b = a.copy()
# ou
b = a[:]
```

---

# 🟢 12. Aplicações Reais

✔ Cadastro de alunos
✔ Carrinho de compras
✔ Armazenar dados temporários
✔ Algoritmos de ordenação
✔ Simulações

---

# 🎯 Exercício Desafio

Crie um programa que:

1. Leia 10 números
2. Armazene em uma lista
3. Mostre:

   * Maior
   * Menor
   * Média
   * Lista ordenada

---
