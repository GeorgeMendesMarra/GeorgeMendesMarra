/*
 * Versão Java: 8+
 * Paradigma: Orientado a Objetos (instância)
 */
public class HelloWorld28 { // Classe principal
    private String mensagem; // Atributo de instância
    
    public HelloWorld28(String mensagem) { // Construtor
        this.mensagem = mensagem; // Inicializa o atributo
    } // Fim do construtor
    
    public void imprimir() { // Método de instância
        System.out.println(mensagem); // Imprime a mensagem
    } // Fim do método
    
    public static void main(String[] args) { // Método principal
        new HelloWorld28("Hello World").imprimir(); // Cria e chama
    } // Fim do main
} // Fim da classe
