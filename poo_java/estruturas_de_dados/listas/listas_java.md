## Listas em Java - Todos os Métodos (Explicação Conceitual)

### O que é uma Lista em Java?

Uma **lista** é uma estrutura de dados que mantém os elementos em uma **sequência ordenada**, permite **elementos duplicados** e oferece **acesso direto por posição (índice)**. Diferente da fila (FIFO) ou pilha (LIFO), a lista permite manipular qualquer elemento independentemente de sua posição.

### Tipos de Lista

| Tipo | Estrutura Interna | Característica Principal |
|------|-------------------|--------------------------|
| **ArrayList** | Array dinâmico | Acesso rápido por índice |
| **LinkedList** | Nós duplamente encadeados | Inserção/remoção rápida nas extremidades |
| **Vector** | Array sincronizado | Thread-safe (versão antiga) |
| **Stack** | Herda Vector | Comportamento LIFO (pilha) |

---

## Categorias de Métodos

### 1. Métodos de Inserção

| Método | O que faz | Comportamento |
|--------|-----------|---------------|
| `add(elemento)` | Adiciona ao final | Sempre insere, retorna true |
| `add(índice, elemento)` | Insere em posição específica | Desloca elementos subsequentes |
| `addAll(coleção)` | Adiciona múltiplos ao final | Insere todos elementos de outra coleção |
| `addAll(índice, coleção)` | Insere coleção em posição específica | Desloca elementos existentes |
| `offer(elemento)` | Adiciona ao final (LinkedList) | Retorna true se conseguiu |
| `offerFirst(elemento)` | Adiciona no início (LinkedList) | Específico para Deque |
| `offerLast(elemento)` | Adiciona no final (LinkedList) | Específico para Deque |
| `push(elemento)` | Adiciona no início (LinkedList) | Comportamento de pilha |

### 2. Métodos de Acesso e Busca

| Método | O que faz | Retorno |
|--------|-----------|---------|
| `get(índice)` | Retorna elemento na posição | O elemento encontrado |
| `indexOf(objeto)` | Primeira ocorrência do objeto | Índice ou -1 se não existe |
| `lastIndexOf(objeto)` | Última ocorrência do objeto | Índice ou -1 se não existe |
| `getFirst()` | Primeiro elemento (LinkedList) | Lança exceção se vazia |
| `getLast()` | Último elemento (LinkedList) | Lança exceção se vazia |
| `peek()` | Primeiro elemento (LinkedList) | Retorna null se vazia |
| `peekFirst()` | Primeiro elemento (LinkedList) | Retorna null se vazia |
| `peekLast()` | Último elemento (LinkedList) | Retorna null se vazia |
| `element()` | Primeiro elemento (LinkedList) | Lança exceção se vazia |

### 3. Métodos de Remoção

| Método | O que faz | O que retorna |
|--------|-----------|---------------|
| `remove(índice)` | Remove por posição | O elemento removido |
| `remove(objeto)` | Remove primeira ocorrência | true se removeu algo |
| `removeAll(coleção)` | Remove todos da coleção | true se houve alteração |
| `removeIf(condição)` | Remove baseado em regra | true se removeu algo |
| `retainAll(coleção)` | Mantém apenas os da coleção | true se houve alteração |
| `clear()` | Remove todos elementos | void (nada retorna) |
| `removeFirst()` | Remove primeiro (LinkedList) | Elemento removido |
| `removeLast()` | Remove último (LinkedList) | Elemento removido |
| `poll()` | Remove primeiro (LinkedList) | Elemento ou null |
| `pollFirst()` | Remove primeiro (LinkedList) | Elemento ou null |
| `pollLast()` | Remove último (LinkedList) | Elemento ou null |
| `pop()` | Remove primeiro (LinkedList) | Elemento removido |

### 4. Métodos de Substituição

| Método | O que faz | Retorno |
|--------|-----------|---------|
| `set(índice, elemento)` | Substitui elemento na posição | Elemento antigo |

### 5. Métodos de Verificação e Consulta

| Método | O que faz | Retorno |
|--------|-----------|---------|
| `contains(objeto)` | Verifica se elemento existe | boolean (true/false) |
| `containsAll(coleção)` | Verifica se todos existem | boolean |
| `isEmpty()` | Verifica se está vazia | boolean |
| `size()` | Retorna quantidade de elementos | int |
| `equals(objeto)` | Compara com outra lista | boolean |
| `hashCode()` | Código hash da lista | int |

### 6. Métodos de Iteração e Navegação

| Método | O que faz | Retorno |
|--------|-----------|---------|
| `forEach(ação)` | Executa ação para cada elemento | void |
| `iterator()` | Obtém iterador comum | Iterator |
| `listIterator()` | Obtém iterador bidirecional | ListIterator |
| `listIterator(índice)` | Iterador começando na posição | ListIterator |
| `descendingIterator()` | Iterador reverso (LinkedList) | Iterator |
| `spliterator()` | Iterador para processamento paralelo | Spliterator |

### 7. Métodos de Visualização e Conversão

| Método | O que faz | Retorno |
|--------|-----------|---------|
| `toString()` | Representação textual | String |
| `toArray()` | Converte para array de Object | Object[] |
| `toArray(array)` | Converte para array tipo específico | T[] |
| `subList(início, fim)` | Visão de uma parte da lista | List |
| `clone()` | Cópia superficial (ArrayList/LinkedList) | Object |

