import javax.swing.*;
import java.awt.*;

public class CampeonatoBrasileiro2 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Campeonato Brasileiro");
        frame.setSize(420, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JTextField campoLider = new JTextField();
        JTextField campoLanterna = new JTextField();

        formPanel.add(new JLabel("Pontos do Líder:"));
        formPanel.add(campoLider);

        formPanel.add(new JLabel("Pontos do Lanterna:"));
        formPanel.add(campoLanterna);

        // Área de resultado
        JTextArea resultadoArea = new JTextArea(3, 30);
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
                if (campoLider.getText().isEmpty() ||
                    campoLanterna.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int lider = Integer.parseInt(campoLider.getText());
                int lanterna = Integer.parseInt(campoLanterna.getText());

                int diferenca = lider - lanterna;

                // Ajuste correto: arredondar para cima
                int vitorias = (int) Math.ceil(diferenca / 3.0);

                resultadoArea.setText(
                        "Diferença de pontos: " + diferenca +
                        "\nVitórias necessárias: " + vitorias
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números inteiros válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // BOTÃO LIMPAR
        btnLimpar.addActionListener(e -> {
            campoLider.setText("");
            campoLanterna.setText("");
            resultadoArea.setText("");
        });

        // BOTÃO SAIR
        btnSair.addActionListener(e -> System.exit(0));
    }
}
