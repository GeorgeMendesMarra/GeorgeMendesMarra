**QUESTÃO 1 - (0,5 pontos)**

Gradle é uma ferramenta de automação de build que se destaca no ecossistema Java por sua flexibilidade e desempenho. Sobre as características fundamentais do Gradle, analise as alternativas abaixo e assinale a correta.

A. Gradle é uma ferramenta de build open-source que utiliza uma linguagem específica de domínio (DSL) baseada em Groovy ou Kotlin para definir scripts de build, combinando o melhor das ferramentas anteriores (a flexibilidade do ANT e o gerenciamento de dependências do Maven), além de oferecer build incremental, cache de tarefas e execução paralela para acelerar o processo de construção de software.

B. Gradle é uma ferramenta exclusiva para a linguagem de programação Kotlin, não sendo compatível com projetos Java tradicionais, pois foi desenvolvida pela JetBrains especificamente para substituir o Maven no ecossistema Kotlin, sendo que projetos Java precisam continuar usando Maven ou ANT.

C. Gradle utiliza arquivos de configuração no formato XML chamados `gradle.xml`, que devem ser escritos manualmente pelo desenvolvedor seguindo uma estrutura rígida de tags, similar ao `pom.xml` do Maven, e não possui suporte a gerenciamento automático de dependências.

D. Gradle não suporta a definição de tarefas customizadas, sendo limitado apenas às tarefas pré-definidas pelos plugins oficiais, como `compileJava`, `test` e `build`, não permitindo que o desenvolvedor crie suas próprias tarefas para necessidades específicas do projeto.

E. Gradle foi desenvolvido pela Apache Foundation como sucessor do Maven, mantendo a mesma sintaxe XML e os mesmos ciclos de vida, mas adicionando suporte nativo a linguagens dinâmicas como Groovy e Scala, sendo essencialmente uma versão atualizada do Maven com nome diferente.

---

**QUESTÃO 2 - (0,5 pontos)**

O arquivo `build.gradle` é o coração de um projeto Gradle, contendo as configurações, dependências e tarefas necessárias para construir o software. Sobre a estrutura e sintaxe do `build.gradle`, analise as alternativas e assinale a correta.

A. O arquivo `build.gradle` é escrito utilizando uma DSL (Domain Specific Language) baseada em Groovy (ou Kotlin Script no caso de `build.gradle.kts`), permitindo uma sintaxe mais concisa e expressiva que o XML do Maven, onde blocos como `plugins {}`, `repositories {}` e `dependencies {}` configuram respectivamente os plugins do projeto, os repositórios onde as dependências serão buscadas e as bibliotecas necessárias para compilação e execução.

B. O arquivo `build.gradle` deve ser escrito obrigatoriamente em XML, seguindo a mesma estrutura do `pom.xml` do Maven, pois o Gradle foi projetado para ser compatível com projetos Maven existentes sem necessidade de conversão ou adaptação dos arquivos de configuração.

C. No arquivo `build.gradle`, as dependências são declaradas utilizando o bloco `libraries {}` e cada dependência deve especificar o caminho absoluto para o arquivo JAR no sistema de arquivos local, pois o Gradle não possui gerenciamento automático de dependências baixadas de repositórios remotos.

D. O arquivo `build.gradle` é opcional em projetos Gradle; se o desenvolvedor não o fornecer, o Gradle cria automaticamente um arquivo de configuração padrão baseado no nome da pasta do projeto e no conteúdo do código fonte, aplicando heurísticas para determinar as dependências necessárias.

E. No Gradle, o arquivo de configuração deve se chamar obrigatoriamente `gradle.groovy` e ser escrito exclusivamente na linguagem Groovy, não sendo permitido o uso de Kotlin Script ou qualquer outra linguagem alternativa para definir o script de build.

---

**QUESTÃO 3 - (0,5 pontos)**

O Gradle utiliza uma estrutura de diretórios padronizada conhecida como "convenção sobre configuração", similar ao Maven, que organiza automaticamente o código fonte e os recursos do projeto. Sobre a estrutura de diretórios padrão do Gradle, analise as alternativas e assinale a correta.

A. Por convenção, o Gradle espera que o código fonte principal (Java/Kotlin) esteja no diretório `src/main/java`, os recursos principais (arquivos de propriedades, XML, imagens) em `src/main/resources`, o código fonte de testes em `src/test/java`, os recursos de teste em `src/test/resources`, e o diretório de saída do build como `build` (semelhante ao `target` do Maven), sendo que essa estrutura pode ser customizada através do `build.gradle` se necessário.

B. No Gradle, todos os arquivos do projeto (código fonte, recursos, testes e arquivos de configuração) devem ser colocados em um único diretório chamado `src`, sem qualquer subdivisão, pois o Gradle não adota nenhuma convenção de estrutura de diretórios, exigindo configuração manual completa.

