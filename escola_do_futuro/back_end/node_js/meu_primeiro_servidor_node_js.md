# 🚀 1️⃣ Criando o projeto

No terminal:

```bash
mkdir meu-servidor
cd meu-servidor
npm init -y
```

Isso cria o arquivo `package.json`.

---

# 🧠 2️⃣ Criando o servidor básico (sem frameworks)

Crie um arquivo chamado `server.js`:

```javascript
// Importa o módulo HTTP (módulo interno do Node)
const http = require('http');

// Define a porta do servidor
const PORT = 3000;

// Cria o servidor
const server = http.createServer((req, res) => {
    
    // Define o cabeçalho da resposta
    res.writeHead(200, { 'Content-Type': 'text/plain' });

    // Envia resposta para o navegador
    res.end('Olá, mundo! Meu primeiro servidor Node.js 🚀');
});

// Coloca o servidor para escutar a porta definida
server.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});
```

---

# ▶️ 3️⃣ Executando o servidor

No terminal:

```bash
node server.js
```

Abra o navegador e acesse:

```
http://localhost:3000
```

🎉 Pronto! Seu servidor está funcionando.

---

# 🧩 O que está acontecendo aqui?

Estamos usando o módulo interno:

* **Node.js**
* Módulo `http` (interno)

O servidor:

* Escuta requisições
* Recebe um request (`req`)
* Envia uma response (`res`)
* Funciona de forma assíncrona (Event Loop)

---

# 🔥 Versão 2 – Criando Rotas

```javascript
const http = require('http');
const PORT = 3000;

const server = http.createServer((req, res) => {

    if (req.url === '/') {
        res.end('Página Inicial');
    } 
    else if (req.url === '/sobre') {
        res.end('Página Sobre');
    } 
    else {
        res.writeHead(404);
        res.end('Página não encontrada');
    }

});

server.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});
```

Agora teste:

* `http://localhost:3000/`
* `http://localhost:3000/sobre`

Qual o objetivo da aula? Introdução ao back-end ou já começar API?
