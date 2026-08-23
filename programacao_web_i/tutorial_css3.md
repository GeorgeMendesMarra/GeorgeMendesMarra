# Tutorial de CSS3 para Front-End

> Guia introdutório sobre CSS3, mostrando — via comentários no código — como cada regra se conecta às tags HTML5 vistas no tutorial anterior. Inclui também uma seção sobre propriedades e recursos obsoletos do CSS.

## Sumário

1. [O que é CSS3](#1-o-que-é-css3)
2. [Como o CSS se conecta ao HTML](#2-como-o-css-se-conecta-ao-html)
3. [Seletores e sua relação com as tags HTML5](#3-seletores-e-sua-relação-com-as-tags-html5)
4. [Box Model](#4-box-model)
5. [Flexbox](#5-flexbox)
6. [Grid Layout](#6-grid-layout)
7. [Estilizando formulários e mídia do HTML5](#7-estilizando-formulários-e-mídia-do-html5)
8. [Animações e transições](#8-animações-e-transições)
9. [Responsividade com Media Queries](#9-responsividade-com-media-queries)
10. [Propriedades e recursos obsoletos do CSS](#10-propriedades-e-recursos-obsoletos-do-css)
11. [Boas práticas modernas](#11-boas-práticas-modernas)
12. [Exercícios propostos](#12-exercícios-propostos)

---

## 1. O que é CSS3

CSS3 é a versão atual do CSS (Cascading Style Sheets), a linguagem responsável por definir a **apresentação visual** de documentos HTML. Diferente do HTML5 (que define estrutura/semântica), o CSS3 cuida de:

- Cores, tipografia e espaçamento
- Layout (Flexbox, Grid)
- Animações e transições
- Responsividade (Media Queries)
- Efeitos visuais (sombras, gradientes, transformações)

CSS3 não é uma única especificação monolítica como o CSS2 era — é dividida em **módulos** (Selectors, Flexbox, Grid, Animations, etc.), cada um evoluindo de forma independente.

---

## 2. Como o CSS se conecta ao HTML

Existem três formas de ligar CSS a um documento HTML5:

```html
<!-- 1. CSS externo (recomendado): liga o <head> do HTML5 a um arquivo .css -->
<link rel="stylesheet" href="estilos.css">

<!-- 2. CSS interno: bloco <style> dentro do <head> -->
<style>
  body { font-family: sans-serif; }
</style>

<!-- 3. CSS inline: atributo style diretamente na tag (evitar!) -->
<p style="color: red;">Texto</p>
```

A abordagem externa é a preferida porque **separa estrutura (HTML) de apresentação (CSS)** — exatamente o motivo pelo qual tags obsoletas como `<font>` e `<center>` foram abandonadas no HTML5.

---

## 3. Seletores e sua relação com as tags HTML5

Cada seletor CSS "mira" diretamente nas tags que vimos no tutorial de HTML5. Veja com comentários explicando essa relação:

```css
/* Seleciona a tag semântica <header> do HTML5 */
header {
  background-color: #222;
  color: white;
  padding: 1rem;
}

/* Seleciona a tag <nav>, usada para o menu de navegação */
nav ul {
  display: flex;       /* organiza os <li> do menu em linha */
  list-style: none;    /* remove os marcadores padrão da <ul> */
}

/* Seleciona a tag <main>, que contém o conteúdo principal da página */
main {
  max-width: 960px;
  margin: 0 auto;       /* substitui o antigo <center> do HTML */
}

/* Seleciona todo <article> dentro de <main> */
main article {
  border-bottom: 1px solid #ccc;
  padding: 1rem 0;
}

/* Seleciona o <time> usado dentro de <article> para exibir datas */
article time {
  color: gray;
  font-size: 0.9rem;
}

/* Seleciona <aside>, usado para conteúdo secundário (sidebar) */
aside {
  background: #f4f4f4;
  padding: 1rem;
}

/* Seleciona <footer>, rodapé da página */
footer {
  text-align: center;   /* substitui o atributo obsoleto align="center" */
}
```

### Outros tipos de seletores

```css
/* Seletor de classe: aplica-se a qualquer elemento com class="destaque" */
.destaque {
  background-color: yellow;
}

/* Seletor de ID: aplica-se ao elemento único com id="topo" */
#topo {
  font-weight: bold;
}

/* Seletor de atributo: mira em <input type="email"> do formulário HTML5 */
input[type="email"] {
  border: 2px solid steelblue;
}

/* Pseudo-classe: estiliza o estado de foco de qualquer input */
input:focus {
  outline: 2px solid orange;
}

/* Pseudo-elemento: insere conteúdo antes de cada <h2> */
h2::before {
  content: "▶ ";
}
```

---

## 4. Box Model

Todo elemento HTML5 é tratado pelo navegador como uma "caixa". O CSS3 controla essa caixa com:

```css
/* Aplica-se a qualquer <div>, <article>, <section>, etc. */
.caixa {
  width: 300px;
  padding: 20px;      /* espaço interno, entre o conteúdo e a borda */
  border: 1px solid #333;
  margin: 10px;        /* espaço externo, entre a caixa e outros elementos */
  box-sizing: border-box; /* inclui padding e border dentro do width definido */
}
```

---

## 5. Flexbox

Flexbox é o módulo do CSS3 ideal para organizar elementos como `<nav>`, `<ul>` de menus, ou grupos de `<article>`:

```css
/* O <nav> do HTML5 vira um container flexível */
nav {
  display: flex;
  justify-content: space-between; /* distribui os itens do menu */
  align-items: center;
}

/* Os <a> dentro do <nav> se tornam itens flexíveis */
nav a {
  margin-left: 1rem;
}
```

---

## 6. Grid Layout

O CSS Grid é perfeito para organizar o layout geral da página, ligando `<header>`, `<main>`, `<aside>` e `<footer>` do HTML5:

```css
/* O <body> vira a grade principal da página */
body {
  display: grid;
  grid-template-areas:
    "header header"
    "main   aside"
    "footer footer";
  grid-template-columns: 3fr 1fr;
}

header { grid-area: header; }
main   { grid-area: main; }
aside  { grid-area: aside; }
footer { grid-area: footer; }
```

---

## 7. Estilizando formulários e mídia do HTML5

```css
/* Estiliza os novos tipos de input do HTML5 (email, date, range, color, etc.) */
input[type="date"],
input[type="email"],
input[type="tel"] {
  padding: 0.5rem;
  border-radius: 4px;
}

/* Estiliza o <video> do HTML5 para ser responsivo */
video {
  width: 100%;
  max-width: 480px;
  border-radius: 8px;
}

/* Estiliza o player de <audio> do HTML5 */
audio {
  width: 100%;
}

/* Estiliza o <canvas>, útil para dar uma borda de referência durante o desenvolvimento */
canvas {
  border: 1px dashed #999;
}
```

---

## 8. Animações e transições

Substituem, com muito mais controle, o antigo comportamento do `<marquee>` e do `<blink>`:

```css
/* Transição suave ao passar o mouse sobre qualquer <button> */
button {
  transition: background-color 0.3s ease;
}
button:hover {
  background-color: steelblue;
}

/* Animação que substitui o antigo <marquee> */
.aviso {
  white-space: nowrap;
  animation: mover 8s linear infinite;
}
@keyframes mover {
  from { transform: translateX(100%); }
  to   { transform: translateX(-100%); }
}

/* Animação que substitui o antigo <blink> */
.piscando {
  animation: piscar 1s step-start infinite;
}
@keyframes piscar {
  50% { opacity: 0; }
}
```

---

## 9. Responsividade com Media Queries

Trabalha em conjunto com a `<meta name="viewport">` do HTML5:

```css
/* Estilo padrão para telas grandes */
main {
  grid-template-columns: 3fr 1fr;
}

/* Ajusta o layout em telas menores que 768px (tablets/celulares) */
@media (max-width: 768px) {
  body {
    grid-template-areas:
      "header"
      "main"
      "aside"
      "footer";
    grid-template-columns: 1fr;
  }
}
```

---

## 10. Propriedades e recursos obsoletos do CSS

Assim como o HTML5 aposentou tags de apresentação, o CSS também deixou para trás propriedades, valores e práticas de versões antigas (CSS2/CSS2.1) ou que nunca avançaram além de rascunho. Reconhecer esses itens ajuda a entender código legado.

| Propriedade/recurso obsoleto | O que fazia | Substituto moderno |
|---|---|---|
| `clip` | Recortava a área visível de um elemento posicionado | `clip-path` |
| `filter` com sintaxe antiga do IE (`progid:DXImageTransform...`) | Aplicava filtros visuais só no Internet Explorer | `filter` padrão (`blur()`, `brightness()`, etc.) |
| `zoom` (não padrão, específico do IE) | Ampliava/reduzia elementos | `transform: scale()` |
| `*zoom` / hacks de "star property" | Truque para aplicar CSS somente em versões antigas do IE | Feature queries (`@supports`) ou Autoprefixer |
| `-moz-box` / `-webkit-box` (Flexbox antigo, "display: box") | Sintaxe inicial e incompleta do Flexbox | `display: flex` (Flexbox padrão atual) |
| `float` + `clear` como base de layout | Criava colunas e layouts "empurrando" elementos | `display: flex` ou `display: grid` |
| `vertical-align` para centralizar blocos inteiros | Tentava centralizar verticalmente elementos de bloco | `align-items: center` (Flexbox/Grid) |
| `table`/`table-cell` como hack de layout (`display: table-cell`) | Simulava grades usando comportamento de tabela | `display: grid` |
| Prefixos vendor obsoletos (`-khtml-`, `-o-` em várias propriedades) | Suporte a engines de navegadores descontinuados (KHTML, Opera Presto) | Propriedades padrão sem prefixo |
| `page-break-before` / `page-break-after` | Controlava quebras de página na impressão | `break-before` / `break-after` |
| Unidades `pt`/`pc` para telas | Unidades de origem tipográfica, imprecisas em telas | `rem`, `em`, `%`, `vw`/`vh` |

### Exemplo comparativo

**Código antigo (hacks de CSS2 / início dos anos 2000):**

```css
/* Layout de duas colunas usando float — abordagem antiga */
.coluna-esquerda {
  float: left;
  width: 70%;
}
.coluna-direita {
  float: right;
  width: 30%;
}
.container::after {
  content: "";
  display: block;
  clear: both; /* hack para "limpar" o float */
}
```

**Equivalente moderno em CSS3 (Flexbox):**

```css
/* Layout de duas colunas usando Flexbox — abordagem atual */
.container {
  display: flex;
}
.coluna-esquerda {
  flex: 7; /* ocupa 70% do espaço disponível */
}
.coluna-direita {
  flex: 3; /* ocupa 30% do espaço disponível */
}
```

> **Por que isso importa para os alunos:** hacks baseados em `float`, `zoom` e prefixos de engines descontinuadas existiam para contornar limitações de navegadores antigos (como Internet Explorer 6-8). Com Flexbox e Grid nativos e amplamente suportados, essas técnicas se tornaram desnecessárias e devem ser evitadas em projetos novos.

---

## 11. Boas práticas modernas

- Prefira Flexbox/Grid a `float` para layout.
- Use `rem`/`em` em vez de `px` para tipografia, visando acessibilidade.
- Combine `class` (reutilizável) em vez de `id` (único) para estilização.
- Utilize `@supports` para checar suporte a recursos modernos, em vez de hacks de navegador.
- Mantenha o CSS em arquivo externo, nunca inline no `style=""`.
- Valide o CSS em [jigsaw.w3.org/css-validator](https://jigsaw.w3.org/css-validator/).

---

## 12. Exercícios propostos

1. Pegue a página HTML5 criada no tutorial anterior (`<header>`, `<nav>`, `<main>`, `<article>`, `<footer>`) e escreva um arquivo `estilos.css` estilizando cada tag semântica, com um comentário acima de cada regra explicando a que tag ela se refere.
2. Recrie o menu de navegação (`<nav>`) usando Flexbox.
3. Construa um layout de página inteira usando CSS Grid, ligando `header`, `main`, `aside` e `footer`.
4. Pegue um trecho de CSS antigo com `float` e `clear: both` e reescreva-o usando Flexbox ou Grid.
5. Pesquise e liste mais 2 propriedades ou hacks de CSS considerados obsoletos hoje.
