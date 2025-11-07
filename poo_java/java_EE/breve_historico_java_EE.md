---

# 🏛️ O que é Java EE (Java Platform, Enterprise Edition)

## 🔹 Conceito geral

O **Java EE (Java Platform, Enterprise Edition)** é uma **plataforma de desenvolvimento de aplicações corporativas** baseada na linguagem **Java**, criada para resolver problemas de **complexidade, escalabilidade e integração** em sistemas de grande porte.

Ela estende o **Java SE (Standard Edition)**, adicionando um conjunto de **APIs e serviços** que facilitam a criação de aplicações **multicamadas**, **distribuídas** e **orientadas a componentes**, como:

* Sistemas bancários e financeiros
* E-commerces
* Portais de universidades e governos
* Sistemas ERP, CRM, RH e de gestão hospitalar

A grande vantagem é que **o desenvolvedor se concentra na lógica do negócio**, enquanto o **servidor de aplicação Java EE** cuida de aspectos técnicos como:

* Transações
* Segurança
* Conexões com banco de dados
* Balanceamento de carga
* Controle de sessão e ciclo de vida

---

## ⚙️ Estrutura da Plataforma Java EE

A arquitetura Java EE é organizada em **camadas** e **componentes** padronizados, o que permite o desenvolvimento modular e reutilizável.

### 🧩 1. Camada Cliente (Apresentação)

É responsável pela **interação com o usuário**. Pode incluir:

* Aplicações **web** (HTML, JS, CSS)
* Aplicações **desktop (Swing, JavaFX)**
* Aplicações **móveis**
* **Front-ends** JavaScript (React, Angular, Vue) que consomem APIs Java EE

📘 Principais tecnologias:

* **Servlets** → controlam requisições HTTP e respostas
* **JSP (JavaServer Pages)** → geração dinâmica de páginas HTML
* **JSF (JavaServer Faces)** → framework para interfaces web com componentes
* **Jakarta RESTful Web Services (JAX-RS)** → APIs REST modernas para web services

---

### 🧩 2. Camada de Negócios (Business Layer)

Contém as **regras de negócio** — ou seja, a lógica que define como o sistema funciona.
Aqui ficam as classes que processam dados, executam cálculos e interagem com o banco.

📘 Tecnologias principais:

* **EJB (Enterprise JavaBeans)** → componentes que encapsulam a lógica de negócio com suporte automático a:

  * Transações
  * Concorrência
  * Segurança declarativa
  * Escalabilidade
* **CDI (Contexts and Dependency Injection)** → gerencia injeção de dependências e ciclo de vida de objetos, facilitando a integração entre os módulos.

Exemplo de uso:

```java
@Stateless
public class PedidoService {

    public void processarPedido(Pedido p) {
        // validação, cálculo de impostos, atualização de estoque
    }
}
```

---

### 🧩 3. Camada de Persistência (Dados)

Responsável pela **comunicação com o banco de dados**.
O Java EE usa a API **JPA (Jakarta Persistence API)**, que implementa o conceito de **ORM (Object-Relational Mapping)**, permitindo mapear objetos Java em tabelas do banco.

📘 Tecnologias:

* **JPA (Jakarta Persistence API)**
* **JDBC (Java Database Connectivity)** — nível mais baixo, usado para conexões diretas.

Exemplo de entidade JPA:

```java
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
}
```

---

### 🧩 4. Camada de Integração e Comunicação

É usada para **integrar sistemas corporativos diferentes**, trocando mensagens ou serviços entre servidores e aplicações externas.

📘 Principais tecnologias:

* **JMS (Java Message Service)** → comunicação assíncrona por filas e tópicos (mensageria).
* **JAX-WS** → serviços web SOAP.
* **JAX-RS** → serviços web RESTful.
* **JCA (Java Connector Architecture)** → integração com sistemas legados (mainframes, ERPs, etc).

Exemplo de endpoint REST:

```java
@Path("/clientes")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listar() {
        return repositorio.listarTodos();
    }
}
```

---

### 🧩 5. Serviços Transversais (Infraestrutura)

O Java EE fornece **serviços padronizados** que são usados por todas as camadas:

* **JTA (Jakarta Transactions)** → controle de transações distribuídas;
* **Jakarta Security** → autenticação e autorização de usuários;
* **Jakarta Mail** → envio de e-mails corporativos;
* **Jakarta Batch** → processamento em lote (jobs automáticos);
* **Jakarta JSON / Jakarta XML** → manipulação de dados estruturados.

---

## 🧠 Como funciona o Java EE internamente

Quando um desenvolvedor cria uma aplicação Java EE, ele escreve **componentes de software padronizados**, como:

* Servlets
* EJBs
* Entidades JPA
* Recursos REST

Esses componentes são empacotados (geralmente em arquivos `.war` ou `.ear`) e implantados em um **servidor de aplicação Java EE**.
O **contêiner Java EE** gerencia:

* O ciclo de vida de cada componente
* A injeção de dependências
* O pool de conexões com o banco
* A execução de transações
* A segurança declarativa via anotações

Exemplo:

```java
@RolesAllowed("ADMIN")
@Stateless
public class RelatorioService {
    public void gerarRelatorio() {
        // lógica protegida
    }
}
```

