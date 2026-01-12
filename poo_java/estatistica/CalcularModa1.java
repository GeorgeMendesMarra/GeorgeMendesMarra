import java.util.*;

public class CalcularModa1 {
    public static void main(String[] args) {
        // Conjunto de dados de exemplo
        int[] numeros = {1, 2, 3, 4, 2, 2, 3, 5, 2}; 
        
        // Criamos um mapa para armazenar: Chave (o número) e Valor (quantas vezes aparece)
        Map<Integer, Integer> frequencia = new HashMap<>();
        
        // --- PASSO 1: Contagem de frequências ---
        for (int n : numeros) {
            // getOrDefault verifica se o número já está no mapa.
            // Se estiver, pega o valor atual; se não, começa em 0. Depois soma 1.
            frequencia.put(n, frequencia.getOrDefault(n, 0) + 1);
        }
        
        // --- PASSO 2: Identificação da Moda ---
        int moda = numeros[0]; // Variável para armazenar o número mais frequente
        int maxOcorrencias = 0; // Variável para controlar qual a maior contagem encontrada
        
        // Percorremos o mapa de frequências para encontrar o maior valor
        for (Map.Entry<Integer, Integer> entrada : frequencia.entrySet()) {
            // Se a frequência do número atual for maior que a registrada anteriormente
            if (entrada.getValue() > maxOcorrencias) {
                maxOcorrencias = entrada.getValue(); // Atualiza o recorde de ocorrências
                moda = entrada.getKey();             // Define este número como a moda atual
            }
        }
        
        // Exibe o resultado formatado
        System.out.println("Conjunto de dados: " + Arrays.toString(numeros));
        System.out.println("A moda é: " + moda);
        System.out.println("Frequência: " + maxOcorrencias + " vezes.");
    }
}