C. A estrutura de diretórios padrão do Gradle é: `source/main/java` para código fonte, `source/test/java` para testes, e `output/build` para artefatos gerados, sendo que o diretório `resources` não existe por convenção, devendo os recursos ser armazenados dentro dos diretórios de código fonte.

D. O Gradle convenciona que o código fonte deve estar em `app/src/main/java` e os testes em `app/src/test/java`, sendo o diretório `app` obrigatório para qualquer projeto Gradle, independentemente do tipo de aplicação (biblioteca, aplicação web ou aplicação console).

E. No Gradle, a estrutura de diretórios padrão é definida pelo plugin aplicado e não pode ser alterada em hipótese alguma, sendo que projetos que não seguem exatamente a estrutura esperada pelo plugin não podem ser compilados pelo Gradle.

---

**QUESTÃO 4 - (0,5 pontos)**

Os plugins no Gradle são componentes que estendem as capacidades da ferramenta, adicionando tarefas, tipos de fonte e convenções específicas para diferentes tipos de projeto. Sobre os plugins do Gradle, analise as alternativas e assinale a correta.

A. Os plugins no Gradle podem ser aplicados através do bloco `plugins {}` no `build.gradle` (como `id 'java'`, `id 'application'` ou `id 'org.springframework.boot' version '2.7.0'`), e eles adicionam tarefas predefinidas como `compileJava`, `test`, `build`, `run`, além de configurar automaticamente as convenções de diretórios e dependências apropriadas para cada tipo de projeto.

B. No Gradle, os plugins são opcionais e servem apenas para gerar relatórios de documentação, não tendo qualquer influência nas tarefas de compilação, teste ou empacotamento do código fonte, que devem ser configuradas manualmente no `build.gradle` independentemente dos plugins aplicados.

C. O Gradle não suporta o uso de plugins de terceiros, permitindo apenas os plugins oficiais distribuídos junto com a instalação do Gradle, como `java` e `application`, sendo necessário implementar manualmente qualquer funcionalidade adicional não fornecida por esses plugins.

D. No Gradle, os plugins devem ser aplicados através do elemento `<plugin>` no arquivo `settings.gradle`, utilizando sintaxe XML, e não podem ser aplicados dentro do `build.gradle`, pois isso violaria a separação entre configuração do projeto e configuração do build.

E. A aplicação de um plugin no Gradle através do bloco `plugins {}` requer que o plugin esteja presente no classpath do Gradle, sendo impossível baixar plugins automaticamente de repositórios remotos como o Gradle Plugin Portal, que é um repositório exclusivo para plugins Maven, não compatível com Gradle.

---

**QUESTÃO 5 - (0,5 pontos)**

O gerenciamento de dependências no Gradle é realizado através do bloco `dependencies {}` no arquivo `build.gradle`, onde são declaradas as bibliotecas necessárias para o projeto. Sobre a declaração de dependências e suas configurações, analise as alternativas e assinale a correta.

A. No Gradle, as dependências são declaradas com coordenadas no formato `groupId:artifactId:version` (ex: `'com.google.code.gson:gson:2.9.0'`), e cada dependência pode ser associada a uma configuração específica como `implementation` (disponível em compilação e execução, mas não exposta para dependentes), `testImplementation` (disponível apenas para testes), `compileOnly` (necessária apenas para compilação) e `runtimeOnly` (necessária apenas para execução).

B. No Gradle, as dependências são declaradas utilizando o formato `artifactId-version-groupId` (ex: `'gson-2.9.0-com.google.code.gson'`), e não existe distinção entre configurações como `implementation` e `testImplementation`, sendo todas as dependências tratadas igualmente pelo Gradle.

C. O Gradle não suporta a declaração de dependências por coordenadas no formato `groupId:artifactId:version`, exigindo que o desenvolvedor especifique o caminho absoluto para cada arquivo JAR no sistema de arquivos local, não havendo integração com repositórios remotos como Maven Central.

D. No Gradle, a única configuração disponível para declarar dependências é `compile`, que é equivalente ao `implementation` do Maven, sendo que as demais configurações (`testImplementation`, `compileOnly`, `runtimeOnly`) não existem no Gradle, que utiliza um modelo mais simples e menos flexível que o Maven.

E. As dependências no Gradle não precisam especificar a versão, pois o Gradle resolve automaticamente a versão mais recente disponível no repositório configurado, baixando a biblioteca com o número de versão mais alto encontrado, independentemente de compatibilidade com o projeto.

---

**QUESTÃO 6 - (0,5 pontos)**

Os repositórios no Gradle definem de onde as dependências serão baixadas. Sobre a configuração de repositórios no Gradle, analise as alternativas e assinale a correta.

