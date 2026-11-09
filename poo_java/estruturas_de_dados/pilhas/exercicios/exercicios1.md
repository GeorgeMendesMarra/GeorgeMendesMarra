# 📚 20 Questões Objetivas sobre Pilhas em Java (com comentário)

Cada questão tem **5 alternativas (A a E)** e um comentário que explica **por que a resposta correta está certa** e **por que cada alternativa errada está errada**.

> 💡 No final há um **gabarito rápido** com todas as respostas.

---

### 1

Uma pilha (Stack) segue qual princípio de funcionamento?

A) FIFO (First In, First Out)

B) Ordem de prioridade (o maior valor sai primeiro)

C) **LIFO (Last In, First Out)**

D) Ordem alfabética

E) Acesso aleatório por índice

✅ **Resposta correta: C**

**Comentário:**
A pilha segue o princípio **LIFO (Last In, First Out)**: **o último elemento inserido é o primeiro a ser removido**. Pense em uma pilha de pratos: você coloca e retira sempre pelo topo.

- **A) FIFO:** é o princípio da **fila** (Queue), em que o primeiro a entrar é o primeiro a sair.
- **B) Prioridade:** é o comportamento de uma **fila de prioridade** (`PriorityQueue`), não de uma pilha.
- **D) Ordem alfabética:** a pilha não ordena nada; só importa a **ordem de chegada**.
- **E) Acesso aleatório:** na pilha só se acessa o **topo**. Acesso por índice é característica de arrays e listas.

---

### 2

O princípio **LIFO** significa:

A) **Último que entra é o primeiro que sai**

B) Primeiro que entra é o primeiro que sai

C) Elementos são removidos aleatoriamente

D) A pilha remove elementos do meio

E) Os elementos são removidos em ordem crescente de valor

✅ **Resposta correta: A**

**Comentário:**
**LIFO** vem de *Last In, First Out*. Se você empilha `1`, `2` e `3` (nessa ordem), o primeiro a sair será o `3`, depois o `2` e por fim o `1`.

- **B)** Descreve o **FIFO** (fila), o oposto do LIFO.
- **C)** Não há aleatoriedade: a ordem de saída é sempre **determinística**.
- **D)** Remover do meio quebraria a regra da pilha, que só permite operar no **topo**.
- **E)** A pilha não considera o valor dos elementos, apenas a **ordem de inserção**.

---

### 3

Qual classe da biblioteca padrão Java foi criada especificamente para representar uma pilha?

A) Queue

B) ArrayList

C) HashMap

D) TreeSet

E) **Stack**

✅ **Resposta correta: E**

**Comentário:**
A classe `Stack` (do pacote `java.util`) oferece os métodos clássicos de pilha: `push()`, `pop()`, `peek()` e `empty()`.

- **A) Queue:** é uma **interface** que representa **filas** (FIFO), não pilhas.
- **B) ArrayList:** é uma lista dinâmica. Até dá para simular uma pilha com ela, mas **não é uma pilha** pronta.
- **C) HashMap:** guarda pares **chave → valor**, sem noção de topo ou ordem de inserção.
- **D) TreeSet:** é um conjunto **ordenado** sem repetições, nada a ver com LIFO.

📌 **Observação:** na documentação atual do Java, a própria classe `Stack` é considerada *legada*. A recomendação moderna é usar `Deque<Integer> pilha = new ArrayDeque<>();`. Mesmo assim, `Stack` continua sendo a classe mais usada para **aprender** o conceito.

---

### 4

A classe `Stack` pertence a qual pacote?

A) java.io

B) **java.util**

C) java.lang

D) java.net

E) java.text

✅ **Resposta correta: B**

**Comentário:**
`Stack` fica em **`java.util`**, o pacote das coleções (`List`, `Map`, `Set`, `Queue`...). Por isso, o código precisa de `import java.util.Stack;`.

