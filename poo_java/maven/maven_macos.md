## 🍎 Usando Maven no macOS sem IDE

Usar Maven no macOS sem IDE é um processo simples, especialmente com o **Homebrew**, que é o gerenciador de pacotes padrão para Mac. Vou mostrar duas formas de instalação e todos os comandos essenciais para você trabalhar exclusivamente no **Terminal**.


---

## Passo 1: Verificando Pré-requisitos

### Java JDK

O Maven requer o Java JDK instalado. Verifique se já possui:

```bash
java -version
javac -version
```

Se não tiver, instale o JDK (recomendo o OpenJDK 17 ou superior). Você pode instalar via Homebrew:

```bash
brew install openjdk@17
```

### Verifique se o Maven já está instalado

Antes de instalar, verifique se o Maven já não veio com seu macOS:

```bash
mvn --version
```

Em versões muito antigas do OS X (pré-Mavericks), o Maven vinha pré-instalado em `/usr/share/maven`. Porém, nas versões modernas do macOS (Catalina, Big Sur, Monterey, Ventura, Sonoma e Sequoia), o Maven **não** vem mais instalado.


---

## Passo 2: Instalação do Maven no macOS

### 🏆 Opção 1: Usando Homebrew (RECOMENDADO)

Esta é a forma mais simples e eficiente.

**Se você não tem o Homebrew instalado**, instale primeiro:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

**Instale o Maven**:

```bash
brew install maven
```

**Verifique a instalação**:

```bash
mvn --version
```

Saída esperada:
```
Apache Maven 3.9.9
Maven home: /opt/homebrew/Cellar/maven/3.9.9/libexec
Java version: 17.0.9, vendor: Homebrew
OS name: "mac os x", version: "14.5", arch: "aarch64"
```

> ✅ **Vantagem do Homebrew**: Tudo é automático - PATH, variáveis de ambiente, dependências. Nenhuma configuração manual é necessária.

---

### 📦 Opção 2: Instalação Manual (via download)

Use esta opção se quiser controle total sobre a versão ou não puder usar o Homebrew.

1. **Baixe o Maven** do site oficial:

```bash
cd ~/Downloads
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz
```

2. **Extraia o arquivo** para `/opt` (ou outra pasta de sua preferência):

```bash
sudo tar -xzf apache-maven-3.9.9-bin.tar.gz -C /opt/
```

3. **Crie um link simbólico** (opcional, mas prático):

```bash
sudo ln -s /opt/apache-maven-3.9.9 /opt/maven
```

4. **Configure as variáveis de ambiente**:

Para usuários de **zsh** (shell padrão desde Catalina):

```bash
echo 'export M2_HOME=/opt/maven' >> ~/.zshrc
echo 'export PATH=$M2_HOME/bin:$PATH' >> ~/.zshrc
echo 'export JAVA_HOME=$(/usr/libexec/java_home)' >> ~/.zshrc
source ~/.zshrc
```

Para usuários de **bash**:

```bash
echo 'export M2_HOME=/opt/maven' >> ~/.bash_profile
echo 'export PATH=$M2_HOME/bin:$PATH' >> ~/.bash_profile
echo 'export JAVA_HOME=$(/usr/libexec/java_home)' >> ~/.bash_profile
source ~/.bash_profile
```

5. **Verifique**:

```bash
mvn --version
```

---

## 🚀 Passo 3: Criando um Projeto do Zero

Agora que o Maven está instalado, vamos criar um projeto Java completo usando apenas o Terminal.

```bash
# Crie uma pasta para seus projetos
mkdir -p ~/projetos-maven
cd ~/projetos-maven

# Gere um novo projeto usando o archetype quickstart
mvn archetype:generate \
  -DgroupId=com.mac.app \
  -DartifactId=meu-app-mac \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false

# Entre no projeto
cd meu-app-mac
```

**Estrutura gerada**:

```
meu-app-mac/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── mac/
    │               └── app/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── mac/
                    └── app/
                        └── AppTest.java
```

---

## 📝 Passo 4: Editando o Código no Terminal

Sem IDE, você pode usar editores de texto como **nano**, **vim**, ou **emacs**.

### Usando o Nano (mais amigável para iniciantes)

```bash
nano src/main/java/com/mac/app/App.java
```

Substitua o conteúdo por:

```java
package com.mac.app;

public class App {
    public static void main(String[] args) {
        System.out.println("🍎 Executando Maven no macOS!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Usuário: " + System.getProperty("user.name"));
    }
}
```

Salve com `Ctrl+O`, pressione `Enter`, e saia com `Ctrl+X`.


---

## 🔧 Passo 5: Comandos Essenciais do Maven

Execute os comandos dentro da pasta do projeto (`~/projetos-maven/meu-app-mac`):

### 1. Limpar builds anteriores
```bash
mvn clean
```
Remove a pasta `target/` completamente.

### 2. Compilar o código
```bash
mvn compile
```
Gera os arquivos `.class` em `target/classes/`.

