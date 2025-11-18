/**
 * Mostra uma tabela de logaritmos base 10.
 */
public class Logaritmo3 {

    public static void main(String[] args) {
        System.out.println("Valor\tlog10(valor)");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "\t" + Math.log10(i));
        }
    }
}
