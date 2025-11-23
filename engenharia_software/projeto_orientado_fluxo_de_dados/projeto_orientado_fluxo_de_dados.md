# 📘 **Histórico do Projeto Orientado ao Fluxo de Dados (DFD)**

## **1. Origens (década de 1960–1970)**

O Projeto Orientado ao Fluxo de Dados nasceu dentro da evolução da **Análise Estruturada**, cujo objetivo era sistematizar o desenvolvimento de software de forma disciplinada e previsível. Os primeiros passos vieram de:

* **John DeMarco (1968)** — pioneiro na ideia de decompor sistemas por meio de funções e fluxos de informação.
* **Larry Constantine e Ed Yourdon (1975–1978)** — consolidaram notações, regras e práticas de modelagem funcional que deram origem aos **Diagramas de Fluxo de Dados (DFD)**, elementos centrais do projeto orientado ao fluxo de dados.

Nesse período, a indústria buscava **reduzir a complexidade dos sistemas empresariais** e controlar o aumento exponencial da demanda por software. A ideia era modelar **o que o sistema faz**, antes de discutir como fazê-lo, por isso o foco era “função” e “processamento de dados”.

---

## **2. Consolidação (anos 1980)**

Durante os anos 1980, o modelo atingiu o auge:

* Tornou-se padrão na Engenharia de Software clássica.
* Foi adotado em metodologias estruturadas como:

  * Yourdon/DeMarco
  * Gane & Sarson
  * Ward & Mellor (extensão para sistemas de tempo real)
  * Hatley & Pirbhai (extensão para controle, eventos e arquiteturas reativas)

O paradigma ganhou força devido à **simplicidade, visualização clara das transformações de dados e facilidade de decomposição hierárquica**.

---

## **3. Extensões importantes do paradigma**

### **📌 3.1 Ward & Mellor — Tempo Real (1985–1986)**

Complementaram os DFD tradicionais com elementos novos para modelar:

* **Fluxos de eventos e controle**
* **Processos periódicos, assíncronos e críticos**
* **Estados acoplados ao fluxo de dados**

Foram fundamentais para sistemas industriais, automotivos e embarcados.

---

### **📌 3.2 Hatley & Pirbhai — Sistemas Reativos (1987)**

Criaram o **Modelo de Controle e Fluxo de Dados (D/FD + C/FD)**:

* Separação explícita entre **fluxos de dados** e **fluxos de controle**
* Introdução de:

  * Tabelas de controle
  * Diagramas de contexto ampliado
  * Modelagem de eventos externos

A abordagem serviu como ponte entre **DFDs** e sistemas orientados a eventos, influenciando futuramente a UML (máquinas de estado e diagramas de interação).

---

## **4. Declínio e transição para OO (anos 1990 em diante)**

Com o crescimento da **Orientação a Objetos**, dos modelos UML e das arquiteturas orientadas a componentes, o paradigma começou a perder espaço. No entanto, os DFDs continuaram sendo usados em:

* Análise de sistemas legados
* Processos de negócio (BPMN herda traços do DFD)
* Engenharia reversa
* Software embarcado e aplicações industriais

O paradigma permanece extremamente útil pedagogicamente para **explicar transformação de dados** e para projetos com forte processamento funcional.

---

# 📊 **Tabelas Comparativas**

---

## **1. Projeto Orientado ao Fluxo de Dados vs Orientação a Objetos**

| Critério       | Orientado ao Fluxo de Dados (DFD)             | Orientação a Objetos (OO)                   |
| -------------- | --------------------------------------------- | ------------------------------------------- |
| Foco principal | Funções e transformações de dados             | Entidades (objetos) e comportamento         |
| Modelagem      | DFDs hierárquicos                             | Diagramas UML (classes, sequência, estados) |
| Dados          | Passam por processos                          | Encapsulados nos objetos                    |
| Reuso          | Baixo                                         | Alto                                        |
| Indicado para  | Sistemas funcionais, processamento batch, ETL | Sistemas complexos, OO, interativos         |
| Vantagem       | Simplicidade visual e decomposição funcional  | Flexibilidade, modularidade e reuso         |

---

## **2. DFD vs Análise Estruturada**

| Critério  | DFD                                              | Análise Estruturada                                      |
| --------- | ------------------------------------------------ | -------------------------------------------------------- |
| Origem    | Parte central da análise estruturada             | Paradigma completo (DFD, dicionário, ECS, etc.)          |
| Modelagem | Fluxo de dados                                   | Estrutura, funções e comportamento                       |
| Notações  | Processos, fluxos, entidades externas, depósitos | DFD + especificações funcionais + diagramas de estrutura |
| Uso       | Modelar dados e processos                        | Descrever o sistema inteiro                              |

---

## **3. DFD vs Métodos Reativos (Ward/Mellor e Hatley/Pirbhai)**

| Critério      | DFD Tradicional          | Ward & Mellor                            | Hatley & Pirbhai                        |
| ------------- | ------------------------ | ---------------------------------------- | --------------------------------------- |
| Objetivo      | Modelar fluxo de dados   | Tempo real                               | Sistemas reativos                       |
| Eventos       | Implícitos               | Inclusão de eventos                      | Modelagem formal de eventos             |
| Controle      | Não explícito            | Controle acoplado                        | Controle separado em diagramas próprios |
| Indicado para | Sistemas administrativos | Sensores, atuadores, sistemas embarcados | Sistemas dirigidos a eventos            |

---

# 🧩 **Exemplos Práticos — DFD Nível 0, 1 e 2**

---

# **📍 Sistema Exemplo: “Processamento de Pedidos”**

---

## **📘 DFD — Nível 0 (Diagrama de Contexto)**

Representa o sistema como um único processo.

```
[Cliente] → (Sistema de Pedidos) → [Empresa]
```

Processo único: **0 – Sistema de Pedidos**

---

## **📘 DFD — Nível 1**

Divide o sistema em subprocessos principais.

```
        Cliente
           |
           v
   +-----------------+
   | 1. Receber Pedido |
   +-----------------+
           |
           v
   +-----------------+
   | 2. Validar Estoque |
   +-----------------+
           |
           v
   +-----------------+
   | 3. Gerar Fatura |
   +-----------------+
           |
           v
        Empresa
```

---

## **📘 DFD — Nível 2 (Explodindo o processo 2 — Validar Estoque)**

```
                  |
                  v
        +---------------------+
        | 2.1 Consultar Banco de Dados |
        +---------------------+
                  |
                  v
        +---------------------+
        | 2.2 Verificar Quantidade |
        +---------------------+
                  |
        Decisão: quantidade ≥ solicitada?
        /                \
      Sim                Não
      |                   |
      v                   v
+--------------+   +-------------------+
| 2.3 Aprovar  |   | 2.4 Notificar Falta |
|   Pedido     |   +-------------------+
+--------------+
```

---

# 📌 **Resumo Geral**

Você agora tem:

✔ Histórico completo
✔ Evolução por décadas
✔ Extensões clássicas
✔ Comparações estruturadas
✔ Tabelas com diferenças entre paradigmas
✔ Exemplos completos de DFD nível 0, 1 e 2

---
