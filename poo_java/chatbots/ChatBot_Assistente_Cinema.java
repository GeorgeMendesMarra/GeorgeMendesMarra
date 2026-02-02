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
public class ChatBot_Assistente_Cinema {

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
        System.out.println(botRespond(State.SAUDACAO, ""));

        // ⚠️ Correção importante:
        // Após a saudação, o estado já avança para ESCOLHER_FILME
        // para que a primeira entrada do usuário seja tratada corretamente.
        currentState = State.ESCOLHER_FILME;

        // Loop principal — continua até o estado FINALIZAR
        while (currentState != State.FINALIZAR) {
            System.out.print("\nVocê: ");
            String entrada = scanner.nextLine(); // lê entrada do usuário
            processarEntrada(entrada);            // interpreta a entrada
        }
    }

    /**
     * Interpreta a entrada do usuário e decide qual será o próximo estado.
     */
    private void processarEntrada(String input) {
        // Normaliza o texto (minúsculas, sem acentuação)
        input = normalize(input);

        switch (currentState) {

            case SAUDACAO:
                // Estado de saudação — transita imediatamente para ESCOLHER_FILME
                currentState = State.ESCOLHER_FILME;
                System.out.println(botRespond(currentState, input));
                break;

            case ESCOLHER_FILME:
                // Identifica o gênero de filme com base em palavras-chave
                if (input.contains("acao") || input.contains("heroi")) {
                    filmeEscolhido = "Vingadores: Ultimato";
                } else if (input.contains("comedia")) {
                    filmeEscolhido = "As Branquelas";
                } else if (input.contains("terror")) {
                    filmeEscolhido = "Invocação do Mal";
                } else {
                    filmeEscolhido = "Filme Surpresa 🎥";
                }

                // Avança para o próximo estado
                currentState = State.HORARIO;
                System.out.println(botRespond(currentState, filmeEscolhido));
                break;

            case HORARIO:
                // Reconhece o horário desejado com base nas palavras digitadas
                if (input.contains("14") || input.contains("duas") || input.contains("14h")) {
                    currentState = State.INGRESSO;
                    System.out.println(botRespond(currentState, "14h00"));
                } else if (input.contains("18") || input.contains("seis") || input.contains("18h")) {
                    currentState = State.INGRESSO;
                    System.out.println(botRespond(currentState, "18h00"));
                } else {
                    // Caso o horário não seja reconhecido
                    System.out.println("Temos sessões às 14h e 18h. Qual prefere?");
                }
                break;

            case INGRESSO:
                // Extrai o número de ingressos da entrada (qualquer número digitado)
                try {
                    ingressos = Integer.parseInt(input.replaceAll("\\D+", ""));
                    if (ingressos <= 0) ingressos = 1;
                } catch (Exception e) {
                    ingressos = 1;
                }

                // Avança para o pagamento
                currentState = State.PAGAMENTO;
                System.out.println(botRespond(currentState, input));
                break;

            case PAGAMENTO:
                // Identifica o meio de pagamento
                if (input.contains("cartao") || input.contains("pix") ||
                    input.contains("dinheiro") || input.contains("pagar")) {
                    currentState = State.FINALIZAR;
                    System.out.println(botRespond(currentState, input));
                } else {
                    System.out.println("Aceitamos cartão, PIX ou dinheiro. Como deseja pagar?");
                }
                break;

            default:
                // Caso chegue ao final
                System.out.println("Atendimento finalizado. Obrigado por usar o CineChat!");
                currentState = State.FINALIZAR;
        }
    }

    /**
     * Retorna uma resposta automática do bot conforme o estado.
     */
    private String botRespond(State s, String input) {
        switch (s) {
            case SAUDACAO:
                return "Olá! Eu sou o assistente do CineChat. Deseja ver filmes de ação, comédia ou terror?";
            case ESCOLHER_FILME:
                return "Qual gênero de filme você quer assistir hoje?";
            case HORARIO:
                return "Ótima escolha! Temos sessões às 14h e 18h. Qual horário prefere?";
            case INGRESSO:
                return "Quantos ingressos deseja comprar?";
            case PAGAMENTO:
                return "Certo! O total é R$ " + (ingressos * 25) +
                        ". Deseja pagar com cartão, PIX ou dinheiro?";
            case FINALIZAR:
                return "Pagamento confirmado! Bom filme 🍿🎥";
            default:
                return "Desculpe, não entendi. Pode repetir?";
        }
    }

    /**
     * Normaliza o texto para facilitar a comparação de palavras.
     * - Converte para minúsculas
     * - Remove acentos comuns da língua portuguesa
     */
    private String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase()
                .replace("á", "a")
                .replace("ã", "a")
                .replace("â", "a")
                .replace("é", "e")
                .replace("ê", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("õ", "o")
                .replace("ô", "o")
                .replace("ú", "u");
    }

    /**
     * Método principal — ponto de entrada da aplicação.
     */
    public static void main(String[] args) {
        CinemaAssistant bot = new CinemaAssistant();
        bot.iniciar(); // inicia o atendimento
    }
}
