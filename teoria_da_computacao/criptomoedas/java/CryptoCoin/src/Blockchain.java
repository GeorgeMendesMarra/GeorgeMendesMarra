import java.util.ArrayList;
import java.util.HashMap;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
    public static int difficulty = 4;
    public static float minimumTransaction = 0.1f;
    
    public static Wallet walletA;
    public static Wallet walletB;
    public static Wallet walletC;
    
    public static Transaction genesisTransaction;
    
    public static void main(String[] args) {
        // Configuração inicial (executada no Main)
        setup();
    }
    
    public static void setup() {
        // Cria novas carteiras
        walletA = new Wallet();
        walletB = new Wallet();
        walletC = new Wallet();
        
        Wallet coinbase = new Wallet();
        
        // Cria transação gênese (mineração inicial)
        genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
        genesisTransaction.generateSignature(coinbase.privateKey);
        genesisTransaction.transactionId = "0";
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.recipient, 
                                     genesisTransaction.value, genesisTransaction.transactionId));
        UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));
        
        System.out.println("Criando blockchain...");
        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);
        
        // Demo: transações
        Block block1 = new Block(blockchain.get(blockchain.size() - 1).hash);
        System.out.println("\nSaldo da carteira A: " + walletA.getBalance());
        System.out.println("Saldo da carteira B: " + walletB.getBalance());
        
        // Transferência de A para B
        Transaction transaction1 = walletA.sendFunds(walletB.publicKey, 40f);
        if (transaction1 != null) {
            block1.addTransaction(transaction1);
        }
        
        addBlock(block1);
        
        // Transferência de B para C
        Block block2 = new Block(blockchain.get(blockchain.size()  - 1).hash);
        System.out.println("\nSaldo da carteira A: " + walletA.getBalance());
        System.out.println("Saldo da carteira B: " + walletB.getBalance());
        System.out.println("Saldo da carteira C: " + walletC.getBalance());
        
        Transaction transaction2 = walletB.sendFunds(walletC.publicKey, 20f);
        if (transaction2 != null) {
            block2.addTransaction(transaction2);
        }
        
        addBlock(block2);
        
        // Resultados finais
        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("Saldo da carteira A: " + walletA.getBalance());
        System.out.println("Saldo da carteira B: " + walletB.getBalance());
        System.out.println("Saldo da carteira C: " + walletC.getBalance());
        
        // Verifica integridade da blockchain
        System.out.println("\n=== VERIFICAÇÃO DE INTEGRIDADE ===");
        System.out.println("Blockchain válida? " + isChainValid());
    }
    
    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
    
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        
        // Verifica hash de cada bloco
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Hash atual inválido!");
                return false;
            }
            
            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Hash anterior inválido!");
                return false;
            }
            
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("Bloco não foi minerado corretamente!");
                return false;
            }
        }
        
        // Verifica transações
        for (Block block : blockchain) {
            for (Transaction transaction : block.transactions) {
                if (!transaction.verifySignature()) {
                    System.out.println("Assinatura inválida na transação!");
                    return false;
                }
            }
        }
        
        return true;
    }
}
