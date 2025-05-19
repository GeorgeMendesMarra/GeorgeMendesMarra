import java.util.Arrays; // Importa a classe Arrays, que fornece métodos utilitários para trabalhar com arrays, como imprimir o conteúdo de um array.
import java.util.Random; // Importa a classe Random, que é usada para gerar sequências de números aleatórios.

public class ExemploOTP {

    /**
     * Encripta um array de bytes (texto plano) usando a cifra One-Time Pad (OTP) com uma chave fornecida.
     *
     * @param plaintext O array de bytes representando o texto a ser encriptado.
     * @param key       O array de bytes representando a chave OTP.
     * @return Um novo array de bytes representando o texto cifrado.
     * @throws IllegalArgumentException Se o tamanho da chave não for igual ao tamanho do texto plano,
     * uma vez que o OTP requer uma chave de mesmo comprimento para segurança.
     */
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        // **Verificação da Condição Essencial do OTP:**
        // Para que o OTP seja teoricamente seguro, a chave deve ter exatamente o mesmo comprimento que o texto plano.
        if (plaintext.length != key.length) {
            throw new IllegalArgumentException("O tamanho da chave deve ser igual ao tamanho do texto plano para OTP.");
        }

        // Cria um novo array de bytes para armazenar o texto cifrado, com o mesmo tamanho do texto plano.
        byte[] ciphertext = new byte[plaintext.length];

        // **Operação Central do OTP: XOR Bit a Bit**
        // Itera sobre cada byte do texto plano e realiza uma operação XOR (ou exclusivo) com o byte correspondente da chave.
        // A operação XOR tem a propriedade de que (A XOR B) XOR B = A, o que permite a decriptação com a mesma chave.
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ key[i]);
        }

        // Retorna o array de bytes resultante, que é o texto cifrado.
        return ciphertext;
    }

    /**
     * Decripta um array de bytes (texto cifrado) usando a cifra One-Time Pad (OTP) com a mesma chave usada para encriptar.
     *
     * @param ciphertext O array de bytes representando o texto a ser decriptado.
     * @param key        O array de bytes representando a chave OTP (a mesma usada para encriptação).
     * @return Um novo array de bytes representando o texto plano original.
     */
    public static byte[] decrypt(byte[] ciphertext, byte[] key) {
        // **Simetria da Decriptação no OTP:**
        // A decriptação no OTP é idêntica à encriptação: aplicar a operação XOR com a mesma chave.
        // Isso ocorre devido à propriedade da operação XOR mencionada anteriormente.
        return encrypt(ciphertext, key);
    }

    /**
     * Gera uma chave aleatória (array de bytes) de um determinado comprimento.
     *
     * @param length O comprimento desejado da chave em bytes.
     * @return Um novo array de bytes contendo valores aleatórios, representando a chave OTP.
     */
    public static byte[] generateRandomKey(int length) {
        // Cria uma instância do gerador de números aleatórios.
        // Por padrão, a classe Random não é criptograficamente segura. Para aplicações de segurança reais,
        // considere usar SecureRandom. No entanto, para este exemplo ilustrativo, Random é suficiente.
        Random random = new Random();

        // Cria um array de bytes com o tamanho especificado para armazenar a chave.
        byte[] key = new byte[length];

        // Preenche o array de bytes da chave com valores aleatórios gerados pelo objeto Random.
        random.nextBytes(key);

        // Retorna o array de bytes que representa a chave aleatória.
        return key;
    }

    public static void main(String[] args) {
    
        System.out.println();
        System.out.println("Exemplo de programa em Java que utiliza o algoritmo de criptografia OTP");
        System.out.println();
        // Define o texto plano (a mensagem original) como uma String.
        String plaintext = "Mensagem secreta OTP";

        // Converte a String do texto plano para um array de bytes, pois a criptografia OTP opera em bytes.
        byte[] plaintextBytes = plaintext.getBytes();

        // **Geração da Chave OTP:**
        // Gera uma chave aleatória com o mesmo comprimento do texto plano em bytes.
        byte[] key = generateRandomKey(plaintextBytes.length);

        // Imprime o texto plano original para referência.
        System.out.println("Texto Plano: " + plaintext);

        // Imprime a chave gerada em formato de array de bytes. É importante notar que esta chave deve ser mantida em segredo.
        System.out.println("Chave (Bytes): " + Arrays.toString(key));

        // **Encriptação:**
        // Chama o método encrypt para encriptar o texto plano usando a chave gerada.
        byte[] ciphertextBytes = encrypt(plaintextBytes, key);

        // Imprime o texto cifrado resultante em formato de array de bytes. Este é o dado codificado.
        System.out.println("Texto Cifrado (Bytes): " + Arrays.toString(ciphertextBytes));

        // **Decriptação:**
        // Chama o método decrypt para decriptar o texto cifrado usando a mesma chave que foi usada para encriptar.
        byte[] decryptedBytes = decrypt(ciphertextBytes, key);

        // Converte o array de bytes decriptado de volta para uma String para visualizar o resultado.
        String decryptedText = new String(decryptedBytes);

        // Imprime o texto decriptografado, que deve ser idêntico ao texto plano original se a chave correta for usada.
        System.out.println("Texto Decriptografado: " + decryptedText);

        // **Demonstração da Segurança do OTP:**
        // Para ilustrar a segurança do OTP, tenta-se decriptar o texto cifrado com uma chave diferente (gerada aleatoriamente).
        byte[] wrongKey = generateRandomKey(plaintextBytes.length);
        byte[] wrongDecryptedBytes = decrypt(ciphertextBytes, wrongKey);
        String wrongDecryptedText = new String(wrongDecryptedBytes);

        // O resultado da decriptação com uma chave incorreta deve ser um texto ilegível e sem sentido, demonstrando a dependência da chave correta para a recuperação da mensagem original.
        System.out.println("Texto Decriptografado com Chave Errada: " + wrongDecryptedText);
    }
}
