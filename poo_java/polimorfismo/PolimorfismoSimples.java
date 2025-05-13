// Superclasse Animal
class Animal {
    // Método que será sobrescrito pelas subclasses
    public void emitirSom() {
        System.out.println("Som genérico de animal");
    }
}

// Subclasse Cachorro que herda de Animal
class Cachorro extends Animal {
    // Sobrescreve o método emitirSom da superclasse
    @Override
    public void emitirSom() {
        System.out.println("Au au!");
    }
}

// Subclasse Gato que herda de Animal
class Gato extends Animal {
    // Sobrescreve o método emitirSom da superclasse
    @Override
    public void emitirSom() {
        System.out.println("Miau!");
    }
}

public class PolimorfismoSimples {
    public static void main(String[] args) {
        // Criando referências do tipo Animal que apontam para objetos de suas subclasses
        Animal animal1 = new Cachorro();
        Animal animal2 = new Gato();

        System.out.println("--- Chamando emitirSom() através de referências Animal ---");
        // Chamando o método emitirSom() na referência animal1 (que é um Cachorro)
        // O método emitirSom() da classe Cachorro será executado (polimorfismo em ação)
        animal1.emitirSom(); // Saída: Au au!

        // Chamando o método emitirSom() na referência animal2 (que é um Gato)
        // O método emitirSom() da classe Gato será executado (polimorfismo em ação)
        animal2.emitirSom(); // Saída: Miau!

        System.out.println("\n--- Polimorfismo em um array ---");
        // Criando um array de referências do tipo Animal
        Animal[] animais = new Animal[3];
        animais[0] = new Animal();
        animais[1] = new Cachorro();
        animais[2] = new Gato();

        // Iterando pelo array de animais e chamando o método emitirSom() em cada elemento
        // O comportamento do método emitirSom() será diferente dependendo do tipo real do objeto
        for (Animal animal : animais) {
            System.out.print("O animal ");
            if (animal instanceof Cachorro) {
                System.out.print("cachorro ");
            } else if (animal instanceof Gato) {
                System.out.print("gato ");
            } else {
                System.out.print("genérico ");
            }
            animal.emitirSom();
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
 * específica para o método emitirSom() que já existia na classe Animal. Isso permite que
 * cada tipo de animal emita seu próprio som característico.
 *
 * 3. Referências da Superclasse: É possível criar referências do tipo da superclasse (Animal)
 * que apontam para objetos das subclasses (Cachorro e Gato).
 *
 * 4. Comportamento Polimórfico: Quando o método emitirSom() é chamado através de uma referência
 * da superclasse (como 'animal1' e 'animal2'), o método que é realmente executado é a
 * versão sobrescrita na subclasse do objeto referenciado. Isso é o polimorfismo em ação:
 * a mesma chamada de método ("emitirSom()") se comporta de maneiras diferentes dependendo
 * do tipo real do objeto em tempo de execução.
 *
 * Em resumo, o polimorfismo permite que tratemos objetos de diferentes classes de forma uniforme
 * através de uma referência da superclasse, enquanto o comportamento específico de cada objeto
 * é determinado pelo seu tipo real em tempo de execução.
 */
