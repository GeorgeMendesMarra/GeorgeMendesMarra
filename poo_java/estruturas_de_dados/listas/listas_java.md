## Listas em Java — Os 4 Tipos e Todos os Métodos (Explicação Conceitual)

### O que é uma Lista em Java?

Uma **lista** é uma estrutura de dados que mantém os elementos em uma **sequência ordenada**, permite **elementos duplicados** e oferece **acesso direto por posição (índice)**. Diferente da fila (FIFO) ou pilha (LIFO) "puras", a lista permite manipular qualquer elemento independentemente de sua posição.

Em Java, a interface `List` tem quatro implementações clássicas que costumam ser cobradas em prova/aula: **ArrayList**, **LinkedList**, **Vector** e **Stack**. Cada uma tem sua estrutura interna, suas vantagens e um conjunto próprio de métodos — inclusive métodos exclusivos que não existem nas outras.

---

## Visão Geral dos 4 Tipos

| Tipo           | Estrutura Interna          | Implementa                        | Thread-safe | Característica Principal                     |
| -------------- | --------------------------- | ---------------------------------- | ----------- | ---------------------------------------------- |
| **ArrayList**  | Array dinâmico               | `List`, `RandomAccess`             | Não         | Acesso rápido por índice                       |
| **LinkedList** | Nós duplamente encadeados    | `List`, `Deque`                    | Não         | Inserção/remoção rápida nas extremidades       |
| **Vector**     | Array dinâmico sincronizado  | `List`, `RandomAccess`             | Sim         | Versão antiga (legada) e thread-safe do ArrayList |
| **Stack**      | Array dinâmico (herda Vector)| `List` (via Vector)                | Sim         | Comportamento LIFO (pilha) explícito           |

---

# 1. ArrayList

Baseada em um **array dinâmico** que cresce automaticamente. É a lista mais usada no dia a dia por ter acesso por índice em tempo O(1).

### 1.1 Inserção

| Método                    | O que faz                            |
| -------------------------- | ------------------------------------- |
| `add(elemento)`            | Adiciona ao final                     |
| `add(índice, elemento)`    | Insere em posição específica, deslocando os demais |
| `addAll(coleção)`          | Adiciona todos os elementos de outra coleção ao final |
| `addAll(índice, coleção)`  | Insere outra coleção a partir de uma posição |

### 1.2 Acesso e busca

| Método                 | O que faz                          |
| ------------------------ | ------------------------------------ |
| `get(índice)`             | Retorna o elemento na posição indicada |
| `indexOf(objeto)`         | Índice da primeira ocorrência (-1 se não existir) |
| `lastIndexOf(objeto)`     | Índice da última ocorrência (-1 se não existir) |

### 1.3 Remoção

| Método               | O que faz                     |
| ---------------------- | -------------------------------- |
| `remove(índice)`       | Remove o elemento naquela posição |
| `remove(objeto)`       | Remove a primeira ocorrência do objeto |
| `removeAll(coleção)`   | Remove todos os elementos presentes na coleção informada |
| `removeIf(condição)`   | Remove todos que satisfazem a condição (predicado) |
| `retainAll(coleção)`   | Mantém só os elementos que também estão na coleção |
| `clear()`              | Remove todos os elementos |

### 1.4 Substituição

| Método                  | O que faz                        |
| ------------------------- | ----------------------------------- |
| `set(índice, elemento)`   | Substitui o elemento da posição e retorna o antigo |

### 1.5 Verificação e consulta

| Método                 | O que faz                          |
| ------------------------ | ------------------------------------ |
| `contains(objeto)`       | Verifica se o elemento existe        |
| `containsAll(coleção)`   | Verifica se todos os elementos de uma coleção existem |
| `isEmpty()`               | Verifica se está vazia               |
| `size()`                  | Quantidade de elementos              |
| `equals(objeto)`          | Compara o conteúdo com outra lista   |
| `hashCode()`              | Código hash da lista                 |

### 1.6 Iteração

