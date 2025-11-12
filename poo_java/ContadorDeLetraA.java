import java.util.Scanner;

/**
 * Programa que conta o número de ocorrências da letra 'a' (maiúscula ou minúscula)
 * em uma frase digitada pelo usuário.
 * 
 * @author
 * @version 1.0
 */
public class ContadorDeLetraA {

    /**
     * Método principal. Lê uma frase do usuário e conta quantas vezes a letra 'a' aparece.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do teclado
        Scanner entrada = new Scanner(System.in);

        // Exibe uma mensagem pedindo para o usuário digitar uma frase
        System.out.print("Digite uma frase: ");
        // Lê a linha completa digitada pelo usuário
        String frase = entrada.nextLine();

        // Variável que armazenará o número de vezes que a letra 'a' aparece
        int contador = 0;

        // Estrutura de repetição que percorre cada caractere da string
        for (int i = 0; i < frase.length(); i++) {
            // Obtém o caractere atual da posição 'i'
            char c = frase.charAt(i);

            // Verifica se o caractere é 'a' minúsculo ou 'A' maiúsculo
            if (c == 'a' || c == 'A') {
                // Incrementa o contador em 1
                contador++;
            }
        }

        // Exibe na tela o total de ocorrências encontradas
        System.out.println("A letra 'a' aparece " + contador + " vezes na frase.");

        // Fecha o objeto Scanner para liberar recursos
        entrada.close();
    }
}
