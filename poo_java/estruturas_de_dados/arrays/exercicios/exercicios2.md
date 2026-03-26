## Parte 1 — Arrays Unidimensionais

**1. Qual das seguintes opções declara e inicializa corretamente um array unidimensional em Java com 5 elementos do tipo `int`?**

a) `int[] numeros = new int[5];`  
b) `int numeros[5];`  
c) `int[] numeros = new int(5);`  
d) `int numeros = new int[5];`

**Resposta correta: a)** `int[] numeros = new int[5];`

---

**2. Qual é o índice do primeiro elemento de um array em Java?**

a) 0  
b) 1  
c) -1  
d) Depende do tipo do array

**Resposta correta: a)** 0

---

**3. O que acontece ao tentar acessar um índice fora dos limites de um array, como `numeros[5]` em um array de tamanho 5?**

a) O programa retorna `null`  
b) O programa compila, mas lança `ArrayIndexOutOfBoundsException` em tempo de execução  
c) O programa não compila  
d) O programa retorna 0

**Resposta correta: b)** O programa compila, mas lança `ArrayIndexOutOfBoundsException` em tempo de execução

---

**4. Qual das alternativas a seguir é uma forma válida de inicializar um array com valores pré-definidos?**

a) `int[] numeros = new int[]{1, 2, 3, 4, 5};`  
b) `int[] numeros = {1, 2, 3, 4, 5};`  
c) `int numeros[] = {1, 2, 3, 4, 5};`  
d) Todas as alternativas acima

**Resposta correta: d)** Todas as alternativas acima

---

**5. Qual é o valor padrão de um elemento de um array de `int` não inicializado explicitamente?**

a) `null`  
b) `0`  
c) `-1`  
d) `undefined`

**Resposta correta: b)** 0

---

**6. Qual atributo é utilizado para obter o comprimento (tamanho) de um array em Java?**

a) `length()` (método)  
b) `size` (atributo)  
c) `length` (atributo)  
d) `getLength()` (método)

**Resposta correta: c)** `length` (atributo)

---

**7. Considere o código abaixo. O que será impresso?**

```java
int[] numeros = {10, 20, 30, 40, 50};
System.out.println(numeros[2]);
```

a) 10  
b) 20  
c) 30  
d) 40

**Resposta correta: c)** 30

---

**8. Qual das alternativas percorre corretamente todos os elementos de um array `int[] numeros`?**

a) `for (int i = 0; i <= numeros.length; i++)`  
b) `for (int i = 0; i < numeros.length; i++)`  
c) `for (int i = 1; i <= numeros.length; i++)`  
d) `for (int i = 1; i < numeros.length; i++)`

**Resposta correta: b)** `for (int i = 0; i < numeros.length; i++)`

---

**9. Qual é a saída do código abaixo?**

```java
int[] valores = new int[3];
valores[0] = 5;
valores[1] = 10;
System.out.println(valores[0] + valores[2]);
```

a) 5  
b) 10  
c) 15  
d) 0

**Resposta correta: a)** 5 (pois `valores[2]` não foi inicializado e vale 0)

---

**10. Qual das opções representa um array unidimensional de `String`?**

a) `String[] nomes = new String[10];`  
b) `String nomes = new String[10];`  
c) `String[] nomes = new String(10);`  
d) `String nomes[10];`

**Resposta correta: a)** `String[] nomes = new String[10];`

---

## Parte 2 — Arrays Bidimensionais

**11. Qual das opções declara corretamente um array bidimensional em Java?**

a) `int[][] matriz = new int[3][4];`  
b) `int matriz[3][4];`  
c) `int[][] matriz = new int(3,4);`  
d) `int matriz[][] = new int(3)(4);`

**Resposta correta: a)** `int[][] matriz = new int[3][4];`

---

**12. Em um array bidimensional `int[][] matriz = new int[3][4];`, quantas linhas e colunas ele possui, respectivamente?**

a) 3 linhas e 3 colunas  
b) 4 linhas e 3 colunas  
c) 3 linhas e 4 colunas  
d) 4 linhas e 4 colunas

