import java.util.Random;

public class MonteCarloSimples {

    public static void main(String[] args) {
        // Define o número de simulações que serão realizadas.
        // Um número maior de simulações geralmente leva a uma estimativa mais precisa,
        // mas também aumenta o tempo de execução do programa.
        int numSimulacoes = 10000;

        // Cria uma instância da classe Random para gerar números aleatórios.
        // Esta classe fornece métodos para gerar diferentes tipos de números aleatórios.
        Random random = new Random();

        // Inicializa um contador para rastrear o número de vezes que um "evento favorável" ocorre.
        // A definição de "evento favorável" é específica para o problema que estamos simulando.
        int eventosFavoraveis = 0;

        // Define um parâmetro crucial para a nossa simulação.
        // Neste exemplo, estamos simulando um evento com uma probabilidade de ocorrer
        // de 60% (ou 0.6) em cada tentativa independente.
        double probabilidadeSucesso = 0.6;

        // Imprime informações iniciais sobre a simulação para o usuário.
        System.out.println("Simulação de Monte Carlo Simples");
        System.out.println("Número de Simulações: " + numSimulacoes);
        System.out.println("Probabilidade de Sucesso em cada tentativa: " + probabilidadeSucesso);
        System.out.println("----------------------------------");

        // Este loop for executa o número de simulações definido.
        // Cada iteração representa uma tentativa ou uma execução do processo que estamos modelando.
        for (int i = 0; i < numSimulacoes; i++) {
            // Dentro de cada simulação, geramos um número aleatório entre 0.0 (inclusive) e 1.0 (exclusive).
            // Este número aleatório é usado para determinar se um "evento favorável" ocorreu nesta tentativa.
            if (random.nextDouble() < probabilidadeSucesso) {
                // Se o número aleatório gerado for menor que a probabilidade de sucesso definida,
                // consideramos que um "evento favorável" ocorreu e incrementamos o contador.
                eventosFavoraveis++;
            }
        }

        // Após a conclusão de todas as simulações, calculamos a probabilidade estimada
        // do "evento favorável" ocorrer. Fazemos isso dividindo o número total de
        // eventos favoráveis observados pelo número total de simulações realizadas.
        double probabilidadeEstimada = (double) eventosFavoraveis / numSimulacoes;

        // Imprime os resultados finais da simulação para o usuário.
        // Isso inclui o número total de eventos favoráveis encontrados e a probabilidade estimada.
        System.out.println("Número de Eventos Favoráveis: " + eventosFavoraveis);
        System.out.println("Probabilidade Estimada do Evento Favorável: " + probabilidadeEstimada);
    }
}
