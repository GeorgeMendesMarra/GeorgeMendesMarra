/**
 * PowerBasic
 * Calcula potência ativa (watts) e relaciona com resistência.
 */
public class Potencia {

    /** Calcula potência (W) a partir de tensão (V) e corrente (A). */
    public static double powerFromVI(double voltage, double current) {
        return voltage * current;
    }

    /** Calcula potência (W) a partir de corrente (A) e resistência (Ω): P = I^2 * R */
    public static double powerFromIR(double current, double resistance) {
        return current * current * resistance;
    }

    /** Calcula potência (W) a partir de tensão (V) e resistência (Ω): P = V^2 / R */
    public static double powerFromVR(double voltage, double resistance) {
        return (voltage * voltage) / resistance;
    }

    public static void main(String[] args) {
        double v = 230.0;
        double i = 2.0;
        double r = 115.0;
        System.out.println("P (V*I) = " + powerFromVI(v, i) + " W");
        System.out.println("P (I^2*R) = " + powerFromIR(i, r) + " W");
        System.out.println("P (V^2/R) = " + powerFromVR(v, r) + " W");
    }
}
