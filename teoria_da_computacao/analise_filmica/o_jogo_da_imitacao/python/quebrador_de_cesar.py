# Em Python, não precisamos importar classes como Scanner. 
# A entrada e a lógica são diretas.

def decifrar_cesar(texto, deslocamento):
    """
    Função que rotaciona o alfabeto para trás.
    Diferente do Java, Python lida com módulos de números negativos 
    de forma mais intuitiva.
    """
    resultado = ""
    
    for caractere in texto.lower():
        if caractere.isalpha(): # Verifica se é uma letra
            # ord() transforma letra em número (ASCII)
            # chr() transforma número de volta em letra
            posicao = ord(caractere) - ord('a')
            
            # A fórmula modular: (posição - deslocamento) % 26
            nova_posicao = (posicao - deslocamento) % 26
            
            resultado += chr(nova_posicao + ord('a'))
        else:
            # Mantém espaços e símbolos
            resultado += caractere
            
    return resultado

# --- Início do Programa Principal ---
print("--- Sistema de Interceptação Python (Brute Force) ---")
mensagem_cifrada = input("Digite a mensagem cifrada: ")

print("\nIniciando tentativa de quebra...\n")

# O range(1, 26) gera números de 1 a 25
for shift in range(1, 26):
    tentativa = decifrar_cesar(mensagem_cifrada, shift)
    
    # f-strings (f"...") facilitam a formatação de texto em Python
    print(f"Tentativa (Shift {shift:02d}): {tentativa}")

print("\nAnalise as opções acima para encontrar a mensagem original!")
