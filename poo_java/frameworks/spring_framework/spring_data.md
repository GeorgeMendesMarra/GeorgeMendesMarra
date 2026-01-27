# 📦 Spring Data — visão geral

O **Spring Data** é um conjunto de projetos que **simplifica o acesso a dados** no Spring, eliminando grande parte do código boilerplate de DAO.

Principais módulos:

* **Spring Data JPA** (o mais usado)
* Spring Data JDBC
* Spring Data MongoDB
* Spring Data Redis

👉 Aqui vou focar em **Spring Data JPA**, que é o padrão em projetos Java.

---

## 🧠 Arquitetura básica

```
Controller
    ↓
Service
    ↓
Repository (Spring Data)
    ↓
JPA / Hibernate
    ↓
Banco de Dados
```

📌 O desenvolvedor escreve **interfaces**, e o Spring gera a implementação.

---

## 🗄️ Entidade (Entity)

```java
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String role;

    // getters e setters
}
```

---

## 📂 Repository (coração do Spring Data)

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByRole(String role);
}
```

📌 **Sem implementação manual**. O nome do método vira SQL.

---

## 🔎 Query Methods (derivação automática)

Exemplos comuns:

```java
findByNome(String nome)
findByEmailAndSenha(String email, String senha)
findByAtivoTrue()
findByIdGreaterThan(Long id)
```

---

## 📝 JPQL (quando o método não basta)

```java
@Query("SELECT u FROM Usuario u WHERE u.role = :role")
List<Usuario> buscarPorRole(@Param("role") String role);
```

---

## ⚙️ Native Query (SQL puro)

```java
@Query(
  value = "SELECT * FROM usuarios WHERE role = ?1",
  nativeQuery = true
)
List<Usuario> buscarRoleNativo(String role);
```

📌 Use só quando **realmente necessário**.

---

## 🔄 Service (regra de negócio)

```java
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario salvar(Usuario u) {
        return repo.save(u);
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }
}
```

---

## 📄 Paginação e Ordenação (muito cobrado)

```java
Pageable pageable = PageRequest.of(0, 10, Sort.by("email"));

Page<Usuario> page = repo.findAll(pageable);
```

---

## 🧪 Transações

```java
@Transactional
public void atualizarSenha(Long id, String senha) {
    Usuario u = repo.findById(id).orElseThrow();
    u.setSenha(senha);
}
```

📌 Commit automático no final do método.

---

## 🔗 Relacionamentos JPA

### OneToMany

```java
@OneToMany(mappedBy = "usuario")
private List<Pedido> pedidos;
```

### ManyToOne

```java
@ManyToOne
@JoinColumn(name = "usuario_id")
private Usuario usuario;
```

---

## 🧩 Spring Data + Spring Security

📌 O Repository é usado no:

```java
UserDetailsService
```

para buscar usuários no banco.

---

## 🎓 Frase pronta para TCC

> *O Spring Data JPA foi utilizado para abstrair o acesso ao banco de dados, reduzindo a complexidade da camada de persistência e aumentando a produtividade no desenvolvimento.*

---

## ⚖️ Vantagens

✔️ Menos código
✔️ Alta produtividade
✔️ Integração total com Spring
✔️ Padrão de mercado

---
