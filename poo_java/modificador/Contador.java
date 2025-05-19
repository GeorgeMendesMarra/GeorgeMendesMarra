public class Contador {
    // Declara uma variável estática privada chamada 'count' do tipo inteiro e a inicializa com o valor 0.
    // O modificador 'static' significa que esta variável pertence à classe 'Contador' em si,
    // em vez de a qualquer objeto específico criado a partir desta classe.
    // 'private' restringe o acesso direto a esta variável apenas dentro da classe 'Contador'.
    private static int count = 0;

    /**
     * Construtor da classe Contador. Este método é automaticamente chamado quando um novo objeto da classe 'Contador' é criado usando a palavra-chave 'new'.
     */
    public Contador() {
        // Incrementa o valor da variável estática 'count' em 1 cada vez que um novo objeto 'Contador' é instanciado.
        // Como 'count' é estática, essa modificação é refletida em todas as instâncias da classe e na própria classe.
        count++;
        // Imprime uma mensagem no console indicando que um novo objeto foi criado e exibe o valor atual do contador de objetos.
        System.out.println("Novo objeto Contador criado. Count atual: " + count);
    }

    /**
     * Retorna o valor atual da variável estática 'count'.
     * O modificador 'static' permite que este método seja chamado diretamente na classe 'Contador'
     * (por exemplo, Contador.getCount()) sem a necessidade de criar um objeto 'Contador' primeiro.
     * 'public' torna este método acessível de qualquer outra classe ou parte do programa.
     * 'int' indica que este método retorna um valor inteiro.
     *
     * @return O número total de objetos 'Contador' que foram criados até o momento.
     */
    public static int getCount() {
        return count; // Retorna o valor atual da variável estática 'count'.
    }

    /**
     * Método principal onde a execução do programa Java começa.
     * 'public' torna este método acessível pela Java Virtual Machine (JVM).
     * 'static' permite que este método seja executado sem a necessidade de criar um objeto da classe 'Contador'.
     * 'void' indica que este método não retorna nenhum valor.
     * 'String[] args' são os argumentos de linha de comando que podem ser passados para o programa.
     *
     * @param args Um array de strings contendo os argumentos de linha de comando.
     */
    public static void main(String[] args) {
        // Imprime o valor inicial do contador de objetos chamando o método estático 'getCount()' diretamente na classe 'Contador'.
        // Neste ponto, nenhum objeto 'Contador' foi criado, então o valor deve ser o da inicialização (0).
        System.out.println("Contador inicial: " + Contador.getCount());

        // Cria um novo objeto da classe 'Contador' usando a palavra-chave 'new'.
        // Isso fará com que o construtor 'Contador()' seja executado, incrementando a variável 'count'.
        Contador obj1 = new Contador();

        // Cria outro objeto da classe 'Contador'. O construtor será executado novamente, incrementando 'count' mais uma vez.
        Contador obj2 = new Contador();

        // Cria um terceiro objeto 'Contador', resultando em outro incremento de 'count'.
        Contador obj3 = new Contador();

        // Imprime o valor final do contador de objetos após a criação dos três objetos, chamando novamente o método estático 'getCount()'.
        // O valor impresso deve ser 3, pois o construtor foi chamado três vezes.
        System.out.println("Contador final: " + Contador.getCount());
    }
}
