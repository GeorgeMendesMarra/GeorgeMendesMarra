// Importa a classe JOptionPane para criar caixas de diálogo
import javax.swing.JOptionPane;

// Declaração da classe principal
public class Aposentadoria {

    // Método principal onde o programa inicia
    public static void main(String[] args) {

        // Declaração das variáveis
        int idade;
        int anosContribuicao;
        char sexo;
        int anosFaltantes;

        // Solicita ao usuário a idade
        idade = Integer.parseInt(JOptionPane.showInputDialog("Digite sua idade:"));

        // Solicita o sexo da pessoa (M para masculino ou F para feminino)
        sexo = JOptionPane.showInputDialog("Digite seu sexo (M ou F):").charAt(0);

        // Solicita o tempo de contribuição
        anosContribuicao = Integer.parseInt(
                JOptionPane.showInputDialog("Digite seus anos de contribuição:")
        );

        // Converte o sexo para maiúsculo para evitar erro de digitação
        sexo = Character.toUpperCase(sexo);

        // Estrutura condicional para verificar as regras de aposentadoria
        if (sexo == 'F') {

            // Verifica se a mulher já pode se aposentar por idade ou contribuição
            if (idade >= 60 || anosContribuicao >= 30) {
                JOptionPane.showMessageDialog(null,
                        "Você já tem o direito a aposentadoria.");
            } else {

                // Calcula quantos anos faltam de contribuição
                anosFaltantes = 30 - anosContribuicao;

                JOptionPane.showMessageDialog(null,
                        "Faltam " + anosFaltantes +
                        " anos de contribuição para aposentadoria.");
            }

        } else if (sexo == 'M') {

            // Verifica se o homem já pode se aposentar por idade ou contribuição
            if (idade >= 65 || anosContribuicao >= 35) {
                JOptionPane.showMessageDialog(null,
                        "Você já tem o direito a aposentadoria.");
            } else {

                // Calcula quantos anos faltam de contribuição
                anosFaltantes = 35 - anosContribuicao;

                JOptionPane.showMessageDialog(null,
                        "Faltam " + anosFaltantes +
                        " anos de contribuição para aposentadoria.");
            }

        } else {

            // Caso o usuário digite um sexo inválido
            JOptionPane.showMessageDialog(null,
                    "Sexo inválido. Digite apenas M ou F.");
        }

        // Encerra o programa
        System.exit(0);
    }
}
