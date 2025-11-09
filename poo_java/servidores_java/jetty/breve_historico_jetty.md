# 🧩 Histórico e Características do Servidor Java Jetty

## 📘 Histórico do Jetty

O **Eclipse Jetty** é um **servidor web e contêiner de servlets Java** de código aberto, amplamente utilizado para hospedar aplicações baseadas em **Java Servlet**, **JSP** e tecnologias modernas como **WebSocket** e **HTTP/2**.  

O projeto Jetty foi criado em **1995** por **Greg Wilkins** e inicialmente mantido pela **Mort Bay Consulting**, sendo um dos primeiros servidores web a oferecer suporte completo à especificação **Servlet**. Seu desenvolvimento sempre se destacou pela **leveza**, **modularidade** e **embutibilidade**, permitindo que fosse facilmente incorporado a outras aplicações Java — característica que o diferencia do Apache Tomcat.  

Em **2009**, o Jetty passou a ser desenvolvido sob a **Eclipse Foundation**, adotando o nome **Eclipse Jetty**. Essa transição marcou sua integração mais profunda com o ecossistema **Eclipse IDE** e outros projetos open source da fundação. A partir daí, o Jetty tornou-se uma solução padrão para servidores embarcados em sistemas distribuídos, aplicações **microservices** e **IoT**.  

Nos anos 2010 e 2020, o Jetty evoluiu rapidamente para acompanhar as novas especificações **Jakarta EE**, além de oferecer suporte a **HTTP/2**, **WebSocket**, **Servlet 4.0** e, mais recentemente, a **HTTP/3 (QUIC)**.  
Sua arquitetura modular e seu foco em desempenho o tornaram uma escolha popular para **frameworks modernos**, como **Spring Boot**, **Spark Java** e **Scalatra**, bem como para **servidores de aplicações embarcados** dentro de containers Docker.

---

## ⚙️ Principais Características do Eclipse Jetty

| Característica | Descrição Detalhada |
|----------------|--------------------|
| **Leveza e Modularidade** | Jetty é projetado para ser leve e modular, permitindo que o desenvolvedor adicione apenas os componentes necessários. Isso reduz o consumo de memória e facilita a personalização. |
| **Embutível (Embeddable)** | Pode ser facilmente incorporado dentro de aplicações Java, sem necessidade de instalação separada. Essa característica é amplamente usada em microserviços e sistemas IoT. |
| **Suporte a HTTP/1.1, HTTP/2 e HTTP/3 (QUIC)** | Jetty foi um dos primeiros servidores a implementar HTTP/2 e está em processo de adoção do HTTP/3, garantindo alta performance em aplicações modernas. |
| **Compatibilidade com Jakarta EE** | Oferece suporte completo às especificações de **Jakarta Servlet**, **JSP**, **WebSocket** e **Security**, tornando-o compatível com os padrões Java corporativos. |
| **Alta Performance e Escalabilidade** | Projetado para lidar com milhares de conexões simultâneas, utilizando uma arquitetura baseada em threads leves e I/O não bloqueante (NIO). |
| **Integração com Frameworks Java** | Compatível com **Spring Boot**, **Dropwizard**, **Scalatra**, **Ktor**, entre outros frameworks que utilizam servidores embarcados. |
| **Open Source e Multiplataforma** | Código aberto mantido pela **Eclipse Foundation**, executa em qualquer sistema compatível com Java (Windows, Linux, macOS). |
| **Fácil Configuração e Implantação** | Pode ser configurado via XML, scripts ou APIs Java, facilitando automação em ambientes CI/CD e integração com contêineres. |
| **Segurança** | Suporte a TLS/SSL, autenticação básica e digest, controle de acesso baseado em roles e integração com JAAS (Java Authentication and Authorization Service). |
| **Suporte a WebSocket e HTTP/2 Push** | Ideal para aplicações em tempo real, como chats, dashboards e sistemas de streaming. |

---

## 🕰️ Linha do Tempo – Versões do Eclipse Jetty

| Versão | Ano | Especificações Suportadas | Principais Novidades / Recursos | Status |
|--------|-----:|---------------------------|---------------------------------|---------|
| **Jetty 1.x** | 1995 | Servlet inicial (pré-especificação) | Primeira implementação simples de servidor web em Java. | Obsoleta |
| **Jetty 3.x** | 2000 | Servlet 2.2 / JSP 1.1 | Primeira versão madura, compatível com Java Servlet. | Obsoleta |
| **Jetty 4.x** | 2002 | Servlet 2.3 | Introdução da arquitetura modular e foco em embutibilidade. | Obsoleta |
| **Jetty 5.x** | 2004 | Servlet 2.4 / JSP 2.0 | Maior estabilidade, ampliação da base de usuários open source. | Obsoleta |
| **Jetty 6.x** | 2006 | Servlet 2.5 | Suporte a NIO, início do suporte a WebSocket experimental. | EOL |
| **Jetty 7.x** | 2009 | Servlet 3.0 / JSP 2.2 | Transição para Eclipse Foundation; APIs assíncronas. | EOL |
| **Jetty 8.x** | 2011 | Servlet 3.0 / JSP 2.2 | Otimizações de desempenho e suporte a Java 7/8. | EOL |
| **Jetty 9.x** | 2014 | Servlet 3.1 / HTTP/2 / WebSocket 1.0 | Suporte completo a HTTP/2 e integração com frameworks modernos. | Suporte ativo parcial |
| **Jetty 10.x** | 2021 | Servlet 4.0 / Jakarta EE 9 | Migração para `jakarta.*`, suporte a Java 11+ e HTTP/2 Push. | Suporte ativo |
| **Jetty 11.x** | 2022–2025 | Jakarta EE 10 / HTTP/3 | Suporte a HTTP/3, TLS 1.3 e melhorias em segurança e escalabilidade. | Versão estável atual |

---

## 📚 Referência (formato ABNT)

ECLIPSE FOUNDATION. *Eclipse Jetty – Lightweight Java Application Server*. Disponível em: [https://www.eclipse.org/jetty/](https://www.eclipse.org/jetty/). Acesso em: 9 nov. 2025.
