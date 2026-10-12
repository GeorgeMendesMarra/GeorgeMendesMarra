# Pilhas em C++ com o jogo Torre de Hanói

> Material para quem **nunca viu** a estrutura de dados *pilha*.
> Ao final, você terá um jogo da Torre de Hanói funcionando no terminal, construído com uma **classe `Pilha`** em C++, além de cinco exercícios **resolvidos e comentados**.

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

## 3. Como representar uma pilha em C++

Em C++ podemos juntar **os dados** e **as operações** em um único lugar: uma **classe**. Os dados da pilha ficam **escondidos** dentro dela (`private`) e só podem ser mexidos pelas operações que a própria classe oferece (`public`). Essas operações são os **métodos**.

Por dentro, vamos usar um **vetor (array)** e uma variável chamada `topo` que guarda a posição do último elemento.

- `topo = -1` significa **pilha vazia**.
- Ao empilhar: `topo` aumenta e o valor é guardado nessa posição.
- Ao desempilhar: lemos `dados[topo]` e `topo` diminui.

```cpp
// Quantidade máxima de discos (e, portanto, capacidade de cada pilha).
// Em C++ preferimos "const" no lugar do "#define" do C.
const int MAX_DISCOS = 8;

// Uma classe agrupa DADOS (atributos) e OPERAÇÕES (métodos) em um só lugar.
class Pilha {
private:                     // só os métodos desta classe enxergam o que está aqui
    int dados[MAX_DISCOS];   // armazena os elementos da pilha
    int topo;                // índice do elemento do topo (-1 = pilha vazia)

public:                      // o que está aqui pode ser usado por qualquer parte do programa

    // [CONSTRUTOR]
    // Método especial: tem o MESMO NOME da classe, não tem tipo de retorno e é
    // chamado AUTOMATICAMENTE quando criamos uma Pilha. Por isso não existe mais
    // a função "inicializar" do C: a pilha já nasce vazia.
    Pilha() {
        topo = -1;
    }

    // [MÉTODO da classe Pilha]
    // true se não há elementos. O "const" no fim promete que o método
    // apenas CONSULTA a pilha, sem modificá-la.
    bool vazia() const {
        return topo == -1;
    }

    // [MÉTODO da classe Pilha]
    // true se o vetor está lotado (topo está na última posição possível).
    bool cheia() const {
        return topo == MAX_DISCOS - 1;
    }

    // [MÉTODO da classe Pilha]
    // PUSH: coloca "valor" no topo.
    // Devolve true se conseguiu, false se a pilha estava cheia.
    bool empilhar(int valor) {
        if (cheia()) return false;   // não há espaço: recusa
        topo++;                      // 1º) sobe o topo uma posição
        dados[topo] = valor;         // 2º) guarda o valor nessa posição
        return true;
    }

    // [MÉTODO da classe Pilha]
    // POP: remove o elemento do topo e o entrega em "valor".
    // "int &valor" é uma REFERÊNCIA: um apelido para a variável de quem chamou,
    // então alterar "valor" aqui altera a variável original.
    // Devolve true se conseguiu, false se a pilha estava vazia.
    bool desempilhar(int &valor) {
        if (vazia()) return false;   // não há o que remover
        valor = dados[topo];         // 1º) entrega o valor do topo
        topo--;                      // 2º) desce o topo
        return true;
    }

    // [MÉTODO da classe Pilha]
    // PEEK: igual ao pop, mas NÃO remove (o topo permanece onde está).
    bool consultarTopo(int &valor) const {
        if (vazia()) return false;
        valor = dados[topo];
        return true;
    }

    // [MÉTODO da classe Pilha]
    // Quantidade de elementos (lembre: topo é um ÍNDICE, então a quantidade é topo + 1).
    int tamanho() const {
        return topo + 1;
    }

    // [MÉTODO da classe Pilha]
    // Lê o elemento da posição "indice" (0 = base) sem alterar a pilha.
    // Será usado apenas para DESENHAR as torres.
    int obter(int indice) const {
        return dados[indice];
    }
};
```

### Como usar a classe

```cpp
Pilha p;              // cria uma pilha (o construtor roda sozinho e deixa o topo em -1)
p.empilhar(10);       // chama o método "empilhar" sobre o objeto p
int x;
p.desempilhar(x);     // x passa a valer 10
```

> **Dica:** como `Pilha` é um tipo, podemos criar várias pilhas independentes — e na Torre de Hanói precisaremos de **três**: `Pilha torres[3];`.

---

### Função ou método? (para quem vem de Java)

Nos códigos deste material, cada trecho vem marcado com uma etiqueta no comentário:

| Etiqueta | O que significa | Exemplos |
|---|---|---|
| `[CONSTRUTOR]` | Método especial, executado automaticamente ao **criar** o objeto | `Pilha()` |
| `[DESTRUTOR]` | Método especial, executado automaticamente ao **destruir** o objeto | `~PilhaDin()` |
| `[MÉTODO da classe Pilha]` | Função que **pertence a uma classe** e age sobre o objeto (via `this`) | `vazia`, `cheia`, `empilhar`, `desempilhar`, `consultarTopo` |
| `[FUNÇÃO]` | Função comum, **fora de qualquer classe** | `mover`, `desenhar`, `iniciar`, `casam`, `balanceada` |
| `[FUNÇÃO RECURSIVA]` | Função que chama a si mesma | `resolver`, `jogadasMinimasRecorrencia` |
| `[FUNÇÃO PRINCIPAL]` | Ponto de entrada do programa | `main` |

