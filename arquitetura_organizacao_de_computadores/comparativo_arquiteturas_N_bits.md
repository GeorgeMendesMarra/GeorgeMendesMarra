# Comparativo entre Arquiteturas de 8, 16, 32 e 64 bits

> Material didático de Arquitetura e Organização de Computadores, com exemplos em **Assembly** e **C**.

## 1. O que significa "N bits"?

O número de bits de uma arquitetura costuma se referir à **largura da palavra**: o tamanho dos registradores de uso geral e, em muitos casos, da ALU e do barramento de dados. Isso define:

- quanto dado a CPU processa por instrução;
- o tamanho dos inteiros "nativos";
- (em geral) quanta memória o processador consegue endereçar.

Atenção: largura da palavra e largura do barramento de endereços **nem sempre coincidem**. O Intel 8086 é de 16 bits, mas tem barramento de endereços de 20 bits (1 MB). O Z80 e o 6502 são de 8 bits, mas endereçam 64 KB com endereços de 16 bits.

## 2. Tabela comparativa

| Característica | 8 bits | 16 bits | 32 bits | 64 bits |
|---|---|---|---|---|
| **Registradores de uso geral** | 8 bits | 16 bits | 32 bits | 64 bits |
| **Inteiro sem sinal** | 0 a 255 | 0 a 65.535 | 0 a 4.294.967.295 | 0 a 18.446.744.073.709.551.615 |
| **Endereçamento típico** | 64 KB | 64 KB a 1 MB (segmentação) | 4 GB | 16 EB teóricos (na prática 256 TB a 4 PB) |
| **Auge** | 1974 a início dos anos 1980 | Início dos anos 1980 a início dos 1990 | Anos 1990 a 2000 | 2003 até hoje |
| **CPUs de exemplo** | Intel 8080, Z80, MOS 6502, AVR (ATmega) | Intel 8086/8088, 80286, WDC 65C816 | Intel 80386, Pentium, ARM Cortex-A8 | x86-64, ARMv8-A, RISC-V RV64 |
| **Aplicações típicas** | Micros domésticos, microcontroladores | IBM PC/XT/AT, SNES | PCs com Windows 95 a XP | PCs, servidores, smartphones, nuvem |
| **Sistemas operacionais** | CP/M, Apple DOS | MS-DOS, Windows 3.x | Windows 95 a 7 (32-bit), Linux i386 | Windows 10/11, Linux x86_64, macOS, Android |

## 3. Tamanho dos tipos em C

Em C, o tamanho de `int`, `long` e ponteiros **depende da arquitetura e do modelo de dados** do compilador. Por isso o padrão C99 criou os tipos de largura fixa em `<stdint.h>`.

| Plataforma | `char` | `short` | `int` | `long` | `long long` | ponteiro | Modelo |
|---|---|---|---|---|---|---|---|
| AVR 8 bits (avr-gcc) | 8 | 16 | 16 | 32 | 64 | 16 | - |
| x86 16 bits (modo real, *near*) | 8 | 16 | 16 | 32 | - | 16 | - |
| x86 32 bits | 8 | 16 | 32 | 32 | 64 | 32 | ILP32 |
| x86-64 Linux/macOS | 8 | 16 | 32 | **64** | 64 | 64 | LP64 |
| x86-64 Windows | 8 | 16 | 32 | **32** | 64 | 64 | LLP64 |

### Exemplo em C: descobrindo os tamanhos

```c
#include <stdio.h>
#include <stdint.h>

int main(void) {
    printf("char       : %zu bytes\n", sizeof(char));
    printf("short      : %zu bytes\n", sizeof(short));
    printf("int        : %zu bytes\n", sizeof(int));
    printf("long       : %zu bytes\n", sizeof(long));
    printf("long long  : %zu bytes\n", sizeof(long long));
    printf("ponteiro   : %zu bytes\n", sizeof(void *));
    printf("\nArquitetura (pela largura do ponteiro): %zu bits\n",
           sizeof(void *) * 8);

    /* Tipos de largura fixa: portáveis entre arquiteturas */
    printf("\nuint8_t=%zu uint16_t=%zu uint32_t=%zu uint64_t=%zu\n",
           sizeof(uint8_t), sizeof(uint16_t),
           sizeof(uint32_t), sizeof(uint64_t));
    return 0;
}
```

