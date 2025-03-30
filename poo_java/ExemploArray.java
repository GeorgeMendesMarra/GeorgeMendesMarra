class ExemploArray {
    public static void main(String[] args) {
        // Declara e instancia um array de inteiros chamado 'vetor' com 10 posições.
        int[] vetor = new int[10];

        // Imprime o tamanho do array 'vetor' usando a propriedade 'length'.
        System.out.println("Tamanho vetor:" + vetor.length);

        // Inicia um loop 'for' que itera sobre cada posição do array 'vetor'.
        // 'i' começa em 0 e continua até que 'i' seja menor que o tamanho do array.
        for (int i = 0; i < vetor.length; i++) {
            // Atribui o valor de 'i' à posição 'i' do array 'vetor'.
            // Isso preenche o array com os valores de 0 a 9.
            vetor[i] = i;

            // Imprime o valor armazenado na posição 'i' do array 'vetor'.
            System.out.println(vetor[i]);
        }
    }
}
