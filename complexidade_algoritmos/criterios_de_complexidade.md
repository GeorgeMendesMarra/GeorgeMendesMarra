---

# 📊 Complexidade de Algoritmos — Critérios e Crescimento

---

## 1️⃣ Critérios de Complexidade

### 1.1 Critérios de Tempo

| Critério | Notação | Descrição | Exemplo |
|----------|---------|-----------|---------|
| Pior Caso (Worst-case) | O(f(n)) | Tempo máximo possível | Busca linear sem elemento |
| Melhor Caso (Best-case) | Ω(f(n)) | Tempo mínimo possível | Busca linear encontra elemento na 1ª posição |
| Caso Médio (Average-case) | Θ(f(n)) | Tempo esperado considerando todas as entradas | Busca linear com elemento aleatório |
| Comparações | — | Quantas comparações são feitas | Selection Sort: n*(n-1)/2 |
| Movimentações/Trocas | — | Quantas trocas/movimentos de dados | Bubble Sort |

### 1.2 Critérios de Espaço

| Critério | Descrição | Exemplo |
|----------|-----------|---------|
| Variáveis Locais | Memória de variáveis simples ou temporárias | Soma de elementos → O(1) |
| Estruturas de Dados | Arrays, listas, matrizes, árvores | Matriz n x n → O(n²) |
| Espaço Auxiliar | Buffers, pilhas, recursão | Merge Sort → O(n) |

### 1.3 Outros Critérios

| Critério | Descrição | Exemplo |
|----------|-----------|---------|
| Operações de I/O | Leituras e gravações de dados | Processamento de arquivos grandes |
| Complexidade Algorítmica | Relação entre n e número de operações | Ordenação, busca |
| Escalabilidade | Como o algoritmo cresce conforme n aumenta | O(n log n) mais escalável que O(n²) |

---

## 2️⃣ Crescimento Comum de Algoritmos

| Crescimento | Notação Big O | Exemplo | Emoji |
|------------|----------------|--------------------|-------|
| Constante | O(1) | Acesso a array | 🟢 |
| Logarítmico | O(log n) | Busca binária | 🟢🟢 |
| Linear | O(n) | Percorrer lista | 🟡 |
| Linearitmo | O(n log n) | Merge Sort, Quick Sort | 🟡🟡 |
| Quadrático | O(n²) | Bubble Sort, Selection Sort | 🔴 |
| Exponencial | O(2ⁿ) | Força bruta | 🔴🔴 |
| Fatorial | O(n!) | Permutações completas | 💀 |

---

## 3️⃣ Gráfico Conceitual de Crescimento (ASCII Proporcional)

```text
Tempo
│
│                           O(n!) 💀
│                           *
│
│                        O(2ⁿ) 🔴🔴
│                        **
│
│                     O(n²) 🔴
│                     ****
│
│                O(n log n) 🟡🟡
│                ******
│
│           O(n) 🟡
│           ********
│
│       O(log n) 🟢🟢
│       ************
│
│ O(1) 🟢
│       ****************
└─────────────────────────────────────────► n (tamanho da entrada)
     1     2     4     8     16    32    64
````

📍 **Observação:** Algoritmos eficientes crescem lentamente (O(1), O(log n)), enquanto O(n²), O(2ⁿ) e O(n!) explodem rapidamente com o aumento de n.

---

## 4️⃣ Resumo Visual de Escalabilidade

| Categoria | Tempo            | Espaço       | Caso de Entrada     | Emoji |
| --------- | ---------------- | ------------ | ------------------- | ----- |
| Excelente | O(1), O(log n)   | O(1)         | Melhor, Médio, Pior | 🟢    |
| Boa       | O(n), O(n log n) | O(n)         | Caso Médio          | 🟡    |
| Ruim      | O(n²)            | O(n²)        | Pior Caso           | 🔴    |
| Péssima   | O(2ⁿ), O(n!)     | O(2ⁿ), O(n!) | Pior Caso           | 💀    |

---
