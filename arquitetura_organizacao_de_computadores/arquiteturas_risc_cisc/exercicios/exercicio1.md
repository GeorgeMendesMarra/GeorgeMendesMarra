## Exercícios

**1.** Em relação à arquitetura CISC (Complex Instruction Set Computer), assinale a alternativa correta:

a) Possui instruções de tamanho fixo (geralmente 32 bits).  
b) Todas as instruções são executadas em um único ciclo de clock.  
c) Utiliza predominantemente os modos de endereçamento registrador e imediato.  
d) Instruções complexas são decodificadas internamente em micro-operações.  
e) Não permite acesso direto à memória em instruções aritméticas.

---

**2.** Sobre a arquitetura RISC (Reduced Instruction Set Computer), é correto afirmar:

a) Possui grande número de modos de endereçamento (geralmente mais de 10).  
b) Instruções podem ter tamanho variável entre 1 e 15 bytes.  
c) Apenas instruções LOAD e STORE acessam a memória principal.  
d) Não é adequada para pipeline devido à complexidade das instruções.  
e) Possui pequeno número de registradores (tipicamente 8).

---

**3.** Qual das seguintes características é **exclusiva** das arquiteturas CISC tradicionais (pré-anos 2000)?

a) Instruções de tamanho fixo de 32 bits.  
b) Execução fora de ordem (out-of-order).  
c) Tradução dinâmica de instruções em micro-operações.  
d) Grande quantidade de registradores de uso geral (32 ou mais).  
e) Instrução que realiza operação aritmética diretamente com operandos da memória.

---

**4.** Considere a seguinte instrução hipotética de um processador CISC: `ADD [1000], R1` (soma o conteúdo da posição 1000 da memória com o registrador R1 e armazena o resultado em R1). Em uma arquitetura RISC pura (LOAD/STORE), o mesmo efeito exigiria:

a) Uma única instrução, pois RISC também permite acesso direto à memória.  
b) Duas instruções: LOAD e ADD, pois RISC não permite ADD com memória.  
c) Três instruções: LOAD, ADD e STORE.  
d) Quatro instruções: LOAD, LOAD, ADD e STORE.  
e) Uma instrução especial de múltiplos ciclos, similar ao CISC.

---

**5.** Em relação ao pipeline de 5 estágios clássico (IF, ID, EX, MEM, WB), qual arquitetura o utiliza com maior eficiência natural?

a) CISC puro, devido às instruções complexas.  
b) RISC, devido às instruções simples e de tamanho fixo.  
c) Ambas com a mesma eficiência, pois o pipeline não depende da ISA.  
d) Nenhuma, pois pipeline só existe em processadores modernos.  
e) CISC, porque usa microcódigo para ajustar os estágios.

---

**6.** Um processador moderno Intel Core i9 (x86) pode ser classificado como:

a) CISC puro, sem nenhum elemento RISC.  
b) RISC puro, pois executa apenas micro-operações simples.  
c) Híbrido: decodifica instruções CISC em μops RISC-like internamente.  
d) Uma arquitetura completamente diferente, sem relação com CISC ou RISC.  
e) Exclusivamente RISC, pois abandonou o conjunto CISC original.

---

**7.** Qual das alternativas apresenta **apenas** processadores originalmente RISC?

a) Intel 8086, AMD Ryzen, Motorola 68000.  
b) ARM Cortex-A, MIPS, RISC-V, PowerPC.  
c) x86, x86-64, Itanium, Z80.  
d) VAX, IBM System/360, Intel Pentium.  
e) AMD Athlon, Intel Core 2 Duo, ARM Cortex-M.

---

**8.** A principal desvantagem da arquitetura RISC em relação à CISC, em cenários de memória limitada, é:

a) Maior consumo de energia por instrução.  
b) Pipeline mais lento e profundo.  
c) Menor densidade de código (programas mais longos).  
d) Impossibilidade de executar operações de ponto flutuante.  
e) Número insuficiente de registradores.

---

**9.** O que são micro-operações (μops) em processadores x86 modernos?

a) Instruções CISC originais, sem modificação.  
b) Pequenas instruções RISC-like geradas pela decodificação de instruções CISC complexas.  
c) Operações realizadas exclusivamente pelo compilador, não pelo processador.  
d) Instruções assembly que o programador deve escrever manualmente.  
e) Instruções de depuração internas sem função executável.

---

**10.** Em um processador RISC típico (ex.: MIPS, ARMv7), qual das alternativas é **verdadeira**?

a) Instruções como ADD podem ter um operando originado diretamente da memória.  
b) O número de registradores é pequeno (8 ou menos) para reduzir a área do chip.  
c) Os tamanhos das instruções variam conforme a complexidade da operação.  
d) A maioria das instruções executa em um único ciclo de clock (considerando pipeline ideal).  
e) Não é possível implementar pipeline devido à simplicidade das instruções.

---

**11.** Qual fator histórico **não** motivou o surgimento do CISC nos anos 1970?

a) Memória RAM cara e limitada (necessidade de código denso).  
b) Compiladores imaturos (programação assembly frequente).  
c) Alto custo de registradores no chip (poucos registradores).  
d) Necessidade de simplicidade máxima na decodificação de instruções.  
e) Busca por instruções de alto nível que facilitassem a programação manual.

