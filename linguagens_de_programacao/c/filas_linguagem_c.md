# Filas em C com o jogo Batata Quente

> Material para quem **nunca viu** a estrutura de dados *fila*. Ao final, você terá um jogo da Batata Quente funcionando no terminal, construído com uma fila, além de cinco exercícios **resolvidos e comentados**.

---

## 1. O que é uma fila?

Imagine a fila de um caixa de supermercado:

- Quem chega **entra no final** da fila.
- Quem é atendido é sempre **o primeiro da fila**.
- Ninguém "fura" a fila: a ordem de chegada é a ordem de atendimento.

Isso é uma **fila** (*queue*). Ela segue a regra **FIFO**:

> **F**irst **I**n, **F**irst **O**ut — o primeiro a entrar é o primeiro a sair.

```
   SAÍDA                                ENTRADA
  (frente)                               (fim)

   ┌───┬───┬───┬───┐
 ← │ 1 │ 2 │ 3 │ 4 │ ←
   └───┴───┴───┴───┘
     ↑
   primeiro a entrar (primeiro a sair)
```

### Onde filas aparecem no dia a dia da computação?

- **Fila de impressão**: os documentos saem na ordem em que foram enviados.
- **Atendimento** em sites e *call centers*: o primeiro chamado é o primeiro atendido.
- **Teclado e mouse**: o sistema guarda os eventos em uma fila e os processa na ordem.
- **Streaming de vídeo**: os pedaços do vídeo ficam em uma fila de espera (*buffer*) até serem exibidos.
- **Sistemas operacionais**: processos esperam a sua vez de usar o processador.

### Pilha × Fila

| Característica     | Pilha (*stack*)         | Fila (*queue*)                  |
|--------------------|-------------------------|---------------------------------|
| Regra              | **LIFO** (último sai 1º) | **FIFO** (primeiro sai 1º)      |
| Onde se insere     | No topo                 | No **fim**                      |
| Onde se remove     | No topo                 | Na **frente**                   |
| Exemplo do cotidiano | Pilha de pratos       | Fila do supermercado            |
| Quantas "pontas"?  | 1 (o topo)              | 2 (frente e fim)                |

> **Resumindo:** na pilha entra e sai pelo mesmo lado; na fila entra por um lado e sai pelo outro.

---

## 2. Operações básicas de uma fila

| Operação        | Nome em inglês     | O que faz                                |
|-----------------|--------------------|------------------------------------------|
| Enfileirar      | `enqueue`          | Coloca um elemento no **fim**            |
| Desenfileirar   | `dequeue`          | Remove e devolve o elemento da **frente** |
| Consultar frente | `peek` / `front`  | Olha a frente **sem remover**            |
| Vazia?          | `isEmpty`          | Informa se não há elementos              |
| Cheia?          | `isFull`           | Informa se não cabe mais nenhum elemento |

Exemplo passo a passo (fila começa vazia):

```
enqueue(5)   →  [5]
enqueue(8)   →  [5, 8]
enqueue(2)   →  [5, 8, 2]     ← frente é 5, fim é 2
dequeue()    →  devolve 5     →  [8, 2]
front()      →  devolve 8     →  [8, 2]  (nada removido)
```

---

## 3. Como representar uma fila em C

### A primeira ideia (e o problema dela)

Com um vetor, a ideia mais simples seria: `fim` marca onde entra o próximo, e `inicio` marca quem sai. Cada `enqueue` avança `fim`; cada `dequeue` avança `inicio`.

O problema é que **os dois só andam para a frente**. Depois de algumas entradas e saídas, `fim` chega ao último índice do vetor, e o programa acha que está cheio, **mesmo com várias posições livres no começo**, que ficaram vagas quando os elementos saíram.

### A solução: fila circular

Trate o vetor como um **anel**: depois da última posição, volte à posição `0`. Isso se faz com o operador **resto da divisão** (`%`):

```
posição seguinte = (posição atual + 1) % MAX
```

Com `MAX = 5`: depois do índice 4 vem `(4 + 1) % 5 = 0`. O vetor "dá a volta".

```
Vetor com MAX = 5 posições (a fila guardada é: 30, 40, 50, 60):

 índice:      0      1      2      3      4   
           ┌──────┬──────┬──────┬──────┬──────┐
 dados:    │  60  │      │  30  │  40  │  50  │
           └──────┴──────┴──────┴──────┴──────┘
                      ↑      ↑
                     fim   inicio
```

Mesmo com o `60` guardado no índice 0 (depois do `50`), a fila continua na ordem correta: **30 → 40 → 50 → 60**.

Vamos usar **quatro campos**:

- `dados[]`: o vetor que guarda os elementos.
- `inicio`: índice de quem está na **frente** (o próximo a sair).
- `fim`: índice da **próxima posição livre** (onde o próximo entrará).
- `tamanho`: quantos elementos há na fila agora.

> **Por que ter `tamanho`?** Quando a fila está **vazia** e quando está **cheia**, `inicio` e `fim` valem **a mesma coisa**. Sem outra informação, é impossível diferenciar os dois casos. O campo `tamanho` resolve isso: `0` = vazia, `MAX` = cheia.

```
// Quantidade máxima de elementos (capacidade da fila)
#define MAX_JOGADORES 12

// "typedef struct" cria um novo tipo chamado Fila, que agrupa
// o vetor de dados e os três índices em uma única variável.
typedef struct {
    int dados[MAX_JOGADORES];  // armazena os elementos da fila
    int inicio;                // índice de quem está na frente
    int fim;                   // índice da próxima posição livre
    int tamanho;               // quantidade de elementos (0 = vazia)
} Fila;
```

> **Dica:** como `Fila` é um tipo, podemos criar várias filas independentes. No exercício 3 vamos usar **duas** ao mesmo tempo.

### Funções da fila

