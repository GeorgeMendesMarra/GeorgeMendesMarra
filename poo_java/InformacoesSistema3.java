import java.awt.GraphicsEnvironment; // Verifica/obtém o ambiente gráfico local (permite checar se é "headless", sem monitor)
import java.awt.GraphicsDevice;      // Representa cada monitor/tela conectado ao sistema
import java.awt.DisplayMode;         // Fornece resolução, taxa de atualização e profundidade de cor de um monitor
import java.io.File;                 // Usado aqui para listar unidades/discos e consultar espaço total/livre/utilizável
import java.lang.management.ManagementFactory;    // Fábrica para obter os MXBeans (CPU, memória, threads, runtime) da JVM
import java.lang.management.OperatingSystemMXBean; // Bean padrão com informações básicas do SO (núcleos, carga média)
import java.lang.management.RuntimeMXBean;        // Bean com dados de execução da JVM (tempo de atividade, nome do processo)
import java.lang.management.ThreadMXBean;         // Bean com estatísticas de threads (ativas, pico, total criadas)
import java.net.InetAddress;         // Representa endereços IP; usado para obter hostname e IP local
import java.net.NetworkInterface;    // Representa interfaces de rede (placas, adaptadores) para listar MAC/IPs
import java.net.UnknownHostException; // Exceção lançada quando o host local não pode ser resolvido
import java.time.Duration;           // Facilita formatar o tempo de atividade da JVM em horas/minutos/segundos
import java.time.ZoneId;             // Representa o fuso horário padrão do sistema
import java.util.Enumeration;        // Tipo de iteração retornado pelas APIs de rede (interfaces e endereços)
import java.util.Locale;             // Representa o idioma/região padrão configurado no sistema
import java.util.Map;                // Usado para armazenar as variáveis de ambiente (chave/valor)
import java.util.TreeMap;            // Map ordenado por chave, usado para exibir as variáveis de ambiente em ordem alfabética

/**
 * Classe utilitária para extrair o máximo de informações possível sobre
 * hardware e software da máquina em que a JVM está sendo executada,
 * usando apenas API padrão do Java (Java 9+).
 *
 * Observação: algumas informações (ex.: métricas de CPU/memória física
 * detalhadas) dependem da implementação com.sun.management.OperatingSystemMXBean,
 * disponível na maioria das JVMs HotSpot (Oracle/OpenJDK), mas não garantida
 * pela especificação. Por isso o acesso é feito via reflexão/cast defensivo.
 */
public class InformacoesSistema3 {

    // ==================== SISTEMA OPERACIONAL ====================

    public static void obterNomeSistemaOperacional() {
        System.out.println("Nome do sistema operacional: " + System.getProperty("os.name"));
    }

    public static void obterVersaoSistemaOperacional() {
        System.out.println("Versão do sistema operacional: " + System.getProperty("os.version"));
    }

    public static void obterArquiteturaSistemaOperacional() {
        System.out.println("Arquitetura do sistema operacional: " + System.getProperty("os.arch"));
    }

    public static void obterSeparadorArquivos() {
        System.out.println("Separador de arquivos: " + System.getProperty("file.separator"));
    }

    public static void obterSeparadorCaminhos() {
        System.out.println("Separador de caminhos: " + System.getProperty("path.separator"));
    }

    public static void obterSeparadorLinha() {
        System.out.println("Separador de linha: " + System.getProperty("line.separator").replace("\n", "\\n").replace("\r", "\\r"));
    }

    public static void obterDiretorioHomeUsuario() {
        System.out.println("Diretório home do usuário: " + System.getProperty("user.home"));
    }

    public static void obterNomeUsuario() {
        System.out.println("Nome do usuário: " + System.getProperty("user.name"));
    }

    public static void obterDiretorioTrabalhoAtual() {
        System.out.println("Diretório de trabalho atual: " + System.getProperty("user.dir"));
    }

    public static void obterDiretorioTemporario() {
        System.out.println("Diretório temporário: " + System.getProperty("java.io.tmpdir"));
    }

    // ==================== JAVA / JVM ====================

    public static void obterVersaoJava() {
        System.out.println("Versão do Java: " + System.getProperty("java.version"));
    }

    public static void obterFornecedorJava() {
        System.out.println("Fornecedor do Java: " + System.getProperty("java.vendor"));
    }

    public static void obterNomeVM() {
        System.out.println("Nome da JVM: " + System.getProperty("java.vm.name"));
    }

    public static void obterVersaoVM() {
        System.out.println("Versão da JVM: " + System.getProperty("java.vm.version"));
    }

    public static void obterFornecedorVM() {
        System.out.println("Fornecedor da JVM: " + System.getProperty("java.vm.vendor"));
    }