---

**12.** A arquitetura RISC-V se destaca no cenário atual principalmente por:

a) Ser proprietária da Intel, com alto desempenho para jogos.  
b) Ser uma arquitetura aberta, livre e modular, permitindo implementações personalizadas.  
c) Ter o maior número de instruções dentre todas as ISAs.  
d) Ser incompatível com pipeline e execução fora de ordem.  
e) Não suportar sistemas operacionais modernos como Linux.

---

**13.** Sobre modos de endereçamento, é correto afirmar:

a) RISC possui muitos modos de endereçamento (ex.: indireto, indexado, base+deslocamento+escala).  
b) CISC geralmente possui poucos modos de endereçamento (apenas imediato e registrador).  
c) Ambos RISC e CISC possuem exatamente os mesmos modos de endereçamento.  
d) RISC geralmente possui poucos modos, predominando imediato, registrador e deslocamento.  
e) CISC não utiliza modo de endereçamento imediato.

---

**14.** Qual das seguintes tarefas exige **mais instruções** em uma arquitetura RISC LOAD/STORE do que em uma CISC?

a) Somar dois números que estão em registradores.  
b) Copiar um bloco de 100 bytes de uma posição de memória para outra.  
c) Incrementar um contador que está em um registrador.  
d) Realizar uma operação lógica AND entre dois registradores.  
e) Desviar a execução para um rótulo (instrução de jump).

---

**15.** Em relação ao consumo de energia, é correto afirmar:

a) Processadores CISC sempre consomem menos energia que RISC.  
b) RISC tende a ser mais eficiente energeticamente por ter decodificação simples e pipeline eficiente.  
c) O consumo independe totalmente da arquitetura, apenas do processo de fabricação.  
d) RISC consome mais energia porque executa mais instruções para a mesma tarefa.  
e) CISC moderno consome menos energia que RISC devido à tradução em μops.

---

**16.** O que significa a filosofia **LOAD/STORE** presente em arquiteturas RISC?

a) Todas as instruções, inclusive as aritméticas, podem acessar memória livremente.  
b) Apenas as instruções LOAD e STORE acessam a memória; instruções ALU operam apenas sobre registradores.  
c) A memória é carregada apenas uma vez no início do programa e armazenada no final.  
d) O processador não possui registradores; tudo é feito diretamente na memória.  
e) As instruções LOAD e STORE são opcionais e raramente usadas.

---

**17.** Qual alternativa descreve corretamente o papel do compilador em arquiteturas RISC?

a) Deve ser simples, pois as instruções RISC são muito poderosas.  
b) É menos importante que em CISC, pois o hardware faz todo o trabalho.  
c) Precisa ser inteligente para aproveitar o grande número de registradores e gerar sequências eficientes de instruções simples.  
d) Não existe compilador para RISC; programa-se apenas em assembly.  
e) O compilador RISC é idêntico ao compilador CISC, sem diferenças.

---

**18.** Considere um processador com as seguintes características:  
- Instruções de tamanho fixo (32 bits)  
- 32 registradores de uso geral  
- Apenas LOAD e STORE acessam memória  
- Pipeline de 5 estágios  

Essas características pertencem a:

a) Arquitetura CISC pura (ex.: Intel 80486).  
b) Arquitetura RISC pura (ex.: MIPS, ARM).  
c) Arquitetura híbrida exclusiva da AMD.  
d) Arquitetura de processadores DSP apenas.  
e) Arquitetura de mainframes IBM.

---

**19.** Por que os processadores x86 modernos (Intel/AMD) ainda mantêm o conjunto de instruções CISC original?

a) Porque é tecnicamente superior em todos os aspectos ao RISC.  
b) Para manter compatibilidade binária com software legado (aplicativos e sistemas operacionais antigos).  
c) Porque não é possível implementar RISC em chips modernos.  
d) Porque a Intel não conhece arquitetura RISC.  
e) Por exigência legal do governo dos EUA.

---

**20.** Em um projeto de processador para um **dispositivo vestível (smartwatch)** com forte restrição de bateria e área, a arquitetura mais adequada seria:

a) CISC puro (ex.: x86), pois tem código denso.  
b) RISC (ex.: ARM Cortex-M), devido à eficiência energética e decodificação simples.  
c) CISC com microcódigo complexo, pois consome menos energia.  
d) Qualquer arquitetura, pois o consumo é igual em todos os chips.  
e) Exclusivamente arquitetura VAX.

---

## Gabarito

| Q | Resposta | Q | Resposta |
|---|----------|---|----------|
| 1 | **d**     | 11| **d**     |
| 2 | **c**     | 12| **b**     |
| 3 | **e**     | 13| **d**     |
| 4 | **c**     | 14| **b**     |
| 5 | **b**     | 15| **b**     |
| 6 | **c**     | 16| **b**     |
| 7 | **b**     | 17| **c**     |
| 8 | **c**     | 18| **b**     |
| 9 | **b**     | 19| **b**     |
| 10| **d**     | 20| **b**     |

---
- **Correção:** projete o gabarito após a coleta e resolva as dúvidas nas questões com maior taxa de erro.

Caso precise das questões em formato editável (Word, PDF) ou da resolução comentada de cada uma, é só avisar!
