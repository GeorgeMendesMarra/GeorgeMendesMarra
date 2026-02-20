public class ArrayBidimensional {
    public static void main (String[] args) {
        // Declara e inicializa uma matriz bidimensional de 3 linhas por 3 colunas.
        // O tipo de dado é int (números inteiros).
        int[][] matriz = new int[3][3];

        // Atribui valores a cada elemento da matriz, especificando a linha e a coluna.
        // Por exemplo, matriz[0][0] acessa o elemento na primeira linha e primeira coluna.
        matriz[0][0] = 120; matriz[1][0] = 232; matriz[2][0] = 201;
        matriz[0][1] = 120; matriz[1][1] = 232; matriz[2][1] = 201;
        matriz[0][2] = 120; matriz[1][2] = 232; matriz[2][2] = 201;

        // Início do laço externo para percorrer as linhas da matriz.
        // O laço vai de 0 até o número total de linhas (matriz.length).
        for (int linha = 0; linha < matriz.length; linha++) {
            // Início do laço interno para percorrer as colunas da linha atual.
            // O laço vai de 0 até o número de colunas daquela linha (matriz[linha].length).
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                // Imprime a posição (linha e coluna) e o valor do elemento.
                System.out.println("Elemento na posição [" + linha + "][" + coluna + "]: " + matriz[linha][coluna]);
            }
        }
    }
}
