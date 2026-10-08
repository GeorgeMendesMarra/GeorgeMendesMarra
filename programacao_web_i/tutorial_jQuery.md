# Tutorial de jQuery para Iniciantes

> Um guia introdutório para quem nunca programou com jQuery, pensado para a disciplina de Programação Web I.
> Cada seção traz **explicação curta → exemplo que funciona → mini-exercício**. Digite os exemplos, não apenas copie e cole: é digitando (e errando) que se aprende.

**Legenda usada neste material**

| Ícone | Significado |
| ----- | ----------- |
| 🧪 | **Experimente:** teste no console do navegador ou no seu arquivo |
| 🏋️ | **Mini-exercício:** tente resolver sozinho antes de olhar a dica |
| ⚠️ | **Erro comum:** algo que quase todo iniciante encontra |
| 💡 | **Dica:** boa prática ou curiosidade |

---

## Índice

0. [Antes de começar: pré-requisitos](#0-antes-de-começar-pré-requisitos)
1. [O que é jQuery](#1-o-que-é-jquery)
2. [Como incluir o jQuery no seu projeto](#2-como-incluir-o-jquery-no-seu-projeto)
3. [Sintaxe básica: o `$`](#3-sintaxe-básica-o-)
4. [Selecionando elementos](#4-selecionando-elementos)
5. [Navegando pela árvore (DOM)](#5-navegando-pela-árvore-dom)
6. [Manipulando conteúdo e atributos](#6-manipulando-conteúdo-e-atributos)
7. [Manipulando CSS e classes](#7-manipulando-css-e-classes)
8. [Eventos](#8-eventos)
9. [Efeitos e animações](#9-efeitos-e-animações)
10. [Manipulando a estrutura do DOM](#10-manipulando-a-estrutura-do-dom)
11. [Delegação de eventos](#11-delegação-de-eventos)
12. [Formulários e validação](#12-formulários-e-validação)
13. [Requisições AJAX (introdução)](#13-requisições-ajax-introdução)
14. [Projetos práticos guiados](#14-projetos-práticos-guiados)
15. [Erros comuns e como depurar](#15-erros-comuns-e-como-depurar)
16. [Boas práticas](#16-boas-práticas)
17. [Cola rápida: jQuery × JavaScript puro](#17-cola-rápida-jquery--javascript-puro)
18. [Exercícios propostos](#18-exercícios-propostos)
19. [Referências para continuar estudando](#19-referências-para-continuar-estudando)

---

## 0. Antes de começar: pré-requisitos

jQuery é JavaScript. Se algum item abaixo ainda não estiver claro, revise antes de seguir; boa parte das "dificuldades com jQuery" são, na verdade, dificuldades com JavaScript básico.

- [ ] Sei criar um arquivo HTML com `<head>` e `<body>`;
- [ ] Sei o que são **id** e **class** em uma tag HTML;
- [ ] Sei escrever uma regra CSS simples (`#id { }` e `.classe { }`);
- [ ] Sei declarar uma variável com `let` e usar `if`;
- [ ] Sei o que é uma **função** e como chamá-la.

### Ferramentas

- Um editor de código (VS Code, por exemplo);
- Um navegador com **console** (Chrome, Edge ou Firefox). Abra com a tecla **F12** e clique na aba **Console**;
- Recomendado: a extensão **Live Server** do VS Code, que abre sua página em `http://127.0.0.1:5500` e recarrega sozinha a cada alteração (será necessária na seção de AJAX).

> 🧪 **Experimente:** abra qualquer página, aperte F12, vá em *Console* e digite `2 + 2`. Se apareceu `4`, seu console está funcionando.

---

## 1. O que é jQuery

jQuery é uma **biblioteca JavaScript** criada em 2006 para facilitar tarefas comuns no desenvolvimento web, como:

- selecionar e alterar elementos HTML;
- reagir a eventos do usuário (clique, hover, teclado, etc.);
- criar efeitos e animações;
- enviar requisições ao servidor sem recarregar a página (AJAX).

Sua principal vantagem é **reduzir a quantidade de código** em comparação ao JavaScript puro (chamado de "vanilla JS") e lidar com diferenças entre navegadores.

### Comparando lado a lado

```javascript
// Mudar a cor de um título

// JavaScript puro
document.querySelector("#titulo").style.color = "blue";

// jQuery
$("#titulo").css("color", "blue");
```

```javascript
// Esconder todos os parágrafos

// JavaScript puro
document.querySelectorAll("p").forEach(function (p) {
  p.style.display = "none";
});

// jQuery
$("p").hide();
```

Repare que, no segundo exemplo, o jQuery dispensa o laço: **uma única linha age sobre todos os parágrafos**.

### Uma analogia

Pense no jQuery como um **controle remoto** da página:

1. você **aponta** para o que quer controlar (o *seletor*);
2. você **aperta um botão** (a *ação*).

`$("#luz").hide()` → aponta para o elemento de id `luz` e aperta o botão "esconder".

---

## 2. Como incluir o jQuery no seu projeto

### Opção 1 — via CDN (mais comum para estudos)

Adicione a linha do jQuery antes do fechamento da tag `</body>`:

```html
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Meu projeto com jQuery</title>
</head>
<body>

  <h1 id="titulo">Olá, mundo!</h1>

  <!-- jQuery via CDN -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

  <!-- Seu script deve vir DEPOIS do jQuery -->
  <script src="js/script.js"></script>
</body>
</html>
```

> ⚠️ **Importante:** o seu arquivo `script.js` (ou qualquer código jQuery) deve ser carregado **depois** da biblioteca jQuery, senão o navegador não vai reconhecer o `$`.

### Opção 2 — download local

Baixe o arquivo em [jquery.com/download](https://jquery.com/download/), salve na pasta `/js` do seu projeto e referencie normalmente:

```html
<script src="js/jquery-3.7.1.min.js"></script>
<script src="js/script.js"></script>
```

### Teste de instalação

Com a página aberta, digite no console:

```javascript
$.fn.jquery
```

Se aparecer `"3.7.1"`, o jQuery está carregado. Se aparecer `ReferenceError: $ is not defined`, veja a seção [Erros comuns](#15-erros-comuns-e-como-depurar).

### Estrutura de pastas sugerida

```
meu-projeto/
├── index.html
├── css/
│   └── estilo.css
├── js/
│   └── script.js
└── dados/
    └── produtos.json   (usaremos na seção de AJAX)
```

---

## 3. Sintaxe básica: o `$`

Todo código jQuery segue este padrão:

```javascript
$(seletor).ação();
```

- `$` é um atalho para a função `jQuery()`;
- `seletor` indica **qual(is) elemento(s)** você quer manipular (parecido com CSS);
- `ação()` é o que você quer fazer com esse elemento.

### Primeiro exemplo completo

```html
<h1 id="titulo">Olá, mundo!</h1>
<button id="botao">Mudar título</button>
```

```javascript
$("#botao").click(function () {
  $("#titulo").text("Título alterado com jQuery!");
});
```

Leia em voz alta: *"quando clicar no botão, mude o texto do título"*. Quase todo código jQuery se lê assim, como uma frase.

### Garantindo que o documento carregou

Antes de manipular elementos, é uma boa prática esperar a página carregar completamente:

```javascript
$(document).ready(function () {
  // seu código aqui
});
```

Forma reduzida (bastante usada):

```javascript
$(function () {
  // seu código aqui
});
```

> 💡 Se o seu `<script>` já está no final do `<body>`, o `ready` não é estritamente necessário, mas **continua sendo um bom hábito** e evita erros quando o script é movido de lugar.

### O `$` devolve uma *coleção*

`$("p")` não devolve "um parágrafo": devolve **todos** os parágrafos encontrados (pode ser 0, 1 ou 100). A ação é aplicada a todos de uma vez.

> 🧪 **Experimente no console** (numa página com vários `<p>`):
> ```javascript
> $("p").length      // quantos parágrafos existem?
> $("p").hide()      // some com todos
> $("p").show()      // volta com todos
> ```

### Encadeamento (*chaining*)

Como a maioria das ações devolve a própria coleção, é possível **encadear** várias ações:

```javascript
$("#caixa")
  .addClass("destaque")
  .text("Olá!")
  .fadeIn(500);
```

É o mesmo que escrever `$("#caixa")` três vezes, só que mais curto e mais rápido.

> 🏋️ **Mini-exercício 3.1:** crie uma página com um `<h1 id="titulo">` e dois botões: "Azul" e "Vermelho". Cada botão deve mudar a cor do título.
> *Dica:* use `.css("color", "blue")`.

---

## 4. Selecionando elementos

Os seletores do jQuery são muito parecidos com os do CSS:

```javascript
$("#meuId")               // seleciona por ID
$(".minhaClasse")         // seleciona por classe
$("p")                    // seleciona todas as tags <p>
$("div.card")             // seleciona <div> com classe "card"
$("ul li")                // seleciona <li> dentro de <ul>
$("ul > li")              // seleciona apenas <li> filhos diretos de <ul>
$("h1, h2, h3")           // seleciona vários tipos de uma vez
$("input[type='text']")   // seleciona inputs do tipo texto
$("a[target='_blank']")   // seleciona links que abrem em nova aba
```

### Seletores úteis de posição e estado

```javascript
$("li:first")             // primeiro <li> da página
$("li:last")              // último <li>
$("li:even")              // <li> nas posições 0, 2, 4... (pares, contando a partir de 0)
$("li:odd")               // <li> nas posições 1, 3, 5...
$("input:checked")        // checkboxes/radios marcados
$("input:disabled")       // campos desabilitados
$(":button")              // todos os botões
```

### Exemplo prático: zebrar uma tabela

```html
<table id="tabela">
  <tr><td>Maria</td><td>10</td></tr>
  <tr><td>João</td><td>8</td></tr>
  <tr><td>Ana</td><td>9</td></tr>
  <tr><td>Pedro</td><td>7</td></tr>
</table>
```

```css
.listrada { background-color: #f2f2f2; }
```

```javascript
$(function () {
  $("#tabela tr:even").addClass("listrada");
});
```

### Exemplo prático: destacar links externos

```html
<a href="pagina2.html">Link interno</a>
<a href="https://www.jquery.com" target="_blank">Link externo</a>
```

```javascript
$("a[target='_blank']").css("color", "crimson");
```

> 🧪 **Experimente:** abra o site que quiser, abra o console e rode `$("a").length`. Quantos links a página tem? (Só funciona em sites que já carregam jQuery; caso contrário, use a sua própria página.)

> ⚠️ **Erro comum:** esquecer o `#` (id) ou o `.` (classe). `$("titulo")` procura uma tag `<titulo>`, que não existe, e o jQuery **não dá erro**: simplesmente não faz nada.

### Como saber se o seletor encontrou algo?

```javascript
console.log($("#titulo").length);   // 1  → encontrou
console.log($("#tituloo").length);  // 0  → não encontrou (provável erro de digitação)
```

> 🏋️ **Mini-exercício 4.1:** em uma lista `<ul>` com 6 itens, pinte de cinza os itens ímpares e deixe o primeiro item em negrito.

---

## 5. Navegando pela árvore (DOM)

Muitas vezes você já tem um elemento e precisa chegar em outro **a partir dele** (o pai, o irmão, o filho). O jQuery oferece métodos de navegação:

| Método | O que retorna |
| ------ | ------------- |
| `.parent()` | O pai direto |
| `.closest(seletor)` | O ancestral mais próximo que combina com o seletor (inclusive ele mesmo) |
| `.children()` | Os filhos diretos |
| `.find(seletor)` | Descendentes (filhos, netos...) que combinam com o seletor |
| `.next()` / `.prev()` | O irmão seguinte / anterior |
| `.siblings()` | Todos os irmãos |
| `.first()` / `.last()` / `.eq(n)` | O primeiro / último / n-ésimo da coleção (`eq(0)` é o primeiro) |

```html
<div class="card">
  <h3>Produto A</h3>
  <p class="descricao">Descrição do produto A.</p>
  <button class="comprar">Comprar</button>
</div>
<div class="card">
  <h3>Produto B</h3>
  <p class="descricao">Descrição do produto B.</p>
  <button class="comprar">Comprar</button>
</div>
```

```javascript
$(".comprar").click(function () {
  // $(this) é o botão que foi clicado
  let card = $(this).closest(".card");        // sobe até o card
  let nome = card.find("h3").text();           // desce até o título
  alert("Você escolheu: " + nome);
});
```

### O que é o `$(this)`?

Dentro de uma função de evento, `this` é **o elemento que disparou o evento**. Ao escrever `$(this)`, você "embrulha" esse elemento no jQuery para poder usar `.text()`, `.css()`, etc.

Sem o `this`, você teria que escrever um código para cada botão. Com ele, **um único código serve para todos os botões**.

### Percorrendo uma coleção com `.each()`

```javascript
$("li").each(function (indice) {
  $(this).text((indice + 1) + ". " + $(this).text());
});
```

Isso numera todos os itens de uma lista: "1. Maçã", "2. Banana"...

> 🏋️ **Mini-exercício 5.1:** na lista de cards acima, ao clicar em "Comprar", altere o texto **daquele botão** para "Adicionado ✔" e **só** daquele.
> *Dica:* use `$(this).text(...)`.

---

## 6. Manipulando conteúdo e atributos

| Método    | O que faz                                      |
| --------- | ---------------------------------------------- |
| `.text()` | Lê ou define o texto de um elemento            |
| `.html()` | Lê ou define o HTML interno de um elemento     |
| `.val()`  | Lê ou define o valor de um campo de formulário |
| `.attr()` | Lê ou define um atributo (ex: `src`, `href`)   |
| `.prop()` | Lê ou define propriedades (ex: `checked`, `disabled`) |
| `.data()` | Lê atributos `data-*` (dados personalizados)   |

> **Regra simples:** chamar o método **sem argumento** *lê*; chamar **com argumento** *escreve*.

```javascript
// Ler texto
let texto = $("#titulo").text();

// Alterar texto
$("#titulo").text("Novo título");

// Alterar HTML (permite tags)
$("#titulo").html("<strong>Título em negrito</strong>");

// Pegar valor de um input
let nome = $("#nomeInput").val();

// Alterar um atributo
$("#logo").attr("src", "img/nova-logo.png");
```

### `.text()` × `.html()`: qual usar?

```javascript
$("#saida").text("<b>oi</b>");   // mostra literalmente:  <b>oi</b>
$("#saida").html("<b>oi</b>");   // mostra em negrito:    oi
```

> ⚠️ **Segurança:** nunca use `.html()` com texto digitado por um usuário. Alguém poderia digitar código HTML/JavaScript malicioso (ataque conhecido como **XSS**). Para exibir texto do usuário, prefira sempre `.text()`.

### Exemplo: saudação personalizada

```html
<input type="text" id="nome" placeholder="Seu nome">
<button id="btnSaudar">Saudar</button>
<p id="saudacao"></p>
```

```javascript
$("#btnSaudar").click(function () {
  let nome = $("#nome").val().trim();
  if (nome === "") {
    $("#saudacao").text("Digite seu nome primeiro!");
  } else {
    $("#saudacao").text("Olá, " + nome + "! Bem-vindo(a)!");
  }
});
```

### Exemplo: trocar a imagem ao clicar na miniatura

```html
<img id="principal" src="img/foto1.jpg" width="300"><br>
<img class="mini" src="img/foto1.jpg" width="60">
<img class="mini" src="img/foto2.jpg" width="60">
<img class="mini" src="img/foto3.jpg" width="60">
```

```javascript
$(".mini").click(function () {
  let novaFoto = $(this).attr("src");
  $("#principal").attr("src", novaFoto);
});
```

### Exemplo: contador de caracteres

```html
<textarea id="mensagem" maxlength="100" rows="3" cols="40"></textarea>
<p id="contador">100 caracteres restantes</p>
```

```javascript
$("#mensagem").on("input", function () {
  let restantes = 100 - $(this).val().length;
  $("#contador").text(restantes + " caracteres restantes");
});
```

### Exemplo: `.prop()` com checkbox

```html
<label><input type="checkbox" id="aceito"> Aceito os termos</label>
<button id="btnContinuar" disabled>Continuar</button>
```

```javascript
$("#aceito").on("change", function () {
  let marcado = $(this).prop("checked");        // true ou false
  $("#btnContinuar").prop("disabled", !marcado); // habilita/desabilita
});
```

> 💡 Para `checked` e `disabled`, use `.prop()`. Para `src`, `href`, `title`, `placeholder` etc., use `.attr()`.

### Exemplo: atributos `data-*`

```html
<button class="produto" data-nome="Mouse" data-preco="59.90">Mouse</button>
<button class="produto" data-nome="Teclado" data-preco="120">Teclado</button>
<p id="info"></p>
```

```javascript
$(".produto").click(function () {
  let nome  = $(this).data("nome");
  let preco = $(this).data("preco");
  $("#info").text(nome + " custa R$ " + preco);
});
```

Os atributos `data-*` são ótimos para "pendurar" informações extras no HTML sem criar elementos escondidos.

> 🏋️ **Mini-exercício 6.1:** crie um campo de texto e uma `<img>`. Ao digitar uma URL de imagem no campo e clicar em "Carregar", a imagem deve ser exibida.
> *Dica:* `.val()` para ler e `.attr("src", ...)` para escrever.

---

## 7. Manipulando CSS e classes

```javascript
// Alterar uma propriedade CSS diretamente
$("#caixa").css("background-color", "lightblue");

// Alterar várias de uma vez
$("#caixa").css({
  "background-color": "lightblue",
  "padding": "20px",
  "border-radius": "8px"
});

// Adicionar uma classe
$("#caixa").addClass("ativo");

// Remover uma classe
$("#caixa").removeClass("ativo");

// Alternar uma classe (adiciona se não tem, remove se tem)
$("#caixa").toggleClass("ativo");

// Verificar se tem uma classe
if ($("#caixa").hasClass("ativo")) {
  console.log("A caixa está ativa!");
}
```

> 💡 **Dica:** prefira usar `addClass()`/`removeClass()`/`toggleClass()` em vez de `.css()` sempre que possível. Isso mantém o estilo organizado no CSS, e o JavaScript só controla o comportamento. Use `.css()` quando o valor for **calculado ou vier de uma escolha do usuário** (ex.: uma cor escolhida em um seletor).

### Exemplo: modo escuro (dark mode)

```html
<button id="btnTema">🌙 Alternar tema</button>
<p>Este é um texto de exemplo para testar o tema.</p>
```

```css
body         { background: #ffffff; color: #222222; transition: all 0.3s; }
body.escuro  { background: #1e1e1e; color: #f0f0f0; }
```

```javascript
$("#btnTema").click(function () {
  $("body").toggleClass("escuro");
});
```

Apenas **uma linha de jQuery**; todo o visual está no CSS.

### Exemplo: destacar a linha sob o mouse

```css
tr.destacada { background-color: #fff3cd; }
```

```javascript
$("#tabela tr").hover(
  function () { $(this).addClass("destacada"); },
  function () { $(this).removeClass("destacada"); }
);
```

### Exemplo: escolher a cor de fundo (aqui o `.css()` faz sentido)

```html
<select id="cor">
  <option value="white">Branco</option>
  <option value="lightyellow">Amarelo</option>
  <option value="lightgreen">Verde</option>
  <option value="lightblue">Azul</option>
</select>
```

```javascript
$("#cor").on("change", function () {
  $("body").css("background-color", $(this).val());
});
```

### Exemplo: marcar um item selecionado em uma lista

```css
li.selecionado { background-color: #cce5ff; font-weight: bold; }
```

```javascript
$("#menu li").click(function () {
  $("#menu li").removeClass("selecionado");   // limpa todos
  $(this).addClass("selecionado");            // marca só o clicado
});
```

Esse padrão — **"limpa todos, marca o atual"** — aparece o tempo todo: menus, abas, avaliações com estrelas...

> 🏋️ **Mini-exercício 7.1:** crie três botões com os tamanhos "A-", "A", "A+". Ao clicar, o texto de um parágrafo deve diminuir, voltar ao normal ou aumentar. Faça isso usando **classes** (`.pequeno`, `.normal`, `.grande`), não `.css()`.

---

## 8. Eventos

Eventos permitem que seu código reaja a ações do usuário.

```javascript
// Clique
$("#botao").click(function () {
  alert("Botão clicado!");
});

// Mesma coisa, forma mais moderna (recomendada)
$("#botao").on("click", function () {
  alert("Botão clicado!");
});

// Hover (mouse entra e sai)
$("#card").hover(
  function () { $(this).addClass("destacado"); },
  function () { $(this).removeClass("destacado"); }
);

// Envio de formulário
$("#form").on("submit", function (evento) {
  evento.preventDefault(); // impede o recarregamento da página
  alert("Formulário enviado!");
});
```

### Outros eventos comuns

| Evento                      | Quando acontece                  |
| --------------------------- | -------------------------------- |
| `click`                     | Ao clicar no elemento            |
| `dblclick`                  | Ao clicar duas vezes             |
| `mouseenter` / `mouseleave` | Ao passar o mouse sobre / sair   |
| `keyup` / `keydown`         | Ao soltar / pressionar uma tecla |
| `input`                     | A cada alteração no texto de um campo |
| `change`                    | Ao mudar o valor de um campo (ao sair dele, ou ao escolher uma opção) |
| `focus` / `blur`            | Ao entrar / sair de um campo     |
| `submit`                    | Ao enviar um formulário          |
| `scroll`                    | Ao rolar a página                |

### Exemplo: contador de cliques

```html
<button id="btnContar">Clique aqui</button>
<p>Você clicou <span id="total">0</span> vezes.</p>
```

```javascript
$(function () {
  let total = 0;

  $("#btnContar").click(function () {
    total++;
    $("#total").text(total);
  });
});
```

> 💡 A variável `total` fica **fora** da função do clique. Se ela ficasse dentro, voltaria a zero a cada clique. É uma ótima oportunidade para explicar escopo de variáveis.

### Exemplo: mostrar/ocultar a senha

```html
<input type="password" id="senha" placeholder="Senha">
<label><input type="checkbox" id="verSenha"> Mostrar senha</label>
```

```javascript
$("#verSenha").on("change", function () {
  let tipo = $(this).is(":checked") ? "text" : "password";
  $("#senha").attr("type", tipo);
});
```

### Exemplo: busca instantânea em uma lista

```html
<input type="text" id="busca" placeholder="Buscar fruta...">
<ul id="frutas">
  <li>Maçã</li>
  <li>Banana</li>
  <li>Laranja</li>
  <li>Abacaxi</li>
  <li>Manga</li>
</ul>
```

```javascript
$("#busca").on("keyup", function () {
  let termo = $(this).val().toLowerCase();

  $("#frutas li").each(function () {
    let combina = $(this).text().toLowerCase().includes(termo);
    $(this).toggle(combina);   // mostra se combina, esconde se não
  });
});
```

Com 8 linhas, você criou um filtro em tempo real. Esse costuma ser o exemplo que mais **impressiona** quem está começando.

### Exemplo: capturando uma tecla (Enter)

```javascript
$("#nome").on("keyup", function (evento) {
  if (evento.key === "Enter") {
    alert("Você apertou Enter, " + $(this).val());
  }
});
```

### O objeto `evento`

O primeiro parâmetro da função de evento traz informações sobre o que aconteceu:

```javascript
$("#caixa").on("click", function (evento) {
  console.log(evento.type);    // "click"
  console.log(evento.pageX);   // posição X do mouse na página
  console.log(evento.pageY);   // posição Y do mouse na página
});
```

E métodos úteis como `evento.preventDefault()` (cancela o comportamento padrão, ex.: um link ou envio de formulário).

> ⚠️ **Erro comum:** colocar parênteses ao passar a função. `$("#b").click(minhaFuncao)` está certo; `$("#b").click(minhaFuncao())` executa a função **imediatamente**, ao carregar a página.

> 🏋️ **Mini-exercício 8.1:** crie um botão "Curtir" que aumenta um contador a cada clique e, ao chegar em 10, muda o texto do botão para "Você é muito querido(a)! ❤️".
>
> 🏋️ **Mini-exercício 8.2:** crie um campo de texto que, a cada tecla digitada, mostra em um `<p>` o texto **em letras maiúsculas** (use `.toUpperCase()`).

---

## 9. Efeitos e animações

O jQuery vem com efeitos prontos, muito usados para dar dinamismo à interface:

```javascript
$("#caixa").hide();          // esconde imediatamente
$("#caixa").show();          // mostra imediatamente
$("#caixa").toggle();        // alterna entre mostrar/esconder

$("#caixa").fadeIn();        // aparece suavemente
$("#caixa").fadeOut();       // desaparece suavemente
$("#caixa").fadeToggle();    // alterna com efeito suave

$("#caixa").slideDown();     // "desliza" para baixo (aparece)
$("#caixa").slideUp();       // "desliza" para cima (esconde)
$("#caixa").slideToggle();   // alterna com efeito de deslizar
```

Você também pode definir a duração (em milissegundos, ou com as palavras `"slow"` e `"fast"`) e uma função de *callback*, que roda **quando a animação termina**:

```javascript
$("#caixa").fadeOut(500, function () {
  console.log("Animação concluída!");
});
```

### Exemplo: menu que abre e fecha

```html
<button id="btnMenu">Menu</button>
<ul id="menu" style="display:none;">
  <li>Início</li>
  <li>Sobre</li>
  <li>Contato</li>
</ul>
```

```javascript
$("#btnMenu").click(function () {
  $("#menu").slideToggle();
});
```

### Exemplo: perguntas frequentes (acordeão / FAQ)

```html
<div class="faq">
  <h3 class="pergunta">O que é jQuery?</h3>
  <p class="resposta">Uma biblioteca JavaScript que simplifica a manipulação da página.</p>

  <h3 class="pergunta">jQuery ainda é usado?</h3>
  <p class="resposta">Sim, principalmente em sistemas já existentes e sites com Bootstrap 4.</p>

  <h3 class="pergunta">Preciso saber JavaScript antes?</h3>
  <p class="resposta">É muito recomendado ter o básico.</p>
</div>
```

```css
.pergunta { cursor: pointer; background: #eee; padding: 10px; margin: 5px 0; }
.resposta { display: none; padding: 10px; }
```

```javascript
$(".pergunta").click(function () {
  $(this).next(".resposta").slideToggle();
});
```

Aqui aparecem três ideias juntas: **evento** (`click`), **navegação** (`.next()`) e **efeito** (`slideToggle`).

Variação: para que **apenas uma resposta fique aberta por vez**, feche as outras antes:

```javascript
$(".pergunta").click(function () {
  $(".resposta").not($(this).next()).slideUp();
  $(this).next(".resposta").slideToggle();
});
```

### Exemplo: fechar um aviso (com callback)

```html
<div class="aviso">
  Promoção válida até domingo!
  <button class="fechar">✖</button>
</div>
```

```javascript
$(".fechar").click(function () {
  $(this).closest(".aviso").fadeOut(400, function () {
    $(this).remove();   // aqui, "this" é o aviso (já escondido)
  });
});
```

> 💡 Por que o `.remove()` fica dentro do callback? Porque, se removêssemos imediatamente, o efeito de `fadeOut` nem apareceria. O callback garante a ordem: **primeiro some suavemente, depois é removido**.

### Animações personalizadas com `.animate()`

Com `.animate()`, você anima **propriedades CSS numéricas**:

```html
<div id="bola"></div>
<button id="btnMover">Mover</button>
```

```css
#bola {
  width: 50px; height: 50px; border-radius: 50%;
  background: tomato; position: relative;   /* necessário para usar "left" */
}
```

```javascript
$("#btnMover").click(function () {
  $("#bola").animate({ left: "300px", opacity: 0.5 }, 1000);
});
```

> ⚠️ Para animar `left`/`top`, o elemento precisa ter `position: relative`, `absolute` ou `fixed`.

### Exemplo: botão "voltar ao topo"

```html
<button id="topo" style="display:none; position:fixed; bottom:20px; right:20px;">⬆ Topo</button>
```

```javascript
$(window).on("scroll", function () {
  if ($(this).scrollTop() > 200) {
    $("#topo").fadeIn();
  } else {
    $("#topo").fadeOut();
  }
});

$("#topo").click(function () {
  $("html, body").animate({ scrollTop: 0 }, 600);
});
```

> ⚠️ **Erro comum:** clicar várias vezes rápido e acumular animações. Use `.stop()` antes do efeito para cancelar a anterior: `$("#caixa").stop().slideToggle();`.

> 💡 **Dica de bom senso:** efeitos demais cansam o usuário. Use animações quando elas **ajudam** a entender o que mudou na tela.

> 🏋️ **Mini-exercício 9.1:** crie um painel "Detalhes" que começa escondido. Um botão "Ver detalhes" deve mostrar/esconder o painel com `slideToggle` e **trocar o texto do botão** entre "Ver detalhes" e "Ocultar detalhes".

---

## 10. Manipulando a estrutura do DOM

```javascript
// Adicionar conteúdo dentro de um elemento
$("#lista").append("<li>Novo item</li>");      // no final
$("#lista").prepend("<li>Primeiro item</li>"); // no início

// Adicionar ao lado de um elemento (fora dele)
$("#item2").before("<li>Antes do item 2</li>");
$("#item2").after("<li>Depois do item 2</li>");

// Remover um elemento
$("#item3").remove();

// Esvaziar o conteúdo de um elemento (sem remover ele mesmo)
$("#lista").empty();

// Clonar um elemento
let copia = $("#card").clone();
$("#container").append(copia);
```

### Três formas de criar elementos

```javascript
// 1) Texto HTML (simples, mas CUIDADO com texto vindo do usuário)
$("#lista").append("<li>Item</li>");

// 2) Criando o elemento e preenchendo com .text() (seguro)
let novoItem = $("<li>").text("Item");
$("#lista").append(novoItem);

// 3) Criando com atributos
let link = $("<a>", {
  href: "https://jquery.com",
  text: "Site do jQuery",
  target: "_blank"
});
$("#lista").append($("<li>").append(link));
```

### Exemplo: adicionar itens a uma lista dinamicamente

```html
<input type="text" id="novoItem">
<button id="btnAdicionar">Adicionar</button>
<ul id="listaTarefas"></ul>
```

```javascript
$("#btnAdicionar").click(function () {
  let valor = $("#novoItem").val().trim();
  if (valor !== "") {
    $("#listaTarefas").append($("<li>").text(valor));
    $("#novoItem").val("").focus();   // limpa o campo e devolve o cursor a ele
  }
});
```

### Exemplo: gerar uma tabela a partir de um array

```html
<table id="tabelaAlunos">
  <thead><tr><th>Nome</th><th>Nota</th></tr></thead>
  <tbody></tbody>
</table>
```

```javascript
let alunos = [
  { nome: "Maria", nota: 9.5 },
  { nome: "João",  nota: 7.0 },
  { nome: "Ana",   nota: 8.2 }
];

$.each(alunos, function (indice, aluno) {
  let linha = $("<tr>")
    .append($("<td>").text(aluno.nome))
    .append($("<td>").text(aluno.nota.toFixed(1)));

  $("#tabelaAlunos tbody").append(linha);
});
```

### Exemplo: clonar um cartão (modelo / template)

```html
<div id="modelo" class="card" style="display:none;">
  <h3 class="nome"></h3>
  <p class="email"></p>
</div>
<div id="container"></div>
```

```javascript
function criarCard(nome, email) {
  let card = $("#modelo").clone().removeAttr("id").show();
  card.find(".nome").text(nome);
  card.find(".email").text(email);
  $("#container").append(card);
}

criarCard("Maria Silva", "maria@email.com");
criarCard("João Souza", "joao@email.com");
```

> ⚠️ **Segurança (de novo!):** se você usar `append("<li>" + valorDigitado + "</li>")`, o que o usuário digitar será interpretado como HTML. Tente digitar `<b>teste</b>` em um campo assim para ver o problema. A forma segura é `$("<li>").text(valorDigitado)`.

> 🏋️ **Mini-exercício 10.1:** crie uma lista de compras: campo + botão "Adicionar". Cada novo item deve ser inserido **no início** da lista (`prepend`), e o campo deve ser limpo.
>
> 🏋️ **Mini-exercício 10.2:** crie um botão "Limpar tudo" que esvazia a lista (`empty`).

---

## 11. Delegação de eventos

### O problema

Observe o código abaixo. Parece correto, mas **não funciona** para itens adicionados depois:

```javascript
$(function () {
  // Este código "enxerga" apenas os botões que existem AGORA
  $(".btnRemover").click(function () {
    $(this).parent().remove();
  });

  // Botão que cria novos itens, cada um com seu botão "Remover"
  $("#btnAdicionar").click(function () {
    $("#lista").append("<li>Item <button class='btnRemover'>Remover</button></li>");
  });
});
```

Quando a página carrega, o jQuery procura os `.btnRemover` **existentes** e liga o evento neles. Os botões criados depois **nasceram sem o evento**.

### A solução: delegação

Em vez de ligar o evento no botão, ligamos em um **elemento pai que já existe** e dizemos: *"quando o clique vier de dentro de você e for em um `.btnRemover`, execute isto"*.

```javascript
$("#lista").on("click", ".btnRemover", function () {
  $(this).parent().remove();
});
```

Sintaxe:

```javascript
$(pai).on("evento", "seletor-do-filho", function () { ... });
```

### Analogia

Imagine um prédio em que cada apartamento novo ainda não tem campainha instalada. Com a **delegação**, você coloca **um porteiro na entrada**: ele atende a todos, inclusive os moradores que chegarem depois.

> 💡 **Regra prática:** se o elemento é criado por JavaScript depois do carregamento da página, use delegação.

> 🏋️ **Mini-exercício 11.1:** pegue o código "que não funciona" acima, teste, observe o problema e depois corrija usando delegação.

---

## 12. Formulários e validação

```html
<form id="formContato">
  <input type="text" id="nome" placeholder="Seu nome">
  <input type="email" id="email" placeholder="Seu e-mail">
  <button type="submit">Enviar</button>
</form>
<p id="mensagemErro" style="color:red;"></p>
```

```javascript
$("#formContato").on("submit", function (evento) {
  evento.preventDefault();

  let nome = $("#nome").val().trim();
  let email = $("#email").val().trim();

  if (nome === "" || email === "") {
    $("#mensagemErro").text("Por favor, preencha todos os campos.");
    return;
  }

  // Validação simples de e-mail
  let regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!regexEmail.test(email)) {
    $("#mensagemErro").text("Digite um e-mail válido.");
    return;
  }

  $("#mensagemErro").text("");
  alert("Formulário válido! Enviando...");
});
```

### Validação visual: destacar o campo com erro

Uma mensagem sozinha ajuda pouco; mostrar **qual** campo está errado ajuda muito mais:

```css
.invalido { border: 2px solid crimson; background: #fff0f0; }
.valido   { border: 2px solid seagreen; }
.erro     { color: crimson; font-size: 0.85em; }
```

```html
<form id="cadastro">
  <input type="text" id="nome" placeholder="Nome">
  <span class="erro" id="erroNome"></span><br>

  <input type="password" id="senha" placeholder="Senha (mín. 6 caracteres)">
  <span class="erro" id="erroSenha"></span><br>

  <button type="submit">Cadastrar</button>
</form>
```

```javascript
$(function () {

  // Função reutilizável: marca o campo como válido ou inválido
  function validar(campo, erro, condicao, mensagem) {
    if (condicao) {
      campo.removeClass("invalido").addClass("valido");
      erro.text("");
    } else {
      campo.removeClass("valido").addClass("invalido");
      erro.text(mensagem);
    }
    return condicao;
  }

  // Valida enquanto o usuário sai de cada campo
  $("#nome").on("blur", function () {
    validar($(this), $("#erroNome"), $(this).val().trim() !== "", "Informe o nome.");
  });

  $("#senha").on("blur", function () {
    validar($(this), $("#erroSenha"), $(this).val().length >= 6, "Mínimo de 6 caracteres.");
  });

  // Valida tudo ao enviar
  $("#cadastro").on("submit", function (evento) {
    evento.preventDefault();

    let nomeOk  = validar($("#nome"),  $("#erroNome"),  $("#nome").val().trim() !== "", "Informe o nome.");
    let senhaOk = validar($("#senha"), $("#erroSenha"), $("#senha").val().length >= 6, "Mínimo de 6 caracteres.");

    if (nomeOk && senhaOk) {
      alert("Cadastro realizado com sucesso!");
    }
  });
});
```

### Exemplo: aceitar apenas números em um campo

```javascript
$("#idade").on("input", function () {
  // remove tudo o que não for dígito
  $(this).val($(this).val().replace(/\D/g, ""));
});
```

### Exemplo: confirmar senha

```javascript
$("#confirmaSenha").on("keyup", function () {
  if ($(this).val() === $("#senha").val()) {
    $("#aviso").text("As senhas conferem ✔").css("color", "seagreen");
  } else {
    $("#aviso").text("As senhas são diferentes ✖").css("color", "crimson");
  }
});
```

### Exemplo: calculadora de média

```html
<input type="number" id="n1" placeholder="Nota 1">
<input type="number" id="n2" placeholder="Nota 2">
<button id="btnMedia">Calcular média</button>
<p id="resultado"></p>
```

```javascript
$("#btnMedia").click(function () {
  let n1 = parseFloat($("#n1").val());
  let n2 = parseFloat($("#n2").val());

  if (isNaN(n1) || isNaN(n2)) {
    $("#resultado").text("Informe as duas notas.");
    return;
  }

  let media = (n1 + n2) / 2;
  let situacao = media >= 6 ? "Aprovado(a)" : "Reprovado(a)";
  $("#resultado").text("Média: " + media.toFixed(1) + " — " + situacao);
});
```

> ⚠️ **Erro comum:** `.val()` sempre devolve **texto**. Somar `"5" + "3"` dá `"53"`, não `8`. Converta com `parseFloat()` ou `parseInt()` antes de calcular.

> 💡 **Lembrete:** a validação no navegador melhora a experiência do usuário, mas **não é segurança**. Em sistemas reais, o servidor precisa validar tudo de novo.

> 🏋️ **Mini-exercício 12.1:** em um formulário com "E-mail" e "Idade", valide: e-mail no formato correto e idade entre 0 e 120. Destaque com a classe `invalido` o campo errado.

---

## 13. Requisições AJAX (introdução)

O jQuery simplifica bastante o envio e recebimento de dados sem recarregar a página.

```javascript
$.ajax({
  url: "https://api.exemplo.com/dados",
  method: "GET",
  success: function (resposta) {
    console.log("Dados recebidos:", resposta);
    $("#resultado").html(resposta.mensagem);
  },
  error: function () {
    console.log("Erro ao buscar dados.");
  }
});
```

Forma simplificada com `.get()`:

```javascript
$.get("https://api.exemplo.com/dados", function (resposta) {
  console.log(resposta);
});
```

### Exemplo completo com arquivo JSON local

Crie o arquivo `dados/produtos.json`:

```json
[
  { "nome": "Mouse",   "preco": 59.90 },
  { "nome": "Teclado", "preco": 120.00 },
  { "nome": "Monitor", "preco": 899.90 }
]
```

HTML:

```html
<button id="btnCarregar">Carregar produtos</button>
<p id="status"></p>
<table id="tabelaProdutos">
  <thead><tr><th>Produto</th><th>Preço</th></tr></thead>
  <tbody></tbody>
</table>
```

JavaScript:

```javascript
$("#btnCarregar").click(function () {
  $("#status").text("Carregando...");

  $.getJSON("dados/produtos.json")
    .done(function (produtos) {
      $("#tabelaProdutos tbody").empty();

      $.each(produtos, function (i, produto) {
        let linha = $("<tr>")
          .append($("<td>").text(produto.nome))
          .append($("<td>").text("R$ " + produto.preco.toFixed(2)));
        $("#tabelaProdutos tbody").append(linha);
      });

      $("#status").text(produtos.length + " produtos carregados.");
    })
    .fail(function () {
      $("#status").text("Erro ao carregar os dados.");
    });
});
```

> ⚠️ **Importante:** abrir o HTML com duplo clique (endereço `file:///...`) **bloqueia** a leitura de arquivos pelo navegador. Rode o projeto com a extensão **Live Server** do VS Code (ou com `python -m http.server` na pasta do projeto) e acesse por `http://localhost`.

### Exemplo com API pública

```javascript
$.getJSON("https://viacep.com.br/ws/74000000/json/", function (dados) {
  console.log(dados.logradouro, dados.bairro, dados.localidade);
});
```

> 💡 O ViaCEP é uma API pública gratuita de consulta de CEP. Para usar com um CEP digitado pelo usuário, monte a URL com `"https://viacep.com.br/ws/" + cep + "/json/"`.

### Entendendo as etapas

1. O usuário clica → o código **pede** os dados ao servidor;
2. A página **continua funcionando** enquanto espera (por isso o "Carregando...");
3. Quando a resposta chega, `.done()` é executado; se algo falhar, `.fail()`.

> 🏋️ **Mini-exercício 13.1:** crie um `dados/alunos.json` com nome e nota de 4 alunos e liste na tela somente os **aprovados** (nota ≥ 6).

---

## 14. Projetos práticos guiados

### Projeto A — Lista de tarefas (To-Do List)

**Requisitos:**

1. Um campo de texto e um botão "Adicionar" (também deve funcionar com a tecla Enter);
2. Ao adicionar, o texto vira um novo item `<li>` na lista;
3. Clicar no texto da tarefa a marca como **concluída** (riscada);
4. Cada item tem um botão "Remover";
5. Ao passar o mouse sobre um item, ele muda de cor (via classe);
6. Um contador mostra quantas tarefas ainda estão **pendentes**.

**HTML:**

```html
<h2>Minhas tarefas</h2>
<input type="text" id="novaTarefa" placeholder="Digite uma tarefa">
<button id="btnAdicionar">Adicionar</button>
<p>Pendentes: <span id="pendentes">0</span></p>
<ul id="listaTarefas"></ul>
```

**CSS:**

```css
#listaTarefas li { cursor: pointer; padding: 6px; }
#listaTarefas li.hover { background-color: #f0f0f0; }
#listaTarefas li.concluida .texto { text-decoration: line-through; color: gray; }
```

**JavaScript:**

```javascript
$(function () {

  function atualizarContador() {
    let pendentes = $("#listaTarefas li").not(".concluida").length;
    $("#pendentes").text(pendentes);
  }

  function adicionarTarefa() {
    let tarefa = $("#novaTarefa").val().trim();
    if (tarefa === "") return;

    let item = $("<li>")
      .append($("<span>", { class: "texto", text: tarefa }))
      .append(" ")
      .append($("<button>", { class: "btnRemover", text: "Remover" }));

    $("#listaTarefas").append(item);
    $("#novaTarefa").val("").focus();
    atualizarContador();
  }

  $("#btnAdicionar").click(adicionarTarefa);

  $("#novaTarefa").on("keyup", function (e) {
    if (e.key === "Enter") adicionarTarefa();
  });

  // Delegação de eventos: funciona também para itens criados depois
  $("#listaTarefas").on("click", ".btnRemover", function (e) {
    e.stopPropagation();   // evita que o clique também "conclua" a tarefa
    $(this).closest("li").remove();
    atualizarContador();
  });

  $("#listaTarefas").on("click", "li", function () {
    $(this).toggleClass("concluida");
    atualizarContador();
  });

  $("#listaTarefas").on("mouseenter", "li", function () {
    $(this).addClass("hover");
  });

  $("#listaTarefas").on("mouseleave", "li", function () {
    $(this).removeClass("hover");
  });
});
```

> 💡 Note três pontos importantes: (1) a **delegação** nos eventos da lista; (2) o uso de `.text` ao criar o `<span>`, que protege contra HTML malicioso; (3) o `e.stopPropagation()`, que impede que o clique no botão "Remover" também seja tratado como clique no `<li>`.

**Desafios extras:** salvar as tarefas no `localStorage`; adicionar botão "Limpar concluídas"; permitir editar uma tarefa com duplo clique (`dblclick`).

---

### Projeto B — Abas (tabs)

```html
<div class="abas">
  <button class="aba" data-alvo="#sobre">Sobre</button>
  <button class="aba" data-alvo="#cursos">Cursos</button>
  <button class="aba" data-alvo="#contato">Contato</button>
</div>

<div class="conteudo" id="sobre">Texto sobre a instituição.</div>
<div class="conteudo" id="cursos">Lista de cursos.</div>
<div class="conteudo" id="contato">Telefone e e-mail.</div>
```

```css
.aba { padding: 8px 16px; border: 0; background: #ddd; cursor: pointer; }
.aba.ativa { background: #0d6efd; color: white; }
.conteudo { display: none; padding: 15px; border: 1px solid #ddd; }
```

```javascript
$(function () {
  $(".aba").click(function () {
    $(".aba").removeClass("ativa");
    $(this).addClass("ativa");

    $(".conteudo").hide();
    $($(this).data("alvo")).fadeIn();
  });

  // Abre a primeira aba ao carregar
  $(".aba").first().click();
});
```

Aqui você reutiliza o padrão **"limpa todos, marca o atual"** (seção 7) junto com o atributo `data-*` (seção 6).

---

### Projeto C — Galeria com "lightbox" simples

```html
<div class="galeria">
  <img src="img/foto1.jpg" alt="Foto 1" width="120">
  <img src="img/foto2.jpg" alt="Foto 2" width="120">
  <img src="img/foto3.jpg" alt="Foto 3" width="120">
</div>

<div id="fundo" style="display:none;">
  <img id="ampliada" src="" alt="">
</div>
```

```css
#fundo {
  position: fixed; inset: 0; background: rgba(0, 0, 0, 0.8);
  display: flex; align-items: center; justify-content: center;
}
#ampliada { max-width: 80%; max-height: 80%; }
```

```javascript
$(".galeria img").click(function () {
  $("#ampliada").attr("src", $(this).attr("src"));
  $("#fundo").css("display", "flex").hide().fadeIn();
});

$("#fundo").click(function () {
  $(this).fadeOut();
});
```

---

### Projeto D — Mini-carrinho de compras

```html
<div class="produto" data-nome="Mouse" data-preco="59.90">
  Mouse — R$ 59,90 <button class="add">Adicionar</button>
</div>
<div class="produto" data-nome="Teclado" data-preco="120">
  Teclado — R$ 120,00 <button class="add">Adicionar</button>
</div>

<h3>Carrinho</h3>
<ul id="carrinho"></ul>
<p>Total: R$ <span id="total">0.00</span></p>
```

```javascript
$(function () {
  let total = 0;

  $(".add").click(function () {
    let produto = $(this).closest(".produto");
    let nome  = produto.data("nome");
    let preco = parseFloat(produto.data("preco"));

    $("#carrinho").append($("<li>").text(nome + " — R$ " + preco.toFixed(2)));

    total += preco;
    $("#total").text(total.toFixed(2));
  });
});
```

**Desafios:** botão para remover item (atualizando o total); campo de quantidade; cupom de desconto.

---

## 15. Erros comuns e como depurar

### Tabela de erros

| Sintoma | Causa provável | Como resolver |
| ------- | -------------- | ------------- |
| `$ is not defined` | O jQuery não foi carregado, ou o seu script veio **antes** dele | Confira a ordem das tags `<script>` e o endereço do CDN |
| Nada acontece, sem erro | Seletor errado (falta `#` ou `.`, erro de digitação) | `console.log($("seu-seletor").length)`: se der 0, o seletor não encontrou nada |
| Funciona no console, mas não na página | Código rodando antes do HTML existir | Coloque o script no final do `<body>` ou use `$(function(){ ... })` |
| O evento não funciona em itens novos | Elementos criados depois do carregamento | Use delegação: `$(pai).on("click", ".filho", ...)` |
| A página recarrega ao enviar o formulário | Faltou `evento.preventDefault()` | Adicione-o no início do `submit` |
| `"5" + "3"` resulta em `"53"` | `.val()` devolve texto | Converta com `parseFloat()` / `parseInt()` |
| O clique dispara na hora da carga | Parênteses a mais: `.click(funcao())` | Escreva `.click(funcao)` |
| Clicar vários vezes rápido "buga" a animação | Animações acumuladas | Use `.stop()` antes do efeito |
| `$(this)` não funciona como esperado | Uso de *arrow function* `() => {}` | Use `function () { }` quando precisar do `this` do jQuery |
| Erro de CORS / arquivo não carrega no AJAX | Página aberta como `file:///` | Use Live Server ou `http://localhost` |

### Ferramentas de depuração

1. **`console.log()`:** escreva o valor das variáveis para ver o que está acontecendo.
   ```javascript
   let nome = $("#nome").val();
   console.log("nome =", nome);
   ```
2. **Aba Console (F12):** mostra erros em vermelho, com o número da linha.
3. **Aba Elements (F12):** mostra o HTML **como ele está agora** (após o jQuery alterar). Excelente para conferir se `addClass`, `append` etc. fizeram o que você esperava.
4. **`.length`:** confirma se o seletor encontrou algo.
5. **Pontos de parada (aba Sources):** pausam o código em uma linha para você inspecionar as variáveis.

> 💡 **Método de depuração em 3 perguntas:**
> 1. O jQuery foi carregado? (`$.fn.jquery`)
> 2. O seletor encontrou o elemento? (`.length`)
> 3. O evento foi disparado? (um `console.log` dentro da função)

---

## 16. Boas práticas

- ✅ Sempre carregue o jQuery **antes** do seu próprio script;
- ✅ Use `$(function() {...})` para garantir que o DOM já carregou;
- ✅ Prefira `addClass()`/`removeClass()`/`toggleClass()` a `.css()` direto no JS, mantendo o CSS separado;
- ✅ Use `.text()` (e não `.html()`) para exibir conteúdo digitado pelo usuário;
- ✅ **Guarde seletores usados várias vezes** em variáveis, evitando buscar o mesmo elemento repetidamente:
  ```javascript
  let $lista = $("#lista");      // o "$" no nome é só uma convenção
  $lista.empty();
  $lista.append("<li>Item</li>");
  ```
- ✅ Prefira `.on("evento", ...)` à forma curta `.click(...)`: é mais flexível e permite delegação;
- ✅ Dê nomes claros a ids e classes (`btnAdicionar`, `listaTarefas`);
- ✅ Transforme trechos repetidos em **funções** (como `validar()` e `criarCard()` nos exemplos);
- ✅ Comente seu código para facilitar o entendimento;
- ✅ Evite abusar de efeitos: use apenas onde fizer sentido para a experiência do usuário;
- ⚠️ Lembre-se: hoje em dia o JavaScript moderno (ES6+) e frameworks como React/Vue substituem boa parte do uso do jQuery em projetos novos, mas ele continua sendo uma ótima porta de entrada para entender manipulação de DOM, e ainda está presente em muitos sistemas existentes.

---

## 17. Cola rápida: jQuery × JavaScript puro

Conhecer os dois lados facilita a transição para o JavaScript moderno.

| Tarefa | jQuery | JavaScript puro |
| ------ | ------ | --------------- |
| Esperar o carregamento | `$(function(){ })` | `document.addEventListener("DOMContentLoaded", function(){ })` |
| Selecionar um elemento | `$("#id")` | `document.querySelector("#id")` |
| Selecionar vários | `$(".classe")` | `document.querySelectorAll(".classe")` |
| Ler/definir texto | `.text("oi")` | `el.textContent = "oi"` |
| Ler/definir HTML | `.html("<b>oi</b>")` | `el.innerHTML = "<b>oi</b>"` |
| Ler/definir valor | `.val()` | `el.value` |
| Ler/definir atributo | `.attr("src", "a.png")` | `el.setAttribute("src", "a.png")` |
| Adicionar classe | `.addClass("x")` | `el.classList.add("x")` |
| Alternar classe | `.toggleClass("x")` | `el.classList.toggle("x")` |
| Esconder | `.hide()` | `el.style.display = "none"` |
| Evento de clique | `.on("click", fn)` | `el.addEventListener("click", fn)` |
| Adicionar ao final | `.append(x)` | `el.append(x)` |
| Remover | `.remove()` | `el.remove()` |
| Requisição GET | `$.getJSON(url, fn)` | `fetch(url).then(r => r.json()).then(fn)` |

---

## 18. Exercícios propostos

### Nível 1 — Aquecimento

1. Um botão que esconde/mostra uma imagem.
2. Um parágrafo que muda de cor quando o mouse passa sobre ele.
3. Um botão que adiciona um novo parágrafo com a data/hora atual (`new Date().toLocaleString()`).

### Nível 2 — Intermediário

4. Um formulário de login com validação (campos obrigatórios e senha com mínimo de 6 caracteres) que mostra mensagens de erro abaixo de cada campo.
5. Um "seletor de estrelas" (★★★★★): ao clicar na 3ª estrela, as três primeiras ficam amarelas e as demais, cinzas.
6. Uma lista de cidades com campo de busca instantânea que também mostra "Nenhum resultado" quando nada combina.
7. Um quiz de 3 perguntas com botões de resposta: mostre "Acertou!" ou "Errou!" e some a pontuação final.

### Nível 3 — Desafio

8. Uma tabela de produtos carregada de um arquivo JSON, com campo de busca e botões para **ordenar** por nome ou por preço.
9. Um carrossel de imagens com botões "Anterior" e "Próxima" e troca automática a cada 3 segundos (`setInterval`).
10. Uma lista de tarefas que **salva no `localStorage`** e recarrega as tarefas ao abrir a página.

### Projeto final sugerido

Monte uma **página de apresentação pessoal** que combine, no mínimo:

- um menu que abre e fecha (seção 9);
- um acordeão de "Habilidades" (seção 9);
- uma galeria de imagens (projeto C);
- um formulário de contato validado (seção 12);
- um modo escuro (seção 7);
- uma lista carregada de um arquivo JSON (seção 13).

---

## 19. Referências para continuar estudando

- Documentação oficial: <https://api.jquery.com>
- jQuery Learning Center: <https://learn.jquery.com>
- CDN oficial: <https://code.jquery.com>
- Referência de JavaScript (MDN, em português): <https://developer.mozilla.org/pt-BR/docs/Web/JavaScript>
- Seletores CSS (base para os seletores do jQuery): <https://developer.mozilla.org/pt-BR/docs/Web/CSS/CSS_selectors>
- Lista de APIs públicas para praticar AJAX: <https://github.com/public-apis/public-apis>

---

*Material de apoio — Disciplina de Programação Web I — Curso de Engenharia de Software*
