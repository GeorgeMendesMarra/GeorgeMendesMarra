public class VetorBooleano {
    public static void main(String[] args) {
        // Declara e inicializa um vetor de booleanos ('true' ou 'false') com 3 posições.
        boolean[] status = new boolean[3];

        // Atribui valores a cada posição.
        status[0] = true;
        status[1] = false;
        status[2] = true;

        // Usa uma estrutura 'if-else' para verificar o valor da primeira posição.
        if (status[0]) {
            // Este bloco é executado se o valor for 'true'.
            System.out.println("O primeiro status está ativo.");
        } else {
            // Este bloco é executado se o valor for 'false'.
            System.out.println("O primeiro status está inativo.");
        }
    }
}
