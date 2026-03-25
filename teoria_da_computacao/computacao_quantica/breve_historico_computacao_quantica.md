---

## 1. Introdução: O Que é Computação Quântica?

A computação quântica é um campo emergente que utiliza os princípios da mecânica quântica para processar informações de maneira fundamentalmente diferente dos computadores clássicos .

Enquanto os computadores tradicionais utilizam **bits** que representam exclusivamente 0 ou 1, os computadores quânticos utilizam **qubits (quantum bits)** , que podem existir em **superposição** — uma combinação simultânea de 0 e 1 . Além disso, os qubits podem ser **emaranhados (entangled)** , um fenômeno no qual o estado de um qubit está intrinsecamente ligado ao estado de outro, independentemente da distância entre eles .

Essas propriedades permitem que computadores quânticos realizem certos tipos de cálculos exponencialmente mais rápido do que os supercomputadores clássicos, com potencial para revolucionar áreas como criptografia, desenvolvimento de medicamentos, inteligência artificial e ciência dos materiais .


## 2. Fundamentos: As Raízes da Mecânica Quântica (1900-1920)

A história da computação quântica começa, na verdade, com o desenvolvimento da própria mecânica quântica no início do século XX — um período que o físico Paul Dirac chamou de "teoria de quase tudo que importa na vida cotidiana" .

### 2.1. Os Primeiros Passos

| Ano | Contribuição | Cientista | Significado |
|-----|--------------|-----------|-------------|
| **1900** | Teoria dos Quanta | Max Planck | Propôs que a energia não é contínua, mas existe em pacotes discretos chamados "quanta"  |
| **1905** | Fótons | Albert Einstein | Introduziu o conceito de luz quantizada (fótons), unificando propriedades de onda e partícula  |
| **1913** | Modelo Atômico | Niels Bohr | Propôs que elétrons ocupam níveis de energia específicos ao redor do núcleo  |
| **1925** | Mecânica Matricial | Werner Heisenberg | Formulou a primeira versão completa da mecânica quântica em Helgoland  |
| **1926** | Equação de Schrödinger | Erwin Schrödinger | Descreve a evolução temporal de sistemas quânticos, tornando-se um pilar da teoria  |
| **1927** | Princípio da Incerteza | Werner Heisenberg | Demonstrou que certos pares de propriedades (como posição e momento) não podem ser conhecidos com precisão simultânea  |
| **1935** | Paradoxo EPR | Einstein, Podolsky, Rosen | Introduziu o conceito de emaranhamento quântico, que mais tarde se tornaria fundamental para a computação quântica  |

Esses avanços estabeleceram a base conceitual que décadas depois seria aplicada à teoria da informação e à computação .


## 3. A Gênese da Computação Quântica (1959-1985)

A ponte entre a mecânica quântica e a computação começou a ser construída na segunda metade do século XX.

### 3.1. A Visão de Feynman: "Há Muito Espaço Lá Embaixo"

Em **1959**, o físico Richard Feynman proferiu uma palestra visionária intitulada **"Plenty of Room at the Bottom"** (Há Muito Espaço Lá Embaixo). Ele sugeriu que, em escalas atômicas, novos fenômenos físicos poderiam ser explorados para criar dispositivos computacionais radicalmente diferentes .

### 3.2. Década de 1970: Primeiras Conexões entre Física e Informação

Na década de 1970, pesquisadores começaram a explorar formalmente a relação entre a mecânica quântica e a teoria da informação :

- **1970:** James Park articulou o **teorema da não-clonagem (no-cloning theorem)** , demonstrando que não é possível criar uma cópia perfeita de um estado quântico arbitrário .
- **1973:** Alexander Holevo publicou o **teorema de Holevo**, mostrando que, embora n qubits possam carregar mais que n bits de informação clássica, no máximo n bits são acessíveis .
- **1976:** Roman Ingarden publicou um dos primeiros trabalhos sobre "Teoria da Informação Quântica" .

### 3.3. O Marco de 1980: O Primeiro Modelo de Computador Quântico

Em **1980**, o físico **Paul Benioff**, do Argonne National Laboratory, publicou a primeira descrição de um modelo mecânico-quântico de um computador. Benioff demonstrou que uma máquina de Turing poderia operar sob as leis da mecânica quântica, lançando as bases teóricas para o campo .

### 3.4. 1981: O Nascimento Oficial do Campo

