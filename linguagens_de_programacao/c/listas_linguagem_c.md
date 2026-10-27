# Listas em C com o jogo Organize o Trem

> Material para quem **nunca viu** a estrutura de dados *lista encadeada*. Ao final, você terá um jogo de organizar vagões de um trem funcionando no terminal, construído com uma lista, além de cinco exercícios **resolvidos e comentados**.

---

## 1. O que é uma lista?

Imagine um **trem**:

- Ele é formado por **vagões**.
- Cada vagão está **engatado** no vagão seguinte.
- Para chegar ao 4º vagão, você começa na **locomotiva** e vai passando de vagão em vagão.
- Para **encaixar um vagão novo no meio**, basta soltar um engate e prender o vagão novo nas duas pontas. Os outros vagões **não precisam sair do lugar**.

Isso é uma **lista encadeada** (*linked list*): uma sequência de elementos, chamados **nós**, em que cada nó guarda **um valor** e **o endereço do próximo nó**.

```
 locomotiva
 (inicio)
    │
    ▼
 ┌────┬───┐    ┌────┬───┐    ┌────┬───┐    ┌────┬──────┐
 │ 10 │ ●─┼───▶│ 20 │ ●─┼───▶│ 30 │ ●─┼───▶│ 40 │ NULL │
 └────┴───┘    └────┴───┘    └────┴───┘    └────┴──────┘
  valor prox                                       fim da lista
```

> Cada caixinha é um **nó**. A "setinha" (`●─▶`) é um **ponteiro**. O último nó aponta para `NULL`, que significa "não há próximo".

### Vetor × Lista: qual a diferença?

Um **vetor** (*array*) guarda os elementos **lado a lado** na memória, como poltronas de um cinema. Uma **lista** guarda cada elemento **onde houver espaço**, e os liga por ponteiros, como vagões que podem estar em qualquer lugar do pátio, mas estão engatados.

```
Vetor:   │ 10 │ 20 │ 30 │ 40 │          ← blocos colados na memória

Lista:   [10]→       [20]→     [30]→    [40]          ← nós espalhados, ligados por ponteiros
```

| Característica                   | Vetor                                  | Lista encadeada                          |
|----------------------------------|----------------------------------------|------------------------------------------|
| Acesso ao elemento nº *i*        | **Direto** (`v[i]`), muito rápido      | Precisa **andar** do início até lá       |
| Inserir/remover no começo        | Todos os elementos precisam andar      | Só religa **dois ponteiros**             |
| Inserir/remover no meio          | Todos os elementos seguintes andam     | Só religa ponteiros (após chegar ao ponto) |
| Tamanho                          | Fixo (ou precisa realocar)             | **Cresce e encolhe** a cada inserção/remoção |
| Memória extra                    | Nenhuma                                | Um ponteiro por nó                       |

### Onde listas aparecem no dia a dia da computação?

- **Playlists** de música: adicionar, remover e reordenar faixas.
- **Editores de texto**: as linhas do documento podem ser nós de uma lista.
- **Gerenciadores de memória** do sistema operacional: blocos livres ligados em lista.
- **Listas de contatos**, de tarefas, de jogadores...
- Servem de **base para outras estruturas**: pilhas, filas e tabelas hash podem ser construídas com listas.

### Três "sabores" de lista

```
Simples:   [A|•]→[B|•]→[C|NULL]

Dupla:     NULL⇄[A]⇄[B]⇄[C]⇄NULL        (cada nó conhece o anterior E o próximo)

Circular:  [A]→[B]→[C]──┐
            ▲           │
            └───────────┘                (o último volta ao primeiro)
```

Neste material, o jogo usa a lista **simples**. Os exercícios 4 e 5 mostram a **dupla** e a **circular**.

---

## 2. Operações básicas de uma lista

| Operação              | Nome em inglês        | O que faz                                              |
|-----------------------|-----------------------|--------------------------------------------------------|
| Inserir no início     | `insertFirst`         | Cria um nó e o coloca **antes** do primeiro            |
| Inserir no fim        | `insertLast`          | Cria um nó e o coloca **depois** do último             |
| Inserir na posição *p*| `insertAt`            | Cria um nó e o encaixa **no meio**                     |
| Remover da posição *p*| `removeAt`            | Retira o nó da posição *p* e **libera** sua memória    |
| Consultar posição *p* | `get`                 | Lê o valor sem remover (precisa andar até lá)          |
| Buscar um valor       | `search`              | Descobre em que posição o valor está                   |
| Percorrer             | `traverse`            | Visita todos os nós, do primeiro ao último             |
| Vazia?                | `isEmpty`             | Informa se não há nenhum nó                            |

Exemplo passo a passo (lista começa vazia; posições contam a partir do **0**):

```
inserirFim(10)        →  [10]
inserirFim(30)        →  [10, 30]
inserirInicio(5)      →  [5, 10, 30]
inserirPosicao(2, 20) →  [5, 10, 20, 30]     ← o 20 passou a ocupar a posição 2
removerPosicao(0)     →  devolve 5           →  [10, 20, 30]
consultarPosicao(1)   →  devolve 20          →  [10, 20, 30]  (nada removido)
```

---

## 3. Como representar uma lista em C

### Antes de tudo: três ferramentas que vamos usar

**1) Ponteiro.** Uma variável que guarda o **endereço** de outra coisa na memória. `No *p` significa "`p` guarda o endereço de um `No`". Para acessar um campo através do ponteiro, usamos a seta: `p->valor`.

**2) `NULL`.** O ponteiro "que não aponta para lugar nenhum". Marca o **fim da lista**. Percorrer uma lista é seguir `prox` até chegar em `NULL`.

**3) `malloc` e `free`.** Cada nó é **criado sob demanda** com `malloc` (reserva memória) e **destruído** com `free` (devolve a memória). Quem reserva é responsável por devolver, senão ocorre **vazamento de memória** (*memory leak*).

### A estrutura

Precisamos de **dois tipos**: o **nó** e a **lista**.

```
// Um nó (vagão): guarda um valor e aponta para o PRÓXIMO nó.
typedef struct No {
    int valor;            // o número pintado no vagão
    struct No *prox;      // próximo vagão (NULL se este for o último)
} No;

// A lista conhece apenas o primeiro nó e quantos nós existem.
typedef struct {
    No *inicio;           // primeiro nó (NULL = lista vazia)
    int tamanho;          // quantidade de nós
} Lista;
```

> **Por que `struct No *prox` dentro do próprio `No`?** O nó precisa apontar para outro nó **do mesmo tipo**. Dentro da definição o apelido `No` ainda não existe, então usamos o nome completo `struct No`.

> **Dica:** como `Lista` é um tipo, podemos criar várias listas independentes.

### Funções da lista

