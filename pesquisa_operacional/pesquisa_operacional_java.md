# O Papel da Tecnologia Java na Pesquisa Operacional e Otimização

## Introdução

A **Pesquisa Operacional (PO)** é o ramo da matemática aplicada dedicado a modelar e resolver problemas de tomada de decisão sob restrições, como alocação de recursos, roteamento, escalonamento e planejamento da produção. Esses problemas são traduzidos em **modelos matemáticos** — normalmente de programação linear, inteira ou combinatória — que são então resolvidos por algoritmos especializados (solvers).

Java se consolidou como uma das linguagens mais utilizadas para implementar esses modelos em ambientes corporativos. Isso se deve a três fatores principais: **portabilidade** (a JVM roda no mesmo bytecode em qualquer sistema operacional), **maturidade do ecossistema** (bibliotecas testadas em produção há décadas) e **facilidade de integração** com bancos de dados, APIs REST e sistemas web já existentes nas empresas.

Este material apresenta os conceitos fundamentais da relação entre Java e PO, as principais bibliotecas disponíveis, um exemplo prático de código e as vantagens técnicas da linguagem para esse domínio.

---

## 1. Java e a Pesquisa Operacional

A PO trabalha, em geral, com três etapas:

1. **Modelagem** — definir variáveis de decisão, função objetivo e restrições;
2. **Resolução** — aplicar um algoritmo (Simplex, Branch and Bound, heurísticas, etc.) para encontrar a solução ótima ou próxima do ótimo;
3. **Interpretação** — traduzir a solução matemática em uma decisão prática (por exemplo, "produzir 120 unidades do produto A").

Java entra principalmente nas etapas 1 e 2, servindo como camada de modelagem que se comunica com um *solver* — o motor de cálculo responsável por resolver o problema. Essa separação entre "modelo" e "solver" é o padrão adotado pela maioria das bibliotecas do mercado.

---

## 2. Bibliotecas de Otimização em Java

| Biblioteca | Mantenedor | Tipo de problema | Licença |
|---|---|---|---|
| **OR-Tools** | Google | Programação linear, inteira e otimização combinatória (roteamento, bin packing) | Open source (Apache 2.0) |
| **CPLEX (IBM ILOG)** | IBM | Programação linear e inteira em larga escala | Comercial (com licença acadêmica gratuita) |
| **GLPK for Java (GLPK-Java)** | Comunidade / GNU | Programação linear e linear mista (via bindings JNI para o GLPK em C) | Open source (GPL) |
| **OptaPlanner** | Red Hat | Otimização de planejamento e escalonamento (constraint solving) | Open source (Apache 2.0) |

**Como escolher:** para prototipagem rápida e projetos acadêmicos, **OR-Tools** costuma ser o ponto de partida mais simples. Para problemas de escala industrial com suporte comercial, **CPLEX** é referência de mercado. Para problemas de escalonamento com muitas restrições combinatórias (como grade de horários ou escala de funcionários), **OptaPlanner** é especializado nesse tipo de tarefa.

---

## 3. Exemplo Prático: Programação Linear com OR-Tools

O exemplo abaixo resolve um problema clássico de otimização: maximizar o lucro de uma fábrica que produz dois produtos, sujeita a restrições de horas de máquina disponíveis.

```java
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;

public class ExemploOtimizacaoProducao {

    public static void main(String[] args) {
        Loader.loadNativeLibraries();

        // 1. Criação do solver (Simplex para programação linear)
        MPSolver solver = MPSolver.createSolver("GLOP");

        // 2. Variáveis de decisão: quantidade a produzir de cada item
        MPVariable produtoA = solver.makeNumVar(0.0, Double.POSITIVE_INFINITY, "produtoA");
        MPVariable produtoB = solver.makeNumVar(0.0, Double.POSITIVE_INFINITY, "produtoB");

        // 3. Restrição: horas de máquina disponíveis (máx. 40h/semana)
        MPConstraint restricaoHoras = solver.makeConstraint(0.0, 40.0, "horas_maquina");
        restricaoHoras.setCoefficient(produtoA, 2.0); // produtoA gasta 2h/unidade
        restricaoHoras.setCoefficient(produtoB, 1.0); // produtoB gasta 1h/unidade

        // 4. Função objetivo: maximizar o lucro
        MPObjective objetivo = solver.objective();
        objetivo.setCoefficient(produtoA, 30.0); // lucro por unidade de A
        objetivo.setCoefficient(produtoB, 20.0); // lucro por unidade de B
        objetivo.setMaximization();

        // 5. Resolução do modelo
        solver.solve();

        System.out.println("Produzir de A: " + produtoA.solutionValue());
        System.out.println("Produzir de B: " + produtoB.solutionValue());
        System.out.println("Lucro máximo: " + objetivo.value());
    }
}
```

**Comentário sobre o código:** observe que a lógica segue exatamente as três etapas descritas na Seção 1 — declaração das variáveis (modelagem), definição de restrições e objetivo, e chamada de `solver.solve()` (resolução). Esse padrão se repete em praticamente todas as bibliotecas de otimização em Java, o que facilita migrar de uma ferramenta para outra quando necessário.

---

## 4. Aplicações Práticas

| Área | Problema típico | Técnica / biblioteca comum |
|---|---|---|
| **Logística** | Roteamento de veículos (VRP), minimizando distância e custo de entrega | OR-Tools (módulo de roteamento) |
| **Produção industrial** | Alocação de máquinas e matérias-primas, planejamento de capacidade | Programação linear/inteira (CPLEX, GLPK) |
| **Gestão de pessoas** | Escalas de trabalho respeitando restrições legais e de disponibilidade | OptaPlanner (constraint solving) |
| **Finanças** | Otimização de carteiras de investimento (relação risco–retorno) | Programação quadrática / linear |

---

## 5. Vantagens de Usar Java para Pesquisa Operacional

- **Portabilidade** — o mesmo bytecode roda em Windows, Linux ou macOS sem recompilação, o que facilita a implantação em ambientes corporativos heterogêneos.
- **Eficiência** — a JVM moderna, com compilação *just-in-time*, oferece desempenho competitivo mesmo em cálculos numericamente intensivos.
- **Integração** — Java se conecta nativamente a bancos de dados (JDBC), APIs REST e frameworks web (Spring, Jakarta EE), o que permite expor um modelo de otimização como parte de um sistema maior.
- **Ecossistema maduro** — bibliotecas como as citadas na Seção 2 são usadas em produção há anos, com documentação extensa e comunidades ativas.

---

## Para Fixar

1. Qual é a diferença entre a camada de *modelagem* e a camada de *solver* em uma biblioteca de otimização?
2. Reescreva o exemplo da Seção 3 adicionando uma segunda restrição de matéria-prima disponível.
3. Em qual das aplicações da Seção 4 você usaria o OptaPlanner? Justifique.

---

## Referências para Aprofundamento

- Documentação oficial do Google OR-Tools: `https://developers.google.com/optimization`
- Documentação do OptaPlanner: `https://www.optaplanner.org/`
- IBM ILOG CPLEX Optimization Studio: `https://www.ibm.com/products/ilog-cplex-optimization-studio`
