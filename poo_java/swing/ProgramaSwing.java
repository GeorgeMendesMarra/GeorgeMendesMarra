import javax.swing.*; // Importa as classes do Swing para criar interfaces gráficas
import java.awt.*; // Importa as classes do AWT (usado pelo Swing)
import java.awt.event.*; // Importa as classes de eventos para lidar com interações do usuário

public class ProgramaSwing {
    public static void main(String[] args) {
        // Cria a janela
        JFrame frame = new JFrame("Programa Swing"); // Cria uma nova janela com o título "Programa Swing"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação padrão ao fechar a janela (encerrar o programa)
        frame.setSize(300, 200); // Define o tamanho da janela (largura e altura)

        // Cria um painel
        JPanel panel = new JPanel(); // Cria um novo painel para organizar os componentes
        frame.add(panel); // Adiciona o painel à janela

        // Cria um botão
        JButton botao = new JButton("Clique-me"); // Cria um novo botão com o texto "Clique-me"
        panel.add(botao); // Adiciona o botão ao painel

        // Adiciona um ouvinte de ação ao botão
        botao.addActionListener(new ActionListener() { // Adiciona um ouvinte de eventos de ação ao botão
            public void actionPerformed(ActionEvent e) { // Método chamado quando o botão é clicado
                JOptionPane.showMessageDialog(null, "Botão clicado! Mensagem exibida."); // Exibe uma caixa de diálogo com a mensagem
            }
        });

        // Define a visibilidade da janela como verdadeira
        frame.setVisible(true); // Torna a janela visível na tela
    }
}
