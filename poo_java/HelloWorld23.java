/*
 * Versão Java: 8+
 * Paradigma: Orientado a Objetos (Singleton)
 */
class SingletonSaudacao { // Classe Singleton
    private static final SingletonSaudacao INSTANCE = new SingletonSaudacao(); // Instância única
    private SingletonSaudacao() {} // Construtor privado
    
    public static SingletonSaudacao getInstance() { // Método de acesso
        return INSTANCE; // Retorna a instância
    } // Fim do método
    
    public void dizer() { // Método de instância
        System.out.println("Hello World"); // Imprime
    } // Fim do método
} // Fim da classe

public class HelloWorld23 { // Classe principal
    public static void main(String[] args) { // Método principal
        SingletonSaudacao.getInstance().dizer(); // Usa o Singleton
    } // Fim do main
} // Fim da classe
