1) Execute o programa ExemploEnum.java e monte sua semana de prova N1 da UniAlfa.
2) Execute o programa ExemploEnumNumerico.java e adicione o modo Darth Vader.
3) Teste o programa GerenciadorTarefasColorido.java e veja se a sequência de menus está correta.
4) Execute o programa ExemploVector.
5) Quais as diferenças entre Vector e Stack?

Apesar de serem coleções de dados em Java, **Vector** e **Stack** possuem propósitos e características diferentes. A principal distinção é que `Vector` é uma implementação de **lista dinâmica**, enquanto `Stack` é uma implementação de **pilha**.

Vamos entender as diferenças com mais detalhes:

### Vector

O `Vector` é uma classe que implementa a interface `List`, semelhante ao `ArrayList`. Ele funciona como um **array dinâmico**, o que significa que pode crescer ou encolher de tamanho conforme você adiciona ou remove elementos.

**Características principais:**

* **Sincronização:** O `Vector` é **sincronizado**, ou seja, ele é "thread-safe". Isso significa que múltiplos threads podem acessar e modificar um `Vector` ao mesmo tempo sem corromper os dados. No entanto, essa segurança de threads tem um custo: ela torna as operações mais lentas em um ambiente de thread único.
* **Acesso e modificação:** Você pode acessar, adicionar e remover elementos em qualquer posição (início, meio ou fim) usando métodos como `add(index, element)`, `get(index)` e `remove(index)`.
* **Herança:** `Vector` herda da classe `AbstractList` e implementa a interface `List`.

### Stack

O `Stack` é uma classe que **estende `Vector`**. No entanto, ele foi projetado para seguir o princípio **LIFO (Last-In, First-Out)**, que é o comportamento de uma pilha. Pense em uma pilha de pratos: o último prato que você coloca é o primeiro que você tira.

**Características principais:**

* **LIFO (Last-In, First-Out):** A `Stack` foi projetada para operações específicas de pilha. Os métodos principais são:
    * `push()`: Adiciona um elemento no topo da pilha.
    * `pop()`: Remove e retorna o elemento do topo da pilha.
    * `peek()`: Retorna o elemento do topo sem removê-lo.
    * `empty()`: Verifica se a pilha está vazia.
* **Sincronização:** Como `Stack` estende `Vector`, ela também é **sincronizada** e, por consequência, mais lenta.
* **Herança:** `Stack` herda da classe `Vector`.

---

### Qual usar?

Hoje em dia, o `Vector` e o `Stack` são considerados **classes legadas** na maioria das situações, principalmente devido à sua natureza sincronizada que impacta negativamente a performance em ambientes de thread único.

* Para uma **lista dinâmica**, a classe **`ArrayList`** é a escolha preferida. Ela não é sincronizada, o que a torna muito mais rápida. Se você precisar de uma versão thread-safe, pode usar `Collections.synchronizedList(new ArrayList<>())`.
* Para uma **estrutura de pilha**, a classe **`Deque` (Double-Ended Queue)**, especialmente implementada como **`ArrayDeque`**, é a opção moderna e mais performática.

Em resumo, a principal diferença é o **propósito**: o `Vector` serve como uma lista dinâmica, enquanto o `Stack` serve como uma pilha. No entanto, ambas as classes são consideradas legadas, e existem alternativas mais eficientes no ecossistema Java atual.
