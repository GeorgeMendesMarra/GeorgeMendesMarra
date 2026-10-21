import java.util.Random;

/**
 * Rede neural com 1 camada oculta (MLP), treinada por backpropagation.
 *
 * Estrutura:
 *   entradas (x) -> camada oculta (sigmoid) -> neurônio de saída (sigmoid)
 *
 * Diferenças em relação ao Perceptron:
 *   - Tem uma camada oculta, então aprende problemas que NÃO são
 *     linearmente separáveis (como o XOR).
 *   - Usa sigmoid em vez de degrau: a saída é um valor contínuo entre 0 e 1,
 *     e a função é derivável, o que permite calcular gradientes.
 *   - O aprendizado é por descida do gradiente: cada peso é ajustado
 *     na direção que reduz o erro.
 */
public class RedeNeural {

    // Quantidade de entradas (ex.: 3 = sol, amigos, trânsito)
    // e de neurônios na camada oculta.
    private final int nEntradas, nOcultos;

    // Pesos da entrada para a camada oculta.
    // w1[j][i] = peso que liga a entrada i ao neurônio oculto j.
    private final double[][] w1;

    // Bias de cada neurônio oculto (um por neurônio).
    private final double[] b1;

    // Pesos da camada oculta para a saída.
    // w2[j] = peso que liga o neurônio oculto j ao neurônio de saída.
    private final double[] w2;

    // Bias do neurônio de saída.
    // Obs.: b1 e b2 não são sorteados; começam em 0 (padrão do Java),
    // o que é comum e funciona bem, pois os pesos já quebram a simetria.
    private double b2;

    // Taxa de aprendizado: tamanho do passo de cada correção.
    private final double taxa;

    /**
     * Construtor.
     *
     * @param nEntradas número de entradas
     * @param nOcultos  número de neurônios na camada oculta
     * @param taxa      taxa de aprendizado
     * @param semente   semente do gerador aleatório: com a mesma semente,
     *                  a inicialização é sempre igual, então o treino é
     *                  reproduzível (ao contrário do Perceptron, que usava
     *                  new Random() sem semente).
     */
    public RedeNeural(int nEntradas, int nOcultos, double taxa, long semente) {
        this.nEntradas = nEntradas;
        this.nOcultos = nOcultos;
        this.taxa = taxa;

        Random r = new Random(semente);
        w1 = new double[nOcultos][nEntradas];
        b1 = new double[nOcultos];
        w2 = new double[nOcultos];

        // Pesos iniciais aleatórios com distribuição normal (média 0, desvio 1).
        // Precisam ser diferentes entre si; se fossem todos iguais, todos os
        // neurônios ocultos aprenderiam exatamente a mesma coisa.
        for (int j = 0; j < nOcultos; j++) {
            for (int i = 0; i < nEntradas; i++) w1[j][i] = r.nextGaussian();
            w2[j] = r.nextGaussian();
        }
    }

    /**
     * Função de ativação sigmoid: 1 / (1 + e^-x).
     * Comprime qualquer número para o intervalo (0, 1).
     * Valores muito negativos -> perto de 0; muito positivos -> perto de 1.
     * Sua derivada é simples: s * (1 - s), usada no backpropagation.
     */
    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    /**
     * Calcula a saída de cada neurônio da camada oculta.
     * Para cada neurônio j: h[j] = sigmoid(b1[j] + soma(w1[j][i] * x[i]))
     */
    private double[] camadaOculta(double[] x) {
        double[] h = new double[nOcultos];
        for (int j = 0; j < nOcultos; j++) {
            double soma = b1[j]; // começa pelo bias
            for (int i = 0; i < nEntradas; i++) soma += w1[j][i] * x[i];
            h[j] = sigmoid(soma);
        }
        return h;
    }

    /**
     * FORWARD (propagação para frente): só consulta, não altera pesos.
     * Devolve um valor entre 0 e 1, interpretado como "probabilidade"
     * de ir à praia. Para uma decisão sim/não, use um limiar (ex.: >= 0.5).
     */
    public double prever(double[] x) {
        double[] h = camadaOculta(x); // 1) entradas -> oculta
        double soma = b2;             // 2) oculta -> saída
        for (int j = 0; j < nOcultos; j++) soma += w2[j] * h[j];
        return sigmoid(soma);
    }

    /**
     * Um passo de treino com UM exemplo: forward + erro + backpropagation.
     *
     * @param x        entradas do exemplo
     * @param esperado resposta correta (0 ou 1)
     * @return erro quadrático deste exemplo, útil para acompanhar o treino
     */
    public double treinar(double[] x, double esperado) {

        // ---------------------------------------------------------
        // 1) FORWARD: o mesmo cálculo de prever(), mas guardando h
        //    (as saídas da camada oculta), que serão necessárias
        //    para calcular os gradientes.
        // ---------------------------------------------------------
        double[] h = camadaOculta(x);
        double soma = b2;
        for (int j = 0; j < nOcultos; j++) soma += w2[j] * h[j];
        double saida = sigmoid(soma);

        // ---------------------------------------------------------
        // 2) ERRO: quanto a saída diferiu do esperado.
        //    Derivada da função de custo (erro quadrático / 2) em
        //    relação à saída.
        // ---------------------------------------------------------
        double erro = saida - esperado;

        // ---------------------------------------------------------
        // 3) BACKPROPAGATION: distribui a "culpa" do erro para trás,
        //    usando a regra da cadeia.
        // ---------------------------------------------------------

        // Gradiente na saída: erro * derivada da sigmoid (saida * (1 - saida)).
        // Indica o quanto a soma do neurônio de saída deveria mudar.
        double dSaida = erro * saida * (1 - saida);

        for (int j = 0; j < nOcultos; j++) {
            // Gradiente do neurônio oculto j: a culpa da saída, repassada
            // proporcionalmente ao peso w2[j] e multiplicada pela derivada
            // da sigmoid dele. IMPORTANTE: é calculado ANTES de atualizar
            // w2[j], pois precisa do valor antigo do peso.
            double dOculto = dSaida * w2[j] * h[j] * (1 - h[j]);

            // Atualiza peso oculta -> saída.
            // Sinal "-" porque andamos contra o gradiente (descida do gradiente).
            w2[j] -= taxa * dSaida * h[j];

            // Atualiza pesos entrada -> oculta (cada um proporcional à sua entrada).
            for (int i = 0; i < nEntradas; i++) {
                w1[j][i] -= taxa * dOculto * x[i];
            }

            // Atualiza o bias do neurônio oculto (como um peso com entrada = 1).
            b1[j] -= taxa * dOculto;
        }

        // Atualiza o bias da saída.
        b2 -= taxa * dSaida;

        // Erro quadrático: sempre positivo, usado para ver se a rede está
        // melhorando ao longo das épocas (deve diminuir).
        return erro * erro;
    }
}
