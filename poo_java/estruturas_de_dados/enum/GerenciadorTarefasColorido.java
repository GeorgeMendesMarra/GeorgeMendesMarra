import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// Enum que define os status possíveis de uma tarefa
enum StatusTarefa {
    PENDENTE,       // Tarefa ainda não iniciada
    EM_ANDAMENTO,   // Tarefa em andamento
    CONCLUIDA       // Tarefa concluída
}

// Classe que representa uma Tarefa
class Tarefa {
    private String descricao;      // Descrição da tarefa
    private StatusTarefa status;   // Status da tarefa (usando enum)

    // Construtor da classe
    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.status = StatusTarefa.PENDENTE; // Inicialmente toda tarefa é PENDENTE
    }

    // Método para iniciar a tarefa
    public void iniciar() {
        this.status = StatusTarefa.EM_ANDAMENTO;
    }

    // Método para concluir a tarefa
    public void concluir() {
        this.status = StatusTarefa.CONCLUIDA;
    }

    // Getter para status
    public StatusTarefa getStatus() {
        return status;
    }

    // Getter para descrição
    public String getDescricao() {
        return descricao;
    }

    // Exibir informação da tarefa com cores no console
    public void exibirInfo() {
        String cor = ""; // Variável que armazenará o código da cor ANSI
        switch (status) {
            case PENDENTE -> cor = "\u001B[31m";      // Vermelho
            case EM_ANDAMENTO -> cor = "\u001B[33m";  // Amarelo
            case CONCLUIDA -> cor = "\u001B[32m";     // Verde
        }
        String reset = "\u001B[0m"; // Reseta a cor do texto
        System.out.println(cor + "Tarefa: " + descricao + " | Status: " + status + reset);
    }
}

// Classe principal do gerenciador
public class GerenciadorTarefasColorido {
    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = new ArrayList<>(); // Lista para armazenar as tarefas
        Scanner sc = new Scanner(System.in);           // Scanner para ler entrada do usuário
        int opcao;                                     // Variável para armazenar a opção do menu

        do {
            // Exibe o menu principal
            System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
            System.out.println("1 - Cadastrar tarefa");
            System.out.println("2 - Iniciar tarefa");
            System.out.println("3 - Concluir tarefa");
            System.out.println("4 - Listar tarefas (pendentes primeiro)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha deixada pelo nextInt

            switch(opcao) {
                case 1 -> {
                    // Cadastrar nova tarefa
                    System.out.print("Digite a descrição da tarefa: ");
                    String desc = sc.nextLine();
                    tarefas.add(new Tarefa(desc)); // Adiciona na lista
                    System.out.println("Tarefa cadastrada!");
                }

                case 2 -> {
                    // Iniciar uma tarefa existente
                    System.out.print("Digite o número da tarefa para iniciar: ");
                    int numIniciar = sc.nextInt() - 1; // Ajusta índice (usuário digita 1..N)
                    if (numIniciar >= 0 && numIniciar < tarefas.size()) {
                        tarefas.get(numIniciar).iniciar();
                        System.out.println("Tarefa iniciada!");
                    } else {
                        System.out.println("Número inválido!");
                    }
                }

                case 3 -> {
                    // Concluir uma tarefa existente
                    System.out.print("Digite o número da tarefa para concluir: ");
                    int numConcluir = sc.nextInt() - 1;
                    if (numConcluir >= 0 && numConcluir < tarefas.size()) {
                        tarefas.get(numConcluir).concluir();
                        System.out.println("Tarefa concluída!");
                    } else {
                        System.out.println("Número inválido!");
                    }
                }

                case 4 -> {
                    // Listar todas as tarefas ordenadas por status
                    System.out.println("\n---- LISTA DE TAREFAS (ORDENADAS POR STATUS) ----");
                    // Ordena tarefas: PENDENTE → EM_ANDAMENTO → CONCLUIDA
                    tarefas.sort(Comparator.comparingInt(t -> t.getStatus().ordinal()));
                    for (int i = 0; i < tarefas.size(); i++) {
                        System.out.print((i + 1) + " - "); // Mostra número da tarefa
                        tarefas.get(i).exibirInfo();      // Exibe info colorida
                    }
                }

                case 0 -> System.out.println("Saindo do gerenciador...");

                default -> System.out.println("Opção inválida!"); // Opção não reconhecida
            }

        } while(opcao != 0); // Repete o menu até o usuário escolher sair

        sc.close(); // Fecha o Scanner
    }
}
