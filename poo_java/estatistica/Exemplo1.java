import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

// -----------------------------------------------------------------------------
// CLASSE ESTATISTICA
// -----------------------------------------------------------------------------

/**
 * Classe para realizar cálculos estatísticos em um array de doubles.
*/
class Estatistica {
    private double array[];

    /**
     * Calcula o Coeficiente de Variação de Pearson (CV).
     * Métrica de dispersão relativa que compara o Desvio Padrão com a Média Aritmética,
     * útil para comparar a variabilidade de diferentes conjuntos de dados.
     * Fórmula: CV = (Desvio Padrão / Média Aritmética) * 100
     * * @return O Coeficiente de Variação em porcentagem.
     */
    public double getPearson() {
        return (getDesvioPadrao() / getMediaAritmetica()) * 100;
    }

    /**
     * Calcula a Média Aritmética (valor médio).
     * Fórmula: Média = Soma dos Elementos / Número de Elementos
     * * @return A Média Aritmética do array.
     */
    public double getMediaAritmetica() {
        if (array.length == 0) return 0.0;
        return getSomaDosElementos() / array.length;
    }

    /**
     * Calcula a soma de todos os elementos do array.
     * * @return A soma total dos elementos.
     */
    public double getSomaDosElementos() {
        double total = 0;
        // Uso de for-each loop para iteração simplificada
        for (double elemento : array) {
            total += elemento;
        }
        return total;
    }

    /**
     * Calcula a soma dos quadrados de todos os elementos do array.
     * Este valor é essencial para o cálculo rápido da Variância.
     * * @return A soma de cada elemento elevado ao quadrado.
     */
    public double getSomaDosElementosAoQuadrado() {
        double total = 0;
        for (double elemento : array) {
            total += Math.pow(elemento, 2);
        }
        return total;
    }
    
    /**
     * Ordena o array em ordem crescente.
     * Essencial para o cálculo correto da Mediana.
     */
    public void ordenar() {
        Arrays.sort(array);
    }

    /**
     * Imprime todos os elementos do array no console.
     */
    public void imprimeArray() {
        System.out.print("\nElementos do Array: ");
        for (double elemento : array) {
            System.out.print(elemento + " ");
        }
    }

    /**
     * Busca por um valor específico no array usando busca binária (requer array ordenado).
     * O array deve estar ordenado antes de chamar este método.
     * * @param value O valor double a ser buscado.
     * @return O índice do valor (se encontrado) ou um valor negativo (se não encontrado).
     */
    public int buscaPor(double value) {
        return Arrays.binarySearch(array, value);
    }

    /**
     * Calcula a Variância Amostral (s^2).
     * Medida de dispersão que indica quão distantes os valores estão da média.
     * Fórmula simplificada: s^2 = [ 1 / (n-1) ] * [ Sum(x^2) - ( (Sum(x))^2 / n ) ]
     * * @return A Variância Amostral. Retorna 0.0 se o tamanho do array for <= 1.
     */
    public double getVariancia() {
        if (array.length <= 1) {
            return 0.0; // Variância não é definida ou é 0 para 0 ou 1 elemento
        }
        
        // P1: 1 / (n-1)
        double p1 = 1.0 / (array.length - 1);
        
        // P2: Sum(x^2) - ( (Sum(x))^2 / n )
        double somaDosQuadrados = getSomaDosElementosAoQuadrado();
        double somaAoQuadrado = Math.pow(getSomaDosElementos(), 2);
        double p2 = somaDosQuadrados - (somaAoQuadrado / array.length);
        
        return p1 * p2;
    }

    /**
     * Calcula o Desvio Padrão Amostral (s).
     * Medida de dispersão mais interpretável por estar na mesma unidade de medida
     * dos dados originais. É a raiz quadrada da Variância.
     * * @return O Desvio Padrão Amostral.
     */
    public double getDesvioPadrao() {
        return Math.sqrt(getVariancia());
    }

