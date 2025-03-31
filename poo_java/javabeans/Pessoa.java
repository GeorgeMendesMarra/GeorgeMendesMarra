import java.io.Serializable;

/**
 * A classe Pessoa representa uma pessoa com nome e idade.
 * Esta classe é um JavaBean, seguindo as convenções de JavaBeans.
 */
public class Pessoa implements Serializable {

    // Propriedade privada para armazenar o nome da pessoa.
    private String nome;

    // Propriedade privada para armazenar a idade da pessoa.
    private int idade;

    /**
     * Construtor sem argumentos da classe Pessoa.
     * Este construtor é necessário para JavaBeans.
     */
    public Pessoa() {
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome O nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a idade da pessoa.
     *
     * @return A idade da pessoa.
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Define a idade da pessoa.
     *
     * @param idade A idade da pessoa.
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