- **A) java.io:** classes de **entrada e saída** (arquivos, streams).
- **C) java.lang:** classes fundamentais (`String`, `Math`, `Object`), importadas automaticamente. `Stack` **não** está aqui, por isso precisa de `import`.
- **D) java.net:** classes de **rede** (sockets, URLs).
- **E) java.text:** formatação de **textos, datas e números**.

---

### 5

Qual método da classe `Stack` representa a operação de **empilhar** (inserir no topo)?

A) insert()

B) append()

C) enqueue()

D) **push()**

E) offer()

✅ **Resposta correta: D**

**Comentário:**
O método **`push(elemento)`** coloca um novo elemento no **topo** da pilha.

```java
Stack<Integer> pilha = new Stack<>();
pilha.push(10); // pilha: [10]
pilha.push(20); // pilha: [10, 20]  → o topo é o 20
```

- **A) insert():** não existe em `Stack` com esse sentido.
- **B) append():** é um método de `StringBuilder`, não de pilhas.
- **C) enqueue():** é o termo usado em **filas** (enfileirar), e não existe no Java padrão.
- **E) offer():** é um método de **filas** (`Queue`/`Deque`).

📌 **Observação:** como `Stack` herda de `Vector`, o método `add()` também funciona e insere no final (que é o topo). Porém, **`push()` é a operação própria de pilha** e deve ser preferida por deixar a intenção do código clara.

---

### 6

Qual método remove **e retorna** o elemento do topo da pilha?

A) removeTop()

B) **pop()**

C) delete()

D) poll()

E) dequeue()

✅ **Resposta correta: B**

**Comentário:**
O método **`pop()`** **remove** o elemento do topo e **o devolve** ao chamador.

```java
pilha.push(10);
pilha.push(20);
int removido = pilha.pop(); // removido = 20, pilha: [10]
```

- **A) removeTop():** não existe na API do Java.
- **C) delete():** não existe em `Stack`.
- **D) poll():** pertence a **filas** (`Queue`/`Deque`) e retorna `null` quando vazia.
- **E) dequeue():** termo de **filas** que não existe no Java padrão.

---

### 7

Qual método retorna o elemento do topo **sem removê-lo**?

A) view()

B) pop()

C) **peek()**

D) top()

E) first()

✅ **Resposta correta: C**

**Comentário:**
O método **`peek()`** apenas **espia** o topo: o elemento continua na pilha.

```java
pilha.push(10);
pilha.push(20);
int topo = pilha.peek(); // topo = 20, pilha continua [10, 20]
```

- **A) view():** não existe em `Stack`.
- **B) pop():** retorna o topo, mas **também o remove**.
- **D) top():** existe em outras linguagens (como o `std::stack` do C++), mas **não no Java**.
- **E) first():** não existe em `Stack`.

⚠️ **Cuidado:** chamar `peek()` em pilha vazia lança `EmptyStackException`. Verifique `isEmpty()` antes.

---

### 8

Qual método, presente em praticamente todas as coleções Java (inclusive `Stack`), verifica se a coleção está vazia?

A) isNull()

B) size()

C) check()

D) length()

E) **isEmpty()**

✅ **Resposta correta: E**

**Comentário:**
O método **`isEmpty()`** retorna **`true`** se não há elementos e **`false`** caso contrário.

```java
Stack<Integer> pilha = new Stack<>();
System.out.println(pilha.isEmpty()); // true
pilha.push(5);
System.out.println(pilha.isEmpty()); // false
```

- **A) isNull():** não existe. Além disso, "vazia" é diferente de "nula" (`null`).
- **B) size():** retorna a **quantidade** de elementos (um número). Dá para usar `size() == 0`, mas **não é o método que verifica** diretamente.
- **C) check():** não existe na API.
- **D) length():** é usado em **arrays** (`length`, atributo) e `String` (`length()`), mas **não em coleções**.

📌 **Observação:** a classe `Stack` também possui o método próprio `empty()`, que faz a mesma coisa. Por isso a pergunta pede o método **comum às coleções**.

