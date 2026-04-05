## Para que serve a pasta `docs` em um projeto de software?

A pasta `docs` (abreviação de *documents* ou *documentation*) é um diretório padrão em projetos de software utilizado para armazenar **toda a documentação do projeto** que não faz parte do código-fonte propriamente dito.

---

### 📁 O que geralmente vai na pasta `docs`:

| Tipo de Documento | Exemplos |
|------------------|----------|
| **Documentação técnica** | Arquitetura do sistema, diagramas UML, decisões de design |
| **Manuais do usuário** | Guias de instalação, manuais de uso, FAQs |
| **Documentação da API** | Endpoints, parâmetros, exemplos de requisição/resposta |
| **Especificações** | Requisitos funcionais e não funcionais |
| **Relatórios** | Cobertura de testes, análises de performance |
| **Arquivos de configuração de docs** | `mkdocs.yml`, `conf.py` (para Sphinx) |

---

### 🗂️ Estrutura comum da pasta `docs`:

```
docs/
├── api/                 # Documentação da API
├── guides/              # Guias e tutoriais
├── diagrams/            # Diagramas (arquivos .png, .drawio, etc.)
├── requirements/        # Especificações de requisitos
├── user-manual/         # Manual do usuário final
├── technical/           # Documentação técnica para desenvolvedores
├── README.md            # Visão geral da documentação
└── index.md             # Página inicial da documentação
```

---

### 🎯 Para que serve (objetivos principais):

1. **Centralizar a documentação** - Um único local para toda a documentação do projeto

2. **Separar documentação do código** - Código-fonte fica em `src/`, documentação fica em `docs/`

3. **Facilitar a geração de sites de documentação** - Ferramentas como:
   - **MkDocs** (Python)
   - **Docusaurus** (React)
   - **VitePress** (Vue)
   - **Jekyll** (Ruby)
   
   Leem a pasta `docs` e geram sites estáticos de documentação automaticamente

4. **Versionamento da documentação** - A documentação acompanha as versões do código no Git

5. **Colaboração** - Vários desenvolvedores podem contribuir com a documentação via pull requests

---

### 📝 Exemplo prático (com MkDocs - muito usado em Python):

**Estrutura do projeto:**
```
meu-projeto/
├── src/                 # Código-fonte
│   └── main.py
├── docs/                # Documentação
│   ├── index.md         # Página inicial
│   ├── instalacao.md    # Guia de instalação
│   ├── api.md           # Documentação da API
│   └── imagens/         # Imagens da documentação
├── mkdocs.yml           # Configuração do MkDocs
└── README.md            # Visão geral do projeto
```

**Arquivo `mkdocs.yml`:**
```yaml
site_name: Meu Projeto
nav:
  - Início: index.md
  - Instalação: instalacao.md
  - API: api.md
```

**Comando para gerar o site:**
```bash
mkdocs build   # Gera site em site/
mkdocs serve   # Sobe servidor local em http://localhost:8000
```

---

### ⚠️ O que NÃO deve ir na pasta `docs`:

| Não colocar na `docs` | Onde colocar |
|----------------------|--------------|
| Código-fonte | `src/` ou raiz do projeto |
| Bibliotecas/dependências | `lib/`, `node_modules/`, `vendor/` |
| Arquivos de build | `dist/`, `build/`, `target/` |
| Logs | `logs/` (ignorar no .gitignore) |
| Arquivos temporários | `tmp/`, `temp/` |

---

### 🧠 Para os alunos do curso de Engenharia de Software:

A pasta `docs` é uma **boa prática de engenharia de software** porque:

1. **Rastreabilidade** - Permite vincular decisões de design ao código
2. **Manutenibilidade** - Novo desenvolvedor entende o projeto mais rápido
3. **Padronização** - Segue convenções de projetos open source (GitHub, GitLab)
4. **DevOps** - Integra com pipelines de CI/CD para gerar documentação automaticamente

**Exemplo de integração CI/CD:**
```yaml
# .github/workflows/docs.yml
name: Deploy Docs
on:
  push:
    branches: [main]
jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-python@v4
      - run: pip install mkdocs
      - run: mkdocs build
      - run: mkdocs gh-deploy --force
```

Isso faz com que cada push para o `main` atualize automaticamente o site de documentação do projeto! 🚀

---
