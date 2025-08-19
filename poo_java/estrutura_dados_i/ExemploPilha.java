// Classe para representar uma Pilha (Stack)
// Uma pilha é uma estrutura de dados linear que segue o princípio LIFO (Last-In, First-Out - Último a Entrar, Primeiro a Sair).
// Pense em uma pilha de pratos: você só pode adicionar um prato no topo e remover o prato que está no topo.
class MinhaPilha {
    private int maxSize;        // Declaração do tamanho máximo que a pilha poderá armazenar.
                                // Este é o limite de elementos que a pilha pode conter.
    private long[] stackArray;  // O array (vetor) que será usado para armazenar os elementos da pilha.
                                // Neste exemplo, ele armazena números do tipo 'long'.
                                // Você pode alterá-lo para 'int', 'String', 'Object', etc., conforme a necessidade.
    private int top;            // Variável que guarda o índice do elemento que está atualmente no "topo" da pilha.
                                // O 'top' sempre aponta para o último elemento inserido.

    // Construtor da classe MinhaPilha.
    // É chamado automaticamente quando um novo objeto 'MinhaPilha' é criado.
    // Ele inicializa a pilha com a capacidade máxima especificada.
    public MinhaPilha(int size) {
        this.maxSize = size;            // Define o tamanho máximo da pilha com o valor passado.
        this.stackArray = new long[maxSize]; // Inicializa o array com o tamanho máximo.
        this.top = -1;                  // Define 'top' como -1. Isso significa que a pilha está inicialmente vazia,
                                        // pois não há nenhum elemento válido no índice -1.
    }

    // Método 'push' (empilhar): Adiciona um novo elemento ao topo da pilha.
    // Parâmetro 'j': O valor (do tipo long) a ser adicionado na pilha.
    public void push(long j) {
        // Primeiro, verifica se a pilha já atingiu sua capacidade máxima.
        // Se a pilha estiver cheia, não é possível adicionar mais elementos.
        if (isFull()) {
            System.out.println("Erro: Pilha cheia. Não é possível adicionar " + j);
        } else {
            // Se a pilha não estiver cheia:
            // 1. Incrementa o 'top' em 1 (usando o operador de pré-incremento '++top').
            //    Isso move o 'top' para a próxima posição disponível.
            // 2. Insere o elemento 'j' na nova posição apontada por 'top' no 'stackArray'.
            stackArray[++top] = j;
            System.out.println(j + " adicionado à pilha."); // Confirmação da operação.
        }
    }

    // Método 'pop' (desempilhar): Remove e retorna o elemento do topo da pilha.
    // Retorna o elemento que foi removido.
    public long pop() {
        // Primeiro, verifica se a pilha está vazia.
        // Se a pilha estiver vazia, não há elementos para remover.
        if (isEmpty()) {
            System.out.println("Erro: Pilha vazia. Não é possível remover.");
            return -1; // Retorna -1 para indicar um erro ou pilha vazia.
                       // Em aplicações mais robustas, uma exceção como 'EmptyStackException' seria lançada.
        } else {
            // Se a pilha não estiver vazia:
            // 1. Pega o elemento que está na posição atual do 'top'.
            // 2. Decrementa o 'top' em 1 (usando o operador de pós-decremento 'top--').
            //    Isso faz com que o 'top' aponte para o elemento anterior, efetivamente "removendo"
            //    o elemento atual do topo da pilha (embora o valor ainda exista no array, ele não é mais acessível
            //    pela lógica da pilha).
            long temp = stackArray[top--];
            System.out.println(temp + " removido da pilha."); // Confirmação da operação.
            return temp; // Retorna o elemento que foi removido.
        }
    }

    // Método 'peek' (espiar): Retorna o elemento que está no topo da pilha sem removê-lo.
    // Retorna o elemento do topo, ou -1 se a pilha estiver vazia.
    public long peek() {
        // Verifica se a pilha está vazia.
        if (isEmpty()) {
            System.out.println("Pilha vazia. Nenhum elemento para ver.");
            return -1; // Retorna -1 se não houver elementos.
        } else {
            // Se a pilha não estiver vazia, apenas retorna o elemento na posição 'top'.
            // O 'top' não é alterado, pois o elemento não é removido.
            System.out.println("Elemento no topo: " + stackArray[top]);
            return stackArray[top];
        }
    }

    // Método 'isEmpty': Verifica se a pilha está vazia.
    // Retorna 'true' se a pilha estiver vazia, 'false' caso contrário.
    public boolean isEmpty() {
        // Uma pilha está vazia quando o 'top' aponta para -1 (sua posição inicial).
        return (top == -1);
    }

    // Método 'isFull': Verifica se a pilha está cheia.
    // Retorna 'true' se a pilha estiver cheia, 'false' caso contrário.
    public boolean isFull() {
        // Uma pilha está cheia quando o 'top' atinge o índice máximo do array,
        // que é 'maxSize - 1' (arrays em Java são baseados em 0).
        return (top == maxSize - 1);
    }

    // Método 'size': Retorna o número atual de elementos na pilha.
    // Retorna o número de elementos como um inteiro.
    public int size() {
        // O número de elementos é 'top + 1', pois 'top' é um índice baseado em 0,
        // e -1 significa 0 elementos.
        return top + 1;
    }
}

// Classe principal para testar a implementação da Pilha.
public class ExemploPilha {
    public static void main(String[] args) {
        // Cria uma nova instância da 'MinhaPilha' com uma capacidade máxima de 5 elementos.
        MinhaPilha pilha = new MinhaPilha(5);

        // Demonstração da operação 'push' (adicionar elementos)
        System.out.println("--- Adicionando elementos ---");
        pilha.push(10); // Adiciona 10
        pilha.push(20); // Adiciona 20
        pilha.push(30); // Adiciona 30

        // Demonstração da operação 'peek' (espiar o topo)
        pilha.peek(); // Deve exibir 30, pois é o último elemento adicionado.

        pilha.push(40); // Adiciona 40
        pilha.push(50); // Adiciona 50

        // Tentativa de adicionar um elemento quando a pilha já está cheia.
        pilha.push(60); // Isso deve gerar uma mensagem de erro: "Pilha cheia."

        System.out.println("\n--- Removendo elementos da pilha ---");
        // Demonstração da operação 'pop' (remover elementos)
        pilha.pop(); // Remove 50 (o último que foi adicionado)
        pilha.pop(); // Remove 40

        // Demonstração da operação 'peek' após remoções
        pilha.peek(); // Deve exibir 30, pois 50 e 40 foram removidos.

        pilha.pop(); // Remove 30
        pilha.pop(); // Remove 20
        pilha.pop(); // Remove 10

        // Tentativa de remover um elemento quando a pilha já está vazia.
        pilha.pop(); // Isso deve gerar uma mensagem de erro: "Pilha vazia."

        // Verifica se a pilha está vazia após todas as remoções.
        System.out.println("\nA pilha está vazia? " + pilha.isEmpty()); // Deve exibir "true".
    }
}
