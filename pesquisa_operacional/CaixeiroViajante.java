import java.util.ArrayList; // Importa a classe ArrayList para criar listas dinâmicas
import java.util.Arrays; // Importa a classe Arrays para trabalhar com arrays (não diretamente usado aqui, mas pode ser útil em outras partes)
import java.util.List; // Importa a interface List para representar coleções ordenadas de elementos
import java.util.Random; // Importa a classe Random para gerar números aleatórios (usado para algoritmos futuros)

public class CaixeiroViajante {

    private final int[][] distancias; // Matriz para armazenar as distâncias entre as cidades
    private final int numCidades; // Número total de cidades
    private final Random random = new Random(); // Instância da classe Random para uso futuro

    // Construtor da classe CaixeiroViajante
    public CaixeiroViajante(int[][] distancias) {
        this.distancias = distancias;
        this.numCidades = distancias.length;
        // Validação básica da matriz de distâncias
        for (int i = 0; i < numCidades; i++) {
            // Verifica se todas as linhas têm o mesmo número de colunas e se a distância de uma cidade para ela mesma é zero
            if (distancias[i].length != numCidades || distancias[i][i] != 0) {
                throw new IllegalArgumentException("Matriz de distâncias inválida.");
            }
        }
    }

    // Método para resolver o problema do Caixeiro Viajante usando a abordagem de força bruta
    public List<Integer> resolverForcaBruta() {
        List<Integer> melhorRota = null; // Inicializa a melhor rota encontrada como nula
        int menorDistancia = Integer.MAX_VALUE; // Inicializa a menor distância encontrada com o maior valor inteiro possível

        // Cria uma lista com os índices de todas as cidades
        List<Integer> cidades = new ArrayList<>();
        for (int i = 0; i < numCidades; i++) {
            cidades.add(i);
        }

        // Gera todas as possíveis permutações das cidades
        List<List<Integer>> todasPermutacoes = permutar(cidades);

        // Itera sobre todas as permutações para encontrar a rota com a menor distância
        for (List<Integer> rota : todasPermutacoes) {
            // Calcula a distância total da rota atual
            int distanciaAtual = calcularDistancia(rota);
            // Se a distância atual for menor que a menor distância encontrada até agora
            if (distanciaAtual < menorDistancia) {
                menorDistancia = distanciaAtual; // Atualiza a menor distância
                melhorRota = new ArrayList<>(rota); // Atualiza a melhor rota
            }
        }

        // Adiciona a cidade inicial ao final da melhor rota para formar um ciclo completo
        if (melhorRota != null) {
            melhorRota.add(melhorRota.get(0));
        }

        // Imprime os resultados da solução por força bruta
        System.out.println("Solução por Força Bruta:");
        System.out.println("Melhor Rota: " + melhorRota);
        System.out.println("Distância Mínima: " + menorDistancia);

        return melhorRota; // Retorna a melhor rota encontrada
    }

    // Método auxiliar para gerar todas as permutações de uma lista de elementos
    private List<List<Integer>> permutar(List<Integer> elementos) {
        List<List<Integer>> permutacoes = new ArrayList<>();
        permutarRecursivo(elementos, 0, permutacoes);
        return permutacoes;
    }

    // Método recursivo para gerar permutações
    private void permutarRecursivo(List<Integer> elementos, int k, List<List<Integer>> permutacoes) {
        // Caso base: se k chegou ao tamanho da lista, uma permutação completa foi formada
        if (k == elementos.size()) {
            permutacoes.add(new ArrayList<>(elementos)); // Adiciona uma cópia da permutação à lista de permutações
        } else {
            // Para cada elemento a partir da posição k até o final da lista
            for (int i = k; i < elementos.size(); i++) {
                java.util.Collections.swap(elementos, k, i); // Troca o elemento na posição k com o elemento na posição i
                permutarRecursivo(elementos, k + 1, permutacoes); // Chama recursivamente para a próxima posição
                java.util.Collections.swap(elementos, k, i); // Desfaz a troca (backtrack) para explorar outras permutações
            }
        }
    }

