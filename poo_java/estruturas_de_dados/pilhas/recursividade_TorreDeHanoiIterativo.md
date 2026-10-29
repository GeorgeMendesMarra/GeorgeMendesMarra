# Recursividade explicada com a Torre de Hanói

Um detalhe importante: o arquivo que você enviou, `TorreDeHanoiIterativo.java`, é a versão **sem recursão**. O próprio comentário do código diz: *"Esta versão elimina a recursão e simula os movimentos manualmente"*. Por isso ele é ótimo para ensinar recursividade **por contraste**: primeiro mostramos a solução recursiva, depois o que o código iterativo precisa fazer para dar o mesmo resultado sem ela.

---

## 1. O que é recursividade

Recursividade é quando **uma função chama a si mesma** para resolver uma versão menor do mesmo problema.

Toda solução recursiva precisa de duas partes:

| Parte | Função |
|---|---|
| **Caso base** | A situação mais simples, resolvida diretamente, sem nova chamada. É o que faz a recursão **parar**. |
| **Caso recursivo** | Reduz o problema a uma versão menor e chama a si mesma. |

Sem caso base, ocorre `StackOverflowError`. Sem redução do problema, a recursão nunca chega ao caso base.

**Exemplo mínimo: fatorial**

```java
static int fatorial(int n) {
    if (n <= 1) return 1;          // caso base
    return n * fatorial(n - 1);    // caso recursivo
}
```

`fatorial(4)` expande assim:

```
fatorial(4) = 4 * fatorial(3)
                  = 3 * fatorial(2)
                        = 2 * fatorial(1)
                              = 1          ← caso base
```

Depois ocorre a **volta**: 2·1 = 2, depois 3·2 = 6, depois 4·6 = 24.

---

## 2. A pilha de chamadas (a ligação com o seu arquivo)

Cada chamada de método cria um **registro de ativação** (*stack frame*) com parâmetros, variáveis locais e ponto de retorno. A JVM empilha esses registros na **pilha de chamadas** (*call stack*).

```
        ┌──────────────┐
        │ fatorial(1)  │ ← topo (executa primeiro)
        ├──────────────┤
        │ fatorial(2)  │
        ├──────────────┤
        │ fatorial(3)  │
        ├──────────────┤
        │ fatorial(4)  │
        ├──────────────┤
        │ main         │
        └──────────────┘
```

**Recursão é, na prática, o uso implícito de uma pilha.** É exatamente por isso que o arquivo está na pasta `pilhas`: a versão iterativa troca a pilha invisível da JVM por pilhas explícitas (`Stack<Integer>` dentro da classe `Torre`).

---

## 3. A Torre de Hanói de forma recursiva

Regras: mover `n` discos do pino de origem para o de destino, um por vez, nunca colocando um disco maior sobre um menor.

**A ideia recursiva:** para mover `n` discos de A para C usando B como auxiliar:

1. Mover os `n-1` discos de cima de **A para B** (usando C como auxiliar).
2. Mover o maior disco de **A para C**.
3. Mover os `n-1` discos de **B para C** (usando A como auxiliar).

Os passos 1 e 3 são o **mesmo problema**, só que com um disco a menos. É isso que torna a recursão natural aqui.

```java
public class TorreDeHanoiRecursivo {

    static int movimentos = 0;

    static void hanoi(int n, char origem, char destino, char auxiliar) {
        if (n == 1) {                                   // caso base
            movimentos++;
            System.out.printf("Move disco 1 de %c para %c%n", origem, destino);
            return;
        }
        hanoi(n - 1, origem, auxiliar, destino);        // passo 1
        movimentos++;
        System.out.printf("Move disco %d de %c para %c%n", n, origem, destino); // passo 2
        hanoi(n - 1, auxiliar, destino, origem);        // passo 3
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(n, 'A', 'C', 'B');
        System.out.println("Total de movimentos: " + movimentos);
    }
}
```

Note que **os papéis dos pinos mudam** a cada chamada: o que era "destino" vira "auxiliar" e vice-versa. Essa é a parte que mais confunde iniciantes.

---

## 4. Rastreio completo para n = 3

```
hanoi(3, A, C, B)
├─ hanoi(2, A, B, C)
│  ├─ hanoi(1, A, C, B)  → Move disco 1 de A para C
│  ├─ Move disco 2 de A para B
│  └─ hanoi(1, C, B, A)  → Move disco 1 de C para B
├─ Move disco 3 de A para C
└─ hanoi(2, B, C, A)
   ├─ hanoi(1, B, A, C)  → Move disco 1 de B para A
   ├─ Move disco 2 de B para C
   └─ hanoi(1, A, C, B)  → Move disco 1 de A para C
```

