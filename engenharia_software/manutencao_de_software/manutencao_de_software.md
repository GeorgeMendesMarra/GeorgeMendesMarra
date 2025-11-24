---

# 🧠 **Histórico da Manutenção de Software**

A manutenção de software é hoje uma das áreas mais críticas da engenharia de software, representando entre **60% e 90%** do custo total do ciclo de vida de um sistema. Porém, sua importância nem sempre foi reconhecida. O conceito evoluiu ao longo das décadas, acompanhando a profissionalização do desenvolvimento de software.

---

# 📜 **Linha do Tempo Completa da Manutenção de Software**

### **1950–1960: Primeiras ideias — manutenção como correção**

* Software era visto como extensão do hardware.
* Times pequenos e sistemas exclusivos.
* Manutenção significava apenas **consertar bugs** após a entrega.
* Não havia processo formal, documentação ou estimativas.

### **1968–1972: Crise do Software e nascimento da Engenharia de Software**

* A expressão “Crise do Software” expôs atrasos, custos altos e baixa qualidade.
* Surgem as primeiras preocupações com:

  * Documentação
  * Ciclos de vida
  * Controle de mudanças
* Em 1972, a comunidade percebe que **manutenção é maior que desenvolvimento**.

### **1976: Lehman & Belady — As Leis da Evolução do Software**

Este foi um marco fundamental. Eles afirmaram:

* Sistemas precisam evoluir continuamente.
* Evolução causa aumento de complexidade.
* Sem manutenção adequada, o software se degrada.

As *Leis de Lehman* definiram a visão moderna da manutenção.

### **1980–1990: Manutenção como disciplina formal**

* Padrões ISO/IEC começam a incluir manutenção.
* Divide-se manutenção em categorias (corretiva, adaptativa, perfectiva).
* Ferramentas CASE surgem para apoiar mudanças.
* Avanço dos bancos de dados e sistemas corporativos aumentam o custo de evoluir software.
* Reengenharia e engenharia reversa tornam-se práticas comuns.

### **1990–2000: Crescimento da engenharia de requisitos e da reusabilidade**

* Com a orientação a objetos, passa-se a focar em arquitetura mais estável.
* Refactoring ganha força (Martin Fowler, 1999).
* Modelos como CMMI e ISO/IEC 14764 formalizam processos de manutenção.

### **2000–2010: Web, sistemas distribuídos e manutenção contínua**

* Manutenção passa a incluir segurança e compatibilidade entre versões.
* A explosão da internet acelera ciclos de modificação.
* DevOps começa a surgir no fim da década.

### **2010–2020: Era DevOps e integração contínua**

* Manutenção torna-se parte do pipeline CI/CD.
* Refactoring contínuo e testes automatizados tornam-se prática comum.
* Microserviços surgem como forma de diminuir custos de manutenção.

### **2020–2025: Inteligência Artificial, automação e observabilidade**

* Manutenção preventiva e preditiva com IA.
* Uso de logs, métricas e tracing para detectar falhas antes de afetar usuários.
* Assistentes automáticos detectam códigos duplicados, erros comuns, vulnerabilidades.
* Automação de testes, revisão de código e monitoramento.

---

# 🧩 **Tipos de Manutenção — Tabela Comparativa Completa**

| Tipo de Manutenção         | Objetivo Principal                   | Quando Ocorre             | Exemplos                               | Benefícios                | Riscos de Não Realizar    |
| -------------------------- | ------------------------------------ | ------------------------- | -------------------------------------- | ------------------------- | ------------------------- |
| **Corretiva**              | Corrigir defeitos                    | Após falhas ou bugs       | Arrumar exceções, ajustar lógica       | Redução de falhas         | Insatisfação do usuário   |
| **Adaptativa**             | Adaptar o software a ambientes novos | Após mudanças externas    | Atualizar SO, banco, APIs              | Prolonga vida útil        | Sistema fica incompatível |
| **Evolutiva (Perfectiva)** | Adicionar novos recursos             | Durante novas demandas    | Novas telas, relatórios                | Aumenta valor do software | Perda de competitividade  |
| **Preventiva**             | Reduzir deterioração futura          | Durante revisões técnicas | Refactoring, remoção de dívida técnica | Menos erros futuros       | Aumento de complexidade   |
| **Preditiva** (moderna)    | Prever falhas antes de acontecer     | Com IA e métricas         | Análise de logs e tendências           | Evita interrupções        | Falhas inesperadas        |

---

# 🔧 **Exemplos Práticos de Manutenção**

## 🟦 **Exemplo 1 — Java (Correção e Preventiva)**

### **Correção:** corrigindo divisão por zero

```java
public int dividir(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("Divisor não pode ser zero.");
    }
    return a / b;
}
```

### **Preventiva:** refactoring para melhorar legibilidade

```java
public double calcularMedia(List<Integer> valores) {
    return valores.stream()
                  .mapToInt(Integer::intValue)
                  .average()
                  .orElse(0.0);
}
```

---

## 🟩 **Exemplo 2 — Python (Evolutiva e Adaptativa)**

### **Evolutiva:** adicionando logs

```python
import logging

logging.basicConfig(level=logging.INFO)

def processar_dados(dados):
    logging.info("Processando dados...")
    return [d * 2 for d in dados]
```

### **Adaptativa:** ajustando código para nova versão de biblioteca

```python
# antiga: pandas.read_csv(compression='gzip')
df = pd.read_csv("dados.csv", compression="infer")  # versão nova
```

---

## 🟧 **Exemplo 3 — Sistema Corporativo**

### Antes da manutenção:

* ERP não exportava relatórios em PDF.
* Usuários reclamavam (falha operacional).

### Manutenção Evolutiva:

* Criado novo módulo para exportação.
* Integração com serviço externo (ex.: JasperReports).

### Manutenção Preventiva:

* Refatorado módulo de relatórios para remover duplicações de SQL.

### Manutenção Adaptativa:

* Atualizado driver JDBC após migração para PostgreSQL 15.

---

# 🏛️ **Importância Atual da Manutenção**

Hoje, manutenção é parte fundamental do ciclo de vida, influenciada por:

* Arquitetura de software
* DevOps
* Testes automatizados
* Refactoring contínuo
* Observabilidade (logs, métricas, tracing)
* Monitoramento com IA

A manutenção deixou de ser apenas *consertar problemas* para se tornar uma ferramenta estratégica para manter produtos vivos, relevantes e competitivos.

---
