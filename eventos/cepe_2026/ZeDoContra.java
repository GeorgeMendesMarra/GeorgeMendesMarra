/**
 * "Zé do Contra": um problema que o Perceptron simples NÃO consegue resolver,
 * mas a RedeNeural (com camada oculta) consegue.
 *
 * Olhando a tabela de respostas, a regra é:
 *   vai à praia se tem sol E (amigos XOR trânsito)
 *
 * Ou seja, com sol, o Zé só vai quando EXATAMENTE UMA das duas coisas
 * acontece (amigos vão ou tem trânsito). Se os amigos vão e tem trânsito,
 * ele desiste, e se nem os amigos vão nem tem trânsito, também desiste.
 * Sempre do contra.
 *
 * Esse XOR não é linearmente separável: nenhuma única "linha" separa os
 * "vou" dos "não vou". Por isso é preciso a camada oculta, que combina
 * várias fronteiras simples em uma fronteira mais complexa.
 *
 * Usa a classe RedeNeural (RedeNeural.java).
 */
public class ZeDoContra {
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. DADOS DE TREINAMENTO
        // ---------------------------------------------------------
        // Cada linha é um cenário (0 = não, 1 = sim):
        //   posição 0 -> tem sol?
        //   posição 1 -> os amigos vão?
        //   posição 2 -> tem trânsito?
        double[][] x = {
            {1, 1, 0}, {1, 0, 1}, {1, 0, 0}, {1, 1, 1},
            {0, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 1}
        };

        // Respostas esperadas, na mesma ordem das linhas acima.
        // Usa double (e não int como no Perceptron) porque RedeNeural.treinar()
        // recebe double. Só as 2 primeiras linhas dão 1:
        //   {1,1,0} -> sol + amigos, sem trânsito      -> vai
        //   {1,0,1} -> sol + trânsito, sem amigos      -> vai
        //   {1,0,0} -> sol, mas nem amigos nem trânsito -> fica
        //   {1,1,1} -> sol, mas amigos E trânsito       -> fica
        //   as 4 últimas (sem sol)                      -> sempre fica
        double[] y = {1, 1, 0, 0, 0, 0, 0, 0};

        // ---------------------------------------------------------
        // 2. CRIAÇÃO DA REDE
        // ---------------------------------------------------------
        // arquitetura[0] = nº de entradas (sol, amigos, trânsito)
        // arquitetura[1] = nº de neurônios na camada oculta
        // O array serve só para deixar a estrutura da rede explícita
        // em um lugar; poderiam ser duas variáveis int simples.
        int[] arquitetura = {3, 4};

        // Parâmetros: entradas, neurônios ocultos, taxa de aprendizado, semente.
        // A semente fixa (1) torna o treino reproduzível: toda execução
        // começa com os mesmos pesos e produz o mesmo resultado.
        RedeNeural rede = new RedeNeural(arquitetura[0], arquitetura[1], 0.5, 1);

        // ---------------------------------------------------------
        // 3. TREINAMENTO
        // ---------------------------------------------------------
        // Diferente do Perceptron, não há parada antecipada: a sigmoid
        // nunca chega exatamente a 0 ou 1, então o erro nunca zera.
        // Treinamos um número fixo de épocas (10.000).
        for (int epoca = 1; epoca <= 10000; epoca++) {

            // Soma dos erros quadráticos de todos os exemplos nesta época.
            // Deve diminuir ao longo do treino.
            double erroTotal = 0;

            // Passa por cada exemplo; treinar() faz forward + backpropagation
            // e atualiza os pesos a cada exemplo, devolvendo o erro quadrático.
            for (int i = 0; i < x.length; i++) {
                erroTotal += rede.treinar(x[i], y[i]);
            }

            // Mostra só a primeira época e de 2000 em 2000, para acompanhar
            // a queda do erro sem poluir o console.
            // %5d reserva 5 posições para o inteiro; %.4f mostra 4 casas decimais.
            if (epoca == 1 || epoca % 2000 == 0) {
                System.out.printf("Época %5d | erro total: %.4f%n", epoca, erroTotal);
            }
        }

        // ---------------------------------------------------------
        // 4. TESTE FINAL
        // ---------------------------------------------------------
        System.out.println();
        for (int i = 0; i < x.length; i++) {
            // prever() só consulta a rede, sem alterar pesos.
            // Devolve um valor entre 0 e 1 (a "probabilidade" de ir).
            double p = rede.prever(x[i]);

            // Mostra o valor bruto e a decisão final, usando 0,5 como limiar:
            // >= 0,5 -> "vai"; abaixo -> "fica".
            System.out.printf("sol=%.0f amigos=%.0f trânsito=%.0f -> %.3f (%s)%n",
                x[i][0], x[i][1], x[i][2], p, p >= 0.5 ? "vai" : "fica");
        }
    }
}
