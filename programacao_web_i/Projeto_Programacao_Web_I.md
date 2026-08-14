---
title: "Projeto Integrador — Programação Web I"
---

# CENTRO UNIVERSITÁRIO ALVES FARIA — UNIALFA

**Curso:** Engenharia de Software
**Disciplina:** Programação Web I
**Carga Horária:** 60 horas
**Professor:** George Mendes Marra

---

# PROJETO DE DESENVOLVIMENTO WEB

## 1. Apresentação

Este documento define o **Projeto Integrador** da disciplina de Programação Web I, elaborado com base na ementa da disciplina, que contempla: Introdução à Internet e Web, Servidores e ambientes Web, HTML, CSS, JavaScript e jQuery, Bootstrap, Web Design (responsivo e não-responsivo) e bibliotecas/plug-ins/frameworks client-side.

O projeto será desenvolvido **individualmente ou em grupo (definido pelo professor em sala)** e entregue em **duas etapas**, permitindo a aplicação progressiva dos conteúdos estudados ao longo do semestre.

## 2. Objetivo Geral

Desenvolver um **site web completo, responsivo e funcional**, aplicando de forma prática os conceitos de estruturação (HTML), estilização (CSS/Bootstrap) e comportamento (JavaScript/jQuery), simulando um cenário real de desenvolvimento front-end.

## 3. Tema do Projeto

Cada aluno/grupo deverá escolher **um tema de aplicação** dentre as opções abaixo (ou propor outro, mediante aprovação do professor):

- Site institucional para uma empresa fictícia (ex.: escritório, clínica, escola);
- Landing page de um produto ou evento;
- Portfólio pessoal/profissional;
- E-commerce simplificado (catálogo de produtos, sem backend real);
- Blog ou portal de notícias;
- Sistema de cadastro simples com formulários e validação client-side.

## 4. Requisitos Técnicos Gerais

O projeto deverá obrigatoriamente conter:

1. **Estrutura HTML5 semântica** (uso de `header`, `nav`, `main`, `section`, `article`, `footer`, etc.);
2. **Folha de estilos CSS própria**, com uso de seletores, box model, flexbox e/ou grid;
3. **Design responsivo**, adaptado para desktop, tablet e mobile (uso de media queries e/ou Bootstrap);
4. **Framework Bootstrap** aplicado em pelo menos parte do layout (grid system, componentes prontos, utilitários);
5. **JavaScript** para interatividade (validações, eventos, manipulação de DOM);
6. **jQuery** aplicado em pelo menos uma funcionalidade (ex.: efeitos, manipulação de elementos, requisições simples);
7. **Formulário funcional** com validação client-side (HTML5 e/ou JavaScript);
8. **Organização de arquivos** em pastas (`/css`, `/js`, `/img`, `/pages`);
9. **Código comentado e indentado**, seguindo boas práticas de legibilidade;
10. **Hospedagem/execução local** funcional (o projeto deve rodar diretamente no navegador ou em um servidor local simples).

## 5. Estrutura do Projeto (sugestão de organização de pastas)

```
projeto-web/
│
├── index.html
├── /pages
│   ├── sobre.html
│   ├── servicos.html
│   └── contato.html
├── /css
│   └── style.css
├── /js
│   └── script.js
├── /img
│   └── (imagens do projeto)
└── README.md
```

## 6. Etapas de Entrega

O projeto será avaliado em **duas entregas**, correspondentes a fases distintas do desenvolvimento.

---

### 6.1 Primeira Entrega — 02/10/2026

**Foco:** Estrutura, conteúdo e estilização estática do site (HTML + CSS + Bootstrap).

**Itens obrigatórios nesta etapa:**

- Estrutura completa em HTML5 semântico de todas as páginas do site (mínimo de 3 páginas);
- Navegação funcional entre as páginas (menu de navegação);
- Estilização completa com CSS próprio (não apenas Bootstrap puro);
- Aplicação do Bootstrap no layout (grid responsivo, componentes como navbar, cards, botões, etc.);
- Site responsivo, testado em pelo menos 3 resoluções (desktop, tablet, mobile);
- Estrutura de pastas organizada conforme item 5;
- Documento **README.md** com:
  - Nome do(s) aluno(s) e tema escolhido;
  - Breve descrição do projeto;
  - Tecnologias utilizadas até o momento.

**Formato de entrega:** arquivo compactado (.zip) contendo todo o projeto, ou link de repositório (GitHub/GitLab), conforme orientação do professor.

---

### 6.2 Segunda Entrega — 04/12/2026

**Foco:** Interatividade, funcionalidades dinâmicas e finalização do projeto (JavaScript + jQuery + refinamentos).

**Itens obrigatórios nesta etapa:**

- Implementação de **JavaScript** para pelo menos 3 funcionalidades interativas, por exemplo:
  - Validação de formulário (campos obrigatórios, formato de e-mail, etc.);
  - Manipulação dinâmica do DOM (mostrar/ocultar elementos, contadores, carrossel manual, etc.);
  - Eventos de usuário (cliques, hover, scroll, submit);
- Uso de **jQuery** em pelo menos uma funcionalidade adicional (ex.: efeitos visuais, animações, requisição AJAX simulada, plugin jQuery);
- Uso de pelo menos **um plug-in ou biblioteca externa** (ex.: slider/carrossel, máscara de input, ícones, validação de formulário, lightbox, etc.);
- Revisão geral de responsividade e usabilidade (ajustes finais de CSS/Bootstrap);
- Testes de funcionamento em pelo menos dois navegadores diferentes;
- Atualização do **README.md**, incluindo:
  - Lista completa de tecnologias, bibliotecas e plug-ins utilizados;
  - Instruções de como executar o projeto;
  - Prints (ou descrição) das principais telas/funcionalidades.

**Formato de entrega:** arquivo compactado (.zip) contendo o projeto completo e finalizado, ou link de repositório atualizado (GitHub/GitLab).

---

## 7. Critérios de Avaliação

| Critério | Peso |
|---|---|
| Estrutura HTML semântica e organização de código | 15% |
| Estilização CSS e uso correto do Bootstrap | 20% |
| Responsividade (desktop, tablet, mobile) | 15% |
| Funcionalidades em JavaScript | 20% |
| Uso de jQuery e plug-ins/bibliotecas | 15% |
| Organização, documentação (README) e boas práticas | 15% |

## 8. Cronograma Resumo

| Etapa | Conteúdo | Data de Entrega |
|---|---|---|
| 1ª Entrega | HTML + CSS + Bootstrap (estrutura estática e responsiva) | **02/10/2026** |
| 2ª Entrega | JavaScript + jQuery + Plug-ins (interatividade e finalização) | **04/12/2026** |

## 9. Observações Finais

- O uso de conteúdo copiado de terceiros sem autoria/adaptação própria não será aceito;
- Templates prontos podem ser usados apenas como referência visual, mas o código deve ser desenvolvido/adaptado pelo próprio aluno;
- Dúvidas técnicas devem ser encaminhadas ao professor durante os encontros ou horário de atendimento;
- Alterações neste documento poderão ser comunicadas em sala pelo professor responsável.

---

*Centro Universitário Alves Faria — UniAlfa*
*Curso de Engenharia de Software — Disciplina de Programação Web I*
*Professor: George Mendes Marra*
