// Para facilitar o entendimento de cada parte do código, adicionei comentários detalhados que explicam a função de cada bloco, as variáveis e as transições de estado. O código continua em um único arquivo, pronto para ser compilado e executado.

public class ExemploFSM {

    // 1. Definição dos Estados do Semáforo
    // Usamos 'enum' para criar um conjunto fixo de estados.
    // Isso torna o código mais seguro e legível.
    enum EstadoSemafaro {
        VERMELHO,
        AMARELO,
        VERDE
    }

    // 2. Implementação da Máquina de Estado Finito (FSM)
    // A classe `Semafaro` é a nossa FSM. Ela gerencia as transições entre os estados.
    static class Semafaro {

        // Variável que armazena o estado atual do semáforo.
        private EstadoSemafaro estadoAtual;

        // Construtor da classe.
        // O estado inicial do semáforo é sempre VERMELHO.
        public Semafaro() {
            this.estadoAtual = EstadoSemafaro.VERMELHO;
        }

        // Método que realiza a transição para o próximo estado.
        // O 'switch' verifica o estado atual e decide para qual estado mudar.
        
        public void proximoEstado() {
            switch (estadoAtual) {
                case VERMELHO:
                    // Quando o estado é VERMELHO, a próxima transição é para VERDE.
                    this.estadoAtual = EstadoSemafaro.VERDE;
                    System.out.println("Semáforo agora está VERDE.");
                    break;
                case VERDE:
                    // Do VERDE, a transição é para AMARELO.
                    this.estadoAtual = EstadoSemafaro.AMARELO;
                    System.out.println("Semáforo agora está AMARELO.");
                    break;
                case AMARELO:
                    // Do AMARELO, a transição é para VERMELHO.
                    this.estadoAtual = EstadoSemafaro.VERMELHO;
                    System.out.println("Semáforo agora está VERMELHO.");
                    break;
            }
        }

        // Método para obter o estado atual do semáforo.
        public EstadoSemafaro getEstadoAtual() {
            return estadoAtual;
        }
    }

    // 3. Método Principal para Executar o Exemplo
    public static void main(String[] args) {
        // Cria uma nova instância da nossa máquina de estado.
        Semafaro semafaro = new Semafaro();
        
        System.out.println("Estado inicial do semáforo: " + semafaro.getEstadoAtual());
        
        // Simula um ciclo completo do semáforo.
        System.out.println("--- Ciclo 1 ---");
        semafaro.proximoEstado(); // Transição 1: de VERMELHO para VERDE
        semafaro.proximoEstado(); // Transição 2: de VERDE para AMARELO
        semafaro.proximoEstado(); // Transição 3: de AMARELO para VERMELHO
        
        System.out.println("--- Ciclo 2 ---");
        semafaro.proximoEstado(); // Inicia um novo ciclo: de VERMELHO para VERDE
    }
}
