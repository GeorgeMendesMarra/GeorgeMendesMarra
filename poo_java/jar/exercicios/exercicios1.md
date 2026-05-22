**QUESTÃO 1 - (0,5 pontos)**

O formato JAR (Java ARchive) é o padrão para distribuição de bibliotecas e aplicações Java. Sobre as características fundamentais dos arquivos JAR, analise as alternativas abaixo e assinale a correta.

A. Um arquivo JAR é baseado no formato ZIP e pode conter arquivos `.class` compilados, recursos como imagens e propriedades, e um arquivo especial chamado `MANIFEST.MF` localizado no diretório `META-INF/`, que contém metadados como a versão do manifesto, a classe principal (Main-Class) para aplicações executáveis e o classpath necessário para a aplicação funcionar corretamente.

B. Um arquivo JAR é um formato proprietário da Oracle que não pode ser aberto ou manipulado por ferramentas ZIP comuns, sendo necessário utilizar ferramentas específicas do JDK como `jar.exe` ou `jarsigner`, pois o formato utiliza criptografia proprietária para proteger o código fonte Java.

C. O arquivo JAR pode conter apenas arquivos `.class` compilados, não sendo permitido incluir recursos como imagens, arquivos de propriedades ou documentação, pois isso aumentaria desnecessariamente o tamanho do arquivo e violaria as especificações do formato JAR.

D. O diretório `META-INF/` dentro de um JAR é opcional e, quando presente, pode conter apenas o arquivo `MANIFEST.MF`, sendo que qualquer outro arquivo neste diretório é ignorado pela JVM e não pode ser acessado programaticamente pela aplicação.

E. Um arquivo JAR não pode ser executado diretamente pela JVM, sendo necessário sempre extrair seu conteúdo antes da execução, pois a JVM não possui capacidade nativa de ler arquivos JAR como classpath, exigindo que os arquivos `.class` estejam no sistema de arquivos.

---

**QUESTÃO 2 - (0,5 pontos)**

O comando `jar` é a ferramenta padrão do JDK para criar, visualizar e extrair arquivos JAR. Sobre as opções e o uso do comando `jar`, analise as alternativas e assinale a correta.

A. O comando `jar` possui opções principais como `c` (create - criar novo JAR), `x` (extract - extrair conteúdo), `t` (table - listar conteúdo), `u` (update - atualizar), `v` (verbose - saída detalhada), `f` (file - especificar nome do arquivo), `m` (manifest - incluir arquivo de manifesto) e `e` (entry point - definir classe principal), sendo que para criar um JAR executável com classe principal definida usa-se `jar cfe meuapp.jar com.minhaempresa.Main -C build/classes .`.

B. O comando `jar` possui apenas duas opções: `-create` para criar JARs e `-extract` para extrair, sendo que todas as outras operações (listar, atualizar, definir classe principal) não são suportadas diretamente, exigindo scripts externos complexos.

C. Para listar o conteúdo de um JAR, o comando correto é `jar -list meuapp.jar`, não existindo a opção `t` (table), que foi descontinuada após o Java 8 e substituída por opções verbosas como `--list --file`.

D. A opção `-C` no comando `jar` é utilizada para compactar o JAR com maior taxa de compressão (similar ao gzip), não tendo relação com a mudança de diretório durante a inclusão de arquivos, sendo que para incluir arquivos de diretórios diferentes é necessário usar múltiplos comandos `jar` separados.

E. O comando `jar` não permite a atualização de um JAR existente (opção `u`), pois os arquivos JAR são imutáveis após a criação, sendo necessário recriar o JAR completamente sempre que qualquer arquivo for modificado.

---

**QUESTÃO 3 - (0,5 pontos)**

O arquivo `MANIFEST.MF` é um componente essencial em muitos JARs, especialmente aqueles que são executáveis. Sobre o arquivo de manifesto e suas entradas, analise as alternativas e assinale a correta.

A. O arquivo `MANIFEST.MF` está localizado no diretório `META-INF/` dentro do JAR e contém entradas no formato `nome: valor`, sendo que as entradas mais importantes incluem `Manifest-Version` (versão do formato do manifesto), `Main-Class` (nome totalmente qualificado da classe que contém o método `main` para JARs executáveis), `Class-Path` (caminhos para outras bibliotecas JAR necessárias) e entradas opcionais como `Implementation-Title`, `Implementation-Version` e `Implementation-Vendor`.

B. O arquivo `MANIFEST.MF` é opcional e, quando presente, deve estar na raiz do JAR (não dentro de `META-INF/`), e suas entradas são escritas no formato `chave=valor` (com sinal de igual), similar a arquivos de propriedades Java.

C. A entrada `Main-Class` no manifesto deve conter apenas o nome simples da classe (ex: `Main`), não o nome totalmente qualificado com pacote, pois a JVM automaticamente procura a classe no pacote raiz do JAR, independentemente do pacote onde ela está definida.

