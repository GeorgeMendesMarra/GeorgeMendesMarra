import java.util.Scanner;

public class EnigmaChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("--- Assistente Bletchley Park ---");
        System.out.println("Olá! Eu sou o TuringBot. Sobre o que quer falar? (historia, java, huts, sair)");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("sair")) {
                System.out.println("Encerrando transmissão... Heil... digo, até logo!");
                break;
            }

            // Usando as novas Switch Expressions do Java
            String resposta = switch (input) {
                case "historia" -> "A Enigma foi criada por Arthur Scherbius em 1918 para uso comercial.";
                case "java" -> "Podemos programar a lógica dos rotores usando aritmética modular: (i + rot) % 26.";
                case "huts" -> "As Huts eram cabanas de madeira. A Hut 8 era onde Alan Turing trabalhava.";
                case "ajuda" -> "Tente: 'historia', 'java' ou 'huts'.";
                default -> "Desculpe, essa mensagem está criptografada para mim. Pode repetir?";
            };

            System.out.println(resposta);
        }
        scanner.close();
    }
}
