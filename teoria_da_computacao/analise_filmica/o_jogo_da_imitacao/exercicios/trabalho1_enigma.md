# Atividades Educacionais sobre a Máquina Enigma

O uso de um **simulador da máquina Enigma** (aplicativo mobile ou simulador web) constitui uma ferramenta pedagógica eficiente para integrar conhecimentos de **História, Matemática e Ciência da Computação**.

A partir dessa abordagem interdisciplinar, os estudantes podem compreender conceitos fundamentais de **criptografia clássica**, **análise combinatória**, **algoritmos** e **segurança da informação**, além de conhecer o contexto histórico da criptografia durante a Segunda Guerra Mundial.

---

# 1. Desafio de Criptoanálise

### (Matemática e Ciência da Computação)

O objetivo desta atividade é compreender a **complexidade combinatória** da máquina Enigma e como seu grande número de configurações contribuía para a segurança das mensagens militares.

## Atividade

Calcular o **número total de combinações possíveis** da máquina Enigma considerando:

* A **ordem de 3 rotores** escolhidos entre **5 disponíveis**.
* As **26 posições iniciais** possíveis para cada rotor.
* As **conexões do Plugboard** (painel de cabos).

Esses cálculos envolvem conceitos de:

* **Permutação**
* **Combinação**
* **Análise combinatória**

### Exercício prático

Desenvolver programas para auxiliar nesses cálculos em:

* **Python**
* **Java**

---

# 2. Implementação de Algoritmos

### (Programação)

Nesta etapa, os alunos devem explorar o funcionamento prático da criptografia implementando ou executando simuladores da máquina Enigma.

## Atividade

Executar os seguintes programas:

1. **maquina_enigma1.py**
2. **maquina_enigma2.py**
3. **enigma.jar**

Objetivos da atividade:

* Compreender a lógica de **substituição de caracteres**
* Observar o **movimento dos rotores**
* Entender como ocorre a **cifragem e decifragem da mensagem**

---

# 3. Simulação Histórica

### (História e Computação)

Esta atividade é baseada no contexto da **Segunda Guerra Mundial** e no trabalho de criptoanálise realizado em **Bletchley Park**, liderado por Alan Turing.

## Atividade (Roleplay)

Dividir a turma em dois grupos:

### Grupo 1 — Operadores Alemães

* Escolhem uma configuração secreta no simulador.
* Criam uma mensagem cifrada.
* Entregam apenas o **texto criptografado** aos adversários.

### Grupo 2 — Codebreakers de Bletchley Park

* Recebem a mensagem cifrada.

* Tentam identificar padrões e possíveis palavras conhecidas (**cribs**), como:

* "Heil Hitler"

* previsões meteorológicas

* saudações militares

O objetivo é **reduzir o espaço de busca** e tentar descobrir a configuração da máquina.

### Discussão

Debater com os alunos que a quebra da Enigma não ocorreu apenas por **força bruta computacional**, mas também devido a:

* **erros humanos**
* **repetição de padrões**
* **uso previsível da linguagem**

---

# Conceitos Criptográficos Abordados

| Conceito                    | Aplicação na Enigma                                         |
| --------------------------- | ----------------------------------------------------------- |
| **Cifra de Substituição**   | Cada tecla pressionada gera outra letra.                    |
| **Polialfabetismo**         | O alfabeto de substituição muda a cada rotação dos rotores. |
| **Reciprocidade**           | A mesma configuração cifra e decifra a mensagem.            |
| **Segurança da Informação** | A segurança depende do segredo da chave e não do algoritmo. |

---

# Atividade Prática

## Operação Bletchley Park

### Objetivo

Decifrar uma mensagem interceptada utilizando a lógica da máquina Enigma.

---

## 1. Configuração da Máquina (Chave do Dia)

No simulador da Enigma, configurar:

* **Modelo:** Enigma I (ou M3)
* **Rotores:** I – II – III (da esquerda para a direita)
* **Posição inicial:** A – B – C
* **Ring Settings:** 01 – 01 – 01 (A – A – A)
* **Plugboard:**

  * A ↔ M
  * B ↔ N
  * C ↔ O

---

## 2. Mensagem Interceptada

Digite no simulador:

```
PXQZ GBDN
```

---

## 3. Tarefas para os alunos

