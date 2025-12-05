---

# 📘 **Resumo — Classificação de Programas em Python pela Complexidade de Algoritmos**

## 🔹 1. O que é Complexidade de Algoritmos?

A complexidade mede **quanto tempo** e **quanta memória** um algoritmo consome em função do tamanho da entrada **n**.
Usamos **Big-O** para o **pior caso** (mais comum em relatórios). Ignore constantes e termos menores.

* **Tempo (Time Complexity)** → número de operações em função de `n`
* **Espaço (Space Complexity)** → memória adicional além da entrada

---

## 🔹 2. Regras práticas (Python-aware)

* Acesso a índice de lista → **O(1)**.
* Iterar `for x in lista` (ou `range(n)`) → **O(n)**.
* Loops aninhados → multiplicam (ex.: **O(n²)**).
* Dividir o problema pela metade → **O(log n)** (ex.: busca binária).
* Operações de dicionário (`dict`) e conjunto (`set`) → **O(1)** amortizado para inserção/lookup.
* List comprehensions têm mesma complexidade do loop equivalente.
* Recursão → monte a recorrência; use análise de árvore de recursão ou Teorema Mestre quando aplicável.

---

## 🔹 3. Exemplos clássicos em Python (com comentários)

### 1) Busca linear — **O(n)**, espaço O(1)

```python
def linear_search(a, x):
    for i, val in enumerate(a):  # O(n)
        if val == x:             # O(1)
            return i
    return -1
```

### 2) Busca binária (iterativa) — **O(log n)**, espaço O(1)

```python
def binary_search(a, x):
    l, r = 0, len(a) - 1
    while l <= r:                # reduz intervalo pela metade → O(log n)
        m = (l + r) >> 1
        if a[m] == x:
            return m
        if a[m] < x:
            l = m + 1
        else:
            r = m - 1
    return -1
```

### 3) Bubble sort — **O(n²)**, espaço O(1)

```python
def bubble_sort(a):
    n = len(a)
    for i in range(n):
        for j in range(0, n - 1 - i):
            if a[j] > a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]
```

### 4) Merge sort — **O(n log n)**, espaço O(n)

```python
def merge_sort(a):
    if len(a) <= 1:
        return a
    mid = len(a) // 2
    left = merge_sort(a[:mid])    # cópia cria espaço O(n)
    right = merge_sort(a[mid:])
    return merge(left, right)
```

### 5) Fibonacci recursivo ingênuo — **O(2^n)**

```python
def fib(n):
    if n <= 1:
        return n
    return fib(n-1) + fib(n-2)  # chama duas subproblemas → exponencial
```

---

## 🔹 4. Complexidade de estruturas Python

| Estrutura                    | Operação                 | Complexidade        |
| ---------------------------- | ------------------------ | ------------------- |
| `list`                       | acesso por índice        | **O(1)**            |
| `list.insert(0, x)`          | inserir no início        | **O(n)**            |
| `append` / `pop()` no fim    | O(1) amortizado          |                     |
| `dict`                       | lookup / insert / delete | **O(1)** amortizado |
| `set`                        | add / lookup / remove    | **O(1)** amortizado |
| `heapq` (fila de prioridade) | push/pop                 | **O(log n)**        |

> Fonte prática: comportamento observado nas estruturas internas (listas são arrays dinâmicos, `dict` com hashing).

---

## 🔹 5. Melhor / Médio / Pior caso

Explique os três quando pertinente.
Ex.: `list.index(x)` → melhor O(1), pior O(n), médio O(n).

---

## 🔹 6. Complexidade de espaço (Python specifics)

* Strings e slices podem criar cópias: `s[:]` é O(n) espaço.
* List comprehensions criam uma lista inteira (O(n) espaço); use generator expressions (`(...)`) para O(1) adicional.
* Funções recursivas: pilha da linguagem (recursion depth) conta como O(depth).
* Módulos úteis para medir memória: `memory_profiler`, `tracemalloc`.

---

## 🔹 7. Como medir empiricamente (Python)

### Tempo simples (não-recomendado para micro-benchmarks)

```python
import time
t0 = time.perf_counter()
algoritmo(data)
dt = time.perf_counter() - t0
print(f"Tempo (s): {dt:.6f}")
```

### Melhor prática: `timeit` para microbenchmarks

```python
import timeit
timeit.timeit("func()", setup="from mymod import func", number=1000)
```

### Benchmark profissional: `pytest-benchmark` ou `asv` para projetos

### Profiling de CPU: `cProfile` + `pstats`

```bash
python -m cProfile -o out.prof meu_programa.py
python -m pstats out.prof
```

### Memória: `tracemalloc` (builtin) ou `memory_profiler`

```python
import tracemalloc
tracemalloc.start()
# ... executar
print(tracemalloc.get_traced_memory())
tracemalloc.stop()
```

---

## 🔹 8. Checklist para análise de um código Python

1. Identifique **n** (len(lista), tamanho da string, número de nós, etc.).
2. Conte loops e profundidade (aninhamentos).
3. Verifique operações sobre coleções (hashing, slicing, cópias).
4. Analise recursões (escreva recorrência).
5. Avalie uso de memória (listas auxiliares, slices, comprehensions).
6. Indique melhor/médio/pior caso quando pertinente.
7. Se medir, faça várias repetições, descarte warm-up e atente ao GC/JIT (PyPy vs CPython).

---

## 🔹 9. Template pronto (use em relatórios / provas)

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

Observações práticas (Python):
- Uso de slices? (cria cópia)
- Uso de dict/set? (hashing O(1) amortizado)
- Recursão -> possível limite de recursão (recursion depth)
```

---

## 🔹 10. Dicas de otimização em Python

* Prefira **built-ins** e bibliotecas otimizadas (e.g., `bisect`, `heapq`, `itertools`, `collections`).
* Use **generator expressions** para evitar listas temporárias.
* Evite cópias desnecessárias de listas/strings; prefira índices ou `deque` quando necessário.
* Use profiling para encontrar *hot spots* (ex.: `cProfile`, `line_profiler`).
* Para computação intensiva, considere **NumPy**, **Cython**, ou mover para módulos em C.

---

## 🔹 11. Tabela rápida de complexidades (recapitulando)

| Complexidade   | Nome        |
| -------------- | ----------- |
| **O(1)**       | constante   |
| **O(log n)**   | logarítmica |
| **O(n)**       | linear      |
| **O(n log n)** | linear-log  |
| **O(n²)**      | quadrática  |
| **O(2^n)**     | exponencial |

---
