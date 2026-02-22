## Histórico do `enum` no Java

O `enum` no **Java** surgiu oficialmente na **versão 5 (Java 1.5), lançada em 2004**, como parte de uma grande atualização da linguagem conhecida como **Java 5**.

---

## 🔹 Antes do `enum` (Java 1.0 até 1.4)

Antes de 2004, não existia enumeração nativa em Java.
Os desenvolvedores simulavam enums usando constantes `public static final int`:

```java
public class Dia {
    public static final int SEGUNDA = 1;
    public static final int TERCA = 2;
    public static final int QUARTA = 3;
}
```

### ❌ Problemas desse modelo:

* Não havia **segurança de tipo**
* Valores inválidos podiam ser atribuídos
* Não era possível associar comportamento às constantes
* Dificuldade de manutenção

---

## 🔹 Java 5 (2004) – Introdução do `enum`

Com o lançamento do **Java 5**, a linguagem passou por uma modernização importante, incluindo:

* Generics
* Annotations
* Autoboxing
* Enhanced for
* E o **enum**

O `enum` foi inspirado principalmente na linguagem **C++**, mas com um diferencial poderoso:
em Java, **enum é uma classe especial**.

---

## 🔹 O que mudou com o `enum`

Exemplo:

```java
public enum Dia {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;
}
```

### ✅ Benefícios:

* Segurança de tipo
* Conjunto fechado de valores
* Pode ter:

  * atributos
  * construtores
  * métodos
  * sobrescrita de comportamento

Exemplo avançado:

```java
public enum Operacao {
    SOMA {
        public double calcular(double a, double b) {
            return a + b;
        }
    },
    SUBTRACAO {
        public double calcular(double a, double b) {
            return a - b;
        }
    };

    public abstract double calcular(double a, double b);
}
```

---

## 🔹 Evolução após Java 5

O `enum` continuou ganhando integração com novos recursos:

### 🔸 Java 7 (2011)

* `switch` com String (melhor integração com enum)

### 🔸 Java 8 (2014)

* Uso com Streams
* Métodos funcionais

### 🔸 Java 14+ (Preview → oficial no 17)

* Switch expressions modernas
* Pattern matching (evolução futura)

---

## 🔹 Internamente, como funciona?

Todo `enum`:

* Herda implicitamente de `java.lang.Enum`
* É final
* Não pode estender outra classe
* Pode implementar interfaces

---

## 🔹 Importância na Engenharia de Software

Enums são amplamente usados para:

* Estados de sistemas
* Padrão State
* Padrão Strategy
* Máquinas de estados
* Representação de tipos fixos
* Configurações seguras

---

## 📌 Resumo Histórico

| Ano   | Versão   | Evento                                          |
| ----- | -------- | ----------------------------------------------- |
| 1995  | Java 1.0 | Sem enum                                        |
| 2004  | Java 5   | Introdução oficial do `enum`                    |
| 2011+ | Java 7+  | Melhor integração com switch                    |
| 2014+ | Java 8+  | Integração com Streams                          |
| 2017+ | Java 9+  | Modularização, sem mudanças estruturais no enum |

---

