Em um projeto Node.js, a pasta `routes` serve para **organizar e centralizar a definição das rotas da aplicação**, separando a lógica de roteamento do restante do código (como configuração do servidor, controllers e modelos).

## Principais funções:

### 1. **Mapeamento de URLs e métodos HTTP**
Define quais URLs e métodos (GET, POST, PUT, DELETE) cada endpoint da API ou página web irá responder.

```javascript
// routes/usuarios.js
const express = require('express');
const router = express.Router();

router.get('/usuarios', (req, res) => {
  res.json({ usuarios: [] });
});

router.post('/usuarios', (req, res) => {
  // criar usuário
});

module.exports = router;
```

### 2. **Separação de responsabilidades**
- Mantém o arquivo principal (ex: `app.js` ou `server.js`) mais limpo
- Facilita a manutenção do código
- Permite que diferentes desenvolvedores trabalhem em diferentes grupos de rotas

### 3. **Organização por módulos ou recursos**
```javascript
routes/
  ├── usuarios.js      // rotas relacionadas a usuários
  ├── produtos.js      // rotas relacionadas a produtos
  ├── pedidos.js       // rotas relacionadas a pedidos
  └── auth.js          // rotas de autenticação
```

### 4. **Agrupamento e versionamento de API**
```javascript
routes/
  ├── api/
  │   ├── v1/
  │   │   ├── usuarios.js
  │   │   └── produtos.js
  │   └── v2/
  │       └── usuarios.js
```

## Exemplo prático:

```javascript
// app.js - arquivo principal
const express = require('express');
const app = express();
const usuariosRouter = require('./routes/usuarios');
const produtosRouter = require('./routes/produtos');

// Usando as rotas
app.use('/api', usuariosRouter);
app.use('/api', produtosRouter);
```

## Vantagens:
- ✅ **Código mais organizado e legível**
- ✅ **Reutilização de middlewares específicos por rota**
- ✅ **Facilidade para adicionar/remover rotas**
- ✅ **Melhor escalabilidade do projeto**
- ✅ **Padrão amplamente adotado pela comunidade** (especialmente com Express.js)

Em resumo, a pasta `routes` é uma **convenção de organização** que torna o projeto Node.js mais estruturado, facilitando a manutenção e colaboração em equipe.
