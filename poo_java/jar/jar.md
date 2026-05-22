# 📚 Guia Completo sobre como trabalhar com arquivos JAR em Java

Arquivos JAR (Java ARchive) são o formato padrão para distribuição de bibliotecas e aplicações Java. Este guia cobre desde os conceitos básicos até técnicas avançadas.

---

## 🎯 O que é um arquivo JAR?

**JAR (Java ARchive)** é um formato de arquivo baseado no ZIP que agrupa múltiplos arquivos Java (arquivos `.class`), recursos (imagens, propriedades, XML) e um arquivo de manifesto (`MANIFEST.MF`) em um único arquivo.

### Características principais:
- **Compactação:** Reduz o tamanho dos arquivos
- **Agrupamento:** Organiza centenas de classes em um único arquivo
- **Portabilidade:** Um único arquivo contém toda a aplicação
- **Segurança:** Pode ser assinado digitalmente
- **Versionamento:** Facilita a distribuição de versões

---

## 🏗️ Estrutura de um arquivo JAR

```
meu-app.jar
├── META-INF/
│   ├── MANIFEST.MF          # Arquivo de manifesto (metadados)
│   ├── INDEX.LIST           # Índice opcional para aceleração
│   └── signatures/          # Assinaturas digitais (opcional)
├── com/
│   └── minhaempresa/
│       └── meuapp/
│           ├── Main.class
│           ├── Util.class
│           └── controller/
│               └── AppController.class
├── images/
│   ├── logo.png
│   └── icon.png
└── config/
    └── application.properties
```

---

## 📦 Criando arquivos JAR

### 1. Usando o comando `jar` (JDK nativo)

O JDK inclui a ferramenta `jar` (similar ao `tar` ou `zip`).

#### Sintaxe básica:
```bash
jar opções arquivo-jar [arquivos-de-entrada]
```

#### Opções principais do comando `jar`:

| Opção | Descrição |
|:-----:|:----------|
| `c` | Create - cria um novo arquivo JAR |
| `x` | Extract - extrai o conteúdo do JAR |
| `t` | Table - lista o conteúdo do JAR |
| `u` | Update - atualiza um JAR existente |
| `v` | Verbose - saída detalhada |
| `f` | File - especifica o nome do arquivo |
| `m` | Manifest - inclui arquivo de manifesto |
| `e` | Entry point - define a classe principal |

#### Exemplos:

```bash
# Criar JAR a partir do diretório build/classes
jar cf meu-app.jar -C build/classes .

# Criar JAR com saída verbosa
jar cvf meu-app.jar -C build/classes .

# Criar JAR especificando a classe principal
jar cfe meu-app.jar com.minhaempresa.Main -C build/classes .

# Listar conteúdo do JAR
jar tf meu-app.jar

# Extrair conteúdo do JAR
jar xf meu-app.jar

# Atualizar um arquivo no JAR
jar uf meu-app.jar -C build/classes nova-classe.class
```

### 2. Usando ANT

```xml
<target name="jar" depends="compile">
    <jar destfile="dist/meu-app.jar" basedir="build/classes">
        <manifest>
            <attribute name="Main-Class" value="com.minhaempresa.Main"/>
            <attribute name="Class-Path" value="lib/junit.jar lib/commons-lang.jar"/>
        </manifest>
        <!-- Incluir recursos -->
        <fileset dir="src/main/resources" includes="**/*.properties"/>
        <fileset dir="src/main/resources" includes="**/*.xml"/>
    </jar>
</target>
```

### 3. Usando Maven

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
                        <mainClass>com.minhaempresa.Main</mainClass>
                        <addClasspath>true</addClasspath>
                        <classpathPrefix>lib/</classpathPrefix>
                    </manifest>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 4. Usando Gradle

```groovy
plugins {
    id 'java'
    id 'application'
}

jar {
    manifest {
        attributes(
            'Main-Class': 'com.minhaempresa.Main',
            'Class-Path': configurations.runtimeClasspath.collect { "lib/${it.name}" }.join(' ')
        )
    }
}

// Para criar um fat JAR (com todas as dependências)
task fatJar(type: Jar) {
    manifest {
        attributes 'Main-Class': 'com.minhaempresa.Main'
    }
    archiveBaseName = 'meu-app-all'
    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
    with jar
}
```

### 5. Programaticamente (via Java API)

