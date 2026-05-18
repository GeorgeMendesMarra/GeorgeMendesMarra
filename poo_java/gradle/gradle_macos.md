## 🍎 Usando Gradle no macOS sem IDE

## Passo 1: Pré-requisitos

O Gradle requer Java JDK 8 ou superior . Verifique se você tem o Java instalado:

```bash
java -version
javac -version
```

Se não tiver, instale via Homebrew:

```bash
brew install openjdk@17
```

Configure o `JAVA_HOME` no seu `~/.zshrc` (shell padrão do macOS):

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.zshrc
source ~/.zshrc
```


---

## Passo 2: Instalação do Gradle

### 🏆 Opção 1: Homebrew (RECOMENDADO)

Esta é a forma mais simples - o Homebrew é o "gerente de pacotes que faltava no macOS" :

```bash
# Atualize o Homebrew (opcional, mas recomendado)
brew update

# Instale o Gradle
brew install gradle
```

**Verifique a instalação**:

```bash
gradle -v
```

Saída esperada:
```
------------------------------------------------------------
Gradle 9.0
------------------------------------------------------------

Build time:   2025-07-31 14:54:49 UTC
Revision:     ...
Kotlin:       2.0.21
Groovy:       3.0.22
Ant:          Apache Ant(TM) version 1.10.14
JVM:          17.0.9 (Homebrew 17.0.9+0)
OS:           Mac OS X 14.5 aarch64
```

> ✅ **Vantagem**: O Homebrew configura automaticamente o PATH. Nenhuma configuração manual de variáveis de ambiente é necessária .

---

### 📦 Opção 2: Instalação Manual

Se preferir controle total sobre a versão ou não puder usar o Homebrew:

1. **Baixe o Gradle** do site oficial :

```bash
cd ~/Downloads
curl -O https://services.gradle.org/distributions/gradle-9.0-bin.zip
```

2. **Extraia para `/opt/gradle`**:

```bash
sudo unzip -d /opt/gradle gradle-9.0-bin.zip
```

3. **Configure as variáveis de ambiente** no `~/.zshrc` :

```bash
echo 'export GRADLE_HOME=/opt/gradle/gradle-9.0' >> ~/.zshrc
echo 'export PATH=$PATH:$GRADLE_HOME/bin' >> ~/.zshrc
source ~/.zshrc
```


---

## 🚀 Passo 3: Criando um Projeto com `gradle init`

Agora vamos criar um projeto Java do zero usando o Terminal:

```bash
# Crie uma pasta para seus projetos
mkdir -p ~/projetos-gradle
cd ~/projetos-gradle

# Inicialize um novo projeto Java
gradle init --type java-application --dsl kotlin
```

Durante a execução, o Gradle fará algumas perguntas :

```
Select build script DSL:
  1: Kotlin
  2: Groovy
Enter selection (default: Kotlin): [pressione Enter]

Select test framework:
  1: JUnit Jupiter
  2: JUnit 4
  3: TestNG
Enter selection (default: JUnit Jupiter): [pressione Enter]

Project name (default: demo): meu-app-gradle
Source package (default: demo): com.mac.app
```

**Estrutura gerada**:

```
meu-app-gradle/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar      # O coração do Wrapper
│       └── gradle-wrapper.properties
├── gradlew                          # Script para macOS/Linux
├── gradlew.bat                      # Script para Windows
├── settings.gradle.kts              # Configurações do projeto
├── build.gradle.kts                 # Script de build principal
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   └── java/com/mac/app/
│       │       └── App.java
│       └── test/
│           └── java/com/mac/app/
│               └── AppTest.java
```


---

## 🔧 Passo 4: Editando o Código sem IDE

Use o **nano** (mais simples) ou qualquer editor de texto:

```bash
nano app/src/main/java/com/mac/app/App.java
```

Substitua o conteúdo por:

```java
package com.mac.app;

