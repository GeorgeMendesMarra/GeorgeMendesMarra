/*
Em Java, a classe Math faz parte do pacote java.lang, que é importado automaticamente em todos os programas Java. 
Isso significa que você não precisa explicitamente importar a biblioteca Math para usar suas funções.
*/
import java.util.Scanner; // Importa a classe Scanner para permitir a leitura da entrada do usuário.

public class Calculadora4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do teclado.

        imprimirCabecalho(); // Chama o método para imprimir o cabeçalho.

        double numero1 = lerNumero(scanner, "Digite o primeiro número: "); // Chama o método para ler o primeiro número.
        double numero2 = lerNumero(scanner, "Digite o segundo número: "); // Chama o método para ler o segundo número.

        char operacao = escolherOperacao(scanner); // Chama o método para escolher a operação.

        double resultado = calcularResultado(numero1, numero2, operacao); // Chama o método para calcular o resultado.

        imprimirResultado(resultado); // Chama o método para imprimir o resultado.

        scanner.close(); // Fecha o objeto Scanner para liberar recursos.
    }

    // Método para imprimir o cabeçalho da calculadora.
    public static void imprimirCabecalho() {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&Centro Universitário UniAlfa&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&Curso: Análise e Desenvolvimento de Sistemas - ADS&&&&&");
        System.out.println("&&&&&&Disciplina: Programação Orientada a Objetos - Java&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&Professor: George Mendes Marra****&&&&&&&&&&&&");
        System.out.println("&Aluno(a):                                                                  &");
        System.out.println("&Data:                                                                      &");
        System.out.println("&&&&&&&&&&&&Programa Calculadora em Java. Versão: 2.0&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }

    // Método para ler um número do usuário.
    public static double lerNumero(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextDouble();
    }

    // Método para escolher a operação a ser realizada.
    public static char escolherOperacao(Scanner scanner) {
        System.out.println();
        System.out.println("Escolha a operação:");
        System.out.println("1-Soma");
        System.out.println("2-Subtração");
        System.out.println("3-Multiplicação");
        System.out.println("4-Divisão");
        System.out.println("5-Raiz quadrada");
        System.out.println("6-Raiz cúbica");
        System.out.println("7-Logaritmo natural");
        System.out.println("8-Eleva ao quadrado");
        System.out.println("9-Calcula o seno");
        System.out.println();

        return scanner.next().charAt(0);
    }

    // Método para calcular o resultado da operação escolhida.
    public static double calcularResultado(double numero1, double numero2, char operacao) {
        double resultado = 0;

        switch (operacao) {
            case '1': // Soma
                resultado = numero1 + numero2;
                break;
            case '2': // Subtração
                resultado = numero1 - numero2;
                break;
            case '3': // Multiplicação
                resultado = numero1 * numero2;
                break;
            case '4': // Divisão
                if (numero2 == 0) {
                    System.out.println("Erro: divisão por zero!");
                    System.exit(0); // Encerra o programa se houver divisão por zero.
                }
                resultado = numero1 / numero2;
                break;
            case '5': // Raiz quadrada
                System.out.println("Raiz quadrada de " + numero1 + " é = " + Math.sqrt(numero1));
                System.out.println("Raiz quadrada de " + numero2 + " é = " + Math.sqrt(numero2));
                System.exit(0); // Encerra a execução após calcular a raiz quadrada.
                break;
            case '6': // Raiz cúbica
                System.out.println("Raiz cúbica de " + numero1 + " é = " + Math.cbrt(numero1));
                System.out.println("Raiz cúbica de " + numero2 + " é = " + Math.cbrt(numero2));
                System.exit(0); // Encerra a execução após calcular a raiz cúbica.
                break;
            case '7': // Logaritmo natural
                System.out.println("Logaritmo natural de " + numero1 + " é = " + Math.log(numero1));
                System.out.println("Logaritmo natural de " + numero2 + " é = " + Math.log(numero2));
                System.exit(0); // Encerra a execução após calcular o logaritmo natural.
                break;
            case '8': // Eleva ao quadrado
                System.out.println(numero1 + " elevado ao quadrado é = " + Math.pow(numero1, 2));
                System.out.println(numero2 + " elevado ao quadrado é = " + Math.pow(numero2, 2));
                System.exit(0); // Encerra a execução após elevar os números ao quadrado.
                break;
            case '9': // Calcula o seno (radianos)
                System.out.println("Seno de " + numero1 + " é = " + Math.sin(numero1));
                System.out.println("Seno de " + numero2 + " é = " + Math.sin(numero2));
                System.exit(0); // Encerra a execução após calcular o seno dos números.
                break;
            default: // Operação inválida
                System.out.println("Operação inválida!");
                System.exit(0); // Encerra o programa se a operação for inválida.
        }

        return resultado;
    }

    // Método para imprimir o resultado da operação.
    public static void imprimirResultado(double resultado) {
        System.out.println("Resultado: " + resultado);
    }
}
