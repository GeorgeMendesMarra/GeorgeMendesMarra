// Definição de uma interface chamada "Forma"
interface Forma {
    // Uma interface define um contrato, especificando quais métodos uma classe
    // que a implementa DEVE fornecer. Os métodos em interfaces são implicitamente
    // públicos e abstratos (não possuem implementação).

    // Método para calcular a área da forma.
    double calcularArea();

    // Método para obter o nome da forma.
    String getNome();

    // A partir do Java 8, interfaces podem ter métodos default, que fornecem
    // uma implementação padrão.
    default void exibirInformacoes() {
        System.out.println("Forma: " + getNome() + ", Área: " + calcularArea());
    }

    // A partir do Java 8, interfaces também podem ter métodos estáticos.
    static void mensagemEstatica() {
        System.out.println("Esta é uma mensagem estática da interface Forma.");
    }
}

// Classe "Retangulo" que IMPLEMENTA a interface "Forma"
class Retangulo implements Forma {
    private double largura;
    private double altura;
    private String nome;

    // Construtor da classe Retangulo
    public Retangulo(double largura, double altura, String nome) {
        this.largura = largura;
        this.altura = altura;
        this.nome = nome;
    }

    // Implementação do método calcularArea() definido na interface Forma
    @Override
    public double calcularArea() {
        return largura * altura;
    }

    // Implementação do método getNome() definido na interface Forma
    @Override
    public String getNome() {
        return nome;
    }

    // Podemos sobrescrever um método default da interface, se necessário.
    @Override
    public void exibirInformacoes() {
        System.out.println("Retângulo: " + getNome() + ", Largura: " + largura + ", Altura: " + altura + ", Área: " + calcularArea());
    }
}

// Classe "Circulo" que IMPLEMENTA a interface "Forma"
class Circulo implements Forma {
    private double raio;
    private String nome;

    // Construtor da classe Circulo
    public Circulo(double raio, String nome) {
        this.raio = raio;
        this.nome = nome;
    }

    // Implementação do método calcularArea() definido na interface Forma
    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    // Implementação do método getNome() definido na interface Forma
    @Override
    public String getNome() {
        return nome;
    }
}

// Classe principal para demonstrar o uso de interfaces
public class UsoDeInterfaces {
    public static void main(String[] args) {
        // Criando objetos das classes que implementam a interface Forma
        Retangulo ret = new Retangulo(5, 10, "Retângulo Vermelho");
        Circulo circ = new Circulo(7, "Círculo Azul");

        // Demonstrando o polimorfismo através da interface Forma
        // Variáveis do tipo Forma podem referenciar objetos de qualquer classe que implemente Forma
        Forma forma1 = ret;
        Forma forma2 = circ;

        System.out.println("--- Usando referências do tipo Forma ---");
        System.out.println("Área da " + forma1.getNome() + ": " + forma1.calcularArea()); // Chama o método calcularArea() da classe Retangulo
        System.out.println("Área do " + forma2.getNome() + ": " + forma2.calcularArea()); // Chama o método calcularArea() da classe Circulo

        // Chamando o método default exibirInformacoes()
        System.out.println("\n--- Chamando método default exibirInformacoes() ---");
        forma1.exibirInformacoes(); // Chama a versão sobrescrita em Retangulo
        forma2.exibirInformacoes(); // Chama a versão padrão definida na interface

        // Chamando o método estático da interface
        System.out.println("\n--- Chamando método estático da interface ---");
        Forma.mensagemEstatica();

        // Podemos também acessar os objetos diretamente pelo seu tipo de classe
        System.out.println("\n--- Acessando objetos diretamente pelo seu tipo ---");
        ret.exibirInformacoes();
        circ.exibirInformacoes();
    }
}

/*
 * Explicação do uso de interfaces em Java:
 *
 * 1. Contrato: Uma interface define um contrato que as classes devem seguir.
 * Qualquer classe que implementa uma interface promete fornecer implementações
 * para todos os métodos definidos nessa interface.
 *
 * 2. Abstração: Interfaces promovem a abstração, focando no "o quê" uma classe pode fazer,
 * em vez de "como" ela faz. Isso permite criar código mais flexível e desacoplado.
 *
 * 3. Polimorfismo: Interfaces habilitam o polimorfismo. Uma variável do tipo interface
 * pode referenciar objetos de diferentes classes que implementam essa interface.
 * No exemplo, 'forma1' e 'forma2' são do tipo 'Forma', mas referenciam um 'Retangulo'
 * e um 'Circulo', respectivamente. Quando chamamos 'forma1.calcularArea()', o método
 * específico da classe 'Retangulo' é executado.
 *
 * 4. Herança Múltipla de Tipos: Java não permite herança múltipla de classes, mas uma
 * classe pode implementar múltiplas interfaces. Isso permite que uma classe "herde"
 * múltiplos tipos (contratos).
 *
 * 5. Métodos Default (Java 8): Permitem que interfaces forneçam uma implementação
 * padrão para um método. As classes implementadoras podem usar essa implementação
 * ou sobrescrevê-la. Isso facilita a evolução de interfaces sem quebrar o código
 * das classes que já as implementam.
 *
 * 6. Métodos Estáticos (Java 8): Permitem definir métodos estáticos dentro de interfaces.
 * Esses métodos são chamados diretamente na interface (ex: Forma.mensagemEstatica()).
 *
 * Benefícios do uso de interfaces:
 * - Maior flexibilidade e extensibilidade do código.
 * - Melhor desacoplamento entre componentes.
 * - Facilita a criação de sistemas baseados em contratos.
 * - Suporte ao polimorfismo.
 */
