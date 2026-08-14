# Tutorial de jQuery para Iniciantes

> Um guia introdutório para quem nunca programou com jQuery, pensado para a disciplina de Programação Web I.

---

## Índice

1. [O que é jQuery](#1-o-que-é-jquery)
2. [Como incluir o jQuery no seu projeto](#2-como-incluir-o-jquery-no-seu-projeto)
3. [Sintaxe básica: o `$`](#3-sintaxe-básica-o-)
4. [Selecionando elementos](#4-selecionando-elementos)
5. [Manipulando conteúdo e atributos](#5-manipulando-conteúdo-e-atributos)
6. [Manipulando CSS e classes](#6-manipulando-css-e-classes)
7. [Eventos](#7-eventos)
8. [Efeitos e animações](#8-efeitos-e-animações)
9. [Manipulando a estrutura do DOM](#9-manipulando-a-estrutura-do-dom)
10. [Formulários e validação simples](#10-formulários-e-validação-simples)
11. [Requisições AJAX (introdução)](#11-requisições-ajax-introdução)
12. [Boas práticas](#12-boas-práticas)
13. [Exercício prático guiado](#13-exercício-prático-guiado)
14. [Referências para continuar estudando](#14-referências-para-continuar-estudando)

---

## 1. O que é jQuery

jQuery é uma **biblioteca JavaScript** criada em 2006 para facilitar tarefas comuns no desenvolvimento web, como:

- selecionar e alterar elementos HTML;
- reagir a eventos do usuário (clique, hover, etc.);
- criar efeitos e animações;
- enviar requisições ao servidor sem recarregar a página (AJAX).

Sua principal vantagem é **reduzir a quantidade de código** necessária em comparação ao JavaScript puro (chamado de "vanilla JS"), além de lidar com diferenças entre navegadores.

```javascript
// JavaScript puro
document.querySelector("#titulo").style.color = "blue";

// jQuery
$("#titulo").css("color", "blue");
```

---

## 2. Como incluir o jQuery no seu projeto

### Opção 1 — via CDN (mais comum para estudos)

Adicione a linha abaixo antes do fechamento da tag `</body>` no seu HTML:

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

---

## 3. Sintaxe básica: o `$`

Todo código jQuery segue este padrão:

```javascript
$(seletor).ação();
```

- `$` é um atalho para a função `jQuery()`;
- `seletor` indica **qual(is) elemento(s)** você quer manipular (parecido com CSS);
- `ação()` é o que você quer fazer com esse elemento.

### Garantindo que o documento carregou

Antes de manipular elementos, é uma boa prática esperar a página carregar completamente:

```javascript
$(document).ready(function() {
  // seu código aqui
});
```

Forma reduzida (bastante usada):

```javascript
$(function() {
  // seu código aqui
});
```

---

## 4. Selecionando elementos

Os seletores do jQuery são muito parecidos com os do CSS:

```javascript
$("#meuId")         // seleciona por ID
$(".minhaClasse")   // seleciona por classe
$("p")              // seleciona todas as tags <p>
$("div.card")       // seleciona <div> com classe "card"
$("ul li")          // seleciona <li> dentro de <ul>
$("input[type='text']") // seleciona inputs do tipo texto
```

**Exemplo prático:**

```html
<p class="destaque">Texto 1</p>
<p>Texto 2</p>
```

```javascript
$(".destaque").css("font-weight", "bold");
```

---

## 5. Manipulando conteúdo e atributos

| Método | O que faz |
|---|---|
| `.text()` | Lê ou define o texto de um elemento |
| `.html()` | Lê ou define o HTML interno de um elemento |
| `.val()` | Lê ou define o valor de um campo de formulário |
| `.attr()` | Lê ou define um atributo (ex: `src`, `href`) |

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

---

## 6. Manipulando CSS e classes

```javascript
// Alterar uma propriedade CSS diretamente
$("#caixa").css("background-color", "lightblue");

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

> 💡 **Dica:** prefira usar `addClass()`/`removeClass()`/`toggleClass()` em vez de `.css()` sempre que possível — isso mantém o estilo organizado no CSS, e o JavaScript só controla o comportamento.

---

## 7. Eventos

Eventos permitem que seu código reaja a ações do usuário.

```javascript
// Clique
$("#botao").click(function() {
  alert("Botão clicado!");
});

// Mesma coisa, forma mais moderna
$("#botao").on("click", function() {
  alert("Botão clicado!");
});

// Hover (mouse entra e sai)
$("#card").hover(
  function() { $(this).addClass("destacado"); },
  function() { $(this).removeClass("destacado"); }
);

// Envio de formulário
$("#form").on("submit", function(evento) {
  evento.preventDefault(); // impede o recarregamento da página
  alert("Formulário enviado!");
});
```

### Outros eventos comuns

| Evento | Quando acontece |
|---|---|
| `click` | Ao clicar no elemento |
| `dblclick` | Ao clicar duas vezes |
| `mouseenter` / `mouseleave` | Ao passar o mouse sobre / sair |
| `keyup` / `keydown` | Ao soltar / pressionar uma tecla |
| `change` | Ao mudar o valor de um campo |
| `submit` | Ao enviar um formulário |
| `scroll` | Ao rolar a página |

---

## 8. Efeitos e animações

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

Você também pode definir a duração (em milissegundos) e uma função de callback:

```javascript
$("#caixa").fadeOut(500, function() {
  console.log("Animação concluída!");
});
```

**Exemplo clássico — menu que abre e fecha:**

```html
<button id="btnMenu">Menu</button>
<ul id="menu" style="display:none;">
  <li>Início</li>
  <li>Sobre</li>
  <li>Contato</li>
</ul>
```

```javascript
$("#btnMenu").click(function() {
  $("#menu").slideToggle();
});
```

---

## 9. Manipulando a estrutura do DOM

```javascript
// Adicionar conteúdo dentro de um elemento
$("#lista").append("<li>Novo item</li>");   // no final
$("#lista").prepend("<li>Primeiro item</li>"); // no início

// Remover um elemento
$("#item3").remove();

// Esvaziar o conteúdo de um elemento (sem remover ele mesmo)
$("#lista").empty();

// Clonar um elemento
let copia = $("#card").clone();
$("#container").append(copia);
```

**Exemplo — adicionar itens a uma lista dinamicamente:**

```html
<input type="text" id="novoItem">
<button id="btnAdicionar">Adicionar</button>
<ul id="listaTarefas"></ul>
```

```javascript
$("#btnAdicionar").click(function() {
  let valor = $("#novoItem").val();
  if (valor !== "") {
    $("#listaTarefas").append("<li>" + valor + "</li>");
    $("#novoItem").val(""); // limpa o campo
  }
});
```

---

## 10. Formulários e validação simples

```html
<form id="formContato">
  <input type="text" id="nome" placeholder="Seu nome">
  <input type="email" id="email" placeholder="Seu e-mail">
  <button type="submit">Enviar</button>
</form>
<p id="mensagemErro" style="color:red;"></p>
```

```javascript
$("#formContato").on("submit", function(evento) {
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

---

## 11. Requisições AJAX (introdução)

O jQuery simplifica bastante o envio e recebimento de dados sem recarregar a página.

```javascript
$.ajax({
  url: "https://api.exemplo.com/dados",
  method: "GET",
  success: function(resposta) {
    console.log("Dados recebidos:", resposta);
    $("#resultado").html(resposta.mensagem);
  },
  error: function() {
    console.log("Erro ao buscar dados.");
  }
});
```

Forma simplificada com `.get()`:

```javascript
$.get("https://api.exemplo.com/dados", function(resposta) {
  console.log(resposta);
});
```

> 📌 Para o seu projeto da disciplina (sem backend real), você pode simular uma resposta usando um arquivo `.json` local ou apenas demonstrar o conceito com dados fixos no próprio JavaScript.

---

## 12. Boas práticas

- ✅ Sempre carregue o jQuery **antes** do seu próprio script;
- ✅ Use `$(function() {...})` para garantir que o DOM já carregou;
- ✅ Prefira `addClass()`/`removeClass()` a `.css()` direto no JS, mantendo o CSS separado;
- ✅ Comente seu código para facilitar o entendimento;
- ✅ Evite abusar de efeitos — use apenas onde fizer sentido para a experiência do usuário;
- ⚠️ Lembre-se: hoje em dia o JavaScript moderno (ES6+) e frameworks como React/Vue substituem boa parte do uso do jQuery em projetos novos — mas ele continua sendo uma ótima porta de entrada para entender manipulação de DOM.

---

## 13. Exercício prático guiado

Crie um arquivo `index.html` com uma lista de tarefas simples ("To-Do List") usando apenas jQuery:

**Requisitos:**
1. Um campo de texto e um botão "Adicionar";
2. Ao clicar em "Adicionar", o texto digitado deve virar um novo item `<li>` na lista;
3. Cada item da lista deve ter um botão "Remover" que apaga o item ao ser clicado;
4. Ao passar o mouse sobre um item, ele deve mudar de cor (usando `hover()`).

**Estrutura sugerida:**

```html
<input type="text" id="novaTarefa" placeholder="Digite uma tarefa">
<button id="btnAdicionar">Adicionar</button>
<ul id="listaTarefas"></ul>
```

```javascript
$(function() {
  $("#btnAdicionar").click(function() {
    let tarefa = $("#novaTarefa").val().trim();
    if (tarefa !== "") {
      $("#listaTarefas").append(
        "<li>" + tarefa + " <button class='btnRemover'>Remover</button></li>"
      );
      $("#novaTarefa").val("");
    }
  });

  // Delegação de evento: funciona mesmo para itens criados depois
  $("#listaTarefas").on("click", ".btnRemover", function() {
    $(this).parent("li").remove();
  });

  $("#listaTarefas").on("mouseenter", "li", function() {
    $(this).css("background-color", "#f0f0f0");
  });

  $("#listaTarefas").on("mouseleave", "li", function() {
    $(this).css("background-color", "transparent");
  });
});
```

> 💡 Note o uso de `.on("click", ".btnRemover", ...)` em vez de `.click()` direto — essa técnica é chamada de **delegação de eventos** e é necessária para elementos criados dinamicamente após o carregamento da página.

---

## 14. Referências para continuar estudando

- Documentação oficial: https://api.jquery.com
- jQuery Learning Center: https://learn.jquery.com
- CDN oficial: https://code.jquery.com

---

*Material de apoio — Disciplina de Programação Web I — Curso de Engenharia de Software*
