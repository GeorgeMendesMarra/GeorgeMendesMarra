# 🧩 Diferenças entre Classes Abstratas e Não Abstratas em Java

## 📘 1️⃣ Conceito Geral

| Tipo de Classe                     | Descrição                                                                                                                                                                                               |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Classe Abstrata**                | É uma classe **incompleta**, usada como **modelo (base)** para outras classes. Não pode ser instanciada diretamente. Pode conter **métodos abstratos** (sem corpo) e **métodos concretos** (com corpo). |
| **Classe Não Abstrata (Concreta)** | É uma classe **completa**, que pode ser **instanciada** (criar objetos). Todos os seus métodos devem ter **implementação completa**.                                                                    |

---

## ⚙️ 2️⃣ Instanciação

| Característica                | Classe Abstrata                             | Classe Concreta                         |
| ----------------------------- | ------------------------------------------- | --------------------------------------- |
| Pode criar objetos com `new`? | ❌ **Não pode** ser instanciada diretamente. | ✅ **Pode** ser instanciada diretamente. |
| Exemplo                       | `Animal a = new Animal(); // ERRO`          | `Cachorro c = new Cachorro(); // OK`    |

---

## 🔧 3️⃣ Métodos

| Tipo de Método            | Classe Abstrata                       | Classe Concreta                         |
| ------------------------- | ------------------------------------- | --------------------------------------- |
| **Abstratos** (sem corpo) | ✅ Pode conter métodos abstratos.      | ❌ Não pode conter métodos abstratos.    |
| **Concretos** (com corpo) | ✅ Pode conter métodos normais também. | ✅ Deve conter apenas métodos concretos. |

---

## 🧠 4️⃣ Herança e Implementação

| Aspecto         | Classe Abstrata                                                                                                 | Classe Concreta                                             |
| --------------- | --------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------- |
| Uso na herança  | É usada como **classe base (superclasse)**. Outras classes **herdam** e **implementam seus métodos abstratos**. | Pode herdar de outra classe concreta ou abstrata.           |
| Obrigatoriedade | Se uma subclasse herda uma classe abstrata, ela **deve implementar todos os métodos abstratos**.                | Não há métodos abstratos para implementar obrigatoriamente. |

---

## 💡 5️⃣ Exemplo Prático

### 🧱 Classe Abstrata

```java
// Classe abstrata serve como modelo
abstract class Animal {
    // Método abstrato (sem corpo)
    abstract void emitirSom();

    // Método concreto (com corpo)
    void dormir() {
        System.out.println("O animal está dormindo...");
    }
}
```

### 🐶 Classe Concreta que herda da Abstrata

```java
// Classe concreta que IMPLEMENTA o método abstrato
class Cachorro extends Animal {
    @Override
    void emitirSom() {
        System.out.println("O cachorro late: Au Au!");
    }
}

public class Principal {
    public static void main(String[] args) {
        // Animal a = new Animal(); // ERRO → não pode instanciar classe abstrata
        Cachorro c = new Cachorro(); // OK
        c.emitirSom(); // Executa método sobrescrito
        c.dormir();    // Executa método concreto herdado
    }
}
```

### 💻 Saída:

```
O cachorro late: Au Au!
O animal está dormindo...
```

---

## ⚖️ 6️⃣ Quando Usar Cada Tipo

| Situação                                                                                                      | Classe Abstrata | Classe Concreta |
| ------------------------------------------------------------------------------------------------------------- | --------------- | --------------- |
| Quando você quer definir um **modelo genérico** e forçar subclasses a implementar comportamentos específicos. | ✅               | ❌               |
| Quando a classe já possui **comportamento completo** e será usada para criar objetos diretamente.             | ❌               | ✅               |

---

## 🧩 7️⃣ Comparação Rápida

| Característica                               | Classe Abstrata     | Classe Concreta       |
| -------------------------------------------- | ------------------- | --------------------- |
| Pode ser instanciada?                        | ❌ Não               | ✅ Sim                 |
| Pode ter métodos abstratos?                  | ✅ Sim               | ❌ Não                 |
| Pode ter métodos concretos?                  | ✅ Sim               | ✅ Sim                 |
| Serve de modelo para subclasses?             | ✅ Sim               | ❌ Não necessariamente |
| Pode herdar outra classe?                    | ✅ Sim               | ✅ Sim                 |
| Deve implementar métodos abstratos herdados? | ✅ Sim (obrigatório) | —                     |

---

### ✳️ Resumo Final

* **Classe abstrata** → usada como **modelo**; pode ter métodos abstratos e concretos; **não pode ser instanciada**.
* **Classe concreta** → usada para **criar objetos**; todos os métodos têm implementação.

---
