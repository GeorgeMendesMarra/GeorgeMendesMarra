# 🧠 Introdução à Notação Big O de Complexidade de Algoritmos

## 📘 O que é a Notação Big O?

A **notação Big O** é usada para **medir a eficiência de um algoritmo** em termos de **tempo de execução** e **uso de memória**.  
Ela descreve **como o desempenho do algoritmo muda** conforme o tamanho da entrada (n) aumenta.

Big O nos ajuda a responder perguntas como:
- O algoritmo continua rápido se eu dobrar o tamanho dos dados?
- Qual é o limite de escalabilidade da minha solução?
- Vale a pena otimizar este trecho de código?

---

## ⚙️ Conceito Central

A ideia é observar **a taxa de crescimento** do número de operações, ignorando fatores como:
- O hardware utilizado;
- A linguagem de programação;
- Constantes pequenas ou termos menos relevantes.

Em outras palavras, Big O foca **no comportamento assintótico** — o que acontece quando **n → ∞**.

---

## 📊 Tabela de Crescimento das Complexidades

| Complexidade | Descrição | Crescimento | Exemplo típico |
|---------------|------------|--------------|----------------|
| **O(1)** | Constante | Sempre o mesmo tempo | Acesso direto a um elemento de array |
| **O(log n)** | Logarítmica | Cresce lentamente | Busca binária |
| **O(n)** | Linear | Cresce proporcionalmente a n | Percorrer lista |
| **O(n log n)** | Linearítmica | Quase linear | Merge Sort, Quick Sort |
| **O(n²)** | Quadrática | Cresce rapidamente | Bubble Sort, Selection Sort |
| **O(2ⁿ)** | Exponencial | Crescimento explosivo | Força bruta |
| **O(n!)** | Fatorial | Inviável para grandes n | Permutações completas |

---

## 📈 Comparativo Numérico de Crescimento

| n | O(1) | O(log n) | O(n) | O(n log n) | O(n²) | O(2ⁿ) |
|---|-------|-----------|------|-------------|-------|--------|
| 1 | 1 | 0 | 1 | 0 | 1 | 2 |
| 2 | 1 | 1 | 2 | 2 | 4 | 4 |
| 4 | 1 | 2 | 4 | 8 | 16 | 16 |
| 8 | 1 | 3 | 8 | 24 | 64 | 256 |
| 16 | 1 | 4 | 16 | 64 | 256 | 65.536 |
| 32 | 1 | 5 | 32 | 160 | 1.024 | 4.294.967.296 |

📍 **Conclusão visual:** conforme *n* cresce, algoritmos quadráticos e exponenciais tornam-se rapidamente inviáveis.

---

## 💡 Exemplos Práticos de Código

| Situação | Código (simplificado) | Complexidade |
|-----------|-----------------------|---------------|
| Acesso direto em array | `x = vetor[i];` | **O(1)** |
| Percorrer lista | `for(i=0;i<n;i++)` | **O(n)** |
| Loop duplo aninhado | `for(i=0;i<n;i++) for(j=0;j<n;j++)` | **O(n²)** |
| Busca binária | `while(inicio <= fim)` | **O(log n)** |
| Merge Sort | `divide e conquista` | **O(n log n)** |

---

## 📘 Resumo Visual

| Categoria | Exemplo de Algoritmo | Tipo de Crescimento | Escalabilidade |
|------------|----------------------|---------------------|----------------|
| Excelente | O(1), O(log n) | Muito eficiente | ✅ Alta |
| Boa | O(n), O(n log n) | Aceitável | ⚙️ Média |
| Ruim | O(n²) | Pesada | ⚠️ Baixa |
| Péssima | O(2ⁿ), O(n!) | Explosiva | ❌ Inviável |

---

## 🧠 Conclusão

A **notação Big O** é uma das ferramentas mais importantes da análise de algoritmos.  
Ela permite:
- Avaliar o desempenho de forma independente da linguagem;
- Prever a escalabilidade de sistemas;
- Comparar soluções e escolher a mais eficiente.

Dominar Big O é essencial para o raciocínio lógico, a otimização de código e o design de sistemas escaláveis.

---
