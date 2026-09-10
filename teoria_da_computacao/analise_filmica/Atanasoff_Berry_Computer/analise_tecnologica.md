# Análise tecnológica — ABC de Atanasoff

## 1. Identificação tecnológica

**Personagem central:** John Vincent Atanasoff
**Projeto:** Atanasoff–Berry Computer — ABC
**Período:** década de 1930/início da década de 1940
**Área:** Computação eletrônica
**Tecnologias fundamentais:**

* sistema binário;
* eletrônica;
* válvulas a vácuo;
* memória por capacitores;
* processamento digital;
* circuitos lógicos;
* separação entre memória e processamento;
* automação de cálculos matemáticos.

O ABC tinha como objetivo resolver **sistemas de equações lineares**. Diferentemente de máquinas de propósito geral, era uma máquina de propósito específico e não era programável no sentido moderno. A fonte também destaca seu uso de capacitores para memória e a utilização de lógica binária. ([Acta Sapientia][1])

---

# 2. Por que o ABC é importante para Engenharia de Computação?

Para Engenharia de Computação, o ABC é extremamente importante porque representa uma mudança fundamental:

> **do cálculo mecânico para o processamento eletrônico de informação.**

Podemos representar a evolução:

```text
Calculadoras mecânicas
        ↓
Computação eletromecânica
        ↓
ABC
        ↓
Computadores eletrônicos
        ↓
Transistores
        ↓
Circuitos integrados
        ↓
Microprocessadores
        ↓
CPUs/GPU/NPU
        ↓
Computação moderna
```

O ABC introduziu ou consolidou conceitos fundamentais da computação eletrônica, incluindo **binário, lógica booleana, memória e processamento eletrônico**. ([Acta Sapientia][1])

---

# 3. O conceito de processamento eletrônico

Antes de máquinas como o ABC, grande parte dos cálculos automatizados dependia de mecanismos:

```text
Movimento mecânico
      ↓
Engrenagens
      ↓
Operação matemática
```

O ABC utiliza outro paradigma:

```text
Sinal elétrico
      ↓
Circuito eletrônico
      ↓
Representação binária
      ↓
Processamento
```

Essa mudança é gigantesca.

O processamento deixa de depender principalmente do movimento físico de engrenagens e passa a utilizar **fenômenos elétricos controlados por circuitos**.

---

# 4. Válvulas a vácuo

Para Engenharia de Computação, esse é um dos pontos que merece maior atenção.

O ABC utilizava **válvulas a vácuo**.

Podemos apresentar a evolução:

```text
Relé
 ↓
Válvula
 ↓
Transistor
 ↓
Circuito integrado
 ↓
Microprocessador
 ↓
SoC
```

A válvula permitia controlar sinais elétricos e implementar funções lógicas.

O princípio conceitual pode ser simplificado:

```text
Entrada
   ↓
Circuito eletrônico
   ↓
Decisão lógica
   ↓
Saída
```

Hoje fazemos isso utilizando bilhões de transistores.

---

# 5. Binário

O sistema binário é uma das principais contribuições tecnológicas associadas ao ABC.

Enquanto os seres humanos normalmente utilizam:

```text
0 1 2 3 4 5 6 7 8 9
```

o computador trabalha fundamentalmente com:

```text
0
1
```

A partir disso podemos construir:

```text
Bit
 ↓
Byte
 ↓
Palavra
 ↓
Registrador
 ↓
Memória
 ↓
Processador
```

Essa ideia continua absolutamente fundamental nos computadores atuais.

---

# 6. Lógica booleana

Aqui surge uma conexão direta com **Circuitos Digitais**.

A lógica booleana permite representar operações como:

```text
AND
OR
NOT
XOR
```

Por exemplo:

```text
A ───┐
     AND ─── Resultado
B ───┘
```

Tabela verdade:

| A | B | A AND B |
| - | - | ------- |
| 0 | 0 | 0       |
| 0 | 1 | 0       |
| 1 | 0 | 0       |
| 1 | 1 | 1       |

A grande evolução histórica é:

```text
Álgebra booleana
       ↓
Lógica
       ↓
Circuitos
       ↓
Processadores
       ↓
Computadores
```

Portanto, **Boole → Atanasoff → circuitos digitais → CPU moderna** é uma excelente linha conceitual para os alunos.

---

# 7. Memória

O ABC utilizava **capacitores como parte de sua tecnologia de memória**. ([Acta Sapientia][1])

Isso permite explicar uma questão essencial:

> **Processar dados não é suficiente. É necessário armazená-los.**

Temos então:

```text
ENTRADA
   ↓
MEMÓRIA
   ↓
PROCESSAMENTO
   ↓
MEMÓRIA
   ↓
SAÍDA
```

Compare isso com um computador atual:

