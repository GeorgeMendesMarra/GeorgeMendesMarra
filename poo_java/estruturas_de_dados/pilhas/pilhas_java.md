# Pilhas em Java

Uma **pilha** (*stack*) é uma estrutura de dados que segue a regra **LIFO** (*Last In, First Out*): o último elemento a entrar é o primeiro a sair. A imagem clássica é uma pilha de livros ou de pratos: você só empilha e desempilha pelo **topo**.

Em Java, a pilha está em toda parte. A própria JVM (*Java Virtual Machine*) usa uma **pilha de chamadas** (*call stack*) para controlar a execução dos métodos: cada chamada empilha um *frame* com as variáveis locais e o ponto de retorno, e o `return` desempilha esse frame. Por isso uma recursão sem condição de parada termina em `StackOverflowError`.

Neste material você verá cinco formas de trabalhar com pilhas em Java, da mais antiga à mais recomendada, e depois uma aplicação prática.

---

## Operações fundamentais

| Operação | O que faz | Se a pilha estiver vazia |
|---|---|---|
| `push(e)` | Insere `e` no topo | — |
| `pop()` | Remove e retorna o elemento do topo | Lança exceção |
| `peek()` | Retorna o topo **sem remover** | Depende da classe (veja abaixo) |
| `isEmpty()` | Informa se a pilha está vazia | — |
| `size()` | Quantidade de elementos | — |

> **Atenção:** o comportamento de `peek()` em pilha vazia muda conforme a classe. Em `Stack` ele lança `EmptyStackException`; em `Deque`/`ArrayDeque` ele retorna `null`. Já `pop()` lança exceção nas duas (`EmptyStackException` e `NoSuchElementException`, respectivamente).

---

## 1. A classe herdada do passado: `java.util.Stack`

Presente desde o Java 1.0, a classe `Stack` é considerada **legada**. Ela ainda funciona, mas não é a recomendada para código novo.

**Por que evitar:**

- **Estende `Vector`**: seus métodos são sincronizados (*thread-safe* método a método), o que adiciona custo desnecessário em programas de uma única *thread*.
- **Quebra o encapsulamento**: como é um `Vector`, também expõe `get(i)`, `add(i, e)` e `remove(i)`, que permitem acessar ou inserir no meio da pilha, algo que contradiz o conceito de LIFO.
- **Ordem de iteração contraintuitiva**: ao percorrer um `Stack` com `for-each`, a ordem vai da **base para o topo**, o inverso do que se espera de uma pilha.

```java
import java.util.Stack;

public class ExemploStack {
    public static void main(String[] args) {
        Stack<String> pilha = new Stack<>();

        pilha.push("Livro 1");
        pilha.push("Livro 2");
        pilha.push("Livro 3");

        System.out.println("Topo: " + pilha.peek());          // Livro 3
        System.out.println("Removido: " + pilha.pop());       // Livro 3
        System.out.println("Novo topo: " + pilha.peek());     // Livro 2
        System.out.println("Tamanho: " + pilha.size());       // 2

        // Cuidado: a iteração começa pela BASE da pilha
        for (String livro : pilha) {
            System.out.println(livro);                        // Livro 1, Livro 2
        }
    }
}
```

---

## 2. A abordagem moderna: `Deque` com `ArrayDeque`

A documentação oficial do Java recomenda usar a interface `Deque` (*Double Ended Queue*, fila de duas pontas) para implementar pilhas, com a classe `ArrayDeque`. Basta usar apenas os métodos `push`, `pop` e `peek`, que operam sempre na **cabeça** (início) da estrutura.

**Vantagens:**

- Mais rápida que `Stack`, pois não é sincronizada.
- Usa um *array* circular redimensionável, com boa localidade de cache.
- Iteração começa pelo **topo**, como se espera de uma pilha.
- Expõe só o necessário se você declarar a variável como `Deque`.

