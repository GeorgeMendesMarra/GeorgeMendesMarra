// Programa da Torre de Hanói com contador de movimentos
public class TorreDeHanoiContador {

    static int contador = 0;

    static void moverDiscos(int n, char origem, char destino, char auxiliar) {
        if (n == 1) {
            contador++;
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return;
        }
        moverDiscos(n - 1, origem, auxiliar, destino);
        contador++;
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    public static void main(String[] args) {
        int n = 4; // número de discos
        moverDiscos(n, 'A', 'C', 'B');
        System.out.println("Total de movimentos: " + contador);
    }
}
