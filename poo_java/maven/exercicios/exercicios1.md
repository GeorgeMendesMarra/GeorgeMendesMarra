**QUESTÃO 1 - (0,5 pontos)**

O Apache Maven é uma ferramenta de gerenciamento e automação de build amplamente utilizada no ecossistema Java, baseada no conceito de POM (Project Object Model). Sobre as características fundamentais do Maven, analise as alternativas abaixo e assinale a correta.

A. O Maven é uma ferramenta de build que utiliza um arquivo chamado `pom.xml` para descrever o projeto, suas dependências, plugins e configurações, adotando o princípio de "convenção sobre configuração" que define uma estrutura de diretórios padronizada (como `src/main/java` para código fonte, `src/test/java` para testes e `target` para artefatos gerados), além de gerenciar dependências automaticamente a partir de repositórios como Maven Central.

B. O Maven é uma ferramenta exclusiva para compilação de código Java que não suporta gerenciamento de dependências, sendo necessário baixar manualmente todas as bibliotecas JAR e configurar o classpath no arquivo `pom.xml` através de tags `<classpath>` personalizadas que devem ser escritas manualmente pelo desenvolvedor.

C. O Maven utiliza um arquivo de configuração chamado `maven.config` escrito no formato JSON, onde são definidas as coordenadas do projeto (groupId, artifactId e version), sendo que o arquivo `pom.xml` foi descontinuado na versão 3.0 e substituído por uma abordagem baseada em anotações diretamente no código fonte Java.

D. O Maven não suporta a execução de testes automatizados durante o ciclo de build, sendo necessário integrar uma ferramenta externa como o JUnit manualmente através de scripts shell, pois o foco do Maven está exclusivamente na compilação e empacotamento do código fonte, sem qualquer integração com frameworks de teste.

E. No Maven, a estrutura de diretórios é completamente livre e não existe qualquer convenção pré-definida, cabendo ao desenvolvedor configurar manualmente no arquivo `pom.xml` cada um dos caminhos para código fonte, recursos e testes, utilizando as tags `<sourceDirectory>`, `<testSourceDirectory>` e `<resourceDirectory>` obrigatoriamente em todo projeto.

---

**QUESTÃO 2 - (0,5 pontos)**

O arquivo `pom.xml` é o coração de um projeto Maven, contendo as coordenadas que identificam unicamente um projeto e suas dependências. Sobre os elementos fundamentais do POM e as coordenadas Maven, analise as alternativas e assinale a correta.

A. As coordenadas Maven que identificam unicamente um projeto são compostas por três elementos obrigatórios: `groupId` (identifica a organização ou grupo que desenvolve o projeto, geralmente seguindo a convenção de domínio invertido como `org.apache`), `artifactId` (identifica o artefato específico gerado pelo projeto, como o nome do JAR ou WAR) e `version` (especifica a versão do artefato, onde o sufixo `SNAPSHOT` indica uma versão em desenvolvimento que ainda não foi oficialmente lançada), sendo que o elemento `packaging` define o tipo de empacotamento (como `jar`, `war` ou `ear`).

B. As coordenadas Maven são compostas por apenas dois elementos obrigatórios (`groupId` e `artifactId`), sendo que o `version` é opcional e, quando omitido, o Maven automaticamente utiliza a versão mais recente disponível no repositório central, independentemente de compatibilidade com o projeto.

C. O elemento `groupId` em um projeto Maven representa o nome do arquivo JAR que será gerado após a compilação, enquanto o `artifactId` representa a versão do projeto e o `version` representa a organização responsável pelo desenvolvimento, invertendo completamente os papéis desses elementos na identificação do artefato.

D. O elemento `packaging` no Maven pode assumir apenas o valor `jar`, sendo que para gerar arquivos WAR (aplicações web) é necessário utilizar um plugin específico configurado no `pom.xml`, pois o Maven não suporta nativamente outros tipos de empacotamento além de JAR padrão.

E. O sufixo `SNAPSHOT` na versão de um projeto Maven indica que aquele artefato já foi oficialmente lançado e está estável, pronto para uso em produção, sendo recomendado para todas as dependências em projetos corporativos, pois garante que a versão nunca será alterada após o lançamento.

---

**QUESTÃO 3 - (0,5 pontos)**

O Maven utiliza o conceito de ciclos de vida (lifecycles), fases (phases) e goals para executar as tarefas de build de um projeto. Sobre os ciclos de vida do Maven, analise as alternativas e assinale a correta.

A. O Maven possui três ciclos de vida principais: o ciclo `default` (responsável pela compilação, teste, empacotamento e implantação do projeto, contendo fases como `compile`, `test`, `package`, `install` e `deploy`), o ciclo `clean` (responsável por remover arquivos temporários e artefatos gerados em builds anteriores, com fases como `pre-clean`, `clean` e `post-clean`) e o ciclo `site` (responsável por gerar documentação e relatórios do projeto), sendo que ao invocar uma fase, todas as fases anteriores a ela no mesmo ciclo são executadas automaticamente.

