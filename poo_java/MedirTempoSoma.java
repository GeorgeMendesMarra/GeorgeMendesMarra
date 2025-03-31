public class MedirTempoSoma {
    public static void main(String[] args) {
        // Captura o tempo inicial em nanosegundos
        long inicio = System.nanoTime();

        // Bloco de código que queremos medir (soma de 1 a 1 milhão)
        long soma = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            soma += i; // Adiciona o valor de 'i' à variável 'soma'
        }

        // Captura o tempo final em nanosegundos
        long fim = System.nanoTime();

        // Calcula o tempo total de execução em milissegundos (1 ms = 1.000.000 ns)
        long tempoExecucao = (fim - inicio) / 1_000_000;

        // Exibe o resultado da soma
        System.out.println("Resultado da soma: " + soma);

        // Exibe o tempo total de execução do código
        System.out.println("Tempo de execução: " + tempoExecucao + " ms");
    }
}