```text
SSD/HDD
   ↓
RAM
   ↓
Cache
   ↓
Registradores
   ↓
CPU
```

A tecnologia mudou completamente, mas o problema fundamental permanece:

**armazenar informação de forma recuperável e processável.**

---

# 8. Separação entre memória e processamento

Esse é um dos conceitos mais importantes para Engenharia de Computação.

O ABC apresentava uma distinção entre:

**memória**

e

**processamento**.

Podemos simplificar:

```text
             COMPUTADOR
                  │
       ┌──────────┴──────────┐
       │                     │
    MEMÓRIA              PROCESSAMENTO
       │                     │
    Dados                 Operações
```

Hoje isso é absolutamente básico:

```text
RAM
 ↕
CPU
```

E dentro da CPU:

```text
Registradores
     ↓
Unidade de Controle
     ↓
ALU
     ↓
Cache
```

---

# 9. ABC × Z1

Como o senhor está trabalhando também com o vídeo sobre a **Z1**, vale muito a pena colocar os dois na mesma aula.

| Característica       | Z1                              | ABC                                    |
| -------------------- | ------------------------------- | -------------------------------------- |
| Principal tecnologia | Mecânica                        | Eletrônica                             |
| Representação        | Binária                         | Binária                                |
| Memória              | Mecânica                        | Capacitores                            |
| Processamento        | Mecânico                        | Eletrônico                             |
| Programável          | Sim, de forma limitada          | Não, propósito específico              |
| Objetivo             | Computação geral                | Equações lineares                      |
| Importância          | Computador programável pioneiro | Computação digital eletrônica pioneira |

Essa comparação é excelente porque mostra que **“computador” não é uma única invenção**.

Existem várias dimensões:

```text
Computador
├── mecânico
├── eletromecânico
├── eletrônico
├── digital
├── analógico
├── programável
└── propósito específico/geral
```

---

# 10. ABC × ENIAC

Outro ponto fundamental.

O ABC não deve ser simplesmente colocado como "o primeiro computador" sem qualificação.

O problema é que **“primeiro computador” depende do critério adotado**.

O ABC é extremamente importante na história da **computação digital eletrônica**, enquanto o ENIAC representa outro marco da computação eletrônica de grande escala.

A própria literatura histórica continua diferenciando máquinas segundo critérios como:

* eletrônico;
* digital;
* programável;
* propósito geral;
* funcional.

Por isso, para os alunos, eu recomendaria esta formulação:

> **O ABC foi um marco pioneiro da computação digital eletrônica, mas não deve ser confundido com um computador eletrônico moderno de propósito geral.**

---

# 11. Uma diferença fundamental: propósito específico

Esse ponto é muito importante para Engenharia de Software.

O ABC foi projetado para um problema específico:

> **resolver sistemas de equações lineares.**

Portanto:

```text
Problema
   ↓
Projeto do hardware
   ↓
Máquina especializada
```

Em um computador de propósito geral:

```text
Hardware
   ↓
Software
   ↓
Problema A

Software
   ↓
Problema B

Software
   ↓
Problema C
```

Essa diferença é uma das maiores revoluções da computação.

---

# 12. Engenharia de Software: onde está o software?

Aqui temos uma excelente discussão.

O ABC **não era um computador programável de propósito geral**.

Isso significa que o comportamento da máquina estava fortemente associado ao problema que ela deveria resolver.

Compare:

### ABC

```text
Hardware
   ↓
Problema matemático específico
```

### Computador moderno

```text
Hardware
   ↓
Sistema operacional
   ↓
Linguagem
   ↓
Software
   ↓
Aplicação
   ↓
Problema
```

Essa evolução ajuda os alunos a compreenderem **por que o software se tornou uma camada independente do hardware**.

---

# 13. Abstração

Essa é uma das principais lições para Engenharia de Software.

Hoje um programador pode escrever:

```java
double resultado = a + b;
```

Ele não precisa saber:

* qual transistor será utilizado;
* qual porta lógica executará a operação;
* qual tensão elétrica representará o bit;
* qual circuito físico armazenará o resultado.

Existe uma cadeia de abstrações:

```text
Aplicação
   ↓
Linguagem
   ↓
Compilador
   ↓
Assembly
   ↓
Código de máquina
   ↓
Arquitetura
   ↓
Circuitos
   ↓
Transistores
```

O ABC representa uma etapa muito anterior dessa evolução.

---

# 14. Engenharia de Software e o conceito de algoritmo

O ABC também permite uma discussão interessante:

> **Uma máquina pode executar uma solução sem possuir um programa tradicional?**

Sim.

Um algoritmo pode ser materializado diretamente em hardware.

```text
Algoritmo
   ↓
Circuito
   ↓
Máquina
```

Enquanto no modelo moderno:

