from enum import Enum

class Sinal(Enum):
    """Representa o sinal de uma fórmula no tableau (Verdadeiro ou Falso)."""
    T = True
    F = False

class FormulaComSinal:
    """Associa uma fórmula proposicional a um sinal."""
    def __init__(self, formula: str, sinal: Sinal):
        self.formula = formula
        self.sinal = sinal

    def __str__(self):
        return f"{self.sinal.name}({self.formula})"

    def __repr__(self):
        return f"FormulaComSinal('{self.formula}', Sinal.{self.sinal.name})"

    def __eq__(self, other):
        if not isinstance(other, FormulaComSinal):
            return NotImplemented
        return self.formula == other.formula and self.sinal == other.sinal

    def __hash__(self):
        return hash((self.formula, self.sinal))

class Ramo:
    """Representa um ramo do tableau semântico."""
    def __init__(self, formulas=None):
        self.formulas = set(formulas) if formulas else set()
        self.fechado = False

    def adicionar_formula(self, fc: FormulaComSinal):
        """Adiciona uma fórmula com sinal ao ramo e verifica se o ramo fechou."""
        self.formulas.add(fc)
        self.verificar_fechamento()

    def verificar_fechamento(self):
        """Verifica se o ramo contém uma fórmula e sua negação com sinais opostos."""
        for fc in self.formulas:
            negacao = ""
            sinal_negacao = Sinal.F if fc.sinal == Sinal.T else Sinal.T

            if fc.formula.startswith("!"):
                negacao = fc.formula[1:]
            else:
                negacao = f"!{fc.formula}"

            if FormulaComSinal(negacao, sinal_negacao) in self.formulas:
                self.fechado = True
                return True
        return False

    def aplicar_regra(self, fc: FormulaComSinal):
        """Aplica uma regra de expansão a uma fórmula com sinal no ramo."""
        if self.fechado:
            return None  # Não faz nada se o ramo já está fechado

        formula = fc.formula
        sinal = fc.sinal

        if sinal == Sinal.T:
            if formula.startswith("!"):
                # T(!A) -> F(A)
                return Ramo(self.formulas | {FormulaComSinal(formula[1:], Sinal.F)})
            elif "&" in formula:
                # T(A & B) -> T(A), T(B)
                a, b = formula.split("&", 1)
                return Ramo(self.formulas | {FormulaComSinal(a, Sinal.T), FormulaComSinal(b, Sinal.T)})
            elif "|" in formula:
                # T(A | B) -> ramifica em T(A) e T(B)
                a, b = formula.split("|", 1)
                return [Ramo(self.formulas | {FormulaComSinal(a, Sinal.T)}),
                        Ramo(self.formulas | {FormulaComSinal(b, Sinal.T)})]
            elif "->" in formula:
                # T(A -> B) -> ramifica em F(A) e T(B)
                a, b = formula.split("->", 1)
                return [Ramo(self.formulas | {FormulaComSinal(a, Sinal.F)}),
                        Ramo(self.formulas | {FormulaComSinal(b, Sinal.T)})]
        elif sinal == Sinal.F:
            if formula.startswith("!"):
                # F(!A) -> T(A)
                return Ramo(self.formulas | {FormulaComSinal(formula[1:], Sinal.T)})
            elif "&" in formula:
                # F(A & B) -> ramifica em F(A) e F(B)
                a, b = formula.split("&", 1)
                return [Ramo(self.formulas | {FormulaComSinal(a, Sinal.F)}),
                        Ramo(self.formulas | {FormulaComSinal(b, Sinal.F)})]
            elif "|" in formula:
                # F(A | B) -> F(A), F(B)
                a, b = formula.split("|", 1)
                return Ramo(self.formulas | {FormulaComSinal(a, Sinal.F), FormulaComSinal(b, Sinal.F)})
            elif "->" in formula:
                # F(A -> B) -> T(A), F(B)
                a, b = formula.split("->", 1)
                return Ramo(self.formulas | {FormulaComSinal(a, Sinal.T), FormulaComSinal(b, Sinal.F)})
        return None

    def pode_aplicar_regra(self, fc: FormulaComSinal) -> bool:
        """Verifica se uma regra pode ser aplicada a uma fórmula com sinal neste ramo."""
        if self.fechado or fc in self.formulas:
            return False
        formula = fc.formula
        return (fc.sinal == Sinal.T and (formula.startswith("!") or "&" in formula or "|" in formula or "->" in formula)) or \
               (fc.sinal == Sinal.F and (formula.startswith("!") or "&" in formula or "|" in formula or "->" in formula))

def eh_tautologia(formula: str) -> bool:
    """Verifica se uma fórmula proposicional é uma tautologia usando Tableaux Semântico."""
    ramos = [Ramo([FormulaComSinal(formula, Sinal.F)])]  # Nega a fórmula inicial

    while ramos:
        novo_ramos = []
        algum_ramo_expandiu = False
        for ramo in ramos:
            if ramo.fechado:
                novo_ramos.append(ramo)
                continue

            formula_para_expandir = None
            for fc in ramo.formulas:
                if ramo.pode_aplicar_regra(fc):
                    formula_para_expandir = fc
                    break

            if formula_para_expandir:
                resultado = ramo.aplicar_regra(formula_para_expandir)
                algum_ramo_expandiu = True
                if isinstance(resultado, list):
                    novo_ramos.extend(resultado)
                elif resultado:
                    novo_ramos.append(resultado)
                else:
                    novo_ramos.append(ramo) # Se nenhuma regra aplicável ou já aplicada
            else:
                novo_ramos.append(ramo) # Nenhum regra mais aplicável neste ramo

        ramos = [r for r in novo_ramos if not r.fechado or len(novo_ramos) == 1 and novo_ramos[0].fechado]
        if not algum_ramo_expandiu and any(not r.fechado for r in ramos):
            return False # Se nenhum ramo pôde ser expandido e há ramos abertos, não é tautologia

    return all(ramo.fechado for ramo in novo_ramos)

if __name__ == "__main__":
    formula1 = "P|!P"
    formula2 = "P&!P"
    formula3 = "P->P"
    formula4 = "P->Q"
    formula5 = "(P->Q)|(Q->P)"
    formula6 = "!(P&Q)->(!P|!Q)"

    print(f"É tautologia '{formula1}'? {eh_tautologia(formula1)}")   # Deve ser True
    print(f"É tautologia '{formula2}'? {eh_tautologia(formula2)}")   # Deve ser False
    print(f"É tautologia '{formula3}'? {eh_tautologia(formula3)}")   # Deve ser True
    print(f"É tautologia '{formula4}'? {eh_tautologia(formula4)}")   # Deve ser False
    print(f"É tautologia '{formula5}'? {eh_tautologia(formula5)}")   # Deve ser True
    print(f"É tautologia '{formula6}'? {eh_tautologia(formula6)}")   # Deve ser True