```
// Prepara a fila para uso: sem elementos e com os índices no começo.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializar(Fila *f) {
    f->inicio = 0;
    f->fim = 0;
    f->tamanho = 0;
}

// Devolve 1 (verdadeiro) se não há elementos, 0 (falso) caso contrário.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int vazia(Fila *f) {
    return f->tamanho == 0;
}

// Devolve 1 se a fila está lotada.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int cheia(Fila *f) {
    return f->tamanho == MAX_JOGADORES;
}

// ENQUEUE: coloca "valor" no FIM da fila.
// Devolve 1 se conseguiu, 0 se a fila estava cheia.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(Fila *f, int valor) {
    if (cheia(f)) return 0;                      // não há espaço: recusa
    f->dados[f->fim] = valor;                    // 1º) guarda na posição livre
    f->fim = (f->fim + 1) % MAX_JOGADORES;       // 2º) avança "fim" (o % faz voltar ao 0)
    f->tamanho++;                                // 3º) agora há mais um elemento
    return 1;
}

// DEQUEUE: remove o elemento da FRENTE e o entrega em "*valor".
// Devolve 1 se conseguiu, 0 se a fila estava vazia.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(Fila *f, int *valor) {
    if (vazia(f)) return 0;                      // não há o que remover
    *valor = f->dados[f->inicio];                // 1º) copia o valor da frente
    f->inicio = (f->inicio + 1) % MAX_JOGADORES; // 2º) avança "inicio" (o % faz voltar ao 0)
    f->tamanho--;                                // 3º) agora há um elemento a menos
    return 1;
}

// PEEK: igual ao dequeue, mas NÃO remove (a frente permanece onde está).
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int consultarInicio(Fila *f, int *valor) {
    if (vazia(f)) return 0;
    *valor = f->dados[f->inicio];
    return 1;
}
```

### Por que `Fila *f` (ponteiro)?

Em C, os argumentos são **copiados** ao chamar uma função. Se passássemos a `Fila` por valor, a função alteraria apenas uma cópia. Com o **ponteiro**, a função altera a fila original. Usamos `f->campo` para acessar os campos através do ponteiro.

O mesmo vale para `int *valor`: é assim que `desenfileirar` "devolve" o elemento removido, já que o `return` está ocupado informando sucesso (1) ou falha (0).

### Função ou método? (para quem vem de Java)

Nos códigos deste material, cada função vem marcada com uma etiqueta no comentário:

| Etiqueta             | O que significa                                                       | Exemplos                                                                           |
|----------------------|-----------------------------------------------------------------------|------------------------------------------------------------------------------------|
| `[MÉTODO da Fila]`   | Função que **opera sobre a fila** (recebe a fila como 1º parâmetro)   | `inicializar`, `vazia`, `cheia`, `enfileirar`, `desenfileirar`, `consultarInicio`  |
| `[FUNÇÃO]`           | Função comum, que faz um trabalho e não "pertence" a uma estrutura    | `iniciar`, `mostrar`, `rodada`, `vencedorRecorrencia`, `palindromo`                |
| `[FUNÇÃO PRINCIPAL]` | Ponto de entrada do programa                                          | `main`                                                                             |

> **Atenção:** a linguagem C **não possui métodos**. Método é um conceito de **orientação a objetos**: uma função que vive dentro de uma classe e age sobre o objeto (`this`). Em C só existem **funções**. Chamamos as operações da fila de "métodos" apenas **por analogia**: elas fazem o papel que os métodos fariam em uma classe `Fila`.

A diferença aparece na hora de **chamar**:

```
Java (método do objeto):        fila.enfileirar(10);        // a fila é "this", implícita
C    (função com a fila):       enfileirar(&fila, 10);      // a fila é passada explicitamente
```

Por isso o ponteiro `Fila *f` faz o papel do `this` do Java: é ele que diz **sobre qual fila** a função deve agir.

---

## 4. O jogo Batata Quente

### Um pouco de história

A **Batata Quente** é uma brincadeira tradicional de roda: as crianças passam um objeto de mão em mão enquanto toca uma música; quando a música para, quem está com o objeto sai da brincadeira. A última criança que sobra vence.

Essa brincadeira lembra um problema famoso da matemática, o **Problema de Josephus** (ou de Flávio Josefo), que vira um excelente exercício de filas. Flávio Josefo foi um historiador judeu-romano do século I, que participou da primeira guerra entre judeus e romanos (por volta de 67 d.C.). Uma **lenda** muito repetida em livros de matemática recreativa conta que ele e outros 40 companheiros formaram uma roda e passaram a eliminar um a cada **três** pessoas, até sobrar apenas um. Josefo teria calculado em que posição ficar para sobreviver.

> **Importante:** a parte da "contagem em roda" é uma versão contada séculos depois, popularizada em livros de matemática recreativa a partir do século XVII. Nos próprios relatos de Josefo, a sobrevivência é atribuída à sorte (ou à providência divina), e não a um cálculo. Mas o **problema matemático** que nasceu dessa história é real, e é o que nos interessa aqui.

| Época                | Fato                                                                                                              |
|----------------------|-------------------------------------------------------------------------------------------------------------------|
| Século I d.C.        | Flávio Josefo escreve sobre a guerra judaico-romana, da qual a lenda se origina                                   |
| Séculos XVI e XVII   | A história da roda de eliminação aparece em livros de matemática recreativa                                       |
| Século XX            | O problema vira exemplo clássico em cursos de computação e matemática discreta (listas circulares, filas, recorrência) |

#### A versão do problema

> Há `n` pessoas em uma roda, numeradas de `1` a `n`. Conta-se de `k` em `k`: a `k`-ésima pessoa é eliminada, e a contagem recomeça a partir da pessoa seguinte. Repete-se até sobrar **uma só**. Em que posição ficar para vencer?

Na versão da lenda, `n = 41` e `k = 3`. Fazendo a simulação, a ordem de saída começa com 3, 6, 9, 12, 15... e os **dois últimos** são as posições **16 e 31**: quem ocupasse a posição **31** seria o único sobrevivente.

#### Será que dá para saber o vencedor sem simular?

Dá! Existe uma **fórmula de recorrência**. Contando as posições a partir do zero, com uma só pessoa a sobrevivente é a posição `0`. Cada pessoa a mais empurra a resposta `k` casas para a frente, dando a volta na roda:

```
pos(1) = 0
pos(i) = (pos(i-1) + k) % i
```

Para o caso especial `k = 2`, existe ainda um atalho elegante: se `n = 2^m + L`, onde `2^m` é a maior potência de 2 que cabe em `n`, o vencedor é a posição `2L + 1`.

#### Comprovando em C

