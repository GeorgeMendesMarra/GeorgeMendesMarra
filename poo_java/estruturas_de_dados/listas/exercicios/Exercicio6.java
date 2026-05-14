// Passo 1: Importar as classes necessárias
import java.util.ArrayList; // Para usar ArrayList
import java.util.Scanner;   // Para ler entrada do teclado

public class Exercicio6 {
    public static void main(String[] args) {
        
        // Passo 2: Criar os dois ArrayLists para nomes e telefones
        // O mesmo índice nas duas listas representa o mesmo contato
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<String> telefones = new ArrayList<>();
        
        // Passo 3: Criar o Scanner para ler as opções do usuário
        Scanner scanner = new Scanner(System.in);
        
        int opcao; // Variável para armazenar a opção escolhida pelo usuário
        
        System.out.println("=== BEM-VINDO À AGENDA DE CONTATOS ===\n");
        
        // Passo 4: Usar um laço do-while para garantir que o menu apareça pelo menos uma vez
        do {
            // Exibir o menu principal
            System.out.println("\n--- AGENDA DE CONTATOS ---");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Buscar contato");
            System.out.println("3 - Remover contato");
            System.out.println("4 - Listar todos os contatos");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente
            
            System.out.println(); // Linha em branco para organização
            
            // Passo 5: Implementar cada opção usando switch-case
            switch (opcao) {
                case 1:
                    // Opção 1: Adicionar contato
                    adicionarContato(nomes, telefones, scanner);
                    break;
                    
                case 2:
                    // Opção 2: Buscar contato
                    buscarContato(nomes, telefones, scanner);
                    break;
                    
                case 3:
                    // Opção 3: Remover contato
                    removerContato(nomes, telefones, scanner);
                    break;
                    
                case 4:
                    // Opção 4: Listar todos os contatos
                    listarContatos(nomes, telefones);
                    break;
                    
                case 5:
                    // Opção 5: Sair do programa
                    System.out.println("Encerrando a agenda. Até mais!");
                    break;
                    
                default:
                    // Caso o usuário digite uma opção inválida
                    System.out.println("❌ Opção inválida! Digite um número entre 1 e 5.");
                    break;
            }
            
        } while (opcao != 5); // Repete enquanto a opção for diferente de 5 (Sair)
        
        scanner.close(); // Fechar o scanner ao final do programa
    }
    
    // ===========================================
    // MÉTODO PARA ADICIONAR UM CONTATO
    // ===========================================
    public static void adicionarContato(ArrayList<String> nomes, 
                                        ArrayList<String> telefones, 
                                        Scanner scanner) {
        System.out.println("--- ADICIONAR CONTATO ---");
        
        System.out.print("Digite o nome do contato: ");
        String nome = scanner.nextLine();
        
        // Verificar se o contato já existe
        if (nomes.contains(nome)) {
            System.out.println("⚠️ Contato com este nome já existe na agenda!");
            System.out.println("Use um nome diferente ou remova o existente.");
            return; // Sai do método sem adicionar
        }
        
        System.out.print("Digite o telefone do contato: ");
        String telefone = scanner.nextLine();
        
        // Adicionar às listas (mesmo índice)
        nomes.add(nome);
        telefones.add(telefone);
        
        System.out.println("✅ Contato \"" + nome + "\" adicionado com sucesso!");
    }
    
    // ===========================================
    // MÉTODO PARA BUSCAR UM CONTATO
    // ===========================================
    public static void buscarContato(ArrayList<String> nomes, 
                                     ArrayList<String> telefones, 
                                     Scanner scanner) {
        System.out.println("--- BUSCAR CONTATO ---");
        
        System.out.print("Digite o nome do contato que deseja buscar: ");
        String nomeBusca = scanner.nextLine();
        
        // Procurar o contato na lista
        int posicao = encontrarPosicaoPorNome(nomes, nomeBusca);
        
        if (posicao != -1) {
            // Contato encontrado
            System.out.println("\n🔍 CONTATO ENCONTRADO:");
            System.out.println("   Nome: " + nomes.get(posicao));
            System.out.println("   Telefone: " + telefones.get(posicao));
        } else {
            // Contato não encontrado
            System.out.println("❌ Contato \"" + nomeBusca + "\" não encontrado na agenda.");
        }
    }
    
    // ===========================================
    // MÉTODO PARA REMOVER UM CONTATO
    // ===========================================
    public static void removerContato(ArrayList<String> nomes, 
                                      ArrayList<String> telefones, 
                                      Scanner scanner) {
        System.out.println("--- REMOVER CONTATO ---");
        
        System.out.print("Digite o nome do contato que deseja remover: ");
        String nomeRemocao = scanner.nextLine();
        
        // Procurar o contato na lista
        int posicao = encontrarPosicaoPorNome(nomes, nomeRemocao);
        
        if (posicao != -1) {
            // Confirmar a remoção
            System.out.println("\n⚠️ ATENÇÃO: Você está prestes a remover o seguinte contato:");
            System.out.println("   Nome: " + nomes.get(posicao));
            System.out.println("   Telefone: " + telefones.get(posicao));
            System.out.print("Digite 'SIM' para confirmar a remoção: ");
            
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("SIM")) {
                // Remover das duas listas (mesmo índice)
                String nomeRemovido = nomes.remove(posicao);
                String telefoneRemovido = telefones.remove(posicao);
                
                System.out.println("✅ Contato \"" + nomeRemovido + "\" removido com sucesso!");
            } else {
                System.out.println("❌ Remoção cancelada.");
            }
        } else {
            // Contato não encontrado
            System.out.println("❌ Contato \"" + nomeRemocao + "\" não encontrado na agenda.");
        }
    }
    
    // ===========================================
    // MÉTODO PARA LISTAR TODOS OS CONTATOS
    // ===========================================
    public static void listarContatos(ArrayList<String> nomes, 
                                      ArrayList<String> telefones) {
        System.out.println("--- LISTAR TODOS OS CONTATOS ---");
        
        // Verificar se a agenda está vazia
        if (nomes.isEmpty()) {
            System.out.println("📭 A agenda está vazia. Adicione contatos primeiro!");
            return;
        }
        
        // Exibir todos os contatos
        System.out.println("\n📒 AGENDA DE CONTATOS:");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < nomes.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), "=".repeat(40));
            System.out.printf("   Nome: %s\n", nomes.get(i));
            System.out.printf("   Telefone: %s\n", telefones.get(i));
            System.out.println();
        }
        System.out.println("=".repeat(50));
        System.out.println("Total de contatos: " + nomes.size());
    }
    
    // ===========================================
    // MÉTODO AUXILIAR: Encontrar posição pelo nome
    // ===========================================
    public static int encontrarPosicaoPorNome(ArrayList<String> nomes, String nomeBusca) {
        // Percorre a lista de nomes procurando pelo nome informado
        for (int i = 0; i < nomes.size(); i++) {
            // equalsIgnoreCase ignora diferenças entre maiúsculas e minúsculas
            if (nomes.get(i).equalsIgnoreCase(nomeBusca)) {
                return i; // Retorna a posição onde o contato foi encontrado
            }
        }
        return -1; // Retorna -1 se o contato não foi encontrado
    }
}
