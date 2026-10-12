# Pilhas em C com o jogo Torre de Hanói

> Material para quem **nunca viu** a estrutura de dados *pilha*.
> Ao final, você terá um jogo da Torre de Hanói funcionando no terminal, construído com pilhas, além de cinco exercícios **resolvidos e comentados**.

---

## 1. O que é uma pilha?

Imagine uma pilha de pratos na cozinha:

- Você **coloca** um prato novo sempre **em cima** da pilha.
- Você **retira** sempre o prato que está **em cima**.
- Não dá para tirar um prato do meio sem mexer nos de cima.

Isso é uma **pilha** (*stack*). Ela segue a regra **LIFO**:

> **L**ast **I**n, **F**irst **O**ut — o último a entrar é o primeiro a sair.

```
        topo
         ↓
       ┌───┐
       │ 3 │   ← último a entrar (primeiro a sair)
       ├───┤
       │ 2 │
       ├───┤
       │ 1 │   ← primeiro a entrar (último a sair)
       └───┘
        base
```

### Onde pilhas aparecem no dia a dia da computação?

- Botão **Desfazer** (Ctrl+Z) em editores de texto.
- Botão **Voltar** do navegador.
- Chamadas de função (a "pilha de execução" do seu programa).
- Verificação de parênteses em expressões.

---

## 2. Operações básicas de uma pilha

| Operação | Nome em inglês | O que faz |
|---|---|---|
| Empilhar | `push` | Coloca um elemento no topo |
| Desempilhar | `pop` | Remove e devolve o elemento do topo |
| Consultar topo | `peek` / `top` | Olha o topo **sem remover** |
| Vazia? | `isEmpty` | Informa se não há elementos |
| Cheia? | `isFull` | Informa se não cabe mais nenhum elemento |

Exemplo passo a passo (pilha começa vazia):

```
push(5)   →  [5]
push(8)   →  [5, 8]
push(2)   →  [5, 8, 2]     ← topo é 2
pop()     →  devolve 2     →  [5, 8]
peek()    →  devolve 8     →  [5, 8]  (nada removido)
```

---

## 3. Como representar uma pilha em C

Vamos usar um **vetor (array)** e uma variável chamada `topo` que guarda a posição do último elemento.

- `topo = -1` significa **pilha vazia**.
- Ao empilhar: `topo` aumenta e o valor é guardado nessa posição.
- Ao desempilhar: lemos `dados[topo]` e `topo` diminui.

```c
// Quantidade máxima de discos (e, portanto, capacidade de cada pilha)
#define MAX_DISCOS 8

// "typedef struct" cria um novo tipo chamado Pilha, que agrupa
// o vetor de dados e o índice do topo em uma única variável.
typedef struct {
    int dados[MAX_DISCOS];  // armazena os elementos da pilha
    int topo;               // índice do elemento do topo (-1 = pilha vazia)
} Pilha;
```

> **Dica:** como `Pilha` é um tipo, podemos criar várias pilhas independentes — e na Torre de Hanói precisaremos de **três**.

### Funções da pilha

```c
// Prepara a pilha para uso: topo = -1 quer dizer "sem nenhum elemento".
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializar(Pilha *p) {
    p->topo = -1;
}

// Devolve 1 (verdadeiro) se não há elementos, 0 (falso) caso contrário.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int vazia(Pilha *p) {
    return p->topo == -1;
}

// Devolve 1 se o vetor está lotado (topo está na última posição possível).
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int cheia(Pilha *p) {
    return p->topo == MAX_DISCOS - 1;
}

// PUSH: coloca "valor" no topo.
// Devolve 1 se conseguiu, 0 se a pilha estava cheia.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(Pilha *p, int valor) {
    if (cheia(p)) return 0;      // não há espaço: recusa
    p->topo++;                   // 1º) sobe o topo uma posição
    p->dados[p->topo] = valor;   // 2º) guarda o valor nessa posição
    return 1;
}

// POP: remove o elemento do topo e o entrega em "*valor".
// Devolve 1 se conseguiu, 0 se a pilha estava vazia.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(Pilha *p, int *valor) {
    if (vazia(p)) return 0;      // não há o que remover
    *valor = p->dados[p->topo];  // 1º) copia o valor do topo para quem chamou
    p->topo--;                   // 2º) desce o topo (o valor antigo fica "esquecido")
    return 1;
}

// PEEK: igual ao pop, mas NÃO remove (o topo permanece onde está).
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int consultarTopo(Pilha *p, int *valor) {
    if (vazia(p)) return 0;
    *valor = p->dados[p->topo];
    return 1;
}
```

### Por que `Pilha *p` (ponteiro)?

Em C, os argumentos são **copiados** ao chamar uma função. Se passássemos a `Pilha` por valor, a função alteraria apenas uma cópia. Com o **ponteiro**, a função altera a pilha original. Usamos `p->campo` para acessar os campos através do ponteiro.

O mesmo vale para `int *valor`: é assim que `desempilhar` "devolve" o elemento removido, já que o `return` está ocupado informando sucesso (1) ou falha (0).

### Função ou método? (para quem vem de Java)

