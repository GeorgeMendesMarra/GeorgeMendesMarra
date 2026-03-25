from typing import List
from block import Block
from transaction import Transaction
from transaction_output import TransactionOutput
from wallet import Wallet
from utils import Utils

class Blockchain:
    """Cadeia de blocos principal"""
    
    def __init__(self):
        self.chain: List[Block] = []
        self.difficulty = 4
        self.wallet_a: Wallet = None
        self.wallet_b: Wallet = None
        self.wallet_c: Wallet = None
        self.genesis_transaction: Transaction = None
    
    def setup(self) -> None:
        """Configuração inicial da blockchain"""
        # Limpa UTXOs globais
        Transaction.global_utxos.clear()
        
        # Cria carteiras
        self.wallet_a = Wallet()
        self.wallet_b = Wallet()
        self.wallet_c = Wallet()
        coinbase = Wallet()
        
        print("=" * 50)
        print("     CRYPTOCOIN - SIMULAÇÃO PYTHON       ")
        print("=" * 50)
        print(f"\nCarteira A: {self.wallet_a}")
        print(f"Carteira B: {self.wallet_b}")
        print(f"Carteira C: {self.wallet_c}\n")
        
        # Cria transação gênese (mineração inicial)
        print("Criando blockchain...")
        
        self.genesis_transaction = Transaction(
            coinbase.public_key, 
            self.wallet_a.public_key, 
            100.0, 
            []
        )
        self.genesis_transaction.generate_signature(coinbase.private_key)
        self.genesis_transaction.transaction_id = "0"
        
        # Adiciona saída da transação gênese
        genesis_output = TransactionOutput(
            self.genesis_transaction.recipient,
            self.genesis_transaction.value,
            self.genesis_transaction.transaction_id
        )
        self.genesis_transaction.outputs.append(genesis_output)
        Transaction.global_utxos[genesis_output.id] = genesis_output
        
        # Cria bloco gênese
        genesis_block = Block("0")
        genesis_block.add_transaction(self.genesis_transaction)
        self.add_block(genesis_block)
        
        # Demo: transações
        print("\n" + "-" * 50)
        print("Realizando transações...")
        print("-" * 50)
        
        # Bloco 1: Transferência de A para B
        block1 = Block(self.chain[-1].hash)
        print(f"\nSaldo A: {self.wallet_a.get_balance():.2f}")
        print(f"Saldo B: {self.wallet_b.get_balance():.2f}")
        
        tx1 = self.wallet_a.send_funds(self.wallet_b.public_key, 40.0)
        if tx1:
            block1.add_transaction(tx1)
        self.add_block(block1)
        
        # Bloco 2: Transferência de B para C
        block2 = Block(self.chain[-1].hash)
        print(f"\nSaldo A: {self.wallet_a.get_balance():.2f}")
        print(f"Saldo B: {self.wallet_b.get_balance():.2f}")
        print(f"Saldo C: {self.wallet_c.get_balance():.2f}")
        
        tx2 = self.wallet_b.send_funds(self.wallet_c.public_key, 20.0)
        if tx2:
            block2.add_transaction(tx2)
        self.add_block(block2)
        
        # Resultados finais
        print("\n" + "=" * 50)
        print("        RESULTADO FINAL")
        print("=" * 50)
        print(f"Saldo da carteira A: {self.wallet_a.get_balance():.2f}")
        print(f"Saldo da carteira B: {self.wallet_b.get_balance():.2f}")
        print(f"Saldo da carteira C: {self.wallet_c.get_balance():.2f}")
        
        # Verifica integridade
        print("\n" + "=" * 50)
        print("     VERIFICAÇÃO DE INTEGRIDADE")
        print("=" * 50)
        print(f"Blockchain válida? {self.is_chain_valid()}")
    
    def add_block(self, new_block: Block) -> None:
        """Adiciona um bloco à cadeia após minerá-lo"""
        new_block.mine_block(self.difficulty)
        self.chain.append(new_block)
    
    def is_chain_valid(self) -> bool:
        """Verifica a integridade da blockchain"""
        current_block = None
        previous_block = None
        target = '0' * self.difficulty
        
        # Verifica hash de cada bloco
        for i in range(1, len(self.chain)):
            current_block = self.chain[i]
            previous_block = self.chain[i - 1]
            
            if current_block.hash != current_block.calculate_hash():
                print(f"Hash atual inválido no bloco {i}")
                return False
            
            if current_block.previous_hash != previous_block.hash:
                print(f"Hash anterior inválido no bloco {i}")
                return False
            
            if not current_block.hash.startswith(target):
                print(f"Bloco {i} não foi minerado corretamente")
                return False
        
        # Verifica transações
        for block in self.chain:
            for transaction in block.transactions:
                if not transaction.verify_signature():
                    print("Assinatura inválida em uma transação!")
                    return False
        
        return True
    
    def print_chain(self) -> None:
        """Exibe toda a blockchain (para debug)"""
        for i, block in enumerate(self.chain):
            print(f"\n--- Bloco {i} ---")
            print(f"Hash: {block.hash}")
            print(f"Hash Anterior: {block.previous_hash}")
            print(f"Merkle Root: {block.merkle_root}")
            print(f"Nonce: {block.nonce}")
            print(f"Transações: {len(block.transactions)}")
            for tx in block.transactions:
                print(f"  - {tx.to_dict()}")
