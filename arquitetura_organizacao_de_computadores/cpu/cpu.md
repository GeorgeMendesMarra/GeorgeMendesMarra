# CPU (Central Processing Unit): Guia Técnico Completo

## Sumário
1. [Fundamentos de Arquitetura](#1-fundamentos-de-arquitetura)
2. [Tecnologias de Execução Avançada](#2-tecnologias-de-execução-avançada)
3. [Intel: Catálogo Completo](#3-intel-catálogo-completo)
4. [AMD: Catálogo Completo](#4-amd-catálogo-completo)
5. [ARM: Catálogo Completo](#5-arm-catálogo-completo)
6. [Comparativos Técnicos](#6-comparativos-técnicos)
7. [Roadmap e Tecnologias Emergentes](#7-roadmap-e-tecnologias-emergentes-2025-2027)
8. [Métricas e Benchmarks](#8-métricas-de-performance-e-benchmarks)
9. [Guia de Seleção Técnica](#9-guia-de-seleção-técnica)

---

## 1. Fundamentos de Arquitetura

### 1.1 O Que é uma CPU?

A CPU (Central Processing Unit) é o componente central que executa instruções de programas, realizando operações aritméticas, lógicas, de controle e entrada/saída.

### 1.2 Microarquitetura vs. Conjunto de Instruções (ISA)

| Conceito | Definição | Exemplos |
|----------|-----------|----------|
| **ISA (Instruction Set Architecture)** | Interface entre software e hardware, define instruções que o processador entende | x86-64, ARMv8-A, RISC-V |
| **Microarquitetura** | Implementação física da ISA (como as instruções são executadas) | Zen 5, Golden Cove, Cortex-X4 |

### 1.3 Pipeline de Instrução (Estágios Clássicos)

```
┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐
│  Fetch  │───►│ Decode  │───►│  Exec   │───►│ Memory  │───►│Writeback│
│ (Busca) │    │(Decod.) │    │(Executa)│    │(Memória)│    │(Escrita)│
└─────────┘    └─────────┘    └─────────┘    └─────────┘    └─────────┘
```

**Métricas de Pipeline:**
- **IPC (Instructions Per Cycle):** Quantas instruções completadas por ciclo de clock
- **Hazards:** Estrutural (recurso disputado), Dados (dependência), Controle (branch)
- **Técnicas de mitigação:** Forwarding, Branch Prediction, Out-of-Order Execution

### 1.4 Hierarquia de Cache e Latências (ciclos de clock)

| Nível | Tamanho típico | Latência (ciclos) | Associatividade | Política |
|-------|---------------|-------------------|-----------------|----------|
| **L1 (Instrução)** | 32-64 KB | 3-5 | 8-way | Write-through |
| **L1 (Dados)** | 32-64 KB | 4-6 | 8-way | Write-back |
| **L2 (Unificado)** | 256KB-2MB | 10-15 | 16-way | Write-back |
| **L3 (Compartilhado)** | 8-512 MB | 30-50 | 16-20-way | Inclusive/Exclusive |

**Princípio de Localidade:**
- **Temporal:** Dados acessados agora serão acessados novamente em breve
- **Espacial:** Dados próximos ao acessado serão acessados em breve

---

## 2. Tecnologias de Execução Avançada

### 2.1 Superscalar vs. VLIW

| Característica | Superscalar | VLIW (Very Long Instruction Word) |
|----------------|-------------|-----------------------------------|
| **Decodificação** | Hardware dinâmico | Estática (compilador) |
| **Exemplo** | x86, ARM | IA-64 (Intel Itanium), DSPs |
| **Vantagem** | Compatibilidade binária | Eficiência energética |
| **Desvantagem** | Circuito complexo | Compilador complexo |
| **Largura típica** | 4-6 instruções/ciclo | 8-12 instruções/ciclo |

### 2.2 Simultaneous Multithreading (SMT)

**Implementações por fabricante:**

| Tecnologia | Fabricante | Threads por core | Overhead de área |
|------------|------------|------------------|------------------|
| Hyper-Threading | Intel | 2 | ~5% |
| SMT | AMD (Zen) | 2 | ~5% |
| SMT4 | IBM POWER10 | 4 | ~8% |
| SMT8 | Oracle SPARC | 8 | ~12% |

**Ganho de performance típico:** 15-30% para workloads mistos, negativo para códigos single-thread otimizados.

### 2.3 Branch Prediction: Técnicas Evolutivas

| Geração | Técnica | Taxa de acerto | Complexidade |
|---------|---------|----------------|--------------|
| 1ª (486) | Static (always taken) | 60-70% | Baixa |
| 2ª (Pentium) | Bimodal (2-bit saturating) | 80-85% | Média |
| 3ª (P6) | Gshare (global + local) | 90-93% | Média-alta |
| 4ª (Core) | TAGE (Tagged Geometric) | 94-96% | Alta |
| 5ª (Moderno) | Perceptron (ML-based) | 96-98% | Muito alta |

---

## 3. Intel: Catálogo Completo

### 3.1 Linha de Produtos Desktop (por Soquete)

#### Soquete LGA 1851 (Intel 800 Series - Arrow Lake/Panther Lake)

| Modelo | Núcleos (P+E) | Threads | Freq. Base | Freq. Boost | L3 Cache | TDP (W) | Lançamento |
|--------|---------------|---------|------------|-------------|----------|---------|------------|
| **Core Ultra 9 285K** | 8+16 | 24 | 3.7 GHz | 5.7 GHz | 36 MB | 125W | Q4 2024 |
| **Core Ultra 9 275K** | 8+16 | 24 | 3.6 GHz | 5.6 GHz | 36 MB | 125W | Q4 2024 |
| **Core Ultra 7 265K** | 8+12 | 20 | 3.5 GHz | 5.5 GHz | 30 MB | 125W | Q4 2024 |
| **Core Ultra 7 255K** | 8+12 | 20 | 3.4 GHz | 5.4 GHz | 30 MB | 125W | Q4 2024 |
| **Core Ultra 5 245K** | 6+8 | 14 | 3.4 GHz | 5.3 GHz | 24 MB | 100W | Q4 2024 |
| **Core Ultra 5 235K** | 6+8 | 14 | 3.3 GHz | 5.2 GHz | 24 MB | 100W | Q4 2024 |

#### Soquete LGA 1700 (Intel 600/700 Series - Raptor Lake)

| Modelo | Núcleos (P+E) | Threads | Freq. Base | Freq. Boost | L3 Cache | TDP (W) |
|--------|---------------|---------|------------|-------------|----------|---------|
| **Core i9-14900K** | 8+16 | 32 | 3.2 GHz | 6.0 GHz | 36 MB | 125W |
| **Core i9-14900KF** | 8+16 | 32 | 3.2 GHz | 6.0 GHz | 36 MB | 125W |
| **Core i9-14900** | 8+16 | 32 | 2.0 GHz | 5.8 GHz | 36 MB | 65W |
| **Core i9-14900T** | 8+16 | 32 | 1.1 GHz | 5.5 GHz | 36 MB | 35W |
| **Core i7-14700K** | 8+12 | 28 | 3.4 GHz | 5.6 GHz | 33 MB | 125W |
| **Core i7-14700KF** | 8+12 | 28 | 3.4 GHz | 5.6 GHz | 33 MB | 125W |
| **Core i7-14700** | 8+12 | 28 | 2.1 GHz | 5.4 GHz | 33 MB | 65W |
| **Core i7-14700T** | 8+12 | 28 | 1.3 GHz | 5.2 GHz | 33 MB | 35W |
| **Core i5-14600K** | 6+8 | 20 | 3.5 GHz | 5.3 GHz | 24 MB | 125W |
| **Core i5-14600KF** | 6+8 | 20 | 3.5 GHz | 5.3 GHz | 24 MB | 125W |
| **Core i5-14500** | 6+8 | 20 | 2.6 GHz | 5.0 GHz | 24 MB | 65W |
| **Core i5-14400** | 6+4 | 16 | 2.5 GHz | 4.7 GHz | 20 MB | 65W |
| **Core i5-14400F** | 6+4 | 16 | 2.5 GHz | 4.7 GHz | 20 MB | 65W |
| **Core i3-14100** | 4+0 | 8 | 3.5 GHz | 4.7 GHz | 12 MB | 60W |
| **Core i3-14100F** | 4+0 | 8 | 3.5 GHz | 4.7 GHz | 12 MB | 60W |

#### Gerações Anteriores Desktop (LGA 1700 - Alder Lake)

| Modelo | Núcleos (P+E) | Threads | Freq. Boost | L3 Cache | TDP |
|--------|---------------|---------|-------------|----------|-----|
| Core i9-13900K | 8+16 | 32 | 5.8 GHz | 36 MB | 125W |
| Core i9-13900 | 8+16 | 32 | 5.6 GHz | 36 MB | 65W |
| Core i7-13700K | 8+8 | 24 | 5.4 GHz | 30 MB | 125W |
| Core i5-13600K | 6+8 | 20 | 5.1 GHz | 24 MB | 125W |
| Core i5-13400 | 6+4 | 16 | 4.6 GHz | 20 MB | 65W |
| Core i9-12900K | 8+8 | 24 | 5.2 GHz | 30 MB | 125W |
| Core i7-12700K | 8+4 | 20 | 5.0 GHz | 25 MB | 125W |
| Core i5-12600K | 6+4 | 16 | 4.9 GHz | 20 MB | 125W |
| Core i5-12400 | 6+0 | 12 | 4.4 GHz | 18 MB | 65W |
| Core i3-12100 | 4+0 | 8 | 4.3 GHz | 12 MB | 60W |

### 3.2 Linha de Produtos Mobile (Laptops)

#### Meteor Lake (Core Ultra 100 Series - Soquete BGA 2049)

| Modelo | Núcleos (P+E+LPE) | Threads | Freq. Boost | Cache L3 | TDP Base | TDP Turbo |
|--------|-------------------|---------|-------------|----------|----------|-----------|
| **Core Ultra 9 185H** | 6+8+2 | 22 | 5.1 GHz | 24 MB | 45W | 115W |
| **Core Ultra 7 165H** | 6+8+2 | 22 | 5.0 GHz | 24 MB | 28W | 115W |
| **Core Ultra 7 155H** | 6+8+2 | 22 | 4.8 GHz | 24 MB | 28W | 115W |
| **Core Ultra 5 135H** | 4+8+2 | 18 | 4.6 GHz | 18 MB | 28W | 115W |
| **Core Ultra 5 125H** | 4+8+2 | 18 | 4.5 GHz | 18 MB | 28W | 115W |
| **Core Ultra 7 164U** | 2+8+2 | 14 | 4.8 GHz | 12 MB | 9W | 30W |
| **Core Ultra 5 134U** | 2+8+2 | 14 | 4.4 GHz | 12 MB | 9W | 30W |
| **Core Ultra 5 126U** | 2+4+2 | 12 | 4.7 GHz | 12 MB | 9W | 30W |

#### Raptor Lake Mobile (Core 10000 Series)

| Modelo | Núcleos (P+E) | Threads | Freq. Boost | Cache | TDP |
|--------|---------------|---------|-------------|-------|-----|
| Core i9-14900HX | 8+16 | 32 | 5.8 GHz | 36 MB | 55W |
| Core i7-14700HX | 8+12 | 28 | 5.5 GHz | 33 MB | 55W |
| Core i7-14650HX | 8+8 | 24 | 5.2 GHz | 30 MB | 55W |
| Core i5-14500HX | 6+8 | 20 | 4.9 GHz | 24 MB | 55W |
| Core i5-14450HX | 6+8 | 20 | 4.8 GHz | 24 MB | 55W |
| Core i9-13980HX | 8+16 | 32 | 5.6 GHz | 36 MB | 55W |
| Core i7-13800H | 6+8 | 20 | 5.2 GHz | 24 MB | 45W |
| Core i5-13600H | 4+8 | 16 | 4.8 GHz | 18 MB | 45W |

### 3.3 Linha de Produtos Server/Workstation

#### Xeon Scalable (Granite Rapids - 6ª Geração)

| Modelo | Núcleos | Threads | Freq. Base | Freq. Boost | L3 Cache | TDP | Soquete |
|--------|---------|---------|------------|-------------|----------|-----|---------|
| **Xeon 6980P** | 128 | 256 | 2.0 GHz | 3.2 GHz | 480 MB | 500W | LGA 4710 |
| **Xeon 6979P** | 120 | 240 | 2.1 GHz | 3.1 GHz | 450 MB | 500W | LGA 4710 |
| **Xeon 6960P** | 96 | 192 | 2.2 GHz | 3.3 GHz | 384 MB | 400W | LGA 4710 |
| **Xeon 6952P** | 80 | 160 | 2.1 GHz | 3.2 GHz | 320 MB | 400W | LGA 4710 |
| **Xeon 6944P** | 60 | 120 | 2.5 GHz | 3.5 GHz | 240 MB | 350W | LGA 4710 |

#### Xeon Scalable (Emerald Rapids - 5ª Geração)

| Modelo | Núcleos | Threads | Freq. Base | Freq. Boost | L3 Cache | TDP |
|--------|---------|---------|------------|-------------|----------|-----|
| Xeon Platinum 8592+ | 64 | 128 | 1.9 GHz | 3.9 GHz | 320 MB | 350W |
| Xeon Platinum 8580 | 60 | 120 | 2.0 GHz | 3.8 GHz | 300 MB | 350W |
| Xeon Gold 6548Y | 32 | 64 | 2.5 GHz | 4.1 GHz | 60 MB | 250W |
| Xeon Gold 6526Y | 16 | 32 | 2.8 GHz | 4.3 GHz | 37.5 MB | 235W |
| Xeon Silver 4510 | 12 | 24 | 2.2 GHz | 4.1 GHz | 30 MB | 150W |

#### Xeon W Workstation (Sapphire Rapids)

| Modelo | Núcleos | Threads | Freq. Boost | L3 Cache | TDP |
|--------|---------|---------|-------------|----------|-----|
| Xeon w9-3495X | 56 | 112 | 4.8 GHz | 105 MB | 350W |
| Xeon w7-3465X | 28 | 56 | 4.8 GHz | 75 MB | 300W |
| Xeon w5-3435X | 16 | 32 | 4.7 GHz | 45 MB | 270W |
| Xeon w3-2435 | 8 | 16 | 4.5 GHz | 22.5 MB | 165W |

### 3.4 Linha Histórica (Legado)

| Geração | Microarquitetura | Anos | Soquete | Processo | Destaque |
|---------|-----------------|------|---------|----------|----------|
| Core 14ª | Raptor Lake Refresh | 2023-2024 | LGA 1700 | Intel 7 | Último LGA 1700 |
| Core 13ª | Raptor Lake | 2022-2023 | LGA 1700 | Intel 7 | E-cores introduzidos |
| Core 12ª | Alder Lake | 2021-2022 | LGA 1700 | Intel 7 | Arquitetura híbrida |
| Core 11ª | Rocket Lake | 2021 | LGA 1200 | 14nm | PCIe 4.0 |
| Core 10ª | Comet Lake | 2020 | LGA 1200 | 14nm | Até 10 núcleos |
| Core 9ª | Coffee Lake Refresh | 2018-2019 | LGA 1151 | 14nm | i9-9900K (8c/16t) |
| Core 8ª | Coffee Lake | 2017-2018 | LGA 1151 | 14nm | 6 núcleos mainstream |
| Core 7ª | Kaby Lake | 2016-2017 | LGA 1151 | 14nm | Optimização |
| Core 6ª | Skylake | 2015-2016 | LGA 1151 | 14nm | DDR4 |
| Core 5ª | Broadwell | 2015 | LGA 1150 | 14nm | Primeiro 14nm |
| Core 4ª | Haswell | 2013-2014 | LGA 1150 | 22nm | AVX2 |
| Core 3ª | Ivy Bridge | 2012-2013 | LGA 1155 | 22nm | 3D Tri-Gate |
| Core 2ª | Sandy Bridge | 2011-2012 | LGA 1155 | 32nm | Ring bus |
| Core 1ª | Nehalem/Westmere | 2008-2010 | LGA 1156/1366 | 45nm/32nm | IMC integrado |
| Core 2 | Conroe/Penryn | 2006-2008 | LGA 775 | 65nm/45nm | 64-bit |
| Pentium 4 | NetBurst | 2000-2006 | LGA 775/478 | 180-65nm | Hyper-Threading |
| Pentium III | P6 | 1999-2003 | Slot 1/370 | 250-130nm | SSE |

---

## 4. AMD: Catálogo Completo

### 4.1 Linha de Produtos Desktop

#### AM5 (Ryzen 9000 Series - Zen 5)

| Modelo | Núcleos | Threads | Freq. Base | Freq. Boost | L2+L3 Cache | TDP (W) | PCIe |
|--------|---------|---------|------------|-------------|-------------|---------|------|
| **Ryzen 9 9950X** | 16 | 32 | 4.3 GHz | 5.8 GHz | 80 MB | 170W | 5.0 |
| **Ryzen 9 9900X** | 12 | 24 | 4.4 GHz | 5.6 GHz | 76 MB | 120W | 5.0 |
| **Ryzen 7 9700X** | 8 | 16 | 3.8 GHz | 5.5 GHz | 40 MB | 65W | 5.0 |
| **Ryzen 5 9600X** | 6 | 12 | 3.9 GHz | 5.4 GHz | 38 MB | 65W | 5.0 |

#### AM5 (Ryzen 8000 Series - Zen 4 com NPU)

| Modelo | Núcleos | Threads | Freq. Boost | Cache | TDP | NPU TOPs |
|--------|---------|---------|-------------|-------|-----|----------|
| Ryzen 7 8700G | 8 | 16 | 5.1 GHz | 24 MB | 65W | 16 |
| Ryzen 5 8600G | 6 | 12 | 5.0 GHz | 22 MB | 65W | 16 |
| Ryzen 5 8500G | 6 | 12 | 5.0 GHz | 22 MB | 65W | - |
| Ryzen 3 8300G | 4 | 8 | 4.9 GHz | 12 MB | 65W | - |

#### AM5 (Ryzen 7000 Series - Zen 4)

| Modelo | Núcleos | Threads | Freq. Base | Freq. Boost | L2+L3 Cache | TDP |
|--------|---------|---------|------------|-------------|-------------|-----|
| Ryzen 9 7950X | 16 | 32 | 4.5 GHz | 5.7 GHz | 80 MB | 170W |
| Ryzen 9 7950X3D | 16 | 32 | 4.2 GHz | 5.7 GHz | 144 MB | 120W |
| Ryzen 9 7900X | 12 | 24 | 4.7 GHz | 5.6 GHz | 76 MB | 170W |
| Ryzen 9 7900X3D | 12 | 24 | 4.4 GHz | 5.6 GHz | 140 MB | 120W |
| Ryzen 7 7800X3D | 8 | 16 | 4.2 GHz | 5.0 GHz | 104 MB | 120W |
| Ryzen 7 7700X | 8 | 16 | 4.5 GHz | 5.4 GHz | 40 MB | 105W |
| Ryzen 7 7700 | 8 | 16 | 3.8 GHz | 5.3 GHz | 40 MB | 65W |
| Ryzen 5 7600X | 6 | 12 | 4.7 GHz | 5.3 GHz | 38 MB | 105W |
| Ryzen 5 7600 | 6 | 12 | 3.8 GHz | 5.1 GHz | 38 MB | 65W |
| Ryzen 5 7500F | 6 | 12 | 3.7 GHz | 5.0 GHz | 38 MB | 65W |

#### AM4 (Ryzen 5000 Series - Zen 3) - Última geração do soquete

| Modelo | Núcleos | Threads | Freq. Boost | L2+L3 Cache | TDP |
|--------|---------|---------|-------------|-------------|-----|
| Ryzen 9 5950X | 16 | 32 | 4.9 GHz | 72 MB | 105W |
| Ryzen 9 5900X | 12 | 24 | 4.8 GHz | 70 MB | 105W |
| Ryzen 7 5800X3D | 8 | 16 | 4.5 GHz | 100 MB | 105W |
| Ryzen 7 5800X | 8 | 16 | 4.7 GHz | 36 MB | 105W |
| Ryzen 7 5700X | 8 | 16 | 4.6 GHz | 36 MB | 65W |
| Ryzen 5 5600X | 6 | 12 | 4.6 GHz | 35 MB | 65W |
| Ryzen 5 5600 | 6 | 12 | 4.4 GHz | 35 MB | 65W |
| Ryzen 5 5500 | 6 | 12 | 4.2 GHz | 19 MB | 65W |
| Ryzen 3 5300G | 4 | 8 | 4.2 GHz | 10 MB | 65W |

#### AM4 (Ryzen 4000/3000 Series - Zen 2/Zen+)

| Modelo | Núcleos | Threads | Boost | Cache | TDP | Arquitetura |
|--------|---------|---------|-------|-------|-----|-------------|
| Ryzen 9 3950X | 16 | 32 | 4.7 GHz | 72 MB | 105W | Zen 2 |
| Ryzen 9 3900X | 12 | 24 | 4.6 GHz | 70 MB | 105W | Zen 2 |
| Ryzen 7 3800X | 8 | 16 | 4.5 GHz | 36 MB | 105W | Zen 2 |
| Ryzen 7 3700X | 8 | 16 | 4.4 GHz | 36 MB | 65W | Zen 2 |
| Ryzen 5 3600X | 6 | 12 | 4.4 GHz | 35 MB | 95W | Zen 2 |
| Ryzen 5 3600 | 6 | 12 | 4.2 GHz | 35 MB | 65W | Zen 2 |
| Ryzen 7 2700X | 8 | 16 | 4.3 GHz | 20 MB | 105W | Zen+ |
| Ryzen 5 2600X | 6 | 12 | 4.2 GHz | 19 MB | 95W | Zen+ |
| Ryzen 7 1800X | 8 | 16 | 4.0 GHz | 20 MB | 95W | Zen 1 |

### 4.2 Linha de Produtos Mobile

#### Ryzen AI 300 Series (Strix Point - Zen 5)

| Modelo | Núcleos (P+E) | Threads | Freq. Boost | Cache | TDP | NPU (TOPS) |
|--------|---------------|---------|-------------|-------|-----|------------|
| **Ryzen AI 9 HX 370** | 4+8 | 24 | 5.1 GHz | 36 MB | 28W | 50 |
| **Ryzen AI 9 365** | 4+6 | 20 | 5.0 GHz | 34 MB | 28W | 50 |

#### Ryzen 7040 Series (Phoenix - Zen 4)

| Modelo | Núcleos | Threads | Freq. Boost | Cache | TDP | NPU |
|--------|---------|---------|-------------|-------|-----|-----|
| Ryzen 9 7940HS | 8 | 16 | 5.2 GHz | 24 MB | 45W | 10 |
| Ryzen 7 7840HS | 8 | 16 | 5.1 GHz | 24 MB | 45W | 10 |
| Ryzen 7 7840U | 8 | 16 | 5.1 GHz | 24 MB | 28W | 10 |
| Ryzen 5 7640HS | 6 | 12 | 5.0 GHz | 22 MB | 45W | - |
| Ryzen 5 7640U | 6 | 12 | 4.9 GHz | 22 MB | 28W | - |

#### Ryzen 7030 Series (Barcelo - Zen 3)

| Modelo | Núcleos | Threads | Boost | TDP |
|--------|---------|---------|-------|-----|
| Ryzen 7 7730U | 8 | 16 | 4.5 GHz | 15W |
| Ryzen 5 7530U | 6 | 12 | 4.5 GHz | 15W |
| Ryzen 3 7330U | 4 | 8 | 4.3 GHz | 15W |

### 4.3 Linha de Produtos Server/Workstation

#### EPYC 9005 Series (Turin - Zen 5c/5)

| Modelo | Núcleos | Threads | Freq. Base | Freq. Boost | L3 Cache | TDP | CCD Type |
|--------|---------|---------|------------|-------------|----------|-----|----------|
| **EPYC 9845** | 160 | 320 | 2.1 GHz | 3.5 GHz | 640 MB | 500W | Zen 5c |
| **EPYC 9755** | 128 | 256 | 2.7 GHz | 4.1 GHz | 512 MB | 500W | Zen 5 |
| **EPYC 9745** | 128 | 256 | 2.4 GHz | 3.7 GHz | 512 MB | 400W | Zen 5c |
| **EPYC 9655** | 96 | 192 | 2.5 GHz | 4.0 GHz | 384 MB | 400W | Zen 5 |
| **EPYC 9555** | 64 | 128 | 3.0 GHz | 4.4 GHz | 256 MB | 360W | Zen 5 |
| **EPYC 9475F** | 48 | 96 | 3.3 GHz | 4.8 GHz | 256 MB | 400W | Zen 5 |
| **EPYC 9375F** | 32 | 64 | 3.5 GHz | 4.9 GHz | 256 MB | 320W | Zen 5 |

#### EPYC 9004 Series (Genoa/Bergamo - Zen 4/4c)

| Modelo | Núcleos | Threads | Freq. Boost | L3 Cache | TDP | Tipo |
|--------|---------|---------|-------------|----------|-----|------|
| EPYC 9754 | 128 | 256 | 3.1 GHz | 256 MB | 360W | Bergamo (Zen 4c) |
| EPYC 9734 | 112 | 224 | 3.0 GHz | 256 MB | 340W | Bergamo (Zen 4c) |
| EPYC 9654 | 96 | 192 | 3.7 GHz | 384 MB | 360W | Genoa (Zen 4) |
| EPYC 9554 | 64 | 128 | 3.75 GHz | 256 MB | 360W | Genoa (Zen 4) |
| EPYC 9454 | 48 | 96 | 3.8 GHz | 256 MB | 300W | Genoa (Zen 4) |
| EPYC 9354 | 32 | 64 | 3.8 GHz | 256 MB | 280W | Genoa (Zen 4) |
| EPYC 9254 | 24 | 48 | 4.15 GHz | 128 MB | 200W | Genoa (Zen 4) |
| EPYC 9124 | 16 | 32 | 3.7 GHz | 64 MB | 200W | Genoa (Zen 4) |

#### Threadripper 7000 Series (Storm Peak)

| Modelo | Núcleos | Threads | Freq. Boost | L3 Cache | TDP | PCIe Lanes |
|--------|---------|---------|-------------|----------|-----|------------|
| Threadripper 7980X | 64 | 128 | 5.1 GHz | 256 MB | 350W | 148 |
| Threadripper 7970X | 32 | 64 | 5.3 GHz | 128 MB | 350W | 148 |
| Threadripper 7960X | 24 | 48 | 5.3 GHz | 128 MB | 350W | 148 |
| Threadripper PRO 7995WX | 96 | 192 | 5.1 GHz | 384 MB | 350W | 160 |

### 4.4 Linha Histórica (Legado AMD)

| Geração | Microarquitetura | Anos | Soquete | Processo | Destaque |
|---------|-----------------|------|---------|----------|----------|
| Ryzen 5000 | Zen 3 | 2020-2022 | AM4 | TSMC 7nm | Último AM4 |
| Ryzen 3000 | Zen 2 | 2019-2020 | AM4 | TSMC 7nm | Chiplet design |
| Ryzen 2000 | Zen+ | 2018-2019 | AM4 | GF 12nm | Optimização |
| Ryzen 1000 | Zen | 2017-2018 | AM4 | GF 14nm | Ryzen lançamento |
| FX Series | Bulldozer | 2011-2016 | AM3+ | GF 32nm | Módulos CMT |
| Phenom II | K10 | 2008-2011 | AM3 | GF 45nm | Hexa-core |
| Phenom | K10 | 2007-2008 | AM2+ | GF 65nm | Primeiro quad-core AMD |
| Athlon 64 X2 | K8 | 2005-2009 | AM2 | 90-65nm | Dual-core |
| Athlon 64 | K8 | 2003-2005 | 754/939 | 130-90nm | 64-bit desktop |
| Athlon XP | K7 | 2001-2003 | Socket A | 180-130nm | Concorrente Pentium 4 |
| K6-III | K6 | 1999-2000 | Super 7 | 250nm | On-die L2 |
| K6-2 | K6 | 1998-1999 | Super 7 | 250nm | 3DNow! |

---

## 5. ARM: Catálogo Completo

### 5.1 Modelo de Licenciamento ARM

| Tipo de Licença | Descrição | Royalties | Exemplos |
|-----------------|-----------|-----------|----------|
| **Architecture License** | Projetar core próprio compatível ARM | Mais alto | Apple, Qualcomm (Nuvia) |
| **Core License** | Usar cores ARM prontos | Médio | Samsung, MediaTek, NVIDIA |
| **POP License** | Core + otimização para processo específico | Médio-alto | QCT, Unisoc |

### 5.2 Família ARM Cortex (v9.2 - 2024-2025)

#### Série X (Performance Máxima)

| Core | Ano | Decode width | L1 I$ | L1 D$ | L2 (por core) | Aplicação |
|------|-----|--------------|-------|-------|---------------|-----------|
| **Cortex-X925** | 2024 | 10-way | 64KB | 64KB | 2MB | Flagship mobile |
| **Cortex-X4** | 2023 | 10-way | 64KB | 64KB | 2MB | Mobile/Server |
| **Cortex-X3** | 2022 | 8-way | 64KB | 64KB | 1MB | Mobile |
| **Cortex-X2** | 2021 | 8-way | 64KB | 64KB | 1MB | Mobile |
| **Cortex-X1** | 2020 | 8-way | 64KB | 64KB | 1MB | Mobile |

#### Série A (Performance Balanceada)

| Core | Ano | Decode width | L1 I$ | L1 D$ | L2 | Aplicação |
|------|-----|--------------|-------|-------|-----|-----------|
| **Cortex-A725** | 2024 | 8-way | 64KB | 64KB | 1-2MB | Performance mobile |
| **Cortex-A720** | 2023 | 8-way | 64KB | 64KB | 1MB | Mobile |
| **Cortex-A715** | 2022 | 8-way | 64KB | 64KB | 512KB | Mobile |
| **Cortex-A710** | 2021 | 8-way | 64KB | 64KB | 512KB | Mobile |
| **Cortex-A78** | 2020 | 8-way | 32KB | 32KB | 512KB | Mobile |
| **Cortex-A77** | 2019 | 8-way | 32KB | 32KB | 512KB | Mobile |
| **Cortex-A76** | 2018 | 8-way | 32KB | 32KB | 512KB | Mobile |

#### Série A (Eficiência - Little cores)

| Core | Ano | Decode width | L1 I$ | L1 D$ | L2 | Aplicação |
|------|-----|--------------|-------|-------|-----|-----------|
| **Cortex-A520** | 2023 | 4-way | 32KB | 32KB | 256KB | Efficiency mobile |
| **Cortex-A510** | 2021 | 4-way | 32KB | 32KB | 256KB | Efficiency mobile |
| **Cortex-A55** | 2017 | 2-way | 16KB | 16KB | 128KB | Efficiency |
| **Cortex-A53** | 2014 | 2-way | 16KB | 16KB | 128KB | Efficiency |

### 5.3 Família Neoverse (Server/HPC/Infraestrutura)

| Core | Ano | Decode | L2 | L3 | Cores/die | Aplicação |
|------|-----|--------|-----|-----|-----------|-----------|
| **Neoverse V3** | 2023 | 12-way | 4MB | 64MB | 128 | HPC/Cloud |
| **Neoverse V2** | 2022 | 12-way | 2MB | 64MB | 128 | HPC |
| **Neoverse V1** | 2021 | 12-way | 2MB | 64MB | 128 | HPC |
| **Neoverse N3** | 2024 | 8-way | 2MB | 32MB | 192 | Networking/Cloud |
| **Neoverse N2** | 2021 | 8-way | 1MB | 32MB | 192 | Networking |
| **Neoverse E2** | 2023 | 4-way | 1MB | - | Unlimited | Edge/IoT |

### 5.4 Implementações de Terceiros (Licenciados Architecture)

#### Apple Silicon

| Chip | Ano | Núcleos (P+E) | Tech. Process | Transistores | Memória Unificada |
|------|-----|---------------|---------------|--------------|-------------------|
| **M3 Ultra** | 2025 | 24+8 | TSMC N3B | ~180B | 192GB |
| **M3 Max** | 2024 | 12+4 | TSMC N3B | ~92B | 128GB |
| **M3 Pro** | 2024 | 6+6 | TSMC N3B | ~37B | 36GB |
| **M3** | 2023 | 4+4 | TSMC N3B | ~25B | 24GB |
| **M2 Ultra** | 2023 | 16+8 | TSMC N5 | ~134B | 192GB |
| **M2 Max** | 2023 | 8+4 | TSMC N5 | ~67B | 96GB |
| **M2 Pro** | 2023 | 6+4 | TSMC N5 | ~40B | 32GB |
| **M2** | 2022 | 4+4 | TSMC N5 | ~20B | 24GB |
| **M1 Ultra** | 2022 | 16+4 | TSMC N5 | ~114B | 128GB |
| **M1 Max** | 2021 | 8+2 | TSMC N5 | ~57B | 64GB |
| **M1 Pro** | 2021 | 8+2 | TSMC N5 | ~33B | 32GB |
| **M1** | 2020 | 4+4 | TSMC N5 | ~16B | 16GB |
| **A16 Bionic** | 2022 | 2+4 | TSMC N4 | ~16B | - |
| **A15 Bionic** | 2021 | 2+4 | TSMC N5P | ~15B | - |
| **A14 Bionic** | 2020 | 2+4 | TSMC N5 | ~11.8B | - |

#### Qualcomm Snapdragon (Nuvia/Oryon)

| Chip | Ano | Núcleos | Tech. | Freq. | NPU (TOPS) |
|------|-----|---------|-------|-------|------------|
| **Snapdragon X Elite (X1E-84-100)** | 2024 | 12x Oryon | TSMC N4P | 3.8/4.3 GHz | 45 |
| **Snapdragon X Elite (X1E-80-100)** | 2024 | 12x Oryon | TSMC N4P | 3.4/4.0 GHz | 45 |
| **Snapdragon X Elite (X1E-78-100)** | 2024 | 12x Oryon | TSMC N4P | 3.0/3.5 GHz | 45 |
| **Snapdragon X Plus (X1P-64-100)** | 2024 | 10x Oryon | TSMC N4P | 3.4 GHz | 45 |
| **Snapdragon 8 Gen 4** | 2024 | 2+6 Oryon | TSMC N3E | 4.3 GHz | 48 |
| **Snapdragon 8 Gen 3** | 2023 | 1+5+2 | TSMC N4P | 3.3 GHz | 48 |
| **Snapdragon 8 Gen 2** | 2022 | 1+4+3 | TSMC N4 | 3.36 GHz | - |

#### MediaTek Dimensity

| Chip | Ano | Núcleos (ARM) | Tech. | Freq. |
|------|-----|---------------|-------|-------|
| **Dimensity 9400** | 2024 | 1x X925 + 3x X4 + 4x A720 | TSMC N3E | 3.8 GHz |
| **Dimensity 9300** | 2023 | 4x X4 + 4x A720 | TSMC N4P | 3.25 GHz |
| **Dimensity 9200** | 2022 | 1x X3 + 3x A715 + 4x A510 | TSMC N4 | 3.05 GHz |
| **Dimensity 9000** | 2021 | 1x X2 + 3x A710 + 4x A510 | TSMC N4 | 3.05 GHz |

#### Samsung Exynos

| Chip | Ano | Núcleos (ARM/AMD) | Tech. | GPU |
|------|-----|-------------------|-------|-----|
| **Exynos 2400** | 2024 | 1x X4 + 2x A720 + 3x A720 + 4x A520 | Samsung 4LPP+ | Xclipse 940 (AMD RDNA 3) |
| **Exynos 2200** | 2022 | 1x X2 + 3x A710 + 4x A510 | Samsung 4nm | Xclipse 920 (AMD RDNA 2) |
| **Exynos 2100** | 2021 | 1x X1 + 3x A78 + 4x A55 | Samsung 5nm | Mali-G78 MP14 |

#### NVIDIA Grace (Server)

| Chip | Ano | Núcleos (Neoverse) | Tech. | Memória | Largura banda |
|------|-----|-------------------|-------|---------|---------------|
| **Grace Hopper GH200** | 2024 | 144x Neoverse V2 | TSMC 4N | 512GB LPDDR5X | 3.2 TB/s |
| **Grace Superchip** | 2023 | 144x Neoverse V2 | TSMC 4N | 480GB LPDDR5X | 2.0 TB/s |
| **Grace CPU** | 2023 | 72x Neoverse V2 | TSMC 4N | 240GB LPDDR5X | 1.0 TB/s |

#### Amazon Graviton (AWS)

| Chip | Ano | Núcleos (Neoverse) | Tech. | Memória | Aplicação |
|------|-----|-------------------|-------|---------|-----------|
| **Graviton 4** | 2023 | 96x Neoverse V2 | TSMC 5nm | 384GB | AWS EC2 R8g |
| **Graviton 3E** | 2022 | 64x Neoverse V1 | TSMC 5nm | 256GB | HPC optimized |
| **Graviton 3** | 2021 | 64x Neoverse V1 | TSMC 5nm | 256GB | AWS EC2 C7g |
| **Graviton 2** | 2020 | 64x Neoverse N1 | TSMC 7nm | 128GB | AWS EC2 M6g |

### 5.5 Família ARM Microcontrollers (Cortex-M e Cortex-R)

#### Cortex-M (Microcontrollers)

| Core | Ano | Pipeline | DMIPS/MHz | Aplicação |
|------|-----|----------|-----------|-----------|
| **Cortex-M85** | 2022 | 3-stage | 6.3 | IoT High Performance |
| **Cortex-M55** | 2020 | 3-stage | 4.0 | ML/Helium |
| **Cortex-M33** | 2016 | 3-stage | 3.0 | Secure IoT |
| **Cortex-M7** | 2014 | 6-stage | 5.0 | High performance |
| **Cortex-M4** | 2010 | 3-stage | 2.0 | DSP |
| **Cortex-M3** | 2006 | 3-stage | 1.25 | General purpose |
| **Cortex-M0+** | 2012 | 2-stage | 1.0 | Ultra low power |
| **Cortex-M0** | 2009 | 3-stage | 0.9 | Lowest power |

#### Cortex-R (Real-time/Functional Safety)

| Core | Ano | Pipeline | Aplicação |
|------|-----|----------|-----------|
| **Cortex-R82** | 2021 | 8-stage | Storage/SSD controllers |
| **Cortex-R52** | 2016 | 8-stage | Automotive ASIL-D |
| **Cortex-R8** | 2016 | 10-stage | Modem/Baseband |
| **Cortex-R5** | 2011 | 8-stage | Automotive |

---

## 6. Comparativos Técnicos

### 6.1 Arquitetura híbrida: Intel Thread Director

```
┌─────────────────────────────────────────────────────┐
│                 Intel Core Ultra                    │
├─────────────────────────────────────────────────────┤
│  ┌──────────────┐  ┌──────────────┐                │
│  │  P-core      │  │  P-core      │  2-8 núcleos   │
│  │  (Performance)│  │  (Performance)│  (Lion Cove)   │
│  │  L1: 64KB    │  │  L1: 64KB    │  Alta freq.    │
│  │  L2: 2MB     │  │  L2: 2MB     │  (>5.0 GHz)    │
│  └──────┬───────┘  └──────┬───────┘                │
│         │                  │                         │
│  ┌──────┴───────┐  ┌───────┴──────┐                │
│  │  E-core      │  │  E-core      │  8-16 núcleos  │
│  │  (Efficient) │  │  (Efficient) │  (Skymont)     │
│  │  L1: 32KB    │  │  L1: 32KB    │  Baixa freq.   │
│  │  L2: 4MB     │  │  L2: 4MB     │  (2.5-4.0 GHz) │
│  └──────────────┘  └──────────────┘                │
│                                                     │
│      Intel Thread Director (Hardware Scheduler)     │
└─────────────────────────────────────────────────────┘
```

### 6.2 Chiplet Design: AMD Infinity Fabric

```
┌─────────────────────────────────────────────────────────────────┐
│                         EPYC (Server)                            │
├─────────────────────────────────────────────────────────────────┤
│  ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐               │
│  │  CCD    │ │  CCD    │ │  CCD    │ │  CCD    │  8-16 CCDs    │
│  │  Zen 5  │ │  Zen 5  │ │  Zen 5  │ │  Zen 5  │  (8 cores/CCD)│
│  │  32MB   │ │  32MB   │ │  32MB   │ │  32MB   │               │
│  │  L3     │ │  L3     │ │  L3     │ │  L3     │               │
│  └────┬────┘ └────┬────┘ └────┬────┘ └────┬────┘               │
│       │           │           │           │                      │
│  ┌────┴───────────┴───────────┴───────────┴────┐                │
│  │            Infinity Fabric (IF)              │                │
│  │        32 GB/s por link (c/CCD)              │                │
│  └────────────────────┬────────────────────────┘                │
│                       │                                          │
│                  ┌────┴────┐                                    │
│                  │   IOD   │                                    │
│                  │  (I/O)  │                                    │
│                  │  DDR5   │                                    │
│                  │  PCIe 5 │                                    │
│                  └─────────┘                                    │
└─────────────────────────────────────────────────────────────────┘
```

### 6.3 Comparativo de Microarquiteturas (Topo de linha 2025-2026)

| Característica | Intel Lion Cove (P-core) | AMD Zen 5 | Apple M3 P-core | ARM Cortex-X925 |
|----------------|--------------------------|-----------|-----------------|-----------------|
| **Processo** | Intel 20A | TSMC N4P | TSMC N3B | TSMC N3E |
| **Decode width** | 8-way | 8-way | 10-way | 10-way |
| **ROB entries** | ~640 | 448 | ~640 | 448 |
| **ALU count** | 8 | 8 | 8 | 6 |
| **AGU count** | 4 | 4 | 4 | 4 |
| **L1 I-Cache** | 64KB | 32KB | 64KB | 64KB |
| **L1 D-Cache** | 48KB | 32KB | 64KB | 64KB |
| **L2 Cache (per core)** | 3MB | 1MB | 16MB | 2MB |
| **SMT** | Sim (2-way) | Sim (2-way) | Não | Opcional |
| **IPC uplift (vs prev)** | +14% | +16% | +18% | +15% |

### 6.4 Especificações Técnicas Topo de linha (2025-2026)

| Processador | Núcleos (P+E) | Threads | Freq. Base | Freq. Boost | L3 Cache | TDP | Processo |
|-------------|---------------|---------|------------|-------------|----------|-----|----------|
| **Intel Core Ultra 9 285K** | 8+16 | 24 | 3.7 GHz | 5.7 GHz | 36 MB | 125W | Intel 20A |
| **AMD Ryzen 9 9950X** | 16 (16) | 32 | 4.3 GHz | 5.8 GHz | 64 MB | 170W | TSMC N4P |
| **Apple M3 Ultra** | 24 (24) | 24 | 3.5 GHz | 4.1 GHz | 192 MB | 80W | TSMC N3B |
| **Qualcomm Snapdragon X Elite** | 12 (12) | 12 | 3.8 GHz | 4.3 GHz | 42 MB | 45W | TSMC N4P |

---

## 7. Roadmap e Tecnologias Emergentes (2025-2027)

### 7.1 Processos de Fabricação

| Fabricante | Nó atual (2025) | Nó 2026-2027 | Densidade (MTr/mm²) | Vt (mV) |
|------------|-----------------|--------------|---------------------|---------|
| **TSMC** | N3E (3nm) | N2 (2nm GAA) | ~320 (N3E) → ~400 (N2) | 700 → 650 |
| **Samsung** | SF3 (3nm GAA) | SF2 (2nm) | ~280 → ~360 | 720 → 680 |
| **Intel** | Intel 3 (3nm) | Intel 18A (1.8nm) | ~250 → ~380 | 750 → 660 |

### 7.2 Tecnologias de Transistor

| Tecnologia | Fabricante | Vantagem | Desafio |
|------------|------------|----------|---------|
| **FinFET** | Todos (até 5nm) | Maduro, bem compreendido | Escala limitada <3nm |
| **GAAFET (Nanosheet)** | Samsung (SF3), Intel (20A) | Melhor controle de leakage | Complexidade de fabricação |
| **CFET (Complementary FET)** | TSMC (A16), Intel (14A) | Máxima densidade (~3x FinFET) | Desafios térmicos |
| **Backside Power Delivery** | Intel (PowerVia), TSMC | Melhor utilização de área | Yield menor |

### 7.3 Próximas Gerações (Roadmap 2025-2027)

| Ano | Intel | AMD | ARM (Licenciado) | Apple | Processo |
|-----|-------|-----|------------------|-------|----------|
| **2025** | Arrow Lake (20A) | Zen 5 (N4P) | Cortex-X925 | M4 | 3nm |
| **2026** | Panther Lake (18A) | Zen 6 (N2) | Cortex-X930 | M5 | 2nm GAA |
| **2027** | Nova Lake (14A) | Zen 6c (N2) | Cortex-X940 | M6 | 1.4nm CFET |

---

## 8. Métricas de Performance e Benchmarks

### 8.1 Instruções por Ciclo (IPC) - SPEC CPU 2017

| Microarquitetura | SPECint (integer) | SPECfp (FP) | Frequência (GHz) | IPC (int) |
|------------------|-------------------|-------------|------------------|-----------|
| Intel Golden Cove (12ª) | 1.00 (baseline) | 1.00 | 3.0 | - |
| Intel Raptor Cove (13-14ª) | 1.03 | 1.01 | 3.0 | +3% |
| Intel Lion Cove (Arrow Lake) | 1.14 | 1.08 | 3.0 | +14% |
| AMD Zen 4 | 1.04 | 1.06 | 3.0 | +4% |
| AMD Zen 5 | 1.21 | 1.18 | 3.0 | +21% |
| Apple M3 (P-core) | 1.32 | 1.35 | 3.0 | +32% |
| ARM Cortex-X925 | 1.27 | 1.24 | 3.0 | +27% |

### 8.2 Eficiência Energética (Performance por Watt)

```
Performance / Watt (SPECrate @ TDP limite)
─────────────────────────────────────────────────────────────
Apple M3 Ultra          ████████████████████████████ 1.00
AMD Zen 5c (N3E)        ████████████████████████░░░░ 0.87
Qualcomm X Elite        ██████████████████████░░░░░░ 0.79
AMD Zen 5 (N4P)         ██████████████████░░░░░░░░░░ 0.65
Intel Lion Cove (20A)   ████████████████░░░░░░░░░░░░ 0.58
Intel Raptor Cove       ██████████░░░░░░░░░░░░░░░░░░ 0.38
─────────────────────────────────────────────────────────────
                   0.0                     1.0 (relativo)
```

### 8.3 Benchmarks por Categoria

| Categoria | CPU Recomendada | Score Cinebench R24 (MT) | Score Geekbench 6 (MT) |
|-----------|----------------|--------------------------|------------------------|
| **Desktop Ultimate** | Ryzen 9 9950X | ~2500 | ~25,000 |
| **Desktop Gaming** | Core Ultra 9 285K | ~2100 | ~22,000 |
| **Workstation** | Threadripper 7980X | ~4500 | ~45,000 |
| **Mobile Ultimate** | M3 Ultra (Mac) | ~1800 | ~21,000 |
| **Mobile Windows** | Snapdragon X Elite | ~1400 | ~17,000 |
| **Server (per socket)** | EPYC 9755 | - | - |

---

## 9. Guia de Seleção Técnica

### 9.1 Matriz de Decisão por Workload

| Workload | Arquitetura ideal | Porquê | Exemplo de CPU |
|----------|-------------------|--------|----------------|
| **Simulação CFD** | P-cores Intel | AVX-512, alta freq. | Xeon w9-3495X |
| **Modelos de Linguagem (LLM)** | GPU + CPU (qualquer) | Paralelismo massivo | EPYC 9754 |
| **Compilação** | AMD Zen (SMT) | Alto throughput | Ryzen 9 7950X |
| **Banco de dados OLTP** | Intel Xeon (RAS) | Latência baixa | Xeon Platinum 8592+ |
| **Web servers** | ARM Neoverse | Eficiência/Watt | Graviton 4 |
| **Workstation criativa** | Apple M-series | Encoders dedicados | M3 Ultra |
| **Embedded/Robótica** | ARM Cortex-R | Tempo real determinístico | Cortex-R52 |
| **IoT/Bateria** | ARM Cortex-M | Ultra baixo consumo | Cortex-M0+ |

### 9.2 Por Orçamento (Desktop - 2025)

| Orçamento | Recomendação | Núcleos | Preço Aprox. |
|-----------|--------------|---------|--------------|
| **$500+** | Ryzen 9 9950X / Core Ultra 9 285K | 16/24 | $550-650 |
| **$300-500** | Ryzen 7 7800X3D / Core i7-14700K | 8/20 | $350-450 |
| **$200-300** | Ryzen 5 7600X / Core i5-14600K | 6/14 | $220-300 |
| **$100-200** | Ryzen 5 5600 / Core i5-13400 | 6/10 | $130-180 |
| **<$100** | Ryzen 3 5300G / Core i3-14100 | 4/4 | $80-100 |

### 9.3 Por Orçamento (Mobile/Laptop - 2025)

| Orçamento | Recomendação | Núcleos | TDP |
|-----------|--------------|---------|-----|
| **Premium** | M3 Max / Snapdragon X Elite | 12-16 | 28-45W |
| **High-end** | Core Ultra 9 185H / Ryzen AI 9 HX 370 | 16-24 | 28-45W |
| **Mid-range** | Core Ultra 5 125H / Ryzen 7 7840HS | 12-14 | 28W |
| **Entry** | Core i5-1340P / Ryzen 5 7640U | 10-12 | 15-28W |

### 9.4 Fórmulas de Estimativa de Performance

**Performance Single-thread:**
```
P_st = IPC_base × (1 + IPC_gain) × freq_GHz
```

**Performance Multi-thread:**
```
P_mt = P_st × N_cores × E_smp × (1 - O_serial)⁻¹
```
Onde:
- `E_smp` = eficiência de SMP (0.8-0.95)
- `O_serial` = fração serial do código (Lei de Amdahl)

**Performance por Watt:**
```
PPW = P_mt / (TDP_dynamic + P_leakage)
```

---

## 10. Glossário Técnico

| Termo | Definição |
|-------|-----------|
| **uOP (Micro-operation)** | Instrução decomposta após decodificação |
| **ROB (Re-Order Buffer)** | Buffer para reordenamento de instruções OoO |
| **TLB (Translation Lookaside Buffer)** | Cache de tradução de endereços virtuais → físicos |
| **BTB (Branch Target Buffer)** | Cache para predição de branches indiretos |
| **Store Forwarding** | Bypass de dados de store pendente para load subsequente |
| **Speculative Execution** | Execução de instruções antes da confirmação do branch |
| **Cache Coherency (MESI/MOESI)** | Protocolo de consistência entre caches multiprocessados |
| **NUMA (Non-Uniform Memory Access)** | Acesso à memória com latência dependente do nó |
| **CCD (Core Complex Die)** | Die contendo núcleos Zen (AMD) |
| **IOD (I/O Die)** | Die responsável por I/O e memória (AMD) |
| **P-core (Performance core)** | Núcleo de alta performance (Intel híbrido) |
| **E-core (Efficient core)** | Núcleo eficiente (Intel híbrido) |
| **LPE-core (Low Power E-core)** | Núcleo de ultra-baixa potência (Intel Meteor Lake) |

---
