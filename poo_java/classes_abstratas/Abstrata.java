// -----------------------------------------
// Classe abstrata Animal
// -----------------------------------------
abstract class Animal {

    // Atributo comum a todos os animais
    protected String nome;

    // Construtor da classe abstrata
    public Animal(String nome) {
        this.nome = nome;
    }

    // Método concreto (tem implementação)
    public void dormir() {
        System.out.println(nome + " está dormindo...");
    }

    // Método abstrato (sem implementação)
    // As subclasses são obrigadas a implementar
    public abstract void emitirSom();
}


// -----------------------------------------
// Subclasse concreta Cachorro
// -----------------------------------------
class Cachorro extends Animal {

    // Construtor da subclasse
    public Cachorro(String nome) {
        super(nome); // chama o construtor da classe abstrata
    }

    // Implementação obrigatória do método abstrato
    @Override
    public void emitirSom() {
        System.out.println(nome + " diz: Au Au!");
    }
}


// -----------------------------------------
// Classe principal — a única que pode ser pública
// -----------------------------------------
public class Abstrata {
    public static void main(String[] args) {

        // Não é possível criar um objeto de uma classe abstrata:
        // Animal a = new Animal("Bicho");  // ❌ Isto causaria erro!

        // Podemos criar objetos das subclasses concretas:
        Animal c1 = new Cachorro("Rex");

        // Chamando métodos
        c1.dormir();     // método concreto herdado
        c1.emitirSom();  // implementação específica da subclasse
    }
}
