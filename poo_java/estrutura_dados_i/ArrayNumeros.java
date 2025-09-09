import java.text.DecimalFormat; // Importa a classe DecimalFormat para formatar números.

public class ArrayNumeros {
    
    // Método principal, onde a execução do programa começa.
    public static void main (String[] args) {
        
        // Declara e inicializa um array de inteiros chamado 'inteiro' com 10 posições.
        int[] inteiro = new int[10];
        
        // Cria um objeto da classe DecimalFormat para formatar a saída.
        DecimalFormat df = new DecimalFormat();
        
        // Define o padrão de formatação para 3 dígitos, preenchendo com zeros à esquerda.
        // Exemplo: O número 5 se torna "005".
        df.applyPattern("000");
        
        // Inicia um loop 'for' que percorre o array do índice 0 até o final.
        for (int i = 0; i < inteiro.length; i++) {
            
            // Gera um número aleatório entre 0 e 999.
            // Math.random() retorna um valor entre 0.0 e 1.0.
            // Multiplicando por 1000, obtemos um valor entre 0.0 e 999.99...
            // O casting para (int) remove a parte decimal, deixando um número inteiro.
            inteiro[i] = (int) (Math.random() * 1000);
            
            // Imprime o valor do array na posição atual.
            // O número é formatado para ter 3 dígitos, usando o objeto 'df' criado anteriormente.
            System.out.println(df.format(inteiro[i]));
        }
    }
}
