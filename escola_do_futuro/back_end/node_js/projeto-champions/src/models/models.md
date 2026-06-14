Em projetos Node.js (especialmente com frameworks como Express, NestJS ou AdonisJS), a pasta **`models`** serve para definir a **representação estruturada dos dados** que sua aplicação manipula.

Seus principais usos são:

## 1. **Definir a estrutura de dados**
- Representa entidades do negócio (usuários, produtos, pedidos)
- Define campos, tipos e validações básicas

## 2. **Interagir com o banco de dados**
- Se você usa **ORM** (Sequelize, TypeORM, Prisma) ou **ODM** (Mongoose para MongoDB)
- Os models mapeiam tabelas/coleções do banco para objetos JavaScript

```javascript
// Exemplo com Mongoose (MongoDB)
const userSchema = new Schema({
  name: String,
  email: { type: String, unique: true },
  password: String
});

const User = model('User', userSchema);
```

## 3. **Centralizar regras de negócio**
- Validações específicas dos dados
- Métodos auxiliares (ex: `user.fullName()`, `product.applyDiscount()`)

## 4. **Organização do código**
- Separa a lógica de dados do resto da aplicação (controllers, rotas)
- Segue o padrão **MVC** (Model-View-Controller)

## Exemplo prático:

```
src/
├── models/
│   ├── User.js
│   ├── Product.js
│   └── Order.js
├── controllers/
├── routes/
└── app.js
```

## Quando NÃO usar:
- Projetos muito simples sem banco de dados
- Se você usa apenas queries SQL "cruas" (pode colocar no `database/` ou `repositories/`)

**Resumo:** A pasta `models` é o "coração dos dados" da sua aplicação, conectando a lógica de negócio ao armazenamento (banco de dados).
