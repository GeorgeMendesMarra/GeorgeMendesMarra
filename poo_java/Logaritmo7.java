import java.util.Scanner;

public class Logaritmo7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== MENU LOGARÍTMICO ===");
        System.out.println("1 – ln(x)");
        System.out.println("2 – log10(x)");
        System.out.println("3 – log_b(x)");
        System.out.print("Escolha: ");
        int op = sc.nextInt();

        System.out.print("Digite x: ");
        double x = sc.nextDouble();

        switch (op) {
            case 1:
                System.out.println("ln(" + x + ") = " + Math.log(x));
                break;
            case 2:
                System.out.println("log10(" + x + ") = " + Math.log10(x));
                break;
            case 3:
                System.out.print("Digite a base b: ");
                double b = sc.nextDouble();
                System.out.println("log_" + b + "(" + x + ") = " + (Math.log(x) / Math.log(b)));
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}
