/**
 * DecibelConversion
 * Converte entre níveis em dB para razão de potência e tensão.
 *
 * Para potência: dB = 10 * log10(P2/P1)
 * Para tensão:  dB = 20 * log10(V2/V1)
 */
public class ConversaoDecibel {

    public static double dbFromPowerRatio(double p2, double p1) {
        return 10 * Math.log10(p2 / p1);
    }

    public static double dbFromVoltageRatio(double v2, double v1) {
        return 20 * Math.log10(v2 / v1);
    }

    public static double powerRatioFromDb(double db) {
        return Math.pow(10, db / 10.0);
    }

    public static void main(String[] args) {
        System.out.println("3 dB (pot) ~ ratio = " + powerRatioFromDb(3));
        System.out.println("6 dB (volt) ~ ratio = " + Math.pow(10, 6.0/20.0));
    }
}
