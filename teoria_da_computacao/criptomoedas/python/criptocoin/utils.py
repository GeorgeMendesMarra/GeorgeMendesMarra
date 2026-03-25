import hashlib
import json
from ecdsa import SigningKey, VerifyingKey, SECP256k1
import base58
from typing import List, Any

class Utils:
    difficulty = 4  # Dificuldade do Proof-of-Work
    
    @staticmethod
    def sha256(data: str) -> str:
        """Aplica hash SHA-256 a uma string"""
        return hashlib.sha256(data.encode('utf-8')).hexdigest()
    
    @staticmethod
    def apply_ecdsa_sig(private_key: SigningKey, data: str) -> bytes:
        """Aplica assinatura digital ECDSA usando chave privada"""
        return private_key.sign(data.encode('utf-8'))
    
    @staticmethod
    def verify_ecdsa_sig(public_key: VerifyingKey, data: str, signature: bytes) -> bool:
        """Verifica assinatura digital"""
        try:
            return public_key.verify(signature, data.encode('utf-8'))
        except:
            return False
    
    @staticmethod
    def get_string_from_key(key) -> str:
        """Converte chave para string (formato base64)"""
        if hasattr(key, 'to_string'):
            return base58.b58encode(key.to_string()).decode('utf-8')
        return str(key)
    
    @staticmethod
    def mine_block(data: str, difficulty: int) -> tuple:
        """Mineração: encontra hash com a dificuldade especificada"""
        nonce = 0
        target = '0' * difficulty
        
        while True:
            hash_result = Utils.sha256(data + str(nonce))
            if hash_result.startswith(target):
                return hash_result, nonce
            nonce += 1
    
    @staticmethod
    def get_merkle_root(transactions: List[Any]) -> str:
        """Calcula a raiz da árvore de Merkle para um conjunto de transações"""
        if not transactions:
            return ''
        
        previous_layer = [tx.transaction_id for tx in transactions if hasattr(tx, 'transaction_id')]
        
        while len(previous_layer) > 1:
            current_layer = []
            for i in range(0, len(previous_layer), 2):
                if i + 1 < len(previous_layer):
                    combined = previous_layer[i] + previous_layer[i + 1]
                else:
                    combined = previous_layer[i] + previous_layer[i]
                current_layer.append(Utils.sha256(combined))
            previous_layer = current_layer
        
        return previous_layer[0] if previous_layer else ''