> **Diferença para o C:** em C só existem funções, e chamamos as operações da pilha de "métodos" apenas por analogia. Em **C++ os métodos são de verdade**: ficam dentro da `class` e recebem automaticamente o objeto sobre o qual atuam (o ponteiro `this`, igual ao do Java).

A chamada fica **idêntica à do Java**:

```
Java:   pilha.empilhar(10);        // método do objeto
C++:    pilha.empilhar(10);        // método do objeto (mesma sintaxe!)
C:      empilhar(&pilha, 10);      // função recebendo a pilha explicitamente
```

---

### O que muda do C para o C++

| Assunto | Em C | Em C++ |
|---|---|---|
| Entrada e saída | `printf` / `scanf` | `cout <<` / `cin >>` |
| Agrupar dados e operações | `struct` + funções soltas | `class` com atributos e métodos |
| Preparar a pilha para uso | função `inicializar` | **construtor** (automático) |
| Alterar variável de quem chamou | ponteiro (`Pilha *p`, `p->topo`) | **referência** (`Pilha &p`, `p.topo`) |
| Verdadeiro / falso | `int` com 0 e 1 | `bool` com `true` e `false` |
| Constantes | `#define MAX 8` | `const int MAX = 8;` |
| Memória dinâmica | `malloc` / `free` | `new` / `delete` (+ destrutor) |

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

#### Comprovando em C++

