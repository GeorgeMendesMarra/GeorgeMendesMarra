// Passo 1: Importar as classes necessárias
import java.util.LinkedList; // Para usar LinkedList como fila
import java.util.Scanner;    // Para ler entrada do teclado

public class Exercicio9 {
    public static void main(String[] args) {
        
        // Passo 2: Criar a LinkedList para simular a fila de atendimento
        // Uma fila FIFO (First In, First Out) - primeiro a entrar, primeiro a sair
        LinkedList<String> fila = new LinkedList<>();
        
        // Passo 3: Criar o Scanner para ler as opções do usuário
        Scanner scanner = new Scanner(System.in);
        
        int opcao;
        
        System.out.println("=== SISTEMA DE FILA DE ATENDIMENTO ===\n");
        System.out.println("Este sistema simula uma fila FIFO (First In, First Out)");
        System.out.println("Os primeiros a chegar são os primeiros a serem atendidos.\n");
        
        // Passo 4: Menu principal
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            System.out.println(); // Linha em branco para organização
            
            switch (opcao) {
                case 1:
                    adicionarPessoa(fila, scanner);
                    break;
                    
                case 2:
                    chamarProximaPessoa(fila);
                    break;
                    
                case 3:
                    exibirQuantidade(fila);
                    break;
                    
                case 4:
                    exibirFilaCompleta(fila);
                    break;
                    
                case 5:
                    System.out.println("Encerrando o sistema de fila. Até mais!");
                    break;
                    
                default:
                    System.out.println("❌ Opção inválida! Digite um número entre 1 e 5.");
                    break;
            }
            
        } while (opcao != 5);
        
        scanner.close();
    }
    
    // ===========================================
    // MÉTODO PARA EXIBIR O MENU
    // ===========================================
    public static void exibirMenu() {
        System.out.println("\n--- MENU DA FILA ---");
        System.out.println("1 - Adicionar pessoa à fila (enfileirar)");
        System.out.println("2 - Chamar próxima pessoa (desenfileirar)");
        System.out.println("3 - Exibir quantas pessoas estão na fila");
        System.out.println("4 - Exibir a fila completa");
        System.out.println("5 - Sair");
    }
    
    // ===========================================
    // MÉTODO PARA ADICIONAR PESSOA À FILA (ENFILEIRAR)
    // Usa addLast() que adiciona no final da lista
    // ===========================================
    public static void adicionarPessoa(LinkedList<String> fila, Scanner scanner) {
        System.out.println("--- ADICIONAR PESSOA À FILA ---");
        
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
        
        // Validar se o nome não está vazio
        if (nome.trim().isEmpty()) {
            System.out.println("❌ Nome não pode estar vazio!");
            return;
        }
        
        // addLast() adiciona o elemento no FINAL da fila (como na vida real)
        fila.addLast(nome);
        
        System.out.println("✅ " + nome + " entrou na fila!");
        System.out.println("Posição na fila: " + fila.size());
    }
    
    // ===========================================
    // MÉTODO PARA CHAMAR PRÓXIMA PESSOA (DESENFILEIRAR)
    // Usa removeFirst() que remove do início da lista
    // ===========================================
    public static void chamarProximaPessoa(LinkedList<String> fila) {
        System.out.println("--- CHAMAR PRÓXIMA PESSOA ---");
        
        // Verificar se a fila está vazia
        if (fila.isEmpty()) {
            System.out.println("❌ A fila está vazia! Ninguém para atender.");
            return;
        }
        
        // removeFirst() remove e retorna o elemento do INÍCIO da fila
        // Primeiro que entrou é o primeiro a sair (FIFO)
        String pessoaAtendida = fila.removeFirst();
        
        System.out.println("🔔 PRÓXIMO!");
        System.out.println("Atendendo: " + pessoaAtendida);
        System.out.println("✅ " + pessoaAtendida + " foi atendido(a) e removido(a) da fila.");
        
        if (!fila.isEmpty()) {
            System.out.println("Próximo da fila: " + fila.getFirst());
        }
    }
    
    // ===========================================
    // MÉTODO PARA EXIBIR QUANTAS PESSOAS ESTÃO NA FILA
    // ===========================================
    public static void exibirQuantidade(LinkedList<String> fila) {
        System.out.println("--- QUANTIDADE NA FILA ---");
        
        int tamanho = fila.size();
        
        if (tamanho == 0) {
            System.out.println("📭 A fila está vazia. Ninguém aguardando atendimento.");
        } else if (tamanho == 1) {
            System.out.println("👤 Há 1 pessoa aguardando na fila.");
        } else {
            System.out.println("👥 Há " + tamanho + " pessoas aguardando na fila.");
        }
    }
    
    // ===========================================
    // MÉTODO PARA EXIBIR A FILA COMPLETA
    // ===========================================
    public static void exibirFilaCompleta(LinkedList<String> fila) {
        System.out.println("--- FILA COMPLETA ---");
        
        if (fila.isEmpty()) {
            System.out.println("📭 A fila está vazia.");
            return;
        }
        
        System.out.println("\n📋 POSIÇÃO NA FILA (ordem de atendimento):");
        System.out.println("═".repeat(50));
        
        // Exibir a fila com numeração
        int posicao = 1;
        for (String pessoa : fila) {
            System.out.printf("  %d° ─ %s\n", posicao, pessoa);
            posicao++;
        }
        
        System.out.println("═".repeat(50));
        System.out.println("👉 PRÓXIMO A SER ATENDIDO: " + fila.getFirst());
        System.out.println("👈 ÚLTIMO DA FILA: " + fila.getLast());
    }
}
