# Exercícios sobre Grafos em Java

---

## Exercício 1 (Fácil) - Criar Grafo

Crie um grafo com 6 vértices (0 a 5) e adicione as seguintes arestas:
- (0, 1)
- (0, 2)
- (1, 3)
- (2, 4)
- (3, 5)
- (4, 5)

Depois, exiba a lista de adjacência de cada vértice.

**Tarefas:**
1. Criar uma classe `Grafo` com lista de adjacência
2. Implementar o construtor que recebe o número de vértices
3. Implementar o método `adicionarAresta(origem, destino)`
4. Implementar o método `exibir()` que mostra todos os vizinhos
5. Testar no `main()`

**Exemplo de saída esperada:**
```
Vértice 0: 1 2 
Vértice 1: 0 3 
Vértice 2: 0 4 
Vértice 3: 1 5 
Vértice 4: 2 5 
Vértice 5: 3 4 
```

---

## Exercício 2 (Fácil) - Percurso BFS

Usando o grafo do **Exercício 1**, execute uma **Busca em Largura (BFS)** a partir do vértice 0 e imprima a ordem dos vértices visitados.

**Tarefas:**
1. Implementar o método `bfs(int inicio)` na classe `Grafo`
2. Usar uma `Queue` (fila) para controlar a visitação
3. Usar um array `boolean[] visitados` para evitar repetições
4. Exibir os vértices na ordem em que são visitados

**Exemplo de saída esperada:**
```
Ordem BFS a partir do vértice 0: 0 1 2 3 4 5
```

---

## Exercício 3 (Fácil) - Percurso DFS

Usando o mesmo grafo do **Exercício 1**, execute uma **Busca em Profundidade (DFS)** a partir do vértice 0 e imprima a ordem dos vértices visitados.

**Tarefas:**
1. Implementar o método `dfs(int inicio)` na classe `Grafo`
2. Usar recursão ou uma `Stack` (pilha)
3. Usar um array `boolean[] visitados`
4. Exibir os vértices na ordem em que são visitados

**Exemplo de saída esperada:**
```
Ordem DFS a partir do vértice 0: 0 1 3 5 4 2
```

---

## Exercício 4 (Médio) - Estão Conectados?

Implemente um método que verifica se existe um **caminho** entre dois vértices.

**Tarefas:**
1. Criar o método `boolean estaoConectados(int origem, int destino)`
2. Usar BFS ou DFS para percorrer o grafo a partir da origem
3. Se encontrar o destino, retornar `true`
4. Se terminar a busca sem encontrar, retornar `false`

**Exemplo de teste:**
```java
Grafo grafo = new Grafo(6);
// adicionar as arestas do exercício 1

System.out.println(grafo.estaoConectados(0, 5));  // Deve imprimir true
System.out.println(grafo.estaoConectados(0, 9));  // (se existisse) false
```

---

## Exercício 5 (Médio) - Grau dos Vértices

Implemente um método que retorna o **grau** de um vértice (número de conexões que ele possui).

**Tarefas:**
1. Criar o método `int grau(int vertice)`
2. O grau é o tamanho da lista de adjacência daquele vértice
3. Retornar esse valor

**Exemplo de teste:**
```java
System.out.println("Grau do vértice 0: " + grafo.grau(0));  // Deve ser 2
System.out.println("Grau do vértice 1: " + grafo.grau(1));  // Deve ser 2
System.out.println("Grau do vértice 5: " + grafo.grau(5));  // Deve ser 2
```

---

## Exercício 6 (Médio) - Componentes Conectados

Conte quantos **componentes conectados** existem no grafo.

Um componente conectado é um grupo de vértices que estão todos conectados entre si (direta ou indiretamente), mas não conectados a vértices de outros grupos.

**Tarefas:**
1. Criar o método `int contarComponentes()`
2. Percorrer todos os vértices com um loop
3. Para cada vértice não visitado, iniciar uma BFS/DFS e incrementar o contador
4. Retornar o número total de componentes

**Exemplo de teste:**
```java
Grafo grafo1 = new Grafo(6);
// adicionar arestas do exercício 1 (todos conectados)
System.out.println(grafo1.contarComponentes());  // Deve ser 1

Grafo grafo2 = new Grafo(6);
// adicionar apenas (0,1) e (2,3) - dois componentes separados
System.out.println(grafo2.contarComponentes());  // Deve ser 4 (0-1, 2-3, 4, 5)
```

---

## Exercício 7 (Difícil) - Mapa de Cidades (Dijkstra)

Crie um **grafo ponderado** representando cidades de Goiás com distâncias entre elas. Implemente o algoritmo de **Dijkstra** para encontrar a menor distância entre duas cidades.

**Cidades e distâncias (exemplo):**

| Rota | Distância (km) |
|------|----------------|
| Goiânia - Anápolis | 55 |
| Goiânia - Brasília (DF) | 210 |
| Goiânia - Aparecida de Goiânia | 15 |
| Anápolis - Brasília | 130 |
| Anápolis - Formosa | 200 |
| Brasília - Formosa | 90 |
| Aparecida - Trindade | 18 |

**Tarefas:**
1. Criar uma classe `Cidade` ou usar `Map<String, List<Aresta>>`
2. Implementar o algoritmo de Dijkstra
3. O método deve retornar a menor distância e o caminho percorrido
4. Testar para diferentes rotas (ex: Goiânia → Formosa)

