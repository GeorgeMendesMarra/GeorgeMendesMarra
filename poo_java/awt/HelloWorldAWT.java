import java.awt.*;

public class HelloWorldAWT {

    public static void main(String[] args) {

        // Cria uma nova janela (Frame)
        Frame frame = new Frame("Hello World AWT");

        // Cria um rótulo (Label) com o texto "Hello World!"
        Label label = new Label("Hello World!");

        // Adiciona o rótulo ao centro da janela
        frame.add(label, BorderLayout.CENTER);

        // Define o tamanho da janela
        frame.setSize(300, 200);

        // Torna a janela visível
        frame.setVisible(true);

    }

}
