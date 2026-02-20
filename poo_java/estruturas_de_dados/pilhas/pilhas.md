A história da **Pilha (Stack)** é fascinante porque ela não nasceu apenas para organizar dados, mas para resolver um problema específico: como os computadores poderiam "lembrar" de onde pararam ao executar uma sub-rotina ou função.

Aqui está a evolução histórica desta estrutura baseada no princípio **LIFO** (*Last In, First Out* - O último a entrar é o primeiro a sair):

---

### 1. A Origem Teórica (1946 - 1950)

O conceito de empilhamento de dados foi proposto pela primeira vez por **Alan Turing** por volta de 1946. Ele descreveu o uso de "buracos" (bury) e "desenterros" (unbury) como uma forma de chamar e retornar de sub-rotinas em máquinas primitivas.

* **O Problema:** Quando um programa principal chama uma função A, e a função A chama uma função B, o computador precisa salvar o endereço de retorno para saber para onde voltar.
* **A Solução:** Turing percebeu que uma estrutura de "pilha" era a forma perfeita de gerenciar esses endereços de retorno.

### 2. A Patente e a Formalização (1955 - 1957)

A formalização da pilha como a conhecemos hoje (com os termos **Push** para inserir e **Pop** para remover) é creditada aos alemães **Friedrich L. Bauer** e **Klaus Samelson**.

* **A Patente:** Em 1955, eles depositaram uma patente para o que chamaram de "Pilha de Operação" para tradução de linguagens de programação.
* **Reconhecimento:** Por este trabalho, Bauer recebeu o prêmio *IEEE Computer Pioneer Award* em 1988.

### 3. A Linguagem LISP e a Pilha de Software (1958)

Com a criação da linguagem **LISP** por John McCarthy, a pilha tornou-se essencial. O LISP dependia fortemente da **recursividade** (uma função que chama a si mesma).

* Sem uma pilha para armazenar o estado de cada chamada recursiva, o LISP não poderia funcionar. Isso consolidou a pilha como uma estrutura de dados fundamental no software, e não apenas um truque de hardware.

### 4. Notação Polonesa Reversa e Calculadoras (Anos 60 - 70)

A pilha saiu do interior dos computadores e chegou às mãos dos usuários através das calculadoras da **Hewlett-Packard (HP)**.

* **RPN (Reverse Polish Notation):** Em vez de digitar `2 + 3`, o usuário digitava `2 [ENTER] 3 [+]`.
* **Funcionamento:** O `2` ia para a pilha, o `3` ia para o topo da pilha, e o operador `+` retirava os dois, somava-os e colocava o resultado de volta no topo. Isso eliminava a necessidade de parênteses em cálculos complexos.

---

### 💡 Exemplos Práticos no Decorrer da História

Para entender como a pilha moldou a computação, veja estes três usos clássicos:

#### A. A Pilha de Chamada (Call Stack)

É o uso mais importante. Cada vez que o seu código executa uma função, o computador cria um "frame" no topo da pilha com as variáveis locais. Quando a função termina, esse frame é removido (*Pop*).

* **Curiosidade:** O termo **Stack Overflow** (estouro de pilha) ocorre quando há chamadas demais (geralmente uma recursão infinita) e a memória da pilha acaba.

#### B. O Botão "Retroceder" e "Desfazer"

* **Navegador:** Quando você visita sites, a URL atual é empilhada. Ao clicar em "Voltar", o navegador faz um *Pop* na URL atual para revelar a anterior.
* **Editores de Texto:** Cada letra que você digita ou apaga é uma operação empilhada. O `Ctrl + Z` remove a última operação do topo da pilha.

#### C. Avaliação de Expressões Matemáticas

Compiladores usam pilhas para garantir que os parênteses em uma fórmula como `( (A + B) * (C - D) )` estejam balanceados e para converter a fórmula em algo que o processador entenda.

---

### 📊 Resumo Técnico: A Pilha em Números

* **Operações Principais:** `Push` (Inserir), `Pop` (Remover), `Peek/Top` (Olhar o topo).
* **Complexidade:** Todas as operações principais são ****, o que significa que são instantâneas, independentemente de haver 10 ou 10 milhões de itens na pilha.
* **Regra de Ouro:** Acesso apenas ao topo. Não se pode "puxar" um elemento do meio sem remover todos os que estão acima.
