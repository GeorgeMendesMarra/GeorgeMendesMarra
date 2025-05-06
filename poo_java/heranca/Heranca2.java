// Superclasse Animal
class Animal {
    // Atributo comum a todos os animais, acessível diretamente nas subclasses e fora da classe
    public String nome;

    // Método que define um comportamento padrão para a emissão de som de um animal
    // Este método será sobrescrito pelas subclasses para fornecer um comportamento mais específico
    public void emitirSom() {
        System.out.println("O animal faz um som genérico.");
    }
}

// Subclasse Cachorro que herda da superclasse Animal
class Cachorro extends Animal {

    // Sobrescreve (override) o método emitirSom da superclasse Animal
    // Isso permite que a classe Cachorro forneça sua própria implementação específica para emitirSom
    @Override
    public void emitirSom() {
        System.out.println("O cachorro late: Au Au!");
    }
}

// Subclasse Gato que herda da superclasse Animal
class Gato extends Animal {

    // Sobrescreve (override) o método emitirSom da superclasse Animal
    // Assim como a classe Cachorro, a classe Gato fornece sua própria implementação específica para emitirSom
    @Override
    public void emitirSom() {
        System.out.println("O gato mia: Miau!");
    }
}

// Classe principal que contém o método main, ponto de entrada da aplicação Java
public class Heranca2 {
    public static void main(String[] args) {
        // Criando uma instância (objeto) da superclasse Animal
        Animal a = new Animal();
        // Chamando o método emitirSom() no objeto 'a' da classe Animal
        // Será executada a implementação do método definida na própria classe Animal
        a.emitirSom();  // Saída esperada: O animal faz um som genérico.

        // Criando uma instância (objeto) da subclasse Cachorro
        Cachorro c = new Cachorro();
        // Acessando o atributo 'nome' que foi herdado da superclasse Animal
        c.nome = "Rex";  // Atribuindo o valor "Rex" ao atributo 'nome' do objeto 'c'
        // Chamando o método emitirSom() no objeto 'c' da classe Cachorro
        // Será executada a implementação do método sobrescrito na classe Cachorro
        c.emitirSom();   // Saída esperada: O cachorro late: Au Au!
        // Imprimindo o valor do atributo 'nome' do objeto 'c'
        System.out.println("Nome do cachorro: " + c.nome); // Saída esperada: Nome do cachorro: Rex

        // Criando uma instância (objeto) da subclasse Gato
        Gato g = new Gato();
        // Acessando o atributo 'nome' que foi herdado da superclasse Animal
        g.nome = "Mimi";  // Atribuindo o valor "Mimi" ao atributo 'nome' do objeto 'g'
        // Chamando o método emitirSom() no objeto 'g' da classe Gato
        // Será executada a implementação do método sobrescrito na classe Gato
        g.emitirSom();    // Saída esperada: O gato mia: Miau!
        // Imprimindo o valor do atributo 'nome' do objeto 'g'
        System.out.println("Nome do gato: " + g.nome);   // Saída esperada: Nome do gato: Mimi
    }
}
