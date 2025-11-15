/**
 * SchoolTransportChatbot.java
 *
 * Chatbot de atendimento de transporte escolar implementado como um autômato de estados.
 *
 * Fluxo principal (sequência): SAUDACAO -> INFORMAR_ROTA -> HORARIO -> FINALIZAR
 * Também reage a palavras-chave como “horário”, “motorista” e “rota”.
 *
 * Autor:
 * Data: 2025
 */

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ChatBot_Transporte_Escolar {

    /**
     * Enumeração com os estados possíveis do autômato.
     * Cada estado representa uma fase da conversa.
     */
    private enum State {
        SAUDACAO,      // 1️⃣ Saudação inicial
        INFORMAR_ROTA, // 2️⃣ Informar rota desejada
        HORARIO,       // 3️⃣ Informar horários
        FINALIZAR,     // 4️⃣ Encerrar atendimento
        UNKNOWN        // Estado genérico para perguntas não reconhecidas
    }

    // Estado atual do chatbot (começa em SAUDACAO)
    private State currentState;

    // Tabelas de exemplo para rotas, horários e motoristas
    private final Map<String, String> routes = new HashMap<>();
    private final Map<String, String> routeTimes = new HashMap<>();
    private final Map<String, String> routeDrivers = new HashMap<>();

    /**
     * Construtor — inicializa o chatbot e os dados de exemplo.
     */
    public SchoolTransportChatbot() {
        this.currentState = State.SAUDACAO; // estado inicial
        initData(); // carrega rotas e motoristas
    }

    /**
     * Inicializa os dados de exemplo.
     * Em um sistema real, essas informações viriam de um banco de dados.
     */
    private void initData() {
        // Rotas com suas descrições
        routes.put("rota 1", "Rota 1: Bairro A → Bairro B → Escola Central");
        routes.put("rota 2", "Rota 2: Condomínio X → Rua 5 → Escola Central");
        routes.put("rota 3", "Rota 3: Zona Rural (Ponto Fazenda) → Escola Central");

        // Horários por rota
        routeTimes.put("rota 1", "06:30 (ida), 12:30 (volta)");
        routeTimes.put("rota 2", "06:45 (ida), 12:45 (volta)");
        routeTimes.put("rota 3", "06:00 (ida), 13:00 (volta)");

        // Motoristas por rota
        routeDrivers.put("rota 1", "Motorista: João Silva — contato: (99) 91234-0001");
        routeDrivers.put("rota 2", "Motorista: Maria Oliveira — contato: (99) 91234-0002");
        routeDrivers.put("rota 3", "Motorista: Carlos Pereira — contato: (99) 91234-0003");
    }

    /**
     * Detecta o estado baseado na entrada do usuário.
     * Usa correspondência simples de palavras-chave.
     */
    private State detectState(String input) {
        String s = input.toLowerCase(Locale.ROOT);

        // Verifica se o usuário fala algo sobre horário
        if (s.contains("horário") || s.contains("hora")) {
            return State.HORARIO;
        }

        // Se falar sobre motorista
        if (s.contains("motorista") || s.contains("condutor")) {
            return State.UNKNOWN; // será tratado dentro da resposta
        }

        // Se mencionar rota
        if (s.contains("rota")) {
            return State.INFORMAR_ROTA;
        }

        // Caso contrário, não entendeu o que o usuário quis dizer
        return State.UNKNOWN;
    }

    /**
     * Gera a resposta do chatbot com base no estado atual e na entrada do usuário.
     */
    private String respond(State state, String userInput) {
        String s = userInput.toLowerCase(Locale.ROOT).trim();

        switch (state) {

            // 🟢 Estado 1: Saudação inicial
            case SAUDACAO:
                this.currentState = State.INFORMAR_ROTA; // muda para o próximo estado
                return "Olá! Bem-vindo ao atendimento de transporte escolar. " +
                       "Qual rota você precisa — por exemplo 'Rota 1', 'Rota 2' ou 'Rota 3'?";

            // 🟡 Estado 2: Informar rota solicitada
            case INFORMAR_ROTA:
                // Tenta identificar qual rota foi mencionada
                String foundRoute = extractRouteKey(s);
                if (foundRoute != null) {
                    // Se a rota for reconhecida, exibe informações e vai para o estado HORARIO
                    this.currentState = State.HORARIO;
                    String desc = routes.getOrDefault(foundRoute, "Descrição da rota indisponível.");
                    return desc + " — Deseja saber o horário ou o nome do motorista?";
                } else {
                    // Se não identificou a rota, pede que o usuário digite novamente
                    this.currentState = State.INFORMAR_ROTA;
                    return "Não identifiquei a rota. Informe como 'Rota 1', 'Rota 2' ou 'Rota 3'.";
                }

            // 🕒 Estado 3: Informar horário
            case HORARIO:
                // Verifica se o usuário especificou uma rota
                String routeKey = extractRouteKey(s);
                if (routeKey != null) {
                    String time = routeTimes.getOrDefault(routeKey, "Horário não cadastrado para essa rota.");
                    this.currentState = State.FINALIZAR; // próximo estado é finalizar
                    return "Horário da " + routeKey + ": " + time +
                           ". Precisa de mais alguma informação ou deseja finalizar?";
                } else {
                    // Se o usuário não informou a rota
                    this.currentState = State.HORARIO;
                    return "Para qual rota você quer o horário? Informe 'Rota 1', 'Rota 2' ou 'Rota 3'.";
                }

            // 🔴 Estado 4: Finalizar atendimento
            case FINALIZAR:
                this.currentState = State.SAUDACAO; // volta ao estado inicial
                return "Atendimento finalizado. Obrigado! Volte sempre!";

            // ⚪ Estado genérico (não reconheceu)
            case UNKNOWN:
            default:
                // Se o usuário falou de motorista
                if (s.contains("motorista")) {
                    String rk = extractRouteKey(s);
                    if (rk != null) {
                        // Se informou rota e motorista
                        String drv = routeDrivers.getOrDefault(rk, "Motorista não cadastrado para essa rota.");
                        this.currentState = State.HORARIO; // volta ao fluxo principal
                        return drv + " — Deseja também saber o horário dessa rota?";
                    } else {
                        // Se só disse "motorista"
                        this.currentState = State.INFORMAR_ROTA;
                        return "Qual rota você quer saber o motorista? 'Rota 1', 'Rota 2' ou 'Rota 3'?";
                    }
                }

                // Caso nenhuma palavra-chave seja encontrada
                this.currentState = State.INFORMAR_ROTA;
                return "Desculpe, não entendi. Posso ajudar com 'rota', 'horário' ou 'motorista'. Qual você deseja?";
        }
    }

    /**
     * Extrai a chave da rota do texto (ex: "rota 1", "rota 2").
     * Retorna null se não encontrar nenhuma rota válida.
     */
    private String extractRouteKey(String s) {
        // Procura por "rota 1", "rota 2", etc.
        for (int i = 1; i <= 10; i++) {
            String key = "rota " + i;
            if (s.contains(key) || s.contains("rota" + i)) {
                return key;
            }
        }
        // Também verifica rotas já cadastradas no mapa
        for (String k : routes.keySet()) {
            if (s.contains(k)) return k;
        }
        return null;
    }

    /**
     * Loop principal de execução do chatbot (modo texto).
     */
    public void run() {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Atendimento de Transporte Escolar — digite 'sair' para encerrar.");

        // Envia saudação inicial automaticamente
        System.out.println("Bot: " + respond(State.SAUDACAO, ""));

        // Laço principal de conversa
        while (true) {
            System.out.print("\nVocê: ");
            String userInput = scanner.nextLine().trim();

            // Se o usuário não digitar nada
            if (userInput.isEmpty()) {
                System.out.println("Bot: Por favor, digite sua pergunta ou 'sair' para encerrar.");
                continue;
            }

            // Se o usuário quiser encerrar
            String lower = userInput.toLowerCase(Locale.ROOT);
            if (lower.equals("sair") || lower.equals("finalizar") || lower.equals("tchau")) {
                System.out.println("Bot: Obrigado! Atendimento encerrado.");
                break;
            }

            // Detecta o estado com base nas palavras da frase
            State detected = detectState(userInput);

            // Gera a resposta e exibe
            String botReply = respond(detected, userInput);
            System.out.println("Bot: " + botReply);
        }

        scanner.close();
    }

    /**
     * Método main — executa o chatbot.
     */
    public static void main(String[] args) {
        SchoolTransportChatbot chatbot = new SchoolTransportChatbot();
        chatbot.run();
    }
}
