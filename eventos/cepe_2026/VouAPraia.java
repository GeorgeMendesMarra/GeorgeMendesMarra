/**
 * Exemplo didático de um Perceptron decidindo se vai à praia.
 *
 * O Perceptron é o neurônio artificial mais simples: recebe entradas,
 * multiplica cada uma por um peso, soma tudo (mais um bias) e decide
 * 1 ou 0. No treino, ele ajusta os pesos sempre que erra.
 *
 * Obs.: este arquivo depende da classe Perceptron, que precisa ter:
 *   - Perceptron(int numEntradas)
 *   - int treinar(double[] entrada, int esperado) -> retorna o erro (esperado - obtido)
 *   - int calcular(double[] entrada)              -> retorna 0 ou 1
 *   - toString()                                  -> mostra pesos e bias
 */
public class VouAPraia {
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. DADOS DE TREINAMENTO
        // ---------------------------------------------------------
        // Cada linha é um cenário com 3 características (0 = não, 1 = sim):
        //   posição 0 -> tem sol?
        //   posição 1 -> os amigos vão?
        //   posição 2 -> tem trânsito?
        double[][] entradas = {
            {1, 1, 0}, {1, 0, 0}, {1, 1, 1}, {1, 0, 1},
            {0, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 1}
        };

        // Respostas corretas para cada cenário acima (mesma ordem):
        // 1 = vou à praia, 0 = não vou.
        // Pela tabela, a regra implícita é: vou se tem sol E
        // (amigos vão OU não tem trânsito), ou seja, o sol é
        // indispensável e o trânsito só atrapalha sem os amigos.
        int[] esperados = {1, 1, 1, 0, 0, 0, 0, 0};

        // ---------------------------------------------------------
        // 2. CRIAÇÃO DO PERCEPTRON
        // ---------------------------------------------------------
        // O 3 indica a quantidade de entradas (sol, amigos, trânsito).
        // Internamente ele cria 3 pesos (e provavelmente um bias).
        Perceptron p = new Perceptron(3);

        // ---------------------------------------------------------
        // 3. TREINAMENTO
        // ---------------------------------------------------------
        // Uma "época" é uma passada completa por todos os exemplos.
        // Limitamos a 100 épocas para evitar loop infinito caso o
        // problema não seja linearmente separável.
        for (int epoca = 1; epoca <= 100; epoca++) {
            int erros = 0; // conta quantos exemplos o perceptron errou nesta época

            // Passa por cada exemplo de treino
            for (int i = 0; i < entradas.length; i++) {
                // treinar() calcula a saída, compara com o esperado e
                // ajusta os pesos se errou. Retorna a diferença
                // (esperado - obtido): 0 significa que acertou.
                if (p.treinar(entradas[i], esperados[i]) != 0) erros++;
            }

            // Imprime só as 5 primeiras épocas (para acompanhar o início
            // do aprendizado) e a época final, quando não há mais erros.
            if (epoca <= 5 || erros == 0) {
                System.out.println("Época " + epoca + " | erros: " + erros + " | " + p);
            }

            // Critério de parada: se acertou todos os exemplos em uma
            // época inteira, os pesos já estão bons e não precisa continuar.
            if (erros == 0) break;
        }

        // ---------------------------------------------------------
        // 4. TESTE FINAL
        // ---------------------------------------------------------
        // Depois de treinado, mostra a decisão do perceptron para
        // cada cenário, para conferir se bate com os valores esperados.
        System.out.println();
        for (double[] e : entradas) {
            // %.0f formata o double sem casas decimais (1.0 -> 1)
            // %d imprime o resultado de calcular() (0 ou 1)
            // %n é a quebra de linha independente de sistema operacional
            System.out.printf("sol=%.0f amigos=%.0f trânsito=%.0f -> vou? %d%n",
                e[0], e[1], e[2], p.calcular(e));
        }
    }
}
