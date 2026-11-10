# 📦 Guia de JAR em Java para Iniciantes

> **Para quem é este guia?** Para quem já sabe escrever e executar um programa Java simples, mas **nunca trabalhou com arquivos `.jar`**.
> Você vai sair daqui sabendo criar, abrir, executar e usar um JAR, e entendendo o que fazer quando algo dá errado.

---

## 📑 Índice

1. [O que é um arquivo JAR?](#1--o-que-é-um-arquivo-jar)
2. [Antes de começar: o que você precisa](#2--antes-de-começar-o-que-você-precisa)
3. [Seu primeiro JAR, passo a passo](#3--seu-primeiro-jar-passo-a-passo)
4. [Entendendo o comando `jar`](#4--entendendo-o-comando-jar)
5. [O arquivo MANIFEST.MF](#5--o-arquivo-manifestmf)
6. [Projetos com pacotes](#6--projetos-com-pacotes)
7. [Abrindo e inspecionando um JAR](#7--abrindo-e-inspecionando-um-jar)
8. [Usando um JAR como biblioteca](#8--usando-um-jar-como-biblioteca)
9. [Colocando arquivos (recursos) dentro do JAR](#9--colocando-arquivos-recursos-dentro-do-jar)
10. [Gerando JAR com Maven e Gradle](#10--gerando-jar-com-maven-e-gradle)
11. [Erros comuns e como resolver](#11--erros-comuns-e-como-resolver)
12. [Tópicos avançados (opcional)](#12--tópicos-avançados-opcional)
13. [Cola rápida de comandos](#13--cola-rápida-de-comandos)
14. [Boas práticas](#14--boas-práticas)
15. [Exercícios](#15--exercícios)
16. [Glossário](#16--glossário)

---

## 1. 🎯 O que é um arquivo JAR?

**JAR** significa **J**ava **AR**chive (arquivo Java). É um arquivo `.jar` que guarda **vários arquivos dentro de um só**.

### Uma analogia

Imagine que você precisa enviar por e-mail 200 fotos. Em vez de anexar uma por uma, você coloca todas numa pasta e compacta em um `.zip`. Um arquivo só, mais fácil de enviar e guardar.

**O JAR é a mesma ideia, só que para programas Java.**

Um programa Java real tem dezenas ou centenas de arquivos `.class` (o código já compilado), além de imagens, textos de configuração e outros recursos. O JAR reúne tudo isso em **um único arquivo**.

### Um fato que ajuda muito

> **Um JAR é, tecnicamente, um arquivo ZIP.** Se você renomear `programa.jar` para `programa.zip`, consegue abrir com qualquer programa de descompactação e ver o que há dentro.

A diferença é que o JAR tem uma pasta especial chamada `META-INF`, com um arquivo de informações sobre o conteúdo (o **manifesto**, que veremos na seção 5).

### Para que serve?

| Uso | Exemplo |
|:----|:--------|
| **Distribuir um programa** | Você entrega `calculadora.jar` e a pessoa executa com um comando ou duplo clique |
| **Distribuir uma biblioteca** | Bibliotecas como JUnit e Gson chegam até você como `.jar` |
| **Organizar o projeto** | Um arquivo no lugar de centenas de `.class` soltos |
| **Garantir autoria** | O JAR pode ser assinado digitalmente (seção 12) |

### O que tem dentro de um JAR?

```
meu-app.jar
├── META-INF/
│   └── MANIFEST.MF          ← informações sobre o JAR
├── com/
│   └── exemplo/
│       └── Main.class       ← código compilado
├── imagens/
│   └── logo.png             ← recursos (imagens, textos...)
└── config/
    └── app.properties
```

> 💡 **Importante:** o JAR guarda código **compilado** (`.class`), não o código-fonte (`.java`). Para criar um JAR, primeiro é preciso compilar.

---

## 2. 🧰 Antes de começar: o que você precisa

Você precisa do **JDK** (Java Development Kit), e não apenas do JRE. O JDK traz as ferramentas `javac` (compilador) e `jar` (criador de JAR).

### Verifique se já está instalado

Abra o terminal:
- **Windows:** tecle `Win + R`, digite `cmd` e Enter (ou use o PowerShell)
- **Linux:** abra o aplicativo "Terminal"
- **macOS:** abra o aplicativo "Terminal" (Spotlight: `Cmd + Espaço` e digite "Terminal")

Execute:

```bash
java -version
javac -version
jar --version
```

Se os três comandos mostrarem uma versão (por exemplo, `javac 17.0.9`), **está tudo pronto**.

### Se aparecer "comando não encontrado" ou "não é reconhecido"

Instale um JDK (versão 17 ou mais recente é uma boa escolha):

| Sistema | Como instalar |
|:--------|:--------------|
| **Windows** | Baixe o instalador do [Adoptium Temurin](https://adoptium.net) e marque a opção para adicionar ao `PATH` |
| **Ubuntu/Debian** | `sudo apt update && sudo apt install openjdk-17-jdk` |
| **Fedora** | `sudo dnf install java-17-openjdk-devel` |
| **macOS** | Com Homebrew: `brew install openjdk@17` (ou use o instalador do Adoptium) |

Depois de instalar, **feche e abra o terminal** e repita os comandos de verificação.

> ⚠️ **Se `java -version` funciona mas `javac -version` não:** você instalou só o JRE. Instale o JDK.

---

## 3. 🚀 Seu primeiro JAR, passo a passo

Vamos criar um programa mínimo, compilar, empacotar em JAR e executar. Os comandos são **iguais no Windows, Linux e macOS** (as diferenças são avisadas quando existirem).

### Passo 1: crie a pasta do projeto

```bash
mkdir meu-primeiro-jar
cd meu-primeiro-jar
mkdir src
mkdir classes
```

Sua estrutura deve ficar assim:

```
meu-primeiro-jar/
├── src/         ← aqui fica o código-fonte (.java)
└── classes/     ← aqui vai o código compilado (.class)
```

### Passo 2: escreva o programa

Crie o arquivo `src/Ola.java` com um editor de texto (Bloco de Notas, VS Code, nano, o que preferir):

```java
public class Ola {
    public static void main(String[] args) {
        System.out.println("Olá, eu estou rodando de dentro de um JAR!");
    }
}
```

### Passo 3: compile

```bash
javac -d classes src/Ola.java
```

O que isso faz: o `javac` compila o `Ola.java` e coloca o resultado (`Ola.class`) na pasta `classes` (a opção `-d` significa "destino").

Confira que o arquivo foi gerado:
- **Windows:** `dir classes`
- **Linux/macOS:** `ls classes`

Você deve ver `Ola.class`.

### Passo 4: crie o JAR

```bash
jar cf ola.jar -C classes .
```

Pronto, o arquivo `ola.jar` apareceu na pasta. Não se preocupe em decorar o comando agora: a seção 4 explica cada letra.

### Passo 5: veja o que há dentro

```bash
jar tf ola.jar
```

Saída esperada:

```
META-INF/
META-INF/MANIFEST.MF
Ola.class
```

Repare que o `jar` criou sozinho a pasta `META-INF` e o `MANIFEST.MF`.

### Passo 6: execute o JAR

Primeiro, a forma "manual", dizendo qual classe deve ser executada:

```bash
java -cp ola.jar Ola
```

Saída:

```
Olá, eu estou rodando de dentro de um JAR!
```

🎉 **Você acabou de executar código de dentro de um JAR.**

### Passo 7: torne o JAR "executável"

Seria bom executar apenas com `java -jar ola.jar`, sem precisar informar a classe. Para isso, o JAR precisa saber qual é a classe principal. Recrie o JAR com a opção `e` (*entry point*):

```bash
jar cfe ola.jar Ola -C classes .
```

Agora execute:

```bash
java -jar ola.jar
```

Funcionou? Então seu JAR é **executável**. Em muitos sistemas com interface gráfica, também é possível executar um JAR de programa gráfico com duplo clique.

> 🔎 **O que mudou?** O comando escreveu `Main-Class: Ola` dentro do `MANIFEST.MF`. É assim que o Java descobre por onde começar. Veja na seção 5.

### Resumo visual do que você fez

```
Ola.java  ──(javac)──►  Ola.class  ──(jar)──►  ola.jar  ──(java -jar)──►  programa rodando
 código-fonte             compilado           empacotado                    execução
```

---

## 4. 🔤 Entendendo o comando `jar`

Formato geral:

```bash
jar <opções> <nome-do-jar> [arquivos]
```

As letras das opções vêm juntas, sem espaços e sem hífen:

| Letra | Significado | Como lembrar |
|:-----:|:------------|:-------------|
| `c` | **C**riar um JAR novo | **C**reate |
| `t` | Listar o conteúdo (**t**abela) | **T**able of contents |
| `x` | **E**xtrair o conteúdo | e**X**tract |
| `u` | **A**tualizar um JAR existente | **U**pdate |
| `f` | O próximo argumento é o nome do **f**ile (arquivo) JAR | **F**ile |
| `v` | Mostrar detalhes durante a operação | **V**erbose |
| `e` | Definir a classe principal (*entry point*) | **E**ntry point |
| `m` | Usar um arquivo de manifesto seu | **M**anifest |

### Desmontando o comando do passo 4

```bash
jar cf ola.jar -C classes .
```

| Parte | O que significa |
|:------|:----------------|
| `jar` | A ferramenta |
| `c` | Criar |
| `f` | O nome do arquivo vem a seguir |
| `ola.jar` | O nome do JAR a criar |
| `-C classes` | "Entre na pasta `classes` antes de pegar os arquivos" |
| `.` | "Pegue tudo que estiver nessa pasta" |

> 💡 **Por que usar `-C classes .`?** Sem o `-C`, o JAR guardaria os arquivos com o caminho `classes/Ola.class`, e o Java não os encontraria. Com `-C classes`, o `Ola.class` fica **na raiz** do JAR, que é onde precisa estar.

### Exemplos de combinações

```bash
jar cvf ola.jar -C classes .      # cria e mostra cada arquivo adicionado (v = verbose)
jar cfe ola.jar Ola -C classes .  # cria já definindo a classe principal
jar tf ola.jar                    # lista o conteúdo
jar xf ola.jar                    # extrai tudo na pasta atual
```

---

## 5. 📋 O arquivo MANIFEST.MF

O **manifesto** é um arquivo de texto dentro de `META-INF/`. Ele guarda informações **sobre** o JAR, como a "etiqueta" de uma embalagem.

### Como é

```
Manifest-Version: 1.0
Created-By: 17.0.9 (Eclipse Adoptium)
Main-Class: Ola
```

Cada linha segue o formato `Nome: valor`.

### Os atributos mais importantes

| Atributo | Para que serve | Exemplo |
|:---------|:---------------|:--------|
| `Manifest-Version` | Versão do formato do manifesto (sempre `1.0`) | `1.0` |
| `Main-Class` | **Classe que tem o método `main`.** Necessário para `java -jar` | `com.exemplo.app.Main` |
| `Class-Path` | Outros JARs de que este JAR depende, separados por **espaço** | `lib/a.jar lib/b.jar` |

Há outros atributos informativos (`Implementation-Title`, `Implementation-Version`, etc.), mas os três acima resolvem a maioria dos casos.

### Criando seu próprio manifesto

Crie um arquivo `manifesto.txt`:

```
Main-Class: Ola
```

> ⚠️ **Detalhe que pega muita gente:** a **última linha precisa terminar com uma quebra de linha** (tecle Enter depois do texto). Caso contrário, o Java ignora essa linha e o JAR não fica executável.

Depois use a opção `m`:

```bash
jar cfm ola.jar manifesto.txt -C classes .
```

> ⚠️ **Ordem importa:** em `cfm`, os argumentos vêm na ordem das letras: primeiro o nome do JAR (`f`) e depois o manifesto (`m`).

### Sobre o `Class-Path`

- Os caminhos são **relativos à localização do JAR**.
- Vários JARs são separados por **espaço**, não por vírgula nem ponto e vírgula.
- **Não aceita curinga** (`lib/*` não funciona dentro do manifesto).
- Se estiver em Windows, use `/` mesmo assim: `lib/a.jar`.

---

## 6. 🗂️ Projetos com pacotes

Programas reais organizam o código em **pacotes** (`package`). Isso muda um pouco a estrutura, e é aqui que muitos iniciantes se perdem. Vamos a um exemplo.

### Estrutura do projeto

```
projeto-pacotes/
├── src/
│   └── com/
│       └── exemplo/
│           └── app/
│               └── Main.java
└── classes/
```

O arquivo `src/com/exemplo/app/Main.java`:

```java
package com.exemplo.app;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programa com pacote rodando!");
    }
}
```

### Compile, empacote e execute

```bash
javac -d classes src/com/exemplo/app/Main.java
jar cfe app.jar com.exemplo.app.Main -C classes .
java -jar app.jar
```

> 🔑 **Regra de ouro:** na opção `e` (ou no `Main-Class`), use o **nome completo da classe**, incluindo o pacote, com pontos: `com.exemplo.app.Main`. Nada de `Main` sozinho, nem `com/exemplo/app/Main`, nem `Main.class`.

Veja como o JAR guardou os arquivos:

```bash
jar tf app.jar
```

```
META-INF/
META-INF/MANIFEST.MF
com/
com/exemplo/
com/exemplo/app/
com/exemplo/app/Main.class
```

A pasta do JAR espelha o nome do pacote.

---

## 7. 🔍 Abrindo e inspecionando um JAR

Receber um JAR de alguém e querer saber o que há nele é bem comum.

### Listar o conteúdo

```bash
jar tf app.jar
```

### Extrair tudo

```bash
jar xf app.jar
```

Os arquivos serão extraídos **na pasta atual**. Para não bagunçar, crie uma pasta antes:

```bash
mkdir extraido
cd extraido
jar xf ../app.jar
```

### Extrair só um arquivo

```bash
jar xf app.jar META-INF/MANIFEST.MF
```

### Ler o manifesto sem extrair

- **Linux/macOS:** `unzip -p app.jar META-INF/MANIFEST.MF`
- **Windows (PowerShell):** extraia com `jar xf` e abra o arquivo no Bloco de Notas

### Truque: abrir como ZIP

Como o JAR é um ZIP, você pode abri-lo com 7-Zip, WinRAR, o gerenciador de arquivos do Linux ou o macOS (com um descompactador). É a forma mais rápida de "espiar" o conteúdo.

### Ver o código-fonte de um `.class`

Arquivos `.class` são binários. Para ler o conteúdo, use um **decompilador**, como o [JD-GUI](https://github.com/java-decompiler/jd-gui) ou a opção de decompilar das IDEs (IntelliJ IDEA, por exemplo). O resultado é uma reconstrução aproximada do código original.

---

## 8. 📚 Usando um JAR como biblioteca

Boa parte dos JARs que você encontrará **não são programas**, e sim **bibliotecas**: código pronto que você usa nos seus programas. Vamos criar uma e usá-la.

### Parte A: criar a biblioteca

Estrutura:

```
biblioteca/
├── src/com/exemplo/lib/Calculadora.java
└── classes/
```

`Calculadora.java`:

```java
package com.exemplo.lib;

public class Calculadora {
    public static int somar(int a, int b) {
        return a + b;
    }
}
```

Compile e empacote (repare que **não há `Main-Class`**, pois uma biblioteca não executa sozinha):

```bash
cd biblioteca
javac -d classes src/com/exemplo/lib/Calculadora.java
jar cf calculadora.jar -C classes .
```

### Parte B: usar a biblioteca em outro programa

Em outra pasta, crie `Programa.java` e copie o `calculadora.jar` para lá:

```java
import com.exemplo.lib.Calculadora;

public class Programa {
    public static void main(String[] args) {
        System.out.println("2 + 3 = " + Calculadora.somar(2, 3));
    }
}
```

### Compilar informando onde está a biblioteca

A opção `-cp` (*classpath*) diz ao Java **onde procurar classes**:

```bash
javac -cp calculadora.jar -d out Programa.java
```

### Executar

O ponto delicado: o **separador** de classpath muda conforme o sistema.

| Sistema | Separador | Comando |
|:--------|:---------:|:--------|
| **Windows** | `;` | `java -cp "out;calculadora.jar" Programa` |
| **Linux / macOS** | `:` | `java -cp "out:calculadora.jar" Programa` |

Saída:

```
2 + 3 = 5
```

### O que é o classpath?

É a **lista de lugares** (pastas e JARs) onde o Java procura as classes de que o programa precisa. Se uma classe não estiver em nenhum lugar da lista, aparece o erro `ClassNotFoundException` ou `NoClassDefFoundError` (seção 11).

> 💡 **Dica:** para incluir todos os JARs de uma pasta `lib`, use o curinga **na linha de comando** (não no manifesto):
> - Windows: `java -cp "out;lib/*" Programa`
> - Linux/macOS: `java -cp "out:lib/*" Programa`

---

## 9. 🖼️ Colocando arquivos (recursos) dentro do JAR

Um JAR pode guardar mais do que `.class`: imagens, textos, arquivos de configuração. Eles são chamados de **recursos**.

### Exemplo

1. Crie `classes/config/app.properties` com o conteúdo:
   ```
   nome=Meu Aplicativo
   ```
2. Empacote normalmente (`jar cf ... -C classes .`). O arquivo entra no JAR junto com o resto.

### Como ler dentro do código

```java
import java.io.InputStream;
import java.util.Properties;

public class LerConfig {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();

        try (InputStream in = LerConfig.class.getResourceAsStream("/config/app.properties")) {
            if (in == null) {
                System.out.println("Recurso não encontrado!");
                return;
            }
            props.load(in);
        }

        System.out.println("Nome: " + props.getProperty("nome"));
    }
}
```

### ⚠️ Por que não usar `new File(...)`?

Dentro de um JAR, o arquivo **não existe como arquivo comum no disco**; ele está "dentro" do ZIP. `new File("config/app.properties")` funciona na sua IDE (onde os arquivos estão soltos) e **falha** quando você roda pelo JAR. Já o `getResourceAsStream` funciona nos dois casos.

> 📌 O `/` no início do caminho significa "a partir da raiz do JAR".

---

## 10. 🏭 Gerando JAR com Maven e Gradle

Na prática, quase ninguém cria JAR na mão em projetos grandes. Ferramentas de build fazem isso. Veja o essencial.

### Maven

Com um projeto Maven (arquivo `pom.xml`), basta:

```bash
mvn package
```

O JAR aparece na pasta `target/`. Para torná-lo **executável**, configure a classe principal no `pom.xml`:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.3.0</version>
      <configuration>
        <archive>
          <manifest>
            <mainClass>com.exemplo.app.Main</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Execute:

```bash
java -jar target/meu-projeto-1.0.jar
```

### Gradle

No `build.gradle`:

```groovy
plugins {
    id 'java'
}

jar {
    manifest {
        attributes 'Main-Class': 'com.exemplo.app.Main'
    }
}
```

Gere com `./gradlew jar` (Linux/macOS) ou `gradlew.bat jar` (Windows). O resultado fica em `build/libs/`.

### ⚠️ Atenção: dependências não vêm junto

O JAR gerado por `mvn package` ou `gradle jar` contém **só o seu código**. Se o projeto usa bibliotecas (Gson, por exemplo), `java -jar` vai falhar com `NoClassDefFoundError`. As soluções são:

1. **Fat JAR** (ou *uber JAR*): um JAR único com seu código **e** todas as dependências dentro (veja a seção 12).
2. Distribuir as bibliotecas numa pasta `lib/` e referenciá-las no `Class-Path` do manifesto.

---

## 11. 🐛 Erros comuns e como resolver

### ❌ `no main manifest attribute, in app.jar`

**Significa:** o JAR não sabe qual é a classe principal.
**Solução:** recrie o JAR com a classe principal:

```bash
jar cfe app.jar com.exemplo.app.Main -C classes .
```

Confira com `unzip -p app.jar META-INF/MANIFEST.MF` (Linux/macOS) se existe a linha `Main-Class`.

---

### ❌ `Error: Could not find or load main class ...`

**Causas mais comuns:**
- Nome da classe errado em `Main-Class`. Use o nome **completo com pacote**.
- Os `.class` ficaram dentro de uma subpasta no JAR (esqueceu o `-C classes .`). Confira com `jar tf`: o caminho deve começar pelo pacote (`com/exemplo/...`), e não por `classes/`.

---

### ❌ `ClassNotFoundException` / `NoClassDefFoundError`

**Significa:** o Java não achou uma classe de que precisava.
**Solução:**
- Verifique se o JAR da biblioteca está no classpath (`-cp`).
- Verifique o separador: `;` no Windows e `:` no Linux/macOS.
- Para saber se a classe existe no JAR: `jar tf biblioteca.jar` e procure o nome.
- Se usa `java -jar`, lembre que a opção `-cp` é **ignorada**. Use `Class-Path` no manifesto ou um fat JAR.

---

### ❌ `'jar' não é reconhecido como um comando interno` (ou `command not found`)

**Significa:** o JDK não está instalado ou não está no `PATH`.
**Solução:** volte à seção 2.

---

### ❌ `Unsupported class file major version XX` ou `UnsupportedClassVersionError`

**Significa:** o JAR foi compilado com uma versão do Java **mais nova** do que a que você está usando para executá-lo.
**Solução:** atualize o Java para executar, ou compile com uma versão menor usando `javac --release 11 ...`.

---

### ❌ O arquivo de recurso "não é encontrado" quando roda pelo JAR

**Solução:** use `getResourceAsStream` em vez de `File` (seção 9) e confira o caminho com `jar tf`.

---

### ❌ Dei duplo clique no JAR e nada acontece

**Causas:** o JAR não tem `Main-Class`, ou o programa é de console (não gráfico), então a janela fecha na hora. Teste sempre pelo terminal com `java -jar nome.jar` para ver as mensagens de erro.

---

## 12. 🎓 Tópicos avançados (opcional)

Esta seção é para quando você já estiver confortável com o básico.

### 12.1 Fat JAR (uber JAR)

JAR com o seu código **e** todas as dependências dentro, o que facilita a distribuição: um arquivo só, `java -jar` e pronto.

Com Maven, use o **Shade Plugin**:

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-shade-plugin</artifactId>
  <version>3.5.0</version>
  <executions>
    <execution>
      <phase>package</phase>
      <goals><goal>shade</goal></goals>
      <configuration>
        <transformers>
          <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
            <mainClass>com.exemplo.app.Main</mainClass>
          </transformer>
        </transformers>
      </configuration>
    </execution>
  </executions>
</plugin>
```

Aplicações Spring Boot já geram um JAR executável com dependências embutidas.

### 12.2 Lendo um JAR por código Java

O pacote `java.util.jar` permite abrir JARs dentro de um programa:

```java
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ListarJar {
    public static void main(String[] args) throws Exception {
        try (JarFile jar = new JarFile("app.jar")) {
            Enumeration<JarEntry> entradas = jar.entries();
            while (entradas.hasMoreElements()) {
                JarEntry e = entradas.nextElement();
                System.out.println(e.getName() + (e.isDirectory() ? "  (pasta)" : ""));
            }
        }
    }
}
```

### 12.3 Assinatura digital

Assinar um JAR garante que ele **não foi alterado** e identifica quem o publicou.

```bash
# 1. Criar um par de chaves (guardado num keystore)
keytool -genkeypair -alias minha-chave -keyalg RSA -keysize 2048 -validity 365 -keystore meu-keystore.jks

# 2. Assinar o JAR (a senha será solicitada)
jarsigner -keystore meu-keystore.jks app.jar minha-chave

# 3. Verificar
jarsigner -verify -verbose app.jar
```

> ⚠️ Nunca publique o `keystore` nem escreva a senha em scripts versionados no Git.

### 12.4 Atualizar um arquivo dentro de um JAR

```bash
jar uf app.jar -C classes com/exemplo/app/Main.class
```

### 12.5 Analisar dependências

```bash
jdeps app.jar
```

Mostra de quais pacotes e módulos o JAR depende.

### 12.6 JAR, WAR e EAR

Todos são arquivos ZIP com estrutura própria:

| Formato | Significado | Uso |
|:-------:|:------------|:----|
| **JAR** | Java ARchive | Bibliotecas e aplicações Java em geral |
| **WAR** | Web ARchive | Aplicações web (servlets, JSP) em servidores como Tomcat |
| **EAR** | Enterprise ARchive | Aplicações corporativas Jakarta EE (reúne WARs e JARs) |

```
meu-app.war
├── index.html
└── WEB-INF/
    ├── web.xml
    ├── classes/     ← suas classes
    └── lib/         ← JARs de dependências
```

---

## 13. 📊 Cola rápida de comandos

| O que fazer | Comando |
|:------------|:--------|
| Compilar para a pasta `classes` | `javac -d classes src/Arquivo.java` |
| Criar JAR | `jar cf app.jar -C classes .` |
| Criar JAR executável | `jar cfe app.jar pacote.Main -C classes .` |
| Criar JAR com manifesto próprio | `jar cfm app.jar manifesto.txt -C classes .` |
| Listar conteúdo | `jar tf app.jar` |
| Extrair tudo | `jar xf app.jar` |
| Atualizar arquivo | `jar uf app.jar -C classes arquivo.class` |
| Executar JAR executável | `java -jar app.jar` |
| Executar informando a classe | `java -cp app.jar pacote.Main` |
| Executar com biblioteca (Windows) | `java -cp "app.jar;lib/*" pacote.Main` |
| Executar com biblioteca (Linux/macOS) | `java -cp "app.jar:lib/*" pacote.Main` |
| Gerar JAR com Maven | `mvn package` |
| Ver dependências | `jdeps app.jar` |
| Assinar | `jarsigner -keystore ks.jks app.jar alias` |
| Verificar assinatura | `jarsigner -verify app.jar` |

---

## 14. ✅ Boas práticas

1. **Defina a `Main-Class`** em todo JAR que for executável.
2. **Use `-C pasta .`** ao criar o JAR, para que as classes fiquem na raiz.
3. **Coloque a versão no nome:** `meu-app-1.2.0.jar`.
4. **Leia recursos com `getResourceAsStream`**, nunca com `File`.
5. **Teste pelo terminal**, com `java -jar`, antes de entregar, e de preferência em outra máquina ou pasta.
6. **Use Maven ou Gradle** em projetos com dependências.
7. **Use fat JAR** para ferramentas de linha de comando e serviços; **JAR simples** para bibliotecas.
8. **Assine** JARs destinados à distribuição pública.
9. **Não versione `.jar` gerados** no Git (adicione `target/`, `build/` e `*.jar` ao `.gitignore`), a menos que seja uma biblioteca que precise estar no repositório.

---

## 15. 📝 Exercícios

Tente resolver sem olhar as seções anteriores. Depois confira.

**Exercício 1: JAR básico**
Crie um programa `Saudacao.java` que imprime seu nome. Compile, gere `saudacao.jar` e execute com `java -cp`.

**Exercício 2: JAR executável**
Recrie o JAR do exercício 1 para que funcione com `java -jar saudacao.jar`.

**Exercício 3: investigação**
Liste o conteúdo do seu JAR. Depois extraia o `MANIFEST.MF` e identifique a linha `Main-Class`.

**Exercício 4: provocando o erro**
Crie um JAR **sem** `Main-Class` e tente executar com `java -jar`. Que mensagem apareceu? Como corrigir?

**Exercício 5: biblioteca**
Crie uma biblioteca `ferramentas.jar` com uma classe `Texto` que tenha o método `static String inverter(String s)`. Crie outro programa que use essa biblioteca, e execute-o com `-cp`.

**Exercício 6 (desafio): recurso**
Coloque um arquivo `mensagem.txt` dentro de um JAR e faça o programa ler e exibir o conteúdo usando `getResourceAsStream`.

---

## 16. 📖 Glossário

| Termo | Significado simples |
|:------|:--------------------|
| **JAR** | Arquivo ZIP com extensão `.jar` que agrupa classes Java e recursos |
| **JDK** | Kit de desenvolvimento Java; traz `javac`, `jar` e outras ferramentas |
| **JRE** | Ambiente que apenas executa Java; não traz o compilador |
| **JVM** | A "máquina" que executa programas Java |
| **`.class`** | Arquivo com código Java já compilado (bytecode) |
| **Manifesto** | O arquivo `META-INF/MANIFEST.MF`, com informações sobre o JAR |
| **`Main-Class`** | Atributo do manifesto que aponta a classe com o método `main` |
| **Classpath** | Lista de pastas e JARs onde o Java procura classes |
| **Pacote (package)** | Forma de organizar classes em "pastas lógicas" (`com.exemplo.app`) |
| **Biblioteca** | JAR com código pronto para ser usado em outros programas |
| **Recurso** | Arquivo não-código dentro do JAR (imagem, texto, configuração) |
| **Fat JAR / Uber JAR** | JAR com o código do projeto e todas as dependências dentro |
| **Dependência** | Biblioteca de que o seu programa precisa para funcionar |
| **Maven / Gradle** | Ferramentas que automatizam compilação, dependências e geração de JAR |

---

> 🎓 **Próximo passo:** com os JARs dominados, vale estudar **Maven** e **Gradle**, que gerenciam dependências e empacotamento automaticamente, e depois **WAR** e **Spring Boot**.
