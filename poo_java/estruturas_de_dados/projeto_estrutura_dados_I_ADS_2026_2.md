# CENTRO UNIVERSITÁRIO ALVES FARIA — UNIALFA

**Curso:** Análise e Desenvolvimento de Sistemas — ADS
**Disciplina:** Estrutura de Dados I
**Professor:** George Mendes Marra

---

# PROJETO DE ESTRUTURA DE DADOS

## 1. Apresentação

Este documento define o **Projeto Integrador** da disciplina de Estrutura de Dados I, elaborado com base na ementa da disciplina: métodos de programação modular; recursividade; ponteiros; alocação dinâmica de memória; uniões; tipos de dados estruturados; e estruturas de dados homogêneas (listas, filas e pilhas).

A linguagem principal do projeto é **Java**. Nos itens da ementa que não se aplicam diretamente a Java — por não existirem como recurso nativo da linguagem —, o projeto deverá ser desenvolvido em **linguagem C**. Isso se aplica especificamente a:

- **Ponteiros** → C
- **Alocação dinâmica de memória** (`malloc`/`free`) → C
- **Uniões** (`union`) → C

Os demais itens da ementa (métodos de programação modular, recursividade, tipos de dados estruturados, listas, filas e pilhas) são desenvolvidos em **Java**.

O projeto será desenvolvido **individualmente ou em grupo (definido pelo professor em sala)** e entregue em **duas etapas**, permitindo a aplicação progressiva dos conteúdos estudados ao longo do semestre.

## 2. Objetivo Geral

Desenvolver um **conjunto de programas funcionais** que apliquem, na prática, os principais conceitos de estrutura de dados estudados na disciplina: desde estruturas básicas (arrays, enumerações, pilhas) até estruturas dinâmicas mais complexas (listas, filas, ponteiros e alocação dinâmica), consolidando tanto a lógica de programação modular quanto a manipulação de memória.

## 3. Tema do Projeto

Cada aluno/grupo deverá escolher **um domínio de aplicação** para contextualizar as estruturas de dados implementadas (ou propor outro, mediante aprovação do professor):

- Sistema de controle de fila de atendimento (banco, clínica, suporte técnico);
- Gerenciador de tarefas com pilha de undo/redo;
- Catálogo de produtos com lista de itens e busca;
- Sistema de cadastro de alunos/turmas com estruturas encadeadas;
- Simulador de estacionamento (pilha de vagas, fila de espera);
- Outro tema, desde que permita aplicar todas as estruturas exigidas na ementa.

O mesmo tema deve, preferencialmente, ser mantido da primeira para a segunda entrega, evoluindo de estruturas simples para estruturas dinâmicas.

## 4. Requisitos Técnicos Gerais

O projeto deverá obrigatoriamente conter, ao final das duas entregas:

1. **Métodos de programação modular** em Java (funções/métodos bem definidos, com responsabilidade única, evitando código duplicado);
2. **Recursividade** em Java (pelo menos um algoritmo resolvido de forma recursiva, ex.: busca, cálculo, percurso em estrutura);
3. **Ponteiros** em C (uso explícito de ponteiros para manipulação de variáveis e estruturas);
4. **Alocação dinâmica de memória** em C (`malloc`, `realloc`, `free`, com liberação correta da memória alocada);
5. **Uniões** em C (`union` aplicada a um cenário que justifique seu uso, ex.: representação de dados de tipos variados);
6. **Tipos de dados estruturados** em Java (classes/records representando entidades do domínio escolhido);
7. **Listas** em Java (implementação própria ou uso justificado de coleções, com inserção, remoção e busca);
8. **Filas** em Java (implementação própria de fila — FIFO — com enfileirar/desenfileirar);
9. **Pilhas** em Java (implementação própria de pilha — LIFO — com empilhar/desempilhar);
10. **Código comentado e indentado**, seguindo boas práticas de legibilidade, com separação clara entre os módulos em Java e os módulos em C.

## 5. Estrutura do Projeto (sugestão de organização de arquivos)

```
projeto-estrutura-dados/
│
├── java/
│   ├── src/
│   │   ├── Main.java
│   │   ├── modelos/
│   │   │   └── (classes de tipos de dados estruturados)
│   │   ├── estruturas/
│   │   │   ├── Pilha.java
│   │   │   ├── Fila.java
│   │   │   └── Lista.java
│   │   └── util/
│   │       └── (métodos modulares e recursivos)
│   └── README.md
│
├── c/
│   ├── src/
│   │   ├── main.c
│   │   ├── ponteiros.c
│   │   ├── alocacao_dinamica.c
│   │   └── unioes.c
│   └── README.md
│
└── README.md
```

## 6. Etapas de Entrega

O projeto será avaliado em **duas entregas**, correspondentes a fases distintas do desenvolvimento.

---

