# 🧩 Torre de Hanói em Python com contador de movimentos
# Descrição: Implementação recursiva da Torre de Hanói com contagem e exibição dos movimentos

# Variável global para contar o número total de movimentos
contador = 0

def mover_discos(n, origem, destino, auxiliar):
    """
    Função recursiva que resolve o problema da Torre de Hanói.
    :param n: número de discos
    :param origem: haste de onde o disco será movido
    :param destino: haste para onde o disco será movido
    :param auxiliar: haste auxiliar usada para manobrar os discos
    """
    global contador

    # Caso base: se houver apenas um disco, mova diretamente
    if n == 1:
        contador += 1
        print(f"Mover disco 1 de {origem} para {destino}")
        return

    # Mover n-1 discos da origem para a haste auxiliar
    mover_discos(n - 1, origem, auxiliar, destino)

    # Mover o maior disco restante para o destino
    contador += 1
    print(f"Mover disco {n} de {origem} para {destino}")

    # Mover os n-1 discos da auxiliar para o destino
    mover_discos(n - 1, auxiliar, destino, origem)


# Função principal
def main():
    n = 4  # número de discos (pode alterar para testar)
    print(f"=== Torre de Hanói com {n} discos ===\n")
    mover_discos(n, 'A', 'C', 'B')
    print(f"\nTotal de movimentos: {contador}")


# Execução do programa
if __name__ == "__main__":
    main()
