/**
 * NÍVEL SIMPLES - Passagem de parâmetro por valor em Java
 *
 * Regra única: Java SEMPRE passa uma CÓPIA do valor da variável.
 *  - Primitivos: a cópia é o próprio dado.
 *  - Objetos/arrays: a cópia é o ENDEREÇO (referência) do objeto.
 *
 * Execute: java Simples.java
 */
public class Simples {

    // ---------------------------------------------------------------
    // Exemplo 1: primitivo. O método mexe na CÓPIA, não no original.
    // ---------------------------------------------------------------
    static void incrementar(int numero) {
        numero = numero + 10;
        System.out.println("  dentro do método: numero = " + numero);
    }

    // ---------------------------------------------------------------
    // Exemplo 2: o "swap" clássico que NÃO funciona em Java.
    // ---------------------------------------------------------------
    static void trocarQueNaoFunciona(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("  dentro do método: a = " + a + ", b = " + b);
    }

    // ---------------------------------------------------------------
    // Exemplo 3: String é imutável. Atribuir ao parâmetro não altera
    // a variável de quem chamou.
    // ---------------------------------------------------------------
    static void deixarMaiuscula(String texto) {
        texto = texto.toUpperCase();
        System.out.println("  dentro do método: texto = " + texto);
    }

    // ---------------------------------------------------------------
    // Exemplo 4: wrappers (Integer, Double...) também são imutáveis.
    // ---------------------------------------------------------------
    static void somarUm(Integer valor) {
        valor = valor + 1;
        System.out.println("  dentro do método: valor = " + valor);
    }

    // ---------------------------------------------------------------
    // Exemplo 5: array. Alterar o CONTEÚDO é visível para quem chamou,
    // pois as duas variáveis apontam para o mesmo array.
    // ---------------------------------------------------------------
    static void zerarPrimeiro(int[] vetor) {
        vetor[0] = 0;
    }

    // ---------------------------------------------------------------
    // Exemplo 6: array. REATRIBUIR o parâmetro só muda a cópia local
    // da referência. O array original continua intacto.
    // ---------------------------------------------------------------
    static void trocarArray(int[] vetor) {
        vetor = new int[]{99, 99, 99};
        System.out.println("  dentro do método: vetor[0] = " + vetor[0]);
    }

    // ---------------------------------------------------------------
    // Exemplo 7: a solução para "alterar" um primitivo é RETORNAR.
    // ---------------------------------------------------------------
    static int dobrar(int numero) {
        return numero * 2;
    }

    // ---------------------------------------------------------------
    // Exemplo 8: a aula teste - média das notas recebendo um array.
    // ---------------------------------------------------------------
    static double calcularMedia(double[] notas) {
        double soma = 0;
        for (int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }
        return soma / notas.length;
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Primitivo ===");
        int idade = 20;
        incrementar(idade);
        System.out.println("  fora do método:   idade = " + idade);

        System.out.println("\n=== 2. Swap que não funciona ===");
        int x = 1, y = 2;
        trocarQueNaoFunciona(x, y);
        System.out.println("  fora do método:   x = " + x + ", y = " + y);

        System.out.println("\n=== 3. String é imutável ===");
        String nome = "java";
        deixarMaiuscula(nome);
        System.out.println("  fora do método:   nome = " + nome);

        System.out.println("\n=== 4. Wrapper Integer é imutável ===");
        Integer contador = 5;
        somarUm(contador);
        System.out.println("  fora do método:   contador = " + contador);

        System.out.println("\n=== 5. Array: alterar o conteúdo ===");
        int[] numeros = {7, 8, 9};
        zerarPrimeiro(numeros);
        System.out.println("  fora do método:   numeros[0] = " + numeros[0]);

        System.out.println("\n=== 6. Array: reatribuir a referência ===");
        int[] outros = {1, 2, 3};
        trocarArray(outros);
        System.out.println("  fora do método:   outros[0] = " + outros[0]);

        System.out.println("\n=== 7. Retornando o resultado ===");
        int valor = 21;
        valor = dobrar(valor);
        System.out.println("  fora do método:   valor = " + valor);

        System.out.println("\n=== 8. Média das notas (aula teste) ===");
        double[] notas = {7.5, 8.0, 9.5, 6.0};
        System.out.println("  média = " + calcularMedia(notas));
    }
}
