## 🐧 Usando Gradle no Linux sem IDE

Usar Gradle no Linux sem IDE é uma prática essencial para automação em servidores e para quem prefere um workflow enxuto no terminal. A principal recomendação da Gradle Inc. é utilizar o **Gradle Wrapper** (`./gradlew`), que permite executar builds em qualquer máquina **sem precisar instalar o Gradle globalmente**.

Aqui está o guia completo, do zero ao projeto rodando.

---

## Passo 1: Instalação do Java (Pré-requisito)

Antes de qualquer coisa, o Gradle precisa do Java JDK instalado.

```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk -y

# Fedora/RHEL
sudo dnf install java-17-openjdk -y
```

Verifique a instalação:

```bash
java -version
```

A saída deve mostrar a versão do Java (11 ou superior é recomendado para versões recentes do Gradle).

---

## Passo 2: Instalação do Gradle (Sistema)

Para criar projetos do zero, você vai precisar do Gradle instalado globalmente. Depois de criar o projeto, você usará apenas o Wrapper.

### Opção 1: Usando Snap (recomendado para Ubuntu)

```bash
sudo snap install gradle --classic
```

### Opção 2: Usando SDKMAN (recomendado para controle de versões)

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle
```

### Opção 3: Instalação manual

```bash
# Baixe a versão desejada
wget https://services.gradle.org/distributions/gradle-8.7-bin.zip

# Extraia para /opt/gradle
sudo unzip -d /opt/gradle gradle-8.7-bin.zip

# Configure o PATH
echo 'export PATH=$PATH:/opt/gradle/gradle-8.7/bin' >> ~/.bashrc
source ~/.bashrc
```

Verifique a instalação:

```bash
gradle -v
```

---

## 🚀 Passo 3: Criando um Novo Projeto

Com o Gradle instalado, vamos criar um projeto Java do zero usando o terminal.

```bash
# Crie uma pasta para seus projetos
mkdir -p ~/projetos-gradle
cd ~/projetos-gradle

# Inicialize um novo projeto Java
gradle init --type java-application --dsl groovy
```

O comando `gradle init` fará algumas perguntas:
- **Select build script DSL**: pressione `Enter` (Groovy é o padrão)
- **Select test framework**: pressione `Enter` (JUnit Jupiter é o padrão)
- **Project name**: `meu-app-gradle` (ou o nome que desejar)
- **Source package**: `com.linux.app`

**Estrutura gerada:**

```
meu-app-gradle/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar      # O coração do Wrapper
│       └── gradle-wrapper.properties
├── gradlew                          # Script executável para Linux/macOS
├── gradlew.bat                      # Script para Windows
├── settings.gradle                  # Configurações do projeto
├── build.gradle                     # Script de build principal
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/java/com/linux/app/
│       │   └── App.java
│       └── test/java/com/linux/app/
│           └── AppTest.java
```

---

## 📝 Passo 4: Editando o Código no Terminal

Use o **nano** (mais amigável) ou **vim** para editar:

```bash
nano app/src/main/java/com/linux/app/App.java
```

Substitua o conteúdo por:

```java
package com.linux.app;

public class App {
    public static void main(String[] args) {
        System.out.println("🐧 Executando Gradle no Linux!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Gradle Wrapper é incrível!");
    }
}
```

Salve com `Ctrl+O`, `Enter`, e saia com `Ctrl+X`.

---

## 🔧 Passo 5: Comandos Essenciais (Usando o Wrapper)

A partir de agora, **sempre use `./gradlew`** em vez de `gradle`. Isso garante que todos no seu time usem a mesma versão do Gradle.

> ⚠️ **Importante**: No Linux, você precisa do prefixo `./` para executar o script do diretório atual:
> ```bash
> ./gradlew build   # Correto
> gradlew build     # Erro: comando não encontrado
> ```

### Comandos fundamentais:

```bash
# 1. Dar permissão de execução ao wrapper (apenas na primeira vez)
chmod +x gradlew

# 2. Limpar builds anteriores
./gradlew clean

# 3. Compilar o código
./gradlew compileJava

# 4. Executar os testes
./gradlew test

# 5. Build completo (compila, testa e empacota)
./gradlew build

# 6. Executar o programa
./gradlew run

# 7. Ver todas as tarefas disponíveis
./gradlew tasks

