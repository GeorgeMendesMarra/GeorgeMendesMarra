public class InformacoesSistema {

    public static void main(String[] args) {

        // Obtém o nome do sistema operacional.
        String nomeSistema = System.getProperty("os.name");
        System.out.println("Nome do sistema operacional: " + nomeSistema);

        // Obtém a versão do sistema operacional.
        String versaoSistema = System.getProperty("os.version");
        System.out.println("Versão do sistema operacional: " + versaoSistema);

        // Obtém a arquitetura do sistema operacional.
        String arquiteturaSistema = System.getProperty("os.arch");
        System.out.println("Arquitetura do sistema operacional: " + arquiteturaSistema);

        // Obtém o separador de arquivos do sistema operacional.
        String separadorArquivos = System.getProperty("file.separator");
        System.out.println("Separador de arquivos: " + separadorArquivos);

        // Obtém o separador de caminhos do sistema operacional.
        String separadorCaminhos = System.getProperty("path.separator");
        System.out.println("Separador de caminhos: " + separadorCaminhos);

        // Obtém o diretório home do usuário.
        String diretorioHome = System.getProperty("user.home");
        System.out.println("Diretório home do usuário: " + diretorioHome);

        // Obtém o nome do usuário.
        String nomeUsuario = System.getProperty("user.name");
        System.out.println("Nome do usuário: " + nomeUsuario);

        // Obtém o diretório de trabalho atual.
        String diretorioTrabalho = System.getProperty("user.dir");
        System.out.println("Diretório de trabalho atual: " + diretorioTrabalho);

        // Obtém a versão do Java.
        String versaoJava = System.getProperty("java.version");
        System.out.println("Versão do Java: " + versaoJava);

        // Obtém o nome do fornecedor do Java.
        String fornecedorJava = System.getProperty("java.vendor");
        System.out.println("Fornecedor do Java: " + fornecedorJava);

        // Obtém a quantidade total de RAM disponível (em bytes).
        long totalRAM = Runtime.getRuntime().totalMemory();
        System.out.println("Quantidade total de RAM (bytes): " + totalRAM);

        // Obtém a quantidade máxima de RAM que a JVM tentará usar (em bytes).
        long maxRAM = Runtime.getRuntime().maxMemory();
        System.out.println("Quantidade máxima de RAM (bytes): " + maxRAM);

        // Obtém a quantidade de RAM livre (em bytes).
        long freeRAM = Runtime.getRuntime().freeMemory();
        System.out.println("Quantidade de RAM livre (bytes): " + freeRAM);

        // Obtém o nome do navegador web padrão (se disponível).
        String navegadorWeb = System.getProperty("browser");
        if (navegadorWeb != null) {
            System.out.println("Navegador web padrão: " + navegadorWeb);
        } else {
            System.out.println("Navegador web padrão: não detectado");
        }
    }
}
