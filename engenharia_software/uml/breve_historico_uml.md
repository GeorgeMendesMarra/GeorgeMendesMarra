## 📜 História do UML (Unified Modeling Language)

## 1. O Contexto: Antes do UML (Décadas de 1980-1990)

Antes do UML, não existia um padrão para modelar sistemas orientados a objetos. Cada metodologia tinha sua própria notação, o que gerava confusão e dificultava a comunicação entre equipes .

As principais metodologias concorrentes eram:

| Método | Criador(es) | Ano | Foco Principal |
|--------|-------------|------|----------------|
| **Booch Method** | Grady Booch | 1989/1994 | Design e implementação (forte em notação para código)  |
| **OMT** (Object Modeling Technique) | James Rumbaugh | 1991 | Análise e sistemas orientados a dados  |
| **OOSE** (Object-Oriented Software Engineering) | Ivar Jacobson | 1992 | Introdução dos **Casos de Uso** (Use Cases) para capturar requisitos  |

Cada método tinha vantagens, mas a falta de padronização era um grande problema para a indústria de software .

> 💡 **Analogia didática:** Imagine que cada fabricante de carro usasse um tipo diferente de volante ou pedal. Seria um caos! O UML veio para padronizar os "símbolos" da engenharia de software, assim como plantas baixas padronizadas existem na engenharia civil .

---

## 2. A Unificação: O Nascimento do UML (1994-1997)

### 2.1 O Encontro dos "Três Amigos" (Three Amigos)

O marco inicial ocorreu em **1994**, quando **James Rumbaugh** (criador do OMT) deixou a General Electric e se juntou a **Grady Booch** na **Rational Software** .

Em **1995**, **Ivar Jacobson** (criador do OOSE) também se uniu à Rational após sua empresa ser adquirida .

Os três passaram a trabalhar juntos para unificar o melhor de cada método em uma única linguagem de modelagem .

### 2.2 Cronologia das Versões Iniciais

```text
1994 ── Rumbaugh junta-se a Booch na Rational
   │
   ▼
1995 ── Publicação do Unified Method 0.8 (OOPSLA)
   │
   ▼
1996 ── UML 0.9 e 0.91 (já com o nome Unified Modeling Language)
   │
   ▼
1997 (Jan) ── UML 1.0 submetido ao OMG
   │
   ▼
1997 (Nov) ── UML 1.1 aprovado oficialmente pelo OMG 🎉
```

**Detalhes importantes:**
- **UML 0.8** (1995): Primeira versão pública, apresentada na conferência OOPSLA .
- **UML 1.0** (janeiro de 1997): Submetido ao OMG (Object Management Group) como resposta a uma solicitação de propostas .
- **UML 1.1** (novembro de 1997): **Adotado oficialmente** pelo OMG como padrão. Esta é considerada a primeira versão padrão da UML .

### 2.3 O Papel do OMG e o UML Partners Consortium

O OMG (Object Management Group) é uma organização sem fins lucrativos que define padrões para tecnologias orientadas a objetos e sistemas distribuídos.

Para desenvolver o UML, a Rational formou o **UML Partners Consortium**, que incluía gigantes da indústria como :
- **IBM**
- **Microsoft**
- **Oracle**
- **Hewlett-Packard (HP)**
- **Digital Equipment Corp**
- E muitas outras empresas (mais de 12 no total)

Esse consórcio ajudou a refinar a UML 1.0 até a versão 1.1, que foi oficialmente aprovada.

---

## 3. Evolução: UML 1.x a UML 2.x (1997 - Presente)

Após a adoção pelo OMG, a UML continuou evoluindo:

| Versão | Ano | Principais Mudanças |
|--------|------|---------------------|
| **UML 1.1** | 1997 | Primeira versão padrão oficial  |
| **UML 1.3** | 1999/2000 | Aceitação pelo ISO (padrão internacional)  |
| **UML 1.4** | 2000/2001 | Melhorias na semântica  |
| **UML 1.5** | 2003 | Correções e pequenos aprimoramentos  |
| **UML 2.0** | 2005 | **Grande revisão!** Novos diagramas (ex: Estrutura Composta, Temporização, Visão Geral de Interação)  |
| **UML 2.1** | 2006-2007 | Refinamentos do UML 2.0  |
| **UML 2.5** | 2015 | Versão mais recente (simplificação e melhorias)  |

