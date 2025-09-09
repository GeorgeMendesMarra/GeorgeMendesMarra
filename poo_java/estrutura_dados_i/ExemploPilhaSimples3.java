public class ExemploPilhaSimples3 {
    private int[] elementos;   // Array para armazenar os elementos da pilha
    private int topo;          // Índice do elemento no topo da pilha
    private int capacidade;    // Capacidade máxima da pilha

    // Construtor: cria a pilha com a capacidade desejada
    public Pilha(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new int[capacidade]; 
        this.topo = -1; // -1 significa que a pilha está vazia
    }

    // Verifica se a pilha está cheia
    public boolean estaCheia() {
        return topo == capacidade - 1;
    }

    // Verifica se a pilha está vazia
    public boolean estaVazia() {
        return topo == -1;
    }

    // Insere um novo elemento no topo da pilha (push)
    public void empilhar(int valor) {
        if (estaCheia()) {
            System.out.println("A pilha está cheia! Não é possível adicionar " + valor);
        } else {
            elementos[++topo] = valor; // Incrementa topo e insere valor
            System.out.println("Empilhado: " + valor);
        }
    }

    // Remove e retorna o elemento do topo da pilha (pop)
    public int desempilhar() {
        if (estaVazia()) {
            System.out.println("A pilha está vazia! Nada a remover.");
            return -1; // Valor especial para indicar pilha vazia
        } else {
            return elementos[topo--]; // Retorna o elemento e decrementa o topo
        }
    }

    // Retorna o valor do topo da pilha sem remover (peek)
    public int topo() {
        if (estaVazia()) {
            System.out.println("A pilha está vazia!");
            return -1;
        } else {
            return elementos[topo];
        }
    }

    // Mostra todos os elementos da pilha
    public void mostrar() {
        if (estaVazia()) {
            System.out.println("A pilha está vazia!");
        } else {
            System.out.print("Pilha: ");
            for (int i = 0; i <= topo; i++) {
                System.out.print(elementos[i] + " ");
            }
            System.out.println();
        }
    }

    // Programa principal para testar a pilha
    public static void main(String[] args) {
        Pilha pilha = new Pilha(5); // Cria uma pilha com capacidade para 5 elementos

        pilha.empilhar(10); // Adiciona 10
        pilha.empilhar(20); // Adiciona 20
        pilha.empilhar(30); // Adiciona 30

        pilha.mostrar(); // Mostra a pilha

        System.out.println("Topo da pilha: " + pilha.topo()); // Consulta o topo

        System.out.println("Desempilhado: " + pilha.desempilhar()); // Remove o topo
        pilha.mostrar(); // Mostra novamente
    }
}
