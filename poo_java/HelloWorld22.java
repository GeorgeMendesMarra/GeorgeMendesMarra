/*
 * Versão Java: 8+
 * Paradigma: Orientado a Objetos (encapsulamento)
 */
class Mensagem { // Classe com encapsulamento
    private String texto; // Atributo privado
    
    public Mensagem(String texto) { // Construtor
        this.texto = texto; // Inicializa o atributo
    } // Fim do construtor
    
    public void exibir() { // Método público
        System.out.println(texto); // Imprime o atributo
    } // Fim do método
} // Fim da classe

public class HelloWorld22 { // Classe principal
    public static void main(String[] args) { // Método principal
        new Mensagem("Hello World").exibir(); // Cria objeto e exibe
    } // Fim do main
} // Fim da classe
