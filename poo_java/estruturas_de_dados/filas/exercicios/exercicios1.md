# Exercícios sobre Filas (Queue) em Java

---

## Exercício 1 (Fácil) - Fila de Números

Crie uma fila de números inteiros, adicione os números de 1 a 5 e depois remova todos exibindo cada um.

**Tarefas:**
1. Criar uma `Queue<Integer>` usando `ArrayDeque` ou `LinkedList`
2. Adicionar os números 1, 2, 3, 4, 5 usando `offer()`
3. Remover e exibir cada número usando `poll()` até a fila ficar vazia

**Exemplo de saída:**
```
Removendo: 1
Removendo: 2
Removendo: 3
Removendo: 4
Removendo: 5
Fila está vazia? true
```

---

## Exercício 2 (Fácil) - Fila de Supermercado

Simule uma fila de supermercado onde 5 clientes entram na fila (use nomes) e depois são atendidos um a um.

**Tarefas:**
1. Criar uma `Queue<String>` para os clientes
2. Adicionar 5 nomes de clientes na fila
3. Exibir a fila completa
4. Atender (remover) um a um, exibindo quem está sendo atendido
5. Ao final, exibir quantos clientes foram atendidos

**Exemplo de saída:**
```
Fila inicial: [João, Maria, Pedro, Ana, Carlos]

Atendendo: João
Atendendo: Maria
Atendendo: Pedro
Atendendo: Ana
Atendendo: Carlos

Total de clientes atendidos: 5
Fila final: []
```

---

## Exercício 3 (Médio) - Menu de Atendimento

Crie um programa que recebe nomes do usuário e os coloca em uma fila. As opções são:

- Digitar um nome → adicionar na fila
- Digitar "atender" → remove e exibe o primeiro da fila
- Digitar "fila" → exibe todos os nomes na fila
- Digitar "sair" → encerra o programa

**Tarefas:**
1. Usar `Scanner` para ler comandos do usuário
2. Usar um loop infinito (`while(true)`) com `break` para sair
3. Para cada comando, executar a operação correspondente na fila
4. Tratar os casos de fila vazia (avisar o usuário)

**Exemplo de interação:**
```
Digite um nome, 'atender', 'fila' ou 'sair': João
João entrou na fila.

Digite um nome, 'atender', 'fila' ou 'sair': Maria
Maria entrou na fila.

Digite um nome, 'atender', 'fila' ou 'sair': fila
Fila atual: [João, Maria]

Digite um nome, 'atender', 'fila' ou 'sair': atender
Atendendo: João

Digite um nome, 'atender', 'fila' ou 'sair': sair
Encerrando...
```

---

## Exercício 4 (Médio) - PriorityQueue com Números Aleatórios

Use `PriorityQueue` com números inteiros. Adicione 10 números aleatórios entre 1 e 100 e depois remova todos. Observe a ordem em que eles saem.

**Tarefas:**
1. Importar `java.util.PriorityQueue` e `java.util.Random`
2. Criar uma `PriorityQueue<Integer>`
3. Adicionar 10 números aleatórios usando `offer()`
4. Exibir a fila (a ordem interna pode parecer estranha)
5. Remover e exibir todos os números usando `poll()`
6. Responder: Os números saem na ordem em que foram adicionados? Por quê?

**Exemplo de saída:**
```
Números adicionados: [23, 45, 12, 67, 34, 89, 5, 78, 91, 43]
Fila interna: [5, 23, 12, 43, 34, 89, 91, 78, 67, 45]

Removendo em ordem:
5, 12, 23, 34, 43, 45, 67, 78, 89, 91
```

---

## Exercício 5 (Difícil) - Sistema de Atendimento com Duas Filas

Crie um sistema de atendimento com **duas filas**:
- Fila prioritária (idosos, gestantes, etc.)
- Fila normal

**Regra de atendimento:** Atende 2 da fila prioritária, depois 1 da fila normal, e repete.

