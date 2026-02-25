import tkinter as tk
import string

ALFABETO = string.ascii_uppercase


# ==============================
# CLASSE ROTOR
# ==============================
class Rotor:
    def __init__(self, wiring, notch, position=0):
        self.wiring = wiring
        self.notch = notch
        self.position = position

    def rotate(self):
        self.position = (self.position + 1) % 26
        return self.position == self.notch

    def forward(self, c):
        index = (ALFABETO.index(c) + self.position) % 26
        letter = self.wiring[index]
        return ALFABETO[(ALFABETO.index(letter) - self.position) % 26]

    def backward(self, c):
        index = (ALFABETO.index(c) + self.position) % 26
        letter = ALFABETO[self.wiring.index(ALFABETO[index])]
        return ALFABETO[(ALFABETO.index(letter) - self.position) % 26]


class Reflector:
    def __init__(self, wiring):
        self.wiring = wiring

    def reflect(self, c):
        return self.wiring[ALFABETO.index(c)]


class Enigma:
    def __init__(self):
        self.reset()

    def reset(self):
        self.rotor1 = Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", notch=16)
        self.rotor2 = Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", notch=4)
        self.rotor3 = Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", notch=21)
        self.reflector = Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT")

    def encrypt_letter(self, c):
        if c not in ALFABETO:
            return c

        if self.rotor1.rotate():
            if self.rotor2.rotate():
                self.rotor3.rotate()

        c = self.rotor1.forward(c)
        c = self.rotor2.forward(c)
        c = self.rotor3.forward(c)

        c = self.reflector.reflect(c)

        c = self.rotor3.backward(c)
        c = self.rotor2.backward(c)
        c = self.rotor1.backward(c)

        return c

    def encrypt(self, text):
        result = ""
        for char in text.upper():
            result += self.encrypt_letter(char)
        return result


# ==============================
# INTERFACE GRÁFICA
# ==============================

enigma = Enigma()

def criptografar():
    enigma.reset()
    mensagem = entrada.get()
    resultado = enigma.encrypt(mensagem)
    saida.config(text=resultado)

def limpar():
    entrada.delete(0, tk.END)
    saida.config(text="")
    enigma.reset()


# Criar janela
janela = tk.Tk()
janela.title("Simulador Máquina Enigma")
janela.geometry("500x300")

titulo = tk.Label(janela, text="Simulador da Máquina Enigma", font=("Arial", 16))
titulo.pack(pady=10)

entrada_label = tk.Label(janela, text="Digite a mensagem:")
entrada_label.pack()

entrada = tk.Entry(janela, width=50)
entrada.pack(pady=5)

botao = tk.Button(janela, text="Criptografar", command=criptografar)
botao.pack(pady=5)

saida_label = tk.Label(janela, text="Mensagem criptografada:")
saida_label.pack()

saida = tk.Label(janela, text="", font=("Courier", 12), fg="blue")
saida.pack(pady=5)

limpar_botao = tk.Button(janela, text="Limpar", command=limpar)
limpar_botao.pack(pady=5)

janela.mainloop()
