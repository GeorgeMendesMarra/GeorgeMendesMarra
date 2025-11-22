// Define a estrutura fundamental para um nó em qualquer árvore binária.
// Cada nó armazena um valor de dado e referências a, no máximo, dois filhos.
class TreeNode {
    // Campo para armazenar o valor ou dado do nó (neste caso, um inteiro).
    int data;
    
    // Referência ao filho da esquerda (nós comumente menores ou mais antigos).
    TreeNode left;
    
    // Referência ao filho da direita (nós comumente maiores ou mais novos).
    TreeNode right;

    // Construtor: Usado para criar uma nova instância de nó com um valor inicial.
    // As referências 'left' e 'right' são implicitamente inicializadas como 'null' (vazias).
    public TreeNode(int data) {
        this.data = data;
    }
}

public class ArvoreBinaria {
    public static void main(String[] args) {
        // --- Início da Construção Manual da Árvore ---
        
        // 1. Cria o nó raiz da árvore (o ponto de partida).
        // A raiz armazena o valor 50.
        TreeNode root = new TreeNode(50);
        
        // 2. Cria o filho da esquerda da raiz.
        // O nó 30 se torna o filho esquerdo do nó 50.
        root.left = new TreeNode(30);
        
        // 3. Cria o filho da direita da raiz.
        // O nó 70 se torna o filho direito do nó 50.
        root.right = new TreeNode(70);
        
        // 4. Cria o filho da esquerda do nó 30.
        // O nó 10 se torna o neto da raiz.
        root.left.left = new TreeNode(10);
        
        // Fim da Criação: A árvore atual é:
        //        50
        //       /  \
        //     30    70
        //    /
        //  10

        // Mensagem de saída indicando que a estrutura foi montada.
        System.out.println("Árvore Binária Simples criada com sucesso.");
        // Nota: Este código apenas constrói a estrutura na memória;
        // para interagir com ela, seriam necessários métodos de percurso (DFS/BFS).
    }
}
