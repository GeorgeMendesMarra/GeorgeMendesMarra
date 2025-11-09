---

# ☕ Histórico e Características do Servidor Java WildFly (antigo JBoss AS)

## 📘 Histórico do WildFly

O **WildFly** é um **servidor de aplicações Java EE / Jakarta EE** de código aberto desenvolvido pela **Red Hat**, reconhecido por sua **alta performance**, **modularidade** e **suporte completo às especificações corporativas da plataforma Java**.

Originalmente, o projeto nasceu sob o nome **JBoss Application Server (JBoss AS)**, criado por **Marc Fleury** em **1999**, com o objetivo de oferecer uma **implementação gratuita e aberta da especificação J2EE (Java 2 Enterprise Edition)**.
Naquela época, o JBoss rapidamente se destacou por permitir que desenvolvedores tivessem acesso a recursos corporativos — como **EJBs, Servlets, JMS e JNDI** — sem custos de licença, rivalizando com servidores pagos como o **BEA WebLogic** e o **IBM WebSphere**.

Em **2006**, a empresa **JBoss Inc.** foi adquirida pela **Red Hat**, que assumiu o desenvolvimento e a manutenção do projeto, integrando-o ao ecossistema de soluções corporativas da companhia.
Com a evolução da plataforma Java, o servidor passou por diversas reestruturações e otimizações. Em **2013**, com o lançamento da versão 8, o nome **JBoss AS** foi oficialmente substituído por **WildFly**, simbolizando **leveza, velocidade e flexibilidade** — características centrais da nova arquitetura.

Desde então, o WildFly serve como **base para o Red Hat JBoss Enterprise Application Platform (JBoss EAP)**, versão comercial e com suporte empresarial.
Além disso, o WildFly é usado como **servidor de referência para muitas tecnologias Jakarta EE** e possui papel fundamental no desenvolvimento do **Quarkus**, framework moderno da Red Hat voltado a **microserviços e aplicações cloud-native**.

---

## ⚙️ Principais Características do WildFly

| Característica                            | Descrição Detalhada                                                                                            |
| ----------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| **Totalmente Open Source**                | Código-fonte disponível sob **licença LGPL**, mantido pela comunidade **JBoss e Red Hat**.                     |
| **Compatibilidade com Jakarta EE**        | Suporte completo às especificações **Jakarta EE 8, 9 e 10** (anteriormente Java EE).                           |
| **Arquitetura Modular (JBoss Modules)**   | Permite carregar apenas os módulos necessários, reduzindo consumo de memória e tempo de inicialização.         |
| **Servidor HTTP Undertow Integrado**      | Desde a versão 8, o **Undertow** é o servidor web padrão, substituindo o antigo Tomcat embutido.               |
| **Alta Performance e Escalabilidade**     | Capaz de lidar com **milhares de conexões simultâneas**, adequado para ambientes corporativos e em nuvem.      |
| **Suporte a Clustering e Load Balancing** | Inclui mecanismos de replicação de sessão, failover e balanceamento de carga nativo.                           |
| **Administração Simplificada**            | Possui **console web interativo** e **CLI (Command Line Interface)** para administração local e remota.        |
| **Deploy Flexível**                       | Aceita deploy via **arquivos WAR, EAR e JAR**, bem como via **hot deployment** e **scripts automatizados**.    |
| **Integração com MicroProfile e Cloud**   | Compatível com **Eclipse MicroProfile**, **Docker** e **Kubernetes**, voltado a arquiteturas de microserviços. |
| **Segurança Avançada**                    | Implementa autenticação via **JAAS, Elytron**, **LDAP**, **OAuth2**, e **Single Sign-On (SSO)**.               |
| **Suporte a Java Moderno**                | Compatível com **Java 11, 17 e 21**, com otimizações para JVMs modernas e ambientes containerizados.           |

---

## 🕰️ Linha do Tempo – Versões do WildFly / JBoss AS

| Versão                 |       Ano | Nome / Fase        | Especificação Suportada | Principais Novidades                                                      | Status               |
| ---------------------- | --------: | ------------------ | ----------------------- | ------------------------------------------------------------------------- | -------------------- |
| **JBoss AS 1.x – 3.x** | 1999–2003 | Primeiras versões  | J2EE 1.3                | Implementação inicial dos padrões EJB e Servlets.                         | Obsoletas            |
| **JBoss AS 4.x**       |      2004 | JBoss Inc.         | J2EE 1.4                | Suporte a JMS, JAAS e clustering básico.                                  | Obsoleta             |
| **JBoss AS 5.x**       |      2008 | Red Hat            | Java EE 5               | Introdução do microkernel JBoss Microcontainer.                           | EOL                  |
| **JBoss AS 6.x**       |      2010 | Red Hat            | Java EE 6 (parcial)     | Preparação para modularidade e suporte parcial à nova especificação.      | EOL                  |
| **JBoss AS 7.x**       |      2011 | Red Hat            | Java EE 6               | Reescrita total, modularidade com JBoss Modules.                          | Fim de vida (EOL)    |
| **WildFly 8.x**        |      2013 | Rebranding oficial | Java EE 7               | Novo nome, novo console, integração com Undertow.                         | Suporte limitado     |
| **WildFly 10.x**       |      2016 | Red Hat            | Java EE 7 / 8           | Suporte a HTTP/2, TLS 1.2 e nova segurança Elytron.                       | Manutenção           |
| **WildFly 18–26**      | 2019–2021 | Red Hat            | Jakarta EE 8–9          | Suporte ao namespace `jakarta.*`, integração com MicroProfile.            | Ativa                |
| **WildFly 27–32**      | 2022–2025 | Red Hat            | Jakarta EE 10           | Compatível com **Java 17+**, **Jakarta EE 10**, melhorias em cloud e TLS. | Versão estável atual |

---

## 🧠 Importância do WildFly

O WildFly é considerado um **dos servidores Java mais robustos e completos disponíveis**, sendo amplamente adotado em **ambientes corporativos, educacionais e governamentais**.
Seu modelo modular e o desempenho do **Undertow** o tornam adequado para sistemas de grande porte e também para aplicações leves baseadas em microserviços.

Além disso, como **base do Red Hat JBoss EAP**, o WildFly continua sendo **referência para certificação Jakarta EE**, influenciando o desenvolvimento de servidores modernos e frameworks Java corporativos.

---

## 📚 Referência (formato ABNT)

RED HAT. *WildFly – Lightweight, Flexible, and High-Performance Java Application Server*. Disponível em: [https://www.wildfly.org](https://www.wildfly.org). Acesso em: 9 nov. 2025.

---
