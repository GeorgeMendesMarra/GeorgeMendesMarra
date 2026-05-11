/**
 * =================================================================================
 * PROGRAMA: CPUInfo - Informações Completas da CPU
 * =================================================================================
 * Este programa obtém e exibe todas as informações possíveis sobre a CPU do computador,
 * incluindo: número de núcleos, arquitetura, frequência, cache, memória RAM,
 * informações do sistema operacional e realiza um benchmark de performance.
 * 
 * Funciona em Windows, Linux e macOS.
 * 
 * @author
 * @version 2.0
 * =================================================================================
 */

// Importa classes necessárias para o funcionamento do programa
import java.io.BufferedReader;              // Para ler textos de forma eficiente
import java.io.File;                       // Para manipular arquivos (não usado diretamente)
import java.io.InputStreamReader;          // Para ler a saída de comandos do sistema
import java.lang.management.ManagementFactory;  // Para acessar informações de gerenciamento do sistema
import java.lang.management.OperatingSystemMXBean; // Interface para informações do SO
import java.lang.reflect.Method;           // Para usar reflexão (chamar métodos dinamicamente)
import java.util.Scanner;                  // Para ler entrada do usuário

/**
 * Classe principal que contém todo o programa
 */
public class CPUInfo {
    
    /**
     * Método principal - ponto de entrada do programa
     * Exibe o menu e gerencia as opções escolhidas pelo usuário
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler o que o usuário digitar no teclado
        Scanner scanner = new Scanner(System.in);
        
        // Exibe o cabeçalho do programa com bordas decorativas
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    INFORMAÇÕES DA CPU                           ║");
        System.out.println("║                   (Detecção Automática)                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        int opcao;  // Variável para armazenar a opção escolhida pelo usuário
        
        // Loop do-while: executa pelo menos uma vez e repete enquanto não escolher 0
        do {
            exibirMenu();  // Chama o método que mostra as opções do menu
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();   // Lê o número digitado
            scanner.nextLine();          // Limpa o buffer do teclado (remove a quebra de linha)
            
            // Estrutura switch: executa um bloco diferente para cada opção
            switch (opcao) {
                case 1:
                    informacoesBasicasJava();  // Opção 1: informações básicas
                    break;
                case 2:
                    informacoesAvancadasJava(); // Opção 2: informações avançadas
                    break;
                case 3:
                    informacoesSistemaOperacional(); // Opção 3: informações do SO
                    break;
                case 4:
                    informacoesCompletasSO();  // Opção 4: informações completas
                    break;
                case 5:
                    benchmarkCPU();            // Opção 5: teste de performance
                    break;
                case 0:
                    System.out.println("\nEncerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!"); // Qualquer número diferente de 0-5
            }
            
            // Se não for sair, pausa o programa e espera o usuário pressionar ENTER
            if (opcao != 0) {
                System.out.print("\nPressione ENTER para continuar...");
                scanner.nextLine();  // Aguarda o ENTER
                System.out.println(); // Pula uma linha
            }
            
        } while (opcao != 0);  // Continua enquanto o usuário não escolher a opção 0 (sair)
        
        scanner.close();  // Fecha o Scanner para liberar recursos da memória
    }
    
    /**
     * Exibe o menu principal com todas as opções disponíveis
     * Usa caracteres especiais para desenhar bordas (Unicode)
     */
    private static void exibirMenu() {
        System.out.println("┌─────────────────────────────────────────────────────────────────┐");
        System.out.println("│                         MENU PRINCIPAL                          │");
        System.out.println("├─────────────────────────────────────────────────────────────────┤");
        System.out.println("│ 1 - Informações Básicas da CPU (Java puro)                      │");
        System.out.println("│ 2 - Informações Avançadas (Arquitetura, Cache, Recursos)        │");
        System.out.println("│ 3 - Informações do Sistema Operacional (OS)                      │");
        System.out.println("│ 4 - Informações Completas (via comandos do sistema)             │");
        System.out.println("│ 5 - Benchmark Rápido da CPU                                     │");
        System.out.println("│ 0 - Sair                                                        │");
        System.out.println("└─────────────────────────────────────────────────────────────────┘");
    }
    
    // ==================== OPÇÃO 1: INFORMAÇÕES BÁSICAS (JAVA PURO) ====================
    
