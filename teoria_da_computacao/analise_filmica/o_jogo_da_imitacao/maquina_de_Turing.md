# A Máquina de Turing

A **Máquina de Turing** é um modelo matemático teórico, proposto por **Alan Turing em 1936** (no artigo *On Computable Numbers, with an Application to the Entscheidungsproblem*), que define com precisão **o que significa "calcular"** e, com isso, os **limites do que pode ser calculado**.

Ela **não é uma máquina física**, com engrenagens ou circuitos, mas um conceito abstrato que serve de fundação para a ciência da computação. O próprio Turing a chamava de "máquina automática" (*a-machine*); o nome "Máquina de Turing" foi popularizado depois, por Alonzo Church.

> **Ideia central:** tudo o que consideramos "computável" por um procedimento mecânico (um algoritmo) pode ser executado por uma Máquina de Turing. Isso é a **Tese de Church-Turing**, enunciada mais adiante.

---

## 1. Como ela funciona?

Imagine um sistema com quatro componentes:

1. **Fita infinita:** dividida em células, cada uma contendo um símbolo (como `0`, `1` ou vazio `_`). Funciona como a "memória" da máquina.
2. **Cabeça de leitura e gravação:** lê o símbolo da célula atual, escreve um novo símbolo e se move **uma célula** para a esquerda (`E`) ou para a direita (`D`).
3. **Registrador de estado:** guarda o **estado atual** da máquina (por exemplo, `q0`, `q1` ou `parar`).
4. **Tabela de transições (regras):** o "programa" da máquina. Cada regra diz: *dado o estado atual e o símbolo lido, qual símbolo escrever, para onde mover e qual será o próximo estado.*

Exemplo de regra: *"Se estiver no estado `q0` e ler `1`, escreva `0`, mova-se para a esquerda e permaneça em `q0`."*

### Definição formal (para quem quer rigor)

Uma Máquina de Turing é uma tupla **M = (Q, Σ, Γ, δ, q₀, F)**, em que:

| Símbolo | Significado |
|---------|-------------|
| **Q** | conjunto finito de estados |
| **Σ** | alfabeto de entrada |
| **Γ** | alfabeto da fita (inclui Σ e o símbolo vazio) |
| **δ** | função de transição: (estado, símbolo) → (novo estado, símbolo escrito, movimento) |
| **q₀** | estado inicial |
| **F** | conjunto de estados de parada (aceitação) |

---

## 2. Exemplo prático: incrementar um número binário

Vamos construir uma máquina que **soma 1** a um número binário. A cabeça começa sobre o **bit mais à direita**.

**Regras** (notação: *estado, símbolo lido* → *símbolo escrito, movimento, novo estado*):

| Estado | Lê | Escreve | Move | Novo estado |
|--------|----|---------|------|-------------|
| `q0` | `1` | `0` | `E` | `q0` |
| `q0` | `0` | `1` | — | `parar` |
| `q0` | `_` (vazio) | `1` | — | `parar` |

**Lógica:** é o "vai um" da soma. Enquanto encontra `1`, troca por `0` e continua para a esquerda; ao encontrar `0` (ou o vazio), troca por `1` e termina.

**Execução com a fita `1011` (11 em decimal):**

| Passo | Fita (cabeça em **negrito**) | Estado | Ação |
|-------|------------------------------|--------|------|
| 0 | 101**1** | `q0` | lê `1`: escreve `0`, vai à esquerda |
| 1 | 10**1**0 | `q0` | lê `1`: escreve `0`, vai à esquerda |
| 2 | 1**0**00 | `q0` | lê `0`: escreve `1`, **para** |
| 3 | 1100 | `parar` | resultado: **1100 = 12** |

Com a fita `111` (7), a máquina produz `1000` (8): ao passar pelo último `1`, a cabeça encontra o vazio e escreve o `1` do "vai um" final.

> Em sala, vale pedir aos alunos que montem a tabela de uma máquina que **inverte todos os bits** de uma fita (troca `0` por `1` e vice-versa).

---

## 3. Por que ela é tão importante?

### Universalidade

Turing mostrou que existe uma **Máquina de Turing Universal**: uma única máquina capaz de **simular qualquer outra**, recebendo na própria fita a descrição da máquina a simular e os dados de entrada. É exatamente o que um computador moderno faz: **um único hardware executa qualquer programa** que lhe seja fornecido. A ideia antecipa o conceito de **programa armazenado** na memória.

### O Problema da Parada (*Halting Problem*)

Turing usou o modelo para provar que existem problemas que **nenhum algoritmo é capaz de resolver**. O mais famoso: dado um programa qualquer e uma entrada, **decidir se ele vai parar ou rodar para sempre**.

*Esboço da prova (por contradição):*

1. Suponha que exista um programa `H(P, x)` que sempre responde corretamente se `P` para com a entrada `x`.
2. Construa um programa `D(P)` que chama `H(P, P)`: se `H` responder "para", `D` entra em loop infinito; se responder "não para", `D` termina.
3. Agora execute `D(D)`. Se `D(D)` parar, `H` disse que não pararia; se `D(D)` rodar para sempre, `H` disse que pararia. **Contradição**, logo `H` não pode existir.

Esse resultado respondeu negativamente ao **Entscheidungsproblem** (o "problema da decisão"), proposto por David Hilbert: não existe um método geral que decida, para qualquer enunciado lógico, se ele é verdadeiro.

### Tese de Church-Turing

Estabelece que tudo o que é **efetivamente computável** (por um procedimento mecânico) pode ser computado por uma Máquina de Turing. Foi formulada de forma independente por **Alonzo Church**, com o **cálculo lambda**, e por Turing, também em 1936. É uma **tese**, não um teorema: não pode ser provada, pois depende da noção informal de "procedimento mecânico". Mesmo assim, todos os modelos de computação propostos até hoje se mostraram equivalentes em poder.

### Complexidade computacional

As classes de complexidade, como **P** e **NP**, são definidas com base em Máquinas de Turing, o que faz do modelo a base para classificar a "dificuldade" dos problemas. Da mesma forma, os modelos de IA atuais, por rodarem em computadores, estão sujeitos aos **mesmos limites de computabilidade**.

---

## 4. Conexão com o filme *O Jogo da Imitação*

É comum confundir três coisas diferentes:

| Conceito | O que é | Exemplo no filme |
|----------|---------|------------------|
| **Máquina de Turing** | Modelo **teórico** e universal de computação (1936) | Não aparece de forma explícita |
| **Bombe** | Máquina **física**, eletromecânica, de propósito específico (quebrar a Enigma) | A máquina "Christopher" |
| **Teste de Turing** | Experimento mental sobre inteligência de máquinas (1950) | O "jogo da imitação" que dá título ao filme |

A Bombe **não é** uma Máquina de Turing universal: ela só fazia uma tarefa. O que une os dois mundos é a visão de Turing de que **raciocínio mecânico** pode ser formalizado e executado por máquinas.

---

## Para refletir

1. Por que a fita da Máquina de Turing precisa ser **infinita**? Os computadores reais também têm essa propriedade?
2. Se o Problema da Parada é **indecidível**, o que isso significa na prática para ferramentas que tentam detectar *loops* infinitos em código?
3. Qual é a diferença entre a Bombe, o Colossus e uma Máquina de Turing universal?
