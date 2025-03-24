public class ExemploWhile {

    public static void main(String[] args) {

        int contador = 1; // Inicializa a variável 'contador' com o valor 1.

        // Loop 'while' que continua enquanto 'contador' for menor ou igual a 5.
        while (contador <= 5) {

            System.out.println("Contador: " + contador); // Imprime o valor atual de 'contador'.

            contador++; // Incrementa 'contador' em 1 a cada iteração do loop.
        }

        System.out.println("Loop encerrado!"); // Mensagem exibida após o término do loop.
    }
}
