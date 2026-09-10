# Arquitetura e Organização de Computadores

*Material de apoio — Curso de Engenharia de Software*

> **Ementa:** Conceitos básicos da arquitetura e organização de computadores. Histórico dos sistemas computacionais. Conversão de base e aritmética em outras bases. Componentes de um Computador: Modelo Von Neumann. Execução de programas. Conjunto de Instruções, Mecanismos de Interrupção e de Exceção. Modos de endereçamento. Interfaces paralela, serial e USB. Dispositivos de entrada e saída. Hierarquia de Memória. Multiprocessadores. Multicomputadores. Arquiteturas Paralelas. Arquiteturas RISC e CISC.

A arquitetura e organização de computadores é a disciplina que explica como estruturamos o "cérebro" das máquinas para processar dados de forma cada vez mais eficiente. Essa jornada vai desde conceitos teóricos, como os de Alan Turing, até os sistemas complexos, paralelos e multiprocessados que usamos hoje. Este texto percorre os tópicos da ementa na ordem em que costumam ser trabalhados em sala de aula, servindo como um roteiro de estudo — os aprofundamentos de cada assunto estão nas pastas correspondentes deste repositório.

---

## 1. Conceitos Básicos da Arquitetura e Organização de Computadores

Antes de detalhar os marcos técnicos, é preciso distinguir os dois termos que dão nome à disciplina:

- **Arquitetura:** refere-se aos atributos de um sistema que são **visíveis ao programador** — conjunto de instruções, número de bits, tipos de dados suportados, modos de endereçamento.
- **Organização:** refere-se a **como** esses atributos são efetivamente **implementados fisicamente** — sinais de controle, barramentos, interfaces entre o computador e os periféricos, tecnologia de memória.

Em outras palavras: a arquitetura define **o que** o sistema faz do ponto de vista de quem programa; a organização define **como** isso é realizado internamente, em hardware.

---

## 2. Histórico dos Sistemas Computacionais

### 2.1 A Base Teórica: a Máquina de Turing (1936)

Antes de existir qualquer circuito, **Alan Turing** — matemático britânico considerado um dos pais da Ciência da Computação — concebeu um modelo matemático abstrato que definiu, pela primeira vez, o que significa "computar". A **Máquina de Turing** é composta por três elementos essenciais:

- **Uma fita de memória**, dividida em células, capaz de armazenar símbolos;
- **Uma cabeça de leitura/gravação**, que se move pela fita lendo e escrevendo símbolos;
- **Um conjunto finito de estados e regras lógicas**, que determinam qual ação tomar a partir do símbolo lido.

Esse modelo é teoricamente capaz de simular qualquer algoritmo que um computador moderno possa executar — a **Turing-completude**. Turing também formulou o conceito de "computador universal": uma única máquina capaz de simular qualquer outra, desde que receba as instruções corretas — germe do computador de propósito geral que usamos até hoje. Durante a Segunda Guerra Mundial, ele liderou a equipe que quebrou o código da máquina Enigma em Bletchley Park, e em 1950 propôs o célebre **Teste de Turing**, ainda hoje referência nos debates sobre inteligência artificial.

Vale registrar que a Máquina de Turing teve precursores mecânicos: em 1837, **Charles Babbage** já havia concebido a **Máquina Analítica**, com um "Moinho" (unidade de processamento) e um "Armazém" (memória), e **Ada Lovelace** escreveu, em 1843, o que é considerado o primeiro programa de computador da história — antecipando conceitos como laços e sub-rotinas.

### 2.2 A Primeira Geração e a Arquitetura de Von Neumann (1945)

Até meados da década de 1940, os computadores eram "programados" manualmente, trocando cabos e ajustando chaves físicas. **John von Neumann** propôs uma mudança radical: o **Conceito de Programa Armazenado**, base do tópico 4 deste texto.

### 2.3 A Revolução do Transistor e dos Circuitos Integrados (1950–1970)

