import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ===== SNMP4J =====
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * Monitor simples de DNS com Ping + Porta TCP + SNMP (v2c).
 *
 * Uso:
 *   java -cp .:snmp4j-<versao>.jar PingDnsMonitorSnmp [intervalo_s] [community] [oid]
 *
 * Exemplos:
 *   java -cp .:snmp4j-3.7.1.jar PingDnsMonitorSnmp
 *   java -cp .:snmp4j-3.7.1.jar PingDnsMonitorSnmp 5
 *   java -cp .:snmp4j-3.7.1.jar PingDnsMonitorSnmp 10 public 1.3.6.1.2.1.1.3.0   // sysUpTime.0
 *
 * No Windows, use ';' no classpath:
 *   java -cp .;snmp4j-3.7.1.jar PingDnsMonitorSnmp 5
 */
public class PingDnsMonitorSnmp {

    // ==== Parâmetros fixos ====
    static final int TIMEOUT_MS = 2000;            // ping, TCP e SNMP timeouts
    static final String ARQUIVO_LOG = "status.log";
    static final int SNMP_PORT = 161;              // porta SNMP padrão
    static final int SNMP_RETRIES = 1;

    public static void main(String[] args) {
        // ===== CLI: intervalo + community + oid =====
        int intervalo = 10;                         // padrão 10s
        String community = "public";                // padrão
        String oid = "1.3.6.1.2.1.1.5.0";           // sysName.0 por padrão

        if (args.length > 0) {
            try { intervalo = Math.max(1, Integer.parseInt(args[0])); }
            catch (NumberFormatException e) { System.out.println("⚠️  Intervalo inválido. Usando 10s."); }
        }
        if (args.length > 1 && !args[1].isBlank()) community = args[1];
        if (args.length > 2 && !args[2].isBlank()) oid = args[2];

        // ===== Lista fixa de DNS (porta 53) =====
        List<Host> hosts = Arrays.asList(
                new Host("Google DNS (8.8.8.8)", "8.8.8.8", 53),
                new Host("Google DNS (8.8.4.4)", "8.8.4.4", 53),
                new Host("Cloudflare (1.1.1.1)", "1.1.1.1", 53),
                new Host("Cloudflare (1.0.0.1)", "1.0.0.1", 53),
                new Host("OpenDNS (208.67.222.222)", "208.67.222.222", 53),
                new Host("OpenDNS (208.67.220.220)", "208.67.220.220", 53),
                new Host("Quad9 (9.9.9.9)", "9.9.9.9", 53),
                new Host("AdGuard (94.140.14.14)", "94.140.14.14", 53),
                new Host("AdGuard (94.140.15.15)", "94.140.15.15", 53),
                new Host("Control D (76.76.2.0)", "76.76.2.0", 53),
                new Host("Control D (76.76.10.0)", "76.76.10.0", 53),
                new Host("CleanBrowsing (185.228.168.9)", "185.228.168.9", 53),
                new Host("CleanBrowsing (185.228.169.9)", "185.228.169.9", 53),
                new Host("Alternate (198.101.242.72)", "198.101.242.72", 53),
                new Host("Alternate (23.253.163.53)", "23.253.163.53", 53)
        );

        System.out.println("Monitor de DNS + SNMP iniciado. Ctrl+C para parar.");
        System.out.printf("Intervalo: %ds | SNMP v2c community: '%s' | OID: %s%n%n", intervalo, community, oid);

        while (true) {
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.out.println("⏰ " + ts);

            for (Host h : hosts) {
                Resultado r = testarTudo(h, TIMEOUT_MS, community, oid);
                String linha = formatarLinha(ts, r);
                System.out.println(linha);
                appendLog(ARQUIVO_LOG, linha);

                // beep em falhas (ping, porta ou SNMP)
                if (!r.pingOk || !r.portaOk || !r.snmpOk) beepSeguro();
            }

            System.out.println("---------------------------------------------------------------\n");
            try { Thread.sleep(intervalo * 1000L); } catch (InterruptedException e) { break; }
        }
    }

    // ===== Testes =====
    static Resultado testarTudo(Host h, int timeoutMs, String community, String oid) {
        boolean pingOk = isReachableRobusto(h.addr, timeoutMs);
        boolean portaOk = isTcpOpen(h.addr, h.porta, timeoutMs);
        SnmpResult snmp = snmpGet(h.addr, community, oid, timeoutMs);

        return new Resultado(h.nome, h.addr, h.porta, pingOk, portaOk, snmp.ok, snmp.value, snmp.errorText);
    }

