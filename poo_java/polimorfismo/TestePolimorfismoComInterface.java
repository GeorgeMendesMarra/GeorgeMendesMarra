// 1. A Interface (Define o Contrato)
// A interface 'Forma' estabelece um método comum que todas as classes que a implementarem devem ter.
interface Forma {
    // Este é o método polimórfico. Ele é a "forma" que será compartilhada,
    // mas a "implementação" (o cálculo) será diferente.
    double calcularArea();
}

// 2. Primeira Implementação Concreta (A 'Forma' do Círculo)
class Circulo implements Forma {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    // Sobrescrita do método 'calcularArea' da interface 'Forma'.
    // Aqui está a implementação específica para o Círculo.
    @Override
    public double calcularArea() {
        // Fórmula da área do Círculo: pi * r^2
        return Math.PI * raio * raio;
    }
}

// 3. Segunda Implementação Concreta (A 'Forma' do Retângulo)
class Retangulo implements Forma {
    private double largura;
    private double altura;

    public Retangulo(double largura, double altura) {
        this.largura = largura;
    }

    // Sobrescrita do método 'calcularArea' da interface 'Forma'.
    // Aqui está a implementação específica para o Retângulo.
    @Override
    public double calcularArea() {
        // Fórmula da área do Retângulo: largura * altura
        return largura * altura;
    }
}

// 4. Classe Principal para Demonstração do Polimorfismo
public class TestePolimorfismoComInterface {
    public static void main(String[] args) {
        // O POLIMORFISMO EM AÇÃO:
        // As variáveis (referências) são do tipo genérico 'Forma' (a interface).
        // Isso permite que tratemos objetos de diferentes classes (Circulo e Retangulo)
        // de maneira uniforme.

        Forma meuCirculo = new Circulo(5.0); // O objeto real é um Círculo
        Forma meuRetangulo = new Retangulo(4.0, 6.0); // O objeto real é um Retângulo

        // Chamada Polimórfica:
        // Apesar de chamarmos 'calcularArea()' na referência 'Forma',
        // o Java, em tempo de execução, sabe qual método específico chamar
        // (o do Círculo ou o do Retângulo).

        System.out.println("Área do Círculo (pi * 5 * 5): " + meuCirculo.calcularArea());
        System.out.println("Área do Retângulo (4 * 6): " + meuRetangulo.calcularArea());

        // O benefício: Podemos adicionar um novo tipo de forma (ex: Triangulo) no futuro,
        // e se ele implementar a interface 'Forma', ele funcionará com o mesmo código
        // sem precisar alterar nada aqui. Isso demonstra a flexibilidade do polimorfismo.
    }
}
