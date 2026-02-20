// Programa simples da Torre de Hanói
public class TorreDeHanoiSimples {

    /**
     * Função recursiva para resolver o problema da Torre de Hanói.
     * 
     * @param n        Número de discos a serem movidos
     * @param origem   Haste de onde os discos partem
     * @param destino  Haste para onde os discos devem ir
     * @param auxiliar Haste auxiliar usada como apoio
     */
    static void moverDiscos(int n, char origem, char destino, char auxiliar) {

        // Caso base: quando há apenas um disco a mover
        // Neste ponto, a solução é direta — basta mover o disco da origem para o destino.
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origem + " para " + destino);
            return; // Encerra a chamada recursiva
        }

        // Passo 1: mover n-1 discos da origem para a haste auxiliar
        // Isso libera o maior disco (n) para ser movido diretamente ao destino.
        moverDiscos(n - 1, origem, auxiliar, destino);

        // Passo 2: mover o disco maior (n) da origem para o destino
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);

        // Passo 3: mover os n-1 discos da haste auxiliar para o destino
        // Assim, todos os discos acabam empilhados corretamente no destino.
        moverDiscos(n - 1, auxiliar, destino, origem);
    }

    // Função principal: ponto de entrada do programa
    public static void main(String[] args) {

        int n = 3; // Define o número de discos que serão utilizados na simulação

        // Chama a função recursiva, informando as hastes:
        // 'A' = origem, 'C' = destino, 'B' = auxiliar
        moverDiscos(n, 'A', 'C', 'B');
    }
}