    // Método para resolver o problema do Caixeiro Viajante usando o algoritmo do vizinho mais próximo
    public List<Integer> resolverVizinhoMaisProximo(int cidadeInicial) {
        // Valida se a cidade inicial é válida
        if (cidadeInicial < 0 || cidadeInicial >= numCidades) {
            throw new IllegalArgumentException("Cidade inicial inválida.");
        }

        List<Integer> rota = new ArrayList<>(); // Inicializa a lista para armazenar a rota
        boolean[] visitado = new boolean[numCidades]; // Array para rastrear as cidades visitadas
        int cidadeAtual = cidadeInicial; // Define a cidade inicial como a cidade atual
        rota.add(cidadeAtual); // Adiciona a cidade inicial à rota
        visitado[cidadeAtual] = true; // Marca a cidade inicial como visitada
        int distanciaTotal = 0; // Inicializa a distância total percorrida

        // Loop para visitar todas as cidades restantes
        for (int i = 1; i < numCidades; i++) {
            int proximaCidade = -1; // Inicializa a próxima cidade como -1
            int menorDistancia = Integer.MAX_VALUE; // Inicializa a menor distância como o maior valor inteiro possível

            // Procura a cidade não visitada mais próxima da cidade atual
            for (int j = 0; j < numCidades; j++) {
                if (!visitado[j] && distancias[cidadeAtual][j] < menorDistancia) {
                    menorDistancia = distancias[cidadeAtual][j]; // Atualiza a menor distância
                    proximaCidade = j; // Atualiza a próxima cidade
                }
            }

            // Se uma próxima cidade foi encontrada
            if (proximaCidade != -1) {
                rota.add(proximaCidade); // Adiciona a próxima cidade à rota
                visitado[proximaCidade] = true; // Marca a próxima cidade como visitada
                distanciaTotal += menorDistancia; // Adiciona a distância percorrida à distância total
                cidadeAtual = proximaCidade; // Atualiza a cidade atual para a próxima cidade
            } else {
                // Isso não deve acontecer em um grafo completo (onde todas as cidades estão conectadas)
                break;
            }
        }

        // Volta para a cidade inicial para completar o ciclo
        rota.add(cidadeInicial);
        distanciaTotal += distancias[cidadeAtual][cidadeInicial];

        // Imprime os resultados da solução por vizinho mais próximo
        System.out.println("Solução por Vizinho Mais Próximo (Iniciando na cidade " + cidadeInicial + "):");
        System.out.println("Rota: " + rota);
        System.out.println("Distância Total: " + distanciaTotal);

        return rota; // Retorna a rota encontrada pelo algoritmo do vizinho mais próximo
    }

    // Método auxiliar para calcular a distância total de uma rota
    private int calcularDistancia(List<Integer> rota) {
        int distancia = 0;
        // Itera sobre as cidades na rota, calculando a distância entre cidades adjacentes
        for (int i = 0; i < rota.size() - 1; i++) {
            distancia += distancias[rota.get(i)][rota.get(i + 1)];
        }
        return distancia; // Retorna a distância total da rota
    }

    // Método principal para executar o programa
    public static void main(String[] args) {
        // Exemplo de matriz de distâncias entre 4 cidades (simétrica)
        int[][] distanciasExemplo = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        // Cria uma instância da classe CaixeiroViajante
        CaixeiroViajante tsp = new CaixeiroViajante(distanciasExemplo);

        // Resolver usando força bruta se o número de cidades for pequeno
        if (tsp.numCidades <= 10) {
            tsp.resolverForcaBruta();
            System.out.println("--------------------");
        } else {
            System.out.println("Número de cidades muito grande para força bruta.");
            System.out.println("--------------------");
        }

        // Resolver usando o algoritmo do vizinho mais próximo, começando de diferentes cidades
        tsp.resolverVizinhoMaisProximo(0); // Começando da cidade 0
        tsp.resolverVizinhoMaisProximo(1); // Começando da cidade 1
        // ... e assim por diante para outras cidades iniciais
    }
}