---

### 9

Qual exceção ocorre ao tentar remover um elemento de uma pilha **vazia** com `pop()`?

A) **EmptyStackException**

B) IOException

C) StackOverflowError

D) NullPointerException

E) ArrayIndexOutOfBoundsException

✅ **Resposta correta: A**

**Comentário:**
Chamar `pop()` (ou `peek()`) em uma `Stack` vazia lança **`EmptyStackException`**, exceção específica criada para esse caso.

```java
Stack<Integer> pilha = new Stack<>();
pilha.pop(); // ❌ lança EmptyStackException
```

- **B) IOException:** erro de **entrada/saída** (arquivos, rede).
- **C) StackOverflowError:** acontece quando a **Call Stack** estoura, geralmente por **recursão infinita**. Não tem relação com `pop()` em pilha vazia.
- **D) NullPointerException:** ocorre ao usar uma referência `null`. Aqui a pilha **existe**, só está sem elementos.
- **E) ArrayIndexOutOfBoundsException:** acontece em acesso a **índice inválido de array**. A `Stack` esconde essa lógica e lança a exceção própria.

---

### 10

Qual estrutura a classe `Stack` herda e usa internamente?

A) LinkedList

B) TreeMap

C) ArrayList

D) **Vector**

E) HashSet

✅ **Resposta correta: D**

**Comentário:**
`Stack` **estende `Vector`**, que é um vetor dinâmico (baseado em array que cresce conforme necessário) e **sincronizado** (seguro para múltiplas threads).

```java
public class Stack<E> extends Vector<E> { ... }
```

- **A) LinkedList:** é uma lista **duplamente encadeada**, outra estrutura.
- **B) TreeMap:** mapa **ordenado** (árvore rubro-negra).
- **C) ArrayList:** é parecida com `Vector`, mas **não** é a classe da qual `Stack` herda.
- **E) HashSet:** conjunto baseado em **tabela hash**, sem ordem.

📌 **Observação:** por herdar de `Vector`, a `Stack` expõe métodos que **quebram o conceito de pilha** (como `get(i)` e `remove(i)`). Esse é um dos motivos de a recomendação atual ser `ArrayDeque`.

---

### 11

A pilha é frequentemente usada em:

A) Ordenação de arquivos

B) **Controle de chamadas de métodos**

C) Comunicação de rede

D) Banco de dados

E) Escalonamento de processos por ordem de chegada

✅ **Resposta correta: B**

**Comentário:**
Cada vez que um método é chamado, o Java **empilha** um registro (*stack frame*) com parâmetros e variáveis locais. Quando o método termina, o registro é **desempilhado**. Essa estrutura é a **Call Stack**.

- **A) Ordenação de arquivos:** usa algoritmos de ordenação (merge sort, quick sort...), não depende de pilha como conceito central.
- **C) Comunicação de rede:** usa protocolos, sockets e buffers. (Atenção: "pilha de protocolos TCP/IP" é outro sentido da palavra "pilha".)
- **D) Banco de dados:** usa índices, árvores B e logs, não pilhas como aplicação clássica.
- **E) Escalonamento por ordem de chegada:** é comportamento **FIFO**, ou seja, de **fila**.

---

### 12

Em Java, o uso de pilha para guardar chamadas de métodos e variáveis locais está associado a qual área de memória?

A) Heap Memory

B) Cache Memory

C) **Stack Memory**

D) Virtual Memory

E) Metaspace

✅ **Resposta correta: C**

**Comentário:**
A **Stack Memory** guarda, para cada chamada de método, as **variáveis locais**, os **parâmetros** e o **endereço de retorno**. Funciona em LIFO e é liberada automaticamente quando o método termina.

