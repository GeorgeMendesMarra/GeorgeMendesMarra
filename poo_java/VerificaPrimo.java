import java.util.Scanner;

/**
 * Programa que verifica se um número inteiro informado pelo usuário é primo.
 * 
 * Um número primo é aquele que só pode ser dividido por 1 e por ele mesmo.
 * 
 * Exemplo:
 * Entrada: 7 → Saída: "É primo"
 * Entrada: 9 → Saída: "Não é primo"
 * 
 * @author
 * @version 1.0
 */
public class VerificaPrimo {

    /**
     * Método principal. Lê um número e verifica se ele é primo.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler dados do teclado
        Scanner entrada = new Scanner(System.in);

        // Solicita que o usuário digite um número inteiro
        System.out.print("Digite um número inteiro: ");
        int numero = entrada.nextInt(); // Lê o número informado

        // Variável booleana que indica se o número é primo (começa como verdadeiro)
        boolean ehPrimo = true;

        // Verifica se o número é menor ou igual a 1 (não são primos)
        if (numero <= 1) {
            ehPrimo = false;
        } else {
            // Loop que testa divisores de 2 até a metade do número
            for (int i = 2; i <= numero / 2; i++) {
                // Se o número for divisível por qualquer outro número, não é primo
                if (numero % i == 0) {
                    ehPrimo = false;
                    break; // Encerra o loop assim que encontra um divisor
                }
            }
        }

        // Exibe o resultado com base na variável booleana
        if (ehPrimo) {
            System.out.println("É primo");
        } else {
            System.out.println("Não é primo");
        }

        // Fecha o Scanner para liberar recursos
        entrada.close();
    }
}