Em maio de 1981, ocorreu um evento seminal no MIT: a **Primeira Conferência sobre Física da Computação (First Conference on the Physics of Computation)** . Neste evento:

- **Paul Benioff** apresentou seu modelo de computador quântico .
- **Richard Feynman** fez uma palestra que se tornaria lendária: **"Simulando Física com Computadores" (Simulating Physics with Computers)** . Feynman argumentou que a natureza é fundamentalmente quântica e que simular sistemas quânticos em computadores clássicos é ineficiente — seria necessário um computador que operasse com base nos mesmos princípios quânticos .

Feynman é frequentemente creditado como o **"pai da computação quântica"** por ter popularizado a ideia e inspirado uma geração de pesquisadores .

### 3.5. 1985: O Computador Quântico Universal

Em **1985**, o físico **David Deutsch** , da Universidade de Oxford, publicou um artigo fundamental no qual descreveu o conceito de um **"computador quântico universal" (universal quantum computer)** . Inspirado pela máquina de Turing clássica, Deutsch formulou a ideia de uma máquina de Turing quântica capaz de simular qualquer processo físico . Ele também introduziu o conceito de **paralelismo quântico**, no qual um sistema quântico processa múltiplas possibilidades simultaneamente .

Neste mesmo ano, Deutsch propôs o primeiro algoritmo quântico, conhecido como **Deutsch Algorithm** .


## 4. A Era dos Algoritmos Quânticos (1992-1996)

O período entre 1992 e 1996 foi marcado por descobertas teóricas que demonstraram o potencial transformador dos computadores quânticos.

### 4.1. 1992: Deutsch-Jozsa Algorithm

Em 1992, Deutsch e Richard Jozsa expandiram o algoritmo original de Deutsch para múltiplos qubits, criando o **algoritmo Deutsch-Jozsa**. Este foi o primeiro algoritmo a demonstrar que um computador quântico poderia resolver um problema específico de forma mais eficiente que qualquer computador clássico determinístico .

### 4.2. 1993: Simon's Algorithm

Dan Simon, da Université de Montréal, desenvolveu um algoritmo que resolvia um problema de oráculo com aceleração exponencial em relação aos melhores algoritmos clássicos. Este trabalho introduziu ideias fundamentais que seriam posteriormente utilizadas por Peter Shor .

### 4.3. 1994: Shor's Algorithm — O Rompimento

Em **1994**, **Peter Shor** , dos Laboratórios Bell da AT&T, publicou um algoritmo que chocou as comunidades de ciência da computação e criptografia . O **algoritmo de Shor** demonstrou que um computador quântico poderia fatorar números inteiros grandes exponencialmente mais rápido que os melhores algoritmos clássicos conhecidos .

Este algoritmo ameaçava diretamente os sistemas de criptografia assimétrica amplamente utilizados (como RSA), cuja segurança se baseia na dificuldade de fatorar grandes números . O algoritmo de Shor é considerado o **"killer application"** da computação quântica e impulsionou massivamente o interesse no campo .

### 4.4. 1996: Grover's Algorithm

Em **1996**, **Lov Grover** , também dos Laboratórios Bell, propôs um algoritmo para busca em bancos de dados não estruturados que oferece uma **aceleração quadrática** em relação aos algoritmos clássicos . Embora menos dramática que a aceleração exponencial de Shor, a aceleração quadrática do algoritmo de Grover tem aplicações muito mais amplas, afetando qualquer problema que dependa de busca por força bruta — incluindo a criptografia simétrica .


## 5. O Nascimento Experimental: Primeiros Qubits e Portas Lógicas (1995-2001)

Enquanto os algoritmos avançavam, pesquisadores começaram a construir os primeiros dispositivos quânticos físicos.

### 5.1. 1995: Primeira Porta Lógica Quântica

Em **18 de dezembro de 1995**, ocorreu um marco fundamental: **Chris Monroe e David Wineland** , no National Institute of Standards and Technology (NIST) em Boulder, Colorado, demonstraram a primeira porta lógica quântica controlada-NOT (CNOT) usando íons aprisionados (trapped ions) .

Esta demonstração provou que era possível realizar operações lógicas quânticas em hardware real, transformando a computação quântica de uma abstração teórica em uma possibilidade experimental concreta .

### 5.2. 1995: Primeiros Códigos de Correção de Erros