Compile com `gcc -m32` (32 bits, exige `gcc-multilib`) e com `gcc -m64` (64 bits) e compare as saídas.

### Detecção em tempo de compilação

```c
#include <stdint.h>

#if UINTPTR_MAX == 0xFFFFFFFFFFFFFFFFULL
    #define ARQUITETURA "64 bits"
#elif UINTPTR_MAX == 0xFFFFFFFFUL
    #define ARQUITETURA "32 bits"
#elif UINTPTR_MAX == 0xFFFFU
    #define ARQUITETURA "16 bits"
#else
    #define ARQUITETURA "desconhecida"
#endif
```

## 4. Faixa de valores e *overflow*

Cada largura de palavra tem um limite. Ao ultrapassá-lo, inteiros **sem sinal** dão a volta (aritmética módulo 2ⁿ).

```c
#include <stdio.h>
#include <stdint.h>

int main(void) {
    uint8_t  a = 255;
    uint16_t b = 65535;
    uint32_t c = 4294967295u;
    uint64_t d = 18446744073709551615ull;

    a++; b++; c++; d++;

    printf("uint8_t  : 255 + 1 = %u\n", a);               /* 0 */
    printf("uint16_t : 65535 + 1 = %u\n", b);             /* 0 */
    printf("uint32_t : 4294967295 + 1 = %u\n", c);        /* 0 */
    printf("uint64_t : 2^64-1 + 1 = %llu\n",
           (unsigned long long)d);                        /* 0 */
    return 0;
}
```

> **Cuidado:** em inteiros **com sinal**, o *overflow* é **comportamento indefinido** em C. Use tipos `unsigned` quando a aritmética modular for intencional.

## 5. Somando dois números de 32 bits em cada arquitetura

Esta é a melhor forma de ver a diferença de largura de palavra: **quantas instruções** são necessárias para somar dois valores de 32 bits?

```c
uint32_t soma32(uint32_t a, uint32_t b) {
    return a + b;
}
```

### 5.1. CPU de 8 bits (6502): 4 somas com propagação de *carry*

O 6502 só soma 8 bits por vez, no acumulador `A`. O *carry* precisa ser limpo antes (`CLC`) e é propagado automaticamente por `ADC` (*add with carry*).

```asm
; r = a + b   (valores de 32 bits em little-endian na memória)
        CLC               ; zera o carry
        LDA a+0           ; byte menos significativo
        ADC b+0
        STA r+0
        LDA a+1
        ADC b+1           ; soma + carry do byte anterior
        STA r+1
        LDA a+2
        ADC b+2
        STA r+2
        LDA a+3           ; byte mais significativo
        ADC b+3
        STA r+3
```

### 5.2. CPU de 8 bits (AVR / Arduino Uno): 4 instruções

O AVR tem 32 registradores de 8 bits, então os quatro bytes de cada operando ficam em registradores.

```asm
; r19:r18:r17:r16 = r19:r18:r17:r16 + r23:r22:r21:r20
        add  r16, r20     ; byte 0
        adc  r17, r21     ; byte 1 + carry
        adc  r18, r22     ; byte 2 + carry
        adc  r19, r23     ; byte 3 + carry
```

### 5.3. CPU de 16 bits (Intel 8086): 2 instruções

```asm
; DX:AX = DX:AX + CX:BX     (NASM, modo real)
        add  ax, bx       ; parte baixa (16 bits)
        adc  dx, cx       ; parte alta + carry
```

### 5.4. CPU de 32 bits (Intel 80386): 1 instrução

```asm
; NASM, 32 bits
        mov  eax, [a]
        add  eax, [b]     ; soma completa em uma instrução
        mov  [r], eax
```

### 5.5. CPU de 64 bits (x86-64): 1 instrução, usando metade do registrador

```asm
; NASM, 64 bits (convenção System V: a em edi, b em esi, retorno em eax)
soma32:
        lea  eax, [rdi + rsi]    ; ou: mov eax, edi / add eax, esi
        ret
```

### Resumo