| Método                | O que faz                            |
| ----------------------- | -------------------------------------- |
| `forEach(ação)`         | Executa uma ação para cada elemento    |
| `iterator()`            | Retorna um iterador simples (avança)   |
| `listIterator()`        | Iterador bidirecional (avança e volta) |
| `listIterator(índice)`  | Iterador bidirecional a partir de uma posição |
| `spliterator()`         | Iterador voltado a processamento paralelo/streams |

### 1.7 Visualização e conversão

| Método                  | O que faz                                 |
| -------------------------- | -------------------------------------------- |
| `toString()`               | Representação textual da lista               |
| `toArray()`                | Converte para `Object[]`                     |
| `toArray(array)`           | Converte para array de tipo específico (`T[]`) |
| `subList(início, fim)`     | Retorna uma visão (view) de parte da lista   |
| `clone()`                  | Cópia superficial (shallow copy)             |

### 1.8 Capacidade (exclusivo do ArrayList)

| Método                   | O que faz                                |
| --------------------------- | ------------------------------------------- |
| `ensureCapacity(mínimo)`    | Garante uma capacidade interna mínima (otimização, evita realocações) |
| `trimToSize()`              | Reduz a capacidade interna ao tamanho atual, economizando memória |

### 1.9 Exemplo completo de código

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ExemploArrayList {
    public static void main(String[] args) {
        List<String> produtos = new ArrayList<>();

        // Inserção
        produtos.add("Notebook");
        produtos.add("Mouse");
        produtos.add("Teclado");
        produtos.add(1, "Monitor"); // insere na posição 1

        // Acesso
        System.out.println("Produto na posição 0: " + produtos.get(0));

        // Busca
        System.out.println("Índice de 'Mouse': " + produtos.indexOf("Mouse"));

        // Substituição
        produtos.set(2, "Mouse sem fio");

        // Iteração
        System.out.println("Catálogo atual:");
        for (String produto : produtos) {
            System.out.println("- " + produto);
        }

        // Ordenação
        Collections.sort(produtos);
        System.out.println("Catálogo ordenado: " + produtos);

        // Remoção
        produtos.remove("Teclado");
        produtos.removeIf(p -> p.startsWith("Monitor"));

        // Consulta
        System.out.println("Contém 'Notebook'? " + produtos.contains("Notebook"));
        System.out.println("Tamanho final: " + produtos.size());
    }
}
```

### 1.10 Principais aplicações em sistemas operacionais e sistemas comerciais

**Sistemas operacionais:**
- Listagem de processos em execução exibida por gerenciadores de tarefas, onde cada processo é acessado por índice/posição na tabela.
- Tabelas de descritores de arquivos abertos, onde o acesso rápido por posição (índice do descritor) é essencial.
- Estruturas internas de cache que precisam de acesso aleatório rápido a itens já carregados.

**Sistemas comerciais:**
- Catálogo de produtos de um e-commerce, onde o usuário navega por páginas e acessa itens por posição.
- Carrinho de compras, onde itens são adicionados, listados e removidos com frequência, mas raramente inseridos no meio.
- Relatórios e extratos (notas fiscais, pedidos, transações) carregados inteiramente em memória para exibição paginada.

---

# 2. LinkedList

Baseada em **nós duplamente encadeados** (cada nó aponta para o anterior e o próximo). Implementa tanto `List` quanto `Deque`, por isso funciona como lista, fila e pilha ao mesmo tempo.

### 2.1 Métodos herdados de List (iguais aos do ArrayList)

Todos os métodos da seção 1 (`add`, `get`, `remove(índice)`, `remove(objeto)`, `set`, `contains`, `size`, `isEmpty`, `clear`, `indexOf`, `lastIndexOf`, `subList`, `toArray`, `iterator`, `listIterator`, `forEach`, etc.) também existem na LinkedList — a diferença é o desempenho: acesso por índice é O(n) em vez de O(1).

### 2.2 Inserção nas extremidades (exclusivos, via Deque)

| Método                    | O que faz                                  |
| ---------------------------- | ---------------------------------------------- |
| `addFirst(elemento)`         | Insere no início da lista                      |
| `addLast(elemento)`          | Insere no final da lista                       |
| `offer(elemento)`            | Adiciona ao final, retorna `true`/`false`      |
| `offerFirst(elemento)`       | Adiciona no início, retorna `true`/`false`     |
| `offerLast(elemento)`        | Adiciona no final, retorna `true`/`false`      |
| `push(elemento)`             | Adiciona no início (comportamento de pilha)    |

### 2.3 Acesso nas extremidades (exclusivos)

| Método            | O que faz                                    |
| -------------------- | ----------------------------------------------- |
| `getFirst()`         | Retorna o primeiro elemento (lança exceção se vazia) |
| `getLast()`          | Retorna o último elemento (lança exceção se vazia)   |
| `peek()`             | Retorna o primeiro elemento (retorna `null` se vazia) |
| `peekFirst()`        | Retorna o primeiro elemento (retorna `null` se vazia) |
| `peekLast()`         | Retorna o último elemento (retorna `null` se vazia)   |
| `element()`          | Retorna o primeiro elemento (lança exceção se vazia)  |

### 2.4 Remoção nas extremidades (exclusivos)

| Método            | O que faz                                  |
| -------------------- | ---------------------------------------------- |
| `removeFirst()`      | Remove e retorna o primeiro elemento           |
| `removeLast()`       | Remove e retorna o último elemento             |
| `poll()`             | Remove e retorna o primeiro (retorna `null` se vazia) |
| `pollFirst()`        | Remove e retorna o primeiro (retorna `null` se vazia) |
| `pollLast()`         | Remove e retorna o último (retorna `null` se vazia)   |
| `pop()`              | Remove e retorna o primeiro (comportamento de pilha)  |
| `removeFirstOccurrence(objeto)` | Remove a primeira ocorrência do objeto (varre do início) |
| `removeLastOccurrence(objeto)`  | Remove a última ocorrência do objeto (varre do fim) |

### 2.5 Navegação (exclusivo)

| Método                   | O que faz                            |
| --------------------------- | ---------------------------------------- |
| `descendingIterator()`      | Iterador que percorre a lista do fim para o início |

### 2.6 Exemplo completo de código

```java
import java.util.LinkedList;

