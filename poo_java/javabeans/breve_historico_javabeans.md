---

## ☕ Breve Histórico dos JavaBeans e a Distinção EJB

JavaBeans é uma especificação da linguagem Java, criada pela **Sun Microsystems** (hoje Oracle), que define um **modelo de componentes de software reutilizáveis**.

### 1. 📅 Contexto e Nascimento (Meados dos Anos 90)

* **Necessidade de Componentização:** Com a ascensão de Java no final dos anos 90, havia uma grande necessidade de criar pequenos pacotes de código que pudessem ser facilmente **reutilizados, conectados e manipulados** visualmente por ferramentas de *design* (IDEs).
* **O Lançamento:** A especificação JavaBeans foi introduzida como parte do **JDK 1.1** (Java Development Kit), lançado em **1997**.

### 2. ✨ As Convenções Chave

O objetivo principal dos JavaBeans era estabelecer um padrão que permitisse a **Introspecção** (descoberta automática das capacidades do componente) por ferramentas. Para que uma classe Java seja reconhecida como um JavaBean, ela deve seguir as seguintes convenções:

1.  Deve ter um **construtor público sem argumentos** (padrão).
2.  Deve implementar a interface `java.io.Serializable` para persistência.
3.  As propriedades (atributos) devem ser privadas, acessíveis por métodos públicos **`get`** e **`set`** (ou `is` para booleanos) seguindo o padrão camelCase.

### 3. 🎯 O Propósito e Legado

A convenção JavaBeans rapidamente se tornou o **padrão *de facto*** para **modelar dados simples** (*Plain Old Java Objects - POJOs*) e transferir informações entre diferentes camadas de uma aplicação, além do seu uso original em componentes visuais. Muitos *frameworks* modernos, como **Spring** e **JSF**, dependem dessa convenção.

---

## 🆚 A Grande Diferença: JavaBeans vs. Enterprise JavaBeans (EJB)

Embora o nome seja semelhante, **JavaBeans** e **Enterprise JavaBeans (EJB)** são tecnologias que servem a propósitos dramaticamente diferentes dentro do ecossistema Java.

| Característica | JavaBeans (Componente de Dados) | Enterprise JavaBeans (EJB) |
| :--- | :--- | :--- |
| **Função Principal** | Modelagem de dados, encapsulamento e reutilização de código simples. Atua como um "contêiner de dados" (POJO). | Componente do lado do servidor que encapsula **lógica de negócio** complexa. |
| **Tecnologia/Plataforma** | Java SE (Standard Edition) – Padrão básico de codificação. | Java EE / Jakarta EE (Enterprise Edition) – Tecnologia de servidor. |
| **Requisitos** | Apenas um conjunto de convenções de nomenclatura (`getters`/`setters`, construtor vazio, serializável). | Requer um **Contêiner EJB** rodando em um Servidor de Aplicações (ex: WildFly, GlassFish). |
| **Serviços** | Nenhum serviço embutido. Apenas código simples. | Oferece serviços complexos automaticamente (transações, segurança, concorrência, acesso remoto, pooling). |
| **Complexidade** | Baixa. É um objeto simples. | Alta. Lida com a complexidade da infraestrutura corporativa. |
| **Exemplo de Uso** | Um objeto `Cliente` com propriedades `nome` e `endereco`. | Um componente `GerenciadorTransacao` que lida com o débito e crédito de contas bancárias. |

Em suma, a relação entre eles é mais de **coincidência de nome** (ambos são "beans" no sentido de componentes modulares de software) do que de função:

* **JavaBean:** Define **como um objeto deve ser estruturado** para ser facilmente manipulado por software.
* **EJB:** É uma **arquitetura de servidor** que fornece um ambiente robusto para componentes de lógica de negócios (os *Enterprise Beans*) rodarem em escala corporativa.