```
// Deixa a lista vazia e pronta para uso.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void inicializar(Lista *l) {
    l->inicio = NULL;
    l->tamanho = 0;
}

// Devolve 1 se não há nenhum nó.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int vazia(Lista *l) {
    return l->inicio == NULL;
}

// Insere "valor" de modo que ele passe a ocupar a posição "pos".
// Posições contam a partir do 0: pos = 0 é o início; pos = tamanho é o fim.
// Devolve 1 se conseguiu, 0 se a posição é inválida ou faltou memória.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirPosicao(Lista *l, int pos, int valor) {
    No *novo, *anterior;
    int i;

    if (pos < 0 || pos > l->tamanho) return 0;       // posição inexistente

    novo = (No *) malloc(sizeof(No));                // reserva memória para o nó
    if (novo == NULL) return 0;                      // malloc falhou: sem memória
    novo->valor = valor;

    if (pos == 0) {
        // Caso especial: entrar no começo muda o "inicio" da lista.
        novo->prox = l->inicio;       // o novo aponta para o antigo primeiro
        l->inicio = novo;             // o novo passa a ser o primeiro
    } else {
        // Anda até o nó que ficará IMEDIATAMENTE ANTES do novo (posição pos-1).
        anterior = l->inicio;
        for (i = 0; i < pos - 1; i++) {
            anterior = anterior->prox;
        }
        novo->prox = anterior->prox;  // 1º) o novo aponta para quem vinha depois
        anterior->prox = novo;        // 2º) o anterior aponta para o novo
    }
    l->tamanho++;
    return 1;
}

// Atalho: insere no começo.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirInicio(Lista *l, int valor) {
    return inserirPosicao(l, 0, valor);
}

// Atalho: insere no fim.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirFim(Lista *l, int valor) {
    return inserirPosicao(l, l->tamanho, valor);
}

// Remove o nó da posição "pos" e entrega o valor em "*valor".
// Devolve 1 se conseguiu, 0 se a posição não existe.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int removerPosicao(Lista *l, int pos, int *valor) {
    No *anterior, *removido;
    int i;

    if (pos < 0 || pos >= l->tamanho) return 0;     // posição inexistente

    if (pos == 0) {
        removido = l->inicio;
        l->inicio = removido->prox;                 // o segundo vira o primeiro
    } else {
        anterior = l->inicio;
        for (i = 0; i < pos - 1; i++) {
            anterior = anterior->prox;
        }
        removido = anterior->prox;                  // o nó a ser retirado
        anterior->prox = removido->prox;            // "pula" o removido
    }
    *valor = removido->valor;   // guarda o valor ANTES de liberar o nó
    free(removido);             // devolve a memória (evita vazamento!)
    l->tamanho--;
    return 1;
}

// Lê o valor da posição "pos" sem remover nada.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int consultarPosicao(Lista *l, int pos, int *valor) {
    No *p;
    int i;

    if (pos < 0 || pos >= l->tamanho) return 0;
    p = l->inicio;
    for (i = 0; i < pos; i++) {
        p = p->prox;            // uma lista NÃO tem acesso direto: é preciso andar
    }
    *valor = p->valor;
    return 1;
}

// Libera todos os nós restantes. Chame antes de encerrar o programa.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void liberar(Lista *l) {
    No *atual = l->inicio;
    No *seguinte;

    while (atual != NULL) {
        seguinte = atual->prox;   // guarda o próximo ANTES de liberar o atual
        free(atual);
        atual = seguinte;
    }
    l->inicio = NULL;
    l->tamanho = 0;
}
```

### Por que `Lista *l` (ponteiro)?

Em C, os argumentos são **copiados** ao chamar uma função. Se passássemos a `Lista` por valor, a função alteraria apenas uma cópia (e o `inicio` da lista original continuaria o mesmo). Com o **ponteiro**, a função altera a lista original. Usamos `l->campo` para acessar os campos através do ponteiro.

O mesmo vale para `int *valor`: é assim que `removerPosicao` "devolve" o valor retirado, já que o `return` está ocupado informando sucesso (1) ou falha (0).

### Por que o início é um caso especial?

Para inserir ou remover **no meio**, usamos o nó *anterior* (`anterior->prox = ...`). Mas o primeiro nó **não tem anterior**: quem aponta para ele é o próprio `l->inicio`. Por isso `pos == 0` tem um tratamento separado, em que mudamos `l->inicio` em vez de `anterior->prox`.

### Função ou método? (para quem vem de Java)

Nos códigos deste material, cada função vem marcada com uma etiqueta no comentário:

| Etiqueta             | O que significa                                                       | Exemplos                                                                                  |
|----------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| `[MÉTODO da Lista]`  | Função que **opera sobre a lista** (recebe a lista como 1º parâmetro) | `inicializar`, `vazia`, `inserirPosicao`, `inserirInicio`, `inserirFim`, `removerPosicao`, `consultarPosicao`, `liberar` |
| `[FUNÇÃO]`           | Função comum, que faz um trabalho e não "pertence" a uma estrutura    | `embaralhar`, `ordenado`, `mostrar`, `minimoJogadas`, `tocar`                             |
| `[FUNÇÃO PRINCIPAL]` | Ponto de entrada do programa                                          | `main`                                                                                    |

> **Atenção:** a linguagem C **não possui métodos**. Método é um conceito de **orientação a objetos**: uma função que vive dentro de uma classe e age sobre o objeto (`this`). Em C só existem **funções**. Chamamos as operações da lista de "métodos" apenas **por analogia**: elas fazem o papel que os métodos fariam em uma classe `Lista`.

A diferença aparece na hora de **chamar**:

```
Java (método do objeto):        lista.inserirFim(10);        // a lista é "this", implícita
C    (função com a lista):      inserirFim(&lista, 10);      // a lista é passada explicitamente
```

Por isso o ponteiro `Lista *l` faz o papel do `this` do Java: é ele que diz **sobre qual lista** a função deve agir.

---

## 4. O jogo Organize o Trem

### Um pouco de história

As listas encadeadas surgiram **porque os programas precisavam de memória que crescesse e encolhesse**. Costuma-se creditar a invenção a **Allen Newell, Cliff Shaw e Herbert Simon**, que, em meados da década de 1950, criaram a linguagem **IPL** (*Information Processing Language*) para escrever o **Logic Theorist**, programa que provava teoremas de lógica. Os "pensamentos" do programa não cabiam em vetores de tamanho fixo: era preciso ligar pedaços de informação uns aos outros.

Pouco depois, em **1958**, **John McCarthy** criou a linguagem **Lisp**, nome que vem de *LISt Processing*, na qual a lista é a estrutura de dados central.

| Época                | Fato                                                                                                       |
|----------------------|------------------------------------------------------------------------------------------------------------|
| Meados dos anos 1950 | Newell, Shaw e Simon usam listas encadeadas na linguagem IPL (programa Logic Theorist)                     |
| 1958                 | John McCarthy cria o Lisp, uma linguagem construída em torno de listas                                      |
| Décadas seguintes    | A lista encadeada vira uma estrutura fundamental em todos os cursos de computação                          |

Já o **jogo** que vamos construir é uma simplificação de um tema clássico: *manobrar vagões em um pátio ferroviário* para deixá-los em uma certa ordem. Aqui, a regra é simples: **retire um vagão de qualquer ponto do trem e encaixe-o em qualquer outro ponto**.

#### Por que usar lista, e não vetor?

Em um vetor, **abrir espaço** em uma posição obriga todos os elementos seguintes a andar uma casa. Em uma lista, basta religar **dois ponteiros**. Veja o tamanho da diferença quando inserimos elementos sempre no começo:

```
Inserir n elementos no começo de um VETOR:   0 + 1 + 2 + ... + (n-1) = n(n-1)/2 deslocamentos
Inserir n elementos no começo de uma LISTA:  2 religações de ponteiros por elemento = 2n
```

#### Comprovando em C

**Arquivo:** `lista_vs_vetor.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>

// Um nó da lista: guarda um valor e aponta para o próximo nó.
typedef struct No {
    int valor;
    struct No *prox;
} No;

// [FUNÇÃO]
// Insere n elementos, um por um, SEMPRE NO COMEÇO de um vetor.
// Para abrir espaço, todos os elementos que já estão lá precisam andar uma casa.
// Devolve quantas "andadas" (deslocamentos) foram necessárias.
unsigned long long deslocamentosNoVetor(int n) {
    int *v = (int *) malloc(n * sizeof(int));
    unsigned long long deslocamentos = 0;
    int i, j;

    for (i = 0; i < n; i++) {
        // já existem i elementos: cada um anda uma posição para a direita
        for (j = i; j > 0; j--) {
            v[j] = v[j - 1];
            deslocamentos++;
        }
        v[0] = i;   // finalmente o novo elemento entra na posição 0
    }

    free(v);
    return deslocamentos;
}

// [FUNÇÃO]
// Insere n elementos, um por um, SEMPRE NO COMEÇO de uma lista encadeada.
// Aqui ninguém "anda": basta religar dois ponteiros.
// Devolve quantos ponteiros foram religados no total.
unsigned long long religacoesNaLista(int n) {
    No *inicio = NULL;
    No *novo, *seguinte;
    unsigned long long religacoes = 0;
    int i;

    for (i = 0; i < n; i++) {
        novo = (No *) malloc(sizeof(No));
        novo->valor = i;
        novo->prox = inicio;   // 1ª religação: o novo aponta para o antigo primeiro
        inicio = novo;         // 2ª religação: o novo passa a ser o primeiro
        religacoes += 2;
    }

    // Libera os nós (guardando o próximo ANTES de dar free).
    while (inicio != NULL) {
        seguinte = inicio->prox;
        free(inicio);
        inicio = seguinte;
    }
    return religacoes;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    int tamanhos[] = {10, 100, 1000, 10000};   // quantidades de elementos a comparar
    int total = sizeof(tamanhos) / sizeof(tamanhos[0]);
    int i;

    printf("Elementos | Vetor (deslocamentos) | Lista (religacoes)\n");
    for (i = 0; i < total; i++) {
        printf(" %7d  | %21llu | %18llu\n",
               tamanhos[i],
               deslocamentosNoVetor(tamanhos[i]),
               religacoesNaLista(tamanhos[i]));
    }

    return 0;
}
```

