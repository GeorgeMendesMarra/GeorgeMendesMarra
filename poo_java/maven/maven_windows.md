## 🪟 Passo 1: Instalação do Maven no Windows

### Pré-requisito: Java JDK
Antes de tudo, você precisa ter o Java JDK instalado. Verifique abrindo o **Prompt de Comando** (cmd) ou **PowerShell** e digitando:
```cmd
java -version
```

Se o comando não for reconhecido, você precisa instalar o JDK primeiro (recomendo o OpenJDK ou Oracle JDK) .

### Baixando e Instalando o Maven

1.  Acesse o site oficial do Maven: `https://maven.apache.org/download.cgi` 
2.  Baixe o arquivo **Binary zip archive** (ex: `apache-maven-3.9.9-bin.zip`)
3.  Extraia o conteúdo do zip para uma pasta de sua escolha. O local recomendado é `C:\Program Files\Apache\maven` ou `C:\maven` 
4.  O caminho final será algo como `C:\maven\apache-maven-3.9.9`

### Configurando as Variáveis de Ambiente

As variáveis de ambiente permitem que você execute o Maven de qualquer lugar no terminal .

**Passo a passo para Windows 10/11:**
1.  Abra o **Painel de Controle** → **Sistema** → **Configurações avançadas do sistema**
2.  Clique em **Variáveis de Ambiente...**
3.  Em "Variáveis do sistema", clique em **Novo** e adicione:
    - Nome da variável: `MAVEN_HOME`
    - Valor da variável: `C:\maven\apache-maven-3.9.9` (ajuste para o caminho onde você extraiu)
4.  Localize a variável **Path** na lista, selecione e clique em **Editar**
5.  Clique em **Novo** e adicione: `%MAVEN_HOME%\bin`
6.  Clique em **OK** em todas as janelas para salvar 

### Verificando a Instalação

Abra uma nova janela do **Prompt de Comando** ou **PowerShell** (é importante abrir uma nova para carregar as variáveis) e digite:
```cmd
mvn --version
```

Se tudo estiver correto, você verá informações sobre a versão do Maven, Java e o sistema operacional . A saída será semelhante a:
```
Apache Maven 3.9.9
Maven home: C:\maven\apache-maven-3.9.9
Java version: 17.0.9, vendor: Oracle Corporation
OS name: "windows 11", version: "10.0", arch: "amd64"
```

---

## 📁 Passo 2: Estrutura de Diretórios do Maven no Windows

O Maven cria algumas pastas importantes no seu usuário do Windows:
- **Repositório local**: `C:\Users\SeuUsuario\.m2\repository` - onde todas as dependências baixadas ficam armazenadas 
- **Arquivo de configuração do usuário**: `C:\Users\SeuUsuario\.m2\settings.xml` (opcional, pode ser copiado da pasta `conf` do Maven)

---

## 🚀 Passo 3: Criando um Projeto do Zero

Vamos criar um projeto Java simples usando o terminal.

```cmd
# Crie uma pasta para seus projetos
mkdir C:\projetos-maven
cd C:\projetos-maven

# Gere um novo projeto usando o archetype quickstart
mvn archetype:generate ^
  -DgroupId=com.windows.app ^
  -DartifactId=meu-primeiro-projeto ^
  -DarchetypeArtifactId=maven-archetype-quickstart ^
  -DarchetypeVersion=1.4 ^
  -DinteractiveMode=false
```

**Explicação dos parâmetros** :
- `archetype:generate`: comando para gerar a estrutura do projeto
- `-DgroupId`: identificador único do seu projeto/organização
- `-DartifactId`: nome do seu projeto (será o nome da pasta)
- `-DarchetypeArtifactId`: modelo (archetype) a ser usado (`quickstart` cria um projeto Java simples)
- `-DinteractiveMode=false`: executa sem perguntas adicionais

Após a execução, entre no projeto:
```cmd
cd meu-primeiro-projeto
```

A estrutura gerada será:
```
meu-primeiro-projeto/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── windows/
    │               └── app/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── windows/
                    └── app/
                        └── AppTest.java
```

---

## 📝 Passo 4: Editando o Código sem IDE

No Windows sem IDE, você pode usar editores de texto como **Notepad**, **Notepad++** ou até mesmo o **PowerShell** com o comando `notepad`:

```cmd
notepad src\main\java\com\windows\app\App.java
```

Substitua o conteúdo da classe `App.java` por:
```java
package com.windows.app;

public class App {
    public static void main(String[] args) {
        System.out.println("🚀 Executando Maven no Windows!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
    }
}
```

Salve o arquivo (`Ctrl+S`) e feche o Notepad.

---

## 🔧 Passo 5: Comandos Essenciais do Maven

Execute os comandos na pasta do projeto (`C:\projetos-maven\meu-primeiro-projeto`) :

### 1. Limpar builds anteriores
```cmd
mvn clean
```
Remove a pasta `target/` com todos os arquivos gerados anteriormente.

### 2. Compilar o código
```cmd
mvn compile
```
Compila o código Java e gera os arquivos `.class` em `target/classes/`.

### 3. Executar os testes
```cmd
mvn test
```
Compila e executa os testes unitários localizados em `src/test/java/`.

### 4. Empacotar o projeto
```cmd
mvn package
```
Cria um arquivo JAR em `target\meu-primeiro-projeto-1.0-SNAPSHOT.jar`.

