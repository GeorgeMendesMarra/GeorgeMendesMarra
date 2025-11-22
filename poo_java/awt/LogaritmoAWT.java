import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Lê um número fornecido pelo usuário e calcula logaritmos (ln, log10, log2)
 * usando a interface gráfica AWT.
 */
public class LogaritmoAWT extends Frame implements ActionListener {

    // Componentes AWT
    private TextField campoEntrada;
    private Label labelResultadoLn;
    private Label labelResultadoLog10;
    private Label labelResultadoLog2;
    private Button botaoCalcular;

    // Constante para Ln(2), usada no cálculo de log2 (base 2)
    private static final double LOG_2 = Math.log(2); 

    public LogaritmoAWT() {
        super("Calculadora de Logaritmos (AWT)");

        // Configuração do Layout: Grid de 5 linhas e 1 coluna
        setLayout(new GridLayout(6, 1, 10, 5)); // 5 Linhas para componentes + 1 para o título da entrada

        // --- Componentes ---

        // Título/Instrução
        add(new Label("Digite um número positivo:", Label.CENTER));
        
        // Campo de Entrada (AWT TextField)
        campoEntrada = new TextField();
        campoEntrada.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(campoEntrada);
        
        // Botão (AWT Button)
        botaoCalcular = new Button("Calcular Logaritmos");
        botaoCalcular.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoCalcular.addActionListener(this); // A própria classe lida com o evento
        add(botaoCalcular);

        // Labels de Resultado (AWT Label)
        labelResultadoLn = new Label("ln(x) = ", Label.LEFT);
        labelResultadoLog10 = new Label("log10(x) = ", Label.LEFT);
        labelResultadoLog2 = new Label("log2(x) = ", Label.LEFT);
        
        labelResultadoLn.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelResultadoLog10.setFont(new Font("Monospaced", Font.PLAIN, 14));
        labelResultadoLog2.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        add(labelResultadoLn);
        add(labelResultadoLog10);
        add(labelResultadoLog2);

        // --- Configurações da Janela ---

        setSize(350, 300);
        
        // Trata o evento de fechar a janela (necessário no AWT/Frame)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        setVisible(true);
    }

    /**
     * Implementa o método actionPerformed da interface ActionListener.
     * Chamado ao clicar no botão.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCalcular) {
            calcularLogaritmos();
        }
    }

    private void calcularLogaritmos() {
        try {
            double valor = Double.parseDouble(campoEntrada.getText());
            
            // Logaritmos só são definidos para valores estritamente positivos (x > 0)
            if (valor <= 0) {
                String erroMsg = "ERRO: Digite um número > 0";
                labelResultadoLn.setText(erroMsg);
                labelResultadoLog10.setText("");
                labelResultadoLog2.setText("");
                return;
            }

            // --- Cálculos usando a classe Math ---
            
            double ln = Math.log(valor);        // logaritmo natural (base e)
            double log10 = Math.log10(valor);   // logaritmo base 10
            
            // Mudança de base: log2(x) = ln(x) / ln(2)
            double log2 = ln / LOG_2;           

            // Atualiza os Labels com os resultados formatados
            String formatado = "%.6f";
            
            labelResultadoLn.setText("ln(" + valor + ") \t= " + String.format(formatado, ln));
            labelResultadoLog10.setText("log10(" + valor + ") \t= " + String.format(formatado, log10));
            labelResultadoLog2.setText("log2(" + valor + ") \t= " + String.format(formatado, log2));

        } catch (NumberFormatException ex) {
            labelResultadoLn.setText("ERRO: Entrada inválida.");
            labelResultadoLog10.setText("");
            labelResultadoLog2.setText("");
        }
    }

    public static void main(String[] args) {
        new LogaritmoAWT();
    }
}
