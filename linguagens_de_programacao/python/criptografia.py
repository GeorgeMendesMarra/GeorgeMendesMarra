"""
============================================================================
CRIPTOGRAFIA EM PYTHON - EXEMPLOS DO MAIS SIMPLES AO MAIS COMPLEXO
============================================================================

Mesmo conteudo das versoes em C, C++ e Java, reescrito em Python.

Bibliotecas usadas:
- hashlib, hmac, os: modulos NATIVOS do Python (nao precisa instalar nada)
  usados para hash, HMAC, PBKDF2 e geracao de bytes aleatorios seguros.
- cryptography: biblioteca externa (a mais usada no ecossistema Python
  para criptografia "de verdade"), usada para AES e RSA.

Cada tecnica vira uma classe com metodos estaticos, seguindo a mesma
organizacao das versoes em C++ e Java.

PARTE 1 (sem bibliotecas externas): cifras classicas / didaticas
  1. Cifra de Cesar
  2. Cifra de Vigenere
  3. Cifra XOR

PARTE 2 (usando modulos nativos + a lib cryptography): criptografia real
  4. Bytes aleatorios seguros
  5. Hash SHA-256
  6. HMAC (hash com chave)
  7. PBKDF2 (derivar chave a partir de senha)
  8. AES-256-CBC (cifra simetrica)
  9. AES-256-GCM (cifra simetrica + autenticacao)
 10. RSA (cifra assimetrica com par de chaves)
 11. Assinatura digital RSA

COMO INSTALAR A DEPENDENCIA EXTERNA:
    pip install cryptography

COMO EXECUTAR:
    python3 criptografia_completo.py
============================================================================
"""

import hashlib
import hmac
import os

from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives import padding as sym_padding
from cryptography.hazmat.primitives.asymmetric import rsa, padding as asym_padding
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.exceptions import InvalidSignature, InvalidTag


# ============================================================================
# FUNCAO AUXILIAR GERAL: converte bytes para uma string hexadecimal, para
# exibir na tela dados que nem sempre sao caracteres imprimiveis (como
# texto cifrado). Em Python, isso ja existe pronto: bytes.hex()
# ============================================================================
def para_hex(dados: bytes) -> str:
    return dados.hex()


# ============================================================================
# PARTE 1.1 - CIFRA DE CESAR
# Ideia: desloca cada letra um numero fixo de posicoes no alfabeto.
# Fraqueza: so existem 25 chaves possiveis -> facil de quebrar por forca
# bruta.
# ============================================================================
class CifraCesar:

    @staticmethod
    def cifrar(texto: str, deslocamento: int) -> str:
        resultado = []

        for c in texto:
            if c.isupper():
                # 'A' = 65 na tabela ASCII. ord() converte caractere em
                # numero; normalizamos para 0-25, aplicamos o deslocamento
                # com modulo 26 (para "dar a volta" no alfabeto) e
                # convertemos de volta para caractere com chr()
                nova = chr((ord(c) - ord('A') + deslocamento) % 26 + ord('A'))
            elif c.islower():
                nova = chr((ord(c) - ord('a') + deslocamento) % 26 + ord('a'))
            else:
                # espacos, numeros e pontuacao permanecem inalterados
                nova = c
            resultado.append(nova)

        return "".join(resultado)

    @staticmethod
    def decifrar(texto: str, deslocamento: int) -> str:
        # decifrar e cifrar com o deslocamento invertido
        return CifraCesar.cifrar(texto, 26 - (deslocamento % 26))


def demo_cesar():
    print("\n=== 1. Cifra de Cesar ===")

    mensagem = "Ola Mundo"
    chave = 3

    print(f"Original:  {mensagem}")

    cifrado = CifraCesar.cifrar(mensagem, chave)
    print(f"Cifrado:   {cifrado}")

    decifrado = CifraCesar.decifrar(cifrado, chave)
    print(f"Decifrado: {decifrado}")


