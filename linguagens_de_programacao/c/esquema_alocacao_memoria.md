+-------------------------------------------------------+  Endereço Alto
  |                                                       |  (0xFFFFFFFF...)
  |                  ARGUMENTOS E ENVS                    |
  |  (Parâmetros da linha de comando / Variáveis de amb.) |
  +-------------------------------------------------------+
  |                                                       |
  |                      STACK                            |
  |                        |                              |
  |                        v  (Cresce para BAIXO)         |
  |                                                       |
  |  - Variáveis locais                                   |
  |  - Parâmetros de funções                              |
  |  - Endereços de retorno das chamadas                  |
  |                                                       |
  + - - - - - - - - - - - - - - - - - - - - - - - - - - - +
  |                                                       |
  |                   ESPAÇO LIVRE                        |
  |            (Memória disponível do SO)                 |
  |                                                       |
  + - - - - - - - - - - - - - - - - - - - - - - - - - - - +
  |                                                       |
  |                        ^  (Cresce para CIMA)          |
  |                        |                              |
  |                       HEAP                            |
  |                                                       |
  |  - Alocação dinâmica: malloc(), calloc(), realloc()   |
  |  - Gerenciado manualmente com free()                  |
  |                                                       |
  +-------------------------------------------------------+
  |                                                       |
  |                        BSS                            |
  |  (Variáveis globais e estáticas NÃO inicializadas)    |
  |                                                       |
  +-------------------------------------------------------+
  |                                                       |
  |                       DATA                            |
  |  (Variáveis globais e estáticas INICIALIZADAS)        |
  |                                                       |
  +-------------------------------------------------------+
  |                                                       |
  |                       TEXT                            |
  |  (Código executável do programa / Instruções C)       |
  |                                                       |
  +-------------------------------------------------------+  Endereço Baixo
                                                             (0x00000000...)
