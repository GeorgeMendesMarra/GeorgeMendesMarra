import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAExample {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    // Construtor para gerar um novo par de chaves RSA
    public RSAExample() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048); // Tamanho da chave em bits (quanto maior, mais seguro, mas mais lento)
        KeyPair pair = generator.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    // Construtor para usar chaves existentes (em formato de string Base64)
    public RSAExample(String publicKeyBase64, String privateKeyBase64) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // Decodifica a chave pública da string Base64
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        this.publicKey = keyFactory.generatePublic(publicKeySpec);

        // Decodifica a chave privada da string Base64
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        this.privateKey = keyFactory.generatePrivate(privateKeySpec);
    }

    // Retorna a chave pública em formato de string Base64
    public String getPublicKeyAsBase64() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // Retorna a chave privada em formato de string Base64
    public String getPrivateKeyAsBase64() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // Método para criptografar um texto usando a chave pública
    public String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA"); // Obtém uma instância do Cipher para RSA
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey); // Inicializa o Cipher para o modo de criptografia com a chave pública
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8")); // Criptografa o texto
        return Base64.getEncoder().encodeToString(encryptedBytes); // Codifica os bytes criptografados para Base64 para facilitar a transmissão
    }

    // Método para descriptografar um texto usando a chave privada
    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA"); // Obtém uma instância do Cipher para RSA
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey); // Inicializa o Cipher para o modo de descriptografia com a chave privada
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText); // Decodifica a string Base64 para obter os bytes criptografados
        byte[] decryptedBytes = cipher.doFinal(decodedBytes); // Descriptografa os bytes
        return new String(decryptedBytes, "UTF-8"); // Converte os bytes descriptografados para uma string
    }

    public static void main(String[] args) {
        try {
            // Cria uma instância da classe RSAExample, gerando um novo par de chaves
            RSAExample rsa = new RSAExample();

            // Obtém as chaves pública e privada em formato Base64
            String publicKeyBase64 = rsa.getPublicKeyAsBase64();
            String privateKeyBase64 = rsa.getPrivateKeyAsBase64();

            System.out.println("Chave Pública (Base64): " + publicKeyBase64);
            System.out.println("Chave Privada (Base64): " + privateKeyBase64);

            // Mensagem a ser criptografada
            String message = "Esta é uma mensagem secreta!";
            System.out.println("\nMensagem original: " + message);

            // Criptografa a mensagem
            String encryptedMessage = rsa.encrypt(message);
            System.out.println("Mensagem criptografada (Base64): " + encryptedMessage);

            // Descriptografa a mensagem
            String decryptedMessage = rsa.decrypt(encryptedMessage);
            System.out.println("Mensagem descriptografada: " + decryptedMessage);

            // Exemplo de uso com chaves existentes
            System.out.println("\n--- Exemplo com chaves existentes ---");
            RSAExample rsaComChaves = new RSAExample(publicKeyBase64, privateKeyBase64);
            String outraMensagem = "Outra mensagem para testar.";
            System.out.println("Mensagem original: " + outraMensagem);
            String outraMensagemCriptografada = rsaComChaves.encrypt(outraMensagem);
            System.out.println("Mensagem criptografada (Base64): " + outraMensagemCriptografada);
            String outraMensagemDescriptografada = rsaComChaves.decrypt(outraMensagemCriptografada);
            System.out.println("Mensagem descriptografada: " + outraMensagemDescriptografada);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
