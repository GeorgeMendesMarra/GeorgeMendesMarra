// Importa as classes do pacote "animais"
import animais.Animal;
import animais.Cachorro;
import animais.Gato;

// Classe principal com o método main
public class Heranca {
    public static void main(String[] args) {
        // Criando um objeto da superclasse Animal
        Animal a = new Animal();
        a.emitirSom();  // Saída: O animal faz um som.

        // Criando um objeto da subclasse Cachorro
        Cachorro c = new Cachorro();
        c.nome = "Rex";  // Acessando atributo herdado
        c.emitirSom();   // Saída: O cachorro late: Au Au!
        System.out.println("Nome do cachorro: " + c.nome);

        // Criando um objeto da subclasse Gato
        Gato g = new Gato();
        g.nome = "Mimi";  // Acessando atributo herdado
        g.emitirSom();    // Saída: O gato mia: Miau!
        System.out.println("Nome do gato: " + g.nome);
    }
}
