# Tutorial de Desenvolvimento de Back-End (Java + Spring Boot)

> Guia introdutório para construir o lado servidor de uma aplicação web: arquitetura em camadas, API REST, persistência com JPA, validação, tratamento de erros, segurança básica, testes e consumo de APIs externas. Stack de referência: **Java 17+ e Spring Boot 3**.

## Sumário

1. [O que é back-end](#1-o-que-é-back-end)
2. [Frameworks especiais para Web (visão geral)](#2-frameworks-especiais-para-web-visão-geral)
3. [Criando o projeto](#3-criando-o-projeto)
4. [Arquitetura em camadas](#4-arquitetura-em-camadas)
5. [Entidade, repositório, service e controller](#5-entidade-repositório-service-e-controller)
6. [DTOs e validação](#6-dtos-e-validação)
7. [Tratamento global de erros](#7-tratamento-global-de-erros)
8. [Relacionamentos com JPA](#8-relacionamentos-com-jpa)
9. [Configuração por ambiente e banco de dados](#9-configuração-por-ambiente-e-banco-de-dados)
10. [Consumindo uma API externa](#10-consumindo-uma-api-externa)
11. [CORS e integração com o front-end](#11-cors-e-integração-com-o-front-end)
12. [Segurança básica (JWT)](#12-segurança-básica-jwt)
13. [Testes automatizados](#13-testes-automatizados)
14. [Documentação com springdoc-openapi](#14-documentação-com-springdoc-openapi)
15. [Boas práticas](#15-boas-práticas)
16. [Exercícios propostos](#16-exercícios-propostos)

---

## 1. O que é back-end

O **back-end** é a parte da aplicação que roda no servidor: recebe requisições, aplica **regras de negócio**, acessa o **banco de dados** e devolve respostas ao front-end.

```
Navegador (front-end)  --HTTP/JSON-->  API (back-end)  --JPA/SQL-->  Banco de dados
```

Responsabilidades: autenticação e autorização, validação, persistência, integração com outros sistemas, regras de negócio, desempenho e segurança.

---

## 2. Frameworks especiais para Web (visão geral)

Um **framework** fornece estrutura, convenções e componentes prontos, para que o desenvolvedor foque nas regras do negócio.

| Ecossistema | Frameworks | Observação |
|---|---|---|
| **Java** | **Spring Boot**, Jakarta EE/Quarkus, Micronaut, JavaServer Faces | Spring Boot é o mais usado no mercado |
| **JavaScript/TypeScript** | Express, NestJS, Fastify | Mesma linguagem do front-end |
| **Python** | Django, FastAPI, Flask | Muito usado com dados e IA |
| **PHP** | Laravel, Symfony | Grande base instalada na web |
| **C#** | ASP.NET Core | Ecossistema Microsoft |
| **Ruby** | Ruby on Rails | Pioneiro em "convenção sobre configuração" |

Ideias comuns: **injeção de dependência**, **ORM**, **roteamento**, **middlewares/filtros**, **convenção sobre configuração**.

> Nesta disciplina usamos **Spring Boot** como referência por ser coerente com a formação em Java do curso (veja os materiais anteriores sobre JSP, Servlets e JSF: o Spring Boot é a evolução moderna dessa linha).

---

## 3. Criando o projeto

1. Acesse **[start.spring.io](https://start.spring.io)**.
2. Escolha: Maven, Java 17 (ou superior), Spring Boot estável mais recente.
3. Dependências: **Spring Web**, **Spring Data JPA**, **Validation**, **H2 Database**, **PostgreSQL Driver**, **Lombok** (opcional).
4. Gere, extraia e abra na IDE (IntelliJ, Eclipse, VS Code).

Classe principal:

```java
@SpringBootApplication
public class BibliotecaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }
}
```

Executar: `./mvnw spring-boot:run` — a API sobe em `http://localhost:8080`.

---

## 4. Arquitetura em camadas

```
Controller  →  Service  →  Repository  →  Banco
   (HTTP)      (regras)     (acesso a dados)
```

| Camada | Responsabilidade | Anotação |
|---|---|---|
| **Controller** | Receber requisição, devolver resposta HTTP | `@RestController` |
| **Service** | Regras de negócio e transações | `@Service` |
| **Repository** | Consultas e persistência | `@Repository` / `JpaRepository` |
| **Model (Entity)** | Representa a tabela | `@Entity` |
| **DTO** | Contrato de entrada/saída da API | `record` / classe simples |

Regra: o controller **não** acessa o repository diretamente; o fluxo é sempre de cima para baixo.

---

## 5. Entidade, repositório, service e controller

### Entidade

```java
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    private Integer anoPublicacao;

    // construtores, getters e setters
}
```

### Repositório

```java
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
```

O Spring Data cria a implementação automaticamente a partir do nome do método.

### Service

```java
@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) { // injeção por construtor
        this.repository = repository;
    }

    public List<Livro> listar() { return repository.findAll(); }

    public Livro buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro " + id + " não encontrado"));
    }

    public Livro salvar(Livro livro) { return repository.save(livro); }

    public void remover(Long id) {
        repository.delete(buscar(id));
    }
}
```

### Controller

```java
@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) { this.service = service; }

    @GetMapping
    public List<Livro> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        Livro salvo = service.salvar(livro);
        URI local = URI.create("/api/v1/livros/" + salvo.getId());
        return ResponseEntity.created(local).body(salvo); // 201 + Location
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) { service.remover(id); }
}
```

---

## 6. DTOs e validação

**Não exponha entidades diretamente:** use DTOs para controlar o que entra e o que sai.

```java
public record LivroRequest(
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 150)
    String titulo,

    @Min(value = 1000, message = "Ano inválido")
    @Max(2100)
    Integer anoPublicacao
) {}

public record LivroResponse(Long id, String titulo, Integer anoPublicacao) {
    public static LivroResponse de(Livro l) {
        return new LivroResponse(l.getId(), l.getTitulo(), l.getAnoPublicacao());
    }
}
```

No controller, ative a validação com `@Valid`:

```java
@PostMapping
public ResponseEntity<LivroResponse> criar(@Valid @RequestBody LivroRequest req) { ... }
```

Anotações comuns: `@NotNull`, `@NotBlank`, `@Email`, `@Size`, `@Min`, `@Max`, `@Pattern`, `@Past`, `@Positive`.

---

## 7. Tratamento global de erros

```java
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String msg) { super(msg); }
}

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> naoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("status", 404, "mensagem", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacao(MethodArgumentNotValidException ex) {
        List<Map<String, String>> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> Map.of("campo", e.getField(), "mensagem", e.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Map.of("status", 422, "erros", erros));
    }
}
```

Assim todas as respostas de erro seguem **um formato único**, sem expor *stack traces*.

---

## 8. Relacionamentos com JPA

```java
@Entity
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();
}

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
```

| Anotação | Relação |
|---|---|
| `@OneToOne` | 1 para 1 |
| `@OneToMany` / `@ManyToOne` | 1 para N / N para 1 |
| `@ManyToMany` | N para N (tabela de junção) |

Atenção: use DTOs para evitar **loop infinito** na serialização JSON de relacionamentos bidirecionais e o problema **N+1** (consultas excessivas) — resolva com `JOIN FETCH` ou `@EntityGraph`.

---

## 9. Configuração por ambiente e banco de dados

`src/main/resources/application.properties` (desenvolvimento, H2):

```properties
spring.datasource.url=jdbc:h2:mem:biblioteca
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

`application-prod.properties` (PostgreSQL, com **variáveis de ambiente**):

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
```

Ativar o perfil: `SPRING_PROFILES_ACTIVE=prod`. Em produção, prefira **migrações versionadas** (Flyway ou Liquibase) em vez de `ddl-auto=update`.

---

## 10. Consumindo uma API externa

Exemplo com `RestClient` (Spring 6.1+) consultando o ViaCEP:

```java
public record EnderecoViaCep(String cep, String logradouro, String bairro,
                             String localidade, String uf) {}

@Service
public class CepService {

    private final RestClient client = RestClient.create("https://viacep.com.br/ws");

    public EnderecoViaCep consultar(String cep) {
        return client.get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .body(EnderecoViaCep.class);
    }
}
```

Boas práticas: **timeouts**, tratamento de falhas (a API de terceiros pode cair), cache de respostas repetidas e nunca expor chaves de API no código.

---

## 11. CORS e integração com o front-end

Quando o front-end (ex.: `http://localhost:5500`) chama a API (`http://localhost:8080`), o navegador exige **CORS**:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5500")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
    }
}
```

Libere **apenas** as origens necessárias (evite `*` em produção).

---

## 12. Segurança básica (JWT)

Fluxo com **JWT** (*JSON Web Token*) — alinhado ao princípio *stateless* do REST:

```
1. POST /api/v1/auth/login  (usuário + senha)
2. API valida e devolve um token JWT assinado
3. Cliente envia: Authorization: Bearer <token> em cada requisição
4. Um filtro valida o token e identifica o usuário
```

Com **Spring Security**:

- Armazene senhas com **BCrypt** (`BCryptPasswordEncoder`), **nunca** em texto puro.
- Defina quais rotas são públicas (`/auth/**`) e quais exigem autenticação/papel.
- Mantenha a chave de assinatura em variável de ambiente.
- Defina expiração curta para o token.

> Para estudo inicial, **Basic Auth** é aceitável; para o projeto da disciplina, implemente JWT ou justifique outra abordagem.

---

## 13. Testes automatizados

### Teste unitário do service (JUnit 5 + Mockito)

```java
@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock LivroRepository repository;
    @InjectMocks LivroService service;

    @Test
    void deveLancarExcecaoQuandoLivroNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> service.buscar(99L));
    }
}
```

### Teste de integração da API (MockMvc)

```java
@SpringBootTest
@AutoConfigureMockMvc
class LivroControllerTest {

    @Autowired MockMvc mvc;

    @Test
    void deveRetornar404ParaLivroInexistente() throws Exception {
        mvc.perform(get("/api/v1/livros/999"))
           .andExpect(status().isNotFound());
    }
}
```

Executar: `./mvnw test`.

---

## 14. Documentação com springdoc-openapi

Dependência:

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```

Acesse `http://localhost:8080/swagger-ui.html` para a documentação interativa e `/v3/api-docs` para o JSON OpenAPI. (Confirme a versão mais recente compatível com o seu Spring Boot.)

---

## 15. Boas práticas

- **Camadas** bem definidas; regras de negócio no *service*.
- **DTOs** na borda da API; entidades apenas internas.
- **Injeção por construtor**; evite `@Autowired` em campos.
- Validação na entrada e **tratamento global de erros**.
- Configuração por **variáveis de ambiente**; nada de senhas no repositório (`.gitignore`).
- **Logs** úteis (SLF4J), sem dados sensíveis.
- Migrações versionadas (Flyway/Liquibase).
- Testes automatizados e *code review*.
- Commits pequenos e descritivos; `README` sempre atualizado.

---

## 16. Exercícios propostos

1. Crie o projeto no Spring Initializr e implemente o CRUD do recurso `Livro` com H2.
2. Adicione `@Valid` e ao menos 4 regras de validação; teste os erros 422.
3. Crie o recurso `Autor` e relacione com `Livro` (`@OneToMany`/`@ManyToOne`) usando DTOs.
4. Implemente o `@RestControllerAdvice` com formato único de erro.
5. Consuma a API ViaCEP e exponha `GET /api/v1/enderecos/{cep}`.
6. Escreva 3 testes: um unitário e dois de integração (`MockMvc`).
7. Configure o perfil `prod` com PostgreSQL via variáveis de ambiente.
8. Conecte o front-end da disciplina de Programação Web I à API usando `fetch` e configure o CORS.

---

## Referências

- Spring Boot: [spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- Spring Initializr: [start.spring.io](https://start.spring.io)
- Spring Data JPA: [spring.io/projects/spring-data-jpa](https://spring.io/projects/spring-data-jpa)
- Spring Security: [spring.io/projects/spring-security](https://spring.io/projects/spring-security)
- Baeldung (tutoriais): [baeldung.com](https://www.baeldung.com)
- JWT: [jwt.io](https://jwt.io)
