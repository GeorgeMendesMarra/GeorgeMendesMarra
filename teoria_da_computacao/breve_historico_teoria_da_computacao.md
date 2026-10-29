# 🧠 Breve Histórico da Teoria da Computação

A **Teoria da Computação** é o ramo da Ciência da Computação que estuda os **fundamentos matemáticos e lógicos da computação**. Ela responde a três perguntas centrais:

1. **O que é possível computar?** → *Teoria da Computabilidade*
2. **Quanto custa computar?** (tempo e memória) → *Teoria da Complexidade*
3. **Como descrever e reconhecer linguagens e padrões?** → *Linguagens Formais e Autômatos*

Essa área nasceu **antes dos computadores digitais**, quando matemáticos e lógicos tentavam entender a natureza do raciocínio e do cálculo mecânico. Hoje ela sustenta compiladores, linguagens de programação, segurança da informação, bancos de dados, inteligência artificial e até a criptografia.

---

## 🗺️ Linha do Tempo

| Ano | Marco | Personagem(ns) |
|-----|-------|----------------|
| 1679 | Sonho de um "cálculo do raciocínio" (*calculus ratiocinator*) | Leibniz |
| 1847 | Álgebra booleana: a lógica como cálculo | George Boole |
| 1891 | Argumento da diagonal (base das provas de indecidibilidade) | Georg Cantor |
| 1900 | Programa de Hilbert e os 23 problemas | David Hilbert |
| 1928 | Formulação do **Entscheidungsproblem** (problema da decisão) | Hilbert e Ackermann |
| 1931 | Teoremas da incompletude | Kurt Gödel |
| 1932–36 | λ-cálculo e prova de que o problema da decisão é insolúvel | Alonzo Church |
| 1936 | **Máquina de Turing** e a indecidibilidade da parada | Alan Turing |
| 1936 | Sistemas de produção (modelo equivalente) | Emil Post |
| 1936 | Funções recursivas e equivalência entre os modelos | Stephen Kleene |
| 1937 | Álgebra booleana aplicada a circuitos de relés | Claude Shannon |
| 1943 | Neurônio formal (precursor dos autômatos e das redes neurais) | McCulloch e Pitts |
| 1945 | Arquitetura de programa armazenado (relatório do EDVAC) | John von Neumann |
| 1951–56 | Expressões regulares | Stephen Kleene |
| 1956–59 | **Hierarquia de Chomsky** | Noam Chomsky |
| 1959 | Autômatos finitos não determinísticos | Rabin e Scott |
| 1961–63 | Máquinas de contadores/registradores | Minsky, Lambek, Shepherdson e Sturgis |
| 1965 | Fundação da **Teoria da Complexidade** | Hartmanis e Stearns |
| 1965 | Tempo polinomial como noção de "eficiente" | Cobham e Edmonds |
| 1971–73 | **NP-completude** | Stephen Cook e Leonid Levin |
| 1972 | 21 problemas NP-completos | Richard Karp |
| 1985–96 | Computação quântica (Deutsch, Shor, Grover) | vários |
| 2000 | P vs NP entra na lista dos Problemas do Milênio | Instituto Clay |

---

## 📜 Origens e Contexto Histórico

No início do século XX, a matemática passava por uma crise de fundamentos. **David Hilbert** (1862–1943) propôs o **Programa de Hilbert**: formalizar toda a matemática em um conjunto finito de axiomas e regras lógicas, e provar que esse sistema seria **completo** (tudo que é verdade pode ser provado), **consistente** (não gera contradições) e **decidível**.

Em 1928, Hilbert e Wilhelm Ackermann formularam o **Entscheidungsproblem** (problema da decisão):

> *Existe um procedimento mecânico que, em um número finito de passos, decide se uma sentença da lógica de primeira ordem é válida?*

Para responder "sim" ou "não", era preciso antes definir o que significa **procedimento mecânico**, isto é, **algoritmo**. Foi essa necessidade que fez nascer a Teoria da Computação.

### Os protagonistas