Nos códigos deste material, cada função vem marcada com uma etiqueta no comentário:

| Etiqueta | O que significa | Exemplos |
|---|---|---|
| `[MÉTODO da Pilha]` | Função que **opera sobre a pilha** (recebe a pilha como 1º parâmetro) | `inicializar`, `vazia`, `cheia`, `empilhar`, `desempilhar`, `consultarTopo` |
| `[FUNÇÃO]` | Função comum, que faz um trabalho e não "pertence" a uma estrutura | `mover`, `desenhar`, `iniciar`, `casam`, `balanceada` |
| `[FUNÇÃO RECURSIVA]` | Função que chama a si mesma | `resolver`, `jogadasMinimasRecorrencia` |
| `[FUNÇÃO PRINCIPAL]` | Ponto de entrada do programa | `main` |

> **Atenção:** a linguagem C **não possui métodos**. Método é um conceito de **orientação a objetos**: uma função que vive dentro de uma classe e age sobre o objeto (`this`). Em C só existem **funções**. Chamamos as operações da pilha de "métodos" apenas **por analogia**: elas fazem o papel que os métodos fariam em uma classe `Pilha`.

A diferença aparece na hora de **chamar**:

```
Java (método do objeto):        pilha.empilhar(10);        // a pilha é "this", implícita
C    (função com a pilha):      empilhar(&pilha, 10);      // a pilha é passada explicitamente
```

Por isso o ponteiro `Pilha *p` faz o papel do `this` do Java: é ele que diz **sobre qual pilha** a função deve agir.

---

## 4. O jogo Torre de Hanói

### Um pouco de história

A Torre de Hanói não é um jogo milenar do Oriente, como a lenda faz parecer: foi **inventada em 1883** pelo matemático francês **Édouard Lucas** (1842-1891) e lançada como brinquedo. Na divulgação, Lucas assinou com o pseudônimo **"N. Claus de Siam"**, um anagrama de **"Lucas d'Amiens"** (Amiens era sua cidade natal). O jogo foi batizado de *La Tour d'Hanoï*, e o nome da capital do Vietnã, então parte da Indochina Francesa, dava ao brinquedo um ar exótico que ajudava a vender. Segundo os relatos da época, o brinquedo original vinha com **8 discos**.

| Ano | Fato |
|:---:|---|
| 1883 | Édouard Lucas lança a *Tour d'Hanoï* sob o pseudônimo "N. Claus de Siam" |
| 1884 | A lenda dos 64 discos é divulgada em artigos de revistas científicas francesas e se espalha pelo mundo |
| Século XX | O problema vira um exemplo clássico de **recursão** nos cursos de computação |

#### A lenda dos 64 discos

A história que acompanhava o jogo (existem variações, que falam de um templo em Benares ou em Hanói) conta mais ou menos assim:

> Em um templo, sob a cúpula que marca o centro do mundo, há **três hastes de diamante**. Na criação do mundo, foram colocados em uma delas **64 discos de ouro**, do maior para o menor. Sacerdotes movem os discos, **um por vez**, sem jamais pôr um maior sobre um menor. Quando todos os discos estiverem na terceira haste, **o mundo acabará**.

#### Será que o mundo vai acabar?

Já vimos que o mínimo de jogadas para `n` discos é `2^n - 1`. Para 64 discos:

```
2^64 - 1 = 18.446.744.073.709.551.615 movimentos
```

Mesmo que os sacerdotes fizessem **1 movimento por segundo**, sem parar, levariam cerca de **585 bilhões de anos**, aproximadamente 42 vezes a idade estimada do universo (cerca de 13,8 bilhões de anos). Pode ficar tranquilo.

Isso mostra, de forma bem concreta, o que é **crescimento exponencial**: cada disco a mais **dobra** (e soma 1) o número de movimentos.

#### Comprovando em C

**Arquivo:** `hanoi_lenda.c`

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define SEGUNDOS_POR_ANO 31557600.0   // 365,25 dias * 24 h * 3600 s

// [FUNÇÃO]
// Calcula o mínimo de jogadas para n discos usando a recorrência:
//   minimo(0) = 0
//   minimo(n) = 2 * minimo(n-1) + 1
// "unsigned long long" tem 64 bits e comporta exatamente 2^64 - 1,
// por isso o valor para 64 discos cabe sem estourar.
unsigned long long jogadasMinimas(int n) {
    unsigned long long movimentos = 0;   // com 0 discos não há o que mover
    int i;
    for (i = 1; i <= n; i++) {
        movimentos = 2 * movimentos + 1; // cada disco novo dobra o trabalho e soma 1
    }
    return movimentos;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    int discos[] = {3, 8, 20, 64};   // quantidades de discos a comparar
    int total = sizeof(discos) / sizeof(discos[0]);
    int i;
    unsigned long long movimentos;
    double anos;

    printf("Discos | Movimentos              | Anos a 1 movimento/segundo\n");
    for (i = 0; i < total; i++) {
        movimentos = jogadasMinimas(discos[i]);
        // Converte segundos em anos (dividindo por segundos por ano).
        anos = movimentos / SEGUNDOS_POR_ANO;
        printf("  %2d   | %23llu | %.3e\n", discos[i], movimentos, anos);
    }

    return 0;
}
```

**Saída esperada:**

```
Discos | Movimentos              | Anos a 1 movimento/segundo
   3   |                       7 | 2.218e-07
   8   |                     255 | 8.080e-06
  20   |                 1048575 | 3.323e-02
  64   |    18446744073709551615 | 5.845e+11