**Saída esperada:**

```
Elementos | Vetor (deslocamentos) | Lista (religacoes)
      10  |                    45 |                 20
     100  |                  4950 |                200
    1000  |                499500 |               2000
   10000  |              49995000 |              20000
```

Com 10.000 elementos, o vetor faz quase **50 milhões** de deslocamentos; a lista, apenas **20 mil** religações (cerca de 2.500 vezes menos). E a diferença **cresce** a cada vez que `n` aumenta: o trabalho do vetor cresce com o **quadrado** de `n`; o da lista, de forma proporcional a `n`.

> **Mas atenção:** a lista não é melhor em tudo! Para ler o elemento da posição 5000, o vetor vai direto (`v[5000]`), enquanto a lista precisa andar 5000 nós. Cada estrutura tem a sua hora (veja a seção 7).

---

### Regras

Há `N` vagões numerados de `1` a `N`, engatados em **ordem embaralhada**. Para jogar, você escolhe:

- **de**: a posição atual do vagão que quer retirar;
- **para**: a posição em que ele deve ficar **depois** da jogada.

**Objetivo:** deixar o trem na ordem `1, 2, 3, ..., N`, com o **menor número de jogadas** possível.

```
Exemplo (de = 1, para = 5):

Antes:    Locomotiva -> [5] -> [2] -> [1] -> [3] -> [4] -> FIM
          (retira o vagão 5, da posição 1, e o encaixa na posição 5)
Depois:   Locomotiva -> [2] -> [1] -> [3] -> [4] -> [5] -> FIM
```

#### O mínimo de jogadas

O jogo calcula e mostra o **mínimo possível**. A ideia: vagões que **já estão em ordem relativa** (formam uma sequência crescente, mesmo com outros vagões no meio) **não precisam sair do lugar**; só os demais precisam de **uma jogada cada**.

```
mínimo = n - (tamanho da maior sequência crescente já existente)

Trem: 5 2 1 3 4   →  maior sequência crescente: 1 3 4  (tamanho 3)
mínimo = 5 - 3 = 2 jogadas
```

### Onde está a lista nisso?

O **trem é uma lista**:

| No jogo                                             | Na lista                  |
|-----------------------------------------------------|---------------------------|
| Vagão                                               | nó (`No`)                 |
| Engate entre dois vagões                            | ponteiro `prox`           |
| Locomotiva (a ponta do trem)                        | `l->inicio`               |
| Tirar um vagão de qualquer ponto                    | `removerPosicao`          |
| Encaixar o vagão em outro ponto                     | `inserirPosicao`          |
| Conferir se o trem está organizado                  | percorrer a lista inteira |
| Quantidade de vagões                                | `l->tamanho`              |

**Mover um vagão = um `removerPosicao` seguido de um `inserirPosicao`.** Em nenhum momento os demais vagões "andam": só mudam os engates.

> **Sendo honesto:** com no máximo 9 vagões, um vetor também daria conta do recado. A lista foi escolhida aqui porque o jogo é feito **só de** remover e inserir em pontos quaisquer, exatamente o que ela faz de melhor, e porque é um ótimo treino de ponteiros.

---

## 5. Programa completo

Salve como `organize_trem.c`.

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>   // malloc, free, rand, srand
#include <time.h>     // time (para a semente aleatória)

// Número máximo de vagões aceito pelo jogo.
#define MAX_VAGOES 9

/* =====================================================================
   PARTE 1 - A ESTRUTURA DE DADOS LISTA ENCADEADA
   ===================================================================== */

// Um nó (vagão): guarda um valor e aponta para o PRÓXIMO nó.
typedef struct No {
    int valor;            // o número pintado no vagão
    struct No *prox;      // próximo vagão (NULL se este for o último)
} No;

// A lista conhece apenas o primeiro nó e quantos nós existem.
typedef struct {
    No *inicio;           // primeiro nó (NULL = lista vazia)
    int tamanho;          // quantidade de nós
} Lista;

// Deixa a lista vazia e pronta para uso.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void inicializar(Lista *l) {
    l->inicio = NULL;
    l->tamanho = 0;
}

// Devolve 1 se não há nenhum nó.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int vazia(Lista *l) {
    return l->inicio == NULL;
}

// Insere "valor" de modo que ele passe a ocupar a posição "pos".
// Posições contam a partir do 0: pos = 0 é o início; pos = tamanho é o fim.
// Devolve 1 se conseguiu, 0 se a posição é inválida ou faltou memória.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirPosicao(Lista *l, int pos, int valor) {
    No *novo, *anterior;
    int i;

    if (pos < 0 || pos > l->tamanho) return 0;       // posição inexistente

    novo = (No *) malloc(sizeof(No));                // reserva memória para o nó
    if (novo == NULL) return 0;                      // malloc falhou: sem memória
    novo->valor = valor;

    if (pos == 0) {
        // Caso especial: entrar no começo muda o "inicio" da lista.
        novo->prox = l->inicio;       // o novo aponta para o antigo primeiro
        l->inicio = novo;             // o novo passa a ser o primeiro
    } else {
        // Anda até o nó que ficará IMEDIATAMENTE ANTES do novo (posição pos-1).
        anterior = l->inicio;
        for (i = 0; i < pos - 1; i++) {
            anterior = anterior->prox;
        }
        novo->prox = anterior->prox;  // 1º) o novo aponta para quem vinha depois
        anterior->prox = novo;        // 2º) o anterior aponta para o novo
    }
    l->tamanho++;
    return 1;
}

// Atalho: insere no começo.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirInicio(Lista *l, int valor) {
    return inserirPosicao(l, 0, valor);
}

// Atalho: insere no fim.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirFim(Lista *l, int valor) {
    return inserirPosicao(l, l->tamanho, valor);
}

// Remove o nó da posição "pos" e entrega o valor em "*valor".
// Devolve 1 se conseguiu, 0 se a posição não existe.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int removerPosicao(Lista *l, int pos, int *valor) {
    No *anterior, *removido;
    int i;

    if (pos < 0 || pos >= l->tamanho) return 0;     // posição inexistente

    if (pos == 0) {
        removido = l->inicio;
        l->inicio = removido->prox;                 // o segundo vira o primeiro
    } else {
        anterior = l->inicio;
        for (i = 0; i < pos - 1; i++) {
            anterior = anterior->prox;
        }
        removido = anterior->prox;                  // o nó a ser retirado
        anterior->prox = removido->prox;            // "pula" o removido
    }
    *valor = removido->valor;   // guarda o valor ANTES de liberar o nó
    free(removido);             // devolve a memória (evita vazamento!)
    l->tamanho--;
    return 1;
}

// Lê o valor da posição "pos" sem remover nada.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int consultarPosicao(Lista *l, int pos, int *valor) {
    No *p;
    int i;

    if (pos < 0 || pos >= l->tamanho) return 0;
    p = l->inicio;
    for (i = 0; i < pos; i++) {
        p = p->prox;            // uma lista NÃO tem acesso direto: é preciso andar
    }
    *valor = p->valor;
    return 1;
}

// Libera todos os nós restantes. Chame antes de encerrar o programa.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void liberar(Lista *l) {
    No *atual = l->inicio;
    No *seguinte;

    while (atual != NULL) {
        seguinte = atual->prox;   // guarda o próximo ANTES de liberar o atual
        free(atual);
        atual = seguinte;
    }
    l->inicio = NULL;
    l->tamanho = 0;
}