public class App {
    public static void main(String[] args) {
        System.out.println("🍎 Executando Gradle no macOS!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Gradle é mais rápido que Maven!");
    }
}
```

Salve com `Ctrl+O`, `Enter`, e saia com `Ctrl+X`.


---

## 🛠️ Passo 5: Comandos Essenciais do Gradle

Aqui está o coração do workflow - todos os comandos que você precisará:

### 1. Limpar arquivos de build
```bash
./gradlew clean
```
Remove a pasta `build/` com todos os artefatos gerados.

### 2. Compilar o código
```bash
./gradlew compileJava
```
Compila apenas o código Java, sem executar testes.

### 3. Executar os testes
```bash
./gradlew test
```
Executa todos os testes unitários.

### 4. Build completo (compilar + testar + empacotar)
```bash
./gradlew build
```
É o comando mais usado - faz tudo de uma vez !

### 5. Executar o programa
```bash
./gradlew run
```
Executa a classe principal configurada no `build.gradle.kts`.

### 6. Verificar dependências
```bash
./gradlew dependencies
```

---

## 📦 Passo 6: Adicionando uma Dependência

Vamos adicionar a biblioteca **Gson** (Google JSON). Edite o arquivo `app/build.gradle.kts` :

```bash
nano app/build.gradle.kts
```

Localize a seção `dependencies` e adicione o Gson:

```kotlin
dependencies {
    // Dependência existente (JUnit)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // NOVA DEPENDÊNCIA: Gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation(project(":lib"))
}
```

Agora edite a classe principal para usar o Gson:

```bash
nano app/src/main/java/com/mac/app/App.java
```

```java
package com.mac.app;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        Pessoa pessoa = new Pessoa("João Silva", 30, "joao@email.com");
        String json = gson.toJson(pessoa);
        
        System.out.println("Objeto convertido para JSON:");
        System.out.println(json);
        
        // Convertendo de volta
        Pessoa decodificada = gson.fromJson(json, Pessoa.class);
        System.out.println("\nJSON convertido de volta para objeto:");
        System.out.println(decodificada);
    }
    
    static class Pessoa {
        String nome;
        int idade;
        String email;
        
        public Pessoa(String nome, int idade, String email) {
            this.nome = nome;
            this.idade = idade;
            this.email = email;
        }
        
        @Override
        public String toString() {
            return "Pessoa{nome='" + nome + "', idade=" + idade + ", email='" + email + "'}";
        }
    }
}
```

Execute o build:

```bash
./gradlew build run
```

O Gradle baixará automaticamente o Gson do Maven Central para o diretório `~/.gradle/caches/` .


---

## 🎯 O Que é o Gradle Wrapper (e Por Que Usar)?

Você deve ter notado que usamos `./gradlew` em vez de `gradle`. Isso é o **Gradle Wrapper**, e é a **forma recomendada pela Gradle, Inc.** .

### O que é?

O Wrapper é um conjunto de scripts e um JAR que:
- **Garante a versão correta** do Gradle para aquele projeto
- **Não exige que você instale o Gradle globalmente** 
- **Baixa automaticamente** a versão correta na primeira execução

### Por que usar?

| Cenário | Sem Wrapper (`gradle`) | Com Wrapper (`./gradlew`) |
|---------|------------------------|---------------------------|
| Time com versões diferentes do Gradle | Conflitos e bugs | Todos usam a mesma versão |
| Servidor CI/CD (Jenkins, GitHub Actions) | Precisa instalar Gradle manualmente | Funciona imediatamente |
| Projeto open source | Usuários precisam instalar Gradle | Basta clonar e rodar `./gradlew` |

### Arquivos do Wrapper no seu projeto

```
meu-projeto/
├── gradlew              # Script executável (macOS/Linux)
├── gradlew.bat          # Script para Windows
└── gradle/
    └── wrapper/
        ├── gradle-wrapper.jar        # O coração do Wrapper
        └── gradle-wrapper.properties # Configuração da versão
