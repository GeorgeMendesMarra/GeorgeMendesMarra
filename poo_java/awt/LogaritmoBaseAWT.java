import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Calculadora gráfica AWT que calcula logaritmo em qualquer base
 * usando a fórmula de mudança de base: log_b(x) = ln(x) / ln(b).
 */
public class LogaritmoBaseAWT extends Frame implements ActionListener {

    // Campos de entrada (AWT TextField)
    private TextField campoX;    // Para o valor x
    private TextField campoBase; // Para a base b
    
    private Label labelResultado;
    private Button botaoCalcular;

    public LogaritmoBaseAWT() {
        super("Calculadora de Logaritmo em Qualquer Base (AWT)");

        // Usa BorderLayout para a estrutura principal e um Panel para os campos de entrada.
        setLayout(new BorderLayout(10, 10));

        // --- Painel de Entradas (Grid de 2x2) ---
        Panel painelEntrada = new Panel(new GridLayout(2, 2, 10, 10));
        painelEntrada.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // Campo X (Valor)
        painelEntrada.add(new Label("Valor (x):", Label.RIGHT));
        campoX = new TextField("256"); // Valor inicial
        painelEntrada.add(campoX);
        
        // Campo Base (Base)
        painelEntrada.add(new Label("Base (b):", Label.RIGHT));
        campoBase = new TextField("2"); // Base inicial
        painelEntrada.add(campoBase);
        
        // --- Botão de Cálculo ---
        botaoCalcular = new Button("Calcular log_b(x)");
        botaoCalcular.setFont(new Font("SansSerif", Font.BOLD, 16));
        botaoCalcular.addActionListener(this);

        // --- Label de Resultado ---
        labelResultado = new Label("Resultado:", Label.CENTER);
        labelResultado.setFont(new Font("Monospaced", Font.BOLD, 16));
        
        // --- Adiciona componentes ao Frame principal ---
        add(painelEntrada, BorderLayout.NORTH);
        add(botaoCalcular, BorderLayout.CENTER);
        add(labelResultado, BorderLayout.SOUTH);

        // --- Configurações da Janela AWT ---
        setSize(400, 200);
        
        // Trata o evento de fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        setVisible(true);
    }

    /**
     * Retorna log_b(x) usando a fórmula de Mudança de Base: log_b(x) = ln(x) / ln(b).
     */
    public static double logBase(double x, double b) {
        // Math.log(x) é o logaritmo natural (ln) em Java
        return Math.log(x) / Math.log(b); 
    }

    /**
     * Implementa o método actionPerformed da interface ActionListener.
     * Chamado ao clicar no botão.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCalcular) {
            calcularLogaritmo();
        }
    }

    private void calcularLogaritmo() {
        try {
            double x = Double.parseDouble(campoX.getText());
            double b = Double.parseDouble(campoBase.getText());
            
            // --- Validações ---
            // 1. O valor x deve ser estritamente positivo (x > 0).
            if (x <= 0) {
                labelResultado.setText("ERRO: Valor (x) deve ser > 0.");
                return;
            }
            
            // 2. A base b deve ser estritamente positiva (b > 0).
            if (b <= 0) {
                 labelResultado.setText("ERRO: Base (b) deve ser > 0.");
                return;
            }
            
            // 3. A base b não pode ser 1 (log(1) não é permitido no denominador).
            if (b == 1) {
                labelResultado.setText("ERRO: Base (b) não pode ser 1.");
                return;
            }

            // --- Cálculo ---
            double resultado = logBase(x, b); 
            
            // Exibe o resultado.
            String saida = String.format("log%.0f(%.0f) = %.6f", b, x, resultado);
            labelResultado.setText(saida);

        } catch (NumberFormatException ex) {
            labelResultado.setText("ERRO: Digite números válidos em ambos os campos.");
        }
    }

    public static void main(String[] args) {
        new LogaritmoBaseAWT();
    }
}
