/*
 * Versão Java: 8+
 * Paradigma: Orientado a Objetos (herança)
 */
class Base { // Classe base
    public void imprimir() { // Método da classe base
        System.out.println("Hello World"); // Imprime a mensagem
    } // Fim do método
} // Fim da classe Base

public class HelloWorld20 extends Base { // Classe que herda de Base
    public static void main(String[] args) { // Método principal
        new HelloWorld20().imprimir(); // Cria instância e chama o método
    } // Fim do main
} // Fim da classe