D. A entrada `Class-Path` no manifesto aceita apenas caminhos absolutos no sistema de arquivos (ex: `C:\lib\biblioteca.jar`), não sendo possível usar caminhos relativos ao diretório do JAR, o que torna os JARs menos portáveis entre diferentes ambientes.

E. O arquivo `MANIFEST.MF` não pode ser editado manualmente, sendo gerado exclusivamente pelo comando `jar` ou por ferramentas de build, e qualquer tentativa de modificá-lo com um editor de texto corrompe o JAR, tornando-o inválido para execução.

---

**QUESTÃO 4 - (0,5 pontos)**

Para executar uma aplicação empacotada em um arquivo JAR, existem diferentes abordagens dependendo de como o JAR foi construído. Sobre a execução de arquivos JAR, analise as alternativas e assinale a correta.

A. Um JAR pode ser executado de duas formas principais: se o `MANIFEST.MF` contiver a entrada `Main-Class`, pode-se usar o comando `java -jar meuapp.jar`; caso contrário, ou se for necessário especificar outra classe principal, usa-se `java -cp meuapp.jar com.minhaempresa.Main`, sendo que também é possível passar argumentos para a aplicação ao final do comando (ex: `java -jar meuapp.jar arg1 arg2`).

B. Para executar qualquer JAR, é obrigatório que o `MANIFEST.MF` contenha a entrada `Main-Class`, não existindo alternativa para executar um JAR sem essa entrada, pois o comando `java -jar` falha e `java -cp` não funciona com arquivos JAR, apenas com diretórios de classes.

C. A execução de um JAR com `java -jar` não permite a passagem de argumentos para a aplicação, sendo que todos os argumentos após o nome do JAR são interpretados como opções da JVM (ex: `-Xmx512m`), não sendo possível passar argumentos para o método `main`.

D. Ao usar `java -cp meuapp.jar com.minhaempresa.Main`, o classpath deve incluir também todas as dependências do JAR manualmente, pois o JAR não carrega automaticamente bibliotecas referenciadas no `Class-Path` do manifesto quando executado com `-cp`.

E. Para executar um JAR que depende de outros JARs, é necessário extrair todos os JARs em um único diretório antes da execução, pois a JVM não consegue resolver dependências quando múltiplos JARs estão presentes no classpath, sendo que o comando `java -jar` suporta apenas um único arquivo JAR.

---

**QUESTÃO 5 - (0,5 pontos)**

A ferramenta `jarsigner` é utilizada para assinar digitalmente arquivos JAR, garantindo autenticidade e integridade. Sobre a assinatura de JARs e o comando `jarsigner`, analise as alternativas e assinale a correta.

A. O comando `jarsigner` utiliza um keystore (armazenamento de chaves) contendo certificados digitais para assinar um JAR, sendo que o comando básico é `jarsigner -keystore meukeystore.jks -storepass senha meuapp.jar alias-chave`, e a verificação da assinatura é feita com `jarsigner -verify -verbose -certs meuapp.jar`, que exibe detalhes sobre o certificado usado na assinatura.

B. O comando `jarsigner` é utilizado para compactar JARs, não para assinar, sendo que o nome "signer" é enganoso; na verdade, a ferramenta foi renomeada para `jarsqueeze` nas versões mais recentes do JDK, e a assinatura digital de JARs não é mais suportada.

C. Para assinar um JAR, é necessário primeiro converter o arquivo para o formato JKS (Java Key Store) usando a ferramenta `jar2jks`, pois o `jarsigner` não trabalha diretamente com arquivos JAR, apenas com keystores, sendo que o JAR assinado é gerado em um arquivo separado com extensão `.jks`.

D. A verificação de assinatura com `jarsigner -verify` sempre retorna `true` (assinatura válida) mesmo se o JAR não foi assinado ou se a assinatura foi corrompida, pois a ferramenta não realiza validação criptográfica real, apenas verifica a existência do arquivo `META-INF/*.SF`.

E. A assinatura de JARs com `jarsigner` é irreversível, não sendo possível remover ou substituir uma assinatura existente; uma vez assinado, o JAR não pode mais ser modificado ou atualizado de nenhuma forma, mesmo por seu próprio autor.

---

**QUESTÃO 6 - (0,5 pontos)**

O termo "FAT JAR" (ou Uber JAR) refere-se a um arquivo JAR que contém não apenas as classes da aplicação, mas também todas as suas dependências embutidas no mesmo arquivo. Sobre FAT JARs e suas características, analise as alternativas e assinale a correta.

A. Um FAT JAR é um arquivo JAR que inclui todas as bibliotecas das quais a aplicação depende (seus próprios JARs) dentro de um único arquivo, facilitando a distribuição e execução pois elimina a necessidade de gerenciar múltiplos JARs separados, sendo frequentemente criado com plugins como Maven Shade, Maven Assembly ou Gradle Shadow, embora possa causar conflitos de classes se diferentes bibliotecas incluírem classes com o mesmo nome.

