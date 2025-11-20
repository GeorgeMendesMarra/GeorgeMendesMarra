import javax.swing.JOptionPane; // Importa a classe JOptionPane para usar janelas de diálogo (GUI)

/**
 * Classe para calcular a média de um aluno com base em duas provas e um trabalho,
 * utilizando JOptionPane para entrada e saída de dados.
 * A média é calculada como a soma das 3 notas dividida por 3.
 */
public class CalculadoraMedia {

    public static void main(String[] args) {
        // Constante para a média mínima necessária para que o aluno seja APROVADO
        final double MEDIA_MINIMA = 6.0;
        
        // Declaração das variáveis que irão armazenar as notas e o resultado
        double notaProva1;
        double notaProva2;
        double notaTrabalho;
        double media;
        String resultadoFinal;

        // ----------------------------------------------------------------------
        // --- INÍCIO DA ENTRADA DE DADOS COM JOptionPane ---
        // ----------------------------------------------------------------------

        // 1. Receber a nota da Prova 1
        String inputP1 = JOptionPane.showInputDialog(
            null, // Componente pai (null para centralizar na tela)
            "Digite a nota da Prova 1:", // Mensagem para o usuário
            "Entrada de Notas", // Título da janela
            JOptionPane.QUESTION_MESSAGE // Ícone de pergunta
        );
        
        // Converte a String de entrada para Double, com tratamento de exceção (try-catch)
        try {
            notaProva1 = Double.parseDouble(inputP1); // Tenta converter a entrada
        } catch (NumberFormatException e) {
            // Se a conversão falhar (ex: usuário digitou texto ou cancelou), exibe um erro
            JOptionPane.showMessageDialog(null, "Nota da Prova 1 inválida. Encerrando o programa.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return; // Encerra a execução do método main
        }


        // 2. Receber a nota da Prova 2
        String inputP2 = JOptionPane.showInputDialog(
            null, 
            "Digite a nota da Prova 2:", 
            "Entrada de Notas", 
            JOptionPane.QUESTION_MESSAGE
        );
        try {
            notaProva2 = Double.parseDouble(inputP2);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nota da Prova 2 inválida. Encerrando o programa.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return; // Encerra a execução do método main
        }

        // 3. Receber a nota do Trabalho
        String inputT = JOptionPane.showInputDialog(
            null, 
            "Digite a nota do Trabalho:", 
            "Entrada de Notas", 
            JOptionPane.QUESTION_MESSAGE
        );
        try {
            notaTrabalho = Double.parseDouble(inputT);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nota do Trabalho inválida. Encerrando o programa.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return; // Encerra a execução do método main
        }

        // ----------------------------------------------------------------------
        // --- PROCESSAMENTO (Cálculo da Média) ---
        // ----------------------------------------------------------------------

        // Fórmula de cálculo: média = (nota da prova 1 + nota da prova 2 + nota do trabalho) / 3
        media = (notaProva1 + notaProva2 + notaTrabalho) / 3.0; // Usa 3.0 para garantir a divisão double

        // ----------------------------------------------------------------------
        // --- VERIFICAÇÃO DO RESULTADO FINAL ---
        // ----------------------------------------------------------------------

        // Verifica se a média atingiu ou ultrapassou a média mínima (6.0)
        if (media >= MEDIA_MINIMA) {
            resultadoFinal = "APROVADO";
        } else {
            resultadoFinal = "REPROVADO";
        }

        // ----------------------------------------------------------------------
        // --- SAÍDA DE DADOS COM JOptionPane ---
        // ----------------------------------------------------------------------
        
        // Formata a média para ter no máximo duas casas decimais (ex: 7.66)
        String mediaFormatada = String.format("%.2f", media);

        // Monta a mensagem final que será exibida ao usuário
        String mensagem = "Notas Lançadas:\n"
                        + "Prova 1: " + notaProva1 + "\n"
                        + "Prova 2: " + notaProva2 + "\n"
                        + "Trabalho: " + notaTrabalho + "\n"
                        + "--------------------------------\n"
                        + "Média Final: " + mediaFormatada + "\n" // Exibe a média formatada
                        + "Resultado: " + resultadoFinal; // Exibe o resultado (APROVADO/REPROVADO)
        
        // Exibe o resultado final em uma caixa de diálogo informativa
        JOptionPane.showMessageDialog(
            null, // Componente pai (centralizado)
            mensagem, // Conteúdo da mensagem
            "Resultado da Média", // Título da janela
            JOptionPane.INFORMATION_MESSAGE // Ícone de informação
        );
    }
}
