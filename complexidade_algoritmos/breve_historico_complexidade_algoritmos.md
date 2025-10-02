O breve histórico da **Complexidade de Algoritmos** está intimamente ligado ao nascimento da Ciência da Computação e à necessidade de classificar o que é e o que não é um problema prático.

---

## O Início: A Computabilidade e a Máquina de Turing (Décadas de 1930 e 1940)

Antes de medir a **eficiência** dos algoritmos, os matemáticos precisavam saber se um problema era **solucionável** por um computador.

* **Alan Turing (1936):** Introduziu a **Máquina de Turing**, um modelo teórico que formalizou a ideia de **algoritmo** e **computação**. Isso estabeleceu o que pode ser computado (*computabilidade*).
* **John von Neumann (Anos 50):** Enquanto desenvolvia os primeiros computadores modernos, já se preocupava e discutia a distinção entre algoritmos de tempo **polinomial** (considerados rápidos) e **exponencial** (considerados lentos ou impraticáveis).

---

## O Nascimento Formal da Teoria da Complexidade (Década de 1960)

Com a proliferação dos computadores, a questão mudou de "o que podemos calcular?" para **"o quão rápido podemos calcular?"**.

* **Hartmanis e Stearns (1965):** Publicaram o artigo fundamental "On the Computational Complexity of Algorithms", que formalizou o campo de estudo. Eles introduziram a noção de **medida de complexidade** (tempo e espaço) em função do tamanho da entrada.
* **Cobham (1965):** Formalizou a distinção crucial entre problemas resolvidos em **tempo polinomial** ($O(n^k)$), considerados *tratáveis* ou *eficientes*, e aqueles que exigem tempo superior.

---

## O Marco: As Classes P, NP e o Problema do Milênio (Década de 1970)

O campo atingiu o seu auge ao tentar entender a relação entre problemas difíceis.

* **Stephen Cook (1971):** Publicou um trabalho que formalizou a classe de problemas **NP-completo** e demonstrou que o problema da Satisfatibilidade Booleana (**SAT**) era o primeiro problema a ser **NP-completo**.
* **Richard Karp (1972):** Expandiu o trabalho de Cook, mostrando que muitos problemas importantes em otimização combinatória (como o problema do caixeiro viajante e o problema do clique) também eram NP-completos, unindo-os em uma "classe de dificuldade".
* **O Problema P vs. NP:** Este período cristalizou a questão mais importante da teoria: **P é igual a NP?** (Ou seja, todo problema cuja solução pode ser rapidamente **verificada** também pode ser rapidamente **resolvido**?) Esta questão continua sendo um dos sete **Problemas do Prêmio do Milênio**, sem solução até hoje.

---

## Resumo da Evolução

| Período | Foco Principal | Conceito Chave |
| :--- | :--- | :--- |
| **1930s-1950s** | Computabilidade | Máquina de Turing, Polinomial vs. Exponencial |
| **1960s** | Formalização da Eficiência | Complexidade Assintótica (**Big O**), Tempo Polinomial ($P$) |
| **1970s** | Classificação da Dificuldade | Classes $NP$, **NP-Completo** |
| **Hoje** | Aplicação e Novos Paradigmas | Algoritmos quânticos, *Hardware* e Otimização |

A análise de complexidade (notação **Big O**) se tornou a ferramenta padrão para cientistas da computação medirem a **escalabilidade** de um algoritmo, garantindo que ele funcione bem mesmo com grandes volumes de dados.
