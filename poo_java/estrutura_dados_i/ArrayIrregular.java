public class ArrayIrregular {

    public static void main(String[] args) {

        // --- Demonstração de um Array Irregular ---

        // Declara um array bidimensional de Strings.
        // O primeiro colchete define o número de linhas (3),
        // mas o segundo fica vazio para indicar que o número de colunas
        // será definido separadamente para cada linha.
        String[][] nomes = new String[3][];

        // 1. Inicializa a primeira linha do array.
        // Cria um array de Strings com 2 posições e o atribui à primeira linha (índice 0).
        nomes[0] = new String[2]; 

        // 2. Inicializa a segunda linha do array.
        // Cria um array de Strings com 3 posições e o atribui à segunda linha (índice 1).
        nomes[1] = new String[3]; 

        // 3. Inicializa a terceira linha do array.
        // Cria um array de Strings com 1 posição e o atribui à terceira linha (índice 2).
        nomes[2] = new String[1]; 

        // Agora o array 'nomes' é uma estrutura irregular, com cada linha
        // (ou sub-array) tendo um tamanho diferente.

        // --- Atribuindo valores a cada posição ---

        // Atribui valores à primeira linha (índice 0).
        nomes[0][0] = "João";
        nomes[0][1] = "Maria";

        // Atribui valores à segunda linha (índice 1).
        nomes[1][0] = "Pedro";
        nomes[1][1] = "Ana";
        nomes[1][2] = "Carlos";

        // Atribui valores à terceira linha (índice 2).
        nomes[2][0] = "Paula";

        // Exibindo os valores para confirmar a estrutura.
        System.out.println("Array Irregular:");
        for (int i = 0; i < nomes.length; i++) {
            System.out.print("Linha " + i + ": ");
            for (int j = 0; j < nomes[i].length; j++) {
                System.out.print(nomes[i][j] + " ");
            }
            System.out.println(); // Pula para a próxima linha
        }
    }
}
