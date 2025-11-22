import java.util.List;
import java.util.ArrayList;

// O Nó da Árvore B é a estrutura central. Diferente de uma BST, ele contém
// múltiplas chaves e múltiplas referências a filhos.
class BTreeNode {
    // Ordem M: O número máximo de filhos que um nó pode ter. (M=3 neste exemplo simplificado,
    // o que significa que o nó pode ter no máximo M-1 = 2 chaves.)
    
    boolean isLeaf;         // Indica se este nó é um nó folha (leaf) ou um nó interno.
    
    // Lista ordenada de chaves de dados armazenadas neste nó.
    // Em um nó interno com 'k' chaves, há 'k+1' ponteiros para filhos.
    List<Integer> keys;       
    
    // Lista de Referências de Filhos (M filhos para M-1 chaves).
    // O número de referências aqui deve ser 'keys.size() + 1'.
    List<BTreeNode> children; 

    // Construtor: Inicializa a lista de chaves e de filhos, e define se é uma folha.
    public BTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }
}

public class BTreeExample {

    // Método fictício de inserção que ilustra a complexidade da Árvore B.
    // A inserção em uma B-Tree é sempre feita nas folhas.
    public void insert(BTreeNode root, int key) {
        // --- Lógica de Balanceamento (Complexidade) ---
        
        // Verifica a condição de 'cheio' (neste exemplo, M-1 = 2).
        if (root.keys.size() == 2) { 
            // A principal complexidade da B-Tree reside aqui.
            // Para manter a B-Tree balanceada, um nó cheio deve ser dividido (split).
            // A chave mediana sobe para o nó pai, e duas novas referências de filhos são criadas.
            System.out.println("Nó cheio. Executando operação de SPLIT para balancear a Árvore B.");
        }
        
        // --- Lógica de Inserção Simplificada ---
        
        // O código real envolveria primeiro navegar recursivamente até a folha correta.
        // Já que este é um exemplo simples, assume-se que 'root' é a folha onde a chave será inserida.
        
        // Adiciona a nova chave à lista.
        root.keys.add(key);
        
        // Ordena a lista de chaves no nó (necessário para a busca binária e estrutura da B-Tree).
        root.keys.sort(null); 

        System.out.println("Chave " + key + " inserida em um Nó da Árvore B (lista de chaves).");
    }
    
    public static void main(String[] args) {
        BTreeExample tree = new BTreeExample();
        // Um nó folha é criado. Em uma B-Tree, a inserção começa sempre nas folhas.
        BTreeNode root = new BTreeNode(true); 
        
        // Inserções:
        tree.insert(root, 10); // keys: [10]
        tree.insert(root, 50); // keys: [10, 50]
        
        // Esta inserção dispara a verificação de 'split', pois o nó já tem 2 chaves.
        tree.insert(root, 30); // keys: [10, 30, 50] (Aqui o split seria obrigatório)
        
        // Saída esperada (se a lógica de split estivesse completa):
        // A chave 30 subiria, e 10 e 50 formariam novos nós.

        System.out.println("\nÁrvore B finalizada (Exemplo de Nó Multi-Chave).");
    }
}
