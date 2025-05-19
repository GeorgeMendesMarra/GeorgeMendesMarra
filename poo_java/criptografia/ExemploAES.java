import java.security.Key; // Importa a interface Key, que representa uma chave criptográfica.
import javax.crypto.Cipher; // Importa a classe Cipher, que fornece a funcionalidade de criptografia e descriptografia.
import javax.crypto.spec.SecretKeySpec; // Importa a classe SecretKeySpec, usada para criar chaves secretas (simétricas) a partir de um array de bytes.
import java.util.Base64; // Importa a classe Base64, utilizada para codificar e decodificar dados em formato Base64.

public class ExemploAES {

    // Define uma constante para o nome do algoritmo de criptografia a ser utilizado (AES - Advanced Encryption Standard).
    private static final String ALGO = "AES";
    // Define uma constante para o array de bytes que representa a chave secreta.
    // **Importante:** Em aplicações reais, esta chave não deve ser hardcoded e deve ser gerenciada de forma segura.
    private static final byte[] keyValue = new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    // Método para encriptar (codificar) uma String de dados.
    public static String encrypt(String Data) throws Exception {
        // Gera uma chave secreta a partir do array de bytes 'keyValue' e do algoritmo 'ALGO'.
        Key key = generateKey();
        // Obtém uma instância do objeto Cipher para o algoritmo especificado em 'ALGO' (AES).
        Cipher c = Cipher.getInstance(ALGO);
        // Inicializa a cifra para o modo de encriptação, utilizando a chave gerada.
        c.init(Cipher.ENCRYPT_MODE, key);
        // Converte a String de entrada 'Data' para um array de bytes e executa a operação de encriptação.
        byte[] encVal = c.doFinal(Data.getBytes());
        // Codifica o array de bytes encriptado para uma String em formato Base64 para facilitar a transmissão e armazenamento.
        return Base64.getEncoder().encodeToString(encVal);
    }

    // Método para decriptar (descodificar) uma String de dados encriptada (em formato Base64).
    public static String decrypt(String encryptedData) throws Exception {
        // Gera uma chave secreta a partir do array de bytes 'keyValue' e do algoritmo 'ALGO'.
        Key key = generateKey();
        // Obtém uma instância do objeto Cipher para o algoritmo especificado em 'ALGO' (AES).
        Cipher c = Cipher.getInstance(ALGO);
        // Inicializa a cifra para o modo de decriptação, utilizando a mesma chave usada para encriptar.
        c.init(Cipher.DECRYPT_MODE, key);
        // Decodifica a String encriptada em formato Base64 de volta para um array de bytes.
        byte[] decVal = Base64.getDecoder().decode(encryptedData);
        // Executa a operação de decriptação no array de bytes decodificado, obtendo o texto original (em bytes).
        byte[] decryptedValue = c.doFinal(decVal);
        // Converte o array de bytes decriptado de volta para uma String.
        return new String(decryptedValue);
    }

    // Método privado para gerar uma chave secreta (do tipo Key) a partir do array de bytes 'keyValue' e do algoritmo 'ALGO'.
    private static Key generateKey() throws Exception {
        // Cria um objeto SecretKeySpec, que é uma implementação da interface Key para chaves secretas (simétricas).
        // O construtor recebe o array de bytes da chave e o nome do algoritmo.
        return new SecretKeySpec(keyValue, ALGO);
    }

    // Método principal (ponto de entrada) da aplicação.
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("Exemplo de programa em Java que utiliza o algoritmo de criptografia AES");
        System.out.println();
        // Define o texto simples (não criptografado) a ser encriptado.
        String textoPuro = "Texto secreto AES";
        // Chama o método 'encrypt' para encriptar o texto puro.
        String textoEncriptado = ExemploAES.encrypt(textoPuro);
        // Chama o método 'decrypt' para decriptar o texto encriptado.
        String textoDecriptografado = ExemploAES.decrypt(textoEncriptado);
        // Imprime o texto encriptado (em formato Base64).
        System.out.println("Texto Encriptado: " + textoEncriptado);
        // Imprime o texto decriptografado (de volta ao texto original).
        System.out.println("Texto Decriptografado: " + textoDecriptografado);
    }
}
