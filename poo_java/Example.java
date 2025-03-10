public class Example {
    public static void main(String[] args) {
        // Declaração de uma variável 'c' do tipo Counter (inicialmente nula)
        Counter c;

        // Criação de um novo objeto Counter e atribuição à variável 'd'
        Counter d = new Counter();

        // Criação de outro novo objeto Counter e atribuição à variável 'c'
        c = new Counter();

        // Atribuição da referência do objeto 'c' à variável 'd'
        // Agora, 'c' e 'd' apontam para o mesmo objeto Counter na memória
        d = c;

        // A partir deste ponto, qualquer modificação feita em 'c' afetará 'd', e vice-versa,
        // pois ambas as variáveis referenciam o mesmo objeto.

        // Exemplos de como usar os métodos da classe Counter (adicionados para demonstração):
        // c.increment(); // Incrementa o contador do objeto referenciado por 'c' (e 'd')
        // System.out.println(c.getCount()); // Imprime o valor do contador do objeto referenciado por 'c' (e 'd')
    }
}

class Counter {
    // Campo privado para armazenar o valor do contador
    private int count;

    // Construtor da classe Counter, inicializa o contador com 0
    public Counter() {
        count = 0;
    }

    // Método para incrementar o contador
    public void increment() {
        count++;
    }

    // Método para obter o valor atual do contador
    public int getCount() {
        return count;
    }
}
