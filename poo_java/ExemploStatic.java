public class ExemploStatic {

    // Variável estática (compartilhada por todas as instâncias)
    static int contador = 0;

    // Construtor
    public ExemploStatic() {
        contador++; // Incrementa o contador cada vez que um objeto é criado
    }

    // Método estático (pode ser chamado sem criar um objeto)
    public static int obterContador() {
        return contador;
    }

    public static void main(String[] args) {
        // Cria três objetos da classe ExemploStatic
        ExemploStatic obj1 = new ExemploStatic();
        ExemploStatic obj2 = new ExemploStatic();
        ExemploStatic obj3 = new ExemploStatic();

        // Chama o método estático para obter o valor do contador
        System.out.println("Número de objetos criados: " + ExemploStatic.obterContador()); // Saída: 3
    }
}
