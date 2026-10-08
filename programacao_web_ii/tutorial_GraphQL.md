# Tutorial de GraphQL

> Guia introdutório sobre GraphQL: conceitos, schema, queries, mutations, variáveis, comparação com REST e implementação de um endpoint com Spring Boot.

## Sumário

1. [O que é GraphQL](#1-o-que-é-graphql)
2. [Problemas que o GraphQL resolve](#2-problemas-que-o-graphql-resolve)
3. [Schema e sistema de tipos](#3-schema-e-sistema-de-tipos)
4. [Queries](#4-queries)
5. [Mutations](#5-mutations)
6. [Variáveis, aliases e fragments](#6-variáveis-aliases-e-fragments)
7. [Subscriptions (visão geral)](#7-subscriptions-visão-geral)
8. [Testando com GraphiQL e curl](#8-testando-com-graphiql-e-curl)
9. [GraphQL com Spring Boot](#9-graphql-com-spring-boot)
10. [Consumindo GraphQL no front-end](#10-consumindo-graphql-no-front-end)
11. [GraphQL × REST](#11-graphql--rest)
12. [Erros, segurança e desempenho](#12-erros-segurança-e-desempenho)
13. [Boas práticas](#13-boas-práticas)
14. [Exercícios propostos](#14-exercícios-propostos)

---

## 1. O que é GraphQL

**GraphQL** é uma **linguagem de consulta para APIs** e um *runtime* para executá-las, criada pelo Facebook (2012) e aberta ao público em 2015. Hoje é mantida pela **GraphQL Foundation**.

A ideia central: **o cliente pede exatamente os campos de que precisa**, em uma única requisição, e recebe um JSON com a mesma forma da consulta.

- Normalmente há **um único endpoint** (ex.: `POST /graphql`).
- A API é descrita por um **schema fortemente tipado**.
- Operações: **Query** (ler), **Mutation** (alterar) e **Subscription** (eventos em tempo real).

---

## 2. Problemas que o GraphQL resolve

| Problema em REST | Como o GraphQL ajuda |
|---|---|
| **Over-fetching:** o endpoint devolve campos que a tela não usa | O cliente escolhe os campos |
| **Under-fetching:** são necessárias várias chamadas para montar uma tela | Uma única consulta traz dados relacionados |
| Muitas versões (`v1`, `v2`) | O schema evolui de forma incremental (campos novos e `@deprecated`) |
| Documentação desatualizada | O schema é a documentação (introspecção) |

Exemplo: uma tela precisa do nome do autor e do título de seus livros.

- **REST:** `GET /autores/1` e depois `GET /autores/1/livros` (2 chamadas, campos extras).
- **GraphQL:** 1 chamada pedindo só `nome` e `livros { titulo }`.

---

## 3. Schema e sistema de tipos

O schema é escrito em **SDL** (*Schema Definition Language*).

```graphql
type Autor {
  id: ID!
  nome: String!
  livros: [Livro!]!
}

type Livro {
  id: ID!
  titulo: String!
  anoPublicacao: Int
  autor: Autor!
}

type Query {
  livros: [Livro!]!
  livro(id: ID!): Livro
  autores: [Autor!]!
}

type Mutation {
  criarLivro(input: LivroInput!): Livro!
  removerLivro(id: ID!): Boolean!
}

input LivroInput {
  titulo: String!
  anoPublicacao: Int
  autorId: ID!
}
```

### Tipos escalares nativos

`Int`, `Float`, `String`, `Boolean`, `ID`.

### Modificadores

| Notação | Significado |
|---|---|
| `String` | Opcional (pode ser `null`) |
| `String!` | Obrigatório (não nulo) |
| `[String]` | Lista (itens e lista podem ser nulos) |
| `[String!]!` | Lista obrigatória de itens obrigatórios |

Outros recursos: `enum`, `interface`, `union`, `input` (tipos de entrada) e escalares customizados (ex.: `Date`).

---

## 4. Queries

### Consulta simples

```graphql
query {
  livros {
    id
    titulo
  }
}
```

Resposta:

```json
{
  "data": {
    "livros": [
      { "id": "1", "titulo": "Dom Casmurro" },
      { "id": "2", "titulo": "Memórias Póstumas" }
    ]
  }
}
```

### Consulta com argumento e dados relacionados

```graphql
query {
  livro(id: 1) {
    titulo
    anoPublicacao
    autor {
      nome
    }
  }
}
```

A resposta tem **o mesmo formato** da consulta — nada além do pedido.

---

## 5. Mutations

```graphql
mutation {
  criarLivro(input: { titulo: "Quincas Borba", anoPublicacao: 1891, autorId: 1 }) {
    id
    titulo
  }
}
```

```graphql
mutation {
  removerLivro(id: 2)
}
```

> Convenção: queries **não** devem causar efeitos colaterais; toda alteração de estado é feita por mutations.

---

## 6. Variáveis, aliases e fragments

### Variáveis (evitam concatenar strings)

```graphql
query BuscarLivro($id: ID!) {
  livro(id: $id) {
    titulo
  }
}
```

Variáveis enviadas à parte: `{ "id": 1 }`.

### Aliases (renomear campos na resposta)

```graphql
query {
  primeiro: livro(id: 1) { titulo }
  segundo:  livro(id: 2) { titulo }
}
```

### Fragments (reaproveitar seleção de campos)

```graphql
fragment DadosLivro on Livro {
  id
  titulo
  anoPublicacao
}

query {
  livros { ...DadosLivro }
}
```

### Diretivas

```graphql
query ($comAutor: Boolean!) {
  livros {
    titulo
    autor @include(if: $comAutor) { nome }
  }
}
```

---

## 7. Subscriptions (visão geral)

Permitem receber **atualizações em tempo real** (via WebSocket ou SSE):

```graphql
subscription {
  livroCriado {
    id
    titulo
  }
}
```

Úteis para chats, notificações e painéis ao vivo. Na disciplina, o foco é em **queries e mutations**.

---

## 8. Testando com GraphiQL e curl

- **GraphiQL** (interface web no navegador), **Apollo Sandbox**, **Postman** e **Insomnia** suportam GraphQL.
- Com `curl`:

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query": "{ livros { id titulo } }"}'
```

- Com variáveis:

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"query($id: ID!){ livro(id:$id){ titulo } }","variables":{"id":1}}'
```

---

## 9. GraphQL com Spring Boot

### 9.1 Dependências

Inclua **Spring for GraphQL** (`spring-boot-starter-graphql`) e **Spring Web** no projeto (Spring Initializr).

### 9.2 Habilitar o GraphiQL e definir o schema

`application.properties`:

```properties
spring.graphql.graphiql.enabled=true
spring.graphql.schema.printer.enabled=true
```

O schema fica em `src/main/resources/graphql/schema.graphqls` (use o schema da seção 3).

### 9.3 Controller GraphQL

```java
@Controller
public class LivroGraphQLController {

    private final LivroRepository livros;
    private final AutorRepository autores;

    public LivroGraphQLController(LivroRepository livros, AutorRepository autores) {
        this.livros = livros;
        this.autores = autores;
    }

    @QueryMapping
    public List<Livro> livros() {
        return livros.findAll();
    }

    @QueryMapping
    public Livro livro(@Argument Long id) {
        return livros.findById(id).orElse(null);
    }

    @MutationMapping
    public Livro criarLivro(@Argument LivroInput input) {
        Autor autor = autores.findById(input.autorId()).orElseThrow();
        Livro livro = new Livro();
        livro.setTitulo(input.titulo());
        livro.setAnoPublicacao(input.anoPublicacao());
        livro.setAutor(autor);
        return livros.save(livro);
    }

    // resolve o campo "autor" de Livro apenas quando solicitado
    @SchemaMapping(typeName = "Livro", field = "autor")
    public Autor autor(Livro livro) {
        return livro.getAutor();
    }
}

public record LivroInput(String titulo, Integer anoPublicacao, Long autorId) {}
```

Anotações principais:

| Anotação | Função |
|---|---|
| `@QueryMapping` | Resolve um campo de `Query` |
| `@MutationMapping` | Resolve um campo de `Mutation` |
| `@SchemaMapping` | Resolve um campo de um tipo (relacionamentos) |
| `@Argument` | Recebe argumentos da operação |

Com a aplicação em execução, acesse `http://localhost:8080/graphiql`.

> O nome do método deve coincidir com o campo do schema. Consulte a documentação do Spring for GraphQL para ajustes de versão.

### 9.4 O problema N+1 e `@BatchMapping`

Ao listar 100 livros e pedir o autor de cada um, podem ocorrer 100 consultas extras. Use `@BatchMapping` (ou DataLoader) para carregar os autores **em lote**.

---

## 10. Consumindo GraphQL no front-end

```javascript
async function buscarLivros() {
  const query = `
    query {
      livros {
        id
        titulo
        autor { nome }
      }
    }`;

  const resposta = await fetch("http://localhost:8080/graphql", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ query })
  });

  const { data, errors } = await resposta.json();
  if (errors) throw new Error(errors[0].message);
  return data.livros;
}
```

Com jQuery:

```javascript
$.ajax({
  url: "http://localhost:8080/graphql",
  method: "POST",
  contentType: "application/json",
  data: JSON.stringify({ query: "{ livros { id titulo } }" })
}).done(r => r.data.livros.forEach(l => $("#lista").append("<li>" + l.titulo + "</li>")));
```

Bibliotecas para projetos maiores: **Apollo Client**, **urql**, **Relay**.

---

## 11. GraphQL × REST

| Critério | REST | GraphQL |
|---|---|---|
| Endpoints | Vários (um por recurso) | Normalmente um (`/graphql`) |
| Formato da resposta | Definido pelo servidor | Definido pelo cliente |
| Over/under-fetching | Comum | Evitado |
| Tipagem/contrato | OpenAPI (opcional) | Schema obrigatório |
| Cache HTTP | Simples e nativo | Mais complexo (requer estratégia) |
| Status HTTP | Semântico (404, 201...) | Em geral `200` com campo `errors` |
| Upload de arquivos | Direto | Requer extensão/convenção |
| Curva de aprendizado | Menor | Maior |
| Bom para | CRUD simples, APIs públicas | Front-ends complexos, mobile, múltiplos clientes |

> **Não é "um substitui o outro":** muitos sistemas usam ambos. REST costuma ser mais simples para APIs públicas e CRUD; GraphQL brilha quando vários clientes precisam de **formatos de dados diferentes**.

---

## 12. Erros, segurança e desempenho

### Erros

```json
{
  "data": { "livro": null },
  "errors": [
    { "message": "Livro não encontrado", "path": ["livro"] }
  ]
}
```

Podem existir **dados parciais** junto com erros. O cliente deve sempre verificar `errors`.

### Segurança

- Autenticação por **JWT/Bearer** no cabeçalho, como em REST.
- Autorização **por campo/operação** (ex.: `@PreAuthorize`).
- Limite de **profundidade** e de **complexidade** das consultas (evita consultas abusivas).
- Desative a **introspecção** e o GraphiQL em produção, se necessário.
- Valide entradas e use variáveis (evita injeção).

### Desempenho

- Resolva o **N+1** com DataLoader/`@BatchMapping`.
- Paginação (ex.: padrão *connections* do Relay ou `limit/offset`).
- Cache em camadas (aplicação, CDN para consultas persistidas).

---

## 13. Boas práticas

- Pense o schema a partir das **necessidades dos clientes**, não das tabelas.
- Use `input` types nas mutations e retorne o objeto alterado.
- Nomes claros: `criarLivro`, `atualizarLivro`, `removerLivro`.
- Evolua o schema **sem quebrar clientes**: adicione campos e marque antigos com `@deprecated`.
- Documente tipos e campos com descrições (`"""..."""`).
- Mantenha as regras de negócio no *service*, reutilizado pelo REST e pelo GraphQL.

---

## 14. Exercícios propostos

1. Escreva o schema SDL para `Aluno`, `Curso` e `Matricula`.
2. Escreva uma query que traga o nome de um aluno e os nomes dos cursos em que está matriculado.
3. Escreva uma mutation para matricular um aluno em um curso, usando `input` e variáveis.
4. Implemente o schema do projeto no Spring Boot e teste no GraphiQL.
5. Reescreva a tela de listagem do front-end para consumir GraphQL por `fetch`.
6. Compare, em uma tabela, o número de chamadas necessárias para montar a mesma tela em REST e em GraphQL.
7. Explique com suas palavras o problema N+1 e como resolvê-lo.

---

## Referências

- GraphQL: [graphql.org](https://graphql.org/learn/)
- Spring for GraphQL: [spring.io/projects/spring-graphql](https://spring.io/projects/spring-graphql)
- Apollo GraphQL: [apollographql.com](https://www.apollographql.com)
- GraphQL Specification: [spec.graphql.org](https://spec.graphql.org)