    public static void obterHomeJava() {
        System.out.println("Diretório de instalação do Java (java.home): " + System.getProperty("java.home"));
    }

    public static void obterClassPath() {
        System.out.println("Class path: " + System.getProperty("java.class.path"));
    }

    public static void obterTempoAtividadeJVM() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptimeMs = runtimeMXBean.getUptime();
        Duration d = Duration.ofMillis(uptimeMs);
        System.out.println("Tempo de atividade da JVM: " + d.toHours() + "h " + d.toMinutesPart() + "m " + d.toSecondsPart() + "s");
    }

    public static void obterNomeProcessoJVM() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("Identificação do processo da JVM (name@pid): " + runtimeMXBean.getName());
    }

    public static void obterPidProcessoAtual() {
        System.out.println("PID do processo atual: " + ProcessHandle.current().pid());
    }

    // ==================== CPU ====================

    public static void obterNumeroProcessadoresDisponiveis() {
        System.out.println("Número de processadores/núcleos disponíveis para a JVM: " + Runtime.getRuntime().availableProcessors());
    }

    public static void obterCargaMediaSistema() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        double loadAverage = osBean.getSystemLoadAverage();
        if (loadAverage >= 0) {
            System.out.println("Carga média do sistema (últimos 1 min): " + loadAverage);
        } else {
            System.out.println("Carga média do sistema: não disponível nesta plataforma");
        }
    }

    /**
     * Métricas estendidas de CPU e memória física, disponíveis na maioria
     * das JVMs HotSpot através de com.sun.management.OperatingSystemMXBean.
     * Acesso feito de forma defensiva (cast + verificação de tipo).
     */
    public static void obterMetricasEstendidasSO() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        try {
            Class<?> sunOsBeanClass = Class.forName("com.sun.management.OperatingSystemMXBean");
            if (sunOsBeanClass.isInstance(osBean)) {
                imprimirSeExistir(sunOsBeanClass, osBean, "getCpuLoad", "Uso de CPU do processo (0.0 a 1.0)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getSystemCpuLoad", "Uso de CPU do sistema (0.0 a 1.0)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getProcessCpuLoad", "Uso de CPU do processo atual (0.0 a 1.0)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getTotalMemorySize", "Memória física total (bytes)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getFreeMemorySize", "Memória física livre (bytes)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getTotalSwapSpaceSize", "Espaço total de swap (bytes)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getFreeSwapSpaceSize", "Espaço livre de swap (bytes)");
                imprimirSeExistir(sunOsBeanClass, osBean, "getProcessCpuTime", "Tempo de CPU usado pelo processo (ns)");
            } else {
                System.out.println("Métricas estendidas de SO: não suportadas nesta JVM");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Métricas estendidas de SO: com.sun.management indisponível nesta JVM");
        }
    }

    private static void imprimirSeExistir(Class<?> classe, Object instancia, String metodo, String rotulo) {
        try {
            Object valor = classe.getMethod(metodo).invoke(instancia);
            System.out.println(rotulo + ": " + valor);
        } catch (ReflectiveOperationException ignored) {
            // método não disponível nesta implementação/plataforma
        }
    }

    // ==================== MEMÓRIA (HEAP DA JVM) ====================

    public static void obterTotalRAM() {
        System.out.println("Memória heap total alocada pela JVM (bytes): " + Runtime.getRuntime().totalMemory());
    }

    public static void obterMaxRAM() {
        System.out.println("Memória heap máxima que a JVM tentará usar (bytes): " + Runtime.getRuntime().maxMemory());
    }

    public static void obterFreeRAM() {
        System.out.println("Memória heap livre (bytes): " + Runtime.getRuntime().freeMemory());
    }

    public static void obterMemoriaEmUso() {
        Runtime rt = Runtime.getRuntime();
        long usada = rt.totalMemory() - rt.freeMemory();
        System.out.println("Memória heap em uso (bytes): " + usada);
    }

    // ==================== DISCO / ARMAZENAMENTO ====================

    public static void obterEspacoEmDisco() {
        File[] raizes = File.listRoots();
        for (File raiz : raizes) {
            System.out.println("Unidade: " + raiz.getPath());
            System.out.println("  Espaço total (bytes): " + raiz.getTotalSpace());
            System.out.println("  Espaço livre (bytes): " + raiz.getFreeSpace());
            System.out.println("  Espaço utilizável (bytes): " + raiz.getUsableSpace());
        }
    }

    // ==================== REDE ====================

    public static void obterNomeHostEIP() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Nome do host: " + local.getHostName());
            System.out.println("Endereço IP local: " + local.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Nome do host / IP local: não foi possível determinar");
        }
    }

    public static void obterInterfacesRede() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                System.out.println("Interface: " + ni.getDisplayName());
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    System.out.println("  MAC: " + sb);
                }
                Enumeration<InetAddress> enderecos = ni.getInetAddresses();
                while (enderecos.hasMoreElements()) {
                    System.out.println("  Endereço: " + enderecos.nextElement().getHostAddress());
                }
            }
        } catch (Exception e) {
            System.out.println("Interfaces de rede: não foi possível listar (" + e.getMessage() + ")");
        }
    }

    // ==================== LOCALE / IDIOMA / FUSO HORÁRIO ====================

    public static void obterLocaleEIdioma() {
        Locale locale = Locale.getDefault();
        System.out.println("Locale padrão: " + locale);
        System.out.println("Idioma: " + locale.getDisplayLanguage());
        System.out.println("País/região: " + locale.getDisplayCountry());
        System.out.println("Codificação de caracteres padrão: " + System.getProperty("file.encoding"));
    }

    public static void obterFusoHorario() {
        System.out.println("Fuso horário do sistema: " + ZoneId.systemDefault());
    }

    // ==================== VARIÁVEIS DE AMBIENTE ====================

    public static void obterVariaveisAmbiente() {
        Map<String, String> env = new TreeMap<>(System.getenv());
        System.out.println("Variáveis de ambiente (" + env.size() + "):");
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }

    // ==================== THREADS ====================

    public static void obterInformacoesThreads() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        System.out.println("Número de threads ativas na JVM: " + threadBean.getThreadCount());
        System.out.println("Pico de threads desde o início da JVM: " + threadBean.getPeakThreadCount());
        System.out.println("Total de threads iniciadas desde o início da JVM: " + threadBean.getTotalStartedThreadCount());
    }

    // ==================== TELA / GRÁFICOS (se houver ambiente gráfico) ====================

    public static void obterResolucaoTela() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Resolução de tela: ambiente sem interface gráfica (headless)");
            return;
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] dispositivos = ge.getScreenDevices();
        for (int i = 0; i < dispositivos.length; i++) {
            DisplayMode dm = dispositivos[i].getDisplayMode();
            System.out.println("Monitor " + (i + 1) + ": " + dm.getWidth() + "x" + dm.getHeight()
                    + " @ " + dm.getRefreshRate() + "Hz, " + dm.getBitDepth() + " bits");
        }
    }

    // ==================== NAVEGADOR PADRÃO ====================

    public static void obterNavegadorWebPadrao() {
        // Não existe API padrão do Java para descobrir o navegador padrão do SO;
        // a propriedade "browser" normalmente não é definida pela JVM.
        String navegadorWeb = System.getProperty("browser");
        System.out.println("Navegador web padrão: " + (navegadorWeb != null ? navegadorWeb : "não detectado (sem API padrão do Java para isso)"));
    }

    // ==================== MAIN ====================

    public static void main(String[] args) {
        System.out.println("========== SISTEMA OPERACIONAL ==========");
        obterNomeSistemaOperacional();
        obterVersaoSistemaOperacional();
        obterArquiteturaSistemaOperacional();
        obterSeparadorArquivos();
        obterSeparadorCaminhos();
        obterSeparadorLinha();
        obterDiretorioHomeUsuario();
        obterNomeUsuario();
        obterDiretorioTrabalhoAtual();
        obterDiretorioTemporario();

        System.out.println("\n========== JAVA / JVM ==========");
        obterVersaoJava();
        obterFornecedorJava();
        obterNomeVM();
        obterVersaoVM();
        obterFornecedorVM();
        obterHomeJava();
        obterClassPath();
        obterTempoAtividadeJVM();
        obterNomeProcessoJVM();
        obterPidProcessoAtual();

        System.out.println("\n========== CPU ==========");
        obterNumeroProcessadoresDisponiveis();
        obterCargaMediaSistema();
        obterMetricasEstendidasSO();

        System.out.println("\n========== MEMÓRIA (HEAP JVM) ==========");
        obterTotalRAM();
        obterMaxRAM();
        obterFreeRAM();
        obterMemoriaEmUso();

        System.out.println("\n========== DISCO ==========");
        obterEspacoEmDisco();

        System.out.println("\n========== REDE ==========");
        obterNomeHostEIP();
        obterInterfacesRede();

        System.out.println("\n========== LOCALE / FUSO HORÁRIO ==========");
        obterLocaleEIdioma();
        obterFusoHorario();

        System.out.println("\n========== THREADS ==========");
        obterInformacoesThreads();

        System.out.println("\n========== TELA ==========");
        obterResolucaoTela();

        System.out.println("\n========== NAVEGADOR ==========");
        obterNavegadorWebPadrao();

        System.out.println("\n========== VARIÁVEIS DE AMBIENTE ==========");
        obterVariaveisAmbiente();
    }
}
