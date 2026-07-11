/*
 * Versão Java: 8+
 * Paradigma: Orientado a Objetos (polimorfismo)
 */
interface Saudacao { // Interface
    void cumprimentar(); // Método abstrato
} // Fim da interface

class SaudacaoHello implements Saudacao { // Implementação
    @Override
    public void cumprimentar() { // Implementa o método
        System.out.println("Hello World"); // Imprime
    } // Fim do método
} // Fim da classe

public class HelloWorld21 { // Classe principal
    public static void main(String[] args) { // Método principal
        Saudacao s = new SaudacaoHello(); // Polimorfismo
        s.cumprimentar(); // Chama o método
    } // Fim do main
} // Fim da classe
