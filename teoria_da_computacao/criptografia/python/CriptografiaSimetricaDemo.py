import base64
import time
import secrets

from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding


class CriptografiaSimetricaDemo:

    def gerar_chave(self, algoritmo):
        if algoritmo == "AES":
            return secrets.token_bytes(32)  # 256 bits
        elif algoritmo == "3DES":
            return secrets.token_bytes(24)  # obrigatório correto
        elif algoritmo == "Blowfish":
            return secrets.token_bytes(16)

    def tamanho_bloco(self, algoritmo):
        return 16 if algoritmo == "AES" else 8

    def cifrar(self, mensagem, algoritmo):
        chave = self.gerar_chave(algoritmo)
        bloco = self.tamanho_bloco(algoritmo)

        iv = secrets.token_bytes(bloco)

        padder = padding.PKCS7(bloco * 8).padder()
        mensagem_preenchida = padder.update(mensagem.encode()) + padder.finalize()

        if algoritmo == "AES":
            alg = algorithms.AES(chave)
        elif algoritmo == "3DES":
            alg = algorithms.TripleDES(chave)
        else:
            alg = algorithms.Blowfish(chave)

        cipher = Cipher(alg, modes.CBC(iv))
        encryptor = cipher.encryptor()

        inicio = time.time()
        cifrado = encryptor.update(mensagem_preenchida) + encryptor.finalize()
        fim = time.time()

        return base64.b64encode(cifrado).decode(), chave, iv, fim - inicio

    def decifrar(self, texto, chave, iv, algoritmo):
        bloco = self.tamanho_bloco(algoritmo)
        texto_bytes = base64.b64decode(texto)

        if algoritmo == "AES":
            alg = algorithms.AES(chave)
        elif algoritmo == "3DES":
            alg = algorithms.TripleDES(chave)
        else:
            alg = algorithms.Blowfish(chave)

        cipher = Cipher(alg, modes.CBC(iv))
        decryptor = cipher.decryptor()

        inicio = time.time()
        texto_preenchido = decryptor.update(texto_bytes) + decryptor.finalize()
        fim = time.time()

        unpadder = padding.PKCS7(bloco * 8).unpadder()
        texto_final = unpadder.update(texto_preenchido) + unpadder.finalize()

        return texto_final.decode(), fim - inicio


# =========================
# TESTE SIMPLES
# =========================

demo = CriptografiaSimetricaDemo()

mensagem = "Teste funcionando agora!"

for algoritmo in ["AES", "3DES", "Blowfish"]:
    print(f"\n🔐 {algoritmo}")

    cifrado, chave, iv, t1 = demo.cifrar(mensagem, algoritmo)
    texto, t2 = demo.decifrar(cifrado, chave, iv, algoritmo)

    print("Cifrado:", cifrado)
    print("Decifrado:", texto)
    print("Tempo cifrar:", t1)
    print("Tempo decifrar:", t2)