public class ExemploLinkedList {
    public static void main(String[] args) {
        LinkedList<String> historicoNavegador = new LinkedList<>();

        // Simula navegação: cada página visitada entra no final
        historicoNavegador.addLast("google.com");
        historicoNavegador.addLast("unialfa.com.br");
        historicoNavegador.addLast("github.com");

        System.out.println("Histórico completo: " + historicoNavegador);

        // Página atual (última visitada)
        System.out.println("Página atual: " + historicoNavegador.getLast());

        // Botão "voltar": remove a página atual e mostra a anterior
        historicoNavegador.removeLast();
        System.out.println("Após voltar, página atual: " + historicoNavegador.getLast());

        // Usando como pilha (push/pop) para um histórico de "desfazer"
        LinkedList<String> desfazer = new LinkedList<>();
        desfazer.push("digitar texto");
        desfazer.push("aplicar negrito");
        desfazer.push("inserir imagem");

        System.out.println("Última ação: " + desfazer.peek());
        System.out.println("Desfazendo: " + desfazer.pop());
        System.out.println("Ações restantes: " + desfazer);

        // Usando como fila (offer/poll) para uma fila de impressão
        LinkedList<String> filaImpressao = new LinkedList<>();
        filaImpressao.offer("documento1.pdf");
        filaImpressao.offer("documento2.pdf");
        System.out.println("Imprimindo: " + filaImpressao.poll());
        System.out.println("Fila restante: " + filaImpressao);
    }
}
```

### 2.7 Principais aplicações em sistemas operacionais e sistemas comerciais

**Sistemas operacionais:**
- Filas de escalonamento de processos (ex.: algoritmo Round Robin), onde processos entram no final da fila e são atendidos pelo início.
- Filas de impressão (spooler), em que os trabalhos são inseridos e removidos constantemente nas extremidades.
- Buffers de dados em drivers e sistemas de I/O, onde a inserção/remoção rápida nas pontas é mais importante que o acesso aleatório.

**Sistemas comerciais:**
- Histórico de navegação de um navegador ou de um aplicativo (botões "voltar"/"avançar").
- Funcionalidade de "desfazer/refazer" em editores de texto, planilhas e ferramentas de design.
- Playlists de músicas ou vídeos, onde o usuário adiciona/remove itens no início ou no final com frequência.
- Filas de atendimento em sistemas de suporte/chat, onde clientes entram e são atendidos em ordem.

---

# 3. Vector

Também baseado em **array dinâmico**, é a versão legada (anterior ao Java Collections Framework) e **sincronizada** (thread-safe), o que a torna mais lenta que ArrayList em uso comum.

### 3.1 Métodos herdados de List (iguais aos do ArrayList)

`add`, `get`, `remove(índice)`, `remove(objeto)`, `set`, `contains`, `size`, `isEmpty`, `clear`, `indexOf`, `lastIndexOf`, `subList`, `toArray`, `iterator`, `listIterator`, `forEach`, `ensureCapacity`, `trimToSize`, etc.

### 3.2 Métodos legados exclusivos do Vector

| Método                          | O que faz                               |
| ---------------------------------- | ------------------------------------------- |
| `addElement(elemento)`             | Adiciona ao final (equivalente antigo de `add`) |
| `insertElementAt(elemento, índice)`| Insere em uma posição específica            |
| `elementAt(índice)`                | Retorna o elemento na posição (equivalente antigo de `get`) |
| `firstElement()`                   | Retorna o primeiro elemento (lança exceção se vazia) |
| `lastElement()`                    | Retorna o último elemento (lança exceção se vazia)   |
| `setElementAt(elemento, índice)`   | Substitui o elemento da posição (equivalente antigo de `set`) |
| `removeElement(objeto)`            | Remove a primeira ocorrência (equivalente antigo de `remove(objeto)`) |
| `removeElementAt(índice)`          | Remove pela posição (equivalente antigo de `remove(índice)`) |
| `removeAllElements()`              | Remove todos os elementos (equivalente antigo de `clear`) |
| `elements()`                       | Retorna um `Enumeration` para percorrer os elementos (forma antiga de iterar) |
| `capacity()`                       | Retorna a capacidade interna atual do array   |

### 3.3 Exemplo completo de código

```java
import java.util.Vector;
import java.util.Enumeration;

