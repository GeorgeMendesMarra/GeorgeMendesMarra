import java.util.Scanner; // Importa a classe Scanner para ler a entrada do usuário.

public class VerificaIdade {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do usuário.

        System.out.print("Digite sua idade: "); // Solicita ao usuário que digite sua idade.
        int idade = scanner.nextInt(); // Lê a idade inserida pelo usuário.

        // Verifica se a idade é maior ou igual a 18.
        if (idade >= 18) {
            // Se a condição for verdadeira, exibe a mensagem abaixo.
            System.out.println("Você é maior de idade.");
        } else {
            // Se a condição for falsa, exibe a mensagem abaixo.
            System.out.println("Você é menor de idade.");
        }

        scanner.close(); // Fecha o objeto Scanner para liberar recursos.
    }
}
