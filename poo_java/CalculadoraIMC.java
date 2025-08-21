import java.util.Scanner;

public class CalculadoraIMC {

    public static void main(String[] args) {

        // Cria um objeto Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Calculadora de IMC ---");

        // Solicita e lê o peso do usuário
        System.out.print("Digite seu peso em kg: ");
        double peso = scanner.nextDouble();

        // Solicita e lê a altura do usuário
        System.out.print("Digite sua altura em metros (ex: 1.75): ");
        double altura = scanner.nextDouble();

        // Fecha o scanner para evitar vazamento de recursos
        scanner.close();

        // Calcula o IMC
        // A fórmula é: peso / (altura * altura)
        double imc = peso / (altura * altura);

        // Exibe o IMC com duas casas decimais
        System.out.printf("\nSeu IMC é: %.2f\n", imc);

        // Determina a categoria do IMC
        if (imc < 18.5) {
            System.out.println("Status: Abaixo do peso");
        } else if (imc >= 18.5 && imc < 24.9) {
            System.out.println("Status: Peso normal");
        } else if (imc >= 25.0 && imc < 29.9) {
            System.out.println("Status: Sobrepeso");
        } else if (imc >= 30.0 && imc < 34.9) {
            System.out.println("Status: Obesidade grau 1");
        } else if (imc >= 35.0 && imc < 39.9) {
            System.out.println("Status: Obesidade grau 2");
        } else {
            System.out.println("Status: Obesidade grau 3 (Obesidade mórbida)");
        }
    }
}
