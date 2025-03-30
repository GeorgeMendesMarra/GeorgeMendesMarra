class Matrizes {
    public static void main(String[] args) {
        // Declara e instancia uma matriz de inteiros com 2 linhas e 3 colunas.
        int[][] matriz = new int[2][3];

        // Loop externo para iterar pelas linhas da matriz.
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("Linha: " + i);

            // Loop interno para iterar pelas colunas da matriz.
            for (int j = 0; j < matriz[i].length; j++) {
                // Atribui um valor à posição [i][j] da matriz.
                // Neste exemplo, atribuímos i * 3 + j, que gera valores diferentes.
                matriz[i][j] = i * 3 + j;

                // Imprime a posição e o valor do elemento na matriz.
                System.out.println("  matriz[" + i + "][" + j + "] = " + matriz[i][j]);
            }
        }
    }
}
