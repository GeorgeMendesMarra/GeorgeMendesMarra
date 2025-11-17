/**
 * CapacitorChargeDischarge
 * Mostra equações para carga e descarga exponencial de um capacitor.
 */
public class CapacitorCargaDescarga {

    /** Tensão no capacitor durante descarga: V(t) = V0 * exp(-t/(R*C)) */
    public static double vDischarge(double v0, double r, double c, double t) {
        return v0 * Math.exp(-t / (r * c));
    }

    public static void main(String[] args) {
        double v0 = 5.0;
        double r = 1000;
        double c = 10e-6;
        double t = 0.005;
        System.out.println("V descarga = " + vDischarge(v0, r, c, t) + " V");
    }
}
