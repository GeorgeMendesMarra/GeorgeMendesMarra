// Programa da Torre de Hanói com contador de movimentos
public class TorreDeHanoiContador {

    // Variável estática para contar o número total de movimentos realizados
    static int contador = 0;

    /**
     * Função recursiva responsável por mover os discos entre as hastes.
     *
     * @param n        Número de discos a mover
     * @param origem   Haste de origem (de onde os discos serão movidos)
     * @param destino  Haste de destino (para onde os discos irão)
     * @param auxiliar Haste auxiliar usada para movimentação intermediária
     */
    static void moverDiscos(int n, char origem, char destino, char auxiliar) {

        // Caso base: quando há apenas um disco para mover
        if (n == 1) {
            contador++; // Incrementa o contador de movimentos
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return; // Encerra a chamada recursiva
        }

        // Passo 1: mover n-1 discos da origem para a haste auxiliar
        moverDiscos(n - 1, origem, auxiliar, destino);

        // Passo 2: mover o maior disco (n) da origem para o destino
        contador++; // Incrementa o contador de movimentos
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);

        // Passo 3: mover os n-1 discos da auxiliar para o destino
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {

        int n = 4; // Define o número de discos (pode ser alterado para testar outros casos)

        // Chama a função recursiva para resolver o problema
        moverDiscos(n, 'A', 'C', 'B');

        // Exibe o número total de movimentos após a execução completa
        System.out.println("Total de movimentos: " + contador);
    }
}
