# 📊 Medidas de Complexidade de Algoritmos (Visual)

Na disciplina de **Complexidade de Algoritmos**, analisamos:

- **Complexidade de tempo**: quanto tempo o algoritmo leva para processar uma entrada de tamanho n.
- **Complexidade de espaço**: quanto espaço de memória o algoritmo consome.
- **Caso de entrada**: melhor, pior ou caso médio.
- **Outras métricas**: comparações, trocas, operações de I/O.

---

## 1️⃣ Complexidade de Tempo

| Tipo | Notação | Descrição | Exemplo |
|------|---------|-----------|---------|
| Pior caso | O(f(n)) | Tempo máximo possível | Busca linear sem o elemento |
| Melhor caso | Ω(f(n)) | Tempo mínimo possível | Busca linear encontra na 1ª posição |
| Caso médio | Θ(f(n)) | Tempo esperado considerando todas as entradas | Busca linear com elemento aleatório |

---

## 2️⃣ Complexidade de Espaço

| Tipo de uso | Descrição | Exemplo |
|-------------|-----------|---------|
| Variáveis locais | Memória usada por variáveis simples | Soma de elementos → O(1) |
| Estruturas de dados | Memória para arrays, listas, pilhas etc. | Matriz n x n → O(n²) |
| Memória auxiliar | Variáveis temporárias ou buffers | Merge Sort → O(n) |

---

## 3️⃣ Outras Medidas Relacionadas

| Medida | Descrição | Exemplo |
|--------|-----------|---------|
| Comparações | Quantas comparações são feitas | Selection Sort |
| Trocas | Quantas movimentações de dados | Bubble Sort |
| Operações de I/O | Entrada/Saída de dados | Leitura de arquivos grandes |

---

## 4️⃣ Crescimento Comum de Algoritmos

| Crescimento | Notação Big O | Exemplo |
|------------|----------------|--------------------|
| Constante | O(1) | Acesso a elemento de array |
| Logarítmico | O(log n) | Busca binária |
| Linear | O(n) | Percorrer lista |
| Linearitmo | O(n log n) | Merge Sort, Quick Sort |
| Quadrático | O(n²) | Bubble Sort, Selection Sort |
| Exponencial | O(2ⁿ) | Problema do Caixeiro Viajante |
| Fatorial | O(n!) | Gerar todas as permutações |

---

## 5️⃣ Comparativo Numérico

| n  | O(1) | O(log n) | O(n) | O(n log n) | O(n²) | O(2ⁿ) |
|----|-------|-----------|------|-------------|-------|--------|
| 1  | 1     | 0         | 1    | 0           | 1     | 2      |
| 2  | 1     | 1         | 2    | 2           | 4     | 4      |
| 4  | 1     | 2         | 4    | 8           | 16    | 16     |
| 8  | 1     | 3         | 8    | 24          | 64    | 256    |
| 16 | 1     | 4         | 16   | 64          | 256   | 65.536 |
| 32 | 1     | 5         | 32   | 160         | 1.024 | 4.294.967.296 |

---

## 6️⃣ Gráfico Conceitual de Crescimento (ASCII)