B. Um FAT JAR é um JAR que foi compactado utilizando o algoritmo de compressão LZMA em vez do ZIP padrão, resultando em arquivos muito menores, e o termo "FAT" refere-se ao sistema de arquivos (File Allocation Table) usado para organizar o conteúdo compactado.

C. FAT JAR é um formato proprietário da Oracle que não segue as especificações padrão de JAR, sendo que para executar um FAT JAR é necessário um classloader especial fornecido pelo plugin de construção, não sendo suportado pela JVM padrão.

D. Um FAT JAR não pode conter arquivos de manifesto personalizados, sendo que o `MANIFEST.MF` é sempre gerado automaticamente sem possibilidade de customização, o que impede a definição de uma classe principal ou de entradas de classpath.

E. FAT JARs são desencorajados em ambientes de produção porque tornam a aplicação mais lenta para iniciar (devido ao maior tamanho do arquivo) e não permitem o compartilhamento de bibliotecas comuns entre múltiplas aplicações, sendo recomendado apenas para protótipos e desenvolvimento local.

---

**QUESTÃO 7 - (0,5 pontos)**

A API `java.util.jar` fornece classes para manipulação programática de arquivos JAR. Sobre as classes `JarFile`, `JarEntry` e `JarOutputStream`, analise as alternativas e assinale a correta.

A. A classe `JarFile` permite ler o conteúdo de um arquivo JAR, fornecendo métodos como `entries()` para obter uma enumeração de `JarEntry` (representando cada entrada no JAR), `getInputStream(JarEntry)` para obter um fluxo de leitura do conteúdo da entrada, e `getManifest()` para acessar o objeto `Manifest` que representa o arquivo `MANIFEST.MF`, permitindo a leitura programática de qualquer recurso dentro do JAR.

B. A classe `JarFile` só pode ser usada para ler JARs que não possuem assinatura digital, pois a presença de arquivos `.SF` e `.DSA` no diretório `META-INF/` impede a abertura do JAR, lançando uma exceção `SecurityException`.

C. A classe `JarOutputStream` é utilizada para extrair arquivos de um JAR, não para criá-los, sendo que para criar um JAR programaticamente deve-se usar a classe `ZipOutputStream` diretamente, pois `JarOutputStream` foi descontinuada após o Java 11.

D. O método `entries()` da classe `JarFile` retorna um `List<JarEntry>` ordenado alfabeticamente, não uma `Enumeration<JarEntry>`, e a iteração sobre as entradas requer conversão para array antes do processamento.

E. A classe `JarEntry` não permite distinguir entre diretórios e arquivos, sendo que todas as entradas são tratadas como arquivos, exigindo que o desenvolvedor verifique manualmente se o nome termina com "/" para identificar diretórios, pois não há método como `isDirectory()`.

---

**QUESTÃO 8 - (0,5 pontos)**

O Maven possui plugins específicos para manipulação de JARs, incluindo a criação de JARs executáveis e a inclusão de recursos no manifesto. Sobre o plugin `maven-jar-plugin` e suas configurações, analise as alternativas e assinale a correta.

A. O `maven-jar-plugin` é configurado no `<build><plugins>` do `pom.xml`, permitindo definir a classe principal através do elemento `<manifest><mainClass>`, adicionar entradas personalizadas ao manifesto com `<manifestEntries>`, e configurar o classpath com `<addClasspath>true</addClasspath>` e `<classpathPrefix>lib/</classpathPrefix>`, sendo que o plugin executa automaticamente na fase `package` do ciclo de vida do Maven.

B. O `maven-jar-plugin` não permite a personalização do manifesto do JAR, sendo que o arquivo `MANIFEST.MF` gerado contém apenas a versão do manifesto e a data de criação, não sendo possível adicionar `Main-Class` ou outras entradas sem modificar o código fonte do plugin.

C. Para criar um JAR executável com Maven, é necessário usar obrigatoriamente o `maven-assembly-plugin`, pois o `maven-jar-plugin` não suporta a definição de `Main-Class` no manifesto, sendo limitado a criar apenas JARs de biblioteca (não executáveis).

D. O `maven-jar-plugin` executa na fase `compile`, não na fase `package`, gerando o JAR antes mesmo da compilação das classes, o que frequentemente resulta em um JAR vazio ou incompleto se a configuração não for ajustada manualmente.

E. O elemento `<classpathPrefix>` no `maven-jar-plugin` define o prefixo para os caminhos das dependências no manifesto, mas seu valor é ignorado se `<addClasspath>` estiver definido como `false`, sendo que o comportamento padrão quando `addClasspath` é `true` é usar classpath absoluto, não relativo.

---

**QUESTÃO 9 - (0,5 pontos)**

Recursos (resources) como arquivos de propriedades, imagens e XML devem ser acessados de forma portável dentro de um JAR. Sobre o acesso a recursos empacotados em JARs, analise as alternativas e assinale a correta.