- **Georg Cantor (1845–1918):** criou o **argumento da diagonal**, técnica que mais tarde seria a base das provas de que certos problemas não têm solução algorítmica.
- **Kurt Gödel (1906–1978):** em 1931, com os **teoremas da incompletude**, mostrou que em qualquer sistema formal suficientemente poderoso existem afirmações verdadeiras que não podem ser provadas dentro dele. Isso abalou o Programa de Hilbert e apontou limites para o raciocínio formal. Gödel também contribuiu com as **funções recursivas**, um dos modelos de computação.
- **Alonzo Church (1903–1995):** desenvolveu o **λ-cálculo**, um sistema formal baseado em funções e substituição de símbolos. Em 1936, usou-o para provar que o Entscheidungsproblem **não tem solução**.
- **Alan Turing (1912–1954):** em 1936, de forma **independente** de Church, propôs a **Máquina de Turing** e chegou à mesma conclusão negativa por outro caminho. Pouco depois, Turing provou que o λ-cálculo e as máquinas de Turing definem **exatamente as mesmas funções**, e foi trabalhar com Church em Princeton.
- **Emil Post (1897–1954) e Stephen Kleene (1909–1994):** apresentaram outros formalismos (sistemas de produção de Post, funções recursivas de Kleene) e ajudaram a mostrar que **todos esses modelos são equivalentes**.

> 💡 **Curiosidade:** o resultado de Church e o de Turing chegaram em poucos meses de diferença, em 1936. O fato de abordagens tão distintas terem dado no mesmo conjunto de funções computáveis foi o principal argumento a favor da Tese de Church-Turing.

---

## 🧩 Noções de Programas e Máquinas

Com a formalização, foi possível definir matematicamente o que é um **programa** (uma sequência finita de instruções que manipula símbolos segundo regras precisas) e o que é uma **máquina** (um modelo abstrato que executa esses programas).

Estudar modelos abstratos permite analisar a computação **sem depender de uma tecnologia específica**: as conclusões valem para qualquer computador, passado, presente ou futuro (exceto, em parte, o quântico, como veremos adiante).

| Modelo | Memória | Poder computacional | Uso típico |
|--------|---------|---------------------|-----------|
| **Autômato finito** | Nenhuma (só o estado atual) | Linguagens regulares | Validação de entradas, analisadores léxicos, protocolos |
| **Autômato com pilha** | Uma pilha | Linguagens livres de contexto | Análise sintática (parsers) |
| **Máquina de registradores / contadores** | Registradores com números inteiros | Equivalente à Máquina de Turing | Raciocinar sobre programas "estilo assembly" |
| **Máquina de Turing** | Fita infinita | Tudo que é efetivamente computável | Modelo universal de computação |

Esses modelos permitem analisar:

- **se um problema pode ser resolvido** (decidibilidade);
- **quanto tempo e espaço são necessários** (complexidade);
- **que classe de linguagem uma máquina consegue reconhecer** (hierarquia de Chomsky).

---

## ⚙️ Computabilidade Efetiva

O conceito de **computabilidade efetiva** é o núcleo da área. Ele responde:

> *O que significa dizer que um problema é computável?*

Um problema é **efetivamente computável** se existe um **método mecânico**, executável passo a passo, que leva ao resultado correto em tempo finito, sem recorrer a intuição, criatividade ou adivinhação.

Antes de 1936, essa ideia era apenas intuitiva. O λ-cálculo, as funções recursivas e a Máquina de Turing deram a ela uma **definição matemática precisa**. Esse estudo revelou que:

- existem **problemas insolúveis** (como o Problema da Parada);
- **nem todo problema** pode ser decidido por um algoritmo;
- mesmo entre os computáveis, alguns são **intratáveis na prática**, pois exigem tempo ou memória exponenciais.

### Decidível, semidecidível e indecidível

| Classe | Significado | Exemplo |
|--------|-------------|---------|
| **Decidível** | Existe algoritmo que sempre para e responde SIM ou NÃO | "Este número é primo?" |
| **Semidecidível** (recursivamente enumerável) | Existe algoritmo que responde SIM quando a resposta é SIM, mas pode rodar para sempre quando é NÃO | "Este programa para com esta entrada?" |
| **Indecidível** | Nenhum algoritmo resolve para todas as entradas | Problema da Parada; "este programa faz o que a especificação pede?" |

> 📌 **Teorema de Rice (1953):** qualquer propriedade **não trivial** sobre o *comportamento* de programas é indecidível. É por isso que não existe um analisador perfeito que garanta, para qualquer programa, que ele "nunca trava", "nunca tem bugs" ou "nunca é malicioso".

---

## 🧮 Máquinas de Registradores e Máquina de Turing

### 🔹 Máquinas de Registradores

São modelos teóricos que operam sobre **inteiros armazenados em registradores numerados**. Um conjunto mínimo de instruções é suficiente:

- `INC r`: incrementa o registrador `r`;
- `DEC r`: decrementa `r` (se já for zero, permanece zero);
- `JZ r, k`: se `r` vale zero, salta para a instrução `k`;
- `HALT`: encerra.

**Exemplo:** somar `R1` em `R0` (usando `R2`, que sempre vale 0, para um salto incondicional):

