// Importa a classe Scanner do pacote java.util para permitir a leitura de dados do teclado
import java.util.Scanner;

// Declaração da classe principal do programa
public class Comparacao1 {

    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
    
        // Cria um objeto Scanner para ler dados do teclado
        Scanner input = new Scanner(System.in);
        
        // Declara duas variáveis inteiras para armazenar os números inseridos pelo usuário
        int numero1;
        int numero2;
        
        // Solicita ao usuário que insira o primeiro número
        System.out.println("Entre com o primeiro número:");
        numero1 = input.nextInt();  // Lê o número digitado e armazena em numero1
        
        // Solicita ao usuário que insira o segundo número
        System.out.println("Entre com o segundo número:");
        numero2 = input.nextInt();  // Lê o número digitado e armazena em numero2
        
        // Verifica se os números são iguais
        if (numero1 == numero2)
            System.out.printf("%d == %d\n", numero1, numero2);
        
        // Verifica se os números são diferentes
        if (numero1 != numero2)
            System.out.printf("%d != %d\n", numero1, numero2);
        
        // Verifica se o primeiro número é menor que o segundo
        if (numero1 < numero2)
            System.out.printf("%d < %d\n", numero1, numero2);
        
        // Verifica se o primeiro número é maior que o segundo
        if (numero1 > numero2)
            System.out.printf("%d > %d\n", numero1, numero2);
        
        // Verifica se o primeiro número é menor ou igual ao segundo
        if (numero1 <= numero2)
            System.out.printf("%d <= %d\n", numero1, numero2);
        
        // Verifica se o primeiro número é maior ou igual ao segundo
        if (numero1 >= numero2)
            System.out.printf("%d >= %d\n", numero1, numero2);
    }
}
