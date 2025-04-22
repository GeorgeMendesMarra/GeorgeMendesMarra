import java.util.Scanner;

public class Comparacao2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Lê os dois números usando métodos auxiliares
        int numero1 = lerNumero(input, "Entre com o primeiro número:");
        int numero2 = lerNumero(input, "Entre com o segundo número:");

        // Chama o método para comparar os números
        compararNumeros(numero1, numero2);
    }

    // Método para ler um número do teclado
    public static int lerNumero(Scanner input, String mensagem) {
        System.out.println(mensagem);
        return input.nextInt();
    }

    // Método para comparar dois números e exibir os resultados
    public static void compararNumeros(int numero1, int numero2) {
        if (numero1 == numero2)
            System.out.printf("%d == %d\n", numero1, numero2);

        if (numero1 != numero2)
            System.out.printf("%d != %d\n", numero1, numero2);

        if (numero1 < numero2)
            System.out.printf("%d < %d\n", numero1, numero2);

        if (numero1 > numero2)
            System.out.printf("%d > %d\n", numero1, numero2);

        if (numero1 <= numero2)
            System.out.printf("%d <= %d\n", numero1, numero2);

        if (numero1 >= numero2)
            System.out.printf("%d >= %d\n", numero1, numero2);
    }
}
/* Benefícios dessa versão:
- O código fica mais organizado e reutilizável;
- Facilita testes e manutenção;
- Isola responsabilidades: entrada e comparação estão separadas.
*/
