import java.util.Scanner;

/**
 * Programa que lê 5 números inteiros, armazena em um array
 * e exibe os valores na ordem original e na ordem inversa.
 * 
 * Exemplo:
 * Entrada: 10, 20, 30, 40, 50
 * Saída original: 10, 20, 30, 40, 50
 * Saída inversa: 50, 40, 30, 20, 10
 * 
 * @author
 * @version 1.0
 */
public class ArrayOrdemInversa {

    /**
     * Método principal. Lê os números digitados pelo usuário,
     * armazena em um array e depois imprime nas duas ordens.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para capturar dados do teclado
        Scanner entrada = new Scanner(System.in);

        // Declara um array de inteiros com 5 posições
        int[] numeros = new int[5];

        // Mensagem inicial pedindo os números ao usuário
        System.out.println("Digite 5 números inteiros:");

        // Loop para ler e armazenar os 5 números digitados
        for (int i = 0; i < numeros.length; i++) {
            // Mostra o número da posição atual (de 1 a 5)
            System.out.print("Número " + (i + 1) + ": ");
            // Lê o valor digitado e armazena no array
            numeros[i] = entrada.nextInt();
        }

        // Linha em branco para organizar a saída
        System.out.println();

        // Exibe os números na ordem original
        System.out.println("Números na ordem original:");
        for (int i = 0; i < numeros.length; i++) {
            // Imprime o número na posição atual do array
            System.out.print(numeros[i] + " ");
        }

        // Duas quebras de linha para separar visualmente
        System.out.println("\n");

        // Exibe os números na ordem inversa
        System.out.println("Números na ordem inversa:");
        // O loop começa do último índice e vai até o primeiro
        for (int i = numeros.length - 1; i >= 0; i--) {
            // Imprime o número atual do array (de trás para frente)
            System.out.print(numeros[i] + " ");
        }

        // Quebra de linha final para formatar a saída
        System.out.println();

        // Fecha o objeto Scanner para liberar recursos do sistema
        entrada.close();
    }
}
