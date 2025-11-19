/**
 * EightQueens
 *
 * Resolve o problema das 8 rainhas (n-queens) por backtracking.
 * O programa imprime todas as soluções possíveis e conta quantas foram encontradas.
 *
 * Como funciona (resumo):
 * - O tabuleiro é representado implicitamente por um array int[] cols onde cols[row] = column da rainha na linha 'row'.
 * - Arrays booleanos são usados para marcar colunas e diagonais ocupadas:
 *      - colsUsed[c]     => se a coluna c já tem uma rainha
 *      - diag1Used[d]    => diagonal principal (row - col) mapeada para índice
 *      - diag2Used[d]    => diagonal secundária (row + col) mapeada para índice
 * - Backtracking: tenta colocar uma rainha por linha, verificando conflitos em O(1).
 *
 * Para alterar N (número de rainhas), altere a constante N.
 */
public class OitoRainhas {

    private final int N;                // número de rainhas / tamanho do tabuleiro
    private final int[] cols;           // cols[row] = coluna onde está a rainha na linha 'row'
    private final boolean[] colsUsed;   // colunas ocupadas
    private final boolean[] diag1Used;  // diagonais principal (row - col + (N-1))
    private final boolean[] diag2Used;  // diagonais secundária (row + col)
    private int solutionCount = 0;

    /**
     * Construtor.
     *
     * @param n número de rainhas / dimensão do tabuleiro
     */
    public OitoRainhas(int n) {
        this.N = n;
        this.cols = new int[n];
        this.colsUsed = new boolean[n];
        // diag1 index range: -(N-1) .. (N-1) -> shift by (N-1) to map to 0..2N-2
        this.diag1Used = new boolean[2 * n - 1];
        // diag2 index range: 0 .. 2N-2
        this.diag2Used = new boolean[2 * n - 1];
    }

    /**
     * Inicia a busca por soluções.
     */
    public void solve() {
        backtrack(0);
        System.out.printf("Total de soluções para %d rainhas: %d%n", N, solutionCount);
    }

    /**
     * Backtracking recursivo: tenta posicionar uma rainha na linha 'row'.
     *
     * @param row linha atual (0-indexed)
     */
    private void backtrack(int row) {
        if (row == N) {
            // encontrou uma solução completa
            solutionCount++;
            printSolution();
            return;
        }

        for (int col = 0; col < N; col++) {
            int d1 = row - col + (N - 1);
            int d2 = row + col;
            if (colsUsed[col] || diag1Used[d1] || diag2Used[d2]) {
                continue; // conflito, não pode colocar aqui
            }

            // coloca a rainha
            cols[row] = col;
            colsUsed[col] = true;
            diag1Used[d1] = true;
            diag2Used[d2] = true;

            // next row
            backtrack(row + 1);

            // remove (backtrack)
            colsUsed[col] = false;
            diag1Used[d1] = false;
            diag2Used[d2] = false;
        }
    }

    /**
     * Imprime a solução atual (representada por cols[]).
     * Usa 'Q' para rainha e '.' para casa vazia.
     */
    private void printSolution() {
        System.out.printf("Solução %d:%n", solutionCount);
        for (int row = 0; row < N; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < N; col++) {
                if (cols[row] == col) sb.append('Q').append(' ');
                else sb.append('.').append(' ');
            }
            System.out.println(sb.toString().trim());
        }
        System.out.println();
    }

    /**
     * Main para executar o programa.
     * Por padrão executa para N = 8. Altere args[0] para usar outro N.
     */
    public static void main(String[] args) {
        int n = 8; // padrão
        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
                if (n <= 0) {
                    System.err.println("N deve ser maior que zero. Usando N = 8.");
                    n = 8;
                }
            } catch (NumberFormatException e) {
                System.err.println("Argumento inválido. Usando N = 8.");
                n = 8;
            }
        }

        OitoRainhas solver = new OitoRainhas(n);
        solver.solve();
    }
}
