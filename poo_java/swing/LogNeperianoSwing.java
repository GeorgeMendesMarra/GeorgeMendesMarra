import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogNeperianoSwing extends JFrame {

    private JTextField campoEntrada;
    private JLabel labelResultado;

    public LogNeperianoSwing() {
        super("Calculadora de Logaritmo Neperiano (ln)");

        // Layout básico
        setLayout(new GridLayout(3, 1, 10, 10));

        // Campo de entrada
        campoEntrada = new JTextField();
        campoEntrada.setFont(new Font("Arial", Font.PLAIN, 18));

        // Botão
        JButton botaoCalcular = new JButton("Calcular ln");
        botaoCalcular.setFont(new Font("Arial", Font.BOLD, 18));

        // Resultado
        labelResultado = new JLabel("Resultado aparecerá aqui.", SwingConstants.CENTER);
        labelResultado.setFont(new Font("Arial", Font.PLAIN, 18));

        // Ação do botão
        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularLn();
            }
        });

        // Adiciona os componentes
        add(campoEntrada);
        add(botaoCalcular);
        add(labelResultado);

        // Configurações da janela
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setVisible(true);
    }

    private void calcularLn() {
        try {
            double valor = Double.parseDouble(campoEntrada.getText());
            if (valor <= 0) {
                labelResultado.setText("O número deve ser > 0 para calcular ln.");
                return;
            }

            double resultado = Math.log(valor);
            labelResultado.setText("ln(" + valor + ") = " + resultado);

        } catch (NumberFormatException ex) {
            labelResultado.setText("Por favor, digite um número válido.");
        }
    }

    public static void main(String[] args) {
        new LogNeperianoSwing();
    }
}