**Exemplo de saída esperada:**
```
Menor distância de Goiânia a Formosa: 205 km
Caminho: Goiânia → Anápolis → Brasília → Formosa
```

---

## Exercício 8 (Difícil) - Rede Social

Implemente uma **rede social** simples usando grafos, onde os usuários são vértices e as amizades são arestas.

**Funcionalidades:**
1. `adicionarUsuario(String nome)` - adiciona um novo usuário
2. `adicionarAmizade(String user1, String user2)` - conecta dois usuários
3. `listarAmigos(String usuario)` - exibe todos os amigos do usuário
4. `amigosEmComum(String user1, String user2)` - retorna a lista de amigos em comum
5. `sugerirAmigos(String usuario)` - sugere amigos de amigos que ainda não são amigos diretos
6. `exibirRede()` - mostra todos os usuários e suas conexões

**Tarefas:**
1. Usar `Map<String, List<String>>` para representar o grafo
2. Implementar cada funcionalidade em um método separado
3. No `main()`, criar um menu interativo para testar

**Exemplo de interação:**
```
--- REDE SOCIAL ---
1 - Adicionar usuário
2 - Adicionar amizade
3 - Listar amigos
4 - Amigos em comum
5 - Sugerir amigos
6 - Exibir rede
7 - Sair
Escolha: 1
Nome do usuário: João
Usuário João adicionado!

Escolha: 1
Nome do usuário: Maria
Usuário Maria adicionado!

Escolha: 2
Usuário 1: João
Usuário 2: Maria
Amizade adicionada!

Escolha: 3
Usuário: João
Amigos de João: Maria
```

---

## Exercício 9 (Desafio) - Verificar Ciclo

Implemente um algoritmo para **detectar se existe um ciclo** no grafo.

Um ciclo é um caminho que começa e termina no mesmo vértice, sem repetir vértices (exceto o primeiro/último).

**Tarefas:**
1. Criar o método `boolean temCiclo()`
2. Para grafos não direcionados: se durante a DFS você encontrar um vizinho que já foi visitado e não é o pai do vértice atual, encontrou um ciclo
3. Para grafos direcionados: é necessário rastrear vértices na pilha de recursão atual
4. Retornar `true` se existir algum ciclo, `false` caso contrário

**Exemplo de teste:**
```java
Grafo comCiclo = new Grafo(3);
comCiclo.adicionarAresta(0, 1);
comCiclo.adicionarAresta(1, 2);
comCiclo.adicionarAresta(2, 0);  // forma um triângulo
System.out.println(comCiclo.temCiclo());  // true

Grafo semCiclo = new Grafo(3);
semCiclo.adicionarAresta(0, 1);
semCiclo.adicionarAresta(1, 2);
System.out.println(semCiclo.temCiclo());   // false (é uma árvore)
```

---

## Exercício 10 (Desafio) - Ordenação Topológica

Para um **grafo direcionado acíclico (DAG)**, implemente a **ordenação topológica**.

Ordenação topológica é uma ordenação linear dos vértices onde, para toda aresta `u → v`, o vértice `u` aparece antes do vértice `v`.

**Exemplo de uso:** Sequenciamento de tarefas onde algumas dependem de outras (Tarefa A deve ser feita antes da Tarefa B).

**Tarefas:**
1. Criar um grafo direcionado
2. Implementar o método `List<Integer> ordenacaoTopologica()`
3. Usar o algoritmo de Kahn (baseado em grau de entrada) ou DFS modificada
4. Se o grafo tiver ciclo, retornar uma lista vazia ou lançar uma exceção

**Exemplo de grafo:**
```java
// Tarefas: 0 = Acordar, 1 = Tomar café, 2 = Escovar dentes
// Dependências: Acordar → Tomar café, Acordar → Escovar dentes
GrafoDirecionado tarefas = new GrafoDirecionado(3);
tarefas.adicionarAresta(0, 1);  // 0 → 1
tarefas.adicionarAresta(0, 2);  // 0 → 2

System.out.println(tarefas.ordenacaoTopologica());  
// 0 deve aparecer antes de 1 e 2. Possível saída: [0, 1, 2] ou [0, 2, 1]
```

---

## Dicas para os Exercícios

| Exercício | Dicas importantes |
|-----------|-------------------|
| **1, 2, 3** | Domine BFS e DFS antes de prosseguir. Esse é o fundamento de tudo! |
| **4, 5, 6** | Reaproveite o código de BFS/DFS para percorrer o grafo |
| **7** | Dijkstra usa `PriorityQueue` e uma estrutura para guardar distâncias |
| **8** | Use `HashMap<String, List<String>>` para armazenar os nomes |
| **9** | Para ciclo em grafo direcionado, você precisa do array `recursionStack` |
| **10** | No algoritmo de Kahn, você precisa calcular o grau de entrada de cada vértice |

---

## Checklist de Verificação

Antes de considerar um exercício completo, verifique se você:

- [ ] Consegue criar um grafo (lista de adjacência)
- [ ] Consegue adicionar arestas (direcionadas e não direcionadas)
- [ ] Consegue percorrer com BFS
- [ ] Consegue percorrer com DFS
- [ ] Entende a diferença entre BFS e DFS
- [ ] Sabe quando usar cada tipo de busca
- [ ] Implementou corretamente a marcação de vértices visitados

---
