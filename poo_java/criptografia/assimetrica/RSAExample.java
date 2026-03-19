import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyFactory;

import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

import java.util.Base64;

public class RSAExample {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    // 🔑 Gera novo par de chaves
    public RSAExample() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    // 🔑 Usa chaves existentes (Base64)
    public RSAExample(String publicKeyBase64, String privateKeyBase64) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        this.publicKey = keyFactory.generatePublic(publicKeySpec);

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        this.privateKey = keyFactory.generatePrivate(privateKeySpec);
    }

    // 📤 Exportar chave pública
    public String getPublicKeyAsBase64() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // 📥 Exportar chave privada
    public String getPrivateKeyAsBase64() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // 🔐 Criptografar
    public String encrypt(String plainText) throws Exception {

        byte[] data = plainText.getBytes("UTF-8");

        // ⚠️ Limite do RSA
        if (data.length > 200) {
            throw new IllegalArgumentException("Mensagem muito grande para RSA");
        }

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);

        byte[] encryptedBytes = cipher.doFinal(data);

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 🔓 Descriptografar
    public String decrypt(String cipherText) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes, "UTF-8");
    }

    // 🚀 TESTE COMPLETO
    public static void main(String[] args) {

        try {
            System.out.println("=== GERANDO CHAVES ===\n");

            RSAExample rsa = new RSAExample();

            String publicKeyBase64 = rsa.getPublicKeyAsBase64();
            String privateKeyBase64 = rsa.getPrivateKeyAsBase64();

            System.out.println("Chave Pública:\n" + publicKeyBase64);
            System.out.println("\nChave Privada:\n" + privateKeyBase64);

            // 📩 Mensagem
            String message = "Esta é uma mensagem secreta!";
            System.out.println("\nMensagem original: " + message);

            // 🔐 Criptografar
            String encryptedMessage = rsa.encrypt(message);
            System.out.println("\nMensagem criptografada:\n" + encryptedMessage);

            // 🔓 Descriptografar
            String decryptedMessage = rsa.decrypt(encryptedMessage);
            System.out.println("\nMensagem descriptografada: " + decryptedMessage);

            // ✔ Verificação
            if (message.equals(decryptedMessage)) {
                System.out.println("\n✅ SUCESSO!");
            } else {
                System.out.println("\n❌ ERRO!");
            }

            // 🔁 Teste com chaves existentes
            System.out.println("\n=== TESTE COM CHAVES SALVAS ===");

            RSAExample rsa2 = new RSAExample(publicKeyBase64, privateKeyBase64);

            String novaMensagem = "Teste com chaves carregadas";
            String enc = rsa2.encrypt(novaMensagem);
            String dec = rsa2.decrypt(enc);

            System.out.println("\nMensagem: " + novaMensagem);
            System.out.println("Criptografada: " + enc);
            System.out.println("Decriptada: " + dec);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