B. O Maven possui apenas um único ciclo de vida chamado `default`, que contém todas as fases necessárias para compilar, testar, empacotar e implantar um projeto, sendo que as operações de limpeza (clean) e geração de documentação (site) devem ser realizadas manualmente pelo desenvolvedor através de comandos do sistema operacional.

C. Os ciclos de vida do Maven são independentes e não possuem relação de ordem entre suas fases, de modo que o comando `mvn clean package` executa primeiro o empacotamento e depois a limpeza, pois a ordem dos argumentos na linha de comando determina a sequência de execução das fases.

D. O Maven possui quatro ciclos de vida: `default`, `clean`, `site` e `deploy`, sendo que o ciclo `deploy` é responsável exclusivamente pelo envio do artefato para repositórios remotos, enquanto o ciclo `default` não possui a fase `deploy` em sua sequência de fases.

E. As fases do ciclo de vida `default` são executadas em ordem aleatória, dependendo das dependências declaradas no `pom.xml`, sendo que o Maven analisa o grafo de dependências para determinar a ordem ideal de execução das fases, o que pode fazer com que a fase `test` seja executada antes da fase `compile` se houver dependências circulares.

---

**QUESTÃO 4 - (0,5 pontos)**

As dependências em um projeto Maven são declaradas no arquivo `pom.xml` e podem ter diferentes escopos (scope) que definem em quais classpaths a dependência estará disponível e se ela é transitive. Sobre os escopos de dependência no Maven, analise as alternativas e assinale a correta.

A. O Maven define diversos escopos para dependências: `compile` (escopo padrão, disponível em todos os classpaths - compilação, teste e execução), `provided` (indica que a dependência é fornecida pelo JDK ou pelo contêiner em tempo de execução, sendo disponível apenas durante compilação e teste, mas não incluída no artefato final), `test` (disponível apenas durante compilação e execução de testes), `runtime` (não necessária para compilação, mas necessária para execução) e `system` (similar ao provided, mas com caminho local explícito).

B. O escopo `compile` no Maven é utilizado apenas para dependências que serão usadas exclusivamente durante a execução dos testes unitários, não estando disponível durante a compilação do código fonte principal, sendo esse o comportamento padrão quando nenhum escopo é declarado.

C. O escopo `provided` deve ser utilizado para dependências que o projeto precisa em tempo de compilação e também devem ser empacotadas no artefato final (como no caso de um arquivo WAR que precisa conter todas as bibliotecas dentro da pasta WEB-INF/lib), sendo o escopo mais comum para aplicações web.

D. O escopo `test` é o escopo padrão do Maven quando nenhum escopo é explicitamente declarado na dependência, fazendo com que bibliotecas como JUnit sejam incluídas no artefato final de produção se não forem sobrescritas com um escopo diferente.

E. O escopo `runtime` indica que a dependência é necessária apenas durante a compilação do código fonte, mas não é necessária durante a execução do programa, sendo utilizado para bibliotecas que realizam processamento em tempo de compilação, como anotações e processadores de anotações.

---

**QUESTÃO 5 - (0,5 pontos)**

O gerenciamento de dependências em projetos multimódulo é facilitado pelo elemento `<dependencyManagement>` no Maven. Sobre a diferença entre `<dependencies>` e `<dependencyManagement>`, analise as alternativas e assinale a correta.

A. O elemento `<dependencies>` declara dependências que são imediatamente incluídas no projeto e herdadas por todos os submódulos, enquanto o elemento `<dependencyManagement>` é utilizado em projetos pai para centralizar as versões das dependências, permitindo que os módulos filhos herdem apenas a versão (sem precisar declarar explicitamente) quando incluírem a dependência em seu próprio `<dependencies>`, promovendo consistência de versões em todo o projeto.

B. O elemento `<dependencyManagement>` é utilizado para declarar dependências que serão automaticamente incluídas em todos os módulos filhos sem necessidade de qualquer declaração adicional, sendo uma forma de forçar que todas as dependências definidas no pai estejam presentes em todos os submódulos do projeto.

C. O elemento `<dependencies>` é utilizado exclusivamente em projetos pai, enquanto o elemento `<dependencyManagement>` é utilizado exclusivamente em projetos filhos, sendo que os projetos filhos não podem ter seu próprio bloco `<dependencies>` independente do pai.

D. O elemento `<dependencyManagement>` serve para excluir dependências herdadas do projeto pai, permitindo que um módulo filho remova uma dependência que foi declarada no pai sem precisar sobrescrever todo o bloco de dependências.

E. O elemento `<dependencies>` e o elemento `<dependencyManagement>` são completamente equivalentes e intercambiáveis, sendo que a escolha entre um e outro é apenas uma questão de estilo de codificação, sem qualquer impacto no comportamento do build ou na resolução de dependências.

