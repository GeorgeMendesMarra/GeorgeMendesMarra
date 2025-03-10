import java.util.Scanner; //Linha 1

public class Calculadora1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Linha 6

        System.out.print("Digite o primeiro número: "); //Linha 8
        double numero1 = scanner.nextDouble();          //Linha 9

        System.out.print("Digite o segundo número: ");  //Linha 11
        double numero2 = scanner.nextDouble();          //Linha 12

        System.out.print("Digite a operação (+, -, *, /): "); //Linha 14
        char operacao = scanner.next().charAt(0);             //Linha 15

        double resultado;  //Linha 17

        switch (operacao) { //Linha 19
            case '+':
                resultado = numero1 + numero2;
                break;
            case '-':
                resultado = numero1 - numero2;
                break;
            case '*':
                resultado = numero1 * numero2;
                break;
            case '/':
                if (numero2 == 0) { //Linha 30
                    System.out.println("Erro: divisão por zero!");
                    return;
                }
                resultado = numero1 / numero2;
                break;
            default:
                System.out.println("Operação inválida!");
                return;
        }

        System.out.println("Resultado: " + resultado); //Linha 41
        scanner.close(); //Linha 42
    }
}

/* Linha 1 - import java.util.Scanner; importa a classe Scanner, que permite ler a entrada do usuário.
   Linha 6 - Scanner scanner = new Scanner(System.in); cria um objeto Scanner para ler a entrada do teclado.
   Linhas 8, 11, 14 - Programa solicita ao usuário que digite dois números e a operação desejada (+, -, *, /).
   Linhas 9,12,15 - scanner.nextDouble() lê os números decimais inseridos.
   Linha 17 - O resultado é armazenado na variável resultado.
   Linha 19 - Um switch é usado para determinar qual operação realizar com base no caractere inserido.
   Linha 30 - Se a operação for divisão (/) e o segundo número for zero, o programa exibe uma mensagem de erro e encerra.
   Linha 41 - O resultado da operação é exibido no console.
   Linha 42 - scanner.close(); fecha o objeto Scanner para liberar recursos.
*/
