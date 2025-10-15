## Quiz de Filas e Pilhas em Java

### Questões sobre o programa de fila (`ExemploFila1.java`):

**1. Qual princípio fundamental de Estrutura de Dados é estritamente seguido pela `Queue` (Fila), conforme demonstrado no exemplo?**

    a) LIFO (Last In, First Out)    
    b) FIFO (First In, First Out)
    c) LILO (Last In, Last Out)
    d) FOLI (First Out, Last In)

**2. No exemplo, qual será o conteúdo da `filaDeClientes` imediatamente após a execução da linha que processa o segundo cliente (`String atendido2 = filaDeClientes.poll();`)?**

    a) `[Alice, Bruno, Carla]`
    b) `[]` (Vazia)
    c) `[Carla]`
    d) `[Bruno, Carla]`

**3. Qual é a principal diferença de funcionalidade entre os métodos `peek()` e `poll()` da interface `Queue`, conforme demonstrado no código?**

    a) `peek()` insere no início da fila, e `poll()` remove do final.
    b) `poll()` remove o elemento, enquanto `peek()` apenas o visualiza.
    c) Ambos removem o primeiro elemento, mas `poll()` retorna o valor e `peek()` não.
    d) `peek()` lança uma exceção se a fila estiver vazia, e `poll()` retorna `null`.

**4. No contexto da Coleção Java (Java Collections Framework), qual classe é utilizada no código para *implementar* o comportamento da interface `Queue`?**

    a) `ArrayList`
    b) `PriorityQueue`
    c) `Stack`
    d) `LinkedList`

**5. Considere que a linha `filaDeClientes.add("Diana");` fosse adicionada imediatamente após a linha `filaDeClientes.add("Carla");`. Qual elemento seria retornado pela última operação `poll()` que resulta no atendimento de `Carla`?**

    a) Alice
    b) Bruno
    c) Diana
    d) Carla

---

### Questões Adicionais (Comparando Filas e Pilhas)

**6. Qual das seguintes afirmações descreve corretamente a diferença entre uma Pilha e uma Fila em Java em relação à ordem de processamento?**

    a) A Fila utiliza o princípio LIFO, e a Pilha utiliza o princípio FIFO.
    b) A Pilha utiliza o princípio LIFO, e a Fila utiliza o princípio FIFO.
    c) Ambas utilizam o princípio FIFO, mas em Pilhas o acesso é restrito.
    d) Ambas utilizam o princípio LIFO, mas em Filas a remoção é feita pelo início.

**7. Em uma Pilha (`Stack`), os métodos para adicionar e remover elementos são, respectivamente:**

    a) `add()` e `poll()`
    b) `push()` e `pop()`
    c) `offer()` e `peek()`
    d) `enqueue()` e `dequeue()`

**8. Se a sequência de operações `add(1)`, `add(2)`, `add(3)` for executada tanto em uma Fila quanto em uma Pilha (assumindo que a Pilha usa `push`), qual seria a sequência de elementos retornados se usarmos **três remoções consecutivas** (na Fila: `poll()`; na Pilha: `pop()`)?**

    a) **Fila:** 3, 2, 1; **Pilha:** 1, 2, 3
    b) **Fila:** 1, 2, 3; **Pilha:** 3, 2, 1
    c) **Fila:** 1, 3, 2; **Pilha:** 2, 1, 3
    d) **Fila:** 3, 1, 2; **Pilha:** 1, 3, 2

**9. Em Java, a classe `java.util.Stack` geralmente é desaconselhada. Para implementar uma Pilha de maneira mais moderna e eficiente, qual interface ou classe da Java Collections Framework é comumente utilizada?**

    a) `java.util.List` com implementação `ArrayList`
    b) `java.util.Queue` com implementação `PriorityQueue`
    c) `java.util.Deque` com implementação `ArrayDeque`
    d) `java.util.Map` com implementação `HashMap`

**10. Considere um sistema de gerenciamento de tarefas. Se você precisa processar tarefas na ordem exata em que foram recebidas (ex: fila de impressão), você usaria uma **Fila**. Se você precisa gerenciar o histórico de comandos que podem ser desfeitos, onde o último comando é o primeiro a ser desfeito (operação de "undo"), você usaria uma **Pilha**. Por quê?**

    a) O "undo" segue FIFO, e a fila de impressão segue LIFO.
    b) O "undo" segue LIFO (último a entrar, primeiro a sair), e a fila de impressão segue FIFO (primeiro a entrar, primeiro a sair).
    c) As Pilhas são mais rápidas para processamento, e Filas são mais seguras para impressão.
    d) Filas podem ser implementadas com arrays, e Pilhas, apenas com listas encadeadas.