**Arquivo:** `hanoi_lenda.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>
#include <iomanip>   // setw, setprecision

using namespace std; // permite escrever "cout" em vez de "std::cout"

const double SEGUNDOS_POR_ANO = 31557600.0;   // 365,25 dias * 24 h * 3600 s

// [FUNÇÃO]
// Calcula o mínimo de jogadas para n discos usando a recorrência:
//   minimo(0) = 0
//   minimo(n) = 2 * minimo(n-1) + 1
// "unsigned long long" tem 64 bits e comporta exatamente 2^64 - 1,
// por isso o valor para 64 discos cabe sem estourar.
unsigned long long jogadasMinimas(int n) {
    unsigned long long movimentos = 0;   // com 0 discos não há o que mover
    for (int i = 1; i <= n; i++) {
        movimentos = 2 * movimentos + 1; // cada disco novo dobra o trabalho e soma 1
    }
    return movimentos;
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    int discos[] = {3, 8, 20, 64};   // quantidades de discos a comparar
    int total = sizeof(discos) / sizeof(discos[0]);

    cout << "Discos | Movimentos              | Anos a 1 movimento/segundo\n";
    for (int i = 0; i < total; i++) {
        unsigned long long movimentos = jogadasMinimas(discos[i]);
        // Converte segundos em anos (dividindo por segundos por ano).
        double anos = movimentos / SEGUNDOS_POR_ANO;
        // setw(n) reserva n colunas; scientific + setprecision(3) mostram 2.218e-07
        cout << "  " << setw(2) << discos[i] << "   | "
             << setw(23) << movimentos << " | "
             << scientific << setprecision(3) << anos << "\n";
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

Cada **torre é um objeto da classe `Pilha`**:

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

Salve como `hanoi.cpp`.

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>
#include <iomanip>   // setw: reserva uma largura fixa para o número impresso

using namespace std; // permite escrever "cout" em vez de "std::cout"

// Número máximo de discos aceito pelo jogo (e capacidade de cada torre).
const int MAX_DISCOS = 8;

/* =====================================================================
   PARTE 1 - A ESTRUTURA DE DADOS PILHA (uma classe)
   ===================================================================== */

class Pilha {
private:
    int dados[MAX_DISCOS];  // os discos: cada número é o tamanho do disco
    int topo;               // índice do disco do topo (-1 = torre vazia)

public:
    // [CONSTRUTOR] a torre já nasce vazia.
    Pilha() {
        topo = -1;
    }

    // [MÉTODO da classe Pilha] true se a torre não tem discos.
    bool vazia() const {
        return topo == -1;
    }

    // [MÉTODO da classe Pilha] true se a torre não comporta mais nenhum disco.
    bool cheia() const {
        return topo == MAX_DISCOS - 1;
    }

    // [MÉTODO da classe Pilha]
    // PUSH: coloca um disco no topo da torre.
    bool empilhar(int valor) {
        if (cheia()) return false;   // sem espaço
        topo++;                      // sobe o topo
        dados[topo] = valor;         // guarda o disco
        return true;
    }

    // [MÉTODO da classe Pilha]
    // POP: retira o disco do topo e o entrega em "valor" (referência).
    bool desempilhar(int &valor) {
        if (vazia()) return false;   // nada para retirar
        valor = dados[topo];         // entrega o disco do topo
        topo--;                      // desce o topo
        return true;
    }

    // [MÉTODO da classe Pilha]
    // PEEK: apenas espia o disco do topo, sem retirá-lo.
    bool consultarTopo(int &valor) const {
        if (vazia()) return false;
        valor = dados[topo];
        return true;
    }

    // [MÉTODO da classe Pilha] quantidade de discos na torre (topo + 1).
    int tamanho() const {
        return topo + 1;
    }

    // [MÉTODO da classe Pilha] lê o disco da posição "indice" (0 = base), só para desenhar.
    int obter(int indice) const {
        return dados[indice];
    }
};

/* =====================================================================
   PARTE 2 - A LÓGICA DO JOGO (funções comuns, fora da classe)
   ===================================================================== */

// Resultado possível de uma tentativa de movimento.
// "enum" cria nomes legíveis no lugar de números soltos (0, 1, 2).
enum Resultado { MOVIDO, ORIGEM_VAZIA, DISCO_MAIOR };

// [FUNÇÃO]
// Move o disco do topo de "origem" para "destino", respeitando as regras.
// "Pilha &" é uma referência: as torres originais são alteradas (nada de cópias).
Resultado mover(Pilha &origem, Pilha &destino) {
    int disco;         // disco que queremos mover
    int topoDestino;   // disco que está no topo da torre de destino

    // Passo 1: espiamos (sem retirar) o disco de cima da origem.
    // Se não existe disco, não há o que mover.
    if (!origem.consultarTopo(disco)) {
        return ORIGEM_VAZIA;
    }

    // Passo 2: se o destino tem algum disco, comparamos os tamanhos.
    // consultarTopo devolve false quando o destino está vazio - nesse caso
    // qualquer disco pode entrar e esta condição é falsa.
    if (destino.consultarTopo(topoDestino) && disco > topoDestino) {
        return DISCO_MAIOR;   // disco maior sobre menor: regra violada
    }

    // Passo 3: movimento válido = um POP na origem + um PUSH no destino.
    origem.desempilhar(disco);
    destino.empilhar(disco);
    return MOVIDO;
}

// [FUNÇÃO]
// Desenha as três torres no terminal, de cima para baixo.
void desenhar(const Pilha torres[3], int n) {
    cout << "\n";
    // "nivel" é a altura: começa no mais alto (n-1) e desce até 0 (base).
    for (int nivel = n - 1; nivel >= 0; nivel--) {
        for (int i = 0; i < 3; i++) {
            // Se a torre i tem disco nessa altura, imprime o número dele;
            // senão imprime só a haste vazia "|".
            if (nivel < torres[i].tamanho())
                cout << "    " << setw(2) << torres[i].obter(nivel) << "    ";
            else
                cout << "     |    ";
        }
        cout << "\n";
    }
    cout << "  -------   -------   -------\n";
    cout << "  Torre 1   Torre 2   Torre 3\n\n";
}

// [FUNÇÃO]
// Deixa o jogo na posição inicial: todos os discos na torre 1.
// As três torres já nasceram vazias, pois o construtor de Pilha rodou sozinho.
void iniciar(Pilha torres[3], int n) {
    // Empilhamos do maior (n) para o menor (1): o maior fica na base.
    for (int i = n; i >= 1; i--) torres[0].empilhar(i);
}

// [FUNÇÃO RECURSIVA]
// Solução automática. Para mover n discos de "origem" para "destino" usando "auxiliar":
//   1) move n-1 discos de origem para auxiliar;
//   2) move o maior disco de origem para destino;
//   3) move os n-1 discos de auxiliar para destino.
void resolver(Pilha torres[3], int n, int origem, int destino, int auxiliar) {
    if (n == 0) return;   // caso base: não há disco para mover
    resolver(torres, n - 1, origem, auxiliar, destino);       // passo 1
    mover(torres[origem], torres[destino]);                   // passo 2
    cout << "Mover disco " << n << ": Torre " << origem + 1
         << " -> Torre " << destino + 1 << "\n";
    resolver(torres, n - 1, auxiliar, destino, origem);       // passo 3
}

/* =====================================================================
   PARTE 3 - PROGRAMA PRINCIPAL
   ===================================================================== */

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    Pilha torres[3];     // 3 objetos Pilha: torres[0], torres[1], torres[2]
    int n;               // quantidade de discos
    int origem, destino; // torres escolhidas pelo jogador (1 a 3)
    int jogadas = 0;     // contador de jogadas válidas
    int opcao;           // 1 = jogar, 2 = solução automática

    cout << "=== TORRE DE HANOI ===\n";
    cout << "Quantos discos (1 a " << MAX_DISCOS << ")? ";
    // "cin >> n" devolve falso se a leitura falhar (ex.: o usuário digitou letras).
    if (!(cin >> n) || n < 1 || n > MAX_DISCOS) {
        cout << "Valor invalido.\n";
        return 1;
    }

    iniciar(torres, n);   // coloca todos os discos na torre 1

    cout << "\n1 - Jogar\n2 - Ver solucao automatica\nEscolha: ";
    if (!(cin >> opcao)) return 1;

    // ---- Modo automático ----
    if (opcao == 2) {
        desenhar(torres, n);
        resolver(torres, n, 0, 2, 1);   // da torre 1 (0) para a 3 (2), apoio na 2 (1)
        desenhar(torres, n);
        return 0;
    }

    // ---- Modo jogo ----
    // Condição de vitória: a torre 3 (índice 2) tem n discos. Repetimos até isso acontecer.
    while (torres[2].tamanho() != n) {
        desenhar(torres, n);
        cout << "Jogada " << jogadas + 1 << " - origem destino (0 0 para sair): ";
        if (!(cin >> origem >> destino)) {
            cout << "Entrada invalida.\n";
            return 1;
        }
        if (origem == 0 && destino == 0) {   // jogador desistiu
            cout << "Ate a proxima!\n";
            return 0;
        }
        // Valida os números digitados pelo jogador.
        if (origem < 1 || origem > 3 || destino < 1 || destino > 3
            || origem == destino) {
            cout << ">> Escolha torres diferentes entre 1 e 3.\n";
            continue;   // volta ao início do while
        }

        // O jogador digita 1..3, mas o vetor usa índices 0..2: por isso "- 1".
        Resultado resultado = mover(torres[origem - 1], torres[destino - 1]);
        if (resultado == ORIGEM_VAZIA) {
            cout << ">> A torre " << origem << " esta vazia!\n";
        } else if (resultado == DISCO_MAIOR) {
            cout << ">> Nao pode colocar disco maior sobre menor!\n";
        } else {
            jogadas++;   // só conta quando o movimento foi realmente feito
        }
    }

    // Saiu do while: o jogador venceu.
    desenhar(torres, n);
    cout << "Parabens! Voce venceu em " << jogadas << " jogadas.\n";
    // (1 << n) é o mesmo que 2^n; o mínimo de jogadas é 2^n - 1.
    cout << "O minimo possivel era " << (1 << n) - 1 << " jogadas.\n";
    return 0;
}
```

