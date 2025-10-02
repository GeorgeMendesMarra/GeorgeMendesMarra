Para calcular a **complexidade de um algoritmo em Java**, você precisa analisar o número de operações que ele executa em função do tamanho da entrada (n). A complexidade é uma forma de medir a eficiência de um algoritmo, e é geralmente expressa usando a notação "Big O" ($O$).

-----

### O que é a notação Big O ($O$)?

A notação Big O descreve o comportamento assintótico de um algoritmo, ou seja, como seu tempo de execução ou espaço em memória cresce à medida que o tamanho da entrada aumenta. Ela se concentra no pior caso de tempo de execução, ignorando constantes e termos de menor ordem.

Por exemplo, um algoritmo que leva $2n + 5$ operações para ser concluído, tem uma complexidade de tempo de $O(n)$, pois o termo dominante é o que cresce linearmente com $n$.

-----

### Principais Complexidades de Tempo

Aqui estão alguns dos tipos mais comuns de complexidade de tempo, com exemplos em Java:

#### 1\. Complexidade Constante: $O(1)$

O tempo de execução é o mesmo, independentemente do tamanho da entrada.

```java
// Acesso a um elemento em um array pelo índice.
int elemento = meuArray[5];
```

#### 2\. Complexidade Logarítmica: $O(\log n)$

O tempo de execução cresce lentamente à medida que a entrada aumenta. Geralmente, ocorre em algoritmos que dividem o problema pela metade a cada passo.

```java
// Exemplo: Busca Binária
int buscaBinaria(int[] array, int chave) {
    int inicio = 0;
    int fim = array.length - 1;

    while (inicio <= fim) {
        int meio = inicio + (fim - inicio) / 2;
        if (array[meio] == chave) {
            return meio;
        }
        if (array[meio] < chave) {
            inicio = meio + 1;
        } else {
            fim = meio - 1;
        }
    }
    return -1;
}
```

#### 3\. Complexidade Linear: $O(n)$

O tempo de execução é diretamente proporcional ao tamanho da entrada. Se a entrada dobra de tamanho, o tempo de execução também dobra.

```java
// Exemplo: Iterar por um array
for (int i = 0; i < meuArray.length; i++) {
    System.out.println(meuArray[i]);
}
```

#### 4\. Complexidade Linear-Logarítmica: $O(n \log n)$

Comum em algoritmos de ordenação eficientes que dividem o problema e depois o combinam, como o **Merge Sort** ou **Quick Sort**.

```java
// Exemplo (simplificado): Algoritmo de ordenação eficiente.
// A implementação real do Merge Sort ou Quick Sort teria esta complexidade.
void quicksort(int[] array, int inicio, int fim) {
    if (inicio < fim) {
        int indicePivot = particionar(array, inicio, fim);
        quicksort(array, inicio, indicePivot - 1);
        quicksort(array, indicePivot + 1, fim);
    }
}
```

#### 5\. Complexidade Quadrática: $O(n^2)$

O tempo de execução é proporcional ao quadrado do tamanho da entrada. Geralmente ocorre quando há loops aninhados.

```java
// Exemplo: Iterar por todos os pares de elementos
for (int i = 0; i < meuArray.length; i++) {
    for (int j = 0; j < meuArray.length; j++) {
        System.out.println(meuArray[i] + ", " + meuArray[j]);
    }
}
```

-----

### Como Analisar o Código Java

1.  **Encontre a Variável de Entrada ($n$):** Geralmente, é o tamanho de uma coleção, como um `array` ou `ArrayList`.
2.  **Identifique as Operações de Alto Nível:** Considere as operações que mais impactam o tempo de execução, como loops, chamadas de métodos recursivos e acesso a dados.
3.  **Calcule a Complexidade dos Loops:**
      * Um único `for` ou `while` que itera sobre todos os elementos é **$O(n)$**.
      * Loops aninhados (um `for` dentro de outro `for`) resultam em **$O(n^2)$**, **$O(n^3)$**, etc., dependendo do número de níveis.
4.  **Descarte Constantes e Termos de Baixa Ordem:** Se a complexidade for $O(n^2 + n)$, o termo dominante é $n^2$, então a complexidade final é $O(n^2)$. Se for $O(2n)$, a constante 2 é ignorada, resultando em $O(n)$.

Compreender a complexidade de um algoritmo é fundamental para escolher a melhor solução para um problema, especialmente quando a performance é crítica.
