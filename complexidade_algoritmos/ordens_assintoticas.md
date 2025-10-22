# 📊 Ordens Assintóticas de Algoritmos

Na análise de algoritmos, **ordens assintóticas** descrevem **como o tempo de execução ou o espaço consumido cresce** conforme o tamanho da entrada (n) aumenta.  
Ela permite **comparar algoritmos independentemente do hardware ou linguagem**.

---

## 1️⃣ Principais Notações

| Notação | Símbolo | Descrição | Exemplo |
|---------|---------|-----------|---------|
| **O grande (Big O)** | O(f(n)) | Limite superior (pior caso) | Busca linear: O(n) |
| **Ômega (Big Ω)** | Ω(f(n)) | Limite inferior (melhor caso) | Busca linear encontra na 1ª posição: Ω(1) |
| **Theta (Big Θ)** | Θ(f(n)) | Limite apertado (caso médio ou exato) | Busca linear com elemento aleatório: Θ(n) |

---

## 2️⃣ Ordens Assintóticas Comuns

| Ordem | Notação Big O | Descrição | Exemplo |
|-------|---------------|-----------|---------|
| Constante | O(1) | Tempo não depende do tamanho da entrada | Acesso a um elemento de array |
| Logarítmica | O(log n) | Cresce lentamente | Busca binária |
| Linear | O(n) | Cresce proporcional ao tamanho da entrada | Percorrer lista |
| Linearitmo | O(n log n) | Quase linear | Merge Sort, Quick Sort |
| Quadrática | O(n²) | Cresce rapidamente | Bubble Sort, Selection Sort |
| Exponencial | O(2ⁿ) | Crescimento muito rápido | Problema do Caixeiro Viajante (força bruta) |
| Fatorial | O(n!) | Inviável para grandes n | Permutações completas |

---

## 3️⃣ Representação Gráfica Conceitual (ASCII)

```text
Tempo
│
│                          O(n!) 💀
│                        O(2ⁿ) 🔴🔴
│                     O(n²) 🔴
│                O(n log n) 🟡🟡
│           O(n) 🟡
│       O(log n) 🟢🟢
│ O(1) 🟢
└─────────────────────────────► n (tamanho da entrada)
