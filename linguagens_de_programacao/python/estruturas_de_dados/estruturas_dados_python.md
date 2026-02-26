# 📚 Estruturas de Dados em Python

## 🎯 Objetivos da Aula

* Compreender os principais tipos de estruturas de dados
* Saber quando utilizar cada uma
* Analisar eficiência básica (tempo e espaço)
* Aplicar em problemas reais

---

# 🧠 1️⃣ Conceito de Estrutura de Dados

Estrutura de dados é uma forma organizada de:

* Armazenar
* Gerenciar
* Manipular dados

Ela impacta diretamente:

✔ Desempenho
✔ Organização do código
✔ Complexidade do algoritmo

---

# 🟢 2️⃣ Estruturas Nativas do Python

O **Python** possui estruturas internas muito eficientes.

---

## 🔹 2.1 Listas (List)

Estrutura:

* Ordenada
* Mutável
* Permite valores duplicados

```python
numeros = [10, 20, 30, 40]

numeros.append(50)
numeros.remove(20)

print(numeros)
```

### Operações importantes

```python
len(numeros)
numeros[0]
numeros.sort()
```

📌 Uso ideal:

* Coleções dinâmicas
* Sequências ordenadas

Complexidade média:

* Acesso: O(1)
* Inserção no final: O(1)
* Busca: O(n)

---

## 🔹 2.2 Tuplas (Tuple)

* Ordenadas
* Imutáveis
* Permitem duplicados

```python
coordenadas = (10, 20)
```

📌 Uso ideal:

* Dados fixos
* Chaves em dicionários
* Retorno múltiplo de funções

---

## 🔹 2.3 Dicionários (Dict)

* Estrutura chave → valor
* Muito eficiente

```python
aluno = {
    "nome": "Ana",
    "idade": 20,
    "curso": "ADS"
}

print(aluno["nome"])
```

Complexidade:

* Acesso médio: O(1)

📌 Uso ideal:

* Busca rápida
* Estruturas tipo JSON
* Contadores

---

## 🔹 2.4 Conjuntos (Set)

* Não ordenado
* Não permite duplicados

```python
numeros = {1, 2, 3, 3, 4}
print(numeros)
```

Operações matemáticas:

```python
a = {1,2,3}
b = {3,4,5}

print(a.union(b))
print(a.intersection(b))
```

📌 Uso ideal:

* Remover duplicados
* Operações matemáticas
* Verificação rápida de pertencimento

---

# 🟡 3️⃣ Estruturas Lineares Clássicas

---

## 🔹 3.1 Pilha (Stack)

Princípio:
LIFO (Last In, First Out)

```python
pilha = []

pilha.append(10)
pilha.append(20)

pilha.pop()

print(pilha)
```

Aplicações:

* Undo/Redo
* Avaliação de expressões
* Recursão

---

## 🔹 3.2 Fila (Queue)

Princípio:
FIFO (First In, First Out)

```python
from collections import deque

fila = deque()

fila.append(10)
fila.append(20)

fila.popleft()
```

Aplicações:

* Processamento de tarefas
* Sistemas de impressão
* BFS em grafos

---

# 🔵 4️⃣ Estruturas Não Lineares

---

## 🔹 4.1 Árvores

Estrutura hierárquica.

Exemplo simples:

```python
class No:
    def __init__(self, valor):
        self.valor = valor
        self.esquerda = None
        self.direita = None
```

Aplicações:

* Banco de dados
* Sistemas de arquivos
* Compiladores

---

## 🔹 4.2 Grafos

Representação:

* Lista de adjacência
* Matriz de adjacência

Exemplo:

```python
grafo = {
    "A": ["B", "C"],
    "B": ["A", "D"],
    "C": ["A"],
    "D": ["B"]
}
```

Aplicações:

* Redes sociais
* Rotas GPS
* Internet

---

# 🔴 5️⃣ Análise de Complexidade

Conceito fundamental:

* O(1)
* O(n)
* O(log n)
* O(n²)

Exemplo:

```python
for i in range(n):
    print(i)
```

Complexidade:
O(n)

---

# 🧪 6️⃣ Exercícios para Aula

1️⃣ Criar uma pilha manual
2️⃣ Implementar fila sem usar deque
3️⃣ Criar contador de palavras usando dicionário
4️⃣ Remover duplicados usando set
5️⃣ Implementar busca em grafo

---

# 🎯 Comparação Rápida

| Estrutura | Ordenada | Mutável | Busca Rápida |
| --------- | -------- | ------- | ------------ |
| List      | Sim      | Sim     | Não          |
| Tuple     | Sim      | Não     | Não          |
| Dict      | Não      | Sim     | Sim          |
| Set       | Não      | Sim     | Sim          |

---

# 🚀 Aplicações Reais

✔ Sistemas web
✔ Machine Learning
✔ Banco de dados
✔ Criptografia
✔ Simulações

---
Quer nível técnico ou nível superior aprofundado (com análise formal de complexidade)?