**Arquivo:** `josephus_lenda.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

// [FUNÇÃO]
// Descobre quem sobra (posição de 1 a n) quando, em um círculo de n pessoas,
// elimina-se sempre a k-ésima contada. Usa a recorrência de Josephus:
//   com 1 pessoa, o sobrevivente está na posição 0 (contando a partir do zero);
//   com i pessoas: pos(i) = (pos(i-1) + k) % i
// No fim somamos 1 para voltar a contar a partir do 1.
int vencedorRecorrencia(int n, int k) {
    int i;
    int pos = 0;                    // com 1 pessoa, quem sobra é a posição 0
    for (i = 2; i <= n; i++) {
        pos = (pos + k) % i;        // acrescenta uma pessoa ao círculo
    }
    return pos + 1;
}

// [FUNÇÃO]
// Atalho que só vale para k = 2: acha a maior potência de 2 que cabe em n
// e usa a fórmula  2 * (n - potencia) + 1.
int vencedorK2(int n) {
    int potencia = 1;
    while (potencia * 2 <= n) {
        potencia = potencia * 2;
    }
    return 2 * (n - potencia) + 1;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    int n;

    printf("Jogadores | Vencedor (k=2) | Formula 2*(n-2^m)+1\n");
    for (n = 1; n <= 16; n++) {
        printf("   %2d     |      %2d        |        %2d\n",
               n, vencedorRecorrencia(n, 2), vencedorK2(n));
    }

    printf("\nCaso classico: 41 pessoas, eliminando de 3 em 3\n");
    printf("Sobra a posicao %d\n", vencedorRecorrencia(41, 3));

    return 0;
}
```

**Saída esperada:**

```
Jogadores | Vencedor (k=2) | Formula 2*(n-2^m)+1
    1     |       1        |         1
    2     |       1        |         1
    3     |       3        |         3
    4     |       1        |         1
    5     |       3        |         3
    6     |       5        |         5
    7     |       7        |         7
    8     |       1        |         1
    9     |       3        |         3
   10     |       5        |         5
   11     |       7        |         7
   12     |       9        |         9
   13     |      11        |        11
   14     |      13        |        13
   15     |      15        |        15
   16     |       1        |         1

Caso classico: 41 pessoas, eliminando de 3 em 3
Sobra a posicao 31
```

Repare no padrão da coluna do meio: ele **recomeça em 1** toda vez que `n` é uma potência de 2 (1, 2, 4, 8, 16) e sobe de 2 em 2 depois disso.

---

### Regras

Há `N` jogadores numerados de `1` a `N`, em roda. O jogador `1` começa com a batata.

**Cada rodada:**

1. A música toca e a batata passa de mão em mão.
2. A música para na **`k`-ésima contagem**: esse jogador está **eliminado**.
3. A brincadeira continua com a pessoa seguinte segurando a batata.

**Objetivo:** ser o último que sobra. Neste jogo, você faz um **palpite** antes de começar e o computador mostra quem vence de verdade.

```
Início (6 jogadores, k = 3):

        1  →  2
      ↗          ↘
    6              3
      ↖          ↙
        5  ←  4

 A batata começa com o jogador 1.
 Contagem: 1, 2, 3 → o jogador 3 é eliminado.
```

### Onde está a fila nisso?

A **roda de jogadores é uma fila**:

| No jogo                                              | Na fila           |
|------------------------------------------------------|-------------------|
| Jogador que está com a batata                        | frente da fila    |
| Passar a batata adiante (o jogador vai para o fim)   | `desenfileirar` + `enfileirar` do mesmo jogador |
| Eliminar o jogador da frente                         | `desenfileirar` (e **não** enfileira de volta) |
| Saber quem é o último que sobrou                     | `consultarInicio` |
| Sobrou só um jogador                                 | `tamanho == 1`    |

Perceba a beleza da ideia: **passar a batata** é tirar o jogador da frente e colocá-lo **no fim da fila**. Depois de `k-1` passes, quem está na frente é o jogador sorteado pela música, e ele **sai de vez**.

```
Fila (frente → fim):   1  2  3  4  5  6          k = 3

passe 1: 1 vai para o fim →   2  3  4  5  6  1
passe 2: 2 vai para o fim →   3  4  5  6  1  2
eliminar o da frente (3)  →   4  5  6  1  2        ← o jogador 3 saiu
```

Uma pilha **não serviria** aqui: precisamos tirar da frente e inserir no fim, e isso é justamente o **FIFO**.

---

## 5. Programa completo

Salve como `batata_quente.c`.

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

// Número máximo de jogadores aceito pelo jogo (e capacidade da fila).
#define MAX_JOGADORES 12

/* =====================================================================
   PARTE 1 - A ESTRUTURA DE DADOS FILA (CIRCULAR)
   ===================================================================== */

typedef struct {
    int dados[MAX_JOGADORES];  // os jogadores: cada número é o "crachá" do jogador
    int inicio;                // índice de quem está na FRENTE (próximo a sair)
    int fim;                   // índice da próxima posição LIVRE (onde o próximo entra)
    int tamanho;               // quantos elementos há na fila agora
} Fila;

// Deixa a fila vazia e pronta para uso.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializar(Fila *f) {
    f->inicio = 0;
    f->fim = 0;
    f->tamanho = 0;
}

// 1 se não há ninguém na fila.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int vazia(Fila *f) {
    return f->tamanho == 0;
}

// 1 se a fila não comporta mais ninguém.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int cheia(Fila *f) {
    return f->tamanho == MAX_JOGADORES;
}

// ENQUEUE: coloca um elemento no FIM da fila.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(Fila *f, int valor) {
    if (cheia(f)) return 0;                      // sem espaço
    f->dados[f->fim] = valor;                    // 1º) guarda na posição livre
    f->fim = (f->fim + 1) % MAX_JOGADORES;       // 2º) avança "fim"; o % faz voltar ao 0
    f->tamanho++;                                // 3º) agora há mais um elemento
    return 1;
}

// DEQUEUE: retira o elemento da FRENTE e o entrega em *valor.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(Fila *f, int *valor) {
    if (vazia(f)) return 0;                      // nada para retirar
    *valor = f->dados[f->inicio];                // 1º) copia o valor da frente
    f->inicio = (f->inicio + 1) % MAX_JOGADORES; // 2º) avança "inicio"; o % faz voltar ao 0
    f->tamanho--;                                // 3º) agora há um elemento a menos
    return 1;
}

// PEEK/FRONT: apenas espia quem está na frente, sem retirá-lo.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int consultarInicio(Fila *f, int *valor) {
    if (vazia(f)) return 0;
    *valor = f->dados[f->inicio];
    return 1;
}

