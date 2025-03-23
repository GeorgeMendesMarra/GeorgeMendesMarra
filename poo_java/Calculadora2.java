import java.util.Scanner;

public class Calculadora2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
        
        System.out.print("Digite o primeiro número: ");
        double numero1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double numero2 = scanner.nextDouble();

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
        
        char operacao = scanner.next().charAt(0); 

        double resultado;

        switch (operacao) {
            case '1':
                resultado = numero1 + numero2;
                break;
            case '2':
                resultado = numero1 - numero2;
                break;
            case '3':
                resultado = numero1 * numero2;
                break;
            case '4':
                if (numero2 == 0) {
                    System.out.println("Erro: divisão por zero!");
                    return;
                }
                resultado = numero1 / numero2;
                break;
            case '5': // Calcula a raiz quadrada
              resultado = Math.sqrt(numero1); // Calcula a raiz quadrada do primeiro número
              System.out.println("Raiz quadrada de " + numero1 + " é = " + resultado);
              resultado = Math.sqrt(numero2); // Calcula a raiz quadrada do segundo número
              System.out.println("Raiz quadrada de " + numero2 + " é = " + resultado);
              return; // Encerra a execução após calcular a raiz quadrada.
              
           case '6': // Calcula a  raiz cúbica
              resultado = Math.cbrt(numero1); // Calcula a raiz cúbica do primeiro número
              System.out.println("Raiz cúbica de " + numero1 + " é = " + resultado);
              resultado = Math.cbrt(numero2); // Calcula a raiz cúbica do segundo número
              System.out.println("Raiz cúbica de " + numero2 + " é = " + resultado);
              return; // Encerra a execução após calcular a raiz cúbica.
              
           case '7': // Calcula o logaritmo natural
              resultado = Math.log(numero1); // Calcula o logaritmo natural do primeiro número
              System.out.println("Logaritmo natural de " + numero1 + " é = " + resultado);
              resultado = Math.log(numero2); // Calcula o logaritmo natural do segundo número
              System.out.println("Logaritmo natural de " + numero2 + " é = " + resultado);
              return; // Encerra a execução após calcular o logaritmo natural.
              
            case '8': // Eleva o número ao quadrado
              resultado = Math.pow(numero1, 2); // Eleva o primeiro número ao quadrado
              System.out.println( + numero1 + " elevado ao quadrado é = " + resultado);
              resultado = Math.pow(numero2, 2); // Eleva o segundo número ao quadrado
              System.out.println( + numero2 + " elevado ao quadrado é = " + resultado);
              return; // Encerra a execução após elevar os números ao quadrado
              
            case '9': // Calcula o seno (radianos)
              resultado = Math.sin(numero1); // Calcula o seno do primeiro número
              System.out.println("Seno de " + numero1 + " é = " + resultado);
              resultado = Math.sin(numero2); // Calcula o seno do segundo número
              System.out.println("Seno de " + numero2 + " é = " + resultado);
              return; // Encerra a execução após calcular o seno do primeiro e segundo número
              
                        
            default:
                System.out.println("Operação inválida!");
                return;
        }

        System.out.println("Resultado: " + resultado);
        scanner.close();
    }
}
