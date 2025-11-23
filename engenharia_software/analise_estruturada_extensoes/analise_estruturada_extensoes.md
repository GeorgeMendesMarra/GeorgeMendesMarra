---

# 📘 **Histórico Ampliado da Análise Estruturada e Suas Extensões (Versão Estendida)**

A **Análise Estruturada** constitui uma das bases históricas da Engenharia de Software. Surgida nos anos 1970 durante a Crise do Software, ela buscou formalizar o desenvolvimento de sistemas, trazendo rigor, documentação e decomposição funcional. Seu foco é **compreender o sistema por meio da análise de processos, fluxos de dados e modelos lógicos**, aplicando notações sistemáticas.

---

# 🕰️ **1. Origem e Contexto (1970–1980)**

Durante os anos 1970, o desenvolvimento de software carecia de metodologia e padronização. Sistemas eram escritos de forma artesanal, com baixo reuso e grande risco de falhas.

A Análise Estruturada surge como resposta a esse cenário, com influências diretas de:

* técnicas de **engenharia de sistemas**,
* modelagem de processos industriais,
* métodos formais emergentes,
* teoria das linguagens e autômatos.

### Principais pioneiros:

* **Tom DeMarco**

  * *Structured Analysis and System Specification* (1978)
* **Edward Yourdon & Larry Constantine**

  * *Structured Design* (1978)
* **Chris Gane & Trish Sarson**

  * Notação de DFD – *Structured Systems Analysis* (1979)

Seu objetivo central era promover **modelagem lógica**, evitando decisões prematuras de implementação.

---

# 🔷 **2. O Papel Central dos Diagramas de Fluxo de Dados (DFD)**

Os **DFDs** tornaram-se o símbolo máximo da Análise Estruturada e uma das ferramentas mais duradouras já criadas para modelagem de sistemas.

### Objetivo dos DFDs

Representar:

* fluxos de informação,
* processos que transformam dados,
* armazenamentos,
* entidades externas.

São organizados por meio de decomposição hierárquica:

1. **Diagrama de Contexto (nível 0)** – visão global do sistema
2. **DFD de Nível 1** – detalhamento dos principais processos
3. **Níveis inferiores** – decomposição funcional progressiva

### Notações mais utilizadas:

* **Yourdon/DeMarco**
* **Gane & Sarson**
* **SSADM (britânico)**

Os DFDs influenciaram, mais tarde:

* BPMN
* fluxogramas de dados em ETL
* arquitetura orientada a serviços
* pipelines de dados modernos

---

# 🔧 **3. Extensões e Evolução da Análise Estruturada**

À medida que a análise estruturada se expandiu, várias extensões surgiram para superar limitações, principalmente em:

* modelagem dinâmica
* comportamento temporal
* sistemas de tempo real
* controle de eventos
* integração entre processos, dados e estados

A seguir, as principais extensões históricas.

---

# 🚦 **4. Extensão para Sistemas de Tempo Real**

A análise estruturada clássica funciona bem para sistemas administrativos, porém era insuficiente para sistemas que dependem de:

* **tempo**,
* **eventos**,
* **conexões contínuas com o ambiente**,
* **sincronização**,
* **respostas rápidas**.

Por isso, nos anos 1980 surgiram métodos especializados.

---

# 🔶 **4.1. Ward & Mellor – Structured Analysis for Real-Time Systems**

Desenvolvida por **Paul Ward e Stephen Mellor**, é uma das extensões mais influentes.

### Problemas que eles resolveram:

* como modelar eventos (algo ausente nos DFDs)
* como representar comportamento baseado em estados
* como especificar resposta temporal

### Elementos introduzidos:

* **Diagramas de Controle** (Control Flow Diagrams)
* **Machine Models (Statecharts Específicos)**
* **Modelos de Transformação e de Fluxo**
* **Observadores de eventos (event-driven modeling)**

### Contribuição fundamental:

Eles combinaram:

* DFDs → descrição do fluxo de dados
* DCT (diagrama de controle) → comportamento dinâmico
* Modelos de Estados → lógica temporal do sistema

Essa integração representou um avanço decisivo para sistemas:

* embarcados
* aeroespaciais
* telecomunicações
* automotivos
* industriais

Ward & Mellor são considerados precursores diretos da UML, especialmente do diagrama de estados.

---

# 🔶 **4.2. Hatley & Pirbhai – Software Requirements for Real-Time Systems (1987)**

Hatley e Pirbhai elevaram ainda mais a modelagem de tempo real ao introduzir o conceito de **Arquitetura de Fluxo (Flow Architecture)**.

### Contribuições principais:

* separação clara entre **função**, **comportamento**, **controle** e **entrada/saída**
* criação da **Arquitetura de Controle** (Control Specification)
* ampliação dos DFDs usando:

  * *Control Flow Diagrams*
  * *Process Activation Tables*
  * *Timing Specifications*

### Diferenciais da abordagem:

* clarifica o que **controla** o sistema (eventos e condições)
* relaciona controle com processos e fluxos
* define prioridades, tempos máximos e janelas de resposta

É uma metodologia essencial para sistemas com:

* múltiplos sensores
* acionadores
* restrições rígidas de timing
* decisões baseadas em eventos

---

# 🧱 **5. Outras Extensões Relevantes**

### **5.1. Structured Systems Analysis and Design Method (SSADM)**

Método oficial do governo britânico na década de 1980.

Características:

* documentação extremamente detalhada
* integração entre DFDs, DER e dicionários de dados
* sequenciamento rígido de fases

Foi amplamente usado em bancos e grandes sistemas públicos.

---

### **5.2. Essential Modeling – McMenamin & Palmer (1984)**

Busca separar:

* modelo essencial (sem tecnologia)
* modelo de implementação

A principal crítica à análise estruturada clássica era o risco de misturar lógica do negócio com tecnologia.

---

### **5.3. Information Engineering – James Martin**

Foco principal:

* modelagem corporativa dos dados
* planejamento estratégico de sistemas
* lógica de alto nível

Foi muito influente na era dos bancos de dados relacionais.

---

# 📉 **6. Declínio e Transição para Modelos OO (1990–2000)**

Com o surgimento da orientação a objetos, a análise estruturada perdeu espaço devido a limitações:

* não trata bem objetos, herança e encapsulamento
* separa dados e processos de maneira rígida
* dificuldade de modelar interfaces ricas, concorrência e eventos

Metodologias OO começaram a dominar:

* Booch
* OMT (Rumbaugh)
* OOSE (Jacobson)
* UML (unificação nos anos 1990)

Ainda assim, as extensões para sistemas de tempo real influenciaram diretamente:

* diagrama de estados da UML
* diagrama de atividades
* notações de controle
* OMG SysML

---

# 🧬 **7. Influência na Engenharia de Software Moderna**

Apesar de não ser mais a abordagem dominante, a análise estruturada e seus derivados permanecem fundamentais em:

* engenharia de requisitos clássica
* modelagem funcional
* pipelines de dados
* ETL
* automação industrial
* sistemas legados
* órgãos governamentais
* bancos e empresas de telecom

### Heranças diretas:

* BPMN
* modelagem de processos
* arquitetura de integração
* arquitetura de controle em tempo real
* diagramas UML de estados e atividades

---

# 📌 **Resumo Final**

A **Análise Estruturada**:

* nasceu para enfrentar a Crise do Software,
* introduziu DFDs, dicionários de dados e decomposição funcional,
* foi ampliada para sistemas de tempo real por Ward & Mellor e Hatley & Pirbhai,
* dominou as décadas de 1970–1990,
* influenciou metodologias modernas,
* permanece relevante em sistemas legados e análise funcional.

É uma das metodologias mais importantes da história da Engenharia de Software.

---
