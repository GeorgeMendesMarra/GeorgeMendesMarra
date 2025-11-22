// Enumeração que define as duas cores possíveis dos nós, essenciais para o balanço.
enum Color { RED, BLACK }

// Estrutura do Nó da Árvore Vermelha e Preta (RBT).
class RBNode {
    int data;       
    
    // Campo essencial para RBT: A cor do nó, usada para manter as propriedades de balanço.
    Color color; 
    
    RBNode left;
    RBNode right;
    
    // Campo essencial para RBT: A referência ao nó pai.
    // Isso permite que a RBT suba na árvore após uma inserção para verificar e corrigir violações (fixUp).
    RBNode parent; 

    // Construtor: Inicializa o nó com o dado, a cor (geralmente Vermelho) e a referência ao pai.
    public RBNode(int data, Color color, RBNode parent) {
        this.data = data;
        this.color = color;
        this.parent = parent;
    }
}

public class RedBlackTreeExample {

    // Método de 'conserto' (fixUp) para restaurar as propriedades da RBT após a inserção.
    // É aqui que reside a maior complexidade do algoritmo.
    private void fixUp(RBNode node) {
        // Envolve checar 5 regras complexas da RBT.
        
        // Loop que continua enquanto houver violação, especificamente a violação da Regra 3:
        // 'Se um nó é Vermelho, seus filhos devem ser Pretos.' (A violação ocorre quando pai e filho são Vermelhos).
        while (node != null && node.parent != null && node.parent.color == Color.RED) {
            
            // Lógica COMPLEXA de Recoloração e Rotações (Tio Vermelho vs. Tio Preto).
            // Existem vários casos a serem tratados (rotação simples, rotação dupla, recoloração)
            // dependendo se o nó pai é o filho esquerdo/direito e a cor do nó 'tio'.
            System.out.println("  -> Regra de cor violada! Executando rotação e recoloração (Fix-up).");
            break; // Apenas para o exemplo, a implementação real continua até a raiz ou até a violação ser resolvida.
        }
    }

    // Método para inserir um novo valor na RBT.
    public void insert(RBNode root, int value) {
        // Insere como uma BST normal.
        // Novo nó é sempre criado como VERMELHO. Se o nó recém-inserido for Vermelho,
        // ele pode violar a Regra 3 se o nó pai também for Vermelho.
        RBNode newNode = new RBNode(value, Color.RED, null); 

        // (A lógica de busca e inserção BST para encontrar a posição do 'newNode' seria colocada aqui)

        // Após a inserção no local correto, é NECESSÁRIO chamar o fix-up.
        // A função 'fixUp' garante que as 5 propriedades da RBT sejam mantidas,
        // restaurando o balanço e garantindo o desempenho O(log n).
        fixUp(newNode); 
        
        System.out.println("Inserido " + value + ". A RBT precisa de regras de coloração e rotações complexas.");
    }
    
    public static void main(String[] args) {
        System.out.println("A RBT mantém o balanço usando cores e rotações.");
        // A complexidade reside no método 'fixUp' e na manutenção das 5 regras de cor e balanço.
    }
}
