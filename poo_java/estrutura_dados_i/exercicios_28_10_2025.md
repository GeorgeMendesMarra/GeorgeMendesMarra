# 🧩 Questionário — Estrutura de Dados em Java (Fila com Queue e LinkedList)

**Código-base:** `ExemploFila1.java`

---

## 1. Qual é o comportamento principal da interface `Queue` utilizada no código?

a) Os elementos são organizados em ordem aleatória.  
b) Os elementos seguem a política **LIFO** (último a entrar, primeiro a sair).  
c) Os elementos seguem a política **FIFO** (primeiro a entrar, primeiro a sair).  
d) Os elementos são acessados por índice, como em uma lista.  
e) A fila remove todos os elementos automaticamente após 10 inserções.

---

## 2. Qual será a **primeira linha de saída** exibida no console ao executar o programa?

a) `Fila após adição: [Alice, Bruno, Carla]`  
b) `--- Adicionando Clientes ---`  
c) `Primeiro da fila (peek): Alice`  
d) `Cliente atendido (poll): Alice`  
e) `A fila está vazia? true`

---

## 3. Qual método é utilizado para **remover o elemento do início da fila** sem lançar exceção se a fila estiver vazia?

a) `remove()`  
b) `poll()`  
c) `peek()`  
d) `pop()`  
e) `delete()`

---

## 4. O método `peek()` remove o elemento do início da fila?

a) Sim, ele remove e retorna o elemento.  
b) Não, ele apenas visualiza o primeiro elemento sem removê-lo.  
c) Sim, mas somente se a fila tiver mais de um elemento.  
d) Depende da implementação da fila.  
e) O método `peek()` é usado para inserir elementos.

---

## 5. Qual é a diferença entre os métodos `add()` e `offer()` na interface `Queue`?

a) `add()` e `offer()` fazem exatamente o mesmo e nunca lançam exceção.  
b) `offer()` lança exceção quando a fila está cheia.  
c) `add()` pode lançar exceção se a fila estiver cheia, enquanto `offer()` retorna `false`.  
d) `add()` remove o primeiro elemento e adiciona outro.  
e) `offer()` serve apenas para filas de prioridade.

---

## 6. Qual seria a **substituição correta** da linha de instância da fila para usar `ArrayDeque`?

a) `Queue<String> filaDeClientes = new ArrayDeque<>();`  
b) `Queue<String> filaDeClientes = new Stack<>();`  
c) `Queue<String> filaDeClientes = new ArrayList<>();`  
d) `Queue<String> filaDeClientes = new Vector<>();`  
e) `Queue<String> filaDeClientes = new PriorityQueue<>();`

---

## 7. O que acontece se tentarmos inserir `null` na fila implementada com `ArrayDeque`?

a) O programa aceita normalmente o valor `null`.  
b) O programa lança uma exceção `NullPointerException`.  
c) O programa substitui `null` por uma string vazia.  
d) O programa remove todos os elementos antes de adicionar `null`.  
e) O programa encerra sem erros, mas o valor não é armazenado.

---

## 8. Qual é o comportamento esperado de um método `atenderTodos(Queue<String> fila)` que usa `poll()` em um laço até a fila ficar vazia?

a) Atender apenas o primeiro cliente.  
b) Lançar exceção se a fila estiver vazia.  
c) Remover e imprimir todos os clientes até a fila esvaziar.  
d) Adicionar novos clientes durante o atendimento.  
e) Inverter a ordem da fila.

---

## 9. Qual seria o **resultado final impresso** da fila após as três chamadas a `poll()` no código?

a) `[Alice, Bruno, Carla]`  
b) `[Carla]`  
c) `[]` (fila vazia)  
d) `[Bruno, Carla]`  
e) `[Alice]`

---

## 10. Em relação ao método `isEmpty()`, qual das alternativas é **verdadeira**?

a) Retorna `true` quando a fila contém pelo menos um elemento.  
b) Lança exceção se a fila for nula.  
c) Retorna `true` apenas quando todos os elementos foram removidos.  
d) Sempre retorna `false`, pois a fila é inicializada.  
e) Remove o último elemento ao ser chamado.

---
