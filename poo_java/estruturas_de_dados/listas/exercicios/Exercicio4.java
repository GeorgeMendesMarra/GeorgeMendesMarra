// Passo 1: Importar a classe ArrayList
import java.util.ArrayList;

public class Exercicio4 {
    public static void main(String[] args) {
        
        // Passo 2: Criar uma lista com os valores: 5, 15, 3, 20, 8, 25, 2, 30
        ArrayList<Integer> numeros = new ArrayList<>();
        
        // Adicionando os números à lista
        numeros.add(5);
        numeros.add(15);
        numeros.add(3);
        numeros.add(20);
        numeros.add(8);
        numeros.add(25);
        numeros.add(2);
        numeros.add(30);
        
        // Passo 3: Exibir a lista ANTES da remoção
        System.out.println("=== LISTA ANTES DA REMOÇÃO ===");
        System.out.print("Lista original: ");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
        System.out.println("\n");
        
        // Passo 4: Remover todos os elementos que são menores que 10
        // Percorrendo a lista de trás para frente para evitar problemas de índice
        // Isso é necessário porque quando removemos um elemento, 
        // os índices dos elementos seguintes mudam
        
        System.out.println("=== PROCESSO DE REMOÇÃO ===");
        for (int i = numeros.size() - 1; i >= 0; i--) {
            int numeroAtual = numeros.get(i);
            if (numeroAtual < 10) {
                System.out.println("Removendo: " + numeroAtual + " (posição " + i + ")");
                numeros.remove(i);
            }
        }
        
        // Passo 5: Exibir a lista DEPOIS da remoção
        System.out.println("\n=== LISTA DEPOIS DA REMOÇÃO ===");
        System.out.print("Lista após remover números menores que 10: ");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
        System.out.println("\n");
        
        // Informações adicionais
        System.out.println("=== RESUMO ===");
        System.out.println("Quantidade de números removidos: " + (8 - numeros.size()));
        System.out.println("Quantidade de números restantes: " + numeros.size());
    }
}
