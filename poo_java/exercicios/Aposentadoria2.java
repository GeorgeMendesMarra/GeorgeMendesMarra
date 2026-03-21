import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Aposentadoria2 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Aposentadoria");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField campoIdade = new JTextField();
        JTextField campoSexo = new JTextField();
        JTextField campoContribuicao = new JTextField();

        formPanel.add(new JLabel("Idade:"));
        formPanel.add(campoIdade);

        formPanel.add(new JLabel("Sexo (M/F):"));
        formPanel.add(campoSexo);

        formPanel.add(new JLabel("Anos de contribuição:"));
        formPanel.add(campoContribuicao);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        // Área de resultado
        JTextArea resultadoArea = new JTextArea(3, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));

        // Adiciona tudo no painel principal
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // BOTÃO CALCULAR
        btnCalcular.addActionListener((ActionEvent e) -> {
            try {
                //  VALIDAÇÃO
                if (campoIdade.getText().isEmpty() ||
                    campoSexo.getText().isEmpty() ||
                    campoContribuicao.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(frame,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idade = Integer.parseInt(campoIdade.getText());
                char sexo = campoSexo.getText().toUpperCase().charAt(0);
                int contribuicao = Integer.parseInt(campoContribuicao.getText());

                int faltante;

                if (sexo == 'F') {
                    if (idade >= 60 || contribuicao >= 30) {
                        resultadoArea.setText("Você já tem direito à aposentadoria.");
                    } else {
                        faltante = 30 - contribuicao;
                        resultadoArea.setText("Faltam " + faltante + " anos de contribuição.");
                    }

                } else if (sexo == 'M') {
                    if (idade >= 65 || contribuicao >= 35) {
                        resultadoArea.setText("Você já tem direito à aposentadoria.");
                    } else {
                        faltante = 35 - contribuicao;
                        resultadoArea.setText("Faltam " + faltante + " anos de contribuição.");
                    }

                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Sexo inválido! Use M ou F.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // BOTÃO LIMPAR
        btnLimpar.addActionListener(e -> {
            campoIdade.setText("");
            campoSexo.setText("");
            campoContribuicao.setText("");
            resultadoArea.setText("");
        });

        //  BOTÃO SAIR
        btnSair.addActionListener(e -> System.exit(0));
    }
}
