import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta classe demonstra como usar a API de Expressões Regulares (Regex) do Java
 * para encontrar e substituir padrões de texto em uma string.
 * O exemplo mostra a substituição de e-mails por um marcador.
 */
public class SubstituicaoComRegex {

    public static void main(String[] args) {

        // 1. A string de texto original que contém e-mails.
        String textoOriginal = "Entre em contato através do email suporte@empresa.com.br ou do email de vendas: vendas@negocios.com. Para mais informações, acesse o site.";

        // 2. A Expressão Regular (Regex) para encontrar e-mails.
        // A regex tem o objetivo de ser robusta o suficiente para a maioria dos casos simples.
        String regexEmail = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\\b";

        /*
         * Decifrando a regex acima:
         *
         * \\b                    ->  "Limite de palavra" (Word boundary). Garante que a correspondência
         * seja uma palavra inteira. Por exemplo, evita corresponder a parte "email" na palavra "emailing".
         *
         * [a-zA-Z0-9._%+-]+      ->  Corresponde a um ou mais caracteres do conjunto de letras,
         * números e símbolos permitidos no nome de usuário do e-mail (antes do '@').
         *
         * @                      ->  Corresponde ao caractere literal '@'.
         *
         * [a-zA-Z0-9.-]+         ->  Corresponde a um ou mais caracteres do conjunto permitido no domínio
         * do e-mail (após o '@').
         *
         * \\.                    ->  Corresponde ao caractere literal de ponto '.', que é um metacaractere
         * em regex e, por isso, precisa ser "escapado" com barras invertidas.
         *
         * [a-zA-Z]{2,6}          ->  Corresponde a um TLD (Top-Level Domain) de 2 a 6 letras (ex: 'com', 'br', 'org').
         *
         * \\b                    ->  Outro "limite de palavra" para garantir que a correspondência termine.
         */

        // 3. A string que será usada para substituir cada e-mail encontrado.
        String substituto = "[EMAIL_CENSURADO]";

        // 4. Utiliza o método replaceAll() da classe String.
        // Este é um método de conveniência que automaticamente busca TODAS as ocorrências
        // do padrão de regex na string e as substitui pela string de substituição.
        String textoModificado = textoOriginal.replaceAll(regexEmail, substituto);

        // 5. Imprime o resultado para comparação.
        System.out.println("Texto Original:");
        System.out.println(textoOriginal);
        System.out.println("\nTexto Modificado:");
        System.out.println(textoModificado);
    }
}
