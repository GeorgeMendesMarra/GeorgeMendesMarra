"""
================================================================================
CONVERSOR DE BASES AVANÇADO - Python
================================================================================
Este programa permite converter números entre diferentes bases numéricas (2 a 36)
Possui: 
- Conversão Decimal ↔ Outras bases
- Conversão entre quaisquer bases
- Modo interativo
- Tabela de conversão
================================================================================
Autor:
Versão: 1.0
================================================================================
"""

import os  # Para limpar a tela (funciona no Windows/Linux/Mac)


# ==================== CONSTANTES GLOBAIS ====================

# String contendo todos os dígitos/símbolos para bases até 36
# Posição 0 = '0', posição 10 = 'A', posição 35 = 'Z'
DIGITOS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"


# ==================== FUNÇÕES AUXILIARES (NÚCLEO DO CONVERSOR) ====================

def decimal_para_base(numero: int, base: int) -> str:
    """
    Converte um número decimal (base 10) para qualquer base entre 2 e 36
    
    Algoritmo: Divide o número sucessivamente pela base desejada
    e coleta os restos (do último para o primeiro)
    
    Exemplo: 255 em binário (base 2)
    255 ÷ 2 = 127, resto 1 (LSB)
    127 ÷ 2 = 63,  resto 1
    63  ÷ 2 = 31,  resto 1
    31  ÷ 2 = 15,  resto 1
    15  ÷ 2 = 7,   resto 1
    7   ÷ 2 = 3,   resto 1
    3   ÷ 2 = 1,   resto 1
    1   ÷ 2 = 0,   resto 1 (MSB)
    Lendo os restos de baixo para cima: 11111111
    
    Args:
        numero: Número decimal a ser convertido (pode ser negativo)
        base: Base de destino (2 a 36)
    
    Returns:
        String representando o número na base desejada
    """
    # Caso especial: número zero
    if numero == 0:
        return "0"
    
    # Validação da base
    if base < 2 or base > 36:
        raise ValueError(f"Base inválida: {base}. Deve estar entre 2 e 36.")
    
    # Verifica se o número é negativo e guarda essa informação
    negativo = numero < 0
    if negativo:
        numero = -numero  # Trabalha com o valor absoluto
    
    # Lista para armazenar os dígitos (mais eficiente que string para concatenação)
    digitos = []
    
    # Loop principal do algoritmo de conversão
    while numero > 0:
        resto = numero % base              # Calcula o resto da divisão
        digitos.append(DIGITOS[resto])     # Adiciona o dígito correspondente
        numero = numero // base            # Divide o número para a próxima iteração
    
    # Os dígitos foram coletados do LSB para o MSB (ordem inversa)
    # Por isso invertemos a lista antes de converter para string
    resultado = ''.join(reversed(digitos))
    
    # Se o número original era negativo, adiciona o sinal de menos
    if negativo:
        resultado = "-" + resultado
    
    return resultado


def base_para_decimal(numero_str: str, base: int) -> int:
    """
    Converte um número em uma base específica para decimal
    
    Algoritmo: Percorre cada dígito da esquerda para direita,
    multiplicando o resultado atual pela base e somando o valor do dígito
    
    Exemplo: FF (hexadecimal) para decimal
    F = 15, F = 15
    resultado = 0 * 16 + 15 = 15
    resultado = 15 * 16 + 15 = 255
    
    Args:
        numero_str: String representando o número na base original
        base: Base de origem (2 a 36)
    
    Returns:
        Valor decimal do número
    
    Raises:
        ValueError: Se a base for inválida ou o número contiver dígitos inválidos
    """
    # Validação da base
    if base < 2 or base > 36:
        raise ValueError(f"Base deve estar entre 2 e 36. Fornecida: {base}")
    
    # Remove espaços extras e converte para maiúsculo (para padronizar)
    numero_str = numero_str.strip().upper()
    
    # Verifica se o número é negativo
    negativo = numero_str.startswith('-')
    if negativo:
        numero_str = numero_str[1:]  # Remove o sinal para processamento
    
    resultado = 0
    
    # Percorre cada caractere da string (da esquerda para a direita)
    for caractere in numero_str:
        # Converte caractere para valor numérico
        try:
            valor_digito = DIGITOS.index(caractere)
        except ValueError:
            raise ValueError(f"Dígito inválido '{caractere}' para a base {base}")
        
        # Verifica se o dígito é válido para a base
        # Ex: 'F' é válido apenas para base >= 16
        if valor_digito >= base:
            raise ValueError(f"Dígito '{caractere}' (valor {valor_digito}) é inválido para a base {base}")
        
        # Fórmula de conversão: resultado = resultado * base + valor_do_dígito
        resultado = resultado * base + valor_digito
    
    # Aplica o sinal negativo se necessário
    return -resultado if negativo else resultado


