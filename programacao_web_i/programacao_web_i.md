# 🌐 Tutorial de Programação Web - Lado Cliente (Front-End)

Um guia completo para iniciantes que querem dominar o desenvolvimento web do lado do cliente!

---

## 📌 Índice

1. [O que é Programação Web do Lado Cliente?](#-o-que-é-programação-web-do-lado-cliente)
2. [Como Funciona a Web?](#-como-funciona-a-web)
3. [Linguagens de Marcação](#-linguagens-de-marcação)
4. [Linguagens de Estilização](#-linguagens-de-estilização)
5. [Linguagens de Programação](#-linguagens-de-programação)
6. [Ferramentas de Desenvolvimento](#-ferramentas-de-desenvolvimento)
7. [Bibliotecas JavaScript](#-bibliotecas-javascript)
8. [Frameworks Front-End](#-frameworks-front-end)
9. [Gerenciadores de Pacotes](#-gerenciadores-de-pacotes)
10. [Ferramentas de Build e Bundlers](#-ferramentas-de-build-e-bundlers)
11. [Controle de Versão (Git)](#-controle-de-versão-git)
12. [Ferramentas de Teste](#-ferramentas-de-teste)
13. [Boas Práticas](#-boas-práticas)
14. [Roteiro de Aprendizado](#-roteiro-de-aprendizado)

---

## 📖 O que é Programação Web do Lado Cliente?

A programação web do lado cliente (também chamada de **Front-End**) é tudo que acontece no navegador do usuário. É a parte da aplicação web que o usuário vê, interage e experiencia.

> 💡 **Pense assim:** O Front-End é como o interior de um restaurante - o cliente vê a decoração, interage com os garçons, pede a comida. O Back-End é a cozinha - tudo acontece nos bastidores.

### O que o Front-End faz?
- 🎨 **Interface do Usuário** - Layout, design, botões
- 🖱️ **Interatividade** - Cliques, animações, scroll
- 📱 **Responsividade** - Adaptação a diferentes telas
- 🔄 **Comunicação** - Envia/recebe dados do servidor
- 🎯 **Experiência** - Feedback ao usuário

---

## 🔄 Como Funciona a Web?

### O Fluxo Básico:
```
1. Usuário digita URL → 2. Navegador faz requisição → 3. Servidor responde
4. Navegador baixa arquivos → 5. Navegador renderiza → 6. Usuário interage
```

### O que o navegador processa:
| Arquivo | Função | Exemplo |
|---------|--------|---------|
| **HTML** | Estrutura | `<h1>Título</h1>` |
| **CSS** | Estilo | `color: blue;` |
| **JavaScript** | Comportamento | `alert('Olá!')` |

---

## 📝 Linguagens de Marcação

### 1. HTML5 (HyperText Markup Language)

HTML é a espinha dorsal de qualquer página web. Define a estrutura e o conteúdo.

#### Estrutura Básica:
```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minha Página</title>
    <!-- CSS aqui -->
</head>
<body>
    <header>
        <h1>Bem-vindo!</h1>
    </header>
    <main>
        <p>Conteúdo principal</p>
        <button id="meuBotao">Clique aqui</button>
    </main>
    <footer>
        <p>© 2024</p>
    </footer>
    <!-- JavaScript aqui -->
</body>
</html>
```

#### Tags Mais Comuns:
```html
<!-- Estrutura -->
<header>Cabeçalho</header>
<nav>Menu de navegação</nav>
<main>Conteúdo principal</main>
<section>Seção</section>
<article>Artigo</article>
<aside>Conteúdo lateral</aside>
<footer>Rodapé</footer>

<!-- Texto -->
<h1>...<h6> Títulos</h1>
<p>Parágrafo</p>
<span>Texto inline</span>
<strong>Negrito</strong>
<em>Itálico</em>
<br> Quebra de linha
<hr> Linha divisória

<!-- Listas -->
<ul>
    <li>Item não ordenado</li>
</ul>
<ol>
    <li>Item ordenado</li>
</ol>
<dl>
    <dt>Termo</dt>
    <dd>Definição</dd>
</dl>

<!-- Links e Imagens -->
<a href="https://google.com">Link</a>
<img src="imagem.jpg" alt="Descrição">

<!-- Formulários -->
<form>
    <input type="text" placeholder="Nome">
    <input type="email" placeholder="Email">
    <input type="password" placeholder="Senha">
    <select>
        <option>Opção 1</option>
    </select>
    <textarea>Mensagem</textarea>
    <button type="submit">Enviar</button>
</form>

<!-- Tabelas -->
<table>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Idade</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>João</td>
            <td>30</td>
        </tr>
    </tbody>
</table>

<!-- Áudio e Vídeo -->
<audio controls>
    <source src="musica.mp3" type="audio/mpeg">
</audio>
<video controls width="400">
    <source src="video.mp4" type="video/mp4">
</video>

<!-- Elementos Semânticos -->
<time datetime="2024-01-01">1 de Janeiro</time>
<address>Rua das Flores, 123</address>
<code>console.log('Hello');</code>
<pre>Texto pré-formatado</pre>
<blockquote>Citação longa</blockquote>
<cite>Fonte da citação</cite>
```

#### HTML5 Novidades:
```html
<!-- Elementos de formulário modernos -->
<input type="date">
<input type="color">
<input type="range" min="0" max="100">
<input type="search">
<input type="tel">
<input type="number" min="1" max="10">

<!-- Elementos nativos -->
<details>
    <summary>Clique para ver mais</summary>
    <p>Conteúdo oculto que aparece quando clica</p>
</details>

<progress value="70" max="100">70%</progress>
<meter value="80" min="0" max="100">80%</meter>
<datalist id="opcoes">
    <option value="Brasil">
    <option value="Portugal">
</datalist>

<!-- Canvas (desenho) -->
<canvas id="meuCanvas" width="200" height="100"></canvas>
<script>
    const canvas = document.getElementById('meuCanvas');
    const ctx = canvas.getContext('2d');
    ctx.fillStyle = 'red';
    ctx.fillRect(10, 10, 50, 50);
</script>
```

### 2. XML (Extensible Markup Language)
Usado para transportar dados, menos comum hoje em dia (substituído por JSON).
```xml
<usuario>
    <nome>João</nome>
    <idade>30</idade>
    <email>joao@email.com</email>
</usuario>
```

### 3. Markdown
Usado para documentação, READMEs, etc.
```markdown
# Título principal
## Subtítulo

**Negrito** e *itálico*

- Lista não ordenada
- Item 2

1. Lista ordenada
2. Item 2

[Link](https://google.com)
```

---

## 🎨 Linguagens de Estilização

### CSS (Cascading Style Sheets)

CSS define a aparência dos elementos HTML.

#### Sintaxe Básica:
```css
seletor {
    propriedade: valor;
}

/* Exemplo */
p {
    color: blue;
    font-size: 16px;
    margin: 10px;
}
```

#### Seletores CSS:
```css
/* Por tag */
p { color: red; }

/* Por classe */
.destaque { background: yellow; }

/* Por ID */
#principal { width: 100%; }

/* Por atributo */
[type="text"] { border: 1px solid gray; }

/* Combinadores */
div p { /* p dentro de div */ }
div > p { /* p filho direto */ }
h1 + p { /* p imediatamente após h1 */ }
h1 ~ p { /* p que vem depois de h1 */ }

/* Pseudo-classes */
a:hover { color: red; }
input:focus { border-color: blue; }
li:first-child { font-weight: bold; }
li:nth-child(3) { background: #f0f0f0; }

/* Pseudo-elementos */
p::first-line { font-weight: bold; }
p::first-letter { font-size: 200%; }
p::before { content: "→ "; }
p::after { content: " ←"; }
```

#### Propriedades CSS Importantes:
```css
/* Cores e Fundo */
color: #ff0000;
background-color: rgb(255, 0, 0);
background-image: url('imagem.jpg');
background-size: cover;
background-position: center;
opacity: 0.8;

/* Texto */
font-family: Arial, sans-serif;
font-size: 16px;
font-weight: bold;
text-align: center;
text-decoration: underline;
text-transform: uppercase;
line-height: 1.5;
letter-spacing: 2px;

/* Box Model */
width: 100px;
height: 100px;
max-width: 100%;
min-height: 50px;
margin: 10px;
padding: 20px;
border: 2px solid black;
border-radius: 10px;
box-shadow: 5px 5px 10px rgba(0,0,0,0.5);

/* Layout */
display: block; /* inline, flex, grid, none */
position: relative; /* absolute, fixed, sticky */
top: 10px; left: 20px;
float: left;
clear: both;
overflow: hidden;
z-index: 10;
visibility: visible; /* hidden */

/* Flexbox */
display: flex;
flex-direction: row; /* column */
justify-content: center; /* space-between, space-around */
align-items: center; /* flex-start, flex-end */
flex-wrap: wrap;
gap: 20px;

/* Grid */
display: grid;
grid-template-columns: 1fr 1fr 1fr;
grid-template-rows: auto 100px;
gap: 15px;

/* Animações */
transition: all 0.3s ease;
transform: rotate(45deg) scale(1.2) translateX(10px);
animation: minhaAnimacao 2s infinite;

@keyframes minhaAnimacao {
    0% { opacity: 0; }
    50% { opacity: 1; }
    100% { opacity: 0; }
}

/* Responsividade */
@media (max-width: 768px) {
    body { font-size: 14px; }
}

/* Variáveis CSS */
:root {
    --cor-principal: #007bff;
    --espacamento: 20px;
}

.elemento {
    color: var(--cor-principal);
    margin: var(--espacamento);
}
```

#### Pré-processadores CSS:

**SASS/SCSS** (mais popular):
```scss
// Variáveis
$primary-color: #007bff;
$spacing: 20px;

// Aninhamento
.nav {
    background: dark;
    .menu-item {
        color: white;
        &:hover {
            color: $primary-color;
        }
    }
}

// Mixins
@mixin flex-center {
    display: flex;
    justify-content: center;
    align-items: center;
}

.container {
    @include flex-center;
}

// Herança
%button-base {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
}

.button-primary {
    @extend %button-base;
    background: $primary-color;
}

// Partials e Import
@import 'variables';
@import 'components/buttons';
```

**LESS**:
```less
@primary-color: #007bff;

.nav {
    background: dark;
    .menu-item {
        color: white;
        &:hover {
            color: @primary-color;
        }
    }
}
```

**PostCSS** (ferramenta de transformação):
```css
/* Usa plugins como Autoprefixer */
.flex-container {
    display: flex;
}
/* Gera automaticamente com prefixos */
```

---

## 💻 Linguagens de Programação

### 1. JavaScript - A Linguagem do Front-End

JavaScript é a única linguagem de programação nativa dos navegadores.

#### Fundamentos:
```javascript
// Comentários
// Comentário de uma linha
/* Comentário
   de múltiplas linhas */

// Variáveis
var nome = 'João'; // Antigo, não use mais
let idade = 30;    // Moderno, variável mutável
const PI = 3.14;   // Constante

// Tipos de Dados
let string = 'Olá mundo';
let number = 42;
let boolean = true; // ou false
let nullValue = null;
let undefinedValue = undefined;
let symbol = Symbol('id');
let bigInt = 9007199254740991n;

// Objetos
let pessoa = {
    nome: 'João',
    idade: 30,
    saudacao: function() {
        return `Olá, ${this.nome}`;
    }
};

// Arrays
let frutas = ['maçã', 'banana', 'laranja'];
frutas.push('uva'); // Adiciona no final
frutas.pop(); // Remove do final
frutas[0] = 'pera';

// Funções
// Declaração
function somar(a, b) {
    return a + b;
}

// Expressão
const subtrair = function(a, b) {
    return a - b;
};

// Arrow function (moderna)
const multiplicar = (a, b) => a * b;

// Parâmetros padrão
function saudacao(nome = 'Visitante') {
    return `Olá, ${nome}!`;
}

// Operadores
// Aritméticos: + - * / % **
// Atribuição: = += -= *= /=
// Comparação: == === != !== > < >= <=
// Lógicos: && (AND) || (OR) ! (NOT)

// Condicionais
if (idade >= 18) {
    console.log('Maior de idade');
} else {
    console.log('Menor de idade');
}

// Switch
switch (dia) {
    case 'segunda':
        console.log('Início da semana');
        break;
    case 'sexta':
        console.log('Sextou!');
        break;
    default:
        console.log('Outro dia');
}

// Operador Ternário
let status = idade >= 18 ? 'Adulto' : 'Menor';

// Laços
// For
for (let i = 0; i < 5; i++) {
    console.log(i);
}

// For of (arrays)
for (let fruta of frutas) {
    console.log(fruta);
}

// For in (objetos)
for (let chave in pessoa) {
    console.log(chave + ': ' + pessoa[chave]);
}

// While
let count = 0;
while (count < 5) {
    console.log(count);
    count++;
}

// Do while
do {
    console.log('Executa pelo menos uma vez');
} while (false);

// Tratamento de Erros
try {
    // Código que pode dar erro
    throw new Error('Algo deu errado');
} catch (erro) {
    console.error('Erro capturado:', erro.message);
} finally {
    console.log('Sempre executa');
}
```

#### Manipulação do DOM:
```javascript
// Selecionar elementos
document.getElementById('id');
document.querySelector('.classe');
document.querySelectorAll('p');
document.getElementsByTagName('div');
document.getElementsByClassName('classe');

// Manipular elementos
const element = document.querySelector('#meuElemento');
element.textContent = 'Novo texto';
element.innerHTML = '<strong>HTML</strong>';
element.style.color = 'red';
element.style.backgroundColor = 'yellow';
element.classList.add('destaque');
element.classList.remove('destaque');
element.classList.toggle('ativo');
element.setAttribute('data-info', 'valor');
element.getAttribute('data-info');
element.removeAttribute('data-info');

// Criar e remover elementos
const novoElemento = document.createElement('div');
novoElemento.textContent = 'Novo elemento';
document.body.appendChild(novoElemento);
element.parentNode.removeChild(element);

// Eventos
element.addEventListener('click', function(event) {
    console.log('Clicou!', event.target);
});

element.addEventListener('mouseover', () => console.log('Mouse em cima'));
element.addEventListener('keydown', (e) => console.log('Tecla:', e.key));

// Eventos comuns: click, dblclick, mouseover, mouseout, keydown, keyup, focus, blur, submit, load, scroll, change, input

// Obter informações do evento
function handleClick(event) {
    event.preventDefault(); // Previne ação padrão
    event.stopPropagation(); // Para propagação
    console.log('Tipo:', event.type);
    console.log('Alvo:', event.target);
    console.log('Posição X:', event.clientX);
    console.log('Posição Y:', event.clientY);
}
```

#### Conceitos Avançados:
```javascript
// Promise (Assíncrono)
const promessa = new Promise((resolve, reject) => {
    setTimeout(() => {
        resolve('Deu certo!');
        // reject('Deu erro!');
    }, 1000);
});

promessa
    .then(resultado => console.log(resultado))
    .catch(erro => console.error(erro))
    .finally(() => console.log('Finalizou'));

// Async/Await (Moderno)
async function buscarDados() {
    try {
        const resposta = await fetch('https://api.exemplo.com/dados');
        const dados = await resposta.json();
        console.log(dados);
    } catch (erro) {
        console.error('Erro:', erro);
    }
}

// Fetch API
fetch('https://api.exemplo.com/dados')
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro na requisição');
        }
        return response.json();
    })
    .then(data => console.log(data))
    .catch(error => console.error('Erro:', error));

// Funções de Array
const numeros = [1, 2, 3, 4, 5];
numeros.map(n => n * 2); // [2, 4, 6, 8, 10]
numeros.filter(n => n > 3); // [4, 5]
numeros.reduce((acc, n) => acc + n, 0); // 15
numeros.find(n => n === 3); // 3
numeros.some(n => n > 4); // true
numeros.every(n => n > 0); // true

// Spread Operator
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5]; // [1, 2, 3, 4, 5]

const obj1 = { nome: 'João' };
const obj2 = { ...obj1, idade: 30 }; // { nome: 'João', idade: 30 }

// Destructuring
const { nome, idade } = pessoa;
const [primeira, segunda] = frutas;

// Template Literals
const nome2 = 'João';
console.log(`Olá, ${nome2}!`);

// Classes (ES6)
class Pessoa {
    constructor(nome, idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    saudacao() {
        return `Olá, ${this.nome}`;
    }
    
    static metodoEstatico() {
        return 'Método estático';
    }
}

class Estudante extends Pessoa {
    constructor(nome, idade, curso) {
        super(nome, idade);
        this.curso = curso;
    }
}
```

#### TypeScript (JavaScript com tipos):
```typescript
// Tipos básicos
let nome: string = 'João';
let idade: number = 30;
let ativo: boolean = true;
let numeros: number[] = [1, 2, 3];
let tupla: [string, number] = ['João', 30];

// Interfaces
interface Usuario {
    nome: string;
    idade: number;
    email?: string; // Opcional
}

// Tipos personalizados
type Status = 'ativo' | 'inativo' | 'pendente';

// Classes com tipos
class PessoaTS {
    constructor(
        public nome: string,
        private idade: number
    ) {}
    
    saudacao(): string {
        return `Olá, ${this.nome}`;
    }
}

// Genéricos
function identidade<T>(valor: T): T {
    return valor;
}

// Enum
enum Cor {
    Vermelho = 'red',
    Verde = 'green',
    Azul = 'blue'
}
```

---

## 🛠️ Ferramentas de Desenvolvimento

### 1. Navegadores e DevTools

#### Google Chrome DevTools:
```javascript
// Atalhos (Windows/Linux: F12, Mac: Cmd+Option+I)

// Abas principais:
// - Elements: Ver/editar HTML e CSS ao vivo
// - Console: Executar JavaScript
// - Sources: Debug de código
// - Network: Monitorar requisições
// - Performance: Analisar performance
// - Application: Gerenciar storage, cookies
// - Lighthouse: Avaliar qualidade

// Comandos úteis no Console
console.log('Mensagem');
console.error('Erro');
console.warn('Aviso');
console.table(dados); // Tabela para arrays/objetos
console.time('tempo'); // Iniciar timer
console.timeEnd('tempo'); // Finalizar timer

// Debug
debugger; // Pausa a execução

// Monitoramento
monitorEvents(document.body, 'click');
unmonitorEvents(document.body, 'click');
```

#### Firefox Developer Tools:
- Similar ao Chrome DevTools
- Algumas ferramentas únicas para CSS Grid e Flexbox

### 2. Editores de Código

#### VS Code (Recomendado):
```json
// Extensões Essenciais:
{
    "extensions": [
        "ESLint",
        "Prettier",
        "Live Server",
        "GitLens",
        "JavaScript (ES6) code snippets",
        "HTML CSS Support",
        "Auto Rename Tag",
        "Bracket Pair Colorizer",
        "Color Highlight",
        "Path Intellisense",
        "npm Intellisense"
    ],
    
    // Settings recomendados:
    "settings": {
        "editor.formatOnSave": true,
        "editor.codeActionsOnSave": {
            "source.fixAll.eslint": true
        },
        "editor.tabSize": 2,
        "editor.fontSize": 14,
        "editor.wordWrap": "on",
        "files.autoSave": "onFocusChange"
    }
}
```

#### VS Code Live Server:
```html
<!-- Clique com botão direito no HTML e "Open with Live Server" -->
<!-- Atualiza automaticamente ao salvar -->
```

#### Outros Editores:
- **Sublime Text**: Rápido, leve
- **WebStorm**: IDE completa (paga)
- **Atom**: Editor do GitHub (descontinuado)
- **Brackets**: Focado em front-end

### 3. Navegadores para Teste
```javascript
// Extensões para teste de responsividade
// - Responsive Viewer
// - Window Resizer

// Teste em diferentes navegadores:
// - Chrome
// - Firefox
// - Safari (apenas Mac)
// - Edge
// - Brave, Opera, etc
```

---

## 📚 Bibliotecas JavaScript

### 1. jQuery (ainda muito usado)
```html
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(document).ready(function() {
    // Seleção simplificada
    $('#elemento').click(function() {
        $(this).fadeOut();
    });
    
    // AJAX simplificado
    $.get('https://api.exemplo.com/dados', function(dados) {
        console.log(dados);
    });
});
</script>
```

### 2. Lodash (utilitários)
```javascript
import _ from 'lodash';

_.map([1, 2, 3], n => n * 2); // [2, 4, 6]
_.filter([1, 2, 3, 4], n => n % 2 === 0); // [2, 4]
_.find(usuarios, { nome: 'João' });
_.groupBy(usuarios, 'idade');
_.deepClone(objeto);
_.debounce(funcao, 300); // Delay
_.throttle(funcao, 1000); // Limitador
```

### 3. Axios (HTTP Client)
```javascript
import axios from 'axios';

// GET
axios.get('https://api.exemplo.com/dados')
    .then(response => console.log(response.data))
    .catch(error => console.error(error));

// POST
axios.post('https://api.exemplo.com/dados', {
    nome: 'João',
    idade: 30
})
.then(response => console.log(response.data))
.catch(error => console.error(error));

// Interceptores
axios.interceptors.request.use(config => {
    config.headers.Authorization = 'Bearer token';
    return config;
});
```

### 4. Moment.js (Datas) - Depreciado
```javascript
// Atual: Day.js (leve) ou Date-fns (moderno)
import dayjs from 'dayjs';

dayjs().format('DD/MM/YYYY');
dayjs('2024-01-01').add(1, 'day');
dayjs().diff(dayjs('2024-01-01'), 'day');
```

### 5. Chart.js (Gráficos)
```javascript
import Chart from 'chart.js';

const ctx = document.getElementById('meuGrafico').getContext('2d');
new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Jan', 'Fev', 'Mar'],
        datasets: [{
            label: 'Vendas',
            data: [12, 19, 3],
            backgroundColor: 'blue'
        }]
    },
    options: {
        responsive: true
    }
});
```

### 6. D3.js (Visualização de Dados)
```javascript
import * as d3 from 'd3';

// Criação de gráficos complexos
d3.select('svg')
    .append('circle')
    .attr('r', 10)
    .style('fill', 'red');
```

---

## 🚀 Frameworks Front-End

### 1. React (Biblioteca para Interfaces)
```jsx
// Componente Funcional
import React, { useState, useEffect } from 'react';

function Contador() {
    const [count, setCount] = useState(0);
    
    useEffect(() => {
        document.title = `Contagem: ${count}`;
    }, [count]);
    
    return (
        <div>
            <p>Contador: {count}</p>
            <button onClick={() => setCount(count + 1)}>
                Incrementar
            </button>
        </div>
    );
}

// Props
function Saudacao({ nome, idade }) {
    return <h1>Olá, {nome}! Você tem {idade} anos.</h1>;
}

// Hooks personalizados
function useWindowWidth() {
    const [width, setWidth] = useState(window.innerWidth);
    
    useEffect(() => {
        const handleResize = () => setWidth(window.innerWidth);
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);
    
    return width;
}
```

### 2. Vue.js (Progressivo)
```vue
<template>
    <div>
        <h1>{{ mensagem }}</h1>
        <button @click="incrementar">Clique: {{ count }}</button>
        
        <input v-model="nome">
        <p>Olá, {{ nome }}</p>
        
        <ul>
            <li v-for="item in lista" :key="item.id">
                {{ item.nome }}
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {
            mensagem: 'Olá Vue!',
            count: 0,
            nome: '',
            lista: [
                { id: 1, nome: 'Item 1' },
                { id: 2, nome: 'Item 2' }
            ]
        };
    },
    methods: {
        incrementar() {
            this.count++;
        }
    },
    computed: {
        mensagemFormatada() {
            return this.mensagem.toUpperCase();
        }
    },
    watch: {
        count(novoValor) {
            console.log('Count mudou para:', novoValor);
        }
    },
    mounted() {
        console.log('Componente montado');
    }
};
</script>

<style scoped>
h1 {
    color: blue;
}
</style>
```

### 3. Angular (Framework Completo)
```typescript
// Componente
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-componente',
    template: `
        <h1>{{ titulo }}</h1>
        <button (click)="incrementar()">
            Clique: {{ contador }}
        </button>
    `,
    styles: [`
        h1 { color: blue; }
    `]
})
export class MeuComponente implements OnInit {
    titulo = 'Angular App';
    contador = 0;
    
    ngOnInit() {
        console.log('Componente inicializado');
    }
    
    incrementar() {
        this.contador++;
    }
}

// Serviço
@Injectable({
    providedIn: 'root'
})
export class ApiService {
    constructor(private http: HttpClient) {}
    
    getDados() {
        return this.http.get('https://api.exemplo.com/dados');
    }
}
```

### 4. Svelte (Compilador)
```svelte
<script>
    let count = 0;
    let nome = '';
    
    function incrementar() {
        count += 1;
    }
    
    $: console.log(`Contador é ${count}`); // Reativo
</script>

<main>
    <h1>Olá {nome || 'Mundo'}!</h1>
    <p>Contador: {count}</p>
    <button on:click={incrementar}>Clique</button>
    <input bind:value={nome} placeholder="Digite seu nome">
</main>

<style>
    main {
        font-family: sans-serif;
    }
    button {
        background: blue;
        color: white;
    }
</style>
```

### Comparação entre Frameworks:
| Característica | React | Vue | Angular | Svelte |
|---------------|-------|-----|---------|---------|
| **Curva de Aprendizado** | Média | Baixa | Alta | Média |
| **Popularidade** | Altíssima | Alta | Média | Crescendo |
| **Tamanho** | ~40KB | ~30KB | ~65KB | ~10KB |
| **Performance** | Boa | Boa | Boa | Excelente |
| **Ecosistema** | Excelente | Muito Bom | Bom | Em crescimento |
| **JSX** | Sim | Opcional | Não | Não |
| **TypeScript** | Suporte nativo | Suporte | Nativo | Suporte |

---

## 📦 Gerenciadores de Pacotes

### 1. NPM (Node Package Manager)
```bash
# Iniciar projeto
npm init -y

# Instalar pacotes
npm install react react-dom
npm install --save-dev webpack
npm install -g create-react-app

# Remover pacotes
npm uninstall react

# Atualizar pacotes
npm update
npm upgrade

# Executar scripts
npm run start
npm test

# Visualizar pacotes instalados
npm list
npm outdated
```

### 2. Yarn (Alternativa ao NPM)
```bash
# Instalar yarn
npm install -g yarn

# Iniciar projeto
yarn init -y

# Instalar pacotes
yarn add react react-dom
yarn add --dev webpack
yarn global add create-react-app

# Remover pacotes
yarn remove react

# Atualizar
yarn upgrade

# Executar scripts
yarn start
yarn test
```

### 3. Package.json
```json
{
    "name": "meu-projeto",
    "version": "1.0.0",
    "description": "Projeto exemplo",
    "main": "index.js",
    "scripts": {
        "start": "webpack serve --open",
        "build": "webpack --mode production",
        "test": "jest",
        "lint": "eslint src/**/*.js"
    },
    "dependencies": {
        "react": "^18.2.0",
        "react-dom": "^18.2.0"
    },
    "devDependencies": {
        "webpack": "^5.0.0",
        "webpack-cli": "^5.0.0",
        "webpack-dev-server": "^4.0.0",
        "jest": "^29.0.0",
        "eslint": "^8.0.0",
        "@babel/core": "^7.0.0",
        "babel-loader": "^9.0.0"
    }
}
```

---

## 🔧 Ferramentas de Build e Bundlers

### 1. Webpack
```javascript
// webpack.config.js
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: './src/index.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist'),
        clean: true
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react']
                    }
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(png|jpg|svg)$/,
                type: 'asset/resource'
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html'
        })
    ],
    devServer: {
        static: './dist',
        hot: true,
        port: 3000,
        open: true
    },
    devtool: 'source-map',
    mode: 'development'
};
```

### 2. Vite (Moderno e Rápido)
```javascript
// vite.config.js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [react()],
    server: {
        port: 3000,
        open: true
    },
    build: {
        outDir: 'dist',
        sourcemap: true
    }
});
```

### 3. Parcel (Zero Configuração)
```bash
# Instalar
npm install -g parcel

# Usar
parcel src/index.html
parcel build src/index.html
```

### 4. Babel (Transpilador)
```json
// .babelrc
{
    "presets": [
        "@babel/preset-env",
        "@babel/preset-react"
    ],
    "plugins": [
        "@babel/plugin-proposal-class-properties",
        "@babel/plugin-transform-runtime"
    ]
}
```

---

## 🗄️ Controle de Versão (Git)

### Comandos Básicos Git:
```bash
# Configuração
git config --global user.name "Seu Nome"
git config --global user.email "seu@email.com"

# Iniciar repositório
git init

# Clonar repositório
git clone https://github.com/usuario/repositorio.git

# Status
git status
git log --oneline
git diff

# Adicionar arquivos
git add arquivo.js
git add .  # Todos os arquivos

# Commit
git commit -m "Descrição do commit"

# Branches
git branch           # Listar branches
git branch nova-feature  # Criar branch
git checkout nova-feature  # Mudar de branch
git checkout -b nova-feature  # Criar e mudar
git merge nova-feature  # Mesclar branch
git branch -d nova-feature  # Deletar branch

# Remoto
git remote add origin https://github.com/usuario/repositorio.git
git push -u origin main
git pull
git fetch

# Resolver conflitos
# Editar arquivos com conflitos manualmente
git add .
git commit -m "Resolveu conflitos"

# .gitignore
node_modules/
dist/
*.log
.env
.DS_Store
```

### Boas Práticas de Git:
```bash
# Commits semânticos
feat: nova funcionalidade
fix: correção de bug
docs: documentação
style: formatação
refactor: refatoração
test: testes
chore: manutenção

# Exemplo
git commit -m "feat: adiciona autenticação com JWT"
git commit -m "fix: corrige validação do formulário"
git commit -m "docs: atualiza README com instruções"
```

---

## 🧪 Ferramentas de Teste

### 1. Jest (Testes Unitários)
```javascript
// sum.js
function sum(a, b) {
    return a + b;
}
module.exports = sum;

// sum.test.js
const sum = require('./sum');

test('soma 1 + 2 = 3', () => {
    expect(sum(1, 2)).toBe(3);
});

test('soma de números negativos', () => {
    expect(sum(-1, -2)).toBe(-3);
});

// Teste de componentes React
import { render, screen, fireEvent } from '@testing-library/react';
import Contador from './Contador';

test('incrementa contador', () => {
    render(<Contador />);
    const button = screen.getByText('Incrementar');
    const count = screen.getByText('Contador: 0');
    
    fireEvent.click(button);
    expect(count).toHaveTextContent('Contador: 1');
});
```

### 2. Cypress (Testes E2E)
```javascript
// cypress/integration/login.spec.js
describe('Teste de Login', () => {
    it('deve fazer login com sucesso', () => {
        cy.visit('http://localhost:3000/login');
        cy.get('#email').type('usuario@teste.com');
        cy.get('#senha').type('senha123');
        cy.get('#login-btn').click();
        cy.url().should('include', '/dashboard');
        cy.contains('Bem-vindo, Usuário!');
    });
});
```

### 3. ESLint (Linter)
```json
// .eslintrc.json
{
    "env": {
        "browser": true,
        "es2021": true,
        "node": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:react/recommended"
    ],
    "parserOptions": {
        "ecmaVersion": "latest",
        "sourceType": "module"
    },
    "rules": {
        "indent": ["error", 4],
        "quotes": ["error", "single"],
        "semi": ["error", "always"],
        "no-unused-vars": "warn",
        "no-console": "warn"
    }
}
```

### 4. Prettier (Formatador)
```json
// .prettierrc
{
    "semi": true,
    "singleQuote": true,
    "trailingComma": "es5",
    "tabWidth": 4,
    "printWidth": 80,
    "bracketSpacing": true,
    "arrowParens": "always"
}
```

---

## ✨ Boas Práticas

### 1. Performance
```html
<!-- Carregamento assíncrono -->
<script async src="script.js"></script>
<script defer src="script.js"></script>

<!-- Lazy loading de imagens -->
<img loading="lazy" src="imagem.jpg" alt="">

<!-- Minificação -->
<!-- Use build tools para minificar CSS, JS e HTML -->

<!-- Compressão -->
<!-- Use gzip ou brotli no servidor -->
```

### 2. Acessibilidade (A11y)
```html
<!-- ARIA Labels -->
<button aria-label="Fechar menu">×</button>
<input aria-required="true" required>

<!-- Hierarquia correta -->
<h1>Título Principal</h1>
<h2>Subtítulo</h2>
<h3>Sub-subtítulo</h3>

<!-- Alt text -->
<img src="imagem.jpg" alt="Descrição da imagem">

<!-- Contraste -->
<!-- Use ferramentas para verificar contraste -->

<!-- Navegação por teclado -->
<button onkeydown="handleKey(event)">Botão</button>
```

### 3. SEO
```html
<!-- Meta tags -->
<meta name="description" content="Descrição da página">
<meta name="keywords" content="palavras, chave, SEO">
<meta property="og:title" content="Título para redes sociais">
<meta property="og:description" content="Descrição para redes sociais">
<meta property="og:image" content="https://site.com/imagem.jpg">

<!-- Estrutura semântica -->
<header>
    <nav>
        <!-- Links de navegação -->
    </nav>
</header>
<main>
    <article>
        <!-- Conteúdo principal -->
    </article>
</main>
<footer>
    <!-- Rodapé -->
</footer>

<!-- Robots -->
<meta name="robots" content="index, follow">

<!-- Favicon -->
<link rel="icon" type="image/x-icon" href="favicon.ico">

<!-- Schema.org (dados estruturados) -->
<script type="application/ld+json">
{
    "@context": "https://schema.org",
    "@type": "Article",
    "headline": "Título do artigo",
    "author": "Nome do autor"
}
</script>
```

### 4. Segurança
```html
<!-- Content Security Policy -->
<meta http-equiv="Content-Security-Policy" 
      content="default-src 'self'; script-src 'self' https://cdn.jsdelivr.net">

<!-- HTTPS -->
<!-- Sempre use HTTPS em produção -->

<!-- Sanitização -->
<!-- Nunca insira HTML não sanitizado -->

<!-- Proteção XSS -->
<!-- Escape dados do usuário -->
<!-- Use bibliotecas de sanitização -->
```

### 5. Código Limpo
```javascript
// ✅ Nomes descritivos
const nomeDoUsuario = 'João';
const calcularPrecoTotal = (itens) => { ... };

// ❌ Nomes ruins
const n = 'João';
const calc = (i) => { ... };

// ✅ Funções pequenas e focadas
function validarEmail(email) {
    return email.includes('@');
}

function enviarEmail(dados) {
    // Lógica de envio
}

// ❌ Funções gigantes
function processarDados(dados) {
    // 100+ linhas fazendo tudo
}

// ✅ Comentários úteis
// Calcula o desconto baseado no valor total e no tipo de cliente
function calcularDesconto(total, tipoCliente) { ... }

// ❌ Comentários óbvios
// Incrementa i
i++;

// ✅ Constantes
const DIAS_DA_SEMANA = ['seg', 'ter', 'qua', 'qui', 'sex', 'sab', 'dom'];
const URL_BASE = 'https://api.exemplo.com';

// ❌ Magic numbers
if (status === 200) { ... } // O que é 200?
```

---

## 🗺️ Roteiro de Aprendizado

### Fase 1: Fundamentos (1-2 meses)
```
📚 HTML
- Estrutura básica
- Tags semânticas
- Formulários
- Tabelas
- Elementos multimídia

🎨 CSS
- Sintaxe e seletores
- Box Model
- Flexbox
- Grid
- Responsividade
- Animações

💻 JavaScript Básico
- Sintaxe
- Variáveis e tipos
- Funções
- Arrays e objetos
- DOM Manipulation
- Eventos
- JSON
```

### Fase 2: Ferramentas (1 mês)
```
🛠️ DevTools
- Elements
- Console
- Network
- Sources

📦 Git e GitHub
- Comandos básicos
- Branches
- Pull Requests

🔧 VS Code
- Extensões
- Shortcuts
- Configurações
```

### Fase 3: Aprofundamento (2-3 meses)
```
💻 JavaScript Avançado
- ES6+
- Promises e Async/Await
- Fetch API
- Módulos
- Classes

📚 Bibliotecas
- jQuery (básico)
- Lodash
- Axios

🎯 Frameworks (escolha um)
- React (recomendado)
- Vue.js
- Angular

🧪 Testes
- Jest
- Testing Library
- Cypress
```

### Fase 4: Projetos e Especialização (Contínuo)
```
🚀 Projetos
- Landing page
- Todo App
- E-commerce
- Dashboard
- Blog
- API Client

📊 Performance
- Web Vitals
- Lighthouse
- Otimização

🌐 PWA
- Service Workers
- Manifest
- Offline

🔧 Build Tools
- Webpack
- Vite
- NPM/Yarn
```

### Recursos de Estudo:

#### 📚 Livros:
- "JavaScript: O Guia Definitivo"
- "Você não Sabe JS" (série)
- "CSS: O Guia Definitivo"
- "HTML5: A Linguagem de Marcação"

#### 🎥 Cursos Gratuitos:
- **freeCodeCamp**: [freecodecamp.org](https://www.freecodecamp.org)
- **MDN Web Docs**: [developer.mozilla.org](https://developer.mozilla.org)
- **The Odin Project**: [theodinproject.com](https://www.theodinproject.com)
- **Curso em Vídeo** (YouTube)

#### 🎓 Cursos Pagos:
- **Alura** (Brasil)
- **Rocketseat** (Brasil)
- **Udemy** (internacional)
- **Frontend Masters** (inglês)

#### 📱 Comunidades:
- Stack Overflow
- GitHub
- Dev.to
- Reddit (r/javascript, r/webdev)
- Discord/Slack de comunidades

---

## 🎉 Conclusão

A programação web do lado cliente é um campo vasto e emocionante! Comece pelos fundamentos, pratique consistentemente e construa projetos reais.

### Checklist do Desenvolvedor Front-End:
```
☐ HTML5 (semântico, acessível)
☐ CSS3 (Flexbox, Grid, Responsivo)
☐ JavaScript (ES6+, DOM, APIs)
☐ Versionamento (Git)
☐ Frameworks (React/Vue/Angular)
☐ Build Tools (Webpack/Vite)
☐ Testes (Jest, Testing Library)
☐ Performance
☐ Acessibilidade
☐ SEO
☐ Segurança
☐ NPM/Yarn
☐ TypeScript (bônus)
☐ PWA (bônus)
```

### Motivação Final:
> 💬 *"A jornada de mil milhas começa com um único passo."* - Lao Tzu

> 💬 *"O código é como poesia: deve ser bonito, limpo e expressivo."*

---

## 📎 Links Úteis

- **Documentação**: [MDN Web Docs](https://developer.mozilla.org)
- **CSS Tricks**: [css-tricks.com](https://css-tricks.com)
- **JavaScript Info**: [javascript.info](https://javascript.info)
- **Can I Use**: [caniuse.com](https://caniuse.com)
- **Dev.to**: [dev.to](https://dev.to)
- **GitHub**: [github.com](https://github.com)
- **Codepen**: [codepen.io](https://codepen.io)
- **Figma** (design): [figma.com](https://figma.com)
