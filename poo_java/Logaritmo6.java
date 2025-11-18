/**
 * Converte um conjunto de valores para escala log base 10.
 */
public class Logaritmo6 {

    public static void main(String[] args) {
        double[] dados = {1, 10, 100, 1000, 10000};

        System.out.println("Valor\tlog10");
        for (double v : dados) {
            System.out.println(v + "\t" + Math.log10(v));
        }
    }
}