A. Os repositórios no Gradle são configurados através do bloco `repositories {}` no `build.gradle`, onde podem ser adicionados repositórios como `mavenCentral()` (Maven Central), `google()` (repositório do Google para Android), `jcenter()` (depreciado, mas ainda suportado em versões antigas), `mavenLocal()` (repositório local do Maven) e repositórios customizados através de `maven { url 'https://repo.example.com' }`.

B. O Gradle só permite a configuração de um único repositório por projeto, que deve ser obrigatoriamente o Maven Central, não sendo possível adicionar repositórios adicionais como Google ou JCenter, nem repositórios corporativos privados.

C. No Gradle, os repositórios são configurados através de um arquivo separado chamado `gradle.repositories` no formato XML, que deve ser colocado na raiz do projeto, não sendo possível configurar repositórios diretamente no `build.gradle`.

D. O Gradle não possui suporte nativo ao Maven Central, exigindo que o desenvolvedor configure manualmente a URL completa do repositório (`https://repo.maven.apache.org/maven2/`) através do bloco `mavenRepo`, não existindo um atalho como `mavenCentral()`.

E. No Gradle, o repositório local do Maven (`mavenLocal()`) não é suportado, pois o Gradle utiliza um repositório local próprio chamado `gradleCache`, incompatível com a estrutura de diretórios do Maven (`.m2/repository`).

---

**QUESTÃO 7 - (0,5 pontos)**

As tarefas (tasks) no Gradle são unidades de trabalho executadas durante o build, como compilar código, executar testes ou empacotar artefatos. Sobre a criação e execução de tarefas no Gradle, analise as alternativas e assinale a correta.

A. No Gradle, tarefas podem ser definidas através do bloco `task` no `build.gradle` (ex: `task hello { doLast { println 'Olá Gradle!' } }`), e podem ter dependências entre si (ex: `task build(dependsOn: ['compileJava', 'test'])`), sendo que ao executar uma tarefa, todas as suas dependências são executadas automaticamente antes dela, e o Gradle suporta tarefas customizadas escritas em Groovy ou Kotlin.

B. No Gradle, as tarefas são pré-definidas e não podem ser criadas pelo desenvolvedor, sendo que o único modo de adicionar comportamento customizado ao build é através de plugins externos, não sendo possível escrever tarefas diretamente no `build.gradle`.

C. As tarefas no Gradle são definidas em um arquivo separado chamado `tasks.gradle` e são escritas em XML, similar ao conceito de targets do ANT, não sendo possível definir tarefas diretamente no `build.gradle` para manter a separação entre configuração e execução.

D. No Gradle, as dependências entre tarefas são definidas automaticamente pelo Gradle com base na ordem de declaração das tarefas no arquivo `build.gradle`, não sendo necessário declarar explicitamente `dependsOn`, pois o Gradle infere a ordem de execução analisando os nomes das tarefas.

E. O Gradle não permite que tarefas customizadas acessem ou modifiquem o sistema de arquivos, sendo limitadas a exibir mensagens no console ou realizar cálculos matemáticos simples, sem interagir com os diretórios do projeto ou arquivos compilados.

---

**QUESTÃO 8 - (0,5 pontos)**

O Gradle Daemon é um processo em segundo plano que permanece em execução após o primeiro build, melhorando significativamente o desempenho de builds subsequentes. Sobre o Gradle Daemon e sua importância para o desempenho, analise as alternativas e assinale a correta.

A. O Gradle Daemon é um processo de longa duração que roda em segundo plano, mantendo em cache informações do projeto, classes carregadas e artefatos compilados, evitando o custo de inicialização da JVM (que inclui carregamento de classes, JIT compilation e alocação de memória) em cada execução do Gradle, resultando em builds muito mais rápidos após o primeiro, sendo habilitado por padrão.

B. O Gradle Daemon é um recurso opcional e desabilitado por padrão, exigindo que o desenvolvedor adicione a flag `--daemon` em cada comando do Gradle para ativá-lo, sendo recomendado apenas para projetos de grande porte com mais de 500 módulos.

C. O Gradle Daemon é um serviço do sistema operacional que precisa ser instalado separadamente do Gradle, não sendo distribuído junto com a ferramenta, e deve ser configurado manualmente através do arquivo `daemon.conf` no diretório de instalação do Gradle.

D. O Gradle Daemon não possui cache de classes ou artefatos, servindo apenas para manter uma conexão de rede aberta com repositórios remotos, acelerando o download de dependências, mas sem impacto no tempo de compilação ou execução de tarefas do build.

E. O Gradle Daemon é desencorajado em ambientes de integração contínua (CI/CD), pois pode causar conflitos de versão entre diferentes builds executados no mesmo agente, sendo recomendado que sistemas de CI executem sempre `gradle --no-daemon` para evitar problemas de cache.

