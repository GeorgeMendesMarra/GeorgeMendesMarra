import java.util.Scanner;

/**
 * Esta classe simula um ataque de 'Força Bruta' (Brute Force).
 * O objetivo é testar todas as combinações possíveis de uma cifra
 * até encontrar uma que seja legível em português/inglês.
 */
public class QuebradorCesar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Sistema de Interceptação (Brute Force) ---");
        System.out.print("Digite a mensagem cifrada para quebrar: ");
        String mensagemCifrada = scanner.nextLine().toLowerCase();

        System.out.println("\nIniciando tentativa de quebra...\n");

        /* * Na Cifra de César, só existem 25 deslocamentos possíveis.
         * O loop abaixo testa cada um deles, um por um.
         */
        for (int shift = 1; shift <= 25; shift++) {
            String tentativa = decifrar(mensagemCifrada, shift);
            
            // Exibimos cada possibilidade para o "analista" humano identificar a correta
            System.out.printf("Tentativa (Shift %02d): %s\n", shift, tentativa);
        }

        System.out.println("\nAnalise as opções acima. A correta saltará aos olhos!");
        scanner.close();
    }

    /**
     * Método que realiza a rotação reversa do alfabeto.
     * @param texto Texto cifrado interceptado
     * @param deslocamento A chave que estamos testando no momento
     * @return O texto resultante deste teste específico
     */
    public static String decifrar(String texto, int deslocamento) {
        StringBuilder sb = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                // 'a' é o nosso marco zero na tabela ASCII/Unicode
                int posicaoAlfabeto = c - 'a';
                
                /* * Aritmética Modular Reversa:
                 * Subtraímos o deslocamento e somamos 26 para evitar números negativos
                 * antes de aplicar o operador de módulo (%).
                 */
                int novaPosicao = (posicaoAlfabeto - deslocamento + 26) % 26;
                
                // Converte a nova posição de volta para um caractere
                sb.append((char) ('a' + novaPosicao));
            } else {
                // Se não for letra (espaço, ponto, etc), apenas mantém o caractere
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
