/*
 * Versão Java: 8+
 * Paradigma: Funcional (recursão)
 */
public class HelloWorld15 { // Classe principal
    public static void main(String[] args) { // Método principal
        imprimirRecursivo(1); // Chama o método recursivo com contador
    } // Fim do main
    
    public static void imprimirRecursivo(int n) { // Método recursivo
        if (n > 0) { // Condição de parada
            System.out.println("Hello World"); // Imprime a mensagem
            imprimirRecursivo(n - 1); // Chamada recursiva
        } // Fim do if
    } // Fim do método
} // Fim da classe
