## 🎯 O que é Apache ANT? (A resposta curta)

**Apache ANT é uma ferramenta de automação de build que foi o padrão da indústria Java antes do Maven e do Gradle.**

Pense no ANT como um **"faz-tudo" manual** para construir software — você diz exatamente cada passo que ele deve dar, e ele obedece.

> Curiosidade: O nome "ANT" vem de "Another Neat Tool" (Outra Ferramenta Legal). Irônico, né? Ele se autodenomina "outra" ferramenta desde o início!

---

## 🏗️ Que tarefas o ANT automatizava?

Antes do ANT, os desenvolvedores compilavam seus programas manualmente, linha por linha, ou usando scripts de shell do sistema operacional (`.bat` no Windows, `.sh` no Linux/Mac). O ANT trouxe uma forma **padronizada e multiplataforma** de fazer isso.

| Tarefa | O que faz | Como era antes | Com ANT |
|:-------|:----------|:---------------|:---------|
| **Compilar** | Transformar código Java em bytecode | `javac *.java` no terminal | `<javac srcdir="src" destdir="build"/>` |
| **Testar** | Executar testes JUnit | Executar testes manualmente | `<junit>` com relatórios |
| **Empacotar** | Criar arquivo JAR | `jar cf meuapp.jar -C build .` | `<jar destfile="meuapp.jar" basedir="build"/>` |
| **Copiar arquivos** | Mover recursos para o lugar certo | Comandos `cp` ou `copy` | `<copy todir="build/resources">` |
| **Limpar** | Remover arquivos temporários | `rm -rf build/` ou `rmdir /s build` | `<delete dir="build"/>` |

---

## 🔄 Comparação com o dia a dia

Imagine que você vai montar um móvel de madeira (como um armário):

**Sem ferramenta (manual total):**
1. Você corta a madeira com uma serra manual
2. Lixa cada peça com lixa
3. Passa cola nas emendas
4. Pregos e parafusos um a um
5. Pinta com pincel

Você faz **cada movimento**, exatamente como quer, com total controle.

**Com ANT:**
1. Você escreve uma "receita" dizendo: "corte a madeira, lixe, cole, pregue, pinte"
2. Executa o ANT
3. O ANT segue sua receita, passo a passo, exatamente como você mandou

**Com Maven/Gradle (contraste):**
1. Você só diz: "faça um armário"
2. A ferramenta decide como fazer, seguindo padrões predefinidos

---

## 📝 Como o ANT sabe o que fazer?

Você escreve um arquivo de instruções chamado **`build.xml`** (sempre com esse nome, na raiz do projeto). Ele é escrito em **XML** e parece uma lista de tarefas (chamadas de **targets**).

### Exemplo simples de `build.xml`:

```xml
<?xml version="1.0"?>
<project name="MeuProjeto" default="build" basedir=".">

    <!-- Propriedades (variáveis) -->
    <property name="src.dir" value="src"/>
    <property name="build.dir" value="build"/>
    <property name="dist.dir" value="dist"/>

    <!-- Tarefa de limpeza -->
    <target name="clean">
        <delete dir="${build.dir}"/>
        <delete dir="${dist.dir}"/>
    </target>

    <!-- Tarefa de compilação -->
    <target name="compile" depends="clean">
        <mkdir dir="${build.dir}"/>
        <javac srcdir="${src.dir}" destdir="${build.dir}"/>
    </target>

    <!-- Tarefa de empacotamento -->
    <target name="jar" depends="compile">
        <mkdir dir="${dist.dir}"/>
        <jar destfile="${dist.dir}/meuapp.jar" basedir="${build.dir}"/>
    </target>

    <!-- Tarefa principal -->
    <target name="build" depends="jar"/>

</project>
```

Com esse arquivo, você executa:

```bash
ant          # executa o target padrão ("build")
ant clean    # só limpa os diretórios
ant compile  # só compila
ant jar      # compila e empacota (depende de compile)
```

---

## 🔑 Conceitos fundamentais do ANT

| Conceito | Explicação | Exemplo |
|:---------|:-----------|:--------|
| **Target** | Uma tarefa específica que o ANT pode executar | `<target name="compile">` |
| **Task** | Uma ação atômica dentro de um target | `<javac>`, `<jar>`, `<copy>`, `<delete>` |
| **Property** | Uma variável que armazena um valor | `<property name="src.dir" value="src"/>` |
| **Dependency** | Um target que precisa ser executado antes de outro | `depends="clean, compile"` |
| **basedir** | Diretório base do projeto (onde está o `build.xml`) | `basedir="."` |

---

## 🆚 ANT vs Maven vs Gradle (Comparação visual)

