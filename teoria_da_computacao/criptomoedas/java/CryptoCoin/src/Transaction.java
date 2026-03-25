import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    public String transactionId;                    // ID da transação (hash)
    public PublicKey sender;                        // Chave pública do remetente
    public PublicKey recipient;                     // Chave pública do destinatário
    public float value;                             // Valor transferido
    public byte[] signature;                        // Assinatura digital
    public ArrayList<TransactionInput> inputs;      // Entradas (UTXOs gastos)
    public ArrayList<TransactionOutput> outputs;    // Saídas (UTXOs criados)
    public long timestamp;                          // Data/hora
    
    private static int sequence = 0;                // Contador para garantir IDs únicos
    
    public Transaction(PublicKey sender, PublicKey recipient, float value, 
                       ArrayList<TransactionInput> inputs) {
        this.sender = sender;
        this.recipient = recipient;
        this.value = value;
        this.inputs = inputs;
        this.outputs = new ArrayList<>();
        this.timestamp = new Date().getTime();
    }
    
    // Calcula o hash da transação
    private String calculateHash() {
        sequence++;
        return StringUtil.applySha256(
            StringUtil.getStringFromKey(sender) +
            StringUtil.getStringFromKey(recipient) +
            Float.toString(value) + sequence + timestamp
        );
    }
    
    // Gera assinatura digital
    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + 
                      StringUtil.getStringFromKey(recipient) + 
                      Float.toString(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }
    
    // Verifica assinatura
    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + 
                      StringUtil.getStringFromKey(recipient) + 
                      Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
    }
    
    // Processa a transação
    public boolean processTransaction() {
        // Verifica assinatura
        if (!verifySignature()) {
            System.out.println("Assinatura inválida!");
            return false;
        }
        
        // Coleta UTXOs das entradas
        for (TransactionInput input : inputs) {
            input.UTXO = Blockchain.UTXOs.get(input.transactionOutputId);
        }
        
        // Verifica se há saldo suficiente
        float totalInput = getInputsValue();
        if (totalInput < value) {
            System.out.println("Saldo insuficiente! Total disponível: " + totalInput + 
                               " - Necessário: " + value);
            return false;
        }
        
        // Remove UTXOs gastos
        for (TransactionInput input : inputs) {
            Blockchain.UTXOs.remove(input.transactionOutputId);
        }
        
        // Cria saída para o destinatário
        float leftOver = totalInput - value;
        transactionId = calculateHash();
        outputs.add(new TransactionOutput(this.recipient, value, transactionId));
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionId));
        
        // Adiciona novas UTXOs
        for (TransactionOutput output : outputs) {
            Blockchain.UTXOs.put(output.id, output);
        }
        
        return true;
    }
    
    // Calcula valor total das entradas
    public float getInputsValue() {
        float total = 0;
        for (TransactionInput input : inputs) {
            if (input.UTXO != null) {
                total += input.UTXO.value;
            }
        }
        return total;
    }
    
    // Calcula valor total das saídas
    public float getOutputsValue() {
        float total = 0;
        for (TransactionOutput output : outputs) {
            total += output.value;
        }
        return total;
    }
}
