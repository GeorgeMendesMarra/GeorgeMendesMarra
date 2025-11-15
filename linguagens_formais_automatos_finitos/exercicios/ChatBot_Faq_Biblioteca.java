/**
 * LibraryFAQChatbot.java
 *
 * Chatbot de FAQ de biblioteca implementado como um autômato de estados.
 * Cada categoria (horário, renovar livro, multas, localização, empréstimo)
 * é um estado. O autômato recebe frases do usuário, detecta o estado
 * (categoria) próprio da pergunta, responde e retorna ao estado inicial (IDLE).
 *
 * Uso:
 *   java LibraryFAQChatbot
 *
 * Comandos:
 *   - Digite perguntas como "Qual o horário?", "Como renovar meu livro?",
 *     "Tenho multa, o que faço?".
 *   - Para sair, digite "sair" ou "exit".
 *
 * Autor:
 * Data: 2025
 */

import java.util.Locale;
import java.util.Scanner;

public class ChatBot_Faq_Biblioteca {

    /**
     * Estados do autômato — cada categoria de FAQ é um estado.
     */
    private enum State {
        IDLE,       // Estado inicial / esperando entrada
        HOURS,      // Horário de funcionamento
        RENEW,      // Renovação de empréstimo
        FINES,      // Multas / pagamentos
        LOCATION,   // Localização / setores
        LOAN_INFO,  // Empréstimo (prazos, limites)
        UNKNOWN     // Pergunta não reconhecida
    }

    private State currentState;

    public LibraryFAQChatbot() {
        this.currentState = State.IDLE; // começa no estado inicial
    }

    /**
     * Detecta o estado (categoria) com base na entrada do usuário.
     * Método simples baseado em palavras-chave. Pode ser refinado com NLP.
     *
     * @param input entrada do usuário (frase)
     * @return estado detectado
     */
    private State detectState(String input) {
        String s = input.toLowerCase(Locale.ROOT);

        // Checagens simples por palavras-chave (podem ser expandidas)
        if (s.contains("horário") || s.contains("aberto") || s.contains("fech") || s.contains("funcion")) {
            return State.HOURS;
        }
        if (s.contains("renov") || s.contains("renovar") || s.contains("prorroga")) {
            return State.RENEW;
        }
        if (s.contains("multa") || s.contains("multas") || s.contains("pagar") || s.contains("multa?")) {
            return State.FINES;
        }
        if (s.contains("onde") || s.contains("localiza") || s.contains("localização") || s.contains("setor")) {
            return State.LOCATION;
        }
        if (s.contains("emprest") || s.contains("prazo") || s.contains("limite") || s.contains("devolver")) {
            return State.LOAN_INFO;
        }

        // Se nada bate, retorna UNKNOWN
        return State.UNKNOWN;
    }

    /**
     * Produz a resposta baseada no estado detectado. Após responder, o autômato
     * volta para o estado inicial (IDLE).
     *
     * @param state estado detectado
     * @param userInput a frase original do usuário (pode ser usada para respostas personalizadas)
     * @return resposta textual do chatbot
     */
    private String respond(State state, String userInput) {
        String response;

        switch (state) {
            case HOURS:
                response = "Horário de funcionamento: segunda a sexta, das 08:00 às 18:00. " +
                           "Aos sábados, das 08:00 às 12:00. Fechamos aos domingos e feriados.";
                break;

            case RENEW:
                response = "Para renovar um livro: acesse sua conta no portal da biblioteca ou " +
                           "leve o exemplar ao balcão de atendimento. Renovações online são permitidas até " +
                           "2 dias antes do vencimento e se não houver reservas.";
                break;

            case FINES:
                response = "Multas: você pode pagar no caixa da biblioteca ou via boleto no portal. " +
                           "Consulte seu extrato online para ver valores e datas de vencimento. " +
                           "Se precisar, podemos gerar instruções passo a passo para pagamento.";
                break;

            case LOCATION:
                response = "Localização: a biblioteca fica no Bloco B, 2º andar. " +
                           "Setor de Ciências exatas no corredor A, Humanas no corredor B. " +
                           "Precisa que eu indique a seção específica?";
                break;

            case LOAN_INFO:
                response = "Informações de empréstimo: o prazo padrão é 14 dias para alunos e 28 dias " +
                           "para professores. O limite é 5 títulos por vez (podendo variar conforme perfil).";
                break;

            case UNKNOWN:
            default:
                response = "Desculpe — não entendi bem. Posso ajudar com: horário, renovar livro, multas, " +
                           "localização ou informações de empréstimo. Qual dessas você quer?";
                break;
        }

        // Volta ao estado inicial (comportamento do autômato)
        this.currentState = State.IDLE;
        return response;
    }

    /**
     * Loop principal de interação (linha de comando).
     */
    public void run() {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Olá! Sou o assistente FAQ da biblioteca. Pergunte algo (digite 'sair' para encerrar).");

        while (true) {
            System.out.print("\nVocê: ");
            String userInput = scanner.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("Bot: Por favor, escreva uma pergunta ou 'sair' para encerrar.");
                continue;
            }

            // Comandos de saída
            String lower = userInput.toLowerCase(Locale.ROOT);
            if (lower.equals("sair") || lower.equals("exit") || lower.equals("fim")) {
                System.out.println("Bot: Obrigado! Até mais.");
                break;
            }

            // Transição: detecta para qual estado ir
            this.currentState = detectState(userInput);

            // Responde (e retorna a IDLE internamente)
            String botResponse = respond(this.currentState, userInput);
            System.out.println("Bot: " + botResponse);
        }

        scanner.close();
    }

    /**
     * Método main — inicia o chatbot.
     *
     * @param args argumentos de linha de comando (não usados)
     */
    public static void main(String[] args) {
        LibraryFAQChatbot chatbot = new LibraryFAQChatbot();
        chatbot.run();
    }
}
