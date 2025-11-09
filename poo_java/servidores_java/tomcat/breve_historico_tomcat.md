# 🐱 Apache Tomcat: Histórico e Características

## 📘 Introdução

O **Apache Tomcat** é um **servidor de aplicações web em Java** amplamente utilizado em todo o mundo. Desenvolvido e mantido pela **Apache Software Foundation (ASF)**, o Tomcat implementa as especificações das tecnologias **Jakarta Servlet**, **JavaServer Pages (JSP)**, **Jakarta Expression Language (EL)** e **WebSocket**, fornecendo um ambiente leve, robusto e multiplataforma para o desenvolvimento e execução de aplicações baseadas em Java.

Desde sua criação, o Tomcat se consolidou como uma solução de **código aberto**, **gratuita** e **estável**, tornando-se a escolha padrão para desenvolvedores que buscam **simplicidade**, **desempenho** e **conformidade com os padrões da plataforma Jakarta EE** (antiga Java EE).

---

## 🕰️ Histórico do Apache Tomcat

O projeto **Apache Tomcat** teve sua origem em **1998**, a partir de uma implementação de referência desenvolvida pela **Sun Microsystems** para as tecnologias **Servlet** e **JSP**. O código inicial foi criado por **James Duncan Davidson**, engenheiro da Sun, que o doou posteriormente à comunidade Apache, marcando o início de uma das iniciativas mais relevantes de software livre no ecossistema Java.

Em **1999**, a **Apache Software Foundation (ASF)** oficializou o Tomcat como um projeto próprio. A partir daí, o servidor passou a evoluir rapidamente, sendo constantemente atualizado para acompanhar as versões das especificações Java.

Durante os primeiros anos, o **Tomcat 3** ainda refletia a arquitetura inicial da Sun, mas já trazia melhorias na modularização e na performance. Com o **Tomcat 4**, lançado em **2002**, foi introduzido o **Catalina**, um novo motor de servlet que tornou o servidor mais estável e extensível.

Em **2004**, o **Tomcat 5** ampliou o suporte às APIs **Servlet 2.4** e **JSP 2.0**, além de reforçar a integração com outras tecnologias corporativas. Já o **Tomcat 6**, lançado em **2007**, focou na compatibilidade com o **Java EE 5**, adicionando suporte a **Servlet 2.5** e melhor gerenciamento de threads e recursos.

O **Tomcat 7** (2011) trouxe suporte ao **Servlet 3.0**, permitindo o uso de **processamento assíncrono** e **anotações**, enquanto o **Tomcat 8** (2014) implementou **WebSockets**, **Servlet 3.1** e melhorias de segurança.

Com o **Tomcat 9** (2018), a ASF incorporou o suporte ao **HTTP/2** e à especificação **Servlet 4.0**, alinhando o servidor aos requisitos modernos da web. A partir do **Tomcat 10** (2021), ocorreu a migração total do namespace `javax.*` para `jakarta.*`, acompanhando a transição do **Java EE para Jakarta EE** sob a administração da **Eclipse Foundation**.

Atualmente, o Tomcat continua sendo atualizado, com o **Tomcat 11** em desenvolvimento, trazendo melhorias voltadas à **containerização (Docker e Kubernetes)**, **microserviços** e **computação em nuvem**.

---

## 📊 Linha do Tempo das Versões do Apache Tomcat

