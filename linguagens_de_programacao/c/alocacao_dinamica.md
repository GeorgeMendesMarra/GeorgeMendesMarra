---

## 🧠 O que é alocação dinâmica?

Até agora, você declarava variáveis assim:
```c
int numeros[100];  // tamanho fixo, definido em tempo de compilação
```

Mas e se você **não souber** quantos elementos vai precisar? Ou se precisar de **mais memória** durante a execução?

**Alocação dinâmica** = pedir memória para o sistema operacional **enquanto o programa está rodando**.

Pense como:
- **Estático** → você compra uma mala de tamanho fixo.
- **Dinâmico** → você pede uma mala do tamanho que precisa na hora, e pode trocar de tamanho depois.

---

## 📦 Onde fica a memória?

| Tipo | Local | Quem gerencia |
|------|-------|---------------|
| Variáveis locais | Pilha (stack) | Compilador |
| Variáveis globais | Estático | Compilador |
| **Alocação dinâmica** | **Heap** | **Você (programador)** |

A memória alocada dinamicamente **não é liberada automaticamente** — você precisa devolver quando terminar!

---

## 🔧 As 3 funções principais

Você precisa incluir a biblioteca:
```c
#include <stdlib.h>  // obrigatório para malloc, calloc, free
```

---

## 1️⃣ `malloc()` – Memory Allocation

**Sintaxe:**
```c
void *malloc(size_t tamanho_em_bytes);
```

- Aloca um bloco de memória contínuo.
- **Não inicializa** a memória (pode ter "lixo").
- Retorna um ponteiro `void*` (genérico) — você deve **converter** para o tipo desejado.
- Retorna `NULL` se não houver memória suficiente.

### Exemplo básico:
```c
int *p = (int*) malloc(5 * sizeof(int));  // aloca espaço para 5 inteiros

if (p == NULL) {
    printf("Erro: memória insuficiente!\n");
    return 1;
}

// Agora p é um array de 5 inteiros!
p[0] = 10;
p[1] = 20;
p[2] = 30;
p[3] = 40;
p[4] = 50;

for(int i = 0; i < 5; i++) {
    printf("%d ", p[i]);  // 10 20 30 40 50
}
```

**Por que `sizeof(int)`?** Porque o tamanho de `int` pode variar entre sistemas (geralmente 4 bytes). Usar `sizeof` garante portabilidade.

---

## 2️⃣ `calloc()` – Clear Allocation

**Sintaxe:**
```c
void *calloc(size_t quantidade, size_t tamanho_em_bytes);
```

- Aloca memória para `quantidade` elementos de `tamanho` bytes.
- **Inicializa todos os bytes com ZERO**.
- Retorna `NULL` se falhar.

### Exemplo:
```c
int *p = (int*) calloc(5, sizeof(int));

if (p == NULL) {
    printf("Erro: memória insuficiente!\n");
    return 1;
}

// Todos os 5 inteiros começam com 0
for(int i = 0; i < 5; i++) {
    printf("%d ", p[i]);  // 0 0 0 0 0
}
```

### Comparação `malloc` vs `calloc`:

| `malloc(n)` | `calloc(n, tamanho)` |
|-------------|----------------------|
| Aloca `n` bytes | Aloca `n * tamanho` bytes |
| **Não inicializa** (lixo) | **Inicializa com zero** |
| Mais rápido (não limpa) | Mais lento (limpa memória) |
| 1 parâmetro | 2 parâmetros |

---

## 3️⃣ `free()` – Libera a memória

**Sintaxe:**
```c
void free(void *ptr);
```

- Devolve a memória alocada para o sistema.
- **SEMPRE** libere a memória quando não precisar mais.
- Memória não liberada = **vazamento de memória** (memory leak).

### Exemplo:
```c
int *p = (int*) malloc(10 * sizeof(int));

// ... usa a memória ...

free(p);  // Libera! 
p = NULL; // Boa prática: evitar ponteiro pendente
```

---

## 🧪 Exemplo completo com as 3 funções

```c
#include <stdio.h>
#include <stdlib.h>

int main() {
    int tamanho;
    
    printf("Quantos números você quer armazenar? ");
    scanf("%d", &tamanho);
    
    // 1. Aloca dinamicamente
    int *numeros = (int*) malloc(tamanho * sizeof(int));
    
    // 2. Verifica se alocou
    if (numeros == NULL) {
        printf("Erro: memória insuficiente!\n");
        return 1;
    }
    
    // 3. Usa a memória
    for(int i = 0; i < tamanho; i++) {
        numeros[i] = i * 10;
    }
    
    printf("Valores armazenados:\n");
    for(int i = 0; i < tamanho; i++) {
        printf("%d ", numeros[i]);
    }
    printf("\n");
    
    // 4. Libera a memória
    free(numeros);
    numeros = NULL;
    
    return 0;
}
```

---

## 🔄 Realocando memória: `realloc()`

Às vezes você precisa **aumentar ou diminuir** o tamanho de um bloco já alocado.

**Sintaxe:**
```c
void *realloc(void *ptr, size_t novo_tamanho);
```

- Altera o tamanho do bloco apontado por `ptr`.
- Preserva os dados existentes.
- Retorna um novo ponteiro (pode mudar de endereço).

