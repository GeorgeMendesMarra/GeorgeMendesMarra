package animais; // Define que essa classe pertence ao pacote "animais"

// Subclasse Gato herda de Animal
public class Gato extends Animal {

    // Sobrescreve o método emitirSom da superclasse
    @Override
    public void emitirSom() {
        System.out.println("O gato mia: Miau!");
    }
}