**Limitação:** `ArrayDeque` **não aceita `null`**; tentar inserir `null` lança `NullPointerException`.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class PilhaModerna {
    public static void main(String[] args) {
        // Declare como Deque (interface) e instancie ArrayDeque
        Deque<Integer> pilha = new ArrayDeque<>();

        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        System.out.println("Topo: " + pilha.peek());   // 30

        while (!pilha.isEmpty()) {
            System.out.println("Processando: " + pilha.pop()); // 30, 20, 10
        }

        // pilha.pop() agora lançaria NoSuchElementException
        System.out.println("peek em pilha vazia: " + pilha.peek()); // null
    }
}
```

---

## 3. Implementação manual com array (didática)

Para entender o que acontece "por baixo do capô", vale construir uma pilha do zero. Na versão abaixo, usamos **genéricos** e **exceções** (em vez de imprimir mensagens e devolver `-1`, o que confunde um erro com um valor válido).

```java
import java.util.NoSuchElementException;

public class PilhaArray<T> {
    private final Object[] elementos;
    private int topo = -1; // -1 indica pilha vazia

    public PilhaArray(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva");
        }
        this.elementos = new Object[capacidade];
    }

    public void push(T elemento) {
        if (isCheia()) {
            throw new IllegalStateException("Pilha cheia (stack overflow)");
        }
        elementos[++topo] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isVazia()) {
            throw new NoSuchElementException("Pilha vazia (stack underflow)");
        }
        T elemento = (T) elementos[topo];
        elementos[topo--] = null; // libera a referência para o coletor de lixo
        return elemento;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isVazia()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return (T) elementos[topo];
    }

    public boolean isVazia() {
        return topo == -1;
    }

    public boolean isCheia() {
        return topo == elementos.length - 1;
    }

    public int size() {
        return topo + 1;
    }
}
```

**Pontos de atenção:**

- `topo = -1` representa a pilha vazia; o primeiro `push` leva `topo` a `0`.
- Em `pop()`, atribuir `null` à posição removida evita *memory leak* (a pilha continuaria referenciando o objeto).
- O tamanho é **fixo**. Para uma pilha que cresce, seria preciso criar um novo *array* maior e copiar os elementos (é o que o `ArrayDeque` faz internamente).

---

## 4. Implementação manual com lista encadeada

Outra forma clássica é usar nós ligados entre si. O topo da pilha é o primeiro nó da lista, então `push` e `pop` são feitos sempre no início, sem limite de tamanho.

```java
import java.util.NoSuchElementException;

public class PilhaEncadeada<T> {

    private static class No<T> {
        T valor;
        No<T> proximo;

        No(T valor, No<T> proximo) {
            this.valor = valor;
            this.proximo = proximo;
        }
    }

    private No<T> topo;
    private int tamanho;

    public void push(T valor) {
        topo = new No<>(valor, topo); // o novo nó aponta para o antigo topo
        tamanho++;
    }

