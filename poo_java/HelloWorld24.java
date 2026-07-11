/*
 * Versão Java: 8+
 * Paradigma: Reflexão (Metaprogramação)
 */
import java.lang.reflect.Method; // Importa Method

public class HelloWorld24 { // Classe principal
    public static void main(String[] args) throws Exception { // Método principal
        Class<?> clazz = Class.forName("java.lang.System"); // Obtém a classe System
        Method out = clazz.getDeclaredField("out").get(null).getClass() // Obtém out
                         .getMethod("println", String.class); // Obtém o método println
        out.invoke(System.out, "Hello World"); // Invoca o método com reflexão
    } // Fim do main
} // Fim da classe
