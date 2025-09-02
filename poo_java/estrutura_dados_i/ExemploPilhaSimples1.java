import java.util.Stack;

public class ExemploPilhaSimples1 {

    public static void main(String[] args) {

        // 1. Criando uma nova pilha
        Stack<String> nomes = new Stack<>();
        
        System.out.println("Pilha criada: " + nomes);
        System.out.println("A pilha está vazia? " + nomes.isEmpty());
        System.out.println("---");

        // 2. Adicionando elementos à pilha (método push())
        nomes.push("Carlos");
        nomes.push("Ana");
        nomes.push("Bruno");
        
        System.out.println("Elementos adicionados. Pilha atual: " + nomes);
        System.out.println("Tamanho da pilha: " + nomes.size());
        System.out.println("---");

        // 3. Verificando o elemento do topo sem removê-lo (método peek())
        String nomeDoTopo = nomes.peek();
        System.out.println("Elemento no topo (sem remover): " + nomeDoTopo);
        System.out.println("Pilha após o peek(): " + nomes);
        System.out.println("---");

        // 4. Removendo elementos da pilha (método pop())
        String nomeRemovido1 = nomes.pop();
        System.out.println("Elemento removido: " + nomeRemovido1);
        System.out.println("Pilha após a primeira remoção: " + nomes);

        String nomeRemovido2 = nomes.pop();
        System.out.println("Elemento removido: " + nomeRemovido2);
        System.out.println("Pilha após a segunda remoção: " + nomes);
        System.out.println("---");
        
        // 5. Tentando remover de uma pilha vazia
        // nomes.pop(); // Isso geraria uma exceção EmptyStackException
        
        System.out.println("A pilha está vazia? " + nomes.isEmpty());
    }
}