- **A) Heap:** guarda os **objetos** criados com `new` (e é gerenciada pelo Garbage Collector). As **referências** ficam na Stack, mas os objetos ficam na Heap.
- **B) Cache:** memória rápida do **processador**, gerenciada pelo hardware.
- **D) Memória virtual:** mecanismo do **sistema operacional** para estender a RAM usando disco.
- **E) Metaspace:** guarda **metadados das classes** carregadas pela JVM.

---

### 13

Qual alternativa representa **corretamente** uma pilha?

A)

```
1 2 3 4
```

B)

```
1 → 2 → 3 → 4
```

C)

```
4 → 3 → 2 → 1
```

D) **Pilha com topo e base**

```
Topo
 4
 3
 2
 1
Base
```

E)

```
Frente → 1 2 3 4 ← Fim
```

✅ **Resposta correta: D**

**Comentário:**
Em uma pilha os elementos são empilhados **na vertical**, e **só o topo** é acessível diretamente. O elemento `4`, inserido por último, é o primeiro a sair.

- **A)** Lista horizontal simples, sem indicar topo ou base. Não representa as regras da pilha.
- **B)** Representa uma **lista encadeada** (cada elemento aponta para o próximo).
- **C)** Também é uma **lista encadeada**, apenas em sentido inverso.
- **E)** Representa uma **fila**: entra pelo **fim** e sai pela **frente** (FIFO).

---

### 14

Qual operação adiciona um elemento no topo da pilha?

A) **push**

B) pop

C) peek

D) remove

E) insert

✅ **Resposta correta: A**

**Comentário:**
**`push`** (empilhar) insere um novo elemento no **topo**. É uma das duas operações fundamentais da pilha, junto com `pop`.

- **B) pop:** **remove** o topo (oposto de `push`).
- **C) peek:** apenas **consulta** o topo, sem alterar a pilha.
- **D) remove:** remove, não adiciona (e não é operação clássica de pilha).
- **E) insert:** não é nome de operação de pilha.

---

### 15

Qual operação remove o elemento do topo da pilha?

A) push

B) peek

C) add

D) poll

E) **pop**

✅ **Resposta correta: E**

**Comentário:**
**`pop`** (desempilhar) **remove e retorna** o elemento do topo.

- **A) push:** **insere** no topo (o contrário de `pop`).
- **B) peek:** só **consulta** o topo, não remove.
- **C) add:** **adiciona** elementos, não remove.
- **D) poll:** é operação de **fila** (`Queue`), que remove o elemento da **frente**.

---

### 16

Qual estrutura de dados pode ser usada para implementar uma pilha?

A) Array

B) Lista encadeada

C) Vetor dinâmico (como `Vector` ou `ArrayList`)

D) Nenhuma delas

E) **Todas as alternativas A, B e C**

✅ **Resposta correta: E**

**Comentário:**
A pilha é um **tipo abstrato de dados** (o que ela faz: `push`, `pop`, `peek`). A forma de **armazenar** os elementos pode variar:

- **Array:** simples e rápido, mas de **tamanho fixo** (pode encher).
- **Lista encadeada:** cresce **sem limite predefinido**; o topo é a cabeça da lista.
- **Vetor dinâmico:** é um array que **cresce automaticamente**; é o caso da própria classe `Stack`, que usa `Vector`.

A alternativa **D** está errada porque as três opções anteriores **funcionam** para implementar uma pilha.

---

### 17

Qual é a complexidade da operação `push` em uma pilha?

A) O(n)

B) **O(1)**

C) O(log n)

D) O(n²)

E) O(n log n)

✅ **Resposta correta: B**

**Comentário:**
`push` tem **complexidade constante, O(1)**: o elemento é colocado diretamente no topo, **sem percorrer** os outros elementos.

- **A) O(n):** seria necessário se fosse preciso percorrer ou deslocar todos os elementos (por exemplo, inserir no início de um array).
- **C) O(log n):** típica de busca binária e de árvores balanceadas.
- **D) O(n²):** típica de algoritmos com laços aninhados, como o bubble sort.
- **E) O(n log n):** típica de ordenações eficientes (merge sort, quick sort).

