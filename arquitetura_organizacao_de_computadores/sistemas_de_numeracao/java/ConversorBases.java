import java.util.Scanner;

public class ConversorBases {
    public static void main(String[] args) {
        // Criamos um Scanner para ler a entrada do usuário no console
        Scanner leitor = new Scanner(System.in);

        System.out.print("Digite um número inteiro (decimal): ");
        
        // Verificamos se a entrada é de fato um número inteiro
        if (leitor.hasNextInt()) {
            int numero = leitor.nextInt();

            System.out.println("\n--- Resultados para o número: " + numero + " ---");

            // 1. Convertendo para Binário (Base 2)
            String binario = Integer.toBinaryString(numero);
            System.out.println("Binário:     " + binario);

            // 2. Convertendo para Octal (Base 8)
            String octal = Integer.toOctalString(numero);
            System.out.println("Octal:       " + octal);

            // 3. Convertendo para Hexadecimal (Base 16)
            // O método toUpperCase() deixa as letras (A, B, C, D, E, F) em maiúsculo
            String hex = Integer.toHexString(numero).toUpperCase();
            System.out.println("Hexadecimal: 0x" + hex);

            // 4. Exemplo de Base Customizada (Base 3)
            // O segundo parâmetro do toString é o 'radix' (a base desejada)
            String base3 = Integer.toString(numero, 3);
            System.out.println("Base 3:      " + base3);
            
            System.out.println("---------------------------------------");

        } else {
            System.out.println("Erro: Por favor, digite apenas números inteiros.");
        }

        // Fechamos o scanner para liberar recursos
        leitor.close();
    }
}
