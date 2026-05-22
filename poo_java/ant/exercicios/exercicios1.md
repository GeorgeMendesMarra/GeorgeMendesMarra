**QUESTÃO 1 - (0,5 pontos)**

O Apache ANT é uma ferramenta de automação de build amplamente utilizada no ecossistema Java antes da popularização do Maven e Gradle. Sobre as características fundamentais do ANT, analise as alternativas abaixo e assinale a correta.

A. O Apache ANT é uma ferramenta de build baseada em arquivos XML chamados `build.xml`, que utiliza o conceito de targets (tarefas) que podem ter dependências entre si, sendo cada target composto por tasks (ações atômicas como compilar, copiar, deletar), e é conhecido por sua flexibilidade e natureza imperativa, onde o desenvolvedor especifica explicitamente cada passo do processo de build.

B. O Apache ANT é uma ferramenta de build que utiliza arquivos de configuração no formato JSON chamados `ant.json`, sendo que a sintaxe é baseada em JavaScript e as tarefas são definidas como funções que são executadas em ordem alfabética, independentemente de dependências declaradas entre elas.

C. O Apache ANT é uma ferramenta exclusiva para compilar código C++, não sendo compatível com projetos Java, pois foi desenvolvida pela Microsoft para competir com o MSBuild no ecossistema .NET, sendo que projetos Java devem usar exclusivamente o Maven.

D. O Apache ANT não suporta a definição de dependências entre tarefas, sendo que todas as tarefas definidas no `build.xml` são executadas em paralelo e em ordem aleatória, cabendo ao desenvolvedor garantir que os efeitos colaterais das tarefas não interfiram umas nas outras.

E. O Apache ANT utiliza uma linguagem de script proprietária baseada em Python chamada AntScript, e os arquivos de configuração possuem extensão `.ant`, sendo que o XML foi descontinuado na versão 2.0 em favor de uma sintaxe mais moderna e legível.

---

**QUESTÃO 2 - (0,5 pontos)**

O arquivo `build.xml` é o coração de um projeto ANT, contendo a estrutura de targets e tasks que definem o processo de build. Sobre a estrutura e os elementos do arquivo `build.xml`, analise as alternativas e assinale a correta.

A. O arquivo `build.xml` tem como elemento raiz a tag `<project>`, que pode conter atributos como `name` (nome do projeto), `default` (nome do target padrão a ser executado quando o comando `ant` é invocado sem parâmetros) e `basedir` (diretório base a partir do qual os caminhos relativos são resolvidos), além de conter tags `<target>` e `<property>` como elementos filhos.

B. O arquivo `build.xml` tem como elemento raiz a tag `<ant>` e não pode conter atributos, sendo que todos os projetos ANT devem obrigatoriamente ter o nome "build" e o basedir fixo no diretório atual, sem possibilidade de customização.

C. O arquivo `build.xml` deve ser escrito em formato JSON e seu elemento raiz é uma lista de objetos chamada `tasks`, onde cada objeto representa uma tarefa com seus parâmetros, não sendo permitido o uso de XML em projetos ANT modernos.

D. O arquivo `build.xml` é opcional em projetos ANT; se o desenvolvedor não o fornecer, o ANT gera automaticamente um arquivo de configuração padrão baseado no conteúdo da pasta `src`, aplicando heurísticas para determinar as tarefas necessárias.

E. O elemento `<project>` no arquivo `build.xml` pode conter apenas targets, não sendo permitido declarar propriedades ou definições de path dentro dele, sendo que todas as propriedades devem ser definidas em um arquivo separado chamado `ant.properties`.

---

**QUESTÃO 3 - (0,5 pontos)**

Os targets são os blocos fundamentais de um build no ANT, representando unidades de trabalho que podem ser executadas individualmente ou em sequência. Sobre os targets no Apache ANT, analise as alternativas e assinale a correta.

A. No ANT, um target é definido pela tag `<target>` com atributos como `name` (nome único do target), `depends` (lista de nomes de targets que devem ser executados antes deste target) e `description` (texto descritivo exibido pelo comando `ant -projecthelp`), e ao executar um target, todos os seus dependentes são executados primeiro, garantindo a ordem correta de execução.