| Característica | ANT | Maven | Gradle |
|:---------------|:----|:------|:-------|
| **Idade** | Mais velho (2000) | Médio (2004) | Mais novo (2012) |
| **Arquivo de configuração** | `build.xml` em XML | `pom.xml` em XML | `build.gradle` (DSL) |
| **Estilo** | **Imperativo** (você diz COMO fazer) | **Declarativo** (você diz O QUE quer) | Híbrido (ambos) |
| **Convenções** | Nenhuma (tudo manual) | Muitas (segue padrões) | Equilibrado |
| **Flexibilidade** | Máxima | Limitada | Alta |
| **Curva de aprendizado** | Fácil (XML simples) | Médio | Médio |
| **Gerenciamento de dependências** | ❌ Não tem (manual) | ✅ Automático | ✅ Automático |
| **Build incremental** | ❌ Não tem | ✅ Sim (parcial) | ✅ Sim (avançado) |
| **Plugins** | Sim (mas manuais) | Sim (ricos) | Sim (modernos) |

---

## 📜 Contexto histórico: Por que o ANT foi criado?

**Era pré-ANT (anos 1990):**
- Cada desenvolvedor tinha seus próprios scripts de build (`.bat` no Windows, `.sh` no Linux)
- Um projeto não compilava na máquina do colega
- Compilar no Windows vs Linux exigia scripts diferentes
- Não havia padronização

**Nascimento do ANT (2000):**
- Criado por James Duncan Davidson (criador do Tomcat)
- Inspirado no `make` do Unix, mas usando XML (multiplataforma)
- Eliminou scripts específicos de sistema operacional
- Rapidamente se tornou padrão de fato para projetos Java

**Era pós-ANT (2004 em diante):**
- Maven chegou com gerenciamento automático de dependências
- Gradle chegou com mais performance e flexibilidade
- ANT hoje é usado principalmente em projetos legados

---

## 🚀 Vantagens do ANT (por que alguém ainda usaria?)

| Vantagem | Explicação |
|:---------|:-----------|
| **Controle total** | Você decide **exatamente** cada passo do build |
| **Simplicidade** | XML básico, sem magia ou "convenções ocultas" |
| **Multiplataforma** | Funciona igual no Windows, Linux, Mac |
| **Extensível** | Pode criar suas próprias tarefas em Java |
| **Leve** | Sem camadas de abstração complexas |
| **Ivy integrado** | Pode adicionar gerenciamento de dependências com Apache Ivy |

---

## 💔 Desvantagens do ANT (por que foi substituído)

| Desvantagem | Explicação |
|:------------|:-----------|
| **XML verboso** | Um build simples já tem muitas linhas |
| **Sem convenções** | Todo projeto precisa configurar TUDO do zero |
| **Sem gerenciamento de dependências** | Você mesmo precisa baixar e gerenciar JARs |
| **Build não incremental** | Compila tudo toda vez (lento) |
| **Scripts repetitivos** | Muita cópia e cola entre projetos |
| **Dependências manuais** | Você declara `depends="..."` explicitamente |

---

## 📦 O que é Apache Ivy?

O **Apache Ivy** é um complemento para o ANT que **adiciona gerenciamento de dependências automático**, similar ao que o Maven tem.

Com Ivy, você declara as dependências em um arquivo `ivy.xml`:

```xml
<ivy-module version="2.0">
    <info organisation="meuprojeto" module="meuapp"/>
    <dependencies>
        <dependency org="junit" name="junit" rev="4.13.2"/>
        <dependency org="com.google.code.gson" name="gson" rev="2.9.0"/>
    </dependencies>
</ivy-module>
```

E no `build.xml`, você usa Ivy para baixar automaticamente essas bibliotecas.

> Ivy foi criado porque o ANT "puro" não resolvia um dos maiores problemas do desenvolvimento Java na época: gerenciar bibliotecas externas manualmente.

---

## 🧪 Exemplo prático: Criando um projeto com ANT

### Passo 1: Instalar o ANT
- Baixe do site do Apache ANT
- Descompacte em uma pasta (ex: `C:\ant`)
- Adicione a pasta `bin` ao PATH do sistema

### Passo 2: Estrutura do projeto
```
meuprojeto/
├── build.xml
└── src/
    └── meuapp/
        └── Main.java
```

### Passo 3: Conteúdo do `src/meuapp/Main.java`
```java
package meuapp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Olá, ANT!");
    }
}
```

