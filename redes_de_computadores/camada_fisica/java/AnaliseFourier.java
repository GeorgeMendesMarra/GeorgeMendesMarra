import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * COMO COMPILAR E EXECUTAR:
 * * 1. BAIXE O JAR: commons-math3-3.6.1.jar no site da Apache Commons.
 * 2. COLOQUE O JAR na mesma pasta deste arquivo .java.
 * * --- NO WINDOWS (Prompt de Comando/CMD) ---
 * Compilar: javac -cp ".;commons-math3-3.6.1.jar" AnaliseFourier.java
 * Executar: java -cp ".;commons-math3-3.6.1.jar" AnaliseFourier
 * * --- NO LINUX / MAC (Terminal) ---
 * Compilar: javac -cp ".:commons-math3-3.6.1.jar" AnaliseFourier.java
 * Executar: java -cp ".:commons-math3-3.6.1.jar" AnaliseFourier
 * * NOTA: O ponto (.) representa a pasta atual e o ponto-e-vírgula (;) 
 * ou dois-pontos (:) servem para separar as bibliotecas no Classpath.
 */

public class AnaliseFourier {
    public static void main(String[] args) {
        // O tamanho do array de entrada DEVE ser potência de 2 (2, 4, 8, 16, 32...)
        // Caso contrário, o programa lançará um erro de MathIllegalArgumentException.
        double[] dadosSinal = {1.0, 0.0, -1.0, 0.0, 1.0, 0.0, -1.0, 0.0};

        // Instancia o transformador usando normalização padrão
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        
        try {
            // Realiza a FFT: converte do Domínio do Tempo para o Domínio da Frequência
            // O resultado é um array de números Complexos (parte Real e Imaginária)
            Complex[] frequencias = transformer.transform(dadosSinal, TransformType.FORWARD);

            System.out.println("--- Resultado da Análise de Fourier ---");
            
            for (int i = 0; i < frequencias.length; i++) {
                // Cálculo da Magnitude: sqrt(real² + imag²)
                double magnitude = Math.sqrt(Math.pow(frequencias[i].getReal(), 2) + 
                                             Math.pow(frequencias[i].getImaginary(), 2));
                
                System.out.printf("Bin %d: Real: %6.2f | Imag: %6.2f | Magnitude: %6.2f%n", 
                                  i, frequencias[i].getReal(), frequencias[i].getImaginary(), magnitude);
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar FFT: " + e.getMessage());
            System.err.println("Certifique-se que o array tem tamanho potência de 2.");
        }
    }
}
