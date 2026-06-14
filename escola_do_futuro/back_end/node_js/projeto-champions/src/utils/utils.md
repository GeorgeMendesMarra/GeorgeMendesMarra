A pasta `utils` (ou `utilities/`) em um projeto Node.js serve para armazenar **funções auxiliares genéricas e reutilizáveis** que não pertencem especificamente à lógica de negócio, às rotas ou ao acesso a dados. São utilitários de propósito geral que podem ser usados em qualquer parte da aplicação.

## Principais funções da pasta `utils`:

1. **Formatação de dados**  
   Exemplo: formatar datas, números, telefones, CPF/CNPJ, moedas.

2. **Validações comuns**  
   Exemplo: validar formato de email, URL, CPF, CEP.

3. **Funções matemáticas/estatísticas**  
   Exemplo: calcular média, percentual, arredondamentos específicos.

4. **Tratamento de erros e logging**  
   Exemplo: funções para padronizar respostas de erro, formatar logs.

5. **Conversões e manipulações**  
   Exemplo: converter string para slug, remover acentos, gerar tokens aleatórios.

6. **Helpers para arquivos e strings**  
   Exemplo: ler arquivos do sistema, manipular caminhos, sanitizar entradas.

## Exemplos práticos

```javascript
// utils/format.js
function formatDateToBR(date) {
  return new Date(date).toLocaleDateString('pt-BR');
}

function formatCurrency(value) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  }).format(value);
}

function slugify(text) {
  return text
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .replace(/[^\w\s]/g, '')
    .toLowerCase()
    .replace(/\s+/g, '-');
}

module.exports = { formatDateToBR, formatCurrency, slugify };
```

```javascript
// utils/validation.js
function isValidEmail(email) {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email);
}

function isValidCPF(cpf) {
  cpf = cpf.replace(/[^\d]/g, '');
  if (cpf.length !== 11) return false;
  // Lógica completa de validação de CPF...
  return true;
}

function isEmpty(value) {
  return value === null || value === undefined || value === '';
}

module.exports = { isValidEmail, isValidCPF, isEmpty };
```

```javascript
// utils/random.js
function generateRandomToken(length = 32) {
  const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  let token = '';
  for (let i = 0; i < length; i++) {
    token += chars.charAt(Math.floor(Math.random() * chars.length));
  }
  return token;
}

function generateShortId() {
  return Math.random().toString(36).substring(2, 10);
}

module.exports = { generateRandomToken, generateShortId };
```

## Exemplo de uso em outras camadas

```javascript
// services/userService.js
const { isValidEmail, isEmpty } = require('../utils/validation');
const { slugify } = require('../utils/format');
const { generateRandomToken } = require('../utils/random');

async function createUser(userData) {
  if (!isValidEmail(userData.email)) {
    throw new Error('E-mail inválido');
  }
  
  if (isEmpty(userData.name)) {
    throw new Error('Nome é obrigatório');
  }
  
  const username = slugify(userData.name);
  const confirmationToken = generateRandomToken();
  
  // Restante da lógica...
}
```

## Diferença entre `utils` e outras pastas

| Pasta | Responsabilidade |
|-------|------------------|
| `services` | Lógica de negócio, orquestração de operações |
| `controllers` | Lidar com requisições/respostas HTTP |
| `repositories` | Acesso ao banco de dados |
| `models` | Definição de entidades/schemas |
| **`utils`** | **Funções genéricas sem dependência do domínio** |
| `helpers` | Similar ao utils (muitos usam como sinônimo) |
| `lib` | Configurações de bibliotecas terceiras |

## Boas práticas para `utils`

✅ **Mantenha funções puras** (sem efeitos colaterais) quando possível.  
✅ **Agrupe por tema** (ex: `dateUtils.js`, `stringUtils.js`, `cryptoUtils.js`).  
✅ **Documente o que cada função faz** (especialmente as mais complexas).  
✅ **Evite dependências da aplicação** (utils devem funcionar isoladamente).  
✅ **Teste unitariamente** (são as funções mais fáceis de testar).

## Quando NÃO usar `utils`

❌ **Lógica de negócio** → vai em `services`  
❌ **Acesso a banco de dados** → vai em `repositories`  
❌ **Regras específicas de uma entidade** → vai no `model` ou `service`  
❌ **Configurações de ambiente** → vai em `config`

**Resumo**: `utils` é a "caixa de ferramentas" do projeto — funções pequenas, reutilizáveis e sem contexto específico de negócio, que ajudam a evitar repetição de código em toda a aplicação.