/* =====================================================================
   PARTE 2 - A LÓGICA DO JOGO
   ===================================================================== */

// Coloca os jogadores 1, 2, ..., n na fila, nesta ordem.
// [FUNÇÃO]
void iniciar(Fila *f, int n) {
    int i;
    inicializar(f);
    for (i = 1; i <= n; i++) {
        enfileirar(f, i);   // o jogador 1 entra primeiro e fica na frente
    }
}

// Mostra a roda de jogadores, de quem está com a batata até o último.
// [FUNÇÃO]
void mostrar(Fila *f) {
    int i, pos;

    printf("Roda (1o da lista esta com a batata):");
    for (i = 0; i < f->tamanho; i++) {
        pos = (f->inicio + i) % MAX_JOGADORES;   // percorre a fila SEM retirar ninguém
        printf(" %d", f->dados[pos]);
    }
    printf("\n");
}

// Executa UMA rodada: a música para na k-ésima contagem e esse jogador sai.
// Devolve o número do jogador eliminado.
//   - quem está com a batata (a frente) a passa adiante e vai para o FIM da fila;
//   - repete isso k-1 vezes; o k-ésimo jogador é eliminado.
// [FUNÇÃO]
int rodada(Fila *f, int k) {
    // Dar voltas completas na roda não muda nada, então basta (k-1) % tamanho passes.
    int passes = (k - 1) % f->tamanho;
    int i, jogador;

    for (i = 0; i < passes; i++) {
        desenfileirar(f, &jogador);   // sai da frente...
        enfileirar(f, jogador);       // ...e entra no fim: a batata "andou" uma posição
    }
    desenfileirar(f, &jogador);       // o jogador da frente é eliminado (não volta)
    return jogador;
}

/* =====================================================================
   PARTE 3 - PROGRAMA PRINCIPAL
   ===================================================================== */

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Fila roda;            // a roda de jogadores é uma fila
    int n;                // quantidade de jogadores
    int k;                // a música para a cada k contagens
    int palpite;          // quem o jogador acha que vai vencer (0 = só assistir)
    int eliminado;        // jogador retirado na rodada
    int vencedor;         // último que restou
    int rodadas = 0;      // contador de rodadas

    printf("=== BATATA QUENTE ===\n");
    printf("Quantos jogadores (2 a %d)? ", MAX_JOGADORES);
    // scanf devolve quantos valores leu; se não leu 1, a entrada é inválida.
    if (scanf("%d", &n) != 1 || n < 2 || n > MAX_JOGADORES) {
        printf("Valor invalido.\n");
        return 1;
    }

    printf("A musica para a cada quantas contagens (k >= 1)? ");
    if (scanf("%d", &k) != 1 || k < 1) {
        printf("Valor invalido.\n");
        return 1;
    }

    printf("Quem vai vencer (1 a %d, ou 0 para so assistir)? ", n);
    if (scanf("%d", &palpite) != 1 || palpite < 0 || palpite > n) {
        printf("Valor invalido.\n");
        return 1;
    }

    iniciar(&roda, n);   // todos entram na fila, do 1 ao n
    printf("\n");

    // Enquanto houver mais de um jogador, continua eliminando.
    while (roda.tamanho > 1) {
        mostrar(&roda);
        eliminado = rodada(&roda, k);
        rodadas++;
        printf(">> Rodada %d: jogador %d foi eliminado!\n\n", rodadas, eliminado);
    }

    // Restou um só: ele está na frente da fila.
    consultarInicio(&roda, &vencedor);
    printf("*** O vencedor e o jogador %d! ***\n", vencedor);

    if (palpite == 0) {
        printf("Voce so assistiu. Na proxima, arrisque um palpite!\n");
    } else if (palpite == vencedor) {
        printf("Parabens! Voce acertou o palpite!\n");
    } else {
        printf("Voce apostou no jogador %d. Nao foi dessa vez!\n", palpite);
    }

    return 0;
}
```

### Compilar e executar

```
gcc batata_quente.c -o batata_quente
./batata_quente
```

### Exemplo de execução

```
=== BATATA QUENTE ===
Quantos jogadores (2 a 12)? 7
A musica para a cada quantas contagens (k >= 1)? 3
Quem vai vencer (1 a 7, ou 0 para so assistir)? 4

Roda (1o da lista esta com a batata): 1 2 3 4 5 6 7
>> Rodada 1: jogador 3 foi eliminado!

Roda (1o da lista esta com a batata): 4 5 6 7 1 2
>> Rodada 2: jogador 6 foi eliminado!

Roda (1o da lista esta com a batata): 7 1 2 4 5
>> Rodada 3: jogador 2 foi eliminado!

Roda (1o da lista esta com a batata): 4 5 7 1
>> Rodada 4: jogador 7 foi eliminado!

Roda (1o da lista esta com a batata): 1 4 5
>> Rodada 5: jogador 5 foi eliminado!

Roda (1o da lista esta com a batata): 1 4
>> Rodada 6: jogador 1 foi eliminado!