| Arquitetura | Instruções para somar 32 bits |
|---|---|
| 8 bits | 4 (ADD + 3×ADC) |
| 16 bits | 2 (ADD + ADC) |
| 32 bits | 1 (ADD) |
| 64 bits | 1 (ADD), registrador subutilizado |

## 6. Somando dois números de 64 bits

Agora o inverso: o dado é de 64 bits.

```c
uint64_t soma64(uint64_t a, uint64_t b) {
    return a + b;
}
```

### Em 32 bits (x86, convenção cdecl): 2 instruções

O valor de 64 bits é dividido em dois registradores de 32 bits: parte baixa em `EAX` e parte alta em `EDX`.

```asm
; NASM, 32 bits. Argumentos na pilha: a (esp+4..esp+11), b (esp+12..esp+19)
soma64:
        mov  eax, [esp + 4]     ; a, parte baixa
        mov  edx, [esp + 8]     ; a, parte alta
        add  eax, [esp + 12]    ; + b, parte baixa
        adc  edx, [esp + 16]    ; + b, parte alta + carry
        ret                     ; resultado em EDX:EAX
```

### Em 64 bits (x86-64, System V): 1 instrução

```asm
; NASM, 64 bits. a em rdi, b em rsi, retorno em rax
soma64:
        lea  rax, [rdi + rsi]
        ret
```

**Conclusão:** a arquitetura de 64 bits processa dados de 64 bits em uma única instrução e passa os argumentos em **registradores**, enquanto a de 32 bits usa a **pilha** e duas instruções.

## 7. Registradores e compatibilidade no x86

O x86-64 foi estendido a partir do 8086, e cada geração manteve acesso às partes menores do registrador:

```
 63                              31              15       7      0
 |<------------ RAX ------------>|
                                 |<---- EAX ---->|
                                                 |<-- AX -->|
                                                 | AH | AL  |
```

| Largura | Registrador | Surgiu com |
|---|---|---|
| 8 bits | `AL`, `AH` | 8080/8086 |
| 16 bits | `AX` | 8086 |
| 32 bits | `EAX` | 80386 |
| 64 bits | `RAX` | x86-64 (AMD64) |

### Exemplo em Assembly: acessando partes do mesmo registrador

```asm
; NASM, 64 bits
        mov  rax, 0x1122334455667788
        ; RAX = 0x1122334455667788
        ; EAX = 0x55667788
        ; AX  = 0x7788
        ; AL  = 0x88
        ; AH  = 0x77

        mov  eax, 1             ; ATENÇÃO: escrever em EAX ZERA os 32 bits altos
        ; RAX = 0x0000000000000001

        mov  rax, 0x1122334455667788
        mov  ax, 1              ; escrever em AX NÃO altera o restante
        ; RAX = 0x1122334455660001
```

> **Detalhe importante:** no x86-64, escrever em um registrador de 32 bits (`EAX`) **zera** a metade alta do de 64 bits. Escrever em 16 ou 8 bits (`AX`, `AL`) **preserva** o restante.

O x86-64 também adicionou os registradores `R8` a `R15` (com as formas `R8D`, `R8W`, `R8B`), passando de 8 para 16 registradores de uso geral.

## 8. Endereçamento de memória

Com *n* bits de endereço, a CPU acessa 2ⁿ posições de memória.

| Bits de endereço | Memória endereçável |
|---|---|
| 16 | 64 KB |
| 20 (8086) | 1 MB |
| 32 | 4 GB |
| 64 | 16 EB (teórico) |

### 8.1. Segmentação no 8086 (16 bits com endereço de 20 bits)

Registradores de 16 bits não alcançam 1 MB. O 8086 combina **segmento** e **deslocamento (offset)**:

```
endereço físico = segmento × 16 + offset
```

Exemplo: `1234h:0010h` → `12340h + 0010h` = `12350h`.

```asm
; NASM, modo real 16 bits: escrevendo 'A' na memória de vídeo texto
        mov  ax, 0B800h        ; segmento da memória de vídeo colorida
        mov  es, ax
        mov  di, 0             ; offset 0 = primeira célula da tela
        mov  byte [es:di], 'A'      ; caractere
        mov  byte [es:di + 1], 07h  ; atributo (cinza sobre preto)
```

