import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * EXECUÇÃO:
 * javac -cp ".;commons-math3-3.6.1.jar" AnaliseNyquist.java
 * java -cp ".;commons-math3-3.6.1.jar" AnaliseNyquist
 */

public class AnaliseNyquist {
    public static void main(String[] args) {
        // 1. DEFINIÇÃO DA TAXA DE AMOSTRAGEM (Sampling Rate)
        // Exemplo: 1000 amostras por segundo (1000 Hz)
        double taxaAmostragem = 1000.0; 
        
        // 2. TEOREMA DE NYQUIST:
        // A frequência máxima que podemos detectar com precisão é metade da taxa de amostragem.
        double frequenciaNyquist = taxaAmostragem / 2.0;

        // Sinal de exemplo (Potência de 2: 8 elementos)
        double[] dadosSinal = {1.0, 0.5, -1.0, -0.5, 1.0, 0.5, -1.0, -0.5};

        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        
        try {
            // Transformada de Fourier
            Complex[] resultado = transformer.transform(dadosSinal, TransformType.FORWARD);

            System.out.println("Limite de Nyquist: " + frequenciaNyquist + " Hz");
            System.out.println("-------------------------------------------------------");

            for (int i = 0; i < resultado.length; i++) {
                // 3. CÁLCULO DA FREQUÊNCIA DE CADA BIN
                // A frequência de um "bin" é (índice * taxaAmostragem / totalAmostras)
                double frequenciaDoBin = i * (taxaAmostragem / resultado.length);
                
                double mag = Math.sqrt(Math.pow(resultado[i].getReal(), 2) + 
                                       Math.pow(resultado[i].getImaginary(), 2));

                // 4. INTERPRETAÇÃO DE NYQUIST
                // Se a frequência do bin ultrapassar Nyquist, é um "alias" (espelhamento)
                String status = (frequenciaDoBin <= frequenciaNyquist) ? "[SINAL REAL]" : "[ESPELHAMENTO]";

                System.out.printf("Bin %d | Freq: %7.1f Hz | Magnitude: %5.2f | %s%n", 
                                  i, frequenciaDoBin, mag, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
