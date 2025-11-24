---

# 🧪 **Histórico das Técnicas de Teste de Software**

O teste de software, como disciplina formal, evoluiu ao longo de mais de sete décadas, acompanhando a complexidade crescente dos sistemas computacionais. No início da computação, até meados dos anos 1950, o teste era visto apenas como um processo simples de depuração, cujo objetivo era identificar e corrigir erros evidentes. No entanto, conforme os softwares passaram a ser usados em contextos mais críticos e de alto risco, tornou-se necessário incorporar metodologias científicas, técnicas sistemáticas e processos estruturados.

## **📌 Anos 1950 – Teste como depuração**

Entre 1949 e 1956, o teste de software era praticamente sinônimo de *debugging*. Os programadores executavam seus programas e, ao encontrar falhas, simplesmente as corrigiam. Não havia distinção entre os atos de *procurar defeitos* e *verificar se o software funcionava*. O objetivo era apenas “fazer o programa rodar”.

## **📌 Anos 1960 – Primeira visão teórica sobre testes**

A década de 1960 trouxe os primeiros trabalhos formais. O artigo seminal de **Gerald M. Weinberg** e outros pesquisadores introduziu a ideia de que testar não era apenas encontrar bugs, mas também *avaliar o comportamento* de um programa.
Começam a surgir as bases das técnicas **caixa-preta**, **caixa-branca** e **teste funcional**.

## **📌 Anos 1970 – Consolidação como disciplina**

Durante os anos 1970, com o aumento dos projetos governamentais e sistemas empresariais, o teste passou a ser visto como parte integrante do ciclo de desenvolvimento.

Eventos importantes:

* **1972:** Glenford Myers publica *The Art of Software Testing*, diferenciando depuração de teste e sistematizando as principais técnicas funcionais.
* Introdução dos conceitos de:

  * **teste de unidades**,
  * **teste de integração**,
  * **teste de sistema**,
  * **teste de aceitação**.

A disciplina começa a aparecer em cursos de Ciência da Computação.

## **📌 Anos 1980 – Processos e qualidade**

A qualidade ganha destaque com modelos como **ISO 9000** e **CMM**, que incorporam formalmente atividades de teste no ciclo de vida do software.

Marco importante:

* **Surgimento das primeiras ferramentas automatizadas**, voltadas principalmente para execução de testes repetitivos.

O foco passa a ser **confiabilidade**, **maturidade do processo** e **padronização**.

## **📌 Anos 1990 – Teste estruturado e orientado a objetos**

Com a expansão dos sistemas orientados a objetos, surgem técnicas específicas:

* **Teste baseado em classes e objetos**,
* **Testes baseados em estados** (máquinas de estado),
* **Testes derivados de diagramas UML**,
* **Testes orientados a cenários de uso** (use cases).

No final da década, surgem as primeiras ferramentas de automação robustas:

* JUnit (1997),
* Selenium primitivo (início dos anos 2000).

## **📌 Anos 2000 – Automação e metodologias ágeis**

Com o desenvolvimento ágil, o teste passa a fazer parte contínua do processo.
Práticas como:

* **TDD (Test-Driven Development)**,
* **BDD (Behavior-Driven Development)**,
* **Integração Contínua com testes automatizados**,
* **Testes regressivos automatizados**.

As equipes começam a trabalhar com pipelines de qualidade e testes em múltiplos ambientes.

## **📌 Anos 2010 – Testes para sistemas distribuídos, mobile e web escalável**

A explosão do desenvolvimento móvel e da computação em nuvem gera novas necessidades:

* Testes de carga massiva,
* Testes de performance distribuída,
* Testes de segurança avançada (OWASP),
* Testes em múltiplas plataformas e dispositivos (Android/iOS).

Frameworks como:
**Selenium WebDriver**, **Appium**, **JMeter**, **Cypress**, **JUnit 5**.

## **📌 Anos 2020–presente – Teste baseado em IA e ambientes autônomos**

Com IA e ML, surgem novos tipos de teste:

* **Testes de modelos de aprendizado de máquina** (verificação de viés, sobreajuste, estabilidade),
* **Teste autônomo de UI**,
* **Ferramentas capazes de gerar casos de teste automaticamente**.

Além disso, cresce a importância de:

* Teste para **microservices**,
* Teste de **APIs REST**,
* Teste em **pipelines DevOps**,
* Testes contínuos (*continuous testing*).

---

# 📊 **Tabela Comparativa entre Técnicas de Teste**

| **Técnica de Teste**     | **Tipo**             | **Como funciona**                              | **Vantagens**                                     | **Desvantagens**                            | **Quando usar**                         |
| ------------------------ | -------------------- | ---------------------------------------------- | ------------------------------------------------- | ------------------------------------------- | --------------------------------------- |
| **Caixa-Preta**          | Funcional            | Testa entradas e saídas sem ver o código.      | Fácil de aplicar; independente da implementação.  | Não revela erros internos; menor cobertura. | Teste de sistema, aceitação.            |
| **Caixa-Branca**         | Estrutural           | Testa caminhos internos do código.             | Alta cobertura; encontra erros lógicos.           | Exige conhecimento do código; mais caro.    | Teste de unidade e integração.          |
| **Teste de Unidade**     | Estrutural           | Testa funções, métodos e classes isoladamente. | Localiza erros cedo; fácil de automatizar.        | Pode não detectar falhas de integração.     | Desenvolvimento incremental, TDD.       |
| **Teste de Integração**  | Funcional/estrutural | Verifica interação entre módulos.              | Identifica erros de interface.                    | Pode ser complexo em sistemas grandes.      | Arquiteturas modulares e microservices. |
| **Teste de Regressão**   | Automatizado         | Reexecuta testes após mudanças no código.      | Remove efeitos colaterais e reintrodução de bugs. | Exige boa automação.                        | DevOps, CI/CD.                          |
| **Teste de Aceitação**   | Funcional            | Valida requisitos do usuário/cliente.          | Garante que o software atende ao objetivo final.  | Depende de comunicação clara com cliente.   | Entregas finais.                        |
| **Teste de Performance** | Não funcional        | Mede tempo, carga e estabilidade do sistema.   | Revela gargalos e problemas de escalabilidade.    | Requer ferramentas especializadas.          | Aplicações web, APIs.                   |
| **Teste de Segurança**   | Não funcional        | Avalia vulnerabilidades e invasão.             | Essencial para ambientes críticos.                | Alto custo e conhecimento técnico elevado.  | Sistemas corporativos e web.            |

---