*** O vencedor e o jogador 4! ***
Parabens! Voce acertou o palpite!
```

---

## 6. Entendendo o código por partes

### 6.1 Montando a roda

```
for (i = 1; i <= n; i++) {
    enfileirar(f, i);
}
```

Com `n = 4`, entram 1, depois 2, 3 e 4. O jogador **1** fica na frente (começa com a batata) e o **4** fica no fim. O primeiro a entrar é o primeiro a poder sair.

### 6.2 Passando a batata

```
desenfileirar(f, &jogador);   // sai da frente...
enfileirar(f, jogador);       // ...e entra no fim
```

**Passar a batata = um `dequeue` seguido de um `enqueue` do mesmo jogador.** Quem estava na frente vai para o fim da roda, e o próximo passa a segurar a batata.

### 6.3 Eliminando um jogador

```
desenfileirar(f, &jogador);   // o da frente sai e NÃO volta
return jogador;
```

É o mesmo `dequeue`, mas sem o `enqueue` depois. Por isso `tamanho` diminui de 1 a cada rodada.

### 6.4 Por que `(k - 1) % f->tamanho`?

```
int passes = (k - 1) % f->tamanho;
```

Se a roda tem 3 jogadores e `k = 100`, passar a batata 99 vezes equivale a dar **33 voltas completas** e voltar ao mesmo ponto. Só importam os passes que **sobram** depois das voltas completas, ou seja, o resto da divisão. Isso evita repetir milhares de vezes um trabalho inútil.

### 6.5 Mostrando a fila sem retirar ninguém

```
for (i = 0; i < f->tamanho; i++) {
    pos = (f->inicio + i) % MAX_JOGADORES;
    printf(" %d", f->dados[pos]);
}
```

Para **ver** a fila sem mexer nela, percorremos a partir de `inicio` e usamos `%` para dar a volta no vetor, igualzinho ao `enfileirar` e ao `desenfileirar`.

### 6.6 Condição de término

```
while (roda.tamanho > 1)
```

Enquanto houver mais de um jogador, continua eliminando. Quando `tamanho` chega a 1, o único que restou está na frente, e `consultarInicio` revela o vencedor.

---

## 7. Bônus: simular ou calcular?

Simular com a fila é **fácil de entender**, mas faz muitas operações: cada rodada gasta até `k` passes, e há `n - 1` rodadas, ou seja, cerca de `n × k` operações. Se `n` fosse um milhão e `k` também, seriam trilhões de operações.

A **recorrência** da seção 4 faz apenas `n` passos:

```
// [FUNÇÃO]
int vencedorRecorrencia(int n, int k) {
    int i;
    int pos = 0;                    // com 1 pessoa, quem sobra é a posição 0
    for (i = 2; i <= n; i++) {
        pos = (pos + k) % i;        // acrescenta uma pessoa ao círculo
    }
    return pos + 1;                 // volta a contar a partir do 1
}
```

| Método              | Operações aproximadas | Mostra a roda passo a passo? |
|---------------------|:---------------------:|:----------------------------:|
| Simulação com fila  | `n × k`               | Sim                          |
| Recorrência         | `n`                   | Não                          |

> **Moral da história:** a fila é a ferramenta certa quando você precisa **mostrar o que acontece** (como no jogo). Quando você só quer **a resposta**, uma boa fórmula pode ser muito mais rápida. No exercício 2 vamos confirmar que os dois métodos chegam sempre ao mesmo resultado.

> **Curiosidade:** o sistema operacional também usa filas o tempo todo. Uma das estratégias mais simples de escalonamento de processos, o **Round Robin**, funciona exatamente como a Batata Quente: cada processo usa o processador por um tempinho e, se não terminou, **vai para o fim da fila**.

---

## 8. Erros comuns de iniciantes

| Erro                                   | Por que acontece                                          | Como evitar                                              |
|----------------------------------------|-----------------------------------------------------------|----------------------------------------------------------|
| Esquecer o `inicializar`               | Os campos ficam com lixo de memória                       | Sempre chamar `inicializar` antes de usar                |
| Esquecer o `% MAX` ao avançar `inicio`/`fim` | Os índices saem do vetor (escrita fora dos limites) | Sempre usar `(indice + 1) % MAX`                         |
| Usar `inicio == fim` para testar vazia | Também é verdade quando a fila está **cheia**             | Testar `tamanho == 0` (e `tamanho == MAX` para cheia)    |
| `desenfileirar` em fila vazia          | Lê uma posição sem elemento válido                        | Testar `vazia` antes (as funções já fazem isso)          |
| `enfileirar` em fila cheia             | Sobrescreve elementos que ainda não saíram                | Testar `cheia` antes                                     |
| Passar `Fila` sem ponteiro             | Altera só uma cópia                                       | Usar `Fila *f`                                           |
| Confundir `fim` com "último elemento"  | `fim` é a **próxima posição livre**, não o último         | O último elemento está em `(fim - 1 + MAX) % MAX`        |
| Esquecer de zerar `fim` na fila dinâmica | Ao esvaziar, `fim` fica apontando para um nó já liberado | Quando `inicio` virar `NULL`, fazer também `fim = NULL`  |

---

## 9. Exercícios resolvidos

Cada exercício abaixo é um programa **completo**, já comentado. Salve em um arquivo `.c`, compile com `gcc` e execute.

> **Sugestão didática:** tente resolver sozinho antes de olhar a resposta.

---

### Exercício 1 - Teste a fila sozinha

**Enunciado:** crie um `main` que enfileira 10, 20, 30, desenfileira duas vezes e imprime o que sobrou. Depois, enfileire mais quatro valores em uma fila de capacidade 5 e observe a fila "dar a volta".

**Arquivo:** `exercicio1_fila.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define MAX 5   // capacidade pequena de propósito: assim vemos a fila "dar a volta"

typedef struct {
    int dados[MAX];
    int inicio;
    int fim;
    int tamanho;
} Fila;

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializar(Fila *f) { f->inicio = 0; f->fim = 0; f->tamanho = 0; }
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int  vazia(Fila *f)       { return f->tamanho == 0; }
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int  cheia(Fila *f)       { return f->tamanho == MAX; }

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(Fila *f, int valor) {
    if (cheia(f)) return 0;
    f->dados[f->fim] = valor;
    f->fim = (f->fim + 1) % MAX;     // depois da última posição, volta para a 0
    f->tamanho++;
    return 1;
}

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(Fila *f, int *valor) {
    if (vazia(f)) return 0;
    *valor = f->dados[f->inicio];
    f->inicio = (f->inicio + 1) % MAX;
    f->tamanho--;
    return 1;
}