/* =====================================================================
   PARTE 2 - A LÓGICA DO JOGO
   ===================================================================== */

// Monta um trem com os vagões 1..n em ordem aleatória.
// [FUNÇÃO]
void embaralhar(Lista *trem, int n) {
    int v[MAX_VAGOES];
    int i, j, tmp;

    liberar(trem);                         // descarta o trem anterior, se houver
    for (i = 0; i < n; i++) v[i] = i + 1;  // v = 1, 2, ..., n

    // Embaralhamento de Fisher-Yates: sorteia um índice e troca.
    for (i = n - 1; i > 0; i--) {
        j = rand() % (i + 1);
        tmp = v[i]; v[i] = v[j]; v[j] = tmp;
    }

    for (i = 0; i < n; i++) inserirFim(trem, v[i]);
}

// Devolve 1 se os vagões estão na ordem 1, 2, 3, ..., n.
// [FUNÇÃO]
int ordenado(Lista *trem) {
    No *p;
    int esperado = 1;

    // Percorrer uma lista: começa no primeiro e segue "prox" até chegar em NULL.
    for (p = trem->inicio; p != NULL; p = p->prox) {
        if (p->valor != esperado) return 0;
        esperado++;
    }
    return 1;
}

// Desenha o trem no terminal.
// [FUNÇÃO]
void mostrar(Lista *trem) {
    No *p;
    int i;

    printf("\nLocomotiva -> ");
    for (p = trem->inicio; p != NULL; p = p->prox) {
        printf("[%d] -> ", p->valor);
    }
    printf("FIM\n");

    printf("Posicao:       ");
    for (i = 1; i <= trem->tamanho; i++) {
        printf("%d      ", i);
    }
    printf("\n\n");
}

// Calcula o MÍNIMO de jogadas possível: n menos o tamanho da maior
// sequência crescente já existente no trem (esses vagões nem precisam sair do lugar).
// [FUNÇÃO]
int minimoJogadas(Lista *trem) {
    int v[MAX_VAGOES];      // cópia do trem em um vetor
    int melhor[MAX_VAGOES]; // melhor[i] = maior sequência crescente que termina em i
    int n = 0, maior = 0, i, j;
    No *p;

    for (p = trem->inicio; p != NULL; p = p->prox) v[n++] = p->valor;

    for (i = 0; i < n; i++) {
        melhor[i] = 1;
        for (j = 0; j < i; j++) {
            if (v[j] < v[i] && melhor[j] + 1 > melhor[i]) melhor[i] = melhor[j] + 1;
        }
        if (melhor[i] > maior) maior = melhor[i];
    }
    return n - maior;
}

/* =====================================================================
   PARTE 3 - PROGRAMA PRINCIPAL
   ===================================================================== */

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Lista trem;           // o trem é uma lista encadeada
    int n;                // quantidade de vagões
    unsigned semente;     // semente do embaralhamento (0 = aleatória)
    int de, para;         // posições digitadas pelo jogador (1 a n)
    int vagao;            // vagão retirado
    int jogadas = 0;      // contador de jogadas
    int minimo;           // mínimo de jogadas possível

    printf("=== ORGANIZE O TREM ===\n");
    printf("Quantos vagoes (3 a %d)? ", MAX_VAGOES);
    // scanf devolve quantos valores leu; se não leu 1, a entrada é inválida.
    if (scanf("%d", &n) != 1 || n < 3 || n > MAX_VAGOES) {
        printf("Valor invalido.\n");
        return 1;
    }

    printf("Semente do embaralhamento (0 = aleatoria)? ");
    if (scanf("%u", &semente) != 1) {
        printf("Valor invalido.\n");
        return 1;
    }
    if (semente == 0) semente = (unsigned) time(NULL);
    srand(semente);

    inicializar(&trem);
    // Embaralha até sair um trem que ainda NÃO esteja organizado.
    do {
        embaralhar(&trem, n);
    } while (ordenado(&trem));

    minimo = minimoJogadas(&trem);
    printf("\nObjetivo: deixar os vagoes na ordem 1, 2, 3, ..., %d\n", n);
    printf("A cada jogada voce tira UM vagao de uma posicao e o encaixa em outra.\n");
    printf("Minimo possivel neste trem: %d jogada(s).\n", minimo);

    while (!ordenado(&trem)) {
        mostrar(&trem);
        printf("Jogada %d - de para (0 0 para sair): ", jogadas + 1);
        if (scanf("%d %d", &de, &para) != 2) {
            printf("Entrada invalida.\n");
            liberar(&trem);
            return 1;
        }
        if (de == 0 && para == 0) {          // jogador desistiu
            printf("Ate a proxima!\n");
            liberar(&trem);
            return 0;
        }
        if (de < 1 || de > n || para < 1 || para > n || de == para) {
            printf(">> Escolha posicoes diferentes entre 1 e %d.\n", n);
            continue;                        // volta ao início do while
        }

        // O jogador digita 1..n, mas a lista conta a partir de 0: por isso "- 1".
        // Mover um vagão = REMOVER de uma posição + INSERIR em outra.
        removerPosicao(&trem, de - 1, &vagao);
        inserirPosicao(&trem, para - 1, vagao);
        jogadas++;
    }

    // Saiu do while: o trem está organizado.
    mostrar(&trem);
    printf("Parabens! Voce organizou o trem em %d jogadas.\n", jogadas);
    printf("O minimo possivel era %d.\n", minimo);

    liberar(&trem);   // devolve toda a memória
    return 0;
}
```

### Compilar e executar

```
gcc organize_trem.c -o organize_trem
./organize_trem
```

### Exemplo de execução

Com a semente `1`, o embaralhamento é sempre o mesmo, o que facilita reproduzir o exemplo. (O resultado pode variar entre sistemas operacionais, pois depende da implementação de `rand`.)

```
=== ORGANIZE O TREM ===
Quantos vagoes (3 a 9)? 5
Semente do embaralhamento (0 = aleatoria)? 1

Objetivo: deixar os vagoes na ordem 1, 2, 3, ..., 5
A cada jogada voce tira UM vagao de uma posicao e o encaixa em outra.
Minimo possivel neste trem: 2 jogada(s).

Locomotiva -> [5] -> [2] -> [1] -> [3] -> [4] -> FIM
Posicao:       1      2      3      4      5

Jogada 1 - de para (0 0 para sair): 3 3
>> Escolha posicoes diferentes entre 1 e 5.

Locomotiva -> [5] -> [2] -> [1] -> [3] -> [4] -> FIM
Posicao:       1      2      3      4      5

Jogada 1 - de para (0 0 para sair): 1 5

Locomotiva -> [2] -> [1] -> [3] -> [4] -> [5] -> FIM
Posicao:       1      2      3      4      5

Jogada 2 - de para (0 0 para sair): 2 1

Locomotiva -> [1] -> [2] -> [3] -> [4] -> [5] -> FIM
Posicao:       1      2      3      4      5

