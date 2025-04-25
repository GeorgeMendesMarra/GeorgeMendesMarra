import java.util.ArrayList;   // Importa a classe ArrayList para criar listas dinâmicas.
import java.util.Collections; // Importa a classe Collections que fornece métodos utilitários para coleções, como embaralhar.
import java.util.List;        // Importa a interface List que representa uma coleção ordenada de elementos.
import java.util.Random;      // Importa a classe Random para gerar números aleatórios (usado para o baralho).
import java.util.Scanner;     // Importa a classe Scanner para obter entrada do usuário (nome dos jogadores, ações).

// Representa um naipe de uma carta
enum Naipe {
    PAUS, // Naipe de Paus
    OUROS, // Naipe de Ouros
    COPAS, // Naipe de Copas
    ESPADAS // Naipe de Espadas
}

// Representa o valor de uma carta
enum Valor {
    DOIS("2"), // Valor 2
    TRES("3"), // Valor 3
    QUATRO("4"), // Valor 4
    CINCO("5"), // Valor 5
    SEIS("6"), // Valor 6
    SETE("7"), // Valor 7
    OITO("8"), // Valor 8
    NOVE("9"), // Valor 9
    DEZ("10"), // Valor 10
    VALETE("J"), // Valor Valete
    DAMA("Q"), // Valor Dama
    REI("K"), // Valor Rei
    AS("A");    // Valor Ás

    private final String simbolo; // Símbolo do valor da carta (para impressão)

    Valor(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}

// Representa uma carta de baralho
class Carta {
    private final Naipe naipe; // O naipe da carta
    private final Valor valor; // O valor da carta

    public Carta(Naipe naipe, Valor valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public Valor getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor.getSimbolo() + " de " + naipe; // Representação da carta como string (ex: "A de ESPADAS")
    }
}

// Representa um jogador
class Jogador {
    private final String nome; // Nome do jogador
    private final List<Carta> mao; // As cartas na mão do jogador
    private int fichas; // Quantidade de fichas que o jogador possui

    public Jogador(String nome, int fichasIniciais) {
        this.nome = nome;
        this.mao = new ArrayList<>();
        this.fichas = fichasIniciais;
    }

    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public int getFichas() {
        return fichas;
    }

    public void receberCarta(Carta carta) {
        this.mao.add(carta);
    }

    public void apostar(int valor) {
        if (valor <= fichas) {
            fichas -= valor;
        } else {
            System.out.println("Você não tem fichas suficientes para apostar " + valor + ".");
            // Lógica para lidar com aposta inválida (ex: voltar a pedir aposta)
        }
    }

    public void ganharFichas(int valor) {
        fichas += valor;
    }

    public void mostrarMao() {
        System.out.println(nome + " tem na mão: " + mao);
    }

    public void limparMao() {
        this.mao.clear();
    }
}

// Representa o baralho
class Baralho {
    private final List<Carta> cartas; // A lista de cartas no baralho

    public Baralho() {
        this.cartas = criarBaralho();
        embaralhar();
    }

    private List<Carta> criarBaralho() {
        List<Carta> novoBaralho = new ArrayList<>();
        for (Naipe naipe : Naipe.values()) { // Itera por todos os naipes
            for (Valor valor : Valor.values()) { // Itera por todos os valores
                novoBaralho.add(new Carta(naipe, valor)); // Cria uma nova carta e adiciona ao baralho
            }
        }
        return novoBaralho;
    }

    public void embaralhar() {
        Collections.shuffle(cartas); // Embaralha a lista de cartas aleatoriamente
    }

    public Carta distribuirCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0); // Remove e retorna a primeira carta do baralho (topo)
        }
        return null; // Retorna null se o baralho estiver vazio
    }
}

public class JogoDePoker {

    private final List<Jogador> jogadores; // A lista de jogadores participando do jogo
    private final Baralho baralho; // O baralho de cartas usado no jogo
    private final List<Carta> mesa; // As cartas comunitárias na mesa
    private int pote; // O pote de fichas acumuladas para a rodada
    private int apostaAtual; // A aposta atual na rodada de apostas
    private int indiceJogadorAtual; // O índice do jogador cuja vez é apostar

    public JogoDePoker(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.baralho = new Baralho();
        this.mesa = new ArrayList<>();
        this.pote = 0;
        this.apostaAtual = 0;
        this.indiceJogadorAtual = 0;
    }