// Mostra a fila da frente até o fim, sem retirar ninguém.
// [FUNÇÃO]
void mostrar(Fila *f) {
    int i;
    printf("Fila (frente -> fim):");
    for (i = 0; i < f->tamanho; i++) {
        printf(" %d", f->dados[(f->inicio + i) % MAX]);
    }
    printf("   [inicio=%d, fim=%d, tamanho=%d]\n", f->inicio, f->fim, f->tamanho);
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Fila f;
    int valor, i;

    inicializar(&f);   // sempre inicialize antes de usar!

    // Enfileira 10, 20 e 30 (o 10 fica na frente).
    enfileirar(&f, 10);
    enfileirar(&f, 20);
    enfileirar(&f, 30);
    printf("Enfileirados: 10, 20, 30\n");
    mostrar(&f);

    // Desenfileira duas vezes: devem sair 10 e depois 20 (ordem FIFO).
    for (i = 0; i < 2; i++) {
        if (desenfileirar(&f, &valor)) {
            printf("Desenfileirou: %d\n", valor);
        }
    }
    mostrar(&f);

    // Agora enfileiramos mais quatro: "fim" vai passar da última posição
    // do vetor e voltar para o começo (fila circular em ação!).
    enfileirar(&f, 40);
    enfileirar(&f, 50);
    enfileirar(&f, 60);
    enfileirar(&f, 70);
    printf("Enfileirados: 40, 50, 60, 70\n");
    mostrar(&f);

    // A fila está cheia (5 de 5): mais uma tentativa deve falhar.
    if (!enfileirar(&f, 80)) {
        printf("Nao coube o 80: fila cheia!\n");
    }

    return 0;
}
```

**Saída esperada:**

```
Enfileirados: 10, 20, 30
Fila (frente -> fim): 10 20 30   [inicio=0, fim=3, tamanho=3]
Desenfileirou: 10
Desenfileirou: 20
Fila (frente -> fim): 30   [inicio=2, fim=3, tamanho=1]
Enfileirados: 40, 50, 60, 70
Fila (frente -> fim): 30 40 50 60 70   [inicio=2, fim=2, tamanho=5]
Nao coube o 80: fila cheia!
```

> **Observe:** na penúltima linha, `inicio=2` e `fim=2` são **iguais**, e mesmo assim a fila está cheia (`tamanho=5`). É por isso que precisamos do campo `tamanho`!

---

### Exercício 2 - Simulação × Recorrência

**Enunciado:** escreva duas funções que descobrem o vencedor da Batata Quente, uma **simulando com a fila** e outra usando a **recorrência**, e verifique que elas sempre concordam.

**Arquivo:** `exercicio2_josephus.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define MAX 50

typedef struct {
    int dados[MAX];
    int inicio;
    int fim;
    int tamanho;
} Fila;

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializar(Fila *f) { f->inicio = 0; f->fim = 0; f->tamanho = 0; }
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int  vazia(Fila *f)       { return f->tamanho == 0; }
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int  cheia(Fila *f)       { return f->tamanho == MAX; }

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(Fila *f, int valor) {
    if (cheia(f)) return 0;
    f->dados[f->fim] = valor;
    f->fim = (f->fim + 1) % MAX;
    f->tamanho++;
    return 1;
}

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(Fila *f, int *valor) {
    if (vazia(f)) return 0;
    *valor = f->dados[f->inicio];
    f->inicio = (f->inicio + 1) % MAX;
    f->tamanho--;
    return 1;
}

// Versão 1: SIMULA o jogo usando a fila (como no programa principal).
// [FUNÇÃO]
int vencedorSimulacao(int n, int k) {
    Fila f;
    int i, jogador;

    inicializar(&f);
    for (i = 1; i <= n; i++) enfileirar(&f, i);

    while (f.tamanho > 1) {
        for (i = 1; i < k; i++) {          // k-1 passes
            desenfileirar(&f, &jogador);
            enfileirar(&f, jogador);
        }
        desenfileirar(&f, &jogador);       // elimina o k-ésimo
    }
    desenfileirar(&f, &jogador);           // o último que restou
    return jogador;
}

// Versão 2: usa a RECORRÊNCIA, sem fila e sem simular nada.
//   pos(1) = 0
//   pos(i) = (pos(i-1) + k) % i      (posições contadas a partir do zero)
// [FUNÇÃO]
int vencedorRecorrencia(int n, int k) {
    int i;
    int pos = 0;
    for (i = 2; i <= n; i++) {
        pos = (pos + k) % i;
    }
    return pos + 1;   // volta a contar a partir do 1
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    int n;
    int k = 3;
    int sim, rec;

    printf("k = %d\n", k);
    printf("Jogadores | Simulacao (fila) | Recorrencia | Iguais?\n");
    for (n = 1; n <= 12; n++) {
        sim = vencedorSimulacao(n, k);
        rec = vencedorRecorrencia(n, k);
        printf("   %2d     |        %2d        |     %2d      | %s\n",
               n, sim, rec, sim == rec ? "sim" : "NAO");
    }

    return 0;
}
```

**Saída esperada:**

```
k = 3
Jogadores | Simulacao (fila) | Recorrencia | Iguais?
    1     |         1        |      1      | sim
    2     |         2        |      2      | sim
    3     |         2        |      2      | sim
    4     |         1        |      1      | sim
    5     |         4        |      4      | sim
    6     |         1        |      1      | sim
    7     |         4        |      4      | sim
    8     |         7        |      7      | sim
    9     |         1        |      1      | sim
   10     |         4        |      4      | sim
   11     |         7        |      7      | sim
   12     |        10        |     10      | sim
```

**Para usar no jogo:** copie `vencedorRecorrencia` para o `batata_quente.c` e, depois de ler `n` e `k`, acrescente:

```
printf("(Dica da matematica: o vencedor sera o jogador %d)\n", vencedorRecorrencia(n, k));
```

---

### Exercício 3 - Fila de atendimento com prioridade (duas filas)

**Enunciado:** simule a recepção de um banco com **duas filas de senhas**: a normal e a prioritária. Quem chama o próximo atende **sempre a prioritária primeiro**; dentro de cada fila, vale a ordem de chegada.

**Ideia:** cada tipo de senha tem a sua própria `Fila`. Ao chamar o próximo, tentamos `desenfileirar` da prioritária; **só se ela estiver vazia** tentamos a normal. Como `desenfileirar` devolve 0 quando a fila está vazia, a própria função serve de teste.

**Arquivo:** `exercicio3_atendimento.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>

#define MAX 50   // capacidade de cada fila de senhas

