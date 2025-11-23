---

# 🏛️ **Histórico do Projeto Orientado a Objetos (OO)**

---

## 📌 **1. Origem (1960–1970): Os Primeiros Conceitos**

O paradigma orientado a objetos nasce em um contexto de crescente complexidade dos sistemas e de necessidade de modelar software de forma mais próxima do mundo real.

### **Simula (1962–1967) – O marco inicial**

* Desenvolvido por Ole-Johan Dahl e Kristen Nygaard (Noruega).
* Introduziu:

  * **Classes**
  * **Objetos**
  * **Herança**
  * **Simulação de processos**
* Primeira linguagem com comportamento típico de "objetos vivos" trocando mensagens.

### **Smalltalk (1972–1980) – Consolidação da OO**

* Criado no Xerox PARC por Alan Kay e equipe.
* Reforçou:

  * Tudo é objeto
  * Metáfora de mensagens
  * Ambientes gráficos interativos
  * Polimorfismo e late binding
* Influenciou fortemente Java, Python, Ruby e C#.

---

## 📌 **2. Expansão (1980–1990): OO Entra na Engenharia de Software**

### **C++ (1983) – OO para sistemas de alto desempenho**

* Adicionou OO à linguagem C.
* Popularizou o conceito de **classes + eficiência**.
* Tornou OO atraente para:

  * compiladores,
  * sistemas operacionais,
  * jogos,
  * aplicações científicas.

### **Modelagem Orientada a Objetos**

Autores como:

* Grady Booch
* James Rumbaugh
* Ivar Jacobson

Criam notações próprias (Booch Method, OMT e OOSE).
Unificadas posteriormente na **UML (1997)**.

---

## 📌 **3. Era Moderna (1995–2005)**

### **Java (1995)**

* Modelo OO mais seguro e portátil.
* Encapsulamento forte e memória gerenciada (Garbage Collector).
* Padroniza conceitos como:

  * Classes abstratas
  * Interfaces
  * Polimorfismo dinâmico

### **UML (1997) – Universalização da análise e projeto OO**

* Unificação dos métodos Booch, OMT e OOSE.
* Se torna padrão de modelagem da OMG.
* Introduzpleta taxonomia de diagramas:

  * Classes, objetos, sequências, atividades, casos de uso etc.

### **C# (2000)**

* Refinamento moderno e pragmático do paradigma.

---

## 📌 **4. Era Contemporânea (2005–2025)**

OO passa a conviver com novos paradigmas:

* Programação funcional (Scala, Haskell, Elixir)
* Paradigma reativo (ReactiveX, Akka, Vert.x)
* Programação orientada a serviços e microserviços
* Arquiteturas hexagonais, DDD e CQRS

OO se mantém relevante por:

* representar bem entidades de domínio;
* suportar encapsulamento e modularidade;
* integrar-se bem com frameworks modernos (Spring, .NET Core).

---

# 🧠 **Comparações Entre Paradigmas de Projeto**

A seguir, tabelas organizadas para comparação direta.

---

## 🗂️ **Tabela 1 — OO vs Programação Estruturada**

| Critério       | Orientada a Objetos     | Programação Estruturada       |
| -------------- | ----------------------- | ----------------------------- |
| Unidade básica | Objeto                  | Função/Procedimento           |
| Organização    | Classes e hierarquias   | Fluxo sequencial e modular    |
| Estado         | Encapsulado em objetos  | Variáveis globais e locais    |
| Reuso          | Herança, polimorfismo   | Funções reutilizáveis         |
| Modelagem      | Inspirada no mundo real | Inspirada em sequência lógica |
| Exemplos       | Java, C#, Smalltalk     | C, Pascal                     |

---

## 🗂️ **Tabela 2 — OO vs Programação Funcional**

| Critério            | OO                                  | Funcional                  |
| ------------------- | ----------------------------------- | -------------------------- |
| Estado              | Mutável                             | Imutável                   |
| Fluxo               | Mensagens entre objetos             | Composição de funções      |
| Abstração principal | Classe/objeto                       | Função                     |
| Concorrência        | Mais difícil (estado compartilhado) | Mais fácil (imutabilidade) |
| Exemplos            | Java, C#                            | Haskell, Elixir, Scala     |

---

## 🗂️ **Tabela 3 — OO vs Programação Reativa**

| Critério         | OO                    | Reativa                           |
| ---------------- | --------------------- | --------------------------------- |
| Modelo mental    | Objetos encapsulados  | Fluxos contínuos de eventos       |
| Foco             | Estrutura de classes  | Propagação assíncrona             |
| Aplicação típica | Sistemas corporativos | Sistemas distribuídos e streaming |
| Exemplos         | Java/POO, C#          | RxJava, Akka, Reactor             |

---

# 🏗️ **Exemplos Práticos – Evolução do Projeto OO**

A seguir, 3 exemplos práticos mostrando como OO se diferencia dos outros paradigmas.

---

## ▶️ **Exemplo 1 — Modelagem OO (padrão domínio)**

```java
class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double aplicarDesconto(double percentual) {
        return preco - (preco * percentual);
    }
}

class Pedido {
    private List<Produto> itens = new ArrayList<>();

    public void adicionarItem(Produto p) {
        itens.add(p);
    }
}
```

Características: encapsulamento, objetos conversando entre si.

---

## ▶️ **Exemplo 2 — Versão Estruturada**

```c
struct Produto {
    char nome[50];
    float preco;
};

float aplicarDesconto(struct Produto p, float percentual) {
    return p.preco - (p.preco * percentual);
}
```

Foco em dados + funções separadas.

---

## ▶️ **Exemplo 3 — Versão Funcional (conceitualmente)**

```scala
case class Produto(nome: String, preco: Double)

val aplicarDesconto = (produto: Produto, p: Double) =>
  produto.copy(preco = produto.preco - produto.preco * p)
```

Imutabilidade + funções puras.

---

# 🧭 **Conclusão Geral**

O **Projeto Orientado a Objetos** evoluiu de:

* simulações (1960),
* linguagens puras de objetos (1970),
* engenharia de software estruturada (1980–1990),
* padronização e linguagens populares (1990–2005),
* integração com novos paradigmas (2005–2025).

Embora novos paradigmas tenham surgido, OO continua essencial porque:

* organiza grandes sistemas de forma natural;
* facilita modularidade e manutenção;
* integra-se a padrões modernos como DDD, MVC, Microservices e SOLID.

---