---

**QUESTÃO 6 - (0,5 pontos)**

Os plugins no Maven são responsáveis por executar as tarefas reais durante o build, como compilar código, executar testes e empacotar artefatos. Sobre a relação entre plugins, goals e fases no Maven, analise as alternativas e assinale a correta.

A. No Maven, um plugin é um conjunto de goals (ações específicas, como `compile` do plugin `maven-compiler-plugin` ou `test` do plugin `maven-surefire-plugin`), e cada goal pode ser associado (bind) a uma ou mais fases do ciclo de vida, de modo que quando a fase é executada, todos os goals associados a ela são executados automaticamente, sendo que o Maven já possui bindings padrão para os plugins mais comuns de acordo com o tipo de empacotamento do projeto.

B. No Maven, os plugins são executados apenas quando invocados diretamente pela linha de comando (como `mvn compiler:compile`), não sendo possível associar automaticamente um goal a uma fase do ciclo de vida, pois isso exigiria configuração manual complexa em cada projeto.

C. Cada plugin no Maven pode conter apenas um único goal, que é definido pelo nome do plugin (por exemplo, o plugin `maven-compiler-plugin` tem apenas o goal `compiler`), sendo impossível um plugin oferecer múltiplos goals para diferentes finalidades.

D. Os goals dos plugins no Maven são executados em ordem alfabética dentro de cada fase, independentemente da ordem em que foram declarados no `pom.xml`, o que pode causar comportamentos imprevisíveis se houver dependências entre goals de diferentes plugins.

E. O binding entre goals e fases no Maven é fixo e imutável, não sendo possível personalizar quais goals são executados em quais fases, pois o Maven foi projetado para seguir rigidamente as convenções sem permitir qualquer tipo de customização do ciclo de build.

---

**QUESTÃO 7 - (0,5 pontos)**

O Maven resolve automaticamente as dependências transitivas de um projeto, ou seja, as dependências das dependências declaradas. Sobre o mecanismo de resolução de dependências transitivas no Maven, analise as alternativas e assinale a correta.

A. O Maven resolve dependências transitivas automaticamente, baixando não apenas as dependências declaradas diretamente no `pom.xml`, mas também todas as dependências necessárias para essas dependências funcionarem, utilizando um algoritmo que evita conflitos de versão através da estratégia "nearest definition" (a versão mais próxima do projeto raiz na árvore de dependências é a utilizada), e os escopos das dependências transitivas são herdados com regras específicas (por exemplo, uma dependência transitiva com escopo `test` não é propagada para o projeto pai).

B. O Maven não resolve dependências transitivas automaticamente, sendo necessário que o desenvolvedor declare manualmente todas as dependências de todas as bibliotecas utilizadas no projeto, incluindo dependências indiretas, caso contrário o build falhará com erros de classe não encontrada.

C. O Maven resolve dependências transitivas baixando todas as versões disponíveis de cada biblioteca e mesclando-as em um único classpath, o que causa frequentemente conflitos de versão que precisam ser resolvidos manualmente através da exclusão de arquivos JAR da pasta `target`.

D. As dependências transitivas no Maven são ignoradas por padrão, sendo necessário ativar explicitamente a resolução transitiva através da configuração `<transitive>true</transitive>` em cada dependência declarada no `pom.xml`, caso contrário apenas as dependências de primeiro nível são consideradas.

E. A estratégia de resolução de conflitos do Maven utiliza sempre a versão mais recente disponível no repositório central, independentemente da distância na árvore de dependências, o que pode causar a inclusão de versões incompatíveis que não foram testadas com o projeto.

---

**QUESTÃO 8 - (0,5 pontos)**

O repositório local do Maven é um diretório no computador do desenvolvedor onde as dependências baixadas são armazenadas em cache. Sobre o repositório local e sua localização padrão, analise as alternativas e assinale a correta.

A. O repositório local do Maven é armazenado por padrão no diretório `.m2/repository` dentro da pasta home do usuário (em sistemas Unix/Linux/Mac: `~/.m2/repository` e em Windows: `C:\Users\usuario\.m2\repository`), e contém todas as dependências baixadas, organizadas por grupo, artefato e versão, sendo que este cache evita o download repetido das mesmas bibliotecas em diferentes projetos.

B. O repositório local do Maven é armazenado por padrão dentro da pasta `target` de cada projeto Maven, sendo que cada projeto mantém sua própria cópia independente das dependências baixadas, o que aumenta significativamente o espaço em disco utilizado.

C. O repositório local do Maven é um repositório remoto hospedado nos servidores da Apache Foundation, sendo necessário conexão com a internet para acessar as dependências mesmo após o primeiro download, pois o Maven não mantém cache local das bibliotecas.

D. O repositório local do Maven está localizado por padrão em `/var/maven/repository` em sistemas Unix/Linux e em `C:\Program Files\Apache Maven\repository` em Windows, sendo que o usuário comum não tem permissão de escrita nesse diretório, exigindo privilégios de administrador para executar qualquer build.

