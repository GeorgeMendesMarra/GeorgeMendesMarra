/*
 * Versão Java: 8+
 * Paradigma: Orientado a Objetos (classe anônima)
 */
public class HelloWorld19 { // Classe principal
    interface Saudacao { // Interface
        void dizer(); // Método abstrato
    } // Fim da interface
    
    public static void main(String[] args) { // Método principal
        Saudacao saudacao = new Saudacao() { // Classe anônima
            @Override
            public void dizer() { // Implementação do método
                System.out.println("Hello World"); // Imprime a mensagem
            } // Fim do método
        }; // Fim da classe anônima
        saudacao.dizer(); // Chama o método
    } // Fim do main
} // Fim da classe