No mesmo ano, **Peter Shor** propôs os primeiros esquemas de **correção de erros quânticos (quantum error correction)** , um requisito fundamental para construir computadores quânticos tolerantes a falhas .

### 5.3. 1998: Primeira Execução de Algoritmo Quântico

Em **1998**, duas equipes independentes — uma liderada por **Jonathan Jones e Michele Mosca** em Oxford, e outra por **Isaac Chuang** na IBM — executaram pela primeira vez um algoritmo quântico (o algoritmo de Deutsch) em um computador de 2 qubits baseado em ressonância magnética nuclear (NMR) .

### 5.4. 1999: O Qubit Supercondutor

Em **1999**, **Yasunobu Nakamura e Jaw-Shen Tsai** , da NEC, demonstraram que circuitos supercondutores poderiam ser utilizados como qubits — uma abordagem que se tornaria dominante na indústria .

### 5.5. 2001: Fatorando o Número 15

Em **2001**, a equipe de **Isaac Chuang** na IBM executou o algoritmo de Shor em um computador quântico de 7 qubits, fatorando o número **15** (3 × 5) . Embora trivial, foi a primeira demonstração prática do algoritmo mais famoso da computação quântica .


## 6. A Era NISQ e a Primeira Indústria (2007-2019)

A partir de meados dos anos 2000, a computação quântica começou a transicionar da academia para a indústria, com o surgimento das primeiras empresas e o início da corrida pela "supremacia quântica".

### 6.1. 2007: D-Wave e Computação Quântica Comercial

Em **2007**, a empresa canadense **D-Wave Systems** demonstrou um computador quântico de 28 qubits baseado em **quantum annealing** — uma abordagem especializada para problemas de otimização .

### 6.2. 2011: Primeiro Computador Quântico Comercial

Em maio de **2011**, a D-Wave lançou o **D-Wave One**, o primeiro computador quântico comercialmente disponível. O sistema tinha 128 qubits e custava aproximadamente US$ 10 milhões . Embora não fosse um computador quântico universal, marcou o início da indústria quântica .

### 6.3. 2016: IBM Quantum Experience — Acesso na Nuvem

Em maio de **2016**, a IBM tornou seu processador quântico de 5 qubits acessível pela internet através do serviço **IBM Quantum Experience** . Nas primeiras duas semanas, mais de 17.000 pessoas se registraram, democratizando o acesso à experimentação quântica .

### 6.4. 2018: A Criação do Termo NISQ

O físico **John Preskill** cunhou o termo **NISQ (Noisy Intermediate-Scale Quantum)** — "Quântico de Escala Intermediária e Ruidoso" — para descrever a era atual, caracterizada por computadores com centenas de qubits, mas ainda suscetíveis a erros e sem correção de falhas completa .

### 6.5. 2019: Google Alega "Supremacia Quântica"

Em setembro de **2019**, o Google anunciou que seu processador **Sycamore** (53 qubits) havia realizado um cálculo em **200 segundos** que, segundo a empresa, levaria cerca de **10.000 anos** para o supercomputador clássico mais poderoso da época .

A alegação de "supremacia quântica" (termo que posteriormente evoluiu para "vantagem quântica") foi controversa. A IBM, sua principal concorrente, contestou imediatamente, argumentando que o problema poderia ser resolvido em dias, não milênios .


## 7. Maturação e Correção de Erros (2020-2026)

A década de 2020 tem sido marcada pelo foco em escalabilidade, redução de erros e os primeiros passos rumo à computação quântica tolerante a falhas.

### 7.1. 2020-2021: Avanços Chineses

Em 2020, pesquisadores chineses demonstraram um simulador quântico baseado em fótons que resolveu um problema que, segundo eles, levaria bilhões de anos para um computador clássico. Em 2021, demonstraram um processador supercondutor de 66 qubits .

### 7.2. 2022: O Algoritmo Clássico que Contestou a Supremacia

Um grupo da Academia Chinesa de Ciências desenvolveu um algoritmo clássico que conseguiu simular os cálculos do Google Sycamore em **15 horas** usando 512 GPUs — e argumentaram que, com acesso ao maior supercomputador do mundo, poderiam fazê-lo em segundos . Este evento foi um lembrete de que a vantagem quântica é um "alvo móvel": os algoritmos e hardware clássicos também continuam avançando .

### 7.3. 2023: Recorde de Qubits Lógicos

