import java.util.*;

/**
 * 
 *
 * Chatbot que simula o atendimento de um cinema usando um autômato finito.
 *
 * Estados principais:
 *  - SAUDACAO → saudação inicial
 *  - ESCOLHER_FILME → usuário escolhe o gênero do filme
 *  - HORARIO → usuário escolhe o horário da sessão
 *  - INGRESSO → usuário informa quantos ingressos deseja
 *  - PAGAMENTO → usuário escolhe o método de pagamento
 *  - FINALIZAR → encerra o atendimento
 *
 * O programa usa palavras-chave simples e muda de estado conforme a entrada do usuário.
 */
public class CinemaAssistant {

    // Enumeração que representa os estados do autômato
    enum State {
        SAUDACAO, ESCOLHER_FILME, HORARIO, INGRESSO, PAGAMENTO, FINALIZAR
    }

    // Estado atual do chatbot
    private State currentState;

    // Scanner para leitura de entradas do teclado
    private Scanner scanner;

    // Armazena o filme e a quantidade de ingressos escolhidos pelo usuário
    private String filmeEscolhido;
    private int ingressos;

    /**
     * Construtor da classe.
     * Define o estado inicial e inicializa o scanner.
     */
    public CinemaAssistant() {
        currentState = State.SAUDACAO;
        scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     * Método principal que executa o chatbot.
     * Mostra a saudação inicial e inicia o loop de atendimento.
     */
    public void iniciar() {
        System.out.println("🎬 Bem-vindo ao CineChat!");

        // Mostra a primeira mensagem (estado de saudação)
        System.out.println(botRespond(St
