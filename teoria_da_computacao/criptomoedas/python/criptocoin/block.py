import time
from typing import List, Optional, TYPE_CHECKING
from utils import Utils

if TYPE_CHECKING:
    from transaction import Transaction

class Block:
    """Bloco da blockchain"""
    
    def __init__(self, previous_hash: str):
        self.hash = None
        self.previous_hash = previous_hash
        self.merkle_root = None
        self.transactions: List['Transaction'] = []
        self.timestamp = int(time.time() * 1000)
        self.nonce = 0
        self.hash = self.calculate_hash()
    
    def calculate_hash(self) -> str:
        """Calcula o hash do bloco"""
        return Utils.sha256(
            self.previous_hash +
            str(self.timestamp) +
            str(self.nonce) +
            (self.merkle_root or '')
        )
    
    def mine_block(self, difficulty: int) -> None:
        """Minera o bloco (proof-of-work)"""
        from transaction import Transaction
        
        # Calcula Merkle root
        self.merkle_root = Utils.get_merkle_root(self.transactions)
        target = '0' * difficulty
        
        while not self.hash.startswith(target):
            self.nonce += 1
            self.hash = self.calculate_hash()
        
        print(f"Bloco minerado! Nonce: {self.nonce} - Hash: {self.hash}")
    
    def add_transaction(self, transaction: 'Transaction') -> bool:
        """Adiciona transação ao bloco"""
        if transaction is None:
            return False
        
        # Verifica se é bloco gênese
        if self.previous_hash != "0":
            if not transaction.process_transaction():
                print("Transação inválida!")
                return False
        
        self.transactions.append(transaction)
        return True
    
    def to_dict(self) -> dict:
        """Converte para dicionário (para debug)"""
        return {
            'hash': self.hash,
            'previous_hash': self.previous_hash,
            'merkle_root': self.merkle_root,
            'transactions': [tx.to_dict() for tx in self.transactions],
            'timestamp': self.timestamp,
            'nonce': self.nonce
        }
