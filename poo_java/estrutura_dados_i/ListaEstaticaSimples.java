public class ListaEstaticaSimples {

    // Constante que define o tamanho máximo da lista
    static final int TAMANHO_MAX = 5;

    // Vetor (lista estática) para armazenar os nomes dos alunos
    static String[] alunos = new String[TAMANHO_MAX];

    // Variável que controla quantos elementos já foram inseridos na lista
    static int contador = 0;

    // -------------------------------------------------------
    // Método para adicionar um novo aluno na lista
    // -------------------------------------------------------
    public static void adicionar(String nome) {
        // Verifica se ainda há espaço na lista
        if (contador < TAMANHO_MAX) {
            alunos[contador] = nome;   // Armazena o nome na posição atual
            contador++;                // Incrementa o número de elementos
            System.out.println("Aluno \"" + nome + "\" adicionado com sucesso!");
        } else {
            // Caso a lista esteja cheia
            System.out.println("Lista cheia! Não é possível adicionar \"" + nome + "\".");
        }
    }

    // -------------------------------------------------------
    // Método para listar todos os alunos cadastrados
    // -------------------------------------------------------
    public static void listar() {
        System.out.println("\n--- Lista de Alunos ---");

        // Percorre todas as posições já ocupadas
        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + " - " + alunos[i]);
        }

        // Caso não haja nenhum elemento
        if (contador == 0) {
            System.out.println("A lista está vazia.");
        }
    }

    // -------------------------------------------------------
    // Método para buscar um aluno pelo nome
    // -------------------------------------------------------
    public static void buscar(String nome) {
        boolean encontrado = false; // Flag para controlar se encontrou o nome

        // Percorre as posições ocupadas do vetor
        for (int i = 0; i < contador; i++) {
            // Compara o nome procurado com o nome armazenado (ignorando maiúsculas/minúsculas)
            if (alunos[i].equalsIgnoreCase(nome)) {
                System.out.println("Aluno \"" + nome + "\" encontrado na posição " + (i + 1));
                encontrado = true;
                break; // Sai do laço ao encontrar o aluno
            }
        }

        // Caso o nome não tenha sido encontrado
        if (!encontrado) {
            System.out.println("Aluno \"" + nome + "\" não encontrado na lista.");
        }
    }

    // -------------------------------------------------------
    // Método principal - ponto de entrada do programa
    // -------------------------------------------------------
    public static void main(String[] args) {
        // Adiciona alguns alunos manualmente
        adicionar("Alice");
        adicionar("Bruno");
        adicionar("Carla");
        adicionar("Daniel");
        adicionar("Eva");
        adicionar("Felipe"); // Este excederá o limite da lista

        // Exibe todos os alunos cadastrados
        listar();

        System.out.println();

        // Realiza buscas na lista
        buscar("Carla"); // Deve encontrar
        buscar("Lucas"); // Não deve encontrar
    }
}
