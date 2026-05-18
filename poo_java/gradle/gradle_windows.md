## Usando Gradle no Windows sem IDE

Usar Gradle no Windows sem IDE é muito prático e é exatamente o que você faria em um servidor de CI/CD (como Jenkins ou GitHub Actions). A abordagem recomendada pela Gradle é usar o **Gradle Wrapper** (`gradlew.bat`), que permite executar builds em qualquer máquina sem precisar instalar o Gradle globalmente .

Aqui está o guia completo para Windows, do zero ao projeto rodando.

---

## 🔧 Pré-requisito: Java JDK

Antes de tudo, o Gradle precisa do Java JDK instalado. Verifique se você já tem:

```cmd
java -version
```

A saída esperada deve mostrar a versão do Java (recomenda-se JDK 17 ou superior) :

```
java version "17.0.9" 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 17.0.9+11-LTS-201)
```

**Se não tiver o Java instalado**, você pode instalar via `winget` (gerenciador de pacotes do Windows). Abra o **Prompt de Comando como Administrador** e execute :

```cmd
winget install --scope machine Oracle.JDK.17
```

> 💡 **Importante**: Após instalar o JDK, verifique se a variável `JAVA_HOME` está configurada corretamente nas variáveis de ambiente do sistema .

---

## 📦 Passo 2: Instalação do Gradle (para criar projetos)

Para criar projetos do zero, você vai precisar do Gradle instalado globalmente. Depois de criar o projeto, você usará apenas o Wrapper.

### Download e instalação manual

1. Acesse o site oficial: https://gradle.org
2. Clique em **"Install Gradle"** e depois em **"Binary-only"** para baixar o arquivo `.zip` 
3. Extraia o conteúdo para uma pasta, por exemplo: `C:\gradle\gradle-8.13` 

### Configurar variáveis de ambiente

1. Abra o menu **Variáveis de Ambiente**:
   - Pressione `Win` e digite "Variáveis de Ambiente"
   - Clique em **"Editar as variáveis de ambiente do sistema"**

2. Crie a variável `GRADLE_HOME`:
   - Clique em **"Variáveis do sistema"** → **"Novo"**
   - Nome: `GRADLE_HOME`
   - Valor: `C:\gradle\gradle-8.13` (o caminho onde você extraiu)

3. Adicione ao `Path`:
   - Localize a variável `Path` na lista
   - Clique em **"Editar"** → **"Novo"**
   - Adicione: `%GRADLE_HOME%\bin` 

### Verificar a instalação

Abra um **novo Prompt de Comando** (para carregar as novas variáveis) e execute:

```cmd
gradle -v
```

Você deve ver informações sobre a versão do Gradle, JVM e sistema operacional.

### (Opcional) Configurar cache em outra unidade

O Gradle armazena dependências em `C:\Users\SeuUsuario\.gradle`. Para evitar ocupar espaço no C:, crie a variável :

- Nome: `GRADLE_USER_HOME`
- Valor: `D:\.gradle` (ou o caminho desejado)

---

## 🚀 Passo 3: Criando um Projeto do Zero

Agora vamos criar um projeto Java usando apenas o terminal.

```cmd
# Crie uma pasta para seus projetos
mkdir C:\projetos-gradle
cd C:\projetos-gradle

# Inicialize um novo projeto Java
gradle init
```

O Gradle fará algumas perguntas :

```
Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic): 2

Select implementation language:
  1: Java
  2: Kotlin
  3: Groovy
  4: Scala
Enter selection (default: Java): 1

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy): 1

Select test framework:
  1: JUnit Jupiter
  2: JUnit 4
  3: TestNG
Enter selection (default: JUnit Jupiter): 1

Project name (default: demo): meu-app-gradle
Source package (default: demo): com.windows.app
```

**Estrutura gerada** :

```
meu-app-gradle/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew.bat              # ⭐ Wrapper para Windows
├── gradlew                  # Wrapper para Linux/Mac
├── settings.gradle
├── build.gradle
└── app/
    ├── build.gradle
    └── src/
        ├── main/java/com/windows/app/
        │   └── App.java
        └── test/java/com/windows/app/
            └── AppTest.java
```

---

## 📝 Passo 4: Editando o Código sem IDE

Use o **Notepad** ou qualquer editor de texto:

```cmd
notepad app\src\main\java\com\windows\app\App.java
```

Substitua o conteúdo por:

```java
package com.windows.app;

public class App {
    public static void main(String[] args) {
        System.out.println("🪟 Executando Gradle no Windows!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Gradle Wrapper é incrível!");
    }
}
```

Salve o arquivo (`Ctrl+S`) e feche o Notepad.

---

## 🔧 Passo 5: Comandos Essenciais (Usando o Wrapper)

A partir de agora, **use `gradlew.bat`** em vez de `gradle`. Isso garante que todos no seu time usem a mesma versão .

> ⚠️ **No Windows CMD**, use `gradlew.bat` (com a extensão .bat) :
> ```cmd
> gradlew.bat build    # Correto
> gradlew build        # Também funciona no CMD (sem o .bat)
> ```

### Comandos fundamentais:

```cmd
# 1. Limpar builds anteriores
gradlew.bat clean

# 2. Compilar o código
gradlew.bat compileJava

# 3. Executar os testes
gradlew.bat test

# 4. Build completo (compila, testa e empacota)
gradlew.bat build

# 5. Executar o programa
gradlew.bat run

# 6. Ver todas as tarefas disponíveis
gradlew.bat tasks

# 7. Ver árvore de dependências
gradlew.bat dependencies
```