### 8.2. O limite de 4 GB em 32 bits

Um ponteiro de 32 bits vai de `0x00000000` a `0xFFFFFFFF`, ou seja, 4 GB. Esse limite motivou a migração para 64 bits quando a RAM dos PCs passou desse valor.

```c
#include <stdio.h>
#include <stdint.h>

int main(void) {
    int x = 42;
    int *p = &x;

    printf("Endereço de x : %p\n", (void *)p);
    printf("Ponteiro ocupa: %zu bytes\n", sizeof(p));

    /* Em 32 bits: endereços como 0xFFC4A1B8 (8 dígitos hex)
       Em 64 bits: endereços como 0x7FFD5E3A91CC (até 16 dígitos hex) */
    printf("Valor numérico: 0x%llx\n",
           (unsigned long long)(uintptr_t)p);
    return 0;
}
```

## 9. Consumo de memória: o custo dos ponteiros

Em 64 bits, ponteiros ocupam 8 bytes em vez de 4. Estruturas com muitos ponteiros (listas ligadas, árvores) **consomem mais memória**.

```c
#include <stdio.h>
#include <stdint.h>

struct No {
    int32_t valor;       /* 4 bytes */
    struct No *proximo;  /* 4 bytes (32 bits) ou 8 bytes (64 bits) */
};

int main(void) {
    printf("sizeof(struct No) = %zu bytes\n", sizeof(struct No));
    /* 32 bits: 8 bytes   (4 + 4)
       64 bits: 16 bytes  (4 + 4 de padding + 8) */
    return 0;
}
```

| Arquitetura | `valor` | padding | `proximo` | Total |
|---|---|---|---|---|
| 32 bits | 4 | 0 | 4 | **8 bytes** |
| 64 bits | 4 | 4 | 8 | **16 bytes** |

O tamanho **dobrou** por causa do ponteiro e do alinhamento (*padding*). Por isso existe o **x32 ABI** no Linux, que usa ponteiros de 32 bits em CPUs de 64.

## 10. Convenção de chamada: pilha × registradores

Quantos argumentos viajam por registradores muda bastante entre arquiteturas.

```c
int soma3(int a, int b, int c) {
    return a + b + c;
}
```

### x86 32 bits (cdecl): argumentos na pilha

```asm
soma3:
        mov  eax, [esp + 4]     ; a
        add  eax, [esp + 8]     ; + b
        add  eax, [esp + 12]    ; + c
        ret                     ; retorno em EAX
```

### x86-64 System V (Linux/macOS): argumentos em registradores

```asm
soma3:
        lea  eax, [rdi + rsi]   ; a em edi, b em esi
        add  eax, edx           ; c em edx
        ret
```

O System V passa os seis primeiros argumentos inteiros em `RDI, RSI, RDX, RCX, R8, R9`. Acessar registradores é mais rápido que acessar a pilha (memória).

## 11. Desempenho: mais bits significam mais velocidade?

**Nem sempre.** Ganhos reais de 64 bits:

- aritmética de inteiros grandes em uma instrução;
- mais registradores no x86-64 (16 em vez de 8);
- chamadas de função mais eficientes;
- espaço de endereçamento enorme.

Mas o desempenho real depende mais de **frequência, pipeline, cache e número de núcleos**. Em programas que usam só inteiros pequenos, a diferença pode ser mínima, e o maior uso de memória por ponteiros pode até piorar o uso de cache.

## 12. Compatibilidade

- SO de **64 bits** executa programas de **32 bits** (modo de compatibilidade, como o WoW64 no Windows).
- SO de **32 bits** **não** executa programas de 64 bits.
- **Drivers** precisam ter a mesma largura (*bitness*) do kernel.

## 13. E 128, 256 e 512 bits?

Não existem CPUs de uso geral com registradores inteiros de 128 bits ou mais, porque 64 bits já cobre o endereçamento e a aritmética comum. O que existe são **extensões vetoriais (SIMD)**, que processam **vários dados pequenos ao mesmo tempo**:

