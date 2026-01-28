## 📌 O que é Arquitetura Cliente–Servidor?

É um modelo onde o sistema é dividido em **dois lados bem definidos**:

* **Cliente** → solicita serviços
* **Servidor** → processa as solicitações e devolve respostas

Eles se comunicam por uma **rede** (geralmente via HTTP/HTTPS).

---

## 🧩 Componentes

### 🧑‍💻 Cliente

Responsável por:

* Interface com o usuário
* Enviar requisições
* Exibir respostas

📌 Exemplos:

* Navegador (Chrome, Firefox)
* App mobile
* Frontend HTML/CSS/JS
* Cliente Java, Python, etc.

---

### 🖥️ Servidor

Responsável por:

* Regras de negócio
* Processamento
* Acesso a banco de dados
* Segurança

📌 Exemplos:

* Servidor Java (Spring Boot)
* Node.js
* PHP
* Python (Django, Flask)

---

## 🔄 Funcionamento (fluxo básico)

```
Cliente → Requisição → Servidor
Cliente ← Resposta ← Servidor
```

Exemplo real:

1. Cliente pede: “Quero meus dados”
2. Servidor processa no banco
3. Servidor responde com JSON
4. Cliente exibe na tela

---

## 🏗️ Tipos de Arquitetura Cliente–Servidor

### 🔹 2 Camadas (2-Tier)

Cliente ↔ Servidor

Ex:

* App desktop conectado direto ao banco

❌ Pouca escalabilidade
❌ Segurança limitada

---

### 🔹 3 Camadas (3-Tier) ⭐ (mais usada)

1. **Apresentação** (Cliente)
2. **Lógica de Negócio** (Servidor)
3. **Dados** (Banco)

Ex:

* Frontend (HTML/JS)
* Backend (Spring Boot)
* Banco (MySQL/PostgreSQL)

✔ Escalável
✔ Mais segura
✔ Manutenção fácil

---

### 🔹 N Camadas (N-Tier)

* Microserviços
* APIs separadas
* Gateway, cache, etc.

Usada em sistemas grandes (bancos, e-commerces).

---

## ✅ Vantagens

✔ Centralização de dados
✔ Segurança
✔ Escalabilidade
✔ Manutenção facilitada
✔ Reuso de serviços

---

## ❌ Desvantagens

❌ Dependência da rede
❌ Servidor pode virar gargalo
❌ Custo de infraestrutura

---

## 🧠 Exemplos práticos

* WhatsApp
* Instagram
* Sistemas web
* APIs REST
* Sistemas corporativos

---