B. No ANT, os targets são definidos pela tag `<task>` e não possuem nomes, sendo identificados pela ordem em que aparecem no arquivo `build.xml`, e a execução sempre ocorre do primeiro ao último target declarado, independentemente de dependências.

C. O atributo `depends` de um target no ANT aceita apenas um único target como dependência, não sendo possível especificar múltiplas dependências separadas por vírgula, o que força o desenvolvedor a criar targets intermediários para encadear múltiplas dependências.

D. No ANT, targets não podem ter descrições, pois o comando `ant -projecthelp` não existe, sendo que a única forma de documentar um target é através de comentários XML dentro da tag `<target>`, que são ignorados pela ferramenta.

E. A execução de um target no ANT sempre executa também todos os targets que dependem dele (dependentes reversos), pois o ANT analisa o grafo de dependências completo e executa não apenas as dependências, mas também todos os targets que têm o target atual como dependência.

---

**QUESTÃO 4 - (0,5 pontos)**

As tasks (tarefas) no ANT são ações atômicas que realizam operações específicas, como compilar código, copiar arquivos ou criar diretórios. Sobre as tasks do Apache ANT, analise as alternativas e assinale a correta.

A. O ANT possui um conjunto extenso de tasks nativas, incluindo `<javac>` para compilar código Java, `<copy>` para copiar arquivos e diretórios, `<delete>` para remover arquivos, `<mkdir>` para criar diretórios, `<jar>` para empacotar arquivos no formato JAR, e `<java>` para executar programas Java, sendo possível também criar tasks customizadas em Java.

B. O ANT possui apenas três tasks nativas: `<compile>`, `<copy>` e `<delete>`, sendo que todas as outras operações (como criar JAR ou executar testes) exigem o desenvolvimento de tasks customizadas em Java pelo usuário, o que limita severamente sua usabilidade.

C. As tasks no ANT são escritas em uma linguagem de script proprietária chamada AntTask Language (ATL), que não é baseada em Java, sendo que o desenvolvedor precisa aprender uma nova linguagem para criar tasks customizadas, o que torna o ANT difícil de estender.

D. No ANT, todas as tasks devem ser definidas dentro de um elemento `<tasks>` que deve ser declarado antes de qualquer target, e o nome de cada task é livre, podendo ser qualquer palavra, desde que não conflite com palavras reservadas do XML.

E. A task `<javac>` do ANT não suporta a definição de classpath ou de níveis de compilação, sendo limitada a compilar arquivos `.java` que não dependem de bibliotecas externas, tornando-a inadequada para projetos Java reais que utilizam dependências de terceiros.

---

**QUESTÃO 5 - (0,5 pontos)**

As propriedades (properties) no ANT são variáveis que armazenam valores que podem ser reutilizados em todo o arquivo `build.xml`. Sobre a definição e uso de propriedades no Apache ANT, analise as alternativas e assinale a correta.

A. Propriedades no ANT são definidas através da tag `<property>`, com atributos `name` e `value` (ex: `<property name="src.dir" value="src"/>`), e são referenciadas utilizando a sintaxe `${nome.da.propriedade}`, sendo que uma vez definida, uma propriedade é imutável (não pode ser alterada) durante a execução do build, o que ajuda a evitar efeitos colaterais indesejados.

B. Propriedades no ANT são definidas através da tag `<var>` com atributos `nome` e `valor`, e podem ser alteradas quantas vezes o desenvolvedor desejar durante a execução do build, pois o ANT trata propriedades como variáveis mutáveis similares a linguagens de programação imperativas.

C. No ANT, propriedades são definidas automaticamente pelo sistema e o desenvolvedor não pode criar propriedades customizadas, sendo que os nomes das propriedades são fixos e predefinidos (como `ant.java.version` e `ant.file`), não havendo como armazenar valores personalizados.

D. A sintaxe para referenciar uma propriedade no ANT é `%nome.da.propriedade%` (similar a variáveis de ambiente do Windows), e não `${nome}`, sendo que o uso da sintaxe incorreta gera erro de compilação do arquivo `build.xml`.

