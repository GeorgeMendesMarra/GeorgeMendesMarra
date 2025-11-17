/**
 * UnitConversions
 * Pequenas funções utilitárias para conversões de unidades elétricas.
 */
public class ConversaoUnidade {

    public static double milliToAmp(double mA) { return mA / 1000.0; }
    public static double ampToMilli(double a) { return a * 1000.0; }
    public static double kWToW(double kw) { return kw * 1000.0; }
    public static double wToKW(double w) { return w / 1000.0; }

    public static void main(String[] args) {
        System.out.println("10 mA = " + milliToAmp(10) + " A");
        System.out.println("2 kW = " + kWToW(2) + " W");
    }
}