public class ExemploVector {
    public static void main(String[] args) throws InterruptedException {
        Vector<String> conexoesAtivas = new Vector<>();

        // Simula múltiplas threads acessando a mesma estrutura com segurança
        Runnable tarefaConectar = () -> {
            for (int i = 0; i < 3; i++) {
                conexoesAtivas.addElement(Thread.currentThread().getName() + "-conexao" + i);
            }
        };

        Thread t1 = new Thread(tarefaConectar, "Thread1");
        Thread t2 = new Thread(tarefaConectar, "Thread2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Total de conexões registradas: " + conexoesAtivas.size());

        // Acesso legado por posição
        System.out.println("Primeira conexão: " + conexoesAtivas.firstElement());
        System.out.println("Última conexão: " + conexoesAtivas.lastElement());

        // Iteração usando Enumeration (forma antiga, típica de código legado)
        Enumeration<String> enumeracao = conexoesAtivas.elements();
        System.out.println("Todas as conexões:");
        while (enumeracao.hasMoreElements()) {
            System.out.println("- " + enumeracao.nextElement());
        }

        // Remoção legada
        conexoesAtivas.removeElementAt(0);
        System.out.println("Capacidade interna atual: " + conexoesAtivas.capacity());
    }
}
```

### 3.4 Principais aplicações em sistemas operacionais e sistemas comerciais

**Sistemas operacionais:**
- Estruturas internas mais antigas de kernels/drivers escritos ou adaptados para Java embarcado, onde a sincronização automática evitava corrupção de dados entre threads concorrentes.
- Componentes de monitoramento de sistema que registram eventos (conexões, acessos, logs) vindos de múltiplas threads simultaneamente.

**Sistemas comerciais:**
- Sistemas corporativos legados (bancos, ERPs antigos) escritos em versões antigas do Java, anteriores ao Collections Framework (pré-Java 2).
- Aplicações Java EE mais antigas que usavam `Vector` como estrutura "segura por padrão" antes da popularização de `Collections.synchronizedList()`.
- Cenários de manutenção de sistemas legados, onde substituir `Vector` por `ArrayList` exigiria reescrever trechos sensíveis de concorrência.

---

# 4. Stack

Estende `Vector`, então **herda todos os métodos dele** (e, por consequência, todos os de `List`). Além disso, adiciona um conjunto de métodos próprios que deixam explícito o comportamento **LIFO** (Last In, First Out).

### 4.1 Métodos herdados de Vector e List

Todos os métodos das seções 1 e 3 (`add`, `get`, `remove`, `elementAt`, `firstElement`, `lastElement`, `size`, `isEmpty`, `clear`, etc.) continuam disponíveis, já que Stack é uma subclasse de Vector.

### 4.2 Métodos exclusivos do Stack

| Método            | O que faz                                       |
| -------------------- | ---------------------------------------------------- |
| `push(elemento)`     | Empilha o elemento no topo                            |
| `pop()`              | Remove e retorna o elemento do topo (lança exceção se vazia) |
| `peek()`             | Retorna o elemento do topo sem remover (lança exceção se vazia) |
| `empty()`            | Verifica se a pilha está vazia (equivalente a `isEmpty()`) |
| `search(objeto)`     | Retorna a distância do topo até o objeto (1 = topo); retorna -1 se não encontrar |

> **Observação:** hoje em dia, para pilhas, costuma-se recomendar `ArrayDeque` em vez de `Stack`, já que `Stack` herda a sincronização (mais lenta) do `Vector`. Mas para fins didáticos, `Stack` deixa o comportamento LIFO bem explícito.

### 4.3 Exemplo completo de código

```java
import java.util.Stack;

