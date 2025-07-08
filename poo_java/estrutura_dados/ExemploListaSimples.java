import java.util.ArrayList; // Importa a classe ArrayList
import java.util.List;    // Importa a interface List

public class ExemploListaSimples {

    public static void main(String[] args) {
        // 1. Criando uma lista de Strings (usando ArrayList, que implementa a interface List)
        // A interface List é mais genérica, e ArrayList é uma implementação concreta.
        List<String> frutas = new ArrayList<>();

        System.out.println("Lista vazia: " + frutas);
        System.out.println("Tamanho da lista: " + frutas.size());

        // 2. Adicionando elementos à lista
        frutas.add("Maçã");
        frutas.add("Banana");
        frutas.add("Laranja");
        frutas.add("Uva");

        System.out.println("\nLista após adicionar elementos: " + frutas);
        System.out.println("Tamanho da lista: " + frutas.size());

        // 3. Acessando elementos da lista pelo índice
        // Os índices começam em 0
        System.out.println("\nPrimeira fruta: " + frutas.get(0));
        System.out.println("Terceira fruta: " + frutas.get(2));

        // 4. Verificando se um elemento existe na lista
        System.out.println("\nA lista contém 'Banana'? " + frutas.contains("Banana"));
        System.out.println("A lista contém 'Pera'? " + frutas.contains("Pera"));

        // 5. Removendo um elemento da lista
        // Pode ser por valor ou por índice
        frutas.remove("Banana"); // Remove pelo valor
        System.out.println("\nLista após remover 'Banana': " + frutas);

        frutas.remove(0); // Remove o elemento no índice 0 (que agora é 'Laranja')
        System.out.println("Lista após remover elemento no índice 0: " + frutas);

        // 6. Iterando sobre os elementos da lista (várias formas)

        // Forma 1: Usando loop for aprimorado (forEach) - mais comum e legível
        System.out.println("\nIterando com for-each:");
        for (String fruta : frutas) {
            System.out.println("- " + fruta);
        }

        // Forma 2: Usando lambda expression com forEach() (Java 8+)
        System.out.println("\nIterando com forEach() e lambda:");
        frutas.forEach(fruta -> System.out.println("* " + fruta));

        // 7. Limpando a lista
        frutas.clear();
        System.out.println("\nLista após limpar: " + frutas);
        System.out.println("Tamanho da lista: " + frutas.size());

        // 8. Verificando se a lista está vazia
        System.out.println("A lista está vazia? " + frutas.isEmpty());
    }
}
