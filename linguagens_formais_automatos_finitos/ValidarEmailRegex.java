import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta classe demonstra como usar a API de Expressões Regulares (Regex) do Java
 * para validar se uma string de texto corresponde a um formato de e-mail.
 * Usaremos as classes Pattern e Matcher.
 */
public class ValidarEmailRegex {

    public static void main(String[] args) {

        // 1. Definir a Expressão Regular (Regex) para o formato de e-mail.
        // Uma regex é uma linguagem formal para descrever padrões de texto.
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        /*
         * Decifrando a regex acima:
         *
         * ^                      ->  Sinaliza o INÍCIO da string. A correspondência deve começar aqui.
         *
         * [a-zA-Z0-9._%+-]+      ->  Primeira parte do e-mail (antes do @).
         * - [a-zA-Z0-9._%+-] define o CONJUNTO de caracteres permitidos.
         * Inclui letras (maiúsculas e minúsculas), números, e os símbolos . _ % + -.
         * - O sinal '+' (quantificador) significa que o conjunto anterior
         * deve aparecer UMA OU MAIS VEZES.
         *
         * @                      ->  O caractere LITERAL '@'. Deve aparecer exatamente uma vez.
         *
         * [a-zA-Z0-9.-]+        ->  Segunda parte do e-mail (o domínio, ex: 'exemplo').
         * - [a-zA-Z0-9.-] define o conjunto de caracteres permitidos para o domínio.
         * - O '+' significa que esse conjunto deve aparecer UMA OU MAIS VEZES.
         *
         * \\.                    ->  O caractere LITERAL '.'.
         * - O ponto '.' tem um significado especial em regex, por isso
         * precisamos 'escapá-lo' com duas barras invertidas '\\'.
         *
         * [a-zA-Z]{2,6}         ->  A terceira parte (o TLD, ex: 'com', 'br').
         * - [a-zA-Z] é o conjunto de letras.
         * - O quantificador '{2,6}' significa que esse conjunto
         * deve aparecer no mínimo 2 e no máximo 6 vezes.
         *
         * $                      ->  Sinaliza o FIM da string. A correspondência deve terminar aqui.
         */

        // 2. Definir as strings para teste.
        String emailValido = "usuario.teste123@exemplo.com.br";
        String emailInvalido1 = "usuario_invalido@.com"; // E-mail com ponto no lugar errado
        String emailInvalido2 = "usuario_sem_arroba.com"; // E-mail sem o caractere '@'

        // 3. Compilar a regex.
        // A classe Pattern representa a regex compilada. Compilar a regex
        // uma única vez é mais eficiente se você for usá-la várias vezes.
        Pattern pattern = Pattern.compile(regexEmail);

        // 4. Criar e usar o objeto Matcher para o primeiro e-mail.
        // O Matcher faz a correspondência real entre a regex e a string de entrada.
        Matcher matcherValido = pattern.matcher(emailValido);

        // O método `matches()` verifica se a STRING INTEIRA corresponde ao padrão.
        boolean isEmailValido = matcherValido.matches();
        System.out.println("O e-mail '" + emailValido + "' é válido? " + isEmailValido); // Saída: true

        // 5. Reutilizar o objeto Pattern para testar os outros e-mails.
        // Apenas criamos novos Matchers para as strings que queremos testar.
        Matcher matcherInvalido1 = pattern.matcher(emailInvalido1);
        boolean isEmailInvalido1 = matcherInvalido1.matches();
        System.out.println("O e-mail '" + emailInvalido1 + "' é válido? " + isEmailInvalido1); // Saída: false

        Matcher matcherInvalido2 = pattern.matcher(emailInvalido2);
        boolean isEmailInvalido2 = matcherInvalido2.matches();
        System.out.println("O e-mail '" + emailInvalido2 + "' é válido? " + isEmailInvalido2); // Saída: false
    }
}
