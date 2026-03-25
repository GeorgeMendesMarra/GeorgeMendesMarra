from ecdsa import SigningKey, VerifyingKey, SECP256k1
import os
from typing import List, Dict
from transaction import Transaction
from transaction_input import TransactionInput
from transaction_output import TransactionOutput

class Wallet:
    """Carteira digital com par de chaves"""
    
    def __init__(self):
        self.private_key: SigningKey = None
        self.public_key: VerifyingKey = None
        self.utxos: Dict[str, TransactionOutput] = {}  # UTXOs pertencentes a esta carteira
        self.generate_key_pair()
    
    def generate_key_pair(self) -> None:
        """Gera par de chaves (ECDSA - SECP256k1)"""
        self.private_key = SigningKey.generate(curve=SECP256k1)
        self.public_key = self.private_key.get_verifying_key()
    
    def get_balance(self) -> float:
        """Calcula saldo da carteira"""
        from transaction import Transaction
        
        total = 0.0
        self.utxos.clear()
        
        for utxo_id, utxo in Transaction.global_utxos.items():
            if utxo.recipient.to_string() == self.public_key.to_string():
                self.utxos[utxo_id] = utxo
                total += utxo.value
        
        return total
    
    def send_funds(self, recipient_public_key, value: float):
        """Envia fundos para outra carteira"""
        if self.get_balance() < value:
            print(f"Saldo insuficiente! Disponível: {self.get_balance()} - Necessário: {value}")
            return None
        
        # Coleta UTXOs suficientes
        inputs = []
        total = 0.0
        
        for utxo_id, utxo in self.utxos.items():
            total += utxo.value
            inputs.append(TransactionInput(utxo_id))
            if total >= value:
                break
        
        # Cria transação
        transaction = Transaction(self.public_key, recipient_public_key, value, inputs)
        transaction.generate_signature(self.private_key)
        
        # Remove UTXOs gastos da carteira
        for inp in inputs:
            if inp.transaction_output_id in self.utxos:
                del self.utxos[inp.transaction_output_id]
        
        return transaction
    
    def __str__(self) -> str:
        """Representação textual da carteira"""
        from utils import Utils
        return f"Wallet(public_key={Utils.get_string_from_key(self.public_key)[:10]}..., balance={self.get_balance()})"