```

---

### Regras

Há **3 torres** (hastes) e **N discos** de tamanhos diferentes. No início, todos os discos estão na torre 1, do maior (embaixo) para o menor (em cima).

**Objetivo:** levar todos os discos para a torre 3.

**Regras:**

1. Mova **um disco por vez**.
2. Só pode mover o disco que está no **topo** de uma torre.
3. **Nunca** coloque um disco maior sobre um menor.

```
Início (3 discos):

    1         |         |
   2          |         |
  3           |         |
 ---       ---       ---
Torre 1   Torre 2   Torre 3
```

### Onde está a pilha nisso?

Cada **torre é uma pilha**:

| No jogo | Na pilha |
|---|---|
| Colocar um disco numa torre | `empilhar` |
| Retirar o disco de cima | `desempilhar` |
| Ver o disco de cima para validar a regra 3 | `consultarTopo` |
| Torre sem discos | `vazia` |

A regra "só mexe no disco do topo" **é exatamente o comportamento LIFO**. Por isso a pilha é a estrutura perfeita para esse jogo.

Representaremos os discos por números: **1 = menor, 2 = médio, 3 = maior**, e assim por diante. A regra de ouro vira uma comparação:

> Só pode empilhar o disco `d` sobre a torre se ela estiver vazia **ou** se `d` for **menor** que o disco do topo.

---

## 5. Programa completo

Salve como `hanoi.c`.

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

// Número máximo de discos aceito pelo jogo (e capacidade de cada torre).
#define MAX_DISCOS 8

/* =====================================================================
   PARTE 1 - A ESTRUTURA DE DADOS PILHA
   ===================================================================== */

typedef struct {
    int dados[MAX_DISCOS];  // os discos: cada número é o tamanho do disco
    int topo;               // índice do disco do topo (-1 = torre vazia)
} Pilha;

// Deixa a pilha vazia e pronta para uso.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializar(Pilha *p) {
    p->topo = -1;
}

// 1 se a torre não tem discos.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int vazia(Pilha *p) {
    return p->topo == -1;
}

// 1 se a torre não comporta mais nenhum disco.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int cheia(Pilha *p) {
    return p->topo == MAX_DISCOS - 1;
}

// PUSH: coloca um disco no topo da torre.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(Pilha *p, int valor) {
    if (cheia(p)) return 0;      // sem espaço
    p->topo++;                   // sobe o topo
    p->dados[p->topo] = valor;   // guarda o disco
    return 1;
}

// POP: retira o disco do topo e o entrega em *valor.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(Pilha *p, int *valor) {
    if (vazia(p)) return 0;      // nada para retirar
    *valor = p->dados[p->topo];  // copia o disco do topo
    p->topo--;                   // desce o topo
    return 1;
}

// PEEK: apenas espia o disco do topo, sem retirá-lo.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int consultarTopo(Pilha *p, int *valor) {
    if (vazia(p)) return 0;
    *valor = p->dados[p->topo];
    return 1;
}

/* =====================================================================
   PARTE 2 - A LÓGICA DO JOGO
   ===================================================================== */

// Move o disco do topo de "origem" para "destino", respeitando as regras.
// Retorna: 0 = movimento feito
//          1 = a torre de origem está vazia
//          2 = tentou colocar disco maior sobre menor (proibido)
// [FUNÇÃO]
int mover(Pilha *origem, Pilha *destino) {
    int disco;         // disco que queremos mover
    int topoDestino;   // disco que está no topo da torre de destino

    // Passo 1: espiamos (sem retirar) o disco de cima da origem.
    // Se não existe disco, não há o que mover.
    if (!consultarTopo(origem, &disco)) {
        return 1;
    }

    // Passo 2: se o destino tem algum disco, comparamos os tamanhos.
    // consultarTopo devolve 0 quando o destino está vazio - nesse caso
    // qualquer disco pode entrar e esta condição é falsa.
    if (consultarTopo(destino, &topoDestino) && disco > topoDestino) {
        return 2;      // disco maior sobre menor: regra violada
    }

    // Passo 3: movimento válido = um POP na origem + um PUSH no destino.
    desempilhar(origem, &disco);
    empilhar(destino, disco);
    return 0;
}

// Desenha as três torres no terminal, de cima para baixo.
// [FUNÇÃO]
void desenhar(Pilha torres[3], int n) {
    int nivel, i;

    printf("\n");
    // "nivel" é a altura: começa no mais alto (n-1) e desce até 0 (base).
    for (nivel = n - 1; nivel >= 0; nivel--) {
        for (i = 0; i < 3; i++) {
            // Se a torre i tem disco nessa altura, imprime o número dele;
            // senão imprime só a haste vazia "|".
            if (nivel <= torres[i].topo)
                printf("    %2d    ", torres[i].dados[nivel]);
            else
                printf("     |    ");
        }
        printf("\n");
    }
    printf("  -------   -------   -------\n");
    printf("  Torre 1   Torre 2   Torre 3\n\n");
}

// Deixa o jogo na posição inicial: todos os discos na torre 1.
// [FUNÇÃO]
void iniciar(Pilha torres[3], int n) {
    int i;
    for (i = 0; i < 3; i++) inicializar(&torres[i]);   // 3 torres vazias
    // Empilhamos do maior (n) para o menor (1): o maior fica na base.
    for (i = n; i >= 1; i--) empilhar(&torres[0], i);
}

// Solução automática, recursiva.
// Para mover n discos de "origem" para "destino" usando "auxiliar":
//   1) move n-1 discos de origem para auxiliar;
//   2) move o maior disco de origem para destino;
//   3) move os n-1 discos de auxiliar para destino.
// [FUNÇÃO RECURSIVA] chama a si mesma
void resolver(Pilha torres[3], int n, int origem, int destino, int auxiliar) {
    if (n == 0) return;   // caso base: não há disco para mover
    resolver(torres, n - 1, origem, auxiliar, destino);       // passo 1
    mover(&torres[origem], &torres[destino]);                 // passo 2
    printf("Mover disco %d: Torre %d -> Torre %d\n", n, origem + 1, destino + 1);
    resolver(torres, n - 1, auxiliar, destino, origem);       // passo 3
}

/* =====================================================================
   PARTE 3 - PROGRAMA PRINCIPAL
   ===================================================================== */

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Pilha torres[3];     // vetor com 3 pilhas: torres[0], torres[1], torres[2]
    int n;               // quantidade de discos
    int origem, destino; // torres escolhidas pelo jogador (1 a 3)
    int resultado;       // retorno da função mover
    int jogadas = 0;     // contador de jogadas válidas
    int opcao;           // 1 = jogar, 2 = solução automática

    printf("=== TORRE DE HANOI ===\n");
    printf("Quantos discos (1 a %d)? ", MAX_DISCOS);
    // scanf devolve quantos valores leu; se não leu 1, a entrada é inválida.
    if (scanf("%d", &n) != 1 || n < 1 || n > MAX_DISCOS) {
        printf("Valor invalido.\n");
        return 1;
    }

    iniciar(torres, n);   // coloca todos os discos na torre 1

    printf("\n1 - Jogar\n2 - Ver solucao automatica\nEscolha: ");
    if (scanf("%d", &opcao) != 1) return 1;

    // ---- Modo automático ----
    if (opcao == 2) {
        desenhar(torres, n);
        resolver(torres, n, 0, 2, 1);   // da torre 1 (0) para a 3 (2), apoio na 2 (1)
        desenhar(torres, n);
        return 0;
    }

    // ---- Modo jogo ----
    // Condição de vitória: a torre 3 (índice 2) tem n discos, ou seja,
    // seu topo está no índice n-1. Repetimos até isso acontecer.
    while (torres[2].topo != n - 1) {
        desenhar(torres, n);
        printf("Jogada %d - origem destino (0 0 para sair): ", jogadas + 1);
        if (scanf("%d %d", &origem, &destino) != 2) {
            printf("Entrada invalida.\n");
            return 1;
        }
        if (origem == 0 && destino == 0) {   // jogador desistiu
            printf("Ate a proxima!\n");
            return 0;
        }
        // Valida os números digitados pelo jogador.
        if (origem < 1 || origem > 3 || destino < 1 || destino > 3
            || origem == destino) {
            printf(">> Escolha torres diferentes entre 1 e 3.\n");
            continue;   // volta ao início do while
        }

        // O jogador digita 1..3, mas o vetor usa índices 0..2: por isso "- 1".
        resultado = mover(&torres[origem - 1], &torres[destino - 1]);
        if (resultado == 1) {
            printf(">> A torre %d esta vazia!\n", origem);
        } else if (resultado == 2) {
            printf(">> Nao pode colocar disco maior sobre menor!\n");
        } else {
            jogadas++;   // só conta quando o movimento foi realmente feito
        }
    }

    // Saiu do while: o jogador venceu.
    desenhar(torres, n);
    printf("Parabens! Voce venceu em %d jogadas.\n", jogadas);
    // (1 << n) é o mesmo que 2^n; o mínimo de jogadas é 2^n - 1.
    printf("O minimo possivel era %d jogadas.\n", (1 << n) - 1);
    return 0;
}
```

