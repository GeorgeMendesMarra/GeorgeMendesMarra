import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * NÍVEL COMPLEXO - Estruturas de dados, cópia rasa x profunda,
 * lambdas e simulação de "parâmetro de saída".
 *
 * Execute: java Complexo.java
 */
public class Complexo {

    // =================================================================
    // 1. LISTA ENCADEADA: por que métodos de inserção RETORNAM a cabeça
    // =================================================================
    static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
        }
    }

    // ERRADO: reatribui a cópia local de "cabeca". O chamador não vê.
    static void inserirNoInicioErrado(No cabeca, int valor) {
        No novo = new No(valor);
        novo.proximo = cabeca;
        cabeca = novo;
    }

    // CERTO: devolve a nova cabeça e o chamador a recebe.
    static No inserirNoInicio(No cabeca, int valor) {
        No novo = new No(valor);
        novo.proximo = cabeca;
        return novo;
    }

    // Inserir no fim ALTERA o objeto (proximo), então funciona sem retorno,
    // exceto quando a lista está vazia (cabeça nula): aí é preciso retornar.
    static No inserirNoFim(No cabeca, int valor) {
        No novo = new No(valor);
        if (cabeca == null) {
            return novo;
        }
        No atual = cabeca;
        while (atual.proximo != null) {
            atual = atual.proximo;
        }
        atual.proximo = novo;
        return cabeca;
    }

    static String listar(No cabeca) {
        StringBuilder sb = new StringBuilder();
        for (No n = cabeca; n != null; n = n.proximo) {
            sb.append(n.valor).append(n.proximo != null ? " -> " : "");
        }
        return sb.length() == 0 ? "(vazia)" : sb.toString();
    }

    // =================================================================
    // 2. ÁRVORE BINÁRIA DE BUSCA: inserção recursiva com retorno
    // =================================================================
    static class NoArvore {
        int valor;
        NoArvore esquerda, direita;

        NoArvore(int valor) {
            this.valor = valor;
        }
    }

    static NoArvore inserir(NoArvore raiz, int valor) {
        if (raiz == null) {
            return new NoArvore(valor);
        }
        if (valor < raiz.valor) {
            raiz.esquerda = inserir(raiz.esquerda, valor);
        } else {
            raiz.direita = inserir(raiz.direita, valor);
        }
        return raiz;
    }

    static void emOrdem(NoArvore raiz, StringBuilder saida) {
        if (raiz == null) {
            return;
        }
        emOrdem(raiz.esquerda, saida);
        saida.append(raiz.valor).append(' ');
        emOrdem(raiz.direita, saida);
    }

    // =================================================================
    // 3. CÓPIA RASA x PROFUNDA
    // =================================================================
    static class Item {
        String descricao;

        Item(String descricao) {
            this.descricao = descricao;
        }
    }

    static class Pedido {
        String cliente;
        List<Item> itens = new ArrayList<>();

        Pedido(String cliente) {
            this.cliente = cliente;
        }

        // Cópia RASA: nova lista, mas os mesmos objetos Item
        Pedido copiaRasa() {
            Pedido c = new Pedido(cliente);
            c.itens = new ArrayList<>(itens);
            return c;
        }

        // Cópia PROFUNDA: novos Item também
        Pedido copiaProfunda() {
            Pedido c = new Pedido(cliente);
            for (Item i : itens) {
                c.itens.add(new Item(i.descricao));
            }
            return c;
        }
    }

    static void marcarComoEntregue(Pedido p) {
        p.itens.get(0).descricao += " [ENTREGUE]";
    }

    // =================================================================
    // 4. SIMULANDO "PARÂMETRO DE SAÍDA" (Java não tem ref/out como C#)
    // =================================================================
    // Opção A: AtomicInteger funciona como caixa mutável.
    static void contarPares(int[] numeros, AtomicInteger resultado) {
        int total = 0;
        for (int n : numeros) {
            if (n % 2 == 0) {
                total++;
            }
        }
        resultado.set(total);
    }

    // Opção B: devolver um record com vários valores (mais elegante).
    record Estatisticas(int minimo, int maximo, double media) { }

    static Estatisticas calcular(int[] numeros) {
        int min = numeros[0], max = numeros[0], soma = 0;
        for (int n : numeros) {
            min = Math.min(min, n);
            max = Math.max(max, n);
            soma += n;
        }
        return new Estatisticas(min, max, (double) soma / numeros.length);
    }

    // =================================================================
    // 5. LAMBDAS: a variável capturada é uma CÓPIA (effectively final)
    // =================================================================
    static void executar(Consumer<String> acao) {
        acao.accept("Java");
    }

    // =================================================================
    // 6. VARARGS: o parâmetro "..." chega como array
    // =================================================================
    static int somar(int... valores) {
        valores[0] = 0;                       // altera o array criado na chamada
        int soma = 0;
        for (int v : valores) {
            soma += v;
        }
        return soma;
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Lista encadeada ===");
        No lista = null;
        inserirNoInicioErrado(lista, 1);
        System.out.println("  após método errado:  " + listar(lista));
        lista = inserirNoInicio(lista, 2);
        lista = inserirNoInicio(lista, 1);
        lista = inserirNoFim(lista, 3);
        System.out.println("  após método correto: " + listar(lista));

        System.out.println("\n=== 2. Árvore binária ===");
        NoArvore raiz = null;
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            raiz = inserir(raiz, v);
        }
        StringBuilder saida = new StringBuilder();
        emOrdem(raiz, saida);
        System.out.println("  em ordem: " + saida.toString().trim());

        System.out.println("\n=== 3. Cópia rasa x profunda ===");
        Pedido original = new Pedido("Maria");
        original.itens.add(new Item("Notebook"));
        Pedido rasa = original.copiaRasa();
        Pedido profunda = original.copiaProfunda();
        marcarComoEntregue(rasa);
        System.out.println("  original: " + original.itens.get(0).descricao);
        System.out.println("  rasa:     " + rasa.itens.get(0).descricao);
        System.out.println("  profunda: " + profunda.itens.get(0).descricao);

        System.out.println("\n=== 4. Parâmetro de saída ===");
        int[] numeros = {4, 7, 10, 3, 8};
        AtomicInteger pares = new AtomicInteger();
        contarPares(numeros, pares);
        System.out.println("  pares = " + pares.get());
        Estatisticas e = calcular(numeros);
        System.out.println("  " + e);

        System.out.println("\n=== 5. Lambda e variável capturada ===");
        String prefixo = "Olá, ";
        executar(nome -> System.out.println("  " + prefixo + nome));
        // prefixo = "Oi, ";   // ERRO: a lambda exige variável efetivamente final

        System.out.println("\n=== 6. Varargs ===");
        int[] vetor = {1, 2, 3};
        System.out.println("  soma = " + somar(vetor));
        System.out.println("  vetor[0] = " + vetor[0] + "  (o array foi passado direto)");
        System.out.println("  soma = " + somar(1, 2, 3) + "  (array criado na chamada)");
    }
}
