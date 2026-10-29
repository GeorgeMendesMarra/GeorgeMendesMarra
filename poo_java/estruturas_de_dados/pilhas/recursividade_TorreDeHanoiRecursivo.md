# Explicação detalhada: `TorreDeHanoiRecursivo.java`

## 1. Visão geral

O arquivo implementa a solução **recursiva clássica** da Torre de Hanói em Java, com 57 linhas e uma única classe. Ele mostra como um problema grande pode ser resolvido dividindo-o em versões menores de si mesmo.

**O problema:** há 3 pinos (A, B e C) e *n* discos de tamanhos diferentes empilhados no pino A, com o maior embaixo. O objetivo é levar todos para o pino C, respeitando duas regras:

1. Só se move **um disco por vez**.
2. **Nunca** se coloca um disco maior sobre um menor.

## 2. Estrutura da classe

```java
public class TorreDeHanoiRecursivo {
    private static long contador = 0;
    public static void hanoi(int n, char origem, char destino, char auxiliar) { ... }
    public static void main(String[] args) { ... }
}
```

A classe tem três elementos:

| Elemento | Papel |
|---|---|
| `contador` | Conta os movimentos realizados |
| `hanoi(...)` | Contém a lógica recursiva |
| `main(...)` | Ponto de entrada: lê o parâmetro, chama `hanoi` e imprime o total |

## 3. O contador global

```java
private static long contador = 0;
```

- É `static`, então pertence à classe e pode ser usado dentro do método estático `hanoi`, sem criar objetos.
- É `private`, pois só a própria classe precisa dele (encapsulamento).
- É `long` porque o número de movimentos cresce exponencialmente. Com `int`, o valor estouraria a partir de cerca de 31 discos; com `long`, comporta até 63.

## 4. O método `hanoi`

```java
public static void hanoi(int n, char origem, char destino, char auxiliar)
```

Os parâmetros são:

- `n`: quantidade de discos que ainda precisam ser movidos
- `origem`: pino onde esses discos estão
- `destino`: pino para onde devem ir
- `auxiliar`: pino de apoio, usado temporariamente

**Ponto importante:** os papéis dos pinos são *relativos* a cada chamada. O pino "auxiliar" de uma chamada pode ser o "destino" da seguinte. É isso que faz a recursão funcionar.

### 4.1 Caso base

```java
if (n == 0) return;
```

Sem discos, não há nada a fazer. Esse é o ponto de parada da recursão. Sem ele, o método chamaria a si mesmo indefinidamente até um `StackOverflowError`.

### 4.2 Os três passos do caso recursivo

O raciocínio é: *"Para mover n discos, primeiro tiro os n-1 de cima do caminho, movo o maior, e depois recoloco os n-1 por cima dele."*

**Passo 1: mover n-1 discos da origem para o auxiliar**
```java
hanoi(n - 1, origem, auxiliar, destino);
```
Os discos menores saem do caminho. Note a troca de papéis: o `destino` desta chamada é o `auxiliar` da chamada atual, e o `auxiliar` é o `destino` da chamada atual.

**Passo 2: mover o disco maior (n) da origem para o destino**
```java
System.out.printf("Move disco %d de %c para %c%n", n, origem, destino);
contador++;
```
Este é o único momento em que algo é de fato "movido" (impresso). O disco *n* está livre, porque todos os menores estão no pino auxiliar, e o pino destino está vazio ou só tem discos maiores.

**Passo 3: mover n-1 discos do auxiliar para o destino**
```java
hanoi(n - 1, auxiliar, destino, origem);
```
Os discos menores vão para cima do disco maior. Agora a origem original vira o pino auxiliar.

### 4.3 Formatação da saída

`printf` com `%d` (inteiro), `%c` (caractere) e `%n` (quebra de linha independente de sistema operacional) deixa a saída limpa.

## 5. O método `main`

```java
int n = 3;
if (args.length > 0) {
    try {
        n = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
        System.err.println("Argumento inválido. Usando n = 3.");
    }
}
hanoi(n, 'A', 'C', 'B');
System.out.printf("Total de movimentos: %d%n", contador);
```

