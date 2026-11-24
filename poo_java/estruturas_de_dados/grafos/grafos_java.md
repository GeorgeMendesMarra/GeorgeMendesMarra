# Grafos em Java: Exemplos Comentados

> Este material complementa o `grafos.md` do Prof. George Mendes Marra. Cada exemplo do texto original (vocabulário, representações, BFS, DFS, Dijkstra, Kruskal, Prim, ordenação topológica e problemas clássicos) aparece aqui como um **programa Java completo e comentado**, usando os mesmos grafos do material.

---

## Como executar

Todos os programas são independentes. Requisito: **Java 17 ou superior** (alguns usam `record`).

**Opção 1: modo arquivo único (sem compilar antes)**

```bash
java Exemplo06_BFS.java
```

**Opção 2: compilar e executar**

```bash
javac Exemplo06_BFS.java
java Exemplo06_BFS
```

> O nome do arquivo deve ser igual ao nome da classe pública (por exemplo, `Exemplo06_BFS.java`).

---

## Sumário

1. [Vértices, arestas, graus e grafo completo](#1-vértices-arestas-graus-e-grafo-completo)
2. [Matriz de adjacência](#2-matriz-de-adjacência)
3. [Lista de adjacência](#3-lista-de-adjacência)
4. [Lista de arestas](#4-lista-de-arestas)
5. [Grafo direcionado: grau de entrada e de saída](#5-grafo-direcionado-grau-de-entrada-e-de-saída)
6. [Busca em largura (BFS)](#6-busca-em-largura-bfs)
7. [Busca em profundidade (DFS)](#7-busca-em-profundidade-dfs)
8. [Componentes conexas e verificação de árvore](#8-componentes-conexas-e-verificação-de-árvore)
9. [Detecção de ciclo](#9-detecção-de-ciclo)
10. [Dijkstra](#10-dijkstra)
11. [Bellman-Ford](#11-bellman-ford)
12. [Floyd-Warshall](#12-floyd-warshall)
13. [Kruskal (árvore geradora mínima)](#13-kruskal-árvore-geradora-mínima)
14. [Prim (árvore geradora mínima)](#14-prim-árvore-geradora-mínima)
15. [Ordenação topológica (Kahn)](#15-ordenação-topológica-kahn)
16. [Grafo bipartido](#16-grafo-bipartido)
17. [Circuito euleriano e as pontes de Königsberg](#17-circuito-euleriano-e-as-pontes-de-königsberg)
18. [Coloração de grafos](#18-coloração-de-grafos)
19. [Exercícios resolvidos (5, 7 e 8)](#19-exercícios-resolvidos-5-7-e-8)
20. [Resumo dos programas](#20-resumo-dos-programas)

---

## 1. Vértices, arestas, graus e grafo completo

Grafo da seção 1 do material:

```
   A -------- B
   |          |
   C -------- D -------- E
```

O programa calcula o **grau** de cada vértice e confirma o **Lema do Aperto de Mãos** (soma dos graus = 2 × número de arestas). Também calcula o número de arestas de um **grafo completo**.

**Arquivo:** `Exemplo01_VerticesArestasGraus.java`

```java
import java.util.*;

public class Exemplo01_VerticesArestasGraus {

    public static void main(String[] args) {
        // V = {A, B, C, D, E}
        String[] vertices = {"A", "B", "C", "D", "E"};

        // E = {(A,B), (A,C), (B,D), (C,D), (D,E)}
        String[][] arestas = {
            {"A", "B"}, {"A", "C"}, {"B", "D"}, {"C", "D"}, {"D", "E"}
        };

        // Mapa que guarda o grau de cada vértice.
        // LinkedHashMap mantém a ordem de inserção (A, B, C, D, E).
        Map<String, Integer> grau = new LinkedHashMap<>();
        for (String v : vertices) {
            grau.put(v, 0); // todo vértice começa com grau 0
        }

        // Cada aresta "toca" dois vértices, então soma 1 ao grau de ambos.
        for (String[] aresta : arestas) {
            grau.merge(aresta[0], 1, Integer::sum);
            grau.merge(aresta[1], 1, Integer::sum);
        }

        // Imprime o grau de cada vértice e acumula a soma dos graus.
        int somaDosGraus = 0;
        for (Map.Entry<String, Integer> entrada : grau.entrySet()) {
            System.out.println("Grau de " + entrada.getKey() + " = " + entrada.getValue());
            somaDosGraus += entrada.getValue();
        }

        // Lema do Aperto de Mãos: soma dos graus = 2 * |E|
        System.out.println("\nSoma dos graus = " + somaDosGraus);
        System.out.println("2 x numero de arestas = " + (2 * arestas.length));

        // Grafo completo com n vértices tem n * (n - 1) / 2 arestas.
        System.out.println("\nArestas de um grafo completo:");
        for (int n : new int[]{4, 6}) {
            System.out.println("  n = " + n + " -> " + (n * (n - 1) / 2) + " arestas");
        }
    }
}
```

**Saída esperada:**

```
Grau de A = 2
Grau de B = 2
Grau de C = 2
Grau de D = 3
Grau de E = 1

Soma dos graus = 10
2 x numero de arestas = 10

Arestas de um grafo completo:
  n = 4 -> 6 arestas
  n = 6 -> 15 arestas
```

---

## 2. Matriz de adjacência

Grafo da seção 7 do material (vértices `0` a `4`):

```
   0 -------- 1
   |          |
   2 -------- 3 -------- 4
```

**Arquivo:** `Exemplo02_MatrizAdjacencia.java`

```java
import java.util.*;

public class Exemplo02_MatrizAdjacencia {

    private final int[][] matriz;     // matriz[i][j] = 1 se existe aresta i -> j
    private final boolean direcionado;

    public Exemplo02_MatrizAdjacencia(int n, boolean direcionado) {
        this.matriz = new int[n][n];  // Java inicializa tudo com 0 (sem arestas)
        this.direcionado = direcionado;
    }

    // Adiciona uma aresta u-v.
    public void adicionarAresta(int u, int v) {
        matriz[u][v] = 1;
        // Em grafo NÃO direcionado a aresta vale nos dois sentidos,
        // por isso a matriz fica simétrica.
        if (!direcionado) {
            matriz[v][u] = 1;
        }
    }

    // Consulta instantânea: O(1).
    public boolean existeAresta(int u, int v) {
        return matriz[u][v] == 1;
    }

    // Listar vizinhos exige percorrer a linha inteira: O(n).
    public List<Integer> vizinhos(int u) {
        List<Integer> lista = new ArrayList<>();
        for (int v = 0; v < matriz.length; v++) {
            if (matriz[u][v] == 1) {
                lista.add(v);
            }
        }
        return lista;
    }

    // Verifica se matriz[i][j] == matriz[j][i] para todo par.
    public boolean ehSimetrica() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void imprimir() {
        // Cabeçalho com os índices das colunas
        System.out.print("   ");
        for (int j = 0; j < matriz.length; j++) {
            System.out.print(j + "  ");
        }
        System.out.println();
        // Cada linha da matriz
        for (int i = 0; i < matriz.length; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Exemplo02_MatrizAdjacencia g = new Exemplo02_MatrizAdjacencia(5, false);

        // Arestas: 0-1, 0-2, 1-3, 2-3, 3-4
        g.adicionarAresta(0, 1);
        g.adicionarAresta(0, 2);
        g.adicionarAresta(1, 3);
        g.adicionarAresta(2, 3);
        g.adicionarAresta(3, 4);

        g.imprimir();

        System.out.println("\nExiste aresta 0-1? " + g.existeAresta(0, 1)); // true
        System.out.println("Existe aresta 0-3? " + g.existeAresta(0, 3));   // false
        System.out.println("Vizinhos de 3: " + g.vizinhos(3));              // [1, 2, 4]
        System.out.println("A matriz é simétrica? " + g.ehSimetrica());     // true
    }
}
```

**Saída esperada:**

```
   0  1  2  3  4  
0  0  1  1  0  0  
1  1  0  0  1  0  
2  1  0  0  1  0  
3  0  1  1  0  1  
4  0  0  0  1  0  

Existe aresta 0-1? true
Existe aresta 0-3? false
Vizinhos de 3: [1, 2, 4]
A matriz é simétrica? true
```

---

## 3. Lista de adjacência

Mesmo grafo da seção anterior. Para cada vértice guardamos a lista de seus vizinhos. É a representação **mais usada na prática**.

**Arquivo:** `Exemplo03_ListaAdjacencia.java`

```java
import java.util.*;

public class Exemplo03_ListaAdjacencia {

    // adj.get(u) é a lista de vizinhos do vértice u.
    private final List<List<Integer>> adj = new ArrayList<>();
    private final boolean direcionado;

    public Exemplo03_ListaAdjacencia(int n, boolean direcionado) {
        this.direcionado = direcionado;
        // Cria uma lista vazia para cada um dos n vértices.
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(int u, int v) {
        adj.get(u).add(v);
        if (!direcionado) {
            adj.get(v).add(u); // no não direcionado, registra nos dois sentidos
        }
    }

    // Listar vizinhos é imediato.
    public List<Integer> vizinhos(int u) {
        return adj.get(u);
    }

    // Verificar uma aresta depende do tamanho da lista (grau do vértice).
    public boolean existeAresta(int u, int v) {
        return adj.get(u).contains(v);
    }

    public void imprimir() {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println(i + " -> " + adj.get(i));
        }
    }

    public static void main(String[] args) {
        Exemplo03_ListaAdjacencia g = new Exemplo03_ListaAdjacencia(5, false);

        g.adicionarAresta(0, 1);
        g.adicionarAresta(0, 2);
        g.adicionarAresta(1, 3);
        g.adicionarAresta(2, 3);
        g.adicionarAresta(3, 4);

        g.imprimir();

        System.out.println("\nVizinhos de 3: " + g.vizinhos(3));
        System.out.println("Existe aresta 1-3? " + g.existeAresta(1, 3)); // true
        System.out.println("Existe aresta 1-4? " + g.existeAresta(1, 4)); // false
    }
}
```

**Saída esperada:**

```
0 -> [1, 2]
1 -> [0, 3]
2 -> [0, 3]
3 -> [1, 2, 4]
4 -> [3]

Vizinhos de 3: [1, 2, 4]
Existe aresta 1-3? true
Existe aresta 1-4? false
```

---

## 4. Lista de arestas

Uma lista simples com todas as arestas. É útil para algoritmos que **ordenam arestas**, como o de Kruskal. Aqui as arestas já têm **peso**.

**Arquivo:** `Exemplo04_ListaDeArestas.java`

```java
import java.util.*;

public class Exemplo04_ListaDeArestas {

    // "record" cria automaticamente construtor, getters, equals e toString.
    record Aresta(int origem, int destino, int peso) {}

    public static void main(String[] args) {
        List<Aresta> arestas = new ArrayList<>();

        // Grafo da seção 7, agora com pesos (peso 1 em todas, como no não ponderado).
        arestas.add(new Aresta(0, 1, 1));
        arestas.add(new Aresta(0, 2, 1));
        arestas.add(new Aresta(1, 3, 1));
        arestas.add(new Aresta(2, 3, 1));
        arestas.add(new Aresta(3, 4, 1));

        System.out.println("Todas as arestas:");
        for (Aresta a : arestas) {
            System.out.println("  " + a);
        }

        // Para achar os vizinhos de um vértice precisamos varrer a lista toda.
        int alvo = 3;
        System.out.print("\nVizinhos de " + alvo + ": ");
        for (Aresta a : arestas) {
            if (a.origem() == alvo) System.out.print(a.destino() + " ");
            if (a.destino() == alvo) System.out.print(a.origem() + " ");
        }
        System.out.println();

        // Vantagem: ordenar por peso é trivial (Kruskal usa isso).
        arestas.sort(Comparator.comparingInt(Aresta::peso));
        System.out.println("\nOrdenadas por peso: " + arestas.get(0) + " ...");
    }
}
```

**Saída esperada:**

```
Todas as arestas:
  Aresta[origem=0, destino=1, peso=1]
  Aresta[origem=0, destino=2, peso=1]
  Aresta[origem=1, destino=3, peso=1]
  Aresta[origem=2, destino=3, peso=1]
  Aresta[origem=3, destino=4, peso=1]

Vizinhos de 3: 1 2 4 

Ordenadas por peso: Aresta[origem=0, destino=1, peso=1] ...
```

---

## 5. Grafo direcionado: grau de entrada e de saída

Em um dígrafo o grau se divide em **grau de saída** (setas que saem) e **grau de entrada** (setas que chegam). Usamos o DAG de dependências de disciplinas da seção 6.

```
Cálculo I --> Cálculo II --> Equações Diferenciais
    |
    +-------> Física I
```

**Arquivo:** `Exemplo05_Digrafo.java`

```java
import java.util.*;

public class Exemplo05_Digrafo {

    public static void main(String[] args) {
        String[] nomes = {"Calculo I", "Calculo II", "Equacoes Diferenciais", "Fisica I"};

        // Cada par {a, b} representa a seta a -> b.
        int[][] arestas = {
            {0, 1}, // Cálculo I  -> Cálculo II
            {1, 2}, // Cálculo II -> Equações Diferenciais
            {0, 3}  // Cálculo I  -> Física I
        };

        int n = nomes.length;
        int[] grauSaida = new int[n];
        int[] grauEntrada = new int[n];

        for (int[] a : arestas) {
            grauSaida[a[0]]++;   // a seta SAI de a[0]
            grauEntrada[a[1]]++; // e CHEGA em a[1]
        }

        System.out.printf("%-24s %8s %8s%n", "Disciplina", "Entrada", "Saida");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-24s %8d %8d%n", nomes[i], grauEntrada[i], grauSaida[i]);
        }

        // Vértices com grau de entrada 0 não têm pré-requisitos.
        System.out.print("\nSem pre-requisitos: ");
        for (int i = 0; i < n; i++) {
            if (grauEntrada[i] == 0) System.out.print(nomes[i] + " ");
        }
        System.out.println();
    }
}
```

**Saída esperada:**

```
Disciplina                Entrada    Saida
Calculo I                       0        2
Calculo II                      1        1
Equacoes Diferenciais           1        0
Fisica I                        1        0

Sem pre-requisitos: Calculo I 
```

---

## 6. Busca em largura (BFS)

Explora **em camadas**, usando uma **fila**. Aqui também reconstruímos o **menor caminho** (em número de arestas) usando o vetor `anterior`. Grafo da seção 7, começando em `0`.

**Arquivo:** `Exemplo06_BFS.java`

```java
import java.util.*;

public class Exemplo06_BFS {

    // Monta a lista de adjacência de um grafo NÃO direcionado.
    static List<List<Integer>> criarGrafo(int n, int[][] arestas) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        return adj;
    }

    // Retorna a ordem de visita. Preenche "anterior" com o pai de cada vértice.
    static List<Integer> bfs(List<List<Integer>> adj, int inicio, int[] anterior) {
        boolean[] visitado = new boolean[adj.size()];
        Queue<Integer> fila = new ArrayDeque<>(); // fila: primeiro a entrar, primeiro a sair
        List<Integer> ordem = new ArrayList<>();
        Arrays.fill(anterior, -1);                // -1 significa "sem pai"

        visitado[inicio] = true;                  // marca ao ENFILEIRAR, não ao retirar
        fila.add(inicio);

        while (!fila.isEmpty()) {
            int atual = fila.poll();              // retira o primeiro da fila
            ordem.add(atual);                     // "processar" o vértice
            System.out.println("Visitando " + atual + " | fila agora: " + fila);

            for (int vizinho : adj.get(atual)) {
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;     // evita enfileirar duas vezes
                    anterior[vizinho] = atual;    // guarda de quem foi descoberto
                    fila.add(vizinho);
                }
            }
        }
        return ordem;
    }

    public static void main(String[] args) {
        int[][] arestas = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}};
        List<List<Integer>> adj = criarGrafo(5, arestas);

        int[] anterior = new int[5];
        List<Integer> ordem = bfs(adj, 0, anterior);
        System.out.println("\nOrdem de visita: " + ordem); // [0, 1, 2, 3, 4]

        // Reconstrói o menor caminho de 0 até 4, andando de trás para frente.
        LinkedList<Integer> caminho = new LinkedList<>();
        for (int v = 4; v != -1; v = anterior[v]) {
            caminho.addFirst(v);
        }
        System.out.println("Menor caminho de 0 ate 4: " + caminho
                + " (comprimento " + (caminho.size() - 1) + ")");
    }
}
```

**Saída esperada:**

```
Visitando 0 | fila agora: []
Visitando 1 | fila agora: [2]
Visitando 2 | fila agora: [3]
Visitando 3 | fila agora: []
Visitando 4 | fila agora: []

Ordem de visita: [0, 1, 2, 3, 4]
Menor caminho de 0 ate 4: [0, 1, 3, 4] (comprimento 3)
```

---

## 7. Busca em profundidade (DFS)

Vai **o mais fundo possível** e depois volta. Mostramos as duas versões: **recursiva** (usa a pilha de chamadas) e **iterativa** (usa uma pilha explícita).

**Arquivo:** `Exemplo07_DFS.java`

```java
import java.util.*;

public class Exemplo07_DFS {

    static List<List<Integer>> criarGrafo(int n, int[][] arestas) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        return adj;
    }

    // ---------- Versão recursiva ----------
    static void dfsRecursiva(List<List<Integer>> adj, int atual,
                             boolean[] visitado, List<Integer> ordem) {
        visitado[atual] = true;   // marca como visitado
        ordem.add(atual);         // "processar" o vértice

        for (int vizinho : adj.get(atual)) {
            if (!visitado[vizinho]) {
                // Mergulha no vizinho. Quando a chamada terminar,
                // o programa "volta" para este ponto (backtracking).
                dfsRecursiva(adj, vizinho, visitado, ordem);
            }
        }
    }

    // ---------- Versão iterativa (com pilha) ----------
    static List<Integer> dfsIterativa(List<List<Integer>> adj, int inicio) {
        boolean[] visitado = new boolean[adj.size()];
        Deque<Integer> pilha = new ArrayDeque<>(); // pilha: último a entrar, primeiro a sair
        List<Integer> ordem = new ArrayList<>();

        pilha.push(inicio);
        while (!pilha.isEmpty()) {
            int atual = pilha.pop();
            if (visitado[atual]) continue;   // pode ter sido empilhado mais de uma vez

            visitado[atual] = true;
            ordem.add(atual);

            // Empilhamos os vizinhos em ORDEM INVERSA para que o primeiro
            // vizinho seja o primeiro a sair da pilha (mesma ordem da recursiva).
            List<Integer> vizinhos = adj.get(atual);
            for (int i = vizinhos.size() - 1; i >= 0; i--) {
                int v = vizinhos.get(i);
                if (!visitado[v]) pilha.push(v);
            }
        }
        return ordem;
    }

    public static void main(String[] args) {
        int[][] arestas = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}};
        List<List<Integer>> adj = criarGrafo(5, arestas);

        List<Integer> ordemRec = new ArrayList<>();
        dfsRecursiva(adj, 0, new boolean[5], ordemRec);
        System.out.println("DFS recursiva: " + ordemRec); // [0, 1, 3, 2, 4]

        System.out.println("DFS iterativa: " + dfsIterativa(adj, 0)); // [0, 1, 3, 2, 4]
    }
}
```

**Saída esperada:**

```
DFS recursiva: [0, 1, 3, 2, 4]
DFS iterativa: [0, 1, 3, 2, 4]
```

---

## 8. Componentes conexas e verificação de árvore

Usa a BFS para rotular as **componentes conexas** do grafo desconexo da seção 5. Em seguida verifica se um grafo é uma **árvore** (conexo e com `n - 1` arestas).

```
Desconexo:
A --- B        D --- E
|
C              F
```

**Arquivo:** `Exemplo08_ComponentesConexas.java`

```java
import java.util.*;

public class Exemplo08_ComponentesConexas {

    static List<List<Integer>> criarGrafo(int n, int[][] arestas) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        return adj;
    }

    // Devolve um vetor onde id[v] é o número da componente de v.
    static int[] rotularComponentes(List<List<Integer>> adj) {
        int n = adj.size();
        int[] id = new int[n];
        Arrays.fill(id, -1);          // -1 = ainda não visitado
        int atual = 0;

        for (int inicio = 0; inicio < n; inicio++) {
            if (id[inicio] != -1) continue;  // já pertence a alguma componente

            // Nova componente: faz uma BFS a partir de "inicio".
            Queue<Integer> fila = new ArrayDeque<>();
            id[inicio] = atual;
            fila.add(inicio);
            while (!fila.isEmpty()) {
                int u = fila.poll();
                for (int v : adj.get(u)) {
                    if (id[v] == -1) {
                        id[v] = atual;
                        fila.add(v);
                    }
                }
            }
            atual++; // próxima componente
        }
        return id;
    }

    static int contarComponentes(List<List<Integer>> adj) {
        int[] id = rotularComponentes(adj);
        int max = -1;
        for (int x : id) max = Math.max(max, x);
        return max + 1;
    }

    // Árvore = conexo (1 componente) E exatamente n - 1 arestas.
    static boolean ehArvore(int n, int[][] arestas) {
        return contarComponentes(criarGrafo(n, arestas)) == 1 && arestas.length == n - 1;
    }

    public static void main(String[] args) {
        // ----- Grafo desconexo: A=0, B=1, C=2, D=3, E=4, F=5 -----
        String[] nomes = {"A", "B", "C", "D", "E", "F"};
        int[][] arestas = {{0, 1}, {0, 2}, {3, 4}}; // A-B, A-C, D-E (F está isolado)
        List<List<Integer>> adj = criarGrafo(6, arestas);

        int[] id = rotularComponentes(adj);
        int total = contarComponentes(adj);
        System.out.println("Numero de componentes: " + total); // 3

        for (int c = 0; c < total; c++) {
            StringBuilder sb = new StringBuilder("  Componente " + c + ": ");
            for (int v = 0; v < nomes.length; v++) {
                if (id[v] == c) sb.append(nomes[v]).append(' ');
            }
            System.out.println(sb);
        }

        // ----- Árvore da seção 6: A-B, A-C, B-D, B-E, C-F -----
        int[][] arvore = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}};
        System.out.println("\nA seção 6 é árvore? " + ehArvore(6, arvore)); // true

        // ----- Grafo da seção 1 (tem ciclo): 5 vértices e 5 arestas -----
        int[][] comCiclo = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}};
        System.out.println("O grafo da seção 1 é árvore? " + ehArvore(5, comCiclo)); // false
    }
}
```

**Saída esperada:**

```
Numero de componentes: 3
  Componente 0: A B C 
  Componente 1: D E 
  Componente 2: F 

A seção 6 é árvore? true
O grafo da seção 1 é árvore? false
```

---

## 9. Detecção de ciclo

Em um grafo **não direcionado**, usamos a DFS guardando o **pai** de cada vértice. Se encontrarmos um vizinho já visitado que **não é o pai**, existe um ciclo.

**Arquivo:** `Exemplo09_DeteccaoDeCiclo.java`

```java
import java.util.*;

public class Exemplo09_DeteccaoDeCiclo {

    static List<List<Integer>> criarGrafo(int n, int[][] arestas) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        return adj;
    }

    // DFS que devolve true se encontrar um ciclo.
    static boolean dfs(List<List<Integer>> adj, int atual, int pai, boolean[] visitado) {
        visitado[atual] = true;
        for (int vizinho : adj.get(atual)) {
            if (!visitado[vizinho]) {
                if (dfs(adj, vizinho, atual, visitado)) return true;
            } else if (vizinho != pai) {
                // Vizinho já visitado e que NÃO é de onde viemos: achamos um ciclo.
                return true;
            }
        }
        return false;
    }

    static boolean temCiclo(List<List<Integer>> adj) {
        boolean[] visitado = new boolean[adj.size()];
        // Testa cada componente (o grafo pode ser desconexo).
        for (int s = 0; s < adj.size(); s++) {
            if (!visitado[s] && dfs(adj, s, -1, visitado)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Grafo da seção 1: A-B, A-C, B-D, C-D, D-E  -> tem o ciclo A-B-D-C-A
        int[][] comCiclo = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}};
        System.out.println("Grafo da secao 1 tem ciclo? "
                + temCiclo(criarGrafo(5, comCiclo)));   // true

        // Árvore da seção 6: nenhum ciclo.
        int[][] arvore = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}};
        System.out.println("Arvore da secao 6 tem ciclo? "
                + temCiclo(criarGrafo(6, arvore)));     // false
    }
}
```

**Saída esperada:**

```
Grafo da secao 1 tem ciclo? true
Arvore da secao 6 tem ciclo? false
```

---

## 10. Dijkstra

Menor caminho a partir de uma origem em grafos com **pesos não negativos**. Resolvemos dois exemplos do material: o grafo da seção 9.2 e o exercício 9.

**Arquivo:** `Exemplo10_Dijkstra.java`

```java
import java.util.*;

public class Exemplo10_Dijkstra {

    static final int INF = Integer.MAX_VALUE; // representa "infinito"

    // Cada aresta é guardada como {destino, peso}.
    static List<List<int[]>> criarGrafo(int n, int[][] arestas) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {              // a = {u, v, peso}
            adj.get(a[0]).add(new int[]{a[1], a[2]});
            adj.get(a[1]).add(new int[]{a[0], a[2]}); // não direcionado
        }
        return adj;
    }

    // Retorna dist[] e preenche anterior[] para reconstruir os caminhos.
    static int[] dijkstra(List<List<int[]>> adj, int origem, int[] anterior) {
        int n = adj.size();
        int[] dist = new int[n];
        Arrays.fill(dist, INF);       // todas as distâncias começam infinitas
        Arrays.fill(anterior, -1);
        dist[origem] = 0;             // a origem está a distância 0 dela mesma

        // Fila de prioridade: sempre retira o vértice de MENOR distância.
        // Cada elemento é {vértice, distância}.
        PriorityQueue<int[]> fila = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        fila.add(new int[]{origem, 0});

        while (!fila.isEmpty()) {
            int[] topo = fila.poll();
            int u = topo[0];
            int d = topo[1];

            // Entrada antiga (já existe distância melhor): ignora.
            if (d > dist[u]) continue;

            // "Relaxamento": tenta melhorar a distância dos vizinhos passando por u.
            for (int[] aresta : adj.get(u)) {
                int v = aresta[0];
                int peso = aresta[1];
                int nova = dist[u] + peso;
                if (nova < dist[v]) {
                    dist[v] = nova;
                    anterior[v] = u;            // melhor caminho até v passa por u
                    fila.add(new int[]{v, nova});
                }
            }
        }
        return dist;
    }

    // Reconstrói o caminho seguindo "anterior" de trás para frente.
    static String caminho(int destino, int[] anterior, String[] nomes) {
        LinkedList<String> lista = new LinkedList<>();
        for (int v = destino; v != -1; v = anterior[v]) {
            lista.addFirst(nomes[v]);
        }
        return String.join(" -> ", lista);
    }

    static void executar(String titulo, String[] nomes, int[][] arestas, int origem) {
        System.out.println("=== " + titulo + " ===");
        List<List<int[]>> adj = criarGrafo(nomes.length, arestas);
        int[] anterior = new int[nomes.length];
        int[] dist = dijkstra(adj, origem, anterior);

        for (int v = 0; v < nomes.length; v++) {
            System.out.println("  " + nomes[origem] + " ate " + nomes[v]
                    + ": custo " + dist[v] + "  caminho: " + caminho(v, anterior, nomes));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // ----- Exemplo da seção 9.2 -----
        // A=0, B=1, C=2, D=3 | A-B(4), A-C(1), B-D(1), C-D(5)
        executar("Secao 9.2 (origem A)",
                new String[]{"A", "B", "C", "D"},
                new int[][]{{0, 1, 4}, {0, 2, 1}, {1, 3, 1}, {2, 3, 5}},
                0);

        // ----- Exercício 9 -----
        // S=0, A=1, B=2, C=3, T=4
        // S-A(7), S-B(2), A-C(3), A-T(2), B-C(1), C-T(5)
        executar("Exercicio 9 (origem S)",
                new String[]{"S", "A", "B", "C", "T"},
                new int[][]{{0, 1, 7}, {0, 2, 2}, {1, 3, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 5}},
                0);
    }
}
```

**Saída esperada:**

```
=== Secao 9.2 (origem A) ===
  A ate A: custo 0  caminho: A
  A ate B: custo 4  caminho: A -> B
  A ate C: custo 1  caminho: A -> C
  A ate D: custo 5  caminho: A -> B -> D

=== Exercicio 9 (origem S) ===
  S ate S: custo 0  caminho: S
  S ate A: custo 6  caminho: S -> B -> C -> A
  S ate B: custo 2  caminho: S -> B
  S ate C: custo 3  caminho: S -> B -> C
  S ate T: custo 8  caminho: S -> B -> C -> T
```

---

## 11. Bellman-Ford

Funciona com **pesos negativos** e **detecta ciclos negativos**. É mais lento que o Dijkstra: `O(n × m)`.

**Arquivo:** `Exemplo11_BellmanFord.java`

```java
import java.util.*;

public class Exemplo11_BellmanFord {

    static final long INF = Long.MAX_VALUE / 2; // evita overflow ao somar

    // arestas[i] = {origem, destino, peso}  (grafo DIRECIONADO)
    // Retorna null se houver ciclo negativo alcançável a partir da origem.
    static long[] bellmanFord(int n, int[][] arestas, int origem) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[origem] = 0;

        // Relaxa TODAS as arestas n-1 vezes.
        // Um caminho mínimo simples tem no máximo n-1 arestas.
        for (int i = 1; i <= n - 1; i++) {
            for (int[] a : arestas) {
                if (dist[a[0]] < INF && dist[a[0]] + a[2] < dist[a[1]]) {
                    dist[a[1]] = dist[a[0]] + a[2];
                }
            }
        }

        // Uma rodada extra: se ainda der para melhorar, há ciclo negativo.
        for (int[] a : arestas) {
            if (dist[a[0]] < INF && dist[a[0]] + a[2] < dist[a[1]]) {
                return null;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        // Grafo com peso negativo, mas SEM ciclo negativo.
        // 0->1 (4), 0->2 (5), 1->2 (-3), 2->3 (2)
        int[][] g1 = {{0, 1, 4}, {0, 2, 5}, {1, 2, -3}, {2, 3, 2}};
        long[] d = bellmanFord(4, g1, 0);
        System.out.println("Grafo 1 (peso negativo, sem ciclo negativo):");
        System.out.println("  Distancias a partir de 0: " + Arrays.toString(d));
        // [0, 4, 1, 3] -> 0->1->2 custa 4 + (-3) = 1, melhor que ir direto (5)

        // Grafo com CICLO NEGATIVO: 0->1 (1), 1->2 (-1), 2->0 (-1)  (soma = -1)
        int[][] g2 = {{0, 1, 1}, {1, 2, -1}, {2, 0, -1}};
        long[] d2 = bellmanFord(3, g2, 0);
        System.out.println("\nGrafo 2 (com ciclo negativo):");
        System.out.println("  Resultado: " + (d2 == null
                ? "ciclo negativo detectado!" : Arrays.toString(d2)));
    }
}
```

**Saída esperada:**

```
Grafo 1 (peso negativo, sem ciclo negativo):
  Distancias a partir de 0: [0, 4, 1, 3]

Grafo 2 (com ciclo negativo):
  Resultado: ciclo negativo detectado!
```

---

## 12. Floyd-Warshall

Calcula o menor caminho entre **todos os pares** de vértices. Para cada vértice intermediário `k`, testa se passar por ele melhora a distância `i → j`. Complexidade `O(n³)`.

**Arquivo:** `Exemplo12_FloydWarshall.java`

```java
import java.util.*;

public class Exemplo12_FloydWarshall {

    // Um valor "grande", mas pequeno o bastante para INF + INF não estourar o int.
    static final int INF = 1_000_000;

    public static void main(String[] args) {
        String[] nomes = {"A", "B", "C", "D"};
        int n = nomes.length;

        // Matriz de distâncias iniciais.
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);  // sem aresta direta = infinito
            dist[i][i] = 0;             // distância até si mesmo = 0
        }

        // Grafo da seção 9.2 (não direcionado): A-B(4), A-C(1), B-D(1), C-D(5)
        int[][] arestas = {{0, 1, 4}, {0, 2, 1}, {1, 3, 1}, {2, 3, 5}};
        for (int[] a : arestas) {
            dist[a[0]][a[1]] = a[2];
            dist[a[1]][a[0]] = a[2];
        }

        // Três laços aninhados. A ordem importa: k (intermediário) é o EXTERNO.
        for (int k = 0; k < n; k++) {          // vértice intermediário
            for (int i = 0; i < n; i++) {      // origem
                for (int j = 0; j < n; j++) {  // destino
                    // Ir de i até j passando por k é melhor que o caminho atual?
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Imprime a matriz final.
        System.out.print("    ");
        for (String nome : nomes) System.out.printf("%3s", nome);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%-4s", nomes[i]);
            for (int j = 0; j < n; j++) {
                System.out.printf("%3d", dist[i][j]);
            }
            System.out.println();
        }
    }
}
```

**Saída esperada:**

```
      A  B  C  D
A     0  4  1  5
B     4  0  5  1
C     1  5  0  5
D     5  1  5  0
```

---

## 13. Kruskal (árvore geradora mínima)

Ordena as arestas da mais barata para a mais cara e escolhe cada uma que **não forme ciclo**. A verificação de ciclo usa a estrutura **União-Busca** (*Union-Find*). Grafo da seção 10.1 (custo total esperado: **15**).

```
        2           3
   A ------- B ------- C
   |         |         |
 6 |       1 |         | 4
   |         |         |
   D ------- E ------- F
        5           7
```

**Arquivo:** `Exemplo13_Kruskal.java`

```java
import java.util.*;

public class Exemplo13_Kruskal {

    // Estrutura União-Busca: responde "u e v estão na mesma componente?" quase em O(1).
    static class UniaoBusca {
        private final int[] pai;
        private final int[] rank;

        UniaoBusca(int n) {
            pai = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) pai[i] = i; // cada vértice começa sozinho
        }

        // Encontra o "representante" da componente de x (com compressão de caminho).
        int buscar(int x) {
            if (pai[x] != x) pai[x] = buscar(pai[x]);
            return pai[x];
        }

        // Une as componentes de a e b. Retorna false se já estavam juntas.
        boolean unir(int a, int b) {
            int ra = buscar(a), rb = buscar(b);
            if (ra == rb) return false;              // mesma componente: criaria ciclo
            if (rank[ra] < rank[rb]) { int t = ra; ra = rb; rb = t; }
            pai[rb] = ra;                            // pendura a menor na maior
            if (rank[ra] == rank[rb]) rank[ra]++;
            return true;
        }
    }

    public static void main(String[] args) {
        String[] nomes = {"A", "B", "C", "D", "E", "F"};

        // Cada aresta é {u, v, peso}.
        int[][] arestas = {
            {0, 1, 2},  // A-B
            {1, 2, 3},  // B-C
            {0, 3, 6},  // A-D
            {1, 4, 1},  // B-E
            {2, 5, 4},  // C-F
            {3, 4, 5},  // D-E
            {4, 5, 7}   // E-F
        };

        // 1) Ordena todas as arestas pelo peso (menor primeiro).
        Arrays.sort(arestas, Comparator.comparingInt(a -> a[2]));

        UniaoBusca uf = new UniaoBusca(nomes.length);
        int custoTotal = 0;
        int escolhidas = 0;

        // 2) Percorre as arestas em ordem e decide: escolher ou descartar.
        for (int[] a : arestas) {
            String texto = nomes[a[0]] + "-" + nomes[a[1]] + " (" + a[2] + ")";
            if (uf.unir(a[0], a[1])) {
                System.out.println("escolhe   " + texto);
                custoTotal += a[2];
                escolhidas++;
            } else {
                System.out.println("descarta  " + texto + "  <- formaria ciclo");
            }
        }

        // Uma árvore com n vértices tem n - 1 arestas.
        System.out.println("\nArestas escolhidas: " + escolhidas
                + " (esperado: " + (nomes.length - 1) + ")");
        System.out.println("Custo total: " + custoTotal);
    }
}
```

**Saída esperada:**

```
escolhe   B-E (1)
escolhe   A-B (2)
escolhe   B-C (3)
escolhe   C-F (4)
escolhe   D-E (5)
descarta  A-D (6)  <- formaria ciclo
descarta  E-F (7)  <- formaria ciclo

Arestas escolhidas: 5 (esperado: 5)
Custo total: 15
```

---

## 14. Prim (árvore geradora mínima)

Começa em um vértice e **cresce a árvore**, sempre adicionando a aresta mais barata que leva a um vértice ainda fora dela. Mesmo grafo do Kruskal; o custo total também é **15**.

**Arquivo:** `Exemplo14_Prim.java`

```java
import java.util.*;

public class Exemplo14_Prim {

    static List<List<int[]>> criarGrafo(int n, int[][] arestas) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {                       // a = {u, v, peso}
            adj.get(a[0]).add(new int[]{a[1], a[2]});   // {destino, peso}
            adj.get(a[1]).add(new int[]{a[0], a[2]});
        }
        return adj;
    }

    public static void main(String[] args) {
        String[] nomes = {"A", "B", "C", "D", "E", "F"};
        int[][] arestas = {
            {0, 1, 2}, {1, 2, 3}, {0, 3, 6}, {1, 4, 1},
            {2, 5, 4}, {3, 4, 5}, {4, 5, 7}
        };
        List<List<int[]>> adj = criarGrafo(nomes.length, arestas);

        boolean[] naArvore = new boolean[nomes.length];

        // Fila de prioridade de candidatos: {peso, destino, origem}.
        // Sempre sai a aresta MAIS BARATA.
        PriorityQueue<int[]> candidatas =
                new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));

        int inicio = 0;                  // começa em A
        naArvore[inicio] = true;
        for (int[] viz : adj.get(inicio)) {
            candidatas.add(new int[]{viz[1], viz[0], inicio});
        }

        int custoTotal = 0;
        int vertices = 1;

        while (!candidatas.isEmpty() && vertices < nomes.length) {
            int[] melhor = candidatas.poll();
            int peso = melhor[0], destino = melhor[1], origem = melhor[2];

            if (naArvore[destino]) continue; // já está na árvore: criaria ciclo

            // Adiciona o novo vértice à árvore.
            naArvore[destino] = true;
            vertices++;
            custoTotal += peso;
            System.out.println("adiciona " + nomes[origem] + "-" + nomes[destino]
                    + " (" + peso + ")");

            // As arestas do novo vértice viram candidatas.
            for (int[] viz : adj.get(destino)) {
                if (!naArvore[viz[0]]) {
                    candidatas.add(new int[]{viz[1], viz[0], destino});
                }
            }
        }
        System.out.println("\nCusto total: " + custoTotal);
    }
}
```

**Saída esperada:**

```
adiciona A-B (2)
adiciona B-E (1)
adiciona B-C (3)
adiciona C-F (4)
adiciona E-D (5)

Custo total: 15
```

> As arestas escolhidas são as mesmas do Kruskal (a ordem muda). Em empates de peso, as duas árvores poderiam diferir, mas o **custo total** é sempre o mesmo.

---

## 15. Ordenação topológica (Kahn)

Repete: pega um vértice com **grau de entrada 0**, coloca na ordem e "remove" o vértice do grafo. Se sobrar vértice sem poder ser removido, o grafo tem **ciclo**. O programa resolve o exemplo do bolo, o exercício 10 e um caso com ciclo.

**Arquivo:** `Exemplo15_OrdenacaoTopologica.java`

```java
import java.util.*;

public class Exemplo15_OrdenacaoTopologica {

    // arestas[i] = {a, b} significa "a precisa vir ANTES de b" (a -> b).
    // Retorna a ordem, ou null se houver ciclo.
    static List<Integer> ordenar(int n, int[][] arestas) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        int[] grauEntrada = new int[n];
        for (int[] a : arestas) {
            adj.get(a[0]).add(a[1]);
            grauEntrada[a[1]]++;       // b ganha mais uma dependência
        }

        // Fila inicial: todos os vértices sem dependências.
        Queue<Integer> fila = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (grauEntrada[i] == 0) fila.add(i);
        }

        List<Integer> ordem = new ArrayList<>();
        while (!fila.isEmpty()) {
            int u = fila.poll();
            ordem.add(u);

            // "Remove" u: seus vizinhos têm uma dependência a menos.
            for (int v : adj.get(u)) {
                grauEntrada[v]--;
                if (grauEntrada[v] == 0) {
                    fila.add(v);       // v ficou livre para ser executado
                }
            }
        }

        // Se nem todos os vértices entraram na ordem, sobrou um ciclo.
        return ordem.size() == n ? ordem : null;
    }

    static void mostrar(String titulo, String[] nomes, int[][] arestas) {
        System.out.println("=== " + titulo + " ===");
        List<Integer> ordem = ordenar(nomes.length, arestas);
        if (ordem == null) {
            System.out.println("  Erro: o grafo tem ciclo, nao existe ordem valida.\n");
            return;
        }
        StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < ordem.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(nomes[ordem.get(i)]);
        }
        System.out.println(sb + "\n");
    }

    public static void main(String[] args) {
        // Exemplo do bolo (seção 11)
        mostrar("Receita de bolo",
                new String[]{"Comprar ingredientes", "Pre-aquecer forno",
                             "Misturar", "Assar", "Servir"},
                new int[][]{{0, 2}, {2, 3}, {1, 3}, {3, 4}});

        // Exercício 10
        mostrar("Exercicio 10",
                new String[]{"Acordar", "Tomar cafe", "Tomar banho",
                             "Sair de casa", "Trabalhar"},
                new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}});

        // Caso com ciclo: A depende de B, que depende de A.
        mostrar("Grafo com ciclo",
                new String[]{"A", "B"},
                new int[][]{{0, 1}, {1, 0}});
    }
}
```

**Saída esperada:**

```
=== Receita de bolo ===
  Comprar ingredientes -> Pre-aquecer forno -> Misturar -> Assar -> Servir

=== Exercicio 10 ===
  Acordar -> Tomar cafe -> Tomar banho -> Sair de casa -> Trabalhar

=== Grafo com ciclo ===
  Erro: o grafo tem ciclo, nao existe ordem valida.
```

---

## 16. Grafo bipartido

Tenta **colorir os vértices com 2 cores** usando BFS: cada vizinho recebe a cor oposta. Se dois vértices adjacentes ficarem com a mesma cor, o grafo **não é bipartido**.

**Arquivo:** `Exemplo16_Bipartido.java`

```java
import java.util.*;

public class Exemplo16_Bipartido {

    static List<List<Integer>> criarGrafo(int n, int[][] arestas) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] a : arestas) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        return adj;
    }

    // Devolve o vetor de cores (0 ou 1) ou null se NÃO for bipartido.
    static int[] colorir(List<List<Integer>> adj) {
        int n = adj.size();
        int[] cor = new int[n];
        Arrays.fill(cor, -1);                 // -1 = sem cor

        for (int inicio = 0; inicio < n; inicio++) {
            if (cor[inicio] != -1) continue;
            cor[inicio] = 0;
            Queue<Integer> fila = new ArrayDeque<>();
            fila.add(inicio);

            while (!fila.isEmpty()) {
                int u = fila.poll();
                for (int v : adj.get(u)) {
                    if (cor[v] == -1) {
                        cor[v] = 1 - cor[u];  // cor oposta à do vizinho
                        fila.add(v);
                    } else if (cor[v] == cor[u]) {
                        return null;          // conflito: dois adjacentes com a mesma cor
                    }
                }
            }
        }
        return cor;
    }

    public static void main(String[] args) {
        // Alunos e disciplinas (seção 6):
        // 0=Joao, 1=Maria | 2=Matematica, 3=Fisica, 4=Historia
        String[] nomes = {"Joao", "Maria", "Matematica", "Fisica", "Historia"};
        int[][] matriculas = {{0, 2}, {0, 3}, {1, 3}, {1, 4}};

        int[] cor = colorir(criarGrafo(5, matriculas));
        if (cor != null) {
            System.out.println("E bipartido! Grupos:");
            for (int c = 0; c <= 1; c++) {
                StringBuilder sb = new StringBuilder("  Grupo " + c + ": ");
                for (int v = 0; v < nomes.length; v++) {
                    if (cor[v] == c) sb.append(nomes[v]).append(' ');
                }
                System.out.println(sb);
            }
        }

        // Um triângulo (ciclo de tamanho 3, ímpar) nunca é bipartido.
        int[][] triangulo = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("\nTriangulo e bipartido? "
                + (colorir(criarGrafo(3, triangulo)) != null)); // false
    }
}
```

**Saída esperada:**

```
E bipartido! Grupos:
  Grupo 0: Joao Maria 
  Grupo 1: Matematica Fisica Historia 

Triangulo e bipartido? false
```

---

## 17. Circuito euleriano e as pontes de Königsberg

Resultado de Euler: um grafo conexo tem **circuito euleriano** se, e somente se, **todos os vértices têm grau par**. As sete pontes de Königsberg formam um **multigrafo** (arestas paralelas).

**Arquivo:** `Exemplo17_Euler.java`

```java
public class Exemplo17_Euler {

    // Assume que o grafo é conexo. Verifica se todos os graus são pares.
    static boolean temCircuitoEuleriano(int n, int[][] arestas, boolean imprimirGraus) {
        int[] grau = new int[n];
        for (int[] a : arestas) {
            grau[a[0]]++;   // arestas paralelas contam várias vezes, como esperado
            grau[a[1]]++;
        }
        boolean todosPares = true;
        for (int v = 0; v < n; v++) {
            if (imprimirGraus) System.out.println("  grau(" + v + ") = " + grau[v]);
            if (grau[v] % 2 != 0) todosPares = false;
        }
        return todosPares;
    }

    public static void main(String[] args) {
        // Königsberg: 0 = ilha central, 1 = margem norte, 2 = margem sul, 3 = ilha leste.
        // São 7 pontes (note as arestas repetidas).
        int[][] konigsberg = {
            {0, 1}, {0, 1},   // duas pontes: ilha central <-> norte
            {0, 2}, {0, 2},   // duas pontes: ilha central <-> sul
            {0, 3},           // ilha central <-> ilha leste
            {1, 3},           // norte <-> ilha leste
            {2, 3}            // sul <-> ilha leste
        };
        System.out.println("Pontes de Konigsberg:");
        boolean possivel = temCircuitoEuleriano(4, konigsberg, true);
        System.out.println("  Existe passeio que cruza cada ponte uma unica vez e volta? "
                + possivel);  // false: todos os 4 vértices têm grau ímpar

        // Um quadrado 0-1-2-3-0: todos os vértices têm grau 2 (par).
        int[][] quadrado = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        System.out.println("\nQuadrado:");
        System.out.println("  Existe circuito euleriano? "
                + temCircuitoEuleriano(4, quadrado, false)); // true
    }
}
```

**Saída esperada:**

```
Pontes de Konigsberg:
  grau(0) = 5
  grau(1) = 3
  grau(2) = 3
  grau(3) = 3
  Existe passeio que cruza cada ponte uma unica vez e volta? false

Quadrado:
  Existe circuito euleriano? true
```

---

## 18. Coloração de grafos

Algoritmo **guloso**: para cada vértice, usa a menor cor que nenhum vizinho já usou. Exemplo: montar horários de provas, em que cada vértice é uma prova e cada aresta indica "têm alunos em comum".

> O algoritmo guloso produz uma coloração **válida**, mas não garante o **número mínimo** de cores (esse problema é difícil).

**Arquivo:** `Exemplo18_ColoracaoGulosa.java`

```java
import java.util.*;

public class Exemplo18_ColoracaoGulosa {

    public static void main(String[] args) {
        String[] provas = {"Calculo", "Fisica", "Quimica", "Historia", "Ingles"};

        // Aresta = as duas provas têm alunos em comum (não podem ser no mesmo horário).
        int[][] conflitos = {{0, 1}, {0, 2}, {1, 2}, {2, 3}, {3, 4}};

        int n = provas.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] c : conflitos) {
            adj.get(c[0]).add(c[1]);
            adj.get(c[1]).add(c[0]);
        }

        int[] cor = new int[n];
        Arrays.fill(cor, -1);              // -1 = ainda sem cor

        for (int v = 0; v < n; v++) {
            // Marca as cores já usadas pelos vizinhos de v.
            boolean[] usada = new boolean[n];
            for (int viz : adj.get(v)) {
                if (cor[viz] != -1) usada[cor[viz]] = true;
            }
            // Escolhe a menor cor livre.
            int c = 0;
            while (usada[c]) c++;
            cor[v] = c;
        }

        int totalCores = Arrays.stream(cor).max().getAsInt() + 1;
        for (int v = 0; v < n; v++) {
            System.out.println(provas[v] + " -> horario " + (cor[v] + 1));
        }
        System.out.println("\nTotal de horarios usados: " + totalCores);
    }
}
```

**Saída esperada:**

```
Calculo -> horario 1
Fisica -> horario 2
Quimica -> horario 3
Historia -> horario 1
Ingles -> horario 2

Total de horarios usados: 3
```

---

## 19. Exercícios resolvidos (5, 7 e 8)

Grafo do exercício 5, com arestas `0-1, 0-2, 1-2, 1-3, 2-3`:

```
   0 ------ 1
   |      / |
   |    /   |
   |  /     |
   2 ------ 3
```

O programa constrói a **matriz** e a **lista de adjacência** e executa a **BFS** (exercício 7) e a **DFS** (exercício 8) a partir de `0`, visitando os vizinhos em ordem crescente.

**Arquivo:** `Exemplo19_ExerciciosResolvidos.java`

```java
import java.util.*;

public class Exemplo19_ExerciciosResolvidos {

    static final int N = 4;
    static final int[][] ARESTAS = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}};

    // ----- Exercício 5: matriz de adjacência -----
    static int[][] montarMatriz() {
        int[][] m = new int[N][N];
        for (int[] a : ARESTAS) {
            m[a[0]][a[1]] = 1;
            m[a[1]][a[0]] = 1;   // não direcionado: simétrica
        }
        return m;
    }

    // ----- Exercício 5: lista de adjacência -----
    static List<List<Integer>> montarLista() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());
        for (int[] a : ARESTAS) {
            adj.get(a[0]).add(a[1]);
            adj.get(a[1]).add(a[0]);
        }
        // Ordena os vizinhos de cada vértice (ordem crescente, como pede o enunciado).
        for (List<Integer> l : adj) Collections.sort(l);
        return adj;
    }

    // ----- Exercício 7: BFS -----
    static List<Integer> bfs(List<List<Integer>> adj, int inicio) {
        boolean[] visitado = new boolean[N];
        Queue<Integer> fila = new ArrayDeque<>();
        List<Integer> ordem = new ArrayList<>();

        visitado[inicio] = true;
        fila.add(inicio);
        while (!fila.isEmpty()) {
            int u = fila.poll();
            ordem.add(u);
            for (int v : adj.get(u)) {
                if (!visitado[v]) {
                    visitado[v] = true;
                    fila.add(v);
                }
            }
        }
        return ordem;
    }

    // ----- Exercício 8: DFS (recursiva) -----
    static void dfs(List<List<Integer>> adj, int u, boolean[] visitado, List<Integer> ordem) {
        visitado[u] = true;
        ordem.add(u);
        for (int v : adj.get(u)) {
            if (!visitado[v]) dfs(adj, v, visitado, ordem);
        }
    }

    public static void main(String[] args) {
        System.out.println("Matriz de adjacencia:");
        for (int[] linha : montarMatriz()) System.out.println("  " + Arrays.toString(linha));

        List<List<Integer>> adj = montarLista();
        System.out.println("\nLista de adjacencia:");
        for (int i = 0; i < N; i++) System.out.println("  " + i + " -> " + adj.get(i));

        System.out.println("\nBFS a partir de 0: " + bfs(adj, 0));  // [0, 1, 2, 3]

        List<Integer> ordemDfs = new ArrayList<>();
        dfs(adj, 0, new boolean[N], ordemDfs);
        System.out.println("DFS a partir de 0: " + ordemDfs);       // [0, 1, 2, 3]
    }
}
```

**Saída esperada:**

```
Matriz de adjacencia:
  [0, 1, 1, 0]
  [1, 0, 1, 1]
  [1, 1, 0, 1]
  [0, 1, 1, 0]

Lista de adjacencia:
  0 -> [1, 2]
  1 -> [0, 2, 3]
  2 -> [0, 1, 3]
  3 -> [1, 2]

BFS a partir de 0: [0, 1, 2, 3]
DFS a partir de 0: [0, 1, 2, 3]
```

> Os exercícios 9 e 10 estão resolvidos nos programas das seções [10 (Dijkstra)](#10-dijkstra) e [15 (Ordenação topológica)](#15-ordenação-topológica-kahn).

---

## 20. Resumo dos programas

| Nº | Programa | Tema | Seção do material original | Complexidade |
| -- | -------- | ---- | -------------------------- | ------------ |
| 01 | `Exemplo01_VerticesArestasGraus` | Graus, aperto de mãos, grafo completo | 1, 3, 6 | O(n + m) |
| 02 | `Exemplo02_MatrizAdjacencia` | Matriz de adjacência | 7.1 | O(n²) memória |
| 03 | `Exemplo03_ListaAdjacencia` | Lista de adjacência | 7.2 | O(n + m) memória |
| 04 | `Exemplo04_ListaDeArestas` | Lista de arestas | 7.3 | O(m) memória |
| 05 | `Exemplo05_Digrafo` | Grau de entrada e saída | 4.2, 6 | O(n + m) |
| 06 | `Exemplo06_BFS` | Busca em largura | 8.1 | O(n + m) |
| 07 | `Exemplo07_DFS` | Busca em profundidade | 8.2 | O(n + m) |
| 08 | `Exemplo08_ComponentesConexas` | Componentes e árvore | 5, 6 | O(n + m) |
| 09 | `Exemplo09_DeteccaoDeCiclo` | Detecção de ciclo | 5, 8.2 | O(n + m) |
| 10 | `Exemplo10_Dijkstra` | Menor caminho (pesos ≥ 0) | 9.2 | O((n + m) log n) |
| 11 | `Exemplo11_BellmanFord` | Menor caminho (pesos negativos) | 9.3 | O(n × m) |
| 12 | `Exemplo12_FloydWarshall` | Todos os pares | 9.4 | O(n³) |
| 13 | `Exemplo13_Kruskal` | Árvore geradora mínima | 10.1 | O(m log m) |
| 14 | `Exemplo14_Prim` | Árvore geradora mínima | 10.2 | O(m log n) |
| 15 | `Exemplo15_OrdenacaoTopologica` | Ordenação topológica | 11 | O(n + m) |
| 16 | `Exemplo16_Bipartido` | Grafo bipartido | 6 | O(n + m) |
| 17 | `Exemplo17_Euler` | Circuito euleriano | 12 | O(n + m) |
| 18 | `Exemplo18_ColoracaoGulosa` | Coloração gulosa | 12 | O(n + m) |
| 19 | `Exemplo19_ExerciciosResolvidos` | Exercícios 5, 7 e 8 | 14, 15 | O(n + m) |

*(n = número de vértices; m = número de arestas)*

### Dicas de estudo

1. **Execute os algoritmos à mão** em grafos pequenos antes de rodar o código e compare com a saída.
2. **Altere os grafos** dos exemplos: adicione arestas, crie ciclos, deixe vértices isolados.
3. Experimente **quebrar** os programas de propósito (por exemplo, remova o `visitado` da BFS) para ver por que ele é indispensável.
4. Em Java, as classes `ArrayDeque` (fila e pilha), `PriorityQueue` (Dijkstra e Prim) e `ArrayList` (listas de adjacência) cobrem quase tudo o que você precisa para implementar grafos.
