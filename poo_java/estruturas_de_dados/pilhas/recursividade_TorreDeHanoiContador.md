# Recursividade em `TorreDeHanoiContador.java`

## 1. A ideia central

Para mover **n discos** de A para C, usando B como apoio, o problema se decompõe assim:

1. Mover os **n-1 discos de cima** de A para B (usando C como apoio).
2. Mover o **maior disco (n)** de A para C. Ele agora está livre.
3. Mover os **n-1 discos** de B para C (usando A como apoio).

Os passos 1 e 3 são o mesmo problema com um disco a menos. Por isso o método chama a si mesmo.

## 2. Os dois elementos da recursividade

**Caso base**, que impede a recursão infinita:

```java
if (n == 1) {
    contador++;
    System.out.println("Mover disco 1 de " + origem + " para " + destino);
    return;
}
```

Com um único disco não há o que decompor, então basta movê-lo e encerrar com `return`.

**Caso recursivo**, que reduz o problema:

```java
moverDiscos(n - 1, origem, auxiliar, destino);   // passo 1
contador++; System.out.println(...);             // passo 2
moverDiscos(n - 1, auxiliar, destino, origem);   // passo 3
```

Cada chamada recebe `n - 1`, então o problema sempre diminui até chegar ao caso base.

## 3. Os papéis das hastes mudam a cada chamada

Os parâmetros `origem`, `destino` e `auxiliar` são **papéis**, não hastes fixas. Veja como são passados:

| Passo | Chamada | O que muda |
|---|---|---|
| 1 | `moverDiscos(n-1, origem, auxiliar, destino)` | O destino vira a **auxiliar**, e a auxiliar vira o **destino** |
| 3 | `moverDiscos(n-1, auxiliar, destino, origem)` | A origem vira a **auxiliar**, e a auxiliar vira a **origem** |

Isso funciona porque, no passo 1, o objetivo temporário é "levar os n-1 discos para B". Para essa subtarefa, B é o destino e C é o apoio.

## 4. Rastreamento (teste de mesa) com n = 3

Cada recuo de nível representa uma chamada que terminou e devolveu o controle à anterior:

```
moverDiscos(3, A, C, B)
├─ moverDiscos(2, A, B, C)
│   ├─ moverDiscos(1, A, C, B) → [1] disco 1: A → C   (caso base)
│   ├─ [2] disco 2: A → B
│   └─ moverDiscos(1, C, B, A) → [3] disco 1: C → B   (caso base)
├─ [4] disco 3: A → C
└─ moverDiscos(2, B, C, A)
    ├─ moverDiscos(1, B, A, C) → [5] disco 1: B → A   (caso base)
    ├─ [6] disco 2: B → C
    └─ moverDiscos(1, A, C, B) → [7] disco 1: A → C   (caso base)
```

Os movimentos aparecem na ordem [1] a [7], e o contador termina em **7**.

**Por que o caso base sempre imprime "disco 1"?** Em qualquer subproblema, os discos movidos são sempre os **menores** da torre. Quando `n == 1`, o disco em questão é necessariamente o menor de todos, o disco 1.

## 5. A pilha de chamadas

Cada chamada de `moverDiscos` cria um **quadro de pilha** (*stack frame*) com seus próprios `n`, `origem`, `destino` e `auxiliar`. Isso se liga ao tema da pasta (`pilhas`): a própria JVM usa uma pilha para controlar a recursão.

Para n = 3, no momento do primeiro movimento a pilha tem este estado (topo à direita):

```
main → moverDiscos(3) → moverDiscos(2) → moverDiscos(1)
```

Quando o caso base executa `return`, o quadro do topo é desempilhado. A execução volta ao quadro de `moverDiscos(2)`, exatamente na linha seguinte à chamada, que é o passo 2. Por isso o movimento do disco maior só acontece **depois** que a chamada do passo 1 termina.

A profundidade máxima da pilha é **n** quadros, com os `n` do main incluídos.

## 6. O papel do contador

```java
static int contador = 0;
```

A variável é `static`, então existe uma única cópia compartilhada por todas as chamadas. Se fosse local, cada quadro teria o seu contador e o total se perderia. Ela é incrementada em dois lugares: no caso base e no passo 2. Todo movimento passa por um desses dois pontos, então a contagem fica correta.

## 7. Quantos movimentos? A recorrência

Seja T(n) o número de movimentos:

- T(1) = 1
- T(n) = 2·T(n-1) + 1 (duas chamadas menores mais o movimento do disco maior)

Expandindo: T(n) = 2ⁿ − 1.

| n | Movimentos |
|---|---|
| 1 | 1 |
| 2 | 3 |
| 3 | 7 |
| **4** (valor do `main`) | **15** |
| 10 | 1.023 |
| 20 | 1.048.575 |

Ao rodar o programa com `n = 4`, a última linha será `Total de movimentos: 15`.

## 8. Complexidade

- **Tempo:** O(2ⁿ). Cada disco a mais **dobra** o trabalho, e é por isso que a Torre de Hanói é o exemplo clássico de crescimento exponencial.
- **Espaço:** O(n), pela profundidade da pilha de chamadas.

## 9. Ponto de atenção no código

Se alguém passar `n = 0` (ou negativo), o caso base `n == 1` nunca é atingido. A recursão chama `n-1`, `n-2`, e assim por diante, até estourar a pilha com `StackOverflowError`. Duas formas de corrigir:

```java
// Opção 1: caso base mais seguro
if (n <= 0) return;

// Opção 2: caso base em n == 0 (versão mais elegante)
static void moverDiscos(int n, char origem, char destino, char auxiliar) {
    if (n == 0) return;                          // nada a mover
    moverDiscos(n - 1, origem, auxiliar, destino);
    contador++;
    System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
    moverDiscos(n - 1, auxiliar, destino, origem);
}
```

Na opção 2 desaparece a duplicação do `contador++` e do `println`, pois o movimento do disco passa a ocorrer em um único lugar.

## 10. Resumo para a aula

1. A recursão resolve um problema grande resolvendo **duas cópias menores** dele.
2. O **caso base** (`n == 1`) garante que o processo termina.
3. As hastes são **papéis** que se trocam a cada chamada.
4. A **pilha de chamadas** guarda o contexto de cada nível e explica a ordem dos movimentos.
5. O custo é **2ⁿ − 1** movimentos, ou seja, exponencial.

Se quiser, posso montar um exercício de teste de mesa para os alunos (por exemplo, n = 4 com a árvore de chamadas incompleta para preencher) ou uma versão iterativa com `Stack` para comparar com a recursiva.
