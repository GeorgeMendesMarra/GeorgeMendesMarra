import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.SecureRandom;
import java.util.Base64;

public class ExemploAES {

    private static final String ALGO = "AES/CBC/PKCS5Padding";

    // 🔑 chave de 16 bytes (128 bits)
    private static final byte[] keyValue = "ThisIsASecretKey".getBytes();

    // 🔐 Criptografar
    public static String encrypt(String data) throws Exception {

        SecretKeySpec key = new SecretKeySpec(keyValue, "AES");

        Cipher cipher = Cipher.getInstance(ALGO);

        // 🔥 gerar IV aleatório
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));

        // junta IV + dados criptografados
        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    // 🔓 Descriptografar
    public static String decrypt(String encryptedData) throws Exception {

        byte[] combined = Base64.getDecoder().decode(encryptedData);

        // separa IV
        byte[] iv = new byte[16];
        byte[] encrypted = new byte[combined.length - 16];

        System.arraycopy(combined, 0, iv, 0, 16);
        System.arraycopy(combined, 16, encrypted, 0, encrypted.length);

        SecretKeySpec key = new SecretKeySpec(keyValue, "AES");

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted, "UTF-8");
    }

    // 🚀 TESTE
    public static void main(String[] args) {
        try {

            String texto = "Texto secreto AES";

            System.out.println("Original: " + texto);

            String criptografado = encrypt(texto);
            System.out.println("Criptografado: " + criptografado);

            String descriptografado = decrypt(criptografado);
            System.out.println("Decriptografado: " + descriptografado);

            if (texto.equals(descriptografado)) {
                System.out.println("\n✅ SUCESSO!");
            } else {
                System.out.println("\n❌ ERRO!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
