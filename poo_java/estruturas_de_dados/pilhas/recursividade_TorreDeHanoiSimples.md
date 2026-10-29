# Recursividade em `TorreDeHanoiSimples.java`

## 1. O problema

Há três hastes (A, B, C) e *n* discos de tamanhos diferentes empilhados em A, com o maior embaixo. O objetivo é levar todos para C, com duas regras:

- só se move um disco por vez;
- nunca se coloca um disco maior sobre um menor.

## 2. A ideia recursiva

O programa não tenta descobrir todos os movimentos de uma vez. Ele reduz o problema a versões menores de si mesmo. Para mover *n* discos de A para C:

1. Move os **n-1 discos de cima** de A para B, usando C como apoio. Isso libera o disco maior.
2. Move o **disco maior (n)** de A para C. É um movimento direto.
3. Move os **n-1 discos** de B para C, usando A como apoio.

Os passos 1 e 3 são o mesmo problema com um disco a menos. Isso é a recursividade: a função chama a si mesma com um problema menor até chegar a um caso trivial.

## 3. Anatomia do código

```java
static void moverDiscos(int n, char origem, char destino, char auxiliar)
```

Os parâmetros **mudam de papel** a cada chamada. A haste que era auxiliar numa chamada vira destino ou origem na seguinte. Isso é o que costuma confundir quem está aprendendo.

### Caso base

```java
if (n == 1) {
    System.out.println("Mover disco 1 de " + origem + " para " + destino);
    return;
}
```

Com um único disco, não há nada a decompor: move-se direto. O `return` encerra a chamada e **interrompe a recursão**. Sem o caso base, a função chamaria a si mesma indefinidamente.

### Caso recursivo

```java
moverDiscos(n - 1, origem, auxiliar, destino);                         // Passo 1
System.out.println("Mover disco " + n + " de " + origem + " para " + destino); // Passo 2
moverDiscos(n - 1, auxiliar, destino, origem);                         // Passo 3
```

- **Passo 1:** o destino é a haste *auxiliar*, porque os n-1 discos vão para o lado.
- **Passo 2:** o único `println` do caso recursivo, que move o disco *n*.
- **Passo 3:** a origem agora é a haste *auxiliar*, onde os n-1 discos ficaram, e o destino é o destino final.

Cada chamada reduz o problema (`n - 1`), e isso garante que o caso base seja alcançado.

## 4. Rastreamento para `n = 3` (A → C, auxiliar B)

Cada recuo de linha é uma chamada mais profunda na pilha:

```
moverDiscos(3, A, C, B)
├─ moverDiscos(2, A, B, C)
│   ├─ moverDiscos(1, A, C, B)  → imprime: disco 1 de A para C
│   ├─ imprime: disco 2 de A para B
│   └─ moverDiscos(1, C, B, A)  → imprime: disco 1 de C para B
├─ imprime: disco 3 de A para C
└─ moverDiscos(2, B, C, A)
    ├─ moverDiscos(1, B, A, C)  → imprime: disco 1 de B para A
    ├─ imprime: disco 2 de B para C
    └─ moverDiscos(1, A, C, B)  → imprime: disco 1 de A para C
```

Saída final, na ordem de execução:

| # | Movimento |
|---|-----------|
| 1 | disco 1 de A → C |
| 2 | disco 2 de A → B |
| 3 | disco 1 de C → B |
| 4 | disco 3 de A → C |
| 5 | disco 1 de B → A |
| 6 | disco 2 de B → C |
| 7 | disco 1 de A → C |

Note que o disco 3 só se move (movimento 4) depois que a primeira chamada recursiva termina e devolve o controle. Os discos 1 e 2 já estão em B nesse momento, com a haste A livre e C vazia.

## 5. O que acontece na pilha de chamadas

A JVM empilha um **frame** a cada chamada, com seus próprios `n`, `origem`, `destino` e `auxiliar`. Quando uma chamada termina, o frame sai da pilha e a execução retoma na linha seguinte da chamada anterior. É por isso que, depois do Passo 1 de `n=3`, o programa imprime o movimento do disco 3 e só depois dispara o Passo 3.

Isso conecta bem com a pasta `pilhas` onde o arquivo está: a recursão usa implicitamente a pilha de execução (*call stack*), e uma versão iterativa precisaria de uma pilha explícita para simular o mesmo comportamento.

## 6. Análise de complexidade

- **Número de movimentos:** `T(n) = 2·T(n-1) + 1`, o que resulta em `2ⁿ - 1`. Para n=3 são 7, para n=10 são 1.023 e para n=64 seriam cerca de 1,8 × 10¹⁹.
- **Complexidade de tempo:** O(2ⁿ), exponencial.
- **Profundidade máxima da recursão:** *n*, porque cada chamada reduz n em 1 até chegar a 1. Logo, o uso de memória de pilha é O(n).

## 7. Pontos de atenção

- **`n <= 0` causa estouro de pilha.** O caso base só trata `n == 1`. Com `n = 0`, a chamada gera `n - 1 = -1`, depois `-2`, e assim por diante, até um `StackOverflowError`. Uma versão mais robusta usaria `if (n <= 0) return;` ou `if (n == 0) return;` como caso base. Nesse caso, o `n == 1` poderia até ser removido, e o disco 1 seria tratado pelo caso recursivo.
- **O `println` do caso base sempre diz "disco 1".** Isso está correto, porque na recursão o caso base só é atingido pelo menor disco. Vale deixar isso claro para os alunos.
- **`n = 3` fixo no `main`.** Para fins didáticos, vale mostrar a saída com n = 1, 2 e 3 e pedir que os alunos prevejam a de n = 4 (15 movimentos).