### 6.1 Primeira Entrega (N1) — 05/10/2026

**Foco:** Estruturas básicas em Java — Array, Enum e Pilha.

**Itens obrigatórios nesta etapa:**

- Implementação de operações com **Array** em Java (criação, percurso, busca, ordenação simples);
- Definição de pelo menos um **Enum** representando um conjunto de valores fixos do domínio escolhido (ex.: status, categorias, tipos);
- Implementação de uma **Pilha (Stack)** em Java, própria ou com justificativa de uso da API padrão, com as operações de empilhar, desempilhar e consultar o topo;
- Integração dos três elementos acima em um pequeno programa funcional e executável, com menu simples (console);
- Código organizado em métodos (já aplicando programação modular, mesmo que o tópico seja aprofundado apenas na N2);
- Documento **README.md** com:
  * Nome do(s) aluno(s) e tema escolhido;
  * Breve descrição do projeto;
  * Itens da ementa cobertos até o momento (Array, Enum, Pilha).

**Formato de entrega:** arquivo compactado (.zip) contendo todo o projeto, ou link de repositório (GitHub/GitLab), conforme orientação do professor.

---

### 6.2 Segunda Entrega (N2) — 07/12/2026

**Foco:** Restante da ementa — métodos de programação modular, recursividade, ponteiros, alocação dinâmica de memória, uniões, tipos de dados estruturados, listas e filas.

**Itens obrigatórios nesta etapa:**

- **Métodos de programação modular** (Java): refatoração/expansão do projeto da N1 em métodos coesos e reutilizáveis;
- **Recursividade** (Java): implementação de pelo menos um algoritmo recursivo aplicado ao domínio do projeto;
- **Tipos de dados estruturados** (Java): classes representando as entidades principais do sistema;
- **Lista** (Java): implementação própria de lista (encadeada ou com array dinâmico), com inserção, remoção e busca;
- **Fila** (Java): implementação própria de fila (FIFO) integrada ao restante do sistema;
- **Ponteiros** (C): programa em C demonstrando manipulação de variáveis e estruturas via ponteiros;
- **Alocação dinâmica de memória** (C): programa em C com alocação (`malloc`/`realloc`) e liberação (`free`) corretas de memória, aplicada a uma estrutura de dados (ex.: lista encadeada em C);
- **Uniões** (C): programa em C demonstrando o uso de `union` em um cenário coerente com o domínio escolhido;
- Integração de todos os itens da N1 e da N2 em um projeto único e funcional (módulos Java e módulos C podem ser entregues como partes separadas do mesmo repositório/projeto, desde que documentados);
- Testes básicos de cada estrutura implementada (execução via console demonstrando o funcionamento);
- Atualização do **README.md**, incluindo:
  * Lista completa dos itens da ementa implementados e em qual linguagem;
  * Instruções de como compilar/executar a parte em Java e a parte em C;
  * Descrição de como recursividade, ponteiros, alocação dinâmica e uniões foram aplicados.

**Formato de entrega:** arquivo compactado (.zip) contendo o projeto completo e finalizado, ou link de repositório atualizado (GitHub/GitLab).

---

## 7. Critérios de Avaliação

| Critério                                                          | Peso |
| ------------------------------------------------------------------ | ---- |
| Array, Enum e Pilha (Java) — corretude e integração                | 15%  |
| Programação modular e recursividade (Java)                        | 15%  |
| Tipos de dados estruturados, Lista e Fila (Java)                  | 20%  |
| Ponteiros, alocação dinâmica de memória e uniões (C)               | 25%  |
| Organização, documentação (README) e boas práticas                | 15%  |
| Funcionamento geral e testes demonstrados                          | 10%  |

## 8. Cronograma Resumo

| Etapa           | Conteúdo                                                                 | Data de Entrega |
| ---------------- | --------------------------------------------------------------------------- | --------------- |
| 1ª Entrega (N1)  | Java: Array, Enum, Pilha                                                    | **05/10/2026**  |
| 2ª Entrega (N2)  | Java: métodos modulares, recursividade, tipos estruturados, lista, fila / C: ponteiros, alocação dinâmica, uniões | **07/12/2026**  |

## 9. Observações Finais

- O uso de conteúdo copiado de terceiros sem autoria/adaptação própria não será aceito;
- Implementações prontas de listas/filas/pilhas de bibliotecas de terceiros podem ser usadas apenas como referência de estudo — a entrega deve conter implementação própria das estruturas exigidas;
- Dúvidas técnicas devem ser encaminhadas ao professor durante os encontros ou horário de atendimento;
- Alterações neste documento poderão ser comunicadas em sala pelo professor responsável.

---

*Centro Universitário Alves Faria — UniAlfa*
*Curso de Análise e Desenvolvimento de Sistemas — Disciplina de Estrutura de Dados I*
*Professor: George Mendes Marra*
