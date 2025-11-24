---

# 🕰 **Histórico do Projeto de Sistemas de Tempo Real (RTR – Real-Time Systems Design)**

Os sistemas de tempo real surgiram muito antes do termo ser formalizado. Seu desenvolvimento está intimamente ligado à evolução dos sistemas embarcados, automação industrial, controle aeronáutico e telecomunicações.

---

# 📜 **Linha do Tempo Histórica do Projeto de Tempo Real**

### **1940–1950 — Computação de Controle**

* Primeiros computadores eletromecânicos usados para controle de radares e armas antiaéreas.
* O foco era a **resposta determinística**.

### **1960 — Sistemas Embarcados**

* Surge o termo *real-time* em publicações técnicas.
* Aplicações:

  * Controle de navegação
  * Telecomunicações
  * Early process control
* Primeiros RTOS rudimentares.

### **1970 — Teoria Formal e Estruturas**

* A engenharia de software começa a formalizar métodos para sistemas RT.
* Introdução dos conceitos:

  * **Hard real-time vs soft real-time**
  * **Deadlines e jitter**
  * **Tarefas periódicas e aperiódicas**
* Nasce o **Rate Monotonic Scheduling (Liu & Layland, 1973)**.

### **1980 — Metodologias Estruturadas de Tempo Real**

* Surgem as duas metodologias clássicas:

  * **Ward & Mellor (1985)**
  * **Hatley & Pirbhai (1988)**
* Ambas ampliam a Análise Estruturada (SA/SD) para lidar com:

  * eventos assíncronos
  * processamento periódico
  * concorrência
  * reatividade

### **1990 — Orientação a Objetos e UML**

* Publicação da **UML (1997)** e posteriormente **UML-RT**, baseada em ROOM.
* Introdução de ferramentas de modelagem executável.

### **2000 — Sistemas Críticos & Model-Driven Engineering**

* Expansão da abordagem model-driven:

  * SysML
  * MDD/MDA
* Cresce o uso comercial de UML-RT.

### **2010–atual — IoT, Ciberfísicos e Automação Avançada**

* Projetos de tempo real agora envolvem:

  * drones
  * veículos autônomos
  * robótica avançada
  * sistemas embarcados distribuídos
* Abordagens modernas:

  * **Statecharts executáveis**
  * **Simulink/Stateflow**
  * **ROS, RTOS modernos**
  * **Real-Time Java** e **C++17/20 embedded**

---

# 🧠 **Conceitos Centrais no Projeto de Sistemas de Tempo Real**

* **Determinismo**
* **Latência garantida**
* **Concorrência controlada**
* **Comunicação baseada em eventos**
* **Uso de RTOS**
* **Escalonamento preemptivo**
* **Sincronização segura (mutex, semáforos, filas)**

---

# 📊 **Comparação entre Metodologias Clássicas de Tempo Real**

## **Tabela 1 — Ward & Mellor vs Hatley & Pirbhai**

| Critério           | Ward & Mellor                           | Hatley & Pirbhai                         |
| ------------------ | --------------------------------------- | ---------------------------------------- |
| Base               | Análise Estruturada                     | Estruturada + Controle                   |
| Foco               | Sistemas dirigidos por eventos          | Sistemas com processamento contínuo      |
| Diagrama principal | DFD + Dicionário                        | DFD + Fluxos de Controle                 |
| Estende para RT?   | Sim: eventos, tempo, estímulos          | Sim: modos, controles, estímulos         |
| Melhor uso         | Sistemas embarcados discretos           | Telecom, controle, sistemas híbridos     |
| Ferramentas        | Excelentes para modelar dados e eventos | Mais completo para modelar comportamento |

---

## **Tabela 2 — Métodos Modernos (UML-RT, SysML)**

| Critério               | UML-RT                       | SysML                  | Ward/Mellor / Hatley  |
| ---------------------- | ---------------------------- | ---------------------- | --------------------- |
| Paradigma              | Orientado a objetos          | Engenharia de sistemas | Estruturado           |
| Suporte a concorrência | Forte                        | Médio                  | Fraco                 |
| Modelos executáveis    | Sim                          | Parcial                | Não                   |
| Aplicação              | Telecom, embarcados modernos | Sistemas ciberfísicos  | Sistemas RT clássicos |

---

## **Tabela 3 — Comparação com OO, Estruturado e Reativo**

| Abordagem           | Tempo Real? | Concorrência? | Base                    |
| ------------------- | ----------- | ------------- | ----------------------- |
| Análise Estruturada | Fraco       | Baixo         | DFD                     |
| Ward & Mellor       | Bom         | Médio         | DFD estendido           |
| Hatley & Pirbhai    | Muito bom   | Forte         | DFD + Controle          |
| OO tradicional      | Médio       | Médio         | Classes, objetos        |
| UML-RT (ROOM)       | Excelente   | Excelente     | Ators, cápsulas, portas |
| Métodos Reativos    | Excelente   | Excelente     | Eventos + estados       |

---

# 🗺 **DFD para Sistemas de Tempo Real**

### 📌 **Exemplo: Controle de Temperatura em Forno Industrial**

---

## **DFD Nível 0 (Contexto)**

```
[Sensor Temperatura] → (Sistema de Controle RT) → [Aquecedor]
                           ↑
                       [Interface Operador]
```

---

## **DFD Nível 1**

```
                +———————————————+
Sensor →———→   |  Leitura de Sensores   |
                +———————————————+
                         ↓ temperatura
                +———————————————+
                | Controle PID RT       |
                +———————————————+
                         ↓ comando
                +———————————————+
Operador →——→   | Supervisão e Alarmes  |
                +———————————————+ → Alarmes/Ações
```

---

## **DFD Nível 2 — Controle PID**

```
Entrada → Filtragem → Cálculo PID → Sinal de Controle → Atuação
```

---

# 🎛 **Exemplos de Modelagem em Tempo Real**

---

## **Statechart Simplificado**

Para o forno industrial:

```
[Idle]
   |
   | start
   v
[Heating]
   |
   | temp > limite
   v
[Alarm]
   |
   | reset
   v
[Idle]
```

---

## **Diagrama de Sequência — Ciclo de Controle**

```
Sensor → Controlador → Atuador → Forno
   ↑                                ↓
   ←--------- feedback --------------
```

---

## **Escalonamento em RTOS (Exemplo)**

### Tarefas:

| Tarefa          | Tipo       | Período | Deadline |
| --------------- | ---------- | ------- | -------- |
| Leitura Sensor  | Periódica  | 10 ms   | 10 ms    |
| Controlador PID | Periódica  | 20 ms   | 20 ms    |
| Supervisão      | Aperiódica | —       | 200 ms   |

### Exemplo simples com Rate Monotonic

Prioridade (maior → menor):

1. Sensor
2. PID
3. Supervisão

---
