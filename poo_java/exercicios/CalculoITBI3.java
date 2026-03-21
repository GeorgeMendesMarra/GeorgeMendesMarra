import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CalculoITBI3 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Cálculo de ITBI");
        frame.setSize(420, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // 📋 Formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField campoTransacao = new JTextField();
        JTextField campoVenal = new JTextField();
        JTextField campoPercentual = new JTextField();

        formPanel.add(new JLabel("Valor da Transação:"));
        formPanel.add(campoTransacao);

        formPanel.add(new JLabel("Valor Venal:"));
        formPanel.add(campoVenal);

        formPanel.add(new JLabel("Percentual ITBI (%):"));
        formPanel.add(campoPercentual);

        // 📊 Área de resultado
        JTextArea resultadoArea = new JTextArea(4, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));

        // 🔘 Botões
        JPanel buttonPanel = new JPanel();

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        // Adiciona ao painel principal
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // 🔘 BOTÃO CALCULAR
        btnCalcular.addActionListener(e -> {
            try {

                // ✅ VALIDAÇÃO
                if (campoTransacao.getText().isEmpty() ||
                    campoVenal.getText().isEmpty() ||
                    campoPercentual.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double valorTransacao = Double.parseDouble(campoTransacao.getText());
                double valorVenal = Double.parseDouble(campoVenal.getText());
                double percentual = Double.parseDouble(campoPercentual.getText());

                // 🔍 Maior valor
                double maiorValor = Math.max(valorTransacao, valorVenal);

                // 💰 Cálculo do imposto
                double imposto = maiorValor * percentual / 100;

                // 📊 Resultado
                resultadoArea.setText(
                        "Maior valor considerado: R$ " + maiorValor +
                        "\nPercentual: " + percentual + "%" +
                        "\nITBI a pagar: R$ " + imposto
                );

    // Invoca método SALVAR CSV
                salvarCSV(valorTransacao, valorVenal, percentual, maiorValor, imposto);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // 🔘 BOTÃO LIMPAR
        btnLimpar.addActionListener(e -> {
            campoTransacao.setText("");
            campoVenal.setText("");
            campoPercentual.setText("");
            resultadoArea.setText("");
        });

        // 🔘 BOTÃO SAIR
        btnSair.addActionListener(e -> System.exit(0));
    }
 // MÉTODO CSV
    private static void salvarCSV(double valorTransacao, double valorVenal, double percentual, double maiorValor, double imposto) {

        try {
            File file = new File("calculo_itbi.csv");

            boolean novoArquivo = !file.exists();

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);

            if (novoArquivo) {
                bw.write("Valor Transacao,Valor Venal, Percentual, Maior Valor, Imposto");
                bw.newLine();
            }

            bw.write(valorTransacao + "," + valorVenal + "," + percentual + "," + maiorValor + "," + imposto);
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar no arquivo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