### Compilar e executar

```bash
g++ -std=c++17 -Wall hanoi.cpp -o hanoi
./hanoi
```

---

## 6. Entendendo o código por partes

### 6.1 Montando o jogo

```cpp
for (int i = n; i >= 1; i--) torres[0].empilhar(i);
```

Com `n = 3`, empilhamos 3, depois 2, depois 1. Resultado: o disco **1** fica no topo e o **3** na base. O primeiro a entrar (3) é o último a poder sair.

### 6.2 Validando uma jogada

```cpp
if (destino.consultarTopo(topoDestino) && disco > topoDestino) {
    return DISCO_MAIOR;
}
```

`consultarTopo` devolve `false` se a torre de destino está vazia (aí qualquer disco pode entrar). Se há disco, comparamos: disco maior sobre menor é proibido.

### 6.3 Fazendo o movimento

```cpp
origem.desempilhar(disco);   // tira do topo da origem
destino.empilhar(disco);     // coloca no topo do destino
```

**Mover um disco = um `pop` seguido de um `push`.**

### 6.4 Condição de vitória

```cpp
while (torres[2].tamanho() != n)
```

Com `n` discos na torre 3, o `tamanho()` vale `n`. Quando isso acontece, o jogo termina.

### 6.5 Por que `Pilha &` nas funções?

Sem o `&`, o C++ **copiaria** a pilha inteira ao chamar a função, e o `mover` alteraria apenas a cópia. Com a **referência** (`Pilha &`), a função trabalha na pilha original.

---

## 7. Bônus: a solução recursiva

A ideia para mover `n` discos da torre A para a torre C (usando B como apoio):

1. Mova os `n-1` discos de cima de **A para B**.
2. Mova o maior disco de **A para C**.
3. Mova os `n-1` discos de **B para C**.

```cpp
// [FUNÇÃO RECURSIVA] chama a si mesma
void resolver(Pilha torres[3], int n, int origem, int destino, int auxiliar) {
    if (n == 0) return;                                  // caso base: nada a mover
    resolver(torres, n - 1, origem, auxiliar, destino);  // 1) n-1 discos: A -> B
    mover(torres[origem], torres[destino]);              // 2) maior disco: A -> C
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
| `desempilhar` em pilha vazia | Lê posição `-1` do vetor | Testar `vazia()` antes (os métodos já fazem isso) |
| `empilhar` em pilha cheia | Escreve fora do vetor | Testar `cheia()` antes |
| Passar `Pilha` sem `&` | O C++ **copia** a pilha e a original não muda | Usar `Pilha &p` |
| Confundir `topo` com quantidade | `topo` é um **índice** | Quantidade de elementos = `tamanho()` (`topo + 1`) |
| Tentar acessar `topo` ou `dados` de fora | São `private` | Usar os métodos públicos |
| Esquecer o `delete` (pilha dinâmica) | A memória reservada com `new` nunca é devolvida (vazamento) | Liberar no **destrutor** |

---

## 9. Exercícios resolvidos

Cada exercício abaixo é um programa **completo**, já comentado. Salve em um arquivo `.cpp`, compile com `g++ -std=c++17` e execute.

> **Sugestão didática:** tente resolver sozinho antes de olhar a resposta.

---

### Exercício 1 - Teste a pilha sozinha

**Enunciado:** crie um `main` que empilha 10, 20, 30, desempilha duas vezes e imprime o que sobrou.

**Arquivo:** `exercicio1_pilha.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>

using namespace std;

const int MAX = 10;   // capacidade da pilha neste exercício

class Pilha {
private:
    int dados[MAX];
    int topo;

public:
    // [CONSTRUTOR] pilha nasce vazia
    Pilha() { topo = -1; }