Parabens! Voce organizou o trem em 2 jogadas.
O minimo possivel era 2.
```

---

## 6. Entendendo o código por partes

### 6.1 Criando um nó

```
No *novo = (No *) malloc(sizeof(No));   // reserva memória para UM nó
if (novo == NULL) return 0;             // malloc pode falhar: sempre confira!
novo->valor = valor;                    // preenche o valor
```

`sizeof(No)` diz quantos bytes um nó ocupa. O `(No *)` converte o endereço devolvido pelo `malloc` para "ponteiro para No". Ainda falta preencher `novo->prox`: isso depende de **onde** o nó vai entrar.

### 6.2 Percorrendo a lista

```
for (p = trem->inicio; p != NULL; p = p->prox) {
    printf("[%d] -> ", p->valor);
}
```

Este `for` é **o padrão mais importante** das listas. Leia assim: "comece no primeiro nó; enquanto não chegou em `NULL`; passe para o próximo". Ele aparece em `mostrar`, `ordenado`, `minimoJogadas` e `liberar`.

### 6.3 Inserindo no meio: a ordem importa!

```
novo->prox = anterior->prox;   // 1º) o novo aponta para quem vinha depois
anterior->prox = novo;         // 2º) o anterior aponta para o novo
```

```
Antes:      anterior ──▶ [X] ──▶ ...
1º passo:   novo ──▶ [X]            (o novo "agarra" o resto da lista)
2º passo:   anterior ──▶ novo ──▶ [X]
```

Se trocarmos a ordem, **perdemos o resto da lista**:

```
anterior->prox = novo;         // ERRADO: agora ninguém aponta para o antigo "anterior->prox"
novo->prox = anterior->prox;   // e o novo acaba apontando para si mesmo!
```

Regra de ouro: **primeiro prenda o novo no que vem depois; só então solte o engate antigo.**

### 6.4 Removendo do meio

```
removido = anterior->prox;         // o nó a ser retirado
anterior->prox = removido->prox;   // o anterior "pula" o removido
*valor = removido->valor;          // guarda o valor ANTES de liberar
free(removido);                    // só então devolve a memória
```

Depois de `free(removido)`, **não se pode mais usar** `removido->...`: a memória já não é nossa. Por isso o valor é copiado antes.

### 6.5 Liberando a lista inteira

```
while (atual != NULL) {
    seguinte = atual->prox;   // guarda o próximo ANTES de liberar o atual
    free(atual);
    atual = seguinte;
}
```

Se fizéssemos `free(atual)` e depois `atual = atual->prox`, estaríamos lendo memória que acabou de ser devolvida. Sempre **guarde o próximo antes**.

### 6.6 Mover um vagão

```
removerPosicao(&trem, de - 1, &vagao);   // tira o vagão da posição "de"
inserirPosicao(&trem, para - 1, vagao);  // encaixa-o de modo que fique na posição "para"
```

O jogador digita posições de 1 a `n`, e a lista conta a partir de 0, por isso o `- 1`. Como a remoção acontece **antes** da inserção, `para` já se refere ao trem **sem** o vagão retirado, e é nessa posição que ele reaparece.

### 6.7 Condição de vitória

```
while (!ordenado(&trem))
```

`ordenado` percorre o trem comparando cada vagão com o valor esperado (1, 2, 3...). Quando todos conferem, o jogo termina.

---

## 7. Bônus: qual estrutura usar?

### Comparando o custo das operações

| Operação                               | Vetor                    | Lista simples                    |
|----------------------------------------|--------------------------|----------------------------------|
| Ler o elemento da posição *i*          | 1 passo                  | até *n* passos                   |
| Inserir/remover no **início**          | até *n* deslocamentos    | **1** religação (poucos passos)  |
| Inserir/remover no **fim**             | 1 passo (se há espaço)   | *n* passos (precisa andar até o fim) |
| Inserir/remover no **meio**            | até *n* deslocamentos    | andar até lá + poucas religações |
| Buscar um valor                        | até *n* passos           | até *n* passos                   |

> **Regra prática:** se você precisa **acessar por posição** o tempo todo, use vetor. Se precisa **inserir e remover o tempo todo**, principalmente nas pontas ou em pontos que você já tem em mãos, use lista.

### Os três sabores, lado a lado

| Tipo      | Cada nó guarda...                  | Vantagem                                                    | Exercício |
|-----------|------------------------------------|-------------------------------------------------------------|:---------:|
| Simples   | valor + `prox`                     | Mais simples e econômica                                    | 1, 2 e 3  |
| Dupla     | valor + `ant` + `prox`             | Anda nos **dois sentidos** e remove um nó sem procurar o anterior | 4  |
| Circular  | valor + `prox` (o último aponta para o primeiro) | Ideal para ciclos: playlists, rodízios, "dar a volta"  | 5  |

> **Curiosidade:** nos materiais sobre **pilhas** e **filas**, usamos vetores, mas ambas podem ser construídas com listas. A **pilha** é uma lista em que só se mexe no **início** (`inserirInicio` = `push`; `removerPosicao(0)` = `pop`). A **fila** é uma lista em que se insere no **fim** e se remove do **início**, e por isso convém guardar também um ponteiro para o último nó, exatamente como no exercício 4 do material de filas.

> **Dica de desempenho:** nossa `inserirFim` precisa andar a lista toda até o último nó. Quando inserir no fim é frequente, guarde também um ponteiro `fim` dentro da struct `Lista` (como faz a lista dupla do exercício 4).

---

## 8. Erros comuns de iniciantes

| Erro                                        | Por que acontece                                           | Como evitar                                                      |
|---------------------------------------------|------------------------------------------------------------|------------------------------------------------------------------|
| Esquecer o `inicializar`                    | `inicio` e `tamanho` ficam com lixo de memória             | Sempre chamar `inicializar` antes de usar                        |
| Esquecer o `free`                           | A memória nunca é devolvida (**vazamento**)                | Chamar `liberar` antes de encerrar; todo `malloc` tem um `free`  |
| Usar o nó depois do `free`                  | A memória já foi devolvida; o valor pode ser lixo ou travar | Copiar o que precisa **antes** de liberar                        |
| Soltar o engate antes de prender o novo     | Perde-se o resto da lista                                  | Primeiro `novo->prox = ...`, depois `anterior->prox = novo`      |
| Não tratar a posição 0                      | O primeiro nó não tem anterior                             | Tratar `pos == 0` à parte (muda `l->inicio`)                     |
| Percorrer com `p->prox` sem conferir `NULL` | No último nó, `p->prox` é `NULL`; acessar `NULL->...` trava o programa | Sempre testar `p != NULL` no laço                    |
| Esquecer de preencher `novo->prox`          | O ponteiro fica com lixo e a lista "aponta para o nada"    | Todo nó novo precisa ter `prox` definido antes de entrar na lista |
| Esquecer de atualizar `tamanho`             | `tamanho` deixa de refletir a realidade e as validações falham | Incrementar/decrementar em **toda** inserção/remoção          |
| Não conferir o retorno do `malloc`          | Sem memória, `malloc` devolve `NULL` e o programa trava    | Testar `novo == NULL`                                            |
| Passar `Lista` sem ponteiro                 | Altera só uma cópia                                        | Usar `Lista *l`                                                  |

---

## 9. Exercícios resolvidos

Cada exercício abaixo é um programa **completo**, já comentado. Salve em um arquivo `.c`, compile com `gcc` e execute.

> **Sugestão didática:** tente resolver sozinho antes de olhar a resposta.

---

### Exercício 1 - Teste a lista sozinha

**Enunciado:** crie um `main` que insere 10, 20 e 30 no fim, depois insere um valor no início e outro no meio, remove dois elementos de posições diferentes e mostra a lista a cada passo.

**Arquivo:** `exercicio1_lista.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>   // malloc e free

typedef struct No {
    int valor;
    struct No *prox;
} No;

typedef struct {
    No *inicio;
    int tamanho;
} Lista;

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void inicializar(Lista *l) { l->inicio = NULL; l->tamanho = 0; }

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirPosicao(Lista *l, int pos, int valor) {
    No *novo, *anterior;
    int i;
    if (pos < 0 || pos > l->tamanho) return 0;
    novo = (No *) malloc(sizeof(No));
    if (novo == NULL) return 0;
    novo->valor = valor;
    if (pos == 0) {
        novo->prox = l->inicio;
        l->inicio = novo;
    } else {
        anterior = l->inicio;
        for (i = 0; i < pos - 1; i++) anterior = anterior->prox;
        novo->prox = anterior->prox;   // 1º) o novo aponta para quem vinha depois
        anterior->prox = novo;         // 2º) o anterior aponta para o novo
    }
    l->tamanho++;
    return 1;
}

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int removerPosicao(Lista *l, int pos, int *valor) {
    No *anterior, *removido;
    int i;
    if (pos < 0 || pos >= l->tamanho) return 0;
    if (pos == 0) {
        removido = l->inicio;
        l->inicio = removido->prox;
    } else {
        anterior = l->inicio;
        for (i = 0; i < pos - 1; i++) anterior = anterior->prox;
        removido = anterior->prox;
        anterior->prox = removido->prox;
    }
    *valor = removido->valor;
    free(removido);
    l->tamanho--;
    return 1;
}

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void liberar(Lista *l) {
    No *atual = l->inicio, *seguinte;
    while (atual != NULL) {
        seguinte = atual->prox;
        free(atual);
        atual = seguinte;
    }
    l->inicio = NULL;
    l->tamanho = 0;
}

