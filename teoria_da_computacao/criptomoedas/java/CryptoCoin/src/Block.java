import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;                     // Hash do bloco atual
    public String previousHash;             // Hash do bloco anterior
    public String merkleRoot;               // Raiz da árvore de Merkle
    public ArrayList<Transaction> transactions; // Transações do bloco
    public long timestamp;                  // Data/hora
    public int nonce;                       // Número usado no proof-of-work
    
    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.transactions = new ArrayList<>();
        this.hash = calculateHash();
    }
    
    // Calcula o hash do bloco
    public String calculateHash() {
        return StringUtil.applySha256(
            previousHash +
            Long.toString(timestamp) +
            Integer.toString(nonce) +
            merkleRoot
        );
    }
    
    // Minera o bloco (proof-of-work)
    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = new String(new char[difficulty]).replace('\0', '0');
        
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Bloco minerado! Hash: " + hash);
    }
    
    // Adiciona transação ao bloco
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        
        // Verifica se é o bloco gênese
        if (!previousHash.equals("0")) {
            if (!transaction.processTransaction()) {
                System.out.println("Transação inválida!");
                return false;
            }
        }
        transactions.add(transaction);
        return true;
    }
}
