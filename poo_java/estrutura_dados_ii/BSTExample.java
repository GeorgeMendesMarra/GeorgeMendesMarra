// Estrutura do Nó da Árvore Binária de Busca (BST).
// É similar ao nó de uma Árvore Binária, mas a classe BSTExample impõe a regra de ordenação.
class BSTNode {
    int data;       // O valor ou dado armazenado no nó.
    BSTNode left;   // Referência ao filho da esquerda (deve conter valores MENORES).
    BSTNode right;  // Referência ao filho da direita (deve conter valores MAIORES).

    public BSTNode(int data) {
        this.data = data;
    }
}

public class BSTExample {

    // Método recursivo para inserir um novo valor na BST, garantindo a propriedade de ordenação.
    // Retorna a raiz da subárvore modificada (ou o novo nó, no caso base).
    public BSTNode insert(BSTNode root, int value) {
        // Caso base 1: Se a subárvore for vazia (null),
        // chegamos ao local de inserção. Criamos o novo nó aqui.
        if (root == null) {
            return new BSTNode(value);
        }

        // Lógica de inserção recursiva para manter a ordem:
        // Se o novo valor for menor que o dado da raiz atual, vá para a esquerda.
        if (value < root.data) {
            // Chamada recursiva: tenta inserir na subárvore esquerda.
            // O resultado dessa inserção é reatribuído a 'root.left'.
            root.left = insert(root.left, value);
            
        // Se o novo valor for maior que o dado da raiz atual, vá para a direita.
        } else if (value > root.data) {
            // Chamada recursiva: tenta inserir na subárvore direita.
            // O resultado dessa inserção é reatribuído a 'root.right'.
            root.right = insert(root.right, value);
        }
        // Nota: Se 'value == root.data' (duplicata), o nó é ignorado.

        // Retorna a raiz atual da subárvore (inalterada se não houve inserção,
        // ou a raiz original se o trabalho foi feito em uma subárvore).
        return root;
    }
    
    public static void main(String[] args) {
        BSTExample tree = new BSTExample();
        BSTNode root = null;
        
        // 1. Inserção do primeiro elemento (Raiz): root passa a ser 50.
        root = tree.insert(root, 50); 
        
        // 2. Inserção de 30: 30 < 50, então é inserido à esquerda de 50.
        tree.insert(root, 30);
        
        // 3. Inserção de 70: 70 > 50, então é inserido à direita de 50.
        tree.insert(root, 70);
        
        // 4. Inserção de 10: 10 < 50 (vai para a esquerda), 10 < 30 (vai para a esquerda de 30).
        tree.insert(root, 10);
        
        // A árvore final tem a seguinte estrutura ordenada:
        //        50
        //       /  \
        //     30    70
        //    /
        //  10
        

        System.out.println("Árvore Binária de Busca (BST) criada com 4 elementos.");
        // A principal vantagem desta estrutura é que o percurso 'em-ordem' (in-order traversal)
        // sempre resultará nos elementos ordenados: 10, 30, 50, 70.
    }
}