    public void iniciarRodada() {
        System.out.println("\n--- Iniciando nova rodada ---");
        baralho.embaralhar(); // Embaralha o baralho no início de cada rodada
        mesa.clear(); // Limpa as cartas comunitárias da rodada anterior
        pote = 0; // Reseta o pote para zero
        apostaAtual = 0; // Reseta a aposta atual para zero
        indiceJogadorAtual = 0; // Reseta o índice do jogador atual

        // Limpar as mãos dos jogadores para a nova rodada
        for (Jogador jogador : jogadores) {
            jogador.limparMao();
        }

        // Distribuir 2 cartas para cada jogador (mão inicial)
        for (int i = 0; i < 2; i++) {
            for (Jogador jogador : jogadores) {
                Carta carta = baralho.distribuirCarta();
                if (carta != null) {
                    jogador.receberCarta(carta);
                } else {
                    System.out.println("Erro: Baralho acabou durante a distribuição.");
                    return;
                }
            }
        }

        // Mostrar as mãos dos jogadores (apenas para teste - remover em um jogo real)
        for (Jogador jogador : jogadores) {
            jogador.mostrarMao();
        }

        // Fase de apostas pré-flop (antes das cartas comunitárias)
        faseDeApostas();

        // Flop: Distribuir 3 cartas comunitárias na mesa
        if (jogadoresAtivos() > 1) {
            distribuirCartasComunitarias(3);
            System.out.println("Mesa: " + mesa);
            faseDeApostas();
        }

        // Turn: Distribuir a quarta carta comunitária na mesa
        if (jogadoresAtivos() > 1) {
            distribuirCartasComunitarias(1);
            System.out.println("Mesa: " + mesa);
            faseDeApostas();
        }

        // River: Distribuir a quinta e última carta comunitária na mesa
        if (jogadoresAtivos() > 1) {
            distribuirCartasComunitarias(1);
            System.out.println("Mesa: " + mesa);
            faseDeApostas();
        }

        // Showdown: Se mais de um jogador restou, revelar as mãos e determinar o vencedor
        if (jogadoresAtivos() > 1) {
            determinarVencedor();
        } else if (jogadoresAtivos() == 1) {
            // Se apenas um jogador restou, ele vence por desistência dos outros
            for (Jogador jogador : jogadores) {
                if (!jogador.getMao().isEmpty()) {
                    System.out.println(jogador.getNome() + " venceu por desistência.");
                    jogador.ganharFichas(pote);
                    pote = 0;
                    break;
                }
            }
        }
    }

    private void distribuirCartasComunitarias(int numCartas) {
        for (int i = 0; i < numCartas; i++) {
            Carta carta = baralho.distribuirCarta();
            if (carta != null) {
                mesa.add(carta);
            } else {
                System.out.println("Erro: Baralho acabou ao distribuir cartas comunitárias.");
                return;
            }
        }
    }

    private void faseDeApostas() {
        Scanner scanner = new Scanner(System.in);
        int jogadoresQueApostaram = 0; // Contador de jogadores que fizeram alguma ação na rodada de apostas atual
        int ultimaAposta = 0; // A última aposta feita na rodada

        // Listas para controlar o estado da aposta de cada jogador na rodada atual
        List<Boolean> jogadorAgiu = new ArrayList<>(Collections.nCopies(jogadores.size(), false));
        List<Integer> apostaJogador = new ArrayList<>(Collections.nCopies(jogadores.size(), 0));

        int jogadorAtualApostaIndice = indiceJogadorAtual; // Começa a rodada de apostas com o jogador atual

        // Continua a rodada de apostas enquanto houver mais de um jogador ativo e nem todos tenham apostado/igualado
        while (jogadoresAtivos() > 1 && (jogadoresQueApostaram < jogadoresAtivos() || ultimaAposta > 0)) {
            Jogador jogadorAtual = jogadores.get(jogadorAtualApostaIndice % jogadores.size());

            // Se o jogador ainda tem cartas (não desistiu)
            if (!jogadorAtual.getMao().isEmpty()) {
                System.out.println("\nVez de " + jogadorAtual.getNome() + " (fichas: " + jogadorAtual.getFichas() + ")");
                System.out.println("Aposta atual: " + apostaAtual + ", Pote: " + pote);
                System.out.println("Suas cartas: " + jogadorAtual.getMao());

                // Calcula quanto o jogador precisa apostar para cobrir a aposta atual
                int paraCobrir = apostaAtual - apostaJogador.get(jogadorAtualApostaIndice % jogadores.size());

                System.out.println("Opções: (c)heck, (a)postar " + (paraCobrir > 0 ? "(" + paraCobrir + " para cobrir), " : "") + "(f)old");
                String acao = scanner.next().toLowerCase();

                if (acao.equals("c")) { // Check: passar a vez sem apostar (só possível se a aposta atual for 0 ou já tiver igualado)
                    if (paraCobrir == 0 || jogadorAgiu.get(jogadorAtualApostaIndice % jogadores.size())) {
                        jogadorAgiu.set(jogadorAtualApostaIndice % jogadores.size(), true);
                        jogadoresQueApostaram++;
                    } else {
                        System.out.println("Você precisa cobrir a aposta atual para dar check.");
                        continue;
                    }
                } else if (acao.equals("a")) { // Apostar: fazer uma nova aposta ou aumentar a existente
                    System.out.print("Valor da aposta: ");
                    int valorAposta = scanner.nextInt();
                    if (valorAposta >= paraCobrir && jogadorAtual.getFichas() >= valorAposta) {
                        jogadorAtual.apostar(valorAposta);
                        pote += valorAposta;
                        apostaJogador.set(jogadorAtualApostaIndice % jogadores.size(), apostaJogador.get(jogadorAtualApostaIndice % jogadores.size()) + valorAposta);
                        apostaAtual = Math.max(apostaAtual, apostaJogador.get(jogadorAtualApostaIndice % jogadores.size()));
                        ultimaAposta = apostaAtual;
                        jogadorAgiu.set(jogadorAtualApostaIndice % jogadores.size(), true);
                        jogadoresQueApostaram = 1; // Resetar contador, pois houve uma nova aposta
                        // Atualizar jogadores que já apostaram nesta rodada de apostas
                        for (int i = 0; i < jogadorAgiu.size(); i++) {
                            if (i != jogadorAtualApostaIndice % jogadores.size() && apostaJogador.get(i) < apostaAtual && jogadores.get(i).getMao().size() > 0) {
                                jogadorAgiu.set(i, false);
                            }
                        }
                    } else {
                        System.out.println("Aposta inválida. Deve ser no mínimo " + paraCobrir + " e você deve ter fichas suficientes.");
                        continue;
                    }
                } else if (acao.equals("f")) { // Fold: desistir da rodada
                    jogadorAtual.limparMao(); // Remove as cartas da mão do jogador
                    jogadoresQueApostaram++;
                } else {
                    System.out.println("Ação inválida.");
                    continue;
                }
            }

            jogadorAtualApostaIndice++; // Passa a vez para o próximo jogador
        }
    }

