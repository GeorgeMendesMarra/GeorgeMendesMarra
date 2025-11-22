import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Estende Frame, que é a classe de janela de nível superior do AWT.
public class LogNeperianoAWT extends Frame implements ActionListener {

    // Componentes AWT equivalentes
    private TextField campoEntrada;
    private Label labelResultado;
    private Button botaoCalcular;

    public LogNeperianoAWT() {
        // Título da Janela
        super("Calculadora de Logaritmo Neperiano (ln) - AWT");

        // --- Configuração do Layout e Componentes ---
        
        // Usa GridLayout, o equivalente AWT ao do Swing, para organizar 3 linhas.
        setLayout(new GridLayout(3, 1, 10, 10));

        // Campo de entrada (TextField é o equivalente AWT de JTextField)
        campoEntrada = new TextField();
        // Nota: AWT tem suporte limitado para alteração de Fontes em comparação com Swing.
        campoEntrada.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        // Botão (Button é o equivalente AWT de JButton)
        botaoCalcular = new Button("Calcular ln");
        botaoCalcular.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        // Resultado (Label é o equivalente AWT de JLabel)
        labelResultado = new Label("Resultado aparecerá aqui.", Label.CENTER);
        labelResultado.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // --- Registro de Listeners e Ações ---
        
        // O AWT geralmente requer que a classe implemente ActionListener ou use uma classe interna anônima.
        // Aqui, a classe implementa ActionListener e se registra como o ouvinte do botão.
        botaoCalcular.addActionListener(this); 

        // Adiciona os componentes à janela (Frame)
        add(campoEntrada);
        add(botaoCalcular);
        add(labelResultado);

        // --- Configurações da Janela AWT ---
        
        setSize(400, 200);
        
        // O AWT não possui o método setDefaultCloseOperation. 
        // É necessário adicionar um WindowListener para tratar o evento de fechar a janela (X).
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do aplicativo quando a janela é fechada
                System.exit(0);
            }
        });
        
        // O método pack() do AWT calcula o tamanho preferido para os componentes,
        // mas setSize() foi usado para replicar o tamanho fixo do exemplo Swing.
        
        setVisible(true); // Torna a janela visível
        // Nota: Centralizar a janela requer código manual de posicionamento no AWT.
    }

    /**
     * Método actionPerformed da interface ActionListener.
     * É chamado sempre que o botão 'Calcular ln' é pressionado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica se o evento veio do nosso botão
        if (e.getSource() == botaoCalcular) {
            calcularLn();
        }
    }

    private void calcularLn() {
        try {
            // Obter texto do TextField
            String textoEntrada = campoEntrada.getText();
            double valor = Double.parseDouble(textoEntrada);

            if (valor <= 0) {
                labelResultado.setText("O número deve ser > 0 para calcular ln.");
                return;
            }

            // Cálculo do Logaritmo Neperiano (base e) usando Math.log()
            double resultado = Math.log(valor); 
            
            // Exibe o resultado no Label
            labelResultado.setText("ln(" + valor + ") = " + String.format("%.4f", resultado));

        } catch (NumberFormatException ex) {
            labelResultado.setText("Por favor, digite um número válido.");
        }
    }

    public static void main(String[] args) {
        // Cria a instância da nossa calculadora AWT na thread de eventos (implícito no AWT)
        new LogNeperianoAWT();
    }
}
