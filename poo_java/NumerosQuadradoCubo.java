public class NumerosQuadradoCubo {

    public static void main(String[] args) {    
        /*
           Inicia um loop for que itera de 1 a 10.
           int i = 1: inicializa a variável i com o valor 1.
           i <= 10: a condição de continuação do loop. O loop continua enquanto i for menor ou igual a 10.
           i++: incrementa o valor de i em 1 a cada iteração do loop.
        */
        for (int i = 1; i <= 10; i++) {

            // Calcula o quadrado do número atual (i) e armazena o resultado na variável quadrado.
            int quadrado = i * i;

            // Calcula o cubo do número atual (i) e armazena o resultado na variável cubo.
            int cubo = i * i * i;

            // Imprime o número, seu quadrado e seu cubo
            System.out.println("Número: " + i + ", Quadrado: " + quadrado + ", Cubo: " + cubo);
        }
    }
}

