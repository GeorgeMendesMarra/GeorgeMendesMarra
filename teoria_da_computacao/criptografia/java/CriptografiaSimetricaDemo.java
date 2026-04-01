import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

/**
 * Demonstração de Algoritmos de Criptografia Simétrica
 * 
 * Conceitos de Teoria da Computação abordados:
 * - Algoritmos de chave simétrica: mesma chave para cifrar e decifrar
 * - Complexidade computacional: segurança baseada em tempo exponencial (2^n)
 * - Classes de complexidade: P (polinomial) vs NP (não determinístico)
 * - Funções unidirecionais: fáceis de calcular, difíceis de inverter
 * - Modos de operação: como a teoria de autômatos se aplica ao encadeamento de blocos
 * 
 * @author Material didático para Teoria da Computação
 */
public class CriptografiaSimetricaDemo {
    
    // Constantes que definem os algoritmos, modos e preenchimentos disponíveis
    // Estes parâmetros determinam a transformação criptográfica a ser aplicada
    private static final String[] ALGORITMOS = {"AES", "DES", "Blowfish"};
    private static final String[] MODOS = {"ECB", "CBC"};
    private static final String[] PREENCHIMENTOS = {"PKCS5Padding", "NoPadding"};
    
    /**
     * Método principal - ponto de entrada do programa
     * Implementa um loop interativo com menu para facilitar o uso pelos alunos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        // Cabeçalho explicativo sobre os conceitos teóricos
        System.out.println("=== DEMONSTRAÇÃO DE CRIPTOGRAFIA SIMÉTRICA ===");
        System.out.println("Conceitos de Teoria da Computação: ");
        System.out.println("- Algoritmos de chave simétrica (mesma chave para cifrar/decifrar)");
        System.out.println("- Complexidade computacional e segurança baseada em chave");
        System.out.println("- Modos de operação e padding");
        System.out.println("- Relação com blockchain: hashes, integridade e autenticidade\n");
        
        // Loop principal do menu - executa até o usuário escolher sair (opção 0)
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente
            
            // Estrutura de decisão para cada opção do menu
            switch (opcao) {
                case 1:
                    cifrarMensagem(scanner);
                    break;
                case 2:
                    decifrarMensagem(scanner);
                    break;
                case 3:
                    compararAlgoritmos();
                    break;
                case 4:
                    explicarConceitos();
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
    
    /**
     * Exibe as opções do menu principal
     */
    private static void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Cifrar mensagem");
        System.out.println("2. Decifrar mensagem");
        System.out.println("3. Comparar desempenho dos algoritmos");
        System.out.println("4. Explicar conceitos teóricos");
        System.out.println("0. Sair");
    }
    
    /**
     * Cifra uma mensagem usando o algoritmo, modo e padding escolhidos pelo usuário
     * 
     * Conceitos teóricos demonstrados:
     * - Cifração é uma função determinística que mapeia texto claro para texto cifrado
     * - A segurança depende da chave (princípio de Kerckhoffs)
     * - Diferentes modos de operação afetam a segurança e o desempenho
     * 
     * @param scanner Scanner para leitura das entradas do usuário
     */
    private static void cifrarMensagem(Scanner scanner) {
        try {
            // Entrada da mensagem a ser cifrada
            System.out.print("\nDigite a mensagem para cifrar: ");
            String mensagem = scanner.nextLine();
            
            // Seleção do algoritmo de cifração
            System.out.println("Escolha o algoritmo:");
            for (int i = 0; i < ALGORITMOS.length; i++) {
                System.out.println((i+1) + ". " + ALGORITMOS[i] + 
                    (ALGORITMOS[i].equals("AES") ? " (recomendado)" : 
                     ALGORITMOS[i].equals("DES") ? " (inseguro - chave muito curta)" : " (alternativo)"));
            }
            int algOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            
            // Seleção do modo de operação
            // Modos: ECB (Electronic Codebook) - inseguro para padrões
            //        CBC (Cipher Block Chaining) - mais seguro, encadeia blocos
            System.out.println("Escolha o modo de operação:");
            System.out.println("  ECB: Cifra blocos independentes (NÃO RECOMENDADO para dados com padrão)");
            System.out.println("  CBC: Encadeia blocos (mais seguro, utiliza vetor de inicialização)");
            for (int i = 0; i < MODOS.length; i++) {
                System.out.println((i+1) + ". " + MODOS[i]);
            }
            int modoOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            
            // Seleção do preenchimento (padding)
            // PKCS5Padding: adiciona bytes com o valor do número de bytes adicionados
            // NoPadding: exige que o texto tenha tamanho múltiplo do bloco
            System.out.println("Escolha o padding:");
            System.out.println("  PKCS5Padding: Preenchimento padrão (recomendado)");
            System.out.println("  NoPadding: Sem preenchimento (texto deve ter tamanho múltiplo do bloco)");
            for (int i = 0; i < PREENCHIMENTOS.length; i++) {
                System.out.println((i+1) + ". " + PREENCHIMENTOS[i]);
            }
            int padOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            
            // Constrói a string de transformação no formato "Algoritmo/Modo/Padding"
            // Esta string é usada pelo Java Cryptography Extension (JCE)
            String transformacao = ALGORITMOS[algOpcao] + "/" + MODOS[modoOpcao] + "/" + PREENCHIMENTOS[padOpcao];
            
            // Gera uma chave aleatória apropriada para o algoritmo escolhido
            // O tamanho da chave determina a complexidade do ataque de força bruta: 2^(tamanho)
            Key chave = gerarChave(ALGORITMOS[algOpcao]);
            
            // Configura o cifrador para o modo de ENCRYPT
            Cipher cipher = Cipher.getInstance(transformacao);
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            
            // Executa a cifração: converte a mensagem para bytes e aplica o algoritmo
            byte[] textoCifrado = cipher.doFinal(mensagem.getBytes());
            
            // Codifica o resultado em Base64 para facilitar visualização e armazenamento
            // Base64 é um esquema de codificação que representa dados binários em texto
            String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);
            
            // Exibe os resultados
            System.out.println("\n=== RESULTADO ===");
            System.out.println("Transformação utilizada: " + transformacao);
            System.out.println("Tamanho da chave: " + (chave.getEncoded().length * 8) + " bits");
            System.out.println("Chave (Base64): " + Base64.getEncoder().encodeToString(chave.getEncoded()));
            System.out.println("Mensagem cifrada (Base64): " + textoCifradoBase64);
            
            // Dica para uso didático: guardar chave e texto cifrado para exercício de decifração
            System.out.println("\n[Dica] Guarde a chave e o texto cifrado para decifrar depois!");
            System.out.println("[Conceito] A mesma chave é usada para cifrar e decifrar - isto é criptografia simétrica!");
            
        } catch (Exception e) {
            System.out.println("Erro ao cifrar: " + e.getMessage());
            System.out.println("Verifique se o algoritmo escolhido suporta o modo e padding selecionados.");
        }
    }
    
    /**
     * Decifra uma mensagem previamente cifrada
     * 
     * Conceitos teóricos demonstrados:
     * - Decifração é o processo inverso da cifração
     * - A mesma chave utilizada na cifração deve ser usada na decifração
     * - A segurança exige que a chave seja mantida em segredo
     * 
     * @param scanner Scanner para leitura das entradas do usuário
     */
    private static void decifrarMensagem(Scanner scanner) {
        try {
            // Entrada do texto cifrado em Base64
            System.out.print("\nDigite o texto cifrado (Base64): ");
            String textoCifradoBase64 = scanner.nextLine();
            
            // Entrada da chave utilizada na cifração (também em Base64)
            System.out.print("Digite a chave (Base64): ");
            String chaveBase64 = scanner.nextLine();
            
            // Seleção do algoritmo - deve ser o mesmo usado na cifração
            System.out.println("Escolha o algoritmo (mesmo usado na cifração):");
            for (int i = 0; i < ALGORITMOS.length; i++) {
                System.out.println((i+1) + ". " + ALGORITMOS[i]);
            }
            int algOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            
            // Seleção do modo de operação - deve ser o mesmo usado na cifração
            System.out.println("Escolha o modo de operação (mesmo usado na cifração):");
            for (int i = 0; i < MODOS.length; i++) {
                System.out.println((i+1) + ". " + MODOS[i]);
            }
            int modoOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            
            // Seleção do padding - deve ser o mesmo usado na cifração
            System.out.println("Escolha o padding (mesmo usado na cifração):");
            for (int i = 0; i < PREENCHIMENTOS.length; i++) {
                System.out.println((i+1) + ". " + PREENCHIMENTOS[i]);
            }
            int padOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            
            // Reconstrói a transformação a partir das escolhas do usuário
            String transformacao = ALGORITMOS[algOpcao] + "/" + MODOS[modoOpcao] + "/" + PREENCHIMENTOS[padOpcao];
            
            // Reconstrói a chave a partir da string Base64 fornecida
            byte[] chaveBytes = Base64.getDecoder().decode(chaveBase64);
            Key chave = new SecretKeySpec(chaveBytes, ALGORITMOS[algOpcao]);
            
            // Decodifica o texto cifrado de Base64 para bytes
            byte[] textoCifrado = Base64.getDecoder().decode(textoCifradoBase64);
            
            // Configura o cifrador para o modo de DECRYPT
            Cipher cipher = Cipher.getInstance(transformacao);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            
            // Executa a decifração
            byte[] textoDecifrado = cipher.doFinal(textoCifrado);
            String mensagemOriginal = new String(textoDecifrado);
            
            // Exibe o resultado
            System.out.println("\n=== RESULTADO ===");
            System.out.println("Mensagem decifrada: " + mensagemOriginal);
            System.out.println("[Conceito] A decifração reverte o processo de cifração usando a mesma chave.");
            
        } catch (Exception e) {
            System.out.println("Erro ao decifrar: " + e.getMessage());
            System.out.println("Verifique se a chave, o texto cifrado e os parâmetros estão corretos.");
            System.out.println("Dica: Use os valores gerados na opção de cifração.");
        }
    }
    
    /**
     * Gera uma chave aleatória para o algoritmo especificado
     * 
     * Conceitos teóricos:
     * - O tamanho da chave define o espaço de busca para ataques de força bruta
     * - Chave de n bits → 2^n possibilidades
     * - AES-128: 2^128 possibilidades (inviável com computadores atuais)
     * - DES-56: 2^56 possibilidades (quebrado em 1999)
     * 
     * @param algoritmo Nome do algoritmo (AES, DES, Blowfish)
     * @return Key objeto contendo a chave gerada
     * @throws Exception se o algoritmo não for suportado
     */
    private static Key gerarChave(String algoritmo) throws Exception {
        // Define o tamanho da chave em bits conforme o algoritmo
        // O tamanho da chave impacta diretamente a complexidade computacional do ataque
        int tamanhoChave;
        switch (algoritmo) {
            case "AES":
                tamanhoChave = 128; // AES também suporta 192 e 256 bits
                break;
            case "DES":
                tamanhoChave = 56;  // DES tem chave de 56 bits efetivos (8 bytes com 1 bit de paridade cada)
                break;
            case "Blowfish":
                tamanhoChave = 128; // Blowfish suporta de 32 a 448 bits
                break;
            default:
                tamanhoChave = 128;
        }
        
        // KeyGenerator é uma fábrica de chaves para algoritmos simétricos
        KeyGenerator keyGen = KeyGenerator.getInstance(algoritmo);
        keyGen.init(tamanhoChave); // Inicializa com o tamanho desejado
        return keyGen.generateKey(); // Gera uma chave aleatória
    }
    
    /**
     * Compara o desempenho dos diferentes algoritmos de criptografia
     * 
     * Conceitos teóricos demonstrados:
     * - Análise de desempenho como parte da complexidade prática
     * - Relação entre tamanho da chave e segurança
     * - Importância do modo de operação (ECB vs CBC)
     * 
     * Esta função é útil para discutir trade-offs entre segurança e desempenho
     */
    private static void compararAlgoritmos() {
        System.out.println("\n=== COMPARAÇÃO DE DESEMPENHO ===\n");
        
        // Mensagem de teste com tamanho razoável para medir desempenho
        String mensagemTeste = "Este é um texto de teste para avaliar o desempenho dos algoritmos de criptografia simétrica. " +
                               "A Teoria da Computação nos ensina que a segurança está na chave, não no algoritmo! " +
                               "Este é um princípio fundamental formulado por Kerckhoffs no século XIX.";
        
        System.out.println("Tamanho da mensagem de teste: " + mensagemTeste.length() + " caracteres\n");
        
        // Testa cada algoritmo disponível
        for (String algoritmo : ALGORITMOS) {
            try {
                // Usa modo ECB e PKCS5Padding para padronizar a comparação
                // Nota: ECB não é seguro, mas é útil para comparação didática
                String transformacao = algoritmo + "/ECB/PKCS5Padding";
                Cipher cipher = Cipher.getInstance(transformacao);
                Key chave = gerarChave(algoritmo);
                
                // Medição do tempo de cifragem usando nanossegundos para maior precisão
                long inicio = System.nanoTime();
                cipher.init(Cipher.ENCRYPT_MODE, chave);
                byte[] cifrado = cipher.doFinal(mensagemTeste.getBytes());
                long tempoCifragem = System.nanoTime() - inicio;
                
                // Medição do tempo de decifragem
                inicio = System.nanoTime();
                cipher.init(Cipher.DECRYPT_MODE, chave);
                byte[] decifrado = cipher.doFinal(cifrado);
                long tempoDecifragem = System.nanoTime() - inicio;
                
                // Verifica se a mensagem foi recuperada corretamente
                boolean mensagemOk = mensagemTeste.equals(new String(decifrado));
                
                // Exibe os resultados da comparação
                System.out.println("Algoritmo: " + algoritmo);
                System.out.println("  Tamanho da chave: " + (chave.getEncoded().length * 8) + " bits");
                System.out.println("  Tamanho do texto cifrado: " + cifrado.length + " bytes");
                System.out.println("  Tempo de cifragem: " + tempoCifragem / 1000000.0 + " ms");
                System.out.println("  Tempo de decifragem: " + tempoDecifragem / 1000000.0 + " ms");
                System.out.println("  Mensagem recuperada: " + (mensagemOk ? "OK ✓" : "FALHA ✗"));
                System.out.println();
                
            } catch (Exception e) {
                System.out.println("Erro ao testar " + algoritmo + ": " + e.getMessage());
            }
        }
        
        // Observações importantes sobre os algoritmos testados
        System.out.println("--- Observações importantes ---");
        System.out.println("1. AES (Advanced Encryption Standard): padrão atual do NIST, recomendado para aplicações modernas");
        System.out.println("2. DES (Data Encryption Standard): considerado inseguro desde 1999 devido ao tamanho pequeno da chave (56 bits)");
        System.out.println("3. Blowfish: algoritmo rápido e flexível, mas menos utilizado comercialmente que AES");
        System.out.println("4. Modo ECB (Electronic Codebook): NÃO É SEGURO para dados com padrões repetitivos!");
        System.out.println("   - Para demonstração: cifre uma imagem BMP com ECB e veja o padrão da imagem original ainda visível");
        System.out.println("5. O modo CBC (Cipher Block Chaining) é mais seguro por encadear os blocos de cifração");
        
        // Contextualização com Blockchain
        System.out.println("\n--- Relação com Blockchain e Criptomoedas ---");
        System.out.println("- Blockchain utiliza criptografia assimétrica (chave pública/privada) para transações");
        System.out.println("- Funções hash (SHA-256) são usadas para garantir integridade dos blocos");
        System.out.println("- Criptografia simétrica é utilizada em carteiras e na proteção de chaves privadas");
        System.out.println("- A prova de trabalho (PoW) depende de encontrar hashes com determinada dificuldade");
    }
    
    /**
     * Explica os conceitos teóricos de Teoria da Computação aplicados à criptografia
     * 
     * Esta função serve como material didático para os alunos,
     * conectando os conceitos práticos do programa com a fundamentação teórica.
     */
    private static void explicarConceitos() {
        System.out.println("\n=== CONCEITOS DE TEORIA DA COMPUTAÇÃO EM CRIPTOGRAFIA ===\n");
        
        System.out.println("1. ALGORITMOS DE CHAVE SIMÉTRICA:");
        System.out.println("   - Definição: Usam a MESMA chave para cifrar e decifrar (como uma chave de casa)");
        System.out.println("   - Exemplos: AES, DES, Blowfish, ChaCha20, 3DES");
        System.out.println("   - Vantagem: eficiente computacionalmente, adequado para grandes volumes de dados");
        System.out.println("   - Desvantagem: problema da distribuição segura da chave (canal seguro necessário)");
        System.out.println("   - Princípio de Kerckhoffs: 'O sistema deve ser seguro mesmo que o algoritmo seja público'");
        System.out.println("   - A segurança reside APENAS na chave secreta\n");
        
        System.out.println("2. COMPLEXIDADE COMPUTACIONAL E CLASSES DE PROBLEMAS:");
        System.out.println("   - A segurança baseia-se na dificuldade de ataques de força bruta (busca exaustiva)");
        System.out.println("   - Chave de n bits → 2ⁿ possibilidades (complexidade exponencial O(2ⁿ))");
        System.out.println("   - Problemas de busca em espaço exponencial estão na classe de complexidade EXP");
        System.out.println("   - AES-128: 2¹²⁸ possibilidades → inviável com tecnologia atual");
        System.out.println("   - DES-56: 2⁵⁶ possibilidades → quebrado em 1999 em 22 horas com hardware dedicado");
        System.out.println("   - Relação com P vs NP: fatoração de números grandes (RSA) está em NP, mas não se sabe se está em P\n");
        
        System.out.println("3. MODOS DE OPERAÇÃO (Teoria de Autômatos e Encadeamento):");
        System.out.println("   - ECB (Electronic Codebook): cifra blocos independentes");
        System.out.println("     * Problema: blocos iguais geram cifras iguais → revela padrões");
        System.out.println("     * Exemplo didático: cifrar uma imagem BMP com ECB mantém o contorno visível");
        System.out.println("   - CBC (Cipher Block Chaining): encadeia blocos usando XOR com bloco anterior");
        System.out.println("     * Funciona como um autômato com estado (o bloco cifrado anterior)");
        System.out.println("     * Requer vetor de inicialização (IV) para garantir unicidade");
        System.out.println("   - CTR (Counter): transforma cifra de blocos em cifra de fluxo");
        System.out.println("     * Permite paralelização e acesso aleatório");
        System.out.println("   - GCM (Galois/Counter Mode): fornece autenticação além da confidencialidade\n");
        
        System.out.println("4. RELAÇÃO COM BLOCKCHAIN E CRIPTOMOEDAS:");
        System.out.println("   - Blockchain usa criptografia assimétrica (curvas elípticas) para transações");
        System.out.println("   - Funções hash (SHA-256) são funções unidirecionais: fáceis de calcular, difíceis de inverter");
        System.out.println("   - Criptografia simétrica protege carteiras e chaves privadas armazenadas");
        System.out.println("   - A prova de trabalho (PoW) exige encontrar hash com N zeros à esquerda");
        System.out.println("   - Complexidade do PoW: ajuste dinâmico baseado no poder computacional da rede\n");
        
        System.out.println("5. DESAFIOS DA COMPUTAÇÃO QUÂNTICA:");
        System.out.println("   - Algoritmo de Grover: reduz busca de 2ⁿ para 2ⁿ/² (aceleração quadrática)");
        System.out.println("   - Impacto: AES-128 teria segurança efetiva equivalente a 64 bits contra quantum");
        System.out.println("   - Solução para criptografia simétrica: aumentar tamanho da chave (AES-256)");
        System.out.println("   - Algoritmo de Shor: quebra RSA e ECC em tempo polinomial");
        System.out.println("     * Fatoração de inteiros (RSA) se torna facilmente solucionável");
        System.out.println("     * Isto inviabiliza a criptografia assimétrica atual");
        System.out.println("   - Criptografia pós-quântica: novos algoritmos baseados em:");
        System.out.println("     * Criptografia baseada em reticulados (Lattice-based)");
        System.out.println("     * Códigos corretores de erro (Code-based)");
        System.out.println("     * Funções hash (Hash-based signatures)");
        System.out.println("     * Curvas isógenas (Isogeny-based)\n");
        
        System.out.println("6. CONCEITO DE FUNÇÕES UNIDIRECIONAIS:");
        System.out.println("   - Definição: função f que é fácil de computar, mas difícil de inverter");
        System.out.println("   - Exemplos: multiplicação de primos (fácil) vs fatoração (difícil)");
        System.out.println("   - Funções hash criptográficas são projetadas para serem unidirecionais");
        System.out.println("   - São fundamentais para: autenticação, integridade, assinaturas digitais\n");
        
        System.out.println("=== PERGUNTAS PARA DISCUSSÃO EM SALA ===");
        System.out.println("1. Por que o tamanho da chave é mais importante que a complexidade do algoritmo em si?");
        System.out.println("2. Como a Lei de Moore impacta a segurança dos algoritmos ao longo do tempo?");
        System.out.println("3. O que acontece com os blockchains atuais (Bitcoin, Ethereum) quando tivermos computadores quânticos práticos?");
        System.out.println("4. Por que o modo ECB não deve ser usado em produção? Demonstre com uma imagem.");
        System.out.println("5. Qual a diferença entre segurança computacional e segurança incondicional?");
        System.out.println("6. Como a Teoria da Computação ajuda a definir o que é 'computacionalmente inviável'?");
        System.out.println("7. O problema P vs NP é relevante para a criptografia? Por quê?");
    }
}
