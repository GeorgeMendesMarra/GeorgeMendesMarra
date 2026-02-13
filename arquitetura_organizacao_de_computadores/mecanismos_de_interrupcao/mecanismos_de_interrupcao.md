### 1. Mecanismo de Interrupção (Hardware)

Uma interrupção é um sinal enviado ao processador por um dispositivo de hardware indicando que um evento precisa de atenção imediata.

* **Funcionamento:** O processador suspende temporariamente seu ciclo de execução atual, salva o estado (registradores), executa uma rotina especial chamada **Tratador de Interrupção** (ISR) e depois retorna ao que estava fazendo.
* **Exemplos Comuns:** * Pressionar uma tecla no teclado.
* Chegada de dados pela placa de rede (essencial para o **Back-End** receber requisições).


* Sinal de um temporizador (timer) para o sistema operacional alternar entre tarefas.



### 2. Mecanismo de Exceção (Software)

As exceções são interrupções geradas internamente pelo próprio processador como resultado da execução de uma instrução que causou um erro ou uma condição especial.

* **Tipos de Exceção:**
* **Faltas (Faults):** Erros que podem ser corrigidos, permitindo que o programa continue (ex: *Page Fault* na memória).
* **Trapas (Traps):** Intencionais, usadas para chamadas de sistema (System Calls), onde o programa solicita algo ao Kernel.
* **Abortos (Aborts):** Erros graves e irrecuperáveis (ex: falha de hardware).


* **Exemplos Práticos:** Divisão por zero ou tentativa de acessar um endereço de memória inválido.

### 3. Relação Direta com a Cibersegurança

Na sua aula do dia **15/01/26** sobre "Reconhecer riscos relacionados à informação", as interrupções e exceções desempenham um papel defensivo:

* 
**Proteção de Memória:** Se um programa tenta acessar uma área de memória proibida (comum em ataques de invasão), o processador gera uma **exceção de violação de acesso**, interrompendo o ataque antes que ele comprometa a **Integridade**.


* 
**Disponibilidade:** Um excesso proposital de interrupções (como em um ataque de Negação de Serviço - DoS) pode sobrecarregar o processador, impedindo que ele execute o ciclo de instruções normal, afetando a **Disponibilidade** do sistema.



---
