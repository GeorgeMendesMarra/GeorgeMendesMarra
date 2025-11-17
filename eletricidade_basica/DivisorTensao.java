/**
 * VoltageDivider
 * Calcula a tensão de saída de um divisor formado por R1 (topo) e R2 (baixo):
 * Vout = Vin * R2 / (R1 + R2)
 */
public class DivisorTensao {

    public static double vout(double vin, double r1, double r2) {
        return vin * r2 / (r1 + r2);
    }

    public static void main(String[] args) {
        double vin = 12.0;
        double r1 = 4700; // 4.7kΩ
        double r2 = 1000; // 1kΩ
        System.out.println("Vout = " + vout(vin, r1, r2) + " V");
    }
}
