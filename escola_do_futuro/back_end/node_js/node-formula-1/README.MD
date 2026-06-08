<div align="center">

  <img src="./.github/assets/blue-saiyan-bg.png" alt="Logo" height="200">
  <h1 align="center"><strong>NODE BOILERPLATE - BLUE EDITION</strong></h1>
  <p align="center">
    🚀 Não comece um projeto Node.js do zero!<br>
    Utilize este <b>boilerplate</b> simples e moderno com <b>TypeScript</b> pré-configurado.
  </p>

</div>

<br />

<div align="center">
  
  [![TypeScript version][ts-badge]][typescript-5-4]
  [![Node.js version][nodejs-badge]][nodejs]
  [![License: MIT][license-badge]][license]
  [![PRs Welcome][prs-badge]][prs]

</div>

<br />

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Como Usar](#-como-usar)
- [Scripts Disponíveis](#-scripts-disponíveis)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Configuração](#-configuração)
- [Contribuição](#-contribuição)
- [Autor](#-autor)
- [Créditos](#-créditos)
- [Licença](#-licença)

<br />

## 📖 Sobre o Projeto

**Node Boilerplate - Blue Edition** é um template inicial para projetos Node.js com TypeScript, focado em produtividade e boas práticas. Ele oferece uma configuração moderna e otimizada, eliminando a necessidade de configurar manualmente ferramentas como compiladores, bundlers e ambiente de desenvolvimento.

### ✨ Vantagens

| Benefício | Descrição |
|-----------|-----------|
| ⚡ **Setup Rápido** | Comece a codificar em minutos, sem configurações chatas |
| 🎯 **TypeScript Nativo** | Execução direta de arquivos `.ts` com `tsx` |
| 📦 **Bundling Otimizado** | Compilação rápida com `tsup` (baseado no esbuild) |
| 🔄 **Hot Reload** | Reinício automático ao salvar alterações |
| 🌍 **Variáveis de Ambiente** | Suporte nativo a `.env` |

<br />

## 🚀 Tecnologias

### Principais Ferramentas

| Ferramenta | Versão | Finalidade |
|------------|--------|-------------|
| [TypeScript][typescript-npm] | 5.4+ | Superset tipado para JavaScript escalável |
| [tsx][tsx-npm] | - | Executa arquivos TypeScript diretamente no Node.js |
| [tsup][tsup-npm] | - | Empacotador zero-config baseado no esbuild |
| [Node.js][nodejs] | 20+ | Ambiente de execução JavaScript |

### Dependências de Desenvolvimento

```json
{
  "devDependencies": {
    "typescript": "^5.4.0",      // Compilador TypeScript
    "tsx": "^4.0.0",             // Executor TypeScript
    "tsup": "^8.0.0",            // Bundler TypeScript
    "@types/node": "^20.0.0"     // Tipos para Node.js
  }
}
```

<br />

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **[Git](https://git-scm.com)** - Controle de versão
- **[Node.js](https://nodejs.org/)** v20 ou superior
- **[npm](https://www.npmjs.com/)** v10 ou superior (ou yarn/pnpm)

```bash
# Verifique as versões instaladas
node --version  # Deve ser >= v20.0.0
npm --version   # Deve ser >= v10.0.0
```

<br />

## 👨‍💻 Como Usar

### 1️⃣ Clonar o Template

**Opção A - Usar template do GitHub:**
Clique no botão **[Use this template]** no GitHub

**Opção B - Clonar manualmente:**

```bash
# Clone o repositório
$ git clone https://github.com/felipeAguiarCode/node-blue-boilerplate

# Entre no diretório
$ cd node-blue-boilerplate

# Instale as dependências
$ npm install
```

### 2️⃣ Configurar Ambiente

```bash
# Copie o arquivo de exemplo das variáveis de ambiente
$ cp .env.example .env

# Edite o .env com suas configurações
$ nano .env
```

### 3️⃣ Executar o Projeto

```bash
# Modo desenvolvimento (com hot reload)
$ npm run start:watch

# Ou compile e execute a versão otimizada
$ npm run start:dist
```

<br />

## ⚡ Scripts Disponíveis

| Comando | Descrição |
|---------|-----------|
| `npm run dist` | Compila TypeScript para JavaScript na pasta `dist/` |
| `npm run start:dev` | Executa em desenvolvimento com variáveis do `.env` |
| `npm run start:watch` | Modo desenvolvimento com recarga automática (hot reload) |
| `npm run start:dist` | Compila e executa a versão otimizada da pasta `dist/` |
| `npm run clean` | Remove a pasta `dist/` (adicione se necessário) |
| `npm run build` | Alias para `dist` |

<br />

## 📁 Estrutura do Projeto

```
node-blue-boilerplate/
├── src/                    # Código fonte
│   ├── index.ts           # Ponto de entrada principal
│   └── ...                # Seus arquivos TypeScript
├── dist/                   # Código compilado (gerado)
├── .github/                # Configurações do GitHub
│   └── assets/            # Imagens e assets
├── .env                    # Variáveis de ambiente (não versionado)
├── .env.example            # Exemplo de variáveis de ambiente
├── .gitignore              # Arquivos ignorados pelo Git
├── package.json            # Dependências e scripts
├── tsconfig.json           # Configuração do TypeScript
├── README.md               # Este arquivo
└── LICENSE                 # Licença do projeto
```

<br />

## 🔧 Configuração

### tsconfig.json (Principais opções)

```json
{
  "compilerOptions": {
    "target": "ES2022",           // JavaScript moderno
    "module": "ESNext",           // Módulos ES
    "moduleResolution": "bundler", // Resolução moderna
    "strict": true,               // Modo estrito (recomendado)
    "outDir": "./dist",           // Saída da compilação
    "rootDir": "./src"            // Raiz do código fonte
  }
}
```

### Variáveis de Ambiente (.env)

```env
# Exemplo de configuração
PORT=3000
NODE_ENV=development
DATABASE_URL=mongodb://localhost:27017/myapp
```

<br />

## 🤝 Contribuição

Contribuições são o que fazem a comunidade open-source um lugar incrível para aprender, inspirar e criar. Qualquer contribuição que você fizer será **muito apreciada**.

1. Faça um **fork** do projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/feature-incrivel`)
3. **Commit** suas mudanças (`git commit -m 'feat: adiciona feature incrivel'`)
4. **Push** para a branch (`git push origin feature/feature-incrivel`)
5. Abra um **Pull Request**

> 📝 **Convenção de commits:** Utilize [Conventional Commits](https://www.conventionalcommits.org/)

<br />



<br />

## 🙏 Créditos

- **Ícones** por [Pino Lamanna][dribble-icon]
- **TypeScript** por Microsoft
- **Node.js** por OpenJS Foundation

<br />

## 📄 Licença

Distribuído sob a licença **MIT**. Veja [LICENSE](LICENSE) para mais informações.

<br />

---

<!-- LINKS & BADGES -->
[ts-badge]: https://img.shields.io/badge/TypeScript-5.4-3178C6?logo=typescript&logoColor=white
[nodejs-badge]: https://img.shields.io/badge/Node.js-20%2B-339933?logo=nodedotjs&logoColor=white
[license-badge]: https://img.shields.io/badge/License-MIT-green.svg
[prs-badge]: https://img.shields.io/badge/PRs-welcome-brightgreen.svg

[typescript-5-4]: https://www.typescriptlang.org/
[nodejs]: https://nodejs.org/
[license]: https://opensource.org/licenses/MIT
[prs]: http://makeapullrequest.com

[typescript-npm]: https://www.npmjs.com/package/typescript
[tsx-npm]: https://www.npmjs.com/package/tsx
[tsup-npm]: https://www.npmjs.com/package/tsup
[@types/node-npm]: https://www.npmjs.com/package/@types/node
[dribble-icon]: https://dribbble.com/Schakalwal
```
