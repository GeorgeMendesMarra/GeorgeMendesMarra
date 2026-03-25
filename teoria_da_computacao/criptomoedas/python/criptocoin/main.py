from blockchain import Blockchain

def main():
    """Ponto de entrada principal"""
    blockchain = Blockchain()
    blockchain.setup()
    
    print("\n" + "=" * 50)
    print("         FIM DA SIMULAÇÃO")
    print("=" * 50)

if __name__ == "__main__":
    main()
