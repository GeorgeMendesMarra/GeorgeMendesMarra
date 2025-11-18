import java.util.Scanner;

/**
 * Lê um número fornecido pelo usuário e calcula:
 * - logaritmo natural (ln)
 * - log base 10
 * - log base 2
 */
public class Logaritmo1 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite um número: ");
        double valor = entrada.nextDouble();

        System.out.println("=== RESULTADOS ===");
        System.out.println("ln(x)        = " + Math.log(valor));
        System.out.println("log10(x)     = " + Math.log10(valor));
        System.out.println("log2(x)      = " + (Math.log(valor) / Math.log(2)));
    }
}
