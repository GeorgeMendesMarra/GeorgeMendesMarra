import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe que simula um terminal de informações de Bletchley Park.
 * Utiliza Map para associar termos de busca a explicações históricas.
 */
public class EnigmaChatbotDinamico {
    public static void main(String[] args) {
        // Scanner: Abre o canal de entrada para ler o que o usuário digita no console
        Scanner scanner = new Scanner(System.in);
        
        /* * Estrutura de Dados HashMap:
         * Funciona como o Plugboard da Enigma. Mapeamos uma "Chave" (entrada) 
         * para um "Valor" (saída/resposta).
         * <String, String> significa que tanto a pergunta quanto a resposta são textos.
         */
        Map<String, String> conhecimento = new HashMap<>();
        
        // Método put(): Alimenta a base de dados do bot (nosso dicionário de termos)
        conhecimento.put("historia", "A Enigma foi patenteada em 1918 por Arthur Scherbius.");
        conhecimento.put("java", "Java usa a JVM para rodar em qualquer lugar, como uma Bombe digital.");
        conhecimento.put("turing", "Alan Turing foi o gênio que decifrou a Enigma Naval na Hut 8.");
        conhecimento.put("bombe", "As 'Bombas' eram máquinas eletromecânicas que testavam chaves da Enigma.");

        System.out.println("--- Terminal Bletchley Park (Versão HashMap) ---");
        System.out.println("Pergunte algo (ex: turing, historia) ou digite 'sair':");

        // Loop de execução: Mantém o programa rodando até que o comando de saída seja dado
        while (true) {
            System.out.print("> ");
            
            /* * Tratamento de entrada:
             * .toLowerCase(): Ignora se o usuário digitou maiúsculas ou minúsculas (Case Insensitive)
             * .trim(): Remove espaços em branco acidentais no início ou fim
             */
            String input = scanner.nextLine().toLowerCase().trim();

            // Condição de parada (Breakout clause)
            if (input.equals("sair")) {
                System.out.println("Desconectando dos servidores Ultra...");
                break;
            }

            /* * Método getOrDefault(): 
             * Busca a chave no HashMap. Se encontrar, retorna a resposta.
             * Se NÃO encontrar (ex: o usuário digitou algo desconhecido), 
             * retorna a mensagem padrão de erro.
             */
            String resposta = conhecimento.getOrDefault(input, 
                "Essa informação não consta nos meus arquivos criptografados.");

            // Exibe o resultado da "decifração" para o usuário
            System.out.println(resposta);
        }

        // Importante: Fechar o scanner para liberar os recursos do sistema operacional
        scanner.close();
    }
}