E. O repositório local do Maven é opcional e pode ser desabilitado através da configuração `<localRepository>false</localRepository>` no arquivo `settings.xml`, caso em que todas as dependências são baixadas diretamente do repositório central a cada execução do Maven.

---

**QUESTÃO 9 - (0,5 pontos)**

O Maven Central é o repositório público padrão utilizado pelo Maven para buscar dependências quando elas não são encontradas no repositório local. Sobre o Maven Central e a configuração de repositórios, analise as alternativas e assinale a correta.

A. O Maven Central é um repositório público gerenciado pela Sonatype que contém um grande número de bibliotecas Java de código aberto, sendo configurado como repositório padrão em todo projeto Maven sem necessidade de declaração explícita, embora seja possível adicionar repositórios adicionais através do elemento `<repositories>` no `pom.xml` para acessar bibliotecas que não estão disponíveis no Central.

B. O Maven Central é um repositório privado que requer autenticação paga para acesso, sendo que projetos de código aberto devem utilizar repositórios alternativos como o JCenter ou o repositório do Google, pois o Maven Central não está mais disponível publicamente desde 2020.

C. O Maven Central não é utilizado pelo Maven por padrão; é necessário configurar manualmente cada projeto para adicionar o repositório Central através do elemento `<repositories>` no `pom.xml`, pois o Maven não possui nenhum repositório pré-configurado.

D. O Maven Central contém apenas as dependências que foram explicitamente aprovadas pela Apache Foundation, sendo que bibliotecas de outras organizações (como Spring Framework ou Google Guava) não podem ser publicadas no Central e devem ser obtidas de repositórios específicos de cada fornecedor.

E. O Maven Central é um repositório armazenado localmente em cada máquina, contendo todas as bibliotecas Java já criadas desde o início da linguagem, sendo que o desenvolvedor não precisa de conexão com a internet para baixar novas dependências, pois todas já estão disponíveis localmente na instalação do Maven.

---

**QUESTÃO 10 - (0,5 pontos)**

A herança no Maven permite que projetos filhos herdem configurações de um projeto pai, facilitando o gerenciamento de projetos multimódulo. Sobre o mecanismo de herança no Maven, analise as alternativas e assinale a correta.

A. A herança no Maven é definida através do elemento `<parent>` no `pom.xml` do projeto filho, que deve conter as coordenadas (`groupId`, `artifactId` e `version`) do projeto pai, permitindo que o filho herde configurações como dependências comuns, plugins, propriedades e o `dependencyManagement`, sendo que o filho pode sobrescrever qualquer configuração herdada declarando-a novamente em seu próprio POM.

B. A herança no Maven é automática para todos os projetos localizados em subpastas do projeto pai, não sendo necessário declarar nenhum elemento `<parent>` no `pom.xml` do filho, pois o Maven infere automaticamente a relação hierárquica a partir da estrutura de diretórios.

C. No Maven, a herança só pode ser utilizada para herdar o `groupId` e o `version` do projeto pai, não sendo possível herdar dependências ou plugins configurados no pai, pois isso causaria conflitos de versão e é considerado uma má prática de projeto.

D. A herança no Maven requer que o projeto pai esteja publicado em um repositório remoto, não sendo possível utilizar herança com projetos pai que estão no mesmo sistema de arquivos do projeto filho, mesmo durante o desenvolvimento.

E. No Maven, a herança é unidirecional e irreversível, ou seja, um projeto filho não pode sobrescrever nenhuma configuração herdada do pai, devendo aceitar todas as dependências, plugins e propriedades exatamente como definidas no projeto pai.

---

**QUESTÃO 11 - (0,5 pontos)**

A fase `package` do ciclo de vida default do Maven é responsável por empacotar o código compilado no formato definido pelo elemento `<packaging>`. Sobre o que acontece quando o comando `mvn package` é executado, analise as alternativas e assinale a correta.

A. Ao executar `mvn package`, o Maven executa todas as fases anteriores do ciclo `default` (incluindo `validate`, `compile`, `test`, etc.) e, ao final, gera o artefato empacotado no diretório `target` (como um arquivo `.jar`, `.war` ou `.ear`), mas não instala o artefato no repositório local nem o envia para um repositório remoto.

B. Ao executar `mvn package`, o Maven executa apenas a fase `package`, sem executar as fases anteriores como `compile` e `test`, assumindo que o código já está compilado e testado em execuções anteriores do Maven.

C. O comando `mvn package` gera o artefato empacotado e automaticamente o instala no repositório local do Maven (na pasta `.m2/repository`), mas não executa os testes, pois a fase `test` não é executada automaticamente antes do empacotamento.

D. O comando `mvn package` gera o artefato empacotado e o envia automaticamente para o repositório remoto configurado no elemento `<distributionManagement>` do `pom.xml`, sendo essa a principal diferença entre `package` e `install`.

