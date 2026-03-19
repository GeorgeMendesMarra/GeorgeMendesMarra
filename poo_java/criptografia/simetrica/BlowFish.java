import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BlowFish {

    private static final String ALGORITHM = "Blowfish";

    public static void main(String[] args) throws Exception {

        String originalMessage = "Esta é uma mensagem secreta usando Blowfish!";
        System.out.println("Mensagem Original: " + originalMessage);

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        String encryptedMessage = encrypt(originalMessage, keySpec);
        System.out.println("Criptografada: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, keySpec);
        System.out.println("Descriptografada: " + decryptedMessage);

        if (originalMessage.equals(decryptedMessage)) {
            System.out.println("✅ SUCESSO!");
        } else {
            System.out.println("❌ ERRO!");
        }
    }

    public static String encrypt(String plainText, SecretKeySpec keySpec) throws Exception {

        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedTextBase64, SecretKeySpec keySpec) throws Exception {

        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedTextBase64);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
