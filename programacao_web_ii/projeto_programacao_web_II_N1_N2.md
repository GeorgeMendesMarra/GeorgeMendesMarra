# CENTRO UNIVERSITÁRIO ALVES FARIA — UNIALFA

**Curso:** Análise e Desenvolvimento de Sistemas — ADS
**Disciplina:** Programação Web II
**Carga Horária:** 60 horas
**Professor:** George Mendes Marra

---

# PROJETO DE DESENVOLVIMENTO WEB — BACK-END, APIs E CONTÊINERES

## 1. Apresentação

Este documento define o **Projeto Integrador** da disciplina de Programação Web II, elaborado com base na ementa: Frameworks especiais de desenvolvimento para Web, WebServices (SOAP, WSDL, SaaS, IaaS e PaaS), Arquitetura REST e sistemas RESTful, Desenvolvimento de backend, GraphQL e tecnologia Docker.

O projeto será desenvolvido **individualmente ou em grupo (definido pelo professor em sala)** e entregue em **duas etapas**. Ele dá continuidade ao projeto de Programação Web I: o front-end construído anteriormente (HTML, CSS, JavaScript, jQuery e Bootstrap) passa a consumir uma **API própria**, desenvolvida pelo aluno.

## 2. Objetivo Geral

Desenvolver um **back-end completo, documentado e containerizado**, expondo uma API RESTful (e, na segunda etapa, um endpoint GraphQL), com persistência em banco de dados, consumida por um front-end web.

## 3. Tema do Projeto

Cada aluno/grupo deverá escolher **um domínio de aplicação** dentre as opções abaixo (ou propor outro, mediante aprovação do professor):

- Sistema de gestão de tarefas/projetos;
- Catálogo de produtos com carrinho de compras (sem pagamento real);
- Sistema de agendamento (clínica, salão, quadra);
- Biblioteca/acervo com empréstimos;
- Blog com autores, posts e comentários;
- Controle financeiro pessoal (receitas e despesas).

Sugestão: reaproveitar o tema do projeto de Programação Web I, para que o front-end já existente passe a consumir a API.

## 4. Requisitos Técnicos Gerais

O projeto deverá obrigatoriamente conter, ao final das duas entregas:

1. **API RESTful** com pelo menos **3 recursos (entidades)** relacionados entre si;
2. **CRUD completo** (GET, POST, PUT/PATCH, DELETE) em pelo menos 2 recursos;
3. Uso correto de **verbos HTTP**, **códigos de status** e URIs orientadas a recursos;
4. **Persistência em banco de dados** (H2 em desenvolvimento; PostgreSQL ou MySQL na entrega final);
5. **Validação de dados** de entrada e **tratamento padronizado de erros** (JSON de erro consistente);
6. **Arquitetura em camadas** (controller, service, repository, model/DTO);
7. **Documentação da API** (OpenAPI/Swagger ou coleção Postman/Insomnia);
8. **Consumo de uma API externa** (ex.: ViaCEP, cotação de moedas, clima) — conceito de WebService/SaaS;
9. **GraphQL** (segunda entrega) para ao menos uma consulta e uma mutation;
10. **Docker**: `Dockerfile` e `docker-compose.yml` para subir API + banco com um único comando;
11. **Controle de versão** com Git, com commits frequentes e descritivos;
12. **README.md** completo e **código comentado e indentado**.

> **Stack de referência:** Java + Spring Boot (Web, Data JPA, Validation). Outras stacks (Node.js/Express, Python/FastAPI, PHP/Laravel, .NET) poderão ser aceitas **mediante aprovação prévia do professor**.

## 5. Estrutura do Projeto (sugestão de organização de pastas)

```
projeto-api/
│
├── src/main/java/br/edu/unialfa/projeto/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── dto/
│   └── exception/
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── docs/
│   └── (coleção Postman, diagramas, prints)
└── README.md
```

## 6. Etapas de Entrega

O projeto será avaliado em **duas entregas**. A primeira concentra-se em **WebServices, REST e back-end**; a segunda acrescenta **GraphQL, Docker e integração com o front-end**.

---

