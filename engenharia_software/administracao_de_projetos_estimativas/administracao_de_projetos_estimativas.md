```markdown
# ADMINISTRAÇÃO DE PROJETOS: ESTIMATIVAS

**Disciplina:** Fundamentos de Engenharia de Software  
**Curso:** Engenharia de Software  
**Versão:** 1.0 - Completa

---

## SUMÁRIO

1. [INTRODUÇÃO ÀS ESTIMATIVAS EM PROJETOS DE SOFTWARE](#1-introdução-às-estimativas-em-projetos-de-software)
2. [CONCEITOS FUNDAMENTAIS](#2-conceitos-fundamentais)
3. [MÉTODOS DE ESTIMATIVA](#3-métodos-de-estimativa)
4. [TÉCNICAS DE ESTIMATIVA DE ESFORÇO E CUSTO](#4-técnicas-de-estimativa-de-esforço-e-custo)
5. [TÉCNICAS DE ESTIMATIVA DE PRAZO](#5-técnicas-de-estimativa-de-prazo)
6. [TÉCNICAS DE ESTIMATIVA DE TAMANHO](#6-técnicas-de-estimativa-de-tamanho)
7. [MODELOS PARAMÉTRICOS DE SOFTWARE](#7-modelos-paramétricos-de-software)
8. [FATORES DE AJUSTE E COMPLEXIDADE](#8-fatores-de-ajuste-e-complexidade)
9. [ESTIMATIVA EM METODOLOGIAS ÁGEIS](#9-estimativa-em-metodologias-ágeis)
10. [RISCOS E INCERTEZAS NAS ESTIMATIVAS](#10-riscos-e-incertezas-nas-estimativas)
11. [FERRAMENTAS DE APOIO À ESTIMATIVA](#11-ferramentas-de-apoio-à-estimativa)
12. [EXERCÍCIOS E ESTUDO DE CASO](#12-exercícios-e-estudo-de-caso)
13. [REFERÊNCIAS BIBLIOGRÁFICAS](#13-referências-bibliográficas)
14. [APÊNDICE - CHECKLIST DE ESTIMATIVA](#14-apêndice-checklist-de-estimativa)

---

## 1. INTRODUÇÃO ÀS ESTIMATIVAS EM PROJETOS DE SOFTWARE

### 1.1 Por que Estimar é Crucial?

Estimar em projetos de software é a arte e a ciência de prever recursos,
esforço e tempo necessários para desenvolver um sistema com um determinado
escopo.

**CONSEQUÊNCIAS DA MÁ ESTIMATIVA**

| Estimativa muito baixa | Estimativa muito alta |
|------------------------|----------------------|
| Qualidade comprometida | Recursos ociosos |
| Horas extras não pagas / burnout da equipe | Em consultoria: cliente paga por algo mais lento |
| Insatisfação do cliente | Perda de competitividade no mercado |
| Atrasos consecutivos | |

### 1.2 Desafios Específicos da Estimativa em Software

| Desafio | Descrição |
|---------|-----------|
| **Imaterialidade** | Software não é físico; não dá para medir visualmente |
| **Complexidade** | Sistemas modernos têm milhares de interações |
| **Novidade Tecnológica** | Equipe pode não conhecer a tecnologia |
| **Mudanças Contínuas** | Requisitos mudam durante o projeto |
| **Dependências Humanas** | Produtividade varia muito por pessoa |

---

## 2. CONCEITOS FUNDAMENTAIS

### 2.1 Glossário de Termos

| Termo | Definição | Unidade |
|-------|-----------|---------|
| **Esforço** | Quantidade de trabalho humano necessário | Pessoa-hora/mês |
| **Prazo** | Tempo calendário para execução | Dias/semanas/meses |
| **Custo** | Valor financeiro total | R$, US$ |
| **Tamanho** | Dimensão do software | SLOC, PF |
| **Produtividade** | Tamanho produzido por unidade de esforço | PF/pessoa-mês |
| **Velocidade (Ágil)** | Escopo entregue por iteração | Story points/sprint |

### 2.2 A Equação Fundamental da Estimativa

```
Tamanho (T) = Esforço (E) × Produtividade (P)
Prazo (D) = Esforço (E) / (Tamanho da Equipe × Fator de Comunicação)
Custo (C) = Esforço (E) × Custo Unitário (salário + overhead)
```

### 2.3 Quando Estimar?

| Fase do Projeto | Tipo de Estimativa | Precisão Esperada |
|-----------------|-------------------|-------------------|
| Concepção (Idea) | Ordem de grandeza (ROM) | -25% a +75% |
| Viabilidade | Estimativa preliminar | -15% a +50% |
| Análise de Requisitos | Estimativa detalhada (budget) | -10% a +25% |
| Design/Implementação | Estimativa definitiva | -5% a +10% |

---

## 3. MÉTODOS DE ESTIMATIVA

### 3.1 Classificação dos Métodos

**MÉTODOS EMPÍRICOS** (baseados em experiência):
- Opinião de especialistas
- Analogia
- Delphi (Wideband)
- Estimativa por componentes

**MÉTODOS ALGORÍTMICOS** (baseados em fórmulas):
- COCOMO
- Pontos de Função
- SLIM
- SEER-SEM

### 3.2 Métodos Comparados

| Método | Precisão | Esforço da Estimativa | Quando Usar |
|--------|----------|----------------------|-------------|
| Opinião de especialistas | Baixa | Muito baixo | Estimativas iniciais |
| Analogia | Média | Baixo | Projetos similares |
| Delphi (Wideband) | Média-Alta | Médio | Consenso em equipe |
| Pontos de Função | Alta | Alto | Projetos de negócio |
| COCOMO II | Alta | Muito alto | Projetos grandes |
| Planning Poker | Média | Baixo | Sprints (Ágil) |

---

## 4. TÉCNICAS DE ESTIMATIVA DE ESFORÇO E CUSTO

### 4.1 Opinião de Especialistas

**Fórmula dos 3 cenários (PERT simplificado):**

```
Estimativa Esperada (E) = (Otimista + 4 × Mais Provável + Pessimista) / 6
```

**Exemplo:**
- Otimista: 30 dias
- Mais Provável: 45 dias
- Pessimista: 70 dias

```
E = (30 + 4×45 + 70) / 6 = (30 + 180 + 70) / 6 = 280 / 6 = 46,7 dias
```

### 4.2 Wideband Delphi

**Processo:**
1. Coordenador apresenta especificação
2. Cada membro estima anonimamente
3. Coordenador tabula e apresenta resultados
4. Discussão sobre discrepâncias
5. Repete até convergência
6. Resultado final por consenso ou média

### 4.3 Estimativa por Analogia

**Fórmula:**
```
Estimativa_Novo = Estimativa_Similar × (Tamanho_Novo / Tamanho_Similar) × Fatores_Ajuste
```

**Exemplo:**
- Projeto anterior: 120 PF - 600 horas
- Projeto novo: 180 PF estimado

```
Esforço_Novo = 600 × (180/120) × 1,0 = 600 × 1,5 = 900 horas
```

---

## 5. TÉCNICAS DE ESTIMATIVA DE PRAZO

### 5.1 Lei de Brooks

> *"Adicionar mais pessoas a um projeto de software atrasado o torna ainda mais atrasado."*  
> — **Fred Brooks** (The Mythical Man-Month)

### 5.2 Canais de Comunicação

```
Canais = n × (n-1) / 2 (onde n = número de pessoas)
```

| Tamanho da Equipe | Canais de Comunicação | Overhead Relativo |
|-------------------|----------------------|-------------------|
| 1 | 0 | 0% |
| 2 | 1 | 5% |
| 3 | 3 | 10% |
| 4 | 6 | 20% |
| 5 | 10 | 35% |
| 6-8 | 15-28 | 50% |
| 9+ | 36+ | 70%+ |

### 5.3 Fórmula de Putnam (Norden-Rayleigh)

```
Tempo (anos) = (Esforço / Produtividade) ^ (1/3)
```

**Exemplo:**
- Esforço = 60 pessoa-meses = 5 pessoa-anos
- Produtividade = 10 PF/pessoa-mês

```
T = (60 / 10) ^ (1/3) = 6 ^ 0,333 = 1,82 anos
```

---

## 6. TÉCNICAS DE ESTIMATIVA DE TAMANHO

### 6.1 SLOC (Source Lines of Code)

**Produtividade por Linguagem (SLOC/pessoa-dia):**

| Linguagem | SLOC/pessoa-dia | Fator de Compressão |
|-----------|----------------|---------------------|
| Assembly | 50-100 | 1× |
| C | 100-150 | 2× |
| C++ | 120-180 | 2,5× |
| Java | 150-200 | 3× |
| C# | 150-200 | 3× |
| Python | 200-300 | 4× |
| Ruby | 200-300 | 4× |
| SQL | 100-200 | 3× |

### 6.2 Pontos de Função (Function Points - IFPUG)

**Componentes:**

| Componente | Descrição |
|------------|-----------|
| **EI** (External Input) | Entrada de dados (tela, arquivo) |
| **EO** (External Output) | Saída de dados (relatório, consulta) |
| **EQ** (External Inquiry) | Consulta que não modifica dados |
| **ILF** (Internal Logical File) | Arquivo/tabela interno |
| **EIF** (External Interface File) | Arquivo de outro sistema |

**Pesos:**

| Componente | Baixo | Médio | Alto |
|------------|-------|-------|------|
| EI | 3 | 4 | 6 |
| EO | 4 | 5 | 7 |
| EQ | 3 | 4 | 6 |
| ILF | 7 | 10 | 15 |
| EIF | 5 | 7 | 10 |

**Exemplo de Cálculo:**

| Componente | Quantidade | Complexidade | Peso Total |
|------------|------------|--------------|------------|
| EI | 15 | Média (4) | 60 |
| EO | 8 | Baixa (4) | 32 |
| EQ | 5 | Baixa (3) | 15 |
| ILF | 4 | Média (10) | 40 |
| EIF | 2 | Baixa (5) | 10 |
| **UFP (soma)** | | | **157** |

### 6.3 Ajuste de Complexidade (VAF)

```
PF = UFP × (0,65 + 0,01 × ΣFatores)
```

Onde ΣFatores varia de 0 a 70 (14 fatores de 0 a 5)

**Exemplo:**
```
UFP = 157
ΣFatores = 35
PF = 157 × (0,65 + 0,35) = 157 × 1,0 = 157 PF
```

---

## 7. MODELOS PARAMÉTRICOS DE SOFTWARE

### 7.1 COCOMO (COnstructive COst MOdel) - Barry Boehm

**COCOMO Básico:**

```
Esforço (pessoa-mês) = a × (KLOC)^b
Prazo (meses) = c × (Esforço)^d
```

**Parâmetros por tipo de projeto:**

| Tipo | a | b | c | d |
|------|---|---|---|---|
| Orgânico | 2,4 | 1,05 | 2,5 | 0,38 |
| Semi-acoplado | 3,0 | 1,12 | 2,5 | 0,35 |
| Embutido | 3,6 | 1,20 | 2,5 | 0,32 |

**Exemplo - Projeto Orgânico de 50 KLOC:**

```
Esforço = 2,4 × (50)^1,05 = 2,4 × 58,5 = 140 pessoa-mês
Prazo = 2,5 × (140)^0,38 = 2,5 × 6,5 = 16 meses
Equipe = 140 / 16 = 9 pessoas
```

### 7.2 COCOMO II

**Fórmula (pós-arquitetura):**
```
PM = A × (Tamanho)^B × Π(EMi)
```

Onde:
- A = 2,94 (constante de calibração)
- B = 1,01 + 0,01 × Σ(SFi)
- EMi = Multiplicadores de esforço (10 fatores)
- SFi = Fatores de escala (5 fatores)

---

## 8. FATORES DE AJUSTE E COMPLEXIDADE

### 8.1 Drivers de Produtividade

| Fator | Impacto |
|-------|---------|
| Experiência da equipe | +50% a +100% |
| Ferramentas de automação | +30% a +50% |
| Ambiente estável de requisitos | +20% a +40% |
| Metodologia ágil (vs cascata) | +10% a +30% |
| Testes automatizados | -10% a +20% |
| Documentação extensa | -20% a -40% |

### 8.2 Fatores de Risco na Estimativa

| Fator | Amplitude de erro |
|-------|-------------------|
| Requisitos vagos | +50% a +200% |
| Tecnologia nova | +30% a +100% |
| Equipe distribuída | +20% a +50% |
| Integração com legado | +15% a +40% |
| Segurança crítica | +30% a +60% |
| Conformidade regulatória | +25% a +50% |
| Dependências externas | +20% a +80% |

### 8.3 Matriz de Complexidade

| Nível | Descrição | Fator Multiplicador | Exemplo |
|-------|-----------|--------------------|---------|
| Muito Baixo | CRUD simples | 0,5× | Formulário de contato |
| Baixo | Telas com validação | 0,8× | Cadastro de cliente |
| Médio | Regras de negócio | 1,0× | Sistema de pedidos |
| Alto | Workflows | 1,5× | Sistema fiscal |
| Muito Alto | Algoritmos complexos | 2,0× - 3,0× | Sistema de trading |

---

## 9. ESTIMATIVA EM METODOLOGIAS ÁGEIS

### 9.1 Conceitos Fundamentais

| Característica | Cascata | Ágil |
|----------------|---------|------|
| Quando estima | No início do projeto | Iterativamente |
| Unidade de medida | Horas, dias, PF | Story points |
| Quem estima | Gerente de projeto | Time inteiro |
| Precisão esperada | ±10% (detalhamento) | ±30% (release) |
| Reestimativa | Rara | A cada sprint |

### 9.2 Story Points

**Escala (Sequência de Fibonacci modificada):**

| Pontos | Significado | Exemplo |
|--------|-------------|---------|
| 1 | Muito simples, minutos | Corrigir texto na tela |
| 2 | Simples, algumas horas | Adicionar campo de e-mail |
| 3 | Médio, meio dia | Validação de CPF |
| 5 | Complexo, 1 dia | Relatório com filtros |
| 8 | Muito complexo, 2 dias | Integração com API externa |
| 13 | Muito grande, precisa quebrar | Módulo inteiro |
| 21+ | Épico | Sistema inteiro |

### 9.3 Planning Poker

**Processo:**
1. Product Owner apresenta a história de usuário
2. Time faz perguntas para esclarecimento
3. Cada membro escolhe uma carta em segredo
4. Todos revelam suas cartas simultaneamente
5. Se houver discrepância: valores extremos explicam e nova rodada
6. Repete até consenso

### 9.4 Velocidade do Time (Team Velocity)

```
Velocidade média = Soma dos pontos entregues / Número de sprints
```

**Exemplo:**

| Sprint | Pontos Planejados | Pontos Entregues |
|--------|-------------------|------------------|
| Sprint 1 | 20 | 18 |
| Sprint 2 | 22 | 21 |
| Sprint 3 | 20 | 20 |
| Sprint 4 | 25 | 19 |

```
Velocidade média = (18 + 21 + 20 + 19) / 4 = 19,5 pontos/sprint

