import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

// Classe do Bloco
class Bloco {
    public String hash;
    public String hashAnterior;
    public String dados;
    public long tempo;
    public int nonce;

    public Bloco(String dados, String hashAnterior) {
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.tempo = new Date().getTime();
        this.hash = calcularHash();
    }

    // Gerar hash com SHA-256
    public String calcularHash() {
        try {
            String entrada = hashAnterior + tempo + nonce + dados;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(entrada.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                String hexChar = Integer.toHexString(0xff & b);
                if (hexChar.length() == 1) hex.append('0');
                hex.append(hexChar);
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Mineração (prova de trabalho)
    public void minerarBloco(int dificuldade) {
        String alvo = new String(new char[dificuldade]).replace('\0', '0');

        while (!hash.substring(0, dificuldade).equals(alvo)) {
            nonce++;
            hash = calcularHash();
        }

        System.out.println("Bloco minerado: " + hash);
    }
}

// Classe principal
public class BlockchainSimples {

    public static ArrayList<Bloco> blockchain = new ArrayList<>();
    public static int dificuldade = 4;

    public static void main(String[] args) {

        System.out.println("🚀 Iniciando blockchain...\n");

        // Bloco Gênesis
        Bloco bloco1 = new Bloco("Alice enviou 10 moedas para Bob", "0");
        bloco1.minerarBloco(dificuldade);
        blockchain.add(bloco1);

        // Segundo bloco
        Bloco bloco2 = new Bloco("Bob enviou 5 moedas para Carol", bloco1.hash);
        bloco2.minerarBloco(dificuldade);
        blockchain.add(bloco2);

        // Terceiro bloco
        Bloco bloco3 = new Bloco("Carol enviou 2 moedas para Dave", bloco2.hash);
        bloco3.minerarBloco(dificuldade);
        blockchain.add(bloco3);

        System.out.println("\n🔗 Blockchain criada!");
        for (Bloco b : blockchain) {
            System.out.println("\nHash: " + b.hash);
            System.out.println("Hash Anterior: " + b.hashAnterior);
            System.out.println("Dados: " + b.dados);
        }
    }
}
