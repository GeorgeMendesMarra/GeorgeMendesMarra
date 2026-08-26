import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.*;
import javax.crypto.spec.*;

/* ============================================================================
 * CRIPTOGRAFIA EM JAVA - EXEMPLOS DO MAIS SIMPLES AO MAIS COMPLEXO
 * ============================================================================
 *
 * Mesmo conteudo das versoes em C e C++, reescrito em Java. Diferenca
 * importante: Java ja vem com uma API de criptografia embutida, a JCA
 * (Java Cryptography Architecture, pacotes java.security e javax.crypto),
 * entao NAO precisamos de nenhuma biblioteca externa como a OpenSSL.
 *
 * Cada tecnica vira uma classe com metodos estaticos (equivalente ao que
 * fizemos em C++), representando visualmente a mesma organizacao "POO" do
 * repositorio original de referencia.
 *
 * PARTE 1 (sem bibliotecas externas): cifras classicas / didaticas
 *   1. Cifra de Cesar
 *   2. Cifra de Vigenere
 *   3. Cifra XOR
 *
 * PARTE 2 (usando a JCA nativa do Java): criptografia real, usada na pratica
 *   4. Bytes aleatorios seguros
 *   5. Hash SHA-256
 *   6. HMAC (hash com chave)
 *   7. PBKDF2 (derivar chave a partir de senha)
 *   8. AES-256-CBC (cifra simetrica)
 *   9. AES-256-GCM (cifra simetrica + autenticacao)
 *  10. RSA (cifra assimetrica com par de chaves)
 *  11. Assinatura digital RSA
 *
 * COMO COMPILAR:
 *   javac CriptografiaCompleto.java
 *
 * COMO EXECUTAR:
 *   java CriptografiaCompleto
 * ============================================================================
 */
public class CriptografiaCompleto {

