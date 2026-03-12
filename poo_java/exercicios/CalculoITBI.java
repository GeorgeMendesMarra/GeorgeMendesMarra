// Importa a classe JOptionPane para criar caixas de diálogo
import javax.swing.JOptionPane;

// Declaração da classe principal
public class CalculoITBI {

    // Método principal onde o programa inicia
    public static void main(String[] args) {

        // Declaração das variáveis
        double valorTransacao;
        double valorVenal;
        double percentualImposto;
        double maiorValor;
        double valorImposto;

        // Solicita ao usuário o valor da transação do imóvel
        valorTransacao = Double.parseDouble(
                JOptionPane.showInputDialog("Digite o valor da transação do imóvel:")
        );

        // Solicita ao usuário o valor venal de referência
        valorVenal = Double.parseDouble(
                JOptionPane.showInputDialog("Digite o valor venal de referência:")
        );

        // Solicita ao usuário o percentual do imposto
        percentualImposto = Double.parseDouble(
                JOptionPane.showInputDialog("Digite o percentual do ITBI (%):")
        );

        // Verifica qual é o maior valor entre o valor da transação e o valor venal
        if (valorTransacao > valorVenal) {
            maiorValor = valorTransacao;
        } else {
            maiorValor = valorVenal;
        }

        // Calcula o valor do imposto usando a fórmula:
        // imposto = maiorValor * percentual / 100
        valorImposto = maiorValor * percentualImposto / 100;

        // Exibe o resultado em uma caixa de diálogo
        JOptionPane.showMessageDialog(null,
                "Maior valor considerado: R$ " + maiorValor +
                "\nPercentual do imposto: " + percentualImposto + "%" +
                "\nValor do ITBI a pagar: R$ " + valorImposto);

        // Encerra o programa
        System.exit(0);
    }
}