---

**QUESTÃO 9 - (0,5 pontos)**

O Gradle suporta builds incrementais, uma técnica que evita a reexecução desnecessária de tarefas quando suas entradas não foram modificadas. Sobre o build incremental no Gradle, analise as alternativas e assinale a correta.

A. O build incremental no Gradle é um recurso que permite que tarefas sejam executadas apenas se suas entradas (como arquivos fonte, recursos ou propriedades) foram alteradas desde a última execução, sendo que o Gradle automaticamente rastreia essas entradas para tarefas padrão (como `compileJava`) e pode ser configurado para tarefas customizadas através das APIs de input/output do Gradle.

B. O build incremental no Gradle é um recurso exclusivo para projetos Android, não estando disponível para projetos Java padrão, pois depende de informações específicas do SDK do Android para determinar quais arquivos foram alterados entre builds.

C. O build incremental no Gradle funciona apenas para a tarefa `test`, não sendo suportado para compilação (`compileJava`) ou empacotamento (`jar`), que sempre executam completamente independentemente de alterações nos arquivos fonte.

D. No Gradle, o build incremental é desabilitado por padrão e precisa ser explicitamente ativado através da flag `--incremental` em cada comando, pois o rastreamento de entradas consome recursos significativos e pode degradar o desempenho em projetos pequenos.

E. O build incremental no Gradle não verifica o conteúdo dos arquivos (hash ou timestamp), baseando-se apenas no nome dos arquivos e no horário de criação, o que pode levar a falsos positivos quando um arquivo é modificado mas mantém o mesmo nome e timestamp.

---

**QUESTÃO 10 - (0,5 pontos)**

O Gradle possui integração nativa com o Maven, permitindo o uso de repositórios Maven e até mesmo a conversão de projetos Maven para Gradle. Sobre a interoperabilidade entre Gradle e Maven, analise as alternativas e assinale a correta.

A. O Gradle pode consumir dependências de repositórios Maven (como Maven Central, repositórios privados compatíveis com Maven) através do bloco `repositories { mavenCentral() }`, pode publicar artefatos no formato Maven para repositórios compatíveis, e oferece o comando `gradle init --type pom` para converter um projeto Maven existente (com `pom.xml`) em um projeto Gradle (com `build.gradle`), facilitando a migração entre as ferramentas.

B. O Gradle não é compatível com repositórios Maven, exigindo que todas as dependências sejam armazenadas em repositórios no formato Ivy, sendo que o Gradle foi desenvolvido com um formato de metadados próprio incompatível com o Maven, impossibilitando qualquer interoperabilidade entre as duas ferramentas.

C. O Gradle pode ler o arquivo `pom.xml` do Maven diretamente sem necessidade de conversão, utilizando a flag `--use-maven-pom` no comando `gradle build`, que faz o Gradle interpretar o `pom.xml` como se fosse um `build.gradle`, sem necessidade de arquivo Gradle próprio.

D. A conversão de projetos Maven para Gradle através do comando `gradle init --type pom` é irreversível, e após a conversão o arquivo `pom.xml` original é excluído automaticamente pelo Gradle, impedindo qualquer retorno ao Maven.

E. O Gradle não suporta a publicação de artefatos em repositórios Maven, sendo que os artefatos gerados pelo Gradle só podem ser publicados em repositórios Ivy ou no Gradle Plugin Portal, limitando seu uso em ambientes corporativos que utilizam repositórios Maven privados.

---

**QUESTÃO 11 - (0,5 pontos)**

O arquivo `settings.gradle` (ou `settings.gradle.kts`) é utilizado para configurar aspectos do projeto Gradle que são definidos no nível do conjunto de projetos, como o nome do projeto e os submódulos em um build multimódulo. Sobre o `settings.gradle`, analise as alternativas e assinale a correta.

A. O arquivo `settings.gradle` é executado no início do processo de build antes do `build.gradle`, e contém configurações como `rootProject.name` (define o nome do projeto raiz) e `include('submodulo1', 'submodulo2')` (inclui submódulos em um build multimódulo), sendo obrigatório para projetos com múltiplos submódulos e opcional para projetos simples com um único módulo.

B. O arquivo `settings.gradle` é utilizado exclusivamente para configurar plugins do Gradle que são aplicados globalmente a todos os projetos, não tendo relação com submódulos ou com o nome do projeto raiz, que devem ser configurados no `build.gradle` do projeto raiz.

C. No Gradle, o arquivo `settings.gradle` é obrigatório para qualquer projeto, mesmo os mais simples com um único módulo, e a ausência deste arquivo impede que o Gradle execute qualquer tarefa, gerando erro de configuração.