Saída: 7 movimentos, que é **2³ − 1**.

---

## 5. Quantos movimentos? A relação de recorrência

Seja T(n) o número de movimentos:

- T(1) = 1
- T(n) = 2·T(n−1) + 1

Resolvendo: **T(n) = 2ⁿ − 1**.

É exatamente o que o seu arquivo iterativo calcula na linha:

```java
int totalMovimentos = (1 << n) - 1;   // 1 << n equivale a 2^n
```

Ou seja, o código iterativo **usa o resultado matemático da recorrência** para saber quantas iterações fazer. A complexidade é O(2ⁿ) em tempo, e a recursão usa O(n) de espaço na pilha de chamadas (profundidade máxima n).

---

## 6. Como o código iterativo substitui a recursão

Cada peça do seu arquivo tem um equivalente conceitual:

| Versão recursiva | Versão iterativa (seu arquivo) |
|---|---|
| Pilha de chamadas da JVM | Três `Stack<Integer>` explícitas (classe `Torre`) |
| Troca de papéis dos pinos a cada chamada | Padrão fixo `i % 3` em um único `for` |
| Caso base `n == 1` | Fim do laço após `2^n - 1` movimentos |
| Decisão "qual disco mover" vem da estrutura da recursão | Método `moverDiscoEntre` decide pela comparação dos topos |

**O algoritmo iterativo clássico** baseia-se em dois fatos:

- O disco 1 se move a cada dois movimentos, sempre no mesmo sentido cíclico.
- No movimento restante, só existe **um** movimento legal entre os dois outros pinos.

No seu código, isso aparece em:

```java
if (n % 2 == 0) { destino = B; auxiliar = C; }  // paridade de n define o sentido
...
if (i % 3 == 1)      moverDiscoEntre(origem, destino);
else if (i % 3 == 2) moverDiscoEntre(origem, auxiliar);
else                 moverDiscoEntre(auxiliar, destino);
```

E o método `moverDiscoEntre` resolve a legalidade usando `topSafe()` e `popSafe()`, que devolvem `Integer.MAX_VALUE` para torre vazia. Assim, uma torre vazia sempre "aceita" qualquer disco, porque qualquer valor é menor que o máximo. Os 4 casos do método cobrem: origem vazia, destino vazia, origem maior que destino e origem menor que destino, e em todos o disco **menor** é movido para cima do maior.

---

## 7. Recursão × Iteração: quando usar cada uma

| Critério | Recursiva | Iterativa |
|---|---|---|
| Clareza | Muito alta, espelha a definição do problema | Menor, exige entender o padrão oculto |
| Memória | Usa a pilha de chamadas, risco de `StackOverflowError` | Memória controlada |
| Desempenho | Overhead de chamadas | Geralmente um pouco mais rápido |
| Melhor para | Árvores, divisão e conquista, backtracking, Hanói | Laços simples, grandes profundidades |

**Regra prática:** use recursão quando o problema se divide naturalmente em subproblemas iguais e menores; use iteração quando a profundidade pode ser grande ou o padrão é simples.

---

## 8. Erros comuns de alunos

1. **Esquecer o caso base**, o que gera recursão infinita.
2. **Não reduzir o problema** (chamar com o mesmo `n`).
3. **Trocar a ordem dos parâmetros** em Hanói (origem, destino, auxiliar).
4. **Tentar acompanhar tudo de cabeça**: é melhor desenhar a árvore de chamadas, como na seção 4.
5. **Achar que cada chamada "vê" as variáveis das outras**: cada chamada tem seus próprios parâmetros.

---

## 9. Exercícios sugeridos

1. Escreva `somaAte(int n)` recursivamente.
2. Escreva Fibonacci recursivo e desenhe a árvore de chamadas para `fib(5)`; depois discuta por que é ineficiente.
3. Rode as duas versões de Hanói com `n = 4` e compare as saídas: os movimentos são **idênticos**?
4. Modifique a versão recursiva para usar uma `Stack<Integer>` por pino, como na iterativa, e imprimir o estado dos pinos após cada movimento.

---

Se quiser, posso transformar isto em um arquivo `.md` didático no mesmo formato do `pilhas.md` (com sumário numerado, para iniciantes), para você colocar na pasta `pilhas` ao lado dos dois arquivos de Hanói.
