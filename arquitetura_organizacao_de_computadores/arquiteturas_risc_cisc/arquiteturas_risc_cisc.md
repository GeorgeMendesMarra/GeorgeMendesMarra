# Material Didático: Arquiteturas RISC e CISC

## 1. Objetivos de Aprendizagem

Ao final desta unidade, o aluno será capaz de:

- Compreender os princípios fundamentais das arquiteturas CISC e RISC.
- Diferenciar os conjuntos de instruções, modos de endereçamento e filosofia de projeto.
- Analisar vantagens e desvantagens de cada abordagem.
- Identificar exemplos reais de processadores RISC e CISC.
- Relacionar a escolha da arquitetura com desempenho, consumo e complexidade de software.

---

## 2. Introdução à Arquitetura de Conjunto de Instruções (ISA)

A **ISA (Instruction Set Architecture)** é a interface entre o hardware e o software (compilador/sistema operacional). Duas filosofias dominaram o projeto de ISAs:

- **CISC (Complex Instruction Set Computer)** – décadas de 1970–80.
- **RISC (Reduced Instruction Set Computer)** – proposta a partir do início dos anos 1980.

A escolha entre RISC e CISC afeta diretamente:

- Desempenho
- Consumo energético
- Área do chip
- Complexidade do compilador
- Facilidade de programação em assembly

---

## 3. Arquitetura CISC (Complex Instruction Set Computer)

### 3.1. Filosofia

> Instruções complexas, poderosas e de alto nível, que realizam múltiplas operações (busca, cálculo, acesso à memória) em uma única instrução.

### 3.2. Características Principais

| Característica | Descrição |
|----------------|------------|
| Instruções de tamanho variável | 1 a 15 bytes (ex.: x86) |
| Muitos modos de endereçamento | Ex.: imediato, direto, indireto, indexado, base+deslocamento |
| Instruções que acessam memória diretamente | Ex.: `ADD memória, registrador` |
| Microprogramação | Instruções complexas decodificadas em micro-operações internas |
| Poucos registradores de uso geral | Geralmente 8–16 |

### 3.3. Exemplos de Instruções CISC (Intel x86)

```assembly
; Soma conteúdo da memória no registrador AX
ADD AX, [1234h]

; Move string da fonte para destino (REP MOVS)
REP MOVSB

; Multiplica e já armazena em dois registradores (resultado 32-bit)
MUL BX
```

### 3.4. Vantagens (na época de origem)

- **Densidade de código** – programas menores (memória cara).
- **Facilidade para programadores assembly** – instruções de alto nível.
- **Menos chamadas à memória** – muitas operações em uma instrução.

### 3.5. Desvantagens

- Decodificação complexa – consome área e energia.
- Pipelining difícil – instruções de tamanho e tempo variáveis.
- Compiladores aproveitavam mal as instruções complexas.
- Maior custo de controle (microcódigo).

### 3.6. Exemplos de Processadores CISC

- Intel 8086 → 80386 → Pentium → Core i7/i9 (na verdade, híbridos internamente)
- AMD x86-64
- VAX, Motorola 68000 (68k)

---

## 4. Arquitetura RISC (Reduced Instruction Set Computer)

### 4.1. Filosofia

> Conjunto pequeno e simples de instruções, cada instrução executa uma única operação básica, geralmente em um ciclo de clock.

### 4.2. Características Principais

| Característica | Descrição |
|----------------|------------|
| Instruções de tamanho fixo | Ex.: 32 bits (ARM, MIPS, RISC-V) |
| Poucos modos de endereçamento | Geralmente imediato, registrador, deslocamento (base+offset) |
| Instruções LOAD/STORE | Apenas LOAD e STORE acessam memória; ALU opera apenas entre registradores |
| Grande número de registradores | 32 ou mais |
| Pipeline eficiente | Estágios bem definidos (ex.: 5 estágios clássicos) |
| Decodificação simples e direta | Sem microcódigo (exceto RISC híbridos modernos) |

### 4.3. Exemplo clássico de pipeline RISC de 5 estágios

1. **IF** (Instruction Fetch)
2. **ID** (Instruction Decode / Read Registers)
3. **EX** (Execute ALU)
4. **MEM** (Memory access – só LOAD/STORE)
5. **WB** (Write Back)

### 4.4. Exemplo de instruções RISC (MIPS)

```assembly
; Soma conteúdo dos registradores r2 e r3, resultado em r1
ADD r1, r2, r3

; Carrega da memória (endereço base em r4 + deslocamento 8)
LW r1, 8(r4)

; Armazena r1 na memória
SW r1, 12(r4)
```

### 4.5. Vantagens

- Decodificação simples e rápida.
- Excelente para pipelining e execução fora de ordem.
- Menor área de chip e menor consumo (ideal para embarcados/móveis).
- Compiladores otimizam facilmente (operações simples e regulares).

### 4.6. Desvantagens