E. O comando `mvn package` compila o código e executa os testes, mas não gera nenhum artefato empacotado, sendo necessário utilizar o comando `mvn jar:jar` separadamente para criar o arquivo JAR do projeto.

---

**QUESTÃO 12 - (0,5 pontos)**

O escopo `provided` é frequentemente utilizado para dependências como Servlet API em projetos web que serão implantados em servidores de aplicação como Tomcat. Sobre o comportamento do escopo `provided`, analise as alternativas e assinale a correta.

A. O escopo `provided` indica que a dependência é necessária durante a compilação e execução dos testes, mas é esperada que esteja disponível no ambiente de execução (por exemplo, fornecida pelo contêiner web ou pelo JDK), portanto a dependência não é incluída no artefato empacotado final (como no arquivo WAR), evitando conflitos de versão com bibliotecas já presentes no servidor.

B. O escopo `provided` indica que a dependência deve ser baixada do repositório Maven Central apenas durante a execução dos testes, mas não durante a compilação do código principal, sendo utilizada exclusivamente para bibliotecas de mock em testes unitários.

C. O escopo `provided` faz com que a dependência seja incluída no artefato final empacotado, mas apenas se o projeto estiver configurado com `<packaging>war</packaging>`, sendo ignorada para projetos com packaging `jar`, pois apenas aplicações web precisam de dependências fornecidas pelo contêiner.

D. O escopo `provided` é equivalente ao escopo `compile` em todos os aspectos, diferindo apenas no nome, sendo que ambos incluem a dependência no artefato final e ambos estão disponíveis em todas as fases do ciclo de vida.

E. O escopo `provided` é utilizado exclusivamente para dependências do sistema operacional, como bibliotecas nativas (`.dll` ou `.so`), não sendo aplicável a bibliotecas Java tradicionais como Servlet API ou APIs Java EE.

---

**QUESTÃO 13 - (0,5 pontos)**

O comando `mvn clean install` é um dos mais utilizados no desenvolvimento com Maven. Sobre o que este comando executa, analise as alternativas e assinale a correta.

A. O comando `mvn clean install` executa primeiro todas as fases do ciclo `clean` (removendo o diretório `target` e artefatos gerados) e, em seguida, executa todas as fases do ciclo `default` até a fase `install`, que além de compilar, testar e empacotar o projeto, também instala o artefato gerado no repositório local do Maven (`.m2/repository`), tornando-o disponível para outros projetos no mesmo computador.

B. O comando `mvn clean install` executa a fase `clean` após a fase `install`, limpando o diretório `target` apenas depois que o artefato foi instalado no repositório local, para economizar espaço em disco removendo arquivos temporários que não são mais necessários.

C. O comando `mvn clean install` executa apenas as fases `clean` e `install`, pulando todas as fases intermediárias como `compile`, `test` e `package`, pois o Maven assume que o desenvolvedor já executou essas fases anteriormente manualmente.

D. O comando `mvn clean install` compila o código e executa os testes, mas não gera nenhum artefato empacotado, instalando apenas as classes compiladas diretamente no repositório local, sem criar arquivos JAR ou WAR.

E. O comando `mvn clean install` executa a fase `clean` e a fase `install` do ciclo `default`, mas não executa a fase `package`, pois o comando `install` já inclui o empacotamento como parte de sua execução padrão, sendo a fase `package` obsoleta e mantida apenas por compatibilidade.

---

**QUESTÃO 14 - (0,5 pontos)**

O elemento `<properties>` no `pom.xml` permite definir variáveis que podem ser reutilizadas em todo o arquivo, incluindo a definição de versões de dependências. Sobre o uso de propriedades no Maven, analise as alternativas e assinale a correta.

A. O elemento `<properties>` permite definir variáveis personalizadas (como `<java.version>1.8</java.version>` ou `<spring.version>5.3.9</spring.version>`) que podem ser referenciadas em outros lugares do `pom.xml` usando a sintaxe `${nome.da.propriedade}`, facilitando a manutenção centralizada de versões de dependências e configurações de plugins.

B. O elemento `<properties>` é utilizado exclusivamente para definir propriedades do sistema Java (como `java.home` e `user.dir`), não sendo possível criar propriedades personalizadas pelo desenvolvedor no `pom.xml`.

C. As propriedades definidas no elemento `<properties>` são imutáveis e não podem ser sobrescritas por propriedades definidas na linha de comando usando `-D` (como `mvn package -DskipTests`), pois as propriedades do `pom.xml` têm precedência máxima.

D. O elemento `<properties>` deve ser utilizado apenas para definir a codificação de caracteres do projeto (`<project.build.sourceEncoding>`), sendo que qualquer outra utilização é considerada má prática e pode causar erros imprevisíveis no build.

E. As propriedades definidas no `<properties>` não podem ser utilizadas dentro do elemento `<dependency>` para definir versões, pois o Maven não resolve propriedades em dependências, exigindo que as versões sejam escritas literalmente no arquivo.