### 5. Instalar no repositório local
```cmd
mvn install
```
Copia o JAR para `C:\Users\SeuUsuario\.m2\repository\` para que outros projetos possam utilizá-lo como dependência.

### 6. Executar o programa
Para executar diretamente sem precisar do JAR:
```cmd
mvn exec:java -Dexec.mainClass="com.windows.app.App"
```

---

## 📦 Passo 6: Adicionando uma Dependência Externa

Vamos adicionar a biblioteca **Gson** (Google JSON) para demonstrar como o Maven gerencia dependências.

1.  Abra o arquivo `pom.xml`:
```cmd
notepad pom.xml
```

2.  Dentro da tag `<dependencies>`, adicione:
```xml
<dependencies>
  <!-- Dependência JUnit (já existente) -->
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.3</version>
    <scope>test</scope>
  </dependency>
  
  <!-- NOVA DEPENDÊNCIA: Gson do Google -->
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
  </dependency>
</dependencies>
```

3.  Salve e feche o arquivo.

4.  Agora edite a classe `App.java` novamente:
```cmd
notepad src\main\java\com\windows\app\App.java
```

5.  Atualize para usar o Gson:
```java
package com.windows.app;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        String mensagem = "Maven no Windows funcionando!";
        String json = gson.toJson(mensagem);
        
        System.out.println("Original: " + mensagem);
        System.out.println("JSON: " + json);
        
        // Convertendo de volta
        String decodificado = gson.fromJson(json, String.class);
        System.out.println("Decodificado: " + decodificado);
    }
}
```

6.  Execute o programa. O Maven baixará automaticamente o Gson do repositório central:
```cmd
mvn clean compile exec:java -Dexec.mainClass="com.windows.app.App"
```

**O que acontece nos bastidores**: O Maven detecta a nova dependência, baixa o arquivo `gson-2.10.1.jar` e o armazena em `C:\Users\SeuUsuario\.m2\repository\com\google\code\gson\gson\2.10.1\` para uso futuro.

---

## 🎯 Passo 7: Criando um Executável (Fat JAR)

Por padrão, o Maven cria um JAR simples que não inclui as dependências. Para criar um JAR executável independente, adicione o **Maven Shade Plugin** ao `pom.xml`:

```cmd
notepad pom.xml
```

Dentro da tag `<build><plugins>`, adicione:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.5.1</version>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <transformers>
              <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                <mainClass>com.windows.app.App</mainClass>
              </transformer>
            </transformers>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

Agora execute:
```cmd
mvn clean package
```

E execute o JAR gerado:
```cmd
java -jar target\meu-primeiro-projeto-1.0-SNAPSHOT.jar
```

---

## 🛠️ Comandos Avançados e Dicas para Windows

### Ver árvore de dependências (útil para identificar conflitos)
```cmd
mvn dependency:tree
```

### Baixar os fontes das dependências (para debugging)
```cmd
mvn dependency:sources
```

### Pular a execução dos testes (use com cautela)
```cmd
mvn package -DskipTests
```

### Executar um lifecycle phase específico com um profile
```cmd
mvn clean install -Pproducao
```

### Forçar atualização de snapshots
```cmd
mvn clean install -U
```

### Trabalhando com PowerShell
Se você usa PowerShell, pode redirecionar a saída para um arquivo para análise posterior:
```powershell
mvn clean install > build-log.txt
Get-Content build-log.txt
```

### Criando um script de automação (.bat)

Crie um arquivo `build.bat` na raiz do seu projeto:
```batch
@echo off
echo 🔨 Iniciando build do projeto...
echo --------------------------------
call mvn clean test
if %errorlevel% equ 0 (
    echo ✅ Testes passaram! Empacotando...
    call mvn package
    echo 📦 Build concluido com sucesso!
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

## ⚠️ Problemas Comuns no Windows e Soluções

### 1. "mvn não é reconhecido como um comando interno"
- **Causa**: O PATH não foi configurado corretamente ou o terminal não foi reiniciado após a configuração.
- **Solução**: Feche e reabra o terminal. Se ainda assim não funcionar, verifique se a variável `MAVEN_HOME` está correta e se `%MAVEN_HOME%\bin` está no PATH .

### 2. Conexão bloqueada por Firewall/Antivírus
- **Causa**: O Windows Firewall ou antivírus pode estar bloqueando o Java de baixar dependências da internet.
- **Solução**: Adicione uma exceção no firewall para `java.exe` e `mvn.cmd` .

### 3. "JAVA_HOME is not set"
- **Causa**: A variável de ambiente `JAVA_HOME` não está configurada.
- **Solução**: Crie a variável `JAVA_HOME` apontando para o diretório de instalação do JDK (ex: `C:\Program Files\Java\jdk-17`) e adicione `%JAVA_HOME%\bin` ao PATH.

### 4. Erro de codificação (caracteres acentuados)
- **Causa**: O terminal do Windows usa codificação diferente do padrão UTF-8.
- **Solução**: Execute `chcp 65001` antes dos comandos Maven para mudar para UTF-8.

---

## 📋 Resumo Rápido dos Comandos

| Comando | O que faz |
|---------|-----------|
| `mvn clean` | Remove a pasta `target/` |
| `mvn compile` | Compila o código fonte |
| `mvn test` | Executa os testes |
| `mvn package` | Gera o JAR/WAR |
| `mvn install` | Instala no repositório local |
| `mvn exec:java -Dexec.mainClass="..."` | Executa a classe principal |
| `mvn dependency:tree` | Mostra árvore de dependências |
| `mvn -version` | Verifica a instalação |

Agora você tem um ambiente Maven completo no Windows, funcionando 100% via linha de comando! Esse conhecimento é fundamental para trabalhar com servidores CI/CD (Jenkins, GitLab CI, GitHub Actions) e para quem prefere um workflow mais controlado e automatizado.
