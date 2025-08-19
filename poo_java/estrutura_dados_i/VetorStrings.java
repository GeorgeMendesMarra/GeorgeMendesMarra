public class VetorStrings {
    public static void main(String[] args) {
        // Declara e inicializa um vetor de Strings com valores diretamente.
        // O tamanho do vetor é definido pela quantidade de elementos entre as chaves.
        String[] frutas = {"Maçã", "Banana", "Laranja", "Uva"};

        // Acessa e imprime o valor na primeira posição do vetor (índice 0).
        System.out.println("A primeira fruta é: " + frutas[0]);

        // Altera o valor da segunda posição do vetor (índice 1) para "Morango".
        frutas[1] = "Morango";

        // Imprime o novo valor da segunda posição.
        System.out.println("A segunda fruta agora é: " + frutas[1]);
    }
}
