import java.awt.*;
import java.awt.event.*;

public class ProgramaAWT extends Frame implements ActionListener {
    private Button botao;
    
    public ProgramaAWT() {
        // Configurações da janela
        setTitle("Programa AWT");
        setSize(300, 200);
        setLayout(new FlowLayout());

        // Cria um botão
        botao = new Button("Clique-me");
        add(botao);

        // Adiciona um ouvinte de ação ao botão
        botao.addActionListener(this);

        // Fecha a janela quando o usuário clicar no botão Fechar
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == botao) {
            System.out.println("Botão clicado! Mensagem exibida.");
        }
    }

    public static void main(String[] args) {
        ProgramaAWT programa = new ProgramaAWT();
        programa.setVisible(true);
    }
}
