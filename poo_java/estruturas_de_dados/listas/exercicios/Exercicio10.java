// Passo 1: Importar as classes necessárias
import java.util.ArrayList;      // Para armazenar as tarefas
import java.util.Scanner;        // Para ler entrada do usuário
import java.io.FileWriter;       // Para escrever no arquivo
import java.io.BufferedWriter;   // Para escrita eficiente
import java.io.BufferedReader;   // Para leitura eficiente
import java.io.FileReader;       // Para ler o arquivo
import java.io.File;             // Para verificar se o arquivo existe
import java.io.IOException;      // Para tratar exceções de I/O

public class Exercicio10 {
    
    // Lista para armazenar as tarefas (cada String é uma tarefa)
    private static ArrayList<String> tarefas = new ArrayList<>();
    
    // Nome do arquivo onde as tarefas serão salvas
    private static final String NOME_ARQUIVO = "tarefas.txt";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== LISTA DE TAREFAS COM PERSISTÊNCIA ===\n");
        
        // Passo 1: Carregar tarefas do arquivo ao iniciar
        carregarTarefasDoArquivo();
        
        int opcao;
        
        do {
            // Exibir menu
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            
            System.out.println();
            
            switch (opcao) {
                case 1:
                    adicionarTarefa(scanner);
                    break;
                case 2:
                    removerTarefa(scanner);
                    break;
                case 3:
                    listarTarefas();
                    break;
                case 4:
                    concluirTarefa(scanner);
                    break;
                case 5:
                    System.out.println("Salvando tarefas e encerrando...");
                    salvarTarefasNoArquivo();
                    System.out.println("✅ Programa encerrado com sucesso!");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite um número entre 1 e 5.");
            }
            
        } while (opcao != 5);
        
        scanner.close();
    }
    
    // ===========================================
    // MÉTODO PARA EXIBIR O MENU
    // ===========================================
    public static void exibirMenu() {
        System.out.println("\n--- MINHAS TAREFAS ---");
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Remover tarefa");
        System.out.println("3 - Listar tarefas");
        System.out.println("4 - Marcar tarefa como concluída");
        System.out.println("5 - Sair e salvar");
        System.out.println("-----------------------");
    }
    
    // ===========================================
    // MÉTODO PARA CARREGAR TAREFAS DO ARQUIVO
    // ===========================================
    public static void carregarTarefasDoArquivo() {
        File arquivo = new File(NOME_ARQUIVO);
        
        // Verificar se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("📝 Arquivo de tarefas não encontrado. Um novo será criado ao salvar.\n");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int contador = 0;
            
            System.out.println("📂 Carregando tarefas do arquivo...\n");
            
            // Ler cada linha do arquivo
            while ((linha = reader.readLine()) != null) {
                // Ignorar linhas vazias
                if (!linha.trim().isEmpty()) {
                    tarefas.add(linha);
                    contador++;
                    System.out.println("   ✓ Carregada: " + linha);
                }
            }
            
            System.out.println("\n✅ " + contador + " tarefa(s) carregada(s) com sucesso!\n");
            
        } catch (IOException e) {
            System.out.println("❌ Erro ao carregar o arquivo: " + e.getMessage());
        }
    }
    
    // ===========================================
    // MÉTODO PARA SALVAR TAREFAS NO ARQUIVO
    // ===========================================
    public static void salvarTarefasNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            
            // Escrever cada tarefa em uma linha separada
            for (String tarefa : tarefas) {
                writer.write(tarefa);
                writer.newLine(); // Pular para a próxima linha
            }
            
            System.out.println("💾 " + tarefas.size() + " tarefa(s) salva(s) no arquivo '" + NOME_ARQUIVO + "'");
            
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    
    // ===========================================
    // MÉTODO PARA ADICIONAR UMA TAREFA
    // ===========================================
    public static void adicionarTarefa(Scanner scanner) {
        System.out.println("--- ADICIONAR TAREFA ---");
        System.out.print("Digite a descrição da tarefa: ");
        String tarefa = scanner.nextLine();
        
        // Validar se a tarefa não está vazia
        if (tarefa.trim().isEmpty()) {
            System.out.println("❌ A tarefa não pode estar vazia!");
            return;
        }
        
        // Verificar se a tarefa já existe
        if (tarefas.contains(tarefa)) {
            System.out.println("⚠️ Esta tarefa já existe na lista!");
            return;
        }
        
        tarefas.add(tarefa);
        System.out.println("✅ Tarefa \"" + tarefa + "\" adicionada com sucesso!");
        System.out.println("📊 Total de tarefas: " + tarefas.size());
    }
    
    // ===========================================
    // MÉTODO PARA REMOVER UMA TAREFA
    // ===========================================
    public static void removerTarefa(Scanner scanner) {
        System.out.println("--- REMOVER TAREFA ---");
        
        if (tarefas.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa para remover!");
            return;
        }
        
        // Exibir tarefas numeradas
        System.out.println("\nTarefas atuais:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + " - " + tarefas.get(i));
        }
        
        System.out.print("\nDigite o número da tarefa que deseja remover (0 para cancelar): ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        
        if (numero == 0) {
            System.out.println("❌ Operação cancelada.");
            return;
        }
        
        if (numero >= 1 && numero <= tarefas.size()) {
            String removida = tarefas.remove(numero - 1);
            System.out.println("✅ Tarefa \"" + removida + "\" removida com sucesso!");
        } else {
            System.out.println("❌ Número inválido!");
        }
    }
    
    // ===========================================
    // MÉTODO PARA LISTAR TODAS AS TAREFAS
    // ===========================================
    public static void listarTarefas() {
        System.out.println("--- LISTA DE TAREFAS ---");
        
        if (tarefas.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa cadastrada!");
            System.out.println("Adicione tarefas usando a opção 1.");
            return;
        }
        
        System.out.println("\n📋 SUAS TAREFAS:");
        System.out.println("═".repeat(50));
        
        for (int i = 0; i < tarefas.size(); i++) {
            // Exibir com status (podemos melhorar com marcador de concluída)
            System.out.printf("  %2d. %s\n", (i + 1), tarefas.get(i));
        }
        
        System.out.println("═".repeat(50));
        System.out.println("Total de tarefas: " + tarefas.size());
    }
    
    // ===========================================
    // MÉTODO PARA MARCAR TAREFA COMO CONCLUÍDA
    // ===========================================
    public static void concluirTarefa(Scanner scanner) {
        System.out.println("--- MARCAR TAREFA COMO CONCLUÍDA ---");
        
        if (tarefas.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa para concluir!");
            return;
        }
        
        // Exibir tarefas numeradas
        System.out.println("\nTarefas pendentes:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + " - " + tarefas.get(i));
        }
        
        System.out.print("\nDigite o número da tarefa concluída (0 para cancelar): ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        
        if (numero == 0) {
            System.out.println("❌ Operação cancelada.");
            return;
        }
        
        if (numero >= 1 && numero <= tarefas.size()) {
            String concluida = tarefas.remove(numero - 1);
            System.out.println("🎉 Tarefa \"" + concluida + "\" CONCLUÍDA e removida da lista!");
            System.out.println("Parabéns por completar mais uma tarefa!");
        } else {
            System.out.println("❌ Número inválido!");
        }
    }
}
