public class DetectarSistemaOperacional {

    public static void main(String[] args) {

        // Obtém o nome do sistema operacional usando a propriedade de sistema "os.name".
        String sistemaOperacional = System.getProperty("os.name");

        // Imprime o nome do sistema operacional na saída do console.
        System.out.println("Sistema Operacional do Usuário: " + sistemaOperacional);

        // Verifica qual sistema operacional está sendo usado através de comparações de strings.
        if (sistemaOperacional.toLowerCase().contains("win")) {
            // Se o nome do sistema operacional contiver "win" (case-insensitive), assume que é Windows.
            System.out.println("Executando em Windows.");
        } else if (sistemaOperacional.toLowerCase().contains("mac")) {
            // Se contiver "mac", assume que é macOS.
            System.out.println("Executando em macOS.");
        } else if (sistemaOperacional.toLowerCase().contains("nix") || sistemaOperacional.toLowerCase().contains("nux") || sistemaOperacional.toLowerCase().contains("aix")) {
            // Se contiver "nix", "nux" ou "aix", assume que é Unix ou Linux.
            // "nix" e "nux" são comuns em sistemas Unix-like, e "aix" é para IBM AIX.
            System.out.println("Executando em Unix ou Linux.");
        } else if (sistemaOperacional.toLowerCase().contains("sunos")) {
            // Se contiver "sunos", assume que é Solaris.
            System.out.println("Executando em Solaris.");
        } else {
            // Se nenhuma das condições anteriores for atendida, o sistema operacional não é identificado especificamente.
            System.out.println("Sistema Operacional não identificado especificamente.");
        }

        // As linhas abaixo obtêm e imprimem outras informações do sistema, se necessário.
        // Obtém a versão do sistema operacional usando a propriedade "os.version".
        String versaoSistema = System.getProperty("os.version");
        System.out.println("Versão do Sistema Operacional: " + versaoSistema);

        // Obtém a arquitetura do sistema operacional (por exemplo, "x86", "amd64") usando a propriedade "os.arch".
        String arquiteturaSistema = System.getProperty("os.arch");
        System.out.println("Arquitetura do Sistema Operacional: " + arquiteturaSistema);
    }
}
