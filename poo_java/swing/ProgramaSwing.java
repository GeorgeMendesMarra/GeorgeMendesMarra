import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProgramaSwing {
    public static void main(String[] args) {
        // Cria a janela
        JFrame frame = new JFrame("Programa Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Cria um painel
        JPanel panel = new JPanel();
        frame.add(panel);

        // Cria um botão
        JButton botao = new JButton("Clique-me");
        panel.add(botao);

        // Adiciona um ouvinte de ação ao botão
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botão clicado! Mensagem exibida.");
            }
        });

        // Define a visibilidade da janela como verdadeira
        frame.setVisible(true);
    }
}
