Em Java, a implementação de pilhas evoluiu significativamente ao longo das décadas. Como vimos no histórico, a pilha é fundamental para a execução da própria JVM (Java Virtual Machine), que utiliza uma "Stack" para gerir as chamadas de métodos.

Aqui estão as formas de implementar e utilizar pilhas em Java, da maneira clássica à moderna:

---

### 1. A Classe Herdou do Passado: `java.util.Stack`

Esta é a classe original do Java 1.0. Embora ainda funcione, ela é considerada **legada** para a maioria dos novos projetos.

* **Peculiaridade:** Ela estende `Vector`, o que significa que é sincronizada (thread-safe). Isso a torna mais lenta do que o necessário para aplicações de thread única.

```java
import java.util.Stack;

public class ExemploStack {
    public static void main(String[] args) {
        Stack<String> pilha = new Stack<>();

        // Operações básicas
        pilha.push("Livro 1");
        pilha.push("Livro 2");
        pilha.push("Livro 3");

        System.out.println("Topo: " + pilha.peek()); // Espreita o topo: Livro 3
        System.out.println("Removido: " + pilha.pop()); // Remove o topo
        System.out.println("Novo topo: " + pilha.peek()); // Livro 2
    }
}

```

---

### 2. A Abordagem Moderna: `java.util.Deque`

Atualmente, a documentação oficial do Java recomenda o uso da interface `Deque` (Double Ended Queue) para criar pilhas, utilizando a implementação `ArrayDeque`.

* **Vantagem:** É muito mais rápida que `Stack` por não ser sincronizada e por ter uma estrutura de array dinâmico mais eficiente.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class PilhaModerna {
    public static void main(String[] args) {
        // Deque funciona como LIFO (Pilha) se usares push e pop
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        while (!stack.isEmpty()) {
            System.out.println("Processando: " + stack.pop());
        }
    }
}

```

---

### 3. Implementação Manual (Para fins didáticos)

Para entender o que acontece "debaixo do capô" (o conceito histórico de arrays contíguos), podemos criar uma pilha simples usando um array fixo:

```java
public class MinhaPilha {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public MinhaPilha(int s) {
        maxSize = s;
        stackArray = new int[maxSize];
        top = -1; // Pilha vazia
    }

    public void push(int j) {
        if (top < maxSize - 1) {
            stackArray[++top] = j;
        } else {
            System.out.println("Stack Overflow!");
        }
    }

    public int pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            System.out.println("Stack Underflow!");
            return -1;
        }
    }

    public int peek() {
        return stackArray[top];
    }
    
    public boolean isEmpty() {
        return (top == -1);
    }
}

```

---

### Quando usar cada uma?

1. **`ArrayDeque`**: É a escolha padrão. Use para quase tudo que precise de uma pilha eficiente em termos de memória e velocidade.
2. **`LinkedList`**: Também implementa `Deque`. Use se precisar de muitas inserções e remoções e não souber o tamanho final, embora em Java o `ArrayDeque` costume ganhar em performance de cache (como vimos no histórico de arrays).
3. **`Stack` (Legada)**: Use apenas se estiver a trabalhar com código antigo ou se precisar obrigatoriamente de sincronização nativa entre threads.

### Resumo das Operações em Java:

* **`push(e)`**: Adiciona um elemento ao topo.
* **`pop()`**: Remove e retorna o elemento do topo (lança exceção se estiver vazia).
* **`peek()`**: Retorna o elemento do topo sem o remover.
* **`isEmpty()`**: Verifica se a pilha está vazia.

Gostaria de ver como aplicar isto num problema real, como validar parênteses num algoritmo ou inverter uma string?
