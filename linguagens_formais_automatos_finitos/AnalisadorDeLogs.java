import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta classe demonstra como usar a API de Expressões Regulares (Regex) do Java
 * para extrair informações estruturadas de linhas de log, que são textos
 * geralmente não estruturados. O uso de grupos de captura nomeados torna o código
 * muito mais legível e fácil de manter.
 */
public class AnalisadorDeLogs {

    public static void main(String[] args) {
        // 1. As linhas de log que vamos analisar. Elas seguem um padrão consistente.
        String[] logs = {
            "2025-08-29 10:30:15 INFO  [main] [TransacaoId: 12345] Usuário 'joao.silva' acessou a página de perfil.",
            "2025-08-29 10:31:22 ERROR [http-8080] [TransacaoId: 67890] Falha ao conectar ao banco de dados: tempo limite excedido.",
            "2025-08-29 10:32:01 INFO  [thread-pool-1] [TransacaoId: 11223] Pedido #54321 processado com sucesso.",
            "2025-08-29 10:35:50 DEBUG [worker-thread] [TransacaoId: 99887] Operação de cache concluída."
        };

        // 2. A Expressão Regular (Regex) com grupos de captura nomeados.
        // Cada parte entre parênteses "(?<nome>...)" cria um grupo de captura
        // que podemos referenciar pelo nome, em vez de um índice numérico.
        String logRegex = "(?<data>\\d{4}-\\d{2}-\\d{2})\\s+" +
                          "(?<hora>\\d{2}:\\d{2}:\\d{2})\\s+" +
                          "(?<nivel>\\w+)\\s+" +
                          "\\[(?<threadName>[^\\]]+)\\]\\s+" +
                          "\\[TransacaoId:\\s+(?<transacaoId>\\d+)\\]\\s+" +
                          "(?<mensagem>.*)";

        /*
         * Detalhando a regex de captura:
         *
         * (?<data>\\d{4}-\\d{2}-\\d{2})      ->  Grupo "data": captura 4 dígitos, um traço, 2 dígitos, um traço e 2 dígitos.
         * \\s+                                ->  Corresponde a um ou mais espaços em branco.
         * (?<hora>\\d{2}:\\d{2}:\\d{2})      ->  Grupo "hora": captura a hora no formato HH:MM:SS.
         * \\s+                                ->  Mais espaços em branco.
         * (?<nivel>\\w+)                     ->  Grupo "nivel": captura uma ou mais "palavras" (letras, números, _),
         * como INFO, ERROR, DEBUG.
         * \\s+                                ->  Mais espaços.
         * \\[(?<threadName>[^\\]]+)\\]       ->  Grupo "threadName": os colchetes "\[" e "\]" são literais e
         * precisam ser escapados. A parte "[^\\]]+" significa: "corresponda a um ou
         * mais caracteres que NÃO SEJAM um colchete de fechamento ']'.
         * \\s+                                ->  Mais espaços.
         * \\[(?<transacaoId>\\d+)\\]         ->  Grupo "transacaoId": encontra o padrão literal "[TransacaoId: " e captura
         * um ou mais dígitos.
         * \\s+                                ->  Mais espaços.
         * (?<mensagem>.*)                    ->  Grupo "mensagem": o ponto "." significa qualquer caractere, e o asterisco "*"
         * significa "zero ou mais vezes". Isso captura todo o resto da linha.
         */

        // 3. Compila a regex em um objeto Pattern para otimizar o desempenho.
        // A compilação é um passo importante que transforma a regex em uma máquina de estados eficiente.
        Pattern pattern = Pattern.compile(logRegex);

        System.out.println("Extraindo informações dos logs:");
        System.out.println("---------------------------------------------------------------------------------");
        
        // 4. Itera sobre cada linha de log para processá-la.
        for (String log : logs) {
            // Cria um objeto Matcher para a linha de log atual. O Matcher é a "máquina" que
            // fará a busca de correspondência com o padrão.
            Matcher matcher = pattern.matcher(log);

            // O método find() tenta encontrar a próxima correspondência para o padrão na string.
            // Se encontrar, o método retorna 'true' e os grupos de captura podem ser acessados.
            if (matcher.find()) {
                // 5. Recupera o conteúdo de cada grupo de captura usando o nome do grupo.
                String data = matcher.group("data");
                String hora = matcher.group("hora");
                String nivel = matcher.group("nivel");
                String threadName = matcher.group("threadName");
                String transacaoId = matcher.group("transacaoId");
                String mensagem = matcher.group("mensagem");

                // 6. Imprime as informações extraídas de forma formatada.
                System.out.printf("Data: %s, Hora: %s, Nível: %s, Thread: %s, ID Transação: %s, Mensagem: %s%n",
                                  data, hora, nivel, threadName, transacaoId, mensagem);
            } else {
                // Esta parte do código lida com linhas que não correspondem ao padrão definido,
                // o que pode ser útil para identificar linhas de log com formato inesperado.
                System.out.printf("Linha de log não corresponde ao padrão: %s%n", log);
            }
        }
    }
}