D. O arquivo `settings.gradle` é executado após o `build.gradle` e pode sobrescrever configurações definidas no `build.gradle`, sendo que a ordem de execução é `build.gradle` primeiro, depois `settings.gradle` para ajustes finos.

E. No Gradle, o arquivo de configuração do projeto deve se chamar `gradle.settings` e estar no formato JSON, não sendo suportada a extensão `.gradle` ou `.gradle.kts` para esse arquivo específico, que é gerenciado automaticamente pelo Gradle e não deve ser editado manualmente.

---

**QUESTÃO 12 - (0,5 pontos)**

O Gradle Wrapper é um script que permite que um projeto Gradle seja construído em qualquer máquina sem necessidade de instalar o Gradle manualmente, garantindo que a versão correta seja utilizada. Sobre o Gradle Wrapper, analise as alternativas e assinale a correta.

A. O Gradle Wrapper é um mecanismo que gera scripts (`gradlew` para Unix/Linux/Mac e `gradlew.bat` para Windows) e arquivos JAR associados que baixam automaticamente a versão correta do Gradle especificada no projeto (no arquivo `gradle/wrapper/gradle-wrapper.properties`), permitindo que qualquer desenvolvedor ou servidor de CI execute `./gradlew build` sem instalar o Gradle globalmente.

B. O Gradle Wrapper é um plugin opcional que deve ser adicionado manualmente ao `build.gradle` através de `apply plugin: 'wrapper'`, e não é gerado automaticamente pelo comando `gradle wrapper`, sendo necessário configurar manualmente a versão do Gradle a ser baixada.

C. O Gradle Wrapper não suporta a especificação da versão do Gradle a ser utilizada, baixando sempre a versão mais recente disponível, independentemente da versão com que o projeto foi desenvolvido, o que pode causar incompatibilidades entre diferentes builds do mesmo projeto.

D. O Gradle Wrapper requer que o Gradle esteja instalado globalmente na máquina para funcionar, pois os scripts `gradlew` apenas chamam a instalação global do Gradle, não baixando nenhuma versão automaticamente.

E. O Gradle Wrapper é um recurso exclusivo do ecossistema Android, não estando disponível para projetos Java padrão, que devem sempre ter o Gradle instalado manualmente em cada máquina de desenvolvimento e em cada agente de CI.

---

**QUESTÃO 13 - (0,5 pontos)**

O Gradle utiliza um sistema de cache para armazenar dependências baixadas, tarefas compiladas e outros artefatos, acelerando builds subsequentes. Sobre o sistema de cache do Gradle, analise as alternativas e assinale a correta.

A. O Gradle utiliza um cache de dependências localizado por padrão em `~/.gradle/caches` (Unix/Linux/Mac) ou `C:\Users\usuario\.gradle\caches` (Windows), que armazena dependências baixadas de repositórios remotos, bem como caches de tarefas e metadados de build, podendo ser limpo com o comando `gradle cleanCache` ou manualmente excluindo a pasta.

B. O Gradle não possui sistema de cache, baixando todas as dependências a cada execução diretamente dos repositórios remotos, o que torna os builds mais lentos mas garante que sempre a versão mais recente das dependências seja utilizada.

C. O cache do Gradle é armazenado dentro da pasta `build` de cada projeto, sendo que cada projeto mantém seu próprio cache independente, o que aumenta significativamente o consumo de espaço em disco, já que bibliotecas comuns são duplicadas em cada projeto.

D. O Gradle não permite a limpeza manual do cache, sendo necessário reinstalar completamente o Gradle para remover dependências antigas ou corrompidas, o que é uma limitação conhecida da ferramenta.

E. O cache do Gradle é armazenado em um banco de dados embutido (H2) localizado em `/var/gradle/cache`, e não pode ser acessado diretamente pelo usuário, sendo gerenciado exclusivamente pelo Gradle Daemon.

---

**QUESTÃO 14 - (0,5 pontos)**

O Gradle permite a criação de builds multimódulo, onde um único projeto é composto por vários submódulos que podem ter suas próprias dependências e configurações. Sobre projetos multimódulo no Gradle, analise as alternativas e assinale a correta.

A. Em um build multimódulo do Gradle, a estrutura típica consiste em um diretório raiz contendo o arquivo `settings.gradle` (que lista os submódulos com `include('submodulo1', 'submodulo2')`) e cada submódulo possui seu próprio `build.gradle`, podendo compartilhar configurações comuns através de um bloco `subprojects {}` ou `allprojects {}` no `build.gradle` raiz.

B. No Gradle, projetos multimódulo não são suportados, sendo necessário criar projetos Gradle separados para cada módulo e gerenciar manualmente as dependências entre eles através de comandos de build em sequência.

