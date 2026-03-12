// Importa a classe JOptionPane para criar caixas de diálogo
import javax.swing.JOptionPane;

// Declaração da classe principal
public class MediaAluno {

    // Método principal onde o programa começa a execução
    public static void main(String[] args) {

        // Declaração das variáveis para armazenar as notas
        double notaProva1;
        double notaProva2;
        double notaTrabalho;
        double media;

        // Solicita ao usuário a nota da primeira prova
        notaProva1 = Double.parseDouble(
                JOptionPane.showInputDialog("Digite a nota da Prova 1:")
        );

        // Solicita ao usuário a nota da segunda prova
        notaProva2 = Double.parseDouble(
                JOptionPane.showInputDialog("Digite a nota da Prova 2:")
        );

        // Solicita ao usuário a nota do trabalho
        notaTrabalho = Double.parseDouble(
                JOptionPane.showInputDialog("Digite a nota do Trabalho:")
        );

        // Calcula a média das três notas
        // Fórmula: média = (prova1 + prova2 + trabalho) / 3
        media = (notaProva1 + notaProva2 + notaTrabalho) / 3;

        // Declara uma variável para armazenar o resultado final
        String resultado;

        // Verifica se o aluno foi aprovado ou reprovado
        if (media >= 6) {
            resultado = "Aprovado";
        } else {
            resultado = "Reprovado";
        }

        // Exibe a média e o resultado final em uma caixa de diálogo
        JOptionPane.showMessageDialog(null,
                "Média do aluno: " + media +
                "\nResultado final: " + resultado);

        // Encerra o programa
        System.exit(0);
    }
}
