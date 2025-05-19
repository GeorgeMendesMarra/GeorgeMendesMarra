public class Calculadora {

    /**
     * Método estático para calcular a soma de dois números inteiros.
     * O modificador 'static' permite que este método seja chamado diretamente na classe 'Calculadora'
     * (por exemplo, Calculadora.somar(5, 3)) sem a necessidade de criar um objeto 'Calculadora'.
     * 'public' torna este método acessível de qualquer outra classe ou parte do programa.
     * 'int' indica que este método retorna um valor inteiro, que é o resultado da soma.
     *
     * @param a O primeiro número inteiro a ser somado.
     * @param b O segundo número inteiro a ser somado.
     * @return A soma dos dois números inteiros 'a' e 'b'.
     */
    public static int somar(int a, int b) {
        return a + b; // Realiza a operação de adição dos dois parâmetros e retorna o resultado.
    }

    /**
     * Método estático para calcular o produto de dois números inteiros.
     * Similar ao método 'somar', o 'static' permite a chamada direta na classe.
     * 'public' garante a acessibilidade do método.
     * 'int' especifica que o método retorna um valor inteiro, que é o produto.
     *
     * @param a O primeiro número inteiro a ser multiplicado.
     * @param b O segundo número inteiro a ser multiplicado.
     * @return O produto dos dois números inteiros 'a' e 'b'.
     */
    public static int multiplicar(int a, int b) {
        return a * b; // Realiza a operação de multiplicação dos dois parâmetros e retorna o resultado.
    }

    /**
     * Método principal onde a execução do programa Java começa.
     * 'public' torna este método acessível pela Java Virtual Machine (JVM).
     * 'static' permite que este método seja executado sem a necessidade de criar um objeto da classe 'Calculadora'.
     * 'void' indica que este método não retorna nenhum valor.
     * 'String[] args' são os argumentos de linha de comando que podem ser passados para o programa.
     *
     * @param args Um array de strings contendo os argumentos de linha de comando (não utilizado neste exemplo).
     */
    public static void main(String[] args) {
        // Chama o método estático 'somar' da classe 'Calculadora' com os argumentos 5 e 3.
        // O resultado da soma (8) é armazenado na variável local 'resultadoSoma'.
        int resultadoSoma = Calculadora.somar(5, 3);
        // Imprime no console uma mensagem descritiva e o valor da soma calculado.
        // A parte "// Saída: A soma de 5 e 3 é: 8" é um comentário que mostra o resultado esperado da execução desta linha.
        System.out.println("A soma de 5 e 3 é: " + resultadoSoma);

        // Chama o método estático 'multiplicar' da classe 'Calculadora' com os argumentos 4 e 6.
        // O resultado da multiplicação (24) é armazenado na variável local 'resultadoMultiplicacao'.
        int resultadoMultiplicacao = Calculadora.multiplicar(4, 6);
        // Imprime no console uma mensagem descritiva e o valor do produto calculado.
        // Similarmente, "// Saída: O produto de 4 e 6 é: 24" indica o resultado esperado desta linha.
        System.out.println("O produto de 4 e 6 é: " + resultadoMultiplicacao);
    }
}
