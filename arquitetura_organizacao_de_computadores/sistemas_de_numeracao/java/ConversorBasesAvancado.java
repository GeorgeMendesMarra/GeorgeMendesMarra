// Importa a classe Scanner, que permite ler dados digitados pelo usuário no console
import java.util.Scanner;

/**
 * Classe principal do Conversor de Bases Avançado
 * Permite converter números entre diferentes bases numéricas (2 a 36)
 * 
 * @author
 * @version 2.0
 */
public class ConversorBasesAvancado {
    
    // ==================== CONSTANTES GLOBAIS ====================
    
    /**
     * String contendo todos os dígitos/símbolos possíveis para bases até 36
     * Posição 0 = '0', posição 10 = 'A', posição 35 = 'Z'
     * Usada para representar números em bases maiores que 10
     */
    private static final String DIGITOS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // ==================== MÉTODO PRINCIPAL ====================
    
    /**
     * Método principal que inicia o programa
     * Exibe o menu e gerencia as opções escolhidas pelo usuário
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do teclado
        Scanner leitor = new Scanner(System.in);
        int opcao;  // Variável para armazenar a opção escolhida pelo usuário
        
        // Exibe o cabeçalho do programa
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("       CONVERSOR DE BASES AVANÇADO                  ");
        System.out.println("═══════════════════════════════════════════════════");
        
        // Loop principal - repete até o usuário escolher a opção 0 (sair)
        do {
            exibirMenu();  // Chama o método que mostra as opções do menu
            System.out.print("Escolha uma opção: ");
            
            // Verifica se o usuário digitou um número inteiro
            if (leitor.hasNextInt()) {
                opcao = leitor.nextInt();      // Lê a opção
                leitor.nextLine();              // Limpa o buffer do teclado
                
                // Estrutura switch para executar a opção escolhida
                switch (opcao) {
                    case 1:
                        converterDecimalParaOutrasBases(leitor);
                        break;
                    case 2:
                        converterOutraBaseParaDecimal(leitor);
                        break;
                    case 3:
                        converterEntreBases(leitor);
                        break;
                    case 4:
                        conversorInterativo(leitor);
                        break;
                    case 5:
                        mostrarTabelaConversao(leitor);
                        break;
                    case 0:
                        System.out.println("\nEncerrando o programa. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                // Se o usuário não digitou um número, exibe erro e limpa o buffer
                System.out.println("Por favor, digite um número válido.");
                leitor.next();  // Descarta a entrada inválida
                opcao = -1;     // Define opção inválida para continuar o loop
            }
            
            // Pausa o programa antes de voltar ao menu (exceto para sair e para a tabela)
            if (opcao != 0 && opcao != 5) {
                System.out.print("\nPressione ENTER para continuar...");
                leitor.nextLine();
            }
            
        } while (opcao != 0);  // Continua enquanto o usuário não escolher sair
        
        leitor.close();  // Fecha o Scanner para liberar recursos
    }
    
    // ==================== MÉTODOS DO MENU ====================
    
    /**
     * Exibe as opções do menu principal no console
     */
    private static void exibirMenu() {
        System.out.println("\n───────────────────────────────────────────────────");
        System.out.println("                    MENU PRINCIPAL                   ");
        System.out.println("───────────────────────────────────────────────────");
        System.out.println("1. Decimal → Outras bases (2 a 36)");
        System.out.println("2. Outras bases → Decimal");
        System.out.println("3. Converter entre quaisquer bases (2 a 36)");
        System.out.println("4. Modo conversor interativo");
        System.out.println("5. Tabela de conversão (2, 8, 10, 16)");
        System.out.println("0. Sair");
        System.out.println("───────────────────────────────────────────────────");
    }
    
    // ==================== OPÇÃO 1: Decimal para outras bases ====================
    