```java
import java.io.*;
import java.util.jar.*;

public class CriadorJAR {
    
    public static void criarJAR(String jarFileName, String classesDir, String mainClass) 
            throws IOException {
        
        FileOutputStream fos = new FileOutputStream(jarFileName);
        JarOutputStream jos = new JarOutputStream(fos);
        
        // Adicionar manifesto
        Manifest manifest = new Manifest();
        Attributes attributes = manifest.getMainAttributes();
        attributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        attributes.put(new Attributes.Name("Main-Class"), mainClass);
        jos.putNextEntry(new JarEntry("META-INF/MANIFEST.MF"));
        manifest.write(jos);
        jos.closeEntry();
        
        // Adicionar arquivos .class
        adicionarDiretorio(jos, new File(classesDir), "");
        
        jos.close();
        fos.close();
    }
    
    private static void adicionarDiretorio(JarOutputStream jos, File dir, String prefix) 
            throws IOException {
        
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                adicionarDiretorio(jos, file, prefix + file.getName() + "/");
            } else {
                String entryName = prefix + file.getName();
                jos.putNextEntry(new JarEntry(entryName));
                
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        jos.write(buffer, 0, bytesRead);
                    }
                }
                jos.closeEntry();
            }
        }
    }
}
```

---

## 🚀 Executando JARs

### 1. Executável (com Main-Class definida no MANIFEST)

```bash
java -jar meu-app.jar
```

### 2. JAR com classe principal especificada na linha de comando

```bash
java -cp meu-app.jar com.minhaempresa.Main
```

### 3. JAR com classpath (múltiplos JARs)

```bash
# Windows
java -cp "meu-app.jar;lib/*" com.minhaempresa.Main

# Unix/Linux/Mac
java -cp "meu-app.jar:lib/*" com.minhaempresa.Main
```

### 4. Definindo argumentos para a aplicação

```bash
java -jar meu-app.jar arg1 arg2 arg3

# Dentro da classe main
public static void main(String[] args) {
    System.out.println("Argumentos recebidos: " + Arrays.toString(args));
}
```

### 5. JVM arguments (memória, propriedades do sistema)

```bash
java -Xmx512m -Dconfig.file=config.properties -jar meu-app.jar
```

---

## 📋 O arquivo MANIFEST.MF

O `MANIFEST.MF` é um arquivo especial dentro da pasta `META-INF/` que contém metadados sobre o JAR.

### Estrutura básica:
```
Manifest-Version: 1.0
Created-By: 17.0.5 (Oracle Corporation)
Main-Class: com.minhaempresa.Main
Class-Path: lib/junit.jar lib/commons-lang3.jar
```

### Entradas comuns do MANIFEST:

| Atributo | Descrição | Exemplo |
|:---------|:----------|:--------|
| `Manifest-Version` | Versão do formato do manifesto | `1.0` |
| `Main-Class` | Classe que contém `main()` | `com.app.Main` |
| `Class-Path` | Caminho para outras bibliotecas (espaços separados) | `lib/a.jar lib/b.jar` |
| `Implementation-Title` | Nome da implementação | `Meu App` |
| `Implementation-Version` | Versão da implementação | `1.2.3` |
| `Implementation-Vendor` | Fornecedor da implementação | `Minha Empresa` |
| `Specification-Title` | Nome da especificação | `Java API` |
| `Specification-Version` | Versão da especificação | `1.0` |

### Criando manifesto personalizado com Maven:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <mainClass>com.minhaempresa.Main</mainClass>
                <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
                <addDefaultSpecificationEntries>true</addDefaultSpecificationEntries>
            </manifest>
            <manifestEntries>
                <Built-By>Equipe DevOps</Built-By>
                <Build-Time>${maven.build.timestamp}</Build-Time>
            </manifestEntries>
        </archive>
    </configuration>
</plugin>
```

---

## 🔧 Trabalhando com JARs no código

### 1. Lendo recursos de dentro de um JAR

```java
public class LeitorRecursos {
    
    // Lendo arquivo de propriedades dentro do JAR
    public static Properties carregarPropriedades(String resourcePath) 
            throws IOException {
        
        Properties props = new Properties();
        try (InputStream input = LeitorRecursos.class
                .getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new FileNotFoundException("Recurso não encontrado: " 
                        + resourcePath);
            }
            props.load(input);
        }
        return props;
    }
    
    // Lendo arquivo de texto
    public static String lerArquivoTexto(String resourcePath) 
            throws IOException {
        
        StringBuilder content = new StringBuilder();
        try (InputStream input = LeitorRecursos.class
                .getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(input))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    
    // Listando recursos dentro de um diretório do JAR
    public static List<String> listarRecursos(String directory) 
            throws URISyntaxException, IOException {
        
        List<String> resources = new ArrayList<>();
        URL dirUrl = LeitorRecursos.class.getResource(directory);
        
        if (dirUrl != null && dirUrl.getProtocol().equals("jar")) {
            // Dentro de um JAR
            String jarPath = dirUrl.getPath().substring(5, 
                    dirUrl.getPath().indexOf("!"));
            try (JarFile jarFile = new JarFile(
                    URLDecoder.decode(jarPath, StandardCharsets.UTF_8.name()))) {
                
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.startsWith(directory.substring(1))) {
                        resources.add(name);
                    }
                }
            }
        } else if (dirUrl != null) {
            // No sistema de arquivos
            File dir = new File(dirUrl.toURI());
            for (File file : dir.listFiles()) {
                resources.add(file.getName());
            }
        }
        return resources;
    }
    
    // Uso
    public static void main(String[] args) throws IOException {
        Properties props = carregarPropriedades("/config/app.properties");
        System.out.println("Propriedade: " + props.getProperty("app.name"));
        
        String readme = lerArquivoTexto("/META-INF/README.txt");
        System.out.println(readme);
    }
}
```

### 2. Carregando classes de um JAR externo

```java
import java.net.*;
import java.io.*;

