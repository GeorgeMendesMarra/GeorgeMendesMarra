---

# Histórico do Apache Struts

## 1. O Surgimento: Resolvendo o Caos do JSP (2000)

No final dos anos 90, as aplicações Java Web eram escritas misturando código Java diretamente dentro de páginas HTML (**JSP**). Isso tornava a manutenção impossível em projetos grandes.

* **Maio de 2000:** Criado por **Craig McClanahan** e doado à Apache Software Foundation.
* **Objetivo:** Separar a lógica de negócio (Model), a interface (View) e o controle de navegação (Controller), seguindo o padrão **Model 2** (baseado em Servlets).

## 2. Struts 1: O Padrão da Indústria (2001 - 2006)

O Struts 1 tornou-se rapidamente a escolha padrão para bancos, governos e grandes corporações.

* **Inovação:** Introduziu o `ActionServlet` como o controlador central e o arquivo `struts-config.xml` para gerenciar as rotas do sistema.
* **Dependência do Servlet API:** Nesta versão, as classes eram fortemente acopladas ao ambiente do servidor (HttpServletRequest/Response), o que dificultava os testes unitários.
* **Fim de uma Era:** O Struts 1.x foi declarado **End-of-Life (EOL)** em 2013, mas ainda é encontrado em muitos sistemas legados "que não podem parar".

## 3. A Fusão: Nasce o Struts 2 (2006 - 2007)

O Struts 1 estava ficando datado e complexo. Em vez de uma atualização incremental, a Apache decidiu unir forças com outro framework chamado **WebWork**.

* **WebWork + Struts:** O Struts 2 é, na verdade, o sucessor do WebWork 2.2, rebatizado.
* **Melhorias:** * **POJOs:** As "Actions" deixaram de ser classes complexas e passaram a ser objetos simples (Plain Old Java Objects), fáceis de testar.
* **Interceptors:** Introduziu o conceito de interceptores para tratar segurança, logs e validações de forma limpa.
* **OGNL:** Adotou uma linguagem de expressão poderosa para manipular dados entre a tela e o Java.



## 4. Desafios de Segurança e a Ascensão do Spring

Apesar de ser tecnicamente robusto, o Struts enfrentou dois grandes obstáculos:

1. **Vulnerabilidades Críticas:** O uso da linguagem **OGNL** abriu brechas para ataques de execução remota de código (RCE). O caso mais famoso foi a invasão da **Equifax em 2017**, causada por uma versão não corrigida do Struts 2.
2. **Concorrência:** O **Spring MVC** surgiu com uma proposta mais integrada e flexível, acabando por herdar a coroa de "padrão do mercado" que antes pertencia ao Struts.

## 5. O Struts Hoje (2025/2026)

Embora não seja a primeira escolha para novos projetos (onde Spring Boot e Quarkus dominam), o Apache Struts **não morreu**.

* **Manutenção Ativa:** A comunidade Apache continua lançando versões (como a série 6.x e 7.x) focadas em segurança, suporte ao Java moderno (Jakarta EE) e performance.
* **Legado Corporativo:** Continua sendo essencial para a manutenção de sistemas críticos que foram construídos sobre sua arquitetura e não podem ser reescritos facilmente.

---

### Comparação de Eras

| Característica | Struts 1 (O Clássico) | Struts 2 (O Moderno) |
| --- | --- | --- |
| **Baseado em** | Servlet API puro | WebWork 2 |
| **Configuração** | XML pesado | XML ou Annotations |
| **Testabilidade** | Difícil (requer servidor) | Fácil (usa POJOs) |
| **Status Atual** | Descontinuado (Legado) | Ativo (Manutenção) |

---

> **Nota Técnica:** Se você encontrar um arquivo chamado `struts-config.xml`, você está lidando com o **Struts 1**. Se o arquivo for `struts.xml` e as extensões das URLs forem `.action`, você está no **Struts 2**.