---

**QUESTÃO 15 - (0,5 pontos)**

O conceito de "convenção sobre configuração" é um dos pilares do Maven, reduzindo a necessidade de configuração explícita ao adotar padrões predefinidos. Sobre as convenções adotadas pelo Maven, analise as alternativas e assinale a correta.

A. O Maven adota diversas convenções, incluindo: código fonte principal em `src/main/java`, recursos principais em `src/main/resources`, código fonte de testes em `src/test/java`, recursos de teste em `src/test/resources`, diretório de saída de build como `target`, e o nome do artefato gerado seguindo o padrão `<artifactId>-<version>.<packaging>` (como `myapp-1.0.jar`), permitindo que projetos simples funcionem sem configuração adicional no `pom.xml`.

B. O Maven não adota nenhuma convenção predefinida, sendo necessário configurar manualmente no `pom.xml` todos os caminhos de diretórios (usando tags como `<sourceDirectory>`, `<testSourceDirectory>` e `<outputDirectory>`) para cada projeto, mesmo os mais simples.

C. As convenções do Maven são aplicáveis apenas para projetos com `packaging` do tipo `jar`, sendo que para projetos `war` ou `ear` não existe nenhuma convenção predefinida, exigindo configuração completa da estrutura de diretórios no `pom.xml`.

D. O Maven convenciona que o código fonte deve estar em `src/main/java` e os testes em `src/test/java`, mas o nome do artefato gerado é sempre `project.jar` independentemente do `artifactId` e `version` definidos no `pom.xml`.

E. O diretório de saída de build no Maven é convencionado como `dist` para projetos `jar` e `build` para projetos `war`, sendo a pasta `target` utilizada apenas para projetos de aplicações empresariais (EAR).

---

**QUESTÃO 16 - (0,5 pontos)**

O plugin `maven-compiler-plugin` é responsável pela compilação do código fonte Java no Maven. Sobre a configuração deste plugin para especificar a versão do Java, analise as alternativas e assinale a correta.

A. A versão do Java para compilação pode ser configurada no `maven-compiler-plugin` através dos parâmetros `source` e `target` no elemento `<configuration>`, ou alternativamente através das propriedades `<maven.compiler.source>` e `<maven.compiler.target>` no elemento `<properties>`, sendo que a versão configurada deve ser compatível com o JDK utilizado para executar o Maven.

B. O `maven-compiler-plugin` não permite configurar a versão do Java para compilação, pois o Maven utiliza automaticamente a versão do JDK em que está sendo executado, sendo impossível compilar código para uma versão diferente da versão do JDK do Maven.

C. A configuração da versão do Java no `maven-compiler-plugin` deve ser feita exclusivamente através do elemento `<source>` e `<target>` na tag `<plugin>` dentro de `<build>`, não sendo possível utilizar propriedades como `maven.compiler.source` para esta finalidade.

D. O `maven-compiler-plugin` é utilizado apenas para compilar código Groovy ou Kotlin, sendo que para compilar código Java o Maven utiliza um plugin diferente chamado `java-compiler-plugin`, que não suporta configuração de versão.

E. A configuração de versão do Java no `maven-compiler-plugin` é desnecessária, pois o Maven sempre compila o código para a versão mais recente do Java disponível (como Java 17 ou 21), independentemente da versão especificada no `pom.xml`.

---

**QUESTÃO 17 - (0,5 pontos)**

O arquivo `settings.xml` é utilizado pelo Maven para configurar aspectos globais da ferramenta, como localização do repositório local, servidores autenticados e proxies. Sobre o `settings.xml`, analise as alternativas e assinale a correta.

A. O Maven pode ser configurado globalmente através do arquivo `settings.xml`, que pode existir em dois níveis: o arquivo global (geralmente em `$M2_HOME/conf/settings.xml`) que afeta todos os usuários da instalação do Maven, e o arquivo por usuário (em `~/.m2/settings.xml`) que sobrescreve as configurações globais para aquele usuário específico, sendo comumente utilizado para configurar credenciais de acesso a repositórios privados.

B. O arquivo `settings.xml` é obrigatório para qualquer projeto Maven, pois contém as coordenadas do projeto (groupId, artifactId e version) que não podem ser definidas no `pom.xml`, sendo que um projeto sem `settings.xml` não pode ser compilado pelo Maven.

C. O `settings.xml` é utilizado exclusivamente para definir dependências do projeto, sendo equivalente ao `pom.xml` mas em formato JSON, e é armazenado dentro da pasta `src/main/resources` de cada projeto Maven.

D. O Maven não utiliza arquivo `settings.xml`, sendo que todas as configurações da ferramenta devem ser feitas através de variáveis de ambiente do sistema operacional ou parâmetros de linha de comando (`-D`), não existindo um arquivo de configuração persistente para a ferramenta.