### Passo 4: Conteúdo do `build.xml`
```xml
<project name="MeuApp" default="run" basedir=".">

    <property name="src.dir" value="src"/>
    <property name="build.dir" value="build"/>
    <property name="classes.dir" value="${build.dir}/classes"/>
    <property name="jar.dir" value="${build.dir}/jar"/>

    <target name="clean">
        <delete dir="${build.dir}"/>
    </target>

    <target name="compile" depends="clean">
        <mkdir dir="${classes.dir}"/>
        <javac srcdir="${src.dir}" destdir="${classes.dir}" includeantruntime="false"/>
    </target>

    <target name="jar" depends="compile">
        <mkdir dir="${jar.dir}"/>
        <jar destfile="${jar.dir}/MeuApp.jar" basedir="${classes.dir}">
            <manifest>
                <attribute name="Main-Class" value="meuapp.Main"/>
            </manifest>
        </jar>
    </target>

    <target name="run" depends="jar">
        <java jar="${jar.dir}/MeuApp.jar" fork="true"/>
    </target>

</project>
```

### Passo 5: Executar
```bash
ant run
```

Saída esperada:
```
Buildfile: build.xml

clean:
   [delete] Deleting directory build

compile:
    [mkdir] Created dir: build/classes
    [javac] Compiling 1 source file to build/classes

jar:
    [mkdir] Created dir: build/jar
      [jar] Building jar: build/jar/MeuApp.jar

run:
     [java] Olá, ANT!

BUILD SUCCESSFUL
```

---

## 📋 Principais tarefas (tasks) do ANT

| Task | O que faz | Exemplo |
|:-----|:----------|:--------|
| `<javac>` | Compila código Java | `<javac srcdir="src" destdir="build"/>` |
| `<jar>` | Cria arquivo JAR | `<jar destfile="app.jar" basedir="build"/>` |
| `<war>` | Cria arquivo WAR (web) | `<war destfile="app.war" webxml="web.xml"/>` |
| `<copy>` | Copia arquivos/pastas | `<copy file="src/config.txt" todir="build"/>` |
| `<delete>` | Remove arquivos/pastas | `<delete dir="build"/>` |
| `<mkdir>` | Cria diretório | `<mkdir dir="build/classes"/>` |
| `<move>` | Move/renomeia arquivos | `<move file="old.txt" tofile="new.txt"/>` |
| `<echo>` | Exibe mensagem no console | `<echo message="Olá!"/>` |
| `<junit>` | Executa testes JUnit | `<junit printsummary="yes">...` |
| `<exec>` | Executa comando do sistema | `<exec executable="git">...` |
| `<condition>` | Avalia condição | `<condition property="isWindows">...` |
| `<available>` | Verifica se arquivo existe | `<available file="lib.jar" property="lib.presente"/>` |

---

## 🎓 Resumo para quem está estudando

| Conceito | Explicação simples |
|:---------|:-------------------|
| **ANT** | Ferramenta que constrói software seguindo instruções passo a passo que você escreve |
| **build.xml** | O arquivo de receitas em XML |
| **Target** | Cada receita individual (ex: "compile", "test", "deploy") |
| **Task** | Cada instrução atômica dentro de uma receita (ex: `<javac>`, `<jar>`) |
| **depends** | Diz que um target precisa de outro para funcionar |
| **Property** | Variável que guarda valores (caminhos, versões) |
| **basedir** | Pasta raiz do projeto (onde tudo começa) |

---

## ✅ Quando usar ANT hoje?

| Cenário | Recomendação |
|:--------|:--------------|
| **Projeto legado** | Manter como está, mas considerar migração gradual |
| **Projeto muito customizado** | ANT ainda é bom se você precisa de controle total |
| **Projeto novo** | **Use Gradle ou Maven** (ANT é excessivamente manual) |
| **Ensino/aprendizado** | Ótimo para entender como builds funcionam "por baixo dos panos" |
| **Build com restrições** | Projetos que não podem usar Maven/Gradle por razões corporativas |

---

## 🚨 Cuidados ao começar

1. **ANT não baixa dependências sozinho** → Você precisa gerenciar JARs manualmente ou usar Ivy
2. **XML é verboso** → Projetos médios já têm `build.xml` com centenas de linhas
3. **Não tem build incremental** → Compila tudo toda vez (lento para projetos grandes)
4. **Dependências manuais** → Você define `depends="..."` manualmente em cada target
5. **Documentação antiga** → Muitos exemplos online usam versões desatualizadas

---

## 🔗 Links úteis

- [Site oficial do Apache ANT](https://ant.apache.org/)
- [Documentação de tasks do ANT](https://ant.apache.org/manual/tasksoverview.html)
- [Apache Ivy - gerenciamento de dependências](https://ant.apache.org/ivy/)

---