### 6.1 Primeira Entrega (N1) — data a definir pelo professor

**Foco:** API RESTful funcional com persistência e documentação.

**Itens obrigatórios nesta etapa:**

- Modelagem do domínio (diagrama de entidades e relacionamentos);
- Projeto Spring Boot (ou stack aprovada) com arquitetura em camadas;
- **3 recursos** com relacionamentos, e **CRUD completo em pelo menos 2**;
- Validação com Bean Validation e tratamento global de exceções;
- Banco H2 (ou outro) configurado e populado com dados de exemplo;
- Documentação da API (Swagger/OpenAPI ou coleção Postman);
- **Consumo de uma API externa** (WebService de terceiros) por meio de `RestClient`, `RestTemplate` ou `WebClient`;
- **README.md** com:
  * Nome do(s) aluno(s) e tema escolhido;
  * Descrição do projeto e diagrama de entidades;
  * Tabela de endpoints (verbo, URI, descrição, códigos de status);
  * Como executar o projeto localmente.

**Formato de entrega:** link de repositório (GitHub/GitLab) ou arquivo compactado (.zip), conforme orientação do professor.

---

### 6.2 Segunda Entrega (N2) — data a definir pelo professor

**Foco:** GraphQL, contêineres e integração ponta a ponta.

**Itens obrigatórios nesta etapa:**

- **Endpoint GraphQL** com schema próprio, ao menos 2 queries e 1 mutation;
- **Dockerfile** (preferencialmente multi-stage) e **docker-compose.yml** subindo API + banco (PostgreSQL ou MySQL) com volume persistente;
- Configuração por **variáveis de ambiente** (sem senhas fixas no código);
- **Autenticação** simples (ex.: JWT ou Basic Auth) protegendo ao menos um conjunto de endpoints;
- **Integração com o front-end** de Programação Web I (ou novo front) consumindo a API via `fetch`/jQuery AJAX, com CORS configurado;
- Testes automatizados: pelo menos **5 testes** (unitários e/ou de integração);
- Atualização do **README.md**, incluindo:
  * Instruções para subir o ambiente com `docker compose up`;
  * Exemplos de queries/mutations GraphQL;
  * Prints das principais telas e chamadas.

**Formato de entrega:** repositório atualizado (GitHub/GitLab) ou arquivo compactado (.zip) com o projeto completo.

---

## 7. Critérios de Avaliação

| Critério                                                     | Peso |
| ------------------------------------------------------------ | ---- |
| API REST: modelagem, verbos, status codes e boas práticas    | 25%  |
| Back-end: arquitetura em camadas, validação e persistência   | 20%  |
| GraphQL                                                      | 10%  |
| Docker (Dockerfile e Compose funcionando)                    | 15%  |
| Consumo de WebService externo e integração com o front-end   | 10%  |
| Documentação da API e README                                 | 10%  |
| Testes, segurança básica e boas práticas (Git, código limpo) | 10%  |

## 8. Cronograma Resumo

| Etapa           | Conteúdo                                                       | Data de Entrega |
| --------------- | -------------------------------------------------------------- | --------------- |
| 1ª Entrega (N1) | WebServices + REST + Back-end (API com persistência e docs)   | a definir       |
| 2ª Entrega (N2) | GraphQL + Docker + autenticação + integração com o front-end  | a definir       |

## 9. Observações Finais

- O uso de conteúdo copiado de terceiros sem autoria/adaptação própria não será aceito;
- Ferramentas de IA podem ser usadas como apoio, mas o aluno deve **compreender e saber explicar** todo o código entregue (poderá haver arguição);
- **Nunca** versionar senhas, tokens ou chaves de API; use variáveis de ambiente e `.gitignore`;
- Dúvidas técnicas devem ser encaminhadas ao professor durante os encontros ou horário de atendimento;
- Alterações neste documento poderão ser comunicadas em sala pelo professor responsável.

## 10. Bibliografia

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

*Centro Universitário Alves Faria — UniAlfa*
*Curso de Análise e Desenvolvimento de Sistemas — Disciplina de Programação Web II*
*Professor: George Mendes Marra*
