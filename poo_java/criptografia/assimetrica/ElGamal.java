import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {

    private BigInteger p; // primo
    private BigInteger g; // gerador
    private BigInteger privateKey; // x
    private BigInteger publicKey;  // h = g^x mod p
    private SecureRandom rand;

    public ElGamal(int bitLength) {
        rand = new SecureRandom();

        // 1. Primo grande
        p = BigInteger.probablePrime(bitLength, rand);

        // 2. Gerador simples (didático e funcional)
        g = BigInteger.valueOf(2);

        // 3. Chave privada (2 <= x <= p-2)
        privateKey = generateRandomInRange(BigInteger.TWO, p.subtract(BigInteger.TWO));

        // 4. Chave pública
        publicKey = g.modPow(privateKey, p);
    }

    // Gera número aleatório dentro de um intervalo
    private BigInteger generateRandomInRange(BigInteger min, BigInteger max) {
        BigInteger result;
        do {
            result = new BigInteger(max.bitLength(), rand);
        } while (result.compareTo(min) < 0 || result.compareTo(max) > 0);
        return result;
    }

    // Criptografia
    public Ciphertext encrypt(BigInteger message) {

        if (message.compareTo(p) >= 0) {
            throw new IllegalArgumentException("Mensagem deve ser menor que p");
        }

        // k aleatório
        BigInteger k = generateRandomInRange(BigInteger.TWO, p.subtract(BigInteger.TWO));

        BigInteger c1 = g.modPow(k, p);
        BigInteger c2 = message.multiply(publicKey.modPow(k, p)).mod(p);

        return new Ciphertext(c1, c2);
    }

    // Descriptografia
    public BigInteger decrypt(Ciphertext ciphertext) {
        BigInteger c1 = ciphertext.getC1();
        BigInteger c2 = ciphertext.getC2();

        BigInteger s = c1.modPow(privateKey, p);
        BigInteger sInverse = s.modInverse(p);

        return c2.multiply(sInverse).mod(p);
    }

    public BigInteger getPublicKey() { return publicKey; }
    public BigInteger getP() { return p; }
    public BigInteger getG() { return g; }

    // Classe do texto cifrado
    public static class Ciphertext {
        private BigInteger c1;
        private BigInteger c2;

        public Ciphertext(BigInteger c1, BigInteger c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        public BigInteger getC1() { return c1; }
        public BigInteger getC2() { return c2; }
    }

    // TESTE
    public static void main(String[] args) {

        int keySize = 512; // mais rápido para teste

        ElGamal elGamal = new ElGamal(keySize);

        System.out.println("=== PARÂMETROS ===");
        System.out.println("p: " + elGamal.getP());
        System.out.println("g: " + elGamal.getG());
        System.out.println("h (chave pública): " + elGamal.getPublicKey());

        BigInteger message = new BigInteger("123456789");

        System.out.println("\nMensagem original: " + message);

        Ciphertext cipher = elGamal.encrypt(message);

        System.out.println("\n=== CIFRADO ===");
        System.out.println("c1: " + cipher.getC1());
        System.out.println("c2: " + cipher.getC2());

        BigInteger decrypted = elGamal.decrypt(cipher);

        System.out.println("\nMensagem decriptada: " + decrypted);

        if (message.equals(decrypted)) {
            System.out.println("\n✅ SUCESSO!");
        } else {
            System.out.println("\n❌ ERRO!");
        }
    }
}
