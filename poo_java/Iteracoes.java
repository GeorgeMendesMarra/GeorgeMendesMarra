public class Iteracoes {

    public static void main(String[] args) {
        int i = 5;

        // i++ (pós-incremento)
        System.out.println("i++:");
        System.out.println("Valor inicial de i: " + i);
        System.out.println("i++: " + i++);
        System.out.println("Valor de i após i++: " + i);
        System.out.println();

        // ++i (pré-incremento)
        System.out.println("++i:");
        System.out.println("Valor inicial de i: " + i);
        System.out.println("++i: " + ++i);
        System.out.println("Valor de i após ++i: " + i);
        System.out.println();

        // i-- (pós-decremento)
        System.out.println("i--:");
        System.out.println("Valor inicial de i: " + i);
        System.out.println("i--: " + i--);
        System.out.println("Valor de i após i--: " + i);
        System.out.println();

        // --i (pré-decremento)
        System.out.println("--i:");
        System.out.println("Valor inicial de i: " + i);
        System.out.println("--i: " + --i);
        System.out.println("Valor de i após --i: " + i);
    }
}