### Exemplo:
```c
int *p = (int*) malloc(3 * sizeof(int));
p[0] = 10;
p[1] = 20;
p[2] = 30;

// Preciso de mais espaço: vou aumentar para 5
int *temp = (int*) realloc(p, 5 * sizeof(int));

if (temp == NULL) {
    printf("Erro ao realocar!\n");
    free(p);
    return 1;
}

p = temp;  // Atualiza o ponteiro
p[3] = 40;
p[4] = 50;

// Agora p tem 5 elementos: 10, 20, 30, 40, 50
free(p);
```

⚠️ **Cuidado:** `realloc` pode falhar e retornar `NULL`. Se isso acontecer, você **perde o ponteiro original** se fizer:
```c
p = realloc(p, novo_tamanho);  // PERIGO!
```
Sempre use uma variável temporária como no exemplo acima.

---

## 📊 Alocação para diferentes tipos

### Para `char` (strings):
```c
char *texto = (char*) malloc(50 * sizeof(char));
// ou simplesmente
char *texto = malloc(50);  // sizeof(char) = 1

strcpy(texto, "Olá, mundo!");
printf("%s\n", texto);
free(texto);
```

### Para `struct`:
```c
typedef struct {
    char nome[50];
    int idade;
} Pessoa;

Pessoa *p = (Pessoa*) malloc(sizeof(Pessoa));
if (p != NULL) {
    strcpy(p->nome, "João");
    p->idade = 25;
    printf("%s tem %d anos\n", p->nome, p->idade);
    free(p);
}
```

### Para array de structs:
```c
Pessoa *turma = (Pessoa*) malloc(10 * sizeof(Pessoa));
// turma[0], turma[1], ..., turma[9] disponíveis
free(turma);
```

---

## ⚠️ Erros comuns de iniciantes

### ❌ Esquecer de incluir `<stdlib.h>`
```c
// Sem #include <stdlib.h>, malloc retorna int (erro!)
#include <stdlib.h>  // SEMPRE inclua
```

### ❌ Não verificar se malloc retornou NULL
```c
int *p = (int*) malloc(1000000000 * sizeof(int));
*p = 10;  // Se p == NULL, o programa CRASHA!
```

### ❌ Esquecer de liberar memória
```c
while(1) {
    int *p = malloc(1000 * sizeof(int));
    // Esqueceu free(p) → vazamento de memória!
}
```

### ❌ Usar memória após liberar
```c
int *p = malloc(sizeof(int));
*p = 10;
free(p);
*p = 20;  // PERIGO! Memória já foi devolvida
```

### ❌ Liberar a mesma memória duas vezes
```c
free(p);
free(p);  // PERIGO! Comportamento indefinido
```

---

## 🆚 Comparação visual

| Situação | `malloc` | `calloc` |
|----------|----------|----------|
| Alocar 10 inteiros | `malloc(10 * sizeof(int))` | `calloc(10, sizeof(int))` |
| Inicialização | Lixo | Todos zeros |
| Velocidade | Mais rápido | Mais lento |
| Quando usar | Quando vai preencher logo | Quando precisa de zeros |

---

## 🧩 Ponteiro vs Array (Revisão)

Lembre-se: um ponteiro alocado com `malloc` **pode ser usado como array**:

```c
int *v = (int*) malloc(5 * sizeof(int));
v[0] = 1;    // OK
*(v + 1) = 2; // OK
free(v);
```

Mas **não confunda**:
- `int v[5]` → alocado na pilha, liberado automaticamente.
- `int *v = malloc(5 * sizeof(int))` → alocado no heap, você deve liberar.

---

## 🏁 Exercício para fixar

Crie um programa que:
1. Peça ao usuário quantos números ele quer digitar.
2. Aloque dinamicamente um array desse tamanho.
3. Leia os números do usuário.
4. Calcule e mostre a média.
5. Libere a memória.

```c
// Tente fazer você mesmo!
```

**Resposta:**
```c
#include <stdio.h>
#include <stdlib.h>

int main() {
    int n;
    float soma = 0;
    
    printf("Quantos números? ");
    scanf("%d", &n);
    
    float *nums = (float*) malloc(n * sizeof(float));
    
    if (nums == NULL) {
        printf("Erro de alocação!\n");
        return 1;
    }
    
    for(int i = 0; i < n; i++) {
        printf("Número %d: ", i+1);
        scanf("%f", &nums[i]);
        soma += nums[i];
    }
    
    printf("Média: %.2f\n", soma / n);
    
    free(nums);
    nums = NULL;
    
    return 0;
}
```

---

## 🚀 Próximos passos

Depois de dominar alocação dinâmica, estude:
- **Listas encadeadas** (usam malloc para cada nó)
- **Ponteiros para funções** (callback)
- **Alocação de matrizes 2D dinâmicas**
- **Memory pools** (alocação eficiente)

---

## 📝 Resumo rápido

| Função | O que faz | Precisa de `free`? |
|--------|-----------|-------------------|
| `malloc(n)` | Aloca `n` bytes (lixo) | ✅ Sim |
| `calloc(n, t)` | Aloca `n*t` bytes (zeros) | ✅ Sim |
| `realloc(p, n)` | Redimensiona bloco `p` | ✅ Sim (se realocado) |
| `free(p)` | Libera memória | ❌ Não (é a liberação) |

**Regra de ouro:** Para cada `malloc`/`calloc`, deve haver um `free`!

---