E. O arquivo `settings.xml` é utilizado apenas para configurar a cor da saída do console do Maven e o formato dos logs, não tendo qualquer influência na resolução de dependências ou na configuração de repositórios.

---

**QUESTÃO 18 - (0,5 pontos)**

O Maven utiliza uma estrutura específica de diretórios para projetos web (WAR) que segue as convenções da plataforma Java EE. Sobre a localização dos artefatos em um projeto web Maven, analise as alternativas e assinale a correta.

A. Em um projeto web Maven com `<packaging>war</packaging>`, os arquivos JSP, HTML, CSS e JavaScript devem ser colocados em `src/main/webapp`, os arquivos de configuração como `web.xml` ficam em `src/main/webapp/WEB-INF`, os resources de backend (como arquivos de propriedades) ficam em `src/main/resources`, e o código fonte Java fica em `src/main/java`, sendo que o Maven gera o arquivo WAR no diretório `target` com a estrutura correta para implantação em servidores de aplicação.

B. Em um projeto web Maven, todos os arquivos (incluindo código Java, páginas JSP, e arquivos de configuração) devem ser colocados no diretório `src/main/webapp`, pois o Maven não diferencia entre código fonte e recursos web, tratando todos como parte do conteúdo do WAR.

C. Os projetos web Maven não suportam a estrutura de diretórios convencional, sendo necessário configurar manualmente no `pom.xml` cada um dos diretórios usando as tags `<webResources>`, `<webXml>` e `<sourceDirectory>`, caso contrário o Maven não conseguirá construir o arquivo WAR corretamente.

D. No Maven, projetos com `<packaging>war</packaging>` não podem conter código Java compilado (classes), apenas arquivos estáticos como HTML, CSS e JavaScript, sendo que o código dinâmico deve ser fornecido por serviços externos chamados via JavaScript.

E. O diretório `src/main/webapp` em um projeto web Maven é opcional; se não existir, o Maven cria automaticamente um WAR vazio, e todos os recursos devem ser colocados em `src/main/resources` para serem incluídos na raiz do WAR.

---

**QUESTÃO 19 - (0,5 pontos)**

Um SNAPSHOT no Maven representa uma versão em desenvolvimento de um artefato que ainda não foi lançada oficialmente. Sobre o comportamento do Maven em relação a dependências SNAPSHOT, analise as alternativas e assinale a correta.

A. Versões SNAPSHOT no Maven são versões em desenvolvimento que podem ser atualizadas frequentemente, e por padrão o Maven verifica se há uma versão mais recente de uma dependência SNAPSHOT no repositório remoto uma vez por dia (ou conforme configurado no `updatePolicy`), permitindo que os desenvolvedores obtenham automaticamente as últimas alterações sem precisar alterar a versão no `pom.xml`.

B. Dependências com sufixo SNAPSHOT são baixadas apenas uma vez do repositório remoto e armazenadas permanentemente no cache local, nunca sendo atualizadas mesmo que novas versões sejam publicadas, a menos que o usuário delete manualmente o repositório local.

C. O Maven não suporta o conceito de SNAPSHOT, sendo que o sufixo é ignorado silenciosamente pelo Maven, que trata a versão como qualquer outra versão fixa, baixando a dependência apenas uma vez e nunca a atualizando automaticamente.

D. Versões SNAPSHOT só podem ser utilizadas em projetos que são implantados em repositórios públicos (como Maven Central), não sendo permitido seu uso em projetos corporativos com repositórios privados, pois o Maven Central não aceita artefatos com sufixo SNAPSHOT.

E. Ao utilizar uma dependência SNAPSHOT, o Maven sempre baixa a versão mais recente do repositório remoto a cada execução (`mvn compile`, `mvn test`, etc.), sem qualquer cache local, o que pode tornar o build significativamente mais lento dependendo da velocidade da conexão com a internet.

---

**QUESTÃO 20 - (0,5 pontos)**

O Maven pode ser integrado a IDEs como Eclipse, IntelliJ IDEA e VS Code, que geralmente incluem suporte nativo para importar projetos Maven. Sobre como as IDEs se integram com projetos Maven, analise as alternativas e assinale a correta.

A. As IDEs modernas como Eclipse (com m2e), IntelliJ IDEA e VS Code (com extensões) são capazes de importar projetos Maven diretamente a partir do arquivo `pom.xml`, lendo as dependências declaradas, baixando automaticamente as bibliotecas necessárias e configurando o classpath do projeto, além de sincronizar automaticamente quaisquer alterações feitas no `pom.xml` com a configuração do projeto na IDE.

B. IDEs não suportam a importação direta de projetos Maven, sendo necessário executar `mvn eclipse:eclipse` (para gerar arquivos de projeto do Eclipse) ou `mvn idea:idea` (para IntelliJ) antes de importar o projeto na IDE, pois o Maven não fornece uma forma padronizada de integração com ambientes de desenvolvimento.

