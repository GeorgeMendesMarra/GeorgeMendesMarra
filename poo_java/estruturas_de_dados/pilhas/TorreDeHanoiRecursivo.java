/**
 * TorreDeHanoiRecursivo.java
 * 
 * Implementação clássica recursiva da Torre de Hanói.
 * 
 * Demonstra o conceito de recursividade e divisão de problemas.
 * 
 * 
 */

public class TorreDeHanoiRecursivo {

    // Contador global de movimentos (para exibir ao final)
    private static long contador = 0;

    /**
     * Método recursivo que resolve o problema da Torre de Hanói.
     * 
     * @param n        Número de discos
     * @param origem   Pino de onde os discos serão movidos
     * @param destino  Pino de destino dos discos
     * @param auxiliar Pino auxiliar usado nas transferências
     */
    public static void hanoi(int n, char origem, char destino, char auxiliar) {
        // Caso base: se não há discos, não há o que mover
        if (n == 0) return;

        // 1️⃣ Mover os n-1 discos da origem para o pino auxiliar
        hanoi(n - 1, origem, auxiliar, destino);

        // 2️⃣ Mover o disco maior (n) da origem para o destino
        System.out.printf("Move disco %d de %c para %c%n", n, origem, destino);
        contador++;

        // 3️⃣ Mover os n-1 discos do pino auxiliar para o destino
        hanoi(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {
        int n = 3; // Valor padrão para número de discos

        // Permite receber o número de discos via argumento
        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argumento inválido. Usando n = 3.");
            }
        }

        // Executa o algoritmo de Hanói
        hanoi(n, 'A', 'C', 'B');

        // Exibe o total de movimentos realizados
        System.out.printf("Total de movimentos: %d%n", contador);
    }
}
