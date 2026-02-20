public class VetorNumerosDecimais {
    public static void main(String[] args) {
        // Declara e inicializa um vetor de números decimais ('double').
        double[] precos = {1.50, 2.75, 5.00, 3.25};

        // Declara uma variável para armazenar a soma, inicializada com 0.
        double soma = 0;

        // Usa um loop 'for' para percorrer cada elemento do vetor.
        for (int i = 0; i < precos.length; i++) {
            // Adiciona o valor de cada posição à variável 'soma'.
            soma += precos[i];
        }

        // Imprime o resultado final da soma.
        System.out.println("A soma total dos preços é: " + soma);
    }
}