A. Para acessar um recurso dentro de um JAR, deve-se usar o método `Class.getResourceAsStream(String name)`, que retorna um `InputStream` independentemente de o recurso estar no sistema de arquivos ou dentro de um JAR, sendo que o caminho deve começar com `/` para indicar a raiz do classpath (ex: `/config/app.properties`) ou ser relativo ao pacote da classe se não usar `/`.

B. Recursos dentro de um JAR não podem ser acessados como `InputStream`, sendo necessário extrair o JAR primeiro usando `JarFile` e depois ler o arquivo extraído, pois a JVM não fornece uma API para ler recursos diretamente de dentro de um JAR em tempo de execução.

C. O método `Class.getResourceAsStream()` retorna `null` quando o recurso está dentro de um JAR, funcionando apenas para recursos no sistema de arquivos, sendo necessário usar `ClassLoader.getSystemResourceAsStream()` para recursos dentro de JARs.

D. Para acessar um recurso dentro de um JAR, o caminho deve ser absoluto no sistema de arquivos (ex: `/home/user/projeto/config/app.properties`), pois o recurso perde sua localização relativa quando empacotado, e a JVM não mantém informações sobre a estrutura de diretórios original.

E. Recursos em JARs não podem ser modificados em tempo de execução, mas também não podem ser lidos como `InputStream` se o JAR estiver assinado digitalmente, pois a assinatura bloqueia o acesso de leitura a todos os recursos, permitindo apenas a execução de classes.

---

**QUESTÃO 10 - (0,5 pontos)**

O Gradle também oferece suporte à criação e manipulação de JARs através de seu modelo de build. Sobre a configuração do plugin `java` ou `application` do Gradle para gerar JARs, analise as alternativas e assinale a correta.

A. No Gradle, o plugin `java` adiciona a task `jar` que cria um JAR com as classes compiladas e recursos, e o manifesto pode ser configurado através do bloco `jar { manifest { attributes('Main-Class': 'com.minhaempresa.Main') } }`, sendo que o plugin `application` adiciona a task `distZip` e `distTar` para criar distribuições completas, e a task `installDist` para instalar a aplicação localmente.

B. No Gradle, o plugin `java` não gera um JAR automaticamente; é necessário declarar explicitamente a task `createJar` com configurações detalhadas, pois a task `jar` padrão foi removida após o Gradle 6.0 por questões de segurança.

C. O Gradle não suporta a criação de JARs executáveis com classe principal definida no manifesto, sendo necessário utilizar o plugin `maven-jar-plugin` em conjunto com o Gradle, o que torna o processo complexo e propenso a erros.

D. A task `jar` do Gradle gera o JAR no diretório `build/libs` com o nome padrão `<project-name>.jar`, não sendo possível customizar o nome do arquivo gerado, pois o Gradle não expõe essa configuração para o desenvolvedor.

E. O plugin `application` do Gradle não gera um JAR executável, apenas scripts de inicialização (`.bat` e `.sh`), sendo que a criação de um JAR com `Main-Class` deve ser feita manualmente com configurações avançadas usando o plugin `java-library`.

---

**QUESTÃO 11 - (0,5 pontos)**

O conceito de "JAR Hell" refere-se a problemas comuns que surgem ao trabalhar com múltiplos JARs em um projeto. Sobre os problemas e soluções relacionados ao JAR Hell, analise as alternativas e assinale a correta.

A. "JAR Hell" refere-se a problemas como conflitos de versão de bibliotecas (quando duas dependências exigem versões diferentes da mesma biblioteca), classes duplicadas com o mesmo nome em JARs diferentes (causando ambiguidade de classloading), e dependências transitivas faltando (quando um JAR depende de outro que não está presente no classpath), sendo que ferramentas como Maven, Gradle e Ivy ajudam a resolver esses problemas através de resolução transitiva de dependências e análise de conflitos.

B. "JAR Hell" é um termo que descreve a dificuldade de descompactar arquivos JAR corrompidos, sendo resolvido com o uso de ferramentas de recuperação de arquivos ZIP, não tendo relação com dependências de bibliotecas ou versionamento.

C. O termo "JAR Hell" refere-se a um bug na JVM que ocorre quando mais de 1000 JARs estão presentes no classpath, causando lentidão extrema na inicialização (daí o termo "hell" - inferno), e a única solução é reduzir o número de JARs combinando-os em JARs maiores.

D. Os problemas de JAR Hell não podem ser resolvidos por ferramentas automáticas como Maven, sendo necessário que o desenvolvedor gerencie manualmente todas as dependências e suas versões, testando cada combinação possível para garantir compatibilidade.

E. JAR Hell ocorre exclusivamente quando um JAR é assinado digitalmente e outro não, causando conflitos de segurança no classloader da JVM, sendo que a única solução é assinar todos os JARs do projeto com o mesmo certificado.

---

**QUESTÃO 12 - (0,5 pontos)**