```text
Algoritmo
   ↓
Programa
   ↓
Compilador
   ↓
CPU
```

Essa mudança é essencial para entender a diferença entre:

**hardware dedicado**

e

**software executável em hardware de propósito geral**.

---

# 15. ABC e computação científica

O ABC foi desenvolvido para resolver sistemas de equações lineares.

Isso o conecta diretamente à computação científica.

Exemplo:

```text
2x + 3y = 10
4x +  y = 12
```

Um computador pode executar um algoritmo para encontrar:

```text
x = ?
y = ?
```

Hoje esse tipo de problema aparece em:

* engenharia;
* física;
* estatística;
* economia;
* machine learning;
* inteligência artificial;
* computação gráfica.

---

# 16. Relação com Inteligência Artificial

Aqui existe uma conexão extremamente interessante.

Grande parte da IA moderna depende de **álgebra linear**.

Redes neurais utilizam:

* vetores;
* matrizes;
* multiplicação matricial;
* operações com números reais;
* funções matemáticas.

Por exemplo:

```text
X × W + B
     ↓
Função de ativação
     ↓
Resultado
```

O ABC foi criado para resolver problemas de natureza matemática envolvendo sistemas lineares.

Portanto, existe uma ligação histórica conceitual:

```text
Computação matemática
       ↓
Álgebra linear computacional
       ↓
Computação científica
       ↓
Processamento matricial
       ↓
Machine Learning
       ↓
Deep Learning
       ↓
IA
```

---

# 17. Big Data

Também podemos estabelecer uma comparação de escala.

### ABC

```text
Dados
 ↓
Processamento
 ↓
Resultado
```

### Computação atual

```text
Petabytes de dados
       ↓
Data Lakes
       ↓
Clusters
       ↓
CPU/GPU
       ↓
Machine Learning
       ↓
IA
```

O princípio continua sendo:

> **receber dados → armazenar → processar → produzir informação.**

O que mudou foi a **escala**.

---

# 18. Arquitetura de computadores

Para Engenharia de Computação, o ABC permite introduzir a arquitetura como uma abstração.

```text
              COMPUTADOR
                   │
       ┌───────────┴───────────┐
       │                       │
     MEMÓRIA              PROCESSAMENTO
       │                       │
       │                       │
     Dados                  Lógica
       │                       │
       └───────────┬───────────┘
                   │
                 SAÍDA
```

Depois podemos avançar para:

```text
CPU
├── ALU
├── Unidade de Controle
├── Registradores
└── Cache

Memória
├── Cache
├── RAM
├── SSD
└── Armazenamento
```

---

# 19. Confiabilidade

O vídeo também pode gerar uma excelente discussão sobre confiabilidade.

Uma máquina construída com milhares de componentes físicos precisa lidar com:

* falhas;
* desgaste;
* temperatura;
* interferência;
* alimentação elétrica;
* sincronização.

Hoje ainda temos o mesmo problema, mas em outra escala:

```text
Hardware
   +
Firmware
   +
Sistema operacional
   +
Software
   +
Rede
   =
Confiabilidade do sistema
```

Para Engenharia de Software, isso conduz diretamente a:

* testes;
* tolerância a falhas;
* tratamento de exceções;
* redundância;
* observabilidade;
* recuperação;
* alta disponibilidade.

---

# 20. Hardware dedicado × software

Esta talvez seja a discussão mais importante para os dois cursos.

### Hardware dedicado

```text
Problema
   ↓
Circuito específico
   ↓
Execução rápida
```

### Software

```text
Problema
   ↓
Algoritmo
   ↓
Código
   ↓
CPU
```

### Hardware especializado moderno

Hoje estamos voltando parcialmente ao modelo de hardware especializado:

```text
CPU
GPU
NPU
TPU
FPGA
ASIC
```

Especialmente para IA.

Portanto:

> **A história do ABC também ajuda a compreender por que estamos novamente utilizando hardware especializado para determinados tipos de computação.**

---

# 21. ABC e computação moderna

Podemos construir esta linha para os alunos:

```text
ATANASOFF
    │
    ▼
ABC
    │
    ▼
Computação digital eletrônica
    │
    ▼
ENIAC
    │
    ▼
Transistor
    │
    ▼
Circuito integrado
    │
    ▼
Microprocessador
    │
    ▼
PC
    │
    ▼
Internet
    │
    ▼
Cloud Computing
    │
    ▼
GPU
    │
    ▼
IA
```

Essa linha mostra que a IA contemporânea não surgiu "do nada".

Ela depende de uma enorme cadeia de avanços tecnológicos.

---

# 22. Uma comparação ainda mais profunda

Para os alunos de **Engenharia de Software**, eu apresentaria:

| ABC                            | Software moderno                 |
| ------------------------------ | -------------------------------- |
| Máquina especializada          | Computador de propósito geral    |
| Hardware determina função      | Software determina comportamento |
| Problema matemático específico | Aplicações diversas              |
| Circuitos                      | Algoritmos                       |
| Componentes físicos            | Código                           |
| Configuração física            | Configuração lógica              |
| Operação dedicada              | Reutilização de software         |

E para Engenharia de Computação:

| ABC                   | Computador atual        |
| --------------------- | ----------------------- |
| Válvulas              | Transistores            |
| Capacitores           | SRAM/DRAM               |
| Circuitos eletrônicos | CMOS/SoC                |
| Baixa escala          | Bilhões de transistores |
| Baixa velocidade      | GHz                     |
| Máquina dedicada      | CPU/GPU/NPU             |
| Grande volume físico  | Nanotecnologia          |

---

# 23. Questão central para debate

Eu colocaria no quadro:

> ### **O que é mais importante: inventar uma máquina que resolve um problema ou inventar uma máquina que pode ser programada para resolver vários problemas?**

Essa pergunta permite aos alunos perceberem a revolução provocada pelo conceito de **computador de propósito geral**.

---

# 24. Atividade para Engenharia de Software

### Desafio

**"Transforme o ABC em software."**

Os alunos devem criar um programa que resolva sistemas de equações lineares.

Por exemplo:

```text
2x + 3y = 10
4x +  y = 12
```

O programa deverá:

1. receber os coeficientes;
2. armazenar os dados;
3. executar o algoritmo;
4. apresentar o resultado;
5. tratar entradas inválidas;
6. testar casos conhecidos.

Depois devem explicar:

```text
ABC
 ↓
Problema matemático
 ↓
Algoritmo

Computador moderno
 ↓
Problema matemático
 ↓
Algoritmo
 ↓
Programa
```

---

# 25. Atividade para Engenharia de Computação

### Desafio

Projetar uma versão simplificada do ABC usando tecnologia atual.

Os alunos podem modelar:

```text
Entrada
   ↓
Registradores
   ↓
ALU
   ↓
Memória
   ↓
Controle
   ↓
Saída
```

E implementar conceitualmente ou em laboratório usando:

* Logisim;
* FPGA;
* Verilog;
* VHDL;
* Arduino;
* microcontrolador.

O objetivo não é reproduzir fisicamente o ABC, mas **reproduzir seus princípios computacionais**.

---

# 26. Atividade conjunta entre os cursos

Essa seria particularmente interessante:

## Projeto interdisciplinar: **"Do ABC à IA"**

### Engenharia de Computação

Desenvolve:

* arquitetura;
* ALU;
* memória;
* representação binária;
* circuito.

### Engenharia de Software

Desenvolve:

* algoritmo;
* software;
* interface;
* testes;
* documentação.

### Resultado

Um pequeno computador/simulador capaz de resolver sistemas matemáticos.

Depois:

**desafio final:**

> Adaptar o sistema para realizar operações matriciais utilizadas em Machine Learning.

Assim os alunos percebem a relação:

```text
ABC
 ↓
Álgebra linear
 ↓
Computação
 ↓
Processamento matricial
 ↓
Machine Learning
 ↓
Inteligência Artificial
```

---

# 27. Conclusão tecnológica

O grande valor do vídeo para **Engenharia de Software e Engenharia de Computação** está em mostrar que a história do computador não é apenas uma sucessão de máquinas.

É a história da evolução de uma ideia:

> **Como transformar informação e procedimentos matemáticos em operações que uma máquina consiga executar automaticamente?**

O ABC representa um momento decisivo dessa evolução porque reúne **computação digital, representação binária, lógica eletrônica e memória**, ainda que fosse uma máquina especializada e não um computador programável de propósito geral. ([Acta Sapientia][1])

Para **Engenharia de Computação**, ele permite estudar a origem da arquitetura eletrônica, da memória e dos circuitos digitais.

Para **Engenharia de Software**, permite compreender por que surgiram a abstração, os programas, as linguagens e a separação entre hardware e software.

E existe uma conexão especialmente interessante com a tecnologia atual:

> **O ABC foi criado para acelerar cálculos matemáticos; computadores atuais utilizam quantidades gigantescas de processamento matemático para executar inteligência artificial.**

Ou seja:

**Atanasoff → computação eletrônica → processadores → computação científica → processamento matricial → Machine Learning → IA.**

Essa é uma excelente ponte para mostrar aos alunos que **a Inteligência Artificial contemporânea é, em grande medida, o resultado de décadas de evolução simultânea de hardware, algoritmos, matemática e software.** ([Acta Sapientia][1])

[1]: https://actasapientia.com.br/index.php/acsa/issue/view/14?utm_source=chatgpt.com "v. 13 n. 1 (2026): Edição 2026 | Revista Eletrônica ACTA SAPIENTIA"
