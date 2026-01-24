import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AgroScraper {
    public static void main(String[] args) {
        try {
            // 1. Tente usar uma URL mais direta se a principal falhar
            String url = "https://www.noticiasagricolas.com.br/cotacoes/soja";

            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .referrer("http://www.google.com") // Simula que você veio do Google
                    .header("Accept-Language", "pt-BR,pt;q=0.9")
                    .timeout(15000)
                    .execute();

            Document doc = response.parse();
            System.out.println("Título da página: " + doc.title());

            // 2. Seletor Alternativo: Vamos buscar qualquer tabela que contenha 'Soja'
            // O site usa classes como 'cotacoes-dos-mercados'
            Elements tabelas = doc.select("table");
            
            boolean encontrou = false;
            for (Element tabela : tabelas) {
                if (tabela.text().contains("Soja") || tabela.className().contains("tablesorter")) {
                    Elements linhas = tabela.select("tbody tr");
                    for (Element linha : linhas) {
                        Elements colunas = linha.select("td");
                        if (colunas.size() >= 2) {
                            System.out.println("Local: " + colunas.get(0).text() + " | Valor: " + colunas.get(1).text());
                            encontrou = true;
                        }
                    }
                }
            }

            if (!encontrou) {
                // Se chegar aqui, o HTML veio sem as tabelas (bloqueio ou JS)
                System.out.println("Aviso: O conteúdo da tabela não estava no HTML recebido.");
            }

        } catch (Exception e) {
            System.out.println("Erro técnico: " + e.getMessage());
        }
    }
}
