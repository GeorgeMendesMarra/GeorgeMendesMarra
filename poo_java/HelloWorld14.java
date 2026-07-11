/*
 * Versão Java: 14+ (switch expression)
 * Paradigma: Imperativo
 */
public class HelloWorld14 { // Classe principal
    public static void main(String[] args) { // Método principal
        int opcao = 1; // Define uma opção
        String mensagem = switch (opcao) { // Switch expression
            case 1 -> "Hello World"; // Caso 1 retorna a mensagem
            default -> "Olá Mundo"; // Caso padrão
        }; // Fim do switch
        System.out.println(mensagem); // Imprime a mensagem
    } // Fim do main
} // Fim da classe
