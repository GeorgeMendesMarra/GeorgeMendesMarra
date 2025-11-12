import java.util.Scanner;

/**
 * Programa que gera e exibe os primeiros N números da sequência de Fibonacci.
 * 
 * Exemplo: se N = 6 → saída: 1, 1, 2, 3, 5, 8
 * 
 * A sequência de Fibonacci é formada pela soma dos dois números anteriores.
 * (1, 1, 2, 3, 5, 8, 13, 21, ...)
 * 
 * @author
 * @version 1.0
 */
public class SequenciaFibonacci {

    /**
     * Método principal. Lê o valor de N e exibe os primeiros N números da sequência de Fibonacci.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para capturar a entrada do usuário pelo teclado
        Scanner entrada = new Scanner(System.in);

        // Solicita que o usuário digite um número inteiro N
        System.out.print("Digite um número inteiro N: ");
        int n = entrada.nextInt(); // Lê o número informado e armazena em 'n'

        // Declara e inicializa os dois primeiros números da sequência de Fibonacci
        int primeiro = 1; // Primeiro termo da sequência
        int segundo = 1;  // Segundo termo da sequência

        // Exibe uma mensagem informando quantos números serão mostrados
        System.out.println("Sequência de Fibonacci com " + n + " números:");

        // Verifica se o usuário digitou 1
        if (n == 1) {
            // Caso o valor seja 1, imprime apenas o primeiro número
            System.out.println(primeiro);
        } 
        // Caso o usuário tenha digitado um valor maior ou igual a 2
        else if (n >= 2) {
            // Exibe os dois primeiros números da sequência
            System.out.print(primeiro + ", " + segundo);

            // Loop que gera os próximos números da sequência a partir do 3º termo
            for (int i = 3; i <= n; i++) {
                // O próximo número é a soma dos dois anteriores
                int proximo = primeiro + segundo;

                // Exibe o próximo número da sequência
                System.out.print(", " + proximo);

                // Atualiza os valores das variáveis para o próximo cálculo:
                // o segundo número vira o primeiro, e o próximo vira o segundo
                primeiro = segundo;
                segundo = proximo;
            }

            // Após o loop, quebra a linha para finalizar a saída
            System.out.println();
        } 
        // Caso o usuário digite 0 ou um número negativo
        else {
            System.out.println("Por favor, digite um número maior que zero.");
        }

        // Fecha o objeto Scanner para liberar os recursos
        entrada.close();
    }
}