**Tarefas:**
1. Criar duas `Queue<String>`: `filaPrioritaria` e `filaNormal`
2. Pré-adicionar alguns nomes em cada fila:
   - Prioritária: "Sr. José (80)", "Dona Maria (70)", "Sr. Pedro (65)"
   - Normal: "João", "Maria", "Carlos", "Ana", "Marcos"
3. Implementar a lógica de atendimento que siga a regra: 2 prioritários → 1 normal → 2 prioritários → 1 normal...
4. Quando uma fila acabar, continue atendendo da outra normalmente
5. Exibir cada atendimento e no final mostrar quantos foram atendidos de cada fila

**Exemplo de saída:**
```
=== INICIANDO ATENDIMENTO ===

Atendendo (Prioritário): Sr. José (80)
Atendendo (Prioritário): Dona Maria (70)
Atendendo (Normal): João

Atendendo (Prioritário): Sr. Pedro (65)
Atendendo (Prioritário): (fila prioritária vazia)
Atendendo (Normal): Maria

Atendendo (Normal): Carlos
Atendendo (Normal): Ana
Atendendo (Normal): Marcos

=== FIM DO ATENDIMENTO ===
Total atendidos: 8
- Prioritários: 3
- Normais: 5
```

---

## Exercício 6 (Difícil) - Processador de Tarefas com Prioridade Personalizada

Crie um sistema de tarefas onde cada tarefa tem:
- Nome da tarefa (String)
- Prioridade: 1 (Alta), 2 (Média), 3 (Baixa)

A tarefa com prioridade 1 deve ser executada primeiro. Se duas tarefas tiverem a mesma prioridade, a que entrou primeiro deve ser executada primeiro.

**Tarefas:**
1. Criar uma classe `Tarefa` com os atributos `nome`, `prioridade` e `timestamp` (ou um contador)
2. Implementar `Comparable<Tarefa>` para ordenar por prioridade (menor primeiro) e depois por ordem de chegada
3. Criar uma `PriorityQueue<Tarefa>`
4. Adicionar pelo menos 5 tarefas com prioridades variadas e fora de ordem
5. Processar (remover) todas as tarefas e exibir a ordem de execução

**Exemplo de saída:**
```
Tarefas adicionadas:
1. Fazer backup (Prioridade: 2)
2. Reiniciar servidor (Prioridade: 1)
3. Limpar logs (Prioridade: 3)
4. Atualizar sistema (Prioridade: 1)
5. Responder emails (Prioridade: 2)

=== EXECUTANDO TAREFAS ===
1. Reiniciar servidor (Prioridade: 1)
2. Atualizar sistema (Prioridade: 1)
3. Fazer backup (Prioridade: 2)
4. Responder emails (Prioridade: 2)
5. Limpar logs (Prioridade: 3)
```

---

## Exercício 7 (Desafio) - Fila de Impressão com Cancelamento

Simule uma fila de impressão onde os usuários podem:
- Adicionar documento (nome do documento + nome do usuário)
- Imprimir próximo (remove e exibe o documento)
- Cancelar documento (remove um documento específico da fila, mesmo que não seja o primeiro)
- Exibir fila completa

**Tarefas:**
1. Criar uma classe `Documento` com atributos: `id` (auto-incremento), `nomeDocumento`, `usuario`
2. Usar `LinkedList` como `Queue` (pois precisa de acesso a elementos do meio)
3. Implementar as 4 operações em um menu
4. O cancelamento deve encontrar o documento por `id` e removê-lo da fila (pesquisar e remover)
5. Tratar casos de fila vazia e documento não encontrado

