# O Python lê a entrada como String por padrão, então convertemos para int()
try:
    numero = int(input("Digite um número inteiro (decimal): "))

    print(f"\n--- Resultados para o número: {numero} ---")

    # 1. Convertendo para Binário (Base 2)
    # A função bin() retorna uma string começando com '0b'
    binario = bin(numero)
    print(f"Binário:     {binario}")

    # 2. Convertendo para Octal (Base 8)
    # A função oct() retorna uma string começando com '0o'
    octal = oct(numero)
    print(f"Octal:       {octal}")

    # 3. Convertendo para Hexadecimal (Base 16)
    # A função hex() retorna uma string começando com '0x'
    # Usamos .upper() para as letras ficarem maiúsculas
    hexadecimal = hex(numero).upper()
    print(f"Hexadecimal: {hexadecimal}")

    # 4. Exemplo de Base Customizada (Base 3)
    # O Python não tem uma função nativa como 'tri()', 
    # então usamos uma lógica manual ou formatadores para bases comuns.
    # Para bases genéricas, uma abordagem comum é esta:
    def decimal_para_base(n, b):
        if n == 0: return "0"
        digitos = ""
        while n:
            digitos = str(n % b) + digitos
            n //= b
        return digitos

    print(f"Base 3:      {decimal_para_base(numero, 3)}")
    print("-" * 39)

except ValueError:
    print("Erro: Por favor, digite apenas números inteiros.")
