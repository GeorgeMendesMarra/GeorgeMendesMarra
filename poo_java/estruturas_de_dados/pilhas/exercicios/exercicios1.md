# 📚 20 Questões Objetivas sobre Pilhas em Java (com comentário)

---

### 1

Uma pilha (Stack) segue qual princípio?

A) FIFO

B) **LIFO**

C) FILO

D) LILO

✅ **Resposta correta: B**

**Comentário:**
A pilha segue o princípio **LIFO (Last In, First Out)**, ou seja, **o último elemento inserido é o primeiro a ser removido**.

---

### 2

O princípio **LIFO** significa:

A) Primeiro que entra é o primeiro que sai

B) **Último que entra é o primeiro que sai**

C) Elementos são removidos aleatoriamente

D) A pilha remove elementos do meio

✅ **Resposta correta: B**

**Comentário:**
No modelo LIFO, o elemento inserido mais recentemente na pilha é o primeiro a ser removido.

---

### 3

Qual classe da biblioteca padrão Java implementa uma pilha?

A) Queue

B) **Stack**

C) ArrayList

D) HashMap

✅ **Resposta correta: B**

**Comentário:**
A classe `Stack` faz parte da biblioteca padrão do Java e fornece métodos para manipular pilhas.

---

### 4

A classe `Stack` pertence a qual pacote?

A) **java.util**

B) java.io

C) java.lang

D) java.net

✅ **Resposta correta: A**

**Comentário:**
A classe `Stack` está localizada no pacote **java.util**, que contém diversas estruturas de dados.

---

### 5

Qual método adiciona um elemento na pilha?

A) insert()

B) add()

C) **push()**

D) enqueue()

✅ **Resposta correta: C**

**Comentário:**
O método `push()` é utilizado para **inserir um novo elemento no topo da pilha**.

---

### 6

Qual método remove o elemento do topo da pilha?

A) **pop()**

B) removeTop()

C) delete()

D) poll()

✅ **Resposta correta: A**

**Comentário:**
O método `pop()` remove e retorna **o elemento que está no topo da pilha**.

---

### 7

Qual método retorna o elemento do topo sem removê-lo?

A) get()

B) **peek()**

C) pop()

D) top()

✅ **Resposta correta: B**

**Comentário:**
O método `peek()` permite **visualizar o elemento do topo da pilha sem removê-lo**.

---

### 8

Qual método verifica se a pilha está vazia?

A) empty()

B) **isEmpty()**

C) size()

D) check()

✅ **Resposta correta: B**

**Comentário:**
O método `isEmpty()` retorna **true se a pilha estiver vazia** e **false caso contrário**.

---

### 9

Qual exceção ocorre ao tentar remover elemento de uma pilha vazia?

A) IOException

B) StackOverflowError

C) **EmptyStackException**

D) NullPointerException

✅ **Resposta correta: C**

**Comentário:**
Se `pop()` for chamado em uma pilha vazia, o Java lança a exceção **EmptyStackException**.

---

### 10

Qual estrutura é utilizada internamente pela classe `Stack`?

A) LinkedList

B) **Vector**

C) ArrayList

D) TreeMap

✅ **Resposta correta: B**

**Comentário:**
A classe `Stack` herda da classe **Vector**, que utiliza uma estrutura baseada em vetor dinâmico.

---

### 11

A pilha é frequentemente usada em:

A) Ordenação de arquivos

B) **Controle de chamadas de métodos**

C) Comunicação de rede

D) Banco de dados

✅ **Resposta correta: B**

**Comentário:**
A pilha é utilizada no **controle de chamadas de funções e métodos**, formando a chamada **Call Stack**.

---

### 12

Em Java, o uso da pilha está associado ao conceito de:

A) Heap Memory

B) **Stack Memory**

C) Cache Memory

D) Virtual Memory

✅ **Resposta correta: B**

**Comentário:**
A **Stack Memory** é usada para armazenar chamadas de métodos e variáveis locais.

---

### 13

Qual alternativa representa corretamente uma pilha?

A)

```
1 2 3 4
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
1 → 2 → 3 → 4
```

D)

```
4 → 3 → 2 → 1
```

✅ **Resposta correta: B**

**Comentário:**
Na pilha, os elementos são empilhados verticalmente e **apenas o topo pode ser acessado diretamente**.

---

### 14

Qual operação adiciona um elemento no topo da pilha?

A) **push**
B) pop
C) peek
D) remove

✅ **Resposta correta: A**

**Comentário:**
A operação **push** insere um novo elemento no topo da pilha.

---

### 15

Qual operação remove o elemento do topo?

A) push
B) **pop**
C) peek
D) add

✅ **Resposta correta: B**

**Comentário:**
A operação **pop** remove e retorna o elemento que está no topo.

---

### 16

Qual estrutura de dados pode ser usada para implementar uma pilha?

A) Vetor
B) Lista encadeada
C) Array
D) **Todas as alternativas**

✅ **Resposta correta: D**

**Comentário:**
Pilhas podem ser implementadas usando **arrays, vetores ou listas encadeadas**.

---

### 17

Qual é a complexidade da operação `push` em uma pilha?

A) O(n)
B) O(log n)
C) **O(1)**
D) O(n²)

✅ **Resposta correta: C**

**Comentário:**
A operação `push` tem **complexidade constante**, pois apenas adiciona o elemento no topo.

---

### 18

Qual aplicação clássica utiliza pilhas?

A) Busca em largura
B) **Avaliação de expressões matemáticas**
C) Banco de dados
D) Redes neurais

✅ **Resposta correta: B**

**Comentário:**
Pilhas são muito usadas para **avaliar expressões matemáticas e converter notações (infixa, prefixa e pós-fixa)**.

---

### 19

Qual estrutura utiliza pilha para controlar chamadas recursivas?

A) Heap
B) **Call Stack**
C) Queue
D) Tree

✅ **Resposta correta: B**

**Comentário:**
A **Call Stack** registra todas as chamadas de métodos durante a execução do programa.

---

### 20

Qual código cria corretamente uma pilha em Java?

A)

```java
Stack pilha = new Stack();
```

B)

```java
Stack<Integer> pilha = new Stack<>();
```

C)

```java
Pilha pilha = new Pilha();
```

D)

```java
Stack pilha = Stack();
```

✅ **Resposta correta: B**

**Comentário:**
A forma moderna utiliza **Generics**, garantindo **tipagem segura**, como `Stack<Integer>`.

---