C. Em um build multimódulo do Gradle, todos os submódulos devem ter o mesmo arquivo `build.gradle`, sendo proibido que submódulos tenham configurações diferentes, pois isso violaria o princípio de consistência do build multimódulo.

D. O arquivo `settings.gradle` em um build multimódulo deve conter o código fonte de todos os submódulos diretamente no arquivo, não sendo permitido que os submódulos tenham seus próprios arquivos `build.gradle` independentes.

E. No Gradle, submódulos não podem ter dependências entre si (ex: `submodulo1` depender de `submodulo2`), pois isso criaria dependências circulares que o Gradle não consegue resolver, sendo necessário que cada módulo seja completamente independente.

---

**QUESTÃO 15 - (0,5 pontos)**

O plugin `application` do Gradle é utilizado para criar aplicações Java executáveis, configurando a classe principal e permitindo que a aplicação seja empacotada para distribuição. Sobre o plugin `application`, analise as alternativas e assinale a correta.

A. O plugin `application` (aplicado com `id 'application'`) adiciona as tarefas `run` (para executar a aplicação diretamente), `installDist` (para instalar a aplicação no diretório `build/install`), `distZip` e `distTar` (para criar arquivos de distribuição prontos para implantação), além de exigir a configuração da propriedade `application.mainClass` para definir a classe que contém o método `main`.

B. O plugin `application` é utilizado exclusivamente para aplicações web, não sendo aplicável a aplicações console ou desktop Java, que devem usar o plugin `java` padrão sem recursos de execução ou empacotamento.

C. O plugin `application` não requer a configuração da classe principal, pois o Gradle automaticamente detecta qual classe contém o método `main` analisando todo o código fonte do projeto, mesmo que haja múltiplas classes com métodos `main`.

D. O plugin `application` não gera tarefas de empacotamento como `distZip` ou `distTar`, sendo responsável apenas pela execução da aplicação via `gradle run`, devendo o desenvolvedor criar manualmente os scripts de distribuição.

E. O plugin `application` só pode ser aplicado em projetos que utilizam a linguagem Kotlin, não sendo compatível com projetos Java puro, pois as tarefas de empacotamento e execução dependem de características específicas do ecossistema Kotlin.

---

**QUESTÃO 16 - (0,5 pontos)**

O Gradle suporta a execução paralela de tarefas, permitindo que tarefas independentes sejam executadas simultaneamente para reduzir o tempo total do build. Sobre a execução paralela no Gradle, analise as alternativas e assinale a correta.

A. O Gradle pode executar tarefas em paralelo quando o recurso `--parallel` é ativado (ou configurado permanentemente no `gradle.properties` com `org.gradle.parallel=true`), permitindo que diferentes submódulos sejam construídos simultaneamente ou que tarefas independentes dentro do mesmo projeto rodem em paralelo, respeitando as dependências declaradas entre as tarefas.

B. O Gradle executa todas as tarefas em paralelo por padrão, não sendo necessário ativar nenhuma flag ou configuração, e não respeita dependências entre tarefas, executando-as simultaneamente independentemente da ordem necessária.

C. A execução paralela no Gradle está disponível apenas para tarefas do sistema operacional Windows, não sendo suportada em Unix, Linux ou macOS, devido a diferenças nos modelos de threading entre os sistemas operacionais.

D. O Gradle não suporta execução paralela de tarefas, executando todas as tarefas estritamente em sequência, pois a execução paralela poderia causar condições de corrida e corrupção de dados durante o build.

E. A execução paralela no Gradle só pode ser utilizada em builds multimódulo, não tendo efeito em projetos de módulo único, onde todas as tarefas são executadas sequencialmente independentemente da configuração `--parallel`.

---

**QUESTÃO 17 - (0,5 pontos)**

O catálogo de versões (Version Catalog) é um recurso do Gradle introduzido para centralizar a definição de versões de dependências em um único local, especialmente útil em projetos multimódulo. Sobre o catálogo de versões do Gradle, analise as alternativas e assinale a correta.

A. O catálogo de versões do Gradle permite definir versões de dependências em um arquivo `libs.versions.toml` (formato TOML) localizado na pasta `gradle`, contendo seções como `[versions]` para as versões, `[libraries]` para as dependências com suas coordenadas e versões referenciadas, e `[plugins]` para versões de plugins, permitindo que os módulos referenciem essas dependências de forma consistente com `implementation libs.nome.da.biblioteca`.

B. O catálogo de versões do Gradle é um recurso exclusivo do ecossistema Android, não estando disponível para projetos Java padrão, que devem continuar declarando versões diretamente nos blocos `dependencies` de cada módulo.

C. O catálogo de versões do Gradle deve ser escrito no formato JSON (arquivo `versions.json`) e localizado na raiz do projeto, não sendo suportado o formato TOML nem a estrutura de seções `[versions]` e `[libraries]`.

