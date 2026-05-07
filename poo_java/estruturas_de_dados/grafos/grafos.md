# Grafos em Java para Iniciantes

## Sumário
1. [O que é um Grafo?](#1-o-que-é-um-grafo)
2. [Terminologia Básica](#2-terminologia-básica)
3. [Tipos de Grafos](#3-tipos-de-grafos)
4. [Representação de Grafos em Java](#4-representação-de-grafos-em-java)
5. [Implementação Passo a Passo](#5-implementação-passo-a-passo)
6. [Percorrendo Grafos](#6-percorrendo-grafos)
7. [Exemplos Completos](#7-exemplos-completos)
8. [Aplicações Práticas](#8-aplicações-práticas)
9. [Exercícios](#9-exercícios)

---

## 1. O que é um Grafo?

### 1.1 Conceito Simples

Um **grafo** é uma estrutura de dados que representa **conexões** entre **objetos**.

Imagine um mapa de cidades e estradas:

```
        Goiânia
           │
           │ (estrada 1)
           │
    Anápolis────Brasília
           │      │
           │      │ (estrada 2)
           │      │
       Pirenópolis  │
                ┌───┘
                │
             Formosa
```

### 1.2 Analogias do Mundo Real

| Analogia | Explicação |
|----------|------------|
| **Mapa de estradas** | Cidades = vértices, estradas = arestas |
| **Rede social** | Pessoas = vértices, amizades = arestas |
| **Rede de computadores** | Computadores = vértices, cabos = arestas |
| **Sistema de metrô** | Estações = vértices, linhas = arestas |
| **Árvore genealógica** | Pessoas = vértices, parentesco = arestas |

### 1.3 Componentes do Grafo

```
┌─────────────────────────────────────────────────────────┐
│                        GRAFO                             │
│                                                          │
│                    ┌─────┐                               │
│                    │  A  │ ◄── VÉRTICE (nó)             │
│                    └──┬──┘                               │
│                       │                                  │
│                       │ ARESTA (conexão)                 │
│                       │                                  │
│                    ┌──▼──┐                               │
│                    │  B  │                               │
│                    └─────┘                               │
│                                                          │
│  Grafo simples: A está conectado a B                    │
└─────────────────────────────────────────────────────────┘
```

---

## 2. Terminologia Básica

| Termo | Definição | Exemplo |
|-------|-----------|---------|
| **Vértice (ou nó)** | Um elemento do grafo | Uma pessoa, uma cidade, um computador |
| **Aresta (ou aresta)** | Conexão entre dois vértices | Uma estrada, uma amizade, um cabo |
| **Grau** | Número de conexões de um vértice | Se A conecta com 3 outros → grau 3 |
| **Vizinhos** | Vértices conectados a um vértice | Amigos de uma pessoa |
| **Caminho** | Sequência de vértices conectados | A → B → C → D |
| **Ciclo** | Caminho que começa e termina no mesmo vértice | A → B → C → A |
| **Peso** | Valor associado a uma aresta | Distância em km, custo, tempo |

---

## 3. Tipos de Grafos

### 3.1 Grafo Não Direcionado

A conexão funciona nos **dois sentidos**.

```
    A ────── B
     \       │
      \      │
       \     │
        C ─── D

Se A está conectado a B, então B também está conectado a A.
Exemplo: Amizade no Facebook (se você é amigo, ele é seu amigo)
```

### 3.2 Grafo Direcionado (Digrafo)

A conexão funciona em **apenas um sentido** (seta).

```
    A ──► B
    ▲     │
    │     ▼
    └─── C

A → B significa: A conecta com B, mas B NÃO conecta com A (a menos que tenha seta)
Exemplo: Seguir no Instagram (você segue, mas a pessoa pode não te seguir de volta)
```

### 3.3 Grafo com Peso

Cada aresta tem um **valor numérico** (peso).

```
    A ──5── B
     \      │
      3     2
       \    │
        └─ C

Os números representam distância, custo ou tempo.
Exemplo: Distância entre cidades (Goiânia-Anápolis = 55 km)
```

---

## 4. Representação de Grafos em Java

Existem duas formas principais de representar um grafo:

### 4.1 Matriz de Adjacência

Uma matriz `n x n` onde `n` é o número de vértices.

| - | A | B | C | D |
|---|---|---|---|---|
| **A** | 0 | 1 | 1 | 0 |
| **B** | 1 | 0 | 1 | 1 |
| **C** | 1 | 1 | 0 | 0 |
| **D** | 0 | 1 | 0 | 0 |

**Vantagens:** Acesso rápido (`O(1)`), fácil de implementar
**Desvantagens:** Ocupa muito espaço (`O(n²)`), ruim para grafos esparsos

### 4.2 Lista de Adjacência

Para cada vértice, uma lista dos seus vizinhos.

```
A: [B, C]
B: [A, C, D]
C: [A, B]
D: [B]
```

**Vantagens:** Economiza espaço (`O(V+E)`), bom para grafos esparsos
**Desvantagens:** Acesso mais lento (`O(grau)` do vértice)

**Para iniciantes:** Comece com **lista de adjacência** (mais intuitiva)

---

## 5. Implementação Passo a Passo

### 5.1 Grafo Não Direcionado (Lista de Adjacência)

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
    private int numVertices;
    private List<List<Integer>> listaAdjacencia;
    
    // Construtor
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        listaAdjacencia = new ArrayList<>(numVertices);
        
        // Inicializa a lista para cada vértice
        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia.add(new LinkedList<>());
        }
    }
    
    // Adicionar aresta (não direcionada)
    public void adicionarAresta(int origem, int destino) {
        listaAdjacencia.get(origem).add(destino);  // origem → destino
        listaAdjacencia.get(destino).add(origem);  // destino → origem
    }
    
    // Exibir o grafo
    public void exibir() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vértice " + i + ": ");
            for (Integer vizinho : listaAdjacencia.get(i)) {
                System.out.print(vizinho + " ");
            }
            System.out.println();
        }
    }
    
    // Teste
    public static void main(String[] args) {
        Grafo grafo = new Grafo(5);  // 5 vértices (0, 1, 2, 3, 4)
        
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(2, 4);
        grafo.adicionarAresta(3, 4);
        
        grafo.exibir();
    }
}
```

**Saída:**
```
Vértice 0: 1 2 
Vértice 1: 0 3 
Vértice 2: 0 4 
Vértice 3: 1 4 
Vértice 4: 2 3 
```

### 5.2 Grafo Direcionado

Muda apenas no método `adicionarAresta()`:

```java
// Adicionar aresta (direcionada)
public void adicionarAresta(int origem, int destino) {
    listaAdjacencia.get(origem).add(destino);  // apenas origem → destino
    // NÃO adiciona destino → origem
}
```

### 5.3 Grafo com Peso

Precisamos armazenar pai (vértice, peso):

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Classe para armazenar vértice e peso
class Aresta {
    int destino;
    int peso;
    
    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

public class GrafoPonderado {
    private int numVertices;
    private List<List<Aresta>> listaAdjacencia;
    
    public GrafoPonderado(int numVertices) {
        this.numVertices = numVertices;
        listaAdjacencia = new ArrayList<>(numVertices);
        
        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia.add(new LinkedList<>());
        }
    }
    
    public void adicionarAresta(int origem, int destino, int peso) {
        listaAdjacencia.get(origem).add(new Aresta(destino, peso));
        listaAdjacencia.get(destino).add(new Aresta(origem, peso)); // se não direcionado
    }
    
    public void exibir() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vértice " + i + ": ");
            for (Aresta aresta : listaAdjacencia.get(i)) {
                System.out.print("(" + aresta.destino + ", peso:" + aresta.peso + ") ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        GrafoPonderado grafo = new GrafoPonderado(4);
        
        grafo.adicionarAresta(0, 1, 5);
        grafo.adicionarAresta(0, 2, 3);
        grafo.adicionarAresta(1, 3, 2);
        grafo.adicionarAresta(2, 3, 4);
        
        grafo.exibir();
    }
}
```

**Saída:**
```
Vértice 0: (1, peso:5) (2, peso:3) 
Vértice 1: (0, peso:5) (3, peso:2) 
Vértice 2: (0, peso:3) (3, peso:4) 
Vértice 3: (1, peso:2) (2, peso:4) 
```

### 5.4 Grafo com Nomes nos Vértices

```java
import java.util.*;

public class GrafoComNomes {
    private Map<String, List<String>> adjacencia;
    
    public GrafoComNomes() {
        adjacencia = new HashMap<>();
    }
    
    public void adicionarVertice(String nome) {
        if (!adjacencia.containsKey(nome)) {
            adjacencia.put(nome, new ArrayList<>());
        }
    }
    
    public void adicionarAresta(String origem, String destino) {
        adicionarVertice(origem);
        adicionarVertice(destino);
        adjacencia.get(origem).add(destino);
        adjacencia.get(destino).add(origem);  // se não direcionado
    }
    
    public void exibir() {
        for (String vertice : adjacencia.keySet()) {
            System.out.print(vertice + ": ");
            for (String vizinho : adjacencia.get(vertice)) {
                System.out.print(vizinho + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        GrafoComNomes grafo = new GrafoComNomes();
        
        grafo.adicionarAresta("Goiânia", "Anápolis");
        grafo.adicionarAresta("Goiânia", "Brasília");
        grafo.adicionarAresta("Anápolis", "Brasília");
        grafo.adicionarAresta("Brasília", "Formosa");
        
        grafo.exibir();
    }
}
```

**Saída:**
```
Anápolis: Goiânia Brasília 
Formosa: Brasília 
Brasília: Goiânia Anápolis Formosa 
Goiânia: Anápolis Brasília 
```

---

## 6. Percorrendo Grafos

### 6.1 Busca em Largura (BFS - Breadth-First Search)

**Como funciona:** Explora os vizinhos antes de ir para os "netos".

```
Visualização BFS:
         A (início)
       /   \
      B     C
     / \   / \
    D   E F   G

Ordem: A, B, C, D, E, F, G (por níveis)
```

```java
import java.util.*;

public class BuscaLargura {
    private List<List<Integer>> adj;
    
    public BuscaLargura(int numVertices) {
        adj = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adj.add(new LinkedList<>());
        }
    }
    
    public void adicionarAresta(int origem, int destino) {
        adj.get(origem).add(destino);
        adj.get(destino).add(origem);
    }
    
    // Busca em Largura (BFS)
    public void bfs(int inicio) {
        boolean[] visitados = new boolean[adj.size()];
        Queue<Integer> fila = new LinkedList<>();
        
        visitados[inicio] = true;
        fila.add(inicio);
        
        System.out.print("Ordem BFS: ");
        
        while (!fila.isEmpty()) {
            int atual = fila.poll();
            System.out.print(atual + " ");
            
            for (int vizinho : adj.get(atual)) {
                if (!visitados[vizinho]) {
                    visitados[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        BuscaLargura grafo = new BuscaLargura(6);
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(1, 4);
        grafo.adicionarAresta(2, 5);
        
        grafo.bfs(0);
    }
}
```

### 6.2 Busca em Profundidade (DFS - Depth-First Search)

**Como funciona:** Explora um caminho até o fim, depois volta.

```
Visualização DFS:
         A (início)
       /   \
      B     C
     / \   / \
    D   E F   G

Ordem: A, B, D, E, C, F, G (vai fundo em cada ramo)
```

```java
import java.util.*;

public class BuscaProfundidade {
    private List<List<Integer>> adj;
    private boolean[] visitados;
    
    public BuscaProfundidade(int numVertices) {
        adj = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adj.add(new LinkedList<>());
        }
        visitados = new boolean[numVertices];
    }
    
    public void adicionarAresta(int origem, int destino) {
        adj.get(origem).add(destino);
        adj.get(destino).add(origem);
    }
    
    // Busca em Profundidade (DFS) - recursivo
    public void dfs(int vertice) {
        visitados[vertice] = true;
        System.out.print(vertice + " ");
        
        for (int vizinho : adj.get(vertice)) {
            if (!visitados[vizinho]) {
                dfs(vizinho);
            }
        }
    }
    
    public void buscar(int inicio) {
        Arrays.fill(visitados, false);
        System.out.print("Ordem DFS: ");
        dfs(inicio);
        System.out.println();
    }
    
    public static void main(String[] args) {
        BuscaProfundidade grafo = new BuscaProfundidade(6);
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(1, 4);
        grafo.adicionarAresta(2, 5);
        
        grafo.buscar(0);
    }
}
```

### 6.3 BFS vs DFS - Qual usar?

| Característica | BFS (Busca em Largura) | DFS (Busca em Profundidade) |
|----------------|------------------------|----------------------------|
| **Estrutura** | Fila (Queue) | Pilha (Stack) ou recursão |
| **Encontra menor caminho?** | Sim (em grafos não ponderados) | Não |
| **Consumo de memória** | Maior (guarda nível todo) | Menor (guarda apenas um caminho) |
| **Uso típico** | Shortest path, redes sociais | Labirintos, backtracking |

---

## 7. Exemplos Completos

### 7.1 Verificar se dois vértices estão conectados

```java
public boolean estaoConectados(int inicio, int destino) {
    boolean[] visitados = new boolean[adj.size()];
    Queue<Integer> fila = new LinkedList<>();
    
    visitados[inicio] = true;
    fila.add(inicio);
    
    while (!fila.isEmpty()) {
        int atual = fila.poll();
        
        if (atual == destino) {
            return true;
        }
        
        for (int vizinho : adj.get(atual)) {
            if (!visitados[vizinho]) {
                visitados[vizinho] = true;
                fila.add(vizinho);
            }
        }
    }
    
    return false;
}
```

### 7.2 Encontrar o menor caminho (BFS)

```java
public List<Integer> menorCaminho(int inicio, int destino) {
    boolean[] visitados = new boolean[adj.size()];
    int[] predecessor = new int[adj.size()];
    Arrays.fill(predecessor, -1);
    
    Queue<Integer> fila = new LinkedList<>();
    visitados[inicio] = true;
    fila.add(inicio);
    
    while (!fila.isEmpty()) {
        int atual = fila.poll();
        
        if (atual == destino) {
            break;
        }
        
        for (int vizinho : adj.get(atual)) {
            if (!visitados[vizinho]) {
                visitados[vizinho] = true;
                predecessor[vizinho] = atual;
                fila.add(vizinho);
            }
        }
    }
    
    // Reconstruir caminho
    List<Integer> caminho = new ArrayList<>();
    int atual = destino;
    while (atual != -1) {
        caminho.add(0, atual);
        atual = predecessor[atual];
    }
    
    return caminho;
}
```

### 7.3 Rede Social Simples

```java
import java.util.*;

public class RedeSocial {
    private Map<String, List<String>> amigos;
    
    public RedeSocial() {
        amigos = new HashMap<>();
    }
    
    public void adicionarUsuario(String nome) {
        amigos.putIfAbsent(nome, new ArrayList<>());
    }
    
    public void adicionarAmizade(String usuario1, String usuario2) {
        adicionarUsuario(usuario1);
        adicionarUsuario(usuario2);
        amigos.get(usuario1).add(usuario2);
        amigos.get(usuario2).add(usuario1);
    }
    
    public List<String> sugestoesAmizade(String usuario) {
        Set<String> sugestoes = new HashSet<>();
        List<String> amigosDiretos = amigos.get(usuario);
        
        for (String amigo : amigosDiretos) {
            for (String amigoDoAmigo : amigos.get(amigo)) {
                if (!amigoDoAmigo.equals(usuario) && !amigosDiretos.contains(amigoDoAmigo)) {
                    sugestoes.add(amigoDoAmigo);
                }
            }
        }
        
        return new ArrayList<>(sugestoes);
    }
    
    public static void main(String[] args) {
        RedeSocial rede = new RedeSocial();
        
        rede.adicionarAmizade("João", "Maria");
        rede.adicionarAmizade("João", "Pedro");
        rede.adicionarAmizade("Maria", "Ana");
        rede.adicionarAmizade("Pedro", "Ana");
        rede.adicionarAmizade("Ana", "Carlos");
        
        System.out.println("Sugestões para João: " + rede.sugestoesAmizade("João"));
        System.out.println("Sugestões para Ana: " + rede.sugestoesAmizade("Ana"));
    }
}
```

---

## 8. Aplicações Práticas

| Aplicação | Como o Grafo é usado |
|-----------|---------------------|
| **Google Maps** | Encontrar menor caminho entre duas cidades |
| **Facebook/Instagram** | Recomendar amigos (sugestões de conexão) |
| **Netflix/Amazon** | Recomendação de produtos baseada em "pessoas que compraram X também compraram Y" |
| **Roteamento de rede** | Encontrar o melhor caminho para dados pela internet |
| **Jogos** | Pathfinding (NPCs se movendo pelo mapa) |
| **Detecção de fraudes** | Encontrar ciclos em transações financeiras |
| **Análise de redes sociais** | Identificar influenciadores (vértices com maior grau) |

---

## 9. Exercícios

### Exercício 1 (Fácil) - Criar Grafo
Crie um grafo com 6 vértices e adicione as seguintes arestas: (0-1), (0-2), (1-3), (2-4), (3-5), (4-5). Exiba a lista de adjacência.

### Exercício 2 (Fácil) - Percurso BFS
Usando o grafo do exercício 1, execute uma BFS a partir do vértice 0 e imprima a ordem.

### Exercício 3 (Fácil) - Percurso DFS
Usando o mesmo grafo, execute uma DFS a partir do vértice 0.

### Exercício 4 (Médio) - Estão Conectados?
Implemente um método `boolean estaoConectados(int a, int b)` que verifica se existe um caminho entre dois vértices.

### Exercício 5 (Médio) - Grau dos Vértices
Implemente um método `int grau(int vertice)` que retorna quantas conexões um vértice tem.

### Exercício 6 (Médio) - Componentes Conectados
Conte quantos componentes conectados existem em um grafo (grupos de vértices que estão conectados entre si).

### Exercício 7 (Difícil) - Mapa de Cidades
Crie um grafo de cidades de Goiás com distâncias (pesos). Implemente uma busca que encontra a menor distância entre duas cidades (Dijkstra).

### Exercício 8 (Difícil) - Rede Social
Implemente uma rede social onde você pode:
- Adicionar usuários
- Adicionar amizades
- Ver amigos em comum entre dois usuários
- Sugerir novos amigos (amigos de amigos)

---

## 10. Resumo Rápido

| Conceito | Resumo |
|----------|--------|
| **Vértice** | Cada elemento do grafo (ex: pessoa, cidade) |
| **Aresta** | Conexão entre vértices |
| **Grau** | Número de conexões de um vértice |
| **Matriz de adjacência** | Tabela n×n marcando conexões |
| **Lista de adjacência** | Para cada vértice, lista seus vizinhos |
| **BFS** | Busca em largura (por níveis) - usa fila |
| **DFS** | Busca em profundidade - usa pilha/recursão |

### Passo a passo para criar um grafo:

```java
// 1. Estrutura básica
import java.util.*;

public class Grafo {
    private List<List<Integer>> adj;
    
    // 2. Construtor
    public Grafo(int n) {
        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }
    
    // 3. Adicionar aresta
    public void addAresta(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);  // se não direcionado
    }
    
    // 4. Percorrer
    public void bfs(int start) {
        boolean[] visitado = new boolean[adj.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visitado[start] = true;
        
        while (!queue.isEmpty()) {
            int atual = queue.poll();
            System.out.print(atual + " ");
            
            for (int vizinho : adj.get(atual)) {
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;
                    queue.add(vizinho);
                }
            }
        }
    }
}
```

---

## Dicas para Iniciantes

| Dica | Explicação |
|------|------------|
| 🔑 **Comece pequeno** | Primeiro domine grafos com 3-4 vértices |
| 🔑 **Desenhe no papel** | Visualize o grafo antes de programar |
| 🔑 **Use nomes significativos** | Ao invés de 0,1,2 use "A","B","C" no início |
| 🔑 **Teste cada método** | Adicione um, teste; adicione outro, teste |
| 🔑 **BFS vs DFS** | BFS usa fila (Queue) // DFS usa pilha (Stack) ou recursão |
| 🔑 **Cuidado com recursão** | Grafos grandes podem causar StackOverflow |
| 🔑 **Sempre marque visitados** | Senão você entra em loop infinito |

---