> **Versão atual:** UML 2.5 (adotada oficialmente em 2015, é a base dos principais softwares de modelagem atualmente).

---

## 4. Principais Contribuições de Cada "Amigo"

| Criador | Método Original | Contribuição Principal para o UML |
|---------|----------------|-----------------------------------|
| **Grady Booch** | Booch Method | Notação para classes, objetos e relacionamentos; forte no design  |
| **James Rumbaugh** | OMT | Técnicas de análise, modelagem de dados e diagramas de objetos  |
| **Ivar Jacobson** | OOSE | **Casos de Uso (Use Cases)** – revolucionou a captura de requisitos  |

---

## 5. O Que é o UML? (Definição para a Aula)

O **UML (Unified Modeling Language)** é uma **linguagem de modelagem visual** padronizada, não uma metodologia .

**Características fundamentais:**
- ✅ **Linguagem, não método:** Não diz *como* fazer, mas *o que* pode ser modelado .
- ✅ **Independente de linguagem:** Pode ser usado com Java, C++, Python, etc. .
- ✅ **Padrão da indústria:** Mantido pelo OMG (Object Management Group) .
- ✅ **Versátil:** Aplica-se a sistemas de software, processos de negócio, sistemas embarcados, etc. .

> 📌 **Importante:** UML ≠ Processo de Desenvolvimento. Você pode usar UML com qualquer metodologia (ágil, cascata, RUP, etc.) .

---

## 6. Linha do Tempo Visual (Resumo para Slide)

```text
1989 ──── Booch Method
  │
1991 ──── OMT (Rumbaugh)
  │
1992 ──── OOSE (Jacobson)
  │
1994 ──── Rumbaugh junta-se a Booch na Rational
  │
1995 ──── Jacobson se junta → "Three Amigos"
  │         Unified Method 0.8
  │
1996 ──── UML 0.9 / 0.91
  │
1997 Jan ─ UML 1.0 submetido ao OMG
  │
1997 Nov ─ UML 1.1 APROVADO (padrão oficial)
  │
2005 ──── UML 2.0 (grande revisão)
  │
2015 ──── UML 2.5 (versão atual)
```

---

## 7. Por que o UML é Importante? (Para Debate em Aula)

1. **Padronização:** Permite que equipes diferentes se comuniquem usando os mesmos símbolos .
2. **Comunicação:** Facilita o entendimento entre analistas, designers, programadores e clientes .
3. **Documentação:** Serve como "planta baixa" do sistema .
4. **Geração de código:** Com MDA (Model-Driven Architecture), é possível gerar código automaticamente a partir de diagramas UML .
5. **Reúso:** Padrão consolidado com suporte em dezenas de ferramentas (Draw.io, Lucidchart, Visual Paradigm, etc.) .

---

## 8. Para Saber Mais: Diagramas UML

O UML possui **14 tipos de diagramas**, divididos em duas grandes categorias :

### 📐 Diagramas Estruturais (estática do sistema)
| Diagrama | O que mostra |
|----------|--------------|
| **Classe** | Classes, atributos, métodos e relacionamentos |
| **Objeto** | Instâncias de classes em um momento específico |
| **Componente** | Organização física dos componentes de software |
| **Implantação** | Hardware onde o software será executado |
| **Pacote** | Organização de grupos de elementos |
| **Estrutura Composta** | Estrutura interna de uma classe |

### ⚡ Diagramas Comportamentais (dinâmica do sistema)
| Diagrama | O que mostra |
|----------|--------------|
| **Caso de Uso** | Requisitos funcionais e atores |
| **Sequência** | Interação temporal entre objetos |
| **Atividade** | Fluxo de trabalho (algoritmo/processo) |
| **Estado (Máquina de Estados)** | Ciclo de vida de um objeto |
| **Comunicação** | Interação entre objetos (antigo Colaboração) |
| **Temporização (Timing)** | Restrições de tempo na interação |
| **Visão Geral de Interação** | Combinação de atividades e sequências |

---

## 📚 Referências para Aprofundamento

- **Site oficial do OMG:** Especificações completas do UML (www.omg.org/uml/)
- **Livro:** "The Unified Modeling Language User Guide" (Booch, Rumbaugh, Jacobson)
- **Ferramentas gratuitas:** Draw.io, Lucidchart, Visual Paradigm Community Edition

---
