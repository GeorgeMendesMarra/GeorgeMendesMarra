import java.net.*;
import java.io.*;
import java.util.Enumeration;
import java.util.concurrent.CountDownLatch;

/**
 * NetDemo.java
 *
 * Demonstração das principais classes do pacote java.net.
 *
 * Versão do Java: 21 (LTS).
 *
 * Paradigma utilizado: Orientado a Objetos (com um "esqueleto" imperativo
 * organizando a ordem das demonstrações).
 *
 * Diferente da classe Math (que é uma "utility class" só com métodos
 * estáticos e sem estado), as classes de java.net representam OBJETOS DE
 * VERDADE com estado e ciclo de vida: um Socket representa uma conexão
 * aberta, um ServerSocket "escuta" uma porta, uma URL/URI encapsula os
 * componentes de um endereço. Por isso este programa:
 *   - instancia objetos (new Socket(...), new ServerSocket(...) etc.);
 *   - usa try-with-resources para garantir o fechamento de recursos
 *     (sockets, streams) mesmo em caso de erro — um recurso central da
 *     Programação Orientada a Objetos aliado ao gerenciamento de recursos;
 *   - trata exceções verificadas (IOException, URISyntaxException), típicas
 *     de operações de I/O/rede;
 *   - usa uma Thread para simular um "servidor" rodando em paralelo a um
 *     "cliente", já que operações de rede são, por natureza, concorrentes.
 *
 * Observação: os exemplos de Socket/ServerSocket e DatagramSocket usam
 * "localhost" (loopback), então funcionam em qualquer máquina, mesmo sem
 * acesso à internet. Já o exemplo de HttpURLConnection depende de acesso
 * externo à rede; se a rede estiver indisponível, o programa captura a
 * exceção e informa isso, sem travar a execução.
 */
public class NetDemo {

    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DO PACOTE java.net ===\n");

