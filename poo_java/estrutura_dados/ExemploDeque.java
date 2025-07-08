// Classe para representar um Deque (Fila de Duas Pontas)
// Um Deque permite adicionar e remover elementos de ambas as extremidades.
// Isso significa que ele pode funcionar como uma Fila (FIFO) ou uma Pilha (LIFO).
class MeuDeque {
    private int maxSize;       // Tamanho máximo do Deque.
    private long[] dequeArray; // Array para armazenar os elementos do Deque.
    private int front;         // Índice do elemento na "frente" do Deque.
    private int rear;          // Índice do elemento na "traseira" do Deque.
    private int nItems;        // Contador para o número atual de elementos no Deque.

    // Construtor: Inicializa o Deque com um tamanho máximo.
    public MeuDeque(int size) {
        this.maxSize = size;
        this.dequeArray = new long[maxSize];
        this.front = 0;                     // O 'front' começa no início do array.
        this.rear = -1;                     // O 'rear' começa antes do início, indicando vazio.
        this.nItems = 0;                    // Inicialmente, não há itens no Deque.
    }

    // Método para adicionar um elemento na TRASEIRA do Deque (como 'insert' em fila).
    public void insertRear(long j) {
        if (isFull()) {
            System.out.println("Erro: Deque cheio. Não é possível adicionar " + j + " na traseira.");
            return;
        }
        // Se 'rear' atingiu o final do array, ele "volta" para o início (comportamento circular).
        if (rear == maxSize - 1) {
            rear = -1; // Redefine 'rear' para -1 para o próximo incremento ir para a posição 0.
        }
        dequeArray[++rear] = j; // Incrementa 'rear' e adiciona o elemento.
        nItems++;               // Incrementa o contador de itens.
        System.out.println(j + " adicionado na traseira do Deque.");
    }

    // Método para adicionar um elemento na FRENTE do Deque.
    public void insertFront(long j) {
        if (isFull()) {
            System.out.println("Erro: Deque cheio. Não é possível adicionar " + j + " na frente.");
            return;
        }
        // Se 'front' está no início do array (posição 0), ele "volta" para o final (comportamento circular).
        if (front == 0) {
            front = maxSize; // Redefine 'front' para 'maxSize' para o próximo decremento ir para 'maxSize - 1'.
        }
        dequeArray[--front] = j; // Decrementa 'front' e adiciona o elemento.
        nItems++;               // Incrementa o contador de itens.
        System.out.println(j + " adicionado na frente do Deque.");
    }

    // Método para remover um elemento da FRENTE do Deque (como 'remove' em fila).
    public long removeFront() {
        if (isEmpty()) {
            System.out.println("Erro: Deque vazio. Não é possível remover da frente.");
            return -1; // Retorna -1 para indicar erro.
        }
        long temp = dequeArray[front++]; // Pega o elemento da frente e move 'front' para a próxima posição.

        // Se 'front' passou do final do array, ele "volta" para o início (comportamento circular).
        if (front == maxSize) {
            front = 0; // Redefine 'front' para 0.
        }
        nItems--; // Decrementa o contador de itens.
        System.out.println(temp + " removido da frente do Deque.");
        return temp; // Retorna o elemento removido.
    }

    // Método para remover um elemento da TRASEIRA do Deque (como 'pop' em pilha).
    public long removeRear() {
        if (isEmpty()) {
            System.out.println("Erro: Deque vazio. Não é possível remover da traseira.");
            return -1; // Retorna -1 para indicar erro.
        }
        // Se 'rear' está no início do array, ele "volta" para o final (comportamento circular).
        if (rear == -1) { // Isso pode acontecer se rear era 0 e foi decrementado
            rear = maxSize - 1;
        }
        long temp = dequeArray[rear--]; // Pega o elemento da traseira e move 'rear' para a posição anterior.

        nItems--; // Decrementa o contador de itens.
        System.out.println(temp + " removido da traseira do Deque.");
        return temp; // Retorna o elemento removido.
    }

    // Método para ver o elemento na FRENTE sem removê-lo.
    public long peekFront() {
        if (isEmpty()) {
            System.out.println("Deque vazio. Nenhum elemento na frente para ver.");
            return -1;
        }
        System.out.println("Elemento na frente: " + dequeArray[front]);
        return dequeArray[front];
    }

    // Método para ver o elemento na TRASEIRA sem removê-lo.
    public long peekRear() {
        if (isEmpty()) {
            System.out.println("Deque vazio. Nenhum elemento na traseira para ver.");
            return -1;
        }
        // Calcula o índice correto da traseira, considerando o comportamento circular.
        int actualRearIndex = (rear == -1) ? maxSize - 1 : rear; // Se rear é -1, significa que o elemento está no final
                                                                 // do array, ou que o Deque está vazio.
        if (nItems == 0) return -1; // Se não há itens, retorna -1

        // Precisa lidar com o caso onde rear já "circulou" e é menor que front.
        // Ou simplesmente usar 'rear' se ele não for -1.
        // A lógica do 'rear' já é gerida para ser o índice correto do último elemento.
        System.out.println("Elemento na traseira: " + dequeArray[rear]);
        return dequeArray[rear];
    }


    // Verifica se o Deque está vazio.
    public boolean isEmpty() {
        return (nItems == 0);
    }

    // Verifica se o Deque está cheio.
    public boolean isFull() {
        return (nItems == maxSize);
    }

    // Retorna o número atual de elementos no Deque.
    public int size() {
        return nItems;
    }
}

// Classe principal para testar o Deque.
public class ExemploDeque {
    public static void main(String[] args) {
        MeuDeque deque = new MeuDeque(5); // Cria um Deque com capacidade para 5 elementos.

        System.out.println("--- Adicionando e Removendo na Traseira (como Fila) ---");
        deque.insertRear(10); // 10
        deque.insertRear(20); // 10, 20
        deque.peekFront();    // Deve ser 10
        deque.peekRear();     // Deve ser 20
        deque.removeFront();  // Remove 10 (agora: 20)
        deque.peekFront();    // Deve ser 20

        System.out.println("\n--- Adicionando e Removendo na Frente (como Pilha) ---");
        deque.insertFront(5); // 5, 20
        deque.insertFront(1); // 1, 5, 20
        deque.peekFront();    // Deve ser 1
        deque.peekRear();     // Deve ser 20
        deque.removeFront();  // Remove 1 (agora: 5, 20)
        deque.peekFront();    // Deve ser 5

        System.out.println("\n--- Operações mistas e Teste de Cheio/Vazio ---");
        deque.insertRear(30); // 5, 20, 30
        deque.insertRear(40); // 5, 20, 30, 40
        deque.insertFront(0); // 0, 5, 20, 30, 40 (Deque cheio)
        System.out.println("Tamanho do Deque: " + deque.size()); // Deve ser 5

        deque.insertRear(50); // Tentativa de adicionar em deque cheio na traseira
        deque.insertFront(-1); // Tentativa de adicionar em deque cheio na frente

        deque.removeRear(); // Remove 40 (agora: 0, 5, 20, 30)
        deque.removeFront(); // Remove 0 (agora: 5, 20, 30)

        System.out.println("\n--- Esvaziando o Deque ---");
        while (!deque.isEmpty()) {
            deque.removeFront();
        }
        deque.removeFront(); // Tentativa de remover de deque vazio na frente
        deque.removeRear();  // Tentativa de remover de deque vazio na traseira

        System.out.println("O Deque está vazio? " + deque.isEmpty()); // Deve ser true
    }
}