    /* ========================================================================
     * FUNCAO AUXILIAR GERAL: converte um vetor de bytes para uma string
     * hexadecimal, para exibir na tela dados que nem sempre sao caracteres
     * imprimiveis (como texto cifrado).
     * ========================================================================
     */
    static String paraHex(byte[] dados) {
        StringBuilder sb = new StringBuilder();
        for (byte b : dados) {
            // "%02x" garante sempre 2 digitos por byte (ex.: "0a" e nao "a")
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /* ========================================================================
     * PARTE 1.1 - CIFRA DE CESAR
     * Ideia: desloca cada letra um numero fixo de posicoes no alfabeto.
     * Fraqueza: so existem 25 chaves possiveis -> facil de quebrar por
     * forca bruta.
     * ========================================================================
     */
    static class CifraCesar {

        static String cifrar(String texto, int deslocamento) {
            StringBuilder resultado = new StringBuilder();

            for (char c : texto.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    // 'A' = 65 na tabela ASCII. Normalizamos para 0-25,
                    // aplicamos o deslocamento com modulo 26 e voltamos
                    // para a faixa de letras
                    c = (char) ((c - 'A' + deslocamento) % 26 + 'A');
                } else if (Character.isLowerCase(c)) {
                    c = (char) ((c - 'a' + deslocamento) % 26 + 'a');
                }
                // espacos, numeros e pontuacao permanecem inalterados
                resultado.append(c);
            }
            return resultado.toString();
        }

        static String decifrar(String texto, int deslocamento) {
            // decifrar e cifrar com o deslocamento invertido
            return cifrar(texto, 26 - (deslocamento % 26));
        }
    }

    static void demoCesar() {
        System.out.println("\n=== 1. Cifra de Cesar ===");

        String mensagem = "Ola Mundo";
        int chave = 3;

        System.out.println("Original:  " + mensagem);

        String cifrado = CifraCesar.cifrar(mensagem, chave);
        System.out.println("Cifrado:   " + cifrado);

        String decifrado = CifraCesar.decifrar(cifrado, chave);
        System.out.println("Decifrado: " + decifrado);
    }

    /* ========================================================================
     * PARTE 1.2 - CIFRA DE VIGENERE
     * Ideia: como o Cesar, mas o deslocamento muda a cada letra, seguindo
     * uma palavra-chave que se repete ao longo do texto.
     * Fraqueza: com chave curta e texto longo, e quebravel por analise
     * estatistica (metodo de Kasiski).
     * ========================================================================
     */
    static class CifraVigenere {

        static String cifrar(String texto, String chave) {
            StringBuilder resultado = new StringBuilder();
            int j = 0; // indice que percorre a chave (so avanca em letras)

            for (char c : texto.toCharArray()) {
                if (Character.isLetter(c)) {
                    // pega a letra da chave correspondente e calcula o
                    // deslocamento
                    int deslocamento = Character.toUpperCase(chave.charAt(j % chave.length())) - 'A';

                    if (Character.isUpperCase(c)) {
                        c = (char) ((c - 'A' + deslocamento) % 26 + 'A');
                    } else {
                        c = (char) ((c - 'a' + deslocamento) % 26 + 'a');
                    }
                    j++; // so avanca a chave ao processar uma letra de verdade
                }
                resultado.append(c);
            }
            return resultado.toString();
        }

        static String decifrar(String texto, String chave) {
            StringBuilder resultado = new StringBuilder();
            int j = 0;

            for (char c : texto.toCharArray()) {
                if (Character.isLetter(c)) {
                    int deslocamento = Character.toUpperCase(chave.charAt(j % chave.length())) - 'A';

                    if (Character.isUpperCase(c)) {
                        // +26 evita numero negativo antes do modulo
                        c = (char) ((c - 'A' - deslocamento + 26) % 26 + 'A');
                    } else {
                        c = (char) ((c - 'a' - deslocamento + 26) % 26 + 'a');
                    }
                    j++;
                }
                resultado.append(c);
            }
            return resultado.toString();
        }
    }

    static void demoVigenere() {
        System.out.println("\n=== 2. Cifra de Vigenere ===");

        String mensagem = "Ataque ao amanhecer";
        String chave = "CHAVE";

        System.out.println("Original:  " + mensagem);

        String cifrado = CifraVigenere.cifrar(mensagem, chave);
        System.out.println("Cifrado:   " + cifrado);

        String decifrado = CifraVigenere.decifrar(cifrado, chave);
        System.out.println("Decifrado: " + decifrado);
    }

    /* ========================================================================
     * PARTE 1.3 - CIFRA XOR
     * Ideia: usa a operacao bit a bit XOR (^). Propriedade chave:
     * (A XOR chave) XOR chave = A -- cifrar e decifrar usam o MESMO metodo.
     * Fraqueza: com chave curta reutilizada, padroes se repetem e a cifra
     * pode ser quebrada. So e realmente segura com chave do tamanho do
     * texto, usada uma unica vez.
     * ========================================================================
     */
    static class CifraXor {

        // Cifra e decifra: aplica XOR byte a byte com a chave, repetindo-a
        // ciclicamente. Funciona com qualquer sequencia de bytes.
        static byte[] processar(byte[] dados, byte[] chave) {
            byte[] resultado = new byte[dados.length];

            for (int i = 0; i < dados.length; i++) {
                resultado[i] = (byte) (dados[i] ^ chave[i % chave.length]);
            }
            return resultado;
        }
    }

    static void demoXor() {
        System.out.println("\n=== 3. Cifra XOR ===");

        String mensagemTexto = "Mensagem secreta";
        byte[] mensagem = mensagemTexto.getBytes();
        byte[] chave = "chaveXYZ".getBytes();

        System.out.println("Original:      " + mensagemTexto);

        // ---------- CIFRAR ----------
        byte[] cifrado = CifraXor.processar(mensagem, chave);
        System.out.println("Cifrado (hex): " + paraHex(cifrado));

        // ---------- DECIFRAR ----------
        // e o MESMO metodo aplicado de novo com a mesma chave
        byte[] decifrado = CifraXor.processar(cifrado, chave);
        System.out.println("Decifrado:     " + new String(decifrado));
    }

    /* ========================================================================
     * PARTE 2.1 - BYTES ALEATORIOS SEGUROS
     * Base de praticamente tudo em criptografia real: gerar numeros que
     * nao podem ser previstos. Em Java, isso e feito com SecureRandom (e
     * NAO com java.util.Random, que nao e seguro para criptografia).
     * ========================================================================
     */
    static class Aleatorio {

        static byte[] gerarBytes(int quantidade) {
            // SecureRandom usa uma fonte de entropia segura do sistema
            // operacional por baixo dos panos
            SecureRandom random = new SecureRandom();
            byte[] buffer = new byte[quantidade];
            random.nextBytes(buffer);
            return buffer;
        }
    }

    static void demoBytesAleatorios() {
        System.out.println("\n=== 4. Bytes aleatorios seguros ===");

        byte[] buffer = Aleatorio.gerarBytes(16);
        System.out.println("Bytes aleatorios: " + paraHex(buffer));
    }

    /* ========================================================================
     * PARTE 2.2 - HASH SHA-256
     * Transforma qualquer dado em um "resumo" de tamanho fixo. E uma via:
     * nao da para reverter o hash e obter o dado original.
     * ========================================================================
     */
    static class Sha256 {

        static byte[] calcular(String mensagem) throws NoSuchAlgorithmException {
            // MessageDigest e a classe da JCA responsavel por calcular hashes
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(mensagem.getBytes());
        }
    }

    static void demoSha256() throws NoSuchAlgorithmException {
        System.out.println("\n=== 5. Hash SHA-256 ===");

        String mensagem = "Ola, mundo!";
        byte[] hash = Sha256.calcular(mensagem);

        System.out.println("Mensagem: " + mensagem);
        System.out.println("SHA-256:  " + paraHex(hash));
    }

    /* ========================================================================
     * PARTE 2.3 - HMAC (hash com chave)
     * Um hash comum so garante que o dado nao mudou. O HMAC usa uma chave
     * secreta, garantindo tambem que quem gerou o hash conhecia a chave.
     * ========================================================================
     */
    static class HmacSha256 {

        static byte[] calcular(byte[] chave, String mensagem)
                throws NoSuchAlgorithmException, InvalidKeyException {

            // Mac e a classe da JCA para codigos de autenticacao de mensagem
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec chaveSecreta = new SecretKeySpec(chave, "HmacSHA256");
            mac.init(chaveSecreta);

            return mac.doFinal(mensagem.getBytes());
        }
    }

    static void demoHmac() throws NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("\n=== 6. HMAC-SHA256 ===");

        byte[] chave = Aleatorio.gerarBytes(32); // chave secreta compartilhada
        String mensagem = "transferir 100 reais";

        byte[] resultado = HmacSha256.calcular(chave, mensagem);

        System.out.println("Mensagem:    " + mensagem);
        System.out.println("HMAC-SHA256: " + paraHex(resultado));

        // Na pratica: quem recebe a mensagem + o HMAC recalcula o HMAC com
        // a mesma chave e compara o resultado. Se bater, a mensagem e
        // autentica.
    }