E. Propriedades no ANT, quando definidas com a tag `<property>`, são mutáveis e podem ser alteradas por qualquer task subsequente, sendo que o valor mais recente prevalece, o que pode causar comportamentos imprevisíveis em builds complexos.

---

**QUESTÃO 6 - (0,5 pontos)**

O gerenciamento de classpath no ANT é essencial para compilar e executar código Java que depende de bibliotecas externas. Sobre as formas de definir classpath no ANT, analise as alternativas e assinale a correta.

A. O ANT permite a definição de classpath através do elemento `<classpath>`, que pode conter elementos como `<pathelement path="caminho"/>` (para adicionar um caminho específico) e `<fileset dir="lib" includes="*.jar"/>` (para incluir todos os arquivos JAR de um diretório), sendo que este classpath pode ser utilizado nas tasks `<javac>`, `<java>` e `<junit>` através do atributo `classpath` ou do elemento filho `classpath`.

B. O ANT não suporta a definição de classpath, exigindo que todas as bibliotecas necessárias estejam no diretório de extensões do JRE (`jre/lib/ext`), pois o ANT foi projetado para projetos simples que não utilizam bibliotecas de terceiros.

C. No ANT, o classpath é definido automaticamente com base no conteúdo da pasta `lib` do projeto, não sendo necessário declarar explicitamente quais JARs devem ser incluídos, pois o ANT varre recursivamente todo o diretório `lib` em busca de arquivos JAR e os adiciona ao classpath automaticamente.

D. O elemento `<fileset>` não é suportado dentro de `<classpath>` no ANT, sendo necessário listar cada arquivo JAR individualmente usando múltiplos elementos `<pathelement>`, o que torna o build.xml extremamente verboso para projetos com muitas dependências.

E. A task `<javac>` do ANT não aceita a definição de classpath, sendo que todas as bibliotecas necessárias para compilação devem estar no classpath do próprio ANT (definido pela variável de ambiente `ANT_CLASSPATH`), não havendo como definir classpath por projeto.

---

**QUESTÃO 7 - (0,5 pontos)**

O ANT suporta a execução de testes automatizados através da integração com o JUnit. Sobre a execução de testes no Apache ANT, analise as alternativas e assinale a correta.

A. O ANT possui a task `<junit>` que permite executar testes JUnit, podendo gerar relatórios em formato XML ou texto simples através da task `<junitreport>`, e suporta a execução de testes em batch com `<batchtest>` (executando todas as classes que correspondem a um padrão, como `**/*Test.class`) ou testes individuais com `<test>`.

B. O ANT não suporta a execução de testes JUnit, sendo necessário executar os testes manualmente ou através de scripts externos, pois o ANT foi projetado exclusivamente para compilação e empacotamento de código, não tendo integração com frameworks de teste.

C. A task `<junit>` do ANT executa testes apenas em modo fork (processo separado), não sendo possível executar testes dentro da mesma JVM do ANT, o que torna a depuração de testes mais complexa e limita a integração com IDEs.

D. No ANT, os testes JUnit devem ser executados um por um, não sendo possível executar múltiplos testes em lote, pois a task `<batchtest>` não existe no ANT, sendo necessário criar um target separado para cada classe de teste.

E. A task `<junitreport>` do ANT não existe, sendo que os relatórios de teste devem ser gerados manualmente pelo desenvolvedor processando a saída da task `<junit>` com scripts externos em Perl ou Python.

---

**QUESTÃO 8 - (0,5 pontos)**

O ANT permite a criação de arquivos JAR, WAR e EAR através de tasks específicas de empacotamento. Sobre a criação de artefatos no Apache ANT, analise as alternativas e assinale a correta.

A. O ANT possui a task `<jar>` para criar arquivos JAR, que suporta atributos como `destfile` (nome do arquivo JAR gerado), `basedir` (diretório base cujo conteúdo será empacotado), e pode incluir um manifesto através do elemento filho `<manifest>` com entradas como `Main-Class` e `Class-Path`, sendo também suportadas as tasks `<war>` para arquivos WAR e `<ear>` para arquivos EAR.

