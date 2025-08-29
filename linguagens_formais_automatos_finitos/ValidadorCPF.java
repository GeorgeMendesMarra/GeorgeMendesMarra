import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta classe demonstra a validação completa de um CPF (Cadastro de Pessoas Físicas)
 * combinando uma Expressão Regular (Regex) para o formato e um algoritmo para
 * verificar os dígitos de controle.
 */
public class ValidadorCPF {

    // Regex para validar o formato do CPF: ###.###.###-##
    // A palavra-chave 'final' indica que a variável não pode ser alterada.
    private static final String REGEX_CPF_FORMATO = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    
    // Compila a regex em um objeto Pattern para otimizar o desempenho.
    // Isso evita que a regex seja compilada a cada vez que o método 'validar' é chamado.
    private static final Pattern PATTERN_CPF_FORMATO = Pattern.compile(REGEX_CPF_FORMATO);

    public static void main(String[] args) {
        // Strings de exemplo para testar a validação.
        String cpfValido1 = "111.444.777-05"; // CPF válido, passa no formato e no cálculo.
        String cpfValido2 = "000.000.000-00"; // Exemplo de um CPF com formato válido, mas inválido
                                             // na prática. A lógica do programa o trata corretamente.
        String cpfInvalidoFormato = "12345678901"; // Formato inválido, sem pontos ou traço.
        String cpfInvalidoDigito = "111.444.777-06"; // Formato válido, mas com um dígito verificador incorreto.

        // Imprime os resultados dos testes.
        System.out.println("O CPF '" + cpfValido1 + "' é válido? " + validar(cpfValido1));
        System.out.println("O CPF '" + cpfValido2 + "' é válido? " + validar(cpfValido2));
        System.out.println("O CPF '" + cpfInvalidoFormato + "' é válido? " + validar(cpfInvalidoFormato));
        System.out.println("O CPF '" + cpfInvalidoDigito + "' é válido? " + validar(cpfInvalidoDigito));
    }

    /**
     * Valida um CPF completo (formato e dígitos verificadores).
     * @param cpf A string do CPF a ser validada, esperando o formato ###.###.###-##.
     * @return true se o CPF for válido de acordo com as regras, false caso contrário.
     */
    public static boolean validar(String cpf) {
        // Passo 1: Validação do formato do CPF usando a regex pré-compilada.
        // O método 'matches()' verifica se a string completa corresponde ao padrão.
        Matcher matcher = PATTERN_CPF_FORMATO.matcher(cpf);
        if (!matcher.matches()) {
            return false; // Retorna falso imediatamente se o formato não estiver correto.
        }

        // Passo 2: Remove a formatação (pontos e traço) para trabalhar apenas com os 11 dígitos.
        String cpfLimpo = cpf.replaceAll("[.-]", "");

        // Passo 3: Verifica se todos os dígitos são iguais (ex: "11111111111").
        // CPFs com todos os dígitos iguais são considerados inválidos pelo algoritmo.
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false; // Retorna falso para sequências de dígitos idênticos.
        }

        // Passo 4: Validação do primeiro dígito verificador (DV1).
        int soma1 = 0;
        // O loop calcula a soma ponderada dos 9 primeiros dígitos.
        // O multiplicador vai de 10 a 2.
        for (int i = 0; i < 9; i++) {
            soma1 += (cpfLimpo.charAt(i) - '0') * (10 - i);
        }
        
        // Calcula o primeiro dígito verificador com base no resto da divisão.
        int resto1 = 11 - (soma1 % 11);
        int dv1 = (resto1 > 9) ? 0 : resto1; // Se o resultado for 10 ou 11, o dígito é 0.

        // Compara o dígito calculado com o dígito real da string.
        if ((cpfLimpo.charAt(9) - '0') != dv1) {
            return false; // Se não forem iguais, o CPF é inválido.
        }

        // Passo 5: Validação do segundo dígito verificador (DV2).
        int soma2 = 0;
        // O loop calcula a soma ponderada dos 10 primeiros dígitos (incluindo o DV1).
        // O multiplicador vai de 11 a 2.
        for (int i = 0; i < 10; i++) {
            soma2 += (cpfLimpo.charAt(i) - '0') * (11 - i);
        }
        
        // Calcula o segundo dígito verificador.
        int resto2 = 11 - (soma2 % 11);
        int dv2 = (resto2 > 9) ? 0 : resto2; // Se o resultado for 10 ou 11, o dígito é 0.

        // Compara o dígito calculado com o dígito real da string.
        if ((cpfLimpo.charAt(10) - '0') != dv2) {
            return false; // Se não forem iguais, o CPF é inválido.
        }

        // Se o código chegou até aqui, significa que o CPF passou em todas as verificações.
        return true; // Retorna true para um CPF considerado válido.
    }
}
