// Passo 1: Importar as classes necessárias
import java.util.ArrayList; // Para usar ArrayList
import java.util.Scanner;   // Para ler entrada do teclado

public class Exercicio8 {
    public static void main(String[] args) {
        
        // Passo 2: Criar o ArrayList para armazenar os números
        ArrayList<Integer> numeros = new ArrayList<>();
        
        // Passo 3: Criar o Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Passo 4: Receber números do usuário até que ele digite 0
        System.out.println("=== REMOVER NÚMEROS DUPLICADOS ===");
        System.out.println("Digite números inteiros (digite 0 para encerrar):\n");
        
        while (true) {
            System.out.print("Digite um número: ");
            int numero = scanner.nextInt();
            
            // Verificar se o usuário digitou 0 para encerrar
            if (numero == 0) {
                break;
            }
            
            // Adicionar o número à lista
            numeros.add(numero);
            System.out.println(">>> Número " + numero + " adicionado à lista.");
        }
        
        // Passo 5: Exibir a lista original (com possíveis duplicatas)
        System.out.println("\n=== LISTA ORIGINAL ===");
        System.out.print("Números digitados: ");
        exibirLista(numeros);
        System.out.println("Total de números: " + numeros.size());
        
        // Passo 6: Remover números duplicados mantendo apenas a primeira ocorrência
        ArrayList<Integer> listaSemDuplicatas = removerDuplicatas(numeros);
        
        // Passo 7: Exibir a lista final (sem duplicatas)
        System.out.println("\n=== LISTA SEM DUPLICATAS ===");
        System.out.print("Números únicos: ");
        exibirLista(listaSemDuplicatas);
        System.out.println("Total de números únicos: " + listaSemDuplicatas.size());
        
        // Informações adicionais
        int duplicatasRemovidas = numeros.size() - listaSemDuplicatas.size();
        System.out.println("\n=== RESUMO ===");
        System.out.println("Quantidade de números removidos (duplicatas): " + duplicatasRemovidas);
        
        scanner.close();
    }
    
    // ===========================================
    // MÉTODO PARA REMOVER DUPLICATAS
    // ===========================================
    public static ArrayList<Integer> removerDuplicatas(ArrayList<Integer> listaOriginal) {
        // Criar uma nova lista para armazenar os números sem duplicatas
        ArrayList<Integer> listaUnica = new ArrayList<>();
        
        // Percorrer cada elemento da lista original
        for (int i = 0; i < listaOriginal.size(); i++) {
            int numeroAtual = listaOriginal.get(i);
            
            // Verificar se o número atual já existe na nova lista
            // Método 1: usando o método contains() do ArrayList
            if (!listaUnica.contains(numeroAtual)) {
                // Se não existe, adicionar à lista única
                listaUnica.add(numeroAtual);
                System.out.println("✓ Mantendo primeira ocorrência: " + numeroAtual);
            } else {
                // Se já existe, é uma duplicata (ignorar)
                System.out.println("✗ Removendo duplicata: " + numeroAtual);
            }
        }
        
        return listaUnica;
    }
    
    // ===========================================
    // MÉTODO AUXILIAR PARA EXIBIR A LISTA
    // ===========================================
    public static void exibirLista(ArrayList<Integer> lista) {
        if (lista.isEmpty()) {
            System.out.print("[] (lista vazia)");
        } else {
            System.out.print("[");
            for (int i = 0; i < lista.size(); i++) {
                System.out.print(lista.get(i));
                if (i < lista.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
        }
        System.out.println();
    }
}
