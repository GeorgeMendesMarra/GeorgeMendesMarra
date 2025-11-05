import java.util.Stack;

/**
 * TorreDeHanoiIterativo.java
 * 
 * Implementação iterativa da Torre de Hanói usando 3 pilhas.
 * 
 * Esta versão elimina a recursão e simula os movimentos manualmente.
 * 
 * 
 */
public class TorreDeHanoiIterativo {

    /**
     * Classe auxiliar que representa uma torre (pino).
     * Cada torre possui uma pilha de discos e um nome identificador.
     */
    private static class Torre {
        Stack<Integer> discos = new Stack<>();
        char nome;

        Torre(char nome) { 
            this.nome = nome; 
        }

        // Adiciona um disco no topo da torre
        void push(int d) { 
            discos.push(d); 
        }

        // Remove e retorna o topo da pilha; se estiver vazia, retorna valor grande
        int popSafe() { 
            return discos.isEmpty() ? Integer.MAX_VALUE : discos.pop(); 
        }

        // Retorna o disco do topo sem remover; se vazia, retorna valor grande
        int topSafe() { 
            return discos.isEmpty() ? Integer.MAX_VALUE : discos.peek(); 
        }
    }

    /**
     * Executa um movimento válido entre duas torres (origem e destino).
     * Verifica qual torre pode receber o disco menor.
     */
    private static void moverDiscoEntre(Torre src, Torre dest) {
        int sTop = src.topSafe(); // Disco do topo da torre de origem
        int dTop = dest.topSafe(); // Disco do topo da torre de destino

        // Caso 1: torre de origem vazia -> mover da torre destino para a origem
        if (sTop == Integer.MAX_VALUE) {
            src.push(dest.popSafe());
            System.out.printf("Move disco %d de %c para %c%n", src.topSafe(), dest.nome, src.nome);

        // Caso 2: torre de destino vazia -> mover da origem para o destino
        } else if (dTop == Integer.MAX_VALUE) {
            dest.push(src.popSafe());
            System.out.printf("Move disco %d de %c para %c%n", dest.topSafe(), src.nome, dest.nome);

        // Caso 3: topo da origem maior que o da destino -> mover da destino para origem
        } else if (sTop > dTop) {
            src.push(dest.popSafe());
            System.out.printf("Move disco %d de %c para %c%n", src.topSafe(), dest.nome, src.nome);

        // Caso 4: topo da origem menor -> mover da origem para o destino
        } else {
            dest.push(src.popSafe());
            System.out.printf("Move disco %d de %c para %c%n", dest.topSafe(), src.nome, dest.nome);
        }
    }

    public static void main(String[] args) {
        int n = 3; // número padrão de discos

        // Permite receber n via linha de comando
        if (args.length > 0) {
            try { 
                n = Integer.parseInt(args[0]); 
            } catch (NumberFormatException e) { 
                System.err.println("Argumento inválido. Usando n = 3."); 
            }
        }

        // Criação das três torres (A, B e C)
        Torre A = new Torre('A');
        Torre B = new Torre('B');
        Torre C = new Torre('C');

        // Inicializa a torre A com os discos em ordem decrescente (maior embaixo)
        for (int i = n; i >= 1; i--) 
            A.push(i);

        // Cálculo do número total de movimentos: 2^n - 1
        int totalMovimentos = (1 << n) - 1;

        // Define qual torre será o destino inicial, dependendo da paridade de n
        Torre origem = A, destino = C, auxiliar = B;
        if (n % 2 == 0) { // se n é par, troca destino e auxiliar
            destino = B;
            auxiliar = C;
        }

        // Executa os movimentos um por um
        for (int i = 1; i <= totalMovimentos; i++) {
            if (i % 3 == 1) {
                moverDiscoEntre(origem, destino);
            } else if (i % 3 == 2) {
                moverDiscoEntre(origem, auxiliar);
            } else {
                moverDiscoEntre(auxiliar, destino);
            }
        }

        // Exibe o total de movimentos ao final
        System.out.printf("Total de movimentos: %d%n", totalMovimentos);
    }
}
