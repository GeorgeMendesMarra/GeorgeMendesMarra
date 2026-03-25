class TransactionInput:
    """Entrada de transação - referência a uma UTXO"""
    
    def __init__(self, transaction_output_id: str):
        self.transaction_output_id = transaction_output_id
        self.utxo = None  # Referência à UTXO real
    
    def to_dict(self) -> dict:
        """Converte para dicionário (para debug)"""
        return {
            'transaction_output_id': self.transaction_output_id,
            'utxo_id': self.utxo.id if self.utxo else None
        }