public class ExemploStack {
    public static void main(String[] args) {
        Stack<Character> pilhaParenteses = new Stack<>();
        String expressao = "(a + (b * c) - d)";
        boolean balanceada = true;

        // Validação de parênteses balanceados (uso clássico de pilha)
        for (char c : expressao.toCharArray()) {
            if (c == '(') {
                pilhaParenteses.push(c);
            } else if (c == ')') {
                if (pilhaParenteses.empty()) {
                    balanceada = false;
                    break;
                }
                pilhaParenteses.pop();
            }
        }
        balanceada = balanceada && pilhaParenteses.empty();
        System.out.println("Expressão balanceada? " + balanceada);

        // Simulação de pilha de "desfazer" de um editor
        Stack<String> acoes = new Stack<>();
        acoes.push("digitar 'Olá'");
        acoes.push("aplicar itálico");
        acoes.push("digitar 'mundo'");

        System.out.println("Ação no topo: " + acoes.peek());
        System.out.println("Desfazendo: " + acoes.pop());
        System.out.println("Pilha restante: " + acoes);

        // Busca de um elemento na pilha
        int posicao = acoes.search("digitar 'Olá'");
        System.out.println("Distância de 'digitar Olá' até o topo: " + posicao);
    }
}
```

### 4.4 Principais aplicações em sistemas operacionais e sistemas comerciais

**Sistemas operacionais:**
- Gerenciamento da pilha de chamadas de funções (*call stack*) de cada processo/thread, controlando o retorno de funções e o escopo de variáveis locais.
- Rotinas de interrupção e chamadas de sistema, que empilham o contexto de execução para restaurá-lo depois.
- Algoritmos de backtracking usados em alocação de recursos e resolução de deadlocks.

**Sistemas comerciais:**
- Funcionalidade de "desfazer/refazer" (undo/redo) em editores de texto, planilhas, IDEs e ferramentas de design gráfico.
- Botão "voltar" de navegadores e aplicativos, empilhando o histórico de telas/páginas visitadas.
- Validação e avaliação de expressões matemáticas e lógicas em sistemas financeiros e calculadoras (ex.: conversão infixa para pós-fixa).
- Compiladores e interpretadores (inclusive de linguagens de consulta) que usam pilhas para análise sintática de código.

---

## Comparativo de Desempenho entre os 4 Tipos

| Operação                | ArrayList                 | LinkedList                  | Vector                    | Stack                      |
| -------------------------- | ---------------------------- | ------------------------------- | ----------------------------- | ------------------------------- |
| Acesso `get(índice)`       | **Muito rápido** — O(1)       | Lento — O(n)                     | Rápido — O(1), mas sincronizado | Rápido — O(1), mas sincronizado |
| Inserção no fim             | Rápido — O(1) amortizado      | Rápido — O(1)                    | Rápido, porém sincronizado     | Rápido (via `push`), sincronizado |
| Inserção no início           | **Lento** — desloca tudo       | Rápido — O(1)                    | Lento — desloca tudo           | Lento — desloca tudo             |
| Remoção no meio              | Lento — desloca elementos      | Moderado — O(n) para achar o nó  | Lento — desloca elementos      | Lento — desloca elementos        |
| Thread-safe                  | Não                            | Não                               | **Sim**                        | **Sim**                          |
| Memória por elemento         | Menos (apenas array)           | Mais (nós com dois ponteiros)    | Menos (apenas array)           | Menos (apenas array)             |

---

## Quando Usar Cada Tipo

| Situação                                                | Tipo recomendado |
| ---------------------------------------------------------- | -------------------- |
| Preciso de acesso rápido por índice, sem concorrência        | `ArrayList`           |
| Vou inserir/remover muito nas extremidades (fila dupla)      | `LinkedList`          |
| Preciso de uma lista thread-safe legada (código antigo)      | `Vector`              |
| Preciso de comportamento explícito de pilha (LIFO)            | `Stack` (ou `ArrayDeque`, na prática moderna) |

---

## Métodos Estáticos Utilitários (aplicáveis aos 4 tipos, via `Collections`)

Esses métodos não pertencem à lista em si, mas operam sobre qualquer um dos quatro tipos, já que todos implementam `List`.

### Ordenação e manipulação

| Método                              | O que faz                          |
| -------------------------------------- | -------------------------------------- |
| `Collections.sort(lista)`               | Ordena em ordem natural                |
| `Collections.sort(lista, comparador)`   | Ordena com critério personalizado      |
| `Collections.reverse(lista)`            | Inverte a ordem dos elementos          |
| `Collections.shuffle(lista)`            | Embaralha aleatoriamente               |
| `Collections.rotate(lista, distância)`  | Rotaciona os elementos                 |
| `Collections.swap(lista, i, j)`         | Troca elementos de posição             |
| `Collections.fill(lista, elemento)`     | Preenche toda a lista com o mesmo valor |
| `Collections.copy(destino, origem)`     | Copia elementos de uma lista para outra |
| `Collections.replaceAll(lista, antigo, novo)` | Substitui todas as ocorrências de um valor |

### Busca e estatística

| Método                                  | O que faz                       |
| -------------------------------------------- | ------------------------------------ |
| `Collections.binarySearch(lista, chave)`      | Busca binária (lista precisa estar ordenada) |
| `Collections.max(lista)`                      | Retorna o maior elemento              |
| `Collections.min(lista)`                      | Retorna o menor elemento              |
| `Collections.frequency(lista, elemento)`      | Conta quantas vezes o elemento aparece |
| `Collections.disjoint(coleção1, coleção2)`    | Verifica se duas coleções não têm elementos em comum |

### Criação e proteção

| Método                              | O que faz                          |
| -------------------------------------- | -------------------------------------- |
| `Collections.singletonList(elemento)`   | Lista imutável com um único elemento   |
| `Collections.nCopies(n, elemento)`      | Lista imutável com "n" repetições do elemento |
| `Collections.emptyList()`               | Lista vazia imutável                   |
| `Collections.unmodifiableList(lista)`   | Retorna uma versão somente leitura     |
| `Collections.synchronizedList(lista)`   | Retorna uma versão thread-safe         |
| `Collections.checkedList(lista, tipo)`  | Retorna uma versão com verificação de tipo em tempo de execução |

---

## Métodos de Stream (Java 8+, aplicáveis aos 4 tipos via `.stream()`)

| Método                 | O que faz                                |
| ------------------------- | --------------------------------------------- |
| `stream()`                 | Cria um stream sequencial a partir da lista    |
| `parallelStream()`         | Cria um stream paralelo                        |
| `filter(condição)`         | Seleciona os elementos que atendem à condição  |
| `map(transformação)`       | Transforma cada elemento                       |
| `flatMap(achatamento)`     | "Achata" streams aninhados em um único stream  |
| `distinct()`               | Remove elementos duplicados                    |
| `sorted()`                 | Ordena os elementos do stream                  |
| `limit(n)`                 | Limita o stream a "n" elementos                |
| `skip(n)`                  | Pula os "n" primeiros elementos                |
| `peek(ação)`               | Executa uma ação para depuração (debug)        |
| `collect(coletor)`         | Coleta o resultado (ex.: de volta para uma lista) |
| `reduce(operação)`         | Reduz o stream a um único valor                |
| `count()`                  | Conta os elementos do stream                   |
| `anyMatch(condição)`       | Verifica se algum elemento atende à condição   |
| `allMatch(condição)`       | Verifica se todos os elementos atendem         |
| `noneMatch(condição)`      | Verifica se nenhum elemento atende             |
| `findFirst()`              | Retorna o primeiro elemento do stream          |
| `findAny()`                | Retorna qualquer elemento do stream            |
| `min()` / `max()`          | Retorna o menor/maior valor do stream          |
| `forEach(ação)`            | Executa uma ação para cada elemento do stream  |

### Coletores (`Collectors`) mais usados com listas

| Método                             | O que faz                        |
| -------------------------------------- | ------------------------------------- |
| `Collectors.toList()`                   | Coleta o resultado de volta para uma `List` |
| `Collectors.toSet()`                    | Coleta para um `Set`                   |
| `Collectors.toMap()`                    | Coleta para um `Map`                   |
| `Collectors.joining(separador)`         | Concatena elementos String             |
| `Collectors.groupingBy(classificador)`  | Agrupa elementos por um critério       |
| `Collectors.partitioningBy(condição)`   | Divide os elementos em dois grupos (true/false) |
| `Collectors.summarizingInt()`           | Gera estatísticas (soma, média, etc.)  |

---

## Resumo dos Princípios

1. **ArrayList**: use quando o acesso por índice é frequente e não há concorrência entre threads.
2. **LinkedList**: use quando há muitas inserções/remoções nas extremidades (fila dupla).
3. **Vector**: praticamente só aparece em código legado — use quando precisar de sincronização automática "à moda antiga".
4. **Stack**: use quando o comportamento LIFO precisa ficar explícito no código (na prática moderna, `ArrayDeque` costuma substituí-lo).
5. **Streams e Collections**: os métodos utilitários funcionam sobre qualquer um dos quatro tipos, pois todos implementam `List`.
6. **Listas imutáveis**: prefira `List.of()` (Java 9+) quando a lista não deve ser alterada.

Esta é a referência completa dos métodos de lista em Java, organizada pelos 4 tipos — sem código, apenas conceitual!
