/**
 * SeriesResistors
 * Soma resistências em série: R_total = R1 + R2 + ... + Rn
 */
public class Resistores_Serie {

    /**
     * Soma um array de resistências (ohms).
     * @param resistances vetor de resistências
     * @return resistência equivalente em ohms
     */
    public static double equivalent(double[] resistances) {
        double sum = 0.0;
        for (double r : resistances) sum += r;
        return sum;
    }

    public static void main(String[] args) {
        double[] r = {10, 20, 30};
        System.out.println("R_eq série = " + equivalent(r) + " Ω"); // 60 Ω
    }
}
