# ☕ Estruturas de Dados em Java — Métodos e Exemplos

Este documento apresenta os **principais métodos das estruturas de dados em Java**, abrangendo:
- **Listas (List, ArrayList, LinkedList)**
- **Pilhas (Stack)**
- **Filas (Queue)**
- **Filas Duplas (Deque)**

Todas essas estruturas estão disponíveis no pacote `java.util`.

---

## 📚 1. Listas (`List`, `ArrayList`, `LinkedList`)

A interface `List<E>` representa uma coleção **ordenada e indexada**, onde é possível acessar elementos por posição.  
Suas principais implementações são **ArrayList** e **LinkedList**.

| Método | Descrição | Exemplo |
|--------|------------|----------|
| `add(E e)` | Adiciona elemento ao final da lista. | `lista.add("A");` |
| `add(int index, E e)` | Insere elemento em uma posição específica. | `lista.add(1, "B");` |
| `remove(Object o)` | Remove a primeira ocorrência do elemento. | `lista.remove("A");` |
| `remove(int index)` | Remove o elemento no índice indicado. | `lista.remove(0);` |
| `get(int index)` | Retorna o elemento do índice informado. | `lista.get(2);` |
| `set(int index, E e)` | Substitui o elemento no índice. | `lista.set(0, "X");` |
| `size()` | Retorna a quantidade de elementos. | `lista.size();` |
| `isEmpty()` | Verifica se a lista está vazia. | `lista.isEmpty();` |
| `contains(Object o)` | Verifica se a lista contém o elemento. | `lista.contains("B");` |
| `indexOf(Object o)` | Retorna o índice da primeira ocorrência. | `lista.indexOf("C");` |
| `clear()` | Remove todos os elementos. | `lista.clear();` |
| `toArray()` | Converte a lista para array. | `lista.toArray();` |
| `subList(int from, int to)` | Retorna parte da lista. | `lista.subList(1, 4);` |
| `sort(Comparator c)` | Ordena a lista. | `lista.sort(null);` |
| `replaceAll(UnaryOperator op)` | Aplica operação a todos os elementos. | `lista.replaceAll(s -> s.toUpperCase());` |
| `removeIf(Predicate filter)` | Remove elementos que atendem ao predicado. | `lista.removeIf(s -> s.isEmpty());` |

### ⚙️ Implementações
- **ArrayList:** acesso rápido por índice (`O(1)`), remoção/inserção mais lenta.  
- **LinkedList:** remoção/inserção eficiente, acesso mais lento (`O(n)`).

---

## 🧱 2. Pilha (`Stack`)

A classe `Stack<E>` implementa uma **estrutura LIFO** (*Last In, First Out*), onde o último elemento inserido é o primeiro a ser removido.

| Método | Descrição | Exemplo |
|--------|------------|----------|
| `push(E item)` | Insere elemento no topo. | `pilha.push(10);` |
| `pop()` | Remove e retorna o topo. | `pilha.pop();` |
| `peek()` | Retorna o topo sem remover. | `pilha.peek();` |
| `isEmpty()` | Verifica se está vazia. | `pilha.isEmpty();` |
| `search(Object o)` | Retorna a posição do elemento (1 = topo). | `pilha.search(10);` |
| `size()` | Retorna a quantidade de elementos. | `pilha.size();` |

### 🧠 Exemplo
```java
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> pilha = new Stack<>();
        pilha.push(5);
        pilha.push(10);
        System.out.println(pilha.pop()); // 10
        System.out.println(pilha.peek()); // 5
    }
}
```

---

## 🧮 3. Fila (`Queue`)

A interface `Queue<E>` segue a lógica **FIFO** (*First In, First Out*), onde o primeiro elemento inserido é o primeiro removido.

| Método | Descrição | Exemplo |
|--------|------------|----------|
| `add(E e)` | Adiciona ao final (lança exceção se cheia). | `fila.add(5);` |
| `offer(E e)` | Adiciona ao final (retorna `false` se cheia). | `fila.offer(5);` |
| `remove()` | Remove e retorna o primeiro (exceção se vazia). | `fila.remove();` |
| `poll()` | Remove e retorna o primeiro (`null` se vazia). | `fila.poll();` |
| `element()` | Retorna (sem remover) o primeiro (exceção se vazia). | `fila.element();` |
| `peek()` | Retorna (sem remover) o primeiro (`null` se vazia). | `fila.peek();` |
| `size()` | Retorna o número de elementos. | `fila.size();` |

### 🧠 Exemplo
```java
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();
        fila.offer("Maria");
        fila.offer("João");
        System.out.println(fila.poll()); // Maria
        System.out.println(fila.peek()); // João
    }
}
```

---

## 🔁 4. Fila Dupla (`Deque`)

A interface `Deque<E>` (Double-Ended Queue) permite **inserir e remover elementos nas duas extremidades** — podendo ser usada como pilha ou fila.

| Método | Descrição | Exemplo |
|--------|------------|----------|
| `addFirst(E e)` | Adiciona no início. | `deque.addFirst(10);` |
| `addLast(E e)` | Adiciona no final. | `deque.addLast(20);` |
| `removeFirst()` | Remove o primeiro. | `deque.removeFirst();` |
| `removeLast()` | Remove o último. | `deque.removeLast();` |
| `getFirst()` | Retorna o primeiro (sem remover). | `deque.getFirst();` |
| `getLast()` | Retorna o último (sem remover). | `deque.getLast();` |
| `offerFirst(E e)` | Insere no início (sem exceção). | `deque.offerFirst(30);` |
| `offerLast(E e)` | Insere no final (sem exceção). | `deque.offerLast(40);` |
| `pollFirst()` | Remove e retorna o primeiro (ou `null`). | `deque.pollFirst();` |
| `pollLast()` | Remove e retorna o último (ou `null`). | `deque.pollLast();` |
| `peekFirst()` | Retorna o primeiro (sem remover). | `deque.peekFirst();` |
| `peekLast()` | Retorna o último (sem remover). | `deque.peekLast();` |

### 🧠 Exemplo
```java
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.addLast(2);
        System.out.println(deque.peekFirst()); // 1
        System.out.println(deque.peekLast());  // 2
    }
}
```

---

## 🧠 Resumo das Estruturas

| Estrutura | Implementação | Tipo | Ordem de Acesso | Característica Principal |
|------------|----------------|------|------------------|---------------------------|
| **List** | `ArrayList`, `LinkedList` | Sequencial | Índices (0 a n) | Acesso e modificação por posição. |
| **Stack** | `Stack` | LIFO | Último entra, primeiro sai | Usada para histórico e recursão. |
| **Queue** | `LinkedList`, `PriorityQueue` | FIFO | Primeiro entra, primeiro sai | Controle de tarefas e filas de espera. |
| **Deque** | `LinkedList`, `ArrayDeque` | Bidirecional | Ambas as extremidades | Combina características de pilha e fila. |

---
