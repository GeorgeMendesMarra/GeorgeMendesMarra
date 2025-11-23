---

# 🧭 **Histórico dos Aspectos Fundamentais do Projeto de Software**

O projeto de software (software design) evoluiu ao longo de mais de seis décadas, acompanhando a maturidade da engenharia de software e as mudanças nos paradigmas de programação. Seu objetivo é transformar requisitos em uma arquitetura compreensível, organizada e capaz de guiar a implementação. A seguir, um panorama histórico detalhado.

---

# 📜 **1. Anos 1960 — A Era Estrutural Inicial**

Nos primórdios da programação, o projeto de software era informal e pouco sistematizado. Os sistemas eram desenvolvidos de maneira artesanal, e apenas grandes instituições como IBM, Bell Labs e instituições militares produziam software em larga escala.

### **Marcos importantes**:

* **1966–1968:** A crise do software leva à necessidade de métodos mais sistemáticos.
* **1968:** Conferência da OTAN — o termo "engenharia de software" é popularizado.
* **Final dos anos 1960:** Surge a **programação estruturada** (Dijkstra, Hoare, Mills).

### **Aspectos-chave da época**:

* Redução do uso do comando GOTO.
* Ênfase em modularidade e blocos de controle estruturados (sequência, seleção, iteração).
* Início da preocupação com **acoplamento** e **coesão**.

---

# 📜 **2. Anos 1970 — Consolidação do Projeto Estruturado**

Com a ascensão dos métodos estruturados, o projeto de software tornou-se mais formal.

### **Métodos marcantes**:

* **Yourdon & Constantine (1975):** Estruturas de projeto baseadas em fluxos de dados.
* **DeMarco (1978):** Análise estruturada com **DFD – Diagramas de Fluxo de Dados**.
* **Boehm (1978):** Primeiros modelos de custo e impacto no projeto (COCOMO).

### **Aspectos fundamentais consolidados**:

* Diagramação sistemática.
* Refinamento funcional ("top-down design").
* Introdução dos conceitos de **camadas**, **módulos** e **interfaces explícitas**.

---

# 📜 **3. Anos 1980 — Modularidade, Informação Oculta e Início do OO**

O foco passou a ser a redução da complexidade, enfatizando encapsulamento e ocultação de informações (David Parnas).

### **Avanços**:

* **1982:** Parnas publica os princípios de módulos de responsabilidade única.
* **Meio dos anos 1980:** Linguagens orientadas a objetos, como C++ e Smalltalk, impulsionam o design OO.
* **1986:** Pressman reforça o processo de projeto como atividade sistemática.

### **Aspectos fundamentais da década**:

* Maior foco em manutenibilidade.
* Abordagens híbridas entre estrutura e objeto.
* Adoção do conceito de **interfaces bem definidas** e responsabilidade de componentes.

---

# 📜 **4. Anos 1990 — Engenharia de Software Moderna e UML**

Os anos 1990 foram marcados pela padronização e formalização da orientação a objetos.

### **Momentos decisivos**:

* **1994:** Gang of Four publica *Design Patterns*, influenciando fortemente o projeto de software.
* **1997:** Surge a **UML**, unificando notações OO.
* **Modelos arquiteturais** (MVC, Cliente-Servidor, Camadas) se tornam mainstream.

### **Aspectos fundamentais desta fase**:

* Introdução de padrões de projeto.
* Uso extensivo de diagramas UML (classes, sequência, estados).
* Formalização da arquitetura de software como disciplina própria.

---

# 📜 **5. Anos 2000 — Arquiteturas Distribuídas, SOA e Engenharia de Produto**

### Avanços importantes:

* **Arquitetura orientada a serviços (SOA)**.
* Crescimento dos sistemas distribuídos e web.
* Engenharia de produto e frameworks de reuso (J2EE, .NET).

### Aspectos fundamentais reforçados:

* Componentização.
* Separação clara entre arquitetura e design de detalhamento.
* Qualidade de serviço: desempenho, segurança, escalabilidade.

---

# 📜 **6. Anos 2010 — Arquiteturas para Grandes Escalas e DevOps**

### Evoluções marcantes:

* **Microserviços**, **rest APIs**, **cloud computing**.
* Popularização do DevOps afeta o projeto: sistemas devem ser **observáveis**, **implantáveis** e **resilientes**.
* Design para concorrência (actors, reactive programming).

### Aspectos fundamentais ampliados:

* Tolerância a falhas.
* Desacoplamento máximo.
* Escalabilidade horizontal como objetivo primário.

---

# 📜 **7. Anos 2020–2025 — IA, Sistemas Autônomos e Engenharia Contínua**

### Tendências atuais:

* Aplicação de **inteligência artificial** no apoio ao projeto.
* Arquiteturas orientadas a eventos, pipelines, serverless.
* Sistemas autônomos que adaptam seu comportamento (self-adaptive).

### Aspectos fundamentais contemporâneos:

* Observabilidade nativa.
* Governança de APIs.
* Arquiteturas evolutivas.

---

# ⭐ **Aspectos Fundamentais do Projeto ao Longo da História**

Independentemente da época, alguns princípios permanecem:

| Aspecto Fundamental   | Descrição                                                           |
| --------------------- | ------------------------------------------------------------------- |
| **Modularidade**      | Dividir o sistema em partes independentes e gerenciáveis.           |
| **Abstração**         | Ocultar detalhes internos e expor apenas o necessário.              |
| **Coesão**            | Cada módulo deve ter uma única responsabilidade bem definida.       |
| **Acoplamento**       | Reduzir dependências para facilitar manutenção e evolução.          |
| **Encapsulamento**    | Proteger dados e lógica interna de cada módulo.                     |
| **Reuso**             | Aproveitar componentes já existentes para reduzir tempo e defeitos. |
| **Arquitetura clara** | Definir camadas, componentes e relações.                            |

---

# 🧩 **Tabelas Comparativas Entre Paradigmas de Projeto**

A seguir, tabelas diferenciando abordagens clássicas no design de software.

---

## 🟦 **1. Projeto Estruturado vs Projeto Orientado a Objetos**

| Critério         | Projeto Estruturado         | Projeto OO                        |
| ---------------- | --------------------------- | --------------------------------- |
| Unidade básica   | Função/Procedimento         | Objeto/Classe                     |
| Controle         | Fluxo sequencial            | Troca de mensagens                |
| Representação    | DFD, diagramas de estrutura | UML (classes, sequência, estados) |
| Reuso            | Limitado                    | Alto (herança, polimorfismo)      |
| Modificabilidade | Média                       | Alta                              |
| Foco             | Processo                    | Dados + Comportamento             |
| Exemplos         | C, Pascal                   | Java, C++, Python                 |

---

## 🟩 **2. Paradigma Orientado a Objetos vs Paradigma Componente**

| Critério             | OO                          | Componentes                     |
| -------------------- | --------------------------- | ------------------------------- |
| Tamanho das unidades | Classes                     | Módulos prontos para uso        |
| Reuso                | Médio/Alto                  | Muito alto                      |
| Vínculo              | Estático                    | Dinâmico (plug-and-play)        |
| Independência        | Baixa (muitas dependências) | Alta (interfaces bem definidas) |
| Exemplos             | Java, C++                   | JavaBeans, .NET Assemblies      |

---

## 🟧 **3. Arquitetura Monolítica vs Microserviços**

| Critério       | Monolítico                  | Microserviços                 |
| -------------- | --------------------------- | ----------------------------- |
| Implantação    | Única unidade               | Vários serviços independentes |
| Escalabilidade | Vertical                    | Horizontal                    |
| Complexidade   | Baixa no início             | Alta no longo prazo           |
| Comunicação    | Memória interna             | APIs REST/Eventos             |
| Manutenção     | Difícil em sistemas grandes | Mais fácil por serviço        |

---

## 🟥 **4. Paradigma Funcional vs Imperativo**

| Critério   | Funcional                       | Imperativo             |
| ---------- | ------------------------------- | ---------------------- |
| Estado     | Imutável                        | Mutável                |
| Execução   | Declara o que é                 | Explica como fazer     |
| Primitivas | Funções puras                   | Atribuições e loops    |
| Segurança  | Alta (menos efeitos colaterais) | Depende do programador |
| Exemplos   | Haskell, Clojure                | Java, C, Python        |

---