| Extensão | Largura do registrador | Cabem quantos `float` (32 bits)? |
|---|---|---|
| SSE | 128 bits | 4 |
| AVX/AVX2 | 256 bits | 8 |
| AVX-512 | 512 bits | 16 |
| ARM NEON | 128 bits | 4 |

### Exemplo em C com intrínsecos AVX: somando 8 `float` de uma vez

```c
#include <stdio.h>
#include <immintrin.h>   /* compile com: gcc -mavx exemplo.c */

int main(void) {
    float a[8] = {1, 2, 3, 4, 5, 6, 7, 8};
    float b[8] = {10, 20, 30, 40, 50, 60, 70, 80};
    float r[8];

    __m256 va = _mm256_loadu_ps(a);     /* carrega 8 floats (256 bits) */
    __m256 vb = _mm256_loadu_ps(b);
    __m256 vr = _mm256_add_ps(va, vb);  /* 8 somas em UMA instrução */
    _mm256_storeu_ps(r, vr);

    for (int i = 0; i < 8; i++)
        printf("%.0f ", r[i]);          /* 11 22 33 44 55 66 77 88 */
    printf("\n");
    return 0;
}
```

Equivalente em Assembly (a instrução gerada):

```asm
        vmovups  ymm0, [a]        ; carrega 8 floats
        vaddps   ymm0, ymm0, [b]  ; soma 8 pares de uma vez
        vmovups  [r], ymm0
```

Isso **não** é uma CPU de 256 bits. É uma CPU de 64 bits com uma unidade vetorial de 256 bits.

## 14. Como ver o Assembly gerado pelo compilador

Você pode comparar as arquiteturas com o próprio `gcc`:

```bash
# Salve a função em soma.c e gere o Assembly (sintaxe Intel)
gcc -m32 -O1 -S -masm=intel -fno-asynchronous-unwind-tables soma.c -o soma32.s
gcc -m64 -O1 -S -masm=intel -fno-asynchronous-unwind-tables soma.c -o soma64.s
```

Compare `soma32.s` e `soma64.s` para ver as diferenças de registradores e de convenção de chamada. O site [Compiler Explorer (godbolt.org)](https://godbolt.org) permite fazer isso no navegador, com vários compiladores e arquiteturas lado a lado (inclusive AVR, ARM e RISC-V).

## 15. Resumo final

| Bits | Perfil | Uso hoje |
|---|---|---|
| **8** | Simples, barato, baixo consumo | Microcontroladores básicos (AVR, 8051, PIC), eletrodomésticos |
| **16** | Intermediário, legado | Microcontroladores específicos (MSP430, PIC24) |
| **32** | Equilibrado | Sistemas embarcados e IoT (ARM Cortex-M, ESP32) |
| **64** | Alto desempenho e muita memória | PCs, servidores, smartphones, nuvem |

## 16. Exercícios propostos

1. Compile o programa da seção 3 com `-m32` e com `-m64`. Quais tipos mudaram de tamanho? Explique por quê.
2. Escreva em Assembly (6502 ou AVR) a **subtração** de dois números de 32 bits, usando `SBC`/`SBC` no lugar de `ADC`.
3. Escreva em Assembly x86 de 32 bits a soma de dois inteiros de **128 bits** usando `ADD` e três `ADC`.
4. Calcule o endereço físico de `2000h:0FFFh` no 8086.
5. Calcule `sizeof(struct)` em 32 e 64 bits para uma struct com `char`, `double` e `int *`, considerando o alinhamento.
6. Use o Compiler Explorer para comparar o código gerado para `soma64` em x86 (32 bits), x86-64 e ARM64.
7. Explique por que `mov eax, 1` em x86-64 zera os 32 bits altos de `RAX`, mas `mov ax, 1` não zera.

## 17. Referências

- Stallings, W. *Arquitetura e Organização de Computadores*. Pearson.
- Tanenbaum, A. S.; Austin, T. *Organização Estruturada de Computadores*. Pearson.
- Patterson, D. A.; Hennessy, J. L. *Organização e Projeto de Computadores*. Elsevier.
- Intel. *Intel 64 and IA-32 Architectures Software Developer's Manual*.
- System V ABI, *AMD64 Architecture Processor Supplement*.
