## Filas em Java - Todos os Métodos (Explicação Conceitual)

### O que é uma Fila em Java?

Uma **fila (Queue)** é uma estrutura de dados que segue o princípio **FIFO** (First In, First Out - Primeiro que Entra, Primeiro que Sai). Em Java, a interface `Queue` define o contrato para todas as implementações de fila, com variações como `Deque` (fila de duas pontas) e `PriorityQueue` (fila com prioridade).

---

## Hierarquia das Filas em Java

```
Iterable (interface)
    └── Collection (interface)
        └── Queue (interface)
            ├── Deque (interface - fila dupla)
            │   ├── ArrayDeque (classe)
            │   └── LinkedList (classe)
            └── PriorityQueue (classe)

Outras implementações:
    ├── BlockingQueue (interface - para concorrência)
    │   ├── ArrayBlockingQueue
    │   ├── LinkedBlockingQueue
    │   └── PriorityBlockingQueue
    └── TransferQueue (interface)
        └── LinkedTransferQueue
```

---

## Tipos de Filas em Java

| Tipo | Descrição | Característica Principal |
|------|-----------|--------------------------|
| **Queue** | Interface base | Operações FIFO básicas |
| **Deque** | Double-Ended Queue | Opera nas duas extremidades |
| **PriorityQueue** | Fila de prioridade | Ordenação natural ou por Comparator |
| **ArrayDeque** | Deque baseado em array | Mais eficiente que LinkedList |
| **LinkedList** | Deque baseado em nós | Também implementa List |
| **BlockingQueue** | Fila bloqueante | Thread-safe, espera quando cheia/vazia |

---

## Métodos da Interface Queue (Fila Básica)

Os métodos da fila se organizam em **três grupos** de comportamento: operações que lançam exceção, operações que retornam valor especial, e operações herdadas de Collection.

### 1. Métodos de Inserção (Adicionar)

| Método | Comportamento | Retorno em caso de falha |
|--------|--------------|--------------------------|
| `add(elemento)` | Insere no final da fila | Lança `IllegalStateException` se cheia |
| `offer(elemento)` | Insere no final da fila | Retorna `false` se não conseguiu |

### 2. Métodos de Remoção (Retirar)

| Método | Comportamento | Retorno em caso de falha |
|--------|--------------|--------------------------|
| `remove()` | Remove e retorna o elemento do início | Lança `NoSuchElementException` se vazia |
| `poll()` | Remove e retorna o elemento do início | Retorna `null` se vazia |

### 3. Métodos de Consulta (Espiar)

| Método | Comportamento | Retorno em caso de falha |
|--------|--------------|--------------------------|
| `element()` | Retorna o elemento do início sem remover | Lança `NoSuchElementException` se vazia |
| `peek()` | Retorna o elemento do início sem remover | Retorna `null` se vazia |

### 4. Métodos Herdados de Collection

| Método | Descrição |
|--------|-----------|
| `size()` | Retorna o número de elementos na fila |
| `isEmpty()` | Verifica se a fila está vazia |
| `contains(objeto)` | Verifica se o elemento existe na fila |
| `clear()` | Remove todos os elementos da fila |
| `toArray()` | Converte a fila para array |
| `remove(objeto)` | Remove uma ocorrência específica |
| `removeAll(coleção)` | Remove todos elementos da coleção |
| `retainAll(coleção)` | Mantém apenas elementos da coleção |
| `containsAll(coleção)` | Verifica se contém todos |
| `addAll(coleção)` | Adiciona todos elementos de uma coleção |
| `forEach(ação)` | Executa ação para cada elemento |
| `iterator()` | Retorna um iterador |

---

## Métodos da Interface Deque (Fila de Duas Pontas)

O `Deque` (Double-Ended Queue) permite operações nas **duas extremidades**: início e fim.

### 1. Métodos de Inserção no Início

| Método | Comportamento | Falha |
|--------|--------------|-------|
| `addFirst(elemento)` | Insere no início | Lança exceção se falhar |
| `offerFirst(elemento)` | Insere no início | Retorna false se falhar |
| `push(elemento)` | Insere no início (pilha) | Lança exceção se falhar |

### 2. Métodos de Inserção no Fim