# 8. Ver árvore de dependências
./gradlew dependencies
```

Após executar `./gradlew build`, o JAR será gerado em `app/build/libs/`.

---

## 📦 Passo 6: Adicionando uma Dependência

Vamos adicionar a biblioteca **Gson** do Google. Edite o arquivo `app/build.gradle`:

```bash
nano app/build.gradle
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

```bash
nano app/src/main/java/com/linux/app/App.java
```

```java
package com.linux.app;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        // Criando um objeto para converter em JSON
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", "Linux User");
        dados.put("ferramenta", "Gradle");
        dados.put("versao", "8.7");
        
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

```bash
./gradlew clean build run
```

O Gradle baixará automaticamente o Gson do Maven Central e armazenará em `~/.gradle/caches/`.

---

## 🎯 O que é o Gradle Wrapper e Por Que Usar?

Você deve ter notado os arquivos `gradlew`, `gradlew.bat` e a pasta `gradle/wrapper/`. Esse é o **Gradle Wrapper**, e é a forma **oficial e recomendada** de usar Gradle em qualquer projeto.

### Vantagens do Wrapper:

| Situação | Sem Wrapper | Com Wrapper (`./gradlew`) |
|----------|-------------|---------------------------|
| Time com versões diferentes | Conflitos e bugs | Todos usam a mesma versão |
| Servidor CI/CD (Jenkins, GitHub Actions) | Precisa instalar Gradle manualmente | Funciona imediatamente |
| Projeto open source | Usuários precisam instalar Gradle | Basta clonar e rodar `./gradlew` |
| Máquina nova | Instalação adicional necessária | Zero configuração |

### O que versionar no Git?

```bash
# ✅ Devem estar no repositório
gradlew
gradlew.bat
gradle/wrapper/gradle-wrapper.jar
gradle/wrapper/gradle-wrapper.properties

# ❌ Não devem estar no repositório
.gradle/
build/
```

---

## 🛠️ Comandos Avançados e Dicas

### Build com mais informações (debug)
```bash
./gradlew build --info
```

### Build com stacktrace completo (para erros)
```bash
./gradlew build --stacktrace
```

### Forçar atualização de dependências
```bash
./gradlew build --refresh-dependencies
```

### Pular testes (use com cautela)
```bash
./gradlew build -x test
```

### Executar apenas uma tarefa específica
```bash
./gradlew compileJava
```

### Visualizar dependências do projeto
```bash
./gradlew dependencies
```

### Acelerar builds com Daemon
Crie ou edite o arquivo `~/.gradle/gradle.properties`:

```properties
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.caching=true
```

---

## 🐚 Script de Automação

Crie um script `build.sh` na raiz do projeto:

```bash
#!/bin/bash
# Script para build automatizado com Gradle

echo "🔨 Iniciando build do projeto..."
echo "-------------------------------"

./gradlew clean test

if [ $? -eq 0 ]; then
    echo "✅ Testes passaram! Empacotando..."
    ./gradlew build
    echo "📦 Build concluído com sucesso!"
    echo "📁 JAR gerado em: app/build/libs/"
else
    echo "❌ Falha nos testes. Corrija antes de empacotar."
    exit 1
fi
```

Torne executável e use:

```bash
chmod +x build.sh
./build.sh
```

---

## ⚠️ Solução de Problemas Comuns

### 1. `./gradlew: Permission denied`

```bash
chmod +x gradlew
```

### 2. `./gradlew: No such file or directory`

Você não está no diretório raiz do projeto ou o arquivo `gradlew` não existe. Para gerar o Wrapper em um projeto existente:

```bash
gradle wrapper
```

### 3. `Could not find or load main class`

O arquivo `gradle/wrapper/gradle-wrapper.jar` está faltando no repositório. Isso acontece quando o arquivo não foi versionado. Recrie-o com:

```bash
gradle wrapper
```

### 4. Erro de conexão ao baixar dependências

Verifique sua conexão com a internet. Se estiver atrás de proxy, configure no `~/.gradle/gradle.properties`:

```properties
systemProp.http.proxyHost=proxy.empresa.com
systemProp.http.proxyPort=8080
systemProp.https.proxyHost=proxy.empresa.com
systemProp.https.proxyPort=8080
```

---

## 📋 Resumo Rápido

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

Agora você tem um ambiente Gradle completo no Linux, funcionando 100% via terminal! A chave é sempre usar o **Wrapper** (`./gradlew`) em vez do comando `gradle` global, garantindo builds consistentes em qualquer máquina. Esse workflow é idêntico ao usado em servidores CI/CD como Jenkins e GitHub Actions.
