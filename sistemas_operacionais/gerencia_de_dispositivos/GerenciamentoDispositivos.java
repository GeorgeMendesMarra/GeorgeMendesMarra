/**
 * @file GerenciamentoDispositivos.java
 * @brief Exemplo didático de gerenciamento de dispositivos em sistemas operacionais.
 *
 * Este programa simula o funcionamento básico do gerenciamento de dispositivos,
 * representando conceitos como:
 * - Drivers de dispositivos;
 * - Dispositivos de entrada e saída;
 * - Buffer de dados;
 * - Tratamento de interrupções;
 * - Comunicação via barramento (bus).
 *
 * @author
 * @date 2025-11-07
 * @version 1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe abstrata que representa um dispositivo genérico.
 * Todo dispositivo deve ter um nome e um método de operação.
 */
abstract class Dispositivo {
    protected String nome; // Nome do dispositivo (ex: Teclado, Impressora)

    public Dispositivo(String nome) {
        this.nome = nome;
    }

    /**
     * Método abstrato que define o comportamento do dispositivo.
     * Cada tipo de dispositivo implementa sua própria forma de operar.
     */
    public abstract void operar();
}

/**
 * Classe que representa um dispositivo de entrada.
 * Exemplo: teclado, scanner ou sensor.
 */
class DispositivoEntrada extends Dispositivo {

    public DispositivoEntrada(String nome) {
        super(nome);
    }

    /**
     * Simula o funcionamento de um dispositivo de entrada capturando dados.
     */
    @Override
    public void operar() {
        System.out.println("🔹 [Entrada] O dispositivo " + nome + " está capturando dados...");
    }
}

/**
 * Classe que representa um dispositivo de saída.
 * Exemplo: monitor, impressora ou projetor.
 */
class DispositivoSaida extends Dispositivo {

    public DispositivoSaida(String nome) {
        super(nome);
    }

    /**
     * Simula o funcionamento de um dispositivo de saída exibindo dados.
     */
    @Override
    public void operar() {
        System.out.println("🔸 [Saída] O dispositivo " + nome + " está exibindo dados...");
    }
}

/**
 * Classe que simula um buffer de dados entre dispositivos.
 * O buffer serve como uma área de memória temporária para transferências.
 */
class Buffer {
    private Queue<String> dados = new LinkedList<>(); // Estrutura de fila para armazenar dados

    /**
     * Adiciona dados ao buffer.
     * @param dado conteúdo a ser armazenado.
     */
    public void adicionar(String dado) {
        dados.offer(dado); // Armazena o dado na fila
        System.out.println("🗃️ Dado adicionado ao buffer: " + dado);
    }

    /**
     * Remove o próximo dado do buffer.
     * @return conteúdo removido ou null se o buffer estiver vazio.
     */
    public String remover() {
        String dado = dados.poll(); // Remove o primeiro elemento da fila
        if (dado != null) {
            System.out.println("📤 Dado enviado do buffer: " + dado);
        } else {
            System.out.println("⚠️ Buffer vazio!");
        }
        return dado;
    }

    /**
     * Verifica se o buffer está vazio.
     * @return true se vazio, false caso contrário.
     */
    public boolean vazio() {
        return dados.isEmpty();
    }
}

/**
 * Classe que representa um driver de dispositivo.
 * O driver faz a comunicação entre o sistema operacional e o hardware.
 */
class Driver {
    private Dispositivo dispositivo; // Referência ao dispositivo controlado pelo driver

    public Driver(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    /**
     * Envia comando para o dispositivo operar.
     * O driver atua como "ponte" entre o sistema e o hardware.
     */
    public void enviarComando() {
        System.out.println("🧩 Driver ativando o dispositivo " + dispositivo.nome + "...");
        dispositivo.operar(); // Executa a operação específica do dispositivo
    }
}

/**
 * Classe principal que simula o sistema operacional gerenciando dispositivos.
 */
public class GerenciamentoDispositivos {

    /**
     * Método principal (main) que demonstra o gerenciamento de dispositivos.
     * @param args não utilizado neste exemplo.
     */
    public static void main(String[] args) {

        System.out.println("🖥️ Simulação: Gerenciamento de Dispositivos em um Sistema Operacional\n");

        // ======== ETAPA 1: CRIAÇÃO DOS DISPOSITIVOS ========
        DispositivoEntrada teclado = new DispositivoEntrada("Teclado USB");
        DispositivoSaida impressora = new DispositivoSaida("Impressora HP");

        // ======== ETAPA 2: CRIAÇÃO DOS DRIVERS ========
        Driver driverEntrada = new Driver(teclado);
        Driver driverSaida = new Driver(impressora);

        // ======== ETAPA 3: CRIAÇÃO DO BUFFER ========
        Buffer buffer = new Buffer(); // O buffer servirá como intermediário de dados

        // ======== ETAPA 4: SIMULAÇÃO DO CICLO DE E/S ========
        // O driver de entrada captura dados (simulando uma tecla pressionada, por exemplo)
        driverEntrada.enviarComando();

        // O dado capturado é armazenado no buffer
        buffer.adicionar("Comando: Imprimir Relatório");

        // O driver de saída (impressora) é acionado para processar a saída
        driverSaida.enviarComando();

        // O dado é retirado do buffer e enviado ao dispositivo de saída
        buffer.remover();

        // ======== ETAPA 5: FINALIZAÇÃO ========
        System.out.println("\n✅ Gerenciamento concluído com sucesso!");
    }
}
