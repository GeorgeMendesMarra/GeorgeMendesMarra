// Estrutura do Nó da Árvore AVL.
class AVLNode {
    int data;       // O valor do dado armazenado.
    int height;     // Novo campo essencial para a AVL: rastreia a altura da subárvore com este nó como raiz.
    AVLNode left;
    AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1; // Todo novo nó é uma folha, então sua altura inicial é 1.
    }
}

public class AVLExample {

    // Método auxiliar para obter a altura. Retorna 0 se o nó for nulo.
    // Isso evita erros de NullPointerException ao calcular a altura.
    private int getHeight(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    // Método fictício para rotação à direita (ilustra o mecanismo de balanço).
    // O objetivo real da rotação é reestruturar a subárvore para diminuir o fator de balanceamento.
    private AVLNode rotateRight(AVLNode y) {
        // 
        System.out.println("  -> Rotação para a direita em " + y.data + " (Simplificado)");
        // A lógica real envolve trocar referências e atualizar a altura de dois nós (y e y.left).
        
        // Retorna o novo nó raiz da subárvore balanceada (que seria y.left).
        return y.left; 
    }
    
    // Método para inserir um novo valor na AVL de forma recursiva.
    // Além da inserção BST, ele inclui a verificação e execução de balanço.
    public AVLNode insert(AVLNode node, int value) {
        // Caso Base da Recursão (Inserção BST):
        if (node == null) return new AVLNode(value);
        
        // Percurso BST (Busca Binária):
        if (value < node.data) node.left = insert(node.left, value);
        else if (value > node.data) node.right = insert(node.right, value);
        else return node; // Ignora duplicatas (a BST só permite valores únicos neste exemplo).

        // --- Fase de Balanceamento (O que torna a AVL diferente) ---

        // 1. Atualiza a altura do nó atual (de baixo para cima, após a recursão retornar).
        // A nova altura é 1 mais a altura máxima de seus filhos (esquerda ou direita).
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 2. Calcula o Fator de Balanceamento.
        // Fator = Altura(esquerda) - Altura(direita). O fator deve ser -1, 0 ou 1.
        int balance = getHeight(node.left) - getHeight(node.right);

        // 3. Verifica e Executa as Rotações (Rebalanceamento):

        // Caso LL (Left-Left): Desbalanceamento à esquerda, e a nova chave foi inserida
        // na subárvore esquerda do filho esquerdo.
        // Fator de Balanceamento > 1 (muito alto à esquerda) E a chave está na esquerda.
        if (balance > 1 && value < node.left.data) {
            return rotateRight(node); // Rotação Simples à Direita
        }
        
        // Nota: Os outros três casos (RR, LR, RL) seriam implementados aqui,
        // mas foram omitidos para manter a simplicidade do exemplo.

        // Retorna a raiz da subárvore (que pode ter sido um nó original ou o resultado de uma rotação).
        return node;
    }
    
    public static void main(String[] args) {
        System.out.println("Árvore AVL: Inserção requer atualização de altura e rotações.");
        System.out.println("A complexidade reside na implementação completa das quatro rotações (LL, RR, LR, RL) e na lógica de 'getHeight' e 'balance' a cada inserção/remoção.");
    }
}