D. O catálogo de versões do Gradle não permite o uso de referências entre as seções (ex: `version = "versao.spring"`), exigindo que as versões sejam escritas literalmente em cada dependência dentro da seção `[libraries]`, o que anula o propósito de centralização.

E. O catálogo de versões do Gradle é obrigatório para todos os projetos Gradle a partir da versão 8.0, sendo que projetos que não o possuem não podem ser compilados, gerando erro de configuração.

---

**QUESTÃO 18 - (0,5 pontos)**

O plugin `java-library` é uma variante do plugin `java` que adiciona suporte à criação de bibliotecas Java que serão consumidas por outros projetos. Sobre a diferença entre o plugin `java` e o plugin `java-library`, analise as alternativas e assinale a correta.

A. O plugin `java-library` estende o plugin `java` adicionando o conceito de `api` versus `implementation` como configurações de dependência: dependências declaradas com `api` são expostas para dependentes da biblioteca (ou seja, transitivamente disponíveis), enquanto dependências declaradas com `implementation` são internas e não vazam para quem consome a biblioteca, reduzindo o acoplamento entre módulos.

B. O plugin `java-library` é idêntico ao plugin `java`, diferindo apenas no nome para fins de organização de código, não havendo qualquer diferença funcional entre os dois plugins ou nas configurações de dependência disponíveis.

C. O plugin `java-library` é utilizado exclusivamente para projetos de bibliotecas Android, não sendo aplicável a bibliotecas Java padrão, que devem usar o plugin `java` sem distinção entre `api` e `implementation`.

D. O plugin `java-library` não suporta o conceito de `implementation`, tratando todas as dependências como `api` por padrão, o que pode causar vazamento de dependências internas e aumentar o acoplamento entre módulos.

E. O plugin `java-library` não pode ser utilizado em conjunto com o plugin `application`, pois ambos configuram o mesmo conjunto de tarefas de empacotamento, gerando conflitos que impedem o build de projetos que tentam ser tanto biblioteca quanto aplicação.

---

**QUESTÃO 19 - (0,5 pontos)**

O Gradle permite a personalização do nome do artefato gerado (como JAR ou WAR) através de configurações no `build.gradle`. Sobre a personalização do nome do artefato no Gradle, analise as alternativas e assinale a correta.

A. O nome do artefato gerado pelo Gradle pode ser customizado através da configuração `archiveBaseName` (e opcionalmente `archiveVersion` e `archiveAppendix`) dentro do bloco `tasks.named('jar')` ou `tasks.jar`, permitindo que o desenvolvedor defina nomes como `meuapp-1.0.jar` em vez do padrão `<artifactId>-<version>.jar`, sem alterar as coordenadas do projeto.

B. O nome do artefato no Gradle é fixo e imutável, determinado automaticamente pelo nome do diretório do projeto e pela versão definida no `build.gradle`, não sendo possível personalizar o nome do arquivo JAR gerado.

C. A personalização do nome do artefato no Gradle só é possível para projetos com packaging `war`, não sendo suportada para projetos `jar`, que sempre geram o artefato com o nome `project.jar` independentemente das configurações.

D. Para alterar o nome do artefato no Gradle, é necessário modificar o valor da propriedade `rootProject.name` no arquivo `settings.gradle`, pois o nome do JAR é derivado exclusivamente do nome do projeto raiz, não havendo outras formas de personalização.

E. O Gradle não permite a customização do nome do artefato durante o build, mas oferece a tarefa `renameJar` que deve ser executada após o build para renomear o arquivo manualmente, sendo essa a única forma de obter um nome diferente do padrão.

---

**QUESTÃO 20 - (0,5 pontos)**

O Gradle pode ser utilizado em pipelines de integração contínua (CI/CD) para automatizar a compilação, teste e implantação de software. Sobre o uso do Gradle em ambientes de CI/CD, analise as alternativas e assinale a correta.

A. Em pipelines de CI/CD, é recomendado utilizar o Gradle Wrapper (`./gradlew`) em vez de uma instalação global do Gradle, pois o Wrapper garante que a versão correta do Gradle seja utilizada independentemente do ambiente, além de suportar a passagem de flags como `--no-daemon` (para evitar conflitos de processo em execuções paralelas no mesmo agente) e `--stacktrace` (para obter logs detalhados em caso de falha).

B. O Gradle não é adequado para uso em pipelines de CI/CD porque seus builds são muito lentos e não permitem a execução em ambientes containerizados, sendo recomendado o uso exclusivo do Maven em servidores de integração contínua.

