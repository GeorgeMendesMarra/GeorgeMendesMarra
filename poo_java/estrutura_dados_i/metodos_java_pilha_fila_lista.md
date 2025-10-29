# 🧱 Métodos em Estruturas de Dados — Java

A tabela abaixo apresenta os principais **métodos utilizados nas estruturas de dados** em Java: **Pilha (Stack)**, **Fila (Queue)** e **Fila Dupla (Deque)**.  
Essas estruturas estão localizadas no pacote **`java.util`**.

---

| Estrutura | Classe/Interface | Método | Descrição | Exemplo |
|------------|------------------|---------|------------|----------|
| **Pilha (Stack)** | `java.util.Stack<E>` | `push(E item)` | Insere um elemento no topo da pilha. | `pilha.push(10);` |
| | | `pop()` | Remove e retorna o elemento do topo da pilha. | `pilha.pop();` |
| | | `peek()` | Retorna (sem remover) o elemento do topo. | `pilha.peek();` |
| | | `isEmpty()` | Verifica se a pilha está vazia. | `pilha.isEmpty();` |
| | | `search(Object o)` | Retorna a posição do elemento na pilha (1 = topo). | `pilha.search(10);` |
| | | `size()` | Retorna o número de elementos. | `pilha.size();` |

---

| **Fila (Queue)** | `java.util.Queue<E>` | `add(E e)` | Insere um elemento no final da fila (lança exceção se cheia). | `fila.add(5);` |
| | | `offer(E e)` | Insere um elemento (retorna `false` se cheia, sem exceção). | `fila.offer(5);` |
| | | `remove()` | Remove e retorna o primeiro elemento (lança exceção se vazia). | `fila.remove();` |
| | | `poll()` | Remove e retorna o primeiro elemento (ou `null` se vazia). | `fila.poll();` |
| | | `element()` | Retorna (sem remover) o primeiro elemento (lança exceção se vazia). | `fila.element();` |
| | | `peek()` | Retorna (sem remover) o primeiro elemento (ou `null` se vazia). | `fila.peek();` |
| | | `size()` | Retorna o número de elementos. | `fila.size();` |

---

| **Fila Dupla (Deque)** | `java.util.Deque<E>` | `addFirst(E e)` | Adiciona elemento no início da fila. | `deque.addFirst(10);` |
| | | `addLast(E e)` | Adiciona elemento no final da fila. | `deque.addLast(20);` |
| | | `removeFirst()` | Remove o primeiro elemento. | `deque.removeFirst();` |
| | | `removeLast()` | Remove o último elemento. | `deque.removeLast();` |
| | | `getFirst()` | Retorna (sem remover) o primeiro elemento. | `deque.getFirst();` |
| | | `getLast()` | Retorna (sem remover) o último elemento. | `deque.getLast();` |
| | | `offerFirst(E e)` | Insere elemento no início (sem exceção). | `deque.offerFirst(30);` |
| | | `offerLast(E e)` | Insere elemento no final (sem exceção). | `deque.offerLast(40);` |
| | | `pollFirst()` | Remove e retorna o primeiro elemento (ou `null`). | `deque.pollFirst();` |
| | | `pollLast()` | Remove e retorna o último elemento (ou `null`). | `deque.pollLast();` |
| | | `peekFirst()` | Retorna (sem remover) o primeiro elemento (ou `null`). | `deque.peekFirst();` |
| | | `peekLast()` | Retorna (sem remover) o último elemento (ou `null`). | `deque.peekLast();` |

---

## 🧠 Dica
- **Pilha (Stack)** → LIFO (Last In, First Out) → Último que entra é o primeiro que sai.  
- **Fila (Queue)** → FIFO (First In, First Out) → Primeiro que entra é o primeiro que sai.  
- **Deque** → Permite inserções e remoções em **ambas as extremidades**.

---

**Autor:** Professor George Mendes Marra  
**Disciplina:** Estruturas de Dados em Java  
**Instituição:** Instituto Federal Goiano – Campus Hidrolândia
