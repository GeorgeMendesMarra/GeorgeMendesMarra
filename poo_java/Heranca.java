// Classe principal com o método main
public class Heranca {
    public static void main(String[] args) {
        // Objeto da superclasse
        Animal a = new Animal();
        a.emitirSom();  // Saída: O animal faz um som.

        // Objeto da subclasse Cachorro
        Cachorro c = new Cachorro();
        c.nome = "Rex";
        c.emitirSom();  // Saída: O cachorro late: Au Au!
        System.out.println("Nome do cachorro: " + c.nome);

        // Objeto da subclasse Gato
        Gato g = new Gato();
        g.nome = "Mimi";
        g.emitirSom();  // Saída: O gato mia: Miau!
        System.out.println("Nome do gato: " + g.nome);
    }
}

// Superclasse Animal
class Animal {
    String nome;

    public void emitirSom() {
        System.out.println("O animal faz um som.");
    }
}

// Subclasse Cachorro
class Cachorro extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("O cachorro late: Au Au!");
    }
}

// Subclasse Gato
class Gato extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("O gato mia: Miau!");
    }
}