JARs podem ser criados com diferentes níveis de compressão, e o formato ZIP subjacente suporta tanto compressão quanto armazenamento sem compressão. Sobre compressão em arquivos JAR, analise as alternativas e assinale a correta.

A. O comando `jar` utiliza compressão padrão similar ao ZIP (algoritmo DEFLATE), mas a opção `0` (zero) pode ser usada para armazenar arquivos sem compressão (`jar c0f meuapp.jar -C build/classes .`), o que pode ser útil para JARs que serão carregados frequentemente (pois a descompressão consome CPU), embora resulte em arquivos maiores em disco.

B. Arquivos JAR não suportam compressão, sendo sempre armazenados sem compressão, pois a especificação do formato JAR exige que os arquivos não sejam compactados para permitir acesso aleatório eficiente às classes individuais dentro do JAR.

C. A compressão em JARs é sempre máxima e não pode ser configurada, sendo que o comando `jar` não oferece opções para controlar o nível de compressão, que é fixo e definido pela implementação da JVM, variando entre diferentes fornecedores de JDK.

D. A compressão de JARs deve ser evitada a todo custo porque arquivos compactados não podem ser assinados digitalmente, sendo que a assinatura requer que o conteúdo do JAR não seja modificado após a assinatura, e a compressão altera o conteúdo binário.

E. A opção `0` (zero) do comando `jar` não desabilita a compressão, mas sim ativa o nível máximo de compressão (compressão nível 0 é a mais forte), sendo que o nome da opção é enganoso e frequentemente causa confusão entre desenvolvedores iniciantes.

---

**QUESTÃO 13 - (0,5 pontos)**

O formato WAR (Web ARchive) é uma extensão do formato JAR utilizada para aplicações web Java. Sobre as diferenças entre JAR e WAR, analise as alternativas e assinale a correta.

A. Um arquivo WAR (Web ARchive) segue a mesma estrutura básica de um JAR, mas adiciona requisitos específicos como a presença do diretório `WEB-INF/` contendo o arquivo `web.xml` (ou anotações equivalentes) e os subdiretórios `WEB-INF/classes/` (para classes compiladas) e `WEB-INF/lib/` (para JARs de dependências), sendo que WARs são implantados em servidores de aplicação web como Tomcat, Jetty ou WildFly.

B. JAR e WAR são formatos completamente diferentes e incompatíveis, sendo que JARs usam compressão LZMA enquanto WARs usam compressão BZIP2, e não é possível converter um formato no outro ou extrair um WAR com ferramentas JAR padrão.

C. A principal diferença entre JAR e WAR é que WARs não podem conter um arquivo `MANIFEST.MF`, pois o manifesto é substituído por um arquivo `application.xml` na raiz do WAR, que contém metadados específicos para aplicações web.

D. WARs são executados diretamente pela JVM com o comando `java -war aplicacao.war`, sem necessidade de um servidor de aplicação, pois o formato WAR foi projetado para substituir JARs executáveis em aplicações web standalone.

E. Ao contrário dos JARs, os WARs não podem conter arquivos de recursos (como imagens, CSS e JavaScript) além das classes Java, sendo limitados apenas a servlets e JSPs, o que os torna inadequados para aplicações web modernas com front-end rico.

---

**QUESTÃO 14 - (0,5 pontos)**

O Maven possui o plugin `maven-dependency-plugin` que oferece funcionalidades para análise de dependências de JARs. Sobre as capacidades deste plugin, analise as alternativas e assinale a correta.

A. O `maven-dependency-plugin` permite analisar as dependências de um projeto com goals como `analyze` (identifica dependências declaradas mas não usadas, e dependências usadas mas não declaradas), `tree` (exibe a árvore de dependências transitivas), `list` (lista todas as dependências resolvidas) e `copy-dependencies` (copia os JARs das dependências para um diretório especificado), sendo útil para depurar problemas de classpath e reduzir o tamanho de distribuições.

B. O `maven-dependency-plugin` é utilizado exclusivamente para baixar dependências de repositórios remotos, não oferecendo nenhuma funcionalidade de análise ou listagem, sendo que os goals `analyze` e `tree` não existem neste plugin.

C. O goal `analyze` do `maven-dependency-plugin` requer acesso à internet para funcionar, pois consulta um serviço web da Apache que mantém um banco de dados de todas as dependências Maven e suas utilizações típicas, sendo que em ambientes offline o plugin falha silenciosamente.

D. O goal `copy-dependencies` copia os JARs das dependências para o diretório `target/lib`, mas não respeita o escopo das dependências (ex: dependências `test` são copiadas mesmo em builds de produção), exigindo configuração manual complexa para filtrar os escopos desejados.

E. O `maven-dependency-plugin` foi descontinuado a partir do Maven 3.6 e suas funcionalidades foram movidas para o `maven-jar-plugin`, sendo que os goals `analyze` e `tree` agora são fornecidos pelo plugin `maven-project-info-reports-plugin`.

---

**QUESTÃO 15 - (0,5 pontos)**

