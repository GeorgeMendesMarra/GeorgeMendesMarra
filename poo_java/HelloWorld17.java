/*
 * Versão Java: 8+
 * Paradigma: Funcional (Stream)
 */
import java.util.stream.Stream; // Importa a classe Stream

public class HelloWorld17 { // Classe principal
    public static void main(String[] args) { // Método principal
        Stream.of("Hello World") // Cria um Stream com a mensagem
              .forEach(System.out::println); // Imprime cada elemento
    } // Fim do main
} // Fim da classe
