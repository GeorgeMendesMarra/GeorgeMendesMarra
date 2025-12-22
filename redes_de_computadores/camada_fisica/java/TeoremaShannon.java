public class TeoremaShannon {

    public static void main(String[] args) {

        // =====================================================
        // 1. Definição dos parâmetros do canal
        // =====================================================

        // Largura de banda do canal em Hertz (Hz)
        // Exemplo clássico: canal telefônico
        double larguraBanda = 3000;

        // Relação Sinal-Ruído (S/N)
        // Valor adimensional (não é em dB)
        double sinalRuido = 1000;

        // =====================================================
        // 2. Cálculo da capacidade do canal
        // =====================================================

        // Chamada do método que aplica o Teorema de Shannon
        double capacidade = calcularCapacidade(larguraBanda, sinalRuido);

        // =====================================================
        // 3. Exibição dos resultados
        // =====================================================

        System.out.println("Teorema de Shannon");
        System.out.println("------------------");
        System.out.println("Largura de banda do canal: " + larguraBanda + " Hz");
        System.out.println("Relação Sinal-Ruído (S/N): " + sinalRuido);

        // A capacidade é exibida em bits por segundo (bps)
        System.out.printf(
            "Capacidade máxima teórica do canal: %.2f bps%n",
            capacidade
        );
    }

    /**
     * =====================================================
     * Método responsável por calcular a capacidade do canal
     * segundo o Teorema de Shannon-Hartley
     * =====================================================
     *
     * Fórmula:
     * C = B * log2(1 + S/N)
     *
     * Onde:
     * B  -> largura de banda do canal (Hz)
     * S/N -> relação sinal-ruído
     * C  -> capacidade do canal (bps)
     */
    public static double calcularCapacidade(double B, double SNR) {

        // O Java não possui log base 2 diretamente,
        // então usamos a mudança de base:
        // log2(x) = log(x) / log(2)

        return B * (Math.log(1 + SNR) / Math.log(2));
    }
}
