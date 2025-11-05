A **história da Torre de Hanói** é uma das mais interessantes da computação e da matemática recreativa — mistura **lenda, lógica e ciência**.
---

# 🧩 História da Torre de Hanói

## 📜 Origem

A **Torre de Hanói** (ou *Torre de Hanoi*) foi criada em **1883** pelo matemático francês **Édouard Lucas** (1842–1891).
Lucas era conhecido por inventar problemas matemáticos e jogos de lógica, e publicou o enigma sob o pseudônimo **N. Claus (de Siam)** — um anagrama de *Lucas d’Amiens* (sua cidade natal).

O problema apareceu pela primeira vez em um livro de Lucas intitulado **“Récréations Mathématiques”**.

---

## 🏛️ A Lenda dos Monges de Benares

Junto com o problema, Lucas apresentou uma **lenda indiana** (provavelmente inventada por ele mesmo) para dar um toque místico ao desafio:

> Em um templo na cidade sagrada de **Benares (Varanasi, Índia)**, monges recebem de Brahma um conjunto de **64 discos de ouro** empilhados em uma das três agulhas de diamante.
> A cada segundo, eles movem um disco — sempre obedecendo às regras:
>
> * Apenas um disco pode ser movido por vez;
> * Nenhum disco pode ficar sobre um menor.

Quando todos os 64 discos forem movidos da primeira à terceira haste, **o mundo chegará ao fim**.

---

## ⏱️ O Tempo da Lenda

De acordo com a fórmula da Torre de Hanói:

[
M(n) = 2^n - 1
]

Para 64 discos, o número de movimentos seria:

[
M(64) = 2^{64} - 1 = 18.446.744.073.709.551.615
]

Se os monges movessem **um disco por segundo**, levariam mais de:

> **584 bilhões de anos**

para terminar — muito mais tempo do que a idade estimada do Universo! 🌌

---

## 🧠 Interpretação Matemática

A Torre de Hanói é um **problema recursivo perfeito**, usado para ensinar:

* **Pensamento recursivo** (dividir um problema em subproblemas);
* **Estrutura de chamadas de função** (em linguagens como Java, C ou Python);
* **Análise de complexidade exponencial**.

A cada disco adicionado, o número de movimentos **dobra**, o que a torna um excelente exemplo de **crescimento exponencial**:

| Discos |    Mínimo de movimentos    |
| :----: | :------------------------: |
|    1   |              1             |
|    2   |              3             |
|    3   |              7             |
|    4   |             15             |
|    5   |             31             |
|   ...  |             ...            |
|   64   | 18.446.744.073.709.551.615 |

---

## 💻 Importância na Computação

A Torre de Hanói é amplamente utilizada no ensino de **algoritmos e estruturas de dados** para demonstrar:

* **Recursão direta** e **caso base**;
* **Pilha de chamadas** (stack memory);
* **Cálculo de complexidade** (O(2ⁿ)).

Também aparece em:

* **Exercícios de programação recursiva**;
* **Animações didáticas de algoritmos**;
* **Análises de desempenho de recursão em tempo e espaço**.

---

## 🎮 Curiosidades

* Existem versões **físicas** do jogo, feitas com madeira, metal ou plástico.
* A Torre de Hanói também é usada em **testes de psicologia cognitiva** para medir **planejamento e memória operacional**.
* Alguns **sistemas operacionais antigos (como o UNIX)** traziam o jogo como programa interativo no terminal.

---

## 📚 Conclusão

A Torre de Hanói é muito mais do que um simples quebra-cabeça —
é uma poderosa **metáfora matemática sobre recursão e paciência**.
Criada há mais de um século, continua sendo uma ferramenta essencial para o ensino de **lógica, algoritmos e pensamento computacional**.

---

## 📖 Teoria

A **Torre de Hanói** é um problema clássico de recursividade proposto
pelo matemático francês Édouard Lucas em 1883.\
O desafio consiste em mover uma pilha de discos de um pino (ou haste)
para outro, seguindo **três regras fundamentais**:

1.  Somente **um disco pode ser movido por vez**.
2.  Um disco **nunca pode ser colocado sobre um disco menor**.
3.  Todos os discos devem ser movidos **de um pino inicial para um pino
    destino**, utilizando um pino auxiliar.