Após executar `gradlew.bat build`, o JAR será gerado em `app\build\libs\`.

> 💡 **No PowerShell**, o comando é diferente: use `./gradlew` :
> ```powershell
> ./gradlew build
> ```

---

## 📦 Passo 6: Adicionando uma Dependência

Vamos adicionar a biblioteca **Gson** do Google. Edite o arquivo `app\build.gradle`:

```cmd
notepad app\build.gradle
```

Localize a seção `dependencies` e adicione o Gson:

```gradle
dependencies {
    // Dependência existente
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
    
    // NOVA DEPENDÊNCIA: Gson para manipulação de JSON
    implementation 'com.google.code.gson:gson:2.10.1'
    
    implementation 'com.google.guava:guava:32.1.3-jre'
}
```

Agora edite a classe principal para usar o Gson:

```cmd
notepad app\src\main\java\com\windows\app\App.java
```

```java
package com.windows.app;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        // Criando um objeto para converter em JSON
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", "Windows User");
        dados.put("ferramenta", "Gradle");
        dados.put("versao", "8.13");
        
        String json = gson.toJson(dados);
        System.out.println("📄 Objeto convertido para JSON:");
        System.out.println(json);
        
        // Convertendo JSON de volta para objeto
        Map<String, Object> decodificado = gson.fromJson(json, Map.class);
        System.out.println("\n🔄 JSON convertido de volta:");
        System.out.println("Nome: " + decodificado.get("nome"));
    }
}
```

Execute o build e o programa:

```cmd
gradlew.bat clean build run
```

O Gradle baixará automaticamente o Gson do Maven Central e armazenará em `%USERPROFILE%\.gradle\caches\` .

---

## 🎯 O que é o Gradle Wrapper e Por Que Usar?

Você deve ter notado os arquivos `gradlew.bat`, `gradlew` e a pasta `gradle/wrapper/`. Esse é o **Gradle Wrapper**, e é a forma **oficial e recomendada** de usar Gradle em qualquer projeto .

### Vantagens do Wrapper:

| Situação | Sem Wrapper | Com Wrapper (`gradlew.bat`) |
|----------|-------------|----------------------------|
| Time com versões diferentes | Conflitos e bugs | Todos usam a mesma versão |
| Servidor CI/CD (Jenkins, GitHub Actions) | Precisa instalar Gradle manualmente | Funciona imediatamente |
| Projeto open source | Usuários precisam instalar Gradle | Basta clonar e rodar `gradlew.bat` |
| Máquina nova | Instalação adicional necessária | Zero configuração |

### Gerando o Wrapper em um projeto existente

Se você tem um projeto sem o Wrapper, execute:

```cmd
gradle wrapper
```

Isso criará todos os arquivos necessários .

### O que versionar no Git?

```cmd
# ✅ Devem estar no repositório
gradlew.bat
gradlew
gradle/wrapper/gradle-wrapper.jar
gradle/wrapper/gradle-wrapper.properties

# ❌ Não devem estar no repositório
.gradle/
build/
```

---

## 🛠️ Comandos Avançados e Dicas

### Build com mais informações (debug)
```cmd
gradlew.bat build --info
```

### Build com stacktrace completo (para erros)
```cmd
gradlew.bat build --stacktrace
```

### Forçar atualização de dependências
```cmd
gradlew.bat build --refresh-dependencies
```

### Pular testes (use com cautela)
```cmd
gradlew.bat build -x test
```

### Visualizar dependências de um módulo específico
```cmd
gradlew.bat app:dependencies
```

### Acelerar builds
Crie ou edite o arquivo `%USERPROFILE%\.gradle\gradle.properties`:

```properties
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.caching=true
```

---

## 🐚 Script de Automação (.bat)

Crie um script `build.bat` na raiz do projeto:

```batch
@echo off
echo 🔨 Iniciando build do projeto...
echo -------------------------------

call gradlew.bat clean test

if %errorlevel% equ 0 (
    echo ✅ Testes passaram! Empacotando...
    call gradlew.bat build
    echo 📦 Build concluído com sucesso!
    echo 📁 JAR gerado em: app\build\libs\
) else (
    echo ❌ Falha nos testes. Corrija antes de empacotar.
    exit /b 1
)
```

Execute com:

```cmd
build.bat
```

---

## ⚠️ Solução de Problemas Comuns

### 1. `'gradle' não é reconhecido como um comando interno`

- **Causa**: O PATH não foi configurado corretamente ou o terminal não foi reiniciado.
- **Solução**: Feche e reabra o Prompt de Comando. Verifique se `%GRADLE_HOME%\bin` está no PATH .

### 2. `'java' não é reconhecido como um comando interno`

- **Causa**: JDK não instalado ou JAVA_HOME não configurado.
- **Solução**: Instale o JDK e configure a variável `JAVA_HOME` .

### 3. Erro de permissão ao executar gradlew.bat

- **Causa**: O PowerShell pode bloquear a execução de scripts.
- **Solução**: Use o Prompt de Comando (CMD) em vez do PowerShell, ou execute o PowerShell como Administrador e use `Set-ExecutionPolicy RemoteSigned` .

### 4. Conexão bloqueada por Firewall/Antivírus

- **Causa**: O Windows Firewall pode bloquear o Java/Gradle de baixar dependências.
- **Solução**: Adicione uma exceção no firewall para `java.exe`.

---

## 📋 Resumo Rápido dos Comandos

| Comando | O que faz |
|---------|-----------|
| `gradlew.bat clean` | Remove a pasta `build/` |
| `gradlew.bat compileJava` | Compila apenas o código fonte |
| `gradlew.bat test` | Executa os testes |
| `gradlew.bat build` | Compila, testa e empacota (JAR) |
| `gradlew.bat run` | Executa a classe principal |
| `gradlew.bat dependencies` | Mostra árvore de dependências |
| `gradlew.bat tasks` | Lista todas as tarefas disponíveis |

---