// Mostra a lista da primeira posição até a última.
// [FUNÇÃO]
void mostrar(Lista *l) {
    No *p;
    printf("Lista (tamanho %d):", l->tamanho);
    for (p = l->inicio; p != NULL; p = p->prox) {
        printf(" %d", p->valor);
    }
    printf("\n");
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Lista l;
    int valor;

    inicializar(&l);   // sempre inicialize antes de usar!

    // Insere 10, 20 e 30 no fim: cada um entra na posição "tamanho".
    inserirPosicao(&l, l.tamanho, 10);
    inserirPosicao(&l, l.tamanho, 20);
    inserirPosicao(&l, l.tamanho, 30);
    mostrar(&l);

    inserirPosicao(&l, 0, 5);     // no início
    mostrar(&l);

    inserirPosicao(&l, 2, 15);    // no meio: passa a ser o elemento da posição 2
    mostrar(&l);

    // Remove o primeiro (posição 0).
    if (removerPosicao(&l, 0, &valor)) printf("Removeu da posicao 0: %d\n", valor);
    mostrar(&l);

    // Remove o da posição 2.
    if (removerPosicao(&l, 2, &valor)) printf("Removeu da posicao 2: %d\n", valor);
    mostrar(&l);

    // Tenta remover uma posição que não existe.
    if (!removerPosicao(&l, 10, &valor)) printf("Posicao 10 nao existe!\n");

    liberar(&l);   // devolve toda a memória
    return 0;
}
```

**Saída esperada:**

```
Lista (tamanho 3): 10 20 30
Lista (tamanho 4): 5 10 20 30
Lista (tamanho 5): 5 10 15 20 30
Removeu da posicao 0: 5
Lista (tamanho 4): 10 15 20 30
Removeu da posicao 2: 20
Lista (tamanho 3): 10 15 30
Posicao 10 nao existe!
```

---

### Exercício 2 - Lista ordenada (inserção e busca)

**Enunciado:** em vez de ordenar a lista depois, insira cada valor **já no lugar certo**, de modo que a lista esteja **sempre em ordem crescente**. Depois, implemente uma busca que **aproveita a ordenação** para parar mais cedo.

**Ideia:** ao inserir, andamos até achar o último nó que seja **menor ou igual** ao novo valor e encaixamos o novo logo depois. Ao buscar, **paramos assim que passamos** do valor procurado: se já encontramos um número maior, o valor não está na lista, e não adianta olhar o resto.

**Arquivo:** `exercicio2_ordenada.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int valor;
    struct No *prox;
} No;

typedef struct {
    No *inicio;
    int tamanho;
} Lista;

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void inicializar(Lista *l) { l->inicio = NULL; l->tamanho = 0; }

// Insere "valor" no lugar certo para a lista continuar em ordem crescente.
// Devolve 1 se conseguiu, 0 se faltou memória.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirOrdenado(Lista *l, int valor) {
    No *novo = (No *) malloc(sizeof(No));
    No *anterior;

    if (novo == NULL) return 0;
    novo->valor = valor;

    // Caso 1: a lista está vazia OU o novo valor é menor que o primeiro.
    // Ele vira o novo primeiro nó.
    if (l->inicio == NULL || valor < l->inicio->valor) {
        novo->prox = l->inicio;
        l->inicio = novo;
    }
    // Caso 2: anda até o último nó que seja MENOR OU IGUAL ao novo valor
    // e encaixa o novo logo depois dele.
    else {
        anterior = l->inicio;
        while (anterior->prox != NULL && anterior->prox->valor <= valor) {
            anterior = anterior->prox;
        }
        novo->prox = anterior->prox;   // 1º) o novo aponta para o maior que vem depois
        anterior->prox = novo;         // 2º) o anterior aponta para o novo
    }
    l->tamanho++;
    return 1;
}

// Procura "valor" e devolve a posição (a partir de 0), ou -1 se não achar.
// Como a lista está ORDENADA, podemos parar assim que passarmos do valor.
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int buscar(Lista *l, int valor) {
    No *p = l->inicio;
    int pos = 0;

    while (p != NULL && p->valor < valor) {   // ainda não chegamos no valor
        p = p->prox;
        pos++;
    }
    if (p != NULL && p->valor == valor) return pos;   // achou!
    return -1;                                        // acabou a lista, ou passou do ponto
}

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void liberar(Lista *l) {
    No *atual = l->inicio, *seguinte;
    while (atual != NULL) {
        seguinte = atual->prox;
        free(atual);
        atual = seguinte;
    }
    l->inicio = NULL;
    l->tamanho = 0;
}

// [FUNÇÃO]
void mostrar(Lista *l) {
    No *p;
    printf("Lista:");
    for (p = l->inicio; p != NULL; p = p->prox) printf(" %d", p->valor);
    printf("\n");
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Lista l;
    int chegada[] = {40, 10, 30, 20, 50, 10};   // valores em ordem bagunçada
    int total = sizeof(chegada) / sizeof(chegada[0]);
    int procurados[] = {30, 10, 35, 60, 5};
    int i, pos;

    inicializar(&l);

    for (i = 0; i < total; i++) {
        inserirOrdenado(&l, chegada[i]);
        printf("Inseriu %2d -> ", chegada[i]);
        mostrar(&l);
    }

    printf("\n");
    for (i = 0; i < 5; i++) {
        pos = buscar(&l, procurados[i]);
        if (pos >= 0) printf("Valor %2d encontrado na posicao %d\n", procurados[i], pos);
        else          printf("Valor %2d NAO esta na lista\n", procurados[i]);
    }

    liberar(&l);
    return 0;
}
```

**Saída esperada:**

```
Inseriu 40 -> Lista: 40
Inseriu 10 -> Lista: 10 40
Inseriu 30 -> Lista: 10 30 40
Inseriu 20 -> Lista: 10 20 30 40
Inseriu 50 -> Lista: 10 20 30 40 50
Inseriu 10 -> Lista: 10 10 20 30 40 50

Valor 30 encontrado na posicao 3
Valor 10 encontrado na posicao 0
Valor 35 NAO esta na lista
Valor 60 NAO esta na lista
Valor  5 NAO esta na lista
```

> **Observe:** o valor `10` apareceu duas vezes e a busca devolveu a **primeira** ocorrência (posição 0). Já `60` e `35` nem existem; para o `5`, a busca parou **logo no primeiro nó**.

---

### Exercício 3 - Inverter a lista

**Enunciado:** escreva uma função que **inverte a ordem** dos nós da lista, **sem criar nós novos**: apenas religando os ponteiros.

**Ideia:** percorremos a lista com **três ponteiros** (`anterior`, `atual` e `seguinte`). A cada passo, viramos a setinha do nó atual **para trás**.

```
Antes:      [1] → [2] → [3] → NULL

passo 1:    NULL ← [1]    [2] → [3] → NULL
passo 2:    NULL ← [1] ← [2]    [3] → NULL
passo 3:    NULL ← [1] ← [2] ← [3]

No fim, o antigo último ([3]) vira o primeiro:    [3] → [2] → [1] → NULL
```

A linha mais importante é `seguinte = atual->prox;`: ela **guarda o resto da lista** antes de virarmos a seta; sem ela, o resto seria perdido.

**Arquivo:** `exercicio3_inverter.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>

typedef struct No {
    int valor;
    struct No *prox;
} No;

typedef struct {
    No *inicio;
    int tamanho;
} Lista;

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void inicializar(Lista *l) { l->inicio = NULL; l->tamanho = 0; }

// Insere sempre no início (é o jeito mais simples de montar uma lista).
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
int inserirInicio(Lista *l, int valor) {
    No *novo = (No *) malloc(sizeof(No));
    if (novo == NULL) return 0;
    novo->valor = valor;
    novo->prox = l->inicio;
    l->inicio = novo;
    l->tamanho++;
    return 1;
}

