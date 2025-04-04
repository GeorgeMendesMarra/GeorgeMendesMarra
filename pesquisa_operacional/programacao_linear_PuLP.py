from pulp import LpMaximize, LpProblem, LpVariable

# Criar o modelo de otimização
model = LpProblem(name="maximizacao", sense=LpMaximize)

# Definir as variáveis de decisão
x = LpVariable(name="x", lowBound=0)
y = LpVariable(name="y", lowBound=0)

# Definir a função objetivo
model += 3*x + 5*y, "Função Objetivo"

# Adicionar restrições
model += (2*x + 8*y <= 13, "Restrição 1")
model += (5*x + 2*y <= 11, "Restrição 2")

# Resolver o problema
model.solve()

# Exibir os resultados
print(f"x = {x.varValue}")
print(f"y = {y.varValue}")
print(f"Valor ótimo (Z) = {model.objective.value()}")
