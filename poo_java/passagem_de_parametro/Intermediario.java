import java.util.ArrayList;
import java.util.List;

/**
 * NÍVEL INTERMEDIÁRIO - Objetos, coleções, final, cópia defensiva
 *
 * Execute: java Intermediario.java
 */
public class Intermediario {

    static class Pessoa {
        String nome;
        int idade;

        Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        @Override
        public String toString() {
            return nome + " (" + idade + ")";
        }
    }

    record Ponto(int x, int y) { }

    // ---------------------------------------------------------------
    // Exemplo 1: alterar o ESTADO do objeto x REATRIBUIR a referência.
    // ---------------------------------------------------------------
    static void fazerAniversario(Pessoa p) {
        p.idade = p.idade + 1;               // altera o objeto: o chamador VÊ
    }

    static void trocarPessoa(Pessoa p) {
        p = new Pessoa("Outra Pessoa", 99);  // altera só a cópia: o chamador NÃO vê
    }

    // ---------------------------------------------------------------
    // Exemplo 2: provando que a referência é copiada.
    // identityHashCode identifica o objeto na memória.
    // ---------------------------------------------------------------
    static void mostrarIdentidade(Pessoa p) {
        System.out.println("  parâmetro  -> objeto " + System.identityHashCode(p));
        p = new Pessoa("Novo", 1);
        System.out.println("  após new   -> objeto " + System.identityHashCode(p));
    }

    // ---------------------------------------------------------------
    // Exemplo 3: coleções. add() muda o objeto; reatribuir não.
    // ---------------------------------------------------------------
    static void adicionar(List<String> lista) {
        lista.add("adicionado dentro do método");
    }

    static void reatribuir(List<String> lista) {
        lista = new ArrayList<>();
        lista.add("lista nova (só existe aqui dentro)");
    }

    // ---------------------------------------------------------------
    // Exemplo 4: swap que funciona. Trocamos o CONTEÚDO de um array.
    // ---------------------------------------------------------------
    static void trocar(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    // ---------------------------------------------------------------
    // Exemplo 5: final no parâmetro impede reatribuir, mas NÃO impede
    // alterar o objeto apontado.
    // ---------------------------------------------------------------
    static void comFinal(final Pessoa p) {
        // p = new Pessoa("X", 1);   // ERRO de compilação
        p.nome = "Alterado mesmo com final";
    }

    // ---------------------------------------------------------------
    // Exemplo 6: matriz = array de arrays. Copia-se a referência da
    // matriz, e as linhas continuam compartilhadas.
    // ---------------------------------------------------------------
    static void zerarDiagonal(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            matriz[i][i] = 0;
        }
    }

    // ---------------------------------------------------------------
    // Exemplo 7: cópia defensiva. Protege o original de alterações.
    // ---------------------------------------------------------------
    static int somaSemRisco(int[] original) {
        int[] copia = original.clone();
        copia[0] = 1000;                      // altera só a cópia
        int soma = 0;
        for (int n : copia) {
            soma += n;
        }
        return soma;
    }

    static List<String> protegida(List<String> original) {
        return List.copyOf(original);         // lista imutável independente
    }

    // ---------------------------------------------------------------
    // Exemplo 8: record é imutável. "Alterar" = criar outro e retornar.
    // ---------------------------------------------------------------
    static Ponto moverDireita(Ponto p) {
        return new Ponto(p.x() + 1, p.y());
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Alterar estado x reatribuir ===");
        Pessoa ana = new Pessoa("Ana", 30);
        fazerAniversario(ana);
        System.out.println("  após fazerAniversario: " + ana);
        trocarPessoa(ana);
        System.out.println("  após trocarPessoa:     " + ana);

        System.out.println("\n=== 2. Identidade na memória ===");
        Pessoa bia = new Pessoa("Bia", 25);
        System.out.println("  variável   -> objeto " + System.identityHashCode(bia));
        mostrarIdentidade(bia);
        System.out.println("  variável   -> objeto " + System.identityHashCode(bia));

        System.out.println("\n=== 3. Coleções ===");
        List<String> itens = new ArrayList<>();
        adicionar(itens);
        reatribuir(itens);
        System.out.println("  " + itens);

        System.out.println("\n=== 4. Swap que funciona ===");
        int[] v = {1, 2, 3};
        trocar(v, 0, 2);
        System.out.println("  v = [" + v[0] + ", " + v[1] + ", " + v[2] + "]");

        System.out.println("\n=== 5. final no parâmetro ===");
        Pessoa caio = new Pessoa("Caio", 40);
        comFinal(caio);
        System.out.println("  " + caio);

        System.out.println("\n=== 6. Matriz ===");
        int[][] m = {{1, 2}, {3, 4}};
        zerarDiagonal(m);
        System.out.println("  [" + m[0][0] + "," + m[0][1] + "] [" + m[1][0] + "," + m[1][1] + "]");

        System.out.println("\n=== 7. Cópia defensiva ===");
        int[] dados = {1, 2, 3};
        System.out.println("  soma na cópia = " + somaSemRisco(dados));
        System.out.println("  dados[0] original = " + dados[0]);
        List<String> base = new ArrayList<>(List.of("a", "b"));
        List<String> copia = protegida(base);
        base.add("c");
        System.out.println("  base = " + base + " | cópia = " + copia);

        System.out.println("\n=== 8. Record imutável ===");
        Ponto p = new Ponto(0, 0);
        moverDireita(p);
        System.out.println("  sem usar o retorno: " + p);
        p = moverDireita(p);
        System.out.println("  usando o retorno:   " + p);
    }
}
