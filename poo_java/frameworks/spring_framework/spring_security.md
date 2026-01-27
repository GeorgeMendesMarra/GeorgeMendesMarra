O **Spring Security** é o framework do Spring responsável por:

* **Autenticação** → quem é o usuário?
* **Autorização** → o que ele pode acessar?
* Proteção contra ataques comuns (CSRF, XSS, Session Fixation)

---

## 🧠 Arquitetura básica (como ele funciona)

```
Requisição HTTP
     ↓
Security Filter Chain
     ↓
AuthenticationManager
     ↓
UserDetailsService
     ↓
UserDetails
     ↓
Decisão de acesso (Roles / Authorities)
```

📌 Tudo passa por **filtros** antes de chegar no controller.

---

## 🔑 Autenticação

### 1️⃣ Autenticação em memória (didática)

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("123"))
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user);
}
```

---

### 2️⃣ Autenticação com banco de dados (real)

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario u = repo.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
            .withUsername(u.getEmail())
            .password(u.getSenha())
            .roles(u.getRole())
            .build();
    }
}
```

---

## 🔐 PasswordEncoder (obrigatório)

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

📌 **Nunca** salve senha em texto puro.

---

## 🛡️ Autorização (controle de acesso)

### Por URL

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/admin/**").hasRole("ADMIN")
          .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
          .anyRequest().authenticated()
      )
      .formLogin()
      .and()
      .logout();

    return http.build();
}
```

---

### Por método (mais profissional)

```java
@EnableMethodSecurity
```

```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/relatorio")
public String relatorio() {
    return "ok";
}
```

---

## 🔄 Sessão vs JWT

### 🧾 Sessão (tradicional)

* Usa cookies
* Bom para sistemas web
* Menos escalável

### 🔑 JWT (moderno / APIs)

```
Login → Token JWT → Header Authorization
```

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## 🔥 JWT no Spring Security (resumo)

* Filter para interceptar requisições
* TokenProvider
* SecurityContextHolder

📌 Ideal para **backend Java + frontend JS puro** (bem seu perfil 😉).

---

## 🚨 Proteções importantes

* CSRF (ativado por padrão)
* CORS
* Session Fixation
* Headers de segurança

```java
http.csrf().disable(); // só para APIs REST
```

---

## 🎓 Frase pronta para TCC

> *O Spring Security foi utilizado para implementar mecanismos de autenticação e autorização, garantindo o controle de acesso aos recursos do sistema e a proteção contra ataques comuns a aplicações web.*

---

## 📌 Quando usar Spring Security?

✔️ Sistemas acadêmicos
✔️ APIs REST
✔️ Sistemas com login e níveis de acesso
✔️ Projetos em Java (ADS, TCC, mercado)

---
