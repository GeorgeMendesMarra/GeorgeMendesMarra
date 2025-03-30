class VetorInicializado {
    public static void main(String[] args) {
        // Declara e inicializa um array de doubles chamado 'nota' com 10 valores.
        double[] nota = {7, 8, 8, 4, 4, 2, 1, 8, 6, 4};

        // Inicia um loop 'for' que itera sobre cada elemento do array 'nota'.
        // 'i' começa em 0 e continua até que 'i' seja menor que o tamanho do array.
        for (int i = 0; i < nota.length; i++) {
            // Imprime o valor de 'i' (índice do array) e o valor armazenado na posição 'i' do array 'nota'.
            System.out.println("i = " + i + ", Valor no vetor: " + nota[i]);
        }
    }
}
