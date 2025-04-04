from pulp import LpMaximize, LpProblem, LpVariable, LpStatus, value  # Importando corretamente 'value'

# 🔹 Cria o problema de otimização
prob = LpProblem("Problema da Dieta", LpMaximize)

# 🔹 Define as variáveis de decisão (quantidade de cada alimento)
alimento1 = LpVariable("Alimento1", lowBound=0)  # Deve ser >= 0
alimento2 = LpVariable("Alimento2", lowBound=0)  # Deve ser >= 0

# 🔹 Define a função objetivo (maximizar benefício total)
prob += 4 * alimento1 + 3 * alimento2, "Beneficio_Total"

# 🔹 Define as restrições do problema
prob += 2 * alimento1 + alimento2 <= 20, "Restricao_A"
prob += alimento1 + 3 * alimento2 <= 15, "Restricao_B"

# 🔹 Resolve o problema de otimização
prob.solve()

# 🔹 Exibe os resultados
print("Status:", LpStatus[prob.status])  # Exibe o status da solução
print("Quantidade de Alimento1 =", alimento1.varValue)  # Valor ótimo de Alimento1
print("Quantidade de Alimento2 =", alimento2.varValue)  # Valor ótimo de Alimento2
print("Benefício Total =", value(prob.objective))  # Valor ótimo da função objetivo