# ============================================================================
# PARTE 1.2 - CIFRA DE VIGENERE
# Ideia: como o Cesar, mas o deslocamento muda a cada letra, seguindo uma
# palavra-chave que se repete ao longo do texto.
# Fraqueza: com chave curta e texto longo, e quebravel por analise
# estatistica (metodo de Kasiski).
# ============================================================================
class CifraVigenere:

    @staticmethod
    def cifrar(texto: str, chave: str) -> str:
        resultado = []
        j = 0  # indice que percorre a chave (so avanca em letras)

        for c in texto:
            if c.isalpha():
                # pega a letra da chave correspondente e calcula o
                # deslocamento. upper() garante que a chave funcione
                # independente de estar em maiusculas ou minusculas
                deslocamento = ord(chave[j % len(chave)].upper()) - ord('A')

                if c.isupper():
                    nova = chr((ord(c) - ord('A') + deslocamento) % 26 + ord('A'))
                else:
                    nova = chr((ord(c) - ord('a') + deslocamento) % 26 + ord('a'))

                j += 1  # so avanca a chave ao processar uma letra de verdade
            else:
                nova = c

            resultado.append(nova)

        return "".join(resultado)

    @staticmethod
    def decifrar(texto: str, chave: str) -> str:
        resultado = []
        j = 0

        for c in texto:
            if c.isalpha():
                deslocamento = ord(chave[j % len(chave)].upper()) - ord('A')

                if c.isupper():
                    # +26 evita numero negativo antes do modulo
                    nova = chr((ord(c) - ord('A') - deslocamento + 26) % 26 + ord('A'))
                else:
                    nova = chr((ord(c) - ord('a') - deslocamento + 26) % 26 + ord('a'))

                j += 1
            else:
                nova = c

            resultado.append(nova)

        return "".join(resultado)


def demo_vigenere():
    print("\n=== 2. Cifra de Vigenere ===")

    mensagem = "Ataque ao amanhecer"
    chave = "CHAVE"

    print(f"Original:  {mensagem}")

    cifrado = CifraVigenere.cifrar(mensagem, chave)
    print(f"Cifrado:   {cifrado}")

    decifrado = CifraVigenere.decifrar(cifrado, chave)
    print(f"Decifrado: {decifrado}")


# ============================================================================
# PARTE 1.3 - CIFRA XOR
# Ideia: usa a operacao bit a bit XOR (^). Propriedade chave:
# (A XOR chave) XOR chave = A -- cifrar e decifrar usam o MESMO metodo.
# Fraqueza: com chave curta reutilizada, padroes se repetem e a cifra pode
# ser quebrada. So e realmente segura com chave do tamanho do texto,
# usada uma unica vez.
# ============================================================================
class CifraXor:

    # Cifra e decifra: aplica XOR byte a byte com a chave, repetindo-a
    # ciclicamente. Funciona com qualquer sequencia de bytes.
    @staticmethod
    def processar(dados: bytes, chave: bytes) -> bytes:
        return bytes(
            dados[i] ^ chave[i % len(chave)]
            for i in range(len(dados))
        )


def demo_xor():
    print("\n=== 3. Cifra XOR ===")

    mensagem_texto = "Mensagem secreta"
    mensagem = mensagem_texto.encode()
    chave = b"chaveXYZ"

    print(f"Original:      {mensagem_texto}")

    # ---------- CIFRAR ----------
    cifrado = CifraXor.processar(mensagem, chave)
    print(f"Cifrado (hex): {para_hex(cifrado)}")

    # ---------- DECIFRAR ----------
    # e o MESMO metodo aplicado de novo com a mesma chave
    decifrado = CifraXor.processar(cifrado, chave)
    print(f"Decifrado:     {decifrado.decode()}")


# ============================================================================
# PARTE 2.1 - BYTES ALEATORIOS SEGUROS
# Base de praticamente tudo em criptografia real: gerar numeros que nao
# podem ser previstos. Em Python, isso e feito com os.urandom (e NAO com
# o modulo random, que nao e seguro para criptografia).
# ============================================================================
class Aleatorio:

    @staticmethod
    def gerar_bytes(quantidade: int) -> bytes:
        # os.urandom usa a fonte de entropia segura do sistema operacional
        return os.urandom(quantidade)


def demo_bytes_aleatorios():
    print("\n=== 4. Bytes aleatorios seguros ===")

    buffer = Aleatorio.gerar_bytes(16)
    print(f"Bytes aleatorios: {para_hex(buffer)}")


# ============================================================================
# PARTE 2.2 - HASH SHA-256
# Transforma qualquer dado em um "resumo" de tamanho fixo. E uma via: nao
# da para reverter o hash e obter o dado original.
# ============================================================================
class Sha256:

    @staticmethod
    def calcular(mensagem: str) -> bytes:
        # hashlib e o modulo nativo do Python para funcoes de hash
        return hashlib.sha256(mensagem.encode()).digest()


