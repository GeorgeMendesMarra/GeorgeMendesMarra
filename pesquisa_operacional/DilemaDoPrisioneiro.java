import java.util.Scanner;

public class DilemaDoPrisioneiro {

    public static void main(String[] args) {
        // Cria um objeto Scanner para receber entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Inicializa os jogadores com seus respectivos nomes
        Jogador jogador1 = new Jogador("Prisioneiro A");
        Jogador jogador2 = new Jogador("Prisioneiro B");

        // Inicia um loop infinito para permitir múltiplas rodadas do jogo
        while (true) {
            // Solicita a escolha do Prisioneiro A
            System.out.println("\n" + jogador1.getNome() + ", deseja colaborar (c) ou trair (t)?");
            char escolha1 = scanner.next().toLowerCase().charAt(0);

            // Solicita a escolha do Prisioneiro B
            System.out.println("\n" + jogador2.getNome() + ", deseja colaborar (c) ou trair (t)?");
            char escolha2 = scanner.next().toLowerCase().charAt(0);

            // Define a escolha do Jogador 1 com base na entrada do usuário
            if(escolha1 == 'c'){
                jogador1.escolha = "colaborar";
            } else {
                jogador1.escolha = "trair";
            }

            // Define a escolha do Jogador 2 com base na entrada do usuário
            if(escolha2 == 'c'){
                jogador2.escolha = "colaborar";
            } else {
                jogador2.escolha = "trair";
            }

            // Determina o resultado do jogo com base nas escolhas de ambos os jogadores
            if(jogador1.escolha.equals("colaborar") && jogador2.escolha.equals("colaborar")){
                System.out.println("\nAmbos escolheram colaborar. Ambos recebem 2 anos de prisão.");
            } else if(jogador1.escolha.equals("colaborar") && jogador2.escolha.equals("trair")){
                System.out.println("\n" + jogador1.getNome() + " colaborou e " + jogador2.getNome() + " traiu. " + jogador1.getNome() + " recebe 10 anos e " + jogador2.getNome() + " sai livre.");
            } else if(jogador1.escolha.equals("trair") && jogador2.escolha.equals("colaborar")){
                System.out.println("\n" + jogador1.getNome() + " traiu e " + jogador2.getNome() + " colaborou. " + jogador2.getNome() + " recebe 10 anos e " + jogador1.getNome() + " sai livre.");
            } else {
                System.out.println("\nAmbos escolheram trair. Ambos recebem 5 anos de prisão.");
            }

            // Informa aos jogadores as escolhas que cada um fez
            System.out.println("\n" + jogador1.getNome() + " escolheu: " + jogador1.escolha);
            System.out.println(jogador2.getNome() + " escolheu: " + jogador2.escolha);

            // Pergunta aos jogadores se eles querem jogar novamente
            System.out.println("\nJogar novamente? (s/n)");
            char jogarNovamente = scanner.next().toLowerCase().charAt(0);

            // Se a resposta não for 's', o loop é interrompido e o jogo termina
            if(jogarNovamente != 's'){
                break;
            }
        }
        // Fecha o objeto Scanner para liberar recursos
        scanner.close();
    }
}

// Classe que representa um jogador no Dilema do Prisioneiro
class Jogador {
    String nome; // Nome do jogador
    String escolha; // Escolha do jogador (colaborar ou trair)

    // Construtor da classe Jogador que recebe o nome como parâmetro
    public Jogador(String nome) {
        this.nome = nome;
    }

    // Método para obter o nome do jogador
    public String getNome() {
        return nome;
    }
}
