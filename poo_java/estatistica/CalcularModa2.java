import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalcularModa2 {
    public static void main(String[] args) {
        // Criamos uma lista de números (pode ser qualquer conjunto de dados)
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 2, 2, 3, 5, 2);

        // Usamos Optional porque o conjunto pode estar vazio
        Optional<Map.Entry<Integer, Long>> resultado = numeros.stream()
            // 1. Agrupa os números por valor (Function.identity) 
            // 2. Conta quantas vezes cada um aparece (Collectors.counting)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            
            // 3. Pega o resultado (que é um Map) e abre um novo fluxo de entradas (EntrySet)
            .entrySet().stream()
            
            // 4. Compara as entradas do mapa e seleciona aquela que tem o maior VALOR (frequência)
            .max(Map.Entry.comparingByValue());

        // Se houver um resultado, imprime na tela
        resultado.ifPresent(res -> 
            System.out.printf("A moda é: %d (Apareceu %d vezes)%n", res.getKey(), res.getValue())
        );
    }
}