B. O ANT possui apenas a task `<jar>` para empacotamento, não suportando a criação de arquivos WAR ou EAR, pois esses formatos são específicos de servidores de aplicação e não fazem parte do escopo do ANT, que se limita a bibliotecas JAR simples.

C. A task `<jar>` do ANT não permite incluir um arquivo de manifesto personalizado, sendo que o manifesto gerado é sempre o padrão com apenas as entradas obrigatórias, impossibilitando a definição de `Main-Class` para aplicações executáveis.

D. No ANT, os arquivos WAR devem ser criados manualmente renomeando arquivos JAR para extensão `.war`, pois a task `<war>` não existe, sendo que o desenvolvedor precisa usar a task `<jar>` com atributos específicos para simular a estrutura de um WAR.

E. A task `<ear>` do ANT foi descontinuada na versão 1.9 e removida na versão 1.10, sendo que projetos que necessitam criar arquivos EAR devem utilizar a task `<jar>` com configurações manuais ou migrar para o Maven.

---

**QUESTÃO 9 - (0,5 pontos)**

O Fileset é um dos elementos mais poderosos do ANT para selecionar conjuntos de arquivos com base em padrões. Sobre o uso de Filesets no Apache ANT, analise as alternativas e assinale a correta.

A. O elemento `<fileset>` permite selecionar arquivos com base em um diretório base (`dir`) e incluir ou excluir arquivos usando padrões com `include` e `exclude` (ex: `<include name="**/*.java"/>` para incluir todos os arquivos Java recursivamente), sendo amplamente utilizado em tasks como `<copy>`, `<javac>` e `<jar>` para especificar quais arquivos devem ser processados.

B. O elemento `<fileset>` no ANT é utilizado exclusivamente para definir conjuntos de diretórios, não sendo possível selecionar arquivos individuais, pois o ANT trata diretórios e arquivos de forma distinta, com elementos separados para cada um.

C. No ANT, o padrão `**/` (recursivo) não é suportado em filesets, sendo necessário listar explicitamente todos os subdiretórios que contêm arquivos a serem incluídos, o que torna o build.xml extremamente verboso para projetos com estrutura de pacotes Java aninhados.

D. O elemento `<fileset>` não aceita múltiplas tags `<include>` ou `<exclude>`, permitindo apenas um padrão de inclusão e um padrão de exclusão por fileset, o que limita significativamente a flexibilidade na seleção de arquivos.

E. Filesets no ANT são definidos fora de qualquer target e são compartilhados globalmente entre todos os targets, não podendo ser definidos dentro de um target específico, o que pode causar efeitos colaterais indesejados quando diferentes targets processam conjuntos de arquivos diferentes.

---

**QUESTÃO 10 - (0,5 pontos)**

O Apache ANT foi uma das primeiras ferramentas de build multiplataforma para Java, mas possui limitações que motivaram a criação de alternativas como Maven e Gradle. Sobre as limitações do ANT em comparação com ferramentas mais modernas, analise as alternativas e assinale a correta.

A. O ANT não possui gerenciamento automático de dependências, exigindo que o desenvolvedor baixe manualmente os arquivos JAR das bibliotecas de terceiros e os armazene no projeto (geralmente em uma pasta `lib`), não possui build incremental (recompila tudo a cada execução), e cada projeto precisa definir manualmente sua estrutura de diretórios e ciclo de build, pois não segue convenções predefinidas.

B. O ANT possui gerenciamento automático de dependências similar ao Maven, baixando bibliotecas automaticamente de repositórios como Maven Central, sendo que essa funcionalidade está disponível desde a versão 1.5 através da task `<dependency>`, o que eliminou a necessidade de armazenar JARs localmente.

C. O ANT suporta build incremental nativamente, recompilando apenas arquivos fonte modificados desde o último build, sendo que essa funcionalidade é ativada automaticamente em todas as versões do ANT sem necessidade de configuração adicional.

