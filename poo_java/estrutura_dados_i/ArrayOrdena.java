import java.util.*; // Importa todas as classes do pacote java.util, incluindo a classe Arrays.

public class ArrayOrdena {
    
    // Método principal onde o programa começa a ser executado.
    public static void main (String[] args) {
        
        // Declaração e inicialização de diferentes tipos de arrays.
        String[] nomes = {"Lucas", "Daniel", "Julia", "Tatiana", "Izabela", "Matheus"};
        char[] vogais = {'i', 'u', 'a', 'o', 'e'};
        int[] numeros = {9, 2, 6, 7, 5, 3, 4, 8, 1, 0};
        float[] notas = {7.6f, 8.5f, 9.7f, 5.4f, 7.4f};
        
        // ---
        
        /* * Ordenação e Impressão de Arrays
         * A classe Arrays, do pacote java.util, possui o método estático sort()
         * que ordena os elementos de um array em ordem ascendente.
         */
        
        // Ordena o array de strings em ordem alfabética.
        Arrays.sort(nomes);
        
        // Imprime o cabeçalho "Nomes:" e, em seguida, cada nome do array ordenado,
        // seguido por uma vírgula e um espaço.
        System.out.println("\nNomes:");
        for (String nome: nomes) {
            System.out.print(nome + ", ");
        }
        
        // ---
        
        // Ordena o array de caracteres em ordem alfabética (ascendente).
        Arrays.sort(vogais);
        System.out.print("\nVogais: ");
        for (char vogal: vogais){
            System.out.print(vogal + ", ");
        }
        
        // ---
        
        // Ordena o array de inteiros em ordem crescente.
        Arrays.sort(numeros);
        System.out.print("\nNúmeros: ");
        for (int numero: numeros){
            System.out.print(numero + ", ");
        }
        
        // ---
        
        // Ordena o array de floats em ordem crescente.
        Arrays.sort(notas);
        System.out.print("\nNotas: ");
        for (float nota: notas){
            System.out.print(nota + ", ");
        }
        
        // ---
        
        // Encerra o programa. O argumento '0' indica que o programa terminou com sucesso.
        System.exit(0);
    }
}