Backlog total = 120 pontos
Sprints necessárias = 120 / 19,5 = 6,15 sprints (aprox. 12 semanas)
```

### 9.5 T-Shirt Sizing

| Tamanho | Story Points Equivalentes | Tempo Estimado |
|---------|--------------------------|----------------|
| XS (Extra Small) | 1-2 | Horas |
| S (Small) | 3-5 | 1-2 dias |
| M (Medium) | 8-13 | 1 semana |
| L (Large) | 20+ | 2-4 semanas |
| XL (Extra Large) | 40+ | Sprint+ |

---

## 10. RISCOS E INCERTEZAS NAS ESTIMATIVAS

### 10.1 Por que Estimativas Falham?

| Causa | Descrição | Como Mitigar |
|-------|-----------|--------------|
| Síndrome do Otimismo | Assumir que tudo dará certo | Adicionar reserva (contingência) |
| Viés de Ancoragem | Ficar preso a um número inicial | Usar diferentes métodos |
| Escopo invisível | Funcionalidades não documentadas | Entrevistas com stakeholders |
| Pressão política | Forçar estimativa baixa | Documentar riscos explicitamente |
| Equipe hipotética | Equipe diferente da idealizada | Conhecer quem vai trabalhar |

### 10.2 Intervalo de Confiança (PERT)

| Nível de Confiança | Intervalo | Uso |
|-------------------|-----------|-----|
| 50% (mediano) | -10% a +10% | Compromisso interno |
| 70% | -20% a +30% | Projetos baixo risco |
| 85% | -30% a +60% | Projetos médio risco |
| 95% | -50% a +100% | Estimativa inicial (ROM) |

**Fórmula PERT (3 pontos):**
```
Estimativa Final (E) = (O + 4M + P) / 6
Desvio Padrão (σ) = (P - O) / 6
Intervalo 95% = E ± 2σ
```

**Exemplo:**
```
O = 60, M = 80, P = 130
E = (60 + 4×80 + 130) / 6 = 510 / 6 = 85 dias
σ = (130 - 60) / 6 = 70 / 6 = 11,7 dias
Intervalo 95% = 85 ± 23,4 = [61,6 dias a 108,4 dias]
```

### 10.3 Adicionando Reserva (Contingência)

| Tipo de Reserva | Percentual | Descrição |
|-----------------|------------|-----------|
| Reserva de contingência | 10% a 20% | Riscos conhecidos |
| Reserva gerencial | 15% a 30% | Riscos desconhecidos |
| Reserva total | 25% a 50% | Soma das duas |

### 10.4 Cone of Uncertainty (Baseado no CHAOS Report)

- Projetos bem-sucedidos: variam ±20% da estimativa original
- Projetos desafiados: variam ±50% a ±100%
- Projetos fracassados: variam >200% (ou nunca entregam)

---

## 11. FERRAMENTAS DE APOIO À ESTIMATIVA

### 11.1 Ferramentas Populares

| Ferramenta | Tipo | Preço | Características |
|------------|------|-------|-----------------|
| COCOMO II | Modelo paramétrico | Gratuito (USC) | Baseado em SLOC |
| SEER for Software | Modelo paramétrico | Comercial | Completíssimo |
| TruePlanning | Modelo paramétrico | Comercial | Aerospace/defesa |
| ProjectEstimator | Pontos de Função | Freemium | IFPUG padrão |
| Jira (Velocity) | Ágil | Pago | Tracking de velocidade |
| PlanITpoker | Planning Poker | Gratuito | Online, distribuído |

### 11.2 Excel: Templates Práticos

| Template | Função | Fórmulas-chave |
|----------|--------|----------------|
| Planilha de UFP | Contagem de PF | `=SOMA() × (0,65 + 0,01×SOMA(Fatores))` |
| Estimativa de projetos | Cálculo esforço/custo | Baseada em produtividade histórica |
| Planning Poker | Consenso remoto | Média ponderada |

---

## 12. EXERCÍCIOS E ESTUDO DE CASO

### 12.1 Exercício 1: Cálculo de Pontos de Função

**Enunciado:**
Uma aplicação de vendas possui:
- 12 telas de entrada de dados (baixa complexidade - peso 3)
- 5 relatórios (média complexidade - peso 5)
- 3 consultas (baixa complexidade - peso 3)
- 8 tabelas internas (média complexidade - peso 10)
- 1 arquivo de integração (baixa complexidade - peso 5)
Fatores de ajuste somam 40.

**Calcule o PF total.**

**Resolução:**
```
UFP = (12×3) + (5×5) + (3×3) + (8×10) + (1×5)
UFP = 36 + 25 + 9 + 80 + 5 = 155

