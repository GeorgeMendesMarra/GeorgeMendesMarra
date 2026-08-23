# Tutorial de HTML5 para Front-End

> Guia introdutório sobre HTML5, com uma seção especial sobre tags obsoletas de versões antigas do HTML — importante para reconhecer código legado e entender por que a linguagem evoluiu.

## Sumário

1. [O que é HTML5](#1-o-que-é-html5)
2. [Estrutura básica de um documento](#2-estrutura-básica-de-um-documento)
3. [Tags semânticas do HTML5](#3-tags-semânticas-do-html5)
4. [Formulários e novos tipos de input](#4-formulários-e-novos-tipos-de-input)
5. [Mídia nativa: áudio e vídeo](#5-mídia-nativa-áudio-e-vídeo)
6. [Elementos gráficos: Canvas e SVG](#6-elementos-gráficos-canvas-e-svg)
7. [Atributos globais úteis](#7-atributos-globais-úteis)
8. [Tags e atributos obsoletos (versões antigas do HTML)](#8-tags-e-atributos-obsoletos-versões-antigas-do-html)
9. [Boas práticas modernas](#9-boas-práticas-modernas)
10. [Exercícios propostos](#10-exercícios-propostos)

---

## 1. O que é HTML5

HTML5 é a quinta e atual versão da linguagem de marcação usada para estruturar páginas web. Foi padronizada pelo W3C e trouxe:

- Tags semânticas (`<header>`, `<footer>`, `<article>`, etc.)
- Suporte nativo a áudio e vídeo, sem plugins como Flash
- Novos tipos de campos de formulário
- APIs JavaScript (Canvas, Geolocation, Drag and Drop, Web Storage, etc.)
- Abandono de diversas tags de apresentação em favor do CSS

---

## 2. Estrutura básica de um documento

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Meu primeiro documento HTML5</title>
</head>
<body>
  <h1>Olá, mundo!</h1>
  <p>Este é um parágrafo de exemplo.</p>
</body>
</html>
```

Pontos importantes:

- `<!DOCTYPE html>` é a declaração simplificada do HTML5 (nas versões antigas era bem mais longa e complexa).
- `<meta charset="UTF-8">` evita problemas de acentuação.
- `<meta name="viewport">` é essencial para responsividade.

---

## 3. Tags semânticas do HTML5

Antes do HTML5, os desenvolvedores usavam `<div>` para tudo. Hoje temos tags que descrevem o significado do conteúdo:

| Tag | Uso |
|---|---|
| `<header>` | Cabeçalho da página ou de uma seção |
| `<nav>` | Menu de navegação |
| `<main>` | Conteúdo principal da página |
| `<section>` | Agrupamento temático de conteúdo |
| `<article>` | Conteúdo independente e autocontido (post, notícia) |
| `<aside>` | Conteúdo relacionado, mas secundário (sidebar) |
| `<footer>` | Rodapé da página ou de uma seção |
| `<figure>` / `<figcaption>` | Imagem/gráfico com legenda |
| `<time>` | Datas e horários |
| `<mark>` | Texto destacado/realçado |

Exemplo:

```html
<article>
  <header>
    <h2>Título do post</h2>
    <time datetime="2026-08-23">23 de agosto de 2026</time>
  </header>
  <p>Conteúdo do artigo...</p>
  <footer>Autor: Fulano</footer>
</article>
```

---

## 4. Formulários e novos tipos de input

O HTML5 trouxe validação nativa e novos tipos de `<input>`:

```html
<form>
  <label for="email">E-mail:</label>
  <input type="email" id="email" name="email" required>

  <label for="data">Data:</label>
  <input type="date" id="data" name="data">

  <label for="cor">Cor favorita:</label>
  <input type="color" id="cor" name="cor">

  <label for="faixa">Volume:</label>
  <input type="range" id="faixa" min="0" max="100">

  <label for="tel">Telefone:</label>
  <input type="tel" id="tel" pattern="[0-9]{11}">

  <button type="submit">Enviar</button>
</form>
```

Outros tipos novos: `search`, `url`, `number`, `week`, `month`, `datetime-local`.

---

## 5. Mídia nativa: áudio e vídeo

```html
<video controls width="480">
  <source src="video.mp4" type="video/mp4">
  <source src="video.webm" type="video/webm">
  Seu navegador não suporta o elemento de vídeo.
</video>

<audio controls>
  <source src="musica.mp3" type="audio/mpeg">
  Seu navegador não suporta o elemento de áudio.
</audio>
```

Antes do HTML5, isso exigia plugins como Flash ou Silverlight.

---

## 6. Elementos gráficos: Canvas e SVG

```html
<canvas id="meuCanvas" width="200" height="100"></canvas>

<svg width="100" height="100">
  <circle cx="50" cy="50" r="40" stroke="black" fill="tomato" />
</svg>
```

- `<canvas>`: desenho via JavaScript (jogos, gráficos dinâmicos).
- `<svg>`: gráficos vetoriais escaláveis, definidos diretamente em XML/HTML.

---

## 7. Atributos globais úteis

| Atributo | Função |
|---|---|
| `data-*` | Armazena dados customizados (ex: `data-id="42"`) |
| `contenteditable` | Torna o elemento editável pelo usuário |
| `draggable` | Habilita arrastar (Drag and Drop API) |
| `hidden` | Oculta o elemento |
| `spellcheck` | Ativa/desativa verificação ortográfica |

---

## 8. Tags e atributos obsoletos (versões antigas do HTML)

Estas tags e atributos existiam em HTML 3.2/4.01 e foram **descontinuados (deprecated)** no HTML5. É importante que os alunos os reconheçam ao encontrar código legado, mas **não devem usá-los em projetos novos** — a função visual de todos eles deve ser feita com CSS.

| Tag/Atributo obsoleto | O que fazia | Substituto moderno (CSS) |
|---|---|---|
| `<center>` | Centralizava conteúdo | `text-align: center` / `margin: auto` |
| `<font>` | Definia fonte, cor e tamanho do texto | `font-family`, `color`, `font-size` |
| `<marquee>` | Criava texto/imagem em movimento | `@keyframes` + `animation` |
| `<blink>` | Fazia o texto piscar | `animation` com `opacity` |
| `<big>` | Aumentava o tamanho do texto | `font-size: larger` |
| `<strike>` | Texto riscado | `<s>` (semântica) ou `text-decoration: line-through` |
| `<tt>` | Texto monoespaçado (teletype) | `<code>` ou `font-family: monospace` |
| `<acronym>` | Definia siglas | `<abbr>` |
| `<applet>` | Incorporava applets Java | `<object>` ou `<embed>` |
| `<basefont>` | Definia fonte padrão da página inteira | CSS no `<body>` |
| `<dir>` | Lista de diretórios | `<ul>` |
| `<frame>` / `<frameset>` / `<noframes>` | Dividia a página em quadros (frames) | `<iframe>` (com uso restrito) ou layout com CSS |
| `<isindex>` | Criava um campo de busca simples no topo da página | `<form>` com `<input type="search">` |
| `<xmp>` | Exibia texto pré-formatado (semelhante a `<pre>`) | `<pre>` ou `<code>` |

### Atributos de apresentação obsoletos (usados dentro de tags válidas)

| Atributo | Exemplo antigo | Substituto |
|---|---|---|
| `bgcolor` | `<body bgcolor="yellow">` | `background-color` (CSS) |
| `align` | `<div align="center">` | `text-align` ou Flexbox/Grid |
| `border` (em `<img>`) | `<img border="1">` | `border` (CSS) |
| `color` (em `<hr>`) | `<hr color="red">` | `border-color` (CSS) |
| `width`/`height` em `<table>`, `<td>` | `<td width="100">` | `width`/`height` (CSS) |
| `cellpadding`/`cellspacing` | `<table cellpadding="5">` | `padding`/`border-spacing` (CSS) |

### Exemplo comparativo

**Código antigo (HTML 4 / não recomendado):**

```html
<center>
  <font color="red" size="5">Bem-vindo!</font>
</center>
<marquee>Promoção por tempo limitado!</marquee>
```

**Equivalente moderno em HTML5 + CSS:**

```html
<p class="titulo">Bem-vindo!</p>
<p class="promocao">Promoção por tempo limitado!</p>
```

```css
.titulo {
  text-align: center;
  color: red;
  font-size: 2rem;
}

.promocao {
  animation: mover 8s linear infinite;
  white-space: nowrap;
}

@keyframes mover {
  from { transform: translateX(100%); }
  to { transform: translateX(-100%); }
}
```

> **Por que isso importa para os alunos:** entender tags obsoletas ajuda a ler código antigo em manutenção de sistemas legados, mas o objetivo do HTML5 foi separar **estrutura** (HTML) de **apresentação** (CSS). Usar essas tags hoje é considerado má prática e pode até gerar erros de validação.

---

## 9. Boas práticas modernas

- Sempre separar estrutura (HTML), estilo (CSS) e comportamento (JavaScript).
- Usar tags semânticas em vez de `<div>` genérica sempre que possível.
- Validar o HTML em [validator.w3.org](https://validator.w3.org).
- Garantir acessibilidade: `alt` em imagens, `label` em formulários, hierarquia correta de headings.
- Testar responsividade com a meta tag `viewport`.

---

## 10. Exercícios propostos

1. Crie uma página HTML5 com `<header>`, `<nav>`, `<main>`, `<article>` e `<footer>`.
2. Construa um formulário de cadastro usando pelo menos 4 tipos diferentes de `<input>` do HTML5.
3. Pegue um trecho de código HTML "antigo" (usando `<font>`, `<center>` e `<marquee>`) e reescreva-o em HTML5 + CSS.
4. Insira um vídeo e um áudio na página usando as tags nativas do HTML5.
5. Pesquise e liste mais 2 tags obsoletas não mencionadas neste tutorial.
