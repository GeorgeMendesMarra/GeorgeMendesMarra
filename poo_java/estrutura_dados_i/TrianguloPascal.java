/**
 * Gera e imprime o Triângulo de Pascal com 6 linhas.
 *
 * O Triângulo de Pascal funciona assim:
 * - A primeira e última posição de cada linha são sempre 1.
 * - As posições internas são a soma dos dois valores imediatamente acima.
 */
public class TrianguloPascal {

    public static void main(String[] args) {
        int linhas = 6;
        int[][] pascal = new int[linhas][linhas];

        // Preenche o triângulo
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j <= i; j++) {

                // As bordas são 1
                if (j == 0 || j == i) {
                    pascal[i][j] = 1;
                } else {
                    // Soma dos dois valores acima
                    pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
                }
            }
        }

        // Imprime o triângulo
        for (int i = 0; i < linhas; i++) {

            // Espaçamento para centralizar
            for (int s = 0; s < linhas - i; s++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print(pascal[i][j] + " ");
            }

            System.out.println();
        }
    }
}
