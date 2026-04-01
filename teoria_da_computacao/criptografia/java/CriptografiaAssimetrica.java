import java.math.BigInteger;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;

/**
 * Programa Didático de Criptografia Assimétrica
 * 
 * Este programa demonstra os principais conceitos de criptografia assimétrica:
 * - Geração de pares de chaves (pública/privada)
 * - Cifração e decifração de mensagens
 * - Assinatura digital e verificação
 * 
 * Conceitos teóricos abordados:
 * - RSA: baseado na dificuldade de fatoração de números grandes
 * - ECC: baseado no problema do logaritmo discreto em curvas elípticas
 * 
 * @author Professor - Teoria da Computação
 */
public class CriptografiaAssimetrica {

    // Configurações de segurança
    private static final String RSA_ALGORITHM = "RSA";
    private static final String ECC_ALGORITHM = "EC";
    private static final int RSA_KEY_SIZE = 2048;  // Tamanho seguro para RSA
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("   PROGRAMA DIDÁTICO DE CRIPTOGRAFIA ASSIMÉTRICA");
        System.out.println("   Teoria da Computação - Blockchain e Criptomoedas");
        System.out.println("=".repeat(60));
        
        boolean continuar = true;
        
        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    demonstrarRSA();
                    pausar("Pressione ENTER para continuar...");
                    break;
                case 2:
                    demonstrarECC();
                    pausar("Pressione ENTER para continuar...");
                    break;
                case 3:
                    demonstrarAssinaturaDigital();
                    pausar("Pressione ENTER para continuar...");
                    break;
                case 4:
                    explicarConceitosTeoricos();
                    pausar("\nPressione ENTER para voltar ao menu...");
                    break;
                case 5:
                    continuar = false;
                    System.out.println("\n🔒 Programa encerrado. Lembre-se: a criptografia é a base da segurança digital!");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
                    pausar("Pressione ENTER para continuar...");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Exibe o menu principal do programa
     */
    private static void exibirMenu() {
        System.out.println("\n📋 MENU PRINCIPAL:");
        System.out.println("1. Demonstrar RSA (Cifração/Decifração)");
        System.out.println("2. Demonstrar ECC (Cifração/Decifração)");
        System.out.println("3. Demonstrar Assinatura Digital");
        System.out.println("4. Explicar Conceitos Teóricos");
        System.out.println("5. Sair");
        System.out.print("\nEscolha uma opção: ");
    }
    
    /**
     * Lê e valida a opção escolhida pelo usuário
     */
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Método auxiliar para pausar a execução e aguardar o usuário
     * @param mensagem Mensagem a ser exibida antes da pausa
     */
    private static void pausar(String mensagem) {
        System.out.println("\n" + "-".repeat(60));
        System.out.print(mensagem);
        scanner.nextLine();
    }
    
    /**
     * Demonstra o algoritmo RSA (Rivest-Shamir-Adleman)
     * 
     * Conceitos:
     * - RSA usa duas chaves: pública (para cifrar) e privada (para decifrar)
     * - Segurança baseada na dificuldade de fatorar o produto de dois números primos grandes
     */
    private static void demonstrarRSA() {
        System.out.println("\n🔐 DEMONSTRAÇÃO RSA");
        System.out.println("=".repeat(50));
        
        try {
            // 1. Gerar par de chaves RSA
            System.out.println("\n1. Gerando par de chaves RSA (tamanho: " + RSA_KEY_SIZE + " bits)...");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyGen.initialize(RSA_KEY_SIZE);
            KeyPair keyPair = keyGen.generateKeyPair();
            
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            
            System.out.println("   ✓ Chave pública gerada: " + formatarChave(publicKey));
            System.out.println("   ✓ Chave privada gerada: " + formatarChave(privateKey));
            
            // 2. Solicitar mensagem do usuário
            System.out.print("\n2. Digite a mensagem que deseja cifrar: ");
            String mensagemOriginal = scanner.nextLine();
            
            // 3. Cifrar com chave pública
            System.out.println("\n3. Cifrando a mensagem com a CHAVE PÚBLICA...");
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] mensagemCifrada = cipher.doFinal(mensagemOriginal.getBytes());
            
            System.out.println("   Mensagem original: " + mensagemOriginal);
            System.out.println("   Mensagem cifrada (Base64): " + Base64.getEncoder().encodeToString(mensagemCifrada));
            
            // 4. Decifrar com chave privada
            System.out.println("\n4. Decifrando a mensagem com a CHAVE PRIVADA...");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] mensagemDecifrada = cipher.doFinal(mensagemCifrada);
            String mensagemRecuperada = new String(mensagemDecifrada);
            