### 1. Decifração

Descobrir qual é a mensagem original.

Dica: trata-se de **uma saudação em inglês**.

### 2. Teste de Reciprocidade

Sem reiniciar a máquina:

* apague o texto
* digite novamente a mensagem decifrada

Observe o resultado.

### 3. Análise de erro

Altere apenas a posição do primeiro rotor:

A → B

Digite novamente a mensagem original.

Pergunta:

> O resultado ainda faz sentido?
> Por que pequenas mudanças na chave tornam a mensagem ilegível?

---

# Por que esta é uma atividade acadêmica?

Esta atividade demonstra três pilares da criptografia:

### 1. Espaço de chaves

Uma pequena mudança na configuração gera resultados completamente diferentes.

Isso demonstra o conceito de **sensibilidade à chave**.

---

### 2. Logística militar

Os alemães precisavam distribuir **livros de códigos mensais** contendo as **chaves do dia**.

---

### 3. Matemática discreta

Cada transformação da máquina pode ser vista como uma **permutação de letras**, conceito fundamental da criptografia.

---

# Livro de Códigos — Operação Simulação (Semana 01)

Cada dia possui uma configuração diferente da máquina.

| Dia     | Rotores      | Posição Inicial | Plugboard |
| ------- | ------------ | --------------- | --------- |
| Segunda | I – II – III | D – E – F       | A-B, C-D  |
| Terça   | II – I – III | G – H – I       | X-Y, Z-W  |
| Quarta  | III – II – I | J – K – L       | M-N, O-P  |
| Quinta  | I – III – II | M – N – O       | Q-R, S-T  |
| Sexta   | II – III – I | P – Q – R       | U-V, E-R  |

---

# Dinâmica de Aula

### 1. Estação de transmissão

Dividir os alunos em **duplas**:

* Aluno A: transmissor
* Aluno B: receptor

---

### 2. Mensagem secreta

O transmissor:

1. Escolhe um dia no livro de códigos
2. Configura o simulador
3. Escreve uma frase curta

Exemplo:

```
ATACAR AS DEZ
```

4. Anota apenas a **mensagem cifrada**

---

### 3. Interceptação

O transmissor entrega ao colega:

* o **texto cifrado**
* o **dia da semana**

---

### 4. Decifração

O receptor configura sua máquina de acordo com o livro de códigos e tenta recuperar a mensagem original.

---

# Perguntas para Relatório

**P1.** O que acontece se a ordem dos rotores for alterada?
Exemplo: II-I-III em vez de I-II-III.

---

**P2.** Por que o Plugboard aumentava significativamente a segurança da máquina?

---

**P3.** Se um espião roubasse uma máquina Enigma, mas não tivesse o livro de códigos, ele conseguiria ler as mensagens? Justifique.

---

# O Papel do Refletor na Enigma

O **refletor** (*Umkehrwalze*) era um disco fixo que redirecionava o sinal elétrico de volta pelos rotores.

---

## Vantagem

Graças ao refletor, a máquina era **recíproca**:

Se

```
A → X
```

então

```
X → A
```

Isso permitia que a mesma máquina fosse usada para **cifrar e decifrar mensagens**.

---

## A falha explorada por Turing

Devido ao funcionamento do refletor, existia uma regra fundamental:

> **Uma letra nunca poderia ser cifrada como ela mesma.**

Se o operador digitasse:

```
T
```

a lâmpada **T nunca acenderia**.

Essa propriedade foi explorada por Alan Turing no desenvolvimento da máquina **Bombe**.

---

## A lógica usada na criptoanálise

Se os analistas suspeitavam que a mensagem continha uma palavra conhecida (*crib*), podiam testar alinhamentos.

Se em algum ponto:

```
T → T
```

isso significava que aquela configuração era **impossível**, permitindo eliminar rapidamente milhares de combinações.

---

# Reflexão Final para os Alunos

Durante o uso do simulador, observe:

> Alguma vez uma letra digitada resultou na mesma letra?

Se não, pergunte-se:

**Como essa propriedade poderia ser usada para eliminar configurações incorretas durante a quebra de um código?**

---

✅ Esse tipo de atividade permite compreender como **matemática, lógica e computação** contribuíram para um dos episódios mais importantes da história da criptografia durante a **World War II**.

---
