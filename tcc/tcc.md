# Guia Completo para Elaborar um TCC na Área de Computação

> Guia genérico para estudantes de **Engenharia de Software**, **Engenharia de Computação**, **Ciência da Computação**, **Sistemas de Informação** e **Análise e Desenvolvimento de Sistemas (ADS)**.
>
> Adapte sempre às normas da sua instituição (manual de TCC, regulamento do curso e orientações do seu orientador). Em caso de conflito, **a norma da instituição vence este guia**.

---

## Sumário

1. [O que é um TCC](#1-o-que-é-um-tcc)
2. [O TCC em cada curso](#2-o-tcc-em-cada-curso)
3. [Visão geral do processo](#3-visão-geral-do-processo)
4. [Escolha do tema e do orientador](#4-escolha-do-tema-e-do-orientador)
5. [Problema, objetivos, justificativa e hipótese](#5-problema-objetivos-justificativa-e-hipótese)
6. [Revisão da literatura e referencial teórico](#6-revisão-da-literatura-e-referencial-teórico)
7. [Metodologia](#7-metodologia)
8. [Desenvolvimento do projeto (por perfil de curso)](#8-desenvolvimento-do-projeto-por-perfil-de-curso)
9. [Validação, testes e avaliação dos resultados](#9-validação-testes-e-avaliação-dos-resultados)
10. [Estrutura do texto do TCC](#10-estrutura-do-texto-do-tcc)
11. [Normas ABNT essenciais](#11-normas-abnt-essenciais)
12. [Cronograma sugerido (TCC 1 e TCC 2)](#12-cronograma-sugerido-tcc-1-e-tcc-2)
13. [Gestão do projeto e organização de arquivos](#13-gestão-do-projeto-e-organização-de-arquivos)
14. [Ética, LGPD, plágio e uso de IA](#14-ética-lgpd-plágio-e-uso-de-ia)
15. [Banca e apresentação oral](#15-banca-e-apresentação-oral)
16. [Critérios de avaliação (rubrica)](#16-critérios-de-avaliação-rubrica)
17. [Erros comuns](#17-erros-comuns)
18. [Checklists finais](#18-checklists-finais)
19. [Modelos prontos](#19-modelos-prontos)
20. [Fontes úteis](#20-fontes-úteis)

---

## 1. O que é um TCC

O **Trabalho de Conclusão de Curso (TCC)** é o trabalho final da graduação. Nele, você demonstra que é capaz de:

- identificar um **problema real** ou uma **questão relevante** da área;
- **pesquisar** o que já foi feito (estado da arte);
- **propor e construir** uma solução (software, hardware, modelo, análise, método);
- **avaliar** essa solução com critérios objetivos;
- **comunicar** tudo por escrito (monografia ou artigo) e oralmente (banca).

### Formatos comuns de TCC

| Formato | Descrição | Mais comum em |
|---|---|---|
| **Monografia técnica / projeto** | Desenvolvimento de um produto + texto explicando e avaliando | Eng. de Software, ADS, SI, Eng. de Computação |
| **Monografia de pesquisa** | Investigação com hipótese, experimentos e análise | Ciência da Computação |
| **Artigo científico** | Texto curto (6 a 12 páginas) em formato de periódico/congresso | Todos (quando permitido) |
| **Estudo de caso** | Análise aprofundada de uma organização, processo ou tecnologia | SI, ADS |
| **Revisão sistemática da literatura** | Mapeamento e síntese de trabalhos existentes | Todos (menos comum como único trabalho) |
| **Relatório de estágio / projeto de extensão** | Aplicação prática documentada | Depende da instituição |

> **Regra de ouro:** TCC não é só "um sistema que funciona". É um sistema **justificado, fundamentado, construído com método e avaliado com evidências**.

---

## 2. O TCC em cada curso

Cada curso tem uma ênfase diferente. A tabela resume o que as bancas costumam esperar.

| Curso | Ênfase principal | O que a banca valoriza | Entregas típicas |
|---|---|---|---|
| **Engenharia de Software** | Processo de engenharia, qualidade e manutenibilidade | Requisitos, arquitetura, testes, métricas, processo (ágil/híbrido), documentação, DevOps | Software + documentação + testes + avaliação de qualidade |
| **Engenharia de Computação** | Integração hardware + software, sistemas embarcados, redes, IoT | Prototipagem, esquemáticos, protocolos, desempenho, consumo, testes em bancada | Protótipo funcional + firmware + relatório de testes |
| **Ciência da Computação** | Fundamentos teóricos, algoritmos, IA, dados, rigor científico | Hipótese, método científico, complexidade, comparação com a literatura, reprodutibilidade | Algoritmo/modelo + experimentos + análise estatística |
| **Sistemas de Informação** | Tecnologia a serviço do negócio | Processos, gestão, governança, impacto organizacional, viabilidade, ROI | Sistema/estudo de caso + análise de processos + indicadores |
| **ADS** | Aplicação prática e mercado | Solução funcional, tecnologias atuais, usabilidade, entrega de valor | Aplicação completa + documentação técnica + demonstração |

### Pontos vitais por curso

**Engenharia de Software**
- Mostre o **ciclo de vida** adotado (Scrum, Kanban, XP, RUP, cascata, híbrido) e **por quê**.
- Documente requisitos funcionais e não funcionais, casos de uso/histórias de usuário e critérios de aceitação.
- Apresente **decisões arquiteturais** (ADRs) e trade-offs.
- Inclua **estratégia de testes** (unitário, integração, sistema, aceitação) e **cobertura**.
- Meça qualidade: métricas de código, dívida técnica, desempenho, segurança.
- Mostre **CI/CD**, versionamento e controle de configuração.

**Engenharia de Computação**
- Documente **esquemáticos**, diagramas de blocos, lista de materiais (BOM) e custos.
- Justifique a escolha de **microcontrolador/SoC/sensores/atuadores**.
- Registre **protocolos** (I2C, SPI, UART, MQTT, LoRa, BLE, Wi-Fi etc.) e restrições de tempo real.
- Faça **testes de bancada** com instrumentação e registre medições (consumo, latência, precisão, temperatura).
- Considere **normas e segurança** (EMC, alimentação, proteção elétrica) quando aplicável.

**Ciência da Computação**
- Formule **pergunta de pesquisa** e **hipótese** testável.
- Descreva **datasets, baselines e métricas** (acurácia, F1, tempo, memória, complexidade).
- Garanta **reprodutibilidade**: código, seeds, versões, ambiente e dados disponíveis.
- Use **análise estatística** (média, desvio, intervalos de confiança, testes de significância).
- Compare com **trabalhos relacionados** de forma crítica.

**Sistemas de Informação**
- Mapeie **processos** (BPMN) antes e depois da solução (AS-IS / TO-BE).
- Identifique **stakeholders** e o **valor para o negócio**.
- Apresente **viabilidade** (técnica, econômica, operacional) e indicadores (KPIs, ROI, payback, TCO).
- Relacione com **governança** (COBIT, ITIL), **LGPD** e gestão de projetos (PMBOK).

**ADS**
- Entregue uma **aplicação completa e demonstrável**, com tecnologias atuais.
- Foque em **usabilidade**, **documentação técnica** e **implantação** (deploy).
- Mostre o **problema real** de um cliente, nicho ou organização.

---

## 3. Visão geral do processo

```
Tema → Orientador → Problema → Objetivos → Revisão da literatura
   → Metodologia → Projeto/Desenvolvimento → Validação
   → Escrita final → Banca → Versão final e depósito
```

| Fase | Resultado esperado |
|---|---|
| 1. Ideação | Lista de temas, escolha do tema |
| 2. Planejamento | Pré-projeto/proposta aprovado |
| 3. Fundamentação | Referencial teórico e trabalhos relacionados |
| 4. Projeto | Requisitos, arquitetura/modelo, plano de experimentos |
| 5. Desenvolvimento | Produto/protótipo/algoritmo funcional |
| 6. Avaliação | Testes, experimentos, validação com usuários |
| 7. Escrita | Monografia completa e revisada |
| 8. Defesa | Apresentação e arguição |
| 9. Entrega final | Versão corrigida, ficha catalográfica, repositório |

---

## 4. Escolha do tema e do orientador

### 4.1 Como escolher um bom tema

Um bom tema une **quatro pontos**:

1. **Interesse real** seu (você vai conviver com ele por meses).
2. **Relevância** (problema real, mercado, ciência ou sociedade).
3. **Viabilidade** (prazo, custo, dados, acesso a equipamentos, conhecimento técnico).
4. **Aderência ao curso** (a banca precisa reconhecer o trabalho como da sua área).

**Teste rápido:**

- [ ] Consigo explicar o problema em **duas frases**?
- [ ] Existe **alguém que se beneficiaria** da solução?
- [ ] Consigo **terminar em um semestre** (ou dois, se TCC 1 + TCC 2)?
- [ ] Tenho **acesso** aos dados, ferramentas e equipamentos?
- [ ] Consigo **medir** se deu certo?

### 4.2 Fontes de ideias

- Problemas do seu **estágio/trabalho**.
- **Projetos de extensão** e iniciação científica.
- **ODS da ONU** (saúde, educação, agricultura, cidades inteligentes, energia).
- **Trabalhos futuros** citados em artigos e TCCs anteriores.
- Dores de pequenos negócios, ONGs, escolas e órgãos públicos locais.
- Tendências: IA, dados, cloud, IoT, segurança, acessibilidade, automação.

### 4.3 Como escolher e conduzir o orientador

- Escolha alguém que domine a **área do tema** e tenha **disponibilidade**.
- Combine **frequência de reuniões** (ex.: quinzenal) e **canal de comunicação**.
- Leve **material pronto** para cada reunião: o que fez, dúvidas e próximos passos.
- Registre as decisões em **atas curtas** (data, decisões, tarefas).
- Respeite **prazos de revisão** (envie o texto com antecedência).

### 4.4 Temas a evitar

- Amplos demais ("Inteligência Artificial na sociedade").
- Sem problema claro ("Sistema de gestão genérico").
- Cópias de tutoriais prontos (CRUD sem diferencial).
- Dependentes de dados ou parceiros que talvez nunca cheguem.
- Que exigem conhecimento que você não conseguirá adquirir no prazo.

---

## 5. Problema, objetivos, justificativa e hipótese

### 5.1 Problema de pesquisa

Descreva **quem sofre**, **qual é a dor**, **em que contexto** e **qual a consequência**.

> **Fórmula prática:**
> "Em [contexto], [público] enfrenta [problema], o que causa [consequência]. As soluções atuais [limitações]. Como [proposta] pode [melhorar o quê]?"

### 5.2 Objetivo geral

Uma frase, começando com **verbo no infinitivo**, ligada ao problema.

> Exemplo: *Desenvolver e avaliar um sistema web para [função] que reduza [indicador] em [contexto].*

### 5.3 Objetivos específicos

Etapas **mensuráveis** que levam ao objetivo geral (3 a 6 itens).

Verbos úteis: **levantar, mapear, modelar, projetar, implementar, integrar, testar, validar, comparar, medir, documentar**.

Evite verbos vagos como *conhecer, entender, estudar*.

### 5.4 Justificativa

Responda: **por que importa?** Use dados, estatísticas, citações e a lacuna na literatura. Inclua as contribuições: **acadêmica, técnica, social e econômica**.

### 5.5 Hipótese (quando aplicável)

Afirmação testável. Comum em Ciência da Computação e em trabalhos experimentais.

> Exemplo: *O algoritmo A reduz o tempo médio de resposta em pelo menos 20% em relação ao algoritmo B, com dados de tamanho N.*

### 5.6 Escopo e limitações

Deixe claro **o que está dentro** e **o que está fora** do trabalho. Isso protege você na banca.

### 5.7 Perguntas de pesquisa (opcional, mas útil)

- **PQ1:** Quais soluções existentes tratam o problema?
- **PQ2:** Quais requisitos são essenciais?
- **PQ3:** A solução proposta atinge o desempenho/usabilidade esperados?

---

## 6. Revisão da literatura e referencial teórico

### 6.1 Onde pesquisar

| Fonte | Para quê |
|---|---|
| **Google Scholar** | Busca geral de artigos |
| **Portal de Periódicos CAPES** | Acesso a bases pagas (login institucional) |
| **IEEE Xplore** | Engenharia, computação, eletrônica |
| **ACM Digital Library** | Computação e engenharia de software |
| **SOL/SBC OpenLib** | Artigos de eventos brasileiros (SBES, SBSI, SBRC, SBBD etc.) |
| **SciELO** | Periódicos acadêmicos latino-americanos |
| **Springer, ScienceDirect, arXiv** | Artigos internacionais e preprints |
| **BDTD / repositórios institucionais** | Teses, dissertações e TCCs |

### 6.2 Como buscar com método

1. Defina **palavras-chave** em português e inglês.
2. Monte **strings de busca** com operadores booleanos:
   `("machine learning" OR "deep learning") AND ("crop yield" OR "agriculture") AND prediction`
3. Defina **critérios de inclusão e exclusão** (ano, idioma, tipo, relevância).
4. Registre **quantos resultados** achou e **quantos selecionou**.
5. Leia título → resumo → introdução/conclusão → texto completo.
6. Organize em uma **planilha** (autor, ano, problema, método, resultado, limitação).

### 6.3 Trabalhos relacionados

Compare **de forma crítica** 5 a 10 trabalhos. Uma tabela ajuda:

| Trabalho | Problema | Técnica | Resultados | Limitações | Diferença para o seu |
|---|---|---|---|---|---|
| Autor (ano) | ... | ... | ... | ... | ... |

> Mostre **a lacuna**: o que ainda não foi resolvido e **como o seu trabalho contribui**.

### 6.4 Referencial teórico

Explique os **conceitos necessários** para entender o trabalho, **do geral ao específico**. Não faça "enciclopédia": inclua só o que sustenta o seu projeto.

### 6.5 Dicas

- Priorize **artigos dos últimos 5 a 10 anos** (exceto obras clássicas).
- Use **fontes primárias**; evite depender de blogs e Wikipedia como base.
- Use um **gerenciador de referências** (Zotero, Mendeley, JabRef).
- Cada afirmação importante precisa de **citação**.

---

## 7. Metodologia

A metodologia explica **como** você fez o trabalho, de modo que outra pessoa consiga repetir.

### 7.1 Classificação da pesquisa

| Critério | Opções |
|---|---|
| **Natureza** | Básica, aplicada |
| **Abordagem** | Quantitativa, qualitativa, mista |
| **Objetivos** | Exploratória, descritiva, explicativa |
| **Procedimentos** | Pesquisa bibliográfica, estudo de caso, pesquisa-ação, experimento, pesquisa de campo, *Design Science Research* (DSR) |

### 7.2 Métodos muito usados em computação

- **Design Science Research (DSR):** construir e avaliar um **artefato** (software, modelo, método) para resolver um problema. Ótimo para TCCs de desenvolvimento.
  *Etapas:* identificar problema → definir objetivos → projetar/desenvolver → demonstrar → avaliar → comunicar.
- **Estudo de caso:** análise profunda de um contexto real.
- **Experimento controlado:** variáveis, grupos, métricas e análise estatística.
- **Pesquisa-ação:** intervenção na organização com ciclos de melhoria.
- **Revisão sistemática / mapeamento sistemático** (protocolo de Kitchenham).
- **Survey/questionário:** coleta de percepção de usuários ou profissionais.
- **Prototipação e validação com usuários** (testes de usabilidade).

### 7.3 O que descrever na metodologia

- Tipo de pesquisa e justificativa.
- **Etapas** do trabalho (de preferência em um diagrama).
- **Ferramentas, linguagens, frameworks, hardware e versões**.
- **Fontes e coleta de dados** (origem, volume, tratamento, anonimização).
- **Participantes** (perfil, quantidade, critério de seleção, termo de consentimento).
- **Métricas e critérios de avaliação**.
- **Ameaças à validade** (limitações do método, vieses, amostra pequena).

---

## 8. Desenvolvimento do projeto (por perfil de curso)

### 8.1 Núcleo comum a todos os cursos

1. **Contexto e domínio:** entenda o ambiente do problema.
2. **Levantamento de requisitos:** entrevistas, questionários, observação, análise de documentos.
3. **Priorização:** MoSCoW (Must/Should/Could/Won't) ou matriz valor × esforço.
4. **Projeto da solução:** modelos, arquitetura, protótipos de tela.
5. **Implementação incremental:** entregas pequenas e frequentes.
6. **Testes contínuos.**
7. **Documentação viva.**
8. **Avaliação com critérios definidos antes.**

### 8.2 Perfil Engenharia de Software / ADS (foco em produto de software)

**Requisitos**
- Funcionais (RF) e não funcionais (RNF): desempenho, segurança, usabilidade, disponibilidade, portabilidade.
- Histórias de usuário com **critérios de aceitação** (Dado/Quando/Então).
- Regras de negócio numeradas (RN01, RN02...).

**Modelagem (UML e afins)**
- Diagrama de **casos de uso**.
- Diagrama de **classes** (domínio).
- Diagrama de **sequência** (fluxos principais).
- Diagrama de **atividades** (processos).
- Diagrama de **componentes/implantação**.
- **Modelo Entidade-Relacionamento (MER)** e modelo físico do banco.
- **Modelo C4** (contexto, containers, componentes) para arquitetura.

**Arquitetura**
- Escolha e justifique: monólito modular, MVC, camadas, hexagonal, microsserviços, serverless, event-driven.
- Registre **decisões arquiteturais (ADR)**: contexto, decisão, alternativas, consequências.
- Defina **stack**: linguagem, framework, banco, mensageria, cache, front-end, infraestrutura.

**Boas práticas de código**
- Princípios **SOLID**, **Clean Code**, **DRY**, **KISS**.
- Padrões de projeto aplicados com propósito (não por enfeite).
- Padronização (linters, formatadores), revisão de código e *Conventional Commits*.

**Qualidade e testes**
- Pirâmide de testes: **unitários > integração > ponta a ponta**.
- Ferramentas por ecossistema: JUnit/Mockito (Java), pytest (Python), Jest/Vitest (JS), xUnit (C#), Cypress/Playwright (E2E), Postman/Insomnia (API).
- **Cobertura** (ex.: JaCoCo, Coverage.py, Istanbul) e análise estática (SonarQube).
- Testes de **desempenho** (JMeter, k6) e **segurança** (OWASP Top 10, ZAP).
- **Teste de usabilidade** com usuários reais e escala **SUS**.

**DevOps e entrega**
- **Git** com estratégia de branches (GitFlow ou trunk-based).
- **CI/CD** (GitHub Actions, GitLab CI).
- **Docker** e, se necessário, orquestração.
- Deploy em nuvem (AWS, Azure, GCP) ou VPS; variáveis de ambiente e segredos protegidos.
- **Observabilidade:** logs, métricas, alertas.

**Documentação mínima do software**
- README completo (objetivo, instalação, uso, testes, licença).
- Documentação de API (OpenAPI/Swagger).
- Manual do usuário e guia de implantação.

### 8.3 Perfil Engenharia de Computação (hardware + software + sistemas)

**Etapas**
1. Especificação de requisitos de hardware e de software.
2. **Diagrama de blocos** do sistema.
3. Seleção de componentes (MCU/SoC, sensores, atuadores, comunicação, alimentação) com **tabela comparativa**.
4. **Esquemático** (KiCad, Eagle, Altium, Fritzing) e, se possível, **PCB**.
5. **Simulação** (Proteus, LTspice, Tinkercad, Wokwi, MATLAB/Simulink).
6. **Firmware** (C/C++, MicroPython, RTOS como FreeRTOS/Zephyr).
7. Comunicação e **protocolos** (UART, I2C, SPI, CAN, Modbus, MQTT, HTTP, LoRaWAN, BLE).
8. **Integração com nuvem/app** (dashboard, banco de séries temporais).
9. **Testes em bancada** e calibração.
10. **Análise de custo, consumo e confiabilidade.**

**Documente**
- Lista de materiais (**BOM**) com preços e fornecedores.
- Esquemáticos, fotos e vídeos do protótipo.
- Fluxograma do firmware e máquina de estados.
- Tabelas de medições e gráficos (erro, latência, consumo, alcance).
- Considerações de **segurança elétrica**, **EMC** e **normas** aplicáveis.

**Áreas típicas:** IoT, automação residencial/industrial, robótica, agricultura de precisão, energia solar, redes, sistemas embarcados, processamento de sinais, visão computacional embarcada.

### 8.4 Perfil Ciência da Computação (foco em pesquisa e fundamentos)

**Etapas**
1. **Pergunta de pesquisa e hipótese.**
2. **Revisão sistemática** e identificação da lacuna.
3. Definição de **baselines** (soluções com as quais você vai comparar).
4. Escolha de **datasets** (públicos e reconhecidos, como UCI, Kaggle, ImageNet, OpenML, quando aplicável).
5. **Projeto experimental:** variáveis, parâmetros, divisão treino/validação/teste, validação cruzada.
6. Implementação do **algoritmo/modelo**.
7. **Análise de complexidade** (tempo e espaço) quando for algoritmo.
8. **Experimentos repetidos** (várias execuções com sementes diferentes).
9. **Análise estatística** e discussão crítica.
10. **Publicação do código e dos dados** (reprodutibilidade).

**Métricas comuns**
- Classificação: acurácia, precisão, revocação (recall), F1, AUC-ROC, matriz de confusão.
- Regressão: MAE, MSE, RMSE, R².
- Algoritmos: tempo, memória, escalabilidade, taxa de convergência.
- Recuperação de informação: MAP, nDCG.
- Redes e sistemas distribuídos: vazão, latência, perda de pacotes, escalabilidade.

**Cuidados**
- Evite **vazamento de dados** (*data leakage*) entre treino e teste.
- Verifique **desbalanceamento** de classes e **vieses**.
- Faça **estudos de ablação** quando houver múltiplos componentes.
- Reporte **ameaças à validade** com honestidade.

**Áreas típicas:** IA/ML, PLN, visão computacional, bioinformática, otimização, teoria da computação, segurança, criptografia, sistemas distribuídos, computação gráfica, bancos de dados, computação quântica.

### 8.5 Perfil Sistemas de Informação (foco em organização e negócio)

**Etapas**
1. **Diagnóstico organizacional:** missão, processos, pessoas, tecnologias.
2. **Mapeamento AS-IS** dos processos (BPMN, fluxogramas).
3. Identificação de **gargalos** e oportunidades.
4. Proposta **TO-BE** e solução de TI associada.
5. **Análise de viabilidade:** técnica, econômica, operacional e legal.
6. **Plano de implantação:** cronograma, riscos, treinamento, gestão de mudança.
7. **Indicadores:** KPIs antes e depois (tempo de processo, erros, custo, satisfação).
8. Avaliação com **stakeholders**.

**Instrumentos úteis**
- **Canvas** (Business Model Canvas, Project Model Canvas).
- **Análise SWOT**, **5W2H**, **Ishikawa**, **GUT**, **BSC**.
- **Gestão de projetos:** PMBOK, Scrum, Kanban.
- **Governança e serviços:** COBIT, ITIL, ISO 27001 (segurança), ISO 20000.
- **Dados e BI:** modelagem dimensional, ETL, dashboards (Power BI, Metabase, Grafana).
- **ERP/CRM** e integração de sistemas.

**Financeiro**
- **TCO** (custo total de propriedade), **ROI**, **payback**, **VPL/TIR** (quando fizer sentido).

**Áreas típicas:** gestão de TI, ERP, BI e analytics, transformação digital, e-commerce, governo digital, saúde digital, segurança da informação, sistemas de apoio à decisão.

### 8.6 Tabela-resumo de artefatos por curso

| Artefato | ES | EC | CC | SI | ADS |
|---|:-:|:-:|:-:|:-:|:-:|
| Requisitos (RF/RNF) | ✔ | ✔ | ○ | ✔ | ✔ |
| Modelagem UML | ✔ | ○ | ○ | ✔ | ✔ |
| Arquitetura de software | ✔ | ✔ | ○ | ✔ | ✔ |
| Testes automatizados | ✔ | ✔ | ✔ | ○ | ✔ |
| Esquemático/protótipo físico | – | ✔ | – | – | – |
| Experimentos e estatística | ○ | ✔ | ✔ | ○ | ○ |
| Análise de complexidade | ○ | ○ | ✔ | – | – |
| Mapeamento de processos (BPMN) | ○ | – | – | ✔ | ○ |
| Viabilidade econômica (ROI/TCO) | ○ | ○ | – | ✔ | ○ |
| Teste de usabilidade (SUS) | ✔ | ○ | ○ | ✔ | ✔ |
| Reprodutibilidade (código + dados) | ✔ | ✔ | ✔ | ○ | ✔ |

✔ = essencial  ○ = recomendável  – = geralmente dispensável

---

## 9. Validação, testes e avaliação dos resultados

**Você precisa provar que o trabalho funciona e que resolve o problema.**

### 9.1 Defina critérios antes de testar

Para cada objetivo específico, defina **como será medido**:

| Objetivo | Métrica | Meta | Como medir |
|---|---|---|---|
| Reduzir tempo de cadastro | Tempo médio (s) | -30% | Cronometragem antes/depois |
| Garantir qualidade | Cobertura de testes | ≥ 80% | JaCoCo/pytest-cov |
| Boa usabilidade | Escala SUS | ≥ 68 | Questionário com 10+ usuários |
| Precisão do modelo | F1-score | ≥ 0,85 | Conjunto de teste |

### 9.2 Tipos de validação

- **Verificação técnica:** testes automatizados, desempenho, segurança.
- **Validação com usuários:** testes de usabilidade, questionários (SUS, NPS, TAM), entrevistas.
- **Validação com especialistas:** avaliação de professores, profissionais ou do cliente real.
- **Comparação com baselines/trabalhos relacionados.**
- **Estudo piloto** em ambiente real (mesmo que curto).

### 9.3 Apresentação dos resultados

- Use **tabelas e gráficos** legíveis, com legenda e fonte.
- Mostre **antes × depois**, **proposto × baseline**.
- Relate **resultados negativos** e limitações: faz parte da ciência.
- Na **discussão**, interprete: *o que os números significam? Os objetivos foram atingidos? Por quê?*

### 9.4 Ameaças à validade

- **Interna:** o resultado se deve realmente à sua solução?
- **Externa:** os resultados valem fora do seu contexto?
- **De construto:** as métricas medem o que deveriam medir?
- **De conclusão:** a amostra e a análise estatística sustentam a conclusão?

---

## 10. Estrutura do texto do TCC

### 10.1 Elementos pré-textuais

1. Capa
2. Folha de rosto (e ficha catalográfica, se exigida)
3. Folha de aprovação
4. Dedicatória (opcional)
5. Agradecimentos (opcional)
6. Epígrafe (opcional)
7. **Resumo** em português (150 a 500 palavras) + palavras-chave
8. **Abstract** em inglês + keywords
9. Listas (ilustrações, tabelas, abreviaturas e siglas)
10. **Sumário**

### 10.2 Elementos textuais

**1. Introdução** (≈ 5 a 10% do texto)
- Contexto, problema, justificativa, objetivos, metodologia resumida, estrutura do trabalho.

**2. Fundamentação teórica** (≈ 15 a 25%)
- Conceitos, tecnologias, normas.

**3. Trabalhos relacionados** (≈ 5 a 10%)
- Comparação crítica e lacuna.

**4. Metodologia** (≈ 5 a 10%)
- Tipo de pesquisa, etapas, ferramentas, coleta, métricas.

**5. Desenvolvimento / Proposta** (≈ 25 a 35%)
- Requisitos, modelagem, arquitetura/protótipo, implementação, decisões técnicas.

**6. Resultados e discussão** (≈ 15 a 25%)
- Testes, experimentos, validação, análise, limitações.

**7. Considerações finais / Conclusão** (≈ 3 a 7%)
- Retome objetivos e diga se foram atingidos; contribuições; limitações; **trabalhos futuros**.

### 10.3 Elementos pós-textuais

- **Referências** (obrigatório)
- **Apêndices** (material produzido por você: questionários, roteiros, código extenso, diagramas completos)
- **Anexos** (material de terceiros: termos, normas, documentos)

### 10.4 Dicas de escrita

- Um parágrafo = uma ideia. Frases curtas e diretas.
- Use **voz impessoal** ("foi desenvolvido", "optou-se"), conforme a norma da instituição.
- Mantenha **consistência de termos** (não alterne "usuário", "cliente" e "operador" sem motivo).
- **Toda figura/tabela** deve ser citada e comentada no texto.
- Releia em voz alta; peça revisão a um colega e use corretor ortográfico.
- Escreva **ao longo do projeto**, não só no final.

---

## 11. Normas ABNT essenciais

> Confirme sempre o manual da sua instituição. Abaixo, o padrão mais comum.

| Item | Norma | Regra geral |
|---|---|---|
| Trabalhos acadêmicos | **ABNT NBR 14724** | Estrutura e apresentação |
| Citações | **ABNT NBR 10520** | Citação direta, indireta, citação de citação |
| Referências | **ABNT NBR 6023** | Formato das referências |
| Sumário | **ABNT NBR 6027** | Numeração e títulos |
| Resumo | **ABNT NBR 6028** | Resumo informativo |
| Numeração progressiva | **ABNT NBR 6024** | 1, 1.1, 1.1.1 |
| Apresentação de tabelas | **IBGE** | Tabelas sem linhas laterais |

**Formatação típica**
- Papel **A4**; margens: **superior e esquerda 3 cm; inferior e direita 2 cm**.
- Fonte **Times New Roman ou Arial**, tamanho **12** (citações longas, notas e legendas: **10**).
- Espaçamento **1,5** no texto; **simples** em citações longas, notas, referências e legendas.
- Numeração de páginas no canto superior direito, a partir da introdução.
- Citações **diretas com mais de 3 linhas**: recuo de 4 cm, fonte 10, sem aspas.

**Exemplos de citação**
- Indireta: *Segundo Sommerville (2019), a engenharia de software envolve...*
- Direta curta: *"Texto citado" (SOMMERVILLE, 2019, p. 45).*

**Exemplo de referência (livro)**
```
SOMMERVILLE, Ian. Engenharia de software. 10. ed. São Paulo: Pearson, 2019.
```

**Exemplo de referência (artigo)**
```
SOBRENOME, Nome. Título do artigo. Nome do periódico, local, v. 10, n. 2, p. 15-30, 2023.
```

**Exemplo de referência (site/repositório)**
```
AUTOR. Título do repositório. Local: GitHub, ano. Disponível em: <URL>. Acesso em: 2 nov. 2026.
```

> **Ferramentas:** modelos **abnTeX2** (LaTeX), Overleaf, Zotero + estilo ABNT, templates do Word/LibreOffice fornecidos pela instituição.

---

## 12. Cronograma sugerido (TCC 1 e TCC 2)

### TCC 1 (projeto/proposta), ≈ 4 a 5 meses

| Semanas | Atividade | Entregável |
|---|---|---|
| 1-2 | Escolha do tema e orientador | Tema e termo de compromisso |
| 3-4 | Problema, objetivos e justificativa | Rascunho da introdução |
| 5-8 | Revisão da literatura e trabalhos relacionados | Referencial teórico + tabela comparativa |
| 9-10 | Metodologia e plano de trabalho | Metodologia e cronograma |
| 11-13 | Levantamento de requisitos, modelagem/projeto, protótipo inicial | Requisitos + modelos + protótipo |
| 14-15 | Revisão geral e ajustes | Texto do TCC 1 |
| 16 | Entrega e banca do TCC 1 | Documento + apresentação |

### TCC 2 (execução e conclusão), ≈ 4 a 5 meses

| Semanas | Atividade | Entregável |
|---|---|---|
| 1-2 | Ajustes pós-banca e plano de execução | Plano atualizado |
| 3-9 | Desenvolvimento/implementação | Produto/protótipo/algoritmo funcional |
| 10-12 | Testes, experimentos e validação | Dados e análise |
| 13-14 | Escrita de resultados, discussão e conclusão | Texto completo |
| 15 | Revisão final (orientador + ABNT) | Versão pré-defesa |
| 16 | Defesa | Apresentação e arguição |
| 17-18 | Correções e entrega final | Versão final + repositório |

> **Reserve 15 a 20% do tempo como folga.** Atrasos são comuns em integração, aquisição de componentes, autorizações e coleta de dados.

---

## 13. Gestão do projeto e organização de arquivos

### 13.1 Gestão do trabalho

- Use um quadro **Kanban** (Trello, Notion, GitHub Projects) com colunas: *Backlog → Em andamento → Revisão → Concluído*.
- Trabalhe em **sprints curtas** (1 a 2 semanas) com metas claras.
- Mantenha um **diário de bordo** (o que fez, decisões, problemas, links).
- Faça **backups**: Git, Drive/OneDrive e cópia local.
- Gerencie **riscos** (tabela: risco, probabilidade, impacto, mitigação).

### 13.2 Estrutura de repositório recomendada

```
tcc-nome-do-projeto/
├── README.md                 # visão geral, como rodar, licença, autores
├── docs/
│   ├── requisitos.md
│   ├── arquitetura.md
│   ├── adr/                  # decisões arquiteturais
│   ├── diagramas/            # .puml, .drawio, imagens
│   └── manual-usuario.md
├── src/                      # código-fonte (ou firmware/)
├── tests/                    # testes automatizados
├── hardware/                 # esquemáticos, PCB, BOM (se aplicável)
├── dados/                    # datasets, scripts de coleta (respeite a LGPD)
├── experimentos/             # notebooks, scripts e resultados
├── monografia/               # LaTeX/Word/ODT, figuras, .bib
├── .github/workflows/        # CI/CD
├── docker-compose.yml
└── LICENSE
```

### 13.3 Boas práticas com Git

- **Commits pequenos e descritivos** (ex.: `feat: adiciona cadastro de usuário`).
- Branches por funcionalidade e **pull requests** (mesmo sozinho, para ter histórico).
- **Tags/releases** para as versões entregues (v0.1 na banca do TCC 1, v1.0 na defesa).
- Nunca versione **senhas, tokens, chaves de API ou dados pessoais** (use `.env` e `.gitignore`).

### 13.4 Ferramentas por finalidade

| Finalidade | Opções |
|---|---|
| Diagramas | draw.io, PlantUML, Mermaid, Astah, Lucidchart, StarUML |
| Prototipação de telas | Figma, Penpot, Balsamiq |
| Escrita | Overleaf (abnTeX2), Word, LibreOffice, Notion |
| Referências | Zotero, Mendeley, JabRef |
| Dados/experimentos | Jupyter, Google Colab, MLflow, DVC |
| Gráficos | Matplotlib, Seaborn, Plotly, R/ggplot2, Excel/Calc |
| Qualidade | SonarQube, ESLint, Checkstyle, SpotBugs |
| Gestão | Trello, Notion, GitHub Projects, Jira |

---

## 14. Ética, LGPD, plágio e uso de IA

### 14.1 Plágio e integridade acadêmica

- **Toda ideia, trecho, imagem ou código de terceiros deve ser citado.**
- Paráfrase **sem citação também é plágio**.
- Respeite **licenças** de software, imagens e datasets (MIT, GPL, Apache, Creative Commons).
- Instituições costumam usar **detectores de similaridade**; mantenha o índice baixo e justificado.
- **Autoplágio** (reaproveitar trabalhos seus anteriores) também exige citação.

### 14.2 Pesquisa com pessoas

- Se coletar dados de **pessoas** (questionários, entrevistas, testes de usabilidade), verifique se é necessária aprovação de **Comitê de Ética em Pesquisa (CEP/Plataforma Brasil)**: normas **Resolução CNS 466/2012** e **510/2016** (esta dispensa algumas pesquisas, como as de opinião sem identificação).
- Use **TCLE** (Termo de Consentimento Livre e Esclarecido).
- **Anonimize** os dados sempre que possível.
- Informe que a participação é **voluntária**.

### 14.3 LGPD (Lei 13.709/2018)

Se seu sistema ou pesquisa trata **dados pessoais**:

- Defina a **base legal** (consentimento, legítimo interesse etc.) e a **finalidade**.
- Aplique **minimização** (colete só o necessário).
- Implemente **segurança** (criptografia, controle de acesso, logs).
- Trate **dados sensíveis** (saúde, biometria, religião etc.) com ainda mais rigor.
- Garanta direitos do titular (acesso, correção, exclusão).
- Em datasets públicos, verifique **termos de uso** e anonimização.

### 14.4 Segurança e responsabilidade técnica

- Siga o **OWASP Top 10** em aplicações web.
- Não teste ataques em sistemas de terceiros sem **autorização formal por escrito**.
- Em IA: considere **viés, explicabilidade, privacidade e impacto social**.
- Em hardware: considere **segurança elétrica** e descarte correto de componentes.

### 14.5 Uso de ferramentas de IA generativa

- **Verifique a política da sua instituição** e do seu orientador antes de usar.
- Quando permitido, use como **apoio** (brainstorming, revisão de texto, explicação de conceitos, geração de exemplos de código), **nunca como substituto** do seu raciocínio.
- **Você é responsável por tudo** que entregar: confira fatos, referências e código. Ferramentas de IA podem inventar citações e erros.
- **Declare o uso** quando exigido (por exemplo, em nota na metodologia ou nos agradecimentos).
- Nunca cole dados pessoais, sigilosos ou de clientes em ferramentas externas.

---

## 15. Banca e apresentação oral

### 15.1 Estrutura sugerida (15 a 20 minutos, 12 a 18 slides)

| # | Slide | Conteúdo |
|---|---|---|
| 1 | Capa | Título, autor, orientador, curso, instituição, data |
| 2 | Contexto e problema | Dados, dor, impacto |
| 3 | Objetivos | Geral e específicos |
| 4 | Justificativa e lacuna | Por que importa e o que falta na literatura |
| 5 | Trabalhos relacionados | Comparação resumida |
| 6 | Metodologia | Fluxo das etapas |
| 7 | Solução / arquitetura | Diagrama principal |
| 8-10 | Desenvolvimento | Telas, protótipo, algoritmo, decisões-chave |
| 11-12 | Resultados | Gráficos e tabelas (poucos e claros) |
| 13 | Discussão | O que os resultados significam |
| 14 | Limitações e trabalhos futuros | Honestidade técnica |
| 15 | Conclusão | Objetivos atingidos e contribuições |
| 16 | Agradecimentos / Perguntas | Contato, repositório (QR Code) |

### 15.2 Boas práticas de apresentação

- **Um slide = uma mensagem.** Pouco texto, boas imagens e diagramas.
- **Ensaie** com cronômetro (pelo menos 3 vezes) e diante de outras pessoas.
- **Demonstre o produto** (de preferência ao vivo, com **vídeo de backup**).
- Tenha o **trabalho em PDF**, o **repositório** e a **demo** acessíveis offline.
- Chegue cedo, teste o equipamento, leve adaptadores.
- Vista-se de acordo com o ambiente acadêmico.

### 15.3 Arguição: como se preparar

Perguntas frequentes das bancas:

1. Por que escolheu essa tecnologia/método e não outra?
2. Qual a **diferença** do seu trabalho para os existentes?
3. Como você **validou** os resultados? Quais as **limitações**?
4. Como o sistema **escala**? E a **segurança**?
5. O que faria diferente? Quais os **próximos passos**?
6. Qual foi **sua contribuição** (principalmente em trabalhos em grupo)?
7. Qual o **custo** e a **viabilidade** de implantação?
8. Como tratou **LGPD/ética**?

**Dicas:** ouça a pergunta inteira, anote, responda com calma. Se não souber, diga com honestidade e explique como investigaria. Agradeça as sugestões e registre-as para a versão final.

---

## 16. Critérios de avaliação (rubrica)

Modelo de rubrica (ajuste aos critérios da sua instituição):

| Critério | Peso | Excelente (9-10) | Bom (7-8) | Regular (5-6) | Insuficiente (<5) |
|---|:-:|---|---|---|---|
| **Relevância do tema e problema** | 10% | Problema real, bem delimitado e justificado | Claro, com justificativa razoável | Vago ou pouco justificado | Sem problema definido |
| **Fundamentação e literatura** | 15% | Atual, crítica, bem organizada | Adequada, com algumas lacunas | Superficial | Inexistente ou plagiada |
| **Metodologia** | 15% | Replicável e coerente com os objetivos | Coerente com pequenas falhas | Confusa | Ausente |
| **Desenvolvimento técnico** | 25% | Sólido, bem decidido e documentado | Funcional com boa documentação | Funcional com falhas | Não funcional |
| **Validação e resultados** | 15% | Métricas claras, evidências fortes, discussão crítica | Evidências adequadas | Evidências fracas | Sem validação |
| **Texto e normas ABNT** | 10% | Claro, coeso, sem erros | Poucos erros | Muitos erros | Ilegível |
| **Apresentação e arguição** | 10% | Segura, clara e no tempo | Boa, com pequenas falhas | Insegura | Despreparada |

---

## 17. Erros comuns

| Erro | Como evitar |
|---|---|
| Tema amplo demais | Delimite escopo, público e contexto |
| Objetivos vagos | Use verbos mensuráveis e critérios de sucesso |
| "Só um CRUD" | Inclua diferencial técnico, dados reais, avaliação e justificativa |
| Referencial teórico copiado e sem foco | Selecione só o que sustenta o projeto; cite tudo |
| Deixar a escrita para o final | Escreva por capítulos durante o projeto |
| Não definir como medir sucesso | Estabeleça métricas e metas antes dos testes |
| Testes insuficientes | Planeje testes desde os requisitos |
| Ignorar ética/LGPD | Avalie dados, consentimento e segurança desde o início |
| Depender de terceiros sem plano B | Tenha alternativas (dados sintéticos, simulação, outro parceiro) |
| Subestimar o prazo | Cronograma realista com 15-20% de folga |
| Não versionar nem fazer backup | Git desde o primeiro dia |
| Reunir-se pouco com o orientador | Marque encontros regulares e leve avanços concretos |
| Slides lotados de texto | Priorize diagramas, gráficos e demonstração |
| Resultados sem discussão | Interprete, compare e reconheça limitações |
| Conclusão que não retoma os objetivos | Responda objetivo por objetivo |

---

## 18. Checklists finais

### 18.1 Antes da banca do TCC 1 (proposta)

- [ ] Tema, problema, objetivos e justificativa claros
- [ ] Revisão da literatura com trabalhos relacionados comparados
- [ ] Metodologia definida (inclusive métricas de avaliação)
- [ ] Requisitos iniciais e modelagem/arquitetura preliminar
- [ ] Protótipo, prova de conceito ou estudo de viabilidade
- [ ] Cronograma realista do TCC 2
- [ ] Texto revisado e dentro da ABNT
- [ ] Apresentação ensaiada

### 18.2 Antes da defesa final

- [ ] Todos os objetivos específicos atendidos (ou justificativa documentada)
- [ ] Produto/protótipo/algoritmo funcionando e **demo pronta (+ vídeo de backup)**
- [ ] Testes e experimentos concluídos, com análise e discussão
- [ ] Limitações e trabalhos futuros descritos
- [ ] Resumo e abstract revisados
- [ ] Referências completas e consistentes com as citações
- [ ] Figuras e tabelas numeradas, legendadas e citadas
- [ ] Revisão ortográfica e de formatação concluída
- [ ] Verificação de similaridade/plágio feita
- [ ] Repositório organizado, com README e licença
- [ ] Autorização do orientador para defesa

### 18.3 Após a defesa

- [ ] Registrar sugestões da banca
- [ ] Corrigir o texto e entregar a **versão final** no prazo
- [ ] Depositar no repositório institucional (se exigido)
- [ ] Publicar o código (com licença) e, se possível, **submeter um artigo** a um evento (SBC, SBSI, SBES, WTD, etc.)
- [ ] Atualizar LinkedIn e portfólio com o projeto

---

## 19. Modelos prontos

### 19.1 Modelo de pré-projeto (proposta de TCC)

```markdown
# Título provisório

**Autor:** Nome completo
**Orientador:** Prof. Nome
**Curso:** ______  **Semestre:** ______

## 1. Introdução e contexto
## 2. Problema de pesquisa
## 3. Objetivo geral
## 4. Objetivos específicos
## 5. Justificativa
## 6. Referencial teórico preliminar
## 7. Trabalhos relacionados (resumo)
## 8. Metodologia proposta
## 9. Resultados esperados
## 10. Cronograma
## 11. Riscos e mitigação
## Referências
```

### 19.2 Modelo de ata de reunião de orientação

```markdown
**Data:** __/__/____
**Participantes:** orientador e orientando
**Pauta:** ...
**Decisões:** ...
**Tarefas (responsável, prazo):**
- [ ] ...
**Próxima reunião:** __/__/____
```

### 19.3 Modelo de requisito

```markdown
| ID | Descrição | Prioridade | Critério de aceitação | Origem |
|----|-----------|------------|----------------------|--------|
| RF01 | O sistema deve permitir ... | Alta | Dado ... Quando ... Então ... | Entrevista com X |
| RNF01 | O sistema deve responder em até 2 s para 95% das requisições | Alta | Teste de carga com 100 usuários | Requisito de desempenho |
```

### 19.4 Modelo de ADR (decisão arquitetural)

```markdown
# ADR-001: Escolha do banco de dados
- **Status:** aceita
- **Contexto:** o sistema precisa de transações e relacionamentos complexos
- **Decisão:** usar PostgreSQL
- **Alternativas:** MySQL, MongoDB
- **Consequências:** + integridade e SQL avançado; - maior curva de administração
```

### 19.5 Modelo de matriz de riscos

| Risco | Probabilidade | Impacto | Mitigação | Responsável |
|---|---|---|---|---|
| Atraso na entrega de componentes | Média | Alto | Comprar com antecedência; simulação como plano B | Aluno |
| Dados indisponíveis | Média | Alto | Usar dataset público ou dados sintéticos | Aluno/Orientador |
| Perda de arquivos | Baixa | Alto | Git + backup em nuvem | Aluno |

### 19.5 Modelo de resumo (estrutura em 5 frases)

1. **Contexto:** "Em [área], [problema] afeta [público]."
2. **Objetivo:** "Este trabalho propõe/desenvolve [solução] para [finalidade]."
3. **Método:** "Utilizou-se [método/tecnologias] com [técnica de avaliação]."
4. **Resultados:** "Os resultados mostram [principal achado quantitativo]."
5. **Conclusão:** "Conclui-se que [contribuição principal]."

**Palavras-chave:** 3 a 5 termos separados por ponto.

---

## 20. Fontes úteis

**Normas e metodologia**
- ABNT NBR 14724, 10520, 6023, 6024, 6027, 6028
- WAZLAWICK, Raul S. *Metodologia de pesquisa para ciência da computação*. Elsevier.
- PRODANOV, C. C.; FREITAS, E. C. *Metodologia do trabalho científico*. Feevale.
- KITCHENHAM, B.; CHARTERS, S. *Guidelines for performing systematic literature reviews in software engineering*.
- HEVNER, A. et al. *Design Science in Information Systems Research*.
- WOHLIN, C. et al. *Experimentation in Software Engineering*.

**Engenharia de Software**
- SOMMERVILLE, I. *Engenharia de Software*. Pearson.
- PRESSMAN, R.; MAXIM, B. *Engenharia de Software: uma abordagem profissional*. McGraw-Hill.
- MARTIN, R. *Código Limpo* e *Arquitetura Limpa*. Alta Books.
- FOWLER, M. *Refatoração* e *Padrões de Arquitetura de Aplicações Corporativas*.
- SWEBOK (Guide to the Software Engineering Body of Knowledge).

**Engenharia de Computação**
- TANENBAUM, A. *Organização Estruturada de Computadores*.
- PATTERSON, D.; HENNESSY, J. *Organização e Projeto de Computadores*.
- WOLF, M. *Computers as Components* (sistemas embarcados).
- Documentação oficial: Arduino, ESP-IDF, STM32, Raspberry Pi, Zephyr.

**Ciência da Computação**
- CORMEN, T. et al. *Algoritmos: teoria e prática*.
- RUSSELL, S.; NORVIG, P. *Inteligência Artificial*.
- GOODFELLOW, I. et al. *Deep Learning*.
- SIPSER, M. *Introdução à Teoria da Computação*.
- Plataformas: arXiv, Papers With Code, OpenML.

**Sistemas de Informação**
- LAUDON, K.; LAUDON, J. *Sistemas de Informação Gerenciais*.
- O'BRIEN, J.; MARAKAS, G. *Administração de Sistemas de Informação*.
- PMBOK Guide, COBIT, ITIL, BPMN 2.0.
- *Guia BPM CBOK*.

**Legislação**
- LGPD (Lei nº 13.709/2018)
- Marco Civil da Internet (Lei nº 12.965/2014)
- Lei de Direitos Autorais (Lei nº 9.610/1998)
- Resoluções CNS 466/2012 e 510/2016 (ética em pesquisa)

**Eventos e periódicos brasileiros para publicar o TCC**
- SBES, SBSI, SBRC, SBBD, SBSeg, SBIE, WTD (Workshop de Teses e Dissertações), CSBC e seus workshops
- Periódicos: JSERD, RBIE, RITA, entre outros (verifique o Qualis vigente)

---

## Mensagem final

Um TCC bem-sucedido é resultado de **método, constância e comunicação**. Escolha um problema que importa, delimite bem o escopo, documente enquanto constrói, valide com evidências e converse com seu orientador com frequência. **Pequenos avanços semanais vencem grandes esforços de última hora.**

Bom trabalho!