C. Em ambientes de CI/CD, o Gradle não pode ser utilizado com o Wrapper, sendo obrigatória a instalação global do Gradle em cada agente de build, pois o Wrapper requer interação manual do usuário para baixar a versão correta.

D. O Gradle não suporta a flag `--no-daemon`, sendo que o Daemon é sempre ativado por padrão e não pode ser desabilitado, o que causa problemas em pipelines de CI onde o processo Daemon pode persistir entre execuções, consumindo memória e causando conflitos.

E. O Gradle não gera logs detalhados que possam ser utilizados para depuração em pipelines de CI, sendo que a flag `--stacktrace` não produz informações úteis sobre falhas de build, limitando a capacidade de identificar problemas em ambientes automatizados.

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

1. **Q1 (Fundamentos do Gradle):** Define corretamente Gradle como ferramenta open-source com DSL em Groovy/Kotlin, combinando flexibilidade do ANT e gerenciamento de dependências do Maven, além de build incremental, cache e execução paralela.

2. **Q2 (build.gradle):** Explica corretamente a DSL baseada em Groovy/Kotlin, com blocos `plugins`, `repositories` e `dependencies` para configurar respectivamente plugins, repositórios e dependências.

3. **Q3 (Estrutura de diretórios):** Descreve corretamente a estrutura padronizada: `src/main/java`, `src/main/resources`, `src/test/java`, `src/test/resources`, e diretório de saída `build`, com possibilidade de customização.

4. **Q4 (Plugins):** Explica corretamente o bloco `plugins {}` com `id` e `version`, adicionando tarefas predefinidas e configurando convenções de diretórios e dependências.

5. **Q5 (Dependências):** Descreve corretamente o formato `groupId:artifactId:version` e as configurações `implementation`, `testImplementation`, `compileOnly`, `runtimeOnly`.

6. **Q6 (Repositórios):** Explica corretamente o bloco `repositories {}` com `mavenCentral()`, `google()`, `jcenter()` (depreciado), `mavenLocal()` e repositórios customizados com `maven { url }`.

7. **Q7 (Tarefas):** Descreve corretamente a definição de tarefas com `task` e `doLast`, dependências com `dependsOn`, e execução automática de dependências.

8. **Q8 (Gradle Daemon):** Explica corretamente o Daemon como processo em segundo plano que mantém cache, evitando inicialização da JVM a cada build, habilitado por padrão.

9. **Q9 (Build incremental):** Descreve corretamente o build incremental, onde tarefas executam apenas se entradas foram alteradas, com rastreamento automático para tarefas padrão e APIs de input/output.

10. **Q10 (Integração com Maven):** Explica corretamente a interoperabilidade: consumo de repositórios Maven, publicação no formato Maven e conversão de `pom.xml` com `gradle init --type pom`.

11. **Q11 (settings.gradle):** Descreve corretamente o `settings.gradle` com `rootProject.name` e `include()` para submódulos, executado antes do `build.gradle`.

12. **Q12 (Gradle Wrapper):** Explica corretamente o Wrapper com scripts `gradlew`/`gradlew.bat`, arquivo `gradle-wrapper.properties` e download automático da versão correta.

13. **Q13 (Cache do Gradle):** Descreve corretamente o cache em `~/.gradle/caches` armazenando dependências e metadados de build, com limpeza possível via `gradle cleanCache` ou exclusão manual.

14. **Q14 (Build multimódulo):** Explica corretamente a estrutura com `settings.gradle` (com `include`), submódulos com seus próprios `build.gradle`, e blocos `subprojects`/`allprojects` para configuração comum.

15. **Q15 (Plugin application):** Descreve corretamente o plugin `application` com tarefas `run`, `installDist`, `distZip`/`distTar` e configuração obrigatória de `application.mainClass`.

16. **Q16 (Execução paralela):** Explica corretamente a flag `--parallel` e configuração `org.gradle.parallel=true` para executar tarefas independentes simultaneamente, respeitando dependências.

17. **Q17 (Version Catalog):** Descreve corretamente o catálogo de versões em `gradle/libs.versions.toml` formato TOML, com seções `[versions]`, `[libraries]` e `[plugins]`, e referência via `libs.nome`.

18. **Q18 (Plugin java-library):** Explica corretamente a diferença entre `api` (dependências expostas para dependentes) e `implementation` (dependências internas, não vazam), reduzindo acoplamento.

19. **Q19 (Personalização do nome do artefato):** Descreve corretamente a customização via `archiveBaseName`, `archiveVersion` e `archiveAppendix` no bloco `tasks.named('jar')` ou `tasks.jar`.

20. **Q20 (Gradle em CI/CD):** Explica corretamente o uso do Gradle Wrapper (`./gradlew`) com flags `--no-daemon` (evita conflitos) e `--stacktrace` (logs detalhados) em pipelines de CI/CD.
