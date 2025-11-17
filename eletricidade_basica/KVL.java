/**
 * KirchoffVoltageLaw
 * Demonstra somatória das tensões em um laço (KVL): soma deve ser ~0.
 * Exemplo: fonte - quedas em resistores - outras fontes = 0
 */
public class KVL {

    /** Soma as tensões; retorna true se aproximadamente zero (com tolerância). */
    public static boolean checkKVL(double[] voltages, double tol) {
        double sum = 0.0;
        for (double v : voltages) sum += v;
        return Math.abs(sum) <= tol;
    }

    public static void main(String[] args) {
        // Ex.: +12V fonte, -5V (queda), -7V (queda) => soma zero
        double[] loop = {12.0, -5.0, -7.0};
        System.out.println("KVL satisfeito? " + checkKVL(loop, 1e-6));
    }
}
