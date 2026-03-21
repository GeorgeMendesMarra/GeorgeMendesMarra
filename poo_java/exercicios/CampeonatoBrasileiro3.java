import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CampeonatoBrasileiro3 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Campeonato Brasileiro");
        frame.setSize(420, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JTextField campoLider = new JTextField();
        JTextField campoLanterna = new JTextField();

        formPanel.add(new JLabel("Pontos do Líder:"));
        formPanel.add(campoLider);

        formPanel.add(new JLabel("Pontos do Lanterna:"));
        formPanel.add(campoLanterna);

        JTextArea resultadoArea = new JTextArea(3, 30);
        resultadoArea.setEditable(false);
        resultadoArea.setBorder(BorderFactory.createTitledBorder("Resultado"));

        JPanel buttonPanel = new JPanel();

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSair = new JButton("Sair");

        buttonPanel.add(btnCalcular);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnSair);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(resultadoArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        //  BOTÃO CALCULAR
        btnCalcular.addActionListener(e -> {
            try {

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

                int vitorias = (int) Math.ceil(diferenca / 3.0);

                resultadoArea.setText(
                        "Diferença de pontos: " + diferenca +
                        "\nVitórias necessárias: " + vitorias
                );

                // Invoca método SALVAR CSV
                salvarCSV(lider, lanterna, diferenca, vitorias);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Digite apenas números inteiros válidos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpar.addActionListener(e -> {
            campoLider.setText("");
            campoLanterna.setText("");
            resultadoArea.setText("");
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    // MÉTODO CSV
    private static void salvarCSV(int lider, int lanterna, int diferenca, int vitorias) {

        try {
            File file = new File("campeonato_brasileiro.csv");

            boolean novoArquivo = !file.exists();

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer);

            if (novoArquivo) {
                bw.write("Lider,Lanterna,Diferenca,Vitorias");
                bw.newLine();
            }

            bw.write(lider + "," + lanterna + "," + diferenca + "," + vitorias);
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