    /**
     * Obtém informações básicas usando APIs nativas do Java
     * Não depende de comandos externos do sistema operacional
     * 
     * Mostra: número de cores, arquitetura, versão do SO, carga da CPU,
     *         informações de memória RAM e informações da JVM
     */
    private static void informacoesBasicasJava() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                 INFORMAÇÕES BÁSICAS DA CPU                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        // Obtém o bean de gerenciamento do sistema operacional
        // Este objeto contém métodos para acessar informações do SO e da CPU
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        
        // Número de processadores disponíveis para a JVM (Java Virtual Machine)
        // Este número inclui hyper-threading (threads lógicas)
        int coresDisponiveis = Runtime.getRuntime().availableProcessors();
        
        // Exibe as informações básicas em formato de tabela
        System.out.println("\n📌 DADOS GERAIS:");
        System.out.println("   ┌─────────────────────────────────────────────────────────┐");
        System.out.printf("   │ %-25s │ %-30s │\n", "ATRIBUTO", "VALOR");
        System.out.println("   ├─────────────────────────────────────────────────────────┤");
        System.out.printf("   │ %-25s │ %-30d │\n", "Cores (lógicas/threads):", coresDisponiveis);
        System.out.printf("   │ %-25s │ %-30s │\n", "Arquitetura:", osBean.getArch());
        System.out.printf("   │ %-25s │ %-30s │\n", "Sistema Operacional:", osBean.getName());
        System.out.printf("   │ %-25s │ %-30s │\n", "Versão:", osBean.getVersion());
        System.out.printf("   │ %-25s │ %-30.2f │\n", "Carga do sistema (1 min):", osBean.getSystemLoadAverage());
        System.out.println("   └─────────────────────────────────────────────────────────┘");
        
        // ========== INFORMAÇÕES DE MEMÓRIA (via reflexão) ==========
        // A classe padrão OperatingSystemMXBean não tem métodos para memória
        // Precisamos usar reflexão para acessar a classe estendida com.sun.management.OperatingSystemMXBean
        
        try {
            // Tenta carregar a classe estendida (específica do Oracle/OpenJDK)
            Class<?> comOsBean = Class.forName("com.sun.management.OperatingSystemMXBean");
            
            // Verifica se o objeto osBean é uma instância dessa classe
            if (comOsBean.isInstance(osBean)) {
                // Obtém os métodos da classe via reflexão
                Method getTotalPhysicalMemorySize = comOsBean.getMethod("getTotalPhysicalMemorySize");
                Method getFreePhysicalMemorySize = comOsBean.getMethod("getFreePhysicalMemorySize");
                Method getTotalSwapSpaceSize = comOsBean.getMethod("getTotalSwapSpaceSize");
                Method getFreeSwapSpaceSize = comOsBean.getMethod("getFreeSwapSpaceSize");
                
                // Invoca os métodos (executa) e armazena os valores retornados
                long totalRam = (long) getTotalPhysicalMemorySize.invoke(osBean);
                long freeRam = (long) getFreePhysicalMemorySize.invoke(osBean);
                long totalSwap = (long) getTotalSwapSpaceSize.invoke(osBean);
                long freeSwap = (long) getFreeSwapSpaceSize.invoke(osBean);
                
                // Exibe as informações de memória formatadas
                System.out.println("\n📌 MEMÓRIA:");
                System.out.println("   ┌─────────────────────────────────────────────────────────┐");
                System.out.printf("   │ %-25s │ %-30s │\n", "Total RAM:", formatarBytes(totalRam));
                System.out.printf("   │ %-25s │ %-30s │\n", "RAM em uso:", formatarBytes(totalRam - freeRam));
                System.out.printf("   │ %-25s │ %-30s │\n", "RAM disponível:", formatarBytes(freeRam));
                System.out.printf("   │ %-25s │ %-30s │\n", "Total Swap:", formatarBytes(totalSwap));
                System.out.printf("   │ %-25s │ %-30s │\n", "Swap em uso:", formatarBytes(totalSwap - freeSwap));
                System.out.println("   └─────────────────────────────────────────────────────────┘");
            }
        } catch (Exception e) {
            // Se ocorrer erro (ex: JVM que não suporta reflexão), exibe uma mensagem amigável
            System.out.println("\n   ℹ️  Informações detalhadas de memória não disponíveis.");
        }
        