📌 **Detalhe:** em pilhas baseadas em array dinâmico, quando o array enche e precisa ser redimensionado, aquele `push` específico custa O(n). Mesmo assim, o custo **médio (amortizado) continua O(1)**.

---

### 18

Qual é uma aplicação clássica de pilhas?

A) Busca em largura (BFS)

B) Banco de dados

C) Redes neurais

D) **Avaliação de expressões matemáticas**

E) Escalonamento Round Robin

✅ **Resposta correta: D**

**Comentário:**
Pilhas são muito usadas para **avaliar expressões** e **converter notações** (infixa, prefixa e pós-fixa). Também aparecem em **desfazer/refazer (Ctrl+Z)**, **histórico do navegador** e **verificação de parênteses balanceados**.

- **A) Busca em largura:** usa **fila**. A busca em **profundidade (DFS)** é que usa pilha.
- **B) Banco de dados:** não é uma aplicação clássica de pilha.
- **C) Redes neurais:** usam matrizes, pesos e funções de ativação.
- **E) Round Robin:** escalonamento circular baseado em **fila**.

---

### 19

Qual estrutura utiliza o conceito de pilha para controlar chamadas de métodos, inclusive as **recursivas**?

A) **Call Stack**

B) Heap

C) Queue

D) Tree

E) Garbage Collector

✅ **Resposta correta: A**

**Comentário:**
A **Call Stack** registra todas as chamadas de métodos em andamento. Em uma recursão, cada chamada **empilha** um novo registro, e as respostas voltam **desempilhando** na ordem inversa.

```java
int fatorial(int n) {
    if (n <= 1) return 1;
    return n * fatorial(n - 1); // cada chamada empilha um novo frame
}
```

- **B) Heap:** guarda **objetos**, não o fluxo de chamadas.
- **C) Queue:** é **fila** (FIFO), o oposto do comportamento necessário.
- **D) Tree:** árvore, estrutura **hierárquica**.
- **E) Garbage Collector:** é o mecanismo que **libera objetos** não usados da Heap.

⚠️ Se a recursão não tiver condição de parada, a Call Stack enche e ocorre `StackOverflowError`.

---

### 20

Qual código cria **corretamente** uma pilha de números inteiros em Java, usando Generics?

A)

```java
Stack pilha = Stack();
```

B)

```java
Pilha pilha = new Pilha();
```

C) **Criação com Generics**

```java
Stack<Integer> pilha = new Stack<>();
```

D)

```java
Stack pilha = new Stack();
```

E)

```java
Stack<int> pilha = new Stack<>();
```

✅ **Resposta correta: C**

**Comentário:**
A forma moderna usa **Generics** (`<Integer>`), que garante **tipagem segura**: o compilador só permite inserir `Integer` e dispensa conversões ao fazer `pop()`.

- **A)** Falta a palavra **`new`**. Assim, o Java procura um **método** chamado `Stack()`, que não existe, e dá erro de compilação.
- **B)** A classe `Pilha` **não existe** na biblioteca padrão (só funcionaria se você mesmo a criasse).
- **D)** Compila, mas usa o **tipo bruto** (*raw type*), sem Generics. Isso gera *warning* e permite inserir qualquer tipo, o que causa erros em tempo de execução.
- **E)** Generics **não aceitam tipos primitivos**. É preciso usar a classe *wrapper* **`Integer`**, e não `int`.

---

## 📝 Gabarito rápido

| Questão | Resposta | Questão | Resposta |
|:-------:|:--------:|:-------:|:--------:|
| 1 | C | 11 | B |
| 2 | A | 12 | C |
| 3 | E | 13 | D |
| 4 | B | 14 | A |
| 5 | D | 15 | E |
| 6 | B | 16 | E |
| 7 | C | 17 | B |
| 8 | E | 18 | D |
| 9 | A | 19 | A |
| 10 | D | 20 | C |
