import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class ElGamal {

    private BigInteger p; // Um grande número primo
    private BigInteger g; // Um gerador do grupo multiplicativo módulo p
    private BigInteger privateKey; // Chave privada (x)
    private BigInteger publicKey;  // Chave pública (h = g^x mod p)
    private Random rand;

    public ElGamal(int bitLength) {
        rand = new SecureRandom();
        // 1. Escolher um grande número primo p
        p = BigInteger.probablePrime(bitLength, rand);
        // 2. Escolher um gerador g do grupo multiplicativo módulo p
        // Um gerador g é um número tal que suas potências módulo p geram todos os números de 1 a p-1.
        // Encontrar um gerador pode ser um processo complexo. Para simplificar, vamos escolher um pequeno primo como gerador.
        // Em uma implementação real, uma escolha mais robusta seria necessária.
        g = findGenerator();
        // 3. Escolher um inteiro aleatório x para a chave privada (1 < x < p-1)
        privateKey = new BigInteger(bitLength - 1, rand);
        // 4. Calcular a chave pública h = g^x mod p
        publicKey = g.modPow(privateKey, p);
    }

    // Método para encontrar um gerador (simplificado e não totalmente robusto)
    private BigInteger findGenerator() {
        BigInteger a = new BigInteger("2"); // Tenta com um pequeno primo
        while (true) {
            if (isGenerator(a)) {
                return a;
            }
            a = a.add(BigInteger.ONE);
            if (a.compareTo(p) >= 0) {
                // Isso não deve acontecer com um primo grande o suficiente e uma boa escolha inicial.
                // Em uma implementação real, uma busca mais sofisticada seria necessária.
                throw new IllegalStateException("Não foi possível encontrar um gerador.");
            }
        }
    }

    // Método para verificar se 'a' é um gerador (simplificado)
    private boolean isGenerator(BigInteger a) {
        BigInteger phi = p.subtract(BigInteger.ONE);
        BigInteger[] primeFactors = getPrimeFactors(phi);
        for (BigInteger factor : primeFactors) {
            if (a.modPow(phi.divide(factor), p).equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }

    // Método para obter os fatores primos de um número (simplificado)
    private BigInteger[] getPrimeFactors(BigInteger n) {
        // Esta é uma implementação muito simplificada e ineficiente para fins de demonstração.
        // Em uma implementação real, um algoritmo de fatoração mais eficiente seria necessário.
        java.util.List<BigInteger> factors = new java.util.ArrayList<>();
        BigInteger d = new BigInteger("2");
        BigInteger temp = n;
        while (d.multiply(d).compareTo(temp) <= 0) {
            if (temp.mod(d).equals(BigInteger.ZERO)) {
                factors.add(d);
                while (temp.mod(d).equals(BigInteger.ZERO)) {
                    temp = temp.divide(d);
                }
            }
            d = d.add(BigInteger.ONE);
        }
        if (temp.compareTo(BigInteger.ONE) > 0) {
            factors.add(temp);
        }
        return factors.toArray(new BigInteger[0]);
    }

    // Método para criptografar uma mensagem
    public Ciphertext encrypt(BigInteger message) {
        // 1. Escolher um inteiro aleatório k (chave efêmera), onde 1 < k < p-1
        BigInteger k = new BigInteger(p.bitLength() - 1, rand);
        // 2. Calcular c1 = g^k mod p
        BigInteger c1 = g.modPow(k, p);
        // 3. Calcular c2 = (message * h^k) mod p, onde h é a chave pública
        BigInteger c2 = message.multiply(publicKey.modPow(k, p)).mod(p);
        return new Ciphertext(c1, c2);
    }

    // Método para descriptografar um texto cifrado
    public BigInteger decrypt(Ciphertext ciphertext) {
        BigInteger c1 = ciphertext.getC1();
        BigInteger c2 = ciphertext.getC2();
        // 1. Calcular s = c1^x mod p, onde x é a chave privada
        BigInteger s = c1.modPow(privateKey, p);
        // 2. Calcular o inverso multiplicativo de s módulo p (s^-1)
        BigInteger sInverse = s.modInverse(p);
        // 3. Calcular a mensagem original: message = (c2 * s^-1) mod p
        BigInteger message = c2.multiply(sInverse).mod(p);
        return message;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public static class Ciphertext {
        private BigInteger c1;
        private BigInteger c2;

        public Ciphertext(BigInteger c1, BigInteger c2) {
            this.c1 = c1;
            this.c2 = c2;
        }

        public BigInteger getC1() {
            return c1;
        }

        public BigInteger getC2() {
            return c2;
        }
    }

    public static void main(String[] args) {
        // Tamanho da chave em bits (quanto maior, mais seguro, mas mais lento)
        int keySize = 1024;
        ElGamal elGamal = new ElGamal(keySize);

        BigInteger publicKey = elGamal.getPublicKey();
        BigInteger p = elGamal.getP();
        BigInteger g = elGamal.getG();

        System.out.println("Chave Pública (h): " + publicKey);
        System.out.println("Primo (p): " + p);
        System.out.println("Gerador (g): " + g);

        // Mensagem a ser criptografada (deve ser menor que p)
        BigInteger message = new BigInteger("123456789012345");
        System.out.println("Mensagem Original: " + message);

        // Criptografar a mensagem
        Ciphertext ciphertext = elGamal.encrypt(message);
        System.out.println("Texto Cifrado (c1, c2): (" + ciphertext.getC1() + ", " + ciphertext.getC2() + ")");

        // Descriptografar a mensagem
        BigInteger decryptedMessage = elGamal.decrypt(ciphertext);
        System.out.println("Mensagem Decriptografada: " + decryptedMessage);

        // Verificar se a mensagem original é igual à mensagem descriptografada
        if (message.equals(decryptedMessage)) {
            System.out.println("Criptografia e descriptografia bem-sucedidas!");
        } else {
            System.out.println("Erro na criptografia/descriptografia.");
        }
    }
}