### 3. Executar os testes
```bash
mvn test
```
Executa os testes unitários em `src/test/java/`.

### 4. Empacotar o projeto
```bash
mvn package
```
Cria um arquivo JAR em `target/meu-app-mac-1.0-SNAPSHOT.jar`.

### 5. Instalar no repositório local
```bash
mvn install
```
Copia o JAR para `~/.m2/repository/` para ser usado por outros projetos.

### 6. Executar o programa (sem precisar do JAR)
```bash
mvn exec:java -Dexec.mainClass="com.mac.app.App"
```


---

## 📦 Passo 6: Adicionando uma Dependência Externa

Vamos adicionar a biblioteca **Apache Commons Lang** (para manipular strings).

### Edite o `pom.xml`:

```bash
nano pom.xml
```

Dentro da tag `<dependencies>`, adicione:

```xml
<dependencies>
  <!-- Dependência JUnit (já existente) -->
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.3</version>
    <scope>test</scope>
  </dependency>
  
  <!-- NOVA DEPENDÊNCIA: Apache Commons Lang -->
  <dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.14.0</version>
  </dependency>
</dependencies>
```

### Atualize a classe `App.java`:

```bash
nano src/main/java/com/mac/app/App.java
```

```java
package com.mac.app;

import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        String texto = "   Maven no macOS é incrível!   ";
        
        String textoLimpo = StringUtils.trim(texto);
        String textoMaiusculo = StringUtils.upperCase(textoLimpo);
        
        System.out.println("Original: '" + texto + "'");
        System.out.println("Limpo: '" + textoLimpo + "'");
        System.out.println("Maiúsculo: " + textoMaiusculo);
        System.out.println("Está em branco? " + StringUtils.isBlank(textoLimpo));
    }
}
```

### Execute:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.mac.app.App"
```

O Maven baixará automaticamente o Commons Lang do repositório central para `~/.m2/repository/`.


---

## 🎯 Passo 7: Criando um Executável (Fat JAR)

Para criar um JAR que inclui todas as dependências (executável independente), adicione o **Maven Shade Plugin** ao `pom.xml`:

```bash
nano pom.xml
```

Adicione dentro da tag `<build><plugins>`:

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
                <mainClass>com.mac.app.App</mainClass>
              </transformer>
            </transformers>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

Execute:

```bash
mvn clean package
```

Execute o JAR diretamente:

```bash
java -jar target/meu-app-mac-1.0-SNAPSHOT.jar
```


---

## 🛠️ Comandos Avançados e Dicas para macOS

### Ver árvore de dependências
```bash
mvn dependency:tree
```

### Baixar fontes das dependências
```bash
mvn dependency:sources
```

### Pular testes (use com cautela)
```bash
mvn package -DskipTests
```

### Limpar cache local (se algo der errado)
```bash
rm -rf ~/.m2/repository/
```

### Onde ficam as coisas no macOS

| Item | Localização |
|------|-------------|
| Repositório local do Maven | `~/.m2/repository/` |
| Arquivo de configuração do usuário | `~/.m2/settings.xml` |
| Instalação do Homebrew (Maven) | `/opt/homebrew/Cellar/maven/` (Apple Silicon) ou `/usr/local/Cellar/maven/` (Intel) |
| Configurações do shell (zsh) | `~/.zshrc` |
| Configurações do shell (bash) | `~/.bash_profile` |

---

## 🐚 Criando Aliases no macOS

Para acelerar seu workflow, adicione aliases ao seu `~/.zshrc`:

```bash
nano ~/.zshrc
```

Adicione no final:

```bash
# Aliases Maven
alias mci='mvn clean install'
alias mcp='mvn clean package'
alias mct='mvn clean test'
alias mrun='mvn exec:java -Dexec.mainClass='
alias mtree='mvn dependency:tree'
```

Recarregue:

```bash
source ~/.zshrc
```

---

## 🚀 Alternativa Rápida: Maven Daemon (mvnd)

Para builds ainda mais rápidos, você pode instalar o **Maven Daemon** (`mvnd`), que mantém uma JVM em segundo plano:

```bash
brew install mvndaemon/homebrew-mvnd/mvnd
```

Use `mvnd` no lugar de `mvn` nos comandos:

```bash
mvnd clean install  # muito mais rápido!
```

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

---

## ⚠️ Solução de Problemas Comuns no macOS

### 1. "command not found: mvn" após instalação
- **Solução**: Se instalou com Homebrew, tente `brew link --overwrite maven`. Se foi manual, verifique se o PATH está correto no `~/.zshrc`.

### 2. "JAVA_HOME is not set"
- **Solução**: Adicione `export JAVA_HOME=$(/usr/libexec/java_home)` ao seu `~/.zshrc`.

### 3. Maven está usando Java versão errada
- **Solução**: Execute `/usr/libexec/java_home -V` para listar JDKs instalados e ajuste o `JAVA_HOME`.

### 4. Permissão negada ao executar scripts
- **Solução**: Use `chmod +x arquivo.sh` para tornar executável.

---