D. O ANT segue convenções rígidas de estrutura de diretórios (similar ao Maven), onde o código fonte deve estar em `src/main/java`, os recursos em `src/main/resources` e os testes em `src/test/java`, não sendo permitido alterar essas convenções sem modificar o código fonte do ANT.

E. A principal limitação do ANT é a falta de suporte a tarefas condicionais e loops, sendo que todas as tarefas são executadas sequencialmente sem possibilidade de desvios ou repetições, o que impede a criação de builds complexos com lógica condicional.

---

**QUESTÃO 11 - (0,5 pontos)**

O ANT permite a execução de tarefas condicionais através de elementos como `<condition>` e targets condicionais. Sobre o suporte a condicionais no Apache ANT, analise as alternativas e assinale a correta.

A. O ANT possui a task `<condition>` que avalia uma condição (como existência de arquivo, igualdade de strings, propriedade definida) e define uma propriedade com base no resultado, que pode ser usada em targets com o atributo `if` ou `unless` para executar um target apenas se a propriedade estiver definida (`if`) ou não definida (`unless`), permitindo execução condicional de partes do build.

B. O ANT não suporta nenhum tipo de execução condicional, sendo que todos os targets declarados são sempre executados em sua totalidade, independentemente de condições, pois o ANT foi projetado para builds simples e determinísticos sem necessidade de lógica condicional.

C. O elemento `<condition>` no ANT não pode verificar a existência de arquivos ou diretórios, sendo limitado a comparações de strings e verificação de propriedades, o que o torna pouco útil para builds reais que dependem da presença de certos recursos.

D. O atributo `if` em um target verifica o valor da propriedade (ex: `if="propriedade=true"`), não apenas se a propriedade está definida, sendo que a sintaxe correta exige a comparação com um valor booleano explícito.

E. No ANT, targets condicionais com `if` e `unless` são avaliados em tempo de compilação do `build.xml`, não em tempo de execução, sendo que as condições não podem depender de resultados de tasks executadas anteriormente, limitando severamente seu uso prático.

---

**QUESTÃO 12 - (0,5 pontos)**

O ANT pode ser estendido com tasks customizadas escritas em Java, permitindo que funcionalidades específicas não disponíveis nativamente sejam adicionadas. Sobre a criação de tasks customizadas no Apache ANT, analise as alternativas e assinale a correta.

A. Para criar uma task customizada no ANT, o desenvolvedor deve criar uma classe Java que estenda a classe `org.apache.tools.ant.Task`, implementar o método `execute()`, e declarar a task no `build.xml` usando a tag `<taskdef>`, que mapeia um nome XML para a classe Java, permitindo que a nova task seja utilizada como qualquer task nativa do ANT.

B. Tasks customizadas no ANT não podem ser escritas em Java, sendo necessário utilizar a linguagem de script Groovy ou JavaScript, pois o ANT foi projetado para ser extensível apenas através de scripts dinâmicos, não permitindo a criação de tasks compiladas em Java.

C. Para criar uma task customizada, o desenvolvedor precisa modificar o código fonte do ANT e recompilar a ferramenta, pois não existe suporte oficial a plugins ou extensões, sendo que o ANT é uma ferramenta fechada que não permite customização por usuários finais.

D. A tag `<taskdef>` no ANT requer que a classe Java esteja presente no classpath do sistema operacional (na variável `CLASSPATH` global), não sendo possível definir o classpath da task dentro do próprio `build.xml`, o que torna o deployment de tasks customizadas complexo e dependente do ambiente.

E. Tasks customizadas no ANT não podem acessar o projeto, as propriedades ou outras tasks, sendo limitadas a operações isoladas sem interação com o contexto do build, o que as torna praticamente inúteis para extensões sérias.

---

**QUESTÃO 13 - (0,5 pontos)**

O ANT permite a importação de outros arquivos de build, facilitando a reutilização de configurações comuns entre projetos. Sobre a importação de arquivos no Apache ANT, analise as alternativas e assinale a correta.

A. O ANT possui a tag `<import>` que permite incluir o conteúdo de um arquivo `build.xml` externo no build atual, mesclando seus targets e propriedades, sendo que os targets importados podem ser sobrescritos pelo projeto que faz a importação, permitindo customização sem modificar o arquivo original.

