package animais; // Define que essa classe pertence ao pacote "animais"

// Subclasse Cachorro herda de Animal
public class Cachorro extends Animal {

    // Sobrescreve o método emitirSom da superclasse
    @Override
    public void emitirSom() {
        System.out.println("O cachorro late: Au Au!");
    }
}
