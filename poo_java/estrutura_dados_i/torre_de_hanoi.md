# 🧩 Torre de Hanói em Java

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

**Autor:** Professor George Mendes Marra\
**Disciplina:** Estrutura de Dados / Programação II\
**Instituição:** Instituto Federal Goiano - Campus Hidrolândia
