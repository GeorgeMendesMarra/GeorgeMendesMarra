import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
Como usar este arquivo no Linux/MAC?
javac -cp ".:jsoup-1.22.1.jar" AgroScraper.java
java -cp ".:jsoup-1.22.1.jar" AgroScraper
Como usar este arquivo no Windows?
javac -cp ".;jsoup-1.22.1.jar" AgroScraper.java
java -cp ".;jsoup-1.22.1.jar" AgroScraper
*/

public class AgroScraper {

    public static void main(String[] args) {
        // URL alvo: Portal de notícias agrícolas (Soja)
        String url = "https://www.noticiasagricolas.com.br/cotacoes/soja";

        try {
            // CONFIGURAÇÃO DA CONEXÃO
            // .userAgent: Simula um navegador real (Chrome) para evitar bloqueios de robôs simples
            // .referrer: Diz ao site que viemos do Google, aumentando a confiança da requisição
            // .timeout: Define um tempo máximo de espera (15 segundos) para conexões lentas
            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .referrer("http://www.google.com")
                    .timeout(15000)
                    .execute();

            // PARSE DO HTML
            // Converte o código bruto recebido em um objeto Document (DOM) manipulável
            Document doc = response.parse();
            System.out.println("Conectado a: " + doc.title());

            // SELEÇÃO DE DADOS (SELETOR CSS)
            // Procuramos por tabelas. O site usa classes como 'tablesorter' para dados de mercado.
            Elements tabelas = doc.select("table");

            boolean dadosEncontrados = false;

            // ITERAÇÃO NAS TABELAS
            // Percorremos todas as tabelas encontradas para achar a que contém dados de Soja
            for (Element tabela : tabelas) {
                // Filtro: Verifica se a tabela contém a palavra "Soja" no texto
                if (tabela.text().contains("Soja")) {
                    
                    // Seleciona as linhas (tr) dentro do corpo da tabela (tbody)
                    Elements linhas = tabela.select("tbody tr");

                    for (Element linha : linhas) {
                        // Seleciona as colunas (td) de cada linha
                        Elements colunas = linha.select("td");

                        // VALIDAÇÃO ANTI-ERRO (Evita NullPointerException)
                        // Só tenta ler se a linha tiver pelo menos 2 colunas (Local e Preço)
                        if (colunas.size() >= 2) {
                            String local = colunas.get(0).text(); // Primeira coluna (Cidade/Mercado)
                            String valor = colunas.get(1).text(); // Segunda coluna (Preço)

                            System.out.println("📍 Local: " + local + " | 💰 Valor: " + valor);
                            dadosEncontrados = true;
                        }
                    }
                }
            }

            // FEEDBACK CASO O SELETOR FALHE
            // Útil para identificar se o site mudou a estrutura ou se o conteúdo é dinâmico (JS)
            if (!dadosEncontrados) {
                System.out.println("⚠️ Alerta: Nenhuma tabela de cotação foi extraída. O site pode estar a usar JavaScript dinâmico.");
            }

        } catch (java.net.SocketTimeoutException e) {
            System.err.println("❌ Erro: O tempo de conexão expirou. A internet está lenta ou o site está fora do ar.");
        } catch (Exception e) {
            // Captura qualquer outro erro (Conexão, Parse, etc)
            System.err.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
