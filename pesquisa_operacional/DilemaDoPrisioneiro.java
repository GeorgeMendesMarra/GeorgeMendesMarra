import java.util.Scanner;
import java.util.Random;

public class DilemaDoPrisioneiro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bem-vindo ao Dilema do Prisioneiro em Java!");

        // Loop principal para permitir múltiplas rodadas
        while (true) {
            System.out.println("\n--- Nova Rodada ---");

            // Escolha do Jogador 1 (Humano)
            String escolhaJogador1 = obterEscolhaHumana(scanner, "Jogador 1");

            // Escolha do Jogador 2 (Computador - Aleatório)
            String escolhaJogador2 = obterEscolhaComputadorAleatorio(random);
            System.out.println("Jogador 2 escolheu: " + (escolhaJogador2.equals("colaborar") ? "colaborar (c)" : "trair (t)"));

            // Determina as sentenças com base nas escolhas
            determinarSentencas(escolhaJogador1, escolhaJogador2, "Jogador 1", "Jogador 2");

            // Pergunta se deseja jogar novamente
            if (!desejaJogarNovamente(scanner)) {
                break;
            }
        }

        System.out.println("\nObrigado por jogar!");
        scanner.close();
    }

    /**
     * Obtém a escolha do jogador humano (colaborar ou trair) através da entrada do scanner.
     *
     * @param scanner O objeto Scanner para leitura da entrada do usuário.
     * @param nomeJogador O nome do jogador.
     * @return A escolha do jogador ("colaborar" ou "trair").
     */
    public static String obterEscolhaHumana(Scanner scanner, String nomeJogador) {
        String escolha;
        while (true) {
            System.out.println(nomeJogador + ", deseja colaborar (c) ou trair (t)?");
            String entrada = scanner.next().toLowerCase();
            if (entrada.equals("c")) {
                escolha = "colaborar";
                break;
            } else if (entrada.equals("t")) {
                escolha = "trair";
                break;
            } else {
                System.out.println("Escolha inválida. Digite 'c' para colaborar ou 't' para trair.");
            }
        }
        return escolha;
    }

    /**
     * Obtém a escolha do computador de forma aleatória.
     *
     * @param random O objeto Random para gerar a escolha aleatória.
     * @return A escolha do computador ("colaborar" ou "trair").
     */
    public static String obterEscolhaComputadorAleatorio(Random random) {
        return random.nextBoolean() ? "colaborar" : "trair";
    }

    /**
     * Determina e exibe as sentenças para ambos os jogadores com base em suas escolhas.
     *
     * @param escolhaJogador1 A escolha do Jogador 1.
     * @param escolhaJogador2 A escolha do Jogador 2.
     * @param nomeJogador1 O nome do Jogador 1.
     * @param nomeJogador2 O nome do Jogador 2.
     */
    public static void determinarSentencas(String escolhaJogador1, String escolhaJogador2, String nomeJogador1, String nomeJogador2) {
        System.out.println("\n--- Resultados da Rodada ---");
        System.out.println(nomeJogador1 + " escolheu: " + (escolhaJogador1.equals("colaborar") ? "colaborar (c)" : "trair (t)"));
        System.out.println(nomeJogador2 + " escolheu: " + (escolhaJogador2.equals("colaborar") ? "colaborar (c)" : "trair (t)"));

        if (escolhaJogador1.equals("colaborar") && escolhaJogador2.equals("colaborar")) {
            System.out.println("Ambos colaboraram. Ambos recebem 2 anos de prisão.");
        } else if (escolhaJogador1.equals("colaborar") && escolhaJogador2.equals("trair")) {
            System.out.println(nomeJogador1 + " colaborou e " + nomeJogador2 + " traiu. " + nomeJogador1 + " recebe 10 anos e " + nomeJogador2 + " sai livre.");
        } else if (escolhaJogador1.equals("trair") && escolhaJogador2.equals("colaborar")) {
            System.out.println(nomeJogador1 + " traiu e " + nomeJogador2 + " colaborou. " + nomeJogador2 + " recebe 10 anos e " + nomeJogador1 + " sai livre.");
        } else { // Ambos traíram
            System.out.println("Ambos traíram. Ambos recebem 5 anos de prisão.");
        }
    }

    /**
     * Pergunta ao jogador se ele deseja jogar novamente.
     *
     * @param scanner O objeto Scanner para leitura da entrada do usuário.
     * @return true se o jogador deseja jogar novamente, false caso contrário.
     */
    public static boolean desejaJogarNovamente(Scanner scanner) {
        System.out.println("\nDeseja jogar novamente? (s/n)");
        String resposta = scanner.next().toLowerCase();
        return resposta.equals("s");
    }
}