    // Ping via Java com fallback para 'ping' do SO
    static boolean isReachableRobusto(String host, int timeoutMs) {
        try { if (InetAddress.getByName(host).isReachable(timeoutMs)) return true; }
        catch (Exception ignored) {}
        return pingFallback(host, timeoutMs);
    }
    static boolean pingFallback(String host, int timeoutMs) {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        boolean isWin = os.contains("win");
        List<String> cmd;
        if (isWin) cmd = Arrays.asList("cmd.exe", "/c", "ping -n 1 -w " + timeoutMs + " " + host);
        else {
            int sec = Math.max(1, (int)Math.ceil(timeoutMs / 1000.0));
            cmd = Arrays.asList("bash", "-lc", "ping -c 1 -W " + sec + " " + host);
        }
        try {
            Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            boolean ok = p.waitFor(Math.max(1000, timeoutMs + 500), java.util.concurrent.TimeUnit.MILLISECONDS);
            if (!ok) { p.destroyForcibly(); return false; }
            return p.exitValue() == 0;
        } catch (Exception e) { return false; }
    }

    // TCP
    static boolean isTcpOpen(String host, int port, int timeoutMs) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(host, port), timeoutMs);
            return true;
        } catch (Exception e) { return false; }
    }

    // ===== SNMP (v2c) =====
    static SnmpResult snmpGet(String host, String community, String oidStr, int timeoutMs) {
        TransportMapping<UdpAddress> transport = null;
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            CommunityTarget<UdpAddress> target = new CommunityTarget<>();
            target.setCommunity(new OctetString(community));
            target.setAddress(new UdpAddress(host + "/" + SNMP_PORT));
            target.setVersion(SnmpConstants.version2c);
            target.setTimeout(timeoutMs);
            target.setRetries(SNMP_RETRIES);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oidStr)));
            pdu.setType(PDU.GET);

            ResponseEvent<?> event = snmp.get(pdu, target);
            if (event == null || event.getResponse() == null) {
                return new SnmpResult(false, null, "Sem resposta");
            }
            PDU response = event.getResponse();
            if (response.getErrorStatus() != PDU.noError) {
                return new SnmpResult(false, null, "Erro SNMP: " + response.getErrorStatusText());
            }
            VariableBinding vb = response.get(0);
            String value = vb.getVariable().toString();
            return new SnmpResult(true, value, null);
        } catch (Exception e) {
            return new SnmpResult(false, null, e.getMessage());
        } finally {
            try { if (snmp != null) snmp.close(); } catch (Exception ignored) {}
            try { if (transport != null) transport.close(); } catch (Exception ignored) {}
        }
    }

    // ===== Util =====
    static String formatarLinha(String ts, Resultado r) {
        String snmpStr = r.snmpOk ? ("SNMP OK ✅ (" + r.snmpValue + ")")
                                  : ("SNMP FAIL ❌" + (r.snmpError != null ? " [" + r.snmpError + "]" : ""));
        return String.format("[%s] %-28s %-15s  Ping: %-5s  Porta %d: %-9s  %s",
                ts,
                r.nome,
                r.addr,
                r.pingOk ? "OK ✅" : "FAIL ❌",
                r.porta,
                r.portaOk ? "ABERTA ✅" : "FECHADA ❌",
                snmpStr);
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

    // ===== Modelos =====
    static class Host {
        final String nome; final String addr; final int porta;
        Host(String nome, String addr, int porta) { this.nome = nome; this.addr = addr; this.porta = porta; }
    }
    static class Resultado {
        final String nome, addr; final int porta;
        final boolean pingOk, portaOk, snmpOk; final String snmpValue, snmpError;
        Resultado(String nome, String addr, int porta, boolean pingOk, boolean portaOk, boolean snmpOk, String snmpValue, String snmpError) {
            this.nome = nome; this.addr = addr; this.porta = porta;
            this.pingOk = pingOk; this.portaOk = portaOk;
            this.snmpOk = snmpOk; this.snmpValue = snmpValue; this.snmpError = snmpError;
        }
    }
    static class SnmpResult {
        final boolean ok; final String value; final String errorText;
        SnmpResult(boolean ok, String value, String errorText) { this.ok = ok; this.value = value; this.errorText = errorText; }
    }
}
