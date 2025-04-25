import java.util.ArrayList; // Importa a classe ArrayList para criar listas dinâmicas.
import java.util.List; // Importa a interface List para representar coleções ordenadas.
import java.util.Random; // Importa a classe Random para gerar números aleatórios.

enum Escolha {
    COOPERAR, // Enumeração representando a escolha de cooperar.
    TRAIR    // Enumeração representando a escolha de trair.
}

class JogadorNaoCooperativo {
    String nome; // Nome do jogador.
    Random random = new Random(); // Instância da classe Random para escolhas aleatórias.

    // Construtor da classe JogadorNaoCooperativo.
    public JogadorNaoCooperativo(String nome) {
        this.nome = nome;
    }

    // Método para o jogador escolher sua ação.
    // Recebe a escolha do oponente como argumento (pode ser null na primeira rodada).
    public Escolha escolher(Escolha escolhaOponente) {
        // Estratégia simples: escolher aleatoriamente entre cooperar e trair.
        return random.nextBoolean() ? Escolha.COOPERAR : Escolha.TRAIR;
    }

    // Método para obter o nome do jogador.
    public String getNome() {
        return nome;
    }
}

class DilemaPrisioneiroNaoCooperativo {
    public static void main(String[] args) {
        // Cria dois jogadores não cooperativos.
        JogadorNaoCooperativo jogador1 = new JogadorNaoCooperativo("Jogador 1");
        JogadorNaoCooperativo jogador2 = new JogadorNaoCooperativo("Jogador 2");

        // Cada jogador faz sua escolha, sem comunicação prévia.
        Escolha escolha1 = jogador1.escolher(null); // Jogador 1 escolhe (sem saber a escolha do oponente na primeira vez).
        Escolha escolha2 = jogador2.escolher(escolha1); // Jogador 2 escolhe (sabendo a escolha do jogador 1).

        // Imprime as escolhas de cada jogador.
        System.out.println(jogador1.getNome() + " escolheu: " + escolha1);
        System.out.println(jogador2.getNome() + " escolheu: " + escolha2);

        // Determina as recompensas com base nas combinações de escolhas.
        if (escolha1 == Escolha.COOPERAR && escolha2 == Escolha.COOPERAR) {
            System.out.println("Ambos cooperaram: Recompensa média.");
        } else if (escolha1 == Escolha.COOPERAR && escolha2 == Escolha.TRAIR) {
            System.out.println(jogador1.getNome() + " cooperou, " + jogador2.getNome() + " traiu: " + jogador1.getNome() + " recebe punição, " + jogador2.getNome() + " recebe alta recompensa.");
        } else if (escolha1 == Escolha.TRAIR && escolha2 == Escolha.COOPERAR) {
            System.out.println(jogador1.getNome() + " traiu, " + jogador2.getNome() + " cooperou: " + jogador1.getNome() + " recebe alta recompensa, " + jogador2.getNome() + " recebe punição.");
        } else { // escolha1 == Escolha.TRAIR && escolha2 == Escolha.TRAIR
            System.out.println("Ambos traíram: Recompensa baixa.");
        }
    }
}
