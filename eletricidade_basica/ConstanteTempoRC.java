/**
 * RCtimeConstant
 * Calcula a constante de tempo e tensão de carga teórica:
 * V(t) = V0 * (1 - exp(-t/(R*C)))
 */
public class ConstanteTempoRC {

    public static double tau(double resistance, double capacitance) {
        return resistance * capacitance; // segundos (Ω * F)
    }

    public static double vCharge(double v0, double r, double c, double t) {
        return v0 * (1 - Math.exp(-t / (r * c)));
    }

    public static void main(String[] args) {
        double r = 10000; // 10kΩ
        double c = 1e-6;  // 1µF
        double v0 = 5.0;
        double t = 0.01; // 10 ms
        System.out.println("Tau = " + tau(r, c) + " s");
        System.out.println("V(t) = " + vCharge(v0, r, c, t) + " V");
    }
}
