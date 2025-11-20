import javax.swing.JOptionPane; // Importa a classe JOptionPane, necessária para exibir janelas de diálogo (GUI)

/**
 * Classe para calcular a média de um aluno (P1, P2, Trabalho)
 * utilizando JOptionPane para entrada e saída de dados.
 * ATENÇÃO: Esta versão não possui tratamento de erro (try-catch),
 * assumindo que o usuário digitará números válidos.
 */
public class CalculadoraMediaSimples {

    public static void main(String[] args) {
        // Constante que define a média mínima necessária para aprovação (6.0, conforme o requisito)
        final double MEDIA_MINIMA = 6.0;
        
        // Declaração das variáveis do tipo double para armazenar os valores numéricos das notas
        double notaProva1;
        double notaProva2;
        double notaTrabalho;
        double media;
        // Variável String para armazenar o resultado final do aluno ("APROVADO" ou "REPROVADO")
        String resultadoFinal;

        // --- 1. Entrada de Dados com JOptionPane ---

        // Receber a nota da Prova 1 como String através de uma caixa de diálogo
        String inputP1 = JOptionPane.showInputDialog(
            null, 
            "Digite a nota da Prova 1:", 
            "Entrada de Notas", 
            JOptionPane.QUESTION_MESSAGE
        );
        // Converte a String de entrada para o tipo Double para que possa ser usada em cálculos
        notaProva1 = Double.parseDouble(inputP1);


        // Receber a nota da Prova 2 como String
        String inputP2 = JOptionPane.showInputDialog(
            null, 
            "Digite a nota da Prova 2:", 
            "Entrada de Notas", 
            JOptionPane.QUESTION_MESSAGE
        );
        // Converte a String para Double
        notaProva2 = Double.parseDouble(inputP2);

        // Receber a nota do Trabalho como String
        String inputT = JOptionPane.showInputDialog(
            null, 
            "Digite a nota do Trabalho:", 
            "Entrada de Notas", 
            JOptionPane.QUESTION_MESSAGE
        );
        // Converte a String para Double
        notaTrabalho = Double.parseDouble(inputT);

        // --- 2. Processamento (Cálculo da Média) ---

        // Fórmula: média = (nota da prova 1 + nota da prova 2 + nota do trabalho) / 3
        // É usado 3.0 para garantir que o resultado seja um número de ponto flutuante (double)
        media = (notaProva1 + notaProva2 + notaTrabalho) / 3.0;

        // --- 3. Verificação do Resultado Final ---

        // Estrutura de decisão (if/else) para determinar se o aluno foi APROVADO ou REPROVADO
        if (media >= MEDIA_MINIMA) {
            resultadoFinal = "APROVADO"; // Define o status como APROVADO
        } else {
            resultadoFinal = "REPROVADO"; // Define o status como REPROVADO
        }

        // --- 4. Saída de Dados com JOptionPane ---
        
        // Formata o valor da média para que tenha, no máximo, duas casas decimais, melhorando a visualização
        String mediaFormatada = String.format("%.2f", media);

        // Monta a String final com todas as informações para exibição
        String mensagem = "Notas Lançadas:\n"
                        + "Prova 1: " + notaProva1 + "\n"
                        + "Prova 2: " + notaProva2 + "\n"
                        + "Trabalho: " + notaTrabalho + "\n"
                        + "--------------------------------\n"
                        + "Média Final: " + mediaFormatada + "\n" // Média formatada
                        + "Resultado: " + resultadoFinal; // Status final do aluno
        
        // Exibe o resultado final em uma caixa de diálogo informativa
        JOptionPane.showMessageDialog(
            null, 
            mensagem, 
            "Resultado da Média", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
