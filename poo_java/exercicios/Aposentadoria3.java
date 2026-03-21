import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Aposentadoria3 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de Aposentadoria");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));

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

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        JTextArea resultadoArea = new JTextArea(3, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        // 🔘 BOTÃO CALCULAR
        btnCalcular.addActionListener((ActionEvent e) -> {
            try {

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

                int faltante = 0; // inicializa

                String resultado;

                if (sexo == 'F') {
                    if (idade >= 60 || contribuicao >= 30) {
                        resultado = "Aposentado";
                        resultadoArea.setText("Você já tem direito à aposentadoria.");
                    } else {
                        faltante = 30 - contribuicao;
                        resultado = "Não aposentado";
                        resultadoArea.setText("Faltam " + faltante + " anos de contribuição.");
                    }

                } else if (sexo == 'M') {
                    if (idade >= 65 || contribuicao >= 35) {
                        resultado = "Aposentado";
                        resultadoArea.setText("Você já tem direito à aposentadoria.");
                    } else {
                        faltante = 35 - contribuicao;
                        resultado = "Não aposentado";
                        resultadoArea.setText("Faltam " + faltante + " anos de contribuição.");
                    }

                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Sexo inválido! Use M ou F.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //  Invoca o método CSV
                salvarCSV(idade, sexo, contribuicao, faltante, resultado);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpar.addActionListener(e -> {
            campoIdade.setText("");
            campoSexo.setText("");
            campoContribuicao.setText("");
            resultadoArea.setText("");
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    // MÉTODO CSV
    private static void salvarCSV(int idade, char sexo, int contribuicao, int faltante, String resultado) {

        try {
            File file = new File("aposentadoria.csv");

            boolean novoArquivo = !file.exists();

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);

            if (novoArquivo) {
                bw.write("Idade,Sexo,Contribuicao,Faltante,Resultado");
                bw.newLine();
            }

            bw.write(idade + "," + sexo + "," + contribuicao + "," + faltante + "," + resultado);
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
