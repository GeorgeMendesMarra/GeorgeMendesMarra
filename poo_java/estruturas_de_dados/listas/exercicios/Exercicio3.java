// Passo 1: Importar as classes necessárias
import java.util.ArrayList; // Para usar ArrayList
import java.util.Scanner;   // Para ler entrada do usuário

public class Exercicio3 {
    public static void main(String[] args) {
        
        // Passo 2: Criar um ArrayList<String> para armazenar os nomes
        ArrayList<String> nomes = new ArrayList<>();
        
        // Passo 3: Criar o objeto Scanner para ler a entrada do teclado
        Scanner scanner = new Scanner(System.in);
        
        // Passo 4: Usar um laço while (true) para ler nomes continuamente
        System.out.println("=== CADASTRO DE NOMES ===");
        System.out.println("Digite os nomes desejados.");
        System.out.println("Para encerrar, digite 'fim' (sem aspas).");
        System.out.println("----------------------------");
        
        while (true) { // Laço infinito, será interrompido com break
            
            System.out.print("Digite um nome: ");
            String nome = scanner.nextLine(); // Lê uma linha digitada pelo usuário
            
            // Passo 5: Verificar se o usuário digitou "fim"
            if (nome.equalsIgnoreCase("fim")) { // equalsIgnoreCase ignora maiúsculas/minúsculas
                break; // Interrompe o laço imediatamente
            }
            
            // Passo 6: Caso contrário, adicionar o nome à lista
            nomes.add(nome);
            System.out.println(">>> Nome \"" + nome + "\" cadastrado com sucesso!\n");
        }
        
        // Passo 7: Após sair do laço, exibir todos os nomes cadastrados
        System.out.println("\n=== LISTA DE NOMES CADASTRADOS ===");
        
        // Verificar se a lista está vazia
        if (nomes.isEmpty()) {
            System.out.println("Nenhum nome foi cadastrado.");
        } else {
            // Exibir cada nome em uma linha separada (um por linha)
            System.out.println("Total de nomes: " + nomes.size());
            System.out.println("----------------------------");
            
            int contador = 1;
            for (String nome : nomes) {
                System.out.println(contador + ". " + nome);
                contador++;
            }
        }
        
        // Boa prática: fechar o scanner para liberar recursos
        scanner.close();
    }
}