            System.out.println("   Mensagem recuperada: " + mensagemRecuperada);
            
            // 5. Verificação
            if (mensagemOriginal.equals(mensagemRecuperada)) {
                System.out.println("\n✅ SUCESSO! A mensagem foi cifrada e decifrada corretamente.");
                System.out.println("   🔑 Conceito: Alguém com a chave pública pode CIFRAR, mas apenas quem tem");
                System.out.println("      a chave privada pode DECIFRAR. Isso garante confidencialidade.");
            } else {
                System.out.println("\n❌ ERRO! A mensagem não foi recuperada corretamente.");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Erro na demonstração RSA: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstra o algoritmo ECC (Elliptic Curve Cryptography)
     * 
     * Conceitos:
     * - ECC oferece mesma segurança que RSA com chaves menores
     * - Baseado no problema do logaritmo discreto em curvas elípticas
     * - Amplamente usado em blockchain e criptomoedas (Bitcoin, Ethereum)
     */
    private static void demonstrarECC() {
        System.out.println("\n📈 DEMONSTRAÇÃO ECC (Elliptic Curve Cryptography)");
        System.out.println("=".repeat(50));
        
        try {
            // 1. Gerar par de chaves ECC
            System.out.println("\n1. Gerando par de chaves ECC (curva: secp256r1)...");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ECC_ALGORITHM);
            keyGen.initialize(256); // Tamanho de chave equivalente a 3072 bits RSA
            KeyPair keyPair = keyGen.generateKeyPair();
            
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            
            System.out.println("   ✓ Chave pública gerada: " + formatarChave(publicKey));
            System.out.println("   ✓ Chave privada gerada: " + formatarChave(privateKey));
            System.out.println("   📊 Vantagem: Chave ECC de 256 bits oferece segurança similar a RSA 3072 bits!");
            
            // 2. Solicitar mensagem
            System.out.print("\n2. Digite a mensagem que deseja cifrar (ECC): ");
            String mensagemOriginal = scanner.nextLine();
            
            // Nota: ECC puro não é usado diretamente para cifração, mas sim ECIES (Integrated Encryption Scheme)
            // Para demonstração didática, usamos o esquema de encapsulamento de chaves
            
            System.out.println("\n3. Explicação didática do ECC:");
            System.out.println("   • ECC não cifra mensagens diretamente como o RSA.");
            System.out.println("   • Na prática, usa-se ECDH (Diffie-Hellman em curvas elípticas) para");
            System.out.println("     estabelecer uma chave secreta compartilhada.");
            System.out.println("   • Essa chave é então usada com um cifrador simétrico (AES).");
            System.out.println("   • Bitcoin e Ethereum usam ECDSA (Elliptic Curve Digital Signature Algorithm).");
            
            // Simulação de assinatura com ECC
            System.out.println("\n4. Simulando assinatura digital com ECDSA:");
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
            ecdsaSign.initSign(privateKey);
            ecdsaSign.update(mensagemOriginal.getBytes());
            byte[] assinatura = ecdsaSign.sign();
            
            System.out.println("   Mensagem: " + mensagemOriginal);
            System.out.println("   Assinatura gerada (Base64): " + Base64.getEncoder().encodeToString(assinatura));
            
            // Verificação
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(mensagemOriginal.getBytes());
            boolean valida = ecdsaVerify.verify(assinatura);
            
            if (valida) {
                System.out.println("   ✅ Assinatura verificada com sucesso!");
                System.out.println("   🔑 Conceito: A assinatura digital prova que a mensagem foi criada");
                System.out.println("      pelo proprietário da chave privada e não foi alterada.");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Erro na demonstração ECC: " + e.getMessage());
            System.out.println("\n💡 Nota: Seu ambiente Java precisa suportar ECC. Java 8+ tem suporte nativo.");
        }
    }
    
    /**
     * Demonstra o conceito de assinatura digital
     * 
     * Conceitos:
     * - Autenticidade: garante que a mensagem veio de quem afirma ser
     * - Integridade: garante que a mensagem não foi alterada
     * - Não-repúdio: o autor não pode negar ter criado a assinatura
     */
    private static void demonstrarAssinaturaDigital() {
        System.out.println("\n✍️ DEMONSTRAÇÃO DE ASSINATURA DIGITAL");
        System.out.println("=".repeat(50));
        
        try {
            // 1. Gerar chaves para assinatura
            System.out.println("\n1. Gerando chaves para assinatura digital (RSA)...");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyGen.initialize(RSA_KEY_SIZE);
            KeyPair keyPair = keyGen.generateKeyPair();
            
            // 2. Solicitar mensagem
            System.out.print("\n2. Digite uma mensagem para assinar: ");
            String mensagem = scanner.nextLine();
            
            // 3. Assinar com chave privada
            System.out.println("\n3. Assinando a mensagem com a CHAVE PRIVADA...");
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(keyPair.getPrivate());
            signature.update(mensagem.getBytes());
            byte[] assinatura = signature.sign();
            
            System.out.println("   Mensagem: " + mensagem);
            System.out.println("   Assinatura (Base64): " + Base64.getEncoder().encodeToString(assinatura));
            
            // 4. Verificar com chave pública
            System.out.println("\n4. Verificando a assinatura com a CHAVE PÚBLICA...");
            signature.initVerify(keyPair.getPublic());
            signature.update(mensagem.getBytes());
            boolean valida = signature.verify(assinatura);
            
            if (valida) {
                System.out.println("   ✅ Assinatura VÁLIDA! A mensagem é autêntica e íntegra.");
            } else {
                System.out.println("   ❌ Assinatura INVÁLIDA! A mensagem pode ter sido alterada.");
            }
            
            // 5. Demonstração de tentativa de falsificação
            System.out.println("\n5. Testando alteração da mensagem...");
            String mensagemAlterada = mensagem + " (texto adulterado)";
            signature.initVerify(keyPair.getPublic());
            signature.update(mensagemAlterada.getBytes());
            boolean validaAlterada = signature.verify(assinatura);
            
            System.out.println("   Mensagem original: " + mensagem);
            System.out.println("   Mensagem alterada: " + mensagemAlterada);
            System.out.println("   Assinatura verificada com mensagem alterada: " + 
                              (validaAlterada ? "✅ VÁLIDA" : "❌ INVÁLIDA"));
            
            System.out.println("\n🔑 Conceitos importantes:");
            System.out.println("   • Assinatura digital = HASH(mensagem) cifrado com chave privada");
            System.out.println("   • Garante autenticidade, integridade e não-repúdio");
            System.out.println("   • Fundamental em blockchain para validar transações");
            
        } catch (Exception e) {
            System.err.println("❌ Erro na demonstração de assinatura: " + e.getMessage());
        }
    }
    
    /**
     * Explica os conceitos teóricos fundamentais
     * Conecta a prática com a Teoria da Computação
     */
    private static void explicarConceitosTeoricos() {
        System.out.println("\n📚 CONCEITOS TEÓRICOS DE CRIPTOGRAFIA ASSIMÉTRICA");
        System.out.println("=".repeat(60));
        
        System.out.println("\n1. FUNDAMENTOS MATEMÁTICOS:");
        System.out.println("   • RSA: Baseado na dificuldade de fatorar números inteiros grandes");
        System.out.println("     - Problema: Dado n = p * q (p e q primos), encontrar p e q");
        System.out.println("     - Complexidade: Problema NP (não solucionável em tempo polinomial)");
        System.out.println("     - Classes: NP ∩ co-NP (não se sabe se está em P)");
        System.out.println();
        System.out.println("   • ECC: Baseado no problema do logaritmo discreto em curvas elípticas");
        System.out.println("     - Problema: Dados P e Q = k*P, encontrar k");
        System.out.println("     - Mais eficiente que RSA: chave 256 bits ≈ RSA 3072 bits");
        
        System.out.println("\n2. RELAÇÃO COM A TEORIA DA COMPUTAÇÃO:");
        System.out.println("   • Classes de Complexidade:");
        System.out.println("     - P: Problemas resolvíveis em tempo polinomial");
        System.out.println("     - NP: Problemas verificáveis em tempo polinomial");
        System.out.println("     - BQP: Problemas solúveis em computadores quânticos");
        System.out.println();
        System.out.println("   • O Algoritmo de Shor (1994):");
        System.out.println("     - Fatora números em tempo polinomial em computadores quânticos");
        System.out.println("     - Ameaça diretamente o RSA");
        System.out.println("     - Demonstra que BQP ⊄ P (computação quântica > clássica)");
        
        System.out.println("\n3. BLOCKCHAIN E CRIPTOMOEDAS:");
        System.out.println("   • Bitcoin usa ECDSA (secp256k1) para assinaturas");
        System.out.println("   • Cada transação é assinada com chave privada");
        System.out.println("   • Endereço Bitcoin = hash da chave pública");
        System.out.println("   • Prova de Trabalho (PoW): busca de nonce que satisfaz hash < alvo");
        System.out.println("     - Complexidade: O(2^n) para encontrar solução");
        System.out.println("     - Verificação: O(1) (P)");
        
        System.out.println("\n4. COMPUTAÇÃO QUÂNTICA:");
        System.out.println("   • Algoritmos quânticos que quebram criptografia atual:");
        System.out.println("     - Shor: Fatoração e logaritmo discreto (quebra RSA e ECC)");
        System.out.println("     - Grover: Busca em base de dados não ordenados (reduz segurança simétrica)");
        System.out.println("   • Soluções pós-quânticas:");
        System.out.println("     - Criptografia baseada em lattices (NTRU, Kyber)");
        System.out.println("     - Códigos corretores de erros (McEliece)");
        System.out.println("     - Funções hash multivariadas");
        
        System.out.println("\n5. REFERÊNCIAS TEÓRICAS:");
        System.out.println("   • Turing, A. (1936) - Computabilidade e Máquina de Turing");
        System.out.println("   • Diffie, W. & Hellman, M. (1976) - Criptografia de chave pública");
        System.out.println("   • Rivest, R. et al. (1978) - RSA");
        System.out.println("   • Koblitz, N. & Miller, V. (1985) - Criptografia de curvas elípticas");
        System.out.println("   • Shor, P. (1994) - Algoritmo quântico para fatoração");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("💡 DICA: Esses conceitos são fundamentais para entender a segurança");
        System.out.println("   de blockchains como Bitcoin e Ethereum, e os desafios futuros");
        System.out.println("   com o avanço da computação quântica.");
    }
    
    /**
     * Formata a exibição da chave para facilitar a leitura
     */
    private static String formatarChave(Key key) {
        String encoded = Base64.getEncoder().encodeToString(key.getEncoded());
        return encoded.substring(0, Math.min(encoded.length(), 50)) + "...";
    }
}