| Versão | Ano | Especificações Servlet / JSP / Jakarta | Principais novidades / recursos | Status (em 09/11/2025) |
|--------|-----:|----------------------------------------|----------------------------------|------------------------|
| **Tomcat 3.x** | 1999 | Servlet 2.2 / JSP 1.1 | Primeiras versões sob Apache, implantação inicial do contêiner de servlets. | Obsoleta |
| **Tomcat 4.x** | 2002 | Servlet 2.3 / JSP 1.2 | Introdução do *Catalina* (motor de servlet); maior modularidade e estabilidade. | Obsoleta |
| **Tomcat 5.x** | 2004 | Servlet 2.4 / JSP 2.0 | Melhor compatibilidade com Java EE da época; melhorias de desempenho e configuração. | Obsoleta |
| **Tomcat 6.x** | 2007 | Servlet 2.5 / JSP 2.1 | Melhor gerenciamento de threads e recursos; alinhamento com Java EE 5. | Obsoleta |
| **Tomcat 7.x** | 2011 | Servlet 3.0 / JSP 2.2 | Suporte a APIs assíncronas, anotações, melhorias em segurança e deploy. | Fim de vida (EOL) |
| **Tomcat 8.x** | 2014 | Servlet 3.1 / JSP 2.3 / WebSocket 1.0 | Suporte a WebSocket, melhorias para Java 8, melhor I/O e configurações. | Suporte limitado / EOL em versões antigas |
| **Tomcat 9.x** | 2018 | Servlet 4.0 / HTTP/2 | Suporte a HTTP/2, melhorias de segurança e monitoramento; compatível com `javax.*`. | Suportado (manutenção) |
| **Tomcat 10.x** | 2021 | Jakarta EE 9 (migrado para `jakarta.*`) | Migração do namespace `javax.*` → `jakarta.*`; alinhamento com Jakarta EE 9. | Suportado (manutenção e atualizações) |
| **Tomcat 11.x** | 2024–2025 | Jakarta EE 10/11 (evolução) | Adequações para Jakarta EE 10/11, melhorias para cloud-native; preparação para HTTP/3 (dependendo da build). | Em desenvolvimento / mais recente |

> **Observação:** O status indica a situação geral de suporte até **novembro de 2025**. As versões mais recentes (Tomcat 10 e 11) possuem atualizações contínuas e são recomendadas para projetos atuais.

---

## 🧩 Características Técnicas do Apache Tomcat

### ⚙️ 1. Arquitetura Modular e Leve
- Baseado no **motor Catalina**, responsável pelo processamento de Servlets e JSPs.
- Possui componentes modulares como **Coyote** (conector HTTP) e **Jasper** (compilador JSP).
- Permite integração com outros servidores, como o **Apache HTTP Server**.

### 🌐 2. Suporte às Especificações Jakarta EE
- Implementa **Servlet**, **JSP**, **EL** e **WebSocket**.
- Compatível com frameworks como **Spring**, **Hibernate** e **JSF**.
- Ideal para **aplicações web corporativas** e **sistemas educacionais**.

### 🛠️ 3. Desempenho e Escalabilidade
- Suporte a **HTTP/2**, **SSL/TLS** e **processamento assíncrono**.
- Gerenciamento eficiente de **threads** e múltiplas conexões simultâneas.
- Suporte a **clustering** e balanceamento de carga.

### 🔒 4. Segurança
- Autenticação e autorização via **Realms**, **LDAP** e **roles** (funções).
- Suporte a **SSL**, **HTTPS** e controle de acesso detalhado.
- Atualizações frequentes para correção de vulnerabilidades.

### 📦 5. Implantação Simples e Multiplataforma
- Aplicações empacotadas em **arquivos WAR (Web Application Archive)**.
- Executável em **Windows, Linux e macOS**.
- Compatível com **Docker** e **Kubernetes**.

### 🧠 6. Código Aberto e Comunidade Ativa
- Projeto **open source** da **Apache Software Foundation**.
- Grande base de usuários, documentação extensa e fóruns ativos.
- Mantido por desenvolvedores voluntários e empresas parceiras.

### ☁️ 7. Integração com Tecnologias Modernas
- Compatível com **cloud computing**, **microserviços** e **DevOps**.
- Integra-se com ferramentas como **Maven**, **Gradle** e **Jenkins**.
- Suporte crescente a arquiteturas **serverless** e **containerizadas**.

---

## 🧾 Conclusão

O **Apache Tomcat** consolidou-se como um dos servidores Java mais importantes e versáteis do mundo. Sua **arquitetura leve**, **conformidade com padrões oficiais** e **comunidade ativa** o tornaram um pilar no desenvolvimento de aplicações web em Java.

Desde sua criação em **1998**, o Tomcat evoluiu para acompanhar as novas demandas da tecnologia, mantendo-se relevante em contextos acadêmicos, corporativos e na **nuvem**.
É, portanto, um dos servidores mais recomendados para o ensino de desenvolvimento Java e para a execução de aplicações empresariais modernas.

---

## 📚 Referência (ABNT)

APACHE SOFTWARE FOUNDATION. *Apache Tomcat – The Apache Software Foundation*. Disponível em: <https://tomcat.apache.org>. Acesso em: 9 nov. 2025.
