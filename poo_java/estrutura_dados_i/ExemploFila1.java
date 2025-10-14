import java.util.LinkedList;
import java.util.Queue;

// Classe principal para demonstrar a fila
public class ExemploFila1 {

    public static void main(String[] args) {
        // A interface Queue define o comportamento de fila (FIFO).
        // A implementação comum é o LinkedList, que gerencia a ordem dos elementos.
        Queue<String> filaDeClientes = new LinkedList<>();

        // 1. Adicionar elementos (enqueue): Onde novos itens chegam (no final da fila)
        System.out.println("--- Adicionando Clientes ---");
        
        // O método add() ou offer() é usado para inserir um elemento no final da fila.
        filaDeClientes.add("Alice"); // Alice é a primeira a entrar
        filaDeClientes.add("Bruno"); // Bruno é o segundo
        filaDeClientes.add("Carla"); // Carla é a terceira
        
        // Imprime o estado atual da fila
        System.out.println("Fila após adição: " + filaDeClientes); // Saída: [Alice, Bruno, Carla]

        // 2. Visualizar o primeiro elemento (peek): Olhar quem é o próximo sem removê-lo
        
        // O método peek() retorna o elemento que está no início da fila, 
        // mas não o remove. Se a fila estiver vazia, retorna null.
        String primeiro = filaDeClientes.peek();
        System.out.println("\nPrimeiro da fila (peek): " + primeiro); // Saída: Alice
        
        // A fila permanece inalterada após o peek
        System.out.println("Fila após peek: " + filaDeClientes); // Saída: [Alice, Bruno, Carla]

        // 3. Remover elementos (dequeue): Atendimento ou processamento (o primeiro a entrar, sai)
        System.out.println("\n--- Atendendo Clientes (Removendo) ---");
        
        // O método poll() remove e retorna o elemento que está no início da fila (FIFO).
        // Se a fila estiver vazia, retorna null. É o método mais usado para "desenfileirar".
        String atendido1 = filaDeClientes.poll();
        System.out.println("Cliente atendido (poll): " + atendido1); // Saída: Alice (Sai o primeiro que entrou)

        String atendido2 = filaDeClientes.poll();
        System.out.println("Cliente atendido (poll): " + atendido2); // Saída: Bruno (Sai o segundo)
        
        // Verifica quem sobrou na fila
        System.out.println("Fila após remoções: " + filaDeClientes); // Saída: [Carla]

        // 4. Verificar se está vazia
        
        // O método isEmpty() verifica se a fila contém elementos.
        boolean vazia = filaDeClientes.isEmpty();
        System.out.println("\nA fila está vazia? " + vazia); // Saída: false

        // 5. Remover o último elemento restante
        String atendido3 = filaDeClientes.poll();
        System.out.println("Cliente atendido (poll): " + atendido3); // Saída: Carla

        // 6. Verificar se está vazia novamente
        vazia = filaDeClientes.isEmpty();
        System.out.println("A fila está vazia? " + vazia); // Saída: true
    }
}