// Inverte a lista "no lugar": não cria nós novos, só religa os ponteiros.
// Usa três ponteiros que andam juntos pela lista:
//
//   anterior   atual   seguinte
//      ↓         ↓        ↓
//    NULL  ←  [ 1 ]  →  [ 2 ]  →  [ 3 ]  →  NULL
//
// A cada passo, "atual" passa a apontar para trás (para "anterior").
// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void inverter(Lista *l) {
    No *anterior = NULL;       // começa em NULL: o antigo primeiro será o último
    No *atual = l->inicio;
    No *seguinte;

    while (atual != NULL) {
        seguinte = atual->prox;   // 1º) GUARDA o próximo, senão perdemos o resto da lista!
        atual->prox = anterior;   // 2º) vira a seta para trás
        anterior = atual;         // 3º) anda com "anterior"
        atual = seguinte;         // 4º) anda com "atual"
    }
    l->inicio = anterior;         // o último nó visitado é o novo primeiro
}

// [MÉTODO da Lista] (em C é uma função que recebe a lista)
void liberar(Lista *l) {
    No *atual = l->inicio, *seguinte;
    while (atual != NULL) {
        seguinte = atual->prox;
        free(atual);
        atual = seguinte;
    }
    l->inicio = NULL;
    l->tamanho = 0;
}

// [FUNÇÃO]
void mostrar(const char *titulo, Lista *l) {
    No *p;
    printf("%s", titulo);
    for (p = l->inicio; p != NULL; p = p->prox) printf(" %d", p->valor);
    printf("\n");
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Lista l;
    int i;

    inicializar(&l);
    for (i = 5; i >= 1; i--) {
        inserirInicio(&l, i);      // como entra sempre no início, a lista fica 1 2 3 4 5
    }

    mostrar("Original:", &l);
    inverter(&l);
    mostrar("Invertida:", &l);
    inverter(&l);                  // inverter duas vezes devolve a lista original
    mostrar("De novo:  ", &l);

    liberar(&l);
    return 0;
}
```

**Saída esperada:**

```
Original: 1 2 3 4 5
Invertida: 5 4 3 2 1
De novo:   1 2 3 4 5
```

---

### Exercício 4 - Lista duplamente encadeada

**Enunciado:** implemente uma lista em que cada nó conhece o **anterior** e o **próximo**, e use-a para percorrer nos **dois sentidos** e **remover um nó que já temos em mãos**, sem percorrer a lista de novo.

**Ideia:** além de `prox`, cada nó guarda `ant`; a lista guarda também o ponteiro `fim`.

```
        ┌──────────────────────────────────────────┐
 NULL ⇄ │ 0 │ ⇄ │ 1 │ ⇄ │ 2 │ ⇄ │ 3 │ ⇄ │ 4 │ ⇄ NULL
        └──────────────────────────────────────────┘
     inicio                                     fim
```

Para remover um nó `no`, religamos os dois vizinhos entre si. Os casos das **pontas** (sem anterior ou sem próximo) precisam de cuidado extra, pois quem muda ali é `inicio` ou `fim`.

**Arquivo:** `exercicio4_lista_dupla.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>

// Nó da lista DUPLA: aponta para o anterior E para o próximo.
typedef struct NoD {
    int valor;
    struct NoD *ant;    // nó de trás (NULL se for o primeiro)
    struct NoD *prox;   // nó da frente (NULL se for o último)
} NoD;

// A lista dupla conhece as DUAS pontas.
typedef struct {
    NoD *inicio;
    NoD *fim;
    int tamanho;
} ListaDupla;

// [MÉTODO da ListaDupla] (em C é uma função que recebe a lista)
void inicializar(ListaDupla *l) {
    l->inicio = NULL;
    l->fim = NULL;
    l->tamanho = 0;
}

// Insere no fim. Como temos o ponteiro "fim", não precisa percorrer nada.
// [MÉTODO da ListaDupla] (em C é uma função que recebe a lista)
int inserirFim(ListaDupla *l, int valor) {
    NoD *novo = (NoD *) malloc(sizeof(NoD));
    if (novo == NULL) return 0;
    novo->valor = valor;
    novo->prox = NULL;          // o novo será o último
    novo->ant = l->fim;         // o anterior do novo é o antigo último

    if (l->fim == NULL) {
        l->inicio = novo;       // lista vazia: o novo é também o primeiro
    } else {
        l->fim->prox = novo;    // o antigo último passa a apontar para o novo
    }
    l->fim = novo;
    l->tamanho++;
    return 1;
}

// Insere no início (espelho do inserirFim).
// [MÉTODO da ListaDupla] (em C é uma função que recebe a lista)
int inserirInicio(ListaDupla *l, int valor) {
    NoD *novo = (NoD *) malloc(sizeof(NoD));
    if (novo == NULL) return 0;
    novo->valor = valor;
    novo->ant = NULL;           // o novo será o primeiro
    novo->prox = l->inicio;

    if (l->inicio == NULL) {
        l->fim = novo;          // lista vazia: o novo é também o último
    } else {
        l->inicio->ant = novo;  // o antigo primeiro passa a apontar para trás, para o novo
    }
    l->inicio = novo;
    l->tamanho++;
    return 1;
}

// Procura um valor e devolve o NÓ (ou NULL se não achar).
// [MÉTODO da ListaDupla] (em C é uma função que recebe a lista)
NoD *buscar(ListaDupla *l, int valor) {
    NoD *p;
    for (p = l->inicio; p != NULL; p = p->prox) {
        if (p->valor == valor) return p;
    }
    return NULL;
}

// Remove UM NÓ QUE JÁ TEMOS EM MÃOS, sem percorrer a lista de novo.
// Isso só é possível porque o nó conhece o anterior (na lista simples, não conhece).
// [MÉTODO da ListaDupla] (em C é uma função que recebe a lista)
void removerNo(ListaDupla *l, NoD *no) {
    if (no->ant == NULL) {
        l->inicio = no->prox;       // era o primeiro: o segundo vira o primeiro
    } else {
        no->ant->prox = no->prox;   // o de trás "pula" o removido
    }

    if (no->prox == NULL) {
        l->fim = no->ant;           // era o último: o penúltimo vira o último
    } else {
        no->prox->ant = no->ant;    // o da frente "pula" o removido, olhando para trás
    }

    free(no);
    l->tamanho--;
}

// [MÉTODO da ListaDupla] (em C é uma função que recebe a lista)
void liberar(ListaDupla *l) {
    NoD *atual = l->inicio, *seguinte;
    while (atual != NULL) {
        seguinte = atual->prox;
        free(atual);
        atual = seguinte;
    }
    l->inicio = NULL;
    l->fim = NULL;
    l->tamanho = 0;
}

// Mostra a lista nos DOIS sentidos.
// [FUNÇÃO]
void mostrar(ListaDupla *l) {
    NoD *p;

    printf("  Da frente para tras:");
    for (p = l->inicio; p != NULL; p = p->prox) printf(" %d", p->valor);

    printf("\n  De tras para frente:");
    for (p = l->fim; p != NULL; p = p->ant) printf(" %d", p->valor);

    printf("\n");
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    ListaDupla l;
    NoD *no;
    int i;

    inicializar(&l);
    for (i = 1; i <= 5; i++) inserirFim(&l, i);   // 1 2 3 4 5
    inserirInicio(&l, 0);                         // 0 1 2 3 4 5
    printf("Lista inicial (%d elementos):\n", l.tamanho);
    mostrar(&l);

    // Remove o nó que contém 3: achamos o nó e o removemos direto.
    no = buscar(&l, 3);
    if (no != NULL) removerNo(&l, no);
    printf("\nDepois de remover o 3:\n");
    mostrar(&l);

    // Remove as duas pontas (casos especiais).
    removerNo(&l, l.inicio);
    removerNo(&l, l.fim);
    printf("\nDepois de remover o primeiro e o ultimo:\n");
    mostrar(&l);

    liberar(&l);
    return 0;
}
```

**Saída esperada:**

```
Lista inicial (6 elementos):
  Da frente para tras: 0 1 2 3 4 5
  De tras para frente: 5 4 3 2 1 0

Depois de remover o 3:
  Da frente para tras: 0 1 2 4 5
  De tras para frente: 5 4 2 1 0

Depois de remover o primeiro e o ultimo:
  Da frente para tras: 1 2 4
  De tras para frente: 4 2 1
