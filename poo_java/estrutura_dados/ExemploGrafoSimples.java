import java.util.ArrayList; // Para usar Listas
import java.util.HashMap;   // Para usar Map (HashMap)
import java.util.List;      // Interface List
import java.util.Map;       // Interface Map

// Classe que representa um Grafo utilizando Lista de Adjacência
class Grafo {
    // O Mapa armazena os vértices como chaves e suas listas de adjacência como valores.
    // Map<Vertice, Lista de Vertices Adjacentes>
    private Map<String, List<String>> adjList;

    // Construtor do Grafo
    public Grafo() {
        // Inicializa o mapa de lista de adjacência
        this.adjList = new HashMap<>();
    }

    // Método para adicionar um vértice ao grafo
    public void addVertice(String vertice) {
        // Se o vértice ainda não existe no mapa, adiciona-o com uma nova lista vazia de adjacências.
        // Isso garante que cada vértice tenha uma entrada no mapa, mesmo que ainda não tenha arestas.
        if (!adjList.containsKey(vertice)) {
            adjList.put(vertice, new ArrayList<>());
            System.out.println("Vértice '" + vertice + "' adicionado.");
        } else {
            System.out.println("Vértice '" + vertice + "' já existe.");
        }
    }

    // Método para adicionar uma aresta entre dois vértices (v1 e v2)
    // Para um grafo não direcionado, a aresta é adicionada nos dois sentidos.
    public void addAresta(String v1, String v2) {
        // Garante que ambos os vértices existam no grafo antes de tentar adicionar a aresta.
        // Se um vértice não existir, ele será adicionado automaticamente.
        addVertice(v1);
        addVertice(v2);

        // Adiciona v2 à lista de adjacência de v1
        adjList.get(v1).add(v2);
        // Adiciona v1 à lista de adjacência de v2 (para grafo não direcionado)
        adjList.get(v2).add(v1);
        System.out.println("Aresta entre '" + v1 + "' e '" + v2 + "' adicionada.");
    }

    // Método para imprimir a representação do grafo (lista de adjacência)
    public void printGrafo() {
        System.out.println("\n--- Representação do Grafo (Lista de Adjacência) ---");
        // Itera sobre cada vértice (chave) no mapa
        for (String vertice : adjList.keySet()) {
            // Imprime o vértice e, em seguida, sua lista de vértices adjacentes.
            // Ex: A -> [B, C]
            System.out.print(vertice + " -> ");
            System.out.println(adjList.get(vertice));
        }
        System.out.println("--------------------------------------------------");
    }

    // Método opcional: Verificar se há uma aresta entre dois vértices
    public boolean temAresta(String v1, String v2) {
        // Verifica se ambos os vértices existem e se v2 está na lista de adjacência de v1
        return adjList.containsKey(v1) && adjList.containsKey(v2) && adjList.get(v1).contains(v2);
    }
}

// Classe principal para testar a implementação do Grafo
public class ExemploGrafoSimples {
    public static void main(String[] args) {
        Grafo meuGrafo = new Grafo();

        // 1. Adicionando Vértices
        meuGrafo.addVertice("A");
        meuGrafo.addVertice("B");
        meuGrafo.addVertice("C");
        meuGrafo.addVertice("D");
        meuGrafo.addVertice("E");

        // 2. Adicionando Arestas (Conexões)
        meuGrafo.addAresta("A", "B"); // A ligado a B
        meuGrafo.addAresta("A", "C"); // A ligado a C
        meuGrafo.addAresta("B", "D"); // B ligado a D
        meuGrafo.addAresta("C", "E"); // C ligado a E
        meuGrafo.addAresta("D", "E"); // D ligado a E

        // 3. Imprimindo o Grafo
        meuGrafo.printGrafo();

        // 4. Testando a existência de arestas
        System.out.println("\n--- Verificando Arestas ---");
        System.out.println("Existe aresta entre A e B? " + meuGrafo.temAresta("A", "B")); // true
        System.out.println("Existe aresta entre B e A? " + meuGrafo.temAresta("B", "A")); // true (não direcionado)
        System.out.println("Existe aresta entre A e D? " + meuGrafo.temAresta("A", "D")); // false
        System.out.println("Existe aresta entre C e E? " + meuGrafo.temAresta("C", "E")); // true
        System.out.println("Existe aresta entre X e Y (vértices inexistentes)? " + meuGrafo.temAresta("X", "Y")); // false
    }
}
