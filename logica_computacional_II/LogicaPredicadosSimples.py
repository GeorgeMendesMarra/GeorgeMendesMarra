# Representa um objeto no nosso domínio.
class Objeto:
    def __init__(self, nome):
        self.nome = nome

    def __str__(self):
        return self.nome

    def __repr__(self):
        return f"Objeto('{self.nome}')"

    def __eq__(self, other):
        if isinstance(other, Objeto):
            return self.nome == other.nome
        return NotImplemented

    def __hash__(self):
        return hash(self.nome)

# Representa um predicado com um argumento.
def predicado_unario(nome_predicado, dominio, avaliacao):
    """
    Cria um predicado unário.

    Args:
        nome_predicado (str): O nome do predicado.
        dominio (set): O conjunto de objetos no domínio.
        avaliacao (callable): Uma função que recebe um objeto e retorna True ou False.

    Returns:
        callable: Uma função que recebe um objeto e retorna o valor de verdade do predicado para esse objeto.
    """
    def avaliar_predicado(x):
        if x in dominio:
            return avaliacao(x)
        return False  # Assumindo que o predicado é falso para objetos fora do domínio

    avaliar_predicado.nome = nome_predicado
    return avaliar_predicado

# Representa um predicado com dois argumentos.
def predicado_binario(nome_predicado, dominio, avaliacao):
    """
    Cria um predicado binário.

    Args:
        nome_predicado (str): O nome do predicado.
        dominio (set): O conjunto de objetos no domínio.
        avaliacao (callable): Uma função que recebe dois objetos e retorna True ou False.

    Returns:
        callable: Uma função que recebe dois objetos e retorna o valor de verdade do predicado para esses objetos.
    """
    def avaliar_predicado(x, y):
        if x in dominio and y in dominio:
            return avaliacao(x, y)
        return False  # Assumindo que o predicado é falso para objetos fora do domínio

    avaliar_predicado.nome = nome_predicado
    return avaliar_predicado

if __name__ == "__main__":
    # Definindo nosso domínio de objetos.
    dominio = {Objeto("alice"), Objeto("bob"), Objeto("carol")}

    # Definindo alguns predicados sobre nosso domínio.
    eh_humano = predicado_unario("EhHumano", dominio, lambda x: x.nome == "alice" or x.nome == "bob")
    eh_mulher = predicado_unario("EhMulher", dominio, lambda x: x.nome == "alice" or x.nome == "carol")
    ama = predicado_binario("Ama", dominio, lambda x, y: (x.nome == "alice" and y.nome == "bob") or
                                                      (x.nome == "bob" and y.nome == "alice"))

    # Criando instâncias de objetos do domínio para usar nas avaliações.
    alice = Objeto("alice")
    bob = Objeto("bob")
    carol = Objeto("carol")

    # Avaliando predicados para objetos específicos.
    print(f"{eh_humano.nome}(alice): {eh_humano(alice)}")  # Saída: True
    print(f"{eh_humano.nome}(carol): {eh_humano(carol)}")  # Saída: False
    print(f"{ama.nome}(alice, bob): {ama(alice, bob)}")    # Saída: True
    print(f"{ama.nome}(bob, carol): {ama(bob, carol)}")    # Saída: False

    # Simulação rudimentar de quantificador existencial (para um domínio finito).
    existe_humano = any(eh_humano(x) for x in dominio)
    print(f"Existe x tal que {eh_humano.nome}(x): {existe_humano}")  # Saída: True

    # Simulação rudimentar de quantificador universal (para um domínio finito).
    todos_sao_humanos = all(eh_humano(x) for x in dominio)
    print(f"Para todo x, {eh_humano.nome}(x): {todos_sao_humanos}")  # Saída: False

    # Combinação com conectivo lógico (AND).
    alice_eh_humano_e_mulher = eh_humano(alice) and eh_mulher(alice)
    print(f"{eh_humano.nome}(alice) and {eh_mulher.nome}(alice): {alice_eh_humano_e_mulher}")  # Saída: True