def converter_entre_bases(numero_str: str, base_origem: int, base_destino: int) -> str:
    """
    Converte um número diretamente entre duas bases quaisquer
    
    Args:
        numero_str: Número na base de origem
        base_origem: Base de origem (2 a 36)
        base_destino: Base de destino (2 a 36)
    
    Returns:
        String representando o número na base de destino
    """
    # Passo 1: Converter da base original para decimal
    decimal = base_para_decimal(numero_str, base_origem)
    
    # Passo 2: Converter de decimal para a base de destino
    return decimal_para_base(decimal, base_destino)


# ==================== FUNÇÕES DO MENU ====================

def limpar_tela() -> None:
    """
    Limpa a tela do console (funciona em Windows, Linux e Mac)
    """
    # os.name: 'nt' para Windows, 'posix' para Linux/Mac
    os.system('cls' if os.name == 'nt' else 'clear')


def exibir_menu() -> None:
    """
    Exibe as opções do menu principal
    """
    print("\n" + "=" * 55)
    print("              CONVERSOR DE BASES AVANÇADO")
    print("=" * 55)
    print("1. Decimal → Outras bases (2 a 36)")
    print("2. Outras bases → Decimal")
    print("3. Converter entre quaisquer bases (2 a 36)")
    print("4. Modo conversor interativo")
    print("5. Tabela de conversão (2, 8, 10, 16)")
    print("0. Sair")
    print("-" * 55)


def converter_decimal_para_outras() -> None:
    """
    Opção 1: Converte um número decimal para outras bases
    """
    print("\n--- CONVERSÃO: Decimal → Outra Base ---")
    
    try:
        # Lê o número decimal
        numero = int(input("Digite o número decimal: "))
        
        # Lê a base de destino
        base_destino = int(input("Digite a base de destino (2 a 36): "))
        
        # Valida a base
        if base_destino < 2 or base_destino > 36:
            print("❌ Erro: Base deve estar entre 2 e 36.")
            return
        
        # Realiza a conversão
        resultado = decimal_para_base(numero, base_destino)
        
        # Exibe o resultado
        print(f"\n📊 RESULTADO:")
        print(f"   {numero} (decimal) = {resultado} (base {base_destino})")
        
        # Mostra também as conversões mais comuns
        print(f"\n📌 Outras representações do mesmo número:")
        print(f"   Binário:     {decimal_para_base(numero, 2)}")
        print(f"   Octal:       {decimal_para_base(numero, 8)}")
        print(f"   Hexadecimal: 0x{decimal_para_base(numero, 16)}")
        
    except ValueError as e:
        print(f"❌ Erro: {e}")
    except Exception as e:
        print(f"❌ Erro inesperado: {e}")


def converter_outra_para_decimal() -> None:
    """
    Opção 2: Converte um número de outra base para decimal
    """
    print("\n--- CONVERSÃO: Outra Base → Decimal ---")
    
    try:
        # Lê o número na base de origem
        numero_str = input("Digite o número (na base de origem): ").strip().upper()
        
        # Lê a base de origem
        base_origem = int(input("Digite a base de origem (2 a 36): "))
        
        # Valida a base
        if base_origem < 2 or base_origem > 36:
            print("❌ Erro: Base deve estar entre 2 e 36.")
            return
        
        # Realiza a conversão
        decimal = base_para_decimal(numero_str, base_origem)
        
        # Exibe o resultado
        print(f"\n📊 RESULTADO:")
        print(f"   {numero_str} (base {base_origem}) = {decimal} (decimal)")
        
    except ValueError as e:
        print(f"❌ Erro: {e}")
    except Exception as e:
        print(f"❌ Erro inesperado: {e}")


def converter_qualquer_bases() -> None:
    """
    Opção 3: Converte um número entre quaisquer duas bases
    """
    print("\n--- CONVERSÃO ENTRE BASES ---")
    
    try:
        # Lê o número original
        numero_str = input("Digite o número original: ").strip().upper()
        
        # Lê a base de origem
        base_origem = int(input("Digite a base de origem (2 a 36): "))
        
        # Lê a base de destino
        base_destino = int(input("Digite a base de destino (2 a 36): "))
        
        # Valida as bases
        if base_origem < 2 or base_origem > 36 or base_destino < 2 or base_destino > 36:
            print("❌ Erro: Bases devem estar entre 2 e 36.")
            return
        
        # Realiza a conversão
        resultado = converter_entre_bases(numero_str, base_origem, base_destino)
        
        # Exibe o resultado
        print(f"\n📊 RESULTADO:")
        print(f"   {numero_str} (base {base_origem}) = {resultado} (base {base_destino})")
        
        # Mostra o valor decimal intermediário
        decimal = base_para_decimal(numero_str, base_origem)
        print(f"\n📌 Passo intermediário (decimal): {decimal}")
        
    except ValueError as e:
        print(f"❌ Erro: {e}")
    except Exception as e:
        print(f"❌ Erro inesperado: {e}")