**Resposta correta: c)** 3 linhas e 4 colunas

---

**13. Como acessar o elemento localizado na segunda linha e terceira coluna de uma matriz `int[][] matriz`?**

a) `matriz[1][2]`  
b) `matriz[2][3]`  
c) `matriz[1][3]`  
d) `matriz[2][2]`

**Resposta correta: a)** `matriz[1][2]` (índices começam em 0)

---

**14. Qual é a saída do código abaixo?**

```java
int[][] matriz = {{1, 2}, {3, 4}, {5, 6}};
System.out.println(matriz[1][0]);
```

a) 1  
b) 2  
c) 3  
d) 4

**Resposta correta: c)** 3

---

**15. Considere o código abaixo. Qual será o valor impresso?**

```java
int[][] matriz = new int[2][3];
matriz[0][1] = 10;
matriz[1][2] = 20;
System.out.println(matriz[0][1] + matriz[1][2]);
```

a) 10  
b) 20  
c) 30  
d) 0

**Resposta correta: c)** 30

---

**16. Qual das opções percorre corretamente todos os elementos de uma matriz `int[][] matriz` com 3 linhas e 4 colunas?**

```java
a) for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 4; j++) {
           System.out.println(matriz[i][j]);
       }
   }

b) for (int i = 0; i <= 3; i++) {
       for (int j = 0; j <= 4; j++) {
           System.out.println(matriz[i][j]);
       }
   }

c) for (int i = 0; i < matriz.length; i++) {
       for (int j = 0; j < matriz[i].length; j++) {
           System.out.println(matriz[i][j]);
       }
   }

d) As alternativas a e c estão corretas
```

**Resposta correta: d)** As alternativas a e c estão corretas

---

**17. Em um array bidimensional do tipo `String`, qual é o valor padrão de seus elementos antes da inicialização?**

a) `""` (string vazia)  
b) `null`  
c) `"null"`  
d) `0`

**Resposta correta: b)** `null`

---

**18. Como obter o número de linhas de uma matriz `int[][] matriz`?**

a) `matriz.length`  
b) `matriz[0].length`  
c) `matriz.size()`  
d) `matriz.getLength()`

**Resposta correta: a)** `matriz.length`

---

**19. Como obter o número de colunas de uma matriz `int[][] matriz` (considerando que todas as linhas têm o mesmo tamanho)?**

a) `matriz.length`  
b) `matriz[0].length`  
c) `matriz[0].size()`  
d) `matriz.length[0]`

**Resposta correta: b)** `matriz[0].length`

---

**20. Qual das opções abaixo inicializa uma matriz 2x2 com todos os elementos valendo 0?**

```java
a) int[][] matriz = new int[2][2];

b) int[][] matriz = {{0, 0}, {0, 0}};

c) int[][] matriz = new int[2][2];
   for (int i = 0; i < 2; i++) {
       for (int j = 0; j < 2; j++) {
           matriz[i][j] = 0;
       }
   }

d) Todas as alternativas acima
```

**Resposta correta: d)** Todas as alternativas acima

---

## Gabarito Resumido

| Questão | Resposta | Questão | Resposta |
|---------|----------|---------|----------|
| 1 | a | 11 | a |
| 2 | a | 12 | c |
| 3 | b | 13 | a |
| 4 | d | 14 | c |
| 5 | b | 15 | c |
| 6 | c | 16 | d |
| 7 | c | 17 | b |
| 8 | b | 18 | a |
| 9 | a | 19 | b |
| 10 | a | 20 | d |

---

## Dicas Importantes

| Conceito | Explicação |
|----------|------------|
| **Índices** | Começam sempre em 0 |
| **Tamanho** | `array.length` (atributo, não método) |
| **Array bidimensional** | Matriz de linhas e colunas, pode ser irregular (jagged array) |
| **Valores padrão** | `int` = 0, `double` = 0.0, `boolean` = false, `String` = `null` |
| **Exceção comum** | `ArrayIndexOutOfBoundsException` ao acessar índice inválido |

---
