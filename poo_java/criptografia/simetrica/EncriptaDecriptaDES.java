import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import java.util.Base64;

public class EncriptaDecriptaDES {

    public static void main(String[] argv) {

        try {

            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey chaveDES = keygenerator.generateKey();

            Cipher cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);

            byte[] textoPuro = "Exemplo de texto puro".getBytes();

            System.out.println("Texto Puro: " + new String(textoPuro));

            byte[] textoEncriptado = cifraDES.doFinal(textoPuro);

            // ✔ Mostrar corretamente
            String textoBase64 = Base64.getEncoder().encodeToString(textoEncriptado);
            System.out.println("Texto Encriptado (Base64): " + textoBase64);

            cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);

            byte[] textoDecriptografado = cifraDES.doFinal(textoEncriptado);

            System.out.println("Texto Decriptografado: " + new String(textoDecriptografado));

            if (new String(textoPuro).equals(new String(textoDecriptografado))) {
                System.out.println("\n✅ SUCESSO!");
            } else {
                System.out.println("\n❌ ERRO!");
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro: Algoritmo não encontrado");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.out.println("Erro: Padding não suportado");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Erro: Chave inválida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.out.println("Erro: Tamanho de bloco inválido");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.out.println("Erro: Padding inválido");
            e.printStackTrace();
        }
    }
}
