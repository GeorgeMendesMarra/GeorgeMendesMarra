# Exercícios sobre Listas em Java

---

## Exercício 1 (Fácil) - Lista de Cores

Crie uma lista (`ArrayList`) de 5 cores e exiba todas usando o laço `for-each`.

**Tarefas:**
1. Importar `java.util.ArrayList`
2. Criar um `ArrayList<String>` chamado `cores`
3. Adicionar 5 cores diferentes
4. Exibir todas as cores usando `for (String cor : cores)`

---

## Exercício 2 (Fácil) - Soma de Números

Crie um programa que adiciona os números de 1 a 10 em uma lista e depois calcula e exibe a soma total.

**Tarefas:**
1. Criar um `ArrayList<Integer>`
2. Adicionar os números de 1 a 10 usando um laço `for`
3. Calcular a soma percorrendo a lista
4. Exibir: `"A soma dos números é: X"`

---

## Exercício 3 (Médio) - Cadastro até "fim"

Faça um programa que recebe nomes do usuário (via `Scanner`) até que ele digite `"fim"`. Depois exiba todos os nomes cadastrados.

**Tarefas:**
1. Criar um `ArrayList<String>` para armazenar os nomes
2. Usar um laço `while (true)` para ler nomes
3. Se o usuário digitar `"fim"`, interromper o laço com `break`
4. Caso contrário, adicionar o nome à lista
5. Após sair, exibir todos os nomes (um por linha)

---

## Exercício 4 (Médio) - Removendo números pequenos

Crie uma lista de números inteiros e remova todos os números **menores que 10**.

**Tarefas:**
1. Criar uma lista com os valores: `5, 15, 3, 20, 8, 25, 2, 30`
2. Percorrer a lista (cuidado com a remoção durante iteração)
3. Remover todos os elementos que são menores que 10
4. Exibir a lista antes e depois da remoção

**Dica:** Para remover durante iteração, percorra a lista de **trás para frente**:
```java
for (int i = lista.size() - 1; i >= 0; i--) {
    if (lista.get(i) < 10) {
        lista.remove(i);
    }
}
```

---

## Exercício 5 (Médio/Difícil) - Cadastro de Produtos

Faça um cadastro de produtos com **nome** e **preço**. Depois calcule o valor total do carrinho.

**Tarefas:**
1. Criar um `ArrayList<String>` para os nomes dos produtos
2. Criar um `ArrayList<Double>` para os preços dos produtos
3. Adicionar pelo menos 3 produtos manualmente (ou via teclado)
4. Calcular o valor total do carrinho (soma dos preços)
5. Exibir um relatório:
   - Produto 1: [nome] - R$ [preço]
   - Produto 2: [nome] - R$ [preço]
   - ...
   - Total: R$ [soma]

---

## Exercício 6 (Difícil) - Agenda de Contatos

Crie uma agenda de contatos onde cada contato tem **nome** e **telefone**. Permita as operações:

1. Adicionar contato
2. Buscar contato (por nome)
3. Remover contato (por nome)
4. Listar todos os contatos
5. Sair

**Tarefas:**
1. Criar dois `ArrayList`s: `nomes` e `telefones` (mesmo índice para o mesmo contato)
2. Usar um menu com `do-while` e `switch-case`
3. Implementar cada opção:
   - **Adicionar:** pedir nome e telefone, adicionar às listas
   - **Buscar:** pedir nome, procurar e exibir telefone
   - **Remover:** pedir nome, encontrar a posição e remover das duas listas
   - **Listar:** exibir todos os contatos (número, nome, telefone)

**Exemplo de menu:**
```
--- AGENDA DE CONTATOS ---
1 - Adicionar contato
2 - Buscar contato
3 - Remover contato
4 - Listar todos
5 - Sair
Escolha: _
```

---

## Exercício 7 (Desafio) - Gerenciador de Tarefas com Prioridade

Crie um gerenciador de tarefas onde cada tarefa tem **descrição** (String) e **prioridade** (1 = Alta, 2 = Média, 3 = Baixa).

**Tarefas:**
1. Criar duas listas: `descricoes` (String) e `prioridades` (Integer)
2. Implementar as operações:
   - Adicionar tarefa (descrição + prioridade)
   - Listar tarefas por prioridade (primeiro as prioridade 1, depois 2, depois 3)
   - Marcar tarefa como concluída (remover da lista)
   - Sair

**Exemplo de saída:**
```
--- MINHAS TAREFAS ---
=== PRIORIDADE ALTA ===
1. Estudar Java (Pri: 1)
2. Fazer exercícios (Pri: 1)

=== PRIORIDADE MÉDIA ===
3. Comprar pão (Pri: 2)

=== PRIORIDADE BAIXA ===
4. Assistir série (Pri: 3)
```

---

## Exercício 8 (Desafio) - Remover Duplicatas

Crie um programa que recebe números do usuário até que ele digite `0` e armazena todos em uma lista. Depois, remova todos os números **duplicados**, mantendo apenas a primeira ocorrência de cada número.

**Exemplo:**
```
Entrada: 5, 3, 5, 2, 5, 7, 3, 8
Lista final: [5, 3, 2, 7, 8]
```

**Dica:** Verifique para cada elemento se ele já apareceu antes na lista.

---

## Exercício 9 (Desafio) - Filas usando LinkedList

Use `LinkedList` para simular uma **fila de atendimento** (FIFO - First In, First Out).

**Operações:**
1. Adicionar pessoa à fila (enfileirar)
2. Chamar próxima pessoa (desenfileirar) - remove e exibe o nome
3. Exibir quantas pessoas estão na fila
4. Exibir a fila completa
5. Sair

**Dica:** `LinkedList` possui métodos `addLast()` (adicionar no final) e `removeFirst()` (remover do início).

---

## Exercício 10 (Super Desafio) - Lista de Tarefas com Arquivo

Crie um programa de lista de tarefas que **salva e carrega** as tarefas de um arquivo.

**Tarefas:**
1. Ao iniciar, carregar tarefas de um arquivo `tarefas.txt`
2. Operações: adicionar, remover, listar, concluir
3. Ao sair, salvar todas as tarefas no arquivo

**Dica:** Use `FileWriter` e `BufferedReader` para ler/escrever o arquivo, cada tarefa em uma linha.

---

# Dicas Gerais

| Dica | Explicação |
|------|------------|
| 📌 **Importe no início** | `import java.util.ArrayList;` |
| 📌 **Use tipos Objeto** | `ArrayList<Integer>` não `ArrayList<int>` |
| 📌 **Teste cada operação** | Execute o programa após cada nova funcionalidade |
| 📌 **Use print para debug** | Use `System.out.println()` para ver o que está acontecendo |
| 📌 **Consulte os métodos** | `add()`, `get()`, `remove()`, `size()`, `isEmpty()`, `contains()`, `indexOf()` |

---
