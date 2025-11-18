import java.math.BigDecimal;

/**
 * Exemplo simples de aproximação de ln(x) usando double como ponte.
 * (apenas ilustrativo — BigDecimal real exigiria algoritmos numéricos)
 */
public class Logaritmo5 {

    public static void main(String[] args) {
        BigDecimal valor = new BigDecimal("12345.6789");

        double ln = Math.log(valor.doubleValue());
        System.out.println("ln(12345.6789) ≈ " + ln);
    }
}
