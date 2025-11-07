"""
Arquivo: gerenciamento_dispositivos.py
Autor:
Data: 07/11/2025
Versão: 1.0
Descrição:
    Exemplo didático que simula o gerenciamento de dispositivos em um sistema operacional.

Conceitos ilustrados:
- Dispositivos de entrada e saída
- Drivers de dispositivos
- Buffer de dados
- Interrupções (simuladas)
- Comunicação via barramento (bus lógico)
"""

from collections import deque


# ==============================================================
# CLASSE BASE: Dispositivo
# ==============================================================

class Dispositivo:
    """
    Classe base que representa um dispositivo genérico.
    Todo dispositivo possui um nome e um método de operação.
    """
    def __init__(self, nome):
        self.nome = nome

    def operar(self):
        """
        Método abstrato que define o comportamento do dispositivo.
        Deve ser implementado pelas subclasses.
        """
        raise NotImplementedError("O método operar() deve ser implementado pelas subclasses.")


# ==============================================================
# CLASSE: Dispositivo de Entrada
# ==============================================================

class DispositivoEntrada(Dispositivo):
    """
    Representa um dispositivo de entrada (ex: teclado, scanner).
    """
    def operar(self):
        # Simula a captura de dados de entrada
        print(f"🔹 [Entrada] O dispositivo {self.nome} está capturando dados...")


# ==============================================================
# CLASSE: Dispositivo de Saída
# ==============================================================

class DispositivoSaida(Dispositivo):
    """
    Representa um dispositivo de saída (ex: impressora, monitor).
    """
    def operar(self):
        # Simula a exibição de dados em um dispositivo de saída
        print(f"🔸 [Saída] O dispositivo {self.nome} está exibindo dados...")


# ==============================================================
# CLASSE: Buffer
# ==============================================================

class Buffer:
    """
    Classe que simula um buffer de dados entre dispositivos.
    O buffer é usado como uma área de armazenamento temporário.
    """
    def __init__(self):
        # Usamos uma fila (deque) para armazenar os dados
        self.dados = deque()

    def adicionar(self, dado):
        """
        Adiciona um dado ao buffer.
        """
        self.dados.append(dado)
        print(f"🗃️ Dado adicionado ao buffer: {dado}")

    def remover(self):
        """
        Remove e retorna o primeiro dado do buffer, se existir.
        """
        if self.dados:
            dado = self.dados.popleft()
            print(f"📤 Dado enviado do buffer: {dado}")
            return dado
        else:
            print("⚠️ Buffer vazio!")
            return None

    def vazio(self):
        """
        Verifica se o buffer está vazio.
        """
        return len(self.dados) == 0


# ==============================================================
# CLASSE: Driver
# ==============================================================

class Driver:
    """
    Representa um driver de dispositivo.
    O driver é responsável por traduzir os comandos do sistema
    para o dispositivo físico.
    """
    def __init__(self, dispositivo):
        self.dispositivo = dispositivo

    def enviar_comando(self):
        """
        Simula o envio de um comando para o dispositivo.
        """
        print(f"🧩 Driver ativando o dispositivo {self.dispositivo.nome}...")
        self.dispositivo.operar()  # O driver chama a operação do dispositivo


# ==============================================================
# CLASSE PRINCIPAL: Sistema Operacional (Simulação)
# ==============================================================

class SistemaOperacional:
    """
    Classe que simula o gerenciamento de dispositivos pelo sistema operacional.
    """
    def __init__(self):
        # Criação dos dispositivos e seus drivers
        self.teclado = DispositivoEntrada("Teclado USB")
        self.impressora = DispositivoSaida("Impressora HP")

        self.driver_entrada = Driver(self.teclado)
        self.driver_saida = Driver(self.impressora)

        # Criação de um buffer para simular a troca de dados entre entrada e saída
        self.buffer = Buffer()

    def executar(self):
        """
        Executa a simulação de gerenciamento de dispositivos.
        """
        print("🖥️ Simulação: Gerenciamento de Dispositivos em um Sistema Operacional\n")

        # ===== ETAPA 1: Entrada de dados =====
        self.driver_entrada.enviar_comando()  # Dispositivo de entrada captura dados

        # ===== ETAPA 2: Armazenamento no buffer =====
        self.buffer.adicionar("Comando: Imprimir Relatório")

        # ===== ETAPA 3: Saída de dados =====
        self.driver_saida.enviar_comando()  # Dispositivo de saída é ativado
        self.buffer.remover()  # Dado é enviado do buffer para o dispositivo de saída

        # ===== ETAPA 4: Finalização =====
        print("\n✅ Gerenciamento concluído com sucesso!")


# ==============================================================
# BLOCO PRINCIPAL
# ==============================================================

if __name__ == "__main__":
    # Instancia o sistema operacional simulado e executa o gerenciamento
    so = SistemaOperacional()
    so.executar()
