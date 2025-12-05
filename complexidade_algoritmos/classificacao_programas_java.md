---

# 📘 **Resumo — Classificação de Programas em Java pela Complexidade de Algoritmos**

## 🔹 1. O que é Complexidade de Algoritmos?

A complexidade mede **quanto tempo** e **quanta memória** um algoritmo consome em função do tamanho da entrada **n**.
É usada para comparar desempenho independentemente do hardware.

### Tipos:

* **Tempo (Time Complexity)** → quantidade de operações
* **Espaço (Space Complexity)** → memória extra usada

Usamos a **Notação Big-O**, ignorando constantes e termos menores.

---

## 🔹 2. Regras Práticas para Encontrar o Big-O

### 📌 Operações básicas → **O(1)**

Acessos a índices, atribuições, operações matemáticas simples.

### 📌 Laço simples percorrendo n → **O(n)**

```java
for (int i = 0; i < n; i++) {}
```

### 📌 Laços aninhados → multiplicam → **O(n²)**

```java
for (i …) for (j …) {}
```

### 📌 Redução pela metade → **O(log n)**

```java
while (n > 1) n = n / 2;
```

### 📌 Laço + redução → **O(n log n)**

(ordenadores eficientes — mergesort, quicksort)

### 📌 Recursão exponencial → **O(2^n)**

(ex.: Fibonacci recursivo puro)

---

## 🔹 3. Complexidade Comum em Estruturas Java

| Estrutura         | Operação          | Complexidade        |
| ----------------- | ----------------- | ------------------- |
| Array             | acesso por índice | **O(1)**            |
| ArrayList         | inserção final    | **O(1)** amortizado |
| LinkedList        | busca             | **O(n)**            |
| HashMap / HashSet | inserir/buscar    | **O(1)** amortizado |
| TreeMap / TreeSet | inserir/buscar    | **O(log n)**        |
| PriorityQueue     | inserir/remover   | **O(log n)**        |

---

## 🔹 4. Exemplos Clássicos em Java

### ✔ Busca Linear — **O(n)**

```java
for (int i = 0; i < a.length; i++)
    if (a[i] == x) return i;
```

### ✔ Busca Binária — **O(log n)**

```java
int l=0, r=n-1;
while (l <= r) {
    int m=(l+r)/2;
    if (a[m]==x) return m;
    if (a[m] < x) l=m+1; else r=m-1;
}
```

### ✔ Bubble Sort — **O(n²)**

### ✔ Merge Sort — **O(n log n)** (tempo), **O(n)** (espaço)

Recorrência:
**T(n) = 2T(n/2) + O(n)** → Teorema Mestre → **Θ(n log n)**

### ✔ Fibonacci Recursivo — **O(2^n)**

```java
return fib(n-1) + fib(n-2);
```

---

## 🔹 5. Melhor, Pior e Caso Médio

### Exemplo: busca em array desordenado

* **Melhor:** O(1) (achou na 1ª posição)
* **Pior:** O(n)
* **Médio:** O(n)

### Exemplo: quicksort

* **Melhor:** O(n log n)
* **Pior:** O(n²) (pivot ruim)
* **Médio:** O(n log n)

---

## 🔹 6. Complexidade de Espaço

| Algoritmo           | Espaço Extra                 |
| ------------------- | ---------------------------- |
| Busca linear        | O(1)                         |
| Quick sort          | O(log n) (pilha de recursão) |
| Merge sort          | O(n)                         |
| Fibonacci recursivo | O(n)                         |

---

## 🔹 7. Passo a Passo para Classificar um Programa em Java

1. **Identifique n** (tamanho da entrada).
2. **Conte loops** e determine a profundidade.
3. **Analise recursão** → escreva a recorrência.
4. **Localize operações dominantes**.
5. **Ignore constantes** e termos menores.
6. **Indique melhor/médio/pior caso** quando relevante.
7. **Calcule espaço extra** (arrays, pilha, estruturas).

---

## 🔹 8. Tabela Resumo Big-O

| Complexidade   | Nome        | Comportamento              |
| -------------- | ----------- | -------------------------- |
| **O(1)**       | constante   | melhor possível            |
| **O(log n)**   | logarítmica | cresce devagar             |
| **O(n)**       | linear      | escala com tamanho         |
| **O(n log n)** | linearlog   | ótimo para ordenação       |
| **O(n²)**      | quadrática  | ruim para entradas grandes |
| **O(2^n)**     | exponencial | inaceitável para n ≥ 40    |
| **O(n!)**      | fatorial    | impraticável               |

---

## 🔹 9. Modelo Pronto para Classificar Qualquer Código

Use esse bloco nas suas correções ou relatórios:

```
Algoritmo: ______________________

Entrada: tamanho n = ____________

Complexidade de Tempo:
- Melhor caso: O(_____)
- Médio caso: O(_____)
- Pior caso: O(_____)
Justificativa: ____________________________________

Complexidade de Espaço:
- Espaço extra: O(_____)
Justificativa: ____________________________________
```

---