### Compilar e executar

```bash
gcc hanoi.c -o hanoi
./hanoi
```

---

## 6. Entendendo o código por partes

### 6.1 Montando o jogo

```c
for (i = n; i >= 1; i--) empilhar(&torres[0], i);
```

Com `n = 3`, empilhamos 3, depois 2, depois 1. Resultado: o disco **1** fica no topo e o **3** na base. O primeiro a entrar (3) é o último a poder sair.

### 6.2 Validando uma jogada

```c
if (consultarTopo(destino, &topoDestino) && disco > topoDestino) {
    return 2;
}
```

`consultarTopo` devolve 0 se a torre de destino está vazia (aí qualquer disco pode entrar). Se há disco, comparamos: disco maior sobre menor é proibido.

### 6.3 Fazendo o movimento

```c
desempilhar(origem, &disco);   // tira do topo da origem
empilhar(destino, disco);      // coloca no topo do destino
```

**Mover um disco = um `pop` seguido de um `push`.**

### 6.4 Condição de vitória

```c
while (torres[2].topo != n - 1)
```

Com `n` discos na torre 3, o `topo` vale `n - 1`. Quando isso acontece, o jogo termina.

---

## 7. Bônus: a solução recursiva

A ideia para mover `n` discos da torre A para a torre C (usando B como apoio):

