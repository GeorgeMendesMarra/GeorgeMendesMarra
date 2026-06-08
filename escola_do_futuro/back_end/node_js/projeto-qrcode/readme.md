# 🔐 QR Code & Password Utility Kit

![Node.js](https://img.shields.io/badge/Node.js-339933?logo=nodedotjs&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?logo=typescript&logoColor=white)

## 💻 Sobre o Projeto

O **QR Code & Password Utility Kit** é um conjunto de ferramentas utilitárias desenvolvido para auxiliar operações do dia a dia em um e-commerce. O projeto foi pensado para ser **escalável**, permitindo a adição de novas funcionalidades de forma simples e organizada.

Atualmente, o kit oferece duas funcionalidades principais:

- ✅ **Gerador de QR Code** – Cria QR Codes personalizados a partir de URLs ou textos informados pelo usuário.
- ✅ **Gerador de Senhas** – Gera senhas aleatórias e seguras com opções de personalização (tamanho, inclusão de números, símbolos, letras maiúsculas/minúsculas).

### 🎯 Objetivo

Construir uma base sólida e modular que possa crescer junto com as necessidades do negócio, permitindo a implementação de novas utilidades como:

- Gerador de código de barras
- Validador de CPF/CNPJ
- Conversor de moedas
- Calculadora de fretes
- E muito mais...

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Finalidade |
|------------|-------------|
| [Node.js](https://nodejs.org/) | Ambiente de execução JavaScript back-end |
| [JavaScript](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript) (ES Modules) | Linguagem principal do projeto |
| [prompt](https://www.npmjs.com/package/prompt) | Biblioteca para capturar entradas do usuário no terminal |
| [QR Code](https://www.npmjs.com/package/qrcode) | Geração de QR Codes |

---

## 📁 Estrutura do Projeto

```
qr-code-nodejs/
├── index.js                      # Arquivo principal (menu interativo)
├── prompts-schema/
│   └── prompt-schema-main.js     # Configuração das opções do menu
├── services/
│   ├── qr-code/
│   │   └── create.js             # Lógica de criação do QR Code
│   └── password/
│       └── create.js             # Lógica de criação de senhas
└── README.md
```

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- Node.js instalado (versão 14 ou superior)
- Gerenciador de pacotes (npm ou yarn)

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/qr-code-nodejs.git

# 2. Acesse a pasta do projeto
cd qr-code-nodejs

# 3. Instale as dependências
npm install

# 4. Execute o projeto
npm start
# ou
node index.js
```

### 🧠 Como usar

1. Ao executar o projeto, um menu interativo será exibido no terminal.
2. Escolha a opção desejada digitando o número correspondente:
   - `1` - Criar QR Code
   - `2` - Criar senha
3. Siga as instruções na tela para cada ferramenta.

---

## 🔧 Funcionalidades em Detalhe

### 1. Gerador de QR Code
- Permite inserir qualquer texto ou URL
- Gera automaticamente a imagem do QR Code
- Salva a imagem no diretório `output/`

### 2. Gerador de Senhas
- Permite definir o comprimento da senha
- Opções para incluir: números, símbolos, letras maiúsculas e minúsculas
- Gera senhas fortes e aleatórias

---

## 📦 Expansão Futura

O projeto foi estruturado para facilitar a adição de novos módulos. Para adicionar uma nova funcionalidade:

1. Crie uma nova pasta dentro de `services/`
2. Desenvolva a lógica em um arquivo separado
3. Importe a função no `index.js`
4. Adicione a opção no arquivo de schema `prompt-schema-main.js`

---

## 🤝 Contribuição

Contribuições são muito bem-vindas! Para contribuir:

1. Faça um **fork** do projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/nova-feature`)
3. **Commit** suas alterações (`git commit -m 'feat: adiciona nova feature'`)
4. **Push** para a branch (`git push origin feature/nova-feature`)
5. Abra um **Pull Request**

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo [LICENSE](LICENSE) para mais informações.

---

---

> ⚡ *"Código escalável é código que pensa no futuro sem esquecer do presente."*
