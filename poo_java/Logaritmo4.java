import java.util.Scanner;

/**
 * Exemplo com validação de entrada para evitar logaritmos inválidos.
 */
public class Logaritmo4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número positivo: ");
        double x = sc.nextDouble();

        if (x <= 0) {
            System.out.println("Erro: logaritmos só são definidos para números positivos.");
        } else {
            System.out.println("ln(x) = " + Math.log(x));
            System.out.println("log10(x) = " + Math.log10(x));
        }
    }
}