1. Mova os `n-1` discos de cima de **A para B**.
2. Mova o maior disco de **A para C**.
3. Mova os `n-1` discos de **B para C**.

```c
// [FUNÇÃO RECURSIVA] chama a si mesma
void resolver(Pilha torres[3], int n, int origem, int destino, int auxiliar) {
    if (n == 0) return;                                  // caso base: nada a mover
    resolver(torres, n - 1, origem, auxiliar, destino);  // 1) n-1 discos: A -> B
    mover(&torres[origem], &torres[destino]);            // 2) maior disco: A -> C
    resolver(torres, n - 1, auxiliar, destino, origem);  // 3) n-1 discos: B -> C
}
```

> **Curiosidade:** a recursão também usa uma pilha — a **pilha de chamadas** do sistema! Cada chamada de `resolver` é empilhada e só sai quando termina.

O número mínimo de jogadas com `n` discos é:

```
2^n - 1
```

| Discos | Jogadas mínimas |
|:---:|:---:|
| 1 | 1 |
| 2 | 3 |
| 3 | 7 |
| 4 | 15 |
| 5 | 31 |
| 8 | 255 |
| 64 | 18.446.744.073.709.551.615 |

---

## 8. Erros comuns de iniciantes

| Erro | Por que acontece | Como evitar |
|---|---|---|
| Esquecer `topo = -1` | Variável com lixo de memória | Sempre chamar `inicializar` |
| `desempilhar` em pilha vazia | Lê posição `-1` do vetor | Testar `vazia` antes (as funções já fazem isso) |
| `empilhar` em pilha cheia | Escreve fora do vetor | Testar `cheia` antes |
| Passar `Pilha` sem ponteiro | Altera só uma cópia | Usar `Pilha *p` |
| Confundir `topo` com quantidade | `topo` é um **índice** | Quantidade de elementos = `topo + 1` |

---

## 9. Exercícios resolvidos

Cada exercício abaixo é um programa **completo**, já comentado. Salve em um arquivo `.c`, compile com `gcc` e execute.

> **Sugestão didática:** tente resolver sozinho antes de olhar a resposta.

---

### Exercício 1 - Teste a pilha sozinha

**Enunciado:** crie um `main` que empilha 10, 20, 30, desempilha duas vezes e imprime o que sobrou.

**Arquivo:** `exercicio1_pilha.c`

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define MAX 10   // capacidade da pilha neste exercício

typedef struct {
    int dados[MAX];
    int topo;
} Pilha;

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializar(Pilha *p) { p->topo = -1; }
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int  vazia(Pilha *p)       { return p->topo == -1; }
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int  cheia(Pilha *p)       { return p->topo == MAX - 1; }

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(Pilha *p, int valor) {
    if (cheia(p)) return 0;
    p->dados[++p->topo] = valor;   // ++topo primeiro, depois guarda o valor
    return 1;
}

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(Pilha *p, int *valor) {
    if (vazia(p)) return 0;
    *valor = p->dados[p->topo--];  // lê o valor e só depois decrementa o topo
    return 1;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Pilha p;
    int valor, i;

    inicializar(&p);   // sempre inicialize antes de usar!

    // Empilha 10, 20 e 30 (o 30 fica no topo).
    empilhar(&p, 10);
    empilhar(&p, 20);
    empilhar(&p, 30);
    printf("Empilhados: 10, 20, 30\n");

    // Desempilha duas vezes: devem sair 30 e depois 20 (ordem LIFO).
    for (i = 0; i < 2; i++) {
        if (desempilhar(&p, &valor)) {
            printf("Desempilhou: %d\n", valor);
        }
    }

    // Mostra o que sobrou, do topo até a base, sem remover nada.
    printf("Sobrou na pilha (topo -> base):");
    for (i = p.topo; i >= 0; i--) {
        printf(" %d", p.dados[i]);
    }
    printf("\n");

    return 0;
}
```

**Saída esperada:**

```
Empilhados: 10, 20, 30
Desempilhou: 30
Desempilhou: 20
Sobrou na pilha (topo -> base): 10
```

---

### Exercício 2 - Contador de jogadas mínimas

**Enunciado:** calcule e mostre `2^n - 1` (o mínimo de jogadas) antes de o jogador começar.

**Arquivo:** `exercicio2_minimo.c`

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

// Versão 1: usa deslocamento de bits.
// (1L << n) é o mesmo que 2 elevado a n. "L" garante o tipo long.
// [FUNÇÃO]
long jogadasMinimasBits(int n) {
    return (1L << n) - 1;
}

// Versão 2: usa a recorrência da própria Torre de Hanói.
//   minimo(1) = 1
//   minimo(n) = 2 * minimo(n-1) + 1
// (move n-1 discos, move o maior, move n-1 discos de novo)
// [FUNÇÃO RECURSIVA] chama a si mesma
long jogadasMinimasRecorrencia(int n) {
    if (n == 1) return 1;                               // caso base
    return 2 * jogadasMinimasRecorrencia(n - 1) + 1;    // caso recursivo
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    int n;

    printf("Discos | Bits | Recorrencia\n");
    for (n = 1; n <= 8; n++) {
        printf("  %2d   | %4ld | %6ld\n",
               n, jogadasMinimasBits(n), jogadasMinimasRecorrencia(n));
    }

    return 0;
}
```

