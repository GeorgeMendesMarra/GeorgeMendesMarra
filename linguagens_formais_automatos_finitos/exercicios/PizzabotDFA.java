// PizzabotDFA.java
import java.util.Scanner;

public class PizzabotDFA {
    
    // Enumeração que define todos os estados possíveis do DFA
    enum State { 
        SAUDACAO,           // Estado inicial, aguardando o comando para começar o pedido
        ESCOLHER_SABOR,     // Aguardando a escolha do sabor
        ESCOLHER_TAMANHO,   // Aguardando a escolha do tamanho da pizza
        ESCOLHER_BEBIDA,    // Aguardando a escolha da bebida (ou se é "sem")
        INFORMAR_ENDERECO,  // Aguardando o endereço e telefone
        CONFIRMAR,          // Aguardando a confirmação, alteração ou cancelamento
        PAGAMENTO,          // Aguardando o método de pagamento
        FINALIZADO          // Pedido concluído, estado final
    }

    public static void main(String[] args) {
        // Inicializa o objeto Scanner para ler a entrada do console
        Scanner sc = new Scanner(System.in);
        
        // Define o estado inicial do DFA
        State state = State.SAUDACAO;
        
        // Variáveis para armazenar os dados do pedido (variáveis de contexto)
        String sabor = null, tamanho = null, bebida = null, endereco = null, pagamento = null;

        System.out.println("Bot: Olá! Diga 'quero pedir' para começar.");
        
        // Loop principal do DFA: continua executando até que o estado FINALIZADO ou CANCELADO seja atingido
        while (true) {
            // Lê, limpa e padroniza a entrada do usuário (para facilitar as verificações)
            String input = sc.nextLine().trim().toLowerCase();
            
            // Estrutura principal do DFA: a transição de estado é controlada pelo 'switch'
            switch (state) {
                
                // === ESTADO: SAUDACAO ===
                case SAUDACAO:
                    // Verifica se a entrada do usuário contém palavras-chave para iniciar o pedido.
                    // Isso é o critério de transição para o próximo estado.
                    if (input.contains("quero") || input.contains("pedir") || input.contains("pizza") || input.contains("pedido")) {
                        state = State.ESCOLHER_SABOR; // Transição para o próximo estado
                        System.out.println("Bot: Qual sabor? (calabresa, mussarela, portuguesa, frango)");
                    } else {
                        // Permanece no estado SAUDACAO
                        System.out.println("Bot: Diga 'quero pedir' quando quiser fazer um pedido.");
                    }
                    break;
                    
                // === ESTADO: ESCOLHER_SABOR ===
                case ESCOLHER_SABOR:
                    // Verifica qual dos sabores válidos foi mencionado na entrada
                    if (input.contains("calabresa") || input.contains("mussarela") ||
                        input.contains("portuguesa") || input.contains("frango")) {
                        
                        // Determina e armazena o sabor
                        if (input.contains("calabresa")) sabor = "calabresa";
                        else if (input.contains("mussarela")) sabor = "mussarela";
                        else if (input.contains("portuguesa")) sabor = "portuguesa";
                        else sabor = "frango";
                        
                        state = State.ESCOLHER_TAMANHO; // Transição para o próximo estado
                        System.out.println("Bot: Tamanho? (pequena, média, grande)");
                    } else {
                        // Permanece no estado ESCOLHER_SABOR
                        System.out.println("Bot: Não entendi. Escolha entre calabresa, mussarela, portuguesa, frango.");
                    }
                    break;
                    
                // === ESTADO: ESCOLHER_TAMANHO ===
                case ESCOLHER_TAMANHO:
                    // Verifica e armazena o tamanho escolhido
                    if (input.contains("pequena") || input.contains("pequeno")) {
                        tamanho = "pequena"; state = State.ESCOLHER_BEBIDA;
                        System.out.println("Bot: Deseja bebida? (diga o nome ou 'sem')");
                    } else if (input.contains("média") || input.contains("media")) {
                        tamanho = "média"; state = State.ESCOLHER_BEBIDA;
                        System.out.println("Bot: Deseja bebida? (diga o nome ou 'sem')");
                    } else if (input.contains("grande")) {
                        tamanho = "grande"; state = State.ESCOLHER_BEBIDA;
                        System.out.println("Bot: Deseja bebida? (diga o nome ou 'sem')");
                    } else {
                        // Permanece no estado ESCOLHER_TAMANHO
                        System.out.println("Bot: Tamanhos válidos: pequena, média, grande.");
                    }
                    break;
                    
                // === ESTADO: ESCOLHER_BEBIDA ===
                case ESCOLHER_BEBIDA:
                    // Verifica se o usuário escolheu "sem bebida"
                    if (input.contains("sem")) {
                        bebida = "sem bebida";
                    } else {
                        // Se não disse "sem", assume que a entrada é o nome da bebida.
                        // Nota: Esta lógica é simples e assume que a entrada é a bebida.
                        bebida = input.isEmpty() ? "sem bebida" : input;
                    }
                    state = State.INFORMAR_ENDERECO; // Transição
                    System.out.println("Bot: Informe endereço e telefone:");
                    break;
                    
                // === ESTADO: INFORMAR_ENDERECO ===
                case INFORMAR_ENDERECO:
                    // Validação simples: o endereço deve ter mais de 5 caracteres
                    if (input.length() > 5) {
                        endereco = input;
                        state = State.CONFIRMAR; // Transição para confirmação
                        
                        // Exibe o resumo do pedido
                        System.out.println("Bot: Confirma o pedido? (confirmar/alterar/cancelar)");
                        System.out.println("Resumo: " + sabor + " (" + tamanho + ") - " + bebida + " - " + endereco);
                    } else {
                        // Permanece no estado INFORMAR_ENDERECO
                        System.out.println("Bot: Endereço inválido. Informe rua, número e telefone.");
                    }
                    break;
                    
                // === ESTADO: CONFIRMAR ===
                case CONFIRMAR:
                    if (input.contains("confirm")) {
                        state = State.PAGAMENTO; // Transição para pagamento
                        System.out.println("Bot: Como deseja pagar? (dinheiro/cartão)");
                    } else if (input.contains("alter")) {
                        state = State.ESCOLHER_SABOR; // Retorna para o início do processo (reset)
                        System.out.println("Bot: Ok, qual novo sabor?");
                    } else if (input.contains("cancel")) {
                        System.out.println("Bot: Pedido cancelado. Obrigado!");
                        sc.close(); return; // Sai do método, encerrando o programa
                    } else {
                        // Permanece no estado CONFIRMAR
                        System.out.println("Bot: Use confirmar, alterar ou cancelar.");
                    }
                    break;
                    
                // === ESTADO: PAGAMENTO ===
                case PAGAMENTO:
                    if (input.contains("din")) {
                        pagamento = "dinheiro"; state = State.FINALIZADO;
                    } else if (input.contains("cart")) {
                        pagamento = "cartão"; state = State.FINALIZADO;
                    } else {
                        // Permanece no estado PAGAMENTO
                        System.out.println("Bot: Escolha 'dinheiro' ou 'cartão'.");
                    }
                    
                    // Verifica se a transição para FINALIZADO ocorreu
                    if (state == State.FINALIZADO) {
                        System.out.println("Bot: Pedido confirmado! Resumo:");
                        System.out.println("Sabor: " + sabor + " | Tamanho: " + tamanho + " | Bebida: " + bebida);
                        System.out.println("Endereço: " + endereco + " | Pagamento: " + pagamento);
                        System.out.println("Bot: Obrigado! Sua pizza chegará em ~30 minutos.");
                        sc.close();
                        return; // Encerra o programa
                    }
                    break;
                    
                // === ESTADO: PADRAO/ERRO ===
                default:
                    // Caso um estado não mapeado seja atingido (erro de lógica)
                    System.out.println("Bot: Erro de estado.");
                    sc.close();
                    return;
            }
        }
    }
}
