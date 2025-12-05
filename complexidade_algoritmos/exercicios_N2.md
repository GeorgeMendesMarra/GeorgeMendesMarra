---

# 📘 **Lista de Exercícios — Análise de Complexidade (Python e Java)**

### 📚 *Com gabarito ao final*

---

# 🔶 **PARTE A — Exercícios em Python**

## **1) (Básico)**

Qual é a complexidade de tempo do algoritmo abaixo?

```python
def func(a):
    for x in a:
        print(x)
```

---

## **2) (Básico)**

Determine o tempo e o espaço do código:

```python
def soma(a):
    s = 0
    for x in a:
        s += x
    return s
```

---

## **3) (Intermediário)**

Analise o algoritmo:

```python
def dupla(a):
    for i in range(len(a)):
        for j in range(len(a)):
            print(a[i], a[j])
```

---

## **4) (Intermediário)**

Qual a complexidade do slicing utilizado?

```python
def corte(a):
    b = a[:len(a)//2]
    return b
```

---

## **5) (Avançado — Recursão)**

Classifique a complexidade do algoritmo:

```python
def f(n):
    if n <= 1:
        return 1
    return f(n-1) + f(n-1)
```

---

## **6) (Avançado — Busca Binária)**

Analise:

```python
def busca(a, x):
    l, r = 0, len(a)-1
    while l <= r:
        m = (l+r)//2
        if a[m] == x:
            return m
        elif a[m] < x:
            l = m+1
        else:
            r = m-1
```

---

---

# 🔷 **PARTE B — Exercícios em Java**

## **7) (Básico)**

Qual a complexidade do trecho?

```java
for (int i = 0; i < n; i++) {
    System.out.println(i);
}
```

---

## **8) (Intermediário)**

Analise:

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < i; j++) {
        System.out.println(i + j);
    }
}
```

---

## **9) (Intermediário — Lista Encadeada)**

Qual é a complexidade?

```java
public int busca(Node head, int x) {
    Node atual = head;
    while (atual != null) {
        if (atual.valor == x)
            return 1;
        atual = atual.proximo;
    }
    return -1;
}
```

---

## **10) (Avançado — Merge Sort)**

Determine tempo e espaço:

```java
void mergeSort(int[] a) {
    if (a.length <= 1) return;

    int mid = a.length / 2;
    int[] left = Arrays.copyOfRange(a, 0, mid);
    int[] right = Arrays.copyOfRange(a, mid, a.length);

    mergeSort(left);
    mergeSort(right);
    merge(left, right, a);
}
```

---

## **11) (Avançado — Recursão Dupla)**

Classifique:

```java
int g(int n) {
    if (n == 1) return 1;
    return g(n-1) + g(n-2) + g(n-3);
}
```

---

## **12) (Profissional — HashMap)**

Dê a complexidade das operações:

```java
HashMap<Integer, String> map = new HashMap<>();
map.put(1, "A");
map.put(2, "B");
map.get(1);
map.remove(2);
```

---

---

# 🧩 **GABARITO — COMPLETO**

---

# ✅ **Respostas — Python**

### **1)**

Tempo: **O(n)**
Espaço: **O(1)**

---

### **2)**

Tempo: **O(n)**
Espaço: **O(1)**

---

### **3)**

Tempo: **O(n²)**
Espaço: **O(1)**

---

### **4)**

Tempo: slicing → **O(n)**
Espaço: cópia criada → **O(n)**

---

### **5)**

Recorrência: T(n) = 2T(n−1)
Tempo: **O(2ⁿ)**
Espaço: profundidade da pilha → **O(n)**

---

### **6)**

Tempo: **O(log n)**
Espaço: **O(1)**

---

---

# ✅ **Respostas — Java**

### **7)**

Tempo: **O(n)**
Espaço: **O(1)**

---

### **8)**

Tempo: soma de 1 a n → **O(n²)**
Espaço: **O(1)**

---

### **9)**

Tempo: percorre lista → **O(n)**
Espaço: **O(1)**

---

### **10)**

Tempo: **O(n log n)**
Espaço: **O(n)** (vetores auxiliares)

---

### **11)**

Recorrência: T(n) = T(n−1)+T(n−2)+T(n−3)
Tempo: **O(3ⁿ)**
Espaço: **O(n)**

---

### **12)**

HashMap:

* `put` → **O(1)** amortizado
* `get` → **O(1)** amortizado
* `remove` → **O(1)** amortizado

---