def demo_sha256():
    print("\n=== 5. Hash SHA-256 ===")

    mensagem = "Ola, mundo!"
    hash_resultado = Sha256.calcular(mensagem)

    print(f"Mensagem: {mensagem}")
    print(f"SHA-256:  {para_hex(hash_resultado)}")


# ============================================================================
# PARTE 2.3 - HMAC (hash com chave)
# Um hash comum so garante que o dado nao mudou. O HMAC usa uma chave
# secreta, garantindo tambem que quem gerou o hash conhecia a chave.
# ============================================================================
class HmacSha256:

    @staticmethod
    def calcular(chave: bytes, mensagem: str) -> bytes:
        # hmac e o modulo nativo do Python para codigos de autenticacao
        # de mensagem
        return hmac.new(chave, mensagem.encode(), hashlib.sha256).digest()


def demo_hmac():
    print("\n=== 6. HMAC-SHA256 ===")

    chave = Aleatorio.gerar_bytes(32)  # chave secreta compartilhada
    mensagem = "transferir 100 reais"

    resultado = HmacSha256.calcular(chave, mensagem)

    print(f"Mensagem:    {mensagem}")
    print(f"HMAC-SHA256: {para_hex(resultado)}")

    # Na pratica: quem recebe a mensagem + o HMAC recalcula o HMAC com a
    # mesma chave e compara o resultado. Se bater, a mensagem e autentica.


# ============================================================================
# PARTE 2.4 - PBKDF2 (derivar chave a partir de senha)
# Senhas digitadas por humanos sao fracas como chave direta. O PBKDF2
# transforma uma senha em uma chave forte, aplicando o hash muitas vezes
# de proposito (isso dificulta ataques de forca bruta).
# ============================================================================
class Pbkdf2:

    @staticmethod
    def derivar(senha: str, salt: bytes, iteracoes: int, tamanho_chave_bytes: int) -> bytes:
        # hashlib.pbkdf2_hmac ja vem pronto no modulo nativo hashlib
        return hashlib.pbkdf2_hmac(
            "sha256",
            senha.encode(),
            salt,
            iteracoes,
            dklen=tamanho_chave_bytes,
        )


def demo_pbkdf2():
    print("\n=== 7. PBKDF2 (senha -> chave) ===")

    senha = "minhaSenh4Fraca"

    # "salt": valor aleatorio unico, guardado junto com a chave derivada.
    # Impede que a mesma senha sempre gere a mesma chave.
    salt = Aleatorio.gerar_bytes(16)

    chave_derivada = Pbkdf2.derivar(senha, salt, 100000, 32)  # 32 bytes = 256 bits

    print(f"Senha:          {senha}")
    print(f"Salt:           {para_hex(salt)}")
    print(f"Chave derivada: {para_hex(chave_derivada)}")

    # Importante: o salt precisa ser guardado (nao e secreto), pois sera
    # necessario para derivar a mesma chave de novo no futuro.


# ============================================================================
# PARTE 2.5 - AES-256-CBC (cifra simetrica)
# Cifra e decifra dados usando a MESMA chave dos dois lados.
# ============================================================================
class AesCbc:

    @staticmethod
    def cifrar(texto: str, chave: bytes, iv: bytes) -> bytes:
        dados = texto.encode()

        # CBC exige que os dados sejam multiplos do tamanho do bloco (16
        # bytes no AES). O PKCS7 preenche o espaco que falta antes de cifrar
        preenchedor = sym_padding.PKCS7(algorithms.AES.block_size).padder()
        dados_com_padding = preenchedor.update(dados) + preenchedor.finalize()

        cifrador = Cipher(algorithms.AES(chave), modes.CBC(iv))
        encryptor = cifrador.encryptor()

        return encryptor.update(dados_com_padding) + encryptor.finalize()

    @staticmethod
    def decifrar(cifrado: bytes, chave: bytes, iv: bytes) -> str:
        cifrador = Cipher(algorithms.AES(chave), modes.CBC(iv))
        decryptor = cifrador.decryptor()

        dados_com_padding = decryptor.update(cifrado) + decryptor.finalize()

        # remove o padding adicionado na hora de cifrar
        despreenchedor = sym_padding.PKCS7(algorithms.AES.block_size).unpadder()
        dados = despreenchedor.update(dados_com_padding) + despreenchedor.finalize()

        return dados.decode()


