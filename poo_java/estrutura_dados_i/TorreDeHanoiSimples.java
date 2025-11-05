// Programa simples da Torre de Hanói
public class TorreDeHanoiSimples {

    // Função recursiva para resolver a Torre de Hanói
    static void moverDiscos(int n, char origem, char destino, char auxiliar) {
        // Caso base: apenas um disco para mover
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }
        // Mover n-1 discos de origem para auxiliar
        moverDiscos(n - 1, origem, auxiliar, destino);
        
        // Mover o último disco para o destino
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
        
        // Mover os n-1 discos do auxiliar para o destino
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    // Função principal
    public static void main(String[] args) {
        int n = 3; // número de discos
        moverDiscos(n, 'A', 'C', 'B');
    }
}
