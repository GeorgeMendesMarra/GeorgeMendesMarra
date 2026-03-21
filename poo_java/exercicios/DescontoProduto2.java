import javax.swing.*;
import java.awt.*;

public class DescontoProduto2 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Desconto de Produto");
        frame.setSize(420, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JTextField campoValor = new JTextField();
        JTextField campoPercentual = new JTextField();

        formPanel.add(new JLabel("Valor do Produto (R$):"));
        formPanel.add(campoValor);

        formPanel.add(new JLabel("Desconto (%):"));
        formPanel.add(campoPercentual);

        // Área de resultado
        JTextArea resultadoArea = new JTextArea(5, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));

        // Botões
        JPanel buttonPanel = new JPanel();

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        // Montagem
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // BOTÃO CALCULAR
        btnCalcular.addActionListener(e -> {
            try {

                // Validação
                if (campoValor.getText().isEmpty() ||
                    campoPercentual.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double valorProduto = Double.parseDouble(campoValor.getText());
                double percentual = Double.parseDouble(campoPercentual.getText());

                // Cálculos
                double desconto = valorProduto * percentual / 100;
                double valorFinal = valorProduto - desconto;

                // Resultado
                resultadoArea.setText(
                        "Valor do produto: R$ " + valorProduto +
                        "\nDesconto: " + percentual + "%" +
                        "\nValor do desconto: R$ " + desconto +
                        "\nValor final: R$ " + valorFinal
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        //  BOTÃO LIMPAR
        btnLimpar.addActionListener(e -> {
            campoValor.setText("");
            campoPercentual.setText("");
            resultadoArea.setText("");
        });

        // BOTÃO SAIR
        btnSair.addActionListener(e -> System.exit(0));
    }
}
