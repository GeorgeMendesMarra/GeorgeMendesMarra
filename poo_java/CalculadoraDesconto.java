import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe para calcular o valor do desconto e o preço final de um produto.
 * Utiliza BufferedReader para receber a entrada de dados do usuário.
 */
public class CalculadoraDesconto {

    public static void main(String[] args) {
        // Cria um BufferedReader para ler a entrada do console (System.in)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double valorProduto = 0.0;
        double percentualDesconto = 0.0;
        
        // --- Leitura do Valor do Produto ---
        System.out.print("Digite o valor do produto (ex: 150.75): R$ ");
        try {
            // Tenta ler a linha e converter para double
            String linhaValor = reader.readLine();
            valorProduto = Double.parseDouble(linhaValor);
            
            // Validação simples para garantir que o valor não é negativo
            if (valorProduto < 0) {
                System.err.println("Erro: O valor do produto não pode ser negativo.");
                return; // Encerra a execução
            }

        } catch (IOException e) {
            // Captura erros de I/O (leitura)
            System.err.println("Erro de leitura ao tentar obter o valor do produto: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            // Captura erros se a entrada não for um número válido
            System.err.println("Erro: Entrada inválida para o valor do produto. Certifique-se de usar ponto (.) para decimais.");
            return;
        }
        
        // --- Leitura do Percentual de Desconto ---
        System.out.print("Digite o percentual de desconto (ex: 15.5 para 15.5%): ");
        try {
            // Tenta ler a linha e converter para double
            String linhaPercentual = reader.readLine();
            percentualDesconto = Double.parseDouble(linhaPercentual);

            // Validação simples para garantir que a porcentagem está em um intervalo razoável
            if (percentualDesconto < 0 || percentualDesconto > 100) {
                System.err.println("Atenção: O percentual de desconto deve estar entre 0 e 100.");
                // O código pode continuar, mas é bom alertar.
            }

        } catch (IOException e) {
            // Captura erros de I/O (leitura)
            System.err.println("Erro de leitura ao tentar obter o percentual de desconto: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            // Captura erros se a entrada não for um número válido
            System.err.println("Erro: Entrada inválida para o percentual de desconto. Certifique-se de usar ponto (.) para decimais.");
            return;
        }
        
        // --- Cálculo do Desconto ---
        
        // Fórmula: valor do desconto = valor do produto * percentual de desconto / 100
        double valorDesconto = valorProduto * percentualDesconto / 100.0;
        
        // Cálculo do valor final
        double valorFinal = valorProduto - valorDesconto;
        
        // --- Exibição dos Resultados ---
        
        System.out.println("\n--- Resultados do Cálculo ---");
        System.out.printf("Valor Original do Produto: R$ %.2f%n", valorProduto);
        System.out.printf("Percentual de Desconto: %.2f%%%n", percentualDesconto);
        System.out.println("---------------------------");
        System.out.printf("Valor do Desconto Aplicado: R$ %.2f%n", valorDesconto);
        System.out.printf("Valor do Produto com Desconto: R$ %.2f%n", valorFinal);
    }
}
