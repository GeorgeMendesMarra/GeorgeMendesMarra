// Importa a classe InvalidKeyException do pacote java.security, que sinaliza uma chave inválida (formato incorreto, etc.).
import java.security.InvalidKeyException;
// Importa a classe NoSuchAlgorithmException do pacote java.security, que sinaliza que um algoritmo de criptografia solicitado não está disponível no ambiente.
import java.security.NoSuchAlgorithmException;
// Importa a classe BadPaddingException do pacote javax.crypto, que sinaliza que o padding dos dados de entrada é impróprio.
import javax.crypto.BadPaddingException;
// Importa a classe Cipher do pacote javax.crypto, que fornece a funcionalidade de criptografia e descriptografia.
import javax.crypto.Cipher;
// Importa a classe IllegalBlockSizeException do pacote javax.crypto, que sinaliza que o tamanho do bloco de dados não é apropriado para a operação de criptografia.
import javax.crypto.IllegalBlockSizeException;
// Importa a classe KeyGenerator do pacote javax.crypto, que é usada para gerar chaves secretas para algoritmos simétricos.
import javax.crypto.KeyGenerator;
// Importa a classe NoSuchPaddingException do pacote javax.crypto, que sinaliza que um esquema de padding solicitado não está disponível.
import javax.crypto.NoSuchPaddingException;
// Importa a interface SecretKey do pacote javax.crypto, que representa uma chave secreta (usada em algoritmos simétricos).
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