def demo_aes_cbc():
    print("\n=== 8. AES-256-CBC ===")

    chave = Aleatorio.gerar_bytes(32)  # 32 bytes = AES-256
    iv = Aleatorio.gerar_bytes(16)     # IV deve ser diferente a cada cifragem

    texto = "Mensagem secreta"

    cifrado = AesCbc.cifrar(texto, chave, iv)
    print(f"Original:      {texto}")
    print(f"Cifrado (hex): {para_hex(cifrado)}")

    decifrado = AesCbc.decifrar(cifrado, chave, iv)
    print(f"Decifrado:     {decifrado}")


# ============================================================================
# PARTE 2.6 - AES-256-GCM (cifra + autenticacao, o padrao moderno)
# O CBC so cifra -- nao detecta se alguem alterou o dado cifrado. O GCM
# cifra e gera uma "tag" que comprova que os dados nao foram alterados.
# E o modo recomendado atualmente para a maioria dos casos.
# ============================================================================
class AesGcm:

    @staticmethod
    def cifrar(texto: str, chave: bytes, iv: bytes) -> tuple[bytes, bytes]:
        cifrador = Cipher(algorithms.AES(chave), modes.GCM(iv))
        encryptor = cifrador.encryptor()

        cifrado = encryptor.update(texto.encode()) + encryptor.finalize()

        # a tag de autenticacao fica disponivel apos finalize()
        return cifrado, encryptor.tag

    @staticmethod
    def decifrar(cifrado: bytes, tag: bytes, chave: bytes, iv: bytes) -> str | None:
        try:
            cifrador = Cipher(algorithms.AES(chave), modes.GCM(iv, tag))
            decryptor = cifrador.decryptor()

            # se a tag nao bater, finalize() lanca InvalidTag
            dados = decryptor.update(cifrado) + decryptor.finalize()
            return dados.decode()

        except InvalidTag:
            return None  # sinaliza falha de autenticacao


def demo_aes_gcm():
    print("\n=== 9. AES-256-GCM ===")

    chave = Aleatorio.gerar_bytes(32)
    iv = Aleatorio.gerar_bytes(12)  # GCM usa IV de 12 bytes por convencao

    texto = "Dados confidenciais"

    cifrado, tag = AesGcm.cifrar(texto, chave, iv)
    print(f"Original: {texto}")
    print(f"Cifrado:  {para_hex(cifrado)}")
    print(f"Tag:      {para_hex(tag)}")

    decifrado = AesGcm.decifrar(cifrado, tag, chave, iv)

    if decifrado is not None:
        print(f"Decifrado (autenticado com sucesso): {decifrado}")
    else:
        print("ERRO: dado corrompido ou adulterado!")


# ============================================================================
# PARTE 2.7 - RSA (cifra assimetrica com par de chaves)
# Usa DUAS chaves diferentes: uma publica (cifra, pode ser compartilhada)
# e uma privada (decifra, deve ficar em segredo).
# ============================================================================
class Rsa:

    @staticmethod
    def gerar_par_chaves(bits: int = 2048):
        # gera as duas chaves (publica e privada) de uma vez.
        # 65537 e o expoente publico padrao, recomendado pela industria
        chave_privada = rsa.generate_private_key(public_exponent=65537, key_size=bits)
        return chave_privada, chave_privada.public_key()

    @staticmethod
    def cifrar(chave_publica, mensagem: str) -> bytes:
        # OAEP com SHA-256 = padding seguro recomendado atualmente
        return chave_publica.encrypt(
            mensagem.encode(),
            asym_padding.OAEP(
                mgf=asym_padding.MGF1(algorithm=hashes.SHA256()),
                algorithm=hashes.SHA256(),
                label=None,
            ),
        )

    @staticmethod
    def decifrar(chave_privada, cifrado: bytes) -> str:
        dados = chave_privada.decrypt(
            cifrado,
            asym_padding.OAEP(
                mgf=asym_padding.MGF1(algorithm=hashes.SHA256()),
                algorithm=hashes.SHA256(),
                label=None,
            ),
        )
        return dados.decode()


def demo_rsa_cifrar():
    print("\n=== 10. RSA (cifra assimetrica) ===")

    chave_privada, chave_publica = Rsa.gerar_par_chaves(2048)  # tamanho recomendado atual

    mensagem = "segredo"

    cifrado = Rsa.cifrar(chave_publica, mensagem)
    print(f"Mensagem: {mensagem}")
    print(f"Cifrado:  {para_hex(cifrado)}")

    decifrado = Rsa.decifrar(chave_privada, cifrado)
    print(f"Decifrado: {decifrado}")

    # Nota: RSA so cifra blocos pequenos (poucas centenas de bytes). Na
    # pratica, RSA cifra uma chave AES, e o AES cifra os dados de fato --
    # e a chamada "criptografia hibrida" (assim funciona o HTTPS).


