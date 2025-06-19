public class TestarIgualdades {

    public static void main(String[] args) {

        // --- 1. Testando Igualdade de Tipos Primitivos (usando '==') ---
        System.out.println("--- Testando Igualdade de Tipos Primitivos ---");

        int numero1 = 10;
        int numero2 = 10;
        int numero3 = 20;

        // Para tipos primitivos, '==' compara os valores diretamente.
        System.out.println("numero1 (" + numero1 + ") == numero2 (" + numero2 + "): " + (numero1 == numero2)); // Saída: true
        System.out.println("numero1 (" + numero1 + ") == numero3 (" + numero3 + "): " + (numero1 == numero3)); // Saída: false

        char letra1 = 'A';
        char letra2 = 'A';
        char letra3 = 'B';

        System.out.println("letra1 (" + letra1 + ") == letra2 (" + letra2 + "): " + (letra1 == letra2)); // Saída: true
        System.out.println("letra1 (" + letra1 + ") == letra3 (" + letra3 + "): " + (letra1 == letra3)); // Saída: false

        boolean bool1 = true;
        boolean bool2 = true;
        boolean bool3 = false;

        System.out.println("bool1 (" + bool1 + ") == bool2 (" + bool2 + "): " + (bool1 == bool2)); // Saída: true
        System.out.println("bool1 (" + bool1 + ") == bool3 (" + bool3 + "): " + (bool1 == bool3)); // Saída: false

        System.out.println("\n"); // Adiciona uma linha em branco para melhor leitura

        // --- 2. Testando Igualdade de Strings ---
        System.out.println("--- Testando Igualdade de Strings ---");

        String str1 = "Java";
        String str2 = "Java";
        String str3 = new String("Java"); // Cria uma nova instância de String
        String str4 = "Python";

        // Usando '==' para Strings: Compara se as referências de objeto são as mesmas (se apontam para o mesmo local na memória).
        // str1 e str2 podem ser iguais porque o Java pode otimizar e usar a mesma instância de String Pool.
        System.out.println("str1 (\"" + str1 + "\") == str2 (\"" + str2 + "\"): " + (str1 == str2));     // Pode ser true (String Pool)
        System.out.println("str1 (\"" + str1 + "\") == str3 (\"" + str3 + "\"): " + (str1 == str3));     // Saída: false (referências diferentes, mesmo conteúdo)
        System.out.println("str1 (\"" + str1 + "\") == str4 (\"" + str4 + "\"): " + (str1 == str4));     // Saída: false

        // Usando '.equals()' para Strings: Compara o CONTEÚDO das strings.
        // ESTA É A FORMA CORRETA DE COMPARAR O CONTEÚDO DE STRINGS!
        System.out.println("str1 (\"" + str1 + "\").equals(str2 (\"" + str2 + "\")): " + str1.equals(str2)); // Saída: true
        System.out.println("str1 (\"" + str1 + "\").equals(str3 (\"" + str3 + "\")): " + str1.equals(str3)); // Saída: true
        System.out.println("str1 (\"" + str1 + "\").equals(str4 (\"" + str4 + "\")): " + str1.equals(str4)); // Saída: false

        System.out.println("\n"); // Adiciona uma linha em branco

        // --- 3. Testando Igualdade de Objetos Personalizados ---
        System.out.println("--- Testando Igualdade de Objetos Personalizados ---");

        Pessoa pessoa1 = new Pessoa("Alice", 30);
        Pessoa pessoa2 = new Pessoa("Alice", 30); // Mesmos atributos, nova instância
        Pessoa pessoa3 = new Pessoa("Bob", 25);
        Pessoa pessoa4 = pessoa1; // pessoa4 referencia o mesmo objeto que pessoa1

        // Sem sobrescrever o método .equals() na classe Pessoa:
        // O operador '==' compara referências de objetos.
        System.out.println("Sem .equals() sobrescrito (usando '=='):");
        System.out.println("pessoa1 == pessoa2: " + (pessoa1 == pessoa2)); // Saída: false (referências diferentes)
        System.out.println("pessoa1 == pessoa4: " + (pessoa1 == pessoa4)); // Saída: true (referenciam o mesmo objeto)

        // Com o método .equals() SOBRESCRITO na classe Pessoa (veja a definição da classe abaixo):
        // Agora, .equals() compara o CONTEÚDO (nome e idade neste caso).
        System.out.println("\nCom .equals() sobrescrito (usando .equals()):");
        System.out.println("pessoa1.equals(pessoa2): " + pessoa1.equals(pessoa2)); // Saída: true (conteúdo igual)
        System.out.println("pessoa1.equals(pessoa3): " + pessoa1.equals(pessoa3)); // Saída: false (conteúdo diferente)
        System.out.println("pessoa1.equals(pessoa4): " + pessoa1.equals(pessoa4)); // Saída: true (referenciam o mesmo objeto e conteúdo)
    }
}

// --- Definição da Classe Pessoa ---
// Esta classe é usada para demonstrar a comparação de objetos personalizados.
class Pessoa {
    private String nome;
    private int idade;

    // Construtor da classe Pessoa
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Métodos getters para acessar os atributos (não estritamente necessários para a igualdade, mas boa prática)
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    /*
     * SOBRESCREVENDO O MÉTODO equals()
     *
     * Este é um passo CRUCIAL para comparar o CONTEÚDO de objetos em Java.
     * Por padrão, o método equals() da classe Object (da qual todas as classes Java herdam)
     * se comporta como o operador '==', ou seja, compara referências de objeto.
     *
     * Ao sobrescrever, definimos o que significa "igualdade" para objetos da classe Pessoa.
     * Neste caso, duas Pessoas são consideradas iguais se tiverem o mesmo nome E a mesma idade.
     */
    @Override // Anotação para indicar que estamos sobrescrevendo um método da superclasse
    public boolean equals(Object obj) {
        // 1. Otimização: Se o objeto comparado for a mesma instância, são iguais.
        if (this == obj) {
            return true;
        }
        // 2. Verificação de Nulo e Tipo: Se o objeto é nulo ou não é uma instância de Pessoa, não são iguais.
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // 3. Cast (conversão): Converte o objeto genérico 'obj' para o tipo 'Pessoa'
        Pessoa outraPessoa = (Pessoa) obj;

        // 4. Comparação de Conteúdo: Compara os atributos relevantes para definir a igualdade.
        // Para Strings, usamos .equals() novamente. Para primitivos, usamos '=='.
        return nome.equals(outraPessoa.nome) && idade == outraPessoa.idade;
    }

    /*
     * SOBRESCREVENDO O MÉTODO hashCode() (BOA PRÁTICA COM equals())
     *
     * Sempre que você sobrescreve o método equals(), é FORTEMENTE RECOMENDADO
     * que você também sobrescreva o método hashCode().
     * O contrato geral para hashCode() e equals() é:
     * - Se dois objetos são iguais de acordo com o método equals(Object),
     * então chamar o método hashCode em cada um dos objetos deve produzir
     * o mesmo resultado inteiro.
     * Isso é importante para que objetos se comportem corretamente em coleções
     * baseadas em hash, como HashMap e HashSet.
     */
    @Override
    public int hashCode() {
        int result = nome.hashCode(); // Começa com o hash do nome
        result = 31 * result + Integer.hashCode(idade); // Combina com o hash da idade
        return result;
    }

    // Método toString() para facilitar a impressão do objeto
    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', idade=" + idade + '}';
    }
}