- O valor padrão é **3 discos**.
- Se o usuário passar um argumento de linha de comando (`java TorreDeHanoiRecursivo 5`), ele é convertido para `int`.
- Se a conversão falhar (por exemplo, `abc`), o `catch` avisa em `System.err` e mantém *n = 3*.
- A chamada `hanoi(n, 'A', 'C', 'B')` significa: mover *n* discos de **A** para **C**, usando **B** como apoio.

## 6. Execução passo a passo (n = 3)

Chamada inicial: `hanoi(3, A, C, B)`

```
Move disco 1 de A para C
Move disco 2 de A para B
Move disco 1 de C para B
Move disco 3 de A para C
Move disco 1 de B para A
Move disco 2 de B para C
Move disco 1 de A para C
Total de movimentos: 7
```

A árvore de chamadas funciona assim:

```
hanoi(3, A, C, B)
├── hanoi(2, A, B, C)          ← passo 1
│   ├── hanoi(1, A, C, B)
│   │   ├── hanoi(0, ...)  → retorna
│   │   ├── imprime: disco 1 de A para C
│   │   └── hanoi(0, ...)  → retorna
│   ├── imprime: disco 2 de A para B
│   └── hanoi(1, C, B, A)
│       └── imprime: disco 1 de C para B
├── imprime: disco 3 de A para C   ← passo 2
└── hanoi(2, B, C, A)          ← passo 3
    ├── hanoi(1, B, A, C) → imprime: disco 1 de B para A
    ├── imprime: disco 2 de B para C
    └── hanoi(1, A, C, B) → imprime: disco 1 de A para C
```

## 7. Complexidade

Cada chamada gera duas outras com *n-1* discos, mais um movimento:

> T(n) = 2·T(n-1) + 1, com T(0) = 0

Resolvendo, o número mínimo de movimentos é **2ⁿ − 1**.

| Discos (n) | Movimentos |
|---|---|
| 3 | 7 |
| 5 | 31 |
| 10 | 1.023 |
| 20 | 1.048.575 |
| 30 | ~1,07 bilhão |

- **Tempo:** O(2ⁿ), exponencial. Não há como ser mais rápido, pois 2ⁿ − 1 é o mínimo possível.
- **Espaço:** O(n), que é a profundidade máxima da pilha de chamadas.

## 8. Relação com a estrutura de dados Pilha

Este é o ponto que justifica o arquivo estar na pasta `pilhas`:

- **Cada pino se comporta como uma pilha (LIFO):** só se mexe no disco do topo.
- **A própria recursão usa a pilha de chamadas da JVM**, que também é LIFO. Cada `hanoi(...)` empilha um quadro com seus parâmetros, e o desempilhar acontece quando o método retorna.

Um detalhe didático: **esta versão não modela os pinos como pilhas explícitas.** Ela apenas imprime os movimentos. A pilha aparece de forma *implícita* na recursão. Uma versão complementar poderia usar três `Stack<Integer>` ou `Deque<Integer>` para guardar os discos e validar as regras.

## 9. Pontos fortes e possíveis melhorias

**Pontos fortes**
- Código curto, legível e bem comentado (Javadoc nos parâmetros).
- Separa bem o caso base dos passos recursivos.
- Tem tratamento básico de entrada inválida.

**Melhorias possíveis (para fins didáticos)**
1. **Validar `n` negativo:** `n = -1` nunca chega a 0 e causa `StackOverflowError`. Basta trocar por `if (n <= 0) return;` ou rejeitar na `main`.
2. **Limitar `n` grande:** valores como 40 geram bilhões de linhas e travam o terminal. Um aviso ou limite ajuda.
3. **Resetar o `contador`:** por ser estático, se `hanoi` fosse chamado várias vezes, o valor acumularia. Poderia ser zerado antes de cada execução.
4. **Versão com pilhas explícitas:** modelar os pinos com `Stack` para conectar diretamente ao tema da pasta e permitir mostrar o estado após cada movimento.
5. **Versão iterativa:** usar uma pilha própria para simular a recursão e comparar as duas abordagens.
6. **Mostrar o estado dos pinos** a cada passo, o que ajuda muito os alunos a visualizar.