    public T pop() {
        if (isVazia()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        T valor = topo.valor;
        topo = topo.proximo;
        tamanho--;
        return valor;
    }

    public T peek() {
        if (isVazia()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return topo.valor;
    }

    public boolean isVazia() {
        return topo == null;
    }

    public int size() {
        return tamanho;
    }
}
```

**Comparação com a versão em array:** não existe "pilha cheia" (só a memória limita), mas cada elemento ocupa mais espaço, pois guarda também a referência ao próximo nó.

---

## 5. Quando usar cada uma?

| Opção | Quando usar | Observações |
|---|---|---|
| **`ArrayDeque`** | **Escolha padrão** para qualquer pilha em código novo | Rápida, sem sincronização, não aceita `null` |
| **`LinkedList`** | Raramente; só se precisar armazenar `null` | Também implementa `Deque`, mas consome mais memória e tem pior desempenho de cache |
| **`Stack`** | Apenas em código legado | Sincronizada por método e expõe operações de `Vector` |
| **Pilha de concorrência** (`ConcurrentLinkedDeque`, `LinkedBlockingDeque`) | Quando várias *threads* compartilham a pilha | Mais adequada que `Stack`, pois a sincronização por método não torna seguras as operações compostas (ex.: `if (!isEmpty()) pop()`) |
| **Implementação própria** | Para estudar, ou quando precisar de regras específicas | Foco didático: array (tamanho fixo) ou lista encadeada (sem limite) |

---

## 6. Complexidade das operações

| Operação | `ArrayDeque` | Pilha com array | Pilha encadeada |
|---|---|---|---|
| `push` | O(1) amortizado | O(1) | O(1) |
| `pop` | O(1) | O(1) | O(1) |
| `peek` | O(1) | O(1) | O(1) |
| Buscar um elemento | O(n) | O(n) | O(n) |

O "amortizado" no `ArrayDeque` existe porque, de vez em quando, o *array* interno precisa crescer e ser copiado, mas na média cada `push` continua custando O(1).

---

## 7. Aplicação prática: verificando parênteses balanceados

Uma aplicação clássica de pilha é verificar se uma expressão tem seus delimitadores `()`, `[]` e `{}` corretamente abertos e fechados. É a mesma ideia usada por compiladores e editores de código.

**Estratégia:** ao encontrar um delimitador de abertura, empilhe-o; ao encontrar um de fechamento, desempilhe e confira se os dois formam um par. Ao final, a pilha deve estar vazia.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ParentesesBalanceados {

    public static boolean estaBalanceado(String expressao) {
        Deque<Character> pilha = new ArrayDeque<>();

        for (char c : expressao.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pilha.isEmpty()) {
                    return false; // fechou sem ter aberto
                }
                char abertura = pilha.pop();
                if (!formamPar(abertura, c)) {
                    return false; // tipos diferentes
                }
            }
        }
        return pilha.isEmpty(); // sobrou algo aberto?
    }

    private static boolean formamPar(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
            || (abertura == '[' && fechamento == ']')
            || (abertura == '{' && fechamento == '}');
    }

    public static void main(String[] args) {
        System.out.println(estaBalanceado("{[(1 + 2) * 3] - 4}")); // true
        System.out.println(estaBalanceado("(1 + 2]"));             // false
        System.out.println(estaBalanceado("((1 + 2)"));            // false
        System.out.println(estaBalanceado("1 + 2)"));              // false
    }
}
```

**Outras aplicações de pilhas:** desfazer/refazer (*undo/redo*), histórico de navegação (botão "voltar"), avaliação de expressões pós-fixas, conversão de decimal para binário, percurso em profundidade (DFS) em grafos e árvores, e a própria pilha de chamadas da JVM.

---

## 8. Erros comuns

1. **Chamar `pop()` ou `peek()` em pilha vazia.** Verifique `isEmpty()` antes, ou trate a exceção.
2. **Usar `Stack` por hábito** em projetos novos. Prefira `Deque<T> pilha = new ArrayDeque<>();`.
3. **Confundir a ordem de iteração.** Em `Stack` o `for-each` começa pela base; em `ArrayDeque`, pelo topo.
4. **Inserir `null` em `ArrayDeque`**, o que lança `NullPointerException`.
5. **Esquecer de anular a referência no `pop()`** em implementações com array, o que mantém objetos vivos sem necessidade.
6. **Declarar a variável com o tipo concreto** (`ArrayDeque<T>`), expondo métodos como `addLast()` que violam o LIFO. Declare como `Deque<T>` e use só `push`, `pop` e `peek`.

---

## 9. Exercícios propostos

1. Escreva um programa que **inverta uma `String`** usando uma pilha.
2. Implemente um método que converta um número **decimal em binário** usando pilha.
3. Adapte a `PilhaArray<T>` para **redimensionar o array automaticamente** quando ficar cheia (dobre a capacidade).
4. Crie um **histórico de navegação** com duas pilhas (*voltar* e *avançar*).
5. Avalie uma **expressão pós-fixa** (ex.: `3 4 + 2 *` resulta em `14`) usando pilha.
6. Faça um pequeno teste de desempenho comparando `Stack` e `ArrayDeque` com 1 milhão de `push`/`pop`.

---

## Resumo

- A pilha segue **LIFO**: só se insere e remove pelo **topo**.
- Para código novo, use **`Deque<T> pilha = new ArrayDeque<>()`** com `push`, `pop` e `peek`.
- `Stack` é **legada**: sincronizada, herda de `Vector` e itera da base para o topo.
- `pop()` em pilha vazia lança exceção; `peek()` varia (`Stack` lança, `ArrayDeque` retorna `null`).
- Implementar uma pilha manualmente, com array ou lista encadeada, ajuda a entender o funcionamento interno e todas as operações principais custam **O(1)**.