PF = 155 × (0,65 + 0,01×40)
PF = 155 × (0,65 + 0,40)
PF = 155 × 1,05 = 162,75 ≈ 163 PF
```

---

### 12.2 Exercício 2: Estimativa PERT

**Enunciado:**
Um analista estimou: Otimista = 12 dias, Mais Provável = 18 dias,
Pessimista = 32 dias.

**Calcule:** a) E, b) σ, c) Intervalo 95%

**Resolução:**
```
a) E = (12 + 4×18 + 32) / 6 = (12 + 72 + 32) / 6 = 116 / 6 = 19,3 dias

b) σ = (32 - 12) / 6 = 20 / 6 = 3,3 dias

c) 95% = 19,3 ± (2 × 3,3) = 19,3 ± 6,6 = [12,7 dias a 25,9 dias]
```

---

### 12.3 Exercício 3: COCOMO Básico

**Enunciado:**
Projeto semi-acoplado com 80 KLOC.
Dados: a=3,0; b=1,12; c=2,5; d=0,35.

**Calcule esforço, prazo e tamanho da equipe.**

**Resolução:**
```
Esforço = 3,0 × (80)^1,12
80^1,12 = 80 × 80^0,12 = 80 × 1,69 = 135,2
Esforço = 3,0 × 135,2 = 405,6 pessoa-mês

