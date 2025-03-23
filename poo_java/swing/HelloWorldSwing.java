import javax.swing.*; // Importa as classes do Swing para criar interfaces gráficas
import java.awt.*; // Importa as classes do AWT, usadas pelo Swing

public class HelloWorldSwing {

    public static void main(String[] args) {

        // Cria uma nova janela (JFrame)
        JFrame frame = new JFrame("Hello World Swing");

        // Define a operação padrão ao fechar a janela (encerrar o programa)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria um rótulo (JLabel) com o texto "Hello World!"
        JLabel label = new JLabel("Hello World!");

        // Adiciona o rótulo ao centro da janela
        frame.getContentPane().add(label, BorderLayout.CENTER);

        // Define o tamanho da janela
        frame.setSize(300, 200);

        // Torna a janela visível
        frame.setVisible(true);
    }
}
