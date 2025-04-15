package animais; // Define que essa classe pertence ao pacote "animais"

// Superclasse Animal
public class Animal {
    // Atributo comum a todos os animais
    public String nome;

    // Método que será sobrescrito pelas subclasses
    public void emitirSom() {
        System.out.println("O animal faz um som.");
    }
}
