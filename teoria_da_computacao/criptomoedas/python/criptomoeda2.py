import hashlib                # Biblioteca para gerar hash (SHA-256)
import datetime               # Para registrar o tempo de criação do bloco

# ==============================
# Classe que representa um BLOCO
# ==============================
class Bloco:

    # Construtor do bloco
    def __init__(self, dados, hash_anterior):
        self.dados = dados                    # Informações do bloco (transações)
        self.hash_anterior = hash_anterior    # Hash do bloco anterior (encadeamento)
        self.tempo = str(datetime.datetime.now())  # Data e hora do bloco
        self.nonce = 0                        # Número usado na mineração
        self.hash = self.calcular_hash()      # Calcula o hash inicial

    # Função para calcular o hash do bloco
    def calcular_hash(self):
        # Junta todas as informações do bloco em uma string
        entrada = (self.hash_anterior + self.tempo + str(self.nonce) + self.dados).encode()
        
        # Gera o hash usando SHA-256
        return hashlib.sha256(entrada).hexdigest()

    # Função de mineração (Proof of Work)
    def minerar_bloco(self, dificuldade):
        alvo = "0" * dificuldade  # Ex: dificuldade 4 → "0000"

        # Loop até encontrar um hash que comece com a quantidade de zeros exigida
        while self.hash[:dificuldade] != alvo:
            self.nonce += 1                # Incrementa o nonce
            self.hash = self.calcular_hash()  # Recalcula o hash

        # Quando encontra, imprime o hash minerado
        print(f"⛏️ Bloco minerado: {self.hash}")


# =================================
# Classe que representa a BLOCKCHAIN
# =================================
class Blockchain:

    # Construtor da blockchain
    def __init__(self):
        self.cadeia = []        # Lista que armazenará os blocos
        self.dificuldade = 4    # Define a dificuldade da mineração

        print("🚀 Iniciando blockchain...\n")

        # Criação do bloco gênesis (primeiro bloco da cadeia)
        bloco_genesis = Bloco("Bloco Gênesis", "0")
        bloco_genesis.minerar_bloco(self.dificuldade)
        self.cadeia.append(bloco_genesis)

    # Função para adicionar um novo bloco
    def adicionar_bloco(self, dados):
        ultimo_bloco = self.cadeia[-1]  # Pega o último bloco da cadeia

        # Cria novo bloco apontando para o hash do último
        novo_bloco = Bloco(dados, ultimo_bloco.hash)

        # Realiza a mineração
        novo_bloco.minerar_bloco(self.dificuldade)

        # Adiciona o bloco na cadeia
        self.cadeia.append(novo_bloco)

    # Função para exibir toda a blockchain
    def mostrar_blockchain(self):
        print("\n🔗 Blockchain criada!")

        # Percorre todos os blocos da cadeia
        for bloco in self.cadeia:
            print("\n========================")
            print("Hash:", bloco.hash)
            print("Hash Anterior:", bloco.hash_anterior)
            print("Dados:", bloco.dados)
            print("Tempo:", bloco.tempo)


# ======================
# Programa principal
# ======================
if __name__ == "__main__":

    # Cria a blockchain
    blockchain = Blockchain()

    # Adiciona blocos com transações simuladas
    blockchain.adicionar_bloco("Alice enviou 10 moedas para Bob")
    blockchain.adicionar_bloco("Bob enviou 5 moedas para Carol")
    blockchain.adicionar_bloco("Carol enviou 2 moedas para Dave")

    # Exibe toda a blockchain
    blockchain.mostrar_blockchain()
