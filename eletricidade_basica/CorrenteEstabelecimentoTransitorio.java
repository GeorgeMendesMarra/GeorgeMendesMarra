/**
 * InductorRLTime
 * Equações básicas para RL série: corrente de estabelecimento transitório.
 */
public class CorrenteEstabelecimentoTransitorio {

    /** Corrente no tempo t para uma fonte DC aplicada: I(t)=I_final*(1-exp(-R*t/L)) */
    public static double currentRise(double v, double r, double l, double t) {
        double iFinal = v / r;
        return iFinal * (1 - Math.exp(- (r * t) / l));
    }

    public static void main(String[] args) {
        double v = 12.0;
        double r = 10.0;
        double l = 0.05; // 50 mH
        double t = 0.01; // 10 ms
        System.out.println("I(t) = " + currentRise(v, r, l, t) + " A");
    }
}