    // [MÉTODO da classe Pilha]
    bool vazia() const { return topo == -1; }

    // [MÉTODO da classe Pilha]
    bool cheia() const { return topo == MAX - 1; }

    // [MÉTODO da classe Pilha]
    bool empilhar(int valor) {
        if (cheia()) return false;
        dados[++topo] = valor;    // ++topo primeiro, depois guarda o valor
        return true;
    }

    // [MÉTODO da classe Pilha]
    bool desempilhar(int &valor) {
        if (vazia()) return false;
        valor = dados[topo--];    // lê o valor e só depois decrementa o topo
        return true;
    }

    // [MÉTODO da classe Pilha]
    int tamanho() const { return topo + 1; }

    // [MÉTODO da classe Pilha] lê a posição "indice" (0 = base) sem alterar a pilha
    int obter(int indice) const { return dados[indice]; }
};

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    Pilha p;       // o construtor já deixa a pilha pronta (não precisa inicializar)
    int valor;

    // Empilha 10, 20 e 30 (o 30 fica no topo).
    p.empilhar(10);
    p.empilhar(20);
    p.empilhar(30);
    cout << "Empilhados: 10, 20, 30\n";

    // Desempilha duas vezes: devem sair 30 e depois 20 (ordem LIFO).
    for (int i = 0; i < 2; i++) {
        if (p.desempilhar(valor)) {
            cout << "Desempilhou: " << valor << "\n";
        }
    }

    // Mostra o que sobrou, do topo até a base, sem remover nada.
    cout << "Sobrou na pilha (topo -> base):";
    for (int i = p.tamanho() - 1; i >= 0; i--) {
        cout << " " << p.obter(i);
    }
    cout << "\n";

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

**Arquivo:** `exercicio2_minimo.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>
#include <iomanip>   // setw

using namespace std;

// [FUNÇÃO]
// Versão 1: usa deslocamento de bits.
// (1L << n) é o mesmo que 2 elevado a n. "L" garante o tipo long.
long jogadasMinimasBits(int n) {
    return (1L << n) - 1;
}

// [FUNÇÃO RECURSIVA]
// Versão 2: usa a recorrência da própria Torre de Hanói.
//   minimo(1) = 1
//   minimo(n) = 2 * minimo(n-1) + 1
// (move n-1 discos, move o maior, move n-1 discos de novo)
long jogadasMinimasRecorrencia(int n) {
    if (n == 1) return 1;                               // caso base
    return 2 * jogadasMinimasRecorrencia(n - 1) + 1;    // caso recursivo
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    cout << "Discos | Bits | Recorrencia\n";
    for (int n = 1; n <= 8; n++) {
        cout << "  " << setw(2) << n << "   | "
             << setw(4) << jogadasMinimasBits(n) << " | "
             << setw(6) << jogadasMinimasRecorrencia(n) << "\n";
    }

    return 0;
}
```

**Para usar no jogo:** copie a função `jogadasMinimasBits` para o `hanoi.cpp` e, logo após ler `n`, acrescente:

```cpp
cout << "Minimo de jogadas possivel: " << jogadasMinimasBits(n) << "\n";
```

---

### Exercício 3 - Desfazer jogada (segunda pilha como histórico)

**Enunciado:** use **outra pilha** para guardar o histórico de movimentos e implemente a opção "desfazer".

**Ideia:** cada jogada válida é empilhada no histórico, codificada em **um único número** `origem*10 + destino` (por exemplo, torre 0 para torre 2 vira `2`; torre 1 para torre 2 vira `12`). Para desfazer, fazemos `pop` no histórico, decodificamos e movemos o disco **de volta**. Como o histórico é uma pilha, a última jogada é a primeira a ser desfeita (LIFO).

