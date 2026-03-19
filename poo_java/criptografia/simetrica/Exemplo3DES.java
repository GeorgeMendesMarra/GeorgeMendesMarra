import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Exemplo3DES {

    public static void main(String[] args) {
        try {

            System.out.println("\n=== 3DES ===\n");

            // 🔑 Gerar chave 3DES
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede"); // nome do 3DES
            keyGen.init(168); // tamanho da chave
            SecretKey key = keyGen.generateKey();

            String mensagem = "Mensagem secreta usando 3DES";
            System.out.println("Original: " + mensagem);

            // 🔐 Criptografar
            byte[] encrypted = encrypt(mensagem.getBytes(), key);

            String base64 = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("Criptografado: " + base64);

            // 🔓 Descriptografar
            byte[] decrypted = decrypt(encrypted, key);

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

    // 🔐 ENCRYPT
    public static byte[] encrypt(byte[] data, SecretKey key) throws Exception {

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    // 🔓 DECRYPT
    public static byte[] decrypt(byte[] data, SecretKey key) throws Exception {

        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }
}
