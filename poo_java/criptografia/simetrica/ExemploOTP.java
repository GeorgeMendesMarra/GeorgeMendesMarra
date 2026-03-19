import java.util.Arrays;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class ExemploOTP {

    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        if (plaintext.length != key.length) {
            throw new IllegalArgumentException("Chave deve ter o mesmo tamanho do texto.");
        }

        byte[] ciphertext = new byte[plaintext.length];

        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ key[i]);
        }

        return ciphertext;
    }

    public static byte[] decrypt(byte[] ciphertext, byte[] key) {
        return encrypt(ciphertext, key);
    }

    public static byte[] generateRandomKey(int length) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[length];
        random.nextBytes(key);
        return key;
    }

    public static void main(String[] args) {

        System.out.println("\nExemplo OTP\n");

        String plaintext = "Mensagem secreta OTP";

        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

        byte[] key = generateRandomKey(plaintextBytes.length);

        System.out.println("Texto Plano: " + plaintext);
        System.out.println("Chave: " + Arrays.toString(key));

        byte[] ciphertext = encrypt(plaintextBytes, key);
        System.out.println("Cifrado: " + Arrays.toString(ciphertext));

        byte[] decrypted = decrypt(ciphertext, key);
        String decryptedText = new String(decrypted, StandardCharsets.UTF_8);

        System.out.println("Decriptado: " + decryptedText);

        // teste com chave errada
        byte[] wrongKey = generateRandomKey(plaintextBytes.length);
        String wrong = new String(decrypt(ciphertext, wrongKey), StandardCharsets.UTF_8);

        System.out.println("Com chave errada: " + wrong);

        if (plaintext.equals(decryptedText)) {
            System.out.println("\n✅ SUCESSO!");
        } else {
            System.out.println("\n❌ ERRO!");
        }
    }
}
