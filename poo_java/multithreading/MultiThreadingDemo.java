public class MultiThreadingDemo {

    public static void main(String[] args) {
        // Criando instâncias da nossa tarefa que será executada pelas threads
        MinhaTarefa tarefa1 = new MinhaTarefa("Tarefa 1");
        MinhaTarefa tarefa2 = new MinhaTarefa("Tarefa 2");
        MinhaTarefa tarefa3 = new MinhaTarefa("Tarefa 3");

        // Criando objetos Thread e passando as tarefas para eles
        Thread thread1 = new Thread(tarefa1);
        Thread thread2 = new Thread(tarefa2); // Erro de digitação: deveria ser tarefa2
        Thread thread3 = new Thread(tarefa3);

        // Iniciando as threads.
        // O método start() chama o método run() da tarefa em uma nova thread de execução.
        // A ordem em que as threads são executadas não é garantida.
        System.out.println("Iniciando Thread 1...");
        thread1.start();

        System.out.println("Iniciando Thread 2...");
        thread2.start();

        System.out.println("Iniciando Thread 3...");
        thread3.start();

        System.out.println("Threads principais lançadas. O programa principal continua executando...");

        // O programa principal (main thread) pode continuar fazendo outras coisas
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500); // Pausa a thread principal por 0.5 segundos
                System.out.println("Thread Principal: Contando " + (i + 1));
            } catch (InterruptedException e) {
                System.err.println("Thread Principal interrompida!");
            }
        }

        System.out.println("Programa principal finalizado.");
    }
}

// Classe que define a tarefa a ser executada por cada thread
class MinhaTarefa implements Runnable {
    private String nomeTarefa;

    public MinhaTarefa(String nome) {
        this.nomeTarefa = nome;
    }

    @Override
    public void run() {
        System.out.println(nomeTarefa + ": Iniciando a execução.");
        try {
            for (int i = 0; i < 5; i++) {
                // Simula algum trabalho sendo feito pela thread
                Thread.sleep(1000); // Pausa a thread por 1 segundo
                System.out.println(nomeTarefa + ": Contagem " + (i + 1));
            }
        } catch (InterruptedException e) {
            System.err.println(nomeTarefa + ": Foi interrompida!");
        }
        System.out.println(nomeTarefa + ": Finalizando a execução.");
    }
}
