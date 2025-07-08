// Classe para representar uma Fila (Queue)
// Uma fila é uma estrutura de dados linear que segue o princípio FIFO (First-In, First-Out - Primeiro a Entrar, Primeiro a Sair).
// Pense em uma fila de banco: a primeira pessoa a entrar na fila é a primeira a ser atendida.
class MinhaFila {
    private int maxSize;         // Tamanho máximo que a fila pode armazenar.
    private long[] queueArray;   // O array para armazenar os elementos da fila.
    private int front;           // Índice do elemento na "frente" da fila (o primeiro a ser removido).
    private int rear;            // Índice do elemento na "traseira" da fila (o último a ser adicionado).
    private int nItems;          // Contador para o número atual de elementos na fila.

    // Construtor da classe MinhaFila.
    // Inicializa a fila com a capacidade máxima especificada.
    public MinhaFila(int size) {
        this.maxSize = size;                // Define o tamanho máximo da fila.
        this.queueArray = new long[maxSize]; // Inicializa o array com o tamanho máximo.
        this.front = 0;                     // 'front' começa em 0 (o primeiro slot do array).
        this.rear = -1;                     // 'rear' começa em -1, pois ainda não há elementos na fila.
        this.nItems = 0;                    // O número de itens na fila é 0 inicialmente.
    }

    // Método 'insert' (enfileirar): Adiciona um novo elemento à traseira da fila.
    // Parâmetro 'j': O valor (do tipo long) a ser adicionado na fila.
    public void insert(long j) {
        // Verifica se a fila está cheia.
        if (isFull()) {
            System.out.println("Erro: Fila cheia. Não é possível adicionar " + j);
        } else {
            // Se 'rear' atingiu o final do array, ele "volta" para o início do array (fila circular).
            // Isso evita que o array se esgote rapidamente se houver muitas inserções e remoções.
            if (rear == maxSize - 1) {
                rear = -1; // Volta 'rear' para o início do array.
            }
            queueArray[++rear] = j; // Incrementa 'rear' e insere o elemento.
            nItems++;               // Incrementa o contador de itens.
            System.out.println(j + " adicionado à fila."); // Confirmação da operação.
        }
    }

    // Método 'remove' (desenfileirar): Remove e retorna o elemento da frente da fila.
    // Retorna o elemento que foi removido.
    public long remove() {
        // Verifica se a fila está vazia.
        if (isEmpty()) {
            System.out.println("Erro: Fila vazia. Não é possível remover.");
            return -1; // Retorna -1 para indicar erro. Em aplicações robustas, lançaria uma exceção.
        } else {
            long temp = queueArray[front++]; // Pega o elemento da posição 'front' e depois incrementa 'front'.
                                             // Isso faz com que 'front' aponte para o próximo elemento.

            // Se 'front' atingiu o final do array, ele "volta" para o início do array (fila circular).
            if (front == maxSize) {
                front = 0; // Volta 'front' para o início do array.
            }
            nItems--; // Decrementa o contador de itens.
            System.out.println(temp + " removido da fila."); // Confirmação da operação.
            return temp; // Retorna o elemento que foi removido.
        }
    }

    // Método 'peekFront': Retorna o elemento que está na frente da fila sem removê-lo.
    // Retorna o elemento da frente, ou -1 se a fila estiver vazia.
    public long peekFront() {
        // Verifica se a fila está vazia.
        if (isEmpty()) {
            System.out.println("Fila vazia. Nenhum elemento para ver.");
            return -1;
        } else {
            // Apenas retorna o elemento na posição 'front'. 'front' não é alterado.
            System.out.println("Elemento na frente: " + queueArray[front]);
            return queueArray[front];
        }
    }

    // Método 'isEmpty': Verifica se a fila está vazia.
    // Retorna 'true' se a fila não tiver elementos, 'false' caso contrário.
    public boolean isEmpty() {
        return (nItems == 0); // A fila está vazia se o contador de itens for 0.
    }

    // Método 'isFull': Verifica se a fila está cheia.
    // Retorna 'true' se a fila atingiu sua capacidade máxima, 'false' caso contrário.
    public boolean isFull() {
        return (nItems == maxSize); // A fila está cheia se o contador de itens for igual ao tamanho máximo.
    }

    // Método 'size': Retorna o número atual de elementos na fila.
    public int size() {
        return nItems; // Retorna o valor do contador de itens.
    }
}

// Classe principal para testar a implementação da Fila.
public class ExemploFila {
    public static void main(String[] args) {
        // Cria uma nova instância da 'MinhaFila' com uma capacidade máxima de 5 elementos.
        MinhaFila fila = new MinhaFila(5);

        // Demonstração da operação 'insert' (adicionar elementos à fila)
        System.out.println("--- Adicionando elementos ---");
        fila.insert(10); // Adiciona 10
        fila.insert(20); // Adiciona 20
        fila.insert(30); // Adiciona 30

        // Demonstração da operação 'peekFront' (espiar o elemento da frente)
        fila.peekFront(); // Deve exibir 10, pois é o primeiro a entrar.

        fila.insert(40); // Adiciona 40
        fila.insert(50); // Adiciona 50

        // Tentativa de adicionar um elemento quando a fila já está cheia.
        fila.insert(60); // Isso deve gerar uma mensagem de erro: "Fila cheia."

        System.out.println("\n--- Removendo elementos da fila ---");
        // Demonstração da operação 'remove' (remover elementos da fila)
        fila.remove(); // Remove 10 (o primeiro que entrou)
        fila.remove(); // Remove 20

        // Demonstração da operação 'peekFront' após remoções
        fila.peekFront(); // Deve exibir 30.

        // Adicionando mais elementos para testar a fila circular
        System.out.println("\n--- Adicionando mais elementos para testar a fila circular ---");
        fila.insert(60); // Agora há espaço novamente
        fila.insert(70); // Há espaço novamente

        fila.remove(); // Remove 30
        fila.remove(); // Remove 40
        fila.remove(); // Remove 50
        fila.remove(); // Remove 60
        fila.remove(); // Remove 70

        // Tentativa de remover um elemento quando a fila já está vazia.
        fila.remove(); // Isso deve gerar uma mensagem de erro: "Fila vazia."

        // Verifica se a fila está vazia após todas as remoções.
        System.out.println("\nA fila está vazia? " + fila.isEmpty()); // Deve exibir "true".
    }
}