O servidor (por exemplo, **WildFly**) automaticamente cuida da autenticação e garante que apenas usuários com o papel “ADMIN” possam executar o método.

---

## 🧩 Principais Servidores de Aplicação Java EE

| Servidor                 | Descrição                                                 | Licença         |
| ------------------------ | --------------------------------------------------------- | --------------- |
| **WildFly (ex-JBoss)**   | Um dos mais usados em empresas e instituições.            | Livre (Red Hat) |
| **GlassFish / Payara**   | Referência oficial da especificação Java EE / Jakarta EE. | Livre           |
| **Apache TomEE**         | Tomcat estendido com APIs Java EE.                        | Livre           |
| **Open Liberty**         | Criado pela IBM, leve e modular.                          | Livre           |
| **WebLogic / WebSphere** | Versões comerciais amplamente usadas em bancos.           | Proprietária    |

---

## 📜 Evolução histórica do Java EE

| Ano       | Nome da Plataforma                    | Mantenedor                   | Observações                                                  |
| --------- | ------------------------------------- | ---------------------------- | ------------------------------------------------------------ |
| **1999**  | **J2EE (Java 2 Enterprise Edition)**  | Sun Microsystems             | Primeira versão, foco em EJBs e Servlets.                    |
| **2006**  | **Java EE (Java Enterprise Edition)** | Oracle (após adquirir a Sun) | Simplificação de EJB, inclusão de JPA e Annotations.         |
| **2017**  | **Jakarta EE**                        | Eclipse Foundation           | Novo nome e governança aberta.                               |
| **2021+** | **Jakarta EE 9 / 10 / 11**            | Eclipse Foundation           | Migração de pacotes `javax.*` → `jakarta.*` e foco em nuvem. |

---

## 🌐 Transição para o Jakarta EE

Com a transferência da plataforma para a Eclipse Foundation, o nome **“Java EE”** foi substituído por **“Jakarta EE”**.
Além da mudança de nome, houve uma **modernização da plataforma**, voltada para:

* Microsserviços;
* Cloud computing (nuvem);
* Contêineres (Docker, Kubernetes);
* Integração com frameworks como **Spring**, **MicroProfile**, **Quarkus** e **Helidon**.

### Exemplo da mudança de pacotes:

| Antes (Java EE 8)     | Agora (Jakarta EE 9+)   |
| --------------------- | ----------------------- |
| `javax.persistence.*` | `jakarta.persistence.*` |
| `javax.ws.rs.*`       | `jakarta.ws.rs.*`       |
| `javax.servlet.*`     | `jakarta.servlet.*`     |

---

## 🏢 Aplicações típicas em Java EE

1. **Sistemas bancários** — controle de contas, transações e auditoria.
2. **Sistemas de e-commerce** — integração com gateways de pagamento e estoque.
3. **Sistemas governamentais** — portais e cadastros de cidadãos.
4. **Sistemas acadêmicos** — gestão de alunos, disciplinas e notas.
5. **Aplicações hospitalares** — controle de pacientes, exames e prescrições.

---

## 💡 Vantagens do Java EE

| Vantagem             | Descrição                                                                                    |
| -------------------- | -------------------------------------------------------------------------------------------- |
| **Padronização**     | Todas as APIs seguem especificações abertas, o que garante compatibilidade entre servidores. |
| **Produtividade**    | O contêiner gerencia transações, segurança e conexões automaticamente.                       |
| **Escalabilidade**   | Suporte nativo a clusterização e balanceamento de carga.                                     |
| **Segurança**        | Controle de autenticação e autorização centralizado.                                         |
| **Portabilidade**    | Uma aplicação Java EE pode ser movida entre servidores sem reescrita de código.              |
| **Integração fácil** | APIs prontas para mensageria, e-mail, web services e bancos de dados.                        |

---

## ⚠️ Desvantagens e desafios

| Desvantagem                              | Descrição                                                                 |
| ---------------------------------------- | ------------------------------------------------------------------------- |
| **Curva de aprendizado alta**            | Requer conhecimento de várias APIs e configuração de servidores.          |
| **Mais pesado**                          | Servidores Java EE tradicionais são robustos, mas consomem mais recursos. |
| **Configuração complexa**                | Ambientes corporativos exigem ajustes finos de rede, JNDI e segurança.    |
| **Concorrência com frameworks modernos** | O Spring Boot, por exemplo, é mais simples e rápido de configurar.        |

---

## 🚀 Futuro do Java EE (Jakarta EE)

O **Jakarta EE** continua evoluindo sob a Eclipse Foundation com foco em:

* **Arquiteturas em nuvem** (Cloud Native);
* **Contêineres leves** e **microsserviços**;
* **Integração com o MicroProfile** para monitoramento, health checks e tolerância a falhas;
* **Compatibilidade com Java 21 e superiores**.

---

## 🧭 Conclusão

> O **Java EE** foi e continua sendo um **pilar fundamental do desenvolvimento corporativo em Java**.
> Ele fornece **padrões, estabilidade e interoperabilidade** que sustentam milhares de sistemas críticos em todo o mundo.
> Com o **Jakarta EE**, a plataforma renasceu mais leve, modular e voltada à **era da computação em nuvem** — mantendo a solidez do Java tradicional com a flexibilidade exigida pelos sistemas modernos.

---
