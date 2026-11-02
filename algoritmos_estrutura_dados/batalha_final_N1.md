# Roteiro de Trabalho — Algoritmos e Estrutura de Dados I
**Curso:** Engenharia de Software
**Carga horária sugerida:** 16 semanas (1 semestre)
**Pré-requisito:** Lógica de Programação / Algoritmos I

---

## 1. Apresentação da Disciplina

Esta disciplina aprofunda os conceitos de programação estruturada e introduz o pensamento sobre estruturas de dados como ferramenta central da Engenharia de Software. O aluno é livre para escolher a linguagem de programação (C, C++, Java, C#, Python, Go, etc.), desde que ela permita implementar de forma explícita os conceitos de modularização, passagem de parâmetros, alocação de memória e estruturas encadeadas — a linguagem é meio, não fim.

## 2. Objetivos

**Objetivo geral:** capacitar o aluno a projetar, implementar e analisar estruturas de dados clássicas, aplicando boas práticas de modularização e abstração.

**Objetivos específicos:**
- Compreender e aplicar procedimentos e funções na decomposição de problemas.
- Diferenciar e utilizar corretamente passagem de parâmetros por valor e por referência.
- Projetar Tipos Abstratos de Dados (TADs), separando interface de implementação.
- Distinguir alocação estática e dinâmica de memória e escolher a mais adequada a cada problema.
- Implementar e aplicar listas, pilhas e filas (com array e com estrutura encadeada).
- Avaliar a complexidade das operações básicas de cada estrutura.

## 3. Regras Gerais de Entrega (independentes de linguagem)

Para permitir a correção uniforme mesmo com linguagens diferentes, todo trabalho deve conter:
1. **Código-fonte comentado**, organizado em módulos/arquivos separados (não tudo em um único `main`).
2. **README** curto explicando como compilar/executar e quais decisões de projeto foram tomadas.
3. **Testes** (casos de entrada e saída esperada) demonstrando o funcionamento.
4. **Relatório técnico breve** (1–2 páginas) descrevendo o TAD implementado, sua interface pública e a complexidade das operações.

## 4. Cronograma de Módulos

### Módulo 1 — Modularização (Semanas 1–3)
| Semana | Conteúdo | Atividade |
|---|---|---|
| 1 | Procedimentos vs. funções; escopo de variáveis | Exercícios de refatoração: transformar código monolítico em módulos |
| 2 | Passagem de parâmetros por valor e por referência (ponteiros/referências conforme a linguagem) | Laboratório: função que troca valores (swap) e função que altera uma estrutura |
| 3 | Recursividade como técnica de modularização | Implementar fatorial, Fibonacci e busca binária recursiva |

**Entregável 1:** Biblioteca de funções matemáticas/utilitárias modularizada, com testes.

### Módulo 2 — Tipos Abstratos de Dados e Memória (Semanas 4–6)
| Semana | Conteúdo | Atividade |
|---|---|---|
| 4 | Conceito de TAD: interface x implementação, encapsulamento | Especificar (sem implementar) o TAD "Conta Bancária" ou similar |
| 5 | Alocação estática de memória: arrays, structs/registros | Implementar o TAD especificado com armazenamento estático |
| 6 | Alocação dinâmica de memória: alocação/desalocação, ponteiros, vazamentos de memória | Reimplementar o mesmo TAD com armazenamento dinâmico; comparar as duas versões |

**Entregável 2:** TAD implementado em duas versões (estática e dinâmica) + relatório comparativo.

### Módulo 3 — Listas (Semanas 7–9)
| Semana | Conteúdo | Atividade |
|---|---|---|
| 7 | Lista sequencial (array): inserção, remoção, busca | Implementar TAD Lista com array |
| 8 | Lista encadeada simples: nós, ponteiros/referências, percurso | Implementar TAD Lista com encadeamento simples |
| 9 | Lista duplamente encadeada e lista circular | Estender a lista encadeada para dupla ligação |

**Entregável 3:** TAD Lista completo (inserir, remover, buscar, percorrer), com análise de complexidade de cada operação nas duas versões (array x encadeada).

### Módulo 4 — Pilhas (Semanas 10–11)
| Semana | Conteúdo | Atividade |
|---|---|---|
| 10 | Conceito LIFO; pilha com array e com lista encadeada | Implementar TAD Pilha (push, pop, topo, vazia) |
| 11 | Aplicações de pilha | Escolher uma: avaliação de expressões pós-fixadas, verificação de balanceamento de parênteses, ou desfazer/refazer (undo/redo) |

**Entregável 4:** TAD Pilha + aplicação prática funcionando.

### Módulo 5 — Filas (Semanas 12–13)
| Semana | Conteúdo | Atividade |
|---|---|---|
| 12 | Conceito FIFO; fila com array (circular) e com lista encadeada | Implementar TAD Fila (enfileirar, desenfileirar, frente, vazia) |
| 13 | Fila de prioridade (introdução) e aplicações | Simulação de atendimento (banco, impressora, escalonamento de processos) |

**Entregável 5:** TAD Fila + simulação de atendimento.

### Módulo 6 — Integração e Projeto Final (Semanas 14–16)
| Semana | Conteúdo | Atividade |
|---|---|---|
| 14 | Revisão comparativa: quando usar lista, pilha ou fila; custo de cada operação | Estudo de caso em grupo |
| 15–16 | Projeto final | Sistema que combine ao menos duas estruturas estudadas (ex.: navegador com histórico em pilha + fila de downloads; editor de texto com lista encadeada + pilha de undo) |

**Entregável final:** Projeto integrador com documentação completa e apresentação (10–15 min).

## 5. Critérios de Avaliação

| Item | Peso sugerido |
|---|---|
| Entregáveis dos módulos (1 a 5) | 50% |
| Projeto final | 35% |
| Participação/laboratórios | 15% |

**Critérios de correção de código (válidos para qualquer linguagem):**
- Corretude (passa nos testes propostos)
- Modularização adequada (funções coesas, baixo acoplamento)
- Uso correto de passagem de parâmetros
- Gerenciamento de memória correto (sem vazamentos, sem acesso inválido, quando aplicável à linguagem)
- Complexidade das operações condizente com a estrutura escolhida
- Clareza do código e da documentação

## 6. Linguagens Sugeridas e Observações

| Linguagem | Observação para o professor |
|---|---|
| C | Melhor para evidenciar ponteiros e alocação dinâmica explícita (malloc/free) |
| C++ | Permite comparar TAD "na mão" com STL depois |
| Java / C# | Sem ponteiros explícitos; adaptar avaliação de "alocação dinâmica" para new/objetos e referências |
| Python | Sem tipagem/alocação manual; pedir simulação de ponteiros com índices ou classes, e discutir isso no relatório |
| Go / Rust | Aceitáveis para turmas mais avançadas; exigem nivelamento extra |

> Recomenda-se registrar a linguagem escolhida por cada aluno/grupo desde o Módulo 1, para ajustar as expectativas de avaliação (ex.: o que conta como "alocação dinâmica" varia por linguagem).

## 7. Referências Sugeridas

- CORMEN, T. H. et al. *Algoritmos: Teoria e Prática*.
- ZIVIANI, N. *Projeto de Algoritmos com Implementações em C++ e Java*.
- CELES, W.; CERQUEIRA, R.; RANGEL, J. L. *Introdução a Estruturas de Dados*.
- Documentação oficial da linguagem escolhida pelo aluno.