# ============================================================================
# PARTE 2.8 - ASSINATURA DIGITAL RSA
# Diferente de cifrar, aqui o objetivo e provar autoria: qualquer um pode
# conferir a assinatura com a chave publica, mas so quem tem a chave
# privada consegue cria-la.
# ============================================================================
class AssinaturaRsa:

    @staticmethod
    def assinar(chave_privada, mensagem: str) -> bytes:
        return chave_privada.sign(
            mensagem.encode(),
            asym_padding.PSS(
                mgf=asym_padding.MGF1(hashes.SHA256()),
                salt_length=asym_padding.PSS.MAX_LENGTH,
            ),
            hashes.SHA256(),
        )

    @staticmethod
    def verificar(chave_publica, mensagem: str, assinatura: bytes) -> bool:
        try:
            chave_publica.verify(
                assinatura,
                mensagem.encode(),
                asym_padding.PSS(
                    mgf=asym_padding.MGF1(hashes.SHA256()),
                    salt_length=asym_padding.PSS.MAX_LENGTH,
                ),
                hashes.SHA256(),
            )
            return True
        except InvalidSignature:
            return False


def demo_rsa_assinatura():
    print("\n=== 11. Assinatura digital RSA ===")

    chave_privada, chave_publica = Rsa.gerar_par_chaves(2048)
    mensagem = "Autorizo o pagamento de 500 reais"

    assinatura = AssinaturaRsa.assinar(chave_privada, mensagem)
    print(f"Mensagem:   {mensagem}")
    print(f"Assinatura: {para_hex(assinatura)}")

    # Na pratica, quem verifica so teria a chave publica
    valido = AssinaturaRsa.verificar(chave_publica, mensagem, assinatura)

    if valido:
        print("Resultado: assinatura VALIDA -- a mensagem e autentica.")
    else:
        print("Resultado: assinatura INVALIDA -- mensagem alterada ou chave errada.")


# ============================================================================
# MENU PRINCIPAL
# Permite escolher qual exemplo rodar, ou rodar todos em sequencia.
# ============================================================================
def mostrar_menu():
    print("\n===================================================")
    print(" CRIPTOGRAFIA EM PYTHON - MENU DE EXEMPLOS")
    print("===================================================")
    print(" Cifras classicas (sem bibliotecas externas):")
    print("  1. Cifra de Cesar")
    print("  2. Cifra de Vigenere")
    print("  3. Cifra XOR")
    print(" Criptografia real (hashlib/hmac nativos + lib cryptography):")
    print("  4. Bytes aleatorios seguros")
    print("  5. Hash SHA-256")
    print("  6. HMAC-SHA256")
    print("  7. PBKDF2 (senha -> chave)")
    print("  8. AES-256-CBC")
    print("  9. AES-256-GCM")
    print(" 10. RSA (cifrar/decifrar)")
    print(" 11. Assinatura digital RSA")
    print("  0. Rodar TODOS os exemplos em sequencia")
    print(" -1. Sair")
    print("===================================================")


def rodar_todos():
    demo_cesar()
    demo_vigenere()
    demo_xor()
    demo_bytes_aleatorios()
    demo_sha256()
    demo_hmac()
    demo_pbkdf2()
    demo_aes_cbc()
    demo_aes_gcm()
    demo_rsa_cifrar()
    demo_rsa_assinatura()


# "Mapa" de opcoes para funcoes, similar a um switch/case
OPCOES = {
    1: demo_cesar,
    2: demo_vigenere,
    3: demo_xor,
    4: demo_bytes_aleatorios,
    5: demo_sha256,
    6: demo_hmac,
    7: demo_pbkdf2,
    8: demo_aes_cbc,
    9: demo_aes_gcm,
    10: demo_rsa_cifrar,
    11: demo_rsa_assinatura,
    0: rodar_todos,
}


def main():
    while True:
        mostrar_menu()

        entrada = input("Escolha uma opcao: ")

        try:
            opcao = int(entrada)
        except ValueError:
            break  # encerra se a entrada nao for um numero valido

        if opcao == -1:
            print("Encerrando...")
            break

        funcao = OPCOES.get(opcao)
        if funcao is not None:
            funcao()
        else:
            print("Opcao invalida!")


if __name__ == "__main__":
    main()
