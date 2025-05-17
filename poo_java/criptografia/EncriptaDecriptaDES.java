import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncriptaDecriptaDES {

    public static void main(String[] argv) {

        try {
            // Cria um gerador de chaves para o algoritmo DES
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            // Gera uma chave secreta DES
            SecretKey chaveDES = keygenerator.generateKey();

            Cipher cifraDES;

            // Cria a cifra para o algoritmo DES com o modo de operação ECB e padding PKCS5
            cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Inicializa a cifra para o modo de encriptação, usando a chave secreta gerada
            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);

            // Define o texto puro a ser encriptado
            byte[] textoPuro = "Exemplo de texto puro".getBytes();

            // Imprime o texto puro em formato de byte
            System.out.println("Texto [Formato de Byte] : " + textoPuro);
            // Imprime o texto puro como uma String
            System.out.println("Texto Puro : " + new String(textoPuro));

            // Encripta o texto puro usando a cifra inicializada
            byte[] textoEncriptado = cifraDES.doFinal(textoPuro);

            // Imprime o texto encriptado (em formato de byte, geralmente ilegível)
            System.out.println("Texto Encriptado : " + textoEncriptado);

            // Reinicializa a cifra para o modo de decriptação, usando a mesma chave secreta
            cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);

            // Decriptografa o texto encriptado usando a cifra reinicializada
            byte[] textoDecriptografado = cifraDES.doFinal(textoEncriptado);

            // Imprime o texto decriptografado como uma String, que deve ser igual ao texto puro original
            System.out.println("Texto Decriptografado : " + new String(textoDecriptografado));

        } catch (NoSuchAlgorithmException e) {
            // Captura exceção se o algoritmo DES não estiver disponível no ambiente
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // Captura exceção se o padding especificado não estiver disponível
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // Captura exceção se a chave fornecida for inválida para o algoritmo
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // Captura exceção se o tamanho do bloco de dados for inválido para a operação
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // Captura exceção se o padding dos dados for inválido durante a decriptação
            e.printStackTrace();
        }

    }

}