Prazo = 2,5 × (405,6)^0,35
405,6^0,35 = e^(0,35 × ln(405,6)) = e^(0,35 × 6,005) = e^2,102 = 8,18
Prazo = 2,5 × 8,18 = 20,45 meses

Equipe = 405,6 / 20,45 = 19,8 ≈ 20 pessoas
```

---

### 12.4 Exercício 4: Velocidade Ágil

**Enunciado:**
Um time teve as seguintes entregas nos últimos 4 sprints:
Sprint 1: 21 pontos, Sprint 2: 23 pontos, Sprint 3: 22 pontos, Sprint 4: 18 pontos
O backlog total é de 150 pontos.

**Quantos sprints são necessários?**

**Resolução:**
```
Velocidade média = (21 + 23 + 22 + 18) / 4 = 84 / 4 = 21 pontos/sprint
Sprints = 150 / 21 = 7,14 sprints (aprox. 8 sprints)
```

---

### 12.5 Estudo de Caso: Sistema de Gestão Escolar

**Contexto:**
Startup contratada para desenvolver sistema de gestão escolar (matrículas, notas,
frequência, financeiro, relatórios). Prazo desejado: 6 meses.
Orçamento máximo: R$ 500.000.

**Dados:**
- 40 telas de entrada
- 20 relatórios
- 15 tabelas internas
- 2 integrações externas
- Equipe: 1 gerente, 2 analistas, 4 devs, 1 QA (8 pessoas)
- Produtividade média: 10 PF/pessoa-mês

**Perguntas:**
1. Qual o tamanho estimado em PF?
2. Quantos meses seriam necessários?
3. A estimativa cabe no prazo de 6 meses?

**Respostas:**
1. UFP estimado = 350-400 PF. Com fatores de ajuste (complexidade alta):
   PF = 525 (valor médio adotado)

2. Esforço = PF / Produtividade = 525 / 10 = 52,5 pessoa-mês
   Prazo = 52,5 / 8 pessoas = 6,56 meses

3. Não cabe (6,56 > 6,0 meses). Alternativas:
   - Aumentar equipe (cuidado: Lei de Brooks)
   - Reduzir escopo (MVP)
   - Aumentar prazo
   - Aumentar produtividade (automação, horas extras)

---

### 12.6 Exercícios Propostos para o Aluno

1. Estime 3 projetos hipotéticos usando Pontos de Função (dados fornecidos)
2. Simule planning poker em sala com 5 histórias de usuário
3. Calcule a velocidade do time após 6 sprints (dados tabelados)
4. Compare resultados entre Delphi e COCOMO para um mesmo projeto
5. Faça a estimativa completa de um sistema de e-commerce com escopo definido

---

## 13. REFERÊNCIAS BIBLIOGRÁFICAS

### 13.1 Livros Clássicos

- BROOKS, Fred. **The Mythical Man-Month**. 1975/1995.
- BOEHM, Barry. **Software Engineering Economics**. 1981.
- BOEHM, Barry et al. **Software Cost Estimation with COCOMO II**. 2000.
- JONES, Capers. **Estimating Software Costs**. 2007.
- COHN, Mike. **Agile Estimating and Planning**. 2005.
- McCONNELL, Steve. **Software Estimation: Demystifying the Black Art**. 2006.

### 13.2 Normas e Padrões

- IFPUG FPA (Function Point Analysis) - versão 4.3
- ISO/IEC 20926:2009 - IFPUG FPA
- ISO/IEC 24570:2005 - NESMA FPA
- COSMIC-FFP (para software em tempo real)
- PMBOK (Project Management Body of Knowledge) - 7ª ed./8ª ed.

### 13.3 Artigos Científicos Recomendados

- BOEHM, Barry; BASILI, Victor. **Software Defect Reduction Top 10 List**. 2001.
- COHN, Mike. **What is Agile Estimating and Planning**. 2005.
- Standish Group. **The Chaos Report**. 1994-2023.
- KARNER, Gustav. **Estimating with Use Case Points**. 1993.

### 13.4 Ferramentas Online

- COCOMO II Web: http://csse.usc.edu/tools/
- PlanITpoker: https://www.planitpoker.com/
- IFPUG: https://www.ifpug.org/

---

## 14. APÊNDICE - CHECKLIST DE ESTIMATIVA

### CHECKLIST PARA ESTIMATIVA DE SOFTWARE

#### Escopo e Requisitos
- [ ] Escopo mínimo está claramente definido?
- [ ] Requisitos funcionais levantados?
- [ ] Requisitos não-funcionais documentados?
- [ ] Histórico de projetos similares disponível?

#### Pessoas e Riscos
- [ ] Todos os stakeholders entrevistados?
- [ ] Riscos identificados e documentados?
- [ ] Equipe que vai executar é conhecida?
- [ ] Tecnologias são conhecidas ou novas?

#### Métodos e Validação
- [ ] Método(s) de estimativa selecionado(s):
    - [ ] Pontos de Função
    - [ ] COCOMO
    - [ ] Planning Poker
    - [ ] Delphi
    - [ ] Analogia
    - [ ] Outro: ___________

#### Finalização
- [ ] Estimativa validada com outra técnica (cross-check)?
- [ ] Reserva de contingência adicionada? (___ %)
- [ ] Estimativa aprovada pelo sponsor?
- [ ] Plano de reestimativa estabelecido (a cada milestone)?

---

## FIM DO MATERIAL

### RESUMO PARA O ALUNO

Estimativa em software é mais arte do que ciência exata. Use múltiplas técnicas
(nunca só uma), envolva quem realmente vai executar e revise suas previsões com
dados reais. A transparência sobre a incerteza (intervalos de confiança) é tão
importante quanto o número estimado.

Lembre-se da **Lei de Parkinson**: "O trabalho se expande para preencher o tempo
disponível". E da **Lei de Brooks**: "Adicionar mais pessoas a um projeto atrasado
o torna ainda mais atrasado".

---

*Material preparado para a disciplina Fundamentos de Engenharia de Software*  
*Curso Superior de Engenharia de Software*
```
