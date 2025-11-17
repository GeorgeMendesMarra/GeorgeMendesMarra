/**
 * PowerFactorCorrection
 * Calcula a capacitância necessária para corrigir fator de potência
 * (aproximação para sistema monofásico).
 *
 * Fórmula simplificada:
 * Qc = P * (tan(phi1) - tan(phi2))
 * C = Qc / (2 * PI * f * V^2)
 */
public class CorrecaoFatorPotencia {

    public static double requiredReactivePower(double p, double pfInitial, double pfTarget) {
        double phi1 = Math.acos(pfInitial);
        double phi2 = Math.acos(pfTarget);
        return p * (Math.tan(phi1) - Math.tan(phi2));
    }

    public static double capacitanceForQc(double qc, double vRms, double freq) {
        return qc / (2 * Math.PI * freq * vRms * vRms);
    }

    public static void main(String[] args) {
        double p = 10000; // 10 kW
        double pf0 = 0.7;
        double pfTarget = 0.95;
        double f = 60;
        double v = 230;
        double qc = requiredReactivePower(p, pf0, pfTarget);
        double c = capacitanceForQc(qc, v, f);
        System.out.println("Qc necessário = " + qc + " VAR");
        System.out.println("C ≈ " + c + " F");
    }
}