O número mínimo de movimentos necessários para resolver o problema é
dado pela fórmula:

\[ M(n) = 2\^n - 1 \]

onde `n` é o número de discos.

------------------------------------------------------------------------

## 🧠 Conceito Recursivo

A solução da Torre de Hanói é naturalmente **recursiva**:

-   Para mover `n` discos de `origem` para `destino`:
    1.  Mova `n - 1` discos de `origem` para `auxiliar`.
    2.  Mova o disco restante (maior) de `origem` para `destino`.
    3.  Mova os `n - 1` discos de `auxiliar` para `destino`.

Essa decomposição repete-se até o caso base (`n == 1`).

------------------------------------------------------------------------

## 🧩 Código Java --- Torre de Hanói (Simples)

``` java
// Programa simples da Torre de Hanói
public class TorreDeHanoiSimples {

    // Função recursiva para resolver a Torre de Hanói
    static void moverDiscos(int n, char origem, char destino, char auxiliar) {
        // Caso base: apenas um disco para mover
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }
        // Mover n-1 discos de origem para auxiliar
        moverDiscos(n - 1, origem, auxiliar, destino);
        
        // Mover o último disco para o destino
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
        
        // Mover os n-1 discos do auxiliar para o destino
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    // Função principal
    public static void main(String[] args) {
        int n = 3; // número de discos
        moverDiscos(n, 'A', 'C', 'B');
    }
}
```

------------------------------------------------------------------------

## 🧮 Código Java --- Torre de Hanói com Contador de Movimentos

``` java
// Programa da Torre de Hanói com contador de movimentos
public class TorreDeHanoiContador {

    static int contador = 0;

    static void moverDiscos(int n, char origem, char destino, char auxiliar) {
        if (n == 1) {
            contador++;
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }
        moverDiscos(n - 1, origem, auxiliar, destino);
        contador++;
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {
        int n = 4; // número de discos
        moverDiscos(n, 'A', 'C', 'B');
        System.out.println("Total de movimentos: " + contador);
    }
}
```

------------------------------------------------------------------------

## ⚙️ Execução

### 🧱 Passos para executar:

1.  Salve o arquivo como `TorreDeHanoiSimples.java` (ou
    `TorreDeHanoiContador.java`).

2.  Compile o código:

    ``` bash
    javac TorreDeHanoiSimples.java
    ```

3.  Execute o programa:

    ``` bash
    java TorreDeHanoiSimples
    ```

### 💡 Exemplo de Saída

Para 3 discos:

    Mover disco 1 de A para C
    Mover disco 2 de A para B
    Mover disco 1 de C para B
    Mover disco 3 de A para C
    Mover disco 1 de B para A
    Mover disco 2 de B para C
    Mover disco 1 de A para C
    Total de movimentos: 7

------------------------------------------------------------------------

## ⏱️ Complexidade

A **complexidade de tempo** da Torre de Hanói é **O(2ⁿ)**, pois o número
de movimentos dobra a cada disco adicionado:

  n (discos)   Movimentos mínimos
  ------------ --------------------
  1            1
  2            3
  3            7
  4            15
  5            31
  ...          ...

-   **Tempo:** O(2ⁿ) → crescimento exponencial\
-   **Espaço:** O(n) → devido à profundidade da recursão

------------------------------------------------------------------------

## 📘 Conclusão

A Torre de Hanói é um dos exemplos clássicos de **recursão pura** e
demonstra como um problema complexo pode ser resolvido ao ser **dividido
em subproblemas menores**.\
Apesar de sua simplicidade conceitual, é um excelente exemplo de
**crescimento exponencial de complexidade**.

------------------------------------------------------------------------

## 📦 Ideal para GitHub

Este repositório pode conter: - `TorreDeHanoiSimples.java` -
`TorreDeHanoiContador.java` - `README.md` (este arquivo explicativo)

Para organizar no GitHub:

    📂 TorreDeHanoi-Java/
     ├── TorreDeHanoiSimples.java
     ├── TorreDeHanoiContador.java
     └── README.md

------------------------------------------------------------------------
