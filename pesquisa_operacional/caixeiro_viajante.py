import networkx as nx
from itertools import permutations

# 🔹 Cria um grafo completo com 4 cidades (nós conectados entre si)
G = nx.complete_graph(4)

# 🔹 Define as distâncias (pesos) entre pares de cidades
# Exemplo: de 0 a 1 custa 10 unidades, de 0 a 2 custa 15, etc.
distances = {
    (0, 1): 10,
    (0, 2): 15,
    (0, 3): 20,
    (1, 2): 35,
    (1, 3): 25,
    (2, 3): 30
}

# 🔹 Atribui os pesos (distâncias) às arestas do grafo
nx.set_edge_attributes(G, distances, "weight")

# 🔹 Inicializa variáveis para armazenar o menor custo e o melhor caminho
n = len(G.nodes)  # Número de cidades
min_cost = float('inf')  # Infinito, para garantir que o primeiro custo seja menor
best_path = None

# 🔹 Gera todas as permutações possíveis de rotas entre as cidades
for perm in permutations(range(n)):
    # Calcula o custo total do caminho atual (ida e volta ao ponto inicial)
    cost = sum(G[perm[i]][perm[i+1]]['weight'] for i in range(n-1))
    cost += G[perm[-1]][perm[0]]['weight']  # Volta para a cidade inicial

    # Verifica se este caminho tem o menor custo até agora
    if cost < min_cost:
        min_cost = cost
        best_path = perm  # Armazena a rota ótima

# 🔹 Exibe o melhor caminho encontrado e o seu custo total
print(f"Melhor caminho: {best_path} com custo {min_cost}")
