---

# **Histórico da Gerência de Projetos e da Evolução das Métricas de Software**

A relação entre **gerência de projetos** e **métricas de software** é resultado de décadas de amadurecimento da engenharia de software. As métricas surgiram como resposta à necessidade de **controlar, prever, estimar e melhorar** a qualidade dos produtos e processos de desenvolvimento.

---

# **1. Décadas de 1950–1960: Origem da Gerência de Projetos**

A gerência de projetos moderna surgiu após a Segunda Guerra Mundial, quando governos e indústrias passaram a gerir iniciativas de alta complexidade. Nesse período surgiram métodos fundamentais:

### **PERT (1958)**

Criado pela Marinha dos EUA para o programa de mísseis Polaris.
Trazia foco em:

* estimativa probabilística de prazos,
* identificação de riscos,
* modelagem de atividades.

### **CPM (1957)**

Desenvolvido pela DuPont para gerenciamento industrial.
Permitia:

* identificar caminho crítico,
* otimizar cronogramas,
* reduzir custos operacionais.

Essas técnicas formaram a base científica da gestão de projetos.

➡️ **Ainda não se falava em métricas de software**, pois o software era artesanal, específico e pouco compreendido.

---

# **2. Décadas de 1960–1970: A Crise do Software e o começo das métricas**

Com o rápido crescimento da computação, surgiu a **Crise do Software (1968)**, marcada por:

* projetos atrasados,
* custos imprevisíveis,
* baixa qualidade,
* falta de padronização.

O surgimento do termo **engenharia de software** (NATO, 1968–1969) impulsionou os primeiros esforços para medir e controlar o desenvolvimento.

## **Primeiras métricas informais**

As organizações passaram a coletar dados como:

* quantidade de erros,
* tempo médio de correção,
* quantidade de requisitos implementados.

Mas faltava base teórica sólida.

---

# **3. Década de 1970: Formalização das Métricas de Software**

Neste período surgiu a primeira definição acadêmica de **métricas de software**, considerando:

* produtividade,
* qualidade,
* complexidade estrutural,
* tamanho do software.

As contribuições mais marcantes:

### **3.1. Linhas de Código (LOC)**

Apesar de primitiva, foi a primeira métrica amplamente usada.
Vantagens: fácil de medir.
Limitações: incentiva código desnecessário, depende da linguagem.

### **3.2. Pontos de Função (1979 — Allan Albrecht, IBM)**

Marco revolucionário.
Mede **funções entregues ao usuário** em vez de linhas de código.
Benefícios:

* independência de linguagem,
* permite estimativas desde o início do projeto,
* padrão internacional.

### **3.3. Complexidade Ciclomática (1976 — McCabe)**

Mede o número de caminhos independentes no código.
Contribuições:

* estimativa de esforço de testes,
* detecção de módulos complexos,
* avaliação de risco.

### **3.4. Métrica Halstead (1977 — Maurice Halstead)**

Avalia:

* operadores e operandos,
* volume, esforço e dificuldade do código.

É uma das primeiras tentativas de medir **complexidade cognitiva**.

---

# **4. Décadas de 1980–1990: A Maturidade da Engenharia de Software**

A indústria percebeu que projetos grandes exigiam disciplina, processos e padrões de qualidade.

## **4.1. Modelos de Qualidade e Processos**

* **ISO 9000** (1987) — garantia de qualidade
* **CMM (Capability Maturity Model — 1987)**
  Classificava maturidade de processos em 5 níveis.
* **IEEE 1061 (1998)** — padrão para métricas de software

Esses padrões exigiam **medições formais**, impulsionando o uso de métricas nos processos.

## **4.2. Métricas orientadas a projeto**

* desvio de cronograma (SV — Schedule Variance),
* desvio de custo (CV — Cost Variance),
* valor agregado (EVM — Earned Value Management),
* densidade de defeitos.

## **4.3. Métricas orientadas a código**

* acoplamento,
* coesão,
* tamanho de classes/métodos,
* complexidade estrutural.

As ferramentas CASE (Computer-Aided Software Engineering) permitiram automatizar muitas dessas medições.

---

# **5. Década de 2000: Desenvolvimento Ágil e mudança de paradigma**

Com o **Manifesto Ágil (2001)**, a ênfase migrou para:

* ciclos curtos,
* adaptação rápida,
* entregas contínuas.

As métricas tradicionais foram repensadas.

## **5.1. Novas métricas ágeis**

* **Velocity** (velocidade da equipe por sprint),
* **Burn-down e Burn-up**,
* **Lead Time / Cycle Time**,
* **Throughput** (entregas concluídas).

## **5.2. Valor entregue**

Começou-se a medir:

* satisfação do cliente,
* impacto da funcionalidade,
* prioridade de backlog.

---

# **6. Década de 2010–Presente: DevOps, Nuvem e Métricas Modernas**

Com DevOps e Pipelines de CI/CD, as entregas tornaram-se contínuas e altamente automatizadas.

## **6.1. Métricas DORA (padrão de excelência DevOps)**

1. **Frequência de deploy**
2. **Lead time de mudanças**
3. **MTTR (Mean Time To Recovery)**
4. **Taxa de falhas em produção**

Essas métricas diferenciam equipes de baixa, média e alta performance.

## **6.2. Métricas de Qualidade e Segurança**

Ferramentas como SonarQube, Snyk e OWASP medem:

* bugs críticos,
* vulnerabilidades,
* code smells,
* cobertura de testes.

## **6.3. Métricas de Observabilidade**

Com sistemas em nuvem, surgiram métricas de monitoramento:

* latência,
* throughput,
* consumo de CPU/ram,
* SLIs/SLOs (Service Level Indicators/Objectives).

---

# **7. Tendências Futuras das Métricas de Software**

## **Medições baseadas em IA**

Modelos de IA serão capazes de:

* prever defeitos,
* indicar complexidade antes do código existir,
* sugerir ajustes automáticos em pipelines.

## **Medidas holísticas**

A engenharia moderna busca medir:

* a experiência do usuário,
* ROI e impacto de negócio,
* eficiência energética do software (Green IT).

## **Métricas para equipes híbridas e remotas**

Novos indicadores surgem com equipes globalizadas e distribuídas.

---

# **Conclusão Geral**

A evolução das métricas reflete a evolução da engenharia de software:

| Período           | Destaque                                     |
| ----------------- | -------------------------------------------- |
| **1960–70**       | Primeiros problemas e necessidade de medir   |
| **1970–80**       | Consolidação teórica: LOC, PF, complexidade  |
| **1980–90**       | Padrões, qualidade e maturidade de processos |
| **2000–10**       | Métricas ágeis e foco em valor               |
| **2010–presente** | DevOps, CI/CD, DORA, nuvem e observabilidade |

As métricas deixaram de ser apenas números e tornaram-se ferramentas estratégicas para garantir:

* previsibilidade,
* qualidade,
* segurança,
* entrega contínua,
* valor real ao usuário e ao negócio.

---