    private int jogadoresAtivos() {
        int ativos = 0;
        for (Jogador jogador : jogadores) {
            if (!jogador.getMao().isEmpty()) {
                ativos++;
            }
        }
        return ativos;
    }

    private void determinarVencedor() {
        System.out.println("\n--- Showdown ---");
        List<Jogador> jogadoresAtivos = new ArrayList<>();
        for (Jogador jogador : jogadores) {
            if (!jogador.getMao().isEmpty()) {
                jogador.mostrarMao();
                jogadoresAtivos.add(jogador);
            }
        }
        System.out.println("Mesa: " + mesa);

        if (jogadoresAtivos.size() == 1) {
            Jogador vencedor = jogadoresAtivos.get(0);
            System.out.println(vencedor.getNome() + " é o vencedor! Pote: " + pote);
            vencedor.ganharFichas(pote);
            pote = 0;
            return;
        }

        // TODO: Implementar lógica para avaliar as mãos de poker e determinar o vencedor
        // (straight flush, quadra, full house, flush, straight, trinca, dois pares, um par, carta alta)
        // Por enquanto, o primeiro jogador ativo ganha (para fins de demonstração da estrutura do jogo)
        if (!jogadoresAtivos.isEmpty()) {
            Jogador vencedor = jogadoresAtivos.get(0);
            System.out.println(vencedor.getNome() + " é o vencedor (mão não avaliada)! Pote: " + pote);
            vencedor.ganharFichas(pote);
            pote = 0;
        } else {
            System.out.println("Nenhum jogador ativo para determinar o vencedor.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Jogador> jogadores = new ArrayList<>();

        System.out.print("Digite o nome do Jogador 1: ");
        String nomeJogador1 = scanner.nextLine();
        jogadores.add(new Jogador(nomeJogador1, 100)); // Cria o Jogador 1 com 100 fichas

        System.out.print("Digite o nome do Jogador 2 (Computador): ");
        String nomeJogador2 = scanner.nextLine();
        jogadores.add(new Jogador(nomeJogador2, 100)); // Cria o Jogador 2 (Computador) com 100 fichas

        JogoDePoker jogo = new JogoDePoker(jogadores); // Cria uma instância do jogo com os jogadores

        // Continua o jogo enquanto todos os jogadores tiverem fichas suficientes para jogar
        while (jogadores.stream().allMatch(j -> j.getFichas() > 0)) {
            jogo.iniciarRodada(); // Inicia uma nova rodada de poker
            System.out.print("\nPressione Enter para iniciar a próxima rodada...");
            scanner.nextLine(); // Aguarda o jogador pressionar Enter para continuar
        }

        System.out.println("\n--- Fim de Jogo ---");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome() + " tem " + jogador.getFichas() + " fichas.");
        }
        System.out.println("Obrigado por jogar!");
        scanner.close(); // Fecha o scanner para liberar recursos
    }
}