### 8. Métodos de Capacidade (ArrayList específico)

| Método | O que faz |
|--------|-----------|
| `ensureCapacity(minimo)` | Garante capacidade mínima (otimização) |
| `trimToSize()` | Reduz capacidade ao tamanho atual |

### 9. Métodos de Ordenação e Manipulação

| Método (Collections) | O que faz |
|----------------------|-----------|
| `sort(lista)` | Ordena em ordem natural |
| `sort(lista, comparador)` | Ordena com critério personalizado |
| `reverse(lista)` | Inverte a ordem dos elementos |
| `shuffle(lista)` | Embaralha aleatoriamente |
| `rotate(lista, distância)` | Rotaciona elementos |
| `swap(lista, i, j)` | Troca elementos de posição |
| `fill(lista, elemento)` | Preenche toda com mesmo valor |
| `copy(destino, origem)` | Copia elementos |
| `replaceAll(lista, antigo, novo)` | Substitui ocorrências |

### 10. Métodos de Busca e Estatística (Collections)

| Método | O que faz |
|--------|-----------|
| `binarySearch(lista, chave)` | Busca binária (lista ordenada) |
| `max(lista)` | Maior elemento |
| `min(lista)` | Menor elemento |
| `frequency(lista, elemento)` | Conta ocorrências |
| `disjoint(coleção1, coleção2)` | Verifica se não há interseção |

### 11. Métodos de Criação (Collections)

| Método | O que faz |
|--------|-----------|
| `singletonList(elemento)` | Lista imutável com um elemento |
| `nCopies(n, elemento)` | Lista com n repetições |
| `emptyList()` | Lista vazia imutável |
| `unmodifiableList(lista)` | Versão somente leitura |
| `synchronizedList(lista)` | Versão thread-safe |
| `checkedList(lista, tipo)` | Lista com verificação de tipo |

### 12. Métodos de Operação em Massa (Java 8+ Stream)

| Método | O que faz |
|--------|-----------|
| `stream()` | Cria stream sequencial |
| `parallelStream()` | Cria stream paralelo |
| `filter(condição)` | Seleciona elementos que atendem condição |
| `map(transformação)` | Transforma cada elemento |
| `flatMap(achatamento)` | Achata streams aninhados |
| `distinct()` | Remove duplicatas |
| `sorted()` | Ordena elementos |
| `limit(n)` | Limita ao número de elementos |
| `skip(n)` | Pula elementos |
| `peek(ação)` | Executa ação (debug) |
| `collect(coletor)` | Coleta resultados |
| `reduce(operação)` | Reduz a um valor único |
| `count()` | Conta elementos |
| `anyMatch(condição)` | Algum elemento atende? |
| `allMatch(condição)` | Todos elementos atendem? |
| `noneMatch(condição)` | Nenhum elemento atende? |
| `findFirst()` | Primeiro elemento |
| `findAny()` | Qualquer elemento |
| `min()` | Mínimo |
| `max()` | Máximo |
| `forEach(ação)` | Executa ação para cada |

### 13. Métodos de Agrupamento e Coleta (Collectors)

| Método | O que faz |
|--------|-----------|
| `toList()` | Coleta para List |
| `toSet()` | Coleta para Set |
| `toMap()` | Coleta para Map |
| `joining(separador)` | Concatena strings |
| `groupingBy(classificador)` | Agrupa por critério |
| `partitioningBy(condição)` | Divide em dois grupos |
| `summarizingInt()` | Estatísticas (soma, média, etc) |

---

## Diferenças de Comportamento por Tipo de Lista

| Operação | ArrayList | LinkedList |
|----------|-----------|------------|
| Acesso `get(índice)` | **Muito rápido** (O(1)) | Lento (O(n)) |
| Inserção no fim | Rápido (O(1) amortizado) | Rápido (O(1)) |
| Inserção no início | **Lento** (desloca tudo) | Rápido (O(1)) |
| Remoção no meio | Lento (desloca) | Moderado (O(n) para achar) |
| Memória por elemento | Menos (apenas array) | Mais (nós com ponteiros) |

---

## Quando Usar Cada Método

| Situação | Métodos Recomendados |
|----------|---------------------|
| Adicionar um elemento | `add()` |
| Adicionar vários de uma vez | `addAll()` |
| Inserir em posição específica | `add(índice, elemento)` |
| Acessar por posição | `get()` |
| Encontrar posição de um elemento | `indexOf()` |
| Verificar se existe | `contains()` |
| Remover por posição | `remove(índice)` |
| Remover por valor | `remove(objeto)` |
| Remover múltiplos por condição | `removeIf()` |
| Esvaziar a lista | `clear()` |
| Substituir um elemento | `set()` |
| Percorrer todos elementos | `forEach()` ou `iterator()` |
| Ordenar | `sort()` |
| Embaralhar | `shuffle()` |
| Inverter ordem | `reverse()` |
| Converter para array | `toArray()` |
| Pegar apenas uma parte | `subList()` |
| Versão somente leitura | `unmodifiableList()` |

---

## Resumo dos Princípios

1. **ArrayList**: use para acesso frequente por índice
2. **LinkedList**: use para muitas inserções/remoções nas extremidades
3. **Streams**: use para operações em massa e processamento funcional
4. **Collections**: use para operações estáticas utilitárias
5. **Listas imutáveis**: prefira `List.of()` para segurança

Esta é a referência completa dos métodos de lista em Java sem código!
