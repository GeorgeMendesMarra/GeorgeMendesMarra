import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BlowfishEncryption {

    private static final String ALGORITHM = "Blowfish";

    public static void main(String[] args) throws Exception {
        // Mensagem original a ser criptografada
        String originalMessage = "Esta é uma mensagem secreta usando Blowfish!";
        System.out.println("Mensagem Original: " + originalMessage);

        // Gerar uma chave secreta para o Blowfish
        // Em um cenário real, você armazenaria e gerenciaria esta chave de forma segura.
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        // Converter a chave para um formato utilizável (SecretKeySpec)
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        // Criptografar a mensagem
        String encryptedMessage = encrypt(originalMessage, keySpec);
        System.out.println("Mensagem Criptografada (Base64): " + encryptedMessage);

        // Descriptografar a mensagem
        String decryptedMessage = decrypt(encryptedMessage, keySpec);
        System.out.println("Mensagem Descriptografada: " + decryptedMessage);

        // Verificar se a mensagem original e a descriptografada são iguais
        if (originalMessage.equals(decryptedMessage)) {
            System.out.println("Criptografia e descriptografia bem-sucedidas!");
        } else {
            System.out.println("Erro: A mensagem original e a descriptografada não coincidem.");
        }
    }

    /**
     * Criptografa uma string usando o algoritmo Blowfish e uma chave fornecida.
     *
     * @param plainText A string a ser criptografada.
     * @param keySpec   A especificação da chave secreta Blowfish.
     * @return A string criptografada, codificada em Base64.
     * @throws Exception Se ocorrer algum erro durante a criptografia.
     */
    public static String encrypt(String plainText, SecretKeySpec keySpec) throws Exception {
        // Obter uma instância do Cipher para o algoritmo Blowfish
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Inicializar o Cipher para o modo de criptografia, usando a chave fornecida
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // Converter a string para bytes usando UTF-8
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);

        // Realizar a criptografia
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);

        // Codificar os bytes criptografados para Base64 para facilitar a representação e o transporte
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Descriptografa uma string criptografada (em Base64) usando o algoritmo Blowfish e uma chave fornecida.
     *
     * @param encryptedTextBase64 A string criptografada codificada em Base64.
     * @param keySpec             A especificação da chave secreta Blowfish.
     * @return A string descriptografada.
     * @throws Exception Se ocorrer algum erro durante a descriptografia.
     */
    public static String decrypt(String encryptedTextBase64, SecretKeySpec keySpec) throws Exception {
        // Obter uma instância do Cipher para o algoritmo Blowfish
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Inicializar o Cipher para o modo de descriptografia, usando a chave fornecida
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // Decodificar a string Base64 para obter os bytes criptografados
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedTextBase4);

        // Realizar a descriptografia
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Converter os bytes descriptografados de volta para uma string usando UTF-8
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