public class ClassLoaderFromJar {
    
    public static void main(String[] args) 
            throws MalformedURLException, ClassNotFoundException, 
                   InstantiationException, IllegalAccessException {
        
        // Carregar JAR em tempo de execução
        File jarFile = new File("lib/external-library.jar");
        URL url = jarFile.toURI().toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
        
        // Carregar uma classe do JAR
        Class<?> loadedClass = classLoader.loadClass("com.external.SomeClass");
        
        // Instanciar (requer construtor padrão)
        Object instance = loadedClass.newInstance();
        
        // Invocar métodos via reflexão (ou usar cast se a classe for conhecida)
        System.out.println("Classe carregada: " + loadedClass.getName());
    }
}
```

### 3. Verificando se código está rodando de dentro de um JAR

```java
public class VerificadorJAR {
    
    public static boolean isExecutandoDeJAR() {
        String className = VerificadorJAR.class.getName();
        String classPath = VerificadorJAR.class
                .getResource("/" + className.replace('.', '/') + ".class")
                .toString();
        return classPath.startsWith("jar:");
    }
    
    public static String getJARPatch() {
        String path = VerificadorJAR.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        try {
            return URLDecoder.decode(path, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return path;
        }
    }
    
    public static void main(String[] args) {
        if (isExecutandoDeJAR()) {
            System.out.println("Executando de: " + getJARPatch());
        } else {
            System.out.println("Executando do sistema de arquivos");
        }
    }
}
```

---

## 🔍 Manipulando JARs com Java API

### 1. Lendo conteúdo de um JAR

```java
import java.util.jar.*;
import java.util.zip.*;

public class LeitorJAR {
    
    public static void listarConteudo(String jarFilePath) throws IOException {
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            
            System.out.println("Conteúdo do JAR: " + jarFilePath);
            System.out.println("Manifest Version: " + 
                    jarFile.getManifest().getMainAttributes()
                            .getValue(Attributes.Name.MANIFEST_VERSION));
            
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                System.out.printf("%s - %d bytes %s%n",
                        entry.getName(),
                        entry.getSize(),
                        entry.isDirectory() ? "(DIR)" : "");
            }
        }
    }
    
    public static void extrairJAR(String jarFilePath, String destDir) 
            throws IOException {
        
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                File outFile = new File(destDir, entry.getName());
                
                if (entry.isDirectory()) {
                    outFile.mkdirs();
                } else {
                    outFile.getParentFile().mkdirs();
                    try (InputStream is = jarFile.getInputStream(entry);
                         FileOutputStream fos = new FileOutputStream(outFile)) {
                        
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        }
    }
}
```

### 2. Assinatura digital de JARs (JAR Signing)

```bash
# Gerar keystore (se não tiver)
keytool -genkey -alias minha-chave -keystore meu-keystore.jks -validity 365

# Assinar o JAR
jarsigner -keystore meu-keystore.jks -storepass senha123 meu-app.jar minha-chave

# Verificar assinatura
jarsigner -verify -verbose -certs meu-app.jar

# Verificar se JAR foi assinado
if jarsigner -verify meu-app.jar; then
    echo "Assinatura válida"
else
    echo "Assinatura inválida ou JAR não assinado"
fi
```

---

## 🎁 Tipos especiais de JAR

### 1. FAT JAR / Uber JAR
JAR que contém todas as dependências embutidas.

```xml
<!-- Maven Shade Plugin -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.5.0</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>com.minhaempresa.Main</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### 2. WAR (Web ARchive) para aplicações web
```xml
<!-- Estrutura do WAR -->
meu-app.war
├── META-INF/
│   └── MANIFEST.MF
├── WEB-INF/
│   ├── web.xml
│   ├── classes/          # Classes compiladas
│   └── lib/              # JARs de dependências
└── index.html
```

