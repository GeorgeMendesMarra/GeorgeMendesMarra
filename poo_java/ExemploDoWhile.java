import java.util.Scanner; // Importa a classe Scanner para ler a entrada do usuário.

public class ExemploDoWhile {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do teclado.
        int numero; // Declara uma variável inteira para armazenar o número inserido pelo usuário.

        // Início do loop do-while.
        do {
            System.out.print("Digite um número positivo (ou 0 para sair): "); // Solicita ao usuário que digite um número.
            numero = scanner.nextInt(); // Lê o número inteiro inserido pelo usuário.

            // Verifica se o número inserido é positivo.
            if (numero > 0) {
                System.out.println("Você digitou: " + numero); // Imprime o número inserido.
            } else if (numero < 0) {
                System.out.println("Número inválido. Digite um número positivo."); // Informa o usuário sobre a entrada inválida.
            }

        } while (numero != 0); // O loop continua enquanto o número inserido for diferente de 0.

        System.out.println("Programa encerrado."); // Imprime uma mensagem quando o usuário digita 0 e o loop termina.
        scanner.close(); // Fecha o objeto Scanner para liberar recursos.
    }
}