    /* ========================================================================
     * PARTE 2.4 - PBKDF2 (derivar chave a partir de senha)
     * Senhas digitadas por humanos sao fracas como chave direta. O PBKDF2
     * transforma uma senha em uma chave forte, aplicando o hash muitas
     * vezes de proposito (isso dificulta ataques de forca bruta).
     * ========================================================================
     */
    static class Pbkdf2 {

        static byte[] derivar(String senha, byte[] salt, int iteracoes, int tamanhoChaveBits)
                throws NoSuchAlgorithmException, InvalidKeySpecException {

            // PBEKeySpec agrupa os parametros: senha, salt, iteracoes e
            // tamanho da chave desejada (em bits)
            PBEKeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, iteracoes, tamanhoChaveBits);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return factory.generateSecret(spec).getEncoded();
        }
    }

    static void demoPbkdf2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("\n=== 7. PBKDF2 (senha -> chave) ===");

        String senha = "minhaSenh4Fraca";

        // "salt": valor aleatorio unico, guardado junto com a chave
        // derivada. Impede que a mesma senha sempre gere a mesma chave.
        byte[] salt = Aleatorio.gerarBytes(16);

        byte[] chaveDerivada = Pbkdf2.derivar(senha, salt, 100000, 256); // 256 bits = 32 bytes

        System.out.println("Senha:          " + senha);
        System.out.println("Salt:           " + paraHex(salt));
        System.out.println("Chave derivada: " + paraHex(chaveDerivada));

        // Importante: o salt precisa ser guardado (nao e secreto), pois
        // sera necessario para derivar a mesma chave de novo no futuro.
    }

    /* ========================================================================
     * PARTE 2.5 - AES-256-CBC (cifra simetrica)
     * Cifra e decifra dados usando a MESMA chave dos dois lados.
     * ========================================================================
     */
    static class AesCbc {

        static byte[] cifrar(String texto, byte[] chave, byte[] iv) throws Exception {
            // Cipher e a classe central da JCA para cifrar/decifrar.
            // A string define algoritmo/modo/padding: AES, CBC, com
            // padding PKCS5 (equivalente ao PKCS7 usado internamente)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec chaveSecreta = new SecretKeySpec(chave, "AES");
            IvParameterSpec ivParam = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, chaveSecreta, ivParam);
            return cipher.doFinal(texto.getBytes());
        }

        static String decifrar(byte[] cifrado, byte[] chave, byte[] iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec chaveSecreta = new SecretKeySpec(chave, "AES");
            IvParameterSpec ivParam = new IvParameterSpec(iv);

            cipher.init(Cipher.DECRYPT_MODE, chaveSecreta, ivParam);
            byte[] decifrado = cipher.doFinal(cifrado);

            return new String(decifrado);
        }
    }

    static void demoAesCbc() throws Exception {
        System.out.println("\n=== 8. AES-256-CBC ===");

        byte[] chave = Aleatorio.gerarBytes(32); // 32 bytes = AES-256
        byte[] iv = Aleatorio.gerarBytes(16);    // IV deve ser diferente a cada cifragem

        String texto = "Mensagem secreta";

        byte[] cifrado = AesCbc.cifrar(texto, chave, iv);
        System.out.println("Original:      " + texto);
        System.out.println("Cifrado (hex): " + paraHex(cifrado));

        String decifrado = AesCbc.decifrar(cifrado, chave, iv);
        System.out.println("Decifrado:     " + decifrado);
    }

    /* ========================================================================
     * PARTE 2.6 - AES-256-GCM (cifra + autenticacao, o padrao moderno)
     * O CBC so cifra -- nao detecta se alguem alterou o dado cifrado. O
     * GCM cifra e gera uma "tag" que comprova que os dados nao foram
     * alterados. E o modo recomendado atualmente para a maioria dos casos.
     * ========================================================================
     */
    static class AesGcm {

        // No GCM da JCA, a tag de autenticacao sai automaticamente
        // "grudada" no final do array cifrado -- diferente de C/C++, onde
        // pegamos a tag separadamente
        static byte[] cifrar(String texto, byte[] chave, byte[] iv) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec chaveSecreta = new SecretKeySpec(chave, "AES");
            // 128 = tamanho da tag de autenticacao em bits (16 bytes)
            GCMParameterSpec gcmParam = new GCMParameterSpec(128, iv);

            cipher.init(Cipher.ENCRYPT_MODE, chaveSecreta, gcmParam);
            return cipher.doFinal(texto.getBytes());
        }

        // Retorna null se a autenticacao falhar (dado corrompido/adulterado)
        static String decifrar(byte[] cifradoComTag, byte[] chave, byte[] iv) {
            try {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

                SecretKeySpec chaveSecreta = new SecretKeySpec(chave, "AES");
                GCMParameterSpec gcmParam = new GCMParameterSpec(128, iv);

                cipher.init(Cipher.DECRYPT_MODE, chaveSecreta, gcmParam);

                // Se a tag nao bater, doFinal lanca AEADBadTagException
                byte[] decifrado = cipher.doFinal(cifradoComTag);
                return new String(decifrado);

            } catch (AEADBadTagException e) {
                return null; // sinaliza falha de autenticacao
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void demoAesGcm() throws Exception {
        System.out.println("\n=== 9. AES-256-GCM ===");

        byte[] chave = Aleatorio.gerarBytes(32);
        byte[] iv = Aleatorio.gerarBytes(12); // GCM usa IV de 12 bytes por convencao

        String texto = "Dados confidenciais";

        byte[] cifradoComTag = AesGcm.cifrar(texto, chave, iv);
        System.out.println("Original: " + texto);
        System.out.println("Cifrado+tag (hex): " + paraHex(cifradoComTag));

        String decifrado = AesGcm.decifrar(cifradoComTag, chave, iv);

        if (decifrado != null) {
            System.out.println("Decifrado (autenticado com sucesso): " + decifrado);
        } else {
            System.out.println("ERRO: dado corrompido ou adulterado!");
        }
    }

    /* ========================================================================
     * PARTE 2.7 - RSA (cifra assimetrica com par de chaves)
     * Usa DUAS chaves diferentes: uma publica (cifra, pode ser
     * compartilhada) e uma privada (decifra, deve ficar em segredo).
     * ========================================================================
     */
    static class Rsa {

        // Gera um novo par de chaves publica/privada
        static KeyPair gerarParChaves(int bits) throws NoSuchAlgorithmException {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(bits);
            return generator.generateKeyPair();
        }

        static byte[] cifrar(PublicKey chavePublica, String mensagem) throws Exception {
            // OAEPWithSHA-256AndMGF1Padding = padding seguro recomendado
            // atualmente (equivalente ao RSA_PKCS1_OAEP_PADDING da OpenSSL)
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
            return cipher.doFinal(mensagem.getBytes());
        }

        static String decifrar(PrivateKey chavePrivada, byte[] cifrado) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
            byte[] decifrado = cipher.doFinal(cifrado);
            return new String(decifrado);
        }
    }

    static void demoRsaCifrar() throws Exception {
        System.out.println("\n=== 10. RSA (cifra assimetrica) ===");

        KeyPair par = Rsa.gerarParChaves(2048); // tamanho recomendado atual

        String mensagem = "segredo";

        byte[] cifrado = Rsa.cifrar(par.getPublic(), mensagem);
        System.out.println("Mensagem: " + mensagem);
        System.out.println("Cifrado:  " + paraHex(cifrado));

        String decifrado = Rsa.decifrar(par.getPrivate(), cifrado);
        System.out.println("Decifrado: " + decifrado);

        // Nota: RSA so cifra blocos pequenos (poucas centenas de bytes).
        // Na pratica, RSA cifra uma chave AES, e o AES cifra os dados de
        // fato -- e a chamada "criptografia hibrida" (assim funciona o
        // HTTPS).
    }

    /* ========================================================================
     * PARTE 2.8 - ASSINATURA DIGITAL RSA
     * Diferente de cifrar, aqui o objetivo e provar autoria: qualquer um
     * pode conferir a assinatura com a chave publica, mas so quem tem a
     * chave privada consegue cria-la.
     * ========================================================================
     */
    static class AssinaturaRsa {

        static byte[] assinar(PrivateKey chavePrivada, String mensagem) throws Exception {
            // Signature e a classe da JCA para assinatura/verificacao.
            // "SHA256withRSA" = calcula o hash SHA-256 e depois assina com RSA
            Signature assinador = Signature.getInstance("SHA256withRSA");
            assinador.initSign(chavePrivada);
            assinador.update(mensagem.getBytes());
            return assinador.sign();
        }

        static boolean verificar(PublicKey chavePublica, String mensagem, byte[] assinatura) throws Exception {
            Signature verificador = Signature.getInstance("SHA256withRSA");
            verificador.initVerify(chavePublica);
            verificador.update(mensagem.getBytes());
            return verificador.verify(assinatura);
        }
    }

    static void demoRsaAssinatura() throws Exception {
        System.out.println("\n=== 11. Assinatura digital RSA ===");

        KeyPair par = Rsa.gerarParChaves(2048);
        String mensagem = "Autorizo o pagamento de 500 reais";

        byte[] assinatura = AssinaturaRsa.assinar(par.getPrivate(), mensagem);
        System.out.println("Mensagem:   " + mensagem);
        System.out.println("Assinatura: " + paraHex(assinatura));

        // Na pratica, quem verifica so teria a chave publica (par.getPublic())
        boolean valido = AssinaturaRsa.verificar(par.getPublic(), mensagem, assinatura);

        if (valido) {
            System.out.println("Resultado: assinatura VALIDA -- a mensagem e autentica.");
        } else {
            System.out.println("Resultado: assinatura INVALIDA -- mensagem alterada ou chave errada.");
        }
    }

    /* ========================================================================
     * MENU PRINCIPAL
     * Permite escolher qual exemplo rodar, ou rodar todos em sequencia.
     * ========================================================================
     */
    static void mostrarMenu() {
        System.out.println("\n===================================================");
        System.out.println(" CRIPTOGRAFIA EM JAVA - MENU DE EXEMPLOS");
        System.out.println("===================================================");
        System.out.println(" Cifras classicas (sem bibliotecas externas):");
        System.out.println("  1. Cifra de Cesar");
        System.out.println("  2. Cifra de Vigenere");
        System.out.println("  3. Cifra XOR");
        System.out.println(" Criptografia real (com a JCA nativa do Java):");
        System.out.println("  4. Bytes aleatorios seguros");
        System.out.println("  5. Hash SHA-256");
        System.out.println("  6. HMAC-SHA256");
        System.out.println("  7. PBKDF2 (senha -> chave)");
        System.out.println("  8. AES-256-CBC");
        System.out.println("  9. AES-256-GCM");
        System.out.println(" 10. RSA (cifrar/decifrar)");
        System.out.println(" 11. Assinatura digital RSA");
        System.out.println("  0. Rodar TODOS os exemplos em sequencia");
        System.out.println(" -1. Sair");
        System.out.println("===================================================");
        System.out.print("Escolha uma opcao: ");
    }

    static void rodarTodos() throws Exception {
        demoCesar();
        demoVigenere();
        demoXor();
        demoBytesAleatorios();
        demoSha256();
        demoHmac();
        demoPbkdf2();
        demoAesCbc();
        demoAesGcm();
        demoRsaCifrar();
        demoRsaAssinatura();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            mostrarMenu();

            // encerra se a entrada nao for um numero valido
            if (!scanner.hasNextInt()) {
                break;
            }
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:  demoCesar(); break;
                case 2:  demoVigenere(); break;
                case 3:  demoXor(); break;
                case 4:  demoBytesAleatorios(); break;
                case 5:  demoSha256(); break;
                case 6:  demoHmac(); break;
                case 7:  demoPbkdf2(); break;
                case 8:  demoAesCbc(); break;
                case 9:  demoAesGcm(); break;
                case 10: demoRsaCifrar(); break;
                case 11: demoRsaAssinatura(); break;
                case 0:  rodarTodos(); break;
                case -1: System.out.println("Encerrando..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != -1);

        scanner.close();
    }
}