B. O ANT não suporta a importação de arquivos de build externos, sendo que cada projeto deve ter seu próprio `build.xml` completo e autossuficiente, pois a reutilização de configurações é considerada uma má prática pela Apache Foundation.

C. A tag `<import>` no ANT requer que o arquivo importado esteja em um repositório Maven central, não sendo possível importar arquivos do sistema de arquivos local, o que limita a reutilização em projetos corporativos com políticas de segurança restritas.

D. Quando um arquivo é importado com `<import>`, seus targets são renomeados automaticamente com um prefixo para evitar conflitos de nome, e o projeto importador não pode sobrescrever ou referenciar diretamente os targets importados sem conhecer o prefixo gerado dinamicamente.

E. A tag `<import>` no ANT foi descontinuada na versão 1.8 em favor da tag `<include>`, que possui funcionalidade idêntica mas com nome mais intuitivo, sendo que projetos modernos devem usar `<include>` em vez de `<import>`.

---

**QUESTÃO 14 - (0,5 pontos)**

O ANT pode ser executado a partir da linha de comando com diversas opções que controlam seu comportamento. Sobre os comandos e opções do ANT no terminal, analise as alternativas e assinale a correta.

A. O comando `ant -help` exibe a ajuda com todas as opções disponíveis; `ant -version` mostra a versão do ANT instalada; `ant -projecthelp` exibe a lista de targets do `build.xml` que possuem descrição; `ant -f build-custom.xml` permite especificar um arquivo de build diferente do padrão (`build.xml`); e `ant -Dpropriedade=valor` permite definir propriedades via linha de comando, sobrescrevendo as definidas no `build.xml`.

B. O ANT não aceita parâmetros de linha de comando além do nome do target a ser executado, sendo que todas as configurações devem ser feitas exclusivamente no arquivo `build.xml`, não havendo suporte a flags como `-f` ou `-D`.

C. A flag `-D` no ANT é utilizada para ativar o modo de depuração, não para definir propriedades, sendo que propriedades de linha de comando devem ser definidas com a flag `-P` (similar ao Maven), que não existe no ANT.

D. O comando `ant -projecthelp` exibe todos os targets do `build.xml`, independentemente de terem descrição ou não, sendo que a flag `-verbose` é que filtra apenas targets com descrição, o oposto do comportamento documentado.

E. O ANT não permite especificar um arquivo de build diferente de `build.xml`, sendo que o arquivo deve sempre ter esse nome exato e estar no diretório atual, pois o ANT não suporta a flag `-f` ou `-file` para alterar o nome do arquivo de configuração.

---

**QUESTÃO 15 - (0,5 pontos)**

O ANT suporta a execução de tarefas em paralelo através da task `<parallel>`, permitindo que tarefas independentes sejam executadas simultaneamente. Sobre a execução paralela no Apache ANT, analise as alternativas e assinale a correta.

A. O ANT possui a task `<parallel>` que permite executar múltiplas tarefas simultaneamente em threads separadas, podendo conter um atributo `threadCount` para limitar o número de threads e `timeout` para definir um tempo máximo de execução, sendo útil para acelerar builds que têm tarefas independentes (como compilar múltiplos submódulos ou executar testes em paralelo).

B. O ANT não suporta execução paralela de tarefas, executando todas as tarefas estritamente em sequência, pois foi projetado antes da popularização de processadores multi-core e não possui mecanismos para aproveitar paralelismo.

C. A task `<parallel>` no ANT executa tarefas em processos separados (fork), não em threads, o que consome significativamente mais recursos do sistema e torna o paralelismo impraticável para builds com muitas tarefas pequenas.

D. O atributo `threadCount` da task `<parallel>` é ignorado pelo ANT, que sempre utiliza um número fixo de threads igual ao número de processadores lógicos do sistema, não permitindo que o desenvolvedor controle o grau de paralelismo.

E. No ANT, a task `<parallel>` não pode conter targets como filhos diretos, apenas tasks atômicas, sendo que para executar targets em paralelo é necessário desmembrar cada target em suas tasks constituintes e replicar o código, o que torna o build.xml menos modular.

