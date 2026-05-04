## Sistemas de Numeração para Arquitetura de Computadores

### Sumário
1. [Conceitos Fundamentais](#1-conceitos-fundamentais)
2. [Sistema Decimal (Base 10)](#2-sistema-decimal-base-10)
3. [Sistema Binário (Base 2)](#3-sistema-binário-base-2)
4. [Sistema Octal (Base 8)](#4-sistema-octal-base-8)
5. [Sistema Hexadecimal (Base 16)](#5-sistema-hexadecimal-base-16)
6. [Conversões entre Sistemas](#6-conversões-entre-sistemas)
7. [Representação de Números Negativos](#7-representação-de-números-negativos)
8. [Aritmética Binária](#8-aritmética-binária)
9. [Ponto Fixo e Ponto Flutuante](#9-ponto-fixo-e-ponto-flutuante)
10. [Códigos Especiais (BCD, ASCII, Gray)](#10-códigos-especiais-bcd-ascii-gray)
11. [Tabela de Potências](#11-tabela-de-potências)
12. [Exercícios Resolvidos](#12-exercícios-resolvidos)
13. [Referência Rápida](#13-referência-rápida)

---

## 1. Conceitos Fundamentais

### 1.1 O que é um Sistema de Numeração?

Um sistema de numeração é um conjunto de símbolos (dígitos) e regras para representar números.

| Característica | Descrição |
|----------------|-----------|
| **Base (b)** | Número de símbolos distintos no sistema |
| **Dígitos** | Símbolos que variam de 0 a (b-1) |
| **Notação posicional** | O valor do dígito depende de sua posição |

### 1.2 Teorema Fundamental da Numeração Posicional

Qualquer número pode ser representado como:

\[
N = \sum_{i=0}^{n-1} d_i \times b^i
\]

Onde:
- \(N\) = valor do número
- \(b\) = base do sistema
- \(d_i\) = dígito na posição \(i\)
- \(n\) = número de dígitos

### 1.3 Importância para Arquitetura de Computadores

| Conceito | Relação com Sistemas de Numeração |
|----------|-----------------------------------|
**Memória** | Endereçamento hexadecimal |
**Processador** | Operações aritméticas em binário |
**Cache** | Tags e índices em binário/hexa |
**Instruções** | Códigos de operação em binário |
**Flags** | Bits individuais de status |
**Máscaras** | Operações bit a bit (AND, OR, XOR) |

---

## 2. Sistema Decimal (Base 10)

### 2.1 Características

| Propriedade | Valor |
|-------------|-------|
| **Base** | 10 |
| **Dígitos** | 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 |
| **Uso** | Cotidiano, entrada/saída de dados |

### 2.2 Estrutura Posicional

```
Exemplo: 3.724 = 3×10³ + 7×10² + 2×10¹ + 4×10⁰
                = 3×1000 + 7×100 + 2×10 + 4×1
                = 3000 + 700 + 20 + 4
```

### 2.3 Partes Inteira e Fracionária

```
Exemplo com parte fracionária: 125,375 = 125 + 0,375

Parte inteira:     1×10² + 2×10¹ + 5×10⁰
Parte fracionária: 3×10⁻¹ + 7×10⁻² + 5×10⁻³

Logo: 1×100 + 2×10 + 5×1 + 3×0,1 + 7×0,01 + 5×0,001 = 125,375
```

---

## 3. Sistema Binário (Base 2)

### 3.1 Características

| Propriedade | Valor |
|-------------|-------|
| **Base** | 2 |
| **Dígitos** | 0, 1 |
| **Uso** | Hardware digital (níveis lógicos) |
| **Bit** | Binary digit (0 ou 1) |

### 3.2 Tabela de Pesos Binários (Parte Inteira)

| Posição | 2⁷ | 2⁶ | 2⁵ | 2⁴ | 2³ | 2² | 2¹ | 2⁰ |
|---------|----|----|----|----|----|----|----|----|
| **Valor** | 128 | 64 | 32 | 16 | 8 | 4 | 2 | 1 |

### 3.3 Tabela de Pesos Binários (Parte Fracionária)

| Posição | 2⁻¹ | 2⁻² | 2⁻³ | 2⁻⁴ | 2⁻⁵ | 2⁻⁶ | 2⁻⁷ | 2⁻⁸ |
|---------|-----|-----|-----|-----|-----|-----|-----|-----|
| **Valor** | 0,5 | 0,25 | 0,125 | 0,0625 | 0,03125 | 0,015625 | 0,0078125 | 0,00390625 |

### 3.4 Exemplos de Números Binários

```
1011₂ = 1×2³ + 0×2² + 1×2¹ + 1×2⁰
      = 8 + 0 + 2 + 1 = 11₁₀

1101,011₂ = 1×2³ + 1×2² + 0×2¹ + 1×2⁰ + 0×2⁻¹ + 1×2⁻² + 1×2⁻³
         = 8 + 4 + 0 + 1 + 0 + 0,25 + 0,125 = 13,375₁₀
```

---

## 4. Sistema Octal (Base 8)

### 4.1 Características

| Propriedade | Valor |
|-------------|-------|
| **Base** | 8 |
| **Dígitos** | 0, 1, 2, 3, 4, 5, 6, 7 |
| **Uso** | Representação compacta do binário (3 bits por dígito) |
| **Aplicação** | Permissões de arquivos UNIX/Linux |

### 4.2 Relação com Binário (3 bits → 1 octal)

| Octal | Binário | Octal | Binário |
|-------|---------|-------|---------|
| 0 | 000 | 4 | 100 |
| 1 | 001 | 5 | 101 |
| 2 | 010 | 6 | 110 |
| 3 | 011 | 7 | 111 |

### 4.3 Exemplos

```
357₈ = 3×8² + 5×8¹ + 7×8⁰
     = 3×64 + 5×8 + 7×1
     = 192 + 40 + 7 = 239₁₀

Cada dígito octal mapeia para 3 bits:
357₈ = 011 101 111₂
```

---

## 5. Sistema Hexadecimal (Base 16)

### 5.1 Características

| Propriedade | Valor |
|-------------|-------|
| **Base** | 16 |
| **Dígitos** | 0-9, A, B, C, D, E, F |
| **Uso** | Endereçamento de memória, cores RGB, debug |
| **Aplicação** | Representação compacta do binário (4 bits por dígito) |

### 5.2 Tabela Hexadecimal

| Hexa | Decimal | Binário | Hexa | Decimal | Binário |
|------|---------|---------|------|---------|---------|
| 0 | 0 | 0000 | 8 | 8 | 1000 |
| 1 | 1 | 0001 | 9 | 9 | 1001 |
| 2 | 2 | 0010 | A | 10 | 1010 |
| 3 | 3 | 0011 | B | 11 | 1011 |
| 4 | 4 | 0100 | C | 12 | 1100 |
| 5 | 5 | 0101 | D | 13 | 1101 |
| 6 | 6 | 0110 | E | 14 | 1110 |
| 7 | 7 | 0111 | F | 15 | 1111 |

### 5.3 Exemplos

```
2F3₁₆ = 2×16² + F×16¹ + 3×16⁰
      = 2×256 + 15×16 + 3×1
      = 512 + 240 + 3 = 755₁₀

Cada dígito hexadecimal mapeia para 4 bits:
2F3₁₆ = 0010 1111 0011₂
```

---

## 6. Conversões entre Sistemas

### 6.1 Resumo das Conversões

```
┌─────────────────────────────────────────────────────────────────┐
│                    CONVERSÕES ENTRE BASES                        │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│   Decimal ◄─────────────────────────────────────────────► Octal │
│      │                                                   ▲      │
│      │ (divisões                                          │      │
│      │  sucessivas)                                        │      │
│      ▼                                                   │      │
│   Binário ◄────────────────────────────────────────────► Hexadecimal
│         (agrupamento de bits 3 ou 4)                              │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

### 6.2 Decimal → Binário (Método das Divisões Sucessivas)

```
Converter 25₁₀ para binário:

25 ÷ 2 = 12, resto 1  (LSB - Least Significant Bit)
12 ÷ 2 = 6,  resto 0
6  ÷ 2 = 3,  resto 0
3  ÷ 2 = 1,  resto 1
1  ÷ 2 = 0,  resto 1  (MSB - Most Significant Bit)

Lendo os restos de baixo para cima: 11001₂

Verificação: 16 + 8 + 0 + 0 + 1 = 25 ✓
```

### 6.3 Decimal → Binário (Parte Fracionária)

```
Converter 0,625₁₀ para binário:

0,625 × 2 = 1,250 → dígito 1 (parte inteira)
0,250 × 2 = 0,500 → dígito 0
0,500 × 2 = 1,000 → dígito 1 (parte inteira = 0, para)

Resultado: 0,101₂

Verificação: 1×2⁻¹ + 0×2⁻² + 1×2⁻³ = 0,5 + 0 + 0,125 = 0,625 ✓
```

### 6.4 Decimal → Hexadecimal (Divisões sucessivas por 16)

```
Converter 479₁₀ para hexadecimal:

479 ÷ 16 = 29, resto 15 (F)
29  ÷ 16 = 1,  resto 13 (D)
1   ÷ 16 = 0,  resto 1

Resultado: 1DF₁₆

Verificação: 1×256 + 13×16 + 15 = 256 + 208 + 15 = 479 ✓
```

### 6.5 Decimal → Octal (Divisões sucessivas por 8)

```
Converter 479₁₀ para octal:

479 ÷ 8 = 59, resto 7
59  ÷ 8 = 7,  resto 3
7   ÷ 8 = 0,  resto 7

Resultado: 737₈

Verificação: 7×64 + 3×8 + 7 = 448 + 24 + 7 = 479 ✓
```

### 6.6 Binário ↔ Hexadecimal (Agrupamento de 4 bits)

```
Binário para Hexadecimal (da direita para esquerda):
1011110011010011₂ = ?

Agrupar de 4 em 4 bits (da direita para esquerda):
1011 1100 1101 0011
  B    C    D    3

Resultado: BCD3₁₆

Hexadecimal para Binário (cada dígito → 4 bits):
A3F₁₆ = ?

A (10) = 1010
3      = 0011
F (15) = 1111

Resultado: 1010 0011 1111₂ = 101000111111₂
```

### 6.7 Binário ↔ Octal (Agrupamento de 3 bits)

```
Binário para Octal (da direita para esquerda):
1011110011010011₂ = ?

Agrupar de 3 em 3 bits (da direita para esquerda):
001 011 110 011 010 011
 1   3   6   3   2   3

Resultado: 136323₈

Octal para Binário (cada dígito → 3 bits):
745₈ = ?

7 = 111
4 = 100
5 = 101

Resultado: 111100101₂
```

### 6.8 Tabela de Conversão Rápida (0-15)

| Decimal | Binário | Octal | Hexadecimal |
|---------|---------|-------|-------------|
| 0 | 0000 | 0 | 0 |
| 1 | 0001 | 1 | 1 |
| 2 | 0010 | 2 | 2 |
| 3 | 0011 | 3 | 3 |
| 4 | 0100 | 4 | 4 |
| 5 | 0101 | 5 | 5 |
| 6 | 0110 | 6 | 6 |
| 7 | 0111 | 7 | 7 |
| 8 | 1000 | 10 | 8 |
| 9 | 1001 | 11 | 9 |
| 10 | 1010 | 12 | A |
| 11 | 1011 | 13 | B |
| 12 | 1100 | 14 | C |
| 13 | 1101 | 15 | D |
| 14 | 1110 | 16 | E |
| 15 | 1111 | 17 | F |

---

## 7. Representação de Números Negativos

### 7.1 Métodos de Representação

| Método | Descrição | Faixa (n bits) | Vantagem | Desvantagem |
|--------|-----------|----------------|----------|-------------|
| **Sinal-Magnitude** | Bit MSB = sinal (0+,1-); restos = magnitude | -(2ⁿ⁻¹-1) a +(2ⁿ⁻¹-1) | Simples | Dois zeros (+0 e -0) |
| **Complemento de 1** | Inverte todos os bits | -(2ⁿ⁻¹-1) a +(2ⁿ⁻¹-1) | Fácil de obter | Dois zeros |
| **Complemento de 2** | Inverte bits e soma 1 | -2ⁿ⁻¹ a +(2ⁿ⁻¹-1) | Um zero, aritmética simples | Assimétrico |

### 7.2 Complemento de 2 (Padrão em Computadores)

**Algoritmo para obter -N (com n bits):**
1. Represente N em binário com n bits
2. Inverta todos os bits (0→1, 1→0) → complemento de 1
3. Some 1 ao resultado

**Exemplo: -13 em 8 bits (complemento de 2)**

| Passo | Operação | Resultado (8 bits) |
|-------|----------|-------------------|
| 1 | +13 em binário | 00001101 |
| 2 | Inverter bits (C1) | 11110010 |
| 3 | Somar 1 | 11110011 |

**Verificação:** 11110011₂ = -13₁₀

### 7.3 Tabela de Valores (4 bits - Complemento de 2)

| Binário | Decimal | Binário | Decimal |
|---------|---------|---------|---------|
| 0111 | +7 | 1111 | -1 |
| 0110 | +6 | 1110 | -2 |
| 0101 | +5 | 1101 | -3 |
| 0100 | +4 | 1100 | -4 |
| 0011 | +3 | 1011 | -5 |
| 0010 | +2 | 1010 | -6 |
| 0001 | +1 | 1001 | -7 |
| 0000 | +0 | 1000 | -8 |

### 7.4 Detecção de Overflow

**Overflow ocorre quando:**
- Soma de dois números positivos resulta negativo
- Soma de dois números negativos resulta positivo

| Condição | Overflow |
|----------|----------|
| Positivo + Positivo = Negativo | Sim |
| Negativo + Negativo = Positivo | Sim |
| Positivo + Negativo | Não |

---

## 8. Aritmética Binária

### 8.1 Adição Binária

| A | B | Soma | Carry Out (Vai 1) |
|---|---|---|-------------------|
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |

**Exemplo:** 1011₂ (11) + 1101₂ (13) = 11000₂ (24)

```
   1 0 1 1
 + 1 1 0 1
 ---------
 1 1 0 0 0
 ↑
 Carry final
```

### 8.2 Subtração Binária (Complemento de 2)

```
Subtrair 13 de 25 (em 8 bits):

25₁₀ = 00011001₂
13₁₀ = 00001101₂

Complemento de 2 de 13:
00001101 → 11110010 + 1 = 11110011

Somar:
  00011001
+ 11110011
-----------
1 00001100
↑
Carry descartado

Resultado: 00001100₂ = 12₁₀ ✓
```

### 8.3 Multiplicação Binária (Shift and Add)

```
Multiplicar 1101₂ (13) × 1011₂ (11):

       1 1 0 1  (13)
     × 1 0 1 1  (11)
     ---------
       1 1 0 1  (13 × 1)
     1 1 0 1    (13 × 1, shift 1)
   0 0 0 0      (13 × 0, shift 2)
+ 1 1 0 1      (13 × 1, shift 3)
-------------
  1 0 0 0 1 1 1 1

10001111₂ = 128 + 8 + 4 + 2 + 1 = 143₁₀ ✓
```

### 8.4 Divisão Binária (Shift and Subtract)

```
Dividir 1011₂ (11) por 0011₂ (3):

        0 0 1 1    (quociente)
      ┌─────────
0011 │ 1 0 1 1
        1 1 0 0   (subtrair? não, vai 0 no quociente)
      ─────────
        1 0 1 1
          1 1 0 0  (subtrair? não, vai 0)
        ─────────
          1 0 1 1
            1 1 0 0 (subtrair? sim, vai 1)
          ─────────
              1 1 1 0 (resto?)

Melhor exemplo: 100100₂ (36) ÷ 110₂ (6) = 110₂ (6)

       0 0 1 1 0
     ┌───────────
110 │ 1 0 0 1 0 0
       1 1 0      (110 × 1 = 110)  
     ─────────
         1 0 1 0
         1 1 0    (110 × 1 = 110)
       ─────────
           1 0 0 0
           1 1 0  (110 × 1 = 110)
         ─────────
             1 0 0 (resto)
```

---

## 9. Ponto Fixo e Ponto Flutuante

### 9.1 Representação em Ponto Fixo (Qm.n)

**Formato Qm.n** (m bits para inteiro, n bits para fração)

| Formato | Bits | Faixa (aproximada) | Precisão |
|---------|------|-------------------|----------|
| Q7.8 | 16 | -128 a +127,996 | 1/256 = 0,0039 |
| Q15.16 | 32 | -32768 a +32767,99998 | 1/65536 = 1,5×10⁻⁵ |

**Exemplo: Q4.4 (8 bits: 4 inteiro + 4 fração)**

```
0011,1010₂ = ?

Parte inteira: 0011₂ = 3
Parte fracionária: 1010₂ = 1×2⁻¹ + 0×2⁻² + 1×2⁻³ + 0×2⁻⁴
                     = 0,5 + 0 + 0,125 + 0 = 0,625

Resultado: 3,625₁₀
```

### 9.2 Padrão IEEE 754 (Ponto Flutuante)

#### Single Precision (32 bits)

```
┌──────┬─────────┬─────────────────────┐
│ 1 bit│  8 bits │      23 bits        │
├──────┼─────────┼─────────────────────┤
│ Sinal│ Expoente│      Mantissa       │
└──────┴─────────┴─────────────────────┘
```

**Fórmula:** \((-1)^S \times 2^{(E-127)} \times (1 + M)\)

| Componente | Bits | Faixa |
|------------|------|-------|
| Sinal (S) | 1 | 0 = positivo, 1 = negativo |
| Expoente (E) | 8 | 0 a 255 (bias = 127) |
| Mantissa (M) | 23 | Precisão de ~7 dígitos decimais |

#### Double Precision (64 bits)

```
┌──────┬─────────┬─────────────────────┐
│ 1 bit│ 11 bits │       52 bits       │
├──────┼─────────┼─────────────────────┤
│ Sinal│ Expoente│      Mantissa       │
└──────┴─────────┴─────────────────────┘
```

**Fórmula:** \((-1)^S \times 2^{(E-1023)} \times (1 + M)\)

| Componente | Bits | Faixa |
|------------|------|-------|
| Sinal (S) | 1 | 0 = positivo, 1 = negativo |
| Expoente (E) | 11 | 0 a 2047 (bias = 1023) |
| Mantissa (M) | 52 | Precisão de ~15 dígitos decimais |

---

## 10. Códigos Especiais (BCD, ASCII, Gray)

### 10.1 BCD (Binary Coded Decimal)

**Cada dígito decimal é representado por 4 bits**

| Decimal | BCD (8421) | Decimal | BCD (8421) |
|---------|------------|---------|------------|
| 0 | 0000 | 5 | 0101 |
| 1 | 0001 | 6 | 0110 |
| 2 | 0010 | 7 | 0111 |
| 3 | 0011 | 8 | 1000 |
| 4 | 0100 | 9 | 1001 |

**Exemplo:** 479₁₀ em BCD = 0100 0111 1001 (BCD)

### 10.2 Código Gray (Código Reflexivo)

**Característica:** Apenas 1 bit muda entre números consecutivos

| Decimal | Binário | Gray | Decimal | Binário | Gray |
|---------|---------|------|---------|---------|------|
| 0 | 0000 | 0000 | 8 | 1000 | 1100 |
| 1 | 0001 | 0001 | 9 | 1001 | 1101 |
| 2 | 0010 | 0011 | 10 | 1010 | 1111 |
| 3 | 0011 | 0010 | 11 | 1011 | 1110 |
| 4 | 0100 | 0110 | 12 | 1100 | 1010 |
| 5 | 0101 | 0111 | 13 | 1101 | 1011 |
| 6 | 0110 | 0101 | 14 | 1110 | 1001 |
| 7 | 0111 | 0100 | 15 | 1111 | 1000 |

### 10.3 Conversão Binário → Gray

```
Gray = Binário ⊕ (Binário >> 1)

Exemplo: 13₁₀ = 1101₂

Gray = 1101 ⊕ 0110 = 1011

Resultado: 1011 (Gray)
```

### 10.4 Conversão Gray → Binário

```
bit[0] = gray[0]
bit[i] = gray[i] ⊕ bit[i-1]  (para i > 0)

Exemplo: 1011 (Gray)

bit[3] = gray[3] = 1
bit[2] = gray[2] ⊕ bit[3] = 0 ⊕ 1 = 1
bit[1] = gray[1] ⊕ bit[2] = 1 ⊕ 1 = 0
bit[0] = gray[0] ⊕ bit[1] = 1 ⊕ 0 = 1

Resultado: 1101₂ = 13₁₀ ✓
```

### 10.5 Tabela ASCII (7 bits)

| Decimal | Hexa | Binário | Caractere |
|---------|------|---------|-----------|
| 65 | 41 | 1000001 | A |
| 66 | 42 | 1000010 | B |
| 97 | 61 | 1100001 | a |
| 48 | 30 | 0110000 | 0 |
| 32 | 20 | 0100000 | (espaço) |
| 13 | 0D | 0001101 | CR (Enter) |
| 10 | 0A | 0001010 | LF |

---

## 11. Tabela de Potências

### 11.1 Potências de 2 (Até 2¹⁶)

| 2ⁿ | Valor | 2ⁿ | Valor |
|----|-------|----|-------|
| 2⁰ | 1 | 2⁹ | 512 |
| 2¹ | 2 | 2¹⁰ | 1.024 |
| 2² | 4 | 2¹¹ | 2.048 |
| 2³ | 8 | 2¹² | 4.096 |
| 2⁴ | 16 | 2¹³ | 8.192 |
| 2⁵ | 32 | 2¹⁴ | 16.384 |
| 2⁶ | 64 | 2¹⁵ | 32.768 |
| 2⁷ | 128 | 2¹⁶ | 65.536 |
| 2⁸ | 256 | - | - |

### 11.2 Potências de 16 (Até 16⁴)

| 16ⁿ | Valor | Uso |
|-----|-------|-----|
| 16⁰ | 1 | - |
| 16¹ | 16 | Nibble |
| 16² | 256 | Byte (0-255) |
| 16³ | 4.096 | Página de memória (4KB) |
| 16⁴ | 65.536 | Segmento de memória (64KB) |

### 11.3 Prefixos Binários (IEC) vs Decimais (SI)

| Nome | Símbolo | Valor (potência) | Valor decimal | Aprox. |
|------|---------|-----------------|---------------|--------|
| **Kibi** | Ki | 2¹⁰ | 1.024 | ≈ 1.000 |
| **Mebi** | Mi | 2²⁰ | 1.048.576 | ≈ 1.000.000 |
| **Gibi** | Gi | 2³⁰ | 1.073.741.824 | ≈ 1.000.000.000 |
| **Tebi** | Ti | 2⁴⁰ | 1.099.511.627.776 | ≈ 1.000.000.000.000 |

---

## 12. Exercícios Resolvidos

### Exercício 1: Converter 158₁₀ para binário, octal e hexadecimal

**Resolução:**

**Binário (divisões sucessivas por 2):**

| Divisão | Quociente | Resto |
|---------|-----------|-------|
| 158 ÷ 2 | 79 | 0 (LSB) |
| 79 ÷ 2 | 39 | 1 |
| 39 ÷ 2 | 19 | 1 |
| 19 ÷ 2 | 9 | 1 |
| 9 ÷ 2 | 4 | 1 |
| 4 ÷ 2 | 2 | 0 |
| 2 ÷ 2 | 1 | 0 |
| 1 ÷ 2 | 0 | 1 (MSB) |

**Binário:** 10011110₂

**Octal (agrupar binário de 3 em 3):**
10011110₂ = 010 011 110₂ = 236₈

**Hexadecimal (agrupar binário de 4 em 4):**
10011110₂ = 1001 1110₂ = 9E₁₆

**Resposta:** 10011110₂, 236₈, 9E₁₆

---

### Exercício 2: Converter 3F2A₁₆ para binário e decimal

**Resolução:**

**Binário (cada dígito hexa → 4 bits):**
3 (0011), F (1111), 2 (0010), A (1010)

Binário: 0011 1111 0010 1010₂ = 11111100101010₂

**Decimal (multiplicar potências de 16):**
3F2A₁₆ = 3×16³ + F×16² + 2×16¹ + A×16⁰
       = 3×4096 + 15×256 + 2×16 + 10×1
       = 12.288 + 3.840 + 32 + 10 = 16.170₁₀

**Resposta:** 11111100101010₂, 16.170₁₀

---

### Exercício 3: Calcular -53 em complemento de 2 com 8 bits

**Resolução:**

| Passo | Operação | Resultado (8 bits) |
|-------|----------|-------------------|
| 1 | +53 em binário | 00110101 |
| 2 | Inverter bits (C1) | 11001010 |
| 3 | Somar 1 | 11001011 |

**Verificação:** 11001011₂ = -53₁₀

**Resposta:** 11001011₂

---

### Exercício 4: Somar 10110101₂ e 01101110₂ (verificar overflow em 8 bits)

**Resolução:**

```
  1 0 1 1 0 1 0 1  (181₁₀)
+ 0 1 1 0 1 1 1 0  (110₁₀)
-----------------
1 0 0 1 0 0 0 1 1  (291₁₀, mas 8 bits limitam a 255)

Considerando apenas 8 bits: 00100011₂ = 35₁₀
```

**Análise de overflow:**
- Positivo (181) + Positivo (110) = Negativo (35) →
- **OVERFLOW DETECTADO!** (Resultado real 291 > 255, ultrapassou a faixa de 8 bits)

**Resposta:** 00100011₂ com overflow

---

### Exercício 5: Representar 13,375₁₀ em ponto flutuante IEEE 754 Single Precision

**Resolução:**

**Passo 1:** Converter 13,375₁₀ para binário

```
13₁₀ = 1101₂
0,375 × 2 = 0,75 → 0
0,75 × 2 = 1,5 → 1
0,5 × 2 = 1,0 → 1

0,375₁₀ = 0,011₂

13,375₁₀ = 1101,011₂
```

**Passo 2:** Normalizar (1,xxxx × 2ⁿ)

```
1101,011₂ = 1,101011₂ × 2³
```

**Passo 3:** Calcular expoente (E = n + 127)

```
Expoente = 3 + 127 = 130₁₀ = 10000010₂
```

**Passo 4:** Montar o formato (1 bit sinal, 8 bits expoente, 23 bits mantissa)

```
Sinal = 0 (positivo)
Expoente = 10000010₂
Mantissa = 10101100000000000000000₂ (bits após o 1.)

Resultado: 0 10000010 10101100000000000000000₂
```

**Resposta:** 0x41560000 (hexadecimal)

---

## 13. Referência Rápida

### 13.1 Conversões Rápidas

| Conversão | Método |
|-----------|--------|
| Decimal → Binário | Divisões sucessivas por 2 |
| Decimal → Hexadecimal | Divisões sucessivas por 16 |
| Binário → Hexadecimal | Agrupar de 4 em 4 bits (da direita) |
| Binário → Octal | Agrupar de 3 em 3 bits (da direita) |
| Binário → Decimal | Soma das potências de 2 com bit 1 |
| Negativo (C2) | Inverter bits + somar 1 |

### 13.2 Constantes Importantes

| Constante | Valor |
|-----------|-------|
| 1 KB (Kibibyte) | 2¹⁰ = 1.024 bytes |
| 1 MB (Mebibyte) | 2²⁰ = 1.048.576 bytes |
| 1 GB (Gibibyte) | 2³⁰ = 1.073.741.824 bytes |
| 1 TB (Tebibyte) | 2⁴⁰ = 1.099.511.627.776 bytes |
| Bias (IEEE 754 single) | 127 |
| Bias (IEEE 754 double) | 1.023 |
| Máximo 8 bits sem sinal | 255 |
| Máximo 16 bits sem sinal | 65.535 |
| Máximo 32 bits sem sinal | 4.294.967.295 |

### 13.3 Tabela de Código de Cores HTML (Hexadecimal)

| Cor | Hexadecimal |
|-----|-------------|
| Preto | #000000 |
| Branco | #FFFFFF |
| Vermelho | #FF0000 |
| Verde | #00FF00 |
| Azul | #0000FF |
| Amarelo | #FFFF00 |
| Ciano | #00FFFF |
| Magenta | #FF00FF |

---
