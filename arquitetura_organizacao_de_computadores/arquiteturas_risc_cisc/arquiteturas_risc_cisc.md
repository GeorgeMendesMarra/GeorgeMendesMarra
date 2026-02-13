### 1. Arquiteturas Paralelas

Diferente da arquitetura sequencial clássica, a computação paralela divide um problema em partes menores para serem resolvidas simultaneamente.

* **Multiprocessadores (Sistemas de Memória Compartilhada):** Consistem em um único computador com múltiplas CPUs que compartilham a mesma memória principal e barramentos. É a arquitetura dos PCs e servidores atuais.
* **Multicomputadores (Sistemas de Memória Distribuída):** São redes de computadores (nós) onde cada um tem sua própria memória local e se comunicam através de uma rede de alta velocidade. É a base da **Computação em Nuvem** onde o GitHub, mencionado em seu plano, está hospedado.



---

### 2. Filosofias de Design: RISC vs. CISC

A forma como o processador interpreta instruções define sua eficiência e complexidade:

| Característica | **CISC** (*Complex Instruction Set Computer*) | **RISC** (*Reduced Instruction Set Computer*) |
| --- | --- | --- |
| **Instruções** | Muitas instruções complexas e variadas. | Poucas instruções simples e de tamanho fixo. |
| **Execução** | Uma instrução pode levar vários ciclos de clock. | Quase toda instrução é executada em 1 ciclo. |
| **Hardware** | Ênfase no hardware (chips mais densos). | Ênfase no software (compiladores mais eficientes). |
| **Exemplos** | Processadores Intel x86 e AMD. | Processadores ARM (Apple M1/M2, Smartphones). |

---

### 3. Conexão com a Cibersegurança e o Back-End

Integrando esses conceitos ao seu cronograma de 2026 para a **Turma 34**:

* **Disponibilidade e Paralelismo:** Arquiteturas paralelas permitem que sistemas de Back-End suportem milhares de acessos simultâneos. Se um "nó" falha, outros assumem, garantindo a **Disponibilidade**.


* **Segurança no Nível da Instrução:** Processadores RISC, por serem mais simples, costumam ter uma superfície de ataque menor no nível de hardware, facilitando a implementação de mecanismos de segurança como o isolamento de memória.
* 
**Integridade em Sistemas Distribuídos:** Em multicomputadores, garantir a **Integridade** dos dados enquanto eles viajam entre diferentes máquinas é um dos maiores desafios da cibersegurança atual.



---

Com este tópico, cobrimos toda a base necessária para que os alunos compreendam que a segurança da informação não ocorre no vácuo; ela depende de:

1. **Hardware:** (CISC/RISC, Memórias, I/O).


2. **Organização:** (Von Neumann, Paralelismo).


3. **Lógica:** (Aritmética Binária e Booleana).
