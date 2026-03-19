import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Exemplo2DES {

    public static void main(String[] args) {
        try {

            System.out.println("\n=== 2DES ===\n");

            KeyGenerator keyGen = KeyGenerator.getInstance("DES");

            SecretKey key1 = keyGen.generateKey();
            SecretKey key2 = keyGen.generateKey();

            String mensagem = "Mensagem secreta usando 2DES";
            System.out.println("Original: " + mensagem);

            byte[] encrypted = encrypt(mensagem.getBytes(), key1, key2);

            String base64 = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("Criptografado: " + base64);

            byte[] decrypted = decrypt(encrypted, key1, key2);

            String resultado = new String(decrypted);
            System.out.println("Decriptado: " + resultado);

            if (mensagem.equals(resultado)) {
                System.out.println("\n✅ SUCESSO!");
            } else {
                System.out.println("\n❌ ERRO!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] data, SecretKey key1, SecretKey key2) throws Exception {

        Cipher cipher1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher1.init(Cipher.ENCRYPT_MODE, key1);
        byte[] step1 = cipher1.doFinal(data);

        Cipher cipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher2.init(Cipher.ENCRYPT_MODE, key2);
        return cipher2.doFinal(step1);
    }

    public static byte[] decrypt(byte[] data, SecretKey key1, SecretKey key2) throws Exception {

        Cipher cipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher2.init(Cipher.DECRYPT_MODE, key2);
        byte[] step1 = cipher2.doFinal(data);

        Cipher cipher1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher1.init(Cipher.DECRYPT_MODE, key1);
        return cipher1.doFinal(step1);
    }
}
