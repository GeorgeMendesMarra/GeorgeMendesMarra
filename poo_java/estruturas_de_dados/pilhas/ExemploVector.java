import java.util.Vector;

public class ExemploVector {
    public static void main(String[] args) {
        // Criando um Vector de Strings
        // Vector é como um ArrayList, mas é sincronizado (thread-safe)
        Vector<String> nomes = new Vector<>();

        // Adicionando elementos ao Vector
        nomes.add("Alice");   // Adiciona "Alice" no final
        nomes.add("Bob");     // Adiciona "Bob" no final
        nomes.add("Carlos");  // Adiciona "Carlos" no final

        // Exibindo todos os elementos do Vector
        System.out.println("Elementos do Vector: " + nomes);

        // Acessando um elemento específico pelo índice
        System.out.println("Primeiro elemento: " + nomes.get(0)); // índice 0 = Alice

        // Removendo um elemento pelo valor
        nomes.remove("Bob"); // Remove "Bob" do Vector
        System.out.println("Após remover Bob: " + nomes);

        // Verificando o tamanho atual do Vector
        System.out.println("Tamanho do Vector: " + nomes.size());

        // Verificando se um elemento existe no Vector
        if (nomes.contains("Carlos")) {
            System.out.println("Carlos está no Vector!");
        }

        // Percorrendo o Vector usando for-each
        System.out.println("\nPercorrendo o Vector:");
        for (String nome : nomes) {
            System.out.println(nome); // Imprime cada elemento
        }

        // Outras operações úteis:
        // nomes.add(1, "Daniel"); // Insere "Daniel" na posição 1, deslocando os outros
        // nomes.remove(0);         // Remove o elemento da posição 0
        // nomes.clear();           // Remove todos os elementos
    }
}
