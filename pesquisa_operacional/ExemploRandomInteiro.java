import java.util.Random; // Importa a classe Random para geração de números pseudoaleatórios.

public class ExemploRandomInteiro {
    public static void main(String[] args) {
        // Cria uma instância da classe Random. Cada instância mantém seu próprio estado
        // para gerar a sequência de números aleatórios.
        Random random = new Random();

        // -------------------- Gerando um número inteiro aleatório (sem limites específicos) --------------------
        // O método nextInt() retorna o próximo valor inteiro pseudoaleatório da sequência
        // do gerador. Este valor pode ser positivo ou negativo e cobre toda a faixa
        // de valores possíveis para um int em Java (de Integer.MIN_VALUE a Integer.MAX_VALUE).
        int numeroAleatorio1 = random.nextInt();
        System.out.println("Número inteiro aleatório (ilimitado): " + numeroAleatorio1);

        // -------------------- Gerando um número inteiro aleatório dentro de um limite superior --------------------
        // O método nextInt(limiteSuperior) retorna um valor inteiro pseudoaleatório
        // no intervalo de 0 (inclusive) até limiteSuperior (exclusive).
        int limiteSuperior = 10; // Define o limite superior (não incluído) para o número aleatório.
        int numeroAleatorio2 = random.nextInt(limiteSuperior);
        System.out.println("Número inteiro aleatório (0 até " + (limiteSuperior - 1) + "): " + numeroAleatorio2);

        // -------------------- Gerando um número inteiro aleatório dentro de uma faixa específica (min e max inclusivos) --------------------
        int min = 5; // Define o valor mínimo (inclusive) da faixa desejada.
        int max = 15; // Define o valor máximo (inclusive) da faixa desejada.
        // Para gerar um número inteiro dentro de uma faixa [min, max] (inclusive),
        // usamos a seguinte lógica:
        // 1. Calcula a largura da faixa: (max - min + 1). O +1 é necessário para incluir ambos os extremos.
        // 2. Gera um número aleatório entre 0 (inclusive) e a largura da faixa (exclusive) usando nextInt().
        // 3. Adiciona o valor mínimo (min) ao resultado para deslocar o intervalo para a faixa desejada.
        int numeroAleatorioNaFaixa = random.nextInt(max - min + 1) + min;
        System.out.println("Número inteiro aleatório (" + min + " até " + max + "): " + numeroAleatorioNaFaixa);
    }
}
