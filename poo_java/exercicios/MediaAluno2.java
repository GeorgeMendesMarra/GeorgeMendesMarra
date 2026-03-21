import javax.swing.*;
import java.awt.*;

public class MediaAluno2 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Média do Aluno");
        frame.setSize(420, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // 📋 Formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField campoProva1 = new JTextField();
        JTextField campoProva2 = new JTextField();
        JTextField campoTrabalho = new JTextField();

        formPanel.add(new JLabel("Prova 1:"));
        formPanel.add(campoProva1);

        formPanel.add(new JLabel("Prova 2:"));
        formPanel.add(campoProva2);

        formPanel.add(new JLabel("Trabalho:"));
        formPanel.add(campoTrabalho);

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

        // Montagem
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // 🔘 BOTÃO CALCULAR
        btnCalcular.addActionListener(e -> {
            try {

                // ✅ Validação
                if (campoProva1.getText().isEmpty() ||
                    campoProva2.getText().isEmpty() ||
                    campoTrabalho.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double n1 = Double.parseDouble(campoProva1.getText());
                double n2 = Double.parseDouble(campoProva2.getText());
                double trab = Double.parseDouble(campoTrabalho.getText());

                // 🎯 Cálculo da média
                double media = (n1 + n2 + trab) / 3;

                String resultado = (media >= 6) ? "Aprovado" : "Reprovado";

                // 📊 Resultado
                resultadoArea.setText(
                        "Média: " + String.format("%.2f", media) +
                        "\nSituação: " + resultado
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // 🔘 BOTÃO LIMPAR
        btnLimpar.addActionListener(e -> {
            campoProva1.setText("");
            campoProva2.setText("");
            campoTrabalho.setText("");
            resultadoArea.setText("");
        });

        // 🔘 BOTÃO SAIR
        btnSair.addActionListener(e -> System.exit(0));
    }
}
