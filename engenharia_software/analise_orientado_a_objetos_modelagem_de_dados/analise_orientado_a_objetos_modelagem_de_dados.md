# 🧠 **Histórico da Análise Orientada a Objetos e da Modelagem de Dados**

A Análise Orientada a Objetos (OOA) e a Modelagem de Dados surgiram como respostas à complexidade crescente dos sistemas de software nas décadas finais do século XX. Ambas evoluíram ao longo do tempo, cada uma com raízes em áreas distintas: a OOA deriva da programação orientada a objetos, enquanto a modelagem de dados tem origem no mundo dos bancos de dados e na organização lógica das informações.

A seguir, apresenta-se uma **linha do tempo detalhada**, com marcos conceituais, autores e transformações históricas que moldaram essas áreas.

---

# 🗓️ **Linha do Tempo Geral (1960–2020)**

## **1960–1970: As bases iniciais**

### **Orientação a Objetos**

* **1966–1967** — **Simula 67**, criado por Ole-Johan Dahl e Kristen Nygaard.

  * Considerado o primeiro paradigma OO.
  * Introduziu classes, objetos, herança e simulação.

### **Modelagem de dados**

* **1963–1968** — Surgem métodos formais de organização de dados em sistemas de informação.
* **1970** — **Edgar F. Codd** apresenta o **Modelo Relacional**, marco fundamental da modelagem de dados.

  * Introduz tabelas, relações, dependências funcionais e normalização.

---

## **1970–1985: Consolidação das bases**

### **Orientação a Objetos**

* **1972** — Linguagem **Smalltalk**, no Xerox PARC.

  * Introduz tudo como objeto, mensagens, encapsulamento sofisticado.
  * Transforma OO em paradigma completo.

* **Final dos anos 70** — Primeiras tentativas de usar conceitos OO para análise e projeto.

  * Nasce o embrião da Análise Orientada a Objetos.

### **Modelagem de Dados**

* **1976** — **Peter Chen** apresenta o **modelo Entidade-Relacionamento (ER)**.

  * Representa entidades, atributos e relacionamentos.
  * Torna-se base de praticamente todos os SGBDs futuros.

* **1980–1985** — Estruturas derivadas: DER estendido, DER temporal, DER hierárquico.

* A modelagem de dados torna-se obrigatória em projetos de software corporativo.

---

## **1985–1995: O surgimento das metodologias orientadas a objetos**

### **Orientação a Objetos**

Diversos métodos competem entre si, cada um propondo notações e diagramas:

* **1988** — **Coad & Yourdon**: método voltado à análise orientada a objetos.

* **1989** — **Booch Method** (Grady Booch)

  * Um dos primeiros métodos amplamente utilizados.
  * Foco em design OO, com diagramas complexos.

* **1990** — **OMT (Object Modeling Technique)** — James Rumbaugh

  * Propõe modelos:

    * Modelo de Objetos
    * Modelo Dinâmico
    * Modelo Funcional

* **1994** — **OOSE (Object-Oriented Software Engineering)** — Ivar Jacobson

  * Introduz **use cases** (casos de uso), mudança histórica.
  * Estruturas fundamentais para captura de requisitos.

Esta fase é conhecida como **“guerra dos métodos OO”**, onde mais de 50 métodos coexistiam.

### **Modelagem de Dados**

* A modelagem ER continua dominante.
* Surgem métodos híbridos (Ex.: Information Engineering de James Martin).
* Modelagem orientada a objetos começa a influenciar estruturas de dados.

---

## **1995–2000: Unificação dos métodos e o nascimento da UML**

### **1995–1997 — A grande mudança**

* A Rational Software reúne Booch, Rumbaugh e Jacobson.
* Surge a **Unified Modeling Language (UML)**.
* **1997** — UML é padronizada pela OMG.
* A OOA passa a usar:

  * Diagramas de classes
  * Casos de uso
  * Sequência
  * Atividades
  * Estados

A UML absorve aspectos de OMT, Booch e OOSE.

### **Modelagem de Dados**

* Modelagem OO e relacional começam a se encontrar:

  * Mapeamento Objeto-Relacional (ORM) ganha força.
  * Primeiras ferramentas ORM surgem (embriões do Hibernate futuro).

---

## **2000–2010: Consolidação das abordagens orientadas a objetos**

### **Orientação a Objetos**

* UML 2.x torna-se padrão universal.

* Análise OO passa a envolver:

  * Identificação de classes do domínio
  * Modelagem de responsabilidades
  * Aplicação de padrões de projeto (GoF, 1994)

* Proliferação de metodologias:

  * Rational Unified Process (RUP)
  * ICONIX
  * Unified Process (UP)
  * Agile Model Driven Development

### **Modelagem de Dados**

* Modelagem estendida continua em uso (Cardinalidades, DER completo).
* Integração com bancos NoSQL exige novos modelos (documentos, grafos).
* Práticas como Data Warehousing e OLAP impulsionam modelagem dimensional (Kimball).

---

## **2010–2020: Integração com desenvolvimento ágil e arquiteturas modernas**

### **Orientação a Objetos**

* OOA evolui para práticas simplificadas no contexto ágil:

  * Modelagem “just enough”
  * Diagramas leves
  * Foco em domínio (DDD — Domain-Driven Design)

* **Domain-Driven Design (Eric Evans, 2003)** cresce exponencialmente:

  * Entidades
  * Objetos de valor
  * Agregados
  * Serviços de domínio

### **Modelagem de Dados**

* Big Data e NoSQL ampliam as possibilidades:

  * Modelos chave-valor
  * Documentos
  * Colunas largas
  * Grafos

* Modelagem tradicional não desaparece, mas adapta-se.

* ETL e governança de dados impulsionam metodologias híbridas.

---

# 🏗️ **Conexão entre Análise OO e Modelagem de Dados**

Mesmo originadas em contextos diferentes, ambas se convergem:

### **Análise OO**

* Modela comportamento, interações, responsabilidades.
* Foca em **objetos vivos** no domínio.

### **Modelagem de Dados**

* Modela estrutura lógica e persistência.
* Foca em **dados estáticos**.

Com o tempo, aparecem práticas como:

* ORM (Hibernate, JPA)
* Mapeamento entre classes e tabelas
* DDD + repositórios
* Separação clara entre domínio e persistência (Clean Architecture)

---

# 📌 **Resumo Final**

A evolução da Análise Orientada a Objetos e da Modelagem de Dados foi marcada por:

* Surgimento do paradigma OO (anos 60–70)
* Consolidação da modelagem de dados com o modelo relacional (anos 70)
* Multiplicidade de métodos OO (anos 80–90)
* Unificação pela UML (1997)
* Integração entre OO e modelagem de dados (2000 em diante)
* Adaptação às práticas ágeis e bancos não relacionais (2010–2020)

---
