// Definição de uma interface chamada "Animal"
interface Animal {

    // Métodos abstratos (sem implementação) que as classes que implementam
    // esta interface devem fornecer.

    // Método para o animal emitir um som.
    void emitirSom();

    // Método para o animal se mover.
    void mover();
}

// Classe "Cachorro" que IMPLEMENTA a interface "Animal"
class Cachorro implements Animal {

    // Implementação do método emitirSom() para a classe Cachorro
    @Override
    public void emitirSom() {
        System.out.println("Au au!");
    }

    // Implementação do método mover() para a classe Cachorro
    @Override
    public void mover() {
        System.out.println("O cachorro está correndo.");
    }

    // Método específico da classe Cachorro
    public void latirAlto() {
        System.out.println("AU AU AU!");
    }
}

// Classe "Gato" que IMPLEMENTA a interface "Animal"
class Gato implements Animal {

    // Implementação do método emitirSom() para a classe Gato
    @Override
    public void emitirSom() {
        System.out.println("Miau!");
    }

    // Implementação do método mover() para a classe Gato
    @Override
    public void mover() {
        System.out.println("O gato está andando.");
    }

    // Método específico da classe Gato
    public void ronronar() {
        System.out.println("Rrrrr...");
    }
}

// Classe principal para demonstrar o uso da interface
public class ExemploInterface1 {
    public static void main(String[] args) {
        // Criando objetos das classes que implementam a interface Animal
        Cachorro meuCachorro = new Cachorro();
        Gato meuGato = new Gato();

        System.out.println("--- Interagindo com o Cachorro ---");
        meuCachorro.emitirSom();
        meuCachorro.mover();
        meuCachorro.latirAlto();

        System.out.println("\n--- Interagindo com o Gato ---");
        meuGato.emitirSom();
        meuGato.mover();
        meuGato.ronronar();

        // Demonstrando o polimorfismo através da interface Animal
        // Variáveis do tipo Animal podem referenciar objetos de qualquer classe que implemente Animal
        Animal animal1 = meuCachorro;
        Animal animal2 = meuGato;

        System.out.println("\n--- Polimorfismo com a interface Animal ---");
        animal1.emitirSom(); // Chama o método emitirSom() da classe Cachorro
        animal1.mover();    // Chama o método mover() da classe Cachorro

        animal2.emitirSom(); // Chama o método emitirSom() da classe Gato
        animal2.mover();    // Chama o método mover() da classe Gato

        // Não podemos chamar métodos específicos da classe através da referência da interface
        // animal1.latirAlto(); // Isso geraria um erro de compilação
    }
}

/*
 * Explicação do uso de interfaces em Java:
 *
 * 1. Contrato: A interface "Animal" define um contrato que as classes "Cachorro" e "Gato"
 * devem seguir. Ela especifica que qualquer classe que implementa "Animal" deve ter os
 * métodos "emitirSom()" e "mover()".
 *
 * 2. Abstração: Interfaces focam no "o quê" uma classe pode fazer, em vez de "como" ela faz.
 * A interface "Animal" diz que um animal pode emitir um som e se mover, mas não especifica
 * como exatamente cada tipo de animal faz isso.
 *
 * 3. Polimorfismo: Interfaces habilitam o polimorfismo. No exemplo, as variáveis "animal1" e
 * "animal2" são do tipo "Animal", mas referenciam objetos das classes "Cachorro" e "Gato",
 * respectivamente. Quando chamamos os métodos "emitirSom()" e "mover()" nessas variáveis,
 * o método específico da classe do objeto referenciado é executado.
 *
 * 4. Herança Múltipla de Tipos: Java não permite herança múltipla de classes, mas uma classe
 * pode implementar múltiplas interfaces. Isso permite que uma classe "herde" múltiplos tipos
 * (conjuntos de métodos).
 *
 * Benefícios do uso de interfaces:
 * - Promovem o baixo acoplamento entre classes.
 * - Facilitam a criação de sistemas flexíveis e extensíveis.
 * - Permitem que classes não relacionadas compartilhem um comportamento comum.
 */