        demonstrarURI();
        demonstrarURL();
        demonstrarURLEncoderDecoder();
        demonstrarInetAddress();
        demonstrarInetSocketAddress();
        demonstrarNetworkInterface();
        demonstrarHttpURLConnection();
        demonstrarSocketEServerSocket();
        demonstrarDatagramSocket();

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }

    /**
     * Demonstra a classe URI (Uniform Resource Identifier), que representa
     * um identificador de recurso e permite acessar suas partes (esquema,
     * autoridade, host, porta, caminho, query, fragmento) sem exigir que o
     * recurso exista ou esteja acessível.
     */
    static void demonstrarURI() {
        System.out.println("--- java.net.URI ---");
        try {
            // Construtor que recebe a URI como String e faz o parsing dos componentes.
            URI uri = new URI("https://usuario@www.exemplo.com:8443/caminho/pagina?busca=java&pagina=2#secao1");

            System.out.println("URI completa   = " + uri);
            System.out.println("getScheme()    = " + uri.getScheme());     // https
            System.out.println("getUserInfo()  = " + uri.getUserInfo());   // usuario
            System.out.println("getHost()      = " + uri.getHost());       // www.exemplo.com
            System.out.println("getPort()      = " + uri.getPort());       // 8443
            System.out.println("getPath()      = " + uri.getPath());       // /caminho/pagina
            System.out.println("getQuery()     = " + uri.getQuery());      // busca=java&pagina=2
            System.out.println("getFragment()  = " + uri.getFragment());   // secao1
            System.out.println("isAbsolute()   = " + uri.isAbsolute());    // true (tem esquema definido)

            // resolve(): combina uma URI relativa com esta URI base.
            URI relativa = uri.resolve("/outro/caminho");
            System.out.println("resolve(\"/outro/caminho\") = " + relativa);

        } catch (URISyntaxException e) {
            // Lançada quando a String não representa uma URI sintaticamente válida.
            System.out.println("Erro de sintaxe na URI: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Demonstra a classe URL (Uniform Resource Locator), que — diferente da
     * URI — sempre representa um recurso "localizável" na rede e possui
     * métodos utilitários para abrir conexões com ele.
     */
    static void demonstrarURL() {
        System.out.println("--- java.net.URL ---");
        try {
            // A partir do Java 20, o construtor URL(String) foi descontinuado
            // em favor de URI.create(String).toURL(), que valida a sintaxe via
            // URI antes de gerar a URL — por isso o padrão abaixo é o recomendado.
            URL url = URI.create("https://www.exemplo.com:8443/busca?termo=java#topo").toURL();

            System.out.println("URL completa    = " + url);
            System.out.println("getProtocol()   = " + url.getProtocol());  // https
            System.out.println("getHost()       = " + url.getHost());      // www.exemplo.com
            System.out.println("getPort()       = " + url.getPort());      // 8443 (porta explícita)
            System.out.println("getDefaultPort()= " + url.getDefaultPort()); // 443 (padrão do https)
            System.out.println("getFile()       = " + url.getFile());      // /busca?termo=java (caminho + query)
            System.out.println("getQuery()      = " + url.getQuery());     // termo=java
            System.out.println("getRef()        = " + url.getRef());       // topo (fragmento/âncora)

            // toURI(): converte a URL em um objeto URI (útil pois URI faz
            // validação de sintaxe mais rígida e é imutável/comparável).
            URI comoUri = url.toURI();
            System.out.println("toURI()         = " + comoUri);

        } catch (MalformedURLException e) {
            // Lançada quando a String não segue o formato de uma URL válida.
            System.out.println("URL mal formada: " + e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Erro ao converter para URI: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Demonstra URLEncoder e URLDecoder, usados para codificar/decodificar
     * texto no formato "application/x-www-form-urlencoded" (útil para
     * montar query strings com caracteres especiais, espaços, acentos etc.).
     */
    static void demonstrarURLEncoderDecoder() {
        System.out.println("--- java.net.URLEncoder / URLDecoder ---");

        String textoOriginal = "café & programação java";

        // URLEncoder.encode(texto, charset): substitui caracteres especiais
        // por sequências %XX (percent-encoding) e espaços por "+".
        String codificado = URLEncoder.encode(textoOriginal, java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("Texto original = " + textoOriginal);
        System.out.println("Codificado     = " + codificado);

        // URLDecoder.decode(texto, charset): faz o processo inverso do encode.
        String decodificado = URLDecoder.decode(codificado, java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("Decodificado   = " + decodificado);

        System.out.println();
    }

    /**
     * Demonstra a classe InetAddress, que representa um endereço IP
     * (IPv4 ou IPv6), com ou sem um nome de host associado.
     */
    static void demonstrarInetAddress() {
        System.out.println("--- java.net.InetAddress ---");
        try {
            // getLoopbackAddress(): retorna o endereço de loopback (127.0.0.1
            // em IPv4), sem depender de resolução DNS — sempre funciona.
            InetAddress loopback = InetAddress.getLoopbackAddress();
            System.out.println("getLoopbackAddress() = " + loopback);

            // getByName("localhost"): resolve o nome "localhost" (normalmente
            // não depende de um servidor DNS externo, é resolvido localmente).
            InetAddress local = InetAddress.getByName("localhost");
            System.out.println("getByName(\"localhost\") = " + local);
            System.out.println("  getHostAddress() = " + local.getHostAddress()); // só o IP
            System.out.println("  isLoopbackAddress() = " + local.isLoopbackAddress());

            // getByAddress(bytes): cria um InetAddress diretamente a partir dos
            // bytes brutos do IP, sem qualquer consulta de rede/DNS.
            byte[] bytesIp = {8, 8, 8, 8};
            InetAddress porBytes = InetAddress.getByAddress(bytesIp);
            System.out.println("getByAddress({8,8,8,8}) = " + porBytes);

        } catch (UnknownHostException e) {
            // Lançada quando o host não pode ser resolvido.
            System.out.println("Host desconhecido: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Demonstra a classe InetSocketAddress, que combina um endereço IP (ou
     * nome de host) com um número de porta — usada para configurar Sockets,
     * ServerSockets e conexões em geral.
     */
    static void demonstrarInetSocketAddress() {
        System.out.println("--- java.net.InetSocketAddress ---");

        // Endereço "resolvido": tenta descobrir o IP do host imediatamente.
        InetSocketAddress resolvido = new InetSocketAddress("localhost", 8080);
        System.out.println("Resolvido: " + resolvido);
        System.out.println("  getHostName() = " + resolvido.getHostName());
        System.out.println("  getPort()     = " + resolvido.getPort());
        System.out.println("  isUnresolved()= " + resolvido.isUnresolved());

        // createUnresolved(): cria o endereço SEM tentar resolver o host,
        // útil para representar um endereço que só será resolvido depois
        // (por exemplo, ao configurar um proxy).
        InetSocketAddress naoResolvido = InetSocketAddress.createUnresolved("servidor.interno", 9000);
        System.out.println("Não resolvido: " + naoResolvido);
        System.out.println("  isUnresolved()= " + naoResolvido.isUnresolved());

        System.out.println();
    }

    /**
     * Demonstra a classe NetworkInterface, que representa uma interface de
     * rede (física ou virtual) da máquina local, como "eth0", "lo" (loopback)
     * ou adaptadores Wi-Fi.
     */
    static void demonstrarNetworkInterface() {
        System.out.println("--- java.net.NetworkInterface ---");
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            int contador = 0;

            while (interfaces.hasMoreElements() && contador < 5) { // limita a saída
                NetworkInterface ni = interfaces.nextElement();
                System.out.println("Interface: " + ni.getName()
                        + " (" + ni.getDisplayName() + ")");
                System.out.println("  isLoopback()= " + ni.isLoopback());
                System.out.println("  isUp()      = " + ni.isUp());
                System.out.println("  MTU         = " + ni.getMTU());
                contador++;
            }
        } catch (SocketException e) {
            // Lançada em caso de erro ao consultar as interfaces do sistema.
            System.out.println("Erro ao listar interfaces: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Demonstra HttpURLConnection, usada para abrir uma conexão HTTP(S) a
     * partir de uma URL e consultar informações da resposta (código de
     * status, cabeçalhos etc.). Depende de acesso real à internet — se a
     * rede não estiver disponível, o erro é tratado e informado.
     */
    static void demonstrarHttpURLConnection() {
        System.out.println("--- java.net.HttpURLConnection ---");
        HttpURLConnection conexao = null;
        try {
            URL url = URI.create("https://api.github.com").toURL();

            // openConnection(): cria (mas ainda não abre de fato) a conexão.
            // O retorno é do tipo mais genérico URLConnection; fazemos o cast
            // para HttpURLConnection para acessar métodos específicos de HTTP.
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(5000); // 5 segundos para conectar
            conexao.setReadTimeout(5000);    // 5 segundos para ler a resposta

            int codigoStatus = conexao.getResponseCode(); // aqui a conexão é efetivamente feita
            System.out.println("URL requisitada  = " + url);
            System.out.println("Código de status = " + codigoStatus);
            System.out.println("Mensagem         = " + conexao.getResponseMessage());
            System.out.println("Content-Type     = " + conexao.getContentType());
            System.out.println("Content-Length    = " + conexao.getContentLengthLong());

        } catch (IOException e) {
            // Cobre tanto MalformedURLException quanto falhas de conexão/timeout.
            System.out.println("Não foi possível completar a requisição HTTP: " + e.getMessage());
        } finally {
            // disconnect(): libera os recursos associados à conexão.
            if (conexao != null) {
                conexao.disconnect();
            }
        }
        System.out.println();
    }

    /**
     * Demonstra Socket (cliente) e ServerSocket (servidor) em conjunto,
     * simulando uma comunicação TCP simples via loopback (localhost):
     * o "servidor" roda em uma Thread separada, aceita uma conexão, lê uma
     * mensagem e responde; o "cliente" (thread principal) conecta, envia
     * a mensagem e imprime a resposta recebida.
     */
    static void demonstrarSocketEServerSocket() {
        System.out.println("--- java.net.Socket / java.net.ServerSocket (TCP) ---");

        // CountDownLatch usado apenas para garantir que o servidor já esteja
        // escutando antes do cliente tentar se conectar.
        CountDownLatch servidorPronto = new CountDownLatch(1);

        Thread servidor = new Thread(() -> {
            // ServerSocket(0): porta 0 pede ao sistema operacional para
            // escolher automaticamente uma porta livre.
            try (ServerSocket serverSocket = new ServerSocket(0)) {
                System.out.println("[Servidor] Escutando na porta " + serverSocket.getLocalPort());

                // Guarda a porta escolhida em uma variável externa (via array,
                // truque comum para "escapar" de uma lambda em Java).
                portaEscolhida[0] = serverSocket.getLocalPort();
                servidorPronto.countDown(); // avisa que já pode aceitar conexões

                // accept(): bloqueia até um cliente se conectar.
                try (Socket clienteConectado = serverSocket.accept();
                     BufferedReader entrada = new BufferedReader(
                             new InputStreamReader(clienteConectado.getInputStream()));
                     PrintWriter saida = new PrintWriter(clienteConectado.getOutputStream(), true)) {

                    String mensagemRecebida = entrada.readLine();
                    System.out.println("[Servidor] Recebeu: " + mensagemRecebida);

                    saida.println("Eco do servidor: " + mensagemRecebida);
                }
            } catch (IOException e) {
                System.out.println("[Servidor] Erro: " + e.getMessage());
            }
        });

        servidor.start();

        try {
            servidorPronto.await(); // espera o servidor sinalizar que está pronto

            // Socket(host, porta): cliente conecta ao servidor via loopback.
            try (Socket socketCliente = new Socket("localhost", portaEscolhida[0]);
                 PrintWriter saida = new PrintWriter(socketCliente.getOutputStream(), true);
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(socketCliente.getInputStream()))) {

                saida.println("Olá, servidor!"); // envia mensagem
                String resposta = entrada.readLine(); // aguarda resposta
                System.out.println("[Cliente] Recebeu: " + resposta);
            }

            servidor.join(); // espera a thread do servidor terminar

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro na comunicação TCP: " + e.getMessage());
        }
        System.out.println();
    }

    // Array de 1 posição usado apenas para compartilhar a porta escolhida
    // pelo ServerSocket entre a thread do servidor e a thread principal.
    static final int[] portaEscolhida = new int[1];

    /**
     * Demonstra DatagramSocket e DatagramPacket, usados para comunicação
     * via UDP — um protocolo sem conexão, mais simples e rápido que o TCP,
     * mas sem garantias de entrega ou ordem.
     */
    static void demonstrarDatagramSocket() {
        System.out.println("--- java.net.DatagramSocket / java.net.DatagramPacket (UDP) ---");

        try (DatagramSocket socketServidor = new DatagramSocket(0); // porta livre escolhida pelo SO
             DatagramSocket socketCliente = new DatagramSocket()) {

            int portaServidor = socketServidor.getLocalPort();
            System.out.println("[Servidor UDP] Escutando na porta " + portaServidor);

            // Prepara e envia o pacote do cliente para o servidor.
            byte[] dadosEnvio = "Ping via UDP".getBytes();
            InetAddress enderecoLocal = InetAddress.getLoopbackAddress();
            DatagramPacket pacoteEnvio = new DatagramPacket(
                    dadosEnvio, dadosEnvio.length, enderecoLocal, portaServidor);
            socketCliente.send(pacoteEnvio);
            System.out.println("[Cliente UDP] Enviou: " + new String(dadosEnvio));

            // Servidor recebe o pacote.
            byte[] bufferRecebimento = new byte[256];
            DatagramPacket pacoteRecebido = new DatagramPacket(bufferRecebimento, bufferRecebimento.length);
            socketServidor.receive(pacoteRecebido);
            String mensagemRecebida = new String(
                    pacoteRecebido.getData(), 0, pacoteRecebido.getLength());
            System.out.println("[Servidor UDP] Recebeu: " + mensagemRecebida);

            // Servidor responde ao endereço/porta de onde o pacote veio
            // (informação disponível no próprio DatagramPacket recebido).
            byte[] dadosResposta = "Pong via UDP".getBytes();
            DatagramPacket pacoteResposta = new DatagramPacket(
                    dadosResposta, dadosResposta.length,
                    pacoteRecebido.getAddress(), pacoteRecebido.getPort());
            socketServidor.send(pacoteResposta);

            // Cliente recebe a resposta.
            byte[] bufferResposta = new byte[256];
            DatagramPacket pacoteFinal = new DatagramPacket(bufferResposta, bufferResposta.length);
            socketCliente.receive(pacoteFinal);
            String resposta = new String(pacoteFinal.getData(), 0, pacoteFinal.getLength());
            System.out.println("[Cliente UDP] Recebeu: " + resposta);

        } catch (IOException e) {
            System.out.println("Erro na comunicação UDP: " + e.getMessage());
        }
        System.out.println();
    }
}
