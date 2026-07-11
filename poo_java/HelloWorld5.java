/*
 * Versão Java: 8+
 * Paradigma: Imperativo
 */
public class HelloWorld5 { // Classe principal
    private static String mensagem; // Variável estática
    
    static { // Bloco estático (executado quando a classe é carregada)
        mensagem = "Hello World"; // Inicializa a variável
    } // Fim do bloco estático
    
    public static void main(String[] args) { // Método principal
        System.out.println(mensagem); // Imprime a mensagem
    } // Fim do main
} // Fim da classe
