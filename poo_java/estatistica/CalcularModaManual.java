import java.util.Arrays;

public class CalcularModaManual {
    public static void main(String[] args) {
        // Nosso conjunto de dados inicial
        int[] numeros = {1, 2, 3, 4, 2, 2, 3, 5, 2};

        // --- PASSO 1: Ordenação ---
        // Ordenamos o array para que todos os números iguais fiquem vizinhos.
        // O array passará de [1, 2, 3, 4, 2, 2, 3, 5, 2] para [1, 2, 2, 2, 2, 3, 3, 4, 5]
        Arrays.sort(numeros);

        // --- PASSO 2: Inicialização de Variáveis de Controle ---
        int moda = numeros[0];      // Armazena o número que venceu até agora
        int maxVotos = 1;           // Recorde de repetições encontradas
        
        int candidatoAtual = numeros[0]; // O número que estamos contando no momento
        int contagemAtual = 1;           // Quantas vezes o candidato atual se repetiu

        // --- PASSO 3: Varredura do Array ---
        // Começamos do segundo elemento (índice 1) comparando com o anterior
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] == candidatoAtual) {
                // Se o número atual é igual ao que estamos contando, incrementamos
                contagemAtual++;
            } else {
                // Se o número mudou, verificamos se a sequência anterior foi a maior de todas
                if (contagemAtual > maxVotos) {
                    maxVotos = contagemAtual;
                    moda = candidatoAtual;
                }
                // Reiniciamos a contagem para o novo número que acabou de aparecer
                candidatoAtual = numeros[i];
                contagemAtual = 1;
            }
        }

        // --- PASSO 4: Verificação Final ---
        // Precisamos verificar o último grupo de números após o fim do laço
        if (contagemAtual > maxVotos) {
            maxVotos = contagemAtual;
            moda = candidatoAtual;
        }

        // Exibição do resultado
        System.out.println("Array ordenado para cálculo: " + Arrays.toString(numeros));
        System.out.println("A moda é: " + moda + " (repetiu " + maxVotos + " vezes)");
    }
}