**Para usar no jogo:** copie a função `jogadasMinimasBits` para o `hanoi.c` e, logo após ler `n`, acrescente:

```c
printf("Minimo de jogadas possivel: %ld\n", jogadasMinimasBits(n));
```

---

### Exercício 3 - Desfazer jogada (segunda pilha como histórico)

**Enunciado:** use **outra pilha** para guardar o histórico de movimentos e implemente a opção "desfazer".

**Ideia:** cada jogada válida é empilhada no histórico, codificada em **um único número** `origem*10 + destino` (por exemplo, torre 0 para torre 2 vira `2`; torre 1 para torre 2 vira `12`). Para desfazer, fazemos `pop` no histórico, decodificamos e movemos o disco **de volta**. Como o histórico é uma pilha, a última jogada é a primeira a ser desfeita (LIFO).

**Arquivo:** `hanoi_desfazer.c`

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define MAX_DISCOS 8
// O histórico precisa guardar até 2^8 - 1 = 255 jogadas, então a
// capacidade das pilhas agora é maior que o número de discos.
#define MAX_ELEM 300

typedef struct {
    int dados[MAX_ELEM];
    int topo;
} Pilha;

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializar(Pilha *p) { p->topo = -1; }
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int  vazia(Pilha *p)       { return p->topo == -1; }
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int  cheia(Pilha *p)       { return p->topo == MAX_ELEM - 1; }

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(Pilha *p, int valor) {
    if (cheia(p)) return 0;
    p->dados[++p->topo] = valor;
    return 1;
}

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(Pilha *p, int *valor) {
    if (vazia(p)) return 0;
    *valor = p->dados[p->topo--];
    return 1;
}

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int consultarTopo(Pilha *p, int *valor) {
    if (vazia(p)) return 0;
    *valor = p->dados[p->topo];
    return 1;
}

// 0 = ok, 1 = origem vazia, 2 = disco maior sobre menor
// [FUNÇÃO]
int mover(Pilha *origem, Pilha *destino) {
    int disco, topoDestino;
    if (!consultarTopo(origem, &disco)) return 1;
    if (consultarTopo(destino, &topoDestino) && disco > topoDestino) return 2;
    desempilhar(origem, &disco);
    empilhar(destino, disco);
    return 0;
}

// [FUNÇÃO]
void desenhar(Pilha torres[3], int n) {
    int nivel, i;
    printf("\n");
    for (nivel = n - 1; nivel >= 0; nivel--) {
        for (i = 0; i < 3; i++) {
            if (nivel <= torres[i].topo)
                printf("    %2d    ", torres[i].dados[nivel]);
            else
                printf("     |    ");
        }
        printf("\n");
    }
    printf("  -------   -------   -------\n");
    printf("  Torre 1   Torre 2   Torre 3\n\n");
}