typedef struct {
    int dados[MAX];
    int inicio;
    int fim;
    int tamanho;
} Fila;

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializar(Fila *f) { f->inicio = 0; f->fim = 0; f->tamanho = 0; }
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int  vazia(Fila *f)       { return f->tamanho == 0; }
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int  cheia(Fila *f)       { return f->tamanho == MAX; }

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(Fila *f, int valor) {
    if (cheia(f)) return 0;
    f->dados[f->fim] = valor;
    f->fim = (f->fim + 1) % MAX;
    f->tamanho++;
    return 1;
}

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(Fila *f, int *valor) {
    if (vazia(f)) return 0;
    *valor = f->dados[f->inicio];
    f->inicio = (f->inicio + 1) % MAX;
    f->tamanho--;
    return 1;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Fila normal;          // fila das senhas comuns
    Fila prioritaria;     // fila das senhas prioritárias
    char linha[32];       // texto digitado pelo atendente
    int opcao;
    int senha;
    int proxima = 1;      // numeração das senhas (compartilhada pelas duas filas)

    inicializar(&normal);
    inicializar(&prioritaria);

    while (1) {
        printf("\n--- Atendimento (normal: %d na fila | prioritaria: %d na fila) ---\n",
               normal.tamanho, prioritaria.tamanho);
        printf("1 - Nova senha NORMAL\n");
        printf("2 - Nova senha PRIORITARIA\n");
        printf("3 - Chamar proximo\n");
        printf("0 - Sair\n");
        printf("Escolha: ");

        if (fgets(linha, sizeof linha, stdin) == NULL) break;   // fim da entrada
        if (sscanf(linha, "%d", &opcao) != 1) {
            printf(">> Opcao invalida.\n");
            continue;
        }

        if (opcao == 0) {
            break;   // sai do while
        }
        else if (opcao == 1) {
            if (enfileirar(&normal, proxima)) {
                printf(">> Sua senha: N%03d\n", proxima);
                proxima++;
            } else {
                printf(">> Fila normal cheia!\n");
            }
        }
        else if (opcao == 2) {
            if (enfileirar(&prioritaria, proxima)) {
                printf(">> Sua senha: P%03d\n", proxima);
                proxima++;
            } else {
                printf(">> Fila prioritaria cheia!\n");
            }
        }
        else if (opcao == 3) {
            // Regra: a fila prioritária é sempre atendida primeiro.
            // Dentro de cada fila vale o FIFO: quem chegou antes é chamado antes.
            if (desenfileirar(&prioritaria, &senha)) {
                printf(">> Chamando senha P%03d\n", senha);
            } else if (desenfileirar(&normal, &senha)) {
                printf(">> Chamando senha N%03d\n", senha);
            } else {
                printf(">> Ninguem esperando.\n");
            }
        }
        else {
            printf(">> Opcao invalida.\n");
        }
    }

    printf("Encerrado. Ficaram %d senha(s) sem atendimento.\n",
           normal.tamanho + prioritaria.tamanho);
    return 0;
}
```

**Exemplo de uso** (opções digitadas: 1, 1, 2, 1, 3, 3, 3, 3, 3, 0; o menu foi omitido para economizar espaço):

```
>> Sua senha: N001
>> Sua senha: N002
>> Sua senha: P003
>> Sua senha: N004
>> Chamando senha P003      ← a prioritária passou na frente, apesar de ter chegado depois
>> Chamando senha N001
>> Chamando senha N002
>> Chamando senha N004
>> Ninguem esperando.
Encerrado. Ficaram 0 senha(s) sem atendimento.
```

---

### Exercício 4 - Fila dinâmica (lista encadeada com `malloc`)

**Enunciado:** reescreva a fila com lista encadeada e `malloc`, sem limite fixo de capacidade.

**Ideia:** em vez de um vetor, cada elemento é um **nó** alocado na memória com `malloc`. Cada nó guarda o valor e um ponteiro para o nó que está **atrás** dele. A fila precisa conhecer **as duas pontas**: `inicio` (por onde sai) e `fim` (por onde entra). Com o ponteiro `fim`, enfileirar continua sendo uma operação rápida, sem percorrer a lista.

```
inicio                              fim
  ↓                                  ↓
[ 1 | • ] → [ 2 | • ] → [ 3 | NULL ]
  frente                     último a entrar
```

**Arquivo:** `exercicio4_fila_dinamica.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>   // malloc e free

// Um nó da lista: guarda um valor e aponta para o nó de trás.
typedef struct No {
    int valor;
    struct No *prox;   // nó que vem DEPOIS deste na fila (NULL se for o último)
} No;

// A fila conhece o nó da frente (inicio), o do fim e quantos elementos tem.
typedef struct {
    No *inicio;    // NULL = fila vazia
    No *fim;       // último nó (onde os novos entram)
    int tamanho;
} FilaDin;

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializar(FilaDin *f) {
    f->inicio = NULL;
    f->fim = NULL;
    f->tamanho = 0;
}

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int vazia(FilaDin *f) {
    return f->inicio == NULL;
}

// Não existe "cheia": só falha se o computador ficar sem memória.

// ENQUEUE: cria um nó novo e o coloca no FIM da fila.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(FilaDin *f, int valor) {
    No *novo = (No *) malloc(sizeof(No));   // reserva memória para o nó
    if (novo == NULL) return 0;             // malloc falhou: sem memória
    novo->valor = valor;
    novo->prox = NULL;                      // o novo nó é o último: ninguém atrás dele

    if (vazia(f)) {
        f->inicio = novo;                   // fila vazia: o novo é frente E fim
    } else {
        f->fim->prox = novo;                // o antigo último passa a apontar para o novo
    }
    f->fim = novo;                          // o novo nó é o novo fim
    f->tamanho++;
    return 1;
}

// DEQUEUE: remove o nó da FRENTE, entrega o valor e LIBERA a memória do nó.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(FilaDin *f, int *valor) {
    No *removido;
    if (vazia(f)) return 0;
    removido = f->inicio;          // guarda o nó que vai sair
    *valor = removido->valor;      // entrega o valor
    f->inicio = removido->prox;    // a frente passa a ser o nó de trás
    if (f->inicio == NULL) {
        f->fim = NULL;             // CUIDADO: esvaziou, então "fim" também precisa zerar!
    }
    free(removido);                // devolve a memória (evita vazamento!)
    f->tamanho--;
    return 1;
}

// PEEK: espia a frente sem remover.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int consultarInicio(FilaDin *f, int *valor) {
    if (vazia(f)) return 0;
    *valor = f->inicio->valor;
    return 1;
}

