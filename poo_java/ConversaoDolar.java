import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * 💱 Classe ConversaoDolar
 * <p>
 * Este programa solicita ao usuário um valor em reais (BRL) e a cotação atual do dólar (USD),
 * realiza a conversão e exibe o valor correspondente em dólares.
 * </p>
 * 
 * <p>
 * O cálculo é feito pela fórmula:
 * <pre>
 * valorEmDolares = valorEmReais / cotacaoDolar
 * </pre>
 * </p>
 * 
 * <p><b>Autor:</b> Professor George Mendes Marra</p>
 * <p><b>Disciplina:</b> Programação Orientada a Objetos</p>
 */
public class ConversaoDolar {

    /**
     * Método principal (main) — ponto de entrada da aplicação.
     * 
     * @param args Argumentos da linha de comando (não utilizados neste programa)
     */
    public static void main(String[] args) {

        // Cria um objeto Scanner para ler dados digitados pelo usuário
        Scanner input = new Scanner(System.in);

        // Cria um formatador para exibir valores com duas casas decimais
        DecimalFormat df = new DecimalFormat("0.00");

        // --- Entrada de dados ---
        System.out.print("Digite o valor em reais (R$): ");
        double valorReais = input.nextDouble(); // Lê o valor em reais

        System.out.print("Digite a cotação do dólar (ex: 5.65): ");
        double cotacaoDolar = input.nextDouble(); // Lê a cotação atual do dólar

        // --- Processamento ---
        // Converte o valor em reais para dólares
        double valorDolares = valorReais / cotacaoDolar;

        // --- Saída de dados ---
        System.out.println("\n💱 Conversão Real → Dólar 💱");
        System.out.println("Valor em Reais: R$ " + df.format(valorReais));
        System.out.println("Cotação do Dólar: R$ " + df.format(cotacaoDolar));
        System.out.println("Valor em Dólares: US$ " + df.format(valorDolares));

        // Fecha o objeto Scanner para liberar recursos do sistema
        input.close();
    }
}
