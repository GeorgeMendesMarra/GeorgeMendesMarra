public class Estatisticas1 {

    // Recebe um array, processa (soma/divide) e retorna o resultado
    public static double calcularMedia(double[] notas) {
        if (notas == null || notas.length == 0) {
            return 0.0;
        }
        
        double soma = 0;
        // Percorre o array do índice 0 até o último elemento (notas.length - 1)
        for (int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }
        
        return soma / notas.length;
    }

    // Recebe o array de notas e retorna o maior valor usando o for tradicional
    public static double maiorNota(double[] notas) {
        if (notas == null || notas.length == 0) {
            return 0.0;
        }
        
        double maior = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > maior) {
                maior = notas[i];
            }
        }
        
        return maior;
    }

    public static void main(String[] args) {
        double[] notas = {7.5, 8.0, 6.5, 9.0};
        
        double media = calcularMedia(notas);
        System.out.println("Média: " + media);
        
        double maior = maiorNota(notas);
        System.out.println("Maior nota: " + maior);
    }
}
