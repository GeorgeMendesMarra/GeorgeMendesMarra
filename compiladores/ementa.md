# Compiladores

**Curso:** Engenharia de Software

---

## Ementa

Organização e estrutura de compiladores, interpretadores e analisadores. Fases de análise e síntese. Análise léxica: expressões regulares e reconhecedores (autômatos finitos). Análise sintática: gramáticas livres de contexto e reconhecedores (*parsers*) top-down e bottom-up; conjuntos *First* e *Follow*; tabelas preditivas e tabelas LR. Análise semântica: atributos semânticos herdados e sintetizados; esquemas S-atribuídos e L-atribuídos. Geração de código intermediário. Otimização de código intermediário. Geração e otimização de código objeto.

---

## Objetivo geral

Compreender os princípios, as técnicas e as ferramentas envolvidas na construção de compiladores e interpretadores, relacionando a teoria de linguagens formais e autômatos com a implementação prática de cada fase da compilação.

## Objetivos específicos

- Distinguir compiladores, interpretadores e tradutores, e identificar as fases de análise e síntese.
- Especificar *tokens* com expressões regulares e implementar analisadores léxicos baseados em autômatos finitos.
- Descrever linguagens de programação por meio de gramáticas livres de contexto e eliminar ambiguidade, recursão à esquerda e fatoração à esquerda.
- Construir analisadores sintáticos top-down (descendente recursivo e preditivo LL(1)) e bottom-up (LR).
- Calcular os conjuntos *First* e *Follow* e construir tabelas de análise preditivas e LR.
- Especificar e implementar análise semântica com atributos herdados e sintetizados, tabela de símbolos e verificação de tipos.
- Gerar código intermediário (código de três endereços) e aplicar técnicas básicas de otimização.
- Compreender a geração e a otimização de código objeto para uma arquitetura-alvo.

## Competências e habilidades

- Aplicar conceitos de linguagens formais e autômatos na resolução de problemas de processamento de linguagens.
- Projetar e implementar as principais fases de um compilador ou interpretador para uma linguagem simples.
- Utilizar geradores de analisadores (por exemplo, Flex/Bison, JavaCC ou ANTLR) e avaliar quando usá-los ou implementar manualmente.
- Analisar criticamente a qualidade do código gerado quanto à correção e ao desempenho.

---

## Conteúdo programático

### Unidade 1 — Introdução à compilação

1. Linguagens de programação, tradutores, compiladores e interpretadores
2. Estrutura de um compilador: fases de análise (*front-end*) e de síntese (*back-end*)
3. Ferramentas de construção de compiladores
4. Revisão: alfabetos, linguagens, gramáticas e hierarquia de Chomsky

### Unidade 2 — Análise léxica

1. Papel do analisador léxico: *tokens*, lexemas e padrões
2. Expressões regulares
3. Autômatos finitos determinísticos (AFD) e não determinísticos (AFN)
4. Conversão de expressão regular em AFN e de AFN em AFD
5. Tratamento de erros léxicos
6. Implementação de um analisador léxico

### Unidade 3 — Análise sintática

1. Gramáticas livres de contexto, árvores de derivação e ambiguidade
2. Eliminação de recursão à esquerda e fatoração à esquerda
3. Análise top-down: descendente recursivo e preditivo LL(1)
4. Conjuntos *First* e *Follow* e construção da tabela preditiva
5. Análise bottom-up: redução por deslocamento (*shift-reduce*)
6. Análise LR: LR(0), SLR, LR(1) e LALR; construção da tabela LR
7. Tratamento e recuperação de erros sintáticos

### Unidade 4 — Análise semântica

1. Tradução dirigida pela sintaxe
2. Atributos semânticos herdados e sintetizados
3. Esquemas S-atribuídos e L-atribuídos
4. Tabela de símbolos e escopo
5. Verificação de tipos

### Unidade 5 — Geração e otimização de código

1. Geração de código intermediário: árvores sintáticas abstratas, notação pós-fixada e código de três endereços
2. Otimização de código intermediário: blocos básicos, grafo de fluxo, eliminação de subexpressões comuns, propagação de constantes e eliminação de código morto
3. Geração de código objeto: seleção de instruções, alocação de registradores e ordenação de instruções
4. Otimização de código objeto (otimizações *peephole*)

---

## Metodologia

- Aulas expositivas e dialogadas com resolução de exercícios.
- Aulas práticas em laboratório com implementação das fases do compilador.
- Projeto integrador: construção de um compilador ou interpretador para uma linguagem simples, desenvolvido de forma incremental ao longo do semestre.
- Uso de ambiente virtual de aprendizagem para materiais e entrega de atividades.

## Avaliação

| Etapa | Instrumentos | Conteúdo sugerido |
|-------|--------------|-------------------|
| N1 | Prova escrita e atividades práticas | Unidades 1, 2 e início da Unidade 3 |
| N2 | Prova escrita e entrega do projeto integrador | Restante da Unidade 3 e Unidades 4 e 5 |

---

## Bibliografia básica

- AHO, Alfred V. et al. **Compiladores: princípios, técnicas e ferramentas**. São Paulo: Pearson. 634 p.
- BOSI, Alfredo. **Compiladores**. Rio de Janeiro: Livraria Canuto, 1995.
- RICARTE, Ivan. **Introdução à compilação**. Rio de Janeiro: Elsevier, 2008. xi, 264 p.

## Bibliografia complementar

- DIVERIO, Tiarajú Asmuz; MENEZES, Paulo Fernando Blauth. **Teoria da computação: máquinas universais e computabilidade**. 3. ed. Porto Alegre: Sagra Luzzatto, 2011. 205 p. (Livros Didáticos, v. 5).
- HOPCROFT, John E.; ULLMAN, Jeffrey D.; MOTWANI, Rajeev. **Introdução à teoria de autômatos, linguagens e computação**. 2. ed. São Paulo: Campus, 2002.
- MENEZES, Paulo Fernando Blauth. **Linguagens formais e autômatos**. 6. ed. Porto Alegre: Bookman, 2011. 256 p. (Livros Didáticos Informática UFRGS, v. 3).
- ROSA, João Luís Garcia. **Linguagens formais e autômatos**. Rio de Janeiro: LTC, 2010.
