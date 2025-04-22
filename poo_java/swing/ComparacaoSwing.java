import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComparacaoSwing extends JFrame implements ActionListener {

    // Componentes da interface
    private JTextField campoNumero1, campoNumero2;
    private JTextArea resultado;
    private JButton botaoComparar;

    // Construtor da interface
    public ComparacaoSwing() {
        setTitle("Comparação de Números"); // Título da janela
        setSize(400, 300); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao clicar no X

        // Painel principal com layout em grade
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 linhas, 1 coluna, espaçamento de 10px

        // Linha 1: Campo para o primeiro número
        JPanel linha1 = new JPanel(new FlowLayout());
        linha1.add(new JLabel("Digite o primeiro número:"));
        campoNumero1 = new JTextField(10);
        linha1.add(campoNumero1);
        painel.add(linha1);

        // Linha 2: Campo para o segundo número
        JPanel linha2 = new JPanel(new FlowLayout());
        linha2.add(new JLabel("Digite o segundo número:"));
        campoNumero2 = new JTextField(10);
        linha2.add(campoNumero2);
        painel.add(linha2);

        // Linha 3: Botão de comparar
        JPanel linha3 = new JPanel(new FlowLayout());
        botaoComparar = new JButton("Comparar");
        botaoComparar.addActionListener(this); // Adiciona evento ao botão
        linha3.add(botaoComparar);
        painel.add(linha3);

        // Linha 4: Área de resultado
        JPanel linha4 = new JPanel(new BorderLayout());
        resultado = new JTextArea(6, 30);
        resultado.setEditable(false); // Não permite edição manual
        linha4.add(new JScrollPane(resultado), BorderLayout.CENTER);
        painel.add(linha4);

        // Adiciona o painel principal à janela
        add(painel);

        // Torna a janela visível
        setVisible(true);
    }

    // Ação executada ao clicar no botão
    public void actionPerformed(ActionEvent e) {
        try {
            // Lê os números inseridos nos campos de texto
            int numero1 = Integer.parseInt(campoNumero1.getText());
            int numero2 = Integer.parseInt(campoNumero2.getText());

            // Usa StringBuilder para montar o resultado
            StringBuilder sb = new StringBuilder();

            if (numero1 == numero2)
                sb.append(numero1 + " == " + numero2 + "\n");
            if (numero1 != numero2)
                sb.append(numero1 + " != " + numero2 + "\n");
            if (numero1 < numero2)
                sb.append(numero1 + " < " + numero2 + "\n");
            if (numero1 > numero2)
                sb.append(numero1 + " > " + numero2 + "\n");
            if (numero1 <= numero2)
                sb.append(numero1 + " <= " + numero2 + "\n");
            if (numero1 >= numero2)
                sb.append(numero1 + " >= " + numero2 + "\n");

            // Exibe o resultado na área de texto
            resultado.setText(sb.toString());

        } catch (NumberFormatException ex) {
            // Mostra erro caso o usuário insira algo que não seja número
            resultado.setText("Por favor, insira apenas números inteiros.");
        }
    }

    // Método principal - ponto de partida
    public static void main(String[] args) {
        // Cria a janela principal
        new ComparacaoSwing();
    }
}

