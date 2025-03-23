/*
Em Java, a classe Math faz parte do pacote java.lang, que é importado automaticamente em todos os programas Java. 
Isso significa que você não precisa explicitamente importar a biblioteca Math para usar suas funções.
*/
import java.util.Scanner; // Importa a classe Scanner para permitir a leitura da entrada do usuário.

public class Calculadora2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do teclado.

        // Impressão do cabeçalho com informações do curso e versão da calculadora.
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&Centro Universitário UniAlfa&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&Curso: Análise e Desenvolvimento de Sistemas - ADS&&&&&");
        System.out.println("&&&&&&Disciplina: Programação Orientada a Objetos - Java&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&Professor: George Mendes Marra****&&&&&&&&&&&&");
        System.out.println("&Aluno(a):                                                   &");
        System.out.println("&Data:                                                       &");
        System.out.println("&&&&&&&&&&&&Programa Calculadora em Java. Versão: 2.0&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        // Solicita ao usuário que digite o primeiro número.
        System.out.print("Digite o primeiro número: ");
        double numero1 = scanner.nextDouble(); // Lê o primeiro número como um double.

        // Solicita ao usuário que digite o segundo número.
        System.out.print("Digite o segundo número: ");
        double numero2 = scanner.nextDouble(); // Lê o segundo número como um double.

        System.out.println(); // Imprime uma linha em branco para melhorar a formatação.
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
        System.out.println(); // Imprime uma linha em branco para melhorar a formatação.

        char operacao = scanner.next().charAt(0); // Lê o primeiro caractere da entrada do usuário como a operação.

        double resultado; // Declara a variável para armazenar o resultado da operação.

        // Estrutura switch para realizar a operação escolhida pelo usuário.
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
                if (numero2 == 0) { // Verifica se o segundo número é zero para evitar divisão por zero.
                    System.out.println("Erro: divisão por zero!");
                    return; // Encerra o programa se houver divisão por zero.
                }
                resultado = numero1 / numero2;
                break;
            case '5': // Raiz quadrada
                resultado = Math.sqrt(numero1); // Calcula a raiz quadrada do primeiro número.
                System.out.println("Raiz quadrada de " + numero1 + " é = " + resultado);
                resultado = Math.sqrt(numero2); // Calcula a raiz quadrada do segundo número.
                System.out.println("Raiz quadrada de " + numero2 + " é = " + resultado);
                return; // Encerra a execução após calcular a raiz quadrada.
            case '6': // Raiz cúbica
                resultado = Math.cbrt(numero1); // Calcula a raiz cúbica do primeiro número.
                System.out.println("Raiz cúbica de " + numero1 + " é = " + resultado);
                resultado = Math.cbrt(numero2); // Calcula a raiz cúbica do segundo número.
                System.out.println("Raiz cúbica de " + numero2 + " é = " + resultado);
                return; // Encerra a execução após calcular a raiz cúbica.
            case '7': // Logaritmo natural
                resultado = Math.log(numero1); // Calcula o logaritmo natural do primeiro número.
                System.out.println("Logaritmo natural de " + numero1 + " é = " + resultado);
                resultado = Math.log(numero2); // Calcula o logaritmo natural do segundo número.
                System.out.println("Logaritmo natural de " + numero2 + " é = " + resultado);
                return; // Encerra a execução após calcular o logaritmo natural.
            case '8': // Eleva ao quadrado
                resultado = Math.pow(numero1, 2); // Eleva o primeiro número ao quadrado.
                System.out.println(+numero1 + " elevado ao quadrado é = " + resultado);
                resultado = Math.pow(numero2, 2); // Eleva o segundo número ao quadrado.
                System.out.println(+numero2 + " elevado ao quadrado é = " + resultado);
                return; // Encerra a execução após elevar os números ao quadrado.
            case '9': // Calcula o seno (radianos)
                resultado = Math.sin(numero1); // Calcula o seno do primeiro número.
                System.out.println("Seno de " + numero1 + " é = " + resultado);
                resultado = Math.sin(numero2); // Calcula o seno do segundo número.
                System.out.println("Seno de " + numero2 + " é = " + resultado);
                return; // Encerra a execução após calcular o seno dos números.
            default: // Operação inválida
                System.out.println("Operação inválida!");
                return; // Encerra o programa se a operação for inválida.
        }

        System.out.println("Resultado: " + resultado); // Imprime o resultado da operação.
        scanner.close(); // Fecha o objeto Scanner para liberar recursos.
    }
}
