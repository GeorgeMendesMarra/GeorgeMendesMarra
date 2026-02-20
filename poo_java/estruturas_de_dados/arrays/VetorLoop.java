public class VetorLoop {
    public static void main(String[] args) {
        // Declara e inicializa um vetor de inteiros com as notas.
        int[] notas = {85, 92, 78, 95, 88};
        
        // Imprime uma mensagem informativa antes de listar as notas.
        System.out.println("As notas são:");

        // Usa um loop 'for' para percorrer cada elemento do vetor.
        // 'i' começa em 0 e vai até o tamanho do vetor (notas.length - 1).
        for (int i = 0; i < notas.length; i++) {
            // Imprime o índice (i + 1, para começar a contagem em 1) e o valor em cada posição.
            System.out.println("Nota " + (i + 1) + ": " + notas[i]);
        }
    }
}
