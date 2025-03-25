import java.util.Scanner;

public class Calculadora3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        double numero1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double numero2 = scanner.nextDouble();

        System.out.print("Digite a operação (+, -, *, /): ");
        char operacao = scanner.next().charAt(0);

        double resultado;

        switch (operacao) {
            case '+':
                resultado = somar(numero1, numero2); // Chamada do método somar
                break;
            case '-':
                resultado = subtrair(numero1, numero2); // Chamada do método subtrair
                break;
            case '*':
                resultado = multiplicar(numero1, numero2); // Chamada do método multiplicar
                break;
            case '/':
                if (numero2 == 0) {
                    System.out.println("Erro: divisão por zero!");
                    return;
                }
                resultado = dividir(numero1, numero2); // Chamada do método dividir
                break;
            default:
                System.out.println("Operação inválida!");
                return;
        }

        System.out.println("Resultado: " + resultado);
        scanner.close();
    }

    // Métodos para realizar as operações matemáticas
    
    //Método para somar
    public static double somar(double num1, double num2) {
        return num1 + num2;
    }
   //Método para subtrair
    public static double subtrair(double num1, double num2) {
        return num1 - num2;
    }
    //Método para multiplicar
    public static double multiplicar(double num1, double num2) {
        return num1 * num2;
    }
    //Método para dividir
    public static double dividir(double num1, double num2) {
        return num1 / num2;
    }
}

/*
Por que as variáveis dos métodos são diferentes das variáveis numero1 e numero2? 
Escopo de Variáveis:
    Variáveis Locais:
        As variáveis num1 e num2 dentro dos métodos somar, subtrair, multiplicar e dividir são chamadas de "variáveis locais". Isso significa que elas só existem dentro do escopo desses métodos.
        Quando um método é chamado, essas variáveis são criadas, e quando o método termina, elas são destruídas.
        Elas são como "cópias" dos valores passados para o método.
    Variáveis da Função main:
        As variáveis numero1 e numero2 são declaradas dentro da função main. Elas também são variáveis locais, mas seu escopo é a função main.
        Elas existem durante a execução da função main.
Passagem de Parâmetros:
    Quando você chama um método como somar(numero1, numero2), os valores de numero1 e numero2 são copiados para as variáveis num1 e num2 do método somar.
    Isso é chamado de "passagem de parâmetros por valor". O método recebe cópias dos valores originais.
    Qualquer alteração feita em num1 ou num2 dentro do método não afeta as variáveis numero1 e numero2 na função main.
Razões para a Diferença:

    Modularidade:
        Permitir que os métodos usem nomes de variáveis diferentes torna os métodos mais independentes e reutilizáveis.
        Você pode chamar os métodos com qualquer nome de variável na função main sem se preocupar com conflitos de nomes.
    Encapsulamento:
        O encapsulamento é um princípio da programação orientada a objetos que visa ocultar detalhes de implementação.
        Usar variáveis locais diferentes dentro dos métodos ajuda a encapsular a lógica de cada método.
    Evitar Conflitos:
        Se os métodos fossem obrigados a usar os mesmos nomes de variáveis da função main, isso poderia levar a conflitos de nomes, especialmente em programas grandes e complexos.

Em resumo:

    As variáveis num1 e num2 são variáveis locais dos métodos, enquanto numero1 e numero2 são variáveis locais da função main
*/