```
0: JZ  R1, 4    ; se R1 == 0, vá para o fim
1: DEC R1
2: INC R0
3: JZ  R2, 0    ; R2 é sempre 0, então volta para o início
4: HALT
```

Apesar da simplicidade, elas têm o **mesmo poder computacional** da Máquina de Turing. Por lembrarem a linguagem de máquina (Assembly), são boas pontes entre a teoria e a arquitetura real dos computadores.

### 🔹 Máquina de Turing

Proposta por **Alan Turing em 1936** (artigo *On Computable Numbers, with an Application to the Entscheidungsproblem*), ela é formada por:

1. **Uma fita infinita** dividida em células, cada uma com um símbolo (a memória);
2. **Uma cabeça de leitura/escrita** que se move para a esquerda ou para a direita;
3. **Um conjunto finito de estados**;
4. **Uma tabela de transição**: dado o estado atual e o símbolo lido, define o símbolo a escrever, o movimento e o próximo estado.

**Exemplo didático: incrementar um número binário** (a cabeça começa no bit mais à esquerda; `_` é a célula vazia; `D` = direita, `E` = esquerda).

| Estado | Lê | Escreve | Move | Próximo estado |
|--------|----|---------|------|----------------|
| q0 | 0 | 0 | D | q0 |
| q0 | 1 | 1 | D | q0 |
| q0 | _ | _ | E | q1 |
| q1 | 1 | 0 | E | q1 |
| q1 | 0 | 1 | – | **parada** |
| q1 | _ | 1 | – | **parada** |

Execução com a fita `1011` (onze em decimal): a máquina vai até o fim (q0), volta (q1) trocando os `1` por `0` até achar um `0`, e escreve `1`. Resultado: `1100` (doze). ✅

### 🌐 Máquina de Turing Universal

Turing também mostrou que existe uma máquina capaz de **simular qualquer outra máquina de Turing**: basta receber na fita a descrição da máquina e sua entrada. É a **Máquina de Turing Universal**, ideia por trás do **computador de propósito geral** e do conceito de **programa armazenado em memória** (que von Neumann popularizaria em 1945).

### ⛔ O Problema da Parada (Halting Problem)

> *É possível criar um algoritmo que diga, para qualquer programa e qualquer entrada, se o programa vai parar ou entrar em loop infinito?*

Turing provou que **não**. A ideia da prova, em Java simplificado:

```java
// Suponha que exista um decisor perfeito:
boolean para(Programa p, Entrada x);   // true se p(x) termina

// Construímos um programa "contrário":
void paradoxo(Programa p) {
    if (para(p, p)) {
        while (true) { }   // se p(p) para, entra em loop infinito
    }
    // se p(p) não para, termina imediatamente
}

// O que acontece em paradoxo(paradoxo)?
//  - se para(paradoxo, paradoxo) == true  → ele entra em loop (não para)
//  - se para(paradoxo, paradoxo) == false → ele termina (para)
// Contradição! Logo, o decisor "para" não pode existir.
```

> 📝 **Nota histórica:** o nome "Problema da Parada" foi popularizado depois, por Martin Davis, nos anos 1950. O artigo original de Turing tratava de máquinas "circulares" e "não circulares".

---

## 🧠 Tese de Church-Turing

> *Tudo o que é efetivamente computável por um método mecânico pode ser computado por uma Máquina de Turing.*

Em outras palavras, a Máquina de Turing (e modelos equivalentes, como o λ-cálculo, as funções recursivas e as máquinas de registradores) representa o **limite do que pode ser calculado por algoritmos**.

- Não é um teorema, pois relaciona uma noção **intuitiva** ("método efetivo") a uma **formal**; por isso é chamada de **tese**.
- É amplamente aceita porque **todos os modelos propostos até hoje se mostraram equivalentes** ou mais fracos.
- A **computação quântica** não a contradiz: computadores quânticos podem ser mais **rápidos** para certos problemas, mas não resolvem nada que seja **indecidível**.

> ⚠️ **Atenção ao nome:** o correto é **Tese** de Church-Turing (e não "Teste").

---

## 🗣️ Linguagens Formais e Hierarquia de Chomsky

Nos anos 1950, **Noam Chomsky** classificou as gramáticas formais em níveis de poder crescente. Cada nível está associado a um tipo de máquina:

| Tipo | Linguagem | Máquina que a reconhece | Onde aparece |
|------|-----------|-------------------------|--------------|
| 3 | **Regular** | Autômato finito | Expressões regulares, análise léxica |
| 2 | **Livre de contexto** | Autômato com pilha | Sintaxe de linguagens de programação |
| 1 | **Sensível ao contexto** | Autômato linearmente limitado | Linguagem natural (modelos teóricos) |
| 0 | **Recursivamente enumerável** | Máquina de Turing | Qualquer computação efetiva |

