from utils import Utils
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from wallet import Wallet

class TransactionOutput:
    """Saída de transação - UTXO (Unspent Transaction Output)"""
    
    def __init__(self, recipient, value: float, parent_transaction_id: str):
        self.id = None
        self.recipient = recipient  # Chave pública do destinatário
        self.value = value
        self.parent_transaction_id = parent_transaction_id
        self.id = self.calculate_id()
    
    def calculate_id(self) -> str:
        """Calcula o identificador único da saída"""
        from utils import Utils
        recipient_str = Utils.get_string_from_key(self.recipient)
        return Utils.sha256(recipient_str + str(self.value) + self.parent_transaction_id)
    
    def is_mine(self, public_key) -> bool:
        """Verifica se a saída pertence ao destinatário"""
        return self.recipient.to_string() == public_key.to_string()
    
    def to_dict(self) -> dict:
        """Converte para dicionário (para debug)"""
        from utils import Utils
        return {
            'id': self.id,
            'recipient': Utils.get_string_from_key(self.recipient),
            'value': self.value,
            'parent_transaction_id': self.parent_transaction_id
        }
