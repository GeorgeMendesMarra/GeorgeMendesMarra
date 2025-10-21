import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Programa simples de monitoramento de rede em linha de comando.
 * Faz ping e verifica portas TCP de hosts definidos em um arquivo.
 * Exibe os resultados no console, grava um log e emite um beep se houver falha.
 */
public class PingLoopComAlerta {
    public static void main(String[] args) {
        // Nome do arquivo com a lista de hosts e portas
        String arquivoHosts = "hosts.txt";

        // Arquivo de saída para registrar o histórico
        String arquivoLog = "status.log";

        // Tempo limite (em milissegundos) para cada tentativa de ping/conexão
        int timeout = 2000;

        // Intervalo entre cada rodada de checagem (em segundos)
        int intervalo = 10;

        // Lê os hosts do arquivo (ou cria um modelo caso não exista)
        List<Host> hosts = carregarHosts(arquivoHosts);
        if (hosts.isEmpty()) {
            System.out.println("Nenhum host encontrado em " + arquivoHosts);
            return;
        }

        System.out.println("Iniciando monitoramento...");
        System.out.println("Pressione Ctrl + C para parar.\n");

        // Loop principal: roda até o usuário interromper o programa
        while (true) {
            // Gera timestamp legível
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("⏰ " + timestamp);

            // Verifica cada host da lista
            for (Host h : hosts) {
                String linha = testarHost(h, timeout, timestamp);
                System.out.println(linha);
                append(arquivoLog, linha); // grava no log
            }

            System.out.println("---------------------------------------------\n");

            // Espera o intervalo antes de repetir
            try { Thread.sleep(intervalo * 1000L); }
            catch (InterruptedException e) { break; }
        }
    }

    /**
     * Testa um único host (ping + porta TCP opcional)
     * @param host objeto com endereço e porta
     * @param timeout tempo limite em ms
     * @param ts timestamp atual (para log)
     * @return linha de texto com o resultado formatado
     */
    private static String testarHost(Host host, int timeout, String ts) {
        // Teste de ping (com fallback caso ICMP esteja bloqueado)
        boolean ativo = isReachableRobusto(host.endereco, timeout);

        // Teste da porta TCP (se fornecida)
        boolean portaAberta = false;
        if (host.porta > 0) {
            portaAberta = isTcpOpen(host.endereco, host.porta, timeout);
        }

        // Se falhar o ping ou a porta, emite alerta sonoro
        if (!ativo || (host.porta > 0 && !portaAberta)) {
            beepSeguro();
        }

        // Monta o texto de resultado
        return String.format("[%s] Host: %-15s  Ping: %-5s%s",
                ts,
                host.endereco,
                ativo ? "OK ✅" : "FAIL ❌",
                host.porta > 0
                        ? String.format("  Porta %d: %s", host.porta, portaAberta ? "ABERTA ✅" : "FECHADA ❌")
                        : "");
    }

    /**
     * Tenta realizar ping via Java e, se falhar, usa o comando "ping" do sistema.
     */
    private static boolean isReachableRobusto(String host, int timeoutMs) {
        try {
            if (InetAddress.getByName(host).isReachable(timeoutMs)) return true;
        } catch (IOException ignored) { /* tenta fallback */ }
        return pingFallback(host, timeoutMs);
    }

    /**
     * Executa o comando "ping" nativo (Windows/Linux/macOS) como fallback.
     */
    private static boolean pingFallback(String host, int timeoutMs) {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        boolean isWindows = os.contains("win");
        List<String> cmd;

        // Monta comando conforme o sistema operacional
        if (isWindows) {
            // -n 1 → envia 1 pacote, -w → timeout em ms
            cmd = Arrays.asList("cmd.exe", "/c", "ping -n 1 -w " + timeoutMs + " " + host);
        } else {
            // -c 1 → envia 1 pacote, -W → timeout em segundos
            int sec = Math.max(1, (int)Math.ceil(timeoutMs / 1000.0));
            cmd = Arrays.asList("bash", "-lc", "ping -c 1 -W " + sec + " " + host);
        }

        try {
            // Executa o processo
            Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            boolean finished = p.waitFor(Math.max(1000, timeoutMs + 500), java.util.concurrent.TimeUnit.MILLISECONDS);

            if (!finished) { p.destroyForcibly(); return false; }
            return p.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se uma porta TCP está aberta tentando conectar.
     */
    private static boolean isTcpOpen(String host, int port, int timeoutMs) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(host, port), timeoutMs);
            return true; // conexão OK → porta aberta
        } catch (IOException e) {
            return false; // falhou → porta fechada
        }
    }

    /**
     * Emite um beep que funciona mesmo em ambientes sem interface gráfica.
     */
    private static void beepSeguro() {
        try {
            // Método padrão do Java (requer AWT)
            java.awt.Toolkit.getDefaultToolkit().beep();
        } catch (Throwable t) {
            // Fallback: envia o caractere BEL (funciona em muitos terminais)
            System.out.print("\007");
            System.out.flush();
        }
    }

    /**
     * Lê o arquivo de hosts (ou cria um modelo se não existir).
     */
    private static List<Host> carregarHosts(String arquivo) {
        List<Host> lista = new ArrayList<>();
        File f = new File(arquivo);

        // Se o arquivo não existir, cria um modelo padrão
        if (!f.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
                pw.println("# endereco,porta(opcional)");
                pw.println("8.8.8.8,53");
                pw.println("1.1.1.1,53");
                pw.println("192.168.1.1,80");
            } catch (IOException ignored) {}
            return lista; // vazio; usuário edita e roda novamente
        }

        // Lê linha por linha
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;
                String[] p = linha.split(",");
                String addr = p[0].trim();
                int port = (p.length > 1 && !p[1].trim().isEmpty()) ? Integer.parseInt(p[1].trim()) : 0;
                lista.add(new Host(addr, port));
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler " + arquivo + ": " + e.getMessage());
        }
        return lista;
    }

    /**
     * Grava uma linha de texto no arquivo de log.
     */
    private static void append(String path, String linha) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(linha);
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }
    }

    /**
     * Classe simples que representa um host com endereço e porta.
     */
    static class Host {
        final String endereco;
        final int porta;
        Host(String e, int p){ this.endereco = e; this.porta = p; }
    }
}
