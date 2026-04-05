## Para que serve a pasta `src` em um projeto de software?

A pasta **`src`** (abreviação de **source** - fonte/origem em inglês) é um **padrão de organização** amplamente utilizado na indústria de software para armazenar o **código-fonte principal** do projeto.

---

## 🎯 Propósito Principal

A pasta `src` contém **todo o código que você (ou sua equipe) escreve** e que será executado para fazer o software funcionar. É o "coração" do projeto.

---

## 📁 Estrutura Típica de um Projeto

```
meu-projeto/
│
├── src/                    ← SEU CÓDIGO FONTE (o mais importante)
│   ├── controllers/        # Controladores (lógica de requisições)
│   ├── models/             # Modelos de dados
│   ├── services/           # Regras de negócio
│   ├── utils/              # Funções auxiliares
│   └── index.js            # Ponto de entrada
│
├── tests/                  # Testes automatizados
├── docs/                   # Documentação
├── public/                 # Arquivos estáticos (HTML, CSS, imagens)
├── node_modules/           # Dependências externas (NPM)
├── .env                    # Configurações sensíveis
├── .gitignore              # Arquivos ignorados pelo Git
├── package.json            # Configuração do projeto
└── README.md               # Documentação principal
```

---

## 🔍 O que vai e o que NÃO vai na pasta `src`?

### ✅ **VAI na pasta `src`** (código que você escreve)
- Lógica de negócio
- Controladores e rotas
- Modelos de banco de dados
- Funções utilitárias
- Configurações da aplicação
- Middlewares
- Validações

### ❌ **NÃO vai na pasta `src`** (código/arquivos gerados ou externos)
- Bibliotecas de terceiros (`node_modules/`)
- Arquivos de build/compilação (`dist/`, `build/`)
- Arquivos de configuração do projeto (`package.json`, `.gitignore`)
- Testes automatizados (ficam em `tests/`)
- Documentação (`docs/`)
- Arquivos estáticos (`public/`, `assets/`)

---

## 💡 Por que usar a pasta `src`?

### 1. **Organização e Padronização**
```javascript
// SEM src (tudo misturado) - RUIM
projeto/
├── index.js
├── database.js
├── userController.js
├── productController.js
├── emailService.js
├── authMiddleware.js
└── utils.js

// COM src (organizado) - BOM
projeto/
├── src/
│   ├── controllers/
│   │   ├── userController.js
│   │   └── productController.js
│   ├── services/
│   │   └── emailService.js
│   ├── middlewares/
│   │   └── authMiddleware.js
│   ├── database/
│   │   └── connection.js
│   └── utils/
│       └── helpers.js
└── index.js
```

### 2. **Separação de Responsabilidades**
- Código fonte vs código de terceiros
- Código escrito por você vs código gerado automaticamente
- Ambiente de desenvolvimento vs ambiente de produção

### 3. **Facilita o Build/Compilação**
Ferramentas como **Webpack, Vite, Babel** sabem que devem processar apenas a pasta `src`:

```json
// package.json (exemplo com Vite)
{
  "scripts": {
    "build": "vite build",  // Vai pegar o código da pasta src/
    "dev": "vite"            // Servidor de desenvolvimento lê da src/
  }
}
```

### 4. **Integração com Ferramentas**
- **TypeScript**: O compilador procura por `src/` por padrão
- **ESLint**: Analisa apenas o código que você escreveu
- **Jest/Testes**: Sabe que os testes estão separados

---

## 🌍 Exemplos em Diferentes Linguagens/Ecossistemas

| Linguagem/Framework | Uso da pasta `src` |
|---------------------|---------------------|
| **JavaScript/Node.js** | Código fonte da aplicação |
| **React** | Componentes, hooks, páginas |
| **Vue.js** | Componentes, views, stores |
| **Angular** | Módulos, componentes, serviços |
| **Java (Maven)** | `src/main/java` (código) + `src/test/java` (testes) |
| **Python** | Módulos e pacotes principais |
| **C#/.NET** | `src/Projeto/` (código fonte) |
| **Flutter/Dart** | Código Dart da aplicação |

---

## 🎓 Exemplo Prático com o Código da Corrida

Se organizássemos o código dos personagens Mario e Luigi com a pasta `src`:

```
corrida-mario/
│
├── src/                         ← SEU CÓDIGO FONTE
│   ├── players/
│   │   ├── Mario.js            # Dados e lógica do Mario
│   │   └── Luigi.js            # Dados e lógica do Luigi
│   ├── engine/
│   │   ├── raceEngine.js       # Função playRaceEngine
│   │   ├── diceRoller.js       # Função rollDice
│   │   └── blockGenerator.js   # Função getRandomBlock
│   ├── utils/
│   │   ├── logger.js           # Função logRollResult
│   │   └── winner.js           # Função declareWinner
│   └── main.js                 # Ponto de entrada (main)
│
├── tests/                       # Testes automatizados
│   ├── raceEngine.test.js
│   └── diceRoller.test.js
│
├── docs/                        # Documentação
│   └── README.md
│
├── node_modules/                # Dependências (NÃO versionar)
├── package.json                 # Configuração do projeto
└── .gitignore                   # Arquivos ignorados pelo Git
```

---

## ⚠️ Boas Práticas

1. **Mantenha apenas código fonte**: Nada de arquivos de configuração dentro de `src`
2. **Organize por funcionalidade**: Use subpastas como `controllers/`, `models/`, `services/`
3. **Nomes claros**: Use nomes que indiquem a responsabilidade de cada arquivo
4. **Separe testes**: Testes ficam em `tests/` ou `__tests__/`, não dentro de `src/`
5. **Consistência**: Todo o time deve seguir o mesmo padrão

---

## 📝 Resumo para os Alunos

| Pergunta | Resposta |
|----------|----------|
| **O que significa `src`?** | Source (fonte/origem) |
| **O que guarda?** | Código-fonte que VOCÊ escreveu |
| **O que NÃO guarda?** | Dependências, builds, testes, docs |
| **Por que usar?** | Organização, padrão da indústria, facilita builds |
| **É obrigatório?** | Não, mas é uma convenção fortemente recomendada |

---

## 🎯 Conclusão

A pasta `src` é como a **sala de aula** em uma faculdade: é onde a "mágica" acontece (o aprendizado, no caso da faculdade; o código funcionando, no caso do software). Todo o resto (bibliotecas, testes, documentação) são como os laboratórios, biblioteca e cantina - importantes, mas não são o coração da atividade principal.

Ensinar esse padrão aos alunos desde cedo é fundamental para que eles se acostumem com as **boas práticas de engenharia de software** usadas no mercado! 🚀
