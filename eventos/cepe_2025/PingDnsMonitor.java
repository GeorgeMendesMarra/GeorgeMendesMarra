import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Monitor simples de servidores DNS sem arquivo externo.
 * Verifica ping e porta TCP 53 dos principais provedores.
 * Mostra no console, grava log e emite beep em falhas.
 */
public class PingDnsMonitor {

    // ==== Parâmetros básicos ====
    static final int TIMEOUT_MS = 2000;  // ping/conexão
    static final int INTERVALO_S = 10;   // intervalo entre rodadas
    static final String ARQUIVO_LOG = "status.log";

    public static void main(String[] args) {
        // Lista fixa de DNS (porta 53)
        List<Host> hosts = new ArrayList<>();

        // Google DNS
        hosts.add(new Host("Google DNS (8.8.8.8)",     "8.8.8.8", 53));
        hosts.add(new Host("Google DNS (8.8.4.4)",     "8.8.4.4", 53));

        // Cloudflare DNS
        hosts.add(new Host("Cloudflare (1.1.1.1)",     "1.1.1.1", 53));
        hosts.add(new Host("Cloudflare (1.0.0.1)",     "1.0.0.1", 53));

        // OpenDNS
        hosts.add(new Host("OpenDNS (208.67.222.222)","208.67.222.222", 53));
        hosts.add(new Host("OpenDNS (208.67.220.220)","208.67.220.220", 53));

        // Quad9
        hosts.add(new Host("Quad9 (9.9.9.9)",         "9.9.9.9", 53));

        // AdGuard DNS
        hosts.add(new Host("AdGuard (94.140.14.14)",  "94.140.14.14", 53));
        hosts.add(new Host("AdGuard (94.140.15.15)",  "94.140.15.15", 53));

        // Control D
        hosts.add(new Host("Control D (76.76.2.0)",   "76.76.2.0", 53));
        hosts.add(new Host("Control D (76.76.10.0)",  "76.76.10.0", 53));

        // CleanBrowsing
        hosts.add(new Host("CleanBrowsing (185.228.168.9)", "185.228.168.9", 53));
        hosts.add(new Host("CleanBrowsing (185.228.169.9)", "185.228.169.9", 53));

        // Alternate DNS
        hosts.add(new Host("Alternate (198.101.242.72)", "198.101.242.72", 53));
        hosts.add(new Host("Alternate (23.253.163.53)",  "23.253.163.53", 53));

        if (hosts.isEmpty()) {
            System.out.println("Nenhum host configurado.");
            return;
        }

        System.out.println("Monitor de DNS iniciado. Ctrl+C para parar.\n");

        while (true) {
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("⏰ " + ts);

            for (Host h : hosts) {
                Resultado r = testar(h, TIMEOUT_MS);
                String linha = formatarLinha(ts, r);
                System.out.println(linha);
                appendLog(ARQUIVO_LOG, linha);

                // Beep em falhas de ping ou porta
                if (!r.pingOk || !r.portaOk) beepSeguro();
            }

            System.out.println("---------------------------------------------\n");
            try { Thread.sleep(INTERVALO_S * 1000L); }
            catch (InterruptedException e) { break; }
        }
    }

    // ====== Núcleo dos testes ======
    static Resultado testar(Host h, int timeoutMs) {
        boolean pingOk = isReachableRobusto(h.addr, timeoutMs);
        boolean portaOk = isTcpOpen(h.addr, h.porta, timeoutMs);
        return new Resultado(h.nome, h.addr, h.porta, pingOk, portaOk);
    }

    // Ping via Java e fallback para comando nativo
    static boolean isReachableRobusto(String host, int timeoutMs) {
        try {
            if (InetAddress.getByName(host).isReachable(timeoutMs)) return true;
        } catch (Exception ignored) {}
        return pingFallback(host, timeoutMs);
    }

    // Executa "ping" do SO (Windows: -n 1 -w ms | Unix: -c 1 -W s)
    static boolean pingFallback(String host, int timeoutMs) {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        boolean isWin = os.contains("win");
        List<String> cmd;
        if (isWin) {
            cmd = Arrays.asList("cmd.exe", "/c", "ping -n 1 -w " + timeoutMs + " " + host);
        } else {
            int sec = Math.max(1, (int)Math.ceil(timeoutMs / 1000.0));
            cmd = Arrays.asList("bash", "-lc", "ping -c 1 -W " + sec + " " + host);
        }
        try {
            Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            boolean ok = p.waitFor(Math.max(1000, timeoutMs + 500),
                    java.util.concurrent.TimeUnit.MILLISECONDS);
            if (!ok) { p.destroyForcibly(); return false; }
            return p.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Porta TCP 53
    static boolean isTcpOpen(String host, int port, int timeoutMs) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(host, port), timeoutMs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ====== Util ======
    static String formatarLinha(String ts, Resultado r) {
        return String.format("[%s] %-28s  %-15s  Ping: %-5s  Porta %d: %s",
                ts,
                r.nome,
                r.addr,
                r.pingOk ? "OK ✅" : "FAIL ❌",
                r.porta,
                r.portaOk ? "ABERTA ✅" : "FECHADA ❌");
    }

    static void appendLog(String path, String linha) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(linha);
        } catch (Exception e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
        }
    }

    static void beepSeguro() {
        try { java.awt.Toolkit.getDefaultToolkit().beep(); }
        catch (Throwable t) { System.out.print("\007"); System.out.flush(); }
    }

    // ====== Modelos ======
    static class Host {
        final String nome; final String addr; final int porta;
        Host(String nome, String addr, int porta) { this.nome = nome; this.addr = addr; this.porta = porta; }
    }
    static class Resultado {
        final String nome; final String addr; final int porta;
        final boolean pingOk; final boolean portaOk;
        Resultado(String nome, String addr, int porta, boolean pingOk, boolean portaOk) {
            this.nome = nome; this.addr = addr; this.porta = porta; this.pingOk = pingOk; this.portaOk = portaOk;
        }
    }
}
