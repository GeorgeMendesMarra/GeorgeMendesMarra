public class TransactionInput {
    public String transactionOutputId;  // ID da saída referenciada
    public TransactionOutput UTXO;      // Saída não gasta (Unspent Transaction Output)
    
    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }
}