- Código menos denso (programas maiores).
- Mais instruções para tarefas complexas (ex.: copiar string).
- Maior dependência de compiladores inteligentes.

### 4.7. Exemplos de Processadores RISC

- ARM (Cortex-A, Cortex-M, etc.) – domina mobile e embarcados.
- MIPS – consoles antigos, roteadores.
- RISC-V – arquitetura livre e moderna.
- PowerPC (antigos Macs, consoles GameCube/Wii/PS3).
- SPARC (Sun/Oracle).

---

## 5. Tabela Comparativa: RISC x CISC

| Critério | CISC | RISC |
|----------|------|------|
| Número de instruções | Muitas (100–300+) | Poucas (50–100) |
| Tamanho da instrução | Variável (1–15 bytes) | Fixo (4 bytes típico) |
| Modos de endereçamento | Muitos (8–20) | Poucos (3–5) |
| Acesso à memória | Direto nas instruções ALU | Somente LOAD/STORE |
| Registradores | Poucos (8–16) | Muitos (32+) |
| Decodificação | Complexa (microcódigo) | Simples (hardware dedicado) |
| Pipeline | Difícil (hazards complexos) | Muito eficiente |
| Densidade de código | Alta (programa menor) | Baixa (programa maior) |
| Consumo de energia | Maior (por instrução) | Menor |
| Exemplos | x86, x86-64 | ARM, RISC-V, MIPS |

---

## 6. Evolução e Convergência

### 6.1. CISC moderno com núcleo RISC interno

Processadores x86 modernos (Intel Core, AMD Ryzen) **traduzem dinamicamente** instruções CISC em micro-operações **RISC-like** (μops) executadas em um pipeline RISC interno.

> Instrução CISC (ADD [mem], reg) → μops: LOAD, ALU, STORE

### 6.2. RISC com extensões complexas

ARM moderno (ARMv8, ARMv9) adicionou instruções para:

- Criptografia (AES, SHA)
- SIMD (NEON, SVE)
- Processamento de sinais

Isso aproxima o RISC do CISC em funcionalidade, mas mantendo formato fixo e LOAD/STORE.

### 6.3. Tendência atual

- **RISC-V** – cresce em servidores, embarcados, supercomputadores (ex.: Frontier usa RISC-V em aceleradores).
- **x86** mantém compatibilidade retrógrada (software legado).
- **Convergência** – ambos usam pipelines profundos, previsão de desvio, execução fora de ordem.

---

## 7. Impacto no Desenvolvimento de Software

| Aspecto | CISC (x86) | RISC (ARM/RISC-V) |
|---------|------------|--------------------|
| Programação assembly | Mais fácil para humanos | Mais prolixa, mas regular |
| Compiladores | Complexidade no casamento de instruções | Ótimo para otimizações |
| Portabilidade | Baixa (depende de compatibilidade binária) | Alta (arquiteturas modulares) |
| Virtualização | Suporte maduro (VT-x, AMD-V) | Suporte crescente (ARM virtualization extensions) |

---

## 8. Exemplos de Exercícios Práticos (Laboratório)

1. **Análise de código assembly** – Dado um trecho em x86 (CISC) e outro em ARM (RISC), calcular número de instruções e acessos à memória.

2. **Simulação** – Usar simulador MARS (MIPS) ou RARS (RISC-V) para comparar tempo/ciclos em um algoritmo de soma vetorial.

3. **Estudo de caso** – Por que o iPhone (ARM) tem maior eficiência energética que um notebook x86?

4. **Questão discursiva** – Se você projetasse um processador para um wearable médico (baixa potência, pouca memória), qual arquitetura escolheria e por quê?

---

## 9. Questões para Fixação

1. Explique por que o CISC surgiu antes do RISC, considerando a tecnologia de memória e compiladores da época.
2. O que significa **LOAD/STORE architecture**? Qual filosofia a adota?
3. Por que o pipeline de 5 estágios é mais eficiente em RISC do que em CISC puro?
4. Qual a vantagem de instruções de tamanho fixo?
5. O processador Intel Core i9 é puramente CISC? Justifique.
6. O que são **micro-operações (μops)** e qual seu papel nos processadores x86 modernos?
7. Dê um exemplo de tarefa que exige várias instruções RISC mas apenas uma instrução CISC.

---

## 10. Referências Recomendadas

- **Stallings, W.** – Arquitetura e Organização de Computadores (10ª ed.). Capítulos sobre ISA e RISC/CISC.
- **Patterson, D. A.; Hennessy, J. L.** – Computer Organization and Design RISC-V Edition (ou ARM/MIPS).
- **Tanenbaum, A. S.** – Organização Estruturada de Computadores.
- Artigos clássicos:
  - Patterson, D. A. (1985). “Reduced instruction set computers”. CACM.
  - Sites: RISC-V International, ARM Developer, Intel/AMD developer guides.

---
