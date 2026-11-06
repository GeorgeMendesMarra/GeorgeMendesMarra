# Arquivo WAR em Java: tutorial para iniciantes

Este tutorial é para quem **nunca viu** o assunto. No final você terá criado, empacotado e publicado uma aplicação web Java em um arquivo `.war`, em **Windows**, **Linux** ou **macOS**.

---

## Sumário

1. [O que é um arquivo WAR?](#1-o-que-é-um-arquivo-war)
2. [Conceitos que você precisa conhecer](#2-conceitos-que-você-precisa-conhecer)
3. [Estrutura interna de um WAR](#3-estrutura-interna-de-um-war)
4. [Passo 1: instalar o JDK](#4-passo-1-instalar-o-jdk)
5. [Passo 2: instalar o Apache Tomcat](#5-passo-2-instalar-o-apache-tomcat)
6. [Passo 3: criar a aplicação de exemplo](#6-passo-3-criar-a-aplicação-de-exemplo)
7. [Passo 4: gerar o WAR manualmente](#7-passo-4-gerar-o-war-manualmente)
8. [Passo 5: publicar (deploy) no Tomcat](#8-passo-5-publicar-deploy-no-tomcat)
9. [Passo 6: gerar o WAR com Maven](#9-passo-6-gerar-o-war-com-maven)
10. [Olhando dentro de um WAR](#10-olhando-dentro-de-um-war)
11. [Problemas comuns](#11-problemas-comuns)
12. [Exercícios](#12-exercícios)
13. [Resumo](#13-resumo)

---

## 1. O que é um arquivo WAR?

**WAR** significa **Web Application Archive** (Arquivo de Aplicação Web).

É um arquivo com extensão `.war` que reúne **tudo o que uma aplicação web Java precisa** em um único pacote:

- páginas (HTML, CSS, JavaScript, imagens);
- páginas dinâmicas (JSP);
- classes Java compiladas (Servlets, Controllers etc.);
- bibliotecas (`.jar`);
- arquivos de configuração.

Pense assim: o WAR é como uma **"mala pronta"**. Você leva a mala até o servidor e ele sabe abrir e executar o que está dentro.

### Curiosidade importante

Um arquivo WAR é, na prática, um **arquivo ZIP** com uma estrutura de pastas padronizada. Se você renomear `app.war` para `app.zip`, consegue abri-lo normalmente.

### Comparação rápida: JAR x WAR

| Característica | JAR | WAR |
|---|---|---|
| Nome completo | Java Archive | Web Application Archive |
| Uso típico | Bibliotecas e programas Java comuns | Aplicações web |
| Precisa de servidor web? | Não (roda com `java -jar`) | Sim (Tomcat, Jetty, WildFly etc.) |
| Estrutura | Livre | Padronizada (`WEB-INF`, etc.) |

---

## 2. Conceitos que você precisa conhecer

| Termo | O que é |
|---|---|
| **Servlet** | Classe Java que recebe requisições HTTP e gera respostas. |
| **JSP** | *JavaServer Pages*: página HTML que pode ter código Java embutido. |
| **Contêiner web / servidor de aplicação** | Programa que executa WARs. O mais popular e simples é o **Apache Tomcat**. |
| **Deploy** | Ato de "publicar" o WAR no servidor. |
| **Context path** | Parte da URL que identifica a aplicação. Normalmente é o nome do WAR (`meu-app.war` vira `/meu-app`). |
| **`web.xml`** | Arquivo de configuração da aplicação (hoje muitas vezes opcional). |

> **Atenção (javax x jakarta):** a partir do **Tomcat 10** e do Jakarta EE 9, os pacotes mudaram de `javax.servlet.*` para `jakarta.servlet.*`. Este tutorial usa **Tomcat 10.1** e **`jakarta.servlet`**. Se você usar Tomcat 9 ou anterior, troque `jakarta` por `javax` no código.

---

## 3. Estrutura interna de um WAR

```text
meu-app.war
├── index.jsp                  <- páginas públicas (HTML, JSP, CSS, JS, imagens)
├── css/
│   └── estilo.css
├── META-INF/
│   └── MANIFEST.MF            <- gerado automaticamente pelo comando jar
└── WEB-INF/                   <- pasta PROTEGIDA: o navegador NÃO acessa
    ├── web.xml                <- configuração (opcional nas versões modernas)
    ├── classes/               <- suas classes .class compiladas
    │   └── br/com/exemplo/OlaServlet.class
    └── lib/                   <- bibliotecas .jar usadas pela aplicação
```

Regras de ouro:

1. Tudo **fora** de `WEB-INF` pode ser acessado pelo navegador.
2. Tudo **dentro** de `WEB-INF` é privado e só o servidor enxerga.
3. As classes compiladas ficam em `WEB-INF/classes` e os `.jar` em `WEB-INF/lib`.

---

## 4. Passo 1: instalar o JDK

Você precisa do **JDK** (Java Development Kit), não apenas do JRE. Usaremos o **JDK 17** (versão LTS compatível com Tomcat 10.1).

### Windows

Opção A: pelo terminal (PowerShell), usando o `winget`:

```powershell
winget install EclipseAdoptium.Temurin.17.JDK
```

Opção B: baixe o instalador `.msi` em <https://adoptium.net> e siga o assistente. Marque a opção **"Set JAVA_HOME variable"** e **"Add to PATH"**.

Feche e abra novamente o terminal e verifique:

```powershell
java -version
javac -version
echo $env:JAVA_HOME
```

Se `JAVA_HOME` estiver vazio, configure manualmente:

1. Pesquise **"Editar as variáveis de ambiente do sistema"** no menu Iniciar.
2. Clique em **Variáveis de Ambiente**.
3. Em **Variáveis do sistema**, clique em **Novo**:
   - Nome: `JAVA_HOME`
   - Valor: pasta do JDK, por exemplo `C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot`
4. Edite a variável `Path` e adicione `%JAVA_HOME%\bin`.
5. Reabra o terminal.

### Linux (Ubuntu / Debian)

```bash
sudo apt update
sudo apt install openjdk-17-jdk -y
java -version
javac -version
```

Fedora / RHEL / CentOS:

```bash
sudo dnf install java-17-openjdk-devel -y
```

Para definir o `JAVA_HOME` (adicione ao final do arquivo `~/.bashrc`):

```bash
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which javac))))
export PATH=$JAVA_HOME/bin:$PATH
```

Depois execute `source ~/.bashrc`.

### macOS

Com o [Homebrew](https://brew.sh):

```bash
brew install --cask temurin@17
java -version
javac -version
```

Para definir o `JAVA_HOME` (adicione ao arquivo `~/.zshrc`):

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH
```

Depois execute `source ~/.zshrc`.

> **Como saber se deu certo?** Os comandos `java -version` e `javac -version` devem mostrar a versão 17.

---

## 5. Passo 2: instalar o Apache Tomcat

O **Tomcat** é o servidor que vai executar o WAR. A instalação é simplesmente **baixar e descompactar**.

1. Acesse <https://tomcat.apache.org/download-10.cgi>.
2. Na seção **Binary Distributions → Core**, baixe:
   - **Windows:** arquivo `zip`
   - **Linux e macOS:** arquivo `tar.gz`

A partir daqui, chamaremos a pasta onde o Tomcat foi descompactado de **`CATALINA_HOME`**.

### Windows

1. Extraia o `.zip` em uma pasta simples, por exemplo `C:\tomcat`.
2. Confira que existe `C:\tomcat\bin\startup.bat`.
3. (Opcional) Defina a variável de ambiente `CATALINA_HOME` com o valor `C:\tomcat`.

### Linux

```bash
# vá até a pasta onde o arquivo foi baixado
cd ~/Downloads

# crie a pasta de destino e extraia
sudo mkdir -p /opt/tomcat
sudo tar -xzf apache-tomcat-10.1.*.tar.gz -C /opt/tomcat --strip-components=1

# dê a você mesmo a posse da pasta (evita usar sudo no dia a dia)
sudo chown -R $USER:$USER /opt/tomcat

# permita executar os scripts
chmod +x /opt/tomcat/bin/*.sh
```

### macOS

```bash
cd ~/Downloads

sudo mkdir -p /usr/local/tomcat
sudo tar -xzf apache-tomcat-10.1.*.tar.gz -C /usr/local/tomcat --strip-components=1
sudo chown -R $USER /usr/local/tomcat
chmod +x /usr/local/tomcat/bin/*.sh
```

### Testando o Tomcat

**Windows (PowerShell):**

```powershell
cd C:\tomcat\bin
.\startup.bat
```

**Linux:**

```bash
/opt/tomcat/bin/startup.sh
```

**macOS:**

```bash
/usr/local/tomcat/bin/startup.sh
```

Abra o navegador em **<http://localhost:8080>**. Se aparecer a página do Apache Tomcat, está funcionando.

Para **parar** o Tomcat:

| Sistema | Comando |
|---|---|
| Windows | `C:\tomcat\bin\shutdown.bat` |
| Linux | `/opt/tomcat/bin/shutdown.sh` |
| macOS | `/usr/local/tomcat/bin/shutdown.sh` |

### Pastas importantes do Tomcat

| Pasta | Função |
|---|---|
| `bin/` | Scripts para iniciar e parar o servidor |
| `conf/` | Configurações (porta, usuários etc.) |
| `lib/` | Bibliotecas do próprio Tomcat (inclui `servlet-api.jar`) |
| `logs/` | Registros de funcionamento e de erros |
| `webapps/` | **Onde colocamos os arquivos WAR** |

---

## 6. Passo 3: criar a aplicação de exemplo

Vamos criar uma aplicação mínima com **uma página JSP** e **um Servlet**.

### 6.1 Criar as pastas

**Windows (PowerShell):**

```powershell
mkdir meu-app
cd meu-app
mkdir WEB-INF
mkdir WEB-INF\classes
mkdir src\br\com\exemplo
```

**Linux e macOS:**

```bash
mkdir -p meu-app/WEB-INF/classes
mkdir -p meu-app/src/br/com/exemplo
cd meu-app
```

> A pasta `src` guarda os arquivos `.java` (código-fonte). Ela é só para o nosso uso e **não** entrará no WAR.

### 6.2 Criar a página `index.jsp`

Crie o arquivo `meu-app/index.jsp` com este conteúdo:

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Meu primeiro WAR</title>
</head>
<body>
    <h1>Olá! Meu primeiro WAR funcionou!</h1>
    <p>Data e hora no servidor: <%= new java.util.Date() %></p>
    <p><a href="ola?nome=Maria">Chamar o Servlet</a></p>
</body>
</html>
```

### 6.3 Criar o Servlet

Crie o arquivo `meu-app/src/br/com/exemplo/OlaServlet.java`:

```java
package br.com.exemplo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ola")
public class OlaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nome = req.getParameter("nome");
        if (nome == null || nome.isBlank()) {
            nome = "visitante";
        }

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Olá, " + nome + "!</h1>");
        out.println("<p>Resposta gerada por um Servlet dentro de um WAR.</p>");
        out.println("<a href=\"index.jsp\">Voltar</a>");
    }
}
```

A anotação `@WebServlet("/ola")` diz ao Tomcat: "quando alguém acessar `/ola`, chame esta classe".

### 6.4 Criar o `web.xml` (opcional, mas didático)

Crie `meu-app/WEB-INF/web.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                             https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">

    <display-name>Meu primeiro WAR</display-name>

    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>

</web-app>
```

### 6.5 Estrutura final

```text
meu-app/
├── index.jsp
├── src/
│   └── br/com/exemplo/OlaServlet.java
└── WEB-INF/
    ├── web.xml
    └── classes/            (vazia por enquanto)
```

---

## 7. Passo 4: gerar o WAR manualmente

Existem dois passos: **compilar** o Java e **empacotar** com o comando `jar`.

> O Servlet precisa da biblioteca `servlet-api.jar` para compilar. Ela já vem no Tomcat, na pasta `lib/`.

### 7.1 Compilar

Execute dentro da pasta `meu-app`.

**Windows (PowerShell):**

```powershell
javac -cp "C:\tomcat\lib\servlet-api.jar" -d WEB-INF\classes src\br\com\exemplo\OlaServlet.java
```

**Linux:**

```bash
javac -cp /opt/tomcat/lib/servlet-api.jar -d WEB-INF/classes src/br/com/exemplo/OlaServlet.java
```

**macOS:**

```bash
javac -cp /usr/local/tomcat/lib/servlet-api.jar -d WEB-INF/classes src/br/com/exemplo/OlaServlet.java
```

Confirme que o arquivo `WEB-INF/classes/br/com/exemplo/OlaServlet.class` foi criado.

### 7.2 Empacotar

O comando `jar` vem com o JDK e funciona igual nos três sistemas. A ideia é incluir **apenas** `index.jsp` e `WEB-INF` (sem a pasta `src`).

Execute **dentro da pasta `meu-app`**:

**Windows (PowerShell):**

```powershell
jar -cvf meu-app.war index.jsp WEB-INF
```

**Linux e macOS:**

```bash
jar -cvf meu-app.war index.jsp WEB-INF
```

Entendendo as opções:

| Opção | Significado |
|---|---|
| `c` | *create*: criar um arquivo |
| `v` | *verbose*: mostrar o que está sendo incluído |
| `f` | *file*: o próximo argumento é o nome do arquivo |

Pronto! Você tem o arquivo `meu-app.war`.

---

## 8. Passo 5: publicar (deploy) no Tomcat

### 8.1 Copiar o WAR para `webapps`

**Windows (PowerShell):**

```powershell
copy meu-app.war C:\tomcat\webapps\
```

**Linux:**

```bash
cp meu-app.war /opt/tomcat/webapps/
```

**macOS:**

```bash
cp meu-app.war /usr/local/tomcat/webapps/
```

### 8.2 Iniciar o Tomcat (se ainda não estiver rodando)

| Sistema | Comando |
|---|---|
| Windows | `C:\tomcat\bin\startup.bat` |
| Linux | `/opt/tomcat/bin/startup.sh` |
| macOS | `/usr/local/tomcat/bin/startup.sh` |

Se o Tomcat **já estava rodando**, não precisa reiniciar. Ele detecta o novo WAR sozinho (*hot deploy*).

### 8.3 O que o Tomcat faz

1. Percebe o novo arquivo `meu-app.war` em `webapps`.
2. **Descompacta** o WAR em uma pasta `webapps/meu-app/`.
3. Carrega as classes e inicia a aplicação.

### 8.4 Acessar a aplicação

Abra no navegador:

- **<http://localhost:8080/meu-app/>**: página JSP
- **<http://localhost:8080/meu-app/ola?nome=Maria>**: Servlet

O nome do WAR vira o *context path*. Por isso a URL começa com `/meu-app`.

### 8.5 Atualizando a aplicação

Alterou o código? Refaça a compilação e o `jar`, depois copie o novo WAR para `webapps` substituindo o antigo. O Tomcat recarrega a aplicação.

### 8.6 Aplicação na raiz (sem `/meu-app` na URL)

Renomeie o arquivo para **`ROOT.war`** (maiúsculas). Ela passará a responder em `http://localhost:8080/`.

### 8.7 Remover a aplicação

Apague `meu-app.war` **e** a pasta `meu-app` dentro de `webapps`.

---

## 9. Passo 6: gerar o WAR com Maven

Na vida real, ninguém compila e empacota na mão. Usa-se uma **ferramenta de build**, como o **Maven**, que baixa dependências, compila e gera o WAR com **um único comando**.

### 9.1 Instalar o Maven

**Windows:**

```powershell
winget install Apache.Maven
```

(Ou baixe o `.zip` em <https://maven.apache.org/download.cgi>, extraia e adicione a pasta `bin` ao `Path`.)

**Linux (Ubuntu / Debian):**

```bash
sudo apt install maven -y
```

**macOS:**

```bash
brew install maven
```

Teste (em qualquer sistema):

```bash
mvn -version
```

### 9.2 Estrutura de um projeto Maven web

```text
meu-app-maven/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── br/com/exemplo/OlaServlet.java
        └── webapp/
            ├── index.jsp
            └── WEB-INF/
                └── web.xml   (opcional)
```

Crie as pastas:

**Windows (PowerShell):**

```powershell
mkdir meu-app-maven\src\main\java\br\com\exemplo
mkdir meu-app-maven\src\main\webapp\WEB-INF
cd meu-app-maven
```

**Linux e macOS:**

```bash
mkdir -p meu-app-maven/src/main/java/br/com/exemplo
mkdir -p meu-app-maven/src/main/webapp/WEB-INF
cd meu-app-maven
```

Copie o `OlaServlet.java` para `src/main/java/br/com/exemplo/` e o `index.jsp` para `src/main/webapp/`.

### 9.3 O arquivo `pom.xml`

Crie `meu-app-maven/pom.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>br.com.exemplo</groupId>
    <artifactId>meu-app-maven</artifactId>
    <version>1.0</version>
    <packaging>war</packaging>

    <properties>
        <maven.compiler.release>17</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- "provided": o Tomcat já tem esta biblioteca, então ela NÃO vai dentro do WAR -->
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <!-- Nome do arquivo gerado: meu-app-maven.war (sem o número da versão) -->
        <finalName>meu-app-maven</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.4.0</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Pontos-chave:

- `<packaging>war</packaging>` diz ao Maven que o resultado é um **WAR**.
- `<scope>provided</scope>` evita empacotar a `servlet-api`, pois o servidor já fornece.
- `<finalName>` define o nome do arquivo (e, consequentemente, o *context path*).

### 9.4 Gerar o WAR

Dentro da pasta `meu-app-maven` (mesmo comando nos três sistemas):

```bash
mvn clean package
```

Na primeira vez o Maven baixa vários arquivos da internet. Ao terminar, procure:

```text
meu-app-maven/target/meu-app-maven.war
```

Copie esse arquivo para a pasta `webapps` do Tomcat (como no [Passo 5](#8-passo-5-publicar-deploy-no-tomcat)) e acesse **<http://localhost:8080/meu-app-maven/>**.

---

## 10. Olhando dentro de um WAR

Como o WAR é um ZIP, você pode inspecioná-lo.

### Com o comando `jar` (funciona nos três sistemas)

```bash
jar -tf meu-app.war
```

(`t` = listar o conteúdo, `f` = arquivo.)

### Extrair o conteúdo

```bash
mkdir extraido
cd extraido
jar -xf ../meu-app.war
```

### Alternativas por sistema

| Sistema | Alternativa |
|---|---|
| Windows | Renomeie para `.zip` e abra com o Explorador, ou use o 7-Zip |
| Linux | `unzip -l meu-app.war` |
| macOS | `unzip -l meu-app.war` |

---

## 11. Problemas comuns

| Sintoma | Causa provável | Como resolver |
|---|---|---|
| `javac` não é reconhecido | JDK não instalado ou fora do `PATH` | Refaça o [Passo 1](#4-passo-1-instalar-o-jdk) e reabra o terminal |
| Tomcat não inicia: `JAVA_HOME` não definido | Variável ausente | Defina `JAVA_HOME` conforme o Passo 1 |
| `Permission denied` ao rodar `startup.sh` | Script sem permissão de execução | `chmod +x <pasta-do-tomcat>/bin/*.sh` |
| Porta 8080 já em uso | Outro programa usa a porta | Feche o outro programa ou altere a porta em `conf/server.xml` (`<Connector port="8080" ...>`) |
| Erro **404** ao abrir a aplicação | URL sem o nome do WAR, WAR com nome diferente ou falha no deploy | Use `http://localhost:8080/NOME-DO-WAR/` e veja os logs |
| `package javax.servlet does not exist` ou `jakarta.servlet does not exist` | Tomcat e código usando namespaces diferentes | Tomcat 10+ usa `jakarta.servlet`; Tomcat 9 usa `javax.servlet` |
| `UnsupportedClassVersionError` | Compilou com Java mais novo que o usado pelo Tomcat | Use o mesmo JDK para compilar e executar (ou `--release 17`) |
| Alterações não aparecem | Navegador com cache ou WAR antigo | Recompile, substitua o WAR e force a atualização (`Ctrl+F5`) |
| WAR vai com a pasta `src` dentro | `jar` executado com `*` | Liste apenas o que deve entrar (`index.jsp WEB-INF`) |

### Onde ver os erros: os logs

- **Windows:** `C:\tomcat\logs\catalina.<data>.log`
- **Linux:** `/opt/tomcat/logs/catalina.out`
- **macOS:** `/usr/local/tomcat/logs/catalina.out`

Para acompanhar o log em tempo real:

**Linux e macOS:**

```bash
tail -f /opt/tomcat/logs/catalina.out
```

**Windows (PowerShell):**

```powershell
Get-Content C:\tomcat\logs\catalina.*.log -Wait -Tail 50
```

---

## 12. Exercícios

1. **Explorar:** liste o conteúdo do seu `meu-app.war` com `jar -tf`. A pasta `src` está lá dentro? Por quê?
2. **Novo Servlet:** crie um segundo Servlet em `/tchau`, recompile, gere novamente o WAR e publique.
3. **Arquivo estático:** adicione uma pasta `css/` com um arquivo `estilo.css`, ligue-o ao `index.jsp` e refaça o WAR.
4. **Raiz do servidor:** renomeie o WAR para `ROOT.war`, publique e acesse `http://localhost:8080/`.
5. **Maven:** adicione uma dependência (por exemplo, `org.apache.commons:commons-lang3`) no `pom.xml`, gere o WAR e confira que o `.jar` apareceu em `WEB-INF/lib`.

---

## 13. Resumo

- **WAR** é um ZIP padronizado que contém uma aplicação web Java completa.
- A pasta **`WEB-INF`** é privada e guarda `web.xml`, `classes/` e `lib/`.
- Para executar um WAR você precisa de um **servidor**, como o **Apache Tomcat**.
- **Deploy** é copiar o `.war` para a pasta `webapps` do Tomcat.
- O **nome do WAR** define o endereço da aplicação (`/nome-do-war`); `ROOT.war` responde na raiz.
- Manualmente: `javac` para compilar e `jar -cvf app.war ...` para empacotar.
- Na prática: `mvn clean package` gera o WAR em `target/`.

### Fluxo completo em uma linha

```text
código-fonte  →  compilar  →  empacotar (.war)  →  copiar para webapps  →  acessar no navegador
```

### Próximos passos

- Aprender **Servlets** e **JSP** com mais profundidade.
- Conhecer **JavaServer Faces (JSF)** e **Spring MVC**, que também geram WAR.
- Estudar o **Spring Boot**, que por padrão gera um **JAR executável**, mas também pode gerar WAR para servidores externos.
- Explorar **Docker** para executar o Tomcat e o WAR em contêineres.
