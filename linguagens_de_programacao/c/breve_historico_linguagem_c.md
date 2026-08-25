A linguagem C tem uma história bem documentada, ligada ao desenvolvimento do sistema operacional Unix. Aqui está um resumo dos principais marcos:

**Origens (final dos anos 1960 – início dos 1970)**

C não surgiu do nada — ela é fruto de uma linhagem de linguagens nos Bell Labs (laboratórios da AT&T, nos EUA):

- **BCPL** (Basic Combined Programming Language), criada por Martin Richards em 1966, foi a base.
- **B**, criada por Ken Thompson por volta de 1969–1970, era uma simplificação do BCPL, usada nas primeiras versões do Unix. B tinha uma limitação importante: não trabalhava bem com diferentes tipos de dados (praticamente tudo era tratado como um único tipo de "palavra" de máquina).

**Criação do C (1972)**

**Dennis Ritchie**, também nos Bell Labs, desenvolveu a linguagem C entre 1969 e 1973, evoluindo a partir do B. A grande contribuição de Ritchie foi introduzir um sistema de tipos (int, char, float, arrays, structs, ponteiros), mantendo a proximidade com o hardware que já existia no B.

**C e o Unix (1973)**

Em 1973, Ritchie e Ken Thompson reescreveram o núcleo do sistema operacional Unix em C — até então, sistemas operacionais eram escritos quase exclusivamente em assembly. Isso foi revolucionário: mostrou que era possível escrever um sistema operacional em uma linguagem de alto nível, portátil, sem perder desempenho. Esse casamento entre C e Unix é a razão pela qual a linguagem se espalhou junto com a adoção do Unix nas universidades e empresas ao longo dos anos 1970 e 1980.

**Padronização**

- **1978**: Brian Kernighan e Dennis Ritchie publicam o livro *"The C Programming Language"*, conhecido como "K&R C" — por muito tempo, funcionou como a especificação informal da linguagem.
- **1989/1990**: a linguagem é formalmente padronizada pela ANSI (American National Standards Institute) e depois pela ISO, dando origem ao **C89/C90** (também chamado de ANSI C). Esse é um marco importante porque unificou dialetos divergentes que haviam surgido entre compiladores.
- **C99** (1999): trouxe declarações de variáveis em qualquer ponto do bloco, comentários `//`, tipos como `long long`, entre outras melhorias.
- **C11** (2011), **C17** (2018) e **C23** (2024): revisões mais recentes, com suporte a concorrência, segurança de tipos e outras modernizações — mantendo, porém, forte compatibilidade retroativa com o C clássico.

**Por que ela ainda importa hoje**

C influenciou diretamente praticamente todas as linguagens que vieram depois — C++, Java, C#, JavaScript e até Python usam sintaxe ou conceitos herdados dela. É também a linguagem em que o próprio Unix, o kernel do Linux, e boa parte de sistemas embarcados, drivers e compiladores ainda são escritos até hoje — justamente por dar controle direto sobre memória (como você viu com os ponteiros da sua calculadora) mantendo um nível de abstração superior ao assembly.

Isso conecta bem com o roteiro da disciplina, aliás: os próprios conceitos que vocês estão trabalhando (alocação estática/dinâmica, ponteiros, modularização) são exatamente os pilares que fizeram do C a linguagem certa para escrever um sistema operacional inteiro nos anos 70.