C. As IDEs ignoram completamente o arquivo `pom.xml` ao importar um projeto Maven, sendo necessário que o desenvolvedor adicione manualmente todas as dependências no classpath da IDE, copiando os arquivos JAR da pasta `target` para uma pasta `lib` do projeto.

D. A integração entre Maven e IDEs é limitada apenas à compilação de código, não sendo possível executar testes ou depurar código através da IDE em projetos Maven, sendo necessário executar `mvn test` no terminal para executar os testes unitários.

E. As IDEs suportam projetos Maven apenas se o projeto estiver configurado com `<packaging>jar</packaging>`, sendo que projetos com packaging `war` ou `ear` não podem ser importados em IDEs, devendo ser gerenciados exclusivamente pela linha de comando.

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

1. **Q1 (Fundamentos do Maven):** Define corretamente o POM (`pom.xml`), a convenção sobre configuração, a estrutura padronizada de diretórios e o gerenciamento automático de dependências via repositórios como Maven Central .

2. **Q2 (Coordenadas Maven):** Explica corretamente os três elementos obrigatórios (`groupId`, `artifactId`, `version`), a convenção do domínio invertido, a finalidade do `packaging` e o significado de `SNAPSHOT` como versão em desenvolvimento .

3. **Q3 (Ciclos de vida):** Descreve corretamente os três ciclos de vida (`default`, `clean`, `site`), suas respectivas fases e o comportamento de invocação sequencial onde fases anteriores são executadas automaticamente .

4. **Q4 (Escopos de dependência):** Lista corretamente os escopos (`compile`, `provided`, `test`, `runtime`, `system`) com suas descrições precisas, incluindo o fato de que `compile` é o padrão .

5. **Q5 (DependencyManagement vs Dependencies):** Diferencia corretamente `dependencies` (inclusão imediata) de `dependencyManagement` (centralização de versões para projetos multimódulo) .

6. **Q6 (Plugins, Goals e fases):** Explica a relação entre plugins (conjunto de goals), goals (ações específicas) e o binding de goals a fases do ciclo de vida, incluindo os bindings padrão .

7. **Q7 (Dependências transitivas):** Descreve corretamente a resolução automática de dependências transitivas e a estratégia "nearest definition" para conflitos de versão .

8. **Q8 (Repositório local):** Identifica corretamente a localização padrão do repositório local (`~/.m2/repository`) e sua finalidade como cache de dependências .

9. **Q9 (Maven Central):** Explica corretamente que Maven Central é o repositório público padrão, gerenciado pela Sonatype, acessível sem configuração adicional, e menciona a possibilidade de adicionar outros repositórios .

10. **Q10 (Herança):** Descreve corretamente o mecanismo de herança via elemento `<parent>`, as coordenadas necessárias e a possibilidade de sobrescrita de configurações herdadas .

11. **Q11 (Fase package):** Explica corretamente que `mvn package` executa todas as fases anteriores e gera o artefato no diretório `target`, sem instalá-lo no repositório local .

12. **Q12 (Escopo provided):** Descreve corretamente o comportamento do escopo `provided` (disponível em compilação e teste, mas não incluído no artefato final), com o exemplo da Servlet API em servidores de aplicação .

13. **Q13 (Comando clean install):** Explica corretamente que `mvn clean install` executa o ciclo `clean` e depois o ciclo `default` até a fase `install`, que instala o artefato no repositório local .

14. **Q14 (Elemento properties):** Descreve corretamente o uso de `<properties>` para definir variáveis reutilizáveis e a sintaxe `${nome}` para referenciá-las, incluindo a definição de versões de dependências .

15. **Q15 (Convenção sobre configuração):** Lista corretamente as convenções do Maven: `src/main/java`, `src/main/resources`, `src/test/java`, `target`, e o padrão de nomenclatura `<artifactId>-<version>.<packaging>` .

16. **Q16 (maven-compiler-plugin):** Descreve corretamente a configuração de versão do Java através do plugin ou propriedades (`maven.compiler.source` / `maven.compiler.target`) .

17. **Q17 (settings.xml):** Explica corretamente os dois níveis de configuração do `settings.xml` (global e por usuário), sua localização e finalidade (incluindo credenciais para repositórios privados) .

18. **Q18 (Estrutura de projeto web):** Descreve corretamente a estrutura de diretórios para projetos web Maven: `src/main/webapp` para recursos web, `WEB-INF` dentro dele para `web.xml`, e `src/main/resources` para recursos de backend .

19. **Q19 (SNAPSHOT):** Explica corretamente o comportamento de versões SNAPSHOT, incluindo que são versões em desenvolvimento e que o Maven verifica atualizações periodicamente (por padrão uma vez por dia) .

20. **Q20 (Integração com IDEs):** Descreve corretamente a capacidade das IDEs modernas de importar projetos Maven diretamente do `pom.xml`, baixando dependências e sincronizando alterações automaticamente .
