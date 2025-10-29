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
