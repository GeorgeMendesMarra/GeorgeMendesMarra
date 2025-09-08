import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CliqueMe {

    public static void main(String[] args) {
        // 1. Cria a janela (JFrame), o equivalente ao Frame no AWT
        JFrame frame = new JFrame("Exemplo Simples de Swing");
        
        // 2. Define a operação padrão de fechamento
        // EXIT_ON_CLOSE garante que o programa encerre quando a janela for fechada.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Cria um componente: o botão
        JButton botao = new JButton("Clique-me!");

        // 4. Adiciona um "ouvinte" de eventos ao botão
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "O botão foi clicado!");
            }
        });

        // 5. Adiciona o botão à janela
        frame.add(botao);

        // 6. Define o layout da janela
        frame.setLayout(new FlowLayout());

        // 7. Ajusta o tamanho da janela para caber nos componentes
        // O pack() é uma forma eficiente de definir o tamanho, ajustando-o
        // automaticamente ao conteúdo.
        frame.pack();

        // 8. Torna a janela visível
        frame.setVisible(true);
    }
}
