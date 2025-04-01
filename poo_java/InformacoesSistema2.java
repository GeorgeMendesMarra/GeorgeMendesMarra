public class InformacoesSistema2 {

    /**
     * Obtém e imprime o nome do sistema operacional.
     */
    public static void obterNomeSistemaOperacional() {
        String nomeSistema = System.getProperty("os.name");
        System.out.println("Nome do sistema operacional: " + nomeSistema);
    }

    /**
     * Obtém e imprime a versão do sistema operacional.
     */
    public static void obterVersaoSistemaOperacional() {
        String versaoSistema = System.getProperty("os.version");
        System.out.println("Versão do sistema operacional: " + versaoSistema);
    }

    /**
     * Obtém e imprime a arquitetura do sistema operacional.
     */
    public static void obterArquiteturaSistemaOperacional() {
        String arquiteturaSistema = System.getProperty("os.arch");
        System.out.println("Arquitetura do sistema operacional: " + arquiteturaSistema);
    }

    /**
     * Obtém e imprime o separador de arquivos do sistema operacional.
     */
    public static void obterSeparadorArquivos() {
        String separadorArquivos = System.getProperty("file.separator");
        System.out.println("Separador de arquivos: " + separadorArquivos);
    }

    /**
     * Obtém e imprime o separador de caminhos do sistema operacional.
     */
    public static void obterSeparadorCaminhos() {
        String separadorCaminhos = System.getProperty("path.separator");
        System.out.println("Separador de caminhos: " + separadorCaminhos);
    }

    /**
     * Obtém e imprime o diretório home do usuário.
     */
    public static void obterDiretorioHomeUsuario() {
        String diretorioHome = System.getProperty("user.home");
        System.out.println("Diretório home do usuário: " + diretorioHome);
    }

    /**
     * Obtém e imprime o nome do usuário.
     */
    public static void obterNomeUsuario() {
        String nomeUsuario = System.getProperty("user.name");
        System.out.println("Nome do usuário: " + nomeUsuario);
    }

    /**
     * Obtém e imprime o diretório de trabalho atual.
     */
    public static void obterDiretorioTrabalhoAtual() {
        String diretorioTrabalho = System.getProperty("user.dir");
        System.out.println("Diretório de trabalho atual: " + diretorioTrabalho);
    }

    /**
     * Obtém e imprime a versão do Java.
     */
    public static void obterVersaoJava() {
        String versaoJava = System.getProperty("java.version");
        System.out.println("Versão do Java: " + versaoJava);
    }

    /**
     * Obtém e imprime o nome do fornecedor do Java.
     */
    public static void obterFornecedorJava() {
        String fornecedorJava = System.getProperty("java.vendor");
        System.out.println("Fornecedor do Java: " + fornecedorJava);
    }

    /**
     * Obtém e imprime a quantidade total de RAM disponível (em bytes).
     */
    public static void obterTotalRAM() {
        long totalRAM = Runtime.getRuntime().totalMemory();
        System.out.println("Quantidade total de RAM (bytes): " + totalRAM);
    }

    /**
     * Obtém e imprime a quantidade máxima de RAM que a JVM tentará usar (em bytes).
     */
    public static void obterMaxRAM() {
        long maxRAM = Runtime.getRuntime().maxMemory();
        System.out.println("Quantidade máxima de RAM (bytes): " + maxRAM);
    }

    /**
     * Obtém e imprime a quantidade de RAM livre (em bytes).
     */
    public static void obterFreeRAM() {
        long freeRAM = Runtime.getRuntime().freeMemory();
        System.out.println("Quantidade de RAM livre (bytes): " + freeRAM);
    }

    /**
     * Obtém e imprime o nome do navegador web padrão (se disponível).
     */
    public static void obterNavegadorWebPadrao() {
        String navegadorWeb = System.getProperty("browser");
        if (navegadorWeb != null) {
            System.out.println("Navegador web padrão: " + navegadorWeb);
        } else {
            System.out.println("Navegador web padrão: não detectado");
        }
    }

    public static void main(String[] args) { //Sou o método principal. Observe que abaixo estão as invocações dos métodos declarados acima.
    
    //Você pode mudar a sequência das invocações dos métodos abaixo. Não precisa ser na mesma ordem em que foram declarados acima.
        obterVersaoJava();
        obterNomeSistemaOperacional();
        obterVersaoSistemaOperacional();
        obterArquiteturaSistemaOperacional();
        obterSeparadorArquivos();
        obterSeparadorCaminhos();
        obterDiretorioHomeUsuario();
        obterNomeUsuario();
        obterDiretorioTrabalhoAtual();
        obterFornecedorJava();
        obterTotalRAM();
        obterMaxRAM();
        obterFreeRAM();
        obterNavegadorWebPadrao();
    }
}