// Libera todos os nós restantes. Chame antes de encerrar o programa.
// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void liberar(FilaDin *f) {
    int descartado;
    while (desenfileirar(f, &descartado)) {
        // o próprio desenfileirar já faz o free de cada nó
    }
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    FilaDin f;
    int valor, i;

    inicializar(&f);

    // Enfileira 1000 valores: com o vetor de 12 posições isso seria impossível!
    for (i = 1; i <= 1000; i++) {
        enfileirar(&f, i);
    }
    printf("Tamanho apos enfileirar 1000 valores: %d\n", f.tamanho);

    consultarInicio(&f, &valor);
    printf("Frente atual: %d\n", valor);

    // Desenfileira três valores: saem 1, 2 e 3 (o primeiro a entrar sai primeiro).
    for (i = 0; i < 3; i++) {
        desenfileirar(&f, &valor);
        printf("Desenfileirou: %d\n", valor);
    }
    printf("Tamanho agora: %d\n", f.tamanho);

    liberar(&f);   // devolve toda a memória restante
    printf("Tamanho apos liberar: %d\n", f.tamanho);

    return 0;
}
```

**Saída esperada:**

```
Tamanho apos enfileirar 1000 valores: 1000
Frente atual: 1
Desenfileirou: 1
Desenfileirou: 2
Desenfileirou: 3
Tamanho agora: 997
Tamanho apos liberar: 0
```

**Para usar no jogo:** troque `Fila` por `FilaDin` e a condição `roda.tamanho > 1` continua valendo. Já a função `mostrar` precisaria percorrer os nós (`No *p = f->inicio; p != NULL; p = p->prox`) em vez de usar `dados[pos]`.

---

### Exercício 5 - Palíndromo com pilha e fila

**Enunciado:** use **uma pilha e uma fila juntas** para verificar se uma frase é um palíndromo (lê-se igual de trás para a frente), como `arara`.

**Ideia:** cada letra da frase é colocada nas **duas** estruturas. Depois, retiramos uma letra de cada:

- a **fila** entrega as letras na ordem original (FIFO): primeira, segunda, terceira...
- a **pilha** entrega as letras na ordem inversa (LIFO): última, penúltima, antepenúltima...

Se as letras retiradas forem **sempre iguais**, a frase é igual nos dois sentidos.

```
Palavra: a r a r a

Fila  (sai pela frente):  a → r → a → r → a
Pilha (sai pelo topo):    a → r → a → r → a   (lida de trás para a frente)

Todas as comparações coincidem  →  palíndromo!
```

**Arquivo:** `exercicio5_palindromo.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Fila (recebe a fila como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <ctype.h>    // isalpha e tolower

#define MAX 100   // tamanho máximo da frase (em letras)

// Esta pilha e esta fila guardam CARACTERES (char) em vez de inteiros.
typedef struct {
    char dados[MAX];
    int topo;
} PilhaChar;

typedef struct {
    char dados[MAX];
    int inicio;
    int fim;
    int tamanho;
} FilaChar;

// ---- Operações da pilha (já conhecidas do material sobre pilhas) ----

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
void inicializarPilha(PilhaChar *p) { p->topo = -1; }

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int empilhar(PilhaChar *p, char c) {
    if (p->topo == MAX - 1) return 0;
    p->dados[++p->topo] = c;
    return 1;
}

// [MÉTODO da Pilha] (em C é uma função que recebe a pilha)
int desempilhar(PilhaChar *p, char *c) {
    if (p->topo == -1) return 0;
    *c = p->dados[p->topo--];
    return 1;
}

// ---- Operações da fila ----

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
void inicializarFila(FilaChar *f) { f->inicio = 0; f->fim = 0; f->tamanho = 0; }

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int enfileirar(FilaChar *f, char c) {
    if (f->tamanho == MAX) return 0;
    f->dados[f->fim] = c;
    f->fim = (f->fim + 1) % MAX;
    f->tamanho++;
    return 1;
}

// [MÉTODO da Fila] (em C é uma função que recebe a fila)
int desenfileirar(FilaChar *f, char *c) {
    if (f->tamanho == 0) return 0;
    *c = f->dados[f->inicio];
    f->inicio = (f->inicio + 1) % MAX;
    f->tamanho--;
    return 1;
}

// Devolve 1 se a frase é um palíndromo (lê-se igual nos dois sentidos).
// Ignora espaços, pontuação e diferença entre maiúsculas e minúsculas.
// [FUNÇÃO]
int palindromo(const char *frase) {
    PilhaChar pilha;
    FilaChar fila;
    char daPilha, daFila;
    int i;

    inicializarPilha(&pilha);
    inicializarFila(&fila);

    // Cada letra entra nas DUAS estruturas.
    for (i = 0; frase[i] != '\0'; i++) {
        if (isalpha((unsigned char) frase[i])) {
            char letra = (char) tolower((unsigned char) frase[i]);
            empilhar(&pilha, letra);
            enfileirar(&fila, letra);
        }
    }

    // A fila entrega as letras na ordem em que foram lidas (FIFO).
    // A pilha entrega as letras na ordem INVERSA (LIFO).
    // Se as duas ordens coincidem em todas as letras, é palíndromo.
    while (desenfileirar(&fila, &daFila) && desempilhar(&pilha, &daPilha)) {
        if (daFila != daPilha) return 0;
    }
    return 1;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    const char *testes[] = {
        "arara",                                   // palíndromo
        "Anotaram a data da maratona",             // palíndromo (com espaços e maiúscula)
        "Socorram-me, subi no onibus em Marrocos", // palíndromo (com pontuação)
        "banana",                                  // não é
        "pilha e fila"                             // não é
    };
    int total = sizeof(testes) / sizeof(testes[0]);
    int i;

    for (i = 0; i < total; i++) {
        printf("\"%s\" -> %s\n", testes[i],
               palindromo(testes[i]) ? "palindromo" : "nao e palindromo");
    }

    return 0;
}
```

**Saída esperada:**

```
"arara" -> palindromo
"Anotaram a data da maratona" -> palindromo
"Socorram-me, subi no onibus em Marrocos" -> palindromo
"banana" -> nao e palindromo
"pilha e fila" -> nao e palindromo
```

---

## 10. Resumo

- Fila é uma estrutura **FIFO**: o primeiro a entrar é o primeiro a sair.
- Operações essenciais: `enfileirar` (enqueue), `desenfileirar` (dequeue), `consultarInicio` (peek).
- Em C, podemos implementar com **vetor circular + `inicio`, `fim` e `tamanho`** (tamanho fixo) ou com **lista encadeada + `malloc`** (tamanho flexível, com ponteiros para as duas pontas).
- O operador `%` faz o vetor "dar a volta", aproveitando as posições liberadas no começo.
- Na Batata Quente, **a roda é uma fila**; **passar a batata é um dequeue seguido de um enqueue**, e **eliminar é só o dequeue**.
- A simulação com fila mostra tudo passo a passo; a **recorrência** de Josephus dá a resposta direto, com muito menos operações.
- Pilha e fila se complementam: juntas, resolvem problemas como o do palíndromo; a fila também resolve problemas de atendimento com prioridade (duas filas) e de escalonamento (Round Robin).
