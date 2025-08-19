import java.util.HashSet; // Importa a classe HashSet
import java.util.Set;     // Importa a interface Set
import java.util.Iterator; // Importa Iterator para demonstração de iteração

public class ExemploHashSetSimples {

    public static void main(String[] args) {

        // 1. Criando uma instância de HashSet
        // O tipo dos elementos do Set é especificado entre os sinais de diamante <>.
        // Neste exemplo, o Set armazenará Strings.
        Set<String> frutas = new HashSet<>();

        System.out.println("--- Operações de Adição (add) ---");

        // 2. Adicionando elementos ao Set
        // O método 'add()' retorna true se o elemento foi adicionado com sucesso (não era duplicado)
        // e false se o elemento já existia no Set (e, portanto, não foi adicionado novamente).
        System.out.println("Adicionando 'Maçã': " + frutas.add("Maçã"));   // True, adicionado
        System.out.println("Adicionando 'Banana': " + frutas.add("Banana")); // True, adicionado
        System.out.println("Adicionando 'Laranja': " + frutas.add("Laranja"));// True, adicionado
        System.out.println("Adicionando 'Maçã' novamente: " + frutas.add("Maçã")); // False, já existe

        System.out.println("\nSet de frutas após adições: " + frutas);
        // Note que a ordem de exibição pode não ser a ordem de inserção, pois HashSet não garante ordem.
        // Além disso, 'Maçã' aparece apenas uma vez.

        System.out.println("\n--- Verificando o Tamanho (size) ---");
        System.out.println("Número de frutas no Set: " + frutas.size()); // Deve ser 3

        System.out.println("\n--- Verificando Existência (contains) ---");
        // O método 'contains()' verifica se um elemento está presente no Set.
        System.out.println("O Set contém 'Banana'? " + frutas.contains("Banana")); // True
        System.out.println("O Set contém 'Uva'? " + frutas.contains("Uva"));     // False

        System.out.println("\n--- Operações de Remoção (remove) ---");
        // O método 'remove()' retorna true se o elemento foi removido com sucesso
        // e false se o elemento não estava presente no Set.
        System.out.println("Removendo 'Laranja': " + frutas.remove("Laranja")); // True, removido
        System.out.println("Removendo 'Pera': " + frutas.remove("Pera"));     // False, não existe

        System.out.println("\nSet de frutas após remoções: " + frutas); // Deve ter 'Maçã', 'Banana'

        System.out.println("\n--- Iterando sobre os elementos do Set ---");
        // 1. Usando for-each loop (forma mais comum e simples)
        System.out.println("Usando for-each:");
        for (String fruta : frutas) {
            System.out.println("  " + fruta);
        }

        // 2. Usando Iterator
        System.out.println("\nUsando Iterator:");
        Iterator<String> iterator = frutas.iterator();
        while (iterator.hasNext()) { // Enquanto houver próximo elemento
            System.out.println("  " + iterator.next()); // Pega o próximo elemento
        }

        System.out.println("\n--- Esvaziando o Set (clear) ---");
        frutas.clear(); // Remove todos os elementos do Set.
        System.out.println("Set de frutas após clear(): " + frutas); // Deve ser []
        System.out.println("O Set está vazio? " + frutas.isEmpty()); // Deve ser true
    }
}