// [FUNÇÃO]
void iniciar(Pilha torres[3], int n) {
    int i;
    for (i = 0; i < 3; i++) inicializar(&torres[i]);
    for (i = n; i >= 1; i--) empilhar(&torres[0], i);
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Pilha torres[3];     // as três torres
    Pilha historico;     // NOVA pilha: guarda as jogadas já feitas
    char linha[64];      // texto digitado pelo jogador
    int n, origem, destino, resultado;
    int jogadas = 0;
    int jogada;          // jogada codificada que sai do histórico

    printf("=== TORRE DE HANOI (com desfazer) ===\n");
    printf("Quantos discos (1 a %d)? ", MAX_DISCOS);
    // fgets lê a linha inteira; sscanf extrai o número dela.
    // (Usamos fgets em tudo para não misturar com scanf.)
    if (fgets(linha, sizeof linha, stdin) == NULL
        || sscanf(linha, "%d", &n) != 1 || n < 1 || n > MAX_DISCOS) {
        printf("Valor invalido.\n");
        return 1;
    }

    iniciar(torres, n);
    inicializar(&historico);   // histórico começa vazio
    printf("Minimo de jogadas possivel: %d\n", (1 << n) - 1);

    while (torres[2].topo != n - 1) {
        desenhar(torres, n);
        printf("Jogada %d - 'origem destino', 'd' = desfazer, 's' = sair: ",
               jogadas + 1);
        if (fgets(linha, sizeof linha, stdin) == NULL) return 1;   // fim da entrada

        // ---- Comando: sair ----
        if (linha[0] == 's' || linha[0] == 'S') {
            printf("Ate a proxima!\n");
            return 0;
        }

        // ---- Comando: desfazer ----
        if (linha[0] == 'd' || linha[0] == 'D') {
            // POP no histórico: pega a última jogada feita.
            if (desempilhar(&historico, &jogada)) {
                origem  = jogada / 10;   // decodifica: dezena = origem (0 a 2)
                destino = jogada % 10;   // decodifica: unidade = destino (0 a 2)
                // Desfazer = mover o disco de volta, ou seja, com os papéis
                // trocados: o antigo destino vira origem e vice-versa.
                mover(&torres[destino], &torres[origem]);
                jogadas--;
                printf(">> Jogada desfeita: Torre %d <- Torre %d\n",
                       origem + 1, destino + 1);
            } else {
                printf(">> Nada para desfazer!\n");
            }
            continue;
        }

        // ---- Comando: jogada normal "origem destino" ----
        if (sscanf(linha, "%d %d", &origem, &destino) != 2) {
            printf(">> Entrada invalida.\n");
            continue;
        }
        if (origem < 1 || origem > 3 || destino < 1 || destino > 3
            || origem == destino) {
            printf(">> Escolha torres diferentes entre 1 e 3.\n");
            continue;
        }

        resultado = mover(&torres[origem - 1], &torres[destino - 1]);
        if (resultado == 1) {
            printf(">> A torre %d esta vazia!\n", origem);
        } else if (resultado == 2) {
            printf(">> Nao pode colocar disco maior sobre menor!\n");
        } else {
            // Jogada válida: PUSH no histórico, codificando origem e destino
            // (já em índices 0..2) em um único inteiro.
            empilhar(&historico, (origem - 1) * 10 + (destino - 1));
            jogadas++;
        }
    }

    desenhar(torres, n);
    printf("Parabens! Voce venceu em %d jogadas.\n", jogadas);
    return 0;
}
```

---

### Exercício 4 - Pilha dinâmica (lista encadeada com `malloc`)

**Enunciado:** reescreva a pilha com lista encadeada e `malloc`, sem limite fixo `MAX_DISCOS`.

**Ideia:** em vez de um vetor, cada elemento é um **nó** alocado na memória com `malloc`. Cada nó guarda o valor e um ponteiro para o nó que está **abaixo** dele. A pilha só precisa conhecer o **topo**.

```
topo → [ 30 | • ] → [ 20 | • ] → [ 10 | NULL ]
```

**Arquivo:** `exercicio4_pilha_dinamica.c`

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>   // malloc e free

// Um nó da lista: guarda um valor e aponta para o nó de baixo.
typedef struct No {
    int valor;
    struct No *prox;   // nó abaixo deste (NULL se for o último)
} No;

// A pilha conhece apenas o nó do topo e quantos elementos tem.
typedef struct {
    No *topo;      // NULL = pilha vazia
    int tamanho;   // quantidade de elementos
} PilhaDin;

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializar(PilhaDin *p) {
    p->topo = NULL;
    p->tamanho = 0;
}

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int vazia(PilhaDin *p) {
    return p->topo == NULL;
}

// Não existe "cheia": só falha se o computador ficar sem memória.

// PUSH: cria um nó novo e o coloca no topo.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(PilhaDin *p, int valor) {
    No *novo = (No *) malloc(sizeof(No));   // reserva memória para o nó
    if (novo == NULL) return 0;             // malloc falhou: sem memória
    novo->valor = valor;
    novo->prox = p->topo;   // o novo nó aponta para o antigo topo
    p->topo = novo;         // o novo nó passa a ser o topo
    p->tamanho++;
    return 1;
}

// POP: remove o nó do topo, entrega o valor e LIBERA a memória do nó.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(PilhaDin *p, int *valor) {
    No *removido;
    if (vazia(p)) return 0;
    removido = p->topo;          // guarda o nó que vai sair
    *valor = removido->valor;    // entrega o valor
    p->topo = removido->prox;    // o topo passa a ser o nó de baixo
    free(removido);              // devolve a memória (evita vazamento!)
    p->tamanho--;
    return 1;
}

// PEEK: espia o topo sem remover.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int consultarTopo(PilhaDin *p, int *valor) {
    if (vazia(p)) return 0;
    *valor = p->topo->valor;
    return 1;
}

// Libera todos os nós restantes. Chame antes de encerrar o programa.
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void liberar(PilhaDin *p) {
    int descartado;
    while (desempilhar(p, &descartado)) {
        // o próprio desempilhar já faz o free de cada nó
    }
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    PilhaDin p;
    int valor, i;

    inicializar(&p);

    // Empilha 1000 valores: com o vetor de 8 posições isso seria impossível!
    for (i = 1; i <= 1000; i++) {
        empilhar(&p, i);
    }
    printf("Tamanho apos empilhar 1000 valores: %d\n", p.tamanho);

    consultarTopo(&p, &valor);
    printf("Topo atual: %d\n", valor);

    // Desempilha três valores: saem 1000, 999 e 998.
    for (i = 0; i < 3; i++) {
        desempilhar(&p, &valor);
        printf("Desempilhou: %d\n", valor);
    }
    printf("Tamanho agora: %d\n", p.tamanho);

    liberar(&p);   // devolve toda a memória restante
    printf("Tamanho apos liberar: %d\n", p.tamanho);

    return 0;
}
```