Cada tipo **contém** o de número maior (toda linguagem regular é livre de contexto, e assim por diante). É essa hierarquia que explica, por exemplo, por que **uma expressão regular não consegue verificar se parênteses estão balanceados**, mas um autômato com pilha consegue.

---

## ⏱️ Teoria da Complexidade

Saber que um problema é **decidível** não basta: é preciso saber se dá para resolvê-lo **em tempo razoável**.

A partir de 1965 (Hartmanis e Stearns), a área passou a classificar problemas pelos recursos que exigem:

- **P:** problemas resolvidos em **tempo polinomial** (considerados "eficientes").
- **NP:** problemas cuja **solução pode ser verificada** em tempo polinomial.
- **NP-completos:** os mais difíceis de NP. Se algum deles tiver solução polinomial, **todos** os de NP terão.
- **Exponenciais:** o tempo cresce de forma explosiva com o tamanho da entrada.

| Problema | Classe conhecida |
|----------|------------------|
| Ordenar uma lista | P |
| Buscar o caminho mínimo em um grafo | P |
| SAT (satisfazibilidade booleana) | NP-completo (Cook-Levin, 1971–73) |
| Caixeiro viajante (versão de decisão) | NP-completo |
| Fatoração de inteiros | NP (não se sabe se é NP-completo) |

### ❓ P vs NP

**P = NP?** é a grande questão em aberto da área e um dos **sete Problemas do Milênio** do Instituto Clay (prêmio de US$ 1 milhão). A maioria dos pesquisadores acredita que **P ≠ NP**, mas ninguém provou. A resposta teria enorme impacto na otimização, na inteligência artificial e na **criptografia** moderna, que depende da dificuldade de certos problemas.

---

## 🔗 Teoria na Prática

| Conceito teórico | Onde aparece no dia a dia |
|------------------|---------------------------|
| Autômatos finitos e expressões regulares | Validação de formulários, `grep`, analisador léxico de compiladores |
| Gramáticas livres de contexto | Parsers de linguagens (Java, C, HTML, JSON) |
| Máquina de Turing universal | Computador de propósito geral, interpretadores e máquinas virtuais (JVM) |
| Indecidibilidade / Teorema de Rice | Limites de ferramentas de análise estática, verificação de software e antivírus |
| NP-completude | Escalonamento, roteamento, empacotamento, criptografia |

---

## 🧩 Conclusão

A **Teoria da Computação** é a base conceitual de toda a informática. Graças ao trabalho de **Hilbert, Gödel, Church, Turing, Post, Kleene, Chomsky, Cook, Karp** e muitos outros, sabemos que:

- existe uma **definição precisa** do que significa computar;
- há **limites absolutos** para o que algoritmos podem fazer (indecidibilidade);
- há **limites práticos** para o que algoritmos fazem em tempo útil (complexidade);
- a computação é tão poderosa quanto a lógica matemática, **e limitada por ela**.

Essas descobertas inspiram a engenharia de software e de hardware e alimentam debates filosóficos sobre **inteligência artificial** e a **natureza da mente**.

---

## 📝 Questões para Revisão

1. Qual era a pergunta do **Entscheidungsproblem** e quem a respondeu em 1936?
2. Por que a Tese de Church-Turing é chamada de *tese* e não de *teorema*?
3. Explique, com suas palavras, por que o **Problema da Parada** é indecidível.
4. Qual a diferença entre um problema **decidível** e um **semidecidível**?
5. Por que uma expressão regular não consegue validar parênteses balanceados?
6. O que significa dizer que um problema está em **NP**? E que é **NP-completo**?
7. Por que a computação quântica não invalida a Tese de Church-Turing?

---

## 📚 Referências Sugeridas

- SIPSER, Michael. **Introdução à Teoria da Computação**. Cengage Learning.
- HOPCROFT, John E.; MOTWANI, Rajeev; ULLMAN, Jeffrey D. **Introdução à Teoria de Autômatos, Linguagens e Computação**. Elsevier.
- LEWIS, Harry R.; PAPADIMITRIOU, Christos H. **Elementos de Teoria da Computação**. Bookman.
- DIVERIO, Tiarajú A.; MENEZES, Paulo B. **Teoria da Computação: máquinas universais e computabilidade**. Bookman.
- TURING, Alan M. *On Computable Numbers, with an Application to the Entscheidungsproblem*. Proceedings of the London Mathematical Society, 1936.
- CHURCH, Alonzo. *An Unsolvable Problem of Elementary Number Theory*. American Journal of Mathematics, 1936.
- PETZOLD, Charles. **The Annotated Turing**. Wiley.
