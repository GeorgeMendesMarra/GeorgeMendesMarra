/*
 * Versão Java: 8+
 * Paradigma: Funcional (Optional)
 */
import java.util.Optional; // Importa a classe Optional

public class HelloWorld18 { // Classe principal
    public static void main(String[] args) { // Método principal
        Optional<String> optional = Optional.of("Hello World"); // Cria um Optional
        optional.ifPresent(System.out::println); // Imprime se presente
    } // Fim do main
} // Fim da classe