- **Segunda Geração:** a substituição das válvulas — que esquentavam muito, consumiam energia e falhavam com frequência — pelos **transistores** tornou os computadores menores, mais rápidos e confiáveis.
- **Terceira Geração:** o surgimento dos **Circuitos Integrados (CI)** permitiu reunir milhares de transistores em um único chip de silício, dando início a um processo contínuo de miniaturização e aumento de velocidade de clock (tendência mais tarde descrita pela Lei de Moore).

Um marco importante e por vezes esquecido dessa fase é o **Atanasoff-Berry Computer (ABC)**, construído entre 1937 e 1942 por **John Vincent Atanasoff** e seu aluno Clifford Berry — pioneiro no uso de aritmética binária e memória regenerativa por capacitores. Uma decisão judicial de 1973 (*Honeywell vs. Sperry Rand*) reconheceu Atanasoff, e não os criadores do ENIAC, como o inventor do primeiro computador eletrônico digital.

### 2.4 Microprocessadores e Evolução Organizacional (1971 – presente)

Com o lançamento do **Intel 4004**, em 1971 — o primeiro microprocessador comercial —, toda a CPU passou a ser integrada em um único chip. A partir daí, a organização dos computadores passou a focar em desempenho, por meio de:

- **Pipeline:** processar várias instruções simultaneamente, em estágios sobrepostos, como uma linha de montagem;
- **Memória Cache:** memórias pequenas e ultrarrápidas entre a CPU e a RAM, para evitar que o processador "fique esperando" (ver tópico 10 — Hierarquia de Memória);
- **Processamento Multicore:** em vez de apenas aumentar a frequência de um único núcleo, os fabricantes passaram a integrar vários núcleos independentes em um mesmo processador (ver tópico 11).

---

## 3. Conversão de Base e Aritmética em Outras Bases

Todo processamento digital se apoia na representação de números em diferentes **bases numéricas**. Os sistemas mais usados em arquitetura de computadores são:

- **Decimal (base 10):** usado na interação humana com o sistema;
- **Binário (base 2):** representação nativa do hardware digital (0 e 1, correspondentes aos estados elétricos de um transistor);
- **Octal (base 8)** e **Hexadecimal (base 16):** notações compactas para representar grupos de bits (um dígito hexadecimal equivale exatamente a 4 bits), amplamente usadas para representar endereços de memória, códigos de operação e máscaras de bits.

A conversão entre bases segue o teorema fundamental da numeração posicional (N = Σ dᵢ × bⁱ), e a aritmética binária — soma, subtração, representação de números negativos em complemento de dois, ponto fixo e ponto flutuante (padrão IEEE 754) — é o que efetivamente acontece dentro da Unidade Lógica e Aritmética (ULA) a cada instrução executada. O material completo, com exercícios resolvidos, está na pasta `sistemas_de_numeracao`.

---

## 4. Componentes de um Computador: Modelo Von Neumann

Na arquitetura de **Von Neumann** — ainda a base da maioria dos computadores pessoais —, o sistema é dividido em três partes principais, que se comunicam por meio de **barramentos** (de dados, de endereços e de controle):

- **Unidade Central de Processamento (CPU):** composta pela **Unidade Lógica e Aritmética (ULA)**, responsável pelos cálculos, e pela **Unidade de Controle**, responsável por buscar, decodificar e coordenar a execução das instruções;
- **Memória Principal:** onde dados e instruções ficam armazenados **juntos**, na mesma estrutura de endereçamento — a grande inovação de von Neumann foi tratar instruções e dados da mesma forma, como sequências de bits, tornando os computadores programáveis por software;
- **Sistemas de Entrada e Saída (I/O):** responsáveis pela comunicação com o mundo externo.

Essa unificação entre memória de dados e de instruções gera uma limitação conhecida como **gargalo de von Neumann** (*von Neumann bottleneck*), já que um único barramento precisa transportar tanto instruções quanto dados. Uma alternativa organizacional é o **modelo de Harvard**, que separa fisicamente a memória de instruções da memória de dados, permitindo acessos simultâneos — solução hoje usada em caches (L1 de instrução e L1 de dados) e em microcontroladores.