A ferramenta `jdeps` (Java Dependency Analyzer) foi introduzida no Java 8 para analisar dependências de classes em nível de pacote. Sobre o uso do `jdeps` com arquivos JAR, analise as alternativas e assinale a correta.

A. O comando `jdeps meuapp.jar` analisa as dependências de classes dentro do JAR, mostrando para cada pacote quais outros pacotes ele depende, identificando dependências internas (dentro do mesmo JAR) e externas (para classes da JDK ou outros JARs), sendo útil para entender a estrutura do código, detectar dependências cíclicas e planejar a modularização com o Project Jigsaw (Java 9+).

B. O `jdeps` não funciona com arquivos JAR, apenas com diretórios de classes compiladas (`.class`), sendo que para analisar um JAR é necessário primeiro extraí-lo usando `jar xf` e depois executar o `jdeps` no diretório extraído.

C. A ferramenta `jdeps` foi descontinuada no Java 11 e removida no Java 17, sendo substituída pela ferramenta `javap` com a opção `-depend`, que oferece funcionalidade similar mas com saída em formato JSON.

D. O `jdeps` analisa apenas dependências estáticas em tempo de compilação, não sendo capaz de detectar dependências dinâmicas criadas via `Class.forName()` ou reflexão, o que limita sua utilidade para análise de frameworks que usam carregamento dinâmico de classes.

E. Para usar o `jdeps` com um JAR que depende de outros JARs, é necessário fornecer todos os JARs de dependência no classpath com a opção `-cp`, caso contrário as dependências externas aparecerão como "not found", e o `jdeps` não consegue baixar automaticamente as dependências de repositórios Maven.

---

**QUESTÃO 16 - (0,5 pontos)**

O versionamento de JARs é uma prática importante para gerenciar diferentes releases de uma biblioteca. Sobre as convenções de versionamento de JARs e o sufixo SNAPSHOT no Maven, analise as alternativas e assinale a correta.

A. A convenção comum para nomear JARs versionados é `nome-<versão>.jar` (ex: `meuapp-1.2.3.jar`), onde a versão segue o padrão `major.minor.patch` (semântico), e no Maven o sufixo `-SNAPSHOT` (ex: `1.2.3-SNAPSHOT`) indica uma versão em desenvolvimento que ainda não foi lançada oficialmente, sendo que repositórios Maven permitem múltiplas atualizações para a mesma versão SNAPSHOT ao longo do tempo.

B. O versionamento de JARs não é necessário, pois o nome do arquivo JAR não afeta a execução ou resolução de dependências, sendo que todas as versões de uma biblioteca podem usar o mesmo nome de arquivo (ex: `biblioteca.jar`), e o Maven ignora o nome do arquivo, usando apenas as coordenadas do `pom.xml`.

C. No Maven, o sufixo `-SNAPSHOT` indica uma versão estável e final de uma biblioteca, sendo que a versão SNAPSHOT nunca é alterada após publicada, diferentemente das versões release que podem ser atualizadas a qualquer momento durante o desenvolvimento.

D. A versão de um JAR deve ser sempre um número inteiro crescente (ex: `1`, `2`, `3`), não sendo permitido o uso de múltiplos componentes separados por pontos, pois o Maven não suporta parsing de versões com mais de um separador.

E. JARs com sufixo `-SNAPSHOT` não podem ser publicados em repositórios remotos como Maven Central, sendo restritos a repositórios locais ou corporativos, e sua resolução pelo Maven é desativada por padrão, exigindo configuração especial no `settings.xml`.

---

**QUESTÃO 17 - (0,5 pontos)**

O plugin `maven-shade-plugin` é amplamente utilizado para criar FAT JARs, oferecendo recursos avançados para resolver conflitos de classes. Sobre as capacidades do `maven-shade-plugin`, analise as alternativas e assinale a correta.

A. O `maven-shade-plugin` permite criar um FAT JAR (Uber JAR) que inclui todas as dependências, e oferece transformadores (transformers) para lidar com arquivos de serviços (como `META-INF/services/`), recursos duplicados (ex: arquivos `META-INF/LICENSE` de múltiplos JARs), e permite o "relocation" (renomeação) de pacotes para evitar conflitos de versão quando bibliotecas diferentes incluem classes com os mesmos nomes.

B. O `maven-shade-plugin` é utilizado exclusivamente para ofuscar código Java, não para criar FAT JARs, sendo que o nome "shade" refere-se ao escurecimento do código fonte para dificultar engenharia reversa, e a criação de FAT JARs é feita pelo `maven-assembly-plugin`.

C. O recurso de "relocation" do `maven-shade-plugin` renomeia pacotes no código fonte original (antes da compilação), exigindo que o desenvolvedor mantenha duas versões diferentes do código fonte, uma com os pacotes originais e outra com os pacotes renomeados.