**Arquivo:** `hanoi_desfazer.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>
#include <iomanip>   // setw
#include <string>    // string e getline
#include <sstream>   // istringstream: lê números de dentro de um texto

using namespace std;

const int MAX_DISCOS = 8;
// O histórico precisa guardar até 2^8 - 1 = 255 jogadas, então a
// capacidade das pilhas agora é maior que o número de discos.
const int MAX_ELEM = 300;

class Pilha {
private:
    int dados[MAX_ELEM];
    int topo;

public:
    // [CONSTRUTOR]
    Pilha() { topo = -1; }

    // [MÉTODO da classe Pilha]
    bool vazia() const { return topo == -1; }

    // [MÉTODO da classe Pilha]
    bool cheia() const { return topo == MAX_ELEM - 1; }

    // [MÉTODO da classe Pilha]
    bool empilhar(int valor) {
        if (cheia()) return false;
        dados[++topo] = valor;
        return true;
    }

    // [MÉTODO da classe Pilha]
    bool desempilhar(int &valor) {
        if (vazia()) return false;
        valor = dados[topo--];
        return true;
    }

    // [MÉTODO da classe Pilha]
    bool consultarTopo(int &valor) const {
        if (vazia()) return false;
        valor = dados[topo];
        return true;
    }

    // [MÉTODO da classe Pilha]
    int tamanho() const { return topo + 1; }

    // [MÉTODO da classe Pilha]
    int obter(int indice) const { return dados[indice]; }
};

enum Resultado { MOVIDO, ORIGEM_VAZIA, DISCO_MAIOR };

// [FUNÇÃO]
Resultado mover(Pilha &origem, Pilha &destino) {
    int disco, topoDestino;
    if (!origem.consultarTopo(disco)) return ORIGEM_VAZIA;
    if (destino.consultarTopo(topoDestino) && disco > topoDestino) return DISCO_MAIOR;
    origem.desempilhar(disco);
    destino.empilhar(disco);
    return MOVIDO;
}

// [FUNÇÃO]
void desenhar(const Pilha torres[3], int n) {
    cout << "\n";
    for (int nivel = n - 1; nivel >= 0; nivel--) {
        for (int i = 0; i < 3; i++) {
            if (nivel < torres[i].tamanho())
                cout << "    " << setw(2) << torres[i].obter(nivel) << "    ";
            else
                cout << "     |    ";
        }
        cout << "\n";
    }
    cout << "  -------   -------   -------\n";
    cout << "  Torre 1   Torre 2   Torre 3\n\n";
}

// [FUNÇÃO]
void iniciar(Pilha torres[3], int n) {
    for (int i = n; i >= 1; i--) torres[0].empilhar(i);
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    Pilha torres[3];     // as três torres
    Pilha historico;     // NOVA pilha: guarda as jogadas já feitas
    string linha;        // texto digitado pelo jogador
    int n, origem, destino;
    int jogadas = 0;
    int jogada;          // jogada codificada que sai do histórico

    cout << "=== TORRE DE HANOI (com desfazer) ===\n";
    cout << "Quantos discos (1 a " << MAX_DISCOS << ")? ";
    // getline lê a linha inteira; o istringstream extrai o número dela.
    // (Usamos getline em tudo para não misturar com cin >>.)
    if (!getline(cin, linha)) return 1;
    istringstream leituraN(linha);
    if (!(leituraN >> n) || n < 1 || n > MAX_DISCOS) {
        cout << "Valor invalido.\n";
        return 1;
    }

    iniciar(torres, n);
    cout << "Minimo de jogadas possivel: " << (1 << n) - 1 << "\n";

    while (torres[2].tamanho() != n) {
        desenhar(torres, n);
        cout << "Jogada " << jogadas + 1
             << " - 'origem destino', 'd' = desfazer, 's' = sair: ";
        if (!getline(cin, linha)) return 1;   // fim da entrada

        // ---- Comando: sair ----
        if (!linha.empty() && (linha[0] == 's' || linha[0] == 'S')) {
            cout << "Ate a proxima!\n";
            return 0;
        }

        // ---- Comando: desfazer ----
        if (!linha.empty() && (linha[0] == 'd' || linha[0] == 'D')) {
            // POP no histórico: pega a última jogada feita.
            if (historico.desempilhar(jogada)) {
                origem  = jogada / 10;   // decodifica: dezena = origem (0 a 2)
                destino = jogada % 10;   // decodifica: unidade = destino (0 a 2)
                // Desfazer = mover o disco de volta, ou seja, com os papéis
                // trocados: o antigo destino vira origem e vice-versa.
                mover(torres[destino], torres[origem]);
                jogadas--;
                cout << ">> Jogada desfeita: Torre " << origem + 1
                     << " <- Torre " << destino + 1 << "\n";
            } else {
                cout << ">> Nada para desfazer!\n";
            }
            continue;
        }

        // ---- Comando: jogada normal "origem destino" ----
        istringstream leituraJogada(linha);
        if (!(leituraJogada >> origem >> destino)) {
            cout << ">> Entrada invalida.\n";
            continue;
        }
        if (origem < 1 || origem > 3 || destino < 1 || destino > 3
            || origem == destino) {
            cout << ">> Escolha torres diferentes entre 1 e 3.\n";
            continue;
        }

        Resultado resultado = mover(torres[origem - 1], torres[destino - 1]);
        if (resultado == ORIGEM_VAZIA) {
            cout << ">> A torre " << origem << " esta vazia!\n";
        } else if (resultado == DISCO_MAIOR) {
            cout << ">> Nao pode colocar disco maior sobre menor!\n";
        } else {
            // Jogada válida: PUSH no histórico, codificando origem e destino
            // (já em índices 0..2) em um único inteiro.
            historico.empilhar((origem - 1) * 10 + (destino - 1));
            jogadas++;
        }
    }

    desenhar(torres, n);
    cout << "Parabens! Voce venceu em " << jogadas << " jogadas.\n";
    return 0;
}
```

---

### Exercício 4 - Pilha dinâmica (lista encadeada com `new`)

**Enunciado:** reescreva a pilha com lista encadeada e alocação dinâmica, sem limite fixo `MAX_DISCOS`.

**Ideia:** em vez de um vetor, cada elemento é um **nó** alocado na memória com `new`. Cada nó guarda o valor e um ponteiro para o nó que está **abaixo** dele. A pilha só precisa conhecer o **topo**.

```
topo → [ 30 | • ] → [ 20 | • ] → [ 10 | nullptr ]
```

Em C++ há uma vantagem sobre o C: o **destrutor**. Ele é chamado **automaticamente** quando a pilha deixa de existir (ao fim do `main`, por exemplo), e é o lugar ideal para devolver a memória com `delete`. Assim é muito mais difícil esquecer de liberar.

