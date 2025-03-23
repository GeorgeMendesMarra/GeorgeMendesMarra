import java.awt.*; // Importa as classes do AWT para criar interfaces gráficas
import java.awt.event.*; // Importa as classes de eventos para lidar com interações do usuário

public class ProgramaAWT extends Frame implements ActionListener {
    private Button botao; // Declara um botão como variável de instância

    public ProgramaAWT() {
        // Configurações da janela
        setTitle("Programa AWT"); // Define o título da janela
        setSize(300, 200); // Define o tamanho da janela (largura e altura)
        setLayout(new FlowLayout()); // Define o layout da janela como FlowLayout (organiza os componentes em fluxo)

        // Cria um botão
        botao = new Button("Clique-me"); // Cria um novo botão com o texto "Clique-me"
        add(botao); // Adiciona o botão à janela

        // Adiciona um ouvinte de ação ao botão
        botao.addActionListener(this); // Registra esta classe como ouvinte de ação para o botão

        // Fecha a janela quando o usuário clicar no botão Fechar
        addWindowListener(new WindowAdapter() { // Adiciona um ouvinte de eventos de janela
            public void windowClosing(WindowEvent we) { // Método chamado quando a janela está fechando
                System.exit(0); // Encerra o programa
            }
        });
    }

    // Método chamado quando um evento de ação ocorre
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == botao) { // Verifica se o evento veio do botão
            System.out.println("Botão clicado! Mensagem exibida."); // Imprime uma mensagem no console
        }
    }

    public static void main(String[] args) {
        ProgramaAWT programa = new ProgramaAWT(); // Cria uma nova instância da classe ProgramaAWT
        programa.setVisible(true); // Torna a janela visível
    }
}
