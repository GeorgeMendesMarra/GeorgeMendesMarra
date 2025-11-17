/**
 * Lei de OHM
 * Calcula tensão, corrente ou resistência usando a Lei de Ohm.
 *
 * Exemplos: V = I * R, I = V / R, R = V / I
 */
public class LeiOhm {

    /** Calcula tensão (volts) a partir de corrente (ampères) e resistência (ohms). */
    public static double voltage(double current, double resistance) {
        return current * resistance;
    }

    /** Calcula corrente (ampères) a partir de tensão (volts) e resistência (ohms). */
    public static double current(double voltage, double resistance) {
        return voltage / resistance;
    }

    /** Calcula resistência (ohms) a partir de tensão (volts) e corrente (ampères). */
    public static double resistance(double voltage, double current) {
        return voltage / current;
    }

    /** Exemplo de uso. */
    public static void main(String[] args) {
        double i = 2.0; // A
        double r = 5.0; // Ω
        System.out.println("V = I * R = " + voltage(i, r) + " V"); // 10 V

        double v = 12.0;
        System.out.println("I = V / R = " + current(v, r) + " A"); // 2.4 A

        System.out.println("R = V / I = " + resistance(v, i) + " Ω"); // 6 Ω
    }
}