**Arquivo:** `exercicio4_pilha_dinamica.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>

using namespace std;

class PilhaDin {
private:
    // Um nó da lista: guarda um valor e aponta para o nó de baixo.
    // Está dentro de "private" porque é um detalhe interno da pilha.
    struct No {
        int valor;
        No *prox;   // nó abaixo deste (nullptr se for o último)
    };

    No *topo_;       // nó do topo (nullptr = pilha vazia)
    int tamanho_;    // quantidade de elementos
    // (o "_" no fim do nome evita confusão com o método tamanho())

public:
    // [CONSTRUTOR] pilha nasce vazia
    PilhaDin() : topo_(nullptr), tamanho_(0) {}

    // [DESTRUTOR]
    // O "~" indica destrutor. Roda sozinho quando a pilha é destruída
    // e devolve a memória de todos os nós que sobraram.
    ~PilhaDin() {
        liberar();
        cout << "[destrutor] memoria liberada\n";
    }

    // Proíbe copiar a pilha: duas pilhas apontando para os mesmos nós
    // tentariam dar "delete" duas vezes no mesmo endereço (erro grave).
    PilhaDin(const PilhaDin &) = delete;
    PilhaDin &operator=(const PilhaDin &) = delete;

    // [MÉTODO da classe Pilha]
    bool vazia() const { return topo_ == nullptr; }

    // [MÉTODO da classe Pilha]
    int tamanho() const { return tamanho_; }

    // [MÉTODO da classe Pilha]
    // PUSH: cria um nó novo e o coloca no topo.
    // Não existe "cheia": só falha se o computador ficar sem memória
    // (e então o "new" lança uma exceção).
    void empilhar(int valor) {
        No *novo = new No{valor, topo_};   // o novo nó já aponta para o antigo topo
        topo_ = novo;                      // o novo nó passa a ser o topo
        tamanho_++;
    }

    // [MÉTODO da classe Pilha]
    // POP: remove o nó do topo, entrega o valor e LIBERA a memória do nó.
    bool desempilhar(int &valor) {
        if (vazia()) return false;
        No *removido = topo_;       // guarda o nó que vai sair
        valor = removido->valor;    // entrega o valor
        topo_ = removido->prox;     // o topo passa a ser o nó de baixo
        delete removido;            // devolve a memória (evita vazamento!)
        tamanho_--;
        return true;
    }

    // [MÉTODO da classe Pilha]
    // PEEK: espia o topo sem remover.
    bool consultarTopo(int &valor) const {
        if (vazia()) return false;
        valor = topo_->valor;
        return true;
    }

    // [MÉTODO da classe Pilha]
    // Libera todos os nós restantes.
    void liberar() {
        int descartado;
        while (desempilhar(descartado)) {
            // o próprio desempilhar já faz o delete de cada nó
        }
    }
};

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    PilhaDin p;
    int valor;

    // Empilha 1000 valores: com o vetor de 8 posições isso seria impossível!
    for (int i = 1; i <= 1000; i++) {
        p.empilhar(i);
    }
    cout << "Tamanho apos empilhar 1000 valores: " << p.tamanho() << "\n";

    p.consultarTopo(valor);
    cout << "Topo atual: " << valor << "\n";

    // Desempilha três valores: saem 1000, 999 e 998.
    for (int i = 0; i < 3; i++) {
        p.desempilhar(valor);
        cout << "Desempilhou: " << valor << "\n";
    }
    cout << "Tamanho agora: " << p.tamanho() << "\n";

    p.liberar();   // devolve toda a memória restante
    cout << "Tamanho apos liberar: " << p.tamanho() << "\n";

    return 0;      // ao sair de main, o destrutor de "p" roda automaticamente
}
```

**Saída esperada:**

```
Tamanho apos empilhar 1000 valores: 1000
Topo atual: 1000
Desempilhou: 1000
Desempilhou: 999
Desempilhou: 998
Tamanho agora: 997
Tamanho apos liberar: 0
[destrutor] memoria liberada
```

**Para usar na Torre de Hanói:** troque `Pilha` por `PilhaDin` e a condição de vitória continua `torres[2].tamanho() != n`. Como a lista não tem índices, a função `desenhar` precisaria percorrer os nós (ou você acrescenta um método que copie os valores para um vetor temporário).

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

