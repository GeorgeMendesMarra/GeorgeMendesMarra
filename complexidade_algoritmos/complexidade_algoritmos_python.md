Para determinar a **complexidade de um algoritmo em Python**, você deve analisar como o tempo de execução (ou o espaço de memória) cresce em função do tamanho da entrada ($n$), utilizando a notação **Big O** ($O$).

A análise é crucial para prever a *escalabilidade* do seu código.

-----

## Princípios da Complexidade (Notação Big O)

A notação **Big O** descreve a taxa de crescimento do algoritmo no pior caso, focando apenas no termo dominante e ignorando constantes.

Por exemplo:

  * Se um algoritmo leva $T(n) = 3n + 10$ operações, sua complexidade é **$O(n)$** (Linear).
  * Se leva $T(n) = 2n^2 + 50n + 100$, a complexidade é **$O(n^2)$** (Quadrática), pois o termo $n^2$ domina o crescimento.

-----

## Exemplos de Complexidade em Python

A complexidade em Python é determinada principalmente pela estrutura do código, especialmente **loops** e **operações em estruturas de dados** (listas, dicionários, etc.).

### 1\. Complexidade Constante: $O(1)$

O tempo de execução é constante, não importa o tamanho da entrada.

| Operação em Python | Complexidade | Exemplo |
| :--- | :--- | :--- |
| Acesso a índice (listas, tuplas) | $O(1)$ | `minha_lista[5]` |
| Adicionar/Remover no final (listas) | $O(1)$ | `minha_lista.append(elemento)` |
| Inserir/Excluir (dicionário/set) | $O(1)$ | `meu_dicionario['chave'] = valor` |

```python
# O(1) - Apenas uma operação de atribuição e acesso
def constante(lista):
    return lista[0]
```

### 2\. Complexidade Logarítmica: $O(\log n)$

O tempo de execução é reduzido pela metade a cada iteração. Comum em algoritmos que dividem o problema.

```python
# O(log n) - Exemplo: Busca binária (funciona apenas em listas ordenadas)
def busca_binaria(lista_ordenada, item):
    baixo = 0
    alto = len(lista_ordenada) - 1
    while baixo <= alto:
        meio = (baixo + alto) // 2
        chute = lista_ordenada[meio]
        if chute == item:
            return meio
        if chute > item:
            alto = meio - 1  # Descarta a metade superior
        else:
            baixo = meio + 1 # Descarta a metade inferior
    return -1
```

### 3\. Complexidade Linear: $O(n)$

O tempo de execução é proporcional ao tamanho da entrada ($n$).

```python
# O(n) - O loop executa n vezes
def soma_lista(lista):
    total = 0
    for elemento in lista:
        total += elemento  # Operação O(1)
    return total

# O(n) - Funções embutidas que precisam percorrer a lista
# Exemplo: O operador 'in' verifica cada elemento no pior caso
if 'valor' in minha_lista:
    pass
```

### 4\. Complexidade Quadrática: $O(n^2)$

O tempo de execução é proporcional ao quadrado do tamanho da entrada. Ocorre tipicamente com **loops aninhados** onde cada loop interno itera sobre $n$ elementos.

```python
# O(n^2) - Loops aninhados
def pares_ordenados(lista):
    n = len(lista)
    for i in range(n):
        for j in range(n):
            # Esta linha executa n * n vezes
            print(lista[i], lista[j])
```

### 5\. Complexidade Linear-Logarítmica: $O(n \log n)$

Comum em algoritmos de ordenação eficientes, como a função embutida `sorted()` ou o método `.sort()` em Python, que geralmente utilizam o **Timsort** (uma combinação de Merge Sort e Insertion Sort).

```python
# O(n log n) - Ordenação
minha_lista.sort()
```

-----

## Regras de Análise em Python

1.  **Regra da Soma:** Se você tem duas partes independentes, $O(n) + O(n^2)$, a complexidade total é o maior termo: $O(n^2)$.
2.  **Regra do Produto:** Para loops aninhados, as complexidades se multiplicam. Um loop $O(n)$ dentro de outro $O(n)$ resulta em $O(n \cdot n) = O(n^2)$.
3.  **Complexidade das Estruturas de Dados:** Lembre-se que certas operações em estruturas de dados padrão de Python têm complexidades fixas que devem ser consideradas:
      * **Listas:** Inserir no início ou meio é $O(n)$ porque todos os outros elementos precisam ser deslocados.
      * **Dicionários (`dict`):** A busca e a inserção são, em média, $O(1)$ devido ao uso de *hashing*.