        // ========== INFORMAÇÕES DA JVM ==========
        System.out.println("\n📌 INFORMAÇÕES DA JVM:");
        System.out.println("   ┌─────────────────────────────────────────────────────────┐");
        System.out.printf("   │ %-25s │ %-30s │\n", "Versão Java:", System.getProperty("java.version"));
        System.out.printf("   │ %-25s │ %-30s │\n", "VM:", System.getProperty("java.vm.name"));
        System.out.printf("   │ %-25s │ %-30s │\n", "Fornecedor:", System.getProperty("java.vendor"));
        System.out.printf("   │ %-25s │ %-30s │\n", "Memória máxima JVM:", formatarBytes(Runtime.getRuntime().maxMemory()));
        System.out.printf("   │ %-25s │ %-30s │\n", "Memória total JVM:", formatarBytes(Runtime.getRuntime().totalMemory()));
        System.out.printf("   │ %-25s │ %-30s │\n", "Memória livre JVM:", formatarBytes(Runtime.getRuntime().freeMemory()));
        System.out.println("   └─────────────────────────────────────────────────────────┘");
    }
    
    // ==================== OPÇÃO 2: INFORMAÇÕES AVANÇADAS ====================
    
    /**
     * Tenta obter informações avançadas da CPU usando comandos específicos do sistema
     * 
     * Em Windows: usa o comando 'wmic' (Windows Management Instrumentation)
     * Em Linux/Mac: usa 'lscpu' e 'cat /proc/cpuinfo'
     * 
     * Mostra: nome do processador, frequência, cache L1/L2/L3, virtualização, flags
     */
    private static void informacoesAvancadasJava() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              INFORMAÇÕES AVANÇADAS DA CPU                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        // Obtém o nome do sistema operacional em minúsculo para facilitar a comparação
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("win")) {
            // ===== WINDOWS =====
            // Usa o comando wmic (Windows Management Instrumentation)
            // Para obter nome, número de núcleos, threads, velocidade do clock, cache
            System.out.println("\n📌 INFORMAÇÕES DA CPU (Windows):");
            executarComando("wmic cpu get Name,NumberOfCores,NumberOfLogicalProcessors,MaxClockSpeed,CurrentClockSpeed,L2CacheSize,L3CacheSize,Manufacturer,Family /format:list");
            
            System.out.println("\n📌 DETALHES TÉCNICOS (Windows):");
            executarComando("wmic cpu list full /format:list | findstr /i \"Name CurrentClock MaxClock L2 L3 Virtualization\"");
            
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // ===== LINUX / macOS =====
            // lscpu: comando que exibe informações detalhadas da CPU
            System.out.println("\n📌 ARQUITETURA DA CPU (Linux/Mac):");
            executarComando("lscpu | grep -E \"Model name|Architecture|CPU\\(s\\)|Thread|Core|Socket|L1|L2|L3|Virtualization|Vulnerability|Flags\"");
            
            // /proc/cpuinfo: arquivo virtual com informações de cada núcleo
            System.out.println("\n📌 DETALHES POR NÚCLEO:");
            executarComando("cat /proc/cpuinfo | grep -E \"processor|model name|cache size|cpu MHz|flags\" | head -30");
        } else {
            System.out.println("Sistema operacional não suportado para informações avançadas.");
        }
    }
    
    // ==================== OPÇÃO 3: INFORMAÇÕES DO SISTEMA OPERACIONAL ====================
    
    /**
     * Exibe informações detalhadas do sistema operacional
     * 
     * Mostra: nome do SO, versão, arquitetura, usuário logado,
     *         diretórios do sistema e informações complementares via comandos
     */
    private static void informacoesSistemaOperacional() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║               INFORMAÇÕES DO SISTEMA OPERACIONAL                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        String os = System.getProperty("os.name").toLowerCase();
        
        // Informações básicas obtidas via System.getProperty()
        System.out.println("\n📌 SISTEMA ATUAL:");
        System.out.println("   ┌─────────────────────────────────────────────────────────┐");
        System.out.printf("   │ %-20s │ %-35s │\n", "Sistema:", System.getProperty("os.name"));
        System.out.printf("   │ %-20s │ %-35s │\n", "Versão:", System.getProperty("os.version"));
        System.out.printf("   │ %-20s │ %-35s │\n", "Arquitetura:", System.getProperty("os.arch"));
        System.out.printf("   │ %-20s │ %-35s │\n", "Usuário:", System.getProperty("user.name"));
        System.out.printf("   │ %-20s │ %-35s │\n", "Diretório atual:", System.getProperty("user.dir"));
        System.out.printf("   │ %-20s │ %-35s │\n", "Home do usuário:", System.getProperty("user.home"));
        System.out.println("   └─────────────────────────────────────────────────────────┘");
        
        // Informações complementares via comandos do sistema
        if (os.contains("win")) {
            System.out.println("\n📌 COMANDOS DISPONÍVEIS (Windows):");
            executarComando("systeminfo | findstr /i \"OS Name OS Version System Type Processor\"");
            executarComando("wmic os get Caption,Version,OSArchitecture,TotalVisibleMemorySize,FreePhysicalMemory /format:list");
        } else {
            System.out.println("\n📌 COMANDOS DISPONÍVEIS (Linux/Mac):");
            executarComando("uname -a");
            executarComando("lsb_release -a 2>/dev/null || cat /etc/os-release | head -5");
        }
    }
    
    // ==================== OPÇÃO 4: INFORMAÇÕES COMPLETAS ====================
    
    /**
     * Exibe todas as informações possíveis do sistema via comandos nativos
     * 
     * Em Windows: systeminfo (informações completas do sistema)
     * Em Linux: /proc/cpuinfo, /proc/meminfo, uname -a
     */
    private static void informacoesCompletasSO() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              INFORMAÇÕES COMPLETAS DO SISTEMA                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("win")) {
            // Windows: systeminfo exibe TODAS as informações do sistema
            System.out.println("\n📌 INFORMAÇÕES COMPLETAS (Windows):");
            System.out.println("─────────────────────────────────────────────────────────────");
            executarComando("systeminfo");
            
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux/Mac: exibe informações da CPU
            System.out.println("\n📌 CPUINFO COMPLETO:");
            System.out.println("─────────────────────────────────────────────────────────────");
            executarComando("cat /proc/cpuinfo");
            
            // Exibe informações da memória
            System.out.println("\n📌 MEMINFO:");
            System.out.println("─────────────────────────────────────────────────────────────");
            executarComando("cat /proc/meminfo");
            
            // Exibe versão do kernel
            System.out.println("\n📌 VERSÃO DO KERNEL:");
            executarComando("uname -a");
        }
    }
    
    // ==================== OPÇÃO 5: BENCHMARK DA CPU ====================
    
    /**
     * Realiza um benchmark simples para testar a performance da CPU
     * 
     * Testes realizados:
     * 1. Ponto Flutuante (FPU): cálculos com seno, cosseno e raiz quadrada
     * 2. Inteiros (ALU): multiplicações e divisões com números inteiros
     * 3. Números Primos (Branches): teste de branch prediction e loops
     * 
     * Mede o tempo de execução de cada teste e calcula operações por segundo
     */
    private static void benchmarkCPU() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    BENCHMARK DA CPU                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n🔬 Executando testes de performance...\n");
        
        // Obtém o número de núcleos disponíveis (para informação)
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("   Núcleos disponíveis: " + cores);
        System.out.println("   Executando testes em paralelo...\n");
        
        // ========== TESTE 1: PONTO FLUTUANTE (FPU) ==========
        // Este teste avalia a capacidade da Unidade de Ponto Flutuante
        // Usa operações matemáticas com números decimais (sin, cos, sqrt)
        long inicioFPU = System.nanoTime();  // Marca o tempo inicial (nanosegundos)
        double resultadoFPU = 0;
        
        // Loop de 50 milhões de iterações com operações matemáticas pesadas
        for (int i = 0; i < 50_000_000; i++) {
            resultadoFPU += Math.sin(i) * Math.cos(i) + Math.sqrt(i);
        }
        long tempoFPU = System.nanoTime() - inicioFPU;  // Calcula o tempo gasto
        
        // ========== TESTE 2: INTEIROS (ALU) ==========
        // Este teste avalia a Unidade Lógica e Aritmética (ALU)
        // Usa operações com números inteiros
        long inicioALU = System.nanoTime();
        long resultadoALU = 0;
        
        // Loop de 100 milhões de iterações com operações inteiras
        for (int i = 0; i < 100_000_000; i++) {
            resultadoALU += i * i + i / (i + 1);
        }
        long tempoALU = System.nanoTime() - inicioALU;
        
        // ========== TESTE 3: NÚMEROS PRIMOS ==========
        // Este teste avalia branch prediction (previsão de desvios)
        // Encontrar números primos envolve muitos desvios condicionais (if)
        long inicioPrimos = System.nanoTime();
        int contagemPrimos = 0;
        
        // Verifica quais números entre 2 e 500.000 são primos
        for (int i = 2; i < 500_000; i++) {
            boolean primo = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    primo = false;
                    break;  // Sai do loop se encontrar um divisor
                }
            }
            if (primo) contagemPrimos++;
        }
        long tempoPrimos = System.nanoTime() - inicioPrimos;
        
        // ========== EXIBIÇÃO DOS RESULTADOS ==========
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         RESULTADOS                              ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-35s │ %15s ║\n", "TESTE", "TEMPO (ms)");
        System.out.println("╠────────────────────────────────────────────────────────────────╣");
        System.out.printf("║ %-35s │ %15d ║\n", "Ponto Flutuante (50M ops)", tempoFPU / 1_000_000);
        System.out.printf("║ %-35s │ %15d ║\n", "Inteiros (100M ops)", tempoALU / 1_000_000);
        System.out.printf("║ %-35s │ %15d ║\n", "Números Primos (até 500k)", tempoPrimos / 1_000_000);
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-35s │ %15d ║\n", "TEMPO TOTAL", (tempoFPU + tempoALU + tempoPrimos) / 1_000_000);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        // ========== CÁLCULOS DE PERFORMANCE ==========
        // Calcula quantas operações por segundo cada teste conseguiu realizar
        System.out.println("\n📊 Resultados complementares:");
        System.out.printf("   • Operações FPU por segundo: %.2f milhões\n", 
            (50_000_000.0 / (tempoFPU / 1_000_000_000.0)) / 1_000_000);
        System.out.printf("   • Operações ALU por segundo: %.2f milhões\n", 
            (100_000_000.0 / (tempoALU / 1_000_000_000.0)) / 1_000_000);
        System.out.printf("   • Primos encontrados: %d\n", contagemPrimos);
    }
    
    // ==================== FUNÇÕES AUXILIARES ====================
    
    /**
     * Executa um comando no sistema operacional e exibe sua saída no console
     * 
     * Funciona tanto para Windows (cmd.exe) quanto para Linux/Mac (bash)
     * 
     * @param comando O comando a ser executado (ex: "wmic cpu", "lscpu", "cat /proc/cpuinfo")
     */
    private static void executarComando(String comando) {
        try {
            // Detecta o sistema operacional para usar o shell correto
            ProcessBuilder processBuilder;
            String os = System.getProperty("os.name").toLowerCase();
            
            if (os.contains("win")) {
                // Windows: usa cmd.exe /c (executa e fecha)
                processBuilder = new ProcessBuilder("cmd.exe", "/c", comando);
            } else {
                // Linux/Mac: usa bash -c (executa comando no shell)
                processBuilder = new ProcessBuilder("bash", "-c", comando);
            }
            
            // Redireciona a saída de erro para a saída padrão (para ver tudo junto)
            processBuilder.redirectErrorStream(true);
            
            // Inicia o processo (executa o comando)
            Process processo = processBuilder.start();
            
            // Cria um leitor para ler a saída do comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            int contador = 0;
            int limite = 200; // Limite de linhas para não sobrecarregar o console
            
            // Lê cada linha da saída e exibe na tela
            while ((linha = reader.readLine()) != null && contador < limite) {
                System.out.println(linha);
                contador++;
            }
            
            // Aguarda o processo terminar
            processo.waitFor();
            
            // Se a saída foi truncada, avisa o usuário
            if (contador >= limite) {
                System.out.println("... (saída truncada, muitas informações) ...");
            }
            
        } catch (Exception e) {
            // Em caso de erro (comando não encontrado, permissão negada, etc.)
            System.out.println("Erro ao executar comando: " + e.getMessage());
        }
    }
    
    /**
     * Formata um valor em bytes para uma string legível (KB, MB, GB)
     * 
     * Exemplos:
     * - 500 bytes → "500 B"
     * - 2048 bytes → "2.00 KB"
     * - 3_145_728 bytes → "3.00 MB"
     * - 2_147_483_648 bytes → "2.00 GB"
     * 
     * @param bytes Valor em bytes a ser formatado
     * @return String formatada com a unidade apropriada
     */
    private static String formatarBytes(long bytes) {
        // Menos de 1 KB (1024 bytes)
        if (bytes < 1024) {
            return bytes + " B";
        } 
        // Entre 1 KB e 1 MB
        else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } 
        // Entre 1 MB e 1 GB
        else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024));
        } 
        // Acima de 1 GB
        else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
        }
    }
}