### 3. EAR (Enterprise ARchive) para aplicações corporativas
```xml
meu-app.ear
├── META-INF/
│   ├── application.xml   # Descrição do EAR
│   └── MANIFEST.MF
├── meu-app.war
├── meu-lib.jar
└── meu-ejb.jar
```

---

## 🛠️ Ferramentas para trabalhar com JARs

### 1. Java Decompiler (JD-GUI)
Visualizar código fonte de classes dentro do JAR:
```bash
jd-gui.exe meu-app.jar
```

### 2. JAR Explorer (vim-plugins)
```bash
# No vim, com plugin vim-jar
:JarOpen
:JarList
```

### 3. Analisando JAR com comandos do sistema

```bash
# Listar classes em um JAR (Unix/Linux/Mac)
jar tf meu-app.jar | grep .class$ | sed 's/\.class$//' | tr '/' '.'

# Tamanho total do JAR
jar tf meu-app.jar | xargs -I{} du -b {} | awk '{sum+=$1} END {print sum}'

# Encontrar classes duplicadas
jar tf meu-app.jar | grep .class$ | sort | uniq -d

# Verificar quais bibliotecas um JAR depende
javap -verbose -cp meu-app.jar com.minhaempresa.Main | grep -E "([0-9]+\.[0-9]+\.[0-9]+_?[0-9]*)"
```

### 4. Plugin Maven para análises

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>3.6.0</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>analyze</goal>
                <goal>tree</goal>
                <goal>list</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

---

## 🐛 Debugging de problemas comuns

### Problema 1: ClassNotFoundException

**Causa:** Classe não está no classpath ou JAR não encontrado.

**Solução:**
```bash
# Verificar se classe existe no JAR
jar tf meu-app.jar | grep NomeDaClasse

# Verificar classpath
java -cp meu-app.jar com.minhaempresa.Main
echo %CLASSPATH%  # Windows
echo $CLASSPATH   # Unix

# Usar -verbose:class para ver de onde está carregando
java -verbose:class -jar meu-app.jar
```

### Problema 2: NoClassDefFoundError

**Causa:** Classe presente no classpath mas dependência de outra classe faltando.

**Solução:**
```bash
# Analisar dependências do JAR
jdeps meu-app.jar

# Verificar se todas as dependências estão presentes
java -jar meu-app.jar 2>&1 | grep "ClassNotFoundException"
```

### Problema 3: Main-Class não encontrada

**Causa:** Manifesto sem Main-Class.

**Solução:**
```bash
# Verificar conteúdo do MANIFEST
jar xf meu-app.jar META-INF/MANIFEST.MF
cat META-INF/MANIFEST.MF

# Criar JAR com Main-Class
jar cfe meu-app.jar com.minhaempresa.Main -C build/classes .
```

### Problema 4: Recursos não encontrados

**Solução:**
```java
// DEBUG: Listar recursos disponíveis
public static void debugRecursos(String resourcePath) {
    URL resource = Classe.class.getResource(resourcePath);
    System.out.println("Resource URL: " + resource);
    
    try {
        Enumeration<URL> resources = Classe.class.getClassLoader()
                .getResources(resourcePath);
        while (resources.hasMoreElements()) {
            System.out.println("Found: " + resources.nextElement());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

## 📊 Resumo de comandos úteis

| Comando | Descrição |
|:--------|:----------|
| `jar cf app.jar -C dir .` | Criar JAR |
| `jar cvf app.jar -C dir .` | Criar JAR com saída verbosa |
| `jar cfe app.jar Main -C dir .` | Criar JAR com Main-Class |
| `jar tf app.jar` | Listar conteúdo |
| `jar xf app.jar` | Extrair conteúdo |
| `jar uf app.jar -C dir file` | Atualizar arquivo |
| `java -jar app.jar` | Executar JAR executável |
| `java -cp app.jar Main` | Executar com classe específica |
| `jarsigner -verify app.jar` | Verificar assinatura |
| `jarsigner app.jar alias` | Assinar JAR |
| `jdeps app.jar` | Analisar dependências |

---

## ✅ Boas práticas

1. **Sempre defina um `MANIFEST.MF`** para JARs executáveis com `Main-Class`
2. **Use versionamento** nos nomes dos JARs (ex: `app-1.2.3.jar`)
3. **Evite JARs gigantescos** - divida em módulos quando possível
4. **Documente dependências** em um arquivo separado (ex: `DEPENDENCIES.txt`)
5. **Teste o JAR em diferentes ambientes** antes do deploy
6. **Assine JARs** para distribuição pública
7. **Use recursos via `getResourceAsStream()`** em vez de `File` para código portável
8. **Considere FAT JARs** para microsserviços e ferramentas CLI

---
