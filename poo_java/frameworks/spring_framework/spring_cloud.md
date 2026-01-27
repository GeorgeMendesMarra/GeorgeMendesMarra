# ☁️ Spring Cloud — visão geral

O **Spring Cloud** é um conjunto de ferramentas para **desenvolver sistemas distribuídos / microsserviços** em Java, resolvendo problemas clássicos como:

* Descoberta de serviços
* Configuração centralizada
* Balanceamento de carga
* Tolerância a falhas
* Gateway de APIs

---

## 🧠 Arquitetura típica de microsserviços

```
Cliente
   ↓
API Gateway
   ↓
Service Discovery
   ↓
Microsserviços
   ↓
Banco de Dados (por serviço)
```

📌 Cada serviço é **independente**, inclusive o banco.

---

## 🧩 Principais componentes do Spring Cloud

### 🔹 Eureka (Service Discovery)

📌 Registro e descoberta automática de serviços.

```java
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication { }
```

```yaml
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

---

### 🔹 OpenFeign (Comunicação entre serviços)

📌 Substitui `RestTemplate`.

```java
@FeignClient(name = "pedido-service")
public interface PedidoClient {

    @GetMapping("/pedidos/{id}")
    PedidoDTO buscar(@PathVariable Long id);
}
```

```java
@EnableFeignClients
```

---

### 🔹 Spring Cloud Gateway (API Gateway)

📌 Porta de entrada do sistema.

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: usuario
          uri: lb://usuario-service
          predicates:
            - Path=/usuarios/**
```

✔️ Roteamento
✔️ Segurança
✔️ Logs
✔️ Rate limit

---

### 🔹 Config Server (configuração centralizada)

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication { }
```

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/seu-repo/configs
```

📌 Evita configs duplicadas nos serviços.

---

### 🔹 Resilience4j (tolerância a falhas)

```java
@CircuitBreaker(name = "pedidoService", fallbackMethod = "fallback")
public PedidoDTO buscar(Long id) {
    return client.buscar(id);
}

public PedidoDTO fallback(Long id, Throwable t) {
    return new PedidoDTO();
}
```

📌 Protege contra falhas em cascata.

---

### 🔹 Load Balancing

Hoje: **Spring Cloud LoadBalancer** (substitui Ribbon).

```yaml
uri: lb://usuario-service
```

---

## 🔐 Spring Cloud + Security

* Segurança centralizada no **Gateway**
* JWT compartilhado entre serviços
* Integração com OAuth2

---

## 🔄 Fluxo de uma requisição

```
Cliente → Gateway → Eureka → Microsserviço → Banco
```

---

## 🎓 Texto pronto para TCC

> *O Spring Cloud foi adotado para viabilizar a arquitetura de microsserviços, oferecendo mecanismos de descoberta de serviços, balanceamento de carga, tolerância a falhas e configuração centralizada.*

---

## ⚖️ Quando usar Spring Cloud?

✔️ Sistemas grandes
✔️ Alta escalabilidade
✔️ Equipes múltiplas
❌ Não vale a pena para sistemas pequenos

---

## 🧱 Stack mínima recomendada (didática)

| Camada       | Tecnologia            |
| ------------ | --------------------- |
| Gateway      | Spring Cloud Gateway  |
| Discovery    | Eureka                |
| Comunicação  | OpenFeign             |
| Resiliência  | Resilience4j          |
| Segurança    | Spring Security + JWT |
| Persistência | Spring Data JPA       |

---

## 💡 Dica de professor 😉

Se for TCC:

> **Mostre um monólito → depois migração para microsserviços**