---

**QUESTÃO 16 - (0,5 pontos)**

O ANT possui a task `<echo>` para exibir mensagens no console durante a execução do build. Sobre a task `<echo>` e níveis de logging no Apache ANT, analise as alternativas e assinale a correta.

A. A task `<echo>` exibe mensagens no console e pode ter seu nível de verbosidade controlado pelo atributo `level`, com valores possíveis como `error`, `warning`, `info`, `verbose` e `debug`, sendo que mensagens com nível inferior ao nível de logging atual (definido pela flag `-quiet`, `-verbose` ou `-debug` na linha de comando) não são exibidas.

B. A task `<echo>` no ANT exibe mensagens apenas quando o build falha, sendo utilizada exclusivamente para mensagens de erro, não havendo suporte a mensagens informativas ou de depuração durante a execução normal do build.

C. O ANT não possui níveis de logging, sendo que todas as mensagens de `<echo>` são sempre exibidas independentemente das flags de linha de comando, e não existe suporte a níveis como `verbose` ou `debug`.

D. A flag `-quiet` no ANT aumenta a verbosidade em vez de reduzir, exibindo ainda mais mensagens de log, sendo que o comportamento correto para silenciar o ANT seria `-silent`, que não existe.

E. O atributo `level` da task `<echo>` só pode ser definido como `info` ou `error`, sendo que valores como `verbose` e `debug` não são reconhecidos e causam erro de execução, limitando o controle de logging do ANT.

---

**QUESTÃO 17 - (0,5 pontos)**

O Apache Ivy é um gerenciador de dependências que pode ser integrado ao ANT para adicionar resolução automática de dependências, similar ao que o Maven oferece nativamente. Sobre o Apache Ivy e sua integração com ANT, analise as alternativas e assinale a correta.

A. O Apache Ivy é um módulo separado que se integra ao ANT através da task `<ivy:resolve>`, permitindo declarar dependências em um arquivo `ivy.xml` (similar ao `pom.xml` do Maven) e baixar automaticamente os JARs necessários de repositórios como Maven Central, Ivy Repository ou repositórios privados, resolvendo também dependências transitivas.

B. O Apache Ivy é uma ferramenta de build alternativa ao ANT, não sendo compatível com ele, pois foi desenvolvida pela Apache como concorrente direta do ANT, tendo seu próprio formato de arquivo de configuração e suas próprias tasks, sendo que os dois não podem ser usados em conjunto.

C. O Apache Ivy foi absorvido pelo Maven e descontinuado em 2015, não sendo mais suportado ou recomendado para novos projetos, sendo que o ANT nunca teve suporte oficial a gerenciamento de dependências, mesmo através de módulos complementares.

D. O arquivo `ivy.xml` utilizado pelo Ivy não suporta dependências transitivas, exigindo que o desenvolvedor declare manualmente todas as dependências indiretas de cada biblioteca, o que torna seu uso tão trabalhoso quanto gerenciar JARs manualmente.

E. O Ivy só pode resolver dependências do repositório Ivy Repository, não sendo compatível com Maven Central, o que limita drasticamente o acesso a bibliotecas open source, que em sua maioria estão disponíveis apenas no Maven Central.

---

**QUESTÃO 18 - (0,5 pontos)**

O ANT permite a criação de builds multimódulo, onde um projeto raiz orquestra a construção de vários submódulos. Sobre a construção de projetos multimódulo com ANT, analise as alternativas e assinale a correta.

A. O ANT suporta builds multimódulo através da task `<ant>`, que permite executar um build ANT em outro diretório (usando o atributo `dir` e opcionalmente `target`), e da task `<subant>`, que pode executar builds em múltiplos diretórios de uma só vez, sendo comum usar um arquivo `build.xml` raiz que invoca builds dos submódulos com `antcall` ou `subant`.

B. O ANT não suporta builds multimódulo, sendo que cada módulo deve ser construído independentemente em um processo separado, e o desenvolvedor é responsável por orquestrar manualmente a ordem de construção dos módulos.

