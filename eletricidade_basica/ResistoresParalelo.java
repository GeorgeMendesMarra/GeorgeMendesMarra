/**
 * ParallelResistors
 * Calcula resistência equivalente para resistores em paralelo:
 * 1/R_eq = 1/R1 + 1/R2 + ...
 */
public class ResistoresParalelo {

    /** Retorna resistência equivalente; se toda condutância zero, retorna Double.POSITIVE_INFINITY. */
    public static double equivalent(double[] resistances) {
        double conductance = 0.0; // soma de 1/R
        for (double r : resistances) {
            if (r == 0) return 0.0; // resistor curto
            conductance += 1.0 / r;
        }
        return conductance == 0.0 ? Double.POSITIVE_INFINITY : 1.0 / conductance;
    }

    public static void main(String[] args) {
        double[] r = {100, 200, 300};
        System.out.println("R_eq paralelo = " + equivalent(r) + " Ω");
    }
}
