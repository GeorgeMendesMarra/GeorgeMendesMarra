import java.util.Stack;

public class ExemploPilhaSimples2 {
    public static void main(String[] args) {
        // Criando uma pilha de inteiros
        Stack<Integer> pilha = new Stack<>();

        // Empilhando elementos (push)
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        System.out.println("Pilha atual: " + pilha);

        // Consultando o topo da pilha (peek)
        System.out.println("Topo da pilha: " + pilha.peek());

        // Desempilhando elementos (pop)
        int removido = pilha.pop();
        System.out.println("Elemento removido: " + removido);
        System.out.println("Pilha após pop: " + pilha);

        // Verificando se a pilha está vazia
        System.out.println("A pilha está vazia? " + pilha.isEmpty());
    }
}