C. A task `<ant>` no ANT não aceita o atributo `target`, executando sempre o target padrão do submódulo, não sendo possível especificar qual target do submódulo deve ser executado a partir do projeto raiz.

D. A task `<subant>` no ANT foi descontinuada por problemas de desempenho e não funciona em sistemas Windows, sendo que a única forma de construir múltiplos módulos é usando a task `<ant>` repetidamente para cada submódulo.

E. No ANT, builds multimódulo não podem compartilhar propriedades ou classpaths, sendo que cada submódulo é completamente isolado e não pode acessar artefatos compilados de outros submódulos, a menos que eles já estejam instalados em um repositório compartilhado.

---

**QUESTÃO 19 - (0,5 pontos)**

O ANT possui mecanismos para lidar com falhas durante a execução do build, permitindo que tarefas falhem silenciosamente ou que ações sejam tomadas em caso de erro. Sobre o tratamento de erros no Apache ANT, analise as alternativas e assinale a correta.

A. O ANT permite que tasks tenham o atributo `failonerror="false"` para que a execução continue mesmo se a task falhar (ex: `<delete failonerror="false">` continua mesmo se o arquivo não existir), e possui a task `<trycatch>` (disponível via ant-contrib ou a partir da versão 1.9.3 com o plugin ant-antunit) para capturar exceções e executar blocos `catch` e `finally`, similar a blocos try-catch em Java.

B. O ANT não possui nenhum mecanismo de tratamento de erros; se qualquer task falha, o build inteiro é interrompido imediatamente sem possibilidade de recuperação ou execução de ações de limpeza, sendo essa uma limitação conhecida da ferramenta.

C. O atributo `failonerror` não existe no ANT, sendo que a única forma de evitar que uma task interrompa o build é usando a task `<try>` com atributos especiais, que não é suportada nativamente e requer a instalação de bibliotecas de terceiros.

D. A task `<trycatch>` é nativa do ANT desde a versão 1.7 e não requer nenhum plugin adicional, sendo que o ANT sempre teve suporte completo a tratamento de exceções com blocos `try`, `catch` e `finally` similares às linguagens de programação.

E. No ANT, mesmo com `failonerror="false"`, algumas tasks como `<javac>` sempre interrompem o build em caso de erro de compilação, pois o atributo não tem efeito sobre tasks de compilação, apenas sobre tasks de sistema de arquivos como `<delete>` e `<copy>`.

---

**QUESTÃO 20 - (0,5 pontos)**

O ANT pode ser integrado a IDEs como Eclipse, IntelliJ IDEA e NetBeans, embora essa integração seja menos comum hoje em dia. Sobre a integração do ANT com ambientes de desenvolvimento, analise as alternativas e assinale a correta.

A. IDEs modernas como Eclipse (com plugin Ant), IntelliJ IDEA (suporte nativo) e NetBeans (suporte nativo) podem executar targets do ANT diretamente, visualizar a estrutura do `build.xml` em uma visão hierárquica dos targets e suas dependências, e fornecer destaque de sintaxe para arquivos XML de build, além de permitir a execução de builds ANT como parte de processos de compilação da IDE.

B. Nenhuma IDE moderna suporta ANT, pois todas migraram exclusivamente para Maven e Gradle, sendo que o ANT é considerado obsoleto e não é mais suportado por Eclipse, IntelliJ ou NetBeans desde 2015.

C. O Eclipse requer a instalação manual de um plugin de terceiros para suportar ANT, não havendo suporte nativo, e mesmo com o plugin, apenas a execução básica de targets é possível, sem visualização da estrutura de dependências ou realce de sintaxe.

D. O IntelliJ IDEA não suporta ANT, mas permite que o desenvolvedor configure um "External Tool" para executar o ANT como um processo externo, sem qualquer integração especial ou reconhecimento do arquivo build.xml como um arquivo de build da IDE.

E. No NetBeans, o suporte a ANT é limitado apenas a projetos que foram criados com a estrutura padrão do NetBeans, não sendo possível importar projetos ANT arbitrários ou executar targets personalizados de projetos existentes.
