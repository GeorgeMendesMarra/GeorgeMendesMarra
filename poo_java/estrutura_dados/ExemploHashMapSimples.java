import java.util.HashMap; // Importa a classe HashMap
import java.util.Map;     // Importa a interface Map

public class ExemploHashMapSimples {

    public static void main(String[] args) {

        // 1. Criando uma instância de HashMap
        // O Map é parametrizado com dois tipos: o tipo da Chave e o tipo do Valor.
        // Neste exemplo, as chaves serão Strings (nomes) e os valores serão Integers (idades).
        Map<String, Integer> idadesPessoas = new HashMap<>();

        System.out.println("--- Operações de Adição/Atualização (put) ---");

        // 2. Adicionando pares chave-valor ao Map
        // O método 'put(chave, valor)' adiciona um novo par ou atualiza o valor
        // se a chave já existir.
        idadesPessoas.put("Alice", 30);
        idadesPessoas.put("Bob", 25);
        idadesPessoas.put("Carlos", 35);
        idadesPessoas.put("Diana", 28);

        System.out.println("Map de idades após adições: " + idadesPessoas);
        // Note que a ordem de exibição pode não ser a ordem de inserção, pois HashMap não garante ordem.

        // 3. Atualizando um valor para uma chave existente
        System.out.println("\nAtualizando idade de Alice para 31:");
        idadesPessoas.put("Alice", 31); // 'Alice' já existe, o valor será atualizado.
        System.out.println("Map após atualização de Alice: " + idadesPessoas);

        System.out.println("\n--- Verificando o Tamanho (size) ---");
        System.out.println("Número de pares (chave-valor) no Map: " + idadesPessoas.size()); // Deve ser 4

        System.out.println("\n--- Acessando Valores (get) ---");
        // O método 'get(chave)' retorna o valor associado à chave, ou 'null' se a chave não existir.
        System.out.println("Idade de Bob: " + idadesPessoas.get("Bob"));    // 25
        System.out.println("Idade de Carlos: " + idadesPessoas.get("Carlos")); // 35
        System.out.println("Idade de Fernando (não existe): " + idadesPessoas.get("Fernando")); // null

        System.out.println("\n--- Verificando Existência de Chave/Valor (containsKey, containsValue) ---");
        System.out.println("O Map contém a chave 'Diana'? " + idadesPessoas.containsKey("Diana"));   // True
        System.out.println("O Map contém a chave 'Eva'? " + idadesPessoas.containsKey("Eva"));       // False
        System.out.println("O Map contém o valor 31? " + idadesPessoas.containsValue(31)); // True (Alice)
        System.out.println("O Map contém o valor 40? " + idadesPessoas.containsValue(40)); // False

        System.out.println("\n--- Operações de Remoção (remove) ---");
        // O método 'remove(chave)' remove o par chave-valor e retorna o valor removido.
        // Se a chave não existir, retorna 'null'.
        System.out.println("Removendo Carlos. Idade removida: " + idadesPessoas.remove("Carlos")); // 35
        System.out.println("Removendo Fernando. Idade removida: " + idadesPessoas.remove("Fernando")); // null

        System.out.println("\nMap de idades após remoções: " + idadesPessoas); // Deve ter Alice, Bob, Diana

        System.out.println("\n--- Iterando sobre os elementos do Map ---");

        // 1. Iterar sobre as CHAVES (keySet())
        System.out.println("Chaves no Map:");
        for (String nome : idadesPessoas.keySet()) {
            System.out.println("  " + nome);
        }

        // 2. Iterar sobre os VALORES (values())
        System.out.println("\nValores no Map:");
        for (Integer idade : idadesPessoas.values()) {
            System.out.println("  " + idade);
        }

        // 3. Iterar sobre os PARES CHAVE-VALOR (entrySet())
        // Esta é a forma mais comum e eficiente para iterar sobre ambos.
        System.out.println("\nPares Chave-Valor no Map (Entry Set):");
        for (Map.Entry<String, Integer> entry : idadesPessoas.entrySet()) {
            System.out.println("  Chave: " + entry.getKey() + ", Valor: " + entry.getValue());
        }

        System.out.println("\n--- Esvaziando o Map (clear) ---");
        idadesPessoas.clear(); // Remove todos os pares chave-valor do Map.
        System.out.println("Map de idades após clear(): " + idadesPessoas); // Deve ser {}
        System.out.println("O Map está vazio? " + idadesPessoas.isEmpty()); // Deve ser true
    }
}
