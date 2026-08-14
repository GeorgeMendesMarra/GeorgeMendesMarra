# 🎨 Tutorial de Bootstrap para Iniciantes Absolutos

Bem-vindo ao mundo do Bootstrap! Este tutorial foi criado para quem nunca teve contato com o framework e quer aprender do zero, de forma simples e prática.

---

## 📌 Índice
1. [O que é Bootstrap?](#-o-que-é-bootstrap)
2. [Por que usar Bootstrap?](#-por-que-usar-bootstrap)
3. [Instalação](#-instalação)
4. [Grid System (Sistema de Grades)](#-grid-system-sistema-de-grades)
5. [Tipografia](#-tipografia)
6. [Cores e Temas](#-cores-e-temas)
7. [Componentes Básicos](#-componentes-básicos)
8. [Componentes Avançados](#-componentes-avançados)
9. [Formulários](#-formulários)
10. [Utilitários](#-utilitários)
11. [Exercícios Práticos](#-exercícios-práticos)
12. [Próximos Passos](#-próximos-passos)

---

## 📖 O que é Bootstrap?

Bootstrap é o **framework front-end mais popular do mundo**, criado pelo Twitter em 2011. Ele fornece componentes prontos e estilizados para criar sites e aplicações responsivas com facilidade.

> 💡 **Pense assim:** Bootstrap é como um "kit de construção" para sites. Você tem blocos prontos (componentes) e só precisa montar sua página!

### Filosofia do Bootstrap
> *"Mobile-first, responsive design"* (Design responsivo com foco em mobile)

---

## 🎯 Por que usar Bootstrap?

| Sem Bootstrap | Com Bootstrap |
|--------------|---------------|
| Criar tudo do zero | Componentes prontos |
| Horas de CSS | Minutos de classes |
| Design inconsistente | Design profissional |
| Responsividade manual | Responsividade automática |

### Vantagens:
✅ **Responsivo por padrão**  
✅ **Componentes prontos e testados**  
✅ **Documentação extensa**  
✅ **Compatibilidade com navegadores**  
✅ **Código consistente e profissional**  
✅ **Comunidade enorme**

---

## 📦 Instalação

### Opção 1: CDN (Recomendado para iniciantes)
```html
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meu Primeiro Bootstrap</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Seu conteúdo aqui -->
    
    <!-- Bootstrap JS (opcional, mas necessário para alguns componentes) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

### Opção 2: Download
Baixe em: [https://getbootstrap.com/docs/5.3/getting-started/download/](https://getbootstrap.com/docs/5.3/getting-started/download/)

### Opção 3: NPM (para projetos maiores)
```bash
npm install bootstrap@5.3.2
```

### ⚠️ Template Básico (Use como ponto de partida!)
```html
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap Template</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Olá, Bootstrap!</h1>
        <p class="lead">Este é um template básico.</p>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

## 🏗️ Grid System (Sistema de Grades)

O coração do Bootstrap! O sistema de grid divide a página em **12 colunas** que se adaptam automaticamente.

### Estrutura Básica
```html
<div class="container">
    <div class="row">
        <div class="col">Coluna 1</div>
        <div class="col">Coluna 2</div>
        <div class="col">Coluna 3</div>
    </div>
</div>
```

### Classes de Colunas
```html
<!-- Colunas iguais -->
<div class="row">
    <div class="col">1/3</div>
    <div class="col">1/3</div>
    <div class="col">1/3</div>
</div>

<!-- Colunas com tamanhos específicos -->
<div class="row">
    <div class="col-4">4 colunas (1/3)</div>
    <div class="col-8">8 colunas (2/3)</div>
</div>

<!-- Colunas responsivas -->
<div class="row">
    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
        <!-- 
            - xs (padrão): 100% 
            - sm (>=576px): 50%
            - md (>=768px): 33.33%
            - lg (>=992px): 25%
        -->
    </div>
</div>
```

### Breakpoints do Bootstrap
| Breakpoint | Largura | Prefixo |
|-----------|---------|---------|
| Extra small | < 576px | `col-` |
| Small | ≥ 576px | `col-sm-` |
| Medium | ≥ 768px | `col-md-` |
| Large | ≥ 992px | `col-lg-` |
| Extra large | ≥ 1200px | `col-xl-` |
| Extra extra large | ≥ 1400px | `col-xxl-` |

### Exemplos Práticos
```html
<!-- Layout de 3 colunas responsivo -->
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <h3>Notícia 1</h3>
            <p>Conteúdo da notícia 1</p>
        </div>
        <div class="col-md-4">
            <h3>Notícia 2</h3>
            <p>Conteúdo da notícia 2</p>
        </div>
        <div class="col-md-4">
            <h3>Notícia 3</h3>
            <p>Conteúdo da notícia 3</p>
        </div>
    </div>
</div>

<!-- Layout com sidebar -->
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h1>Conteúdo Principal</h1>
            <p>Lorem ipsum dolor sit amet...</p>
        </div>
        <div class="col-lg-4">
            <h3>Sidebar</h3>
            <ul>
                <li>Menu 1</li>
                <li>Menu 2</li>
            </ul>
        </div>
    </div>
</div>
```

### Aninhamento e Offset
```html
<!-- Aninhamento (grid dentro de grid) -->
<div class="row">
    <div class="col-9">
        <div class="row">
            <div class="col-6">Sub-coluna 1</div>
            <div class="col-6">Sub-coluna 2</div>
        </div>
    </div>
    <div class="col-3">Sidebar</div>
</div>

<!-- Offset (espaçamento) -->
<div class="row">
    <div class="col-4 offset-4">Centralizado</div>
</div>
```

---

## ✍️ Tipografia

### Títulos
```html
<!-- Títulos padrão -->
<h1>h1. Título Principal</h1>
<h2>h2. Título Secundário</h2>
<h3>h3. Título Terciário</h3>
<h4>h4. Título Quaternário</h4>
<h5>h5. Título Quinário</h5>
<h6>h6. Título Senário</h6>

<!-- Títulos usando classes -->
<p class="h1">Este é um parágrafo com estilo de h1</p>
<p class="h2">Este é um parágrafo com estilo de h2</p>

<!-- Títulos de display (mais destaque) -->
<h1 class="display-1">Display 1</h1>
<h1 class="display-2">Display 2</h1>
<h1 class="display-3">Display 3</h1>
<h1 class="display-4">Display 4</h1>
<h1 class="display-5">Display 5</h1>
<h1 class="display-6">Display 6</h1>
```

### Parágrafos
```html
<!-- Parágrafo padrão -->
<p>Este é um parágrafo normal.</p>

<!-- Parágrafo destacado (lead) -->
<p class="lead">Este é um parágrafo em destaque.</p>

<!-- Alinhamento -->
<p class="text-start">Texto à esquerda</p>
<p class="text-center">Texto centralizado</p>
<p class="text-end">Texto à direita</p>

<!-- Formatação -->
<p class="text-uppercase">texto em maiúsculo</p>
<p class="text-lowercase">TEXTO EM MINÚSCULO</p>
<p class="text-capitalize">texto capitalizado</p>

<!-- Tamanhos -->
<p class="fs-1">Tamanho 1</p>
<p class="fs-2">Tamanho 2</p>
<p class="fs-3">Tamanho 3</p>
<p class="fs-4">Tamanho 4</p>
<p class="fs-5">Tamanho 5</p>
<p class="fs-6">Tamanho 6</p>

<!-- Peso da fonte -->
<p class="fw-bold">Negrito</p>
<p class="fw-bolder">Mais negrito</p>
<p class="fw-semibold">Semi-negrito</p>
<p class="fw-normal">Normal</p>
<p class="fw-light">Leve</p>
<p class="fw-lighter">Mais leve</p>
```

---

## 🎨 Cores e Temas

### Cores Contextuais (Texto)
```html
<p class="text-primary">Texto primário (azul)</p>
<p class="text-secondary">Texto secundário (cinza)</p>
<p class="text-success">Texto sucesso (verde)</p>
<p class="text-danger">Texto perigo (vermelho)</p>
<p class="text-warning">Texto aviso (amarelo)</p>
<p class="text-info">Texto informação (ciano)</p>
<p class="text-light bg-dark">Texto claro (branco)</p>
<p class="text-dark">Texto escuro (preto)</p>
<p class="text-body">Texto corpo</p>
<p class="text-muted">Texto esmaecido</p>
<p class="text-white bg-primary">Texto branco</p>
```

### Cores Contextuais (Fundo)
```html
<div class="bg-primary text-white">Fundo primário</div>
<div class="bg-secondary text-white">Fundo secundário</div>
<div class="bg-success text-white">Fundo sucesso</div>
<div class="bg-danger text-white">Fundo perigo</div>
<div class="bg-warning">Fundo aviso</div>
<div class="bg-info">Fundo informação</div>
<div class="bg-light">Fundo claro</div>
<div class="bg-dark text-white">Fundo escuro</div>

<!-- Gradientes -->
<div class="bg-primary bg-gradient">Fundo com gradiente</div>
```

### Opacidade
```html
<!-- Opacidade de texto -->
<p class="text-primary text-opacity-25">25% opacidade</p>
<p class="text-primary text-opacity-50">50% opacidade</p>
<p class="text-primary text-opacity-75">75% opacidade</p>

<!-- Opacidade de fundo -->
<div class="bg-primary bg-opacity-25">25% opacidade</div>
```

---

## 🧩 Componentes Básicos

### 1. Botões
```html
<!-- Cores -->
<button type="button" class="btn btn-primary">Primário</button>
<button type="button" class="btn btn-secondary">Secundário</button>
<button type="button" class="btn btn-success">Sucesso</button>
<button type="button" class="btn btn-danger">Perigo</button>
<button type="button" class="btn btn-warning">Aviso</button>
<button type="button" class="btn btn-info">Informação</button>
<button type="button" class="btn btn-light">Claro</button>
<button type="button" class="btn btn-dark">Escuro</button>
<button type="button" class="btn btn-link">Link</button>

<!-- Tamanhos -->
<button type="button" class="btn btn-primary btn-lg">Grande</button>
<button type="button" class="btn btn-primary">Normal</button>
<button type="button" class="btn btn-primary btn-sm">Pequeno</button>

<!-- Outlines (bordas) -->
<button type="button" class="btn btn-outline-primary">Primário</button>
<button type="button" class="btn btn-outline-danger">Perigo</button>

<!-- Estados -->
<button type="button" class="btn btn-primary" disabled>Desabilitado</button>
<button type="button" class="btn btn-primary active">Ativo</button>

<!-- Botões com ícones (Font Awesome) -->
<button class="btn btn-primary">
    <i class="fas fa-download"></i> Download
</button>
```

### 2. Cards
```html
<!-- Card básico -->
<div class="card" style="width: 18rem;">
    <img src="imagem.jpg" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title">Título do Card</h5>
        <p class="card-text">Texto descritivo do card.</p>
        <a href="#" class="btn btn-primary">Botão</a>
    </div>
</div>

<!-- Card com cabeçalho e rodapé -->
<div class="card">
    <div class="card-header">
        Cabeçalho
    </div>
    <div class="card-body">
        <h5 class="card-title">Título</h5>
        <p class="card-text">Conteúdo do card</p>
    </div>
    <div class="card-footer text-muted">
        Rodapé
    </div>
</div>

<!-- Cards em grid -->
<div class="row">
    <div class="col-md-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Card 1</h5>
                <p class="card-text">Conteúdo do card 1</p>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Card 2</h5>
                <p class="card-text">Conteúdo do card 2</p>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Card 3</h5>
                <p class="card-text">Conteúdo do card 3</p>
            </div>
        </div>
    </div>
</div>
```

### 3. Alertas
```html
<div class="alert alert-primary" role="alert">
    Alerta primário!
</div>
<div class="alert alert-success" role="alert">
    Alerta de sucesso!
</div>
<div class="alert alert-danger" role="alert">
    Alerta de perigo!
</div>

<!-- Alertas com link -->
<div class="alert alert-warning" role="alert">
    <a href="#" class="alert-link">Clique aqui</a> para mais informações.
</div>

<!-- Alertas com fechamento -->
<div class="alert alert-info alert-dismissible fade show" role="alert">
    <strong>Info!</strong> Este alerta pode ser fechado.
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>
```

### 4. Badges (Etiquetas)
```html
<h1>Exemplo <span class="badge bg-primary">Novo</span></h1>
<h2>Exemplo <span class="badge bg-success">Sucesso</span></h2>
<h3>Exemplo <span class="badge bg-danger">Perigo</span></h3>

<!-- Badges em botões -->
<button class="btn btn-primary">
    Notificações <span class="badge bg-light text-dark">4</span>
</button>

<!-- Badges arredondadas -->
<span class="badge rounded-pill bg-primary">Primário</span>
<span class="badge rounded-pill bg-success">Sucesso</span>
```

### 5. Progress Bars
```html
<!-- Básica -->
<div class="progress">
    <div class="progress-bar" style="width: 25%">25%</div>
</div>

<!-- Com cores -->
<div class="progress">
    <div class="progress-bar bg-success" style="width: 50%">50%</div>
</div>

<!-- Com listras -->
<div class="progress">
    <div class="progress-bar progress-bar-striped" style="width: 75%">75%</div>
</div>

<!-- Animada -->
<div class="progress">
    <div class="progress-bar progress-bar-striped progress-bar-animated" style="width: 60%">60%</div>
</div>

<!-- Múltiplas barras -->
<div class="progress">
    <div class="progress-bar bg-success" style="width: 40%">40%</div>
    <div class="progress-bar bg-warning" style="width: 30%">30%</div>
    <div class="progress-bar bg-danger" style="width: 30%">30%</div>
</div>
```

---

## 🚀 Componentes Avançados

### 1. Navbar (Menu de Navegação)
```html
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <!-- Logo/Marca -->
        <a class="navbar-brand" href="#">Meu Site</a>
        
        <!-- Botão do menu mobile -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <!-- Menu -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sobre</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">
                        Serviços
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Serviço 1</a></li>
                        <li><a class="dropdown-item" href="#">Serviço 2</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Serviço 3</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link">Contato</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
```

### 2. Carrossel (Slides)
```html
<div id="meuCarrossel" class="carousel slide" data-bs-ride="carousel">
    <!-- Indicadores -->
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#meuCarrossel" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#meuCarrossel" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#meuCarrossel" data-bs-slide-to="2"></button>
    </div>
    
    <!-- Slides -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="slide1.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption">
                <h3>Primeiro Slide</h3>
                <p>Descrição do primeiro slide</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="slide2.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="slide3.jpg" class="d-block w-100" alt="...">
        </div>
    </div>
    
    <!-- Controles -->
    <button class="carousel-control-prev" data-bs-target="#meuCarrossel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </button>
    <button class="carousel-control-next" data-bs-target="#meuCarrossel" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
    </button>
</div>
```

### 3. Modal (Janela Modal)
```html
<!-- Botão para abrir -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#meuModal">
    Abrir Modal
</button>

<!-- Modal -->
<div class="modal fade" id="meuModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Título do Modal</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>Conteúdo do modal aqui...</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary">Salvar</button>
            </div>
        </div>
    </div>
</div>
```

### 4. Accordion
```html
<div class="accordion" id="accordionExample">
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1">
                Item 1
            </button>
        </h2>
        <div id="collapse1" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
            <div class="accordion-body">
                Conteúdo do item 1.
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2">
                Item 2
            </button>
        </h2>
        <div id="collapse2" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
            <div class="accordion-body">
                Conteúdo do item 2.
            </div>
        </div>
    </div>
</div>
```

### 5. Tabs
```html
<!-- Navegação -->
<ul class="nav nav-tabs" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#tab1">Tab 1</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab2">Tab 2</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab3">Tab 3</button>
    </li>
</ul>

<!-- Conteúdo -->
<div class="tab-content">
    <div id="tab1" class="tab-pane fade show active">
        <p>Conteúdo da Tab 1</p>
    </div>
    <div id="tab2" class="tab-pane fade">
        <p>Conteúdo da Tab 2</p>
    </div>
    <div id="tab3" class="tab-pane fade">
        <p>Conteúdo da Tab 3</p>
    </div>
</div>
```

---

## 📝 Formulários

### Formulário Básico
```html
<form>
    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" placeholder="Seu nome">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" placeholder="seu@email.com">
    </div>
    <div class="mb-3">
        <label for="senha" class="form-label">Senha</label>
        <input type="password" class="form-control" id="senha">
    </div>
    <div class="mb-3">
        <label for="select" class="form-label">Selecione</label>
        <select class="form-select" id="select">
            <option selected>Opção 1</option>
            <option>Opção 2</option>
            <option>Opção 3</option>
        </select>
    </div>
    <div class="mb-3">
        <label for="textarea" class="form-label">Mensagem</label>
        <textarea class="form-control" id="textarea" rows="3"></textarea>
    </div>
    <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="check">
        <label class="form-check-label" for="check">Aceito os termos</label>
    </div>
    <button type="submit" class="btn btn-primary">Enviar</button>
</form>
```

### Validação
```html
<form class="needs-validation" novalidate>
    <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" id="nome" required>
        <div class="valid-feedback">Nome válido!</div>
        <div class="invalid-feedback">Por favor, digite seu nome.</div>
    </div>
    <button type="submit" class="btn btn-primary">Enviar</button>
</form>

<script>
// Validação com JavaScript
(function() {
    'use strict';
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
})();
</script>
```

---

## 🛠️ Utilitários

### Espaçamento (Margin e Padding)
```html
<!-- Margin -->
<div class="m-0">m-0</div>
<div class="m-1">m-1 (0.25rem)</div>
<div class="m-2">m-2 (0.5rem)</div>
<div class="m-3">m-3 (1rem)</div>
<div class="m-4">m-4 (1.5rem)</div>
<div class="m-5">m-5 (3rem)</div>

<!-- Margin específica -->
<div class="mt-2">margin-top</div>
<div class="mb-2">margin-bottom</div>
<div class="ms-2">margin-start (left)</div>
<div class="me-2">margin-end (right)</div>
<div class="mx-2">margin horizontal</div>
<div class="my-2">margin vertical</div>

<!-- Padding -->
<div class="p-3">padding 3</div>
<div class="pt-2">padding-top</div>
<div class="pb-2">padding-bottom</div>
```

### Borders
```html
<!-- Bordas -->
<div class="border">Borda padrão</div>
<div class="border border-primary">Borda primária</div>
<div class="border border-danger">Borda perigo</div>
<div class="border border-3">Borda grossa</div>

<!-- Arredondamento -->
<div class="rounded">Arredondado</div>
<div class="rounded-circle" style="width: 100px; height: 100px;">Círculo</div>
<div class="rounded-pill">Pílula</div>
<div class="rounded-0">Sem arredondamento</div>
<div class="rounded-1">Arredondado pequeno</div>
<div class="rounded-2">Arredondado médio</div>
<div class="rounded-3">Arredondado grande</div>
```

### Display e Posicionamento
```html
<!-- Display -->
<div class="d-none">Escondido</div>
<div class="d-block">Display block</div>
<div class="d-inline">Display inline</div>
<div class="d-inline-block">Display inline-block</div>
<div class="d-flex">Display flex</div>
<div class="d-grid">Display grid</div>

<!-- Flex -->
<div class="d-flex justify-content-center">Centralizado</div>
<div class="d-flex justify-content-between">Espaçado</div>
<div class="d-flex align-items-center">Alinhado ao centro</div>
<div class="d-flex gap-3">Espaçamento entre elementos</div>

<!-- Responsivo -->
<div class="d-none d-md-block">Escondido em mobile, visível em desktop</div>
<div class="d-block d-md-none">Visível em mobile, escondido em desktop</div>
```

### Sombra
```html
<div class="shadow-none">Sem sombra</div>
<div class="shadow-sm">Sombra pequena</div>
<div class="shadow">Sombra padrão</div>
<div class="shadow-lg">Sombra grande</div>
```

---

## 💻 Exercícios Práticos

### Exercício 1: Página de Portfólio
```html
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meu Portfólio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Meu Portfólio</a>
            <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Projetos</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Sobre</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Contato</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="bg-primary text-white text-center py-5">
        <div class="container">
            <h1 class="display-2">João Silva</h1>
            <p class="lead">Desenvolvedor Web Full Stack</p>
            <button class="btn btn-light btn-lg">Contato</button>
        </div>
    </section>

    <!-- Projetos -->
    <section class="container py-5">
        <h2 class="text-center mb-4">Meus Projetos</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Projeto 1</h5>
                        <p class="card-text">Descrição do projeto 1</p>
                        <a href="#" class="btn btn-primary">Ver mais</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Projeto 2</h5>
                        <p class="card-text">Descrição do projeto 2</p>
                        <a href="#" class="btn btn-primary">Ver mais</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Projeto 3</h5>
                        <p class="card-text">Descrição do projeto 3</p>
                        <a href="#" class="btn btn-primary">Ver mais</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Rodapé -->
    <footer class="bg-dark text-white text-center py-3">
        <p class="mb-0">© 2024 Meu Portfólio. Todos direitos reservados.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

### Exercício 2: Formulário de Contato com Validação
```html
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contato</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Entre em Contato</h3>
                    </div>
                    <div class="card-body">
                        <form id="formContato" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" class="form-control" id="nome" required>
                                <div class="invalid-feedback">Por favor, digite seu nome.</div>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" required>
                                <div class="invalid-feedback">Por favor, digite um email válido.</div>
                            </div>
                            <div class="mb-3">
                                <label for="assunto" class="form-label">Assunto</label>
                                <select class="form-select" id="assunto" required>
                                    <option value="">Selecione...</option>
                                    <option>Dúvida</option>
                                    <option>Sugestão</option>
                                    <option>Orçamento</option>
                                    <option>Outros</option>
                                </select>
                                <div class="invalid-feedback">Por favor, selecione um assunto.</div>
                            </div>
                            <div class="mb-3">
                                <label for="mensagem" class="form-label">Mensagem</label>
                                <textarea class="form-control" id="mensagem" rows="4" required></textarea>
                                <div class="invalid-feedback">Por favor, digite sua mensagem.</div>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="termos" required>
                                <label class="form-check-label" for="termos">Aceito os termos e condições</label>
                                <div class="invalid-feedback">Você deve aceitar os termos.</div>
                            </div>
                            <button type="submit" class="btn btn-primary">Enviar</button>
                            <button type="reset" class="btn btn-secondary">Limpar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        (function() {
            'use strict';
            const form = document.getElementById('formContato');
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    event.preventDefault();
                    alert('Formulário enviado com sucesso!');
                    form.reset();
                    form.classList.remove('was-validated');
                }
                form.classList.add('was-validated');
            }, false);
        })();
    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

### Exercício 3: Página de Produtos com Grid
```html
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loja Virtual</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container py-4">
        <h1 class="text-center mb-4">Nossos Produtos</h1>
        
        <!-- Filtros -->
        <div class="row mb-4">
            <div class="col-md-6 mx-auto">
                <div class="btn-group w-100" role="group">
                    <button class="btn btn-outline-primary active" data-filter="todos">Todos</button>
                    <button class="btn btn-outline-primary" data-filter="eletronicos">Eletrônicos</button>
                    <button class="btn btn-outline-primary" data-filter="roupas">Roupas</button>
                    <button class="btn btn-outline-primary" data-filter="livros">Livros</button>
                </div>
            </div>
        </div>
        
        <!-- Produtos -->
        <div class="row" id="produtos">
            <!-- Card 1 -->
            <div class="col-md-3 col-6 mb-4" data-categoria="eletronicos">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Smartphone</h5>
                        <p class="card-text">R$ 1.999,00</p>
                        <button class="btn btn-primary btn-sm">Comprar</button>
                    </div>
                </div>
            </div>
            <!-- Card 2 -->
            <div class="col-md-3 col-6 mb-4" data-categoria="eletronicos">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Notebook</h5>
                        <p class="card-text">R$ 3.499,00</p>
                        <button class="btn btn-primary btn-sm">Comprar</button>
                    </div>
                </div>
            </div>
            <!-- Card 3 -->
            <div class="col-md-3 col-6 mb-4" data-categoria="roupas">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Camiseta</h5>
                        <p class="card-text">R$ 49,90</p>
                        <button class="btn btn-primary btn-sm">Comprar</button>
                    </div>
                </div>
            </div>
            <!-- Card 4 -->
            <div class="col-md-3 col-6 mb-4" data-categoria="livros">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">Livro JavaScript</h5>
                        <p class="card-text">R$ 89,90</p>
                        <button class="btn btn-primary btn-sm">Comprar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        // Filtro de produtos
        document.querySelectorAll('[data-filter]').forEach(btn => {
            btn.addEventListener('click', function() {
                const filter = this.dataset.filter;
                
                // Atualiza botões ativos
                document.querySelectorAll('[data-filter]').forEach(b => 
                    b.classList.remove('active')
                );
                this.classList.add('active');
                
                // Filtra produtos
                document.querySelectorAll('#produtos .col-md-3').forEach(produto => {
                    if (filter === 'todos' || produto.dataset.categoria === filter) {
                        produto.style.display = 'block';
                    } else {
                        produto.style.display = 'none';
                    }
                });
            });
        });
    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

## 🎯 Dicas e Truques

### 1. Personalização com CSS
```css
/* Sobrescrevendo classes do Bootstrap */
.btn-primary {
    background-color: #ff6b6b;
    border-color: #ff6b6b;
}

.btn-primary:hover {
    background-color: #ee5a24;
    border-color: #ee5a24;
}

/* Criando variações */
.btn-purple {
    background-color: #6c5ce7;
    border-color: #6c5ce7;
    color: white;
}

.btn-purple:hover {
    background-color: #5f3dc4;
    border-color: #5f3dc4;
    color: white;
}
```

### 2. Framework + JavaScript
```javascript
// Manipulando componentes via JavaScript
const modal = new bootstrap.Modal(document.getElementById('meuModal'));
modal.show(); // Abrir modal
modal.hide(); // Fechar modal

// Tooltips
const tooltips = document.querySelectorAll('[data-bs-toggle="tooltip"]');
tooltips.forEach(t => new bootstrap.Tooltip(t));

// Popovers
const popovers = document.querySelectorAll('[data-bs-toggle="popover"]');
popovers.forEach(p => new bootstrap.Popover(p));
```

### 3. Classes Úteis
```html
<!-- Container fluido (100% largura) -->
<div class="container-fluid">Conteúdo fluido</div>

<!-- Visibilidade -->
<div class="visible">Visível</div>
<div class="invisible">Invisível (ocupa espaço)</div>

<!-- Overflow -->
<div class="overflow-auto">Scroll automático</div>
<div class="overflow-hidden">Esconder overflow</div>

<!-- User Select -->
<div class="user-select-all">Selecionar tudo</div>
<div class="user-select-none">Não selecionável</div>

<!-- Pointer Events -->
<div class="pe-none">Não recebe eventos de mouse</div>
<div class="pe-auto">Recebe eventos de mouse</div>
```

---

## 📚 Recursos e Próximos Passos

### Documentação Oficial
- **Bootstrap 5**: [getbootstrap.com/docs/5.3](https://getbootstrap.com/docs/5.3/)
- **Exemplos**: [getbootstrap.com/docs/5.3/examples](https://getbootstrap.com/docs/5.3/examples/)
- **Ícones**: [icons.getbootstrap.com](https://icons.getbootstrap.com/)

### Temas e Templates
- **Bootstrap Themes**: [themes.getbootstrap.com](https://themes.getbootstrap.com/)
- **Start Bootstrap**: [startbootstrap.com](https://startbootstrap.com/)
- **Bootswatch**: [bootswatch.com](https://bootswatch.com/) (temas gratuitos)

### Plugins Úteis
- **Font Awesome**: Ícones
- **DataTables**: Tabelas dinâmicas
- **Chart.js**: Gráficos
- **Select2**: Selects melhorados
- **SweetAlert2**: Alertas bonitos

### Dicas Finais
- 🧠 **Pratique construindo páginas completas**
- 📖 **Sempre consulte a documentação**
- 🎨 **Experimente personalizar o Bootstrap**
- 🔍 **Use o DevTools para inspecionar classes**
- 📱 **Teste sempre em dispositivos móveis**

---

**Bons projetos!** 🚀
