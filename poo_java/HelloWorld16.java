/*
 * Versão Java: 8+
 * Paradigma: Funcional (lambda)
 */
public class HelloWorld16 { // Classe principal
    @FunctionalInterface // Anotação para interface funcional
    interface Imprimivel { // Interface funcional
        void imprimir(); // Método abstrato
    } // Fim da interface
    
    public static void main(String[] args) { // Método principal
        Imprimivel imprimivel = () -> System.out.println("Hello World"); // Lambda
        imprimivel.imprimir(); // Executa a lambda
    } // Fim do main
} // Fim da classe
