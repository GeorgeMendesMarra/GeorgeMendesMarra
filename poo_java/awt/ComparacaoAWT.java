import java.awt.*;
import java.awt.event.*;

public class ComparacaoAWT extends Frame implements ActionListener {

    // Componentes da interface
    Label label1, label2;
    TextField campoNumero1, campoNumero2;
    TextArea resultado;
    Button comparar;

    // Construtor da interface
    public ComparacaoAWT() {
        setLayout(new FlowLayout()); // Define o layout da janela como fluxo (um após o outro)

        // Rótulo e campo para o primeiro número
        label1 = new Label("Digite o primeiro número:");
        add(label1);
        campoNumero1 = new TextField(10); // Campo de texto com 10 colunas
        add(campoNumero1);

        // Rótulo e campo para o segundo número
        label2 = new Label("Digite o segundo número:");
        add(label2);
        campoNumero2 = new TextField(10);
        add(campoNumero2);

        // Botão de comparação
        comparar = new Button("Comparar");
        comparar.addActionListener(this); // Registra o evento de clique no botão
        add(comparar);

        // Área de texto para mostrar o resultado
        resultado = new TextArea(6, 30); // 6 linhas por 30 colunas
        resultado.setEditable(false); // Impede edição manual do usuário
        add(resultado);

        // Configurações da janela principal
        setTitle("Comparação de Números");
        setSize(350, 250); // Largura x Altura
        setVisible(true); // Torna a janela visível

        // Evento para fechar a janela corretamente
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); // Libera os recursos e fecha a janela
            }
        });
    }

    // Método que trata o clique no botão
    public void actionPerformed(ActionEvent e) {
        try {
            // Lê os valores dos campos de texto e converte para inteiros
            int numero1 = Integer.parseInt(campoNumero1.getText());
            int numero2 = Integer.parseInt(campoNumero2.getText());

            StringBuilder sb = new StringBuilder(); // Armazena os resultados

            // Compara os números e adiciona os resultados
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

            // Exibe os resultados na área de texto
            resultado.setText(sb.toString());
        } catch (NumberFormatException ex) {
            // Mostra mensagem de erro se os dados não forem números válidos
            resultado.setText("Por favor, insira números válidos.");
        }
    }

    // Método principal: ponto de entrada do programa
    public static void main(String[] args) {
        new ComparacaoAWT(); // Cria e mostra a janela da aplicação
    }
}

