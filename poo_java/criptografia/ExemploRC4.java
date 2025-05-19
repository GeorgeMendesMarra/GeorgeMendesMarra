import javax.crypto.Cipher; // Importa a classe Cipher, que fornece a funcionalidade principal para criptografia e descriptografia.
import javax.crypto.SecretKey; // Importa a interface SecretKey, que representa uma chave secreta usada em algoritmos simétricos como o RC4.
import javax.crypto.spec.SecretKeySpec; // Importa a classe SecretKeySpec, uma implementação concreta de SecretKey, usada para construir uma chave a partir de um array de bytes.
import java.util.Base64; // Importa a classe Base64, utilizada para codificar dados binários (como o texto cifrado) em uma representação de texto ASCII e vice-versa.
import java.util.Arrays; // Importa a classe Arrays, que oferece métodos utilitários para trabalhar com arrays, como imprimir seu conteúdo para depuração (não usado diretamente na lógica principal aqui).

public class ExemploRC4 {

    private static final String ALGORITHM = "RC4"; // Define uma constante estática e final para o nome do algoritmo de criptografia que será utilizado, que é "RC4". Isso torna o código mais legível e fácil de manter.

    /**
     * Encripta uma String de texto plano usando o algoritmo de criptografia RC4 com uma chave fornecida como String.
     *
     * @param plainText A String que representa os dados originais a serem encriptados (codificados).
     * @param key       A chave secreta usada para a encriptação. É crucial que a mesma chave seja usada para a decriptação.
     * @return Uma String representando os dados encriptados. O resultado é codificado em Base64 para facilitar a manipulação e transmissão como texto.
     * @throws Exception Se ocorrer algum erro durante o processo de encriptação, como um algoritmo não suportado ou uma chave inválida.
     */
    public static String encrypt(String plainText, String key) throws Exception {
        // **Criação da Chave Secreta (SecretKey):**
        // A classe SecretKeySpec é usada para criar uma SecretKey a partir de um array de bytes.
        // Aqui, convertemos a String da chave para um array de bytes usando a codificação padrão da plataforma.
        // O segundo argumento especifica o algoritmo associado a esta chave, que é "RC4".
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

        // **Obtenção da Instância do Cipher:**
        // A classe Cipher fornece a funcionalidade para encriptação e decriptação.
        // Chamamos getInstance() com o nome do algoritmo desejado ("RC4") para obter uma instância do Cipher para esse algoritmo.
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // **Inicialização do Cipher para o Modo de Encriptação:**
        // O método init() inicializa o Cipher para uma determinada operação.
        // Aqui, usamos Cipher.ENCRYPT_MODE para indicar que queremos encriptar dados e fornecemos a chave secreta que será usada.
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // **Conversão do Texto Plano para Bytes:**
        // Antes de encriptar, o texto plano (String) precisa ser convertido para um array de bytes.
        // Usamos a codificação UTF-8, que é uma codificação de caracteres amplamente utilizada e recomendada.
        byte[] plainTextBytes = plainText.getBytes("UTF-8");

        // **Execução da Encriptação:**
        // O método doFinal() processa os bytes do texto plano e retorna um novo array de bytes contendo os dados encriptados (texto cifrado).
        byte[] cipherBytes = cipher.doFinal(plainTextBytes);

        // **Codificação do Texto Cifrado para Base64:**
        // Os bytes do texto cifrado geralmente contêm caracteres não imprimíveis, o que pode dificultar a manipulação e transmissão como texto.
        // A codificação Base64 converte esses bytes em uma String usando um conjunto limitado de caracteres ASCII, tornando-o seguro para transmissão em canais de texto.
        return Base64.getEncoder().encodeToString(cipherBytes);
    }

