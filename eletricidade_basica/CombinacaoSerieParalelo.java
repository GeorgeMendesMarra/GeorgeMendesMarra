/**
 * SeriesParallelCombination
 * Exemplo para calcular resistência equivalente de uma combinação:
 * (R1 em série com paralelo(R2, R3))
 */
public class CombinacaoSerieParalelo {

    public static double parallel(double r1, double r2) {
        return 1.0 / (1.0 / r1 + 1.0 / r2);
    }

    public static void main(String[] args) {
        double r1 = 100; // série
        double r2 = 200; // paral com r3
        double r3 = 300;
        double req = r1 + parallel(r2, r3);
        System.out.println("Req = " + req + " Ω");
    }
}
