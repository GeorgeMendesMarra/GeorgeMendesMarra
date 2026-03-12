// Importa a classe Scanner para leitura de dados pelo teclado
import java.util.Scanner;

// Declaração da classe principal
public class CampeonatoBrasileiro {

    // Método principal onde o programa começa a execução
    public static void main(String[] args) {

        // Cria um objeto Scanner para ler dados digitados pelo usuário
        Scanner entrada = new Scanner(System.in);

        // Declara variáveis para armazenar os pontos
        int pontosLider;
        int pontosLanterna;

        // Solicita ao usuário os pontos do líder do campeonato
        System.out.print("Digite a quantidade de pontos do líder: ");
        pontosLider = entrada.nextInt();

        // Solicita ao usuário os pontos do time lanterna
        System.out.print("Digite a quantidade de pontos do lanterna: ");
        pontosLanterna = entrada.nextInt();

        // Calcula a diferença de pontos entre o líder e o lanterna
        int diferenca = pontosLider - pontosLanterna;

        // Cada vitória vale 3 pontos
        // Calcula o número de vitórias necessárias para alcançar ou ultrapassar o líder
        int vitoriasNecessarias = diferenca / 3;

        // Exibe o resultado na tela
        System.out.println("\nNúmero de vitórias necessárias para alcançar o líder: " 
                            + vitoriasNecessarias);

        // Fecha o objeto Scanner
        entrada.close();
    }
}
