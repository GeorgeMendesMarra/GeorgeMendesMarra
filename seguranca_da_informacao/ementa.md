# Segurança da Informação

**Curso:** Engenharia de Software
**Professor:** George Mendes Marra

---

## 1. Ementa

Fundamentos da segurança da informação: confidencialidade, integridade, disponibilidade, autenticidade e não repúdio. Ameaças, vulnerabilidades, riscos e controles. Segurança física, lógica e de comunicações. Criptografia, autenticação e controle de acesso. Segurança no ciclo de desenvolvimento de software e vulnerabilidades em aplicações web. Normas e frameworks (ISO/IEC 27001 e 27002, NIST, COBIT) e legislação aplicável (LGPD e Marco Civil da Internet). Gestão de riscos e métodos de avaliação quantitativa e qualitativa. Auditoria de sistemas e de tecnologia da informação: planejamento, implementação, ambiente computacional e técnicas. Gestão de incidentes, planos de contingência, continuidade de negócios e recuperação de desastres.

## 2. Objetivo geral

Capacitar o estudante a identificar, avaliar e tratar riscos de segurança em sistemas de informação, aplicando controles técnicos e organizacionais, práticas de desenvolvimento seguro e técnicas de auditoria.

## 3. Objetivos específicos

Ao final da disciplina, o estudante será capaz de:

- Explicar os princípios da segurança da informação e classificar ameaças, vulnerabilidades e controles.
- Aplicar mecanismos de segurança física, lógica e de comunicações.
- Utilizar criptografia, hashing, assinatura digital e certificados de forma adequada em sistemas.
- Identificar e mitigar as principais vulnerabilidades de aplicações (OWASP Top 10).
- Conduzir uma análise de riscos, comparando abordagens quantitativas e qualitativas.
- Planejar e executar uma auditoria de TI, produzindo relatório com evidências e recomendações.
- Elaborar planos de contingência, continuidade de negócios e recuperação de desastres.
- Relacionar requisitos legais (LGPD) e normas técnicas às decisões de projeto de software.

## 4. Conteúdo programático

### Unidade 1 — Fundamentos de segurança da informação
- Conceitos: confidencialidade, integridade, disponibilidade, autenticidade e não repúdio
- Ameaças, vulnerabilidades, ataques, riscos e controles
- Engenharia social, malware e principais tipos de ataque
- Política de segurança da informação

### Unidade 2 — Segurança física, lógica e de comunicações
- Segurança física: perímetro, controle de acesso, ambientes críticos
- Segurança lógica: autenticação, autorização, controle de acesso (RBAC) e MFA
- Segurança de comunicações: firewalls, IDS/IPS, VPN, segmentação de redes, TLS/HTTPS
- Hardening de sistemas operacionais e servidores

### Unidade 3 — Criptografia e identidade digital
- Criptografia simétrica e assimétrica
- Funções de hash, armazenamento seguro de senhas, assinatura digital
- Certificados digitais e infraestrutura de chaves públicas (PKI)

### Unidade 4 — Segurança no desenvolvimento de software
- Ciclo de vida de desenvolvimento seguro (Secure SDLC)
- OWASP Top 10: injeção de SQL, XSS, CSRF, quebra de controle de acesso e outras
- Validação de entrada, gestão de sessões, segredos e dependências
- Testes de segurança: SAST, DAST, revisão de código e testes de intrusão (noções)

### Unidade 5 — Gestão de riscos e métodos de avaliação
- Processo de gestão de riscos: identificação, análise, avaliação e tratamento
- Avaliação qualitativa x quantitativa (matriz de risco, ALE, SLE e ARO)
- Classificação e caracterização dos métodos de avaliação
- Normas e frameworks: ISO/IEC 27001, ISO/IEC 27002, NIST CSF e COBIT

### Unidade 6 — Auditoria de sistemas e de tecnologia da informação
- Conceitos e tipos de auditoria
- Planejamento, implementação e execução da auditoria
- Auditoria do ambiente computacional e técnicas de auditoria (entrevistas, testes, trilhas de auditoria e análise de logs)
- Relatório de auditoria, evidências e plano de ação

### Unidade 7 — Continuidade de negócios e recuperação de desastres
- Gestão de incidentes de segurança e resposta a incidentes
- Análise de impacto no negócio (BIA), RTO e RPO
- Planos de contingência, continuidade de negócios e recuperação de desastres
- Estratégias de backup e testes de restauração

### Unidade 8 — Aspectos legais e éticos
- LGPD (Lei nº 13.709/2018) e privacidade desde a concepção (privacy by design)
- Marco Civil da Internet e crimes cibernéticos
- Ética profissional e responsabilidade do engenheiro de software

## 5. Metodologia

- Aulas expositivas dialogadas com estudos de caso de incidentes reais.
- Laboratórios práticos: criptografia, análise de vulnerabilidades em aplicações web, configuração de firewall e análise de logs.
- Simulação de auditoria e de análise de riscos em um sistema fictício.
- Projeto integrador: avaliação de segurança de uma aplicação desenvolvida pelos próprios estudantes.

## 6. Avaliação

| Avaliação | Descrição | Peso |
|-----------|-----------|------|
| N1 | Prova escrita (Unidades 1 a 4) e atividades práticas | 10,0 |
| N2 | Prova escrita (Unidades 5 a 8) e projeto integrador (relatório de análise de riscos e auditoria) | 10,0 |

*Ajuste os pesos conforme o plano de ensino da instituição.*

## 7. Bibliografia básica

1. SÊMOLA, Marcos. **Gestão da segurança da informação**: uma visão executiva. 2. ed. Rio de Janeiro: Elsevier, 2014.
2. STALLINGS, William. **Criptografia e segurança de redes**: princípios e práticas. 6. ed. São Paulo: Pearson, 2015.
3. LYRA, Maurício Rocha. **Segurança e auditoria em sistemas de informação**. Rio de Janeiro: Ciência Moderna, 2008.

## 8. Bibliografia complementar

1. GIL, Antonio de Loureiro. **Auditoria de computadores**. 4. ed. São Paulo: Atlas, 1999.
2. NAKAMURA, Emilio Tissato; GEUS, Paulo Lício de. **Segurança de redes em ambientes cooperativos**. São Paulo: Novatec, 2007.
3. TANENBAUM, Andrew S.; WETHERALL, David. **Redes de computadores**. 5. ed. São Paulo: Pearson, 2011.
4. ALBERTIN, Alberto Luiz; ALBERTIN, Rosa Maria de Moura. **Estratégias de governança de tecnologia da informação**: estrutura e práticas. Rio de Janeiro: Elsevier, 2010.
5. TURBAN, Efraim; RAINER JR., R. Kelly; POTTER, Richard E. **Administração de tecnologia da informação**: teoria e prática. Rio de Janeiro: Elsevier, 2005.
6. ASSOCIAÇÃO BRASILEIRA DE NORMAS TÉCNICAS. **ABNT NBR ISO/IEC 27001:2022** e **ABNT NBR ISO/IEC 27002:2022**. Rio de Janeiro: ABNT, 2022.
7. BRASIL. **Lei nº 13.709, de 14 de agosto de 2018** (Lei Geral de Proteção de Dados Pessoais).
8. OWASP Foundation. **OWASP Top 10** e **OWASP ASVS**. Disponível em: https://owasp.org.
