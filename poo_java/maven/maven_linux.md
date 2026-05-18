## 🐧 Passo 1: Instalação do Maven no Linux

## Opção 1: Usando o gerenciador de pacotes (recomendado)

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install maven -y
```

**Fedora/RHEL/CentOS:**
```bash
sudo dnf install maven -y
```

**Arch Linux:**
```bash
sudo pacman -S maven
```

**Verifique a instalação:**
```bash
mvn --version
```
Saída esperada:
```
Apache Maven 3.9.x
Maven home: /usr/share/maven
Java version: 17.0.x, vendor: Ubuntu
OS: Linux 6.8.0-generic, arch: amd64
```

### Opção 2: Instalação manual (para ter controle da versão)

```bash
# Baixe a versão mais recente
cd /tmp
wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz

# Extraia para /opt
sudo tar -xzf apache-maven-3.9.9-bin.tar.gz -C /opt/

# Crie um link simbólico
sudo ln -s /opt/apache-maven-3.9.9 /opt/maven

# Configure as variáveis de ambiente
echo 'export M2_HOME=/opt/maven' >> ~/.bashrc
echo 'export PATH=$M2_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc
```

---

## 🚀 Passo 2: Criando um Projeto do Zero (Sem IDE)

Vamos criar um projeto Java completo usando apenas o terminal.

```bash
# Crie um diretório para seus projetos
mkdir -p ~/projetos-maven
cd ~/projetos-maven

# Gere um projeto usando o archetype quickstart
mvn archetype:generate \
  -DgroupId=com.linux.app \
  -DartifactId=meu-primeiro-projeto \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false

# Entre no projeto
cd meu-primeiro-projeto
```

**Estrutura gerada:**
```
meu-primeiro-projeto/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── linux/
    │               └── app/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── linux/
                    └── app/
                        └── AppTest.java
```

---

## 📝 Passo 3: Editando o Código no Terminal

Você pode usar qualquer editor de terminal: **nano**, **vim**, **emacs**, ou até mesmo **cat** com here-documents.

### Usando o Nano (mais amigável para iniciantes)

```bash
# Edite a classe principal
nano src/main/java/com/linux/app/App.java
```

Substitua o conteúdo por:
```java
package com.linux.app;

public class App {
    public static void main(String[] args) {
        System.out.println("🚀 Executando Maven no Linux!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
    }
}
```

Salve com `Ctrl+O`, depois `Enter`, e saia com `Ctrl+X`.

---

## 🔧 Passo 4: Comandos Essenciais do Maven

Agora execute cada comando e observe o que acontece:

### 1. Limpar builds anteriores
```bash
mvn clean
```
Remove a pasta `target/` completamente.

### 2. Compilar o código
```bash
mvn compile
```
Gera os arquivos `.class` dentro de `target/classes/`.

### 3. Executar os testes
```bash
mvn test
```
Compila e executa os testes unitários (veja `AppTest.java`).

### 4. Empacotar o projeto
```bash
mvn package
```
Cria um arquivo JAR em `target/meu-primeiro-projeto-1.0-SNAPSHOT.jar`.

### 5. Instalar no repositório local
```bash
mvn install
```
Copia o JAR para `~/.m2/repository/` para que outros projetos possam usá-lo.

### 6. Executar o programa (sem precisar do `java -jar`)
```bash
mvn exec:java -Dexec.mainClass="com.linux.app.App"
```

---

## 📦 Passo 5: Adicionando Dependências (Exemplo Prático)

Vamos adicionar a biblioteca **Apache Commons Lang** (para manipular strings).

### Edite o `pom.xml`:
```bash
nano pom.xml
```

Adicione a dependência dentro da tag `<dependencies>`:
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

### Agora edite a classe principal:
```bash
nano src/main/java/com/linux/app/App.java
```

Atualize para usar a nova biblioteca:
```java
package com.linux.app;

import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        String texto = "   Maven no Linux é poderoso!   ";
        
        // Usando o método da biblioteca Commons Lang
        String textoLimpo = StringUtils.trim(texto);
        String textoMaiusculo = StringUtils.upperCase(textoLimpo);
        
        System.out.println("Original: '" + texto + "'");
        System.out.println("Limpo: '" + textoLimpo + "'");
        System.out.println("Maiúsculo: " + textoMaiusculo);
        
