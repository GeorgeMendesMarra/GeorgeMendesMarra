public class Estatisticas {

    public static double calcularMedia(double[] notas) {
        if (notas == null || notas.length == 0) {
            return 0.0;
        }
        double soma = 0;
        for (int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }
        return soma / notas.length;
    }

    public static void main(String[] args) {
        double[] notas = {7.5, 8.0, 6.5, 9.0};
        double media = calcularMedia(notas);
        System.out.println("Média: " + media);
    }
}
