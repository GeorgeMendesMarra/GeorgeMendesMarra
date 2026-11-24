# Grafos: Uma Introdução para Quem Nunca Viu o Assunto

> Este material é **independente de linguagem de programação**. Os algoritmos aparecem em pseudocódigo em português, que você pode traduzir para Java, C, Python, JavaScript ou qualquer outra linguagem.

---

## Sumário

1. [O que é um grafo?](#1-o-que-é-um-grafo)
2. [Por que estudar grafos?](#2-por-que-estudar-grafos)
3. [Vocabulário básico](#3-vocabulário-básico)
4. [Tipos de grafos](#4-tipos-de-grafos)
5. [Caminhos, ciclos e conectividade](#5-caminhos-ciclos-e-conectividade)
6. [Grafos especiais](#6-grafos-especiais)
7. [Como representar um grafo no computador](#7-como-representar-um-grafo-no-computador)
8. [Percorrendo um grafo: BFS e DFS](#8-percorrendo-um-grafo-bfs-e-dfs)
9. [Caminho mais curto](#9-caminho-mais-curto)
10. [Árvore geradora mínima](#10-árvore-geradora-mínima)
11. [Ordenação topológica](#11-ordenação-topológica)
12. [Problemas clássicos](#12-problemas-clássicos)
13. [Resumo de complexidades](#13-resumo-de-complexidades)
14. [Exercícios](#14-exercícios)
15. [Respostas dos exercícios](#15-respostas-dos-exercícios)
16. [Glossário](#16-glossário)
17. [Para ir além](#17-para-ir-além)

---

## 1. O que é um grafo?

Um **grafo** é uma forma de representar **coisas** e as **ligações** entre elas.

- As **coisas** são chamadas de **vértices** (ou nós).
- As **ligações** são chamadas de **arestas** (ou arcos).

Imagine um mapa de metrô. Cada estação é um vértice. Cada trecho de trilho entre duas estações é uma aresta. Você não precisa do desenho exato dos trilhos, só precisa saber **quem está ligado a quem**. Isso é um grafo.

```
   A -------- B
   |          |
   |          |
   C -------- D -------- E
```

Neste grafo:

- Vértices: `A`, `B`, `C`, `D`, `E`
- Arestas: `A-B`, `A-C`, `B-D`, `C-D`, `D-E`

### Definição formal

Um grafo `G` é um par de conjuntos:

```
G = (V, E)
```

- `V` é o conjunto de vértices.
- `E` é o conjunto de arestas, onde cada aresta liga dois vértices.

Para o exemplo acima:

```
V = {A, B, C, D, E}
E = {(A,B), (A,C), (B,D), (C,D), (D,E)}
```

> **Importante:** o desenho de um grafo não importa. O que importa é **quais vértices estão ligados**. Você pode desenhar o mesmo grafo de várias formas diferentes (com linhas curvas, retas, em posições diferentes) e ele continua sendo o mesmo grafo.

---

## 2. Por que estudar grafos?

Grafos aparecem em quase todo lugar:

| Situação do mundo real | Vértices | Arestas |
|---|---|---|
| Rede social | Pessoas | Amizades ou seguidores |
| Mapa de ruas | Cruzamentos | Ruas |
| Internet | Páginas ou computadores | Links ou cabos |
| Metrô ou ônibus | Estações | Linhas entre estações |
| Dependência de tarefas | Tarefas | "A precisa vir antes de B" |
| Jogo de tabuleiro | Posições do jogo | Jogadas possíveis |
| Pré-requisitos de disciplinas | Disciplinas | "Precisa cursar antes" |
| Redes elétricas | Subestações | Linhas de transmissão |
| Moléculas na química | Átomos | Ligações químicas |
| Sistemas de recomendação | Usuários e produtos | Compras ou avaliações |

Perguntas que os grafos ajudam a responder:

- Qual é o **menor caminho** entre dois lugares?
- Existe **alguma ligação** entre duas pessoas?
- Qual é a **melhor ordem** para executar tarefas que dependem umas das outras?
- Como conectar todas as cidades gastando **o mínimo de cabo**?
- Quem é a pessoa **mais influente** de uma rede?

---

## 3. Vocabulário básico

Antes de avançar, vamos fixar os termos mais usados.

### Vértice (ou nó)
Cada "ponto" do grafo.

### Aresta (ou arco)
A ligação entre dois vértices.

### Vértices adjacentes (ou vizinhos)
Dois vértices são **adjacentes** quando existe uma aresta entre eles. No grafo da seção 1, `A` e `B` são adjacentes, mas `A` e `D` não são.

### Grau de um vértice
É o **número de arestas** que tocam o vértice.

```
   A -------- B
   |          |
   C -------- D -------- E
```

| Vértice | Grau | Explicação |
|---|---|---|
| A | 2 | liga em B e C |
| B | 2 | liga em A e D |
| C | 2 | liga em A e D |
| D | 3 | liga em B, C e E |
| E | 1 | liga só em D |

> **Propriedade curiosa (Lema do Aperto de Mãos):** a soma de todos os graus é sempre **o dobro do número de arestas**, porque cada aresta é contada duas vezes, uma em cada ponta. Aqui: 2+2+2+3+1 = 10 = 2 × 5 arestas.

### Laço (loop)
Uma aresta que liga um vértice **a ele mesmo**.

### Arestas paralelas
Duas ou mais arestas ligando **o mesmo par** de vértices.

### Vértice isolado
Um vértice com grau 0, sem nenhuma aresta.

---

## 4. Tipos de grafos

### 4.1 Grafo não direcionado

As arestas **não têm sentido**. Se `A` está ligado a `B`, então `B` também está ligado a `A`.

Exemplo: **amizade** em uma rede social. Se eu sou seu amigo, você é meu amigo.

```
A ------ B
```

### 4.2 Grafo direcionado (dígrafo)

As arestas **têm sentido**, representado por uma seta. `A → B` não significa `B → A`.

Exemplo: **seguidores**. Eu posso seguir uma celebridade sem que ela me siga.

```
A ------> B
```

Em dígrafos, o grau se divide em dois:

- **Grau de saída:** quantas setas **saem** do vértice.
- **Grau de entrada:** quantas setas **chegam** ao vértice.

### 4.3 Grafo ponderado (ou valorado)

Cada aresta tem um **peso** (um número associado). O peso pode representar distância, custo, tempo, capacidade etc.

```
        5
   A ------- B
   |         |
 2 |         | 1
   |         |
   C ------- D
        4
```

Aqui, ir de `A` até `D` pode custar `5 + 1 = 6` (por B) ou `2 + 4 = 6` (por C).

### 4.4 Grafo simples e multigrafo

- **Grafo simples:** não tem laços nem arestas paralelas. É o tipo mais comum em estudos introdutórios.
- **Multigrafo:** permite arestas paralelas (e às vezes laços).

### 4.5 Combinando as características

Essas características se combinam. Você pode ter, por exemplo, um **grafo direcionado e ponderado**: um mapa de ruas com mão única (direcionado) e distâncias em quilômetros (ponderado).

---

## 5. Caminhos, ciclos e conectividade

### Caminho
Uma **sequência de vértices** em que cada par consecutivo é ligado por uma aresta.

```
   A -------- B
   |          |
   C -------- D -------- E
```

`A → B → D → E` é um caminho de `A` até `E`.

- O **comprimento** de um caminho é o número de arestas usadas (no exemplo, 3).
- Em grafos ponderados, o comprimento costuma ser a **soma dos pesos**.

### Caminho simples
Um caminho que **não repete vértices**.

### Ciclo
Um caminho que **começa e termina no mesmo vértice**, sem repetir arestas.

`A → B → D → C → A` é um ciclo.

Um grafo sem nenhum ciclo é chamado de **acíclico**.

### Grafo conexo e desconexo

- **Conexo:** existe caminho entre **qualquer par** de vértices.
- **Desconexo:** existem vértices que não conseguem alcançar outros.

```
Conexo:             Desconexo:

A --- B             A --- B        D --- E
|     |             |              
C --- D             C              F
```

No desconexo, `A` não consegue chegar em `E`. Cada "pedaço" ligado é chamado de **componente conexa**. O grafo desconexo acima tem 3 componentes: `{A, B, C}`, `{D, E}` e `{F}`.

### Conectividade em dígrafos

Em grafos direcionados, a conexão é mais sutil:

- **Fortemente conexo:** de qualquer vértice se chega a qualquer outro **seguindo as setas**.
- **Fracamente conexo:** o grafo seria conexo se você **ignorasse o sentido** das setas.

---

## 6. Grafos especiais

### Grafo completo
**Todos** os vértices estão ligados entre si. Com `n` vértices, o número de arestas é:

```
n × (n - 1) / 2
```

Exemplo: 4 vértices resultam em 4 × 3 / 2 = 6 arestas.

```
A ----- B
| \   / |
|  \ /  |
|  / \  |
| /   \ |
C ----- D
```

### Árvore
Um grafo **conexo e sem ciclos**. É um dos grafos mais importantes.

```
        A
       / \
      B   C
     / \   \
    D   E   F
```

Propriedades de uma árvore com `n` vértices:

- Tem exatamente `n - 1` arestas.
- Entre dois vértices quaisquer existe **um único caminho**.
- Remover qualquer aresta a **desconecta**.
- Adicionar qualquer aresta cria um **ciclo**.

> Estruturas como árvores genealógicas, hierarquias de pastas e organogramas de empresas são árvores.

### Floresta
Um conjunto de árvores, ou seja, um grafo sem ciclos que pode ser desconexo.

### Grafo bipartido
Os vértices podem ser divididos em **dois grupos** de modo que toda aresta liga um vértice de um grupo a um vértice do outro. Nenhuma aresta liga dois vértices do mesmo grupo.

```
 Alunos          Disciplinas
  João  -------- Matemática
  João  -------- Física
  Maria -------- Física
  Maria -------- História
```

Exemplos: alunos e disciplinas, pessoas e empregos, clientes e produtos.

### DAG (Grafo Acíclico Direcionado)
Um grafo **direcionado sem ciclos**. É usado para representar **dependências**: tarefas que precisam ser feitas antes de outras, pré-requisitos de disciplinas, ordem de compilação de arquivos.

```
Cálculo I --> Cálculo II --> Equações Diferenciais
    |
    +-------> Física I
```

### Grafo planar
Um grafo que pode ser desenhado em um plano **sem que as arestas se cruzem**. Mapas de países vizinhos são um exemplo.

---

## 7. Como representar um grafo no computador

Desenhar é ótimo para humanos. Para o computador, precisamos de **estruturas de dados**. Existem três formas principais. Vamos usar o grafo abaixo como exemplo:

```
   0 -------- 1
   |          |
   |          |
   2 -------- 3 -------- 4
```

Vértices: `0, 1, 2, 3, 4`
Arestas: `0-1, 0-2, 1-3, 2-3, 3-4`

### 7.1 Matriz de adjacência

Uma **tabela** de `n × n`. A célula `[i][j]` indica se existe aresta entre `i` e `j` (1 = sim, 0 = não).

```
     0  1  2  3  4
  0  0  1  1  0  0
  1  1  0  0  1  0
  2  1  0  0  1  0
  3  0  1  1  0  1
  4  0  0  0  1  0
```

Para grafos ponderados, em vez de 1 guardamos o **peso** da aresta (e usamos um valor especial, como 0 ou infinito, para "não existe").

**Vantagens:**
- Descobrir se `i` e `j` estão ligados é **instantâneo**.
- Fácil de entender e implementar.

**Desvantagens:**
- Usa **muita memória** (`n²` células), mesmo que haja poucas arestas.
- Listar os vizinhos de um vértice exige percorrer uma linha inteira.

**Observação:** em grafos não direcionados a matriz é **simétrica** (`[i][j] = [j][i]`).

### 7.2 Lista de adjacência

Para **cada vértice**, guardamos uma lista com seus vizinhos.

```
0 → [1, 2]
1 → [0, 3]
2 → [0, 3]
3 → [1, 2, 4]
4 → [3]
```

**Vantagens:**
- Usa memória proporcional ao número de vértices **mais** o número de arestas.
- Listar os vizinhos de um vértice é **rápido**.

**Desvantagens:**
- Verificar se `i` e `j` estão ligados exige percorrer a lista de `i`.

> Na prática, a lista de adjacência é a escolha **mais comum**, pois a maioria dos grafos reais é **esparsa** (tem poucas arestas em relação ao máximo possível).

### 7.3 Lista de arestas

Uma lista simples com todas as arestas.

```
(0,1), (0,2), (1,3), (2,3), (3,4)
```

**Vantagens:** muito simples; útil para algoritmos que ordenam arestas (como o de Kruskal).

**Desvantagens:** descobrir os vizinhos de um vértice exige varrer a lista inteira.

### 7.4 Qual escolher?

| Critério | Matriz | Lista de adjacência | Lista de arestas |
|---|---|---|---|
| Memória | Alta (n²) | Baixa (n + m) | Baixa (m) |
| Existe aresta i-j? | Imediato | Depende do grau | Percorre tudo |
| Listar vizinhos de i | Percorre linha | Imediato | Percorre tudo |
| Melhor para | Grafos densos | Grafos esparsos | Ordenar arestas |

*(n = número de vértices; m = número de arestas)*

---

## 8. Percorrendo um grafo: BFS e DFS

**Percorrer** (ou **visitar**) um grafo significa passar por seus vértices seguindo as arestas. É a base de quase todos os outros algoritmos. Existem duas estratégias principais.

### 8.1 Busca em Largura (BFS, *Breadth-First Search*)

**Ideia:** explorar **em camadas**. Primeiro o vértice inicial, depois todos os seus vizinhos, depois os vizinhos dos vizinhos, e assim por diante.

**Analogia:** uma pedra jogada na água. As ondas se espalham em círculos, ficando cada vez maiores.

**Estrutura usada:** uma **fila** (o primeiro a entrar é o primeiro a sair).

**Pseudocódigo:**

```
função BFS(grafo, inicio):
    criar fila vazia
    criar conjunto "visitados" vazio

    marcar inicio como visitado
    colocar inicio na fila

    enquanto a fila não estiver vazia:
        atual = retirar o primeiro da fila
        processar atual            // por exemplo, imprimir

        para cada vizinho de atual:
            se vizinho não foi visitado:
                marcar vizinho como visitado
                colocar vizinho na fila
```

**Exemplo passo a passo** (começando em `0`, usando o grafo da seção 7):

```
Lista de adjacência:
0 → [1, 2]
1 → [0, 3]
2 → [0, 3]
3 → [1, 2, 4]
4 → [3]
```

| Passo | Atual | Fila após o passo | Visitados |
|---|---|---|---|
| início | - | [0] | {0} |
| 1 | 0 | [1, 2] | {0, 1, 2} |
| 2 | 1 | [2, 3] | {0, 1, 2, 3} |
| 3 | 2 | [3] | {0, 1, 2, 3} |
| 4 | 3 | [4] | {0, 1, 2, 3, 4} |
| 5 | 4 | [] | {0, 1, 2, 3, 4} |

Ordem de visita: `0, 1, 2, 3, 4`.

**Para que serve a BFS:**
- Encontrar o **menor caminho em número de arestas** (em grafos não ponderados).
- Descobrir se dois vértices estão conectados.
- Encontrar componentes conexas.
- Resolver "quantos passos até chegar lá?" (por exemplo, "seis graus de separação").

### 8.2 Busca em Profundidade (DFS, *Depth-First Search*)

**Ideia:** ir **o mais fundo possível** por um caminho. Quando chegar a um beco sem saída, **voltar** e tentar outro caminho.

**Analogia:** explorar um labirinto. Você segue por um corredor até não poder mais, e então volta até a última bifurcação.

**Estrutura usada:** uma **pilha** (o último a entrar é o primeiro a sair) ou **recursão**, que usa a pilha de chamadas do programa.

**Pseudocódigo (versão recursiva):**

```
função DFS(grafo, atual, visitados):
    marcar atual como visitado
    processar atual            // por exemplo, imprimir

    para cada vizinho de atual:
        se vizinho não foi visitado:
            DFS(grafo, vizinho, visitados)
```

**Pseudocódigo (versão com pilha):**

```
função DFS_iterativa(grafo, inicio):
    criar pilha vazia
    criar conjunto "visitados" vazio

    colocar inicio na pilha

    enquanto a pilha não estiver vazia:
        atual = retirar o topo da pilha

        se atual não foi visitado:
            marcar atual como visitado
            processar atual

            para cada vizinho de atual:
                se vizinho não foi visitado:
                    colocar vizinho na pilha
```

**Exemplo** (começando em `0`):

```
0 → visita 1 (primeiro vizinho)
    1 → visita 3 (vizinho de 1 ainda não visitado)
        3 → visita 2
            2 → vizinhos 0 e 3 já visitados, volta
        3 → visita 4
            4 → sem novos vizinhos, volta
```

Ordem de visita: `0, 1, 3, 2, 4`.

**Para que serve a DFS:**
- Detectar **ciclos**.
- Encontrar **componentes conexas**.
- Fazer **ordenação topológica**.
- Resolver labirintos e quebra-cabeças (explorar todas as possibilidades).

### 8.3 BFS ou DFS?

| Característica | BFS | DFS |
|---|---|---|
| Estrutura | Fila | Pilha (ou recursão) |
| Explora | Em camadas (vizinhos primeiro) | Em profundidade (vai fundo) |
| Menor caminho (sem pesos) | Sim | Não garante |
| Uso de memória | Pode ser alto em grafos largos | Pode ser alto em grafos profundos |

> **Dica importante:** em ambas as buscas, é **essencial marcar os vértices visitados**. Sem isso, em um grafo com ciclo o algoritmo ficaria rodando para sempre.

---

## 9. Caminho mais curto

Em muitos problemas, não basta saber **se** existe caminho. Queremos o **melhor** caminho, aquele de **menor custo total**.

### 9.1 Sem pesos: use BFS

Se todas as arestas "custam o mesmo", o menor caminho é o que usa **menos arestas**. A BFS resolve. Basta guardar, para cada vértice, de quem ele foi descoberto, para reconstruir o caminho depois.

### 9.2 Com pesos positivos: algoritmo de Dijkstra

**Ideia:** manter uma **estimativa de distância** da origem até cada vértice e ir **refinando**. A cada passo, escolher o vértice **mais próximo** ainda não finalizado e ver se, passando por ele, conseguimos melhorar as distâncias dos seus vizinhos.

> **Restrição:** o Dijkstra só funciona se **nenhum peso for negativo**.

**Pseudocódigo:**

```
função Dijkstra(grafo, origem):
    para cada vértice v:
        distancia[v] = infinito
        anterior[v] = nenhum
    distancia[origem] = 0

    nao_finalizados = todos os vértices

    enquanto nao_finalizados não estiver vazio:
        u = vértice de nao_finalizados com menor distancia[u]
        remover u de nao_finalizados

        para cada vizinho v de u:
            nova = distancia[u] + peso(u, v)
            se nova < distancia[v]:
                distancia[v] = nova
                anterior[v] = u

    retornar distancia, anterior
```

**Exemplo passo a passo:**

```
        4
   A ------- B
   |         |
 1 |         | 1
   |         |
   C ------- D
        5
```

Origem: `A`. Queremos o menor caminho até `D`.

Estado inicial: `A = 0`, `B = ∞`, `C = ∞`, `D = ∞`

| Passo | Escolhido | Atualizações | Distâncias (A, B, C, D) |
|---|---|---|---|
| 1 | A (0) | B = 4, C = 1 | 0, 4, 1, ∞ |
| 2 | C (1) | D = 1 + 5 = 6 | 0, 4, 1, 6 |
| 3 | B (4) | D = 4 + 1 = 5 (melhor que 6) | 0, 4, 1, 5 |
| 4 | D (5) | nada a atualizar | 0, 4, 1, 5 |

Menor caminho de `A` até `D`: `A → B → D`, com custo **5**.

Para reconstruir o caminho, seguimos o vetor `anterior` **de trás para frente**: `D ← B ← A`.

### 9.3 Com pesos negativos: Bellman-Ford

Se existirem arestas com peso negativo, use o **algoritmo de Bellman-Ford**. Ele é mais lento, mas lida com pesos negativos e ainda **detecta ciclos negativos** (ciclos cuja soma é negativa, que fariam o "menor caminho" diminuir indefinidamente).

### 9.4 Todos contra todos: Floyd-Warshall

Para calcular o menor caminho entre **todos os pares** de vértices de uma só vez, existe o **algoritmo de Floyd-Warshall**.

### 9.5 Resumo

| Situação | Algoritmo |
|---|---|
| Sem pesos | BFS |
| Pesos não negativos, uma origem | Dijkstra |
| Pesos podem ser negativos, uma origem | Bellman-Ford |
| Todos os pares de vértices | Floyd-Warshall |

---

## 10. Árvore geradora mínima

**Problema:** você precisa conectar várias cidades com cabos de internet. Cada ligação possível tem um custo. Como conectar **todas** as cidades gastando o **mínimo possível**?

A resposta é uma **árvore geradora mínima** (AGM, ou MST, do inglês *Minimum Spanning Tree*):

- **Geradora:** inclui todos os vértices.
- **Árvore:** conexa e sem ciclos (ou seja, sem cabo desperdiçado).
- **Mínima:** a soma dos pesos das arestas é a menor possível.

Funciona para grafos **não direcionados, ponderados e conexos**.

### 10.1 Algoritmo de Kruskal

**Ideia:** ordenar todas as arestas da **mais barata para a mais cara** e ir escolhendo, **pulando** as que criariam um ciclo.

```
função Kruskal(grafo):
    arvore = conjunto vazio de arestas
    ordenar todas as arestas por peso, do menor ao maior

    para cada aresta (u, v) na ordem:
        se u e v estão em componentes diferentes:
            adicionar (u, v) à arvore
            unir as componentes de u e v

    retornar arvore
```

> Para saber rapidamente se `u` e `v` já estão na mesma componente, costuma-se usar uma estrutura chamada **União-Busca** (*Union-Find*).

**Exemplo:**

```
        2           3
   A ------- B ------- C
   |         |         |
 6 |       1 |         | 4
   |         |         |
   D ------- E ------- F
        5           7
```

Arestas ordenadas: `B-E (1)`, `A-B (2)`, `B-C (3)`, `C-F (4)`, `D-E (5)`, `A-D (6)`, `E-F (7)`

| Aresta | Peso | Decisão |
|---|---|---|
| B-E | 1 | escolhe |
| A-B | 2 | escolhe |
| B-C | 3 | escolhe |
| C-F | 4 | escolhe |
| D-E | 5 | escolhe |
| A-D | 6 | **descarta** (A e D já estão conectados, criaria ciclo) |
| E-F | 7 | **descarta** (criaria ciclo) |

Custo total: 1 + 2 + 3 + 4 + 5 = **15**. Com 6 vértices, a árvore tem 5 arestas. ✔

### 10.2 Algoritmo de Prim

**Ideia:** começar em um vértice e ir **crescendo** a árvore, sempre adicionando a **aresta mais barata** que liga a árvore atual a um vértice novo.

```
função Prim(grafo, inicio):
    na_arvore = {inicio}
    arvore = conjunto vazio de arestas

    enquanto na_arvore não contém todos os vértices:
        escolher a aresta (u, v) de menor peso
            tal que u está em na_arvore e v NÃO está
        adicionar v a na_arvore
        adicionar (u, v) à arvore

    retornar arvore
```

### 10.3 Kruskal ou Prim?

Ambos produzem uma árvore geradora de **custo mínimo**. Kruskal pensa em **arestas** (ordena todas), enquanto Prim pensa em **vértices** (cresce a partir de um ponto). Podem produzir árvores diferentes quando há arestas de mesmo peso, mas o **custo total** é sempre o mesmo.

---

## 11. Ordenação topológica

**Problema:** você tem tarefas com dependências. Em que **ordem** executá-las?

Exemplo: para fazer um bolo.

```
Comprar ingredientes --> Misturar --> Assar --> Servir
Pré-aquecer forno -----------------> Assar
```

Uma **ordenação topológica** é uma lista de todos os vértices de um **DAG** tal que, para toda aresta `A → B`, o vértice `A` aparece **antes** de `B`.

Uma ordem válida: `Comprar ingredientes, Pré-aquecer forno, Misturar, Assar, Servir`.

> Só é possível se o grafo for um **DAG**. Se houver ciclo (A depende de B, que depende de A), não existe ordem válida.

### Algoritmo de Kahn

**Ideia:** repetidamente pegar um vértice **sem dependências pendentes** (grau de entrada 0), colocá-lo na ordem e "removê-lo" do grafo.

```
função OrdenacaoTopologica(grafo):
    calcular grau_de_entrada de cada vértice
    fila = todos os vértices com grau_de_entrada = 0
    ordem = lista vazia

    enquanto a fila não estiver vazia:
        u = retirar o primeiro da fila
        adicionar u à ordem

        para cada vizinho v de u:
            grau_de_entrada[v] = grau_de_entrada[v] - 1
            se grau_de_entrada[v] = 0:
                colocar v na fila

    se tamanho(ordem) < número de vértices:
        erro: o grafo tem ciclo
    senão:
        retornar ordem
```

**Aplicações:** planejamento de tarefas, ordem de compilação de programas, resolução de pré-requisitos, instalação de pacotes de software.

---

## 12. Problemas clássicos

### As sete pontes de Königsberg (1736)

A cidade de Königsberg tinha **sete pontes** ligando quatro regiões. Perguntava-se: é possível fazer um passeio que atravesse **cada ponte exatamente uma vez**?

O matemático **Leonhard Euler** modelou o problema como um grafo (regiões = vértices, pontes = arestas) e provou que **não é possível**. Esse é considerado o **nascimento da Teoria dos Grafos**.

**Resultado de Euler:** um grafo conexo possui um **circuito euleriano** (percorre todas as arestas uma única vez e volta ao início) se, e somente se, **todos os vértices têm grau par**.

### Caminho hamiltoniano

Um caminho que passa por **todos os vértices exatamente uma vez**. Parece parecido com o euleriano, mas é muito mais difícil: não se conhece um método eficiente para decidir se um grafo qualquer possui um.

### Problema do Caixeiro Viajante

Um vendedor precisa visitar várias cidades, passando por cada uma uma vez e voltando ao início, **com o menor custo total**. É um problema famoso por ser **muito difícil** (da classe NP-difícil): para muitas cidades, ninguém conhece uma forma rápida de achar a solução perfeita.

### Coloração de grafos

Pintar os vértices com o **menor número de cores** possível, de modo que **vértices adjacentes tenham cores diferentes**.

Aplicação: montar uma grade de horários. Cada vértice é uma prova, cada aresta indica "têm alunos em comum", e cada cor é um horário. O objetivo é usar o mínimo de horários sem conflito.

**Teorema das Quatro Cores:** qualquer mapa (grafo planar) pode ser colorido com no máximo **4 cores**.

### Fluxo máximo

Dada uma rede com capacidades nas arestas (como tubulações de água), qual é o **maior fluxo** possível de uma origem até um destino? É usado em redes de transporte, telecomunicações e alocação de recursos.

### Emparelhamento (matching)

Em um grafo bipartido, formar o maior número de **pares** sem repetir ninguém. Exemplo: associar candidatos a vagas de emprego.

---

## 13. Resumo de complexidades

Seja `n` o número de vértices e `m` o número de arestas.

| Algoritmo | Complexidade (com lista de adjacência) |
|---|---|
| BFS | O(n + m) |
| DFS | O(n + m) |
| Ordenação topológica (Kahn) | O(n + m) |
| Dijkstra (com fila de prioridade) | O((n + m) log n) |
| Bellman-Ford | O(n × m) |
| Floyd-Warshall | O(n³) |
| Kruskal | O(m log m) |
| Prim (com fila de prioridade) | O(m log n) |

> **O que significa O(...)?** É uma forma de medir **quanto o tempo cresce** conforme a entrada aumenta. Quanto menor a expressão, mais eficiente o algoritmo. Não se preocupe em decorar: o importante agora é entender as ideias.

---

## 14. Exercícios

### Nível 1: Fixando o vocabulário

**1.** Considere o grafo abaixo.

```
   A ------ B
   |        |
   C ------ D ------ E
```

a) Quais são os vértices e as arestas?
b) Qual é o grau de cada vértice?
c) Existe um caminho de `A` até `E`? Cite um.
d) O grafo é conexo? Por quê?
e) Existe algum ciclo? Qual?

**2.** Desenhe um grafo **não direcionado** com 4 vértices que seja uma **árvore**. Quantas arestas ele tem?

**3.** Quantas arestas tem um grafo **completo** com 6 vértices?

**4.** Em um grafo com 5 arestas, qual é a soma dos graus de todos os vértices?

### Nível 2: Representações

**5.** Escreva a **matriz de adjacência** e a **lista de adjacência** do grafo abaixo (vértices `0, 1, 2, 3`).

```
   0 ------ 1
   |      / |
   |    /   |
   |  /     |
   2 ------ 3
```

Arestas: `0-1, 0-2, 1-2, 1-3, 2-3`.

**6.** Por que a matriz de adjacência de um grafo **não direcionado** é simétrica? E a de um grafo **direcionado**, costuma ser?

### Nível 3: Algoritmos

**7.** Aplique a **BFS** ao grafo do exercício 5, começando em `0`. Escreva a ordem de visita (considere os vizinhos em ordem crescente).

**8.** Aplique a **DFS** ao mesmo grafo, começando em `0`. Escreva a ordem de visita.

**9.** Aplique o **Dijkstra** ao grafo ponderado abaixo, com origem em `S`. Qual é o menor custo até `T`?

```
        7            2
   S ------- A ------------ T
   |         |              |
 2 |       3 |              | 5
   |         |              |
   B ------- C ------------ +
        1          
```

Arestas: `S-A (7)`, `S-B (2)`, `A-C (3)`, `A-T (2)`, `B-C (1)`, `C-T (5)`.

**10.** Dado o DAG de tarefas abaixo, escreva uma **ordenação topológica** válida.

```
Acordar --> Tomar café --> Sair de casa --> Trabalhar
Acordar --> Tomar banho --> Sair de casa
```

### Nível 4: Pensando

**11.** Explique, com suas palavras, por que a BFS encontra o menor caminho em grafos **não ponderados**, mas a DFS não garante isso.

**12.** Por que é necessário marcar os vértices como visitados durante uma busca?

**13.** Dê **três exemplos** do seu dia a dia que podem ser modelados como grafos. Para cada um, diga quem são os vértices e quem são as arestas, e se o grafo seria direcionado ou não.

---

## 15. Respostas dos exercícios

**1.**
a) Vértices: `A, B, C, D, E`. Arestas: `A-B, A-C, B-D, C-D, D-E`.
b) `A = 2`, `B = 2`, `C = 2`, `D = 3`, `E = 1`.
c) Sim. Por exemplo: `A → B → D → E`.
d) Sim, é conexo, pois existe caminho entre qualquer par de vértices.
e) Sim: `A → B → D → C → A`.

**2.** Exemplo: `A-B, B-C, B-D`. Uma árvore com 4 vértices tem **3 arestas** (`n - 1`).

**3.** `6 × 5 / 2 = ` **15 arestas**.

**4.** **10** (o dobro do número de arestas).

**5.**

Matriz de adjacência:

```
     0  1  2  3
  0  0  1  1  0
  1  1  0  1  1
  2  1  1  0  1
  3  0  1  1  0
```

Lista de adjacência:

```
0 → [1, 2]
1 → [0, 2, 3]
2 → [0, 1, 3]
3 → [1, 2]
```

**6.** Em um grafo não direcionado, se existe aresta de `i` para `j`, existe também de `j` para `i`; logo `[i][j] = [j][i]`. Em grafos direcionados, a matriz **em geral não é simétrica**, pois `i → j` não implica `j → i`.

**7.** BFS a partir de `0`: **`0, 1, 2, 3`**.
(Fila: `[0]` → visita 0, enfileira 1 e 2 → visita 1, enfileira 3 → visita 2 → visita 3.)

**8.** DFS a partir de `0`: **`0, 1, 2, 3`**.
(De `0` vai para `1`; de `1` vai para `2`; de `2` vai para `3`.)

**9.** Passo a passo (origem `S`):

| Passo | Escolhido | Atualizações | Distâncias (S, A, B, C, T) |
|---|---|---|---|
| 1 | S (0) | A = 7, B = 2 | 0, 7, 2, ∞, ∞ |
| 2 | B (2) | C = 2 + 1 = 3 | 0, 7, 2, 3, ∞ |
| 3 | C (3) | A = 3 + 3 = 6 (melhora de 7), T = 3 + 5 = 8 | 0, 6, 2, 3, 8 |
| 4 | A (6) | T = 6 + 2 = 8 (empata) | 0, 6, 2, 3, 8 |
| 5 | T (8) | - | 0, 6, 2, 3, 8 |

Menor custo até `T`: **8**. Um caminho: `S → B → C → T` (custo 2 + 1 + 5 = 8). Outro caminho também de custo 8: `S → B → C → A → T` (2 + 1 + 3 + 2 = 8).

**10.** Uma ordem válida: `Acordar, Tomar café, Tomar banho, Sair de casa, Trabalhar`. (A ordem de `Tomar café` e `Tomar banho` pode ser trocada.)

**11.** A BFS explora **em camadas**: primeiro tudo a 1 aresta de distância, depois a 2, e assim por diante. Assim, a primeira vez que ela alcança um vértice é necessariamente pelo caminho com **menos arestas**. A DFS segue um caminho até o fim antes de tentar outros, então pode chegar ao destino por um caminho longo e tortuoso.

**12.** Porque, se o grafo tiver **ciclos**, sem a marcação o algoritmo voltaria a vértices já visitados e ficaria **repetindo indefinidamente**. A marcação também evita trabalho repetido.

**13.** Respostas livres. Exemplos:
- *Rede de amigos no celular:* vértices = pessoas, arestas = amizade (não direcionado).
- *Trajeto de casa até a faculdade:* vértices = cruzamentos, arestas = ruas (direcionado se houver mão única).
- *Quem segue quem em uma rede social:* vértices = perfis, arestas = "segue" (direcionado).

---

## 16. Glossário

| Termo | Significado |
|---|---|
| **Grafo** | Conjunto de vértices e arestas que representam objetos e suas ligações |
| **Vértice / Nó** | Cada "ponto" do grafo |
| **Aresta / Arco** | Ligação entre dois vértices |
| **Adjacente / Vizinho** | Vértice ligado diretamente a outro |
| **Grau** | Número de arestas que tocam um vértice |
| **Direcionado (dígrafo)** | Grafo cujas arestas têm sentido |
| **Ponderado** | Grafo cujas arestas têm pesos |
| **Laço** | Aresta que liga um vértice a ele mesmo |
| **Caminho** | Sequência de vértices ligados por arestas |
| **Ciclo** | Caminho que volta ao vértice de origem |
| **Conexo** | Existe caminho entre qualquer par de vértices |
| **Componente conexa** | Pedaço de um grafo em que todos os vértices se alcançam |
| **Árvore** | Grafo conexo e sem ciclos |
| **DAG** | Grafo direcionado sem ciclos |
| **Bipartido** | Vértices divididos em dois grupos, com arestas só entre grupos |
| **Grafo completo** | Todos os vértices ligados entre si |
| **BFS** | Busca em largura (por camadas, usa fila) |
| **DFS** | Busca em profundidade (vai fundo, usa pilha ou recursão) |
| **AGM / MST** | Árvore geradora mínima |
| **Esparso / Denso** | Poucas arestas / muitas arestas em relação ao máximo possível |

---

## 17. Para ir além

Depois de dominar este material, você pode estudar:

- **Implementação** dos algoritmos na linguagem de sua escolha (comece pela lista de adjacência, BFS e DFS).
- **Componentes fortemente conexas** (algoritmos de Tarjan e Kosaraju).
- **Fluxo em redes** (Ford-Fulkerson e Edmonds-Karp).
- **A\*** (A-estrela): variação do Dijkstra usada em jogos e navegação.
- **Teoria dos Grafos avançada:** planaridade, isomorfismo, grafos aleatórios.
- **Grafos em bancos de dados** (bancos orientados a grafos).
- **Redes complexas e análise de redes sociais** (centralidade, comunidades).

### Dica de estudo

1. **Desenhe** os grafos no papel antes de pensar em código.
2. **Execute os algoritmos à mão** em grafos pequenos, anotando cada passo em uma tabela.
3. Só depois **implemente**.
4. Teste com grafos **diferentes**: com ciclos, desconexos, com um único vértice, vazios.

> Grafos são, antes de tudo, uma **forma de pensar**: transformar um problema em "coisas e ligações". Quando você aprende a enxergar assim, vê grafos em todo lugar.