---

## 5. Execução de Programas

A execução de um programa ocorre por meio do **ciclo de busca-decodificação-execução** (*fetch-decode-execute cycle*), o processo fundamental que define como um computador processa instruções, repetido continuamente enquanto a máquina está ligada:

1. **Busca (Fetch):** a Unidade de Controle busca a próxima instrução na memória, usando o endereço indicado pelo **contador de programa (PC)**;
2. **Decodificação (Decode):** a instrução binária é interpretada, identificando a operação a ser realizada e os operandos envolvidos;
3. **Execução (Execute):** a ULA (ou outro circuito) realiza a operação — cálculo aritmético, acesso à memória, desvio de fluxo etc.;
4. **Armazenamento/Atualização:** o resultado é gravado (registrador ou memória) e o contador de programa é atualizado para a próxima instrução.

Esse ciclo, presente ainda na Máquina Analítica de Babbage e formalizado teoricamente por Turing, foi refinado ao longo das décadas com técnicas como *pipeline*, execução superescalar (múltiplas instruções por ciclo) e execução fora de ordem, usadas nos processadores modernos.

---

## 6. Conjunto de Instruções, Mecanismos de Interrupção e de Exceção

### 6.1 Conjunto de Instruções (ISA)

A **ISA (Instruction Set Architecture)** é a interface entre o hardware e o software — o conjunto de instruções que um processador é capaz de reconhecer e executar. Duas filosofias de projeto de ISA se consolidaram historicamente (ver também o tópico 12 — RISC e CISC).

### 6.2 Interrupções (hardware)

Uma **interrupção** é um sinal enviado ao processador por um dispositivo de hardware, indicando que um evento precisa de atenção imediata (uma tecla pressionada, dados chegando pela rede, um temporizador do sistema operacional). O processador suspende temporariamente sua execução, salva o estado dos registradores, executa uma rotina especial chamada **Tratador de Interrupção (ISR)** e depois retorna ao que estava fazendo. Um **controlador de interrupções** (como o PIC ou o APIC em sistemas x86) permite priorizar múltiplas interrupções simultâneas.

### 6.3 Exceções (software)

As **exceções** são geradas internamente pelo próprio processador, como resultado de uma instrução que causou um erro ou uma condição especial:

- **Faltas (Faults):** erros recuperáveis, que permitem que o programa continue (ex.: *page fault* na memória virtual);
- **Trapas (Traps):** intencionais, usadas para chamadas de sistema (*system calls*), quando o programa solicita algo ao kernel;
- **Abortos (Aborts):** erros graves e irrecuperáveis (ex.: falha de hardware).

Esses mecanismos também têm relação direta com segurança: uma tentativa de acesso a uma área de memória proibida gera uma exceção de violação de acesso, interrompendo potenciais ataques antes que comprometam a integridade do sistema.

---

## 7. Modos de Endereçamento

Os **modos de endereçamento** definem como uma instrução localiza os operandos sobre os quais vai atuar. Os principais são:

- **Imediato:** o próprio valor do operando está embutido na instrução (ex.: `MOV AX, 5`);
- **Direto:** a instrução contém o endereço de memória exato do operando;
- **Indireto:** a instrução contém o endereço de um registrador ou posição de memória que, por sua vez, aponta para o endereço real do operando;
- **Registrador:** o operando está em um registrador da CPU, com acesso muito mais rápido do que à memória;
- **Indexado:** o endereço final é calculado somando um valor-base a um índice (ideal para percorrer vetores);
- **Base + deslocamento (*base + offset*):** combina um registrador-base com um deslocamento fixo, muito usado para acessar campos de estruturas e variáveis locais na pilha.