```

> ⚠️ **Importante**: Esses arquivos DEVEM ser versionados no Git! Sem o `gradle-wrapper.jar`, o comando `./gradlew` falhará com o erro "Could not find or load main class" .


---

## 📊 Gradle vs Maven: Comparativo Rápido

Já que você usava Maven, vale entender as diferenças :

| Característica | Maven | Gradle |
|----------------|-------|--------|
| **Arquivo de configuração** | `pom.xml` (XML) | `build.gradle.kts` (Kotlin) |
| **Repositório de dependências** | `~/.m2/repository/` | `~/.gradle/caches/` |
| **Comando para build** | `mvn package` | `./gradlew build` |
| **Flexibilidade** | Baixa (padrões rígidos) | Alta (customizável) |
| **Velocidade de build** | Normal | Mais rápida (incremental) |
| **Curva de aprendizado** | Mais suave | Um pouco mais íngreme |

### Mapeamento rápido de comandos

| Ação | Maven | Gradle (com Wrapper) |
|------|-------|----------------------|
| Limpar | `mvn clean` | `./gradlew clean` |
| Compilar | `mvn compile` | `./gradlew compileJava` |
| Testar | `mvn test` | `./gradlew test` |
| Empacotar | `mvn package` | `./gradlew build` |
| Instalar local | `mvn install` | `./gradlew publishToMavenLocal` |
| Executar app | `mvn exec:java -Dexec.mainClass="..."` | `./gradlew run` |

### Convertendo um projeto Maven para Gradle

Se você tem um projeto Maven existente, pode convertê-lo facilmente :

```bash
cd ~/seu-projeto-maven
gradle init
```

O Gradle lerá o `pom.xml` e gerará os arquivos `build.gradle.kts` equivalentes!


---

## 🐚 Aliases para acelerar o workflow

Adicione ao seu `~/.zshrc`:

```bash
# Aliases Gradle
alias gw='./gradlew'
alias gwclean='./gradlew clean'
alias gwbuild='./gradlew build'
alias gwt='./gradlew test'
alias gwrun='./gradlew run'
```

Recarregue: `source ~/.zshrc`

Agora você pode usar comandos curtos:
```bash
gw build
gw run
```


---

## ⚠️ Solução de Problemas Comuns no macOS

### 1. "Permission denied" ao executar `./gradlew`
```bash
chmod +x gradlew
```

### 2. "Could not find or load main class" ao usar Wrapper
- **Causa**: O arquivo `gradle/wrapper/gradle-wrapper.jar` está faltando 
- **Solução**: Certifique-se de que todos os arquivos do Wrapper estão no Git. Ou gere novamente com `gradle wrapper`

### 3. Unsupported Java version
- **Causa**: JDK muito antigo ou muito novo
- **Solução**: Verifique a compatibilidade no site do Gradle e ajuste o `JAVA_HOME`

### 4. Dependências lentas para baixar (usuários no Brasil/China)
Configure um espelho (mirror) do Maven Central. Crie `~/.gradle/init.gradle` :

```gradle
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/public' }
        mavenCentral()
    }
}
```


---

## 📋 Resumo Rápido dos Comandos

| Comando | O que faz |
|---------|-----------|
| `./gradlew clean` | Remove a pasta `build/` |
| `./gradlew compileJava` | Compila apenas o código fonte |
| `./gradlew test` | Executa os testes |
| `./gradlew build` | Compila, testa e empacota (JAR) |
| `./gradlew run` | Executa a classe principal |
| `./gradlew dependencies` | Mostra árvore de dependências |
| `./gradlew tasks` | Lista todas as tarefas disponíveis |

---

Agora você tem um ambiente Gradle completo no macOS, funcionando 100% via Terminal! A principal diferença do seu fluxo com Maven será usar `./gradlew build` em vez de `mvn package`, e toda a configuração ficará em arquivos Kotlin/DSL (`build.gradle.kts`) em vez de XML.

O **Gradle Wrapper** (`./gradlew`) é a chave - ele elimina a necessidade de instalar o Gradle globalmente e garante builds consistentes em qualquer máquina.
