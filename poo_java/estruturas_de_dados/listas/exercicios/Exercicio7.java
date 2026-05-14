// Passo 1: Importar as classes necessárias
import java.util.ArrayList; // Para usar ArrayList
import java.util.Scanner;   // Para ler entrada do teclado

public class Exercicio7 {
    public static void main(String[] args) {
        
        // Passo 2: Criar as duas listas para descrições e prioridades
        // O mesmo índice nas duas listas representa a mesma tarefa
        ArrayList<String> descricoes = new ArrayList<>();
        ArrayList<Integer> prioridades = new ArrayList<>();
        
        // Passo 3: Criar o Scanner para ler as opções do usuário
        Scanner scanner = new Scanner(System.in);
        
        int opcao; // Variável para armazenar a opção escolhida
        
        System.out.println("=== GERENCIADOR DE TAREFAS COM PRIORIDADE ===\n");
        System.out.println("Prioridades:");
        System.out.println("  1 = ALTA (urgente)");
        System.out.println("  2 = MÉDIA (importante)");
        System.out.println("  3 = BAIXA (pode esperar)");
        
        // Passo 4: Usar um laço do-while para exibir o menu repetidamente
        do {
            // Exibir o menu principal
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas por prioridade");
            System.out.println("3 - Marcar tarefa como concluída");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente
            
            System.out.println(); // Linha em branco para organização
            
            // Passo 5: Implementar cada opção usando switch-case
            switch (opcao) {
                case 1:
                    adicionarTarefa(descricoes, prioridades, scanner);
                    break;
                    
                case 2:
                    listarTarefasPorPrioridade(descricoes, prioridades);
                    break;
                    
                case 3:
                    marcarTarefaConcluida(descricoes, prioridades, scanner);
                    break;
                    
                case 4:
                    System.out.println("Encerrando o gerenciador de tarefas. Até mais!");
                    break;
                    
                default:
                    System.out.println("❌ Opção inválida! Digite um número entre 1 e 4.");
                    break;
            }
            
        } while (opcao != 4); // Repete enquanto a opção for diferente de 4 (Sair)
        
        scanner.close();
    }
    
    // ===========================================
    // MÉTODO PARA ADICIONAR UMA TAREFA
    // ===========================================
    public static void adicionarTarefa(ArrayList<String> descricoes, 
                                       ArrayList<Integer> prioridades, 
                                       Scanner scanner) {
        System.out.println("--- ADICIONAR TAREFA ---");
        
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        
        // Validar se a descrição não está vazia
        if (descricao.trim().isEmpty()) {
            System.out.println("❌ Descrição não pode estar vazia!");
            return;
        }
        
        int prioridade;
        while (true) {
            System.out.print("Digite a prioridade (1=Alta, 2=Média, 3=Baixa): ");
            prioridade = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            
            if (prioridade >= 1 && prioridade <= 3) {
                break; // Prioridade válida, sai do loop
            } else {
                System.out.println("❌ Prioridade inválida! Digite 1, 2 ou 3.");
            }
        }
        
        // Adicionar às listas (mesmo índice)
        descricoes.add(descricao);
        prioridades.add(prioridade);
        
        // Exibir mensagem de sucesso com a prioridade por extenso
        String prioridadeTexto = obterTextoPrioridade(prioridade);
        System.out.println("✅ Tarefa \"" + descricao + "\" adicionada com sucesso!");
        System.out.println("   Prioridade: " + prioridadeTexto);
    }
    