    /**
     * Converte um número decimal (base 10) para qualquer outra base (2 a 36)
     * @param leitor Objeto Scanner para ler a entrada do usuário
     */
    private static void converterDecimalParaOutrasBases(Scanner leitor) {
        System.out.println("\n--- CONVERSÃO: Decimal → Outra Base ---");
        
        System.out.print("Digite o número decimal: ");
        // Verifica se o usuário digitou um número inteiro válido
        if (!leitor.hasNextInt()) {
            System.out.println("Erro: Digite um número inteiro válido.");
            leitor.next();  // Descarta a entrada inválida
            return;         // Sai do método
        }
        
        int numero = leitor.nextInt();   // Armazena o número digitado
        leitor.nextLine();               // Limpa o buffer
        
        System.out.print("Digite a base de destino (2 a 36): ");
        if (!leitor.hasNextInt()) {
            System.out.println("Erro: Digite uma base válida.");
            leitor.next();
            return;
        }
        
        int baseDestino = leitor.nextInt();
        leitor.nextLine();
        
        // Valida se a base está dentro do intervalo permitido
        if (baseDestino < 2 || baseDestino > 36) {
            System.out.println("Erro: Base deve estar entre 2 e 36.");
            return;
        }
        
        // Chama o método auxiliar para fazer a conversão
        String resultado = decimalParaBase(numero, baseDestino);
        
        // Exibe o resultado formatado
        System.out.println("\n📊 RESULTADO:");
        System.out.println("   " + numero + " (decimal) = " + resultado + " (base " + baseDestino + ")");
        
        // Mostra também as conversões mais comuns para referência
        System.out.println("\n📌 Outras representações do mesmo número:");
        System.out.println("   Binário:     " + decimalParaBase(numero, 2));
        System.out.println("   Octal:       " + decimalParaBase(numero, 8));
        System.out.println("   Hexadecimal: 0x" + decimalParaBase(numero, 16));
    }
    
    // ==================== OPÇÃO 2: Outras bases para Decimal ====================
    
