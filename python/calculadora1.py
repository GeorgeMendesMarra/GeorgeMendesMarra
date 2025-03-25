def calculadora():
    """
    Calculadora simples que realiza operações básicas.
    """

    print("Bem-vindo à Calculadora!")  # Imprime mensagem de boas-vindas

    while True:  # Loop principal para permitir múltiplas operações
        try:
            num1 = float(input("Digite o primeiro número: "))  # Solicita o primeiro número
            num2 = float(input("Digite o segundo número: "))  # Solicita o segundo número
        except ValueError:
            print("Entrada inválida. Por favor, digite números.")  # Lida com entrada não numérica
            continue  # Volta ao início do loop

        print("Selecione a operação:")
        print("1. Adição (+)")
        print("2. Subtração (-)")
        print("3. Multiplicação (*)")
        print("4. Divisão (/)")

        escolha = input("Digite o número da operação desejada: ")  # Solicita a operação

        if escolha == '1':
            resultado = num1 + num2  # Realiza a adição
            print(f"Resultado: {num1} + {num2} = {resultado}")
        elif escolha == '2':
            resultado = num1 - num2  # Realiza a subtração
            print(f"Resultado: {num1} - {num2} = {resultado}")
        elif escolha == '3':
            resultado = num1 * num2  # Realiza a multiplicação
            print(f"Resultado: {num1} * {num2} = {resultado}")
        elif escolha == '4':
            if num2 == 0:
                print("Erro: Divisão por zero!")  # Lida com divisão por zero
            else:
                resultado = num1 / num2  # Realiza a divisão
                print(f"Resultado: {num1} / {num2} = {resultado}")
        else:
            print("Operação inválida.")  # Lida com operação inválida

        continuar = input("Deseja realizar outra operação? (s/n): ")  # Pergunta se deseja continuar
        if continuar.lower() != 's':
            break  # Sai do loop se a resposta for 'n'

calculadora()  # Chama a função calculadora