**Arquivo:** `exercicio5_parenteses.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>
#include <string>

using namespace std;

const int MAX = 100;   // tamanho máximo de aninhamento

// Esta pilha guarda CARACTERES (char) em vez de inteiros.
class PilhaChar {
private:
    char dados[MAX];
    int topo;

public:
    // [CONSTRUTOR]
    PilhaChar() { topo = -1; }

    // [MÉTODO da classe Pilha]
    bool vazia() const { return topo == -1; }

    // [MÉTODO da classe Pilha]
    bool cheia() const { return topo == MAX - 1; }

    // [MÉTODO da classe Pilha]
    bool empilhar(char c) {
        if (cheia()) return false;
        dados[++topo] = c;
        return true;
    }

    // [MÉTODO da classe Pilha]
    bool desempilhar(char &c) {
        if (vazia()) return false;
        c = dados[topo--];
        return true;
    }
};

// [FUNÇÃO]
// Verifica se "fecha" é o par correto de "abre".
bool casam(char abre, char fecha) {
    return (abre == '(' && fecha == ')')
        || (abre == '[' && fecha == ']')
        || (abre == '{' && fecha == '}');
}

// [FUNÇÃO]
// Devolve true se a expressão está balanceada, false caso contrário.
// "const string &" evita copiar o texto e garante que ele não será alterado.
bool balanceada(const string &expr) {
    PilhaChar p;   // pilha local: nasce vazia graças ao construtor
    char topo;

    for (char c : expr) {   // percorre cada caractere da string
        if (c == '(' || c == '[' || c == '{') {
            // Abertura: guarda na pilha para conferir depois.
            if (!p.empilhar(c)) return false;   // aninhamento além do limite
        }
        else if (c == ')' || c == ']' || c == '}') {
            // Fechamento: precisa existir uma abertura esperando...
            if (!p.desempilhar(topo)) return false;   // pilha vazia: sobrou ")"
            // ...e ela precisa ser do mesmo tipo.
            if (!casam(topo, c)) return false;        // ex.: "(]"
        }
        // Qualquer outro caractere (letras, números, operadores) é ignorado.
    }

    // Se sobrou alguma abertura sem fechar, a pilha não está vazia.
    return p.vazia();
}

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    // Expressões de teste: algumas corretas, outras com erro.
    const string testes[] = {
        "((a+b)*c)",      // correta
        "[(a+b)*{c-d}]",  // correta, com tipos misturados
        "((a+b)*c",       // erro: falta fechar um "("
        "(a+b))*c",       // erro: sobrou um ")"
        "(a+b]",          // erro: "(" fechado com "]"
        ""                // vazia: considerada balanceada
    };

    for (const string &t : testes) {
        cout << "\"" << t << "\" -> "
             << (balanceada(t) ? "balanceada" : "ERRADA") << "\n";
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

## 10. Bônus: a pilha pronta da biblioteca padrão (`std::stack`)

Aprender a construir a pilha é essencial, mas no dia a dia, em C++, você pode usar a que já vem pronta: `std::stack`.

**Arquivo:** `bonus_std_stack.cpp`

```cpp
// LEGENDA: [CONSTRUTOR] = método especial, chamado automaticamente ao criar o objeto
//          [DESTRUTOR]  = método especial, chamado automaticamente ao destruir o objeto
//          [MÉTODO]     = função que pertence a uma classe (age sobre o objeto, via "this")
//          [FUNÇÃO]     = função comum, fora de qualquer classe

#include <iostream>
#include <stack>     // a pilha pronta da biblioteca padrão

using namespace std;

// [FUNÇÃO PRINCIPAL] ponto de entrada: o programa começa aqui
int main() {
    stack<int> pilha;   // pilha de inteiros (a mesma ideia da nossa classe Pilha)

    pilha.push(10);     // [MÉTODO de std::stack] empilhar
    pilha.push(20);
    pilha.push(30);

    cout << "Topo: " << pilha.top() << "\n";          // [MÉTODO] consultar o topo (30)
    cout << "Tamanho: " << pilha.size() << "\n";      // [MÉTODO] quantidade (3)

    // ATENÇÃO: o pop() de std::stack NÃO devolve o valor removido.
    // Para ler e remover, primeiro usamos top() e depois pop().
    int valor = pilha.top();
    pilha.pop();                                      // [MÉTODO] remove o topo
    cout << "Removeu: " << valor << "\n";

    // empty() é o equivalente ao nosso vazia().
    while (!pilha.empty()) {
        cout << "Saindo: " << pilha.top() << "\n";
        pilha.pop();
    }

    return 0;
}
```

**Saída esperada:**

```
Topo: 30
Tamanho: 3
Removeu: 30
Saindo: 20
Saindo: 10
```

| Nossa classe `Pilha` | `std::stack<int>` |
|---|---|
| `empilhar(x)` | `push(x)` |
| `desempilhar(x)` (lê e remove) | `top()` + `pop()` (em dois passos) |
| `consultarTopo(x)` | `top()` |
| `vazia()` | `empty()` |
| `tamanho()` | `size()` |
| `obter(i)` (ler posição `i`) | **não existe** (não dá para percorrer a pilha) |

Por esse último motivo, no jogo da Torre de Hanói usamos a **nossa** classe: precisamos olhar cada disco para **desenhar** as torres.

---

## 11. Resumo

- Pilha é uma estrutura **LIFO**: o último a entrar é o primeiro a sair.
- Operações essenciais: `empilhar` (push), `desempilhar` (pop), `consultarTopo` (peek).
- Em C++, uma **classe** junta os dados (`dados`, `topo`) e as operações (**métodos**); o **construtor** deixa a pilha pronta ao ser criada.
- Podemos implementar com **vetor + índice `topo`** (tamanho fixo) ou com **lista encadeada + `new`/`delete`** (tamanho flexível, com **destrutor** para liberar a memória).
- Na Torre de Hanói, **cada torre é um objeto `Pilha`** e **cada movimento é um pop seguido de um push**.
- A regra "disco maior não vai sobre menor" é validada comparando com o `consultarTopo` do destino.
- A mesma ideia resolve outros problemas: desfazer ações (histórico em pilha) e conferir parênteses.
- Na prática, a biblioteca padrão já oferece `std::stack`.
