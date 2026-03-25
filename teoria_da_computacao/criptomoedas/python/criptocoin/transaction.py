import time
from typing import List, Dict, Optional, TYPE_CHECKING
from utils import Utils
from transaction_input import TransactionInput
from transaction_output import TransactionOutput

if TYPE_CHECKING:
    from wallet import Wallet

class Transaction:
    """Transação entre carteiras"""
    
    # UTXOs globais (simulando um estado compartilhado)
    global_utxos: Dict[str, TransactionOutput] = {}
    
    def __init__(self, sender, recipient, value: float, inputs: List[TransactionInput]):
        self.transaction_id = None
        self.sender = sender          # Chave pública do remetente
        self.recipient = recipient    # Chave pública do destinatário
        self.value = value
        self.signature = None
        self.inputs = inputs if inputs else []
        self.outputs: List[TransactionOutput] = []
        self.timestamp = int(time.time() * 1000)
        self.transaction_id = self.calculate_hash()
    
    def calculate_hash(self) -> str:
        """Calcula o hash da transação"""
        from utils import Utils
        sender_str = Utils.get_string_from_key(self.sender) if self.sender else ''
        recipient_str = Utils.get_string_from_key(self.recipient) if self.recipient else ''
        return Utils.sha256(
            sender_str + recipient_str + 
            str(self.value) + str(self.timestamp)
        )
    
    def generate_signature(self, private_key) -> None:
        """Gera assinatura digital"""
        from utils import Utils
        sender_str = Utils.get_string_from_key(self.sender)
        recipient_str = Utils.get_string_from_key(self.recipient)
        data = sender_str + recipient_str + str(self.value)
        self.signature = Utils.apply_ecdsa_sig(private_key, data)
    
    def verify_signature(self) -> bool:
        """Verifica assinatura digital"""
        from utils import Utils
        if self.sender is None:
            return True  # Transação coinbase (mineração)
        
        sender_str = Utils.get_string_from_key(self.sender)
        recipient_str = Utils.get_string_from_key(self.recipient)
        data = sender_str + recipient_str + str(self.value)
        return Utils.verify_ecdsa_sig(self.sender, data, self.signature)
    
    def get_inputs_value(self) -> float:
        """Calcula valor total das entradas"""
        total = 0.0
        for inp in self.inputs:
            if inp.utxo:
                total += inp.utxo.value
        return total
    
    def get_outputs_value(self) -> float:
        """Calcula valor total das saídas"""
        return sum(out.value for out in self.outputs)
    
    def process_transaction(self) -> bool:
        """Processa a transação"""
        # Verifica assinatura
        if not self.verify_signature():
            print("Assinatura inválida!")
            return False
        
        # Coleta UTXOs das entradas
        for inp in self.inputs:
            inp.utxo = Transaction.global_utxos.get(inp.transaction_output_id)
            if inp.utxo is None:
                print(f"UTXO não encontrado: {inp.transaction_output_id}")
                return False
        
        # Verifica saldo
        total_input = self.get_inputs_value()
        if total_input < self.value:
            print(f"Saldo insuficiente! Total disponível: {total_input} - Necessário: {self.value}")
            return False
        
        # Remove UTXOs gastos
        for inp in self.inputs:
            if inp.transaction_output_id in Transaction.global_utxos:
                del Transaction.global_utxos[inp.transaction_output_id]
        
        # Cria saídas
        leftover = total_input - self.value
        self.transaction_id = self.calculate_hash()
        
        # Saída para o destinatário
        self.outputs.append(TransactionOutput(self.recipient, self.value, self.transaction_id))
        # Saída de troco para o remetente
        if leftover > 0:
            self.outputs.append(TransactionOutput(self.sender, leftover, self.transaction_id))
        
        # Adiciona novas UTXOs
        for output in self.outputs:
            Transaction.global_utxos[output.id] = output
        
        return True
    
    def to_dict(self) -> dict:
        """Converte para dicionário (para debug)"""
        from utils import Utils
        return {
            'transaction_id': self.transaction_id,
            'sender': Utils.get_string_from_key(self.sender) if self.sender else 'COINBASE',
            'recipient': Utils.get_string_from_key(self.recipient),
            'value': self.value,
            'timestamp': self.timestamp,
            'inputs': [inp.to_dict() for inp in self.inputs],
            'outputs': [out.to_dict() for out in self.outputs]
        }