**Para usar na Torre de Hanói:** troque `Pilha` por `PilhaDin` e a condição de vitória `torres[2].topo != n - 1` por `torres[2].tamanho != n`, pois agora `topo` é um ponteiro, não um índice. A função `desenhar` também precisaria percorrer os nós em vez de usar `dados[nivel]`.

---

### Exercício 5 - Parênteses balanceados

**Enunciado:** use a pilha para verificar se `((a+b)*c)` está bem formado.

**Ideia:** percorra a expressão. Cada símbolo de **abertura** (`(`, `[`, `{`) é **empilhado**. Cada símbolo de **fechamento** deve combinar com o **topo** da pilha: se combina, faz `pop`; se a pilha está vazia ou o par não bate, a expressão está errada. No fim, a pilha precisa estar **vazia**.

```
Expressão: ( ( a + b ) * c )

  (   → push        pilha: (
  (   → push        pilha: ( (
  )   → pop, casa   pilha: (
  )   → pop, casa   pilha: (vazia)   → balanceada!
```

**Arquivo:** `exercicio5_parenteses.c`

```c
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Pilha (recebe a pilha como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define MAX 100   // tamanho máximo de aninhamento

// Esta pilha guarda CARACTERES (char) em vez de inteiros.
typedef struct {
    char dados[MAX];
    int topo;
} PilhaChar;

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializar(PilhaChar *p) { p->topo = -1; }
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int  vazia(PilhaChar *p)       { return p->topo == -1; }
// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int  cheia(PilhaChar *p)       { return p->topo == MAX - 1; }

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(PilhaChar *p, char c) {
    if (cheia(p)) return 0;
    p->dados[++p->topo] = c;
    return 1;
}

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(PilhaChar *p, char *c) {
    if (vazia(p)) return 0;
    *c = p->dados[p->topo--];
    return 1;
}

// Verifica se "fecha" é o par correto de "abre".
// [FUNÇÃO]
int casam(char abre, char fecha) {
    return (abre == '(' && fecha == ')')
        || (abre == '[' && fecha == ']')
        || (abre == '{' && fecha == '}');
}

// Devolve 1 se a expressão está balanceada, 0 caso contrário.
// [FUNÇÃO]
int balanceada(const char *expr) {
    PilhaChar p;
    char c, topo;
    int i;

    inicializar(&p);

    for (i = 0; expr[i] != '\0'; i++) {   // percorre até o fim da string
        c = expr[i];

        if (c == '(' || c == '[' || c == '{') {
            // Abertura: guarda na pilha para conferir depois.
            if (!empilhar(&p, c)) return 0;   // aninhamento além do limite
        }
        else if (c == ')' || c == ']' || c == '}') {
            // Fechamento: precisa existir uma abertura esperando...
            if (!desempilhar(&p, &topo)) return 0;   // pilha vazia: sobrou ")"
            // ...e ela precisa ser do mesmo tipo.
            if (!casam(topo, c)) return 0;           // ex.: "(]"
        }
        // Qualquer outro caractere (letras, números, operadores) é ignorado.
    }

    // Se sobrou alguma abertura sem fechar, a pilha não está vazia.
    return vazia(&p);
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    // Expressões de teste: algumas corretas, outras com erro.
    const char *testes[] = {
        "((a+b)*c)",      // correta
        "[(a+b)*{c-d}]",  // correta, com tipos misturados
        "((a+b)*c",       // erro: falta fechar um "("
        "(a+b))*c",       // erro: sobrou um ")"
        "(a+b]",          // erro: "(" fechado com "]"
        ""                // vazia: considerada balanceada
    };
    int total = sizeof(testes) / sizeof(testes[0]);   // quantidade de testes
    int i;

    for (i = 0; i < total; i++) {
        printf("\"%s\" -> %s\n", testes[i],
               balanceada(testes[i]) ? "balanceada" : "ERRADA");
    }

    return 0;
}
```

**Saída esperada:**

```
"((a+b)*c)" -> balanceada
"[(a+b)*{c-d}]" -> balanceada
"((a+b)*c" -> ERRADA
"(a+b))*c" -> ERRADA
"(a+b]" -> ERRADA
"" -> balanceada
```

---

## 10. Resumo

- Pilha é uma estrutura **LIFO**: o último a entrar é o primeiro a sair.
- Operações essenciais: `empilhar` (push), `desempilhar` (pop), `consultarTopo` (peek).
- Em C, podemos implementar com **vetor + índice `topo`** (tamanho fixo) ou com **lista encadeada + `malloc`** (tamanho flexível).
- Na Torre de Hanói, **cada torre é uma pilha** e **cada movimento é um pop seguido de um push**.
- A regra "disco maior não vai sobre menor" é validada comparando com o `consultarTopo` do destino.
- A mesma ideia resolve outros problemas: desfazer ações (histórico em pilha) e conferir parênteses.
