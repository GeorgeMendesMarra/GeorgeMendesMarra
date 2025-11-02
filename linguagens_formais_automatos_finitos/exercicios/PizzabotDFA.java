// PizzabotDFA.java
import java.util.Scanner;

public class PizzabotDFA {
    enum State { SAUDACAO, ESCOLHER_SABOR, ESCOLHER_TAMANHO, ESCOLHER_BEBIDA,
                 INFORMAR_ENDERECO, CONFIRMAR, PAGAMENTO, FINALIZADO }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        State state = State.SAUDACAO;
        String sabor = null, tamanho = null, bebida = null, endereco = null, pagamento = null;

        System.out.println("Bot: Olá! Diga 'quero pedir' para começar.");
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            switch (state) {
                case SAUDACAO:
                    // CORREÇÃO APLICADA AQUI:
                    // Usando contains() para verificar se a entrada contém as palavras-chave,
                    // o que é mais simples e menos propenso a erros de regex do que o matches()
                    if (input.contains("quero") || input.contains("pedir") || input.contains("pizza") || input.contains("pedido")) {
                        state = State.ESCOLHER_SABOR;
                        System.out.println("Bot: Qual sabor? (calabresa, mussarela, portuguesa, frango)");
                    } else {
                        System.out.println("Bot: Diga 'quero pedir' quando quiser fazer um pedido.");
                    }
                    break;
                case ESCOLHER_SABOR:
                    if (input.contains("calabresa") || input.contains("mussarela") ||
                        input.contains("portuguesa") || input.contains("frango")) {
                        if (input.contains("calabresa")) sabor = "calabresa";
                        else if (input.contains("mussarela")) sabor = "mussarela";
                        else if (input.contains("portuguesa")) sabor = "portuguesa";
                        else sabor = "frango";
                        state = State.ESCOLHER_TAMANHO;
                        System.out.println("Bot: Tamanho? (pequena, média, grande)");
                    } else {
                        System.out.println("Bot: Não entendi. Escolha entre calabresa, mussarela, portuguesa, frango.");
                    }
                    break;
                case ESCOLHER_TAMANHO:
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
                        System.out.println("Bot: Tamanhos válidos: pequena, média, grande.");
                    }
                    break;
                case ESCOLHER_BEBIDA:
                    if (input.contains("sem")) {
                        bebida = "sem bebida";
                    } else {
                        bebida = input.isEmpty() ? "sem bebida" : input;
                    }
                    state = State.INFORMAR_ENDERECO;
                    System.out.println("Bot: Informe endereço e telefone:");
                    break;
                case INFORMAR_ENDERECO:
                    if (input.length() > 5) {
                        endereco = input;
                        state = State.CONFIRMAR;
                        System.out.println("Bot: Confirma o pedido? (confirmar/alterar/cancelar)");
                        System.out.println("Resumo: " + sabor + " (" + tamanho + ") - " + bebida + " - " + endereco);
                    } else {
                        System.out.println("Bot: Endereço inválido. Informe rua, número e telefone.");
                    }
                    break;
                case CONFIRMAR:
                    if (input.contains("confirm")) {
                        state = State.PAGAMENTO;
                        System.out.println("Bot: Como deseja pagar? (dinheiro/cartão)");
                    } else if (input.contains("alter")) {
                        state = State.ESCOLHER_SABOR;
                        System.out.println("Bot: Ok, qual novo sabor?");
                    } else if (input.contains("cancel")) {
                        System.out.println("Bot: Pedido cancelado. Obrigado!");
                        sc.close(); return;
                    } else {
                        System.out.println("Bot: Use confirmar, alterar ou cancelar.");
                    }
                    break;
                case PAGAMENTO:
                    if (input.contains("din")) {
                        pagamento = "dinheiro"; state = State.FINALIZADO;
                    } else if (input.contains("cart")) {
                        pagamento = "cartão"; state = State.FINALIZADO;
                    } else {
                        System.out.println("Bot: Escolha 'dinheiro' ou 'cartão'.");
                    }
                    if (state == State.FINALIZADO) {
                        System.out.println("Bot: Pedido confirmado! Resumo:");
                        System.out.println("Sabor: " + sabor + " | Tamanho: " + tamanho + " | Bebida: " + bebida);
                        System.out.println("Endereço: " + endereco + " | Pagamento: " + pagamento);
                        System.out.println("Bot: Obrigado! Sua pizza chegará em ~30 minutos.");
                        sc.close();
                        return;
                    }
                    break;
                default:
                    System.out.println("Bot: Erro de estado.");
                    sc.close();
                    return;
            }
        }
    }
}
