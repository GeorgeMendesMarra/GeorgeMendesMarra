// Importa a classe BufferedReader para leitura de dados
import java.io.BufferedReader;

// Importa a classe InputStreamReader para ler dados do teclado
import java.io.InputStreamReader;

// Importa a classe IOException para tratar possíveis erros de entrada
import java.io.IOException;

// Declaração da classe principal
public class DescontoProduto {

    // Método principal onde o programa começa a executar
    public static void main(String[] args) {

        // Cria um objeto BufferedReader para ler dados digitados pelo usuário
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        try {

            // Solicita ao usuário o valor do produto
            System.out.print("Digite o valor do produto: ");

            // Lê o valor digitado e converte de String para double
            double valorProduto = Double.parseDouble(leitor.readLine());

            // Solicita ao usuário o percentual de desconto
            System.out.print("Digite o percentual de desconto (%): ");

            // Lê o valor digitado e converte para número decimal
            double percentualDesconto = Double.parseDouble(leitor.readLine());

            // Calcula o valor do desconto usando a fórmula
            // desconto = valorProduto * percentualDesconto / 100
            double valorDesconto = valorProduto * percentualDesconto / 100;

            // Calcula o valor final do produto após aplicar o desconto
            double valorFinal = valorProduto - valorDesconto;

            // Exibe os resultados na tela
            System.out.println("\n===== RESULTADO =====");
            System.out.println("Valor do produto: R$ " + valorProduto);
            System.out.println("Percentual de desconto: " + percentualDesconto + "%");
            System.out.println("Valor do desconto: R$ " + valorDesconto);
            System.out.println("Valor final com desconto: R$ " + valorFinal);

        } 
        // Caso ocorra algum erro na leitura dos dados
        catch (IOException e) {
            System.out.println("Erro na leitura dos dados.");
        }

    }
}
