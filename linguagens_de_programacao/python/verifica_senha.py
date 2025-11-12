# Programa que pede ao usuário para definir uma senha e confirmá-la.
# O usuário tem até 3 tentativas para acertar a senha.

# Solicita que o usuário defina uma senha
senha_definida = input("Defina uma senha: ")

# Define o número máximo de tentativas
tentativas_restantes = 3

# Loop de tentativas
while tentativas_restantes > 0:
    # Pede que o usuário digite a senha novamente
    tentativa = input("Digite a senha para confirmar: ")

    # Verifica se a senha está correta
    if tentativa == senha_definida:
        print("Acesso concedido.")
        break  # Sai do loop se acertar
    else:
        tentativas_restantes -= 1  # Diminui uma tentativa
        if tentativas_restantes > 0:
            print("Senha incorreta. Tente novamente.")
            print(f"Tentativas restantes: {tentativas_restantes}")
        else:
            print("Acesso bloqueado. Número máximo de tentativas excedido.")
