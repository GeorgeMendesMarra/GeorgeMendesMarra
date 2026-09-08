# Questões — Ponteiros em Linguagem C

Curso: Engenharia de Software | Disciplina: Algoritmos e Estruturas de Dados I
20 questões de múltipla escolha (5 alternativas, 1 correta).

---

### Questão 1
O que o operador `&` faz quando aplicado a uma variável em C?

A) Retorna o valor armazenado na variável
B) Retorna o endereço de memória da variável
C) Declara a variável como ponteiro
D) Libera a memória da variável
E) Compara dois endereços

---

### Questão 2
O que o operador `*` faz quando aplicado a um ponteiro (fora da declaração)?

A) Multiplica o valor do ponteiro por 2
B) Retorna o endereço do ponteiro
C) Acessa (dereferencia) o valor armazenado no endereço apontado
D) Converte o ponteiro para inteiro
E) Aloca memória dinamicamente

---

### Questão 3
Qual é a forma correta de declarar um ponteiro para `int` em C?

A) `int ptr;`
B) `ptr int;`
C) `int *ptr;`
D) `int &ptr;`
E) `pointer int ptr;`

---

### Questão 4
Considere:
```c
int x = 10;
int *p = &x;
```
Qual o valor de `*p`?

A) O endereço de `x`
B) `10`
C) O endereço de `p`
D) `NULL`
E) Um erro de compilação

---

### Questão 5
Em um sistema com ponteiros de 8 bytes, qual o valor aproximado de `sizeof(p)` para `int *p;`?

A) 1
B) 2
C) 4
D) 8
E) Depende do valor apontado por `p`

---

### Questão 6
Qual é o valor padrão recomendado para inicializar um ponteiro que ainda não aponta para nenhum endereço válido?

A) `0.0`
B) `""`
C) `NULL`
D) `-1`
E) O ponteiro não precisa ser inicializado

---

### Questão 7
Dado o código:
```c
int v[5] = {10, 20, 30, 40, 50};
int *p = v;
```
Qual expressão é equivalente a `v[2]`?

A) `*p + 2`
B) `*(p + 2)`
C) `p + 2`
D) `&p[2]`
E) `*p * 2`

---

### Questão 8
O que acontece quando incrementamos um ponteiro `int *p` com `p++`?

A) O valor apontado é incrementado em 1
B) O endereço em `p` avança 1 byte
C) O endereço em `p` avança `sizeof(int)` bytes
D) Ocorre erro de compilação
E) `p` passa a apontar para `NULL`

---

### Questão 9
Qual a saída do código abaixo?
```c
int x = 5;
int *p = &x;
*p = *p + 1;
printf("%d", x);
```

A) 5
B) 6
C) Endereço de x
D) Erro de compilação
E) Comportamento indefinido

---

### Questão 10
Para que serve a passagem de parâmetros por ponteiro em funções C, como em `void trocar(int *a, int *b)`?

A) Para tornar a função mais rápida sempre
B) Para permitir que a função modifique as variáveis originais do chamador
C) Para evitar o uso de `return`
D) Para criar cópias independentes dos argumentos
E) É obrigatório em toda função C

---

### Questão 11
Qual função da biblioteca `stdlib.h` aloca memória dinamicamente e a inicializa com zeros?

A) `malloc`
B) `free`
C) `calloc`
D) `alloc`
E) `sizeof`

---

### Questão 12
Após usar `int *p = malloc(10 * sizeof(int));` e terminar de usar a memória, qual é a prática correta?

A) Apenas deixar o programa terminar
B) Chamar `free(p);`
C) Fazer `p = 0;` sem liberar
D) Chamar `malloc(0)`
E) Chamar `delete p;`

---

### Questão 13
O que é um "ponteiro solto" (dangling pointer)?

A) Um ponteiro que nunca foi inicializado
B) Um ponteiro que aponta para uma área de memória já liberada ou inválida
C) Um ponteiro que aponta para outro ponteiro
D) Um ponteiro usado apenas em arrays
E) Um ponteiro do tipo `void*`

---

### Questão 14
O que representa `int **pp;` em C?

A) Um array de inteiros
B) Um ponteiro para inteiro
C) Um ponteiro para ponteiro para inteiro
D) Uma função que retorna inteiro
E) Erro de sintaxe

---

### Questão 15
O que é um `void*` (ponteiro genérico) em C?

A) Um ponteiro que sempre aponta para `NULL`
B) Um ponteiro que não pode ser usado
C) Um ponteiro que pode apontar para qualquer tipo de dado, mas não pode ser dereferenciado diretamente sem conversão
D) Um tipo exclusivo para funções
E) Um ponteiro que ocupa 0 bytes

---

### Questão 16
Dado:
```c
void soma_um(int *n) {
    *n = *n + 1;
}

int main(void) {
    int x = 4;
    soma_um(&x);
    printf("%d", x);
}
```
Qual a saída?

A) 4
B) 5
C) Endereço de x
D) Erro de compilação
E) Valor indefinido

---

### Questão 17
Qual a diferença principal entre `const int *p` e `int *const p`?

A) Não há diferença, são idênticos
B) No primeiro, o valor apontado não pode mudar; no segundo, o próprio ponteiro não pode apontar para outro endereço
C) O primeiro só funciona com arrays
D) O segundo é inválido em C
E) O primeiro é mais rápido que o segundo

---

### Questão 18
Em uma struct `typedef struct { int dado; struct No *proximo; } No;`, qual operador é usado para acessar o campo `dado` a partir de um ponteiro `No *n`?

A) `n.dado`
B) `n->dado`
C) `*n.dado`
D) `n[dado]`
E) `n::dado`

---

### Questão 19
O que ocorre se tentarmos dereferenciar (`*p`) um ponteiro `p` que vale `NULL`?

A) O programa retorna 0 automaticamente
B) O compilador impede a compilação
C) Comportamento indefinido / falha em tempo de execução (ex.: segmentation fault)
D) `p` passa a apontar para o início do programa
E) Nada acontece, é ignorado silenciosamente

---

### Questão 20
Qual das opções abaixo é um exemplo válido de declaração de um ponteiro para uma função que recebe dois `int` e retorna `int`?

A) `int f(int, int);`
B) `int *f(int, int);`
C) `int (*f)(int, int);`
D) `pointer int f(int, int);`
E) `int f*(int, int);`

---

## Gabarito

| Questão | Resposta | Questão | Resposta |
|:---:|:---:|:---:|:---:|
| 1 | B | 11 | C |
| 2 | C | 12 | B |
| 3 | C | 13 | B |
| 4 | B | 14 | C |
| 5 | D | 15 | C |
| 6 | C | 16 | B |
| 7 | B | 17 | B |
| 8 | C | 18 | B |
| 9 | B | 19 | C |
| 10 | B | 20 | C |
