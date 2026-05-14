// Passo 1: Importar as classes necessárias
import java.util.ArrayList; // Para usar ArrayList
import java.util.Scanner;   // Para ler entrada do teclado (opção via teclado)

public class Exercicio5 {
    public static void main(String[] args) {
        
        // ===========================================
        // MODO 1: Adicionar produtos MANUALMENTE (código fixo)
        // ===========================================
        
        System.out.println("=== CADASTRO DE PRODUTOS - MODO MANUAL ===\n");
        
        // Passo 2: Criar as listas para nomes e preços
        ArrayList<String> nomesProdutos = new ArrayList<>();
        ArrayList<Double> precosProdutos = new ArrayList<>();
        
        // Passo 3: Adicionar pelo menos 3 produtos manualmente
        // Produto 1
        nomesProdutos.add("Notebook Dell Inspiron");
        precosProdutos.add(3499.90);
        
        // Produto 2
        nomesProdutos.add("Mouse Gamer Logitech");
        precosProdutos.add(149.50);
        
        // Produto 3
        nomesProdutos.add("Teclado Mecânico Redragon");
        precosProdutos.add(289.90);
        
        // Produtos adicionais
        nomesProdutos.add("Monitor LG 24 Polegadas");
        precosProdutos.add(899.99);
        
        nomesProdutos.add("Webcam Full HD");
        precosProdutos.add(199.00);
        
        // Passo 4: Calcular o valor total do carrinho
        double total = 0.0;
        for (double preco : precosProdutos) {
            total += preco; // Soma todos os preços
        }
        
        // Passo 5: Exibir o relatório completo
        System.out.println("=== RELATÓRIO DO CARRINHO ===");
        System.out.println("Produtos cadastrados:\n");
        
        for (int i = 0; i < nomesProdutos.size(); i++) {
            System.out.printf("Produto %d: %s - R$ %.2f\n", 
                              (i + 1), 
                              nomesProdutos.get(i), 
                              precosProdutos.get(i));
        }
        
        System.out.println("\n" + "=".repeat(40));
        System.out.printf("TOTAL DO CARRINHO: R$ %.2f\n", total);
        System.out.println("=".repeat(40));
        System.out.println("Quantidade de itens: " + nomesProdutos.size());
        
        // ===========================================
        // MODO 2: Adicionar produtos VIA TECLADO (opção interativa)
        // ===========================================
        
        System.out.println("\n\n=== CADASTRO DE PRODUTOS - MODO INTERATIVO ===\n");
        
        // Criar novas listas para o modo interativo
        ArrayList<String> nomesInteractive = new ArrayList<>();
        ArrayList<Double> precosInteractive = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Quantos produtos deseja cadastrar? ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        
        System.out.println();
        
        // Loop para cadastrar os produtos
        for (int i = 1; i <= quantidade; i++) {
            System.out.println("--- Produto " + i + " ---");
            
            System.out.print("Nome do produto: ");
            String nome = scanner.nextLine();
            nomesInteractive.add(nome);
            
            System.out.print("Preço do produto: R$ ");
            double preco = scanner.nextDouble();
            scanner.nextLine(); // Consumir a quebra de linha
            precosInteractive.add(preco);
            
            System.out.println("✓ Produto cadastrado com sucesso!\n");
        }
        
        // Calcular o total do modo interativo
        double totalInteractive = 0.0;
        for (double preco : precosInteractive) {
            totalInteractive += preco;
        }
        
        // Exibir relatório do modo interativo
        System.out.println("=== RELATÓRIO DO CARRINHO (MODO INTERATIVO) ===");
        System.out.println("Produtos cadastrados:\n");
        
        for (int i = 0; i < nomesInteractive.size(); i++) {
            System.out.printf("%d. %s - R$ %.2f\n", 
                              (i + 1), 
                              nomesInteractive.get(i), 
                              precosInteractive.get(i));
        }
        
        System.out.println("\n" + "-".repeat(40));
        System.out.printf("TOTAL DO CARRINHO: R$ %.2f\n", totalInteractive);
        System.out.println("-".repeat(40));
        System.out.println("Quantidade de itens: " + nomesInteractive.size());
        
        // Fechar o scanner
        scanner.close();
        
        // ===========================================
        // FUNCIONALIDADE EXTRA: Buscar produto mais caro
        // ===========================================
        
        System.out.println("\n\n=== INFORMAÇÕES ADICIONAIS ===");
        
        // Encontrar o produto mais caro (usando a lista manual)
        if (!precosProdutos.isEmpty()) {
            double maiorPreco = precosProdutos.get(0);
            int indiceMaior = 0;
            
            for (int i = 1; i < precosProdutos.size(); i++) {
                if (precosProdutos.get(i) > maiorPreco) {
                    maiorPreco = precosProdutos.get(i);
                    indiceMaior = i;
                }
            }
            
            System.out.println("Produto mais caro (modo manual):");
            System.out.printf("  %s - R$ %.2f\n", 
                              nomesProdutos.get(indiceMaior), 
                              maiorPreco);
        }
    }
}
