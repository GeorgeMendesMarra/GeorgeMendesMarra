// Classe que representa um Nó (Node) na Árvore Binária de Busca
class No {
    int valor; // O valor que o nó armazena
    No esquerda; // Referência para o nó filho à esquerda
    No direita;  // Referência para o nó filho à direita

    // Construtor do Nó
    public No(int valor) {
        this.valor = valor;
        this.esquerda = null; // Inicialmente, sem filhos
        this.direita = null;  // Inicialmente, sem filhos
    }
}

// Classe que representa a Árvore Binária de Busca (BST)
class ArvoreBinariaBusca {
    No raiz; // A raiz da árvore

    // Construtor da Árvore
    public ArvoreBinariaBusca() {
        this.raiz = null; // A árvore inicia vazia
    }

    // Método principal para inserir um valor na árvore
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
        System.out.println("Inserido: " + valor);
    }

    // Método auxiliar recursivo para inserir um valor
    private No inserirRecursivo(No noAtual, int valor) {
        // Se o nó atual é nulo, significa que encontramos o local para inserir o novo valor
        if (noAtual == null) {
            return new No(valor);
        }

        // Se o valor a ser inserido é menor que o valor do nó atual, vai para a subárvore esquerda
        if (valor < noAtual.valor) {
            noAtual.esquerda = inserirRecursivo(noAtual.esquerda, valor);
        }
        // Se o valor a ser inserido é maior que o valor do nó atual, vai para a subárvore direita
        else if (valor > noAtual.valor) {
            noAtual.direita = inserirRecursivo(noAtual.direita, valor);
        }
        // Se o valor já existe (igual), não faz nada (para não ter duplicatas numa BST simples)
        else {
            // Valor já existe, não adiciona duplicatas
            System.out.println("Valor " + valor + " já existe na árvore. Ignorando.");
            return noAtual; // Retorna o nó atual sem modificação
        }

        return noAtual; // Retorna o nó atual após a inserção na subárvore
    }

    // Método para buscar um valor na árvore
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    // Método auxiliar recursivo para buscar um valor
    private boolean buscarRecursivo(No noAtual, int valor) {
        // Se o nó atual é nulo, o valor não foi encontrado
        if (noAtual == null) {
            return false;
        }
        // Se o valor do nó atual é igual ao valor buscado, encontramos
        if (noAtual.valor == valor) {
            return true;
        }
        // Se o valor buscado é menor, procura na subárvore esquerda
        return valor < noAtual.valor
                ? buscarRecursivo(noAtual.esquerda, valor)
                : buscarRecursivo(noAtual.direita, valor); // Senão, procura na subárvore direita
    }

    // --- Métodos de Travessia (Percurso) ---

    // 1. Travessia In-Order (Em Ordem): Esquerda -> Raiz -> Direita
    // Produz os elementos em ordem crescente para uma BST.
    public void travessiaInOrder() {
        System.out.print("Travessia In-Order (ordenado): ");
        travessiaInOrderRecursivo(raiz);
        System.out.println();
    }

    private void travessiaInOrderRecursivo(No no) {
        if (no != null) {
            travessiaInOrderRecursivo(no.esquerda); // Visita subárvore esquerda
            System.out.print(no.valor + " ");       // Visita a raiz
            travessiaInOrderRecursivo(no.direita);  // Visita subárvore direita
        }
    }

    // 2. Travessia Pre-Order (Pré-Ordem): Raiz -> Esquerda -> Direita
    // Útil para copiar a árvore ou criar uma representação prefixa.
    public void travessiaPreOrder() {
        System.out.print("Travessia Pre-Order: ");
        travessiaPreOrderRecursivo(raiz);
        System.out.println();
    }

    private void travessiaPreOrderRecursivo(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");       // Visita a raiz
            travessiaPreOrderRecursivo(no.esquerda); // Visita subárvore esquerda
            travessiaPreOrderRecursivo(no.direita);  // Visita subárvore direita
        }
    }

    // 3. Travessia Post-Order (Pós-Ordem): Esquerda -> Direita -> Raiz
    // Útil para deletar a árvore (primeiro filhos, depois o pai).
    public void travessiaPostOrder() {
        System.out.print("Travessia Post-Order: ");
        travessiaPostOrderRecursivo(raiz);
        System.out.println();
    }

    private void travessiaPostOrderRecursivo(No no) {
        if (no != null) {
            travessiaPostOrderRecursivo(no.esquerda); // Visita subárvore esquerda
            travessiaPostOrderRecursivo(no.direita);  // Visita subárvore direita
            System.out.print(no.valor + " ");       // Visita a raiz
        }
    }
}

// Classe principal para testar a Árvore Binária de Busca
// Removido 'public' da declaração da classe para evitar o erro de nome de arquivo,
// assumindo que todas as classes estão no mesmo arquivo e este arquivo não é nomeado como ExemploArvoreSimples.java.
class ExemploArvoreSimples {
    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        // Inserindo elementos na árvore
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);
        arvore.inserir(30); // Tentando inserir duplicata

        System.out.println("\n--- Buscando elementos ---");
        System.out.println("Buscando 40: " + arvore.buscar(40)); // Deve ser true
        System.out.println("Buscando 90: " + arvore.buscar(90)); // Deve ser false
        System.out.println("Buscando 50: " + arvore.buscar(50)); // Deve ser true

        System.out.println("\n--- Realizando Travessias ---");
        arvore.travessiaInOrder();  // Esperado: 20 30 40 50 60 70 80
        arvore.travessiaPreOrder(); // Esperado: 50 30 20 40 70 60 80
        arvore.travessiaPostOrder(); // Esperado: 20 40 30 60 80 70 50
    }
}
