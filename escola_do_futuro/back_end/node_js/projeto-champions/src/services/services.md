Em um projeto Node.js, a pasta `services` (ou `services/`) é usada para organizar a **lógica de negócio** e a **comunicação com recursos externos** (APIs, bancos de dados, filas, etc.), mantendo o código separado das camadas de controle (rotas/controllers) e de acesso a dados (repositories/models).

## Principais funções da pasta `services`:

1. **Encapsular regras de negócio**  
   Exemplo: calcular desconto, validar se um usuário pode executar uma ação, aplicar taxas, etc.

2. **Coordenar múltiplas fontes de dados**  
   Buscar dados de um repositório, consultar uma API externa, salvar o resultado em outro local.

3. **Reutilização centralizada**  
   Dois controllers diferentes (ex: web e API) podem usar o mesmo service, evitando duplicação.

4. **Comunicação externa**  
   Envio de e-mail, chamadas HTTP, acesso a serviços de terceiros (S3, Redis, etc.).

5. **Transações e operações atômicas**  
   Garantir que várias operações no banco ocorram com sucesso ou rollback total.

## Exemplo prático

```javascript
// services/userService.js
const userRepository = require('../repositories/userRepository');
const emailService = require('./emailService');

async function createUser(userData) {
  // Lógica de negócio: validar email único
  const existing = await userRepository.findByEmail(userData.email);
  if (existing) throw new Error('E-mail já cadastrado');

  // Hash de senha (regra de negócio)
  const hashedPassword = await bcrypt.hash(userData.password, 10);
  
  // Salvar no banco via repository
  const newUser = await userRepository.create({
    ...userData,
    password: hashedPassword
  });
  
  // Efeito colateral: enviar e-mail de boas-vindas
  await emailService.sendWelcomeEmail(newUser.email);
  
  return newUser;
}

module.exports = { createUser };
```

```javascript
// controllers/userController.js
const userService = require('../services/userService');

async function createUserController(req, res) {
  try {
    const user = await userService.createUser(req.body);
    res.status(201).json(user);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}
```

## Benefícios dessa separação

- **Testabilidade**: services podem ser testados sem depender de requisições HTTP.
- **Manutenção**: mudanças em regras de negócio afetam apenas os services, não as rotas.
- **Organização**: evita controllers inchados (mais de 100 linhas) e código espaguete.

## Estrutura comum de um projeto Node.js

```
src/
├── controllers/     # Lida com requisições/respostas HTTP
├── services/        # Lógica de negócio e orquestração
├── repositories/    # Acesso direto ao banco de dados
├── models/          # Definição de entidades (ORM/ODM)
├── utils/           # Funções auxiliares genéricas
└── config/          # Configurações da aplicação
```

**Resumo**: a pasta `services` é onde a “inteligência” da aplicação mora, desacoplando regras de negócio da infraestrutura (HTTP, banco de dados).
