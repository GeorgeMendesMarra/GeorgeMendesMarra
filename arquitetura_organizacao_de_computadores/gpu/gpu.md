# GPU (Graphics Processing Unit): Guia Técnico Completo

## Sumário
1. [Fundamentos de Arquitetura de GPU](#1-fundamentos-de-arquitetura-de-gpu)
2. [Tecnologias de Execução Paralela](#2-tecnologias-de-execução-paralela)
3. [NVIDIA: Catálogo Completo](#3-nvidia-catálogo-completo)
4. [AMD: Catálogo Completo](#4-amd-catálogo-completo)
5. [Intel: Catálogo Completo](#5-intel-catálogo-completo)
6. [APIs e Modelos de Programação](#6-apis-e-modelos-de-programação)
7. [Comparativos Técnicos](#7-comparativos-técnicos)
8. [Roadmap e Tecnologias Emergentes](#8-roadmap-e-tecnologias-emergentes-2025-2027)
9. [Métricas e Benchmarks](#9-métricas-de-performance-e-benchmarks)
10. [Guia de Seleção Técnica](#10-guia-de-seleção-técnica)

---

## 1. Fundamentos de Arquitetura de GPU

### 1.1 O Que é uma GPU?

A GPU (Graphics Processing Unit) é um processador especializado em operações paralelas massivas, originalmente projetado para aceleração de gráficos 3D, mas agora amplamente utilizado para computação de propósito geral (GPGPU - General-Purpose GPU).

### 1.2 Diferenças Fundamentais: CPU vs GPU

| Característica | CPU | GPU |
|----------------|-----|-----|
| **Núcleos** | 2-64 (complexos, grande controle) | 1000-18000 (simples, pequeno controle) |
| **Frequência** | 3.0-5.8 GHz | 1.0-2.5 GHz |
| **Latência** | Nanossegundos (muito baixa) | Microssegundos (maior) |
| **Cache** | Grande (L1/L2/L3) | Pequeno (L1/L2, alguns MB) |
| **Memória** | DDR4/DDR5 (baixa largura) | GDDR6/HBM (alta largura) |
| **Throughput (FP32)** | ~1 TFLOPS | ~80 TFLOPS |
| **Eficiência energética** | Moderada | Alta para workloads paralelos |

### 1.3 Arquitetura Básica de uma GPU

```
┌─────────────────────────────────────────────────────────────────┐
│                          GPU CHIP                                │
├─────────────────────────────────────────────────────────────────┤
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                   GPC (Graphics Processing Cluster)      │   │
│  │  ┌───────────────────────────────────────────────────┐  │   │
│  │  │              TPC (Texture Processor Cluster)      │  │   │
│  │  │  ┌─────────────┐  ┌─────────────┐                │  │   │
│  │  │  │     SM      │  │     SM      │  (NVIDIA)       │  │   │
│  │  │  │ (Streaming  │  │ (Streaming  │  ou CU (AMD)    │  │   │
│  │  │  │ Multiproc.) │  │ Multiproc.) │                │  │   │
│  │  │  └─────────────┘  └─────────────┘                │  │   │
│  │  └───────────────────────────────────────────────────┘  │   │
│  │                        ... (vários)                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                         ... (vários GPCs)                       │
│                                                                  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │              L2 Cache (compartilhado)                     │  │
│  └──────────────────────────────────────────────────────────┘  │
│                                                                  │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │           Memory Controllers (para VRAM)                  │  │
│  └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### 1.4 Hierarquia de Memória em GPUs

| Nível | Tamanho típico | Latência | Associatividade | Acesso |
|-------|---------------|----------|-----------------|--------|
| **Registradores** | 256KB por SM | 0 ciclo | N/A | Por thread |
| **L1 Cache/Shared Mem** | 64-128 KB por SM | ~10-30 ciclos | 8-16 way | Por SM |
| **L2 Cache** | 4-96 MB (global) | ~50-150 ciclos | 16-32 way | Global |
| **VRAM (GDDR6/HBM)** | 8-144 GB | ~300-500 ciclos | N/A | Global |

### 1.5 Modelo de Execução SIMT (Single Instruction, Multiple Threads)

```
Warps/Wavefronts (32/64 threads executando mesma instrução)
                    │
                    ▼
┌─────────────────────────────────────────────────────────────┐
│  Instrução: ADD R1, R2, R3                                   │
├─────────────────────────────────────────────────────────────┤
│  Thread 0: R1 = R2 + R3   Thread 16: R1 = R2 + R3           │
│  Thread 1: R1 = R2 + R3   Thread 17: R1 = R2 + R3           │
│  Thread 2: R1 = R2 + R3   Thread 18: R1 = R2 + R3           │
│  ...                      ...                               │
│  Thread 15: R1 = R2 + R3  Thread 31: R1 = R2 + R3           │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. Tecnologias de Execução Paralela

### 2.1 Organização de Threads (NVIDIA CUDA)

```
Grid (Kernel)
│
├── Block (0,0)          Block (1,0)          Block (2,0)
│   ├── Thread (0,0)     ├── Thread (0,0)     ├── Thread (0,0)
│   ├── Thread (1,0)     ├── Thread (1,0)     ├── Thread (1,0)
│   ├── Thread (2,0)     ├── Thread (2,0)     ├── Thread (2,0)
│   └── ...              └── ...              └── ...
├── Block (0,1)          Block (1,1)          Block (2,1)
└── ...
```

**Dimensões máximas:**
- Threads por block: 1024 (NVIDIA), 256 (AMD tradicional)
- Grid máximo: 2³¹-1 em cada dimensão

### 2.2 Hierarquia de Memória CUDA

| Memória | Escopo | Lifetime | Latência | Tamanho |
|---------|--------|----------|----------|---------|
| **Registrador** | Thread | Thread | 0 ciclo | Limitado |
| **Local** | Thread | Thread | Alta | Limitado |
| **Shared** | Block | Block | Baixa (~30 ciclos) | 64-128 KB/SM |
| **Global** | Grid | Host allocation | Alta (~400 ciclos) | VRAM total |
| **Constant** | Grid | Host allocation | Média (cached) | 64 KB |
| **Texture** | Grid | Host allocation | Média (cached) | VRAM total |

### 2.3 Tensor Cores (NVIDIA) vs Matrix Cores (AMD)

| Característica | Tensor Cores (NVIDIA) | Matrix Cores (AMD) |
|----------------|----------------------|---------------------|
| **Introdução** | Volta (2017) | CDNA 2 (2021) |
| **Precisões** | FP16, BF16, INT8, INT4, FP8, FP6 | FP16, BF16, INT8 |
| **Operação** | D = A × B + C (matriz 4x4) | D = A × B + C |
| **Throughput por SM** | 1024 FP16 ops/clock | 512 FP16 ops/clock |
| **Aplicação** | Deep Learning, IA | Deep Learning, HPC |

---

## 3. NVIDIA: Catálogo Completo

### 3.1 GeForce RTX 40 Series (Ada Lovelace) - Desktop

#### Flagship

| Modelo | CUDA Cores | Tensor Cores | RT Cores | VRAM | L2 Cache | TDP | Freq. Boost |
|--------|------------|--------------|----------|------|----------|-----|-------------|
| **RTX 4090** | 16384 | 512 (4ª gen) | 128 (3ª gen) | 24 GB GDDR6X | 72 MB | 450W | 2.52 GHz |
| **RTX 4080 SUPER** | 10240 | 320 | 80 | 16 GB GDDR6X | 64 MB | 320W | 2.55 GHz |
| **RTX 4080** | 9728 | 304 | 76 | 16 GB GDDR6X | 64 MB | 320W | 2.51 GHz |

#### Performance

| Modelo | CUDA Cores | Tensor Cores | RT Cores | VRAM | L2 Cache | TDP | Freq. Boost |
|--------|------------|--------------|----------|------|----------|-----|-------------|
| **RTX 4070 Ti SUPER** | 8448 | 264 | 66 | 16 GB GDDR6X | 48 MB | 285W | 2.61 GHz |
| **RTX 4070 Ti** | 7680 | 240 | 60 | 12 GB GDDR6X | 48 MB | 285W | 2.61 GHz |
| **RTX 4070 SUPER** | 7168 | 224 | 56 | 12 GB GDDR6X | 36 MB | 220W | 2.48 GHz |
| **RTX 4070** | 5888 | 184 | 46 | 12 GB GDDR6X | 36 MB | 200W | 2.48 GHz |

#### Entrada

| Modelo | CUDA Cores | Tensor Cores | RT Cores | VRAM | L2 Cache | TDP | Freq. Boost |
|--------|------------|--------------|----------|------|----------|-----|-------------|
| **RTX 4060 Ti 16GB** | 4352 | 136 | 34 | 16 GB GDDR6 | 32 MB | 165W | 2.54 GHz |
| **RTX 4060 Ti 8GB** | 4352 | 136 | 34 | 8 GB GDDR6 | 32 MB | 160W | 2.54 GHz |
| **RTX 4060** | 3072 | 96 | 24 | 8 GB GDDR6 | 24 MB | 115W | 2.46 GHz |

### 3.2 GeForce RTX 30 Series (Ampere) - Desktop

| Modelo | CUDA Cores | Tensor Cores | RT Cores | VRAM | TDP |
|--------|------------|--------------|----------|------|-----|
| RTX 3090 Ti | 10752 | 336 (3ª gen) | 84 (2ª gen) | 24 GB GDDR6X | 450W |
| RTX 3090 | 10496 | 328 | 82 | 24 GB GDDR6X | 350W |
| RTX 3080 Ti | 10240 | 320 | 80 | 12 GB GDDR6X | 350W |
| RTX 3080 | 8704 | 272 | 68 | 10/12 GB GDDR6X | 320W |
| RTX 3070 Ti | 6144 | 192 | 48 | 8 GB GDDR6X | 290W |
| RTX 3070 | 5888 | 184 | 46 | 8 GB GDDR6 | 220W |
| RTX 3060 Ti | 4864 | 152 | 38 | 8 GB GDDR6 | 200W |
| RTX 3060 | 3584 | 112 | 28 | 12 GB GDDR6 | 170W |

### 3.3 Gerações Anteriores NVIDIA

| Geração | Arquitetura | Anos | Processo | Destaque |
|---------|-------------|------|----------|----------|
| RTX 20 Series | Turing | 2018-2020 | TSMC 12nm | Primeiros RT Cores |
| GTX 10 Series | Pascal | 2016-2018 | TSMC 16nm | Grande salto performance |
| GTX 900 Series | Maxwell | 2014-2016 | TSMC 28nm | Eficiência energética |
| GTX 700 Series | Kepler | 2012-2014 | TSMC 28nm | GK110 flagship |
| GTX 600 Series | Kepler | 2012 | TSMC 28nm | Primeiro Kepler |
| GTX 500 Series | Fermi | 2010-2011 | TSMC 40nm | Compute capability 2.0 |
| GTX 400 Series | Fermi | 2010 | TSMC 40nm | Primeira arquitetura unificada |

### 3.4 Linha Mobile (Laptop)

#### RTX 40 Series Mobile

| Modelo | CUDA Cores | VRAM | TGP (W) | Equivalente Desktop |
|--------|------------|------|---------|---------------------|
| RTX 4090 Mobile | 9728 | 16 GB | 150W | RTX 4070 Ti |
| RTX 4080 Mobile | 7424 | 12 GB | 150W | RTX 4070 |
| RTX 4070 Mobile | 4608 | 8 GB | 115W | RTX 4060 |
| RTX 4060 Mobile | 3072 | 8 GB | 115W | RTX 4050 |
| RTX 4050 Mobile | 2560 | 6 GB | 115W | - |

### 3.5 Workstation (RTX Professional)

| Modelo | CUDA Cores | VRAM | TDP | Aplicação |
|--------|------------|------|-----|-----------|
| RTX 6000 Ada | 18176 | 48 GB GDDR6 | 300W | Workstation ultra |
| RTX 5000 Ada | 12800 | 32 GB GDDR6 | 250W | Workstation high |
| RTX 4500 Ada | 7680 | 24 GB GDDR6 | 210W | Workstation mid |
| RTX 4000 Ada | 6144 | 20 GB GDDR6 | 130W | Workstation entry |

### 3.6 Data Center (NVIDIA Tesla/H100)

| Modelo | Arquitetura | CUDA Cores | VRAM (HBM) | TDP | Aplicação |
|--------|-------------|------------|------------|-----|-----------|
| **H100 SXM5** | Hopper | 18432 | 80 GB HBM3 | 700W | IA/Treinamento |
| **H100 PCIe** | Hopper | 18432 | 80 GB HBM2e | 350W | IA/Treinamento |
| **H200** | Hopper | 18432 | 141 GB HBM3e | 700W | LLMs |
| **A100 80GB** | Ampere | 6912 | 80 GB HBM2e | 400W | IA/HPC |
| **A100 40GB** | Ampere | 6912 | 40 GB HBM2 | 250W | IA/HPC |
| **A30** | Ampere | 3456 | 24 GB HBM2 | 165W | Inferência |
| **A10** | Ampere | 9216 | 24 GB GDDR6 | 150W | Virtualização |
| **T4** | Turing | 2560 | 16 GB GDDR6 | 70W | Inferência |

### 3.7 Especificações de SM por Arquitetura

| Arquitetura | CUDA cores/SM | Tensor cores/SM | RT cores/SM | Warp size | Max threads/SM |
|-------------|---------------|-----------------|-------------|-----------|----------------|
| **Hopper** | 128 | 4 | 1 | 32 | 2048 |
| **Ada Lovelace** | 128 | 4 | 2 | 32 | 2048 |
| **Ampere** | 128 | 4 | 1 | 32 | 2048 |
| **Turing** | 64 | 8 | 1 | 32 | 1024 |
| **Pascal** | 128 | - | - | 32 | 2048 |
| **Maxwell** | 128 | - | - | 32 | 2048 |

---

## 4. AMD: Catálogo Completo

### 4.1 Radeon RX 7000 Series (RDNA 3) - Desktop

#### Flagship

| Modelo | Stream Processors | AI Accelerators | Ray Accelerators | VRAM | Infinity Cache | TDP |
|--------|-------------------|-----------------|------------------|------|----------------|-----|
| **RX 7900 XTX** | 6144 | 192 | 96 | 24 GB GDDR6 | 96 MB | 355W |
| **RX 7900 XT** | 5376 | 168 | 84 | 20 GB GDDR6 | 80 MB | 315W |

#### Performance

| Modelo | Stream Processors | AI Accelerators | Ray Accelerators | VRAM | Infinity Cache | TDP |
|--------|-------------------|-----------------|------------------|------|----------------|-----|
| **RX 7900 GRE** | 5120 | 160 | 80 | 16 GB GDDR6 | 64 MB | 260W |
| **RX 7800 XT** | 3840 | 120 | 60 | 16 GB GDDR6 | 64 MB | 263W |
| **RX 7700 XT** | 3456 | 108 | 54 | 12 GB GDDR6 | 48 MB | 245W |

#### Entrada

| Modelo | Stream Processors | AI Accelerators | Ray Accelerators | VRAM | Infinity Cache | TDP |
|--------|-------------------|-----------------|------------------|------|----------------|-----|
| **RX 7600 XT** | 2048 | 64 | 32 | 16 GB GDDR6 | 32 MB | 190W |
| **RX 7600** | 2048 | 64 | 32 | 8 GB GDDR6 | 32 MB | 165W |

### 4.2 Radeon RX 6000 Series (RDNA 2) - Desktop

| Modelo | Stream Processors | Ray Accelerators | VRAM | Infinity Cache | TDP |
|--------|-------------------|------------------|------|----------------|-----|
| RX 6950 XT | 5120 | 80 | 16 GB GDDR6 | 128 MB | 335W |
| RX 6900 XT | 5120 | 80 | 16 GB GDDR6 | 128 MB | 300W |
| RX 6800 XT | 4608 | 72 | 16 GB GDDR6 | 128 MB | 300W |
| RX 6800 | 3840 | 60 | 16 GB GDDR6 | 128 MB | 250W |
| RX 6750 XT | 2560 | 40 | 12 GB GDDR6 | 96 MB | 250W |
| RX 6700 XT | 2560 | 40 | 12 GB GDDR6 | 96 MB | 230W |
| RX 6650 XT | 2048 | 32 | 8 GB GDDR6 | 32 MB | 180W |
| RX 6600 XT | 2048 | 32 | 8 GB GDDR6 | 32 MB | 160W |
| RX 6600 | 1792 | 28 | 8 GB GDDR6 | 32 MB | 132W |
| RX 6500 XT | 1024 | 16 | 4 GB GDDR6 | 16 MB | 107W |

### 4.3 Gerações Anteriores AMD

| Geração | Arquitetura | Anos | Processo | Destaque |
|---------|-------------|------|----------|----------|
| RX 5000 Series | RDNA 1 | 2019-2020 | TSMC 7nm | Primeira RDNA |
| RX 500 Series | GCN 4 (Polaris) | 2017-2019 | GF 14nm | Refresh Polaris |
| RX 400 Series | GCN 4 (Polaris) | 2016-2017 | GF 14nm | Primeiro 14nm |
| R9 300 Series | GCN 3 | 2015-2016 | TSMC 28nm | Última GCN 28nm |
| R9 200 Series | GCN 2 | 2013-2015 | TSMC 28nm | Hawaii |
| HD 7000 Series | GCN 1 | 2012-2013 | TSMC 28nm | Primeira GCN |
| HD 6000 Series | TeraScale 3 | 2010-2012 | TSMC 40nm | Última TeraScale |

### 4.4 Linha Mobile (Radeon RX 7000M)

| Modelo | Stream Processors | VRAM | TDP | Equivalente Desktop |
|--------|-------------------|------|-----|---------------------|
| RX 7900M | 4608 | 16 GB | 180W | RX 7800 XT |
| RX 7600M XT | 2048 | 8 GB | 120W | RX 7600 |
| RX 7600M | 1792 | 8 GB | 90W | - |
| RX 7700S | 2048 | 8 GB | 100W | - |

### 4.5 Workstation (Radeon Pro)

| Modelo | Stream Processors | VRAM (ECC) | TDP | Aplicação |
|--------|-------------------|------------|-----|-----------|
| Radeon Pro W7900 | 6144 | 48 GB GDDR6 | 295W | Workstation ultra |
| Radeon Pro W7800 | 4480 | 32 GB GDDR6 | 260W | Workstation high |
| Radeon Pro W7600 | 2048 | 8 GB GDDR6 | 130W | Workstation mid |
| Radeon Pro W7500 | 1792 | 8 GB GDDR6 | 70W | Workstation entry |

### 4.6 Data Center (AMD Instinct)

| Modelo | Arquitetura | Stream Processors | VRAM (HBM) | TDP | Aplicação |
|--------|-------------|-------------------|------------|-----|-----------|
| **MI300X** | CDNA 3 | 19456 | 192 GB HBM3 | 750W | IA/Treinamento |
| **MI300A** | CDNA 3 | 14592 | 128 GB HBM3 | 550W | APU (CPU+GPU) |
| **MI250X** | CDNA 2 | 14080 | 128 GB HBM2e | 560W | HPC |
| **MI250** | CDNA 2 | 13312 | 128 GB HBM2e | 500W | HPC |
| **MI210** | CDNA 2 | 6656 | 64 GB HBM2e | 300W | Inferência |
| **MI100** | CDNA 1 | 7680 | 32 GB HBM2 | 300W | HPC |

### 4.7 Especificações de CU/WGP por Arquitetura

| Arquitetura | Stream cores/CU | CUs/WGP | ALUs por CU | Ray Accelerators/CU | Matrix cores/CU |
|-------------|-----------------|---------|-------------|--------------------|-----------------|
| **RDNA 3** | 64 | 2 | 128 | 1 | 2 |
| **RDNA 2** | 64 | 2 | 64 | 1 | - |
| **RDNA 1** | 64 | 1 | 64 | - | - |
| **CDNA 3** | 64 | - | 128 | - | 4 |
| **CDNA 2** | 64 | - | 64 | - | 4 |
| **GCN** | 64 | - | 64 | - | - |

---

## 5. Intel: Catálogo Completo

### 5.1 Intel Arc (Alchemist) - Desktop

| Modelo | Xe Cores | Ray Tracing Units | VRAM | TDP | Freq. Boost |
|--------|----------|-------------------|------|-----|-------------|
| **Arc A770 16GB** | 32 | 32 | 16 GB GDDR6 | 225W | 2.4 GHz |
| **Arc A770 8GB** | 32 | 32 | 8 GB GDDR6 | 225W | 2.4 GHz |
| **Arc A750** | 28 | 28 | 8 GB GDDR6 | 225W | 2.4 GHz |
| **Arc A580** | 24 | 24 | 8 GB GDDR6 | 175W | 2.0 GHz |
| **Arc A380** | 8 | 8 | 6 GB GDDR6 | 75W | 2.0 GHz |
| **Arc A310** | 6 | 6 | 4 GB GDDR6 | 75W | 2.0 GHz |

### 5.2 Intel Arc (Battlemage) - Desktop (2025)

| Modelo (previsto) | Xe Cores | VRAM | TDP | Performance target |
|------------------|----------|------|-----|--------------------|
| Arc B770 | ~40 | 16 GB GDDR6 | ~250W | RTX 4070 |
| Arc B750 | ~36 | 12 GB GDDR6 | ~225W | RTX 4060 Ti |
| Arc B580 | ~28 | 12 GB GDDR6 | ~200W | RTX 4060 |
| Arc B380 | ~16 | 8 GB GDDR6 | ~100W | - |

### 5.3 Intel Iris Xe / UHD (Integradas - Mobile)

| Modelo | Xe Cores | Execution Units | Freq. Max | TDP | Presente em |
|--------|----------|-----------------|-----------|-----|-------------|
| Iris Xe (96 EUs) | 96 | 96 | 1.35 GHz | 15-28W | Core Ultra 7/9 |
| Iris Xe (80 EUs) | 80 | 80 | 1.35 GHz | 15-28W | Core i7/i5 |
| UHD Graphics (64 EUs) | 64 | 64 | 1.40 GHz | 15-28W | Core i5/i3 |
| UHD Graphics (48 EUs) | 48 | 48 | 1.20 GHz | 15W | Core i3/Pentium |
| UHD Graphics (32 EUs) | 32 | 32 | 1.10 GHz | 6-15W | Celeron |

### 5.4 Intel Data Center GPU (Flex/Ponte Vecchio)

| Modelo | Arquitetura | Xe Cores | VRAM (HBM) | TDP | Aplicação |
|--------|-------------|----------|------------|-----|-----------|
| **Ponte Vecchio** | Xe-HPC | 128 | 128 GB HBM2e | 600W | HPC (Aurora) |
| **Flex 170** | Xe-HPG | 32 | 16 GB GDDR6 | 150W | Cloud/VDI |
| **Flex 140** | Xe-HPG | 16 | 12 GB GDDR6 | 75W | Cloud/VDI |
| **Data Center GPU Max 1550** | Xe-HPC | 128 | 64 GB HBM2e | 600W | HPC |

---

## 6. APIs e Modelos de Programação

### 6.1 Principais APIs Gráficas

| API | Desenvolvedor | Ano | Plataforma | Destaque |
|-----|---------------|-----|------------|----------|
| **DirectX 12 Ultimate** | Microsoft | 2020 | Windows | Feature level 12_2 |
| **Vulkan** | Khronos Group | 2016 | Multiplataforma | Baixo overhead |
| **Metal** | Apple | 2014 | macOS/iOS | Integração Apple |
| **OpenGL 4.6** | Khronos Group | 2017 | Multiplataforma | Legado |
| **DirectX 11** | Microsoft | 2009 | Windows | Ampla adoção |

### 6.2 Frameworks de Computação GPGPU

| Framework | Desenvolvedor | Linguagem | Plataforma | Destaque |
|-----------|---------------|-----------|------------|----------|
| **CUDA** | NVIDIA | C++/Python/Fortran | NVIDIA only | Mais maduro |
| **ROCm** | AMD | C++/Python (HIP) | AMD + NVIDIA | Open source |
| **OpenCL** | Khronos Group | C/C++ | Multiplataforma | Portável |
| **oneAPI (SYCL)** | Intel | C++ | Multiplataforma | Abstraction |
| **DirectCompute** | Microsoft | HLSL | Windows | Integração DirectX |

### 6.3 Bibliotecas de Deep Learning

| Biblioteca | Suporte GPU | Aceleração |
|------------|-------------|------------|
| **CUDA (NVIDIA)** | NVIDIA | Tensor Cores, cuDNN |
| **ROCm (AMD)** | AMD, NVIDIA | MIOpen |
| **oneDNN (Intel)** | Intel, NVIDIA, AMD | SYCL |
| **Metal Performance Shaders (Apple)** | Apple Silicon | Neural Engine |
| **OpenCV (CUDA/OpenCL)** | Multi | OpenCL, CUDA |

---

## 7. Comparativos Técnicos

### 7.1 Comparativo de Arquiteturas Topo de linha (2025)

| Característica | NVIDIA H100 (Hopper) | AMD MI300X (CDNA 3) | Intel Ponte Vecchio |
|----------------|---------------------|---------------------|---------------------|
| **Transistores** | 80B | 153B | 100B+ |
| **Processo** | TSMC 4N | TSMC N5/N6 | Intel 7 |
| **Compute Units** | 144 SMs | 304 CUs | 128 Xe cores |
| **FP32 ALUs** | 18432 | 19456 | 16384 |
| **Tensor/Matrix cores** | 576 | 1216 | 1024 |
| **VRAM (HBM)** | 80 GB HBM3 | 192 GB HBM3 | 128 GB HBM2e |
| **Largura banda** | 3.35 TB/s | 5.2 TB/s | 2.4 TB/s |
| **L2 Cache** | 50 MB | 80 MB | 128 MB |
| **TDP** | 700W | 750W | 600W |
| **FP16 TFLOPS (Tensor)** | 1979 | 1300 | 1000+ |

### 7.2 Comparativo Desktop Flagship (2025)

| Característica | RTX 4090 | RX 7900 XTX | Arc A770 |
|----------------|----------|-------------|----------|
| **Arquitetura** | Ada Lovelace | RDNA 3 | Alchemist |
| **Processo** | TSMC 4N | TSMC N5/N6 | TSMC N6 |
| **Núcleos** | 16384 CUDA | 6144 SP | 32 Xe cores |
| **Tensor/AI cores** | 512 | 192 | 512 (XMX) |
| **RT cores** | 128 | 96 | 32 |
| **VRAM** | 24 GB GDDR6X | 24 GB GDDR6 | 16 GB GDDR6 |
| **Largura** | 1008 GB/s | 960 GB/s | 560 GB/s |
| **L2 Cache** | 72 MB | 96 MB (Infinity) | 16 MB |
| **TDP** | 450W | 355W | 225W |
| **FP32 TFLOPS** | 82.6 | 61.4 | 19.6 |
| **RT performance** | 191 TFLOPS (RT) | 105 TFLOPS (RT) | 75 TFLOPS (RT) |

### 7.3 Comparativo Mobile Flagship (2025)

| Característica | RTX 4090 Laptop | RX 7900M | Apple M3 Max (GPU) |
|----------------|-----------------|----------|--------------------|
| **Núcleos** | 9728 CUDA | 4608 SP | 40 cores GPU |
| **VRAM** | 16 GB | 16 GB | 96 GB (unificada) |
| **TGP/TDP** | 150W | 180W | ~50W |
| **Largura banda** | 576 GB/s | 576 GB/s | 400 GB/s (unificada) |
| **FP32 TFLOPS** | 32.6 | 28.0 | 21.0 |
| **Ray Tracing** | 3ª gen | 2ª gen | 2ª gen |

---

## 8. Roadmap e Tecnologias Emergentes (2025-2027)

### 8.1 Roadmap NVIDIA

| Geração | Arquitetura | Ano | Processo | Destaque |
|---------|-------------|-----|----------|----------|
| RTX 40 Series | Ada Lovelace | 2022-2024 | TSMC 4N | DLSS 3, Shader Execution Reordering |
| RTX 50 Series | Blackwell | 2025 | TSMC 3nm | PCIe 6.0, GDDR7, DP 2.1 |
| RTX 60 Series | Rubin | 2026-2027 | TSMC 2nm | Chiplet design, HBM4 (datacenter) |

### 8.2 Roadmap AMD

| Geração | Arquitetura | Ano | Processo | Destaque |
|---------|-------------|-----|----------|----------|
| RX 7000 Series | RDNA 3 | 2022-2024 | TSMC N5/N6 | Chiplet GPU |
| RX 8000 Series | RDNA 4 | 2025 | TSMC N3E | Ray tracing melhorado |
| RX 9000 Series | RDNA 5 | 2026-2027 | TSMC N2 | Chiplet avançado |

### 8.3 Roadmap Intel

| Geração | Arquitetura | Ano | Processo | Destaque |
|---------|-------------|-----|----------|----------|
| Arc A-Series | Alchemist | 2022-2024 | TSMC N6 | Primeira GPU discreta |
| Arc B-Series | Battlemage | 2025 | TSMC N4 | Xe2, melhor RT |
| Arc C-Series | Celestial | 2026 | TSMC N3 | Competir com high-end |
| Arc D-Series | Druid | 2027 | Intel 18A/TSMC N2 | Arquitetura pós-Celestial |

### 8.4 Tecnologias Emergentes

| Tecnologia | Fabricantes | Previsão | Impacto |
|------------|-------------|----------|---------|
| **GDDR7** | NVIDIA, AMD | 2025 | 32 Gbps, ~2 TB/s largura |
| **HBM4** | NVIDIA, AMD | 2026 | 2048-bit, 8+ TB/s |
| **Chiplet GPU** | AMD, Intel | Presente | Maior yield, escalabilidade |
| **3D Stacking** | NVIDIA (NVLink) | Presente | H100/H200, superchips |
| **PCIe 6.0** | NVIDIA, AMD | 2025 | 64 GT/s (2x PCIe 5.0) |
| **DP 2.1** | AMD, Intel | 2024 | 80 Gbps (UHBR20) |

---

## 9. Métricas de Performance e Benchmarks

### 9.1 Comparativo de Performance por Tier (Desktop 2025)

| Tier | NVIDIA | AMD | Intel | 4K Gaming (avg FPS) | 1440p Gaming (avg FPS) |
|------|--------|-----|-------|---------------------|------------------------|
| **Ultra** | RTX 4090 | RX 7900 XTX | - | 120+ | 180+ |
| **High** | RTX 4080 SUPER | RX 7900 XT | - | 90-110 | 140-160 |
| **Mid-High** | RTX 4070 Ti SUPER | RX 7800 XT | Arc B770 (2025) | 60-80 | 100-120 |
| **Mid** | RTX 4070 | RX 7700 XT | Arc A770 | 40-60 | 80-100 |
| **Entry** | RTX 4060 | RX 7600 XT | Arc A750 | 25-35 | 50-70 |

### 9.2 Eficiência Energética (Performance por Watt)

```
Performance / Watt (4K gaming FPS per 100W)
─────────────────────────────────────────────────────────────
RTX 4090                 ████████████████████████████ 27.3
RTX 4080                 ██████████████████████████░░ 26.8
RX 7900 XTX              ████████████████████████░░░░ 23.9
RTX 4070                 ████████████████████░░░░░░░░ 20.1
RX 7800 XT               ██████████████████░░░░░░░░░░ 17.8
Arc A770                 ████████████░░░░░░░░░░░░░░░░ 10.5
─────────────────────────────────────────────────────────────
                   0.0                         30.0 (FPS/100W)
```

### 9.3 Throughput de Computação (FP16/INT8)

| GPU | FP16 TFLOPS (Tensor) | INT8 TOPS (Tensor) | Aplicação |
|-----|----------------------|--------------------|-----------|
| H100 | 1979 | 3958 | Treinamento LLM |
| MI300X | 1300 | 2600 | Treinamento LLM |
| RTX 4090 | 330 (Tensor) | 660 | IA local |
| RX 7900 XTX | 123 (ST) | 246 | IA local |
| Arc A770 | 26 (XMX) | 52 | IA local |

### 9.4 Benchmarks por Aplicação (1920x1080 Ultra)

| GPU | Cyberpunk 2077 (RT) | Call of Duty | Forza Horizon 5 | Baldur's Gate 3 |
|-----|---------------------|--------------|-----------------|-----------------|
| RTX 4090 | 140 FPS | 220 FPS | 200 FPS | 180 FPS |
| RTX 4080 | 110 FPS | 180 FPS | 170 FPS | 150 FPS |
| RTX 4070 Ti | 90 FPS | 150 FPS | 140 FPS | 120 FPS |
| RX 7900 XTX | 95 FPS | 190 FPS | 180 FPS | 160 FPS |
| RX 7800 XT | 70 FPS | 140 FPS | 140 FPS | 120 FPS |
| Arc A770 | 50 FPS | 110 FPS | 120 FPS | 90 FPS |

---

## 10. Guia de Seleção Técnica

### 10.1 Matriz de Decisão por Uso

| Uso | GPU Recomendada | VRAM mínima | Porquê |
|-----|----------------|-------------|--------|
| **Gaming 4K** | RTX 4090, RX 7900 XTX | 16-24 GB | Alto throughput |
| **Gaming 1440p** | RTX 4070 Ti SUPER, RX 7800 XT | 12-16 GB | Melhor custo-benefício |
| **Gaming 1080p** | RTX 4060, RX 7600 XT | 8-12 GB | Suficiente para FHD |
| **Workstation 3D** | RTX 6000 Ada, Radeon Pro W7900 | 48 GB | ECC VRAM, certificações |
| **Deep Learning** | H100, RTX 4090 | 24 GB+ | Tensor cores, grande VRAM |
| **Inferência IA** | H200, MI300X | 80 GB+ | LLMs, große batch size |
| **Virtualização** | A10, Flex 170 | 16-24 GB | SR-IOV, vGPU |
| **Edição vídeo 8K** | RTX 4080, W7800 | 16-32 GB | Codecs, decodificação |
| **HPC** | H100, MI300X, Ponte Vecchio | 64 GB+ | FP64 performance |

### 10.2 Por Orçamento - Gaming Desktop (2025)

| Orçamento | Recomendação | VRAM | Performance tier |
|-----------|--------------|------|------------------|
| **$1600+** | RTX 4090 | 24 GB | Ultra 4K |
| **$900-1200** | RTX 4080 SUPER / RX 7900 XTX | 16-24 GB | High 4K |
| **$600-800** | RTX 4070 Ti SUPER / RX 7900 XT | 12-20 GB | High 1440p |
| **$400-500** | RTX 4070 / RX 7800 XT | 12-16 GB | Mid 1440p |
| **$250-350** | RTX 4060 Ti / RX 7600 XT | 8-16 GB | High 1080p |
| **$150-200** | RTX 4060 / RX 7600 | 8 GB | Mid 1080p |

### 10.3 Por Orçamento - Workstation/IA (2025)

| Orçamento | Recomendação | VRAM | Aplicação |
|-----------|--------------|------|-----------|
| **$30000+** | H100 / MI300X | 80-192 GB | Treinamento LLM |
| **$6000-10000** | RTX 6000 Ada / W7900 | 48-64 GB | Workstation 3D/IA |
| **$2000-3000** | RTX 4090 (2x) | 24 GB | Fine-tuning |
| **$1500-2000** | RTX 4080 / Used A100 40GB | 16-40 GB | Inferência |
| **$500-1000** | RTX 4060 Ti 16GB | 16 GB | Inferência local |

### 10.4 Fórmulas de Estimativa de Performance

**Throughput de Gaming (FPS):**
```
FPS = (Shader_cores × freq_boost × IPC_shader) / (Resolution_factor × Quality_factor)
```

**Throughput de Deep Learning (samples/sec):**
```
T_samples = (Tensor_cores × freq × batch_size × precision_factor) / (Model_size / VRAM_bandwidth)
```

**Eficiência para LLMs (tokens/sec per dollar):**
```
E_LLM = (Memory_bandwidth × Utilization_factor) / (TDP × Price_hourly × Precision_bits)
```

---

## 11. Glossário Técnico

| Termo | Definição |
|-------|-----------|
| **SM (Streaming Multiprocessor)** | Unidade básica de processamento NVIDIA contendo CUDA cores |
| **CU (Compute Unit)** | Unidade básica de processamento AMD |
| **Warp/Wavefront** | Grupo de threads executando mesma instrução (32/64 threads) |
| **Tensor Cores** | Unidades especializadas em multiplicação de matrizes (IA) |
| **RT Cores** | Unidades de aceleração de ray tracing |
| **GDDR6/X** | Memória gráfica de alta largura de banda |
| **HBM (High Bandwidth Memory)** | Memória empilhada 3D para datacenter |
| **Infinity Cache** | Cache L3 da AMD para reduzir dependência de VRAM |
| **DLSS (Deep Learning Super Sampling)** | Upscaling via IA da NVIDIA |
| **FSR (FidelityFX Super Resolution)** | Upscaling da AMD |
| **XeSS (Xe Super Sampling)** | Upscaling via IA da Intel |
| **NVLink** | Interconexão de alta velocidade NVIDIA |
| **Infinity Fabric** | Interconexão AMD |
| **PCIe** | Interface de conexão com a placa-mãe |
| **TGP/TDP** | Thermal Design Power / Graphics Power |

---

## 12. Resumo dos Principais Modelos por Categoria

### Gaming Desktop
| Tier | NVIDIA | AMD | Intel |
|------|--------|-----|-------|
| Flagship | RTX 4090 | RX 7900 XTX | - |
| High-end | RTX 4080 SUPER | RX 7900 XT | - |
| Mid-high | RTX 4070 Ti SUPER | RX 7800 XT | Arc B770 (2025) |
| Mid-range | RTX 4070 | RX 7700 XT | Arc A770 |
| Entry | RTX 4060 | RX 7600 | Arc A750 |

### Workstation
| | NVIDIA | AMD |
|--|--------|-----|
| Ultra | RTX 6000 Ada | Radeon Pro W7900 |
| High | RTX 5000 Ada | Radeon Pro W7800 |
| Mid | RTX 4000 Ada | Radeon Pro W7600 |

### Data Center (IA/HPC)
| | NVIDIA | AMD | Intel |
|--|--------|-----|-------|
| Treinamento | H100, H200 | MI300X | Ponte Vecchio |
| Inferência | L40S, A10 | MI210 | Flex 170 |

---
