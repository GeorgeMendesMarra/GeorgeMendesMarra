import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.util.Base64;

public class ExemploDES {
    public static void main(String[] args) {
        try {
            System.out.println("\nExemplo DES\n");

            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey chaveDES = keyGenerator.generateKey();

            Cipher cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);

            byte[] textoPuro = "Texto de exemplo".getBytes();

            byte[] textoEncriptado = cifraDES.doFinal(textoPuro);

            // ✔ Correto
            String textoBase64 = Base64.getEncoder().encodeToString(textoEncriptado);
            System.out.println("Texto Encriptado: " + textoBase64);

            cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);

            byte[] textoDecriptografado = cifraDES.doFinal(textoEncriptado);

            System.out.println("Texto Decriptografado: " + new String(textoDecriptografado));

            if (new String(textoPuro).equals(new String(textoDecriptografado))) {
                System.out.println("\n✅ SUCESSO!");
            } else {
                System.out.println("\n❌ ERRO!");
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro: Algoritmo DES não disponível");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("Erro: Padding não suportado");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Erro: Chave inválida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Erro: Bloco inválido");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Erro: Padding inválido");
            e.printStackTrace();
        }
    }
}
