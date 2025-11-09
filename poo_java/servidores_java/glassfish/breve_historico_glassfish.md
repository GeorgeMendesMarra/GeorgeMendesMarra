# ☕ Histórico e Características do Servidor Java GlassFish

## 📘 Histórico do GlassFish

O **GlassFish** é um **servidor de aplicações Java EE (atualmente Jakarta EE)** de código aberto, originalmente desenvolvido pela **Sun Microsystems** em **2005** como referência oficial para a plataforma **Java Enterprise Edition (Java EE)**.  
Seu principal objetivo sempre foi **implementar integralmente as especificações oficiais** da plataforma corporativa Java, servindo de base para validação e certificação de outros servidores de aplicação comerciais, como o Oracle WebLogic e o IBM WebSphere.

Após a aquisição da **Sun Microsystems pela Oracle Corporation** em **2010**, o projeto GlassFish passou a ser mantido pela Oracle como **GlassFish Server Open Source Edition**, coexistindo com uma versão comercial denominada **Oracle GlassFish Server**.  
Durante esse período, o GlassFish consolidou-se como o **servidor de referência para Java EE 6 e Java EE 7**, trazendo avanços significativos em modularidade, desempenho e compatibilidade com padrões como **EJB, JPA, JAX-RS, JMS e CDI**.

Em **2017**, a Oracle transferiu o projeto para a **Eclipse Foundation**, dentro da iniciativa **Eclipse Enterprise for Java (EE4J)**, sendo renomeado como **Eclipse GlassFish**. A partir de então, o servidor passou a servir como **implementação de referência oficial da Jakarta EE**, continuando o papel de validar cada nova versão da plataforma.  
Atualmente, o GlassFish é mantido pela **Eclipse Foundation** e pela comunidade **Payara**, que também derivou o **Payara Server**, uma versão aprimorada e voltada a produção.  

O GlassFish permanece como um marco na história dos servidores Java, sendo amplamente usado em ambientes acadêmicos e corporativos por sua conformidade total com os padrões **Jakarta EE**, interface administrativa intuitiva e excelente documentação.

---

## ⚙️ Principais Características do Eclipse GlassFish

| Característica | Descrição Detalhada |
|----------------|--------------------|
| **Implementação de Referência Oficial** | É o servidor oficial usado para testar e validar as especificações da **Jakarta EE** (antiga Java EE). |
| **Suporte Completo à Jakarta EE** | Compatível com todos os módulos: **Servlet, JSP, JSF, EJB, JPA, JAX-RS, CDI, JMS, JTA, JSON-P, JSON-B**, entre outros. |
| **Arquitetura Modular (HK2)** | Baseado no framework **HK2 (Hundred-Kilobyte Kernel)**, que oferece modularidade e injeção de dependência leve. |
| **Painel Administrativo Completo** | Interface web intuitiva para gerenciar aplicativos, recursos, segurança e clusters. |
| **CLI (Command Line Interface)** | Ferramenta poderosa para administração remota e automação de tarefas administrativas. |
| **Suporte a Clustering e Load Balancing** | Permite alta disponibilidade, balanceamento de carga e replicação de sessão entre múltiplas instâncias. |
| **Desenvolvimento Simplificado** | Integração com IDEs como **Eclipse**, **NetBeans** e **IntelliJ IDEA**, facilitando o deploy e debug de aplicações. |
| **Compatibilidade com MicroProfile** | Suporte parcial ao **Eclipse MicroProfile**, permitindo o uso de APIs voltadas a microserviços. |
| **Suporte a TLS/SSL e Autenticação Avançada** | Inclui configuração simplificada de certificados, segurança via JAAS e suporte a Single Sign-On (SSO). |
| **Open Source e Multiplataforma** | Distribuído sob licença **CDDL + GPL v2**, compatível com qualquer ambiente Java SE 11+ (Windows, Linux e macOS). |

---

## 🕰️ Linha do Tempo – Versões do GlassFish

| Versão | Ano | Especificações Suportadas | Principais Novidades / Recursos | Status |
|--------|-----:|---------------------------|---------------------------------|---------|
| **GlassFish 1.x** | 2005 | Java EE 5 (inicial) | Lançado pela Sun Microsystems; implementação de referência inicial. | Obsoleta |
| **GlassFish 2.x** | 2007 | Java EE 5 | Introdução de clustering, EJB 3.0 e melhor performance. | Obsoleta |
| **GlassFish 3.x** | 2009 | Java EE 6 | Primeira implementação com **modularidade (HK2)** e suporte a **OSGi**. | Fim de vida (EOL) |
| **GlassFish 4.x** | 2013 | Java EE 7 | Suporte a **WebSocket**, **JSON-P**, **CDI 1.1** e **JAX-RS 2.0**. | Suporte limitado |
| **GlassFish 5.x** | 2017 | Java EE 8 / Jakarta EE 8 | Transição para Eclipse Foundation; suporte a **CDI 2.0**, **Servlet 4.0** e **HTTP/2**. | Manutenção |
| **GlassFish 6.x** | 2020 | Jakarta EE 9 | Migração dos pacotes `javax.*` para `jakarta.*`; compatibilidade total com a nova nomenclatura. | Suporte ativo |
| **GlassFish 7.x** | 2022–2025 | Jakarta EE 10 | Suporte a **Jakarta EE 10**, **Java 17**, e foco em desempenho e compatibilidade cloud-native. | Versão estável atual |

---

## 🧠 Importância do GlassFish

O GlassFish desempenha um papel essencial no **ecossistema Jakarta EE**, sendo o servidor utilizado para **testar a conformidade de outros servidores** e para validar novas APIs Java corporativas.  
Sua **integração com a comunidade Eclipse**, o **apoio ao MicroProfile** e sua **arquitetura modular HK2** o tornam ideal para **pesquisa, ensino e ambientes corporativos que valorizam padrões abertos**.

---

## 📚 Referência (formato ABNT)

ECLIPSE FOUNDATION. *Eclipse GlassFish – Jakarta EE Reference Implementation*. Disponível em: [https://projects.eclipse.org/projects/ee4j.glassfish](https://projects.eclipse.org/projects/ee4j.glassfish). Acesso em: 9 nov. 2025.
