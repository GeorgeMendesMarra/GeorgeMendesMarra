import java.util.Arrays;
import java.util.Random;

/**
 * Perceptron: o neurônio artificial mais simples.
 *
 * Funcionamento:
 *   1. Recebe N entradas (números).
 *   2. Multiplica cada entrada pelo seu peso e soma tudo, mais o bias.
 *   3. Passa a soma por uma função de ativação (degrau) que devolve 0 ou 1.
 *
 * Aprendizado (regra do perceptron):
 *   Quando erra, ajusta os pesos na direção que reduziria o erro:
 *   peso_novo = peso_antigo + taxa * erro * entrada
 *
 * Usada por VouAPraia.java, que cria o objeto, treina com os exemplos
 * e depois consulta as decisões.
 */
public class Perceptron {

    // Um peso por entrada. Indica a "importância" e o sinal de cada uma:
    // peso positivo empurra para 1, peso negativo empurra para 0.
    private final double[] pesos;

    // Bias (viés): valor somado independentemente das entradas.
    // Funciona como o "limiar" do neurônio: desloca o ponto de decisão.
    private double bias;

    // Taxa de aprendizado: tamanho do passo de cada correção.
    // Muito alta = oscila; muito baixa = aprende devagar.
    private final double taxaAprendizado = 0.1;

    /**
     * Construtor. Chamado em VouAPraia com: new Perceptron(3)
     * (3 entradas: sol, amigos, trânsito).
     */
    public Perceptron(int numeroEntradas) {
        Random random = new Random();
        pesos = new double[numeroEntradas];

        // Pesos e bias começam aleatórios entre -1 e 1.
        // Por isso o número de épocas até convergir varia a cada execução.
        for (int i = 0; i < numeroEntradas; i++) {
            pesos[i] = random.nextDouble() * 2 - 1; // nextDouble() dá [0,1) -> vira [-1,1)
        }
        bias = random.nextDouble() * 2 - 1;
    }

    // Função de ativação do tipo degrau: se soma >= 0 devolve 1, senão 0.
    // É o que transforma a soma em uma decisão binária (vou / não vou).
    private int funcaoAtivacao(double soma) {
        return soma >= 0 ? 1 : 0;
    }

    /**
     * Calcula a saída (0 ou 1) para uma entrada, SEM alterar nada.
     * Em VouAPraia é usado na fase de teste final: p.calcular(e)
     * e, internamente, por treinar().
     */
    public int calcular(double[] entradas) {
        // Começa pelo bias e acumula entrada[i] * peso[i]
        double soma = bias;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        return funcaoAtivacao(soma);
    }

    /**
     * Treina com UM exemplo e devolve o erro (esperado - obtido).
     * Em VouAPraia: p.treinar(entradas[i], esperados[i]) dentro do loop de épocas.
     *
     * Valores possíveis do erro:
     *    0 -> acertou, nada muda
     *   +1 -> esperava 1 e deu 0: aumenta pesos das entradas ativas (puxa a soma para cima)
     *   -1 -> esperava 0 e deu 1: diminui pesos das entradas ativas (puxa a soma para baixo)
     */
    public int treinar(double[] entradas, int esperado) {
        int erro = esperado - calcular(entradas);

        // Ajusta cada peso proporcionalmente à sua entrada:
        // entradas com valor 0 não influenciaram o resultado, então seus pesos não mudam.
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }

        // O bias também é corrigido (é como um peso com entrada fixa = 1)
        bias += taxaAprendizado * erro;

        // VouAPraia usa esse retorno para contar os erros da época
        return erro;
    }

    /**
     * Mostra pesos e bias atuais.
     * VouAPraia chama isso implicitamente ao concatenar o objeto em
     * uma String: "..." + p
     */
    @Override
    public String toString() {
        return "pesos=" + Arrays.toString(pesos) + " bias=" + bias;
    }
}
