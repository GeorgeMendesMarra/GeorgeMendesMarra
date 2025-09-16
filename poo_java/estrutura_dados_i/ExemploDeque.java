import java.util.ArrayDeque;
import java.util.Deque;

public class ExemploDeque {
    public static void main(String[] args) {
        // 1. Cria uma nova instância de Deque, usando ArrayDeque como implementação.
        // Deque é a interface preferida para se trabalhar com pilhas em Java.
        // O tipo Deque segue o padrão LIFO (Last-In, First-Out).
        Deque<String> historicoDeNavegacao = new ArrayDeque<>();
        
        // 2. Adiciona elementos na pilha usando o método push().
        // Cada elemento adicionado se torna o novo topo da pilha.
        historicoDeNavegacao.push("pagina-inicial.com");
        historicoDeNavegacao.push("produtos.com");
        historicoDeNavegacao.push("carrinho.com"); // Este é o topo da pilha agora.
        
        // 3. Acessa o elemento do topo da pilha sem removê-lo.
        // O método peek() retorna "carrinho.com".
        System.out.println("Página atual (topo): " + historicoDeNavegacao.peek());
        
        // 4. Remove e retorna o elemento do topo da pilha.
        // O método pop() remove "carrinho.com" e o retorna para ser impresso.
        System.out.println("Voltando para a página anterior: " + historicoDeNavegacao.pop());
        
        // 5. Acessa o novo elemento do topo da pilha.
        // Após a remoção, o novo topo da pilha é "produtos.com".
        System.out.println("Página atual: " + historicoDeNavegacao.peek());
    }
}