    /**
     * Converte um número de qualquer base (2 a 36) para decimal (base 10)
     * @param leitor Objeto Scanner para ler a entrada do usuário
     */
    private static void converterOutraBaseParaDecimal(Scanner leitor) {
        System.out.println("\n--- CONVERSÃO: Outra Base → Decimal ---");
        
        System.out.print("Digite o número (na base de origem): ");
        String numeroStr = leitor.nextLine().toUpperCase().trim();  // Lê e converte para maiúsculo
        
        System.out.print("Digite a base de origem (2 a 36): ");
        if (!leitor.hasNextInt()) {
            System.out.println("Erro: Digite uma base válida.");
            leitor.next();
            return;
        }
        
        int baseOrigem = leitor.nextInt();
        leitor.nextLine();
        
        // Valida a base
        if (baseOrigem < 2 || baseOrigem > 36) {
            System.out.println("Erro: Base deve estar entre 2 e 36.");
            return;
        }
        
        try {
            // Tenta converter o número para decimal usando o método auxiliar
            int decimal = baseParaDecimal(numeroStr, baseOrigem);
            System.out.println("\n📊 RESULTADO:");
            System.out.println("   " + numeroStr + " (base " + baseOrigem + ") = " + decimal + " (decimal)");
        } catch (IllegalArgumentException e) {
            // Captura erro se o número contiver dígitos inválidos para a base
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    // ==================== OPÇÃO 3: Converter entre quaisquer bases ====================
    
    /**
     * Converte um número diretamente entre duas bases quaisquer (2 a 36)
     * Funciona convertendo primeiro para decimal (passo intermediário)
     * @param leitor Objeto Scanner para ler a entrada do usuário
     */
    private static void converterEntreBases(Scanner leitor) {
        System.out.println("\n--- CONVERSÃO ENTRE BASES ---");
        
        System.out.print("Digite o número original: ");
        String numeroStr = leitor.nextLine().toUpperCase().trim();
        
        System.out.print("Digite a base de origem (2 a 36): ");
        if (!leitor.hasNextInt()) {
            System.out.println("Erro: Digite uma base válida.");
            leitor.next();
            return;
        }
        int baseOrigem = leitor.nextInt();
        leitor.nextLine();
        
        System.out.print("Digite a base de destino (2 a 36): ");
        if (!leitor.hasNextInt()) {
            System.out.println("Erro: Digite uma base válida.");
            leitor.next();
            return;
        }
        int baseDestino = leitor.nextInt();
        leitor.nextLine();
        
        // Valida ambas as bases
        if (baseOrigem < 2 || baseOrigem > 36 || baseDestino < 2 || baseDestino > 36) {
            System.out.println("Erro: Bases devem estar entre 2 e 36.");
            return;
        }
        
        try {
            // Passo 1: Converter da base original para decimal
            int decimal = baseParaDecimal(numeroStr, baseOrigem);
            
            // Passo 2: Converter de decimal para a base de destino
            String resultado = decimalParaBase(decimal, baseDestino);
            
            System.out.println("\n📊 RESULTADO:");
            System.out.println("   " + numeroStr + " (base " + baseOrigem + ") = " + 
                             resultado + " (base " + baseDestino + ")");
            
            // Mostra o valor decimal intermediário para entendimento do processo
            System.out.println("\n📌 Passo intermediário (decimal): " + decimal);
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    
    // ==================== OPÇÃO 4: Modo Conversor Interativo ====================
    
    /**
     * Modo interativo que mostra múltiplas conversões do mesmo número
     * O usuário pode digitar vários números consecutivos até digitar "sair"
     * @param leitor Objeto Scanner para ler a entrada do usuário
     */
    private static void conversorInterativo(Scanner leitor) {
        System.out.println("\n--- MODO CONVERSOR INTERATIVO ---");
        System.out.println("Digite 'sair' para voltar ao menu principal.\n");
        
        // Loop infinito que só termina quando o usuário digita "sair"
        while (true) {
            System.out.print("Digite o número (base 10) ou 'sair': ");
            String entrada = leitor.nextLine().trim();
            
            // Verifica se o usuário quer sair do modo interativo
            if (entrada.equalsIgnoreCase("sair")) {
                break;
            }
            
            try {
                // Tenta converter a entrada para número inteiro
                int numero = Integer.parseInt(entrada);
                
                // Exibe uma tabela formatada com várias conversões
                System.out.println("\n┌─────────────────────────────────────────────────┐");
                System.out.println("│           CONVERSÕES DO NÚMERO " + numero + "           │");
                System.out.println("├─────────────────────────────────────────────────┤");
                System.out.printf("│ %-15s │ %-25s │\n", "BASE", "VALOR");
                System.out.println("├─────────────────────────────────────────────────┤");
                System.out.printf("│ %-15s │ %-25s │\n", "2 (Binário)", decimalParaBase(numero, 2));
                System.out.printf("│ %-15s │ %-25s │\n", "3", decimalParaBase(numero, 3));
                System.out.printf("│ %-15s │ %-25s │\n", "4", decimalParaBase(numero, 4));
                System.out.printf("│ %-15s │ %-25s │\n", "5", decimalParaBase(numero, 5));
                System.out.printf("│ %-15s │ %-25s │\n", "6", decimalParaBase(numero, 6));
                System.out.printf("│ %-15s │ %-25s │\n", "7", decimalParaBase(numero, 7));
                System.out.printf("│ %-15s │ %-25s │\n", "8 (Octal)", decimalParaBase(numero, 8));
                System.out.printf("│ %-15s │ %-25s │\n", "9", decimalParaBase(numero, 9));
                System.out.printf("│ %-15s │ %-25s │\n", "10 (Decimal)", numero);
                System.out.printf("│ %-15s │ %-25s │\n", "16 (Hexa)", "0x" + decimalParaBase(numero, 16));
                System.out.printf("│ %-15s │ %-25s │\n", "32", decimalParaBase(numero, 32));
                System.out.println("└─────────────────────────────────────────────────┘\n");
                
            } catch (NumberFormatException e) {
                // Executa se o usuário não digitou um número válido
                System.out.println("❌ Número inválido! Digite um número inteiro ou 'sair'.\n");
            }
        }
    }
    
    // ==================== OPÇÃO 5: Tabela de Conversão ====================
    
    /**
     * Gera uma tabela comparativa mostrando números em decimal, binário, octal e hexadecimal
     * O usuário escolhe até qual número deseja visualizar
     * @param leitor Objeto Scanner para ler a entrada do usuário
     */
    private static void mostrarTabelaConversao(Scanner leitor) {
        System.out.println("\n--- TABELA DE CONVERSÃO (Decimal, Binário, Octal, Hexadecimal) ---\n");
        
        System.out.print("Até qual número deseja ver a tabela? ");
        if (!leitor.hasNextInt()) {
            System.out.println("Digite um número válido.");
            leitor.next();
            return;
        }
        
        int max = leitor.nextInt();
        leitor.nextLine();
        
        // Avisa se a tabela for muito grande (acima de 1000 números)
        if (max > 1000) {
            System.out.println("Aviso: Gerar tabela até " + max + " pode ser demorado. Deseja continuar? (s/n)");
            String resposta = leitor.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                return;  // Cancela se o usuário não confirmar
            }
        }
        
        // Cabeçalho da tabela
        System.out.println("\n┌─────────┬────────────────┬─────────┬────────────┐");
        System.out.println("│ DECIMAL │    BINÁRIO     │  OCTAL  │ HEXADECIMAL│");
        System.out.println("├─────────┼────────────────┼─────────┼────────────┤");
        
        // Gera a tabela (limitada a 200 linhas para não poluir a tela)
        for (int i = 0; i <= Math.min(max, 200); i++) {
            String bin = decimalParaBase(i, 2);   // Converte para binário
            String oct = decimalParaBase(i, 8);   // Converte para octal
            String hex = "0x" + decimalParaBase(i, 16); // Converte para hexadecimal
            
            // Exibe cada linha formatada
            System.out.printf("│ %7d │ %14s │ %7s │ %10s │\n", i, bin, oct, hex);
        }
        
        // Mensagem informando que a tabela foi truncada se o limite foi maior que 200
        if (max > 200) {
            System.out.println("├─────────┴────────────────┴─────────┴────────────┤");
            System.out.println("│            ... tabela truncada em 200           │");
        }
        
        System.out.println("└─────────────────────────────────────────────────────┘");
    }
    
    // ==================== FUNÇÕES AUXILIARES (NÚCLEO DO CONVERSOR) ====================
    
    /**
     * Converte um número decimal para uma base entre 2 e 36
     * 
     * Algoritmo: Divide o número sucessivamente pela base desejada
     * e coleta os restos (do último para o primeiro)
     * 
     * Exemplo: 255 em binário (base 2)
     * 255 ÷ 2 = 127, resto 1 (LSB - least significant bit)
     * 127 ÷ 2 = 63,  resto 1
     * 63  ÷ 2 = 31,  resto 1
     * 31  ÷ 2 = 15,  resto 1
     * 15  ÷ 2 = 7,   resto 1
     * 7   ÷ 2 = 3,   resto 1
     * 3   ÷ 2 = 1,   resto 1
     * 1   ÷ 2 = 0,   resto 1 (MSB - most significant bit)
     * Lendo os restos de baixo para cima: 11111111
     * 
     * @param numero Número decimal a ser convertido
     * @param base Base de destino (2 a 36)
     * @return String representando o número na base desejada
     */
    private static String decimalParaBase(int numero, int base) {
        // Caso especial: número zero
        if (numero == 0) return "0";
        
        // Validação da base (segurança)
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("Base inválida: " + base);
        }
        
        // Verifica se o número é negativo e guarda essa informação
        boolean negativo = numero < 0;
        if (negativo) numero = -numero;  // Trabalha com o valor absoluto
        
        // StringBuilder é mais eficiente que String para concatenações repetidas
        StringBuilder resultado = new StringBuilder();
        
        // Loop principal do algoritmo de conversão
        while (numero > 0) {
            int resto = numero % base;                    // Calcula o resto da divisão
            resultado.insert(0, DIGITOS.charAt(resto));     // Insere o dígito no início
            numero = numero / base;                        // Divide o número para próxima iteração
        }
        
        // Se o número original era negativo, adiciona o sinal de menos
        if (negativo) resultado.insert(0, "-");
        
        return resultado.toString();
    }
    
    /**
     * Converte um número em uma base específica para decimal
     * 
     * Algoritmo: Percorre cada dígito da esquerda para direita,
     * multiplicando o resultado atual pela base e somando o valor do dígito
     * 
     * Exemplo: FF (hexadecimal) para decimal
     * F = 15, F = 15
     * resultado = 0 * 16 + 15 = 15
     * resultado = 15 * 16 + 15 = 255
     * 
     * @param numeroStr String representando o número na base original
     * @param base Base de origem (2 a 36)
     * @return Valor decimal do número
     */
    private static int baseParaDecimal(String numeroStr, int base) {
        // Validação da base
        if (base < 2 || base > 36) {
            throw new IllegalArgumentException("Base deve estar entre 2 e 36");
        }
        
        // Verifica se o número é negativo
        boolean negativo = numeroStr.startsWith("-");
        if (negativo) numeroStr = numeroStr.substring(1);  // Remove o sinal para processamento
        
        int resultado = 0;
        
        // Percorre cada caractere da string (da esquerda para a direita)
        for (int i = 0; i < numeroStr.length(); i++) {
            char c = numeroStr.charAt(i);
            int valorDigito = DIGITOS.indexOf(c);  // Converte caractere para valor numérico
            
            // Verifica se o dígito é válido para a base
            // Ex: 'F' é válido apenas para base >= 16
            if (valorDigito == -1 || valorDigito >= base) {
                throw new IllegalArgumentException("Dígito inválido '" + c + "' para a base " + base);
            }
            
            // Fórmula de conversão: resultado = resultado * base + valor_do_dígito
            resultado = resultado * base + valorDigito;
        }
        
        // Aplica o sinal negativo se necessário
        return negativo ? -resultado : resultado;
    }
}
