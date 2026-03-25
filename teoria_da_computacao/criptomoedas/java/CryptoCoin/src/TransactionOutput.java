import java.security.PublicKey;

public class TransactionOutput {
    public String id;                    // Identificador único
    public PublicKey recipient;          // Destinatário
    public float value;                  // Valor
    public String parentTransactionId;   // ID da transação que originou
    
    public TransactionOutput(PublicKey recipient, float value, String parentTransactionId) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(recipient) + 
                                          Float.toString(value) + parentTransactionId);
    }
    
    // Verifica se a saída pertence ao destinatário
    public boolean isMine(PublicKey publicKey) {
        return publicKey.equals(recipient);
    }
}
