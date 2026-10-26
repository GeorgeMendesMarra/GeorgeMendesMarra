# Pilhas (Stack) em Java para Iniciantes

## Sumário

01. [O que é uma Pilha?](#1-o-que-é-uma-pilha)
02. [Analogias do Mundo Real](#2-analogias-do-mundo-real)
03. [Conceito LIFO (Last In, First Out)](#3-conceito-lifo-last-in-first-out)
04. [Um Pouco de História](#4-um-pouco-de-história)
05. [Onde as Pilhas São Usadas](#5-onde-as-pilhas-são-usadas)
06. [Tipos de Pilhas em Java](#6-tipos-de-pilhas-em-java)
07. [Sintaxe Básica](#7-sintaxe-básica)
08. [Operações Fundamentais](#8-operações-fundamentais)
09. [Exemplos Práticos dos Métodos](#9-exemplos-práticos-dos-métodos)
10. [Percorrendo uma Pilha](#10-percorrendo-uma-pilha)
11. [ArrayDeque como Pilha (Recomendado)](#11-arraydeque-como-pilha-recomendado)
12. [A Classe Stack (Legada)](#12-a-classe-stack-legada)
13. [Implementando uma Pilha do Zero](#13-implementando-uma-pilha-do-zero)
14. [Exemplos Completos](#14-exemplos-completos)
15. [Comparativo: Pilha vs Fila vs Lista](#15-comparativo-pilha-vs-fila-vs-lista)
16. [Erros Comuns e Dicas](#16-erros-comuns-e-dicas)
17. [Dicas Rápidas](#17-dicas-rápidas)
18. [Resumo para Iniciantes](#18-resumo-para-iniciantes)
19. [Exercícios](#19-exercícios)
20. [Próximos Passos](#20-próximos-passos)

---

## 1. O que é uma Pilha?

Uma **pilha (stack)** é uma estrutura de dados que organiza os elementos de forma **empilhada**: o último elemento que entra é o primeiro que sai. Só é possível inserir e remover elementos por uma única extremidade, chamada de **topo**.

### Representação Visual

```
          push(e)       pop()
             │            ▲
             ▼            │
        ┌─────────────────────┐
        │     D   (topo)      │
        ├─────────────────────┤
        │          C          │
        ├─────────────────────┤
        │          B          │
        ├─────────────────────┤
        │     A   (base)      │
        └─────────────────────┘

   A foi o PRIMEIRO a entrar e será o ÚLTIMO a sair.
   D foi o ÚLTIMO a entrar e será o PRIMEIRO a sair.
```

---

## 2. Analogias do Mundo Real

| Analogia                      | Explicação                                                                              |
|-------------------------------|-----------------------------------------------------------------------------------------|
| **Pilha de pratos**           | Você coloca e retira pratos sempre pelo topo; o último prato lavado é o primeiro usado  |
| **Pilha de livros**           | Para pegar o livro de baixo, é preciso tirar todos os que estão em cima                 |
| **Botão "Voltar" do navegador** | A última página visitada é a primeira a ser reaberta ao clicar em "Voltar"            |
| **Desfazer (Ctrl + Z)**       | A última ação feita é a primeira a ser desfeita                                         |
| **Beco sem saída**            | O último carro a entrar é o primeiro que precisa sair para liberar os outros            |
| **Chamadas de função**        | A última função chamada é a primeira a terminar (veja a *call stack* na seção 5)        |

---

## 3. Conceito LIFO (Last In, First Out)

**LIFO** = **L**ast **I**n, **F**irst **O**ut (Último que entra, primeiro que sai)

```
Passo a passo visual (o TOPO fica à esquerda, igual ao que o Java imprime):

1. Pilha vazia:
   [ ]

2. push("Prato 1"):
   [ Prato 1 ]

3. push("Prato 2") (entra no topo):
   [ Prato 2 , Prato 1 ]

4. push("Prato 3") (entra no topo):
   [ Prato 3 , Prato 2 , Prato 1 ]

5. pop() (sai o do topo - Prato 3):
   [ Prato 2 , Prato 1 ]

6. pop() (sai o do topo - Prato 2):
   [ Prato 1 ]
```

---

## 4. Um Pouco de História

A história da **pilha** é fascinante porque ela não nasceu apenas para organizar dados, mas para resolver um problema específico: como os computadores poderiam "lembrar" de onde pararam ao executar uma sub-rotina ou função.

### 4.1 A Origem Teórica (1945 - 1946)

O uso de uma pilha para chamar e retornar de sub-rotinas aparece nos primeiros computadores. A máquina **Z4**, de **Konrad Zuse**, já tinha sub-rotinas e uma pilha de dois níveis em 1945. Em 1946, **Alan Turing** descreveu o mesmo recurso no projeto do computador ACE, usando os termos **"bury"** (enterrar) e **"unbury"** (desenterrar) para chamar e retornar de sub-rotinas.

- **O Problema:** Quando um programa principal chama uma função A, e a função A chama uma função B, o computador precisa salvar o endereço de retorno para saber para onde voltar.
- **A Solução:** Uma estrutura de "pilha" é a forma natural de guardar esses endereços de retorno: o último a ser guardado é o primeiro a ser usado.

### 4.2 A Formalização (1955 - 1957)

Os alemães **Friedrich L. Bauer** e **Klaus Samelson**, da Universidade Técnica de Munique, propuseram em **1955** a ideia da pilha como *Operationskeller* ("porão de operações") e depositaram a **patente em 1957**. O objetivo era a tradução automática de fórmulas, a base dos futuros compiladores.

- **Descoberta independente:** O australiano **Charles Hamblin** chegou ao mesmo conceito, de forma independente, em 1957.
- **Reconhecimento:** Em 1988, Bauer recebeu o prêmio *IEEE Computer Pioneer Award* pela invenção do princípio da pilha.

### 4.3 A Linguagem LISP e a Recursão (1958)

Com a criação da linguagem **LISP** por **John McCarthy**, a pilha ganhou ainda mais importância. O LISP usava intensamente a **recursividade** (uma função que chama a si mesma).

- Cada chamada recursiva precisa guardar seu próprio estado (variáveis e ponto de retorno), e a pilha é o lugar natural para isso. Assim, a pilha deixou de ser só um recurso de hardware e se consolidou como estrutura de dados fundamental do software.

### 4.4 Notação Polonesa Reversa e Calculadoras (Anos 60 - 70)

A pilha saiu do interior dos computadores e chegou às mãos dos usuários através das calculadoras da **Hewlett-Packard (HP)**, como a HP 9100A (1968) e a HP-35 (1972).

- **RPN (Reverse Polish Notation):** Em vez de digitar `2 + 3`, o usuário digitava `2 [ENTER] 3 [+]`. A notação polonesa foi criada em 1920 pelo matemático polonês **Jan Łukasiewicz**.
- **Funcionamento:** O `2` ia para a pilha, o `3` ia para o topo da pilha, e o operador `+` retirava os dois, somava-os e colocava o resultado de volta no topo. Isso eliminava a necessidade de parênteses em cálculos complexos. (Você vai programar isso na seção 14.4.)

---

## 5. Onde as Pilhas São Usadas

Para entender como a pilha moldou a computação, veja estes usos clássicos:

### 5.1 A Pilha de Chamadas (Call Stack)

É o uso mais importante. Cada vez que o seu código executa um método, a **JVM (Java Virtual Machine)** cria um *frame* no topo da pilha com as variáveis locais e o ponto de retorno. Quando o método termina, esse frame é removido (*pop*).

```
main() chama a() ──► a() chama b() ──► b() termina ──► a() termina ──► main() continua

 Pilha de chamadas:
                 ┌──────┐
                 │  b() │  ← topo (executando agora)
                 ├──────┤
                 │  a() │
                 ├──────┤
                 │main()│  ← base
                 └──────┘
```

- **Curiosidade:** O erro **StackOverflowError** (estouro de pilha) ocorre quando há chamadas demais (geralmente uma recursão infinita) e a memória da pilha acaba.

### 5.2 O Botão "Voltar" e o "Desfazer"

- **Navegador:** Quando você visita sites, a URL atual é empilhada. Ao clicar em "Voltar", o navegador faz um *pop* na URL atual para revelar a anterior.
- **Editores de Texto:** Cada letra que você digita ou apaga é uma operação empilhada. O `Ctrl + Z` remove a última operação do topo da pilha.

### 5.3 Avaliação de Expressões Matemáticas

Compiladores usam pilhas para garantir que os parênteses em uma fórmula como `( (A + B) * (C - D) )` estejam balanceados e para converter a fórmula em algo que o processador entenda.

### 5.4 Outras Aplicações

- Busca em profundidade (DFS) em grafos e árvores
- Conversão de números decimais para binário
- Inversão de textos e listas
- Verificação de tags HTML/XML abertas e fechadas

---

## 6. Tipos de Pilhas em Java

| Tipo                          | Classe                                      | Descrição                                      | Quando usar                           |
|-------------------------------|---------------------------------------------|------------------------------------------------|---------------------------------------|
| **Pilha moderna**             | `ArrayDeque` (via `Deque`)                  | Rápida, sem sincronização, usa `push`/`pop`    | **Escolha padrão** para código novo   |
| **Pilha sobre lista**         | `LinkedList` (via `Deque`)                  | Também implementa `Deque`                      | Raramente (aceita `null`)             |
| **Pilha legada**              | `Stack`                                     | Classe original do Java 1.0 (herda de `Vector`)| Somente em código antigo              |
| **Pilha para várias threads** | `ConcurrentLinkedDeque`, `LinkedBlockingDeque` | Seguras para programação concorrente        | Aplicações multi-thread               |
| **Pilha própria**             | Você mesmo implementa                       | Com array ou lista encadeada                   | Para estudar como a pilha funciona    |

> **Para iniciantes:** Comece com `Deque<T> pilha = new ArrayDeque<>();`

> ⚠️ **Atenção:** Diferente de `Queue`, o Java **não tem uma interface chamada `Stack`**. `Stack` é uma **classe** antiga. Para pilhas modernas, usamos a interface `Deque` com os métodos `push()`, `pop()` e `peek()`.

---

## 7. Sintaxe Básica

### 7.1 Importação

```java
import java.util.Deque;        // Interface Deque (usada como pilha)
import java.util.ArrayDeque;   // Implementação ArrayDeque (recomendada)
import java.util.LinkedList;   // Implementação LinkedList
import java.util.Stack;        // Classe Stack (legada)
```

### 7.2 Declaração e Criação

```java
// Usando ArrayDeque (recomendado)
Deque<String> pilha = new ArrayDeque<>();

// Usando LinkedList
Deque<String> pilha2 = new LinkedList<>();

// Usando Stack (legada - evite em código novo)
Stack<String> pilha3 = new Stack<>();
```

### 7.3 Tipos de dados

```java
// Pilha de Strings
Deque<String> nomes = new ArrayDeque<>();

// Pilha de Integers
Deque<Integer> numeros = new ArrayDeque<>();

// Pilha de Doubles
Deque<Double> precos = new ArrayDeque<>();

// Pilha de Characters
Deque<Character> letras = new ArrayDeque<>();

// Pilha de objetos personalizados
Deque<Pessoa> pessoas = new ArrayDeque<>();
```

---

## 8. Operações Fundamentais

### 8.1 Métodos Principais (`Deque` usado como pilha)

| Método          | Descrição                                   | Retorna            | Lança exceção                      |
|-----------------|---------------------------------------------|--------------------|------------------------------------|
| `push(elemento)`| Empilha no topo                             | `void`             | Só no `ArrayDeque`, se o elemento for `null` |
| `pop()`         | Remove e retorna o elemento do topo         | Elemento           | Sim (se vazia)                     |
| `poll()`        | Remove e retorna o elemento do topo         | Elemento ou `null` | Não                                |
| `peek()`        | Retorna o topo (sem remover)                | Elemento ou `null` | Não                                |
| `size()`        | Retorna o tamanho                           | `int`              | Não                                |
| `isEmpty()`     | Verifica se está vazia                      | `boolean`          | Não                                |
| `clear()`       | Remove todos os elementos                   | `void`             | Não                                |

### 8.2 Métodos da classe `Stack` (legada)

| Método            | Descrição                                      | Retorna   | Lança exceção                         |
|-------------------|------------------------------------------------|-----------|---------------------------------------|
| `push(elemento)`  | Empilha no topo                                | Elemento  | Não                                   |
| `pop()`           | Remove e retorna o topo                        | Elemento  | `EmptyStackException` (se vazia)      |
| `peek()`          | Retorna o topo (sem remover)                   | Elemento  | `EmptyStackException` (se vazia)      |
| `empty()`         | Verifica se está vazia                         | `boolean` | Não                                   |
| `search(objeto)`  | Distância até o topo (topo = 1), ou `-1`       | `int`     | Não                                   |

### 8.3 Qual método usar?

| Situação                                  | Método recomendado                 |
|-------------------------------------------|------------------------------------|
| **Empilhar**                              | `push()`                           |
| **Desempilhar** (certo que tem elemento)  | `pop()`                            |
| **Desempilhar** (pode estar vazia)        | `poll()` (ou checar `isEmpty()`)   |
| **Consultar o topo**                      | `peek()` (retorna `null` se vazia) |
| **Saber se está vazia**                   | `isEmpty()`                        |

> ⚠️ **Para iniciantes:** Sempre confira `isEmpty()` antes de chamar `pop()`, ou use `poll()`, que devolve `null` em vez de lançar exceção.

---

## 9. Exemplos Práticos dos Métodos

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ExemploPilha {
    public static void main(String[] args) {
        // Criando a pilha
        Deque<String> pilha = new ArrayDeque<>();

        // 1. Empilhando elementos (push)
        pilha.push("Livro 1");
        pilha.push("Livro 2");
        pilha.push("Livro 3");
        pilha.push("Livro 4");

        System.out.println("Pilha: " + pilha);
        // Saída: Pilha: [Livro 4, Livro 3, Livro 2, Livro 1]
        // (o primeiro elemento impresso é o TOPO)

        // 2. Consultando o topo (peek)
        String topo = pilha.peek();
        System.out.println("Topo da pilha: " + topo);
        // Saída: Topo da pilha: Livro 4

        // 3. Desempilhando (pop)
        String removido = pilha.pop();
        System.out.println("Removido: " + removido);
        // Saída: Removido: Livro 4

        System.out.println("Pilha após remoção: " + pilha);
        // Saída: Pilha após remoção: [Livro 3, Livro 2, Livro 1]

        // 4. Tamanho da pilha
        System.out.println("Livros na pilha: " + pilha.size());
        // Saída: Livros na pilha: 3

        // 5. Verificando se está vazia
        System.out.println("Pilha está vazia? " + pilha.isEmpty());
        // Saída: Pilha está vazia? false

        // 6. Desempilhando todos
        while (!pilha.isEmpty()) {
            System.out.println("Desempilhando: " + pilha.pop());
        }
        // Saída: Livro 3, Livro 2, Livro 1 (nessa ordem)

        System.out.println("Pilha após esvaziar: " + pilha);
        // Saída: Pilha após esvaziar: []
        System.out.println("Pilha está vazia? " + pilha.isEmpty());
        // Saída: Pilha está vazia? true
    }
}
```

---

## 10. Percorrendo uma Pilha

Há quatro formas de percorrer uma pilha. As três primeiras só **consultam**; a última **remove** os elementos enquanto percorre.

| Forma                          | Remove? | Ordem                  |
|--------------------------------|---------|------------------------|
| `for-each`                     | Não     | Do topo para a base    |
| `iterator()`                   | Não     | Do topo para a base    |
| `descendingIterator()`         | Não     | Da base para o topo    |
| `while` com `pop()`            | **Sim** | Do topo para a base    |

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PercorrendoPilha {
    public static void main(String[] args) {
        Deque<String> pilha = new ArrayDeque<>();
        pilha.push("A");
        pilha.push("B");
        pilha.push("C");

        // 10.1 PERCORRER SEM REMOVER (do TOPO para a BASE)
        for (String elemento : pilha) {
            System.out.println("Elemento: " + elemento);
        }
        // Saída: C, B, A (ainda estão na pilha)

        // 10.2 PERCORRER COM ITERATOR (também do topo para a base)
        Iterator<String> iterator = pilha.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // Saída: C, B, A

        // 10.3 PERCORRER DA BASE PARA O TOPO
        Iterator<String> inverso = pilha.descendingIterator();
        while (inverso.hasNext()) {
            System.out.println(inverso.next());
        }
        // Saída: A, B, C

        // 10.4 PERCORRER REMOVENDO (esvazia a pilha)
        while (!pilha.isEmpty()) {
            String elemento = pilha.pop();
            System.out.println("Processando: " + elemento);
        }
        // Saída: C, B, A
        // Após isso, a pilha fica vazia
    }
}
```

> ⚠️ **Atenção:** Em `ArrayDeque`, o `for-each` percorre do **topo para a base**. Na classe `Stack` (legada) é o contrário: do **fundo para o topo**.

---

## 11. ArrayDeque como Pilha (Recomendado)

### 11.1 O que é?

`ArrayDeque` (*Double Ended Queue*, fila de duas pontas) é a implementação recomendada pela documentação do Java para criar pilhas. Os métodos `push()`, `pop()` e `peek()` trabalham sempre na **mesma ponta** da estrutura, e é isso que a transforma em uma pilha LIFO.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class PilhaArrayDeque {
    public static void main(String[] args) {
        // Declare como Deque (interface) e instancie ArrayDeque
        Deque<Integer> pilha = new ArrayDeque<>();

        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        System.out.println("Topo: " + pilha.peek());  // 30

        while (!pilha.isEmpty()) {
            System.out.println("Processando: " + pilha.pop());
        }
        // Saída: 30, 20, 10

        // Pilha vazia: peek() devolve null, mas pop() lança exceção
        System.out.println("peek em pilha vazia: " + pilha.peek());  // null
    }
}
```

### 11.2 Comparativo: ArrayDeque vs Stack

| Característica          | `ArrayDeque`                      | `Stack`                                   |
|-------------------------|-----------------------------------|-------------------------------------------|
| **Desempenho**          | Muito rápido ⚡                    | Mais lento (métodos sincronizados)        |
| **Origem**              | Java 6                            | Java 1.0 (legada)                         |
| **Herda de**            | `AbstractCollection`              | `Vector`                                  |
| **Aceita `null`**       | Não (`NullPointerException`)      | Sim                                       |
| **Ordem do `for-each`** | Do topo para a base               | Da base para o topo                       |
| **Expõe acesso por índice** | Não                           | Sim (`get(i)`, `add(i, e)` quebram o LIFO)|
| **`pop()` em pilha vazia** | `NoSuchElementException`       | `EmptyStackException`                     |

> **Recomendação:** Use `ArrayDeque` para pilhas, a menos que esteja mantendo código antigo que já use `Stack`.

---

## 12. A Classe Stack (Legada)

A classe `java.util.Stack` existe desde o Java 1.0. Você vai encontrá-la em muitos livros, tutoriais e códigos antigos, por isso é importante conhecê-la, mas **evite-a em projetos novos**.

**Por que evitar:**

- Ela **herda de `Vector`**, então todos os seus métodos são sincronizados, o que a deixa mais lenta em programas de uma única *thread*.
- Como é um `Vector`, também expõe `get(i)`, `add(i, e)` e `remove(i)`, que acessam ou alteram o **meio** da pilha e contradizem o conceito de LIFO.
- O `for-each` percorre da **base para o topo**, o inverso do que se espera de uma pilha.

```java
import java.util.Stack;

public class PilhaStack {
    public static void main(String[] args) {
        Stack<String> pilha = new Stack<>();

        pilha.push("A");
        pilha.push("B");
        pilha.push("C");

        System.out.println("Pilha: " + pilha);
        // Saída: Pilha: [A, B, C]  (aqui a BASE é impressa primeiro)

        System.out.println("Topo: " + pilha.peek());        // C
        System.out.println("Vazia? " + pilha.empty());      // false
        System.out.println("search(\"C\"): " + pilha.search("C"));  // 1 (é o topo)
        System.out.println("search(\"A\"): " + pilha.search("A"));  // 3
        System.out.println("search(\"Z\"): " + pilha.search("Z"));  // -1 (não existe)

        // Cuidado: isto acessa a BASE e quebra o conceito de pilha!
        System.out.println("get(0): " + pilha.get(0));      // A

        System.out.println("Removido: " + pilha.pop());     // C
    }
}
```

---

## 13. Implementando uma Pilha do Zero

Para entender o que acontece "por baixo do capô", vale construir uma pilha manualmente. Existem duas formas clássicas.

### 13.1 Pilha com Array (tamanho fixo)

```java
import java.util.NoSuchElementException;

public class PilhaArray<T> {
    private final Object[] elementos;
    private int topo = -1;  // -1 indica pilha vazia

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
        elementos[topo--] = null;  // libera a referência para o coletor de lixo
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

### 13.2 Pilha com Lista Encadeada (sem limite de tamanho)

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
        topo = new No<>(valor, topo);  // o novo nó aponta para o antigo topo
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

### 13.3 Usando as pilhas criadas

```java
public class UsandoPilhaManual {
    public static void main(String[] args) {
        PilhaArray<Integer> pilha1 = new PilhaArray<>(3);
        pilha1.push(1);
        pilha1.push(2);
        pilha1.push(3);
        System.out.println("Cheia? " + pilha1.isCheia());   // true
        System.out.println("Pop: " + pilha1.pop());         // 3

        PilhaEncadeada<String> pilha2 = new PilhaEncadeada<>();
        pilha2.push("A");
        pilha2.push("B");
        System.out.println("Topo: " + pilha2.peek());       // B
        System.out.println("Tamanho: " + pilha2.size());    // 2
    }
}
```

### 13.4 Array ou Lista Encadeada?

| Característica        | Pilha com Array                        | Pilha com Lista Encadeada            |
|-----------------------|----------------------------------------|--------------------------------------|
| **Tamanho**           | Fixo (pode ficar cheia)                | Sem limite (só a memória limita)     |
| **Memória por item**  | Menor                                  | Maior (cada nó guarda uma referência)|
| **push / pop / peek** | O(1)                                   | O(1)                                 |
| **Quando usar**       | Quando o tamanho máximo é conhecido    | Quando o tamanho é imprevisível      |

> 📌 Para uma versão mais completa, com comparações e mais detalhes de uso, veja o arquivo [pilhas_java.md](pilhas_java.md).

---

## 14. Exemplos Completos

### 14.1 Inversor de Texto

Como a pilha devolve os elementos na ordem inversa da entrada, ela é perfeita para inverter textos.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class InverterTexto {

    public static String inverter(String texto) {
        Deque<Character> pilha = new ArrayDeque<>();

        // Empilha letra por letra
        for (char c : texto.toCharArray()) {
            pilha.push(c);
        }

        // Desempilha montando o texto invertido
        StringBuilder invertido = new StringBuilder();
        while (!pilha.isEmpty()) {
            invertido.append(pilha.pop());
        }
        return invertido.toString();
    }

    public static void main(String[] args) {
        System.out.println(inverter("Java"));               // avaJ
        System.out.println(inverter("Pilhas sao legais"));  // siagel oas sahliP
    }
}
```

### 14.2 Verificador de Parênteses Balanceados

Uma aplicação clássica de pilha: verificar se `()`, `[]` e `{}` estão corretamente abertos e fechados. Compiladores e editores de código fazem algo parecido.

**Estratégia:** ao encontrar um símbolo de abertura, empilhe; ao encontrar um de fechamento, desempilhe e confira se formam um par. No final, a pilha deve estar vazia.

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
                    return false;  // fechou sem ter aberto
                }
                char abertura = pilha.pop();
                if (!formamPar(abertura, c)) {
                    return false;  // tipos diferentes
                }
            }
        }
        return pilha.isEmpty();  // sobrou algo aberto?
    }

    private static boolean formamPar(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
            || (abertura == '[' && fechamento == ']')
            || (abertura == '{' && fechamento == '}');
    }

    public static void main(String[] args) {
        System.out.println(estaBalanceado("{[(1 + 2) * 3] - 4}"));  // true
        System.out.println(estaBalanceado("(1 + 2]"));              // false
        System.out.println(estaBalanceado("((1 + 2)"));             // false
        System.out.println(estaBalanceado("1 + 2)"));               // false
    }
}
```

### 14.3 Histórico do Navegador (Voltar e Avançar)

Usa **duas pilhas**: uma com as páginas anteriores e outra com as páginas "à frente".

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class HistoricoNavegador {
    private final Deque<String> pilhaVoltar = new ArrayDeque<>();
    private final Deque<String> pilhaAvancar = new ArrayDeque<>();
    private String paginaAtual;

    public HistoricoNavegador(String paginaInicial) {
        this.paginaAtual = paginaInicial;
    }

    public void visitar(String url) {
        pilhaVoltar.push(paginaAtual);
        paginaAtual = url;
        pilhaAvancar.clear();  // visitar uma página nova invalida o "avançar"
    }

    public void voltar() {
        if (pilhaVoltar.isEmpty()) {
            System.out.println("Não há página anterior.");
            return;
        }
        pilhaAvancar.push(paginaAtual);
        paginaAtual = pilhaVoltar.pop();
    }

    public void avancar() {
        if (pilhaAvancar.isEmpty()) {
            System.out.println("Não há página seguinte.");
            return;
        }
        pilhaVoltar.push(paginaAtual);
        paginaAtual = pilhaAvancar.pop();
    }

    public String getPaginaAtual() {
        return paginaAtual;
    }

    public static void main(String[] args) {
        HistoricoNavegador nav = new HistoricoNavegador("google.com");

        nav.visitar("github.com");
        nav.visitar("wikipedia.org");
        System.out.println("Atual: " + nav.getPaginaAtual());  // wikipedia.org

        nav.voltar();
        System.out.println("Atual: " + nav.getPaginaAtual());  // github.com

        nav.voltar();
        System.out.println("Atual: " + nav.getPaginaAtual());  // google.com

        nav.voltar();  // Não há página anterior.

        nav.avancar();
        System.out.println("Atual: " + nav.getPaginaAtual());  // github.com

        nav.visitar("youtube.com");
        nav.avancar();  // Não há página seguinte.
        System.out.println("Atual: " + nav.getPaginaAtual());  // youtube.com
    }
}
```

### 14.4 Calculadora RPN (como as calculadoras HP)

Avalia expressões em **notação polonesa reversa** (pós-fixa), como `3 4 + 2 *`, que equivale a `(3 + 4) * 2`.

**Estratégia:** número → empilha. Operador → desempilha dois números, calcula e empilha o resultado.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class CalculadoraRPN {

    public static double avaliar(String expressao) {
        Deque<Double> pilha = new ArrayDeque<>();

        for (String token : expressao.trim().split("\\s+")) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/": {
                    if (pilha.size() < 2) {
                        throw new IllegalArgumentException(
                            "Expressão inválida: faltam operandos para '" + token + "'");
                    }
                    double b = pilha.pop();  // o topo é o SEGUNDO operando
                    double a = pilha.pop();
                    pilha.push(calcular(a, b, token));
                    break;
                }
                default:
                    pilha.push(Double.parseDouble(token));
            }
        }

        if (pilha.size() != 1) {
            throw new IllegalArgumentException("Expressão inválida");
        }
        return pilha.pop();
    }

    private static double calcular(double a, double b, String operador) {
        switch (operador) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            default:  return a / b;  // "/"
        }
    }

    public static void main(String[] args) {
        System.out.println(avaliar("2 3 +"));                // 5.0
        System.out.println(avaliar("3 4 + 2 *"));            // 14.0
        System.out.println(avaliar("5 1 2 + 4 * + 3 -"));    // 14.0
        System.out.println(avaliar("10 2 8 * + 3 -"));       // 23.0
    }
}
```

**Passo a passo de `3 4 + 2 *`:**

```
Token   Ação                          Pilha (topo à esquerda)
  3     empilha 3                     [ 3 ]
  4     empilha 4                     [ 4 , 3 ]
  +     desempilha 4 e 3, soma = 7    [ 7 ]
  2     empilha 2                     [ 2 , 7 ]
  *     desempilha 2 e 7, mult = 14   [ 14 ]
```

---

## 15. Comparativo: Pilha vs Fila vs Lista

| Característica  | Pilha (Stack)                              | Fila (Queue)                                 | Lista (ArrayList)           |
|-----------------|--------------------------------------------|----------------------------------------------|-----------------------------|
| **Ordem**       | LIFO (último a entrar, primeiro a sair)    | FIFO (primeiro a entrar, primeiro a sair)    | Acesso por índice           |
| **Inserção**    | No topo                                    | No final                                     | Em qualquer posição         |
| **Remoção**     | Do topo                                    | Do início                                    | De qualquer posição         |
| **Acesso**      | Apenas ao topo                             | Apenas ao primeiro                           | Direto por índice           |
| **Quando usar** | Desfazer (Ctrl+Z), voltar no navegador, recursão | Senhas, filas de atendimento, impressora | Listas com acesso aleatório |

```
Visualização das diferenças:

PILHA (LIFO)         FILA (FIFO)          LISTA (Índice)

 Entrada/Saída        Entrada                Posições:
      │ ▲                │                    ┌─────┐
      ▼ │                ▼                    │  0  │
   ┌─────┐            ┌─────┐                 ├─────┤
   │  3º │ ← topo     │  3º │ ← último        │  1  │
   ├─────┤            ├─────┤                 ├─────┤
   │  2º │            │  2º │                 │  2  │
   ├─────┤            ├─────┤                 └─────┘
   │  1º │ ← base     │  1º │ ← primeiro      Acesso direto
   └─────┘            └─────┘                 por posição
                         │
                         ▼
                       Saída
```

> 📌 Para conhecer a fila em detalhes, veja o arquivo [filas.md](../filas/filas.md).

---

## 16. Erros Comuns e Dicas

### Erro 1: Usar `pop()` sem verificar se a pilha está vazia

```java
// ❌ ERRADO - Pode lançar exceção
Deque<String> pilha = new ArrayDeque<>();
pilha.pop();  // Lança NoSuchElementException se vazia

// ✅ CORRETO
if (!pilha.isEmpty()) {
    pilha.pop();
}
// OU use poll()
String elemento = pilha.poll();  // Retorna null se vazia
```

### Erro 2: Confundir pilha com fila (usar `add()` em vez de `push()`)

```java
// ❌ ERRADO - add() insere no FINAL, mas pop() remove do INÍCIO!
Deque<String> pilha = new ArrayDeque<>();
pilha.add("A");
pilha.add("B");
pilha.add("C");
System.out.println(pilha.pop());  // Saída: A (não é LIFO!)

// ✅ CORRETO - use push() para empilhar
Deque<String> pilhaOk = new ArrayDeque<>();
pilhaOk.push("A");
pilhaOk.push("B");
pilhaOk.push("C");
System.out.println(pilhaOk.pop());  // Saída: C
```

### Erro 3: Esquecer que `Stack` itera da base para o topo

```java
// ⚠️ ATENÇÃO - A ordem do for-each muda conforme a classe!
Stack<String> stack = new Stack<>();
stack.push("A");
stack.push("B");
stack.push("C");
for (String s : stack) {
    System.out.print(s + " ");  // Saída: A B C (da base para o topo)
}

Deque<String> deque = new ArrayDeque<>();
deque.push("A");
deque.push("B");
deque.push("C");
for (String s : deque) {
    System.out.print(s + " ");  // Saída: C B A (do topo para a base)
}
```

### Erro 4: Inserir `null` em um `ArrayDeque`

```java
// ❌ ERRADO - ArrayDeque não aceita null
Deque<String> pilha = new ArrayDeque<>();
pilha.push(null);  // Lança NullPointerException
```

### Erro 5: Recursão sem condição de parada (StackOverflowError)

Cada chamada de método ocupa espaço na pilha de chamadas. Se a recursão nunca termina, a pilha enche.

```java
public class EstouroDePilha {
    static int profundidade = 0;

    static void recursaoInfinita() {
        profundidade++;
        recursaoInfinita();  // ❌ nunca para!
    }

    public static void main(String[] args) {
        try {
            recursaoInfinita();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError! Chamadas empilhadas: " + profundidade);
            // O número varia conforme a máquina e a configuração da JVM
        }
    }
}
```

> ⚠️ **Não confunda:** `StackOverflowError` é o estouro da **pilha de chamadas da JVM**. Não é o mesmo que "pilha cheia" da sua `PilhaArray`, embora a ideia seja parecida: ambas ficaram sem espaço. Capturar `StackOverflowError` aqui serve só para demonstração; em programas reais, corrija a recursão.

---

## 17. Dicas Rápidas

| Dica                                    | Explicação                                                               |
|-----------------------------------------|--------------------------------------------------------------------------|
| 📌 **Escolha a implementação certa**     | `ArrayDeque` para pilhas em código novo; `Stack` só em código legado     |
| 📌 **Declare como `Deque`**              | Assim você usa só `push`, `pop` e `peek`, sem se confundir com `add()`   |
| 📌 **Use `push()` e `pop()` juntos**     | Nunca misture com `add()`/`offer()`, que inserem no lado errado          |
| 📌 **Sempre verifique `isEmpty()`**      | Antes de `pop()` em código crítico (ou use `poll()`)                     |
| 📌 **`Deque` não é só pilha**            | Ele também funciona como fila de duas pontas                             |
| 📌 **`peek()` retorna `null` em `Deque`** | Em `Stack`, `peek()` lança `EmptyStackException`                        |
| 📌 **Pilha é sobre o TOPO**              | Se você precisa acessar o meio, talvez a estrutura certa seja uma lista  |

---

## 18. Resumo para Iniciantes

### Passo a passo para usar uma Pilha:

```java
// 1. IMPORTAR
import java.util.ArrayDeque;
import java.util.Deque;

public class ResumoPilha {
    public static void main(String[] args) {
        // 2. DECLARAR E CRIAR
        Deque<String> minhaPilha = new ArrayDeque<>();

        // 3. EMPILHAR
        minhaPilha.push("Primeiro");
        minhaPilha.push("Segundo");
        minhaPilha.push("Terceiro");

        // 4. CONSULTAR (vê quem está no topo sem remover)
        String topo = minhaPilha.peek();
        System.out.println("Topo: " + topo);  // Terceiro

        // 5. DESEMPILHAR (remove o do topo)
        String removido = minhaPilha.pop();
        System.out.println("Removido: " + removido);  // Terceiro

        // 6. PERCORRER (sem remover, do topo para a base)
        for (String elemento : minhaPilha) {
            System.out.println(elemento);  // Segundo, Primeiro
        }

        // 7. PERCORRER REMOVENDO (esvazia a pilha)
        while (!minhaPilha.isEmpty()) {
            String elemento = minhaPilha.pop();
            System.out.println("Processando: " + elemento);  // Segundo, Primeiro
        }
    }
}
```

### Resumo em poucas linhas

- A pilha segue **LIFO**: só se insere e remove pelo **topo**.
- Para código novo, use `Deque<T> pilha = new ArrayDeque<>();` com `push`, `pop` e `peek`.
- `Stack` é **legada**: sincronizada, herda de `Vector` e itera da base para o topo.
- `pop()` em pilha vazia lança exceção; `peek()` retorna `null` em `Deque` e lança exceção em `Stack`.
- Todas as operações principais (`push`, `pop`, `peek`) são **O(1)**: custam o mesmo tempo com 10 ou com 10 milhões de itens.
- **Regra de ouro:** acesso apenas ao topo. Não se pode "puxar" um elemento do meio sem remover todos os que estão acima.

---

## 19. Exercícios

### Exercício 1 (Fácil)
Crie uma pilha de números inteiros, empilhe os números de 1 a 5 e depois desempilhe todos exibindo cada um. Em que ordem eles saem?

### Exercício 2 (Fácil)
Simule uma pilha de pratos sujos: empilhe 5 pratos (use nomes) e "lave" um por vez. Qual prato é lavado primeiro?

### Exercício 3 (Médio)
Crie um programa que recebe textos do usuário e os coloca em uma pilha. Quando o usuário digitar "desfazer", o programa remove e exibe o último texto digitado. Quando digitar "sair", o programa encerra.

### Exercício 4 (Médio)
Converta um número decimal em binário usando uma pilha (divida por 2 empilhando os restos e depois desempilhe).

### Exercício 5 (Médio)
Adapte a `PilhaArray<T>` para **dobrar a capacidade automaticamente** quando ficar cheia, em vez de lançar exceção.

### Exercício 6 (Difícil)
Crie um editor de texto simples com **desfazer** e **refazer**, usando duas pilhas (como no histórico do navegador da seção 14.3).

---

## 20. Próximos Passos

Depois de dominar Pilhas (Stack), estude:

1. **[Filas (Queue)](../filas/filas.md)** - A estrutura FIFO, par da pilha
2. **Deque (Double Ended Queue)** - Estrutura que permite adicionar/remover nas duas pontas, usada como pilha e como fila
3. **Recursão** - Entenda como a pilha de chamadas funciona na prática
4. **Busca em profundidade (DFS)** - Percurso de grafos e árvores usando pilha
5. **Conversão infixa para pós-fixa** - Transforme `(3 + 4) * 2` em `3 4 + 2 *` usando pilha (algoritmo *Shunting-yard*, de Dijkstra) e avalie com a `CalculadoraRPN`
6. **Pilhas e concorrência** - `ConcurrentLinkedDeque` e `LinkedBlockingDeque` para programação com várias threads
