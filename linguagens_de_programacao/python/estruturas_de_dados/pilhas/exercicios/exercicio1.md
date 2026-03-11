
# 📚 20 Questões Objetivas – Pilhas em Python

---

### 1

Uma pilha (stack) segue qual princípio?

A) FIFO
B) **LIFO**
C) FILO
D) LILO

✅ **Resposta correta: B**

**Comentário:**
Uma pilha segue o princípio **LIFO (Last In, First Out)**, ou seja, **o último elemento inserido é o primeiro a ser removido**.

---

### 2

Qual estrutura nativa de Python é mais usada para implementar uma pilha?

A) Tuple
B) Dictionary
C) **List**
D) Set

✅ **Resposta correta: C**

**Comentário:**
Em Python, a estrutura **list** é frequentemente usada para implementar pilhas, utilizando os métodos `append()` e `pop()`.

---

### 3

Qual método adiciona um elemento ao topo da pilha em Python?

A) insert()
B) **append()**
C) add()
D) push()

✅ **Resposta correta: B**

**Comentário:**
O método `append()` adiciona um elemento **no final da lista**, representando o **topo da pilha**.

---

### 4

Qual método remove o elemento do topo da pilha?

A) delete()
B) remove()
C) **pop()**
D) clear()

✅ **Resposta correta: C**

**Comentário:**
O método `pop()` remove e retorna **o último elemento da lista**, que corresponde ao topo da pilha.

---

### 5

Qual estrutura representa corretamente uma pilha?

A)

```
1 → 2 → 3 → 4
```

B)

```
Topo
4
3
2
1
Base
```

C)

```
1 2 3 4
```

D)

```
4 → 3 → 2 → 1
```

✅ **Resposta correta: B**

**Comentário:**
Na pilha, os elementos são empilhados verticalmente e **somente o topo é acessado diretamente**.

---

### 6

Qual código cria uma pilha vazia em Python?

A)

```python
pilha = ()
```

B)

```python
pilha = {}
```

C)

```python
pilha = []
```

D)

```python
pilha = set()
```

✅ **Resposta correta: C**

**Comentário:**
Uma lista vazia (`[]`) pode ser utilizada para representar uma pilha.

---

### 7

Qual código adiciona um elemento à pilha?

A)

```python
pilha.add(10)
```

B)

```python
pilha.push(10)
```

C)

```python
pilha.append(10)
```

D)

```python
pilha.insert(10)
```

✅ **Resposta correta: C**

**Comentário:**
`append()` adiciona um elemento no final da lista, que representa o topo da pilha.

---

### 8

Qual código remove o elemento do topo da pilha?

A)

```python
pilha.pop()
```

B)

```python
pilha.remove()
```

C)

```python
pilha.delete()
```

D)

```python
pilha.clear()
```

✅ **Resposta correta: A**

**Comentário:**
`pop()` remove o último elemento da lista.

---

### 9

Qual erro ocorre ao usar `pop()` em uma pilha vazia?

A) SyntaxError
B) **IndexError**
C) TypeError
D) ValueError

✅ **Resposta correta: B**

**Comentário:**
Se `pop()` for chamado em uma lista vazia, Python lança um **IndexError**.

---

### 10

Qual operação permite visualizar o topo da pilha sem removê-lo?

A)

```python
pilha.peek()
```

B)

```python
pilha[-1]
```

C)

```python
pilha.top()
```

D)

```python
pilha.get()
```

✅ **Resposta correta: B**

**Comentário:**
`pilha[-1]` acessa **o último elemento da lista**, que corresponde ao topo da pilha.

---

### 11

Qual biblioteca Python possui uma estrutura específica para pilha?

A) random
B) **collections**
C) math
D) statistics

✅ **Resposta correta: B**

**Comentário:**
A biblioteca `collections` possui estruturas úteis para manipulação de dados.

---

### 12

Qual estrutura da biblioteca `collections` pode ser usada como pilha?

A) Counter
B) OrderedDict
C) **deque**
D) ChainMap

✅ **Resposta correta: C**

**Comentário:**
`deque` permite inserir e remover elementos com alta eficiência.

---

### 13

Qual comando cria uma pilha usando `deque`?

A)

```python
pilha = deque()
```

B)

```python
pilha = list()
```

C)

```python
pilha = stack()
```

D)

```python
pilha = queue()
```

✅ **Resposta correta: A**

### 14

Qual aplicação clássica usa pilhas?

A) Banco de dados
B) **Avaliação de expressões matemáticas**
C) Redes neurais
D) Sistemas operacionais

✅ **Resposta correta: B**

**Comentário:**
Pilhas são usadas para **avaliar expressões e converter notações matemáticas**.

---

### 15

Qual algoritmo usa pilha?

A) Busca em largura
B) **Busca em profundidade (DFS)**
C) Ordenação por seleção
D) Bubble Sort

✅ **Resposta correta: B**

**Comentário:**
O algoritmo **Depth-First Search (DFS)** utiliza pilha para explorar os nós.

---

### 16

Qual estrutura armazena chamadas de funções em Python?

A) Heap
B) Queue
C) **Call Stack**
D) Cache

✅ **Resposta correta: C**

**Comentário:**
Cada chamada de função é armazenada na **pilha de execução (Call Stack)**.

---

### 17

Qual complexidade da operação `append()` em uma pilha Python?

A) O(n)
B) **O(1)**
C) O(log n)
D) O(n²)

✅ **Resposta correta: B**

**Comentário:**
Adicionar elementos ao final da lista possui **complexidade constante**.

---

### 18

Qual estrutura pode implementar uma pilha?

A) Lista
B) Lista encadeada
C) Array
D) **Todas as alternativas**

✅ **Resposta correta: D**

**Comentário:**
Pilhas podem ser implementadas usando diferentes estruturas de dados.

---

### 19

Qual alternativa representa corretamente operações de pilha?

A)

```
enqueue / dequeue
```

B)

```
push / pop
```

C)

```
add / remove
```

D)

```
insert / delete
```

✅ **Resposta correta: B**

**Comentário:**
As operações fundamentais da pilha são **push (inserir)** e **pop (remover)**.

---

### 20

Qual código representa corretamente uma pilha em Python?

A)

```python
pilha = []
pilha.append(1)
pilha.append(2)
pilha.pop()
```

B)

```python
pilha = {}
```

C)

```python
pilha = set()
```

D)

```python
pilha = ()
```

✅ **Resposta correta: A**

**Comentário:**
Nesse exemplo, os elementos são adicionados com `append()` e removidos com `pop()`, simulando uma pilha.

---
