# ==============================
# SIMULADOR COMPLETO DA ENIGMA
# ==============================

import string

ALFABETO = string.ascii_uppercase


# ==============================
# CLASSE ROTOR
# ==============================
class Rotor:
    def __init__(self, wiring, notch, position=0):
        """
        wiring  -> mapeamento interno do rotor
        notch   -> posição que faz o próximo rotor girar
        position -> posição inicial do rotor
        """
        self.wiring = wiring
        self.notch = notch
        self.position = position

    def rotate(self):
        """Gira o rotor uma posição"""
        self.position = (self.position + 1) % 26
        return self.position == self.notch

    def forward(self, c):
        """Passagem da letra da direita para esquerda"""
        index = (ALFABETO.index(c) + self.position) % 26
        letter = self.wiring[index]
        return ALFABETO[(ALFABETO.index(letter) - self.position) % 26]

    def backward(self, c):
        """Passagem da esquerda para direita"""
        index = (ALFABETO.index(c) + self.position) % 26
        letter = ALFABETO[self.wiring.index(ALFABETO[index])]
        return ALFABETO[(ALFABETO.index(letter) - self.position) % 26]


# ==============================
# CLASSE REFLETOR
# ==============================
class Reflector:
    def __init__(self, wiring):
        self.wiring = wiring

    def reflect(self, c):
        return self.wiring[ALFABETO.index(c)]


# ==============================
# CLASSE PLUGBOARD
# ==============================
class Plugboard:
    def __init__(self, connections=None):
        """
        connections -> lista de pares como ['AB','CD']
        """
        self.mapping = {c: c for c in ALFABETO}
        if connections:
            for pair in connections:
                a, b = pair[0], pair[1]
                self.mapping[a] = b
                self.mapping[b] = a

    def swap(self, c):
        return self.mapping[c]


# ==============================
# CLASSE ENIGMA
# ==============================
class Enigma:
    def __init__(self, rotor1, rotor2, rotor3, reflector, plugboard=None):
        self.rotor1 = rotor1  # rotor da direita
        self.rotor2 = rotor2
        self.rotor3 = rotor3  # rotor da esquerda
        self.reflector = reflector
        self.plugboard = plugboard if plugboard else Plugboard()

    def encrypt_letter(self, c):
        if c not in ALFABETO:
            return c

        # Giro dos rotores (mecanismo simplificado)
        if self.rotor1.rotate():
            if self.rotor2.rotate():
                self.rotor3.rotate()

        # Plugboard entrada
        c = self.plugboard.swap(c)

        # Passa pelos rotores (direita -> esquerda)
        c = self.rotor1.forward(c)
        c = self.rotor2.forward(c)
        c = self.rotor3.forward(c)

        # Refletor
        c = self.reflector.reflect(c)

        # Volta pelos rotores (esquerda -> direita)
        c = self.rotor3.backward(c)
        c = self.rotor2.backward(c)
        c = self.rotor1.backward(c)

        # Plugboard saída
        c = self.plugboard.swap(c)

        return c

    def encrypt(self, text):
        result = ""
        for char in text.upper():
            result += self.encrypt_letter(char)
        return result


# ==============================
# CONFIGURAÇÃO HISTÓRICA
# ==============================

# Rotores clássicos I, II, III
rotor_I = Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", notch=16)
rotor_II = Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", notch=4)
rotor_III = Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", notch=21)

# Refletor B
reflector_B = Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT")

# Plugboard opcional
plugboard = Plugboard(["AB", "CD", "EF"])

# Criar máquina
enigma = Enigma(rotor_I, rotor_II, rotor_III, reflector_B, plugboard)


# ==============================
# TESTE
# ==============================

mensagem = "ATAQUE AO AMANHECER"
criptografada = enigma.encrypt(mensagem)

print("Mensagem original:", mensagem)
print("Mensagem criptografada:", criptografada)
