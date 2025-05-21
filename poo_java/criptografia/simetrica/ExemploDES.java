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

public class ExemploDES {
    public static void main(String[] args) {
        try {
            System.out.println();
            System.out.println("Exemplo de programa em Java que utiliza o algoritmo de criptografia DES");
            System.out.println();
            // Cria um gerador de chaves para o algoritmo DES (Data Encryption Standard).
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            // Gera uma chave secreta para ser usada com o algoritmo DES.
            SecretKey chaveDES = keyGenerator.generateKey();
            // Obtém uma instância do objeto Cipher para o algoritmo DES, usando o modo de operação ECB (Electronic Codebook)
            // e o esquema de padding PKCS5Padding.
            Cipher cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // Inicializa a cifra para o modo de encriptação (codificação), utilizando a chave DES gerada.
            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);
            // Define o texto simples (não criptografado) que será convertido para bytes.
            byte[] textoPuro = "Texto de exemplo".getBytes();
            // Executa a operação de encriptação no texto puro, resultando no texto criptografado (em formato de bytes).
            byte[] textoEncriptado = cifraDES.doFinal(textoPuro);
            // Imprime o texto encriptado (a representação em String pode não ser legível).
            System.out.println("Texto Encriptado: " + new String(textoEncriptado));
            // Reinicializa a cifra para o modo de decriptação (descodificação), utilizando a mesma chave DES.
            cifraDES.init(Cipher.DECRYPT_MODE, chaveDES);
            // Executa a operação de decriptação no texto encriptado, revertendo-o para o texto original (em formato de bytes).
            byte[] textoDecriptografado = cifraDES.doFinal(textoEncriptado);
            // Imprime o texto decriptografado, convertendo os bytes de volta para uma String.
            System.out.println("Texto Decriptografado: " + new String(textoDecriptografado));
        } catch (NoSuchAlgorithmException e) {
            // Captura a exceção que ocorre se o algoritmo "DES" não estiver disponível no ambiente Java.
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // Captura a exceção que ocorre se o esquema de padding "PKCS5Padding" não estiver disponível.
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // Captura a exceção que ocorre se a chave fornecida para inicializar a cifra for inválida.
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // Captura a exceção que ocorre se o tamanho do bloco de dados não for adequado para a operação de criptografia.
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // Captura a exceção que ocorre se o padding dos dados encriptados for inválido durante a decriptação.
            e.printStackTrace();
        }
    }
}
