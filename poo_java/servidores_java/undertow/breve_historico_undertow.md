---

# ☕ Histórico e Características do Servidor Java Undertow

## 📘 Histórico do Undertow

O **Undertow** é um **servidor web e container de servlets leve e de alto desempenho** desenvolvido pela **Red Hat**, lançado oficialmente em **2013** como parte do projeto **WildFly** (anteriormente conhecido como JBoss Application Server).
Seu principal objetivo era substituir o antigo **JBoss Web (baseado no Apache Tomcat)**, oferecendo uma alternativa **mais moderna, rápida, modular e eficiente** em termos de uso de memória e processamento.

Desenvolvido em **Java puro**, o Undertow foi criado com uma arquitetura **reativa e não bloqueante**, baseada em **I/O assíncrono (NIO)**, o que o torna extremamente escalável. Essa abordagem permite lidar com **milhares de conexões simultâneas** usando um número mínimo de threads — um fator determinante para aplicações de alto tráfego e microserviços.

A partir do **WildFly 8 (2014)**, o Undertow tornou-se o **servidor HTTP e container padrão de servlets** da plataforma, sendo também integrado em projetos modernos como o **Quarkus** e **Spring Boot**, quando se busca leveza e alta performance.

Por ser **open source**, o Undertow é mantido pela comunidade **JBoss e Red Hat**, e continua evoluindo dentro do ecossistema **Jakarta EE** e **MicroProfile**.

---

## ⚙️ Principais Características do Undertow

| Característica                                      | Descrição Detalhada                                                                                                              |
| --------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------- |
| **Leveza e Desempenho**                             | Extremamente leve, com núcleo de apenas **4 MB**, podendo iniciar em **menos de 100 ms**. Ideal para containers e microserviços. |
| **Arquitetura Não Bloqueante (NIO)**                | Utiliza **I/O assíncrono** para lidar com milhares de conexões simultâneas com poucas threads, maximizando a eficiência.         |
| **Container de Servlets e WebSockets**              | Suporte completo ao **Jakarta Servlet**, **HTTP Upgrade** e **WebSocket API**, compatível com as especificações Jakarta EE.      |
| **Configuração Programática**                       | Pode ser configurado totalmente via **API Java**, sem necessidade de arquivos XML, oferecendo grande flexibilidade.              |
| **Integração com WildFly e Quarkus**                | É o **servidor embutido padrão** no WildFly e utilizado no Quarkus como **engine HTTP nativa** para microserviços reativos.      |
| **Suporte a HTTP/2 e TLS**                          | Inclui suporte nativo a **HTTP/2**, **TLS/SSL**, **redirecionamento seguro (HTTPS)** e autenticação configurável.                |
| **Modularidade Extrema**                            | A arquitetura modular permite usar apenas os componentes necessários, tornando o deploy muito mais eficiente.                    |
| **Compatibilidade com MicroProfile e Cloud Native** | Integra-se perfeitamente a soluções em **Docker**, **Kubernetes** e frameworks **Jakarta EE / MicroProfile**.                    |
| **Proxy e Reverse Proxy Integrado**                 | Pode atuar como **proxy reverso** de alto desempenho, semelhante ao NGINX, com balanceamento e caching.                          |
| **Open Source e Multiplataforma**                   | Distribuído sob **licença Apache 2.0**, totalmente compatível com qualquer sistema que suporte Java SE 11+.                      |

---

## 🕰️ Linha do Tempo – Versões do Undertow

| Versão           |  Ano | Integração / Plataforma | Principais Novidades                                                             | Status        |
| ---------------- | ---: | ----------------------- | -------------------------------------------------------------------------------- | ------------- |
| **Undertow 1.0** | 2013 | WildFly 8               | Primeira versão pública; substitui JBoss Web (Tomcat).                           | Obsoleta      |
| **Undertow 1.2** | 2015 | WildFly 9–10            | Suporte a WebSockets, melhorias no desempenho e compressão HTTP.                 | EOL           |
| **Undertow 2.0** | 2018 | WildFly 14+ / Quarkus   | Suporte a **HTTP/2**, TLS melhorado e integração com APIs reativas.              | Manutenção    |
| **Undertow 2.2** | 2021 | WildFly 23+             | Atualização para Java 11, correções de segurança e melhorias no non-blocking IO. | Ativa         |
| **Undertow 3.0** | 2023 | WildFly 30 / Quarkus 3  | Compatível com **Jakarta EE 10**, **Java 17** e **HTTP/3 experimental**.         | Estável atual |

---

## 🧠 Importância do Undertow

O Undertow representa uma **nova geração de servidores Java**, desenhada para o paradigma **reativo e orientado a microserviços**.
Sua leveza, desempenho e integração com plataformas modernas o tornam **ideal para aplicações em nuvem**, **APIs RESTful**, **gateways HTTP** e **sistemas de alta escalabilidade**.

Por ser um projeto **mantido pela Red Hat** e **aberto à comunidade**, o Undertow também serve de base para **pesquisas acadêmicas** e **testes de desempenho** comparativos com outros servidores como Tomcat, Jetty e GlassFish.

---

## 📚 Referência (formato ABNT)

RED HAT. *Undertow – A Flexible and High Performance Web Server*. Disponível em: [https://undertow.io](https://undertow.io). Acesso em: 9 nov. 2025.

---
