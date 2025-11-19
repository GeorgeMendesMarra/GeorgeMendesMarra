---

# 🧩 Diferenças entre `public class` e `static class` em Java — Versão Ampliada

Em Java, `public class` e `static class` representam **conceitos completamente diferentes**:

* `public` é um **modificador de acesso**, ligado à **visibilidade** da classe.
* `static` é um **modificador de contexto**, ligado à **instância**, ao **acesso à classe externa**, e ao **modelo de memória**.

Embora ambos pareçam semelhantes para quem está iniciando, eles atuam em **níveis diferentes** da linguagem.

---

# 📘 1. O que significa `public class`?

`public` determina que a classe pode ser **acessada de qualquer parte do projeto**, independentemente do pacote.

Em Java:

* uma classe pública deve estar **em um arquivo com o mesmo nome**,
* só pode existir **uma classe pública por arquivo .java**,
* ela pode ser usada como **classe principal** para execução.

### ✔ Exemplo simples

```java
// Arquivo: Pessoa.java
public class Pessoa {
    private String nome;
}
```

Neste caso:

* o arquivo *precisa* se chamar `Pessoa.java`,
* qualquer classe de qualquer pacote pode importar e usar `Pessoa`.

---

# 📌 1.1 Como o Java trata `public class` internamente?

O compilador javac faz associações diretas:

* **Nome da classe = nome do arquivo**
* **Arquivo = unidade de compilação**

Isso existe para reforçar a organização do código e simplificar o carregamento de classes pelo JVM ClassLoader.

### 🔍 O que o ClassLoader faz?

Quando você escreve:

```java
Pessoa p = new Pessoa();
```

O ClassLoader procura por:

```
Pessoa.class
```

Se Pessoa não for pública, **nem sempre poderá ser instanciada** fora do pacote.

---

# ⚙ 1.2 Onde a classe pública fica na memória?

Quando carregada:

* o *bytecode* fica no **Método Área (Metaspace)**,
* instâncias ficam no **heap**,
* referências ficam na **stack**.

---

# 🧱 2. O que significa `static class`?

👉 **Não existe `static class` no topo de um arquivo Java.**

Você não pode fazer:

```java
static class Teste {}  // ❌ Inválido
```

O modificador `static` **só pode ser aplicado em classes internas**, ou seja, classes que ficam **dentro de outra classe**.

Essas classes são chamadas de:

### ✔ **Static Nested Classes** (classes internas estáticas)

---

# 🎯 2.1 Como funciona uma classe interna estática?

Uma *static nested class*:

* não depende de uma instância da classe externa,
* pode ser instanciada diretamente usando o nome da classe externa,
* não armazena ponteiro implícito para a classe externa (economia de memória),
* é semelhante a uma classe normal — mas agrupada logicamente dentro de outra.

### ✔ Exemplo

```java
public class Externa {

    static class Interna {
        void info() {
            System.out.println("Sou uma classe interna estática!");
        }
    }
}
```

### ✔ Como usar?

```java
Externa.Interna obj = new Externa.Interna();
obj.info();
```

---

# 🧠 2.2 Por que usar classes internas estáticas?

Elas são extremamente úteis para:

## 🔹 1. **Organização do código**

Quando uma classe só faz sentido dentro de outra:

* Tree.Node
* Map.Entry
* OuterClass.Builder

## 🔹 2. **Padrões de projeto**

Classes estáticas internas aparecem em:

* Builder Pattern
* Singleton com Holder Idiom
* Factory Interna
* Data Structures (árvores, grafos)

## 🔹 3. **Economia de memória**

Diferente de uma inner class normal, elas **não carregam referência oculta** para a classe externa.

---

# 🧩 2.3 Comparação entre classes internas: static vs não-static

| Tipo                           | Depende da instância externa? | Pode acessar membros da classe externa? | Armazena referência implícita? |
| ------------------------------ | ----------------------------- | --------------------------------------- | ------------------------------ |
| `static class`                 | ❌                             | Apenas membros estáticos                | ❌                              |
| `class Interna` (não estática) | ✔                             | Sim, tudo                               | ✔                              |

---

# 📌 3. Diferença conceitual fundamental

## 🟦 `public class`

* trata de **visibilidade (quem pode ver)**
* atua no **nível do arquivo completo**
* é uma classe **de topo** (top-level)

## 🟧 `static class`

* trata de **dependência de instância**
* atua **dentro de outra classe**
* é uma **nested class**

**Eles não concorrem entre si, pois atuam em domínios diferentes da linguagem.**

---

# 📚 4. Exemplos ampliados

## ✔ 4.1 Exemplo com Builder Pattern

```java
public class Pessoa {

    private String nome;
    private int idade;

    // Classe estática interna
    public static class Builder {
        private String nome;
        private int idade;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder idade(int idade) {
            this.idade = idade;
            return this;
        }

        public Pessoa build() {
            Pessoa p = new Pessoa();
            p.nome = this.nome;
            p.idade = this.idade;
            return p;
        }
    }
}
```

Uso:

```java
Pessoa p = new Pessoa.Builder()
    .nome("Ana")
    .idade(30)
    .build();
```

## ✔ 4.2 Exemplo com árvore binária

```java
public class Arvore {

    static class No {
        int valor;
        No esquerda, direita;
    }
}
```

---

# ⚠ 5. Erros comuns dos alunos

### ❌ Tentar criar classe estática de topo

```java
static class Teste {} // inválido
```

### ❌ Achar que `static` muda a visibilidade

Não muda.
Uma classe interna estática pode ser:

```java
public static class Interna {}
private static class Interna {}
protected static class Interna {}
```

### ❌ Achar que classe estática não pode ser instanciada

Pode. Ela só não depende da classe externa.

### ❌ Confundir com C#

C# **permite** static class de topo.
Java **não permite**.

---

# 🧾 6. Tabela comparativa completa

| Característica                                  | `public class` | `static class`        |
| ----------------------------------------------- | -------------- | --------------------- |
| Modificador de acesso                           | ✔              | ❌                     |
| Modificador de contexto                         | ❌              | ✔                     |
| Pode ser classe de topo?                        | ✔              | ❌                     |
| Pode existir sozinha no arquivo?                | ✔              | ❌                     |
| Pode existir dentro de outra classe?            | ✔              | ✔                     |
| Exige nome do arquivo?                          | ✔              | ❌                     |
| Tem acesso ao escopo externo?                   | Não            | Apenas membros static |
| Depende da instância externa?                   | Não            | Não                   |
| Carrega referência implícita da classe externa? | Não            | Não                   |
| Usada para Builder, Node, etc.                  | Pode           | Muito comum           |

---

# 📝 7. Conclusão ampliada

* `public class` controla **onde a classe pode ser acessada**.
* `static class` controla **como a classe se comporta dentro de outra**.
* Uma `static class` é útil para **agrupamento**, **organização**, **padrões de projeto** e **estrutura de dados**.
* Elas atuam em **domínios diferentes**:

  * `public` → **acesso**
  * `static` → **contexto / instância**

---

Professor George, se quiser posso gerar também:

📌 uma lista de exercícios sobre o tema
📌 um PDF formatado
📌 uma versão resumida para slides do IF Goiano
📌 diagramas UML comparativos

É só pedir!
