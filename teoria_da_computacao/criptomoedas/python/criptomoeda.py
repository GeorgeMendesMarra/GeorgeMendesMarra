import hashlib  # Biblioteca para gerar os hashes (assinaturas digitais)
import time     # Biblioteca para registrar o momento exato da transação

class Bloco:
    def __init__(self, indice, transacoes, hash_anterior, dificuldade):
        self.indice = indice           # Posição do bloco na corrente
        self.transacoes = transacoes   # Os dados (ex: "Alice pagou 50")
        self.hash_anterior = hash_anterior  # O "elo" com o bloco anterior
        self.timestamp = time.time()   # Data/Hora da criação
        self.nonce = 0                 # "Number only used once" - usado na mineração
        self.dificuldade = dificuldade # Quantos zeros o hash deve ter no início
        self.hash = self.minerar_bloco() # Gera o hash válido ao criar o bloco

    def calcular_hash(self):
        """
        Cria uma assinatura digital única combinando todos os dados do bloco.
        Se mudar 1 bit nos dados, o hash muda completamente (Efeito Avalanche).
        """
        conteudo = (str(self.indice) + str(self.transacoes) + 
                    str(self.hash_anterior) + str(self.timestamp) + 
                    str(self.nonce))
        # Encode converte para bytes, hexdigest retorna a string hexadecimal
        return hashlib.sha256(conteudo.encode()).hexdigest()

    def minerar_bloco(self):
        """
        O 'Proof of Work' (Prova de Trabalho). 
        O computador gasta energia tentando encontrar um hash que comece com zeros.
        """
        alvo = "0" * self.dificuldade
        tentativa_hash = self.calcular_hash()
        
        # Enquanto o hash não começar com a quantidade de zeros exigida...
        while not tentativa_hash.startswith(alvo):
            self.nonce += 1  # Alteramos o nonce para gerar um hash totalmente diferente
            tentativa_hash = self.calcular_hash()
            
        print(f"✅ Bloco {self.indice} minerado com sucesso!")
        return tentativa_hash

class Blockchain:
    def __init__(self):
        self.cadeia = []        # Lista que armazena todos os blocos
        self.dificuldade = 4    # Define o quão difícil é minerar (4 zeros iniciais)
        self.criar_bloco_genesis()

    def criar_bloco_genesis(self):
        """O Bloco Gênesis é o bloco #0, ele não tem um anterior."""
        bloco_genesis = Bloco(0, "Bloco Gênesis", "0", self.dificuldade)
        self.cadeia.append(bloco_genesis)

    def adicionar_bloco(self, transacoes):
        """Pega o hash do último bloco e cria um novo elo."""
        hash_anterior = self.cadeia[-1].hash
        novo_bloco = Bloco(len(self.cadeia), transacoes, hash_anterior, self.dificuldade)
        self.cadeia.append(novo_bloco)

    def validar_blockchain(self):
        """Verifica se algum bloco foi alterado ou se a corrente foi quebrada."""
        for i in range(1, len(self.cadeia)):
            atual = self.cadeia[i]
            anterior = self.cadeia[i-1]

            # Validação 1: O hash salvo no bloco ainda bate com o cálculo dos dados?
            if atual.hash != atual.calcular_hash():
                print(f"❌ Erro: Dados do bloco {i} foram corrompidos!")
                return False
                
            # Validação 2: O bloco atual aponta para o hash real do anterior?
            if atual.hash_anterior != anterior.hash:
                print(f"❌ Erro: O elo entre o bloco {i-1} e {i} foi quebrado!")
                return False
        return True

# --- Execução do Programa ---

# 1. Inicializa a rede
minha_moeda = Blockchain()

# 2. Adiciona blocos (simulando transações)
print("\nIniciando mineração...")
minha_moeda.adicionar_bloco("Pagamento: João -> Maria (10 coins)")
minha_moeda.adicionar_bloco("Pagamento: Maria -> José (2 coins)")

# 3. Verifica integridade
print(f"\nA rede é segura e válida? {minha_moeda.validar_blockchain()}")

# 4. TENTATIVA DE FRAUDE
print("\n--- TESTE DE SEGURANÇA (FRAUDE) ---")
# Um hacker tenta mudar o valor da primeira transação de 10 para 1000
minha_moeda.cadeia[1].transacoes = "Pagamento: João -> Maria (1000 coins)"

# 5. O sistema detecta a falha imediatamente
print(f"Após a alteração, a rede ainda é válida? {minha_moeda.validar_blockchain()}")
