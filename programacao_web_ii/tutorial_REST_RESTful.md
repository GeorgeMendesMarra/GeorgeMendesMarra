# Tutorial de Arquitetura REST e Sistemas RESTful

> Guia introdutório sobre REST: princípios, recursos, verbos HTTP, códigos de status, boas práticas de projeto de APIs, versionamento, paginação, HATEOAS e documentação com OpenAPI.

## Sumário

1. [O que é REST](#1-o-que-é-rest)
2. [Fundamentos de HTTP](#2-fundamentos-de-http)
3. [Os 6 princípios (restrições) do REST](#3-os-6-princípios-restrições-do-rest)
4. [Recursos e URIs](#4-recursos-e-uris)
5. [Verbos HTTP](#5-verbos-http)
6. [Códigos de status](#6-códigos-de-status)
7. [Representações: JSON](#7-representações-json)
8. [Projetando uma API passo a passo](#8-projetando-uma-api-passo-a-passo)
9. [Paginação, filtros e ordenação](#9-paginação-filtros-e-ordenação)
10. [Versionamento](#10-versionamento)
11. [Tratamento de erros](#11-tratamento-de-erros)
12. [Maturidade de Richardson e HATEOAS](#12-maturidade-de-richardson-e-hateoas)
13. [Segurança](#13-segurança)
14. [Documentação com OpenAPI/Swagger](#14-documentação-com-openapiswagger)
15. [Consumindo APIs REST](#15-consumindo-apis-rest)
16. [Boas práticas](#16-boas-práticas)
17. [Exercícios propostos](#17-exercícios-propostos)

---

## 1. O que é REST

**REST** (*Representational State Transfer*) é um **estilo arquitetural** proposto por Roy Fielding em sua tese de doutorado (2000). Não é um protocolo nem um padrão: é um conjunto de restrições que, quando seguidas, produzem sistemas escaláveis e simples de evoluir.

Um sistema que respeita essas restrições é chamado de **RESTful**. A API expõe **recursos** (alunos, produtos, pedidos) e o cliente os manipula por meio de **representações** (geralmente JSON) usando os **verbos do HTTP**.

---

## 2. Fundamentos de HTTP

### Anatomia de uma requisição

```
POST /api/alunos HTTP/1.1
Host: exemplo.com
Content-Type: application/json
Authorization: Bearer eyJhbGciOi...

{"nome": "Maria", "curso": "ADS"}
```

### Anatomia de uma resposta

```
HTTP/1.1 201 Created
Content-Type: application/json
Location: /api/alunos/42

{"id": 42, "nome": "Maria", "curso": "ADS"}
```

| Parte | Função |
|---|---|
| Linha inicial | Verbo + URI (requisição) ou status (resposta) |
| Cabeçalhos | Metadados (`Content-Type`, `Authorization`, `Accept`, `Location`) |
| Corpo | Dados (opcional) |

---

## 3. Os 6 princípios (restrições) do REST

| # | Princípio | Significado |
|---|---|---|
| 1 | **Cliente-servidor** | Separação de responsabilidades: interface × armazenamento |
| 2 | **Sem estado (stateless)** | Cada requisição contém tudo o que o servidor precisa; não há sessão no servidor |
| 3 | **Cache** | Respostas indicam se podem ser armazenadas (`Cache-Control`, `ETag`) |
| 4 | **Interface uniforme** | Recursos identificados por URI, manipulados por representações e verbos padronizados |
| 5 | **Sistema em camadas** | Proxies, gateways e balanceadores podem existir sem o cliente saber |
| 6 | **Código sob demanda** (opcional) | O servidor pode enviar código executável (ex.: JavaScript) |

> **Stateless na prática:** a autenticação vai em **toda** requisição (token), em vez de depender de uma sessão guardada no servidor. Isso facilita escalar horizontalmente (várias instâncias, balanceador de carga e contêineres).

---

## 4. Recursos e URIs

Um **recurso** é qualquer coisa que o sistema expõe. A URI identifica o recurso; o verbo indica a ação.

### Regras de ouro

- Use **substantivos**, no **plural**: `/alunos`, `/pedidos`.
- Use **minúsculas** e **hífen** para separar palavras: `/ordens-de-servico`.
- Hierarquia expressa relacionamento: `/alunos/42/matriculas`.
- **Não** coloque verbos na URI.
- **Não** exponha detalhes de implementação (`.php`, `.jsp`).

| Ruim | Bom |
|---|---|
| `GET /buscarAlunos` | `GET /alunos` |
| `POST /criarAluno` | `POST /alunos` |
| `GET /aluno?id=42` | `GET /alunos/42` |
| `POST /alunos/42/deletar` | `DELETE /alunos/42` |

---

## 5. Verbos HTTP

| Verbo | Ação | Seguro* | Idempotente** | Corpo |
|---|---|---|---|---|
| `GET` | Ler/consultar | Sim | Sim | Não |
| `POST` | Criar | Não | Não | Sim |
| `PUT` | Substituir por completo | Não | Sim | Sim |
| `PATCH` | Alterar parcialmente | Não | Não (em geral) | Sim |
| `DELETE` | Remover | Não | Sim | Não |
| `HEAD` | Só cabeçalhos | Sim | Sim | Não |
| `OPTIONS` | Métodos permitidos (CORS) | Sim | Sim | Não |

\* **Seguro:** não altera o estado do servidor.
\*\* **Idempotente:** repetir a mesma requisição produz o mesmo resultado final.

### CRUD × HTTP para o recurso `alunos`

| Operação | Requisição | Sucesso |
|---|---|---|
| Listar | `GET /alunos` | `200 OK` |
| Buscar um | `GET /alunos/42` | `200 OK` |
| Criar | `POST /alunos` | `201 Created` + `Location` |
| Atualizar (total) | `PUT /alunos/42` | `200 OK` ou `204 No Content` |
| Atualizar (parcial) | `PATCH /alunos/42` | `200 OK` |
| Remover | `DELETE /alunos/42` | `204 No Content` |

---

## 6. Códigos de status

| Faixa | Significado | Principais |
|---|---|---|
| **1xx** | Informativo | — |
| **2xx** | Sucesso | `200 OK`, `201 Created`, `202 Accepted`, `204 No Content` |
| **3xx** | Redirecionamento | `301 Moved Permanently`, `304 Not Modified` |
| **4xx** | Erro do cliente | `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `405 Method Not Allowed`, `409 Conflict`, `415 Unsupported Media Type`, `422 Unprocessable Entity`, `429 Too Many Requests` |
| **5xx** | Erro do servidor | `500 Internal Server Error`, `502 Bad Gateway`, `503 Service Unavailable`, `504 Gateway Timeout` |

**Dúvidas frequentes**

- `401` = "não sei quem você é" (não autenticado); `403` = "sei quem você é, mas não pode" (sem permissão).
- `400` = requisição malformada; `422` = bem formada, mas com dados inválidos (regras de negócio/validação).
- `409` = conflito de estado (ex.: e-mail já cadastrado).

---

## 7. Representações: JSON

```json
{
  "id": 42,
  "nome": "Maria Silva",
  "email": "maria@exemplo.com",
  "curso": "ADS",
  "ativo": true,
  "matriculas": [
    { "disciplina": "Programação Web II", "semestre": "2026/2" }
  ]
}
```

- Negociação de conteúdo: o cliente envia `Accept: application/json`; o servidor responde com `Content-Type: application/json`.
- Padronize nomes (`camelCase` ou `snake_case`) e **mantenha o padrão** em toda a API.
- Datas em **ISO 8601**: `2026-10-08T14:30:00Z`.

---

## 8. Projetando uma API passo a passo

**Cenário:** sistema de biblioteca.

1. **Identificar recursos:** `livros`, `autores`, `emprestimos`, `usuarios`.
2. **Definir relacionamentos:** um autor tem vários livros; um usuário tem vários empréstimos.
3. **Mapear URIs e verbos:**

| Método | URI | Descrição |
|---|---|---|
| GET | `/livros` | Lista livros |
| GET | `/livros/{id}` | Detalha um livro |
| POST | `/livros` | Cadastra livro |
| PUT | `/livros/{id}` | Atualiza livro |
| DELETE | `/livros/{id}` | Remove livro |
| GET | `/autores/{id}/livros` | Livros de um autor |
| POST | `/emprestimos` | Registra empréstimo |
| POST | `/emprestimos/{id}/devolucao` | Registra devolução (ação de negócio como sub-recurso) |

4. **Definir contratos JSON** de entrada e saída (DTOs).
5. **Definir erros e status** para cada operação.
6. **Documentar** (OpenAPI) e **testar** (Postman/Insomnia).

---

## 9. Paginação, filtros e ordenação

```
GET /livros?page=0&size=20&sort=titulo,asc
GET /livros?autor=Machado&anoMin=1900
GET /livros?q=dom+casmurro
```

Resposta paginada típica:

```json
{
  "content": [ { "id": 1, "titulo": "Dom Casmurro" } ],
  "page": 0,
  "size": 20,
  "totalElements": 134,
  "totalPages": 7
}
```

> Nunca devolva listas ilimitadas: impacta desempenho e segurança.

---

## 10. Versionamento

| Estratégia | Exemplo | Observação |
|---|---|---|
| **URI** | `/api/v1/alunos` | Mais simples e comum |
| **Cabeçalho** | `Accept: application/vnd.exemplo.v2+json` | Mais "puro", menos visível |
| **Parâmetro** | `/alunos?version=2` | Pouco recomendado |

Regra: mudanças **compatíveis** (adicionar campo opcional) não exigem nova versão; mudanças **incompatíveis** (remover/renomear campo) exigem.

---

## 11. Tratamento de erros

Padronize o formato de erro em toda a API. Sugestão baseada na RFC 9457 (*Problem Details*):

```json
{
  "type": "https://exemplo.com/erros/validacao",
  "title": "Dados inválidos",
  "status": 422,
  "detail": "O campo 'email' é obrigatório.",
  "instance": "/api/v1/alunos",
  "errors": [
    { "campo": "email", "mensagem": "não deve estar em branco" }
  ]
}
```

- Nunca exponha *stack trace* ou detalhes internos ao cliente.
- Use mensagens claras e códigos de status coerentes.

---

## 12. Maturidade de Richardson e HATEOAS

| Nível | Descrição |
|---|---|
| **0** | Um único endpoint, tudo via `POST` (estilo RPC) |
| **1** | Recursos com URIs próprias |
| **2** | Uso correto de **verbos HTTP** e **status codes** |
| **3** | **HATEOAS**: respostas trazem links para as próximas ações |

Exemplo nível 3:

```json
{
  "id": 42,
  "nome": "Maria",
  "_links": {
    "self":       { "href": "/alunos/42" },
    "matriculas": { "href": "/alunos/42/matriculas" },
    "excluir":    { "href": "/alunos/42", "method": "DELETE" }
  }
}
```

> A maioria das APIs "RESTful" de mercado fica no **nível 2**, o que já é muito bom.

---

## 13. Segurança

- **HTTPS** obrigatório.
- **Autenticação:** Basic Auth (apenas para estudo), **API Key**, **JWT (Bearer)**, **OAuth 2.0/OIDC**.
- **Autorização:** papéis (RBAC) e checagem de propriedade do recurso (evita acessar dados de outro usuário).
- **CORS:** libere somente as origens necessárias.
- **Validação** de entrada e **limite de requisições** (*rate limiting*).
- **Nunca** coloque segredos na URL; use cabeçalhos.
- Consulte a lista **OWASP API Security Top 10**.

---

## 14. Documentação com OpenAPI/Swagger

A **OpenAPI Specification** descreve a API em YAML/JSON e permite gerar documentação interativa e clientes.

```yaml
openapi: 3.0.3
info:
  title: API da Biblioteca
  version: 1.0.0
paths:
  /livros/{id}:
    get:
      summary: Busca um livro pelo ID
      parameters:
        - name: id
          in: path
          required: true
          schema: { type: integer }
      responses:
        "200":
          description: Livro encontrado
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Livro"
        "404":
          description: Livro não encontrado
components:
  schemas:
    Livro:
      type: object
      properties:
        id:     { type: integer }
        titulo: { type: string }
```

No Spring Boot, a biblioteca **springdoc-openapi** gera a documentação automaticamente (interface em `/swagger-ui.html`).

---

## 15. Consumindo APIs REST

### JavaScript (`fetch`)

```javascript
async function listarLivros() {
  const resposta = await fetch("http://localhost:8080/api/v1/livros");
  if (!resposta.ok) throw new Error("Erro " + resposta.status);
  return resposta.json();
}

async function criarLivro(livro) {
  const resposta = await fetch("http://localhost:8080/api/v1/livros", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(livro)
  });
  return resposta.json();
}
```

### jQuery

```javascript
$.ajax({
  url: "http://localhost:8080/api/v1/livros",
  method: "GET",
  dataType: "json"
}).done(function (livros) {
  livros.forEach(l => $("#lista").append("<li>" + l.titulo + "</li>"));
});
```

### `curl`

```bash
curl -i http://localhost:8080/api/v1/livros/1
curl -i -X POST http://localhost:8080/api/v1/livros \
     -H "Content-Type: application/json" \
     -d '{"titulo":"Dom Casmurro","autor":"Machado de Assis"}'
```

### Exemplo com API pública (ViaCEP)

```javascript
fetch("https://viacep.com.br/ws/74000000/json/")
  .then(r => r.json())
  .then(dados => console.log(dados.logradouro, dados.localidade));
```

---

## 16. Boas práticas

- Substantivos no plural nas URIs; verbos HTTP para as ações.
- Status codes corretos e **formato de erro padronizado**.
- Paginação, filtro e ordenação em listagens.
- Versionamento desde o primeiro dia (`/v1`).
- **DTOs** em vez de expor entidades do banco.
- Idempotência em `PUT`/`DELETE`; considere chave de idempotência em `POST` sensíveis (pagamentos).
- Documentação sempre atualizada e exemplos de uso.
- Testes automatizados de contrato e de integração.

---

## 17. Exercícios propostos

1. Projete as URIs e verbos de uma API de **tarefas** (`tarefas`, `categorias`, `usuarios`).
2. Corrija: `GET /getAlunos`, `POST /alunos/delete/42`, `PUT /aluno`.
3. Para cada situação, escolha o status: criação bem-sucedida; recurso inexistente; e-mail duplicado; token ausente; usuário sem permissão; erro inesperado no servidor.
4. Escreva em OpenAPI (YAML) dois endpoints de um recurso à sua escolha.
5. Use `curl` ou Postman para consumir a API ViaCEP e outra API pública à sua escolha.
6. Explique, com suas palavras, por que REST é *stateless* e qual a vantagem ao usar contêineres.
7. Evolua o exercício 1 adicionando paginação e um exemplo de resposta de erro padronizada.

---

## Referências

- Roy Fielding — *Architectural Styles and the Design of Network-based Software Architectures* (2000)
- MDN — HTTP: [developer.mozilla.org/pt-BR/docs/Web/HTTP](https://developer.mozilla.org/pt-BR/docs/Web/HTTP)
- OpenAPI: [swagger.io/specification](https://swagger.io/specification/)
- OWASP API Security: [owasp.org/API-Security](https://owasp.org/API-Security/)
- Postman: [postman.com](https://www.postman.com) · Insomnia: [insomnia.rest](https://insomnia.rest)