Arquiteturas CISC tendem a oferecer muitos modos de endereçamento, permitindo instruções que acessam a memória diretamente; arquiteturas RISC restringem o acesso à memória a instruções específicas de *load/store*, simplificando o hardware.

---

## 8. Interfaces Paralela, Serial e USB

A comunicação entre o computador e seus periféricos evoluiu por meio de diferentes padrões de interface:

- **Interface paralela:** transmite vários bits simultaneamente por múltiplos fios (um por bit), como nas antigas portas de impressora (Centronics/LPT). É rápida em curtas distâncias, mas sofre com interferência eletromagnética e desalinhamento de sinais (*skew*) em cabos longos, o que limitou sua velocidade prática.
- **Interface serial:** transmite os bits um após o outro, em um único canal (ex.: RS-232). É mais lenta bit a bit, mas permite cabos mais longos e, com o avanço da eletrônica, passou a superar a interface paralela em velocidade efetiva — princípio usado também em barramentos internos modernos, como o PCI Express.
- **USB (Universal Serial Bus):** padrão serial que unificou praticamente todas as conexões de periféricos (teclado, mouse, armazenamento, áudio) em uma única interface plug-and-play, com alimentação elétrica integrada, detecção automática de dispositivos (*hot-plug*) e velocidades crescentes ao longo de suas versões (USB 1.1, 2.0, 3.x e USB4).

---

## 9. Dispositivos de Entrada e Saída

Os **dispositivos de entrada e saída (E/S)** permitem a interação entre o usuário, o ambiente externo e o sistema computacional:

| Categoria | Definição | Exemplos |
| :--- | :--- | :--- |
| **Entrada** | Inserem dados, comandos e sinais no computador | Teclado, mouse, scanner, microfone, webcam |
| **Saída** | Exibem ou reproduzem informações processadas | Monitor, impressora, caixas de som |
| **Mistos (E/S)** | Realizam entrada e saída | Disco rígido/SSD, tela touchscreen, modem |

Esses dispositivos se comunicam com o sistema por meio de **controladores de E/S**, que fazem a interface entre o barramento e o periférico. Para grandes volumes de dados, utiliza-se o **Acesso Direto à Memória (DMA)**, que permite a transferência de dados entre memória e periférico sem intervenção constante da CPU — aumentando significativamente o desempenho e liberando o processador para outras tarefas.

---

## 10. Hierarquia de Memória

Como memórias rápidas são caras e memórias baratas são lentas, os computadores organizam seus dispositivos de armazenamento em uma **hierarquia de memória**, equilibrando velocidade, capacidade e custo:

1. **Registradores:** dentro da própria CPU, com acesso quase instantâneo, mas em número e capacidade muito reduzidos;
2. **Memória Cache (L1, L2, L3):** memórias pequenas e ultrarrápidas, posicionadas entre a CPU e a RAM, que armazenam os dados e instruções mais recentemente (ou frequentemente) usados;
3. **Memória Principal (RAM):** volátil, armazena os dados e instruções em execução no momento;
4. **Memória Virtual:** técnica que usa o armazenamento secundário para simular mais memória RAM do que a fisicamente instalada, gerenciada pelo sistema operacional;
5. **Armazenamento Secundário (HD/SSD):** não volátil, garante a persistência dos dados mesmo com o computador desligado.

Quanto mais próximo da CPU, mais rápido e mais caro (por byte) é o nível de memória — e menor é sua capacidade. Essa organização em camadas é o que permite que processadores modernos, mesmo com uma RAM relativamente lenta, mantenham alto desempenho na maior parte do tempo.

---

## 11. Multiprocessadores, Multicomputadores e Arquiteturas Paralelas

Superado o limite de ganho de desempenho apenas aumentando a frequência de um único núcleo, a arquitetura de computadores passou a explorar diferentes formas de **paralelismo**:

- **Multiprocessadores:** sistemas com **múltiplos processadores (ou núcleos) compartilhando a mesma memória principal**, o que facilita a comunicação entre eles, mas exige mecanismos de sincronização (como *locks* e protocolos de coerência de cache) para evitar inconsistências no acesso concorrente aos dados. É o modelo típico dos processadores multicore usados em notebooks, desktops e servidores atuais.
- **Multicomputadores:** sistemas formados por **múltiplos computadores independentes, cada um com sua própria memória**, conectados por uma rede de interconexão e que trocam informações por **troca de mensagens** em vez de memória compartilhada. É o modelo base de clusters e de boa parte da computação distribuída e em nuvem.
- **Arquiteturas Paralelas:** de forma mais geral, classificam-se segundo a taxonomia de Flynn, com base no número de fluxos de instruções e de dados que podem ser processados simultaneamente:
  - **SISD** (*Single Instruction, Single Data*): um processador tradicional, sequencial;
  - **SIMD** (*Single Instruction, Multiple Data*): uma mesma instrução aplicada a múltiplos dados simultaneamente — princípio usado em GPUs e em extensões vetoriais de CPUs;
  - **MISD** (*Multiple Instruction, Single Data*): pouco usado comercialmente, aplicado em sistemas de tolerância a falhas;
  - **MIMD** (*Multiple Instruction, Multiple Data*): múltiplos processadores executando instruções diferentes sobre dados diferentes — o modelo dos multiprocessadores e multicomputadores modernos.

---

## 12. Arquiteturas RISC e CISC

A **ISA (Instruction Set Architecture)** é a interface entre hardware e software, e ao longo da história duas filosofias de projeto dominaram esse espaço:

- **CISC (Complex Instruction Set Computer):** instruções complexas e de alto nível, capazes de realizar múltiplas operações (busca, cálculo, acesso à memória) em uma única instrução; instruções de tamanho variável; muitos modos de endereçamento; poucos registradores de uso geral. Exemplo clássico: a família x86 da Intel/AMD.
- **RISC (Reduced Instruction Set Computer):** conjunto de instruções reduzido e simples, todas de tamanho fixo e, em geral, executadas em um único ciclo de clock; acesso à memória restrito a instruções específicas de *load/store*; grande número de registradores de uso geral. Exemplos: ARM (presente na maioria dos smartphones) e RISC-V.

A escolha entre RISC e CISC afeta diretamente o desempenho, o consumo energético, a área do chip e a complexidade do compilador — e essa disputa de filosofias atravessa boa parte da história narrada no tópico 2, da miniaturização dos anos 1970 à era dos processadores multicore.

---

## Cinema e Computação: a análise fílmica como ferramenta de ensino

A história da arquitetura de computadores também pode ser explorada através do cinema, que ajuda a dar rosto e contexto humano a conceitos que, no papel, parecem puramente técnicos. A **análise fílmica** — método de interpretação crítica de um filme quanto à narrativa, à linguagem cinematográfica e ao contexto histórico e social que ele retrata — é usada aqui para conectar três obras aos marcos apresentados neste texto:

- 🎬 ***Atanasoff, o Pai do Computador*** (documentário) — dialoga com o tópico 2.3, mostrando como os fundamentos eletrônicos (válvulas, aritmética binária) antecederam a padronização da arquitetura de Von Neumann.
- 🎬 ***Os Piratas do Vale do Silício*** (1999) — ilustra, em tom quase alegórico, como a era dos microprocessadores (tópico 2.4) não foi construída apenas com avanços técnicos, mas também por disputas de mercado e decisões de licenciamento (como o contrato do MS-DOS com a IBM).
- 🎬 ***Steve Jobs*** (2015) — acompanha a evolução pessoal de Jobs em paralelo à evolução dos produtos (Macintosh, NeXT, iMac) que ajudaram a popularizar a arquitetura de computadores pessoais.

Juntos, os três filmes complementam a linha teórica de Turing, Babbage, Atanasoff e Von Neumann com a dimensão humana e comercial da computação: arquitetura e organização de computadores não evoluíram apenas em laboratórios, mas também em garagens, salas de tribunal e disputas corporativas.