D. O `maven-shade-plugin` não suporta a inclusão de arquivos de manifesto personalizados, sendo que o `MANIFEST.MF` gerado contém apenas informações mínimas, não sendo possível definir `Main-Class` ou `Class-Path` no FAT JAR gerado por este plugin.

E. Ao utilizar o `maven-shade-plugin`, as dependências transitivas não são incluídas automaticamente no FAT JAR, sendo necessário listar manualmente cada dependência (incluindo as transitivas) no elemento `<includes>` do plugin, caso contrário elas são excluídas.

---

**QUESTÃO 18 - (0,5 pontos)**

O comando `jar` também pode ser usado para verificar a integridade de arquivos JAR e listar seu conteúdo com diferentes níveis de detalhe. Sobre essas operações de verificação e listagem, analise as alternativas e assinale a correta.

A. O comando `jar tf meuapp.jar` lista o conteúdo do JAR (todos os nomes de arquivos e diretórios), enquanto `jar tvf meuapp.jar` adiciona informações detalhadas como tamanho do arquivo, data de modificação e flags de permissão, sendo que o comando também pode ser usado para verificar se um JAR está corrompido através da opção `-d` (verify) que valida a integridade do arquivo ZIP subjacente.

B. O comando `jar` não possui opção para listar conteúdo detalhado com tamanhos e datas, sendo que a opção `v` (verbose) apenas exibe os nomes dos arquivos com um prefixo indicando se é diretório ou arquivo, não mostrando tamanhos ou timestamps, que só estão disponíveis via ferramentas ZIP externas.

C. Para listar o conteúdo de um JAR com informações detalhadas, é necessário primeiro extrair o JAR usando `jar xf` e depois usar o comando `ls -la` no diretório extraído, pois o `jar tvf` não existe e a combinação das opções `t` e `v` causa erro de sintaxe.

D. O comando `jar` não possui verificação de integridade (`-d` ou equivalente), sendo que a única forma de verificar se um JAR está corrompido é tentar extrair todo o seu conteúdo e verificar se há erros de CRC durante a extração.

E. A opção `t` (table) do comando `jar` lista apenas os arquivos `.class` dentro do JAR, ignorando recursos como imagens, propriedades e arquivos XML, sendo necessário usar `jar x` para extrair e depois verificar manualmente os recursos.

---

**QUESTÃO 19 - (0,5 pontos)**

A modularização introduzida no Java 9 (Project Jigsaw) trouxe o formato JMOD e a modularização de JARs. Sobre JARs modulares e o formato JMOD, analise as alternativas e assinale a correta.

A. Um JAR modular é um JAR comum que contém um arquivo `module-info.class` (compilado a partir de `module-info.java`) na raiz do JAR, que define o nome do módulo, suas dependências (`requires`), e quais pacotes são exportados (`exports`), permitindo a utilização do sistema de módulos da plataforma Java (JPMS) com ferramentas como `jlink` para criar imagens de runtime customizadas.

B. JARs modulares não podem ser executados com o comando `java -jar`, sendo necessário usar o novo comando `java --module` e especificar o módulo e a classe principal separadamente, pois a JVM modular não reconhece mais a entrada `Main-Class` no manifesto de JARs modulares.

C. O formato JMOD (Java Module) substituiu completamente o formato JAR no Java 9, sendo que JARs tradicionais não são mais suportados como formato de distribuição para bibliotecas Java, forçando todos os desenvolvedores a migrar para JMOD.

D. O sistema de módulos do Java 9 (JPMS) não funciona com JARs, sendo que para criar uma aplicação modular é necessário compilar o código diretamente para o formato JMOD usando o comando `jmod`, não sendo possível usar JARs tradicionais em aplicações modulares.

E. A presença de um arquivo `module-info.class` em um JAR impede sua execução em versões do Java anteriores à 9, mas o arquivo é opcional e JARs sem ele continuam funcionando como "JARs automáticos" no classpath do módulo, sendo colocados em um módulo automático com nome derivado do nome do JAR.

---

**QUESTÃO 20 - (0,5 pontos)**

A ferramenta `jlink` permite criar imagens de runtime customizadas que contêm apenas os módulos necessários para uma aplicação. Sobre o uso do `jlink` com JARs modulares, analise as alternativas e assinale a correta.

A. O `jlink` é uma ferramenta que cria uma imagem de runtime personalizada contendo apenas os módulos JDK necessários para a aplicação (reduzindo drasticamente o tamanho da distribuição) e pode incluir JARs modulares da aplicação, produzindo uma pasta com um executável nativo que não requer um JDK/JRE separado para execução, embora os JARs modulares devam estar em formato modular com `module-info.class`.

B. O `jlink` é utilizado para assinar digitalmente JARs modulares, não para criar imagens de runtime, sendo que o nome "jlink" vem de "Java Linker" e sua função é similar ao `jarsigner`, mas para módulos em vez de JARs tradicionais.

C. Para usar o `jlink`, é necessário que todos os JARs da aplicação estejam no formato JMOD, não sendo possível usar JARs modulares tradicionais, pois o `jlink` não entende o formato JAR, apenas JMOD, exigindo conversão prévia com a ferramenta `jmod`.