Em dezembro de **2023**, pesquisadores da **Universidade de Harvard e QuEra** anunciaram a criação de **48 qubits lógicos** operando simultaneamente — 10 vezes mais do que qualquer demonstração anterior . Qubits lógicos são agrupamentos de qubits físicos que, através de correção de erros, podem realizar operações confiáveis .

### 7.4. 2025: Avanços Significativos

O ano de **2025**, designado pelas Nações Unidas como o **Ano Internacional da Ciência e Tecnologia Quântica** (em comemoração ao centenário da mecânica quântica), testemunhou avanços importantes :

- **Google** demonstrou que a correção de erros quânticos poderia sustentar mais de 10 bilhões de ciclos sem falhas .
- **Microsoft** revelou seu chip **Majorana**, baseado em qubits topológicos — uma abordagem teórica que promete maior resistência a erros .
- **IonQ** anunciou a conquista de **99,99% de fidelidade** em portas de dois qubits, estabelecendo um recorde mundial .

### 7.5. O Estado da Arte em 2026

Em **2026**, a computação quântica encontra-se na **era NISQ** . Os principais avanços incluem:

| Aspecto | Situação Atual |
|---------|----------------|
| **Hardware** | Processadores com centenas de qubits (IBM ultrapassou 1.000 qubits em 2023); múltiplas plataformas: supercondutores (Google, IBM), íons aprisionados (IonQ, Quantinuum), átomos neutros (QuEra, Harvard)  |
| **Correção de Erros** | Primeiras demonstrações de qubits lógicos em escala relevante; códigos qLDPC promissores  |
| **Algoritmos** | Algoritmos NISQ (VQE, QAOA) sendo testados para aplicações práticas; recursos para algoritmos criptograficamente relevantes diminuíram drasticamente  |
| **Indústria** | Ecossistema consolidado com empresas líderes (Google, IBM, IonQ, Quantinuum, PsiQuantum, QuEra) e programas governamentais multibilionários nos EUA, Europa e China  |
| **Criptografia Pós-Quântica** | NIST publicou os primeiros padrões PQC em 2024; cronograma para migração de sistemas governamentais estabelecido  |

O consenso atual é que ainda são necessários de **10 a 15 anos** para que computadores quânticos tolerantes a falhas estejam disponíveis para aplicações comerciais generalizadas . A verdadeira vantagem quântica para problemas práticos — como simulação de materiais, desenvolvimento de medicamentos e otimização complexa — ainda está por vir .


## 8. Conclusão: Perspectivas para o Futuro

A história da computação quântica é uma história de **aceleração**. Seus fundamentos foram estabelecidos ao longo de décadas de pesquisa teórica, e a última década testemunhou uma rápida transição para a prática, com o surgimento de uma indústria global, acesso em nuvem e os primeiros vislumbres de vantagem quântica.

O próximo grande marco será a construção de computadores quânticos **tolerantes a falhas (fault-tolerant)** , capazes de executar algoritmos como o de Shor para números criptograficamente relevantes e simulações de materiais com precisão suficiente para aplicações reais. Este objetivo, outrora considerado distante, agora é visto como alcançável dentro de uma ou duas décadas.

Como observou John Preskill, o campo está entrando em seu "segundo século quântico" — uma era em que a manipulação controlada de sistemas quânticos individuais não é mais apenas um experimento de laboratório, mas uma ferramenta em desenvolvimento para resolver alguns dos problemas mais desafiadores da humanidade .


## Referências

1. Quantum Frontiers, Caltech. *Quantum computing in the second quantum century*. 2025. 
2. TechTarget. *The history of quantum computing: A complete timeline*. 2025. 
3. 玻色量子. *通识：量子算法发展的四大阶段*. 2025. 
4. Yahoo. *History of quantum computing: 12 key moments that shaped the future of computers*. 2024. 
5. Wikipedia. *Timeline of quantum computing and communication*. 
6. IonQ. *The Birth of Quantum Computers: How Dr. Chris Monroe Ignited the Quantum Computing Revolution*. 2025. 
7. South Carolina Quantum Association. *Thirty Years of Quantum Technology: The Growth of Computing, Communication, and Sensing (1995 to 2025)*. 2025. 
8. Supermicro. *何謂量子運算？* 
9. Quantum Zeitgeist. *History Of Quantum Computing*. 2025. 
