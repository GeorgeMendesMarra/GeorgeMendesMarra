/**
 * SafetyCheck
 * Funções educacionais para verificar se a corrente excede um limite de segurança
 * dado um condutor com corrente nominal. Apenas para orientação.
 */
public class ChecagemSeguranca {

    /** Retorna true se a corrente está dentro da capacidade nominal do condutor. */
    public static boolean isWithinCapacity(double current, double capacityAmpere) {
        return current <= capacityAmpere;
    }

    public static void main(String[] args) {
        double loadCurrent = 15.0;
        double breakerRating = 20.0;
        System.out.println("Carga segura? " + isWithinCapacity(loadCurrent, breakerRating));
    }
}