**Exemplo de interação:**
```
--- FILA DE IMPRESSÃO ---
1 - Adicionar documento
2 - Imprimir próximo
3 - Cancelar documento
4 - Ver fila
5 - Sair
Escolha: 1
Nome do documento: Relatorio.pdf
Usuário: João
Documento #1 adicionado: Relatorio.pdf (João)

Escolha: 1
Nome do documento: Planilha.xlsx
Usuário: Maria
Documento #2 adicionado: Planilha.xlsx (Maria)

Escolha: 4
Fila: [1-Relatorio.pdf(João), 2-Planilha.xlsx(Maria)]

Escolha: 3
ID do documento a cancelar: 1
Documento #1 cancelado: Relatorio.pdf (João)

Escolha: 4
Fila: [2-Planilha.xlsx(Maria)]
```

---

## Exercício 8 (Desafio) - Call Center com Níveis de Atendimento

Simule um call center com 3 níveis de atendimento:
- Nível 1 (primeiro atendimento)
- Nível 2 (escalação técnica)
- Nível 3 (escalação avançada)

**Regras:**
1. As chamadas entram no Nível 1
2. Se não for resolvida no Nível 1, vai para o Nível 2
3. Se não for resolvida no Nível 2, vai para o Nível 3
4. Cada nível demora um "tempo" diferente para tentar resolver
5. No final, exibir estatísticas

**Tarefas:**
1. Criar uma classe `Chamada` com: `id`, `descricao`, `nivelAtual`
2. Criar 3 filas (uma para cada nível)
3. Adicionar 5 chamadas na fila do Nível 1
4. Simular o processamento:
   - Atender chamada do Nível 1 → se resolvida (random 70%), finaliza; senão, vai para Nível 2
   - Atender chamada do Nível 2 → se resolvida (random 50%), finaliza; senão, vai para Nível 3
   - Atender chamada do Nível 3 → sempre resolvida (100%)
5. Contar quantas chamadas foram resolvidas em cada nível
6. Exibir estatísticas finais

**Exemplo de saída:**
```
=== SIMULAÇÃO CALL CENTER ===

Atendendo Chamada #1: Problema com internet
  Nível 1: RESOLVIDA

Atendendo Chamada #2: Erro no sistema
  Nível 1: NÃO RESOLVIDA → escalando para Nível 2
  Nível 2: RESOLVIDA

Atendendo Chamada #3: Banco de dados lento
  Nível 1: RESOLVIDA

=== ESTATÍSTICAS FINAIS ===
Chamadas resolvidas no Nível 1: 2
Chamadas resolvidas no Nível 2: 1
Chamadas resolvidas no Nível 3: 0
Total de chamadas: 3
```

---

## Dicas para os Exercícios

| Exercício | Dicas importantes |
|-----------|-------------------|
| **1 e 2** | Use `offer()`, `poll()`, `isEmpty()`, `size()` |
| **3** | Use `Scanner.nextLine()`, `equals()` para comparar Strings, loop `while(true)` com `break` |
| **4** | Use `Random.nextInt(100) + 1`, observe que `PriorityQueue` pode não parecer ordenada ao imprimir |
| **5** | Use variáveis de controle ou lógica de alternância, trate filas vazias |
| **6** | Implemente `Comparable<Tarefa>`, use `System.currentTimeMillis()` para timestamp |
| **7** | Use `LinkedList` (não `Queue` interface) para acessar métodos como `remove(Object o)` |
| **8** | Use `Random` para simular resolução, crie enum ou constantes para os níveis |

---

## Checklist de Verificação

Antes de considerar um exercício completo, verifique se você:

- [ ] Importou as classes corretas (`java.util.Queue`, `java.util.LinkedList`, `java.util.ArrayDeque`, etc.)
- [ ] Escolheu a implementação apropriada (`ArrayDeque` para FIFO geral, `PriorityQueue` para prioridade)
- [ ] Usou `offer()` para adicionar e `poll()` para remover (não `add()` e `remove()`)
- [ ] Verificou se a fila está vazia antes de chamar `peek()` ou `element()`
- [ ] Testou os casos extremos (fila vazia, remover elemento inexistente)
- [ ] Fechou o `Scanner` no final do programa

---

Bons estudos! Se precisar das soluções dos exercícios, é só pedir.
