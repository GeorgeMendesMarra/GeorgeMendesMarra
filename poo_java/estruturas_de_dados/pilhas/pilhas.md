# Pilhas (Stack) em Java

> **Resumo:** a pilha é uma estrutura de dados linear que segue o princípio **LIFO** (*Last In, First Out*): o **último** elemento a entrar é o **primeiro** a sair. Todo o acesso acontece em uma única extremidade, chamada **topo**.

## Sumário

1. [A ideia central](#1-a-ideia-central)
2. [Operações fundamentais](#2-operações-fundamentais)
3. [Breve história](#3-breve-história)
4. [Onde as pilhas são usadas](#4-onde-as-pilhas-são-usadas)
5. [Implementando uma pilha em Java](#5-implementando-uma-pilha-em-java)
6. [Exemplo prático: parênteses balanceados](#6-exemplo-prático-parênteses-balanceados)
7. [Pilhas na API do Java](#7-pilhas-na-api-do-java)
8. [Exercícios](#8-exercícios)
9. [Referências](#9-referências)

---

## 1. A ideia central

Pense em uma **pilha de pratos**: você coloca um prato novo sempre em cima e, quando precisa de um, retira o que está no topo. Não é possível pegar um prato do meio sem antes tirar todos os que estão acima.

```
        ┌───────┐
 topo → │   C   │  ← último a entrar, primeiro a sair
        ├───────┤
        │   B   │
        ├───────┤
        │   A   │  ← primeiro a entrar, último a sair
        └───────┘
```

**Regra de ouro:** só se acessa o topo.

## 2. Operações fundamentais

| Operação | Descrição | Complexidade |
|---|---|---|
| `push(x)` | Insere o elemento `x` no topo | O(1) |
| `pop()` | Remove e devolve o elemento do topo | O(1) |
| `peek()` / `top()` | Consulta o topo sem remover | O(1) |
| `isEmpty()` | Informa se a pilha está vazia | O(1) |
| `size()` | Informa quantos elementos existem | O(1) |

Todas as operações principais têm custo **constante** (O(1)): demoram o mesmo tempo com 10 ou com 10 milhões de elementos.

**Situações de erro típicas:**

- **Underflow:** tentar `pop()` ou `peek()` em uma pilha vazia.
- **Overflow:** tentar `push()` em uma pilha cheia (acontece em implementações com capacidade fixa).

**Exemplo passo a passo:**

| Comando | Estado da pilha (base → topo) | Retorno |
|---|---|---|
| `push(10)` | `[10]` | – |
| `push(20)` | `[10, 20]` | – |
| `push(30)` | `[10, 20, 30]` | – |
| `pop()` | `[10, 20]` | `30` |
| `peek()` | `[10, 20]` | `20` |
| `pop()` | `[10]` | `20` |

## 3. Breve história

A pilha nasceu de um problema prático: como o computador pode "lembrar" de onde parou ao executar uma sub-rotina?

### 3.1 Origem teórica (1945–1946)

**Alan Turing** descreveu, no projeto do computador ACE (1945–1946), as instruções **BURY** ("enterrar") e **UNBURY** ("desenterrar") para chamar sub-rotinas e retornar delas. O problema: se o programa principal chama a função A e A chama a função B, é preciso guardar os endereços de retorno. A solução natural foi uma estrutura em que o último endereço guardado é o primeiro a ser usado: uma pilha.

### 3.2 Formalização (1955–1957)

Os alemães **Friedrich L. Bauer** e **Klaus Samelson** propuseram, em 1955, o que chamaram de *Kellerprinzip* ("princípio do porão") para traduzir expressões de linguagens de programação, e depositaram a patente em 1957. Por esse trabalho, Bauer recebeu o *IEEE Computer Pioneer Award* em 1988. Os nomes **Push** e **Pop** se consolidaram mais tarde.

### 3.3 LISP e a recursão (1958)

Com o **LISP**, de John McCarthy, a pilha de execução se tornou indispensável: a linguagem depende de **recursão**, e cada chamada recursiva precisa guardar seu próprio estado. A pilha deixou de ser apenas um recurso de hardware e passou a ser uma estrutura de dados fundamental do software.

### 3.4 Notação Polonesa Reversa e calculadoras (anos 1960–1970)

A **Notação Polonesa Reversa (RPN)** é derivada da notação do lógico polonês Jan Łukasiewicz (1924). Ela chegou ao grande público com as calculadoras da **Hewlett-Packard (HP)**. Em vez de `2 + 3`, o usuário digitava `2 [ENTER] 3 [+]`:

1. o `2` é empilhado;
2. o `3` é empilhado no topo;
3. o `+` desempilha os dois, soma e empilha o resultado `5`.

Assim, expressões complexas dispensam parênteses.

## 4. Onde as pilhas são usadas

### 4.1 Pilha de chamadas (*call stack*)

É o uso mais importante. Cada vez que uma função é chamada, a JVM cria um **frame** no topo da pilha, com variáveis locais e endereço de retorno. Ao terminar, o frame é removido (*pop*).

Em Java, recursão sem condição de parada esgota essa pilha e lança `StackOverflowError`:

```java
public static void infinito() {
    infinito(); // sem caso base: StackOverflowError
}
```

### 4.2 Desfazer / Refazer (*Undo / Redo*)

Editores de texto empilham cada ação. O `Ctrl + Z` desempilha a última. Geralmente há uma segunda pilha para o `Ctrl + Y` (refazer).

### 4.3 Botão "Voltar" do navegador

Cada página visitada é empilhada. Ao clicar em "Voltar", a página atual sai do topo e a anterior reaparece.

### 4.4 Compiladores e expressões

Verificação de parênteses balanceados, conversão de notação infixa para pós-fixa e avaliação de expressões.

### 4.5 Algoritmos

Busca em profundidade (DFS), *backtracking* (labirintos, Sudoku) e conversão de bases numéricas.

## 5. Implementando uma pilha em Java

Implementação **genérica** usando um **array** de capacidade fixa.

```java
import java.util.NoSuchElementException;

public class Pilha<T> {

    private final Object[] elementos;
    private int tamanho; // também indica a próxima posição livre

    public Pilha(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        this.elementos = new Object[capacidade];
        this.tamanho = 0;
    }

    public void push(T elemento) {
        if (estaCheia()) {
            throw new IllegalStateException("Pilha cheia (overflow)");
        }
        elementos[tamanho++] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (estaVazia()) {
            throw new NoSuchElementException("Pilha vazia (underflow)");
        }
        T elemento = (T) elementos[--tamanho];
        elementos[tamanho] = null; // evita retenção de referência (memory leak)
        return elemento;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (estaVazia()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return (T) elementos[tamanho - 1];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == elementos.length;
    }

    public int tamanho() {
        return tamanho;
    }
}
```

**Testando:**

```java
public class Principal {
    public static void main(String[] args) {
        Pilha<Integer> pilha = new Pilha<>(5);

        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        System.out.println(pilha.pop());  // 30
        System.out.println(pilha.peek()); // 20
        System.out.println(pilha.pop());  // 20
        System.out.println(pilha.tamanho()); // 1
    }
}
```

> **Ponto didático:** a linha `elementos[tamanho] = null` é importante. Sem ela, o array continuaria apontando para o objeto removido e o *garbage collector* não conseguiria liberá-lo.

## 6. Exemplo prático: parênteses balanceados

Problema clássico: verificar se `{ [ ( A + B ) * ( C - D ) ] }` está bem formada.

**Algoritmo:**

1. Percorra a expressão caractere a caractere.
2. Se for abertura (`(`, `[`, `{`), **empilhe**.
3. Se for fechamento, **desempilhe** e confira se combina com a abertura.
4. No final, a pilha deve estar **vazia**.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Balanceamento {

    public static boolean estaBalanceada(String expressao) {
        Deque<Character> pilha = new ArrayDeque<>();

        for (char c : expressao.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pilha.isEmpty()) {
                    return false; // fechamento sem abertura
                }
                char abertura = pilha.pop();
                if (!combina(abertura, c)) {
                    return false;
                }
            }
        }
        return pilha.isEmpty(); // sobrou abertura sem fechamento?
    }

    private static boolean combina(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
            || (abertura == '[' && fechamento == ']')
            || (abertura == '{' && fechamento == '}');
    }

    public static void main(String[] args) {
        System.out.println(estaBalanceada("{ [ (A + B) * (C - D) ] }")); // true
        System.out.println(estaBalanceada("(A + B))"));                  // false
        System.out.println(estaBalanceada("[ ( ] )"));                   // false
    }
}
```

## 7. Pilhas na API do Java

| Opção | Observação |
|---|---|
| `java.util.Stack<E>` | Classe **legada** (estende `Vector`, que é sincronizado e mais lento). Funciona, mas **não é recomendada** em código novo. |
| `java.util.ArrayDeque<E>` | **Recomendada** pela própria documentação do Java. Use `push`, `pop` e `peek` através da interface `Deque`. |
| `java.util.LinkedList<E>` | Também implementa `Deque`, mas costuma ser menos eficiente que `ArrayDeque`. |

```java
Deque<String> pilha = new ArrayDeque<>();
pilha.push("A");
pilha.push("B");
System.out.println(pilha.pop()); // B
```

## 8. Exercícios

1. Implemente `Pilha<T>` com **lista encadeada** em vez de array (sem limite de capacidade).
2. Escreva um método que **inverta uma String** usando uma pilha.
3. Converta um número decimal para binário usando uma pilha.
4. Avalie uma expressão em notação pós-fixa (por exemplo, `3 4 + 2 *` deve resultar em `14`).
5. Simule o botão **Voltar/Avançar** de um navegador com duas pilhas.
6. Explique por que `pop()` em uma pilha vazia deve lançar uma exceção em vez de retornar `null`.

## 9. Referências

- Documentação oficial do Java: [`Deque`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Deque.html) e [`ArrayDeque`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html).
- CORMEN, T. H. et al. *Algoritmos: Teoria e Prática*. Elsevier.
- SEDGEWICK, R.; WAYNE, K. *Algorithms*. Addison-Wesley.
- BAUER, F. L.; SAMELSON, K. *Sequential Formula Translation*. Communications of the ACM, 1960.
