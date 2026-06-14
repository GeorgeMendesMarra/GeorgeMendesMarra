Em um projeto Node.js, a pasta `controllers` serve para **organizar a lógica de tratamento das requisições HTTP** que chegam à sua aplicação.

## Função principal

Os controllers são responsáveis por:
- Receber os dados da requisição (`req.params`, `req.query`, `req.body`)
- Executar a lógica de negócio (geralmente chamando services ou models)
- Enviar a resposta adequada ao cliente (`res.json`, `res.status`, `res.send`)

## Exemplo prático

```javascript
// controllers/userController.js
const userService = require('../services/userService');

const getAllUsers = async (req, res) => {
  try {
    const users = await userService.findAll();
    res.status(200).json(users);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const createUser = async (req, res) => {
  try {
    const { name, email } = req.body;
    const newUser = await userService.create({ name, email });
    res.status(201).json(newUser);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

module.exports = { getAllUsers, createUser };
```

## Como se integra com as rotas

```javascript
// routes/userRoutes.js
const express = require('express');
const router = express.Router();
const userController = require('../controllers/userController');

router.get('/', userController.getAllUsers);
router.post('/', userController.createUser);
```

## Vantagens dessa organização

1. **Separação de responsabilidades** - Rotas apenas definem endpoints, controllers contêm a lógica
2. **Reuso de código** - Mesma lógica pode ser usada por diferentes rotas
3. **Manutenibilidade** - Fácil localizar e modificar comportamentos específicos
4. **Testabilidade** - Controllers podem ser testados isoladamente

## Padrão comum de arquitetura

```
src/
├── controllers/   # Lida com requisições/respostas HTTP
├── services/      # Lógica de negócio
├── models/        # Interação com banco de dados
├── routes/        # Definição dos endpoints
└── middlewares/   # Funções intermediárias
```

Esse padrão (MVC - Model-View-Controller) mantém o código organizado e escalável em aplicações Node.js com Express ou similares.
