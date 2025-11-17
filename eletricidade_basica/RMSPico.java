/**
 * RMSandPeak
 * Converte entre valor de pico e RMS para senóide pura:
 * Vrms = Vpeak / sqrt(2)
 */
public class RMSPico {

    public static double vrmsFromVpeak(double vpeak) {
        return vpeak / Math.sqrt(2);
    }

    public static double vpeakFromVrms(double vrms) {
        return vrms * Math.sqrt(2);
    }

    public static void main(String[] args) {
        double vPeak = 325.0; // por ex. para 230Vrms -> 325V peak
        System.out.println("Vrms = " + vrmsFromVpeak(vPeak) + " V");
    }
}