| Método | Comportamento | Falha |
|--------|--------------|-------|
| `addLast(elemento)` | Insere no fim | Lança exceção se falhar |
| `offerLast(elemento)` | Insere no fim | Retorna false se falhar |
| `add(elemento)` | Insere no fim | Lança exceção |
| `offer(elemento)` | Insere no fim | Retorna false |
| `push` não aplica | - | - |

### 3. Métodos de Remoção do Início

| Método | Comportamento | Falha |
|--------|--------------|-------|
| `removeFirst()` | Remove do início | Lança exceção se vazia |
| `pollFirst()` | Remove do início | Retorna null se vazia |
| `remove()` | Remove do início | Lança exceção se vazia |
| `poll()` | Remove do início | Retorna null se vazia |
| `pop()` | Remove do início (pilha) | Lança exceção se vazia |

### 4. Métodos de Remoção do Fim

| Método | Comportamento | Falha |
|--------|--------------|-------|
| `removeLast()` | Remove do fim | Lança exceção se vazia |
| `pollLast()` | Remove do fim | Retorna null se vazia |

### 5. Métodos de Consulta no Início

| Método | Comportamento | Falha |
|--------|--------------|-------|
| `getFirst()` | Consulta início sem remover | Lança exceção se vazia |
| `peekFirst()` | Consulta início sem remover | Retorna null se vazia |
| `element()` | Consulta início sem remover | Lança exceção |
| `peek()` | Consulta início sem remover | Retorna null |

### 6. Métodos de Consulta no Fim

| Método | Comportamento | Falha |
|--------|--------------|-------|
| `getLast()` | Consulta fim sem remover | Lança exceção se vazia |
| `peekLast()` | Consulta fim sem remover | Retorna null se vazia |

### 7. Métodos Específicos do Deque

| Método | Descrição |
|--------|-----------|
| `descendingIterator()` | Retorna iterador que percorre do fim para o início |
| `removeFirstOccurrence(objeto)` | Remove primeira ocorrência do objeto |
| `removeLastOccurrence(objeto)` | Remove última ocorrência do objeto |

---

## Métodos da PriorityQueue (Fila de Prioridade)

A `PriorityQueue` não segue FIFO puro. Os elementos saem **por ordem de prioridade** (menor valor primeiro por padrão).

### Comportamento Diferente

| Aspecto | Fila Comum | PriorityQueue |
|---------|------------|---------------|
| Ordem de saída | FIFO (ordem de chegada) | Prioridade (menor valor primeiro) |
| `peek()` | Primeiro que entrou | Menor elemento (prioridade máxima) |
| `poll()` | Remove primeiro que entrou | Remove menor elemento |

### Métodos Específicos

| Método | Descrição |
|--------|-----------|
| `comparator()` | Retorna o Comparator usado (ou null se ordem natural) |
| `offer(elemento)` | Insere com base na prioridade |
| `add(elemento)` | Insere com base na prioridade |

### Características

- **Não permite elementos nulos**
- **Não é thread-safe**
- **Não garante ordem dos elementos com mesma prioridade**
- **Tempo O(log n)** para inserção e remoção

---

## Métodos de BlockingQueue (Filas Bloqueantes)

Para programação concorrente (múltiplas threads), as `BlockingQueue` adicionam operações que **esperam** até que seja possível completar a operação.

### Métodos de Inserção Bloqueantes

| Método | Comportamento |
|--------|--------------|
| `put(elemento)` | Espera infinitamente até ter espaço |
| `offer(elemento, tempo, unidade)` | Espera até o tempo limite |

### Métodos de Remoção Bloqueantes

| Método | Comportamento |
|--------|--------------|
| `take()` | Espera infinitamente até ter elemento |
| `poll(tempo, unidade)` | Espera até o tempo limite |

### Métodos de Capacidade

| Método | Descrição |
|--------|-----------|
| `remainingCapacity()` | Retorna espaço restante na fila |
| `drainTo(coleção)` | Remove todos e transfere para coleção |
| `drainTo(coleção, max)` | Remove no máximo N elementos |

---

## Métodos da Classe Collections para Filas (Utilitários)

