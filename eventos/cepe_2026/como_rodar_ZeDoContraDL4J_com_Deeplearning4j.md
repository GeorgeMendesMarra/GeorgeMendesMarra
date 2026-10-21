# Como rodar o `ZeDoContraDL4J.java` com Deeplearning4j (DL4J)

Guia passo a passo para executar a rede neural do "Zé do Contra" usando o **Deeplearning4j** no **Windows** e no **Linux**, com **Maven**.

---

## Sumário

1. [O que você vai precisar](#1-o-que-você-vai-precisar)
2. [Instalação no Windows](#2-instalação-no-windows)
3. [Instalação no Linux](#3-instalação-no-linux)
4. [Criando o projeto](#4-criando-o-projeto)
5. [Executando](#5-executando)
6. [Resultado esperado](#6-resultado-esperado)
7. [Rodando pela IDE](#7-rodando-pela-ide)
8. [Problemas comuns](#8-problemas-comuns)

---

## 1. O que você vai precisar

| Item | Versão recomendada | Para quê |
|---|---|---|
| JDK | **11 ou 17** | Compilar e executar Java |
| Maven | 3.8 ou superior | Baixar o DL4J e compilar |
| Internet | - | Primeiro build baixa algumas centenas de MB |
| Espaço em disco | ~1 GB livre | Dependências nativas do ND4J |

> **Importante:** use JDK 11 ou 17. Versões muito novas (21+) podem dar erro com as bibliotecas nativas da versão `1.0.0-M2.1` do DL4J.

---

## 2. Instalação no Windows

### 2.1 Instalar o JDK 17

Abra o **PowerShell** e execute:

```powershell
winget install EclipseAdoptium.Temurin.17.JDK
```

Ou baixe o instalador em <https://adoptium.net> (escolha "Temurin 17", Windows x64, `.msi`) e marque a opção **"Set JAVA_HOME variable"** durante a instalação.

### 2.2 Instalar o Maven

Com o winget:

```powershell
winget install Apache.Maven
```

**Instalação manual (alternativa):**

1. Baixe o ZIP binário em <https://maven.apache.org/download.cgi>.
2. Extraia em `C:\Program Files\Apache\maven`.
3. Adicione `C:\Program Files\Apache\maven\bin` à variável de ambiente **Path**
   (Painel de Controle → Sistema → Configurações avançadas → Variáveis de ambiente).

### 2.3 Instalar o Visual C++ Redistributable

As bibliotecas nativas do ND4J no Windows precisam do **Microsoft Visual C++ Redistributable 2015-2022 (x64)**:

```powershell
winget install Microsoft.VCRedist.2015+.x64
```

### 2.4 Conferir a instalação

**Feche e abra o PowerShell de novo** e rode:

```powershell
java -version
mvn -version
```

Ambos devem mostrar a versão instalada. Se `mvn` não for encontrado, revise o `Path` e a variável `JAVA_HOME`.

---

## 3. Instalação no Linux

### 3.1 Debian / Ubuntu / Mint

```bash
sudo apt update
sudo apt install -y openjdk-17-jdk maven libgomp1
```

### 3.2 Fedora / RHEL / CentOS

```bash
sudo dnf install -y java-17-openjdk-devel maven libgomp
```

### 3.3 Arch Linux

```bash
sudo pacman -S jdk17-openjdk maven gcc-libs
```

> O pacote `libgomp1` (OpenMP) é exigido pelas bibliotecas nativas do ND4J. Sem ele, ocorre erro de carregamento de biblioteca.

### 3.4 Conferir a instalação

```bash
java -version
mvn -version
```

Se houver mais de um JDK instalado, escolha o 17 com:

```bash
sudo update-alternatives --config java
```

---

## 4. Criando o projeto

A estrutura é a mesma nos dois sistemas:

```
zedocontra-dl4j/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── ZeDoContraDL4J.java
```

### 4.1 Criar as pastas

**Windows (PowerShell):**

```powershell
mkdir zedocontra-dl4j\src\main\java
cd zedocontra-dl4j
```

**Linux:**

```bash
mkdir -p zedocontra-dl4j/src/main/java
cd zedocontra-dl4j
```

### 4.2 Criar o `pom.xml`

Na raiz do projeto (`zedocontra-dl4j/pom.xml`):

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>exemplo</groupId>
    <artifactId>zedocontra-dl4j</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <dl4j.version>1.0.0-M2.1</dl4j.version>
    </properties>

    <dependencies>
        <!-- A rede neural em si -->
        <dependency>
            <groupId>org.deeplearning4j</groupId>
            <artifactId>deeplearning4j-core</artifactId>
            <version>${dl4j.version}</version>
        </dependency>

        <!-- ND4J: arrays numéricos (INDArray) com backend de CPU -->
        <dependency>
            <groupId>org.nd4j</groupId>
            <artifactId>nd4j-native-platform</artifactId>
            <version>${dl4j.version}</version>
        </dependency>

        <!-- Evita avisos de logging no console -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.36</version>
        </dependency>
    </dependencies>
</project>
```

> O `nd4j-native-platform` baixa as bibliotecas nativas de **todos** os sistemas operacionais (Windows, Linux, macOS). Por isso o primeiro build é pesado, mas o mesmo projeto funciona em qualquer máquina.

### 4.3 Criar o `ZeDoContraDL4J.java`

Em `src/main/java/ZeDoContraDL4J.java`:

```java
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class ZeDoContraDL4J {
    public static void main(String[] args) {

        // Arquitetura: 3 entradas -> 4 neurônios ocultos -> 1 saída
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .seed(123)
            .updater(new Sgd(0.5))
            .list()
            .layer(new DenseLayer.Builder().nIn(3).nOut(4)
                .activation(Activation.TANH).build())
            .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
                .nIn(4).nOut(1).activation(Activation.SIGMOID).build())
            .build();

        MultiLayerNetwork rede = new MultiLayerNetwork(conf);
        rede.init();

        // Dados: {sol, amigos, transito} e a resposta esperada (Zé do Contra)
        INDArray x = Nd4j.create(new double[][] {
            {1,1,0}, {1,0,1}, {1,0,0}, {1,1,1},
            {0,1,0}, {0,0,0}, {0,1,1}, {0,0,1}
        });
        INDArray y = Nd4j.create(new double[][] {{1},{1},{0},{0},{0},{0},{0},{0}});

        // Treino (5000 épocas, lote completo)
        for (int epoca = 0; epoca < 5000; epoca++) {
            rede.fit(x, y);
        }

        // Previsão: sol=1, amigos=1, trânsito=0
        INDArray saida = rede.output(Nd4j.create(new double[][] {{1, 1, 0}}));
        System.out.println("Chance de ir: " + saida.getDouble(0));

        // Extra: previsão para todos os cenários
        INDArray todas = rede.output(x);
        for (int i = 0; i < 8; i++) {
            System.out.printf("%s -> %.3f%n", x.getRow(i), todas.getDouble(i));
        }
    }
}
```

---

## 5. Executando

Em qualquer sistema, dentro da pasta `zedocontra-dl4j` (onde está o `pom.xml`):

### 5.1 Compilar e executar direto pelo Maven

**Windows (PowerShell):**

```powershell
mvn compile exec:java "-Dexec.mainClass=ZeDoContraDL4J"
```

**Linux:**

```bash
mvn compile exec:java -Dexec.mainClass=ZeDoContraDL4J
```

> No PowerShell, as aspas em volta do `-Dexec.mainClass=...` são necessárias, senão o ponto é interpretado de forma errada.

Na primeira execução o Maven baixa todas as dependências. As seguintes são bem mais rápidas.

### 5.2 (Opcional) Gerar um JAR executável

Se preferir um único `.jar`, adicione este bloco ao `pom.xml`, logo depois de `</dependencies>`:

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
                    <goals><goal>shade</goal></goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>ZeDoContraDL4J</mainClass>
                            </transformer>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                        </transformers>
                        <filters>
                            <filter>
                                <artifact>*:*</artifact>
                                <excludes>
                                    <exclude>META-INF/*.SF</exclude>
                                    <exclude>META-INF/*.DSA</exclude>
                                    <exclude>META-INF/*.RSA</exclude>
                                </excludes>
                            </filter>
                        </filters>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

Depois gere e execute o JAR (igual no Windows e no Linux):

```bash
mvn clean package
java -jar target/zedocontra-dl4j-1.0.jar
```

O JAR fica grande (centenas de MB) por conter as bibliotecas nativas de todos os sistemas.

---

## 6. Resultado esperado

A saída deve ser parecida com esta (os números exatos podem variar):

```
Chance de ir: 0.97...
[[1.0000, 1.0000, 0]] -> 0.97x
[[1.0000, 0, 1.0000]] -> 0.97x
[[1.0000, 0, 0]] -> 0.02x
[[1.0000, 1.0000, 1.0000]] -> 0.02x
[[0, 1.0000, 0]] -> 0.00x
...
```

O comportamento correto é:

| sol | amigos | trânsito | Esperado | Saída da rede |
|:--:|:--:|:--:|:--:|:--:|
| 1 | 1 | 0 | vai | próxima de 1 |
| 1 | 0 | 1 | vai | próxima de 1 |
| 1 | 0 | 0 | fica | próxima de 0 |
| 1 | 1 | 1 | fica | próxima de 0 |
| 0 | qualquer | qualquer | fica | próxima de 0 |

Se alguma saída ficar perto de 0,5 ou errada, a rede ficou presa em um mínimo local (comum no XOR). Troque o `.seed(123)` por outro valor ou aumente as épocas para 10000.

---

## 7. Rodando pela IDE

### IntelliJ IDEA (Windows e Linux)

1. **File → Open** e selecione a pasta `zedocontra-dl4j` (ou o `pom.xml`).
2. Aguarde o Maven baixar as dependências (barra de progresso no canto inferior).
3. Em **File → Project Structure → Project**, confirme que o SDK é o JDK 11 ou 17.
4. Abra `ZeDoContraDL4J.java` e clique no ícone verde de play ao lado do `main`.

### Eclipse

1. **File → Import → Maven → Existing Maven Projects** e selecione a pasta.
2. Clique com o botão direito no projeto → **Maven → Update Project**.
3. Botão direito em `ZeDoContraDL4J.java` → **Run As → Java Application**.

### VS Code

1. Instale a extensão **Extension Pack for Java**.
2. Abra a pasta do projeto; a extensão reconhece o `pom.xml` automaticamente.
3. Clique em **Run** acima do método `main`.

---

## 8. Problemas comuns

| Sintoma | Causa provável | Solução |
|---|---|---|
| `'mvn' não é reconhecido` / `mvn: command not found` | Maven fora do `Path` | Reinstale ou adicione `...\maven\bin` ao `Path`; reabra o terminal |
| `JAVA_HOME is not defined correctly` | Variável não configurada | Defina `JAVA_HOME` apontando para a pasta do JDK |
| `UnsatisfiedLinkError` / `Could not initialize class org.nd4j...` no **Windows** | Falta o Visual C++ Redistributable | Instale o VC++ 2015-2022 x64 (seção 2.3) |
| `libgomp.so.1: cannot open shared object file` no **Linux** | Falta o OpenMP | `sudo apt install libgomp1` |
| Erros de módulos/acesso ao rodar com Java 21+ | JDK novo demais para esta versão do DL4J | Use o JDK 17 (ou 11) |
| `Could not transfer artifact` / timeout | Sem internet, proxy ou firewall | Verifique a conexão; configure o proxy em `~/.m2/settings.xml` |
| `Error: Could not find or load main class ZeDoContraDL4J` | Nome da classe ou da pasta errados | O arquivo deve estar em `src/main/java/ZeDoContraDL4J.java`, sem `package` |
| Aviso `SLF4J: Failed to load class StaticLoggerBinder` | Faltou o `slf4j-simple` | Mantenha a dependência no `pom.xml` |
| Acentos estranhos no console do Windows | Codificação do terminal | Rode `chcp 65001` antes, ou ignore (não afeta a rede) |
| Resultado não converge | Mínimo local no XOR | Mude a `seed` ou aumente as épocas |

### Dica: reduzir o tamanho do download

Se quiser baixar apenas as bibliotecas do seu sistema, troque `nd4j-native-platform` por `nd4j-native` com o classificador correspondente:

```xml
<!-- Windows -->
<dependency>
    <groupId>org.nd4j</groupId>
    <artifactId>nd4j-native</artifactId>
    <version>${dl4j.version}</version>
    <classifier>windows-x86_64</classifier>
</dependency>

<!-- Linux -->
<dependency>
    <groupId>org.nd4j</groupId>
    <artifactId>nd4j-native</artifactId>
    <version>${dl4j.version}</version>
    <classifier>linux-x86_64</classifier>
</dependency>
```

Você também precisa de `openblas` com o mesmo classificador (`org.bytedeco:openblas`) nesse modo, por isso, para um exemplo simples, `nd4j-native-platform` continua sendo o caminho mais fácil.

---

## Observação sobre a versão

A API usada aqui (`MultiLayerConfiguration`, `NeuralNetConfiguration.Builder`) pertence à série `1.0.0-M2.1` do DL4J. O projeto teve pouca atividade recente, então vale conferir no [Maven Central](https://central.sonatype.com/search?q=deeplearning4j) se existe versão mais nova antes de iniciar um projeto sério.