    /**
     * Decripta uma String que foi previamente encriptada usando o algoritmo RC4 e codificada em Base64, utilizando a mesma chave usada para encriptar.
     *
     * @param cipherTextBase64 A String que representa os dados encriptados, codificados em Base64.
     * @param key              A chave secreta usada para a decriptação. Deve ser idêntica à chave usada para a encriptação para recuperar os dados originais.
     * @return Uma String representando os dados originais (texto plano) após a decriptação.
     * @throws Exception Se ocorrer algum erro durante o processo de decriptação, como uma chave incorreta ou dados corrompidos.
     */
    public static String decrypt(String cipherTextBase64, String key) throws Exception {
        // **Criação da Chave Secreta (SecretKey) para Decriptação:**
        // Criamos um SecretKeySpec da mesma forma que na encriptação, usando a mesma chave e algoritmo.
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

        // **Obtenção da Instância do Cipher para Decriptação:**
        // Obtemos outra instância do Cipher para o algoritmo RC4.
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // **Inicialização do Cipher para o Modo de Decriptação:**
        // Inicializamos o Cipher para a operação de decriptação (Cipher.DECRYPT_MODE), fornecendo a mesma chave secreta usada para encriptar.
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // **Decodificação da String Base64 para Bytes do Texto Cifrado:**
        // Antes de decriptar, precisamos converter a String Base64 de volta para o array de bytes original do texto cifrado.
        byte[] cipherBytes = Base64.getDecoder().decode(cipherTextBase64);

        // **Execução da Decriptação:**
        // O método doFinal() processa os bytes do texto cifrado e retorna um novo array de bytes contendo os dados decriptados (texto plano original).
        byte[] plainTextBytes = cipher.doFinal(cipherBytes);

        // **Conversão dos Bytes Decriptados para String:**
        // Finalmente, convertemos o array de bytes do texto plano de volta para uma String usando a mesma codificação (UTF-8) usada na encriptação.
        return new String(plainTextBytes, "UTF-8");
    }

    public static void main(String[] args) {
    
        System.out.println();
        System.out.println("Exemplo de programa em Java que utiliza o algoritmo de criptografia RC4");
        System.out.println();
        // Define a mensagem secreta que queremos encriptar e decriptar.
        String secretMessage = "Esta é uma mensagem secreta usando RC4.";
        // Define a chave secreta que será usada para encriptar e decriptar a mensagem.
        // **Importante:** Em aplicações reais, a chave deve ser gerada de forma segura e mantida em segredo. Chaves hardcoded como esta são apenas para demonstração.
        String encryptionKey = "chave_secreta_rc4";

        try {
            // Imprime o texto plano original para referência.
            System.out.println("Texto Plano: " + secretMessage);

            // Chama o método encrypt para encriptar a mensagem usando a chave definida.
            String encryptedMessage = encrypt(secretMessage, encryptionKey);
            // Imprime o texto encriptado, que está em formato Base64.
            System.out.println("Texto Encriptado (Base64): " + encryptedMessage);

            // Chama o método decrypt para decriptar a mensagem encriptada usando a mesma chave.
            String decryptedMessage = decrypt(encryptedMessage, encryptionKey);
            // Imprime o texto decriptografado, que deve ser idêntico ao texto plano original.
            System.out.println("Texto Decriptografado: " + decryptedMessage);

            // **Demonstração da Importância da Chave Correta:**
            // Tenta decriptar a mensagem encriptada usando uma chave diferente (incorreta).
            String wrongKey = "chave_errada";
            try {
                String wrongDecryptedMessage = decrypt(encryptedMessage, wrongKey);
                // Se a decriptação com a chave errada não lançar uma exceção, o resultado provavelmente será um texto ilegível e sem sentido.
                System.out.println("\nTexto Decriptografado com Chave Errada: " + wrongDecryptedMessage);
            } catch (Exception e) {
                // Se a decriptação com a chave errada lançar uma exceção (o que é comum em sistemas de criptografia robustos), capturamos e exibimos a mensagem de erro.
                System.out.println("\nErro ao decriptar com chave errada: " + e.getMessage());
            }

        } catch (Exception e) {
            // Captura qualquer exceção que possa ocorrer durante os processos de encriptação ou decriptação e imprime uma mensagem de erro e a stack trace para depuração.
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
