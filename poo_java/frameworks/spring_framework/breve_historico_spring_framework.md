---

# Histórico dos Módulos do Spring Framework

## 1. O Núcleo (Core Container) - 2002/2003

Este é o coração do Spring, lançado com a versão 1.0. Ele introduziu o conceito de **Injeção de Dependência (DI)** e **Inversão de Controle (IoC)**.

* **Beans e Core:** Fornecem a infraestrutura fundamental. O histórico aqui é a substituição dos pesados EJBs (Enterprise JavaBeans) por POJOs simples.
* **Context:** Introduziu o `ApplicationContext`, que funciona como um registro de objetos do sistema.
* **SpEL (Spring Expression Language):** Adicionado na versão 3.0 para permitir manipular o gráfico de objetos em tempo de execução.

## 2. Acesso a Dados e Integração (Data Access) - 2003/2004

Criado para simplificar a comunicação com bancos de dados e reduzir o código repetitivo (*boilerplate*) do JDBC puro.

* **JDBC:** O `JdbcTemplate` foi um dos primeiros módulos, eliminando a necessidade de abrir e fechar conexões manualmente.
* **ORM (Object Relational Mapping):** Originalmente criado para integrar com Hibernate 2 e JDO. Hoje suporta as versões mais recentes do JPA e Hibernate.
* **Transactions:** Introduziu o gerenciamento de transações declarativo (o famoso `@Transactional`), um marco que permitiu gerenciar bancos de dados sem código complexo.

## 3. Web (Servlet e Reactive) - 2004 - Presente

O módulo Web evoluiu drasticamente conforme a internet mudava de páginas estáticas para APIs e fluxos de dados.

* **Spring MVC (2004):** Surgiu na versão 1.0 como uma alternativa superior ao Struts. Ele separou a lógica de controle da visualização (JSP, Thymeleaf).
* **Spring WebSocket (2013):** Adicionado na versão 4.0 para suportar comunicação bidirecional em tempo real.
* **Spring WebFlux (2017):** Introduzido na versão 5.0. Foi uma mudança histórica para o **paradigma reativo** (non-blocking), permitindo que o Spring competisse com Node.js em escalabilidade.

## 4. Aspectos e Instrumentação (AOP) - 2004

* **Spring AOP:** Lançado para permitir a Programação Orientada a Aspectos. O histórico desse módulo está ligado à necessidade de separar "preocupações transversais", como logs e segurança, da lógica de negócio principal.
* **Aspects:** Oferece integração profunda com o AspectJ (um framework de AOP mais robusto).

## 5. Módulos de Teste (Test) - 2003

O Spring foi construído com a testabilidade em mente desde o dia 1.

* **Módulo Test:** Evoluiu do suporte básico ao JUnit 3 até as integrações modernas com JUnit 5 e Mockito, permitindo carregar o contexto do Spring de forma parcial para testes de integração rápidos.

## 6. Módulos Especializados (Spring Portfolio)

A partir da versão 3.0, o Spring deixou de ser apenas um framework e tornou-se um ecossistema. Isso deu origem a módulos que hoje são quase frameworks independentes:

* **Spring Security (2008):** Originalmente chamado de **Acegi Security**, foi integrado como módulo oficial para lidar com autenticação e autorização.
* **Spring Data (2010):** Criado para unificar o acesso a bancos NoSQL (MongoDB, Redis, Cassandra) e SQL.
* **Spring Cloud (2014):** Lançado para fornecer ferramentas para padrões de Microserviços (Configuração centralizada, Service Discovery, Circuit Breakers).
* **Spring Batch:** Criado em parceria com a Accenture para processamento de grandes volumes de dados (Batch Jobs).

---

### Resumo da Evolução por Versão

| Versão | Ano | Foco Principal |
| --- | --- | --- |
| **1.0** | 2004 | Injeção de Dependência, JDBC e MVC básico. |
| **2.0** | 2006 | Configurações baseadas em XML (Namespaces) e AOP. |
| **3.0** | 2009 | Suporte total a **Annotations** (adeus XML!) e SpEL. |
| **4.0** | 2013 | Suporte ao Java 8, WebSockets e o início do Spring Boot. |
| **5.0** | 2017 | Programação Reativa (**WebFlux**) e suporte ao Kotlin. |
| **6.0** | 2022 | Observabilidade nativa e suporte ao GraalVM (Binários Nativos). |

---

**Conclusão Histórica:** O Spring começou como um "facilitador" para aplicações Java EE pesadas e evoluiu para um sistema modular onde o desenvolvedor escolhe "peças de Lego" (os módulos) para montar desde um simples script até um sistema global de microserviços.
