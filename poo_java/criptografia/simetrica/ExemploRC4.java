import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class ExemploRC4 {

    private static final String ALGORITHM = "ARCFOUR";

    public static String encrypt(String plainText, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(cipherBytes);
    }

    public static String decrypt(String cipherTextBase64, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] cipherBytes = Base64.getDecoder().decode(cipherTextBase64);

        byte[] plainTextBytes = cipher.doFinal(cipherBytes);

        return new String(plainTextBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {

        System.out.println("\nExemplo RC4\n");

        String mensagem = "Mensagem secreta RC4";
        String chave = "minha_chave";

        try {

            String criptografado = encrypt(mensagem, chave);
            System.out.println("Criptografado: " + criptografado);

            String decriptado = decrypt(criptografado, chave);
            System.out.println("Decriptado: " + decriptado);

            if (mensagem.equals(decriptado)) {
                System.out.println("\n✅ SUCESSO!");
            } else {
                System.out.println("\n❌ ERRO!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
