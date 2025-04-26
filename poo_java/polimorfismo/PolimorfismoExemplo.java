// Classe base Animal
class Animal {
    // Método para o animal fazer um som
    public void fazerSom() {
        System.out.println("Som genérico de animal");
    }
}

// Classe Cachorro que herda de Animal
class Cachorro extends Animal {
    // Sobrescreve (override) o método fazerSom da classe base
    @Override
    public void fazerSom() {
        System.out.println("Au au!");
    }

    // Método específico da classe Cachorro
    public void latirAlto() {
        System.out.println("AU AU AU!");
    }
}

// Classe Gato que herda de Animal
class Gato extends Animal {
    // Sobrescreve (override) o método fazerSom da classe base
    @Override
    public void fazerSom() {
        System.out.println("Miau!");
    }

    // Método específico da classe Gato
    public void ronronar() {
        System.out.println("Rrrrr...");
    }
}

public class PolimorfismoExemplo {
    public static void main(String[] args) {
        // Cria uma referência do tipo Animal que aponta para um objeto Cachorro
        Animal animal1 = new Cachorro();
        // Cria uma referência do tipo Animal que aponta para um objeto Gato
        Animal animal2 = new Gato();
        // Cria uma referência diretamente para um objeto Cachorro
        Cachorro cachorro1 = new Cachorro();

        System.out.println("--- Chamando fazerSom() através de referências Animal ---");
        // Chama o método fazerSom() na referência animal1 (que é um Cachorro)
        // O método fazerSom() da classe Cachorro será executado (polimorfismo em ação)
        animal1.fazerSom(); // Saída: Au au!

        // Chama o método fazerSom() na referência animal2 (que é um Gato)
        // O método fazerSom() da classe Gato será executado (polimorfismo em ação)
        animal2.fazerSom(); // Saída: Miau!

        System.out.println("\n--- Chamando fazerSom() diretamente em objetos ---");
        // Chama o método fazerSom() diretamente no objeto cachorro1
        cachorro1.fazerSom(); // Saída: Au au!

        System.out.println("\n--- Chamando métodos específicos ---");
        // Chama o método latirAlto() que é específico da classe Cachorro
        cachorro1.latirAlto(); // Saída: AU AU AU!

        // Tentar chamar um método específico em uma referência Animal causaria um erro de compilação
        // pois a classe Animal não possui o método latirAlto()
        // animal1.latirAlto(); // Isso geraria um erro de compilação

        // Para chamar um método específico de uma subclasse através de uma referência da superclasse,
        // é necessário fazer um casting (conversão de tipo).
        if (animal1 instanceof Cachorro) {
            Cachorro cachorroCast = (Cachorro) animal1;
            cachorroCast.latirAlto(); // Saída: AU AU AU!
        }

        if (animal2 instanceof Gato) {
            Gato gatoCast = (Gato) animal2;
            gatoCast.ronronar(); // Saída: Rrrrr...
        }

        System.out.println("\n--- Polimorfismo em um array ---");
        // Cria um array de referências do tipo Animal
        Animal[] animais = new Animal[3];
        animais[0] = new Animal();
        animais[1] = new Cachorro();
        animais[2] = new Gato();

        // Itera pelo array de animais e chama o método fazerSom() em cada elemento
        // O comportamento do método fazerSom() será diferente dependendo do tipo real do objeto
        for (Animal animal : animais) {
            System.out.print("O animal ");
            if (animal instanceof Cachorro) {
                System.out.print("cachorro ");
            } else if (animal instanceof Gato) {
                System.out.print("gato ");
            } else {
                System.out.print("genérico ");
            }
            animal.fazerSom();
        }
        /*
        Saída do loop:
        O animal genérico som genérico de animal
        O animal cachorro Au au!
        O animal gato Miau!
        */
    }
}

/*
 * Explicação do Polimorfismo neste exemplo:
 *
 * 1. Herança: As classes Cachorro e Gato herdam da classe Animal. Isso significa que elas
 * possuem as características (métodos e atributos) da classe Animal.
 *
 * 2. Sobrescrita de Método (Override): As classes Cachorro e Gato fornecem uma implementação
 * específica para o método fazerSom() que já existia na classe Animal. Isso permite que
 * cada tipo de animal emita seu próprio som característico.
 *
 * 3. Referências da Superclasse: É possível criar referências do tipo da superclasse (Animal)
 * que apontam para objetos das subclasses (Cachorro e Gato).
 *
 * 4. Comportamento Polimórfico: Quando o método fazerSom() é chamado através de uma referência
 * da superclasse (como 'animal1' e 'animal2'), o método que é realmente executado é a
 * versão sobrescrita na subclasse do objeto referenciado. Isso é o polimorfismo em ação:
 * a mesma chamada de método ("fazerSom()") se comporta de maneiras diferentes dependendo
 * do tipo real do objeto em tempo de execução.
 *
 * Em resumo, o polimorfismo permite que tratemos objetos de diferentes classes de forma uniforme
 * através de uma referência da superclasse, enquanto o comportamento específico de cada objeto
 * é determinado pelo seu tipo real em tempo de execução. Isso promove a flexibilidade, a
 * reutilização de código e a extensibilidade em sistemas orientados a objetos.
 */
