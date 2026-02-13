### 1. Ciclo de Execução de Instruções (Ciclo de Busca-Decodificação-Execução)

Toda tarefa realizada pelo computador, desde a criação de um blog no GitHub até a criptografia de uma senha, é quebrada em pequenas instruções que seguem este ciclo repetitivo:

1. **Busca (Fetch):** A Unidade de Controle busca a próxima instrução na memória principal (RAM), usando o endereço armazenado no Program Counter (PC).
2. **Decodificação (Decode):** O processador interpreta o que a instrução deve fazer (ex: somar, mover dados ou comparar).
3. **Execução (Execute):** A Unidade Lógica e Aritmética (ULA) realiza a operação matemática ou lógica necessária.
4. **Escrita (Store/Write-back):** O resultado é gravado de volta na memória ou em um registrador.

---

### 2. Conjuntos de Instruções (ISA - Instruction Set Architecture)

O conjunto de instruções é a "linguagem" que o processador entende. Ele define as operações que o hardware pode realizar. Existem dois modelos principais que influenciam o desenvolvimento de sistemas:

* **CISC (Complex Instruction Set Computer):** Instruções complexas que realizam várias tarefas de uma vez. Comum em processadores Intel e AMD (computadores de mesa).
* **RISC (Reduced Instruction Set Computer):** Instruções simples e rápidas. É a base da arquitetura ARM, usada em smartphones e servidores modernos de alta eficiência.

**Relação com Cibersegurança:** Muitos ataques de *Buffer Overflow* tentam injetar instruções maliciosas diretamente no ciclo de execução para desviar o fluxo do programa para um código atacante.

---

### 3. Modos de Endereçamento

Os modos de endereçamento definem como o processador localiza os dados (operandos) que precisa processar. Na sua aula sobre **segurança da informação**, entender o endereçamento ajuda a compreender como a memória é protegida.

* **Imediato:** O valor já faz parte da instrução (ex: somar 5).
* **Direto:** A instrução contém o endereço exato de onde o dado está na RAM.
* **Indireto:** A instrução aponta para um local que contém o endereço do dado (muito usado em ponteiros e estruturas complexas de Back-End).
* **Por Registrador:** O dado está dentro de um dos registradores internos da CPU, sendo o modo mais rápido.

---
