/**
 * CurrentDivider
 * Calcula a corrente em um resistor em paralelo: I_k = I_total * (R_eq / R_k)
 * Para dois resistores em paralelo: I1 = Itotal * R2/(R1+R2)
 */
public class DivisorCorrente {

    /** Corrente através de R1 em paral. com R2 para corrente total It. */
    public static double currentThroughR1(double itotal, double r1, double r2) {
        return itotal * (r2 / (r1 + r2));
    }

    public static void main(String[] args) {
        double it = 0.1; // 100 mA
        double r1 = 50;
        double r2 = 100;
        System.out.println("I1 = " + currentThroughR1(it, r1, r2) + " A");
    }
}
