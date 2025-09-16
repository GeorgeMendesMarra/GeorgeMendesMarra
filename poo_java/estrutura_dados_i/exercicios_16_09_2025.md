Mostrar todos os programas de todos os exercícios no seu GitHub até hoje (16/09/2025);

1) Transforme a matriz do programa ArrayBidimensional.java para 5 x 5;
2) Por que não aumentar o array diretamente?

A imutabilidade do tamanho do array é uma característica de Java. Isso porque a alocação de memória para um array é contígua e fixa. Se um array pudesse "crescer", ele exigiria mais espaço do que o inicialmente alocado, o que poderia invadir o espaço de memória de outras variáveis. A solução de copiar para um novo array garante que a nova alocação de memória seja feita de forma segura.

Alternativa: Use ArrayList

Para situações em que você precisa de uma lista de elementos com tamanho dinâmico, a melhor solução é usar a classe ArrayList. Ela é muito mais prática e eficiente para lidar com coleções que precisam aumentar ou diminuir de tamanho.

Teste o programa: AumentarArray.java

3) Arrays Irregulares (ou "Jagged Arrays"). Em Java, arrays multidimensionais não precisam ter um número fixo de colunas em todas as linhas. Você pode ter arrays onde cada "linha" (sub-array) tem um tamanho diferente. Execute o programa ArrayIrregular.java e veja o resultado;
4) Adicione 10 elementos no programa ExemploPilhaSimples1.java;
5) Empilhe 10 elementos no programa ExemploPilhaSimples2.java;
6) Coloque 5 URLs reais no programa ExemploStack.java;
7) Qual a função do método Arrays.sort(nomes) no programa OrdenaArray.java?
