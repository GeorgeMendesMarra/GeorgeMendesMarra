**Atividades sobre a máquina Enigma**

O uso de um **Enigma Simulator** (seja o app para mobile ou simuladores web) é uma ferramenta pedagógica poderosa para unir **História, Matemática e Ciência da Computação**.

1. Desafio de Criptoanálise (Matemática/Computação)

O objetivo é entender a **Análise Combinatória** por trás da máquina.

Atividade

 Calcular o número total de combinações possíveis da Enigma considerando:

* A ordem dos 3 rotores (de um total de 5 disponíveis).
* As 26 posições iniciais de cada rotor.
* As conexões no painel de cabos (*plugboard*).

Faça um programa em Python e outro em Java para auxiliar nos cálculos.

2. Implementação de Algoritmos (Programação)

Atividade: Desenvolver um simulador simplificado em Python e/ou Java.

2.1) Executar o programa maquina_enigma1.py
2.2) Executar o programa maquina_enigma2.py
2.3) Executar o programa enigma.jar

3. Simulação Histórica (História/Interdisciplinar)

Focado no contexto da **Segunda Guerra Mundial** e no trabalho de **Alan Turing**.

* **Atividade (Roleplay):** Divida a classe em dois grupos: "Operadores Alemães" e "Codebreakers de Bletchley Park".
1. Os Alemães escolhem uma configuração secreta no app e entregam apenas a mensagem cifrada.
2. Os Codebreakers devem tentar achar padrões (como as famosas "cribs" — palavras comuns como "Heil Hitler" ou previsões do tempo) para reduzir as possibilidades e tentar descobrir a configuração.


* **Discussão:** Debater como a falha humana (repetição de chaves) foi o que permitiu a quebra do código, e não apenas a força bruta.

---

### Comparativo de Conceitos abordados

| Conceito | Aplicação no Simulador |
| --- | --- |
| **Cifra de Substituição** | Cada letra pressionada vira outra letra. |
| **Polialfabetismo** | O alfabeto de substituição muda a cada clique porque os rotores giram. |
| **Reciprocidade** | A mesma configuração que cifra é a que decifra (devido ao Refletor). |
| **Segurança da Informação** | Importância do segredo da chave vs. segredo do algoritmo. |

### ✍️ Atividade: Operação Bletchley Park

**Objetivo:** Decifrar uma mensagem interceptada utilizando a lógica da Enigma.

#### 1. A Configuração (A Chave do Dia)

Para que o simulador funcione, todos devem configurar a máquina exatamente da mesma forma. No seu **Enigma Simulator**, ajuste os seguintes parâmetros:

* **Modelo:** Enigma I (ou M3)
* **Rotores:** I - II - III (nesta ordem, da esquerda para a direita)
* **Posição Inicial (Ground Setting):** A - B - C
* **Anéis (Ring Settings):** 01 - 01 - 01 (ou A - A - A)
* **Plugboard (Painel de Cabos):** Conecte as letras **A-M**, **B-N** e **C-O**.

#### 2. O Desafio (A Mensagem Interceptada)

Digite o código abaixo no simulador após configurá-lo:

> **PXQZ GBDN**

#### 3. Tarefas para os Alunos:

1. **Decifração:** Qual é a mensagem oculta? (Dica: É uma saudação em inglês).
2. **Teste de Reciprocidade:** Sem resetar a máquina, apague o texto e digite o resultado que você encontrou. O que acontece na saída?
3. **Análise de Erro:** Mude apenas o primeiro rotor da posição **A** para a posição **B** e tente digitar a mensagem original novamente. O resultado ainda faz sentido? Por quê?

---

### 💡 Por que isso é uma atividade acadêmica?

Esta atividade demonstra três pilares da criptografia clássica:

* **Espaço de Chaves:** Mostra que um erro mínimo na configuração (mudar uma letra no rotor) torna a mensagem ilegível, exemplificando a sensibilidade do algoritmo.
* **Logística Militar:** Explica por que os alemães precisavam distribuir livros de códigos mensais com as "chaves do dia".
* **Matemática Discreta:** Introduz o conceito de **Permutação**, onde cada letra é mapeada para outra através de um caminho elétrico complexo.




Excelente! Aqui está um modelo de **Livro de Códigos (Codebook)** para uma semana inteira de atividades. Você pode imprimir e entregar aos alunos, ou projetar na sala.

Cada dia da semana possui uma configuração diferente. Isso forçará os alunos a "resetarem" a máquina e entenderem que o segredo não está apenas na máquina, mas na **combinação variável**.

---

### 🗓️ Livro de Códigos: Operação Simulação (Semana 01)

| Dia | Rotores (Ordem) | Posição Inicial | Plugboard (Cabos) |
| --- | --- | --- | --- |
| **Segunda** | I - II - III | D - E - F | A-B, C-D |
| **Terça** | II - I - III | G - H - I | X-Y, Z-W |
| **Quarta** | III - II - I | J - K - L | M-N, O-P |
| **Quinta** | I - III - II | M - N - O | Q-R, S-T |
| **Sexta** | II - III - I | P - Q - R | U-V, E-R |

---

### 🛠️ Dinâmica Sugerida para a Aula:

1. **A Estação de Transmissão:** Divida a turma em duplas. O aluno A é o "Transmissor" e o aluno B é o "Receptor".
2. **A Mensagem Secreta:** O Transmissor escolhe o dia da semana no Livro de Códigos, configura o app, escreve uma frase curta (ex: "ATACAR AS DEZ") e anota apenas o **resultado cifrado**.
3. **A Interceptação:** O Transmissor passa o papel com a mensagem cifrada e o dia da semana para o Receptor.
4. **A Decifração:** O Receptor configura sua máquina de acordo com o dia e deve obter a frase original.

### 🧠 Perguntas para o Relatório Acadêmico:

Para transformar a brincadeira em uma atividade avaliativa, peça que respondam:

* **P1:** O que acontece se você inverter a ordem dos rotores (ex: usar II-I-III em vez de I-II-III)? A mensagem ainda pode ser lida?
* **P2:** Por que o **Plugboard** (painel de cabos) aumentava tanto a segurança da máquina? (Dica: Pense na quantidade de combinações extras que 10 cabos criam).
* **P3:** Se um espião roubasse a máquina Enigma, mas não tivesse o **Livro de Códigos**, ele conseguiria ler as mensagens? Justifique.

**Gostaria que eu gerasse uma lista de frases cifradas baseadas nesse Livro de Códigos para você testar com eles agora mesmo?**