```

---

### Exercício 5 - Playlist circular

**Enunciado:** simule uma **playlist que repete** quando chega ao fim, permitindo adicionar e remover músicas.

**Ideia:** use uma lista **circular**: o último nó aponta de volta para o primeiro. Guardamos o ponteiro para o **último** nó, pois assim o primeiro é sempre `ultimo->prox`, e inserir no fim fica rápido.

```
            ultimo
              │
              ▼
[Bossa] → [Samba] → [Forro] → [Choro] ─┐
   ▲                                    │
   └────────────────────────────────────┘
```

Atenção a duas armadilhas da lista circular:

1. **Não existe `NULL` para parar o laço**: percorra no máximo `tamanho` nós (ou até voltar ao ponto de partida), senão o programa **nunca termina**.
2. Se removermos justamente a música que está tocando, o ponteiro `atual` ficaria **apontando para um nó liberado**. Por isso, nesse caso, ele recua para o nó anterior.

**Arquivo:** `exercicio5_playlist.c`

```
// LEGENDA: [FUNÇÃO]  = função comum (faz um trabalho e pronto)
//          [MÉTODO]  = função que opera sobre a Lista (recebe a lista como 1º parâmetro)
//          Obs.: C não tem métodos de verdade (isso é de orientação a objetos, como em Java);
//          aqui usamos o nome "método" por analogia. Em C, tudo é função.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>   // strcpy, strcmp

// Nó da lista CIRCULAR: o último nó aponta de volta para o primeiro.
typedef struct NoC {
    char nome[40];
    struct NoC *prox;
} NoC;

// Guardamos o ÚLTIMO nó (e não o primeiro): assim o primeiro é "ultimo->prox",
// e tanto inserir no fim quanto chegar no começo são operações rápidas.
typedef struct {
    NoC *ultimo;    // NULL = playlist vazia
    NoC *atual;     // música que está tocando (NULL = ainda não começou)
    int tamanho;
} Playlist;

// [MÉTODO da Playlist] (em C é uma função que recebe a playlist)
void inicializar(Playlist *p) {
    p->ultimo = NULL;
    p->atual = NULL;
    p->tamanho = 0;
}

// Adiciona uma música no fim da playlist.
// [MÉTODO da Playlist] (em C é uma função que recebe a playlist)
int adicionar(Playlist *p, const char *nome) {
    NoC *novo = (NoC *) malloc(sizeof(NoC));
    if (novo == NULL) return 0;
    strcpy(novo->nome, nome);

    if (p->ultimo == NULL) {
        novo->prox = novo;              // único nó: aponta para si mesmo
    } else {
        novo->prox = p->ultimo->prox;   // o novo aponta para o primeiro
        p->ultimo->prox = novo;         // o antigo último aponta para o novo
    }
    p->ultimo = novo;                   // o novo é o último
    p->tamanho++;
    return 1;
}

// Passa para a próxima música e devolve o nome dela (NULL se a playlist está vazia).
// Depois da última, volta sozinha para a primeira: é a graça da lista circular!
// [MÉTODO da Playlist] (em C é uma função que recebe a playlist)
const char *tocarProxima(Playlist *p) {
    if (p->ultimo == NULL) return NULL;
    if (p->atual == NULL) {
        p->atual = p->ultimo->prox;     // começa pela primeira
    } else {
        p->atual = p->atual->prox;      // anda uma; na última, "prox" já é a primeira
    }
    return p->atual->nome;
}

// Remove a música com o nome dado. Devolve 1 se achou e removeu, 0 se não achou.
// [MÉTODO da Playlist] (em C é uma função que recebe a playlist)
int remover(Playlist *p, const char *nome) {
    NoC *anterior, *atual;
    int i;

    if (p->ultimo == NULL) return 0;

    anterior = p->ultimo;               // o anterior do primeiro é o último
    atual = p->ultimo->prox;            // começa no primeiro
    for (i = 0; i < p->tamanho; i++) {  // no máximo uma volta completa
        if (strcmp(atual->nome, nome) == 0) {
            if (p->tamanho == 1) {
                p->ultimo = NULL;       // era a única música: a playlist esvazia
                p->atual = NULL;
            } else {
                anterior->prox = atual->prox;           // "pula" o removido
                if (atual == p->ultimo) p->ultimo = anterior;
                if (atual == p->atual)  p->atual = anterior;   // evita ponteiro "solto"
            }
            free(atual);
            p->tamanho--;
            return 1;
        }
        anterior = atual;
        atual = atual->prox;
    }
    return 0;   // deu a volta inteira e não achou
}

// [MÉTODO da Playlist] (em C é uma função que recebe a playlist)
void liberar(Playlist *p) {
    NoC *atual, *seguinte;
    int i, total = p->tamanho;

    if (p->ultimo == NULL) return;
    atual = p->ultimo->prox;            // começa no primeiro
    for (i = 0; i < total; i++) {       // listas circulares NÃO terminam em NULL!
        seguinte = atual->prox;
        free(atual);
        atual = seguinte;
    }
    p->ultimo = NULL;
    p->atual = NULL;
    p->tamanho = 0;
}

// Toca "quantas" músicas em sequência.
// [FUNÇÃO]
void tocar(Playlist *p, int quantas) {
    int i;
    for (i = 1; i <= quantas; i++) {
        printf("  %d. %s\n", i, tocarProxima(p));
    }
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main(void) {
    Playlist p;

    inicializar(&p);
    adicionar(&p, "Bossa do Cerrado");
    adicionar(&p, "Samba da Manha");
    adicionar(&p, "Forro do Sertao");
    adicionar(&p, "Choro da Tarde");

    printf("Tocando 6 musicas (a playlist tem %d, entao ela da a volta):\n", p.tamanho);
    tocar(&p, 6);

    printf("\nRemovendo \"Forro do Sertao\"...\n");
    if (remover(&p, "Forro do Sertao")) printf("Removida! Restam %d musicas.\n", p.tamanho);

    printf("\nMais 5 musicas:\n");
    tocar(&p, 5);

    printf("\nTentando remover algo que nao existe: %s\n",
           remover(&p, "Rock do Nada") ? "removeu" : "nao encontrada");

    liberar(&p);
    return 0;
}
```

**Saída esperada:**

```
Tocando 6 musicas (a playlist tem 4, entao ela da a volta):
  1. Bossa do Cerrado
  2. Samba da Manha
  3. Forro do Sertao
  4. Choro da Tarde
  5. Bossa do Cerrado
  6. Samba da Manha

Removendo "Forro do Sertao"...
Removida! Restam 3 musicas.

Mais 5 musicas:
  1. Choro da Tarde
  2. Bossa do Cerrado
  3. Samba da Manha
  4. Choro da Tarde
  5. Bossa do Cerrado

Tentando remover algo que nao existe: nao encontrada
```

---

## 10. Resumo

- Lista encadeada é uma sequência de **nós**; cada nó guarda **um valor** e **um ponteiro para o próximo**. O último aponta para `NULL`.
- Em C, cada nó é criado com **`malloc`** e destruído com **`free`**; a lista só precisa conhecer o **primeiro nó** (`inicio`) e, opcionalmente, o `tamanho`.
- Percorrer = `for (p = inicio; p != NULL; p = p->prox)`.
- Para **inserir** no meio: primeiro `novo->prox = anterior->prox`, depois `anterior->prox = novo`. Para **remover**: `anterior->prox = removido->prox` e então `free(removido)`.
- O **início** é um caso especial, pois não tem nó anterior: quem muda é `l->inicio`.
- Na lista, **inserir e remover** são baratos (só religam ponteiros); **acessar por posição** é caro (é preciso andar).
- No Organize o Trem, **o trem é uma lista**, e **mover um vagão é um `removerPosicao` seguido de um `inserirPosicao`**. O mínimo de jogadas é `n` menos o tamanho da maior sequência crescente já existente.
- As variantes **dupla** (anda nos dois sentidos) e **circular** (dá a volta) resolvem problemas que a lista simples resolve com mais esforço.
- Pilhas e filas podem ser vistas como **listas com regras de acesso**: a pilha mexe só em uma ponta; a fila, nas duas.
