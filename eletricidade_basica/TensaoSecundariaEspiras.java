/**
 * TransformerTurnsRatio
 * Calcula tensão secundária a partir da relação de espiras:
 * V2 = V1 * (N2 / N1)
 */
public class TensaoSecundariaEspiras {

    public static double secondaryVoltage(double vPrimary, double nPrimary, double nSecondary) {
        return vPrimary * (nSecondary / nPrimary);
    }

    public static void main(String[] args) {
        double v1 = 230;
        double n1 = 1000;
        double n2 = 200;
        System.out.println("V2 = " + secondaryVoltage(v1, n1, n2) + " V");
    }
}
