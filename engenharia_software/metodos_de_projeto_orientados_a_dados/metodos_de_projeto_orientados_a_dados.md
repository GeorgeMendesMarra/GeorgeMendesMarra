---

# 📘 **Histórico dos Métodos de Projeto Orientados a Dados (Data-Oriented Design)**


Os métodos de **projeto orientados a dados** surgiram como uma resposta à crescente complexidade dos sistemas de informação a partir da década de **1960**, quando bancos de dados comerciais começaram a se popularizar. Antes disso, a maior parte dos softwares era fortemente acoplada à lógica de processamento, com pouco foco no modelo dos dados. A necessidade de estruturar, padronizar e controlar o fluxo de informações levou ao desenvolvimento de metodologias baseadas em dados.

---

# 🕰️ **Linha do Tempo Histórica**

### **1960–1970 — Era dos Arquivos e dos Diagramas de Fluxo**

* Softwares organizavam dados principalmente em **arquivos sequenciais e indexados**.
* O projeto era **processo-centrado**, com pouca independência dos dados.
* Surgem abordagens iniciais como:

  * *HIPO* (Hierarchy plus Input-Process-Output),
  * *DFD embrionários* (pré-análise estruturada).

**Limitação:** mudanças nos dados exigiam reescrever parte significativa do software.

---

### **1970–1980 — Modelo Relacional e o Foco no Dado**

* **Edgar F. Codd (1970)** publica o modelo relacional, marcando o início do pensamento **data-centric**.
* Organizações percebem que o **banco de dados** deve ser estável e duradouro, enquanto processos são mutáveis.
* Surgem metodologias alinhadas ao paradigma:

  * **Análise Estruturada** (DeMarco, Yourdon)
  * **Modelagem Entidade-Relacionamento (Chen, 1976)**
  * **Decomposição funcional orientada pelos dados**

Essa década estabelece o princípio:
📌 **Primeiro modele os dados; depois derive o software.**

---

### **1980–1990 — Formalização dos Métodos de Projeto Orientados a Dados**

* Com o avanço dos SGBDs relacionais (Oracle, DB2, Ingres), torna-se comum modelar sistemas segundo:

  * **E/R → modelos lógicos → modelos físicos**
* Surgem métodos específicos:

  * **NIAM — Natural Information Analysis Method**
  * **ORM — Object Role Modeling**
  * **Merise (França)** — ciclo de vida centrado em dados.
  * **SSADM (Reino Unido)** — forte ênfase em dicionário de dados e modelagem estruturada.

Esses métodos buscam **reduzir redundâncias**, **evitar anomalias** e **padronizar semânticas**.

---

### **1990–2000 — Integração com OO e Engenharia de Software Moderna**

* Crescimento da Programação Orientada a Objetos e do **UML**.
* Alguns métodos orientados a dados se adaptam incorporando:

  * diagramas de classes,
  * regras de negócio,
  * mapeamentos objeto-relacional.
* Surgem estratégias híbridas:

  * **OO + E/R**
  * **OO + ORM**
  * **Arquiteturas centradas em dados (Data-Centric Architectures)**

---

### **2000–2010 — Data Warehousing, Business Intelligence e Big Data Inicial**

* Projetos passam a exigir:

  * modelagem multidimensional (**Kimball**, **Inmon**),
  * esquemas estrela e floco de neve,
  * análise temporal de dados.
* A orientação a dados se expande para:

  * **ETL**,
  * **data lakes**,
  * **governança de dados**.

---

### **2010–Presente — Sistemas Altamente Data-Driven**

* Aplicações modernas são **extremamente orientadas a dados**:

  * Aprendizado de Máquina
  * Streaming (Kafka)
  * Bancos NoSQL
  * Microserviços baseados em eventos
  * Data mesh / data fabric

Os métodos orientados a dados evoluem para:

* Modelagem poliglota
* Observabilidade dos dados
* Projetos orientados a esquemas evolutivos

---

# 🧩 **Características dos Métodos de Projeto Orientados a Dados**

| Aspecto                 | Descrição                                                                                |
| ----------------------- | ---------------------------------------------------------------------------------------- |
| **Foco principal**      | Entender, estruturar e organizar os dados antes de modelar processos.                    |
| **Ferramentas típicas** | Modelo E/R, ORM, dicionários de dados, normalização, diagramas conceituais.              |
| **Benefícios**          | Maior consistência, menos redundância, manutenção facilitada.                            |
| **Pontos fracos**       | Podem ignorar dinâmicas complexas do sistema; menos adequados a sistemas muito reativos. |

---

# 📚 **Metodologias Clássicas (Resumo)**

### **1. E/R (Chen) — 1976**

Base conceitual para bancos relacionais.
Usa entidades, relacionamentos, cardinalidades.

### **2. NIAM → ORM**

Modelagem semântica de dados usando *roles*.
Forte rigor lógico.

### **3. SSADM (Structured Systems Analysis and Design Method)**

Método governamental do Reino Unido (anos 80).
Foco em:

* DFDs,
* Dicionários de dados,
* Especificações formais.

### **4. Merise**

Ciclo de vida com níveis: conceitual → lógico → físico.
Separação rígida entre dados e processos.

---

# 🏗️ **Aplicações Práticas Modernas**

* Modelagem de banco de dados corporativo
* Projeto de API orientada a recursos (REST)
* Normalização e governança de dados
* Projeto de Data Warehouse (Kimball/Inmon)
* Sistemas de cadastro e ERP

---

# ⚖️ **Comparação com Outras Abordagens**

| Aspecto           | Orientado a Dados              | Orientado a Objetos      | Análise Estruturada      | Reativo / Dirigido a Eventos |
| ----------------- | ------------------------------ | ------------------------ | ------------------------ | ---------------------------- |
| Foco              | Dados e relacionamentos        | Objetos e comportamentos | Processos e funções      | Eventos, fluxos, tempo real  |
| Estabilidade      | Alta                           | Média                    | Baixa                    | Baixa                        |
| Adequado para     | Bancos de dados, sistemas CRUD | Sistemas complexos, OO   | Sistemas administrativos | Sistemas de controle         |
| Diagramas típicos | ER, ORM                        | Classes, sequências      | DFDs                     | Statecharts                  |

---

# 🧪 **Exemplo Prático (Simplificado)**

### **Sistema de Biblioteca – Modelo Conceitual (E/R)**

Entidades:

* **Livro**(ISBN, Título, AutorID)
* **Autor**(AutorID, Nome)
* **Empréstimo**(EmpID, Livro, Usuário, DataSaída, DataDevolução)

Relacionamentos:

* Autor —< Livro
* Livro —< Empréstimo
* Usuário —< Empréstimo

Esse modelo serve de base para:

* normalização,
* criação das tabelas,
* regras de integridade,
* APIs futuras.

---

# 🎯 **Conclusão**

Os **métodos de projeto orientados a dados** evoluíram de simples esquemas de arquivos até abordagens sofisticadas para sistemas distribuídos e massivos. Mesmo com a ascensão dos paradigmas orientados a objetos, funcionais ou reativos, o foco nos dados permanece vital — especialmente na era do Big Data, inteligência artificial e arquiteturas orientadas a eventos.

---