    /**
     * Calcula a Mediana (o valor central).
     * Exige que o array esteja ordenado.
     * Se ímpar: é o valor no meio. Se par: é a média dos dois valores centrais.
     * * @return A Mediana do array.
     */
    public double getMediana() {
        this.ordenar();
        int n = array.length;
        
        if (n % 2 == 1) {
            // Tamanho ímpar: retorna o elemento central
            // O índice é (n / 2) porque a divisão inteira arredonda para baixo (ex: 5/2 = 2, índice 2)
            return array[n / 2];
        } else {
            // Tamanho par: retorna a média dos dois elementos centrais
            int m1 = n / 2 - 1; // Primeiro índice central
            int m2 = n / 2;     // Segundo índice central
            return (array[m1] + array[m2]) / 2.0;
        }
    }

    /**
     * Calcula a Moda (o valor que mais se repete).
     * Usa um HashMap para contar a frequência de cada elemento.
     * Assume que existe apenas uma moda (distribuição unimodal).
     * * @return O valor da Moda (o elemento com a maior frequência).
     */
    public double getModa() {
        // Map armazena o elemento (Double) e sua contagem (Integer)
        Map<Double, Integer> map = new HashMap<>();
        
        // 1. Contagem de Frequências
        for (double elemento : array) {
            // getOrDefault moderniza a contagem: se o elemento existe, pega a contagem + 1. 
            // Se não existe, pega 0 + 1.
            map.put(elemento, map.getOrDefault(elemento, 0) + 1);
        }

        if (map.isEmpty()) return 0.0;

        double moda = array[0]; // Inicializa com o primeiro elemento
        int maxCount = 0;

        // 2. Encontrar o elemento com a contagem máxima
        for (Map.Entry<Double, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                moda = entry.getKey();
            }
        }
        
        return moda;
    }
    
    /**
     * Calcula o Coeficiente de Assimetria de Pearson (Coef. Skewness).
     * Indica a direção e o grau de assimetria da distribuição de dados.
     * Fórmula: Coef. Assimetria = (Média Aritmética - Moda) / Desvio Padrão
     * * @return O Coeficiente de Assimetria. Retorna 0.0 se o desvio padrão for zero.
     */
    public double getCoefAssimetria() {
        double desvio = getDesvioPadrao();
        
        // Evita divisão por zero. Ocorre quando todos os elementos são iguais.
        if (desvio == 0.0) {
            return 0.0;
        }
        return (getMediaAritmetica() - getModa()) / desvio;
    }

    // Getters e Setters
    public double[] getArray() {
        return array;
    }

    public void setArray(double[] array) {
        this.array = array;
    }
}

// -----------------------------------------------------------------------------
//(CLASSE PRINCIPAL)
// -----------------------------------------------------------------------------

public class Exemplo1 {
    public static void main(String[] args) {
        // Instancia a classe utilitária de Estatística
        Estatistica e = new Estatistica();
        
        // Inicializa um array de double com 700.000 elementos
        double array[] = new double[700000];
        
        // Preenche o array com o valor 999.0 para o teste de performance
        Arrays.fill(array, 999.0);
        
        e.setArray(array);

        // Marca o início do tempo
        // CORREÇÃO: System.currentTimeMillis() retorna um 'long'
        long t1 = System.currentTimeMillis();

        // -----------------------
        // EXECUÇÃO DOS CÁLCULOS
        // -----------------------

        System.out.print("\n Mediana: " + e.getMediana());
        System.out.print("\n Soma Elementos: " + e.getSomaDosElementos());
        System.out.print("\n Média Aritmética: " + e.getMediaAritmetica());
        System.out.print("\n SomaDosElementosAoQuadrado: " + e.getSomaDosElementosAoQuadrado());
        System.out.print("\n Variância Amostral: " + e.getVariancia());
        System.out.print("\n Desvio Padrão: " + e.getDesvioPadrao());
        System.out.print("\n Coeficiente Variação de Pearson: " + e.getPearson());
        System.out.print("\n Moda: " + e.getModa());
        System.out.print("\n Coeficiente de Assimetria: " + e.getCoefAssimetria());

        // marca o fim do processamento
        long t2 = System.currentTimeMillis();

        // exibe o tempo em segundos
        // O cálculo (t2 - t1) é dividido por 1000.0 para garantir o resultado em double (ponto flutuante)
        double tempoTotalSegundos = (t2 - t1) / 1000.0;
        System.out.print("\n Tempo Total: " + tempoTotalSegundos + " segundos");
    }
}