D. O `jlink` só funciona no Linux e macOS, não havendo suporte para Windows, pois a criação de executáveis nativos depende de recursos específicos de sistemas Unix, e a Oracle não implementou o suporte multiplataforma para esta ferramenta.

E. A imagem gerada pelo `jlink` é específica para a plataforma onde foi criada (ex: não se pode criar uma imagem para Windows a partir de um sistema Linux), e o executável gerado requer um JDK completo instalado no sistema de destino, anulando o benefício de distribuição autônoma.

---

### Gabarito

| Questão | Alternativa Correta |
|:-------:|:-------------------:|
| 1 | A |
| 2 | A |
| 3 | A |
| 4 | A |
| 5 | A |
| 6 | A |
| 7 | A |
| 8 | A |
| 9 | A |
| 10 | A |
| 11 | A |
| 12 | A |
| 13 | A |
| 14 | A |
| 15 | A |
| 16 | A |
| 17 | A |
| 18 | A |
| 19 | A |
| 20 | A |

---

### Justificativas das alternativas corretas

1. **Q1 (Fundamentos do JAR):** Descreve corretamente a estrutura JAR baseada em ZIP, com diretório `META-INF/` contendo `MANIFEST.MF` e metadados como `Main-Class` e `Class-Path`.

2. **Q2 (Comando jar):** Explica corretamente as opções `c`, `x`, `t`, `u`, `v`, `f`, `m`, `e` e o uso de `-C` com exemplo `jar cfe meuapp.jar com.minhaempresa.Main -C build/classes .`.

3. **Q3 (MANIFEST.MF):** Descreve corretamente o manifesto em `META-INF/`, formato `nome: valor`, e entradas `Main-Class`, `Class-Path`, `Implementation-Title`, etc.

4. **Q4 (Execução de JARs):** Explica corretamente `java -jar` para JARs com `Main-Class` e `java -cp` quando não há, com passagem de argumentos ao final.

5. **Q5 (jarsigner):** Descreve corretamente o uso do `jarsigner` com keystore, opções `-verify -verbose -certs`, e comandos para assinar e verificar.

6. **Q6 (FAT JAR):** Explica corretamente FAT JAR como JAR com todas dependências embutidas, plugins como Maven Shade/Gradle Shadow, e possibilidade de conflitos de classes.

7. **Q7 (API java.util.jar):** Descreve corretamente `JarFile` com `entries()`, `getInputStream()`, `getManifest()`, `JarEntry` e `JarOutputStream`.

8. **Q8 (maven-jar-plugin):** Explica corretamente configuração com `<manifest><mainClass>`, `<manifestEntries>`, `<addClasspath>` e `<classpathPrefix>` na fase `package`.

9. **Q9 (Acesso a recursos):** Descreve corretamente `Class.getResourceAsStream()` com caminho absoluto (`/`) ou relativo, funcionando dentro e fora de JARs.

10. **Q10 (Gradle):** Explica corretamente plugin `java` com task `jar` e bloco `manifest { attributes }` e plugin `application` com tasks `distZip`, `distTar`, `installDist`.

11. **Q11 (JAR Hell):** Descreve corretamente conflitos de versão, classes duplicadas, dependências transitivas faltando, e papel de Maven/Gradle/Ivy.

12. **Q12 (Compressão em JARs):** Explica compressão padrão DEFLATE e opção `0` para armazenamento sem compressão, útil para JARs carregados frequentemente.

13. **Q13 (WAR vs JAR):** Descreve corretamente WAR com diretório `WEB-INF/`, `web.xml`, `classes/` e `lib/`, implantação em servidores web.

14. **Q14 (maven-dependency-plugin):** Explica goals `analyze`, `tree`, `list`, `copy-dependencies` para análise e depuração de dependências.

15. **Q15 (jdeps):** Descreve corretamente `jdeps` para análise de dependências em nível de pacote, útil para modularização e detecção de dependências cíclicas.

16. **Q16 (Versionamento e SNAPSHOT):** Explica convenção `nome-versão.jar` e sufixo `-SNAPSHOT` no Maven para versões em desenvolvimento que podem ser atualizadas.

17. **Q17 (maven-shade-plugin):** Descreve corretamente transformadores, relocation de pacotes, e criação de FAT JAR com resolução de conflitos de classes.

18. **Q18 (jar tvf e verificação):** Explica `jar tf` (listagem simples), `jar tvf` (detalhada com tamanhos/datas) e verificação de integridade.

19. **Q19 (JARs modulares e JPMS):** Descreve corretamente JAR modular com `module-info.class` na raiz, módulos automáticos, e suporte a versões anteriores do Java.

20. **Q20 (jlink):** Explica `jlink` para criar imagens de runtime customizadas com apenas módulos necessários, JARs modulares com `module-info.class`, e distribuição autônoma sem JDK separado.
