import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;
    
    // UTXOs pertencentes a esta carteira
    public HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
    
    public Wallet() {
        generateKeyPair();
    }
    
    // Gera par de chaves (ECDSA)
    public void generateKeyPair() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Calcula saldo da carteira
    public float getBalance() {
        float total = 0;
        for (Map.Entry<String, TransactionOutput> item : Blockchain.UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            if (UTXO.isMine(publicKey)) {
                UTXOs.put(UTXO.id, UTXO);
                total += UTXO.value;
            }
        }
        return total;
    }
    
    // Envia fundos para outra carteira
    public Transaction sendFunds(PublicKey recipient, float value) {
        if (getBalance() < value) {
            System.out.println("Saldo insuficiente!");
            return null;
        }
        
        // Coleta UTXOs suficientes para cobrir o valor
        ArrayList<TransactionInput> inputs = new ArrayList<>();
        float total = 0;
        
        for (Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();
            total += UTXO.value;
            inputs.add(new TransactionInput(UTXO.id));
            if (total >= value) break;
        }
        
        Transaction newTransaction = new Transaction(publicKey, recipient, value, inputs);
        newTransaction.generateSignature(privateKey);
        
        // Remove UTXOs gastos da carteira
        for (TransactionInput input : inputs) {
            UTXOs.remove(input.transactionOutputId);
        }
        
        return newTransaction;
    }
}