        // Verificando se a string está vazia
        boolean vazio = StringUtils.isBlank(textoLimpo);
        System.out.println("Está vazio? " + vazio);
    }
}
```

### Execute novamente (o Maven baixará a dependência automaticamente):
```bash
mvn clean compile exec:java -Dexec.mainClass="com.linux.app.App"
```

**Saída esperada:**
```
[INFO] Scanning for projects...
[INFO] Downloading from central: https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/3.14.0/commons-lang3-3.14.0.jar
[INFO] Downloaded from central: commons-lang3-3.14.0.jar
Original: '   Maven no Linux é poderoso!   '
Limpo: 'Maven no Linux é poderoso!'
Maiúsculo: MAVEN NO LINUX É PODEROSO!
Está vazio? false
```

---

## 🎯 Passo 6: Criando um Executável (JAR com dependências)

Por padrão, o Maven cria um JAR simples sem as dependências. Para criar um **fat JAR** (executável independente), adicione o plugin Maven Shade ao `pom.xml`:

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
                <mainClass>com.linux.app.App</mainClass>
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
```bash
mvn clean package
```

Execute o JAR diretamente:
```bash
java -jar target/meu-primeiro-projeto-1.0-SNAPSHOT.jar
```

---

## 🛠️ Comandos Avançados Úteis

### Ver árvore de dependências
```bash
mvn dependency:tree
```

### Baixar fontes e documentação das dependências
```bash
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc
```

### Compilar com versão específica do Java
```bash
mvn clean compile -Dmaven.compiler.source=11 -Dmaven.compiler.target=11
```

### Pular testes (cuidado, apenas para builds rápidos)
```bash
mvn package -DskipTests
```

### Criar um projeto Web (WAR)
```bash
mvn archetype:generate \
  -DgroupId=com.linux.web \
  -DartifactId=meu-webapp \
  -DarchetypeArtifactId=maven-archetype-webapp \
  -DinteractiveMode=false
```

---

## 📁 Estrutura de Diretórios Útil no Linux

```bash
# Seu workspace no Linux
~/projetos-maven/
├── projeto-1/
│   ├── pom.xml
│   └── src/
├── projeto-2/
└── lib/ (se precisar de JARs locais não no Maven Central)

# Repositório local do Maven (onde ficam as dependências baixadas)
~/.m2/repository/
├── org/apache/commons/commons-lang3/
├── com/google/code/gson/
└── ...
```

---

## 🐚 Script de Automação (Exemplo)

Crie um script para automatizar o ciclo completo:

```bash
nano ~/bin/build-maven.sh
```

```bash
#!/bin/bash
# Script para build automático de projetos Maven

PROJECT_DIR=$1

if [ -z "$PROJECT_DIR" ]; then
    echo "Uso: ./build-maven.sh <diretorio-do-projeto>"
    exit 1
fi

cd "$PROJECT_DIR" || exit

echo "🔨 Iniciando build do projeto em $(pwd)"
echo "----------------------------------------"

mvn clean test

if [ $? -eq 0 ]; then
    echo "✅ Testes passaram! Empacotando..."
    mvn package
    echo "📦 Build concluído com sucesso!"
else
    echo "❌ Falha nos testes. Corrija antes de empacotar."
    exit 1
fi
```

Torne executável e use:
```bash
chmod +x ~/bin/build-maven.sh
./build-maven.sh ~/projetos-maven/meu-primeiro-projeto
```

---

## 💡 Dicas para o Workflow no Linux

1. **Use aliases no `~/.bashrc`**:
   ```bash
   alias mci='mvn clean install'
   alias mcp='mvn clean package'
   alias mct='mvn clean test'
   alias mrun='mvn exec:java -Dexec.mainClass='
   ```

2. **Integração com Git**:
   ```bash
   git init
   echo "target/" > .gitignore
   git add .
   git commit -m "Primeiro commit com Maven"
   ```

3. **Visualização rápida do pom.xml**:
   ```bash
   cat pom.xml | grep -A 2 "<artifactId>"
   ```

4. **Limpeza do cache local** (se algo der errado):
   ```bash
   rm -rf ~/.m2/repository/ # CUIDADO: Remove todas as dependências
   ```