def modo_interativo() -> None:
    """
    Opção 4: Modo interativo que mostra múltiplas conversões
    """
    print("\n--- MODO CONVERSOR INTERATIVO ---")
    print("Digite 'sair' para voltar ao menu principal.\n")
    
    while True:
        entrada = input("Digite o número (base 10) ou 'sair': ").strip()
        
        # Verifica se o usuário quer sair
        if entrada.lower() == 'sair':
            break
        
        try:
            # Tenta converter para número inteiro
            numero = int(entrada)
            
            # Exibe tabela formatada com várias conversões
            print("\n┌" + "─" * 53 + "┐")
            print(f"│           CONVERSÕES DO NÚMERO {numero:<20}│")
            print("├" + "─" * 53 + "┤")
            print("│ BASE           │ VALOR                           │")
            print("├" + "─" * 53 + "┤")
            print(f"│ 2 (Binário)    │ {decimal_para_base(numero, 2):<31}│")
            print(f"│ 3              │ {decimal_para_base(numero, 3):<31}│")
            print(f"│ 4              │ {decimal_para_base(numero, 4):<31}│")
            print(f"│ 5              │ {decimal_para_base(numero, 5):<31}│")
            print(f"│ 6              │ {decimal_para_base(numero, 6):<31}│")
            print(f"│ 7              │ {decimal_para_base(numero, 7):<31}│")
            print(f"│ 8 (Octal)      │ {decimal_para_base(numero, 8):<31}│")
            print(f"│ 9              │ {decimal_para_base(numero, 9):<31}│")
            print(f"│ 10 (Decimal)   │ {numero:<31}│")
            print(f"│ 16 (Hexa)      │ 0x{decimal_para_base(numero, 16):<29}│")
            print(f"│ 32             │ {decimal_para_base(numero, 32):<31}│")
            print("└" + "─" * 53 + "┘")
            print()
            
        except ValueError:
            print("❌ Número inválido! Digite um número inteiro ou 'sair'.\n")


def mostrar_tabela() -> None:
    """
    Opção 5: Gera uma tabela comparativa de conversões
    """
    print("\n--- TABELA DE CONVERSÃO (Decimal, Binário, Octal, Hexadecimal) ---\n")
    
    try:
        maximo = int(input("Até qual número deseja ver a tabela? "))
        
        # Limite de segurança para não poluir a tela
        if maximo > 200:
            print(f"Aviso: Exibindo apenas os primeiros 200 números (solicitado: {maximo})")
            maximo = 200
        
        # Cabeçalho da tabela
        print("\n┌─────────┬────────────────┬─────────┬────────────┐")
        print("│ DECIMAL │    BINÁRIO     │  OCTAL  │ HEXADECIMAL│")
        print("├─────────┼────────────────┼─────────┼────────────┤")
        
        # Gera as linhas da tabela
        for i in range(maximo + 1):
            binario = decimal_para_base(i, 2)
            octal = decimal_para_base(i, 8)
            hexa = "0x" + decimal_para_base(i, 16)
            
            print(f"│ {i:7} │ {binario:14} │ {octal:7} │ {hexa:10} │")
        
        print("└─────────┴────────────────┴─────────┴────────────┘")
        
    except ValueError:
        print("❌ Erro: Digite um número válido.")
    except Exception as e:
        print(f"❌ Erro inesperado: {e}")


# ==================== FUNÇÃO PRINCIPAL ====================

def main() -> None:
    """
    Função principal que controla o fluxo do programa
    """
    # Limpa a tela e exibe o cabeçalho
    limpar_tela()
    
    while True:
        exibir_menu()
        
        try:
            opcao = input("Escolha uma opção: ").strip()
            
            if opcao == '1':
                converter_decimal_para_outras()
            elif opcao == '2':
                converter_outra_para_decimal()
            elif opcao == '3':
                converter_qualquer_bases()
            elif opcao == '4':
                modo_interativo()
            elif opcao == '5':
                mostrar_tabela()
            elif opcao == '0':
                print("\nEncerrando o programa. Até mais!")
                break
            else:
                print("❌ Opção inválida! Tente novamente.")
            
            # Pausa antes de voltar ao menu (exceto para opções '5' e '0')
            if opcao != '5' and opcao != '0':
                input("\nPressione ENTER para continuar...")
                limpar_tela()
                
        except KeyboardInterrupt:
            print("\n\nPrograma interrompido pelo usuário. Até mais!")
            break
        except Exception as e:
            print(f"❌ Erro inesperado: {e}")
            input("\nPressione ENTER para continuar...")
            limpar_tela()


# ==================== PONTO DE ENTRADA ====================

if __name__ == "__main__":
    """
    Verifica se o script está sendo executado diretamente
    (e não importado como módulo)
    """
    main()