| Método Estático | Descrição |
|----------------|-----------|
| `asLifoQueue(deque)` | Converte Deque para fila LIFO (pilha) |
| `synchronizedQueue(fila)` | Cria versão thread-safe da fila |
| `checkedQueue(fila, tipo)` | Cria fila com verificação de tipo |
| `unmodifiableQueue(fila)` | Cria versão somente leitura |
| `emptyIterator()` | Iterador vazio |
| `emptyListIterator()` | ListIterator vazio |
| `singleton(elemento)` | Fila com um único elemento |
| `nCopies(n, elemento)` | Fila com n repetições |

---

## Resumo dos Métodos por Operação

### Operações de Fila (Queue)

| O que fazer | Exceção | Valor Especial |
|-------------|---------|----------------|
| Inserir | `add(e)` | `offer(e)` |
| Remover | `remove()` | `poll()` |
| Consultar | `element()` | `peek()` |

### Operações de Deque (Início)

| O que fazer | Exceção | Valor Especial |
|-------------|---------|----------------|
| Inserir no início | `addFirst(e)` | `offerFirst(e)` |
| Remover do início | `removeFirst()` | `pollFirst()` |
| Consultar início | `getFirst()` | `peekFirst()` |

### Operações de Deque (Fim)

| O que fazer | Exceção | Valor Especial |
|-------------|---------|----------------|
| Inserir no fim | `addLast(e)` | `offerLast(e)` |
| Remover do fim | `removeLast()` | `pollLast()` |
| Consultar fim | `getLast()` | `peekLast()` |

### Operações de Pilha (usando Deque como Stack)

| O que fazer | Método |
|-------------|--------|
| Empilhar (push) | `push(e)` ou `addFirst(e)` |
| Desempilhar (pop) | `pop()` ou `removeFirst()` |
| Consultar topo | `peek()` |

---

## Métodos de Criação (Java 9+)

| Método | Descrição |
|--------|-----------|
| `Queue.of(elementos...)` | Cria fila imutável |
| `Deque.of(elementos...)` | Cria deque imutável |
| `ArrayDeque.of(elementos...)` | Cria ArrayDeque |

---

## Métodos de Stream para Filas

Por herdar de Collection, toda fila tem acesso aos métodos de stream:

| Método | Descrição |
|--------|-----------|
| `stream()` | Stream sequencial |
| `parallelStream()` | Stream paralelo |
| `spliterator()` | Spliterator para processamento |

---

## Tabela de Comportamento por Tipo de Fila

| Operação | Queue (ArrayDeque) | PriorityQueue | BlockingQueue |
|----------|-------------------|---------------|---------------|
| Inserção rápida | `offer()` O(1) | `offer()` O(log n) | `put()` bloqueante |
| Remoção rápida | `poll()` O(1) | `poll()` O(log n) | `take()` bloqueante |
| Acesso ao início | `peek()` O(1) | `peek()` O(1) | `peek()` O(1) |
| Permite null? | Não | Não | Não |
| Thread-safe? | Não | Não | Sim |
| Ordenação | FIFO | Prioridade | FIFO ou prioridade |

---

## Quando Usar Cada Tipo

| Tipo de Fila | Quando Usar |
|--------------|-------------|
| **Queue** (interface) | Para código genérico que só precisa de FIFO básico |
| **ArrayDeque** | **Na maioria dos casos** - mais eficiente que LinkedList |
| **LinkedList** | Quando também precisa das operações de List |
| **PriorityQueue** | Quando a ordem de processamento depende de prioridade |
| **BlockingQueue** | Em aplicações com múltiplas threads (produtor-consumidor) |
| **ArrayBlockingQueue** | Fila com capacidade fixa e thread-safe |
| **LinkedBlockingQueue** | Fila com capacidade opcional e thread-safe |
| **ConcurrentLinkedQueue** | Fila não-bloqueante para alta concorrência |

---

## Resumo dos Princípios da Fila em Java

1. **FIFO padrão**: Use `Queue` com `ArrayDeque`
2. **Prioridade**: Use `PriorityQueue`
3. **Operações nas duas pontas**: Use `Deque`
4. **Comportamento de pilha**: Use `Deque` com `push/pop`
5. **Thread-safe**: Use `BlockingQueue`
6. **Imutável**: Use `Queue.of()` (Java 9+)
7. **Performance**: `ArrayDeque` é geralmente a melhor escolha

Esta é a referência completa de todos os métodos de fila em Java!
