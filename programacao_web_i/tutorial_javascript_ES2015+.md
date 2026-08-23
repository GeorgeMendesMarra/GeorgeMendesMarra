# Tutorial de JavaScript para Front-End

> Guia introdutório sobre JavaScript moderno (ES2015+), mostrando — via comentários no código — como ele se conecta às tags HTML5 e às classes/propriedades CSS3 vistas nos tutoriais anteriores. Inclui uma seção sobre comandos e práticas obsoletas da linguagem.

## Sumário

1. [O que é JavaScript e sua versão atual](#1-o-que-é-javascript-e-sua-versão-atual)
2. [Como o JavaScript se conecta ao HTML e ao CSS](#2-como-o-javascript-se-conecta-ao-html-e-ao-css)
3. [Selecionando elementos HTML5 com JavaScript](#3-selecionando-elementos-html5-com-javascript)
4. [Manipulando classes CSS3 via JavaScript](#4-manipulando-classes-css3-via-javascript)
5. [Eventos em elementos HTML5](#5-eventos-em-elementos-html5)
6. [Trabalhando com formulários HTML5](#6-trabalhando-com-formulários-html5)
7. [Animações: JavaScript + CSS3](#7-animações-javascript--css3)
8. [Canvas e mídia via JavaScript](#8-canvas-e-mídia-via-javascript)
9. [Variáveis, funções e módulos modernos](#9-variáveis-funções-e-módulos-modernos)
10. [Comandos e práticas obsoletas do JavaScript](#10-comandos-e-práticas-obsoletas-do-javascript)
11. [Boas práticas modernas](#11-boas-práticas-modernas)
12. [Exercícios propostos](#12-exercícios-propostos)

---

## 1. O que é JavaScript e sua versão atual

JavaScript é a linguagem responsável pelo **comportamento** e pela **interatividade** das páginas web — enquanto o HTML5 define estrutura e o CSS3 define apresentação, o JavaScript reage a eventos, manipula o DOM e altera estilos e conteúdo em tempo real.

A linguagem é padronizada pela especificação **ECMAScript (ES)**, mantida pela TC39. Desde 2015, uma nova versão é lançada anualmente:

- **ES5** (2009): versão clássica, ainda muito presente em código legado (`var`, `function`).
- **ES6 / ES2015**: grande marco — trouxe `let`, `const`, arrow functions, classes, módulos, template strings, Promises.
- **ES2016 a ES2025**: adições incrementais (`async/await`, spread/rest, `Array.flat`, `Optional chaining ?.`, `Nullish coalescing ??`, `Array.at()`, `Object.hasOwn()`, etc.)
- **ES2025 / ES2026** (atual): recursos mais recentes incluem `Array.fromAsync`, iteradores de conjuntos nativos (`Set.union`, `Set.intersection`) e melhorias em `RegExp`.

> Para efeitos práticas, "JavaScript moderno" hoje significa escrever em **ES2015+ (ES6 em diante)**, evitando a sintaxe antiga do ES5 sempre que possível.

---

## 2. Como o JavaScript se conecta ao HTML e ao CSS

```html
<!-- 1. Script externo (recomendado): conecta o HTML5 a um arquivo .js -->
<script src="app.js" defer></script>

<!-- 2. Script interno: bloco <script> no HTML -->
<script>
  console.log("Olá, mundo!");
</script>

<!-- 3. Atributo de evento inline (evitar!) -->
<button onclick="alert('Oi!')">Clique aqui</button>
```

- O atributo `defer` garante que o script só execute depois que o HTML5 for totalmente interpretado — importante porque o JavaScript geralmente **seleciona tags HTML5** que precisam existir antes.
- Assim como o CSS externo separa apresentação da estrutura, o JS externo separa comportamento da estrutura — evitando o padrão antigo de `onclick=""` espalhado pelo HTML, equivalente em espírito ao antigo `<font>`/`style=""` inline do CSS.

---

## 3. Selecionando elementos HTML5 com JavaScript

```js
// Seleciona a tag <main> definida no HTML5 (conteúdo principal)
const main = document.querySelector("main");

// Seleciona todos os <article> dentro de <main>
const artigos = document.querySelectorAll("main article");

// Seleciona o <nav> usado como menu de navegação
const menu = document.querySelector("nav");

// Seleciona o <time> usado para exibir datas dentro de um <article>
const data = document.querySelector("article time");

console.log(data.getAttribute("datetime")); // lê o atributo datetime do HTML5
```

`querySelector` e `querySelectorAll` usam a mesma sintaxe de seletores CSS3 vista no tutorial anterior — ou seja, dominar seletores CSS3 (`tag`, `.classe`, `#id`, `[atributo]`) ajuda diretamente a selecionar elementos em JavaScript.

---

## 4. Manipulando classes CSS3 via JavaScript

```js
// Seleciona o <footer> do HTML5
const footer = document.querySelector("footer");

// Adiciona a classe .destaque (definida no CSS3) via JavaScript
footer.classList.add("destaque");

// Remove uma classe CSS3
footer.classList.remove("destaque");

// Alterna a classe (liga/desliga) — muito usado em menus responsivos
menu.classList.toggle("aberto");

// Altera diretamente uma propriedade CSS3 via JS (usar com moderação)
footer.style.backgroundColor = "steelblue";
```

Esse é o ponto de encontro dos três tutoriais: o JavaScript seleciona a tag HTML5 (`footer`) e manipula a classe CSS3 (`.destaque`) que já definimos como estilo — em vez de escrever CSS diretamente via `.style`, o ideal é alternar classes pré-definidas.

---

## 5. Eventos em elementos HTML5

```js
// Escuta o clique no <button> dentro do <nav>
const botaoMenu = document.querySelector("nav button");

botaoMenu.addEventListener("click", () => {
  // Alterna a classe CSS3 que controla a exibição do menu (via @media/flex)
  menu.classList.toggle("aberto");
});

// Escuta o evento de rolagem da página
window.addEventListener("scroll", () => {
  const header = document.querySelector("header");
  header.classList.toggle("compacto", window.scrollY > 50);
});
```

---

## 6. Trabalhando com formulários HTML5

```js
// Seleciona o <form> e o <input type="email"> criados no tutorial de HTML5
const formulario = document.querySelector("form");
const campoEmail = document.querySelector("input[type='email']");

formulario.addEventListener("submit", (evento) => {
  evento.preventDefault(); // impede o envio padrão do <form>

  // checkValidity() usa a validação nativa do HTML5 (atributo required, type="email")
  if (!campoEmail.checkValidity()) {
    campoEmail.classList.add("erro"); // classe CSS3 para destacar erro
    return;
  }

  console.log("E-mail válido:", campoEmail.value);
});
```

O HTML5 já valida formatos básicos (`type="email"`, `required`, `pattern`) sozinho; o JavaScript entra para tratar a resposta, exibir mensagens customizadas ou enviar dados via `fetch`.

---

## 7. Animações: JavaScript + CSS3

```js
// Dispara uma animação CSS3 (definida com @keyframes) ao clicar num botão
const aviso = document.querySelector(".aviso");

document.querySelector("button").addEventListener("click", () => {
  aviso.classList.add("animar"); // .animar aplica a animation do CSS3
});

// Usa a Web Animations API (nativa do JS moderno) sem depender de classes CSS
aviso.animate(
  [
    { transform: "translateX(0)" },
    { transform: "translateX(100px)" }
  ],
  { duration: 500, easing: "ease-in-out" }
);
```

---

## 8. Canvas e mídia via JavaScript

```js
// Seleciona o <canvas> do HTML5
const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");

ctx.fillStyle = "tomato"; // equivalente a uma cor definida em CSS3
ctx.fillRect(10, 10, 100, 50);

// Controla o <video> do HTML5 via JavaScript
const video = document.querySelector("video");
document.querySelector(".play").addEventListener("click", () => {
  video.paused ? video.play() : video.pause();
});
```

---

## 9. Variáveis, funções e módulos modernos

```js
// Variáveis modernas (ES6+): let e const substituem var
const titulo = "Tutorial de JavaScript"; // valor não pode ser reatribuído
let contador = 0;                        // valor pode mudar

// Arrow function (ES6+)
const somar = (a, b) => a + b;

// Template strings (ES6+): interpolação de variáveis
const mensagem = `Olá, ${titulo}!`;

// Destructuring (ES6+)
const { nome, idade } = { nome: "Ana", idade: 25 };

// Spread operator (ES6+)
const lista = [1, 2, 3];
const copia = [...lista, 4];

// async/await (ES2017+): forma moderna de lidar com assincronismo
async function buscarDados() {
  const resposta = await fetch("https://api.exemplo.com/dados");
  const dados = await resposta.json();
  return dados;
}

// Optional chaining e nullish coalescing (ES2020+)
const usuario = { endereco: null };
const cidade = usuario.endereco?.cidade ?? "Cidade não informada";

// Módulos ES (ES6+): import/export em vez de scripts globais
// arquivo utils.js
export function formatarData(data) { /* ... */ }
// arquivo app.js
import { formatarData } from "./utils.js";
```

---

## 10. Comandos e práticas obsoletas do JavaScript

Assim como o HTML5 e o CSS3 deixaram tags e propriedades para trás, o JavaScript também acumulou métodos e padrões considerados **obsoletos (deprecated)** ou **más práticas** hoje, geralmente por questões de segurança, performance ou por terem sido substituídos por alternativas melhores no ES6+.

| Recurso obsoleto | O que fazia | Substituto moderno |
|---|---|---|
| `var` | Declarava variáveis com escopo de função (gera bugs de hoisting) | `let` / `const` (escopo de bloco) |
| `document.write()` | Escrevia HTML diretamente no documento durante o carregamento | Manipulação do DOM (`createElement`, `innerHTML` com cautela) |
| `escape()` / `unescape()` | Codificava/decodificava strings de URL (comportamento inconsistente) | `encodeURIComponent()` / `decodeURIComponent()` |
| `arguments.callee` | Referenciava a própria função em execução | Função nomeada ou arrow function com nome explícito |
| `with (objeto) { ... }` | Criava escopo implícito a partir de um objeto | Acesso direto às propriedades do objeto |
| `eval()` | Executava uma string como código JavaScript | Evitar completamente (falha de segurança); usar `JSON.parse` para dados |
| `XMLHttpRequest` (uso direto, verboso) | Fazia requisições HTTP assíncronas | `fetch()` (mais simples, baseado em Promises) |
| Callbacks aninhados ("callback hell") | Encadeava funções de callback para lidar com assincronismo | `Promise` e `async/await` |
| `Number.prototype` extendido via `for...in` sem checagem | Iterava propriedades de objetos, incluindo herdadas indesejadas | `for...of`, `Object.keys()`, `Object.entries()` |
| `attachEvent()` (específico do Internet Explorer antigo) | Registrava eventos apenas no IE8 e anteriores | `addEventListener()` (padrão) |
| `<script language="javascript">` | Atributo antigo para indicar a linguagem do script (redundante) | `<script src="app.js">` (o atributo `type` nem é mais necessário para JS) |

### Exemplo comparativo

**Código antigo (ES5 / práticas ultrapassadas):**

```js
var nome = "Ana";
document.write("<p>Olá, " + nome + "</p>");

var req = new XMLHttpRequest();
req.open("GET", "dados.json", true);
req.onreadystatechange = function () {
  if (req.readyState === 4 && req.status === 200) {
    console.log(JSON.parse(req.responseText));
  }
};
req.send();
```

**Equivalente moderno em JavaScript atual (ES2015+):**

```js
const nome = "Ana";
document.querySelector("main").insertAdjacentHTML("beforeend", `<p>Olá, ${nome}</p>`);

async function carregarDados() {
  const resposta = await fetch("dados.json");
  const dados = await resposta.json();
  console.log(dados);
}
carregarDados();
```

> **Por que isso importa para os alunos:** `var`, `document.write()` e `XMLHttpRequest` verboso ainda aparecem em tutoriais antigos e em sistemas legados. Reconhecer esses padrões ajuda na manutenção de código antigo, mas o código novo deve sempre usar `let`/`const`, manipulação segura do DOM e `fetch`/`async-await`.

---

## 11. Boas práticas modernas

- Prefira `const` por padrão; use `let` só quando o valor realmente muda; evite `var`.
- Use `addEventListener` em vez de atributos `onclick` inline no HTML5.
- Prefira alternar classes CSS3 (`classList`) a manipular `style` diretamente em JS.
- Use `fetch`/`async-await` para requisições, nunca `XMLHttpRequest` em código novo.
- Separe o JavaScript em módulos (`import`/`export`) em vez de um único arquivo gigante.
- Sempre valide/trate erros (`try/catch`) ao lidar com dados assíncronos.

---

## 12. Exercícios propostos

1. Usando o HTML5 e o CSS3 dos tutoriais anteriores, escreva um `app.js` que selecione o `<nav>` e alterne uma classe `.aberto` ao clicar em um botão de menu.
2. Implemente a validação do formulário HTML5 (`<input type="email">`, `required`) em JavaScript, exibindo uma classe CSS3 de erro quando inválido.
3. Use `fetch` e `async/await` para buscar dados de uma API pública e inserir o resultado dentro de um `<article>`.
4. Pegue um trecho de código com `var` e `XMLHttpRequest` e reescreva-o usando `let`/`const` e `fetch`.
5. Pesquise e liste mais 2 métodos ou padrões de JavaScript considerados obsoletos hoje.
