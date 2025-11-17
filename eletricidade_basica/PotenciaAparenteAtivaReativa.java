/**
 * ApparentRealReactivePower
 * Calcula potência aparente (S), ativa (P) e reativa (Q). Fator de potência cos(phi) = P/S.
 */
public class PotenciaAparenteAtivaReativa {

    /** S (VA) = V_rms * I_rms */
    public static double apparentPower(double vrms, double irms) {
        return vrms * irms;
    }

    /** P (W) = S * pf */
    public static double realPower(double s, double pf) {
        return s * pf;
    }

    /** Q (VAR) = sqrt(S^2 - P^2) */
    public static double reactivePower(double s, double p) {
        double val = s * s - p * p;
        return val <= 0 ? 0.0 : Math.sqrt(val);
    }

    public static void main(String[] args) {
        double v = 230;
        double i = 10;
        double s = apparentPower(v, i);
        double pf = 0.8;
        double p = realPower(s, pf);
        double q = reactivePower(s, p);
        System.out.println("S = " + s + " VA, P = " + p + " W, Q = " + q + " VAR, pf = " + pf);
    }
}
