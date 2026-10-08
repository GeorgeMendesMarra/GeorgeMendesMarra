# 🌐 Programação Web II — Guia da Disciplina (Back-End, Serviços Web e Contêineres)

Um guia completo para quem já domina o front-end e quer dominar o **lado servidor**: WebServices, REST, back-end, GraphQL e Docker.

**Curso:** Análise e Desenvolvimento de Sistemas — ADS · **Instituição:** UniAlfa · **Professor:** George Mendes Marra

---

## 📌 Índice

1. [Ementa](#-ementa)
2. [Objetivos da disciplina](#-objetivos-da-disciplina)
3. [Pré-requisitos](#-pré-requisitos)
4. [Visão geral: do front-end ao back-end](#-visão-geral-do-front-end-ao-back-end)
5. [Mapa dos materiais](#-mapa-dos-materiais)
6. [Frameworks especiais de desenvolvimento para Web](#-frameworks-especiais-de-desenvolvimento-para-web)
7. [WebServices, SaaS, IaaS e PaaS (resumo)](#-webservices-saas-iaas-e-paas-resumo)
8. [REST e sistemas RESTful (resumo)](#-rest-e-sistemas-restful-resumo)
9. [Desenvolvimento de back-end (resumo)](#-desenvolvimento-de-back-end-resumo)
10. [GraphQL (resumo)](#-graphql-resumo)
11. [Docker (resumo)](#-docker-resumo)
12. [Ferramentas do curso](#-ferramentas-do-curso)
13. [Plano de aulas sugerido](#-plano-de-aulas-sugerido)
14. [Avaliação](#-avaliação)
15. [Roteiro de aprendizado](#-roteiro-de-aprendizado)
16. [Bibliografia](#-bibliografia)
17. [Links úteis](#-links-úteis)

---

## 📖 Ementa

**Frameworks especiais de Desenvolvimento para Web. WebServices (SOAP, WSDL, SaaS, IaaS e PaaS). Arquitetura REST e sistemas RESTful. Desenvolvimento de backend. GraphQL. Tecnologia Docker.**

---

## 🎯 Objetivos da disciplina

Ao final da disciplina, o aluno será capaz de:

- 🧩 Explicar o que são **WebServices** e diferenciar **SOAP/WSDL** de **REST**;
- ☁️ Distinguir os modelos de nuvem **SaaS, IaaS e PaaS** e escolher o adequado a cada cenário;
- 🔌 Projetar e implementar **APIs RESTful** com boas práticas (recursos, verbos, status codes, versionamento);
- 🛠️ Desenvolver um **back-end** em camadas, com persistência, validação, segurança básica e testes;
- 🔗 **Consumir** serviços externos e integrar a API ao front-end;
- 🕸️ Criar e consultar um endpoint **GraphQL**;
- 🐳 **Containerizar** a aplicação com **Docker** e orquestrar API + banco com **Docker Compose**.

---

## ✅ Pré-requisitos

- HTML5, CSS3, JavaScript, jQuery e Bootstrap (Programação Web I);
- Lógica de programação e **Java** com orientação a objetos;
- Noções de banco de dados relacional e SQL;
- Uso básico de terminal e **Git**.

---

## 🔄 Visão geral: do front-end ao back-end

Em Programação Web I você construiu o que o usuário **vê**. Agora vamos construir o que acontece **nos bastidores**:

```
┌────────────┐   HTTP/JSON    ┌──────────────┐   JPA/SQL   ┌──────────────┐
│ Front-end  │ ─────────────► │  API (REST / │ ──────────► │  Banco de    │
│ (navegador)│ ◄───────────── │   GraphQL)   │ ◄────────── │  dados       │
└────────────┘                └──────────────┘             └──────────────┘
      │                              │
      └────── tudo empacotado em contêineres Docker (Compose) ──────┘
```

> 💡 **Analogia do restaurante:** o front-end é o salão (cardápio, mesas, garçom); a API é o garçom que leva e traz pedidos em um formato combinado; o back-end é a cozinha; o banco de dados é a despensa; o Docker é a "marmita padronizada" que permite montar a mesma cozinha em qualquer lugar.

---

## 🗺️ Mapa dos materiais

| Arquivo | Conteúdo |
|---|---|
| [`programacao_web_ii.md`](programacao_web_ii.md) | Este guia geral da disciplina |
| [`projeto_programacao_web_II_N1_N2.md`](projeto_programacao_web_II_N1_N2.md) | Projeto integrador com as entregas N1 e N2 |
| [`tutorial_webservices_soap_wsdl.md`](tutorial_webservices_soap_wsdl.md) | WebServices, SOAP, WSDL, SaaS, IaaS e PaaS |
| [`tutorial_rest_restful.md`](tutorial_rest_restful.md) | Arquitetura REST e sistemas RESTful |
| [`tutorial_backend_spring_boot.md`](tutorial_backend_spring_boot.md) | Desenvolvimento de back-end com Java + Spring Boot |
| [`tutorial_graphql.md`](tutorial_graphql.md) | GraphQL (schema, queries, mutations, Spring) |
| [`tutorial_docker.md`](tutorial_docker.md) | Docker, Dockerfile e Docker Compose |

**Ordem de estudo sugerida:** WebServices → REST → Back-end → GraphQL → Docker → Projeto.

---

## 🧰 Frameworks especiais de desenvolvimento para Web

Frameworks fornecem **estrutura, convenções e componentes prontos** para acelerar o desenvolvimento e padronizar o código.

| Ecossistema | Frameworks | Destaque |
|---|---|---|
| **Java** | **Spring Boot**, Quarkus, Micronaut, Jakarta EE, JSF | Referência da disciplina; forte no mercado corporativo |
| **JavaScript/TypeScript** | Express, NestJS, Fastify | Mesma linguagem do front-end |
| **Python** | Django, FastAPI, Flask | Produtividade e integração com dados/IA |
| **PHP** | Laravel, Symfony | Grande base instalada |
| **C#** | ASP.NET Core | Ecossistema Microsoft |

Conceitos comuns: **injeção de dependência**, **ORM**, **roteamento**, **filtros/middlewares**, **convenção sobre configuração**.

> 📌 Os tutoriais usam **Java + Spring Boot** por continuidade com a formação em Java do curso. Outras stacks podem ser usadas no projeto mediante aprovação do professor.

---

## ☁️ WebServices, SaaS, IaaS e PaaS (resumo)

- **WebService:** funcionalidade exposta pela rede para ser consumida por outras aplicações.
- **SOAP:** protocolo baseado em **XML** com envelope (`Envelope`, `Header`, `Body`, `Fault`).
- **WSDL:** **contrato** XML do serviço SOAP (tipos, mensagens, operações, binding e endereço).
- **IaaS:** você aluga infraestrutura (VMs, rede, disco) — ex.: EC2.
- **PaaS:** você envia o código e a plataforma cuida do resto — ex.: Render, Heroku.
- **SaaS:** software pronto pela internet — ex.: Gmail, Trello.

➡️ Detalhes e exercícios em [`tutorial_webservices_soap_wsdl.md`](tutorial_webservices_soap_wsdl.md).

---

## 🔌 REST e sistemas RESTful (resumo)

```
GET    /api/v1/livros        → lista        (200)
GET    /api/v1/livros/42     → detalha      (200 / 404)
POST   /api/v1/livros        → cria         (201 + Location)
PUT    /api/v1/livros/42     → substitui    (200 / 204)
PATCH  /api/v1/livros/42     → altera parte (200)
DELETE /api/v1/livros/42     → remove       (204)
```

Princípios: cliente-servidor, **stateless**, cache, interface uniforme, camadas e código sob demanda (opcional).

➡️ Detalhes em [`tutorial_rest_restful.md`](tutorial_rest_restful.md).

---

## 🛠️ Desenvolvimento de back-end (resumo)

Arquitetura em camadas:

```
Controller  →  Service  →  Repository  →  Banco
 (HTTP)        (regras)     (dados)
```

Tópicos: entidades JPA, DTOs, validação (`@Valid`), tratamento global de erros, relacionamentos, perfis de configuração, consumo de API externa, CORS, JWT, testes (JUnit/Mockito/MockMvc) e documentação (OpenAPI/Swagger).

➡️ Detalhes em [`tutorial_backend_spring_boot.md`](tutorial_backend_spring_boot.md).

---

## 🕸️ GraphQL (resumo)

```graphql
query {
  livro(id: 1) {
    titulo
    autor { nome }
  }
}
```

O cliente **escolhe os campos** e recebe tudo em **uma requisição**, evitando *over-fetching* e *under-fetching*. Operações: `query`, `mutation` e `subscription`.

➡️ Detalhes em [`tutorial_graphql.md`](tutorial_graphql.md).

---

## 🐳 Docker (resumo)

```bash
docker build -t minha-api:1.0 .      # constrói a imagem
docker run -d -p 8080:8080 minha-api:1.0   # executa o contêiner
docker compose up -d --build         # sobe API + banco + front-end
```

Conceitos: imagem, contêiner, Dockerfile, volume, rede, Compose e registries.

➡️ Detalhes em [`tutorial_docker.md`](tutorial_docker.md).

---

## 🧪 Ferramentas do curso

| Finalidade | Ferramentas |
|---|---|
| IDE/Editor | IntelliJ IDEA, Eclipse, VS Code |
| Linguagem/Build | Java 17+, Maven (ou Gradle) |
| Framework | Spring Boot (start.spring.io) |
| Teste de APIs | Postman, Insomnia, curl, SoapUI (SOAP), GraphiQL (GraphQL) |
| Banco de dados | H2 (desenvolvimento), PostgreSQL ou MySQL |
| Contêineres | Docker Desktop / Docker Engine, Docker Compose |
| Versionamento | Git + GitHub/GitLab |
| Documentação | OpenAPI/Swagger (springdoc) |

---

## 🗓️ Plano de aulas sugerido

Carga horária de **60 horas** (referência: 15 encontros de 4 h; ajustar ao calendário real).

| Encontro | Tema | Material |
|---|---|---|
| 1 | Apresentação, revisão de HTTP e da arquitetura web | Este guia |
| 2 | WebServices: conceitos, XML e SOAP | `tutorial_webservices_soap_wsdl.md` |
| 3 | WSDL, consumo de serviços SOAP; SaaS, IaaS e PaaS | `tutorial_webservices_soap_wsdl.md` |
| 4 | Arquitetura REST: princípios, recursos, verbos e status | `tutorial_rest_restful.md` |
| 5 | Projeto de APIs RESTful; Postman e OpenAPI | `tutorial_rest_restful.md` |
| 6 | Back-end I: Spring Boot, camadas, controller e service | `tutorial_backend_spring_boot.md` |
| 7 | Back-end II: JPA, repositórios e relacionamentos | `tutorial_backend_spring_boot.md` |
| 8 | Back-end III: DTOs, validação e tratamento de erros; **entrega N1** | `tutorial_backend_spring_boot.md` |
| 9 | Consumo de APIs externas, CORS e integração com o front-end | `tutorial_backend_spring_boot.md` |
| 10 | Segurança: autenticação e JWT | `tutorial_backend_spring_boot.md` |
| 11 | Testes automatizados e documentação | `tutorial_backend_spring_boot.md` |
| 12 | GraphQL I: schema, queries e mutations | `tutorial_graphql.md` |
| 13 | GraphQL II: Spring for GraphQL e consumo no front-end | `tutorial_graphql.md` |
| 14 | Docker: imagens, contêineres, Dockerfile e volumes | `tutorial_docker.md` |
| 15 | Docker Compose, boas práticas e **entrega N2** | `tutorial_docker.md` |

---

## 📝 Avaliação

| Nota | Composição |
|---|---|
| **N1** | Projeto — 1ª entrega (WebServices + REST + Back-end) |
| **N2** | Projeto — 2ª entrega (GraphQL + Docker + integração) |

Critérios detalhados e datas em [`projeto_programacao_web_II_N1_N2.md`](projeto_programacao_web_II_N1_N2.md).

---

## 🧭 Roteiro de aprendizado

```
1. HTTP e JSON
2. WebServices (SOAP/WSDL) e nuvem (SaaS/IaaS/PaaS)
3. REST e boas práticas de APIs
4. Back-end em camadas (Spring Boot + JPA)
5. Segurança, testes e documentação
6. GraphQL
7. Docker e Compose
8. (Depois) CI/CD, Kubernetes, mensageria, microsserviços
```

### Checklist do desenvolvedor back-end

```
☐ HTTP (verbos, status, cabeçalhos)
☐ JSON e XML
☐ REST (recursos, URIs, idempotência)
☐ SOAP/WSDL (reconhecer e consumir)
☐ Java + Spring Boot
☐ JPA/Hibernate e SQL
☐ Validação e tratamento de erros
☐ Autenticação (JWT) e CORS
☐ Testes (JUnit, Mockito, MockMvc)
☐ OpenAPI/Swagger
☐ GraphQL
☐ Docker e Docker Compose
☐ Git
☐ Variáveis de ambiente e segurança de segredos
```

---

## 📚 Bibliografia

### Bibliografia Básica

1. SILVA, Maurício Samy. **Construindo sites com CSS e (X)HTML: sites controlados por folhas de estilo em cascata**. São Paulo, SP: Novatec, 2008. 446 p.
2. GONÇALVES, Edson. **Desenvolvendo aplicações web com JSP, Servlets, JavaServer Faces, Hibernate, EJB 3 Persistence e Ajax**. Rio de Janeiro (RJ): Ciência Moderna, 2007. 736 p.
3. SILVA, Maurício Samy. **HTML5 e CSS3**. São Paulo, SP: Novatec, 2015. 304 p.

### Bibliografia Complementar

1. JANDL JÚNIOR, Peter. **Desenvolvendo aplicações web com JSP e JSTL**. São Paulo, SP: Novatec, 2009. 316 p.
2. DEITEL, P. J.; DEITEL, H. M. **Java: como programar**. 8. ed. São Paulo (SP): Pearson Prentice Hall, 2010. 1386 p.
3. SANTOS, Rafael. **Introdução à programação orientada a objetos usando Java**. Rio de Janeiro (RJ): Campus, 2003. 319 p.
4. MEYER, Jeanine. **O guia essencial do HTML5: usando jogos para aprender HTML5 e JavaScript**. Rio de Janeiro: Ciência Moderna, 2011. xxi, 385 p.
5. MENDES, Douglas Rocha. **Programação Java: com ênfase em orientação a objetos**. São Paulo, SP: Novatec, c2009. 463 p.

---

## 📎 Links úteis

- **Spring Boot**: [spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) · **Initializr**: [start.spring.io](https://start.spring.io)
- **MDN HTTP**: [developer.mozilla.org/pt-BR/docs/Web/HTTP](https://developer.mozilla.org/pt-BR/docs/Web/HTTP)
- **OpenAPI**: [swagger.io/specification](https://swagger.io/specification/)
- **GraphQL**: [graphql.org](https://graphql.org/learn/)
- **Docker Docs**: [docs.docker.com](https://docs.docker.com)
- **Postman**: [postman.com](https://www.postman.com)
- **OWASP API Security**: [owasp.org/API-Security](https://owasp.org/API-Security/)
- **ViaCEP (API pública para prática)**: [viacep.com.br](https://viacep.com.br)

---

*Centro Universitário Alves Faria — UniAlfa · Curso de ADS · Disciplina de Programação Web II · Professor: George Mendes Marra*