    // ===========================================
    // MÉTODO PARA LISTAR TAREFAS POR PRIORIDADE
    // ===========================================
    public static void listarTarefasPorPrioridade(ArrayList<String> descricoes, 
                                                   ArrayList<Integer> prioridades) {
        System.out.println("--- MINHAS TAREFAS ---");
        
        // Verificar se a lista está vazia
        if (descricoes.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa cadastrada. Adicione tarefas primeiro!");
            return;
        }
        
        int contadorGlobal = 1; // Contador para numerar todas as tarefas
        
        // === PRIORIDADE ALTA (1) ===
        System.out.println("\n=== PRIORIDADE ALTA ===");
        boolean temPrioridade1 = false;
        
        for (int i = 0; i < descricoes.size(); i++) {
            if (prioridades.get(i) == 1) {
                System.out.println(contadorGlobal + ". " + descricoes.get(i) + " (Pri: " + prioridades.get(i) + " - ALTA)");
                temPrioridade1 = true;
                contadorGlobal++;
            }
        }
        
        if (!temPrioridade1) {
            System.out.println("   (Nenhuma tarefa com prioridade ALTA)");
        }
        
        // === PRIORIDADE MÉDIA (2) ===
        System.out.println("\n=== PRIORIDADE MÉDIA ===");
        boolean temPrioridade2 = false;
        
        for (int i = 0; i < descricoes.size(); i++) {
            if (prioridades.get(i) == 2) {
                System.out.println(contadorGlobal + ". " + descricoes.get(i) + " (Pri: " + prioridades.get(i) + " - MÉDIA)");
                temPrioridade2 = true;
                contadorGlobal++;
            }
        }
        
        if (!temPrioridade2) {
            System.out.println("   (Nenhuma tarefa com prioridade MÉDIA)");
        }
        
        // === PRIORIDADE BAIXA (3) ===
        System.out.println("\n=== PRIORIDADE BAIXA ===");
        boolean temPrioridade3 = false;
        
        for (int i = 0; i < descricoes.size(); i++) {
            if (prioridades.get(i) == 3) {
                System.out.println(contadorGlobal + ". " + descricoes.get(i) + " (Pri: " + prioridades.get(i) + " - BAIXA)");
                temPrioridade3 = true;
                contadorGlobal++;
            }
        }
        
        if (!temPrioridade3) {
            System.out.println("   (Nenhuma tarefa com prioridade BAIXA)");
        }
        
        System.out.println("\n📊 Total de tarefas: " + descricoes.size());
    }
    
    // ===========================================
    // MÉTODO PARA MARCAR TAREFA COMO CONCLUÍDA (REMOVER)
    // ===========================================
    public static void marcarTarefaConcluida(ArrayList<String> descricoes, 
                                              ArrayList<Integer> prioridades, 
                                              Scanner scanner) {
        System.out.println("--- MARCAR TAREFA COMO CONCLUÍDA ---");
        
        // Verificar se há tarefas para concluir
        if (descricoes.isEmpty()) {
            System.out.println("📭 Nenhuma tarefa cadastrada para concluir.");
            return;
        }
        
        // Mostrar todas as tarefas numeradas para facilitar a escolha
        System.out.println("\nTarefas pendentes:");
        System.out.println("-".repeat(40));
        
        for (int i = 0; i < descricoes.size(); i++) {
            String prioridadeTexto = obterTextoPrioridade(prioridades.get(i));
            System.out.printf("%d. %s [%s]\n", (i + 1), descricoes.get(i), prioridadeTexto);
        }
        
        System.out.println("-".repeat(40));
        
        // Solicitar qual tarefa concluir
        int escolha;
        while (true) {
            System.out.print("\nDigite o número da tarefa concluída (0 para cancelar): ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            
            if (escolha == 0) {
                System.out.println("❌ Operação cancelada.");
                return;
            }
            
            if (escolha >= 1 && escolha <= descricoes.size()) {
                break; // Escolha válida
            } else {
                System.out.println("❌ Número inválido! Digite um número entre 1 e " + descricoes.size());
            }
        }
        
        // Ajustar para índice da lista (começa em 0)
        int indice = escolha - 1;
        
        // Obter os dados da tarefa antes de remover
        String tarefaConcluida = descricoes.get(indice);
        int prioridade = prioridades.get(indice);
        String prioridadeTexto = obterTextoPrioridade(prioridade);
        
        // Confirmar a conclusão
        System.out.println("\n⚠️ Confirmar conclusão da tarefa:");
        System.out.println("   Tarefa: " + tarefaConcluida);
        System.out.println("   Prioridade: " + prioridadeTexto);
        System.out.print("Digite 'SIM' para confirmar: ");
        
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("SIM")) {
            // Remover das duas listas
            descricoes.remove(indice);
            prioridades.remove(indice);
            System.out.println("✅ Tarefa \"" + tarefaConcluida + "\" concluída e removida da lista!");
        } else {
            System.out.println("❌ Conclusão cancelada.");
        }
    }
    
    // ===========================================
    // MÉTODO AUXILIAR: Obter texto da prioridade
    // ===========================================
    public static String obterTextoPrioridade(int prioridade) {
        switch (prioridade) {
            case 1:
                return "ALTA";
            case 2:
                return "MÉDIA";
            case 3:
                return "BAIXA";
            default:
                return "DESCONHECIDA";
        }
    }
}
