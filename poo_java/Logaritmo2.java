/**
 * Calcula logaritmo em qualquer base.
 */
public class Logaritmo2 {

    /**
     * Retorna log_b(x) usando mudança de base.
     */
    public static double logBase(double x, double b) {
        return Math.log(x) / Math.log(b);
    }

    public static void main(String[] args) {
        double resultado = logBase(256, 2);
        System.out.println("log2(256) = " + resultado);
    }
}
