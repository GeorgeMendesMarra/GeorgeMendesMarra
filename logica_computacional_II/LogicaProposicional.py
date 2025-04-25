from itertools import product
import re

# Enum simplificado para operadores
OPERADORES = {
    '!': {'precedencia': 3, 'assoc_esq': False},
    '&': {'precedencia': 2, 'assoc_esq': True},
    '|': {'precedencia': 1, 'assoc_esq': True},
    '->': {'precedencia': 0, 'assoc_esq': True},
    '<->': {'precedencia': 0, 'assoc_esq': True}
}

# Classes para representar a AST
class Formula:
    def avaliar(self, atribuicao):
        raise NotImplementedError

    def proposicoes(self):
        raise NotImplementedError

class Proposicao(Formula):
    def __init__(self, nome):
        self.nome = nome

    def avaliar(self, atribuicao):
        return atribuicao.get(self.nome, False)

    def proposicoes(self):
        return {self.nome}

    def __str__(self):
        return self.nome

class Negacao(Formula):
    def __init__(self, operando):
        self.operando = operando

    def avaliar(self, atribuicao):
        return not self.operando.avaliar(atribuicao)

    def proposicoes(self):
        return self.operando.proposicoes()

    def __str__(self):
        return f"!{self.operando}"

class Binaria(Formula):
    def __init__(self, esquerda, direita, operador):
        self.esquerda = esquerda
        self.direita = direita
        self.operador = operador

    def avaliar(self, atribuicao):
        a = self.esquerda.avaliar(atribuicao)
        b = self.direita.avaliar(atribuicao)
        if self.operador == '&':
            return a and b
        elif self.operador == '|':
            return a or b
        elif self.operador == '->':
            return not a or b
        elif self.operador == '<->':
            return a == b
        else:
            raise ValueError(f"Operador inválido: {self.operador}")

    def proposicoes(self):
        return self.esquerda.proposicoes().union(self.direita.proposicoes())

    def __str__(self):
        return f"({self.esquerda} {self.operador} {self.direita})"

# Tokenizador e parser
def tokenize(formula):
    pattern = r"(<->|->|[()!&|]|\w+)"
    return re.findall(pattern, formula)

def is_operador(token):
    return token in OPERADORES

def is_proposicao(token):
    return re.match(r'^[a-zA-Z]\w*$', token)

def parse(formula_str):
    tokens = tokenize(formula_str)
    output = []
    operadores = []

    for token in tokens:
        if is_proposicao(token):
            output.append(Proposicao(token))
        elif token == '(':
            operadores.append(token)
        elif token == ')':
            while operadores and operadores[-1] != '(':
                aplicar_operador(output, operadores.pop())
            operadores.pop()  # Remove '('
        elif is_operador(token):
            while (operadores and operadores[-1] in OPERADORES and
                   ((OPERADORES[token]['assoc_esq'] and OPERADORES[token]['precedencia'] <= OPERADORES[operadores[-1]]['precedencia']) or
                    (not OPERADORES[token]['assoc_esq'] and OPERADORES[token]['precedencia'] < OPERADORES[operadores[-1]]['precedencia']))):
                aplicar_operador(output, operadores.pop())
            operadores.append(token)

    while operadores:
        aplicar_operador(output, operadores.pop())

    if len(output) != 1:
        raise ValueError("Fórmula mal formada.")

    return output[0]

def aplicar_operador(pilha, operador):
    if operador == '!':
        if not pilha:
            raise ValueError("Negação sem operando.")
        pilha.append(Negacao(pilha.pop()))
    else:
        if len(pilha) < 2:
            raise ValueError("Operador binário sem operandos suficientes.")
        direita = pilha.pop()
        esquerda = pilha.pop()
        pilha.append(Binaria(esquerda, direita, operador))

# Geração da tabela verdade
def gerar_atribuicoes(proposicoes):
    for valores in product([False, True], repeat=len(proposicoes)):
        yield dict(zip(proposicoes, valores))

# Exemplo de uso
def main():
    formula_str = "(P -> Q) & (!Q -> !P)"
    formula = parse(formula_str)
    props = sorted(formula.proposicoes())

    print("Fórmula:", formula)
    print("Proposições:", props)
    print("\nTabela Verdade:")
    print(" | ".join(props) + " | " + str(formula))
    print("-" * 50)

    for atribuicao in gerar_atribuicoes(props):
        valores = ["T" if atribuicao[p] else "F" for p in props]
        resultado = "T" if formula.avaliar(atribuicao) else "F"
        print(" | ".join(valores) + " | " + resultado)

if __name__ == "__main__":
    main()
