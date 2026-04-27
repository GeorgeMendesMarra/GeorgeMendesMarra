# TPU (Tensor Processing Unit): Guia Técnico Completo

## Sumário
1. [Fundamentos de Arquitetura de TPU](#1-fundamentos-de-arquitetura-de-tpu)
2. [Arquitetura Systolic Array e MatrixX](#2-arquitetura-systolic-array-e-matrixx)
3. [Google: Catálogo Completo de Gerações](#3-google-catálogo-completo-de-gerações)
4. [Software Stack e Compilação XLA](#4-software-stack-e-compilação-xla)
5. [Interconexão e Escalabilidade](#5-interconexão-e-escalabilidade)
6. [Comparativos Técnicos: TPU vs GPU vs CPU](#6-comparativos-técnicos-tpu-vs-gpu-vs-cpu)
7. [Roadmap e Tecnologias Emergentes](#7-roadmap-e-tecnologias-emergentes)
8. [Métricas e Benchmarks](#8-métricas-de-performance-e-benchmarks)
9. [Casos de Uso e Aplicações](#9-casos-de-uso-e-aplicações)
10. [Guia de Disponibilidade e Seleção](#10-guia-de-disponibilidade-e-seleção)

---

## 1. Fundamentos de Arquitetura de TPU

### 1.1 O Que é uma TPU?

A TPU (Tensor Processing Unit) é um **ASIC (Application-Specific Integrated Circuit)** desenvolvido pelo Google exclusivamente para acelerar cargas de trabalho de machine learning, especialmente redes neurais baseadas em tensores . Diferente de CPUs e GPUs, que são processadores de propósito geral, a TPU é otimizada para um domínio específico: operações matriciais massivas.

### 1.2 Filosofia de Design: Co-Design Hardware-Software

A filosofia central por trás da TPU é o **co-design em nível de sistema**, tratando todo o pod de TPUs não como uma coleção de aceleradores discretos, mas como um supercomputador coeso e integrado . Isso significa que hardware e software (compilador XLA, frameworks JAX/PyTorch) são desenvolvidos em conjunto desde o início.

```
┌─────────────────────────────────────────────────────────────────┐
│                    TPU Co-Design Philosophy                      │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│   ┌──────────────┐         ┌──────────────┐                    │
│   │   Hardware   │◄───────►│   Software   │                    │
│   │   (Silicon)  │         │   (XLA/JAX)  │                    │
│   └──────┬───────┘         └──────┬───────┘                    │
│          │                         │                             │
│          ▼                         ▼                             │
│   ┌──────────────────────────────────────────┐                  │
│   │         System-Level Co-design            │                  │
│   │  - Whole-program optimization (XLA)      │                  │
│   │  - Custom kernels (Pallas)               │                  │
│   │  - 3D Torus interconnect                 │                  │
│   │  - OCS (Optical Circuit Switch)          │                  │
│   └──────────────────────────────────────────┘                  │
│                                                                  │
│   Resultado: Pod completo tratado como supercomputador único   │
└─────────────────────────────────────────────────────────────────┘
```

### 1.3 Arquitetura do Chip TPU (Ironwood - 7ª Geração)

Cada chip TPU moderno consiste em dois componentes principais :

| Componente | Função | Características (Ironwood) |
|------------|--------|---------------------------|
| **Matrix Multiply Unit (MXU)** | Operações matriciais densas (core do deep learning) | Otimizado para MXUs de alta densidade |
| **Vector Processing Unit (VPU)** | Operações element-wise (ativações, normalizações) | Complementa MXU para operações não matriciais |
| **SparseCores** | Embeddings em larga escala (modelos MoE) | 2ª geração em v5p, melhorada no Ironwood |
| **HBM3E Stacks** | Memória de alta largura de banda | 8 stacks por chip, 192 GiB total  |

```
┌─────────────────────────────────────────────────────────────────┐
│                    Ironwood TPU Chip (v7)                        │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│   ┌─────────────────────────────────────────────────────────┐  │
│   │                    TPU Core                              │  │
│   │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐    │  │
│   │  │    MXU      │  │    VPU      │  │ SparseCore  │    │  │
│   │  │ (Matrix     │  │ (Vector     │  │ (Embedding  │    │  │
│   │  │  Multiply)  │  │  Unit)      │  │  Lookups)   │    │  │
│   │  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘    │  │
│   │         │                │                │           │  │
│   │         └────────────────┼────────────────┘           │  │
│   │                          │                             │  │
│   │                   ┌──────▼──────┐                      │  │
│   │                   │  L1/L2 Cache│                      │  │
│   │                   └──────┬──────┘                      │  │
│   └──────────────────────────┼──────────────────────────────┘  │
│                              │                                   │
│   ┌──────────────────────────┼──────────────────────────────┐  │
│   │                    HBM3E Controllers                     │  │
│   │  ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐│  │
│   │  │HBM0│ │HBM1│ │HBM2│ │HBM3│ │HBM4│ │HBM5│ │HBM6│ │HBM7││  │
│   │  └────┘ └────┘ └────┘ └────┘ └────┘ └────┘ └────┘ └────┘│  │
│   │                   192 GiB total (8 stacks)               │  │
│   └──────────────────────────────────────────────────────────┘  │
│                                                                  │
│   ┌──────────────────────────────────────────────────────────┐  │
│   │              ICI (Inter-Chip Interconnect)               │  │
│   │              6 links × 1.6 Tbps = 9.6 Tbps               │  │
│   └──────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### 1.4 Hierarquia de Memória em TPUs

| Nível | Tamanho (Ironwood) | Latência | Função |
|-------|-------------------|----------|--------|
| **Registradores** | Por core | 0-1 ciclo | Dados de threads individuais |
| **L1 Cache** | ~32-64 KB por core | ~5 ciclos | Dados temporários por core |
| **L2 Cache** | Compartilhado | ~20-30 ciclos | Buffer entre core e HBM |
| **HBM3E (VRAM)** | 192 GiB por chip  | ~300-400 ciclos | Parâmetros do modelo |
| **HBM Bandwidth** | 7.4 TB/s por chip  | - | Transferência de dados |

---

## 2. Arquitetura Systolic Array e MatrixX

### 2.1 Princípio da Systolic Array

A TPU utiliza uma arquitetura de **systolic array** (arranjo sistólico) para multiplicação de matrizes . Diferente de uma GPU, que tem muitos núcleos independentes, a systolic array organiza as unidades de processamento em uma grade onde os dados fluem como sangue no sistema circulatório.

```
┌─────────────────────────────────────────────────────────────────┐
│              Systolic Array (Matrix Multiply Unit)               │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│   Entrada (Activations) ──►                                     │
│                              ┌─────┐ ┌─────┐ ┌─────┐            │
│                      Weights │ PE  │─│ PE  │─│ PE  │            │
│                      ┌──────►│  0  │ │  1  │ │  2  │            │
│                      │       └──┬──┘ └──┬──┘ └──┬──┘            │
│                      │          │       │       │               │
│                      │       ┌──▼──┐ ┌──▼──┐ ┌──▼──┐            │
│                      │       │ PE  │─│ PE  │─│ PE  │            │
│                      └──────►│  3  │ │  4  │ │  5  │            │
│                              └──┬──┘ └──┬──┘ └──┬──┘            │
│                                 │       │       │               │
│                              ┌──▼──┐ ┌──▼──┐ ┌──▼──┐            │
│                              │ PE  │─│ PE  │─│ PE  │            │
│                              │  6  │ │  7  │ │  8  │            │
│                              └─────┘ └─────┘ └─────┘            │
│                                    │                             │
│                                    ▼                             │
│                              Saída (Resultados)                  │
│                                                                  │
│   Dados fluem sincronamente (como batimento cardíaco)           │
└─────────────────────────────────────────────────────────────────┘
```

### 2.2 Vantagens da Systolic Array

| Característica | Descrição | Benefício para IA |
|----------------|-----------|-------------------|
| **Dataflow fixo** | Dados se movem em padrão previsível | Reduz controle, aumenta eficiência |
| **Reutilização de dados** | Mesmo peso usado por múltiplos PEs | Menos acesso à memória |
| **Pipeline profundo** | Múltiplas operações simultâneas | Alto throughput |
| **Precisão BF16** | Bfloat16 para computação | Mesma faixa que FP32, metade da largura |

### 2.3 Unidades Especializadas por Geração

| Geração | MXU por Core | SparseCore | Tensor Cores (equivalentes) |
|---------|--------------|------------|----------------------------|
| **v2** | 1 | Não | Não |
| **v3** | 2 | Não | Não |
| **v4** | 2 | 1ª geração | Não |
| **v5e** | 1 (otimizado) | 1ª geração | Não |
| **v5p** | 2 | 2ª geração | Não |
| **Trillium (v6)** | 2 (melhorado) | 2ª geração | Sim (Matrix Accelerator) |
| **Ironwood (v7)** | 2 (ultra-denso) | 2ª geração aprimorada | Sim (MXU 4x maior) |

---

## 3. Google: Catálogo Completo de Gerações

### 3.1 Visão Geral das Gerações TPU

| Geração | Nome Código | Ano | Processo | Aplicação Principal | Status |
|---------|-------------|-----|----------|---------------------|--------|
| **TPU v1** | - | 2016 | 28nm | Inferência | Aposentada |
| **TPU v2** | - | 2017 | 16nm | Treinamento/Inferência | Aposentada  |
| **TPU v3** | - | 2018 | 16nm | Treinamento/Inferência | Aposentada  |
| **TPU v4** | - | 2021 | 7nm | Treinamento em larga escala | Disponível |
| **TPU v5e** | - | 2023 | 5nm | Treinamento custo-eficiente | Disponível  |
| **TPU v5p** | - | 2023/2024 | 5nm | Treinamento fronteiriço | Disponível  |
| **Trillium (v6)** | TPU v6e | 2024 | 3nm | Performance/Eficiência | Disponível  |
| **Ironwood (v7)** | TPU v7 | 2025 | 3nm/2nm? | Idade da Inferência | GA (2025)  |

### 3.2 TPU v4 (4ª Geração)

| Especificação | Valor |
|---------------|-------|
| **Processo** | TSMC 7nm |
| **Chips por Pod** | 4,096 chips |
| **Topologia** | 3D Mesh |
| **ICI Bandwidth** | 2,400 Gbps por chip  |
| **Peak BF16/INT8** | 275 TFLOPS/550 TOPS (estimado) |
| **HBM** | 32 GB HBM2e? |
| **SparseCore** | 1ª geração |

**Destaque:** Primeira TPU a utilizar topologia 3D mesh em larga escala; base para o supercomputador de IA do Google.

---

### 3.3 TPU v5e

| Especificação | Valor |
|---------------|-------|
| **Processo** | TSMC 5nm |
| **Chips por Pod** | Configurável (flexível) |
| **Peak BF16** | 197 TFLOPS  |
| **Peak INT8** | 394 TOPS |
| **HBM** | 16 GB HBM2e? |
| **Preço-performance** | 2.3× melhor que v4  |
| **Posicionamento** | Treinamento custo-eficiente (modelos <200B params) |
| **Custo por chip/hora** | $1.20  |

**Destaque:** TPU mais custo-efetiva do portfólio; ideal para fine-tuning e experimentação.

---

### 3.4 TPU v5p (5ª Geração - Performance)

| Especificação | Valor |
|---------------|-------|
| **Processo** | TSMC 5nm |
| **Chips por Pod** | 8,960 chips  |
| **Topologia** | 3D Torus  |
| **ICI Bandwidth** | 4,800 Gbps por chip  |
| **Peak BF16** | 459 TFLOPS  |
| **Peak INT8** | 918 TOPS  |
| **HBM Type** | HBM2e |
| **HBM Capacity** | 95 GB por chip  |
| **HBM Bandwidth** | 2,765 GB/s  |
| **SparseCore** | 2ª geração |
| **Treinamento LLM (vs v4)** | 2.8× mais rápido  |
| **Custo por chip/hora** | $4.20  |

**Arquitetura do Core v5p:**

```
┌─────────────────────────────────────────┐
│           TPU v5p Core                  │
├─────────────────────────────────────────┤
│  ┌───────────┐  ┌───────────┐          │
│  │   MXU 0   │  │   MXU 1   │          │
│  └─────┬─────┘  └─────┬─────┘          │
│        │              │                 │
│  ┌─────▼─────┐  ┌─────▼─────┐          │
│  │   VPU     │  │ SparseCore│          │
│  └───────────┘  │ (2nd gen) │          │
│                 └───────────┘          │
└─────────────────────────────────────────┘
```

**Destaque:** Maior pod da série v5 (8,960 chips); competidor direto de clusters NVIDIA HGX.

---

### 3.5 Trillium (TPU v6e)

| Especificação | Valor |
|---------------|-------|
| **Processo** | TSMC 3nm (estimado) |
| **Chips por Pod** | Até 256 chips (tightly coupled)  |
| **Performance vs v5e** | 4.7× por chip |
| **Treinamento (vs v5e)** | 4× melhor |
| **Inferência (vs v5e)** | 3× maior throughput |
| **HBM** | 2× capacidade e largura vs v5e |
| **ICI Bandwidth** | 2× vs v5e |
| **Eficiência Energética** | 67% melhor que v5e  |
| **Posicionamento** | Inflection point de performance/eficiência |

**Destaque:** Maior salto geracional na história das TPUs; marca a mudança para comparação direta com GPUs NVIDIA.

---

### 3.6 Ironwood (TPU v7) - Sétima Geração

| Especificação | Valor |
|---------------|-------|
| **Processo** | TSMC 3nm/2nm (estimado) |
| **Chips por Superpod** | 9,216 chips  |
| **Topologia** | 3D Torus + OCS (Optical Circuit Switch) |
| **ICI Bandwidth** | 9.6 Tbps por chip (6 links × 1.6 Tbps)  |
| **Peak FP8 (por chip)** | 4,614 TFLOPS  |
| **Peak FP8 (superpod)** | 42.5 ExaFLOPS  |
| **Peak BF16 (por chip)** | ~2,300 TFLOPS (estimado) |
| **HBM Type** | HBM3E |
| **HBM Capacity (por chip)** | 192 GiB (8 stacks)  |
| **HBM Bandwidth (por chip)** | 7.4 TB/s  |
| **HBM Total (superpod)** | 1.77 PB  |
| **Performance vs v5p** | 10× pico  |
| **Performance vs v6e** | 4× melhor por chip  |
| **Eficiência Energética** | 2× vs Trillium; 30× vs TPU v1  |
| **Refrigeração** | Líquida avançada |
| **Posicionamento** | "Age of Inference" - primeira TPU projetada para inferência em escala |
| **Disponibilidade** | GA em Novembro 2025  |

**Destaque:** Primeira TPU projetada especificamente para a "era da inferência"; suporta agentes de IA e workloads de inferência de baixa latência em escala massiva.

---

### 3.7 Comparativo entre Gerações (Evolução)

| Geração | BF16 TFLOPS | INT8 TOPS | HBM (GB) | HBM BW (GB/s) | Pod Size |
|---------|-------------|-----------|----------|---------------|----------|
| **v4** | ~275 | ~550 | ~32 | ~1,200 | 4,096 |
| **v5e** | 197 | 394 | ~16 | ~800 | Flexível |
| **v5p** | 459  | 918  | 95  | 2,765  | 8,960 |
| **Trillium (v6e)** | ~4.7× v5e | ~ | 2× v5e | 2× v5e | 256 |
| **Ironwood (v7)** | ~2,300 (est) | ~4,600 (est) | 192  | 7,400  | 9,216 |

```
Evolução da Performance (BF16 TFLOPS) - Escala Log
─────────────────────────────────────────────────────────────
v4 (275)                ████████████████████
v5e (197)               ██████████████
v5p (459)               ████████████████████████████████
v6e (~925)              ████████████████████████████████████████████████████
v7 (~2,300)             ██████████████████████████████████████████████████████████████████████████████████
─────────────────────────────────────────────────────────────
                  0                              2,300
```

```
Evolução da Eficiência Energética (Performance/Watt)
─────────────────────────────────────────────────────────────
TPU v1 (1.0 baseline)   ██
TPU v4 (~10x)           ████████████████████
TPU v5p (~15x)          ██████████████████████████████
Trillium (~20x)         ████████████████████████████████████
Ironwood (~30x)         ██████████████████████████████████████████████████████
─────────────────────────────────────────────────────────────
  Dados relativos baseados em declarações do Google 
```

---

## 4. Software Stack e Compilação XLA

### 4.1 Visão Geral do Stack de Software

```
┌─────────────────────────────────────────────────────────────────┐
│                    TPU Software Stack                           │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│   ┌─────────────────────────────────────────────────────────┐  │
│   │              High-Level Frameworks                       │  │
│   │         JAX ──── PyTorch ──── TensorFlow                │  │
│   └─────────────────────────┬───────────────────────────────┘  │
│                             │                                   │
│   ┌─────────────────────────▼───────────────────────────────┐  │
│   │              XLA (Accelerated Linear Algebra)           │  │
│   │  - Whole-program optimization                           │  │
│   │  - Operation fusion                                     │  │
│   │  - Memory layout optimization                           │  │
│   │  - Kernel scheduling                                    │  │
│   └─────────────────────────┬───────────────────────────────┘  │
│                             │                                   │
│   ┌─────────────────────────▼───────────────────────────────┐  │
│   │              Pallas (Custom Kernels)                    │  │
│   │  - Hand-optimized for specific model-hardware combos    │  │
│   │  - Peak performance on MXU/VPU                          │  │
│   └─────────────────────────┬───────────────────────────────┘  │
│                             │                                   │
│   ┌─────────────────────────▼───────────────────────────────┐  │
│   │                    TPU Hardware                         │  │
│   │         MXU ├─ VPU ├─ SparseCore └─ ICI                │  │
│   └─────────────────────────────────────────────────────────┘  │
│                                                                  │
│   Stack suporta JAX (performance máxima) e PyTorch nativo      │
└─────────────────────────────────────────────────────────────────┘
```

### 4.2 XLA (Accelerated Linear Algebra)

O XLA é o compilador que transforma operações de alto nível (em JAX, PyTorch ou TensorFlow) em instruções otimizadas para o hardware TPU .

**Principais otimizações do XLA:**

| Otimização | Descrição | Benefício |
|------------|-----------|-----------|
| **Operation Fusion** | Combina múltiplas operações em um único kernel | Reduz transferências de memória |
| **Buffer Aliasing** | Reutiliza buffers de memória | Reduz alocações |
| **Layout Optimization** | Otimiza formato de dados para MXU | Satura o array sistólico |
| **Multi-chip Partitioning** | Distribui modelo entre chips | Escala para pods grandes |

### 4.3 Frameworks Suportados

| Framework | Nível de Suporte | Performance | Caso de Uso |
|-----------|-----------------|-------------|-------------|
| **JAX** | Nativo (recomendado) | Máxima | Pesquisa Google, Gemini |
| **PyTorch** | Nativo (via PyTorch/XLA) | Alta | Comunidade PyTorch  |
| **TensorFlow** | Legado | Alta | Modelos existentes |

### 4.4 Pallas: Custom Kernels para Performance Extrema

Para casos específicos onde o XLA não atinge performance máxima, o Google oferece **Pallas**, uma linguagem para escrever kernels customizados que acessam diretamente as unidades MXU e VPU .

**Aplicações típicas de kernels customizados:**
- Operadores de atenção otimizados para arquiteturas específicas
- MoE (Mixture of Experts) routing
- Operações de normalização específicas

---

## 5. Interconexão e Escalabilidade

### 5.1 Topologia 3D Torus

As TPUs utilizam uma topologia de interconexão **3D Torus**, onde cada chip se conecta a 6 vizinhos (eixos X, Y e Z, em ambas as direções) .

```
┌─────────────────────────────────────────────────────────────────┐
│                    3D Torus Topology                            │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│                    Z (vertical)                                 │
│                    ▲                                            │
│                    │                                            │
│              ┌─────┼─────┐                                      │
│             /│    │    /│                                      │
│            / │    │   / │                                      │
│           /  │    │  /  │                                      │
│          ┌───┼────┼─┼───┐                                      │
│          │   │    │ │   │                                      │
│    X ────┼───┼────┼─┼───┼────► Y                               │
│          │   │    │ │   │                                      │
│          └───┼────┼─┼───┘                                      │
│           \  │    │  /                                        │
│            \ │    │ /                                         │
│             \│    │/                                          │
│              └─────┘                                          │
│                                                                  │
│   Cada chip: 6 links (X+, X-, Y+, Y-, Z+, Z-)                  │
│   Permite paralelismo em 3 dimensões: data, modelo, pipeline   │
└─────────────────────────────────────────────────────────────────┘
```

### 5.2 Hierarquia de Escala: Chip → Cube → Pod → Superpod

```
┌─────────────────────────────────────────────────────────────────┐
│                    Hierarquia de Escala TPU                      │
├─────────────────────────────────────────────────────────────────┤
│                                                                  │
│   Nível 1: Chip (TPU Individual)                                │
│   ┌────────────────────────────────────┐                        │
│   │ 192 GB HBM3E │ 7.4 TB/s │ 4.6 PF FP8│                       │
│   └────────────────────────────────────┘                        │
│                              │                                   │
│                              ▼                                   │
│   Nível 2: Cube (1 rack = 64 chips)                 │
│   ┌────────────────────────────────────┐                        │
│   │ 4 hosts × 4 chips = 64 chips total │                        │
│   │ Conexão: 3D Torus dentro do cube   │                        │
│   └────────────────────────────────────┘                        │
│                              │                                   │
│                              ▼                                   │
│   Nível 3: Pod (4 cubes = 256 chips)                │
│   ┌────────────────────────────────────┐                        │
│   │ Conexão: OCS (Optical Circuit Switch)│                      │
│   │ Permite reconfiguração dinâmica     │                        │
│   └────────────────────────────────────┘                        │
│                              │                                   │
│                              ▼                                   │
│   Nível 4: Superpod (144 cubes = 9,216 chips)       │
│   ┌────────────────────────────────────┐                        │
│   │ 42.5 ExaFLOPS FP8 │ 1.77 PB HBM   │                        │
│   │ Conexão: OCS entre cubes           │                        │
│   └────────────────────────────────────┘                        │
│                                                                  │
└─────────────────────────────────────────────────────────────────┘
```

### 5.3 Optical Circuit Switch (OCS)

O OCS é uma inovação crítica na arquitetura TPU que permite :

| Característica | Descrição |
|----------------|-----------|
| **Reconfiguração dinâmica** | O fabric manager instrui o OCS a redirecionar conexões opticamente |
| **Tolerância a falhas** | Cubes ou links defeituosos são contornados opticamente |
| **Provisionamento flexível** | Slices de qualquer tamanho podem ser configurados |
| **Escala massiva** | Conecta cubes dentro de um superpod |

### 5.4 ICI Bandwidth por Geração

| Geração | ICI Bandwidth por Chip | Links por Chip |
|---------|----------------------|----------------|
| **v4** | 2,400 Gbps  | 6 |
| **v5e** | ~1,200 Gbps (estimado) | 6 |
| **v5p** | 4,800 Gbps  | 6 |
| **Trillium (v6e)** | ~4,800-5,000 Gbps | 6 |
| **Ironwood (v7)** | 9,600 Gbps (9.6 Tbps)  | 6 |

---

## 6. Comparativos Técnicos: TPU vs GPU vs CPU

### 6.1 Visão Geral Comparativa

| Característica | CPU | GPU (NVIDIA H100) | TPU (Ironwood v7) |
|----------------|-----|-------------------|-------------------|
| **Tipo** | Propósito geral | Propósito geral paralelo | ASIC específico para ML |
| **Arquitetura** | Out-of-order superscalar | SIMT (Single Instruction, Multiple Threads) | Systolic array |
| **Núcleos** | 2-64 (complexos) | 18,432 CUDA cores | ~2 MXU + VPU + SparseCore |
| **Controle** | Complexo (branch prediction, OoO) | Moderado (warp scheduling) | Mínimo (dataflow fixo) |
| **Precisão otimizada** | FP32/FP64 | FP16/BF16/FP8 (Tensor Cores) | BF16/FP8 (MXU) |
| **Memória** | DDR5 (baixa largura) | HBM3 (alta largura) | HBM3E (altíssima largura) |
| **Eficiência para MatMul** | Baixa | Alta | Muito alta |
| **Flexibilidade** | Máxima | Alta | Mínima (apenas ML) |
| **Disponibilidade** | Universal | Via compra direta ou nuvem | Exclusivamente Google Cloud |

### 6.2 Comparativo de Performance por Geração (NVIDIA vs Google)

| Geração (Ano) | NVIDIA GPU | FP16/ BF16 TFLOPS | TPU Google | BF16 TFLOPS |
|---------------|------------|-------------------|------------|-------------|
| 2017-2018 | V100 | 125 | TPU v2/v3 | 45/180 |
| 2020-2021 | A100 | 312 | TPU v4 | ~275 |
| 2022-2023 | H100 | 1,979 (sparse) | TPU v5p | 459 |
| 2024-2025 | B200 | ~4,500 (est) | Ironwood v7 | ~2,300 |

### 6.3 Resultados de Benchmark (TPU v2/v3 vs V100) 

| Observação | Conclusão |
|------------|-----------|
| **TPU não explora paralelismo de profundidade** | Modelos mais profundos não se beneficiam linearmente |
| **Operações FC e CNN são bottleneck por bandwidth** | MXU fica ocioso se operação não é compute-bound |
| **Overhead de comunicação inter-chip** | Modelos multi-chip sofrem com latência |
| **Modelos grandes preferem CPU** | Devido a restrições de memória (v2/v3) |
| **Batch size grande prefere TPU** | Arrays sistólicos são eficientes com batches grandes |
| **Batch size pequeno prefere GPU** | Warp scheduling da GPU é mais flexível |
| **CNNs grandes têm maior speedup na TPU** | Arquitetura otimizada para convoluções |
| **TPU atinge 2× (CNN) e 3× (RNN) FLOPS utilization vs GPU** | MXU é mais eficiente que Tensor Cores da época |

### 6.4 Eficiência Energética Comparativa

```
Eficiência Energética (Performance por Watt)
─────────────────────────────────────────────────────────────
CPU (Intel Skylake)     ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ 1.0
GPU (NVIDIA H100)       ████████████████████████░░░░░░░░ 7.0
TPU v4                  ████████████████████████████████ 9.0
TPU v5p                 ████████████████████████████████████████ 14.0
Trillium (v6e)          ████████████████████████████████████████████████ 18.0
Ironwood (v7)           ██████████████████████████████████████████████████████████ 28.0
─────────────────────────────────────────────────────────────
                        0                                  30
(Dados relativos baseados em declarações oficiais e estimativas de eficiência)
```

---

## 7. Roadmap e Tecnologias Emergentes

### 7.1 Roadmap de Gerações TPU

| Geração | Ano | Processo | Principais Inovações |
|---------|-----|----------|---------------------|
| **v4** | 2021 | 7nm | 3D Mesh, primeira geração escalável |
| **v5e** | 2023 | 5nm | Chiplet? Custo-eficiência |
| **v5p** | 2023/2024 | 5nm | 3D Torus, SparseCore 2nd gen |
| **v6 (Trillium)** | 2024 | 3nm | 4.7× performance per chip, 67% melhor eficiência |
| **v7 (Ironwood)** | 2025 | 3nm/2nm | 42.5 ExaFLOPS, OCS, projetada para inferência |
| **v8 (Projeto Gemini)** | 2026-2027 | 2nm/1.8nm? | Chiplet avançado? HBM4? |

### 7.2 Tendências Futuras

| Tendência | Descrição | Expectativa |
|-----------|-----------|-------------|
| **FP6/FP4 Suporte** | Precisões mais baixas para inferência | Próximas gerações |
| **HBM4** | 8+ TB/s de largura de banda | TPU v8 (2026-2027) |
| **Chiplet Design** | Múltiplos dies interconectados | Possível em v8 |
| **OCS em escala** | Conectar superpods via OCS | Expansão contínua |
| **Integração com CPU** | Axion + TPU para agentic workloads | Google Cloud Hypercomputer |

---

## 8. Métricas e Benchmarks

### 8.1 Resumo de Métricas por Geração

| Métrica | v4 | v5e | v5p | Trillium (v6e) | Ironwood (v7) |
|---------|-----|-----|-----|----------------|---------------|
| **BF16 TFLOPS** | ~275 | 197 | 459 | ~925 | ~2,300 |
| **INT8 TOPS** | ~550 | 394 | 918 | ~1,850 | ~4,600 |
| **FP8 TFLOPS** | N/A | N/A | ~1,800 | ~3,700 | 4,614  |
| **HBM GB** | ~32 | ~16 | 95 | ~32 | 192  |
| **HBM BW GB/s** | ~1,200 | ~800 | 2,765 | ~1,600 | 7,400  |
| **Pod Size** | 4,096 | Flex | 8,960 | 256 (tight) | 9,216  |
| **ICI Gbps** | 2,400 | 1,200 | 4,800 | ~4,800 | 9,600  |
| **Preço $/chip-hr** | $3.22 | $1.20  | $4.20  | TBD | TBD |

### 8.2 Comparativo de Treinamento LLM (GPT-3 175B)

| Plataforma | Tempo Relativo (v4 = 1.0x) | Custo Relativo |
|------------|---------------------------|----------------|
| **TPU v4** | 1.00x (baseline) | 1.00x |
| **TPU v5e** | ~1.5x (mais lento) | 0.70x (mais barato) |
| **TPU v5p** | 0.36x (2.8× mais rápido que v4)  | 1.30x (mais caro) |
| **NVIDIA H100 (8x)** | ~0.30x | ~2.00x |

### 8.3 Observações de Benchmark (TPU v2/v3 vs GPU) 

| Observação | Detalhamento |
|------------|--------------|
| **Swift do MXU** | MXU da TPU só atinge pico quando operações são compute-bound |
| **Memory bandwidth bottleneck** | Muitas operações FC são limitadas por bandwidth, não compute |
| **Maturidade do software** | Performance da TPU melhorou 2.5-9.7× em 7 meses via otimização de software |
| **Batch size ideal** | TPU prefere batches grandes (≥512); GPU é mais flexível para batches pequenos |
| **Modelos com embeddings** | SparseCores (v5p+) ajudam, mas ainda há gaps |

---

## 9. Casos de Uso e Aplicações

### 9.1 Aplicações Internas do Google

| Produto/Serviço | Uso de TPU | Geração |
|-----------------|------------|---------|
| **Gemini** | Treinamento e inferência | v5p, Trillium, Ironwood  |
| **Google Search** | Ranking de resultados, IA generativa | v4, v5p |
| **YouTube** | Recomendações, transcrição, conteúdo | v4, v5e |
| **Google Photos** | Reconhecimento de imagens | v4 |
| **Google Translate** | Modelos de linguagem | v4, v5p |
| **DeepMind** | AlphaFold, pesquisas de IA | v4, v5p, Trillium |

### 9.2 Aplicações Externas (Clientes Google Cloud)

| Cliente | Aplicação | Detalhe |
|---------|-----------|---------|
| **Anthropic** | Claude models | Planeja usar até 1 milhão de TPUs Ironwood  |
| **Salesforce** | Modelos de CRM com IA | v5p  |
| **Lightricks** | LTX-2 multimodal system | Ironwood  |

### 9.3 Quando Usar TPU vs GPU

| Cenário | Recomendação | Motivo |
|---------|--------------|--------|
| **Treinamento de LLMs (Gemini, GPT-4 class)** | TPU (v5p ou Ironwood) | Eficiência de comunicação em pods grandes |
| **Fine-tuning de modelos open-source** | TPU v5e (melhor custo) | 2.3× melhor price-performance que v4 |
| **Inferência em larga escala** | Ironwood (v7) | Projetada para "age of inference" |
| **Modelos pequenos (ResNet, BERT base)** | GPU pode ser competitiva | Overhead da TPU não compensa |
| **Workloads híbridos (ML + gráficos)** | GPU | TPU não suporta gráficos |
| **Pesquisa/experimentação** | TPU v5e ou GPU | Flexibilidade de batches pequenos |
| **Modelos com muitos embeddings** | TPU v5p+ (com SparseCore) | SparseCores otimizados para lookups |

---

## 10. Guia de Disponibilidade e Seleção

### 10.1 Disponibilidade na Google Cloud

| Geração | Status | Acesso | Regiões |
|---------|--------|--------|---------|
| **v4** | Disponível | Geral | Múltiplas |
| **v5e** | Disponível | Geral | Múltiplas  |
| **v5p** | Disponível | Geral (us-east1) | US East  |
| **Trillium (v6e)** | Preview → GA | Solicitar acesso | Limitado |
| **Ironwood (v7)** | GA (Nov 2025) | Geral  | Expandindo |

### 10.2 Seleção por Tipo de Workload

| Workload | Geração Recomendada | Justificativa |
|----------|---------------------|---------------|
| **Treinamento fronteiriço (Gemini-class)** | v5p ou Ironwood | Maior pod, máxima performance |
| **Fine-tuning de LLMs** | v5e | Melhor custo-performance |
| **Inferência de LLMs em escala** | Ironwood | Projetada para inferência, OCS para escala |
| **Embeddings/RecSys** | v5p (SparseCore 2nd gen) | Aceleração de lookup |
| **Pesquisa/experimentos** | v5e ou Trillium | Flexibilidade e eficiência |
| **RL (Reinforcement Learning)** | Ironwood | Suporte a workloads complexos |

### 10.3 Custo por Performance

```
Custo-efetividade Relativa (Treinamento GPT-3 175B)
─────────────────────────────────────────────────────────────
TPU v4                  ████████████████████░░░░░░░░░░░░ 1.00
TPU v5e                 ████████████████████████████████ 2.30 
TPU v5p                 ████████████████████████████░░░░ 2.10 
Trillium (v6e)          ██████████████████████████████████████████ 3.80 (est)
Ironwood (v7)           ████████████████████████████████████████████████ 4.50 (est)
─────────────────────────────────────────────────────────────
                        0                                  5.0
(Dados relativos - maior é melhor)
```

---

## 11. Glossário Técnico

| Termo | Definição |
|-------|-----------|
| **ASIC (Application-Specific Integrated Circuit)** | Circuito integrado projetado para uma aplicação específica (ML, no caso da TPU) |
| **MXU (Matrix Multiply Unit)** | Unidade central da TPU que executa multiplicações de matrizes |
| **VPU (Vector Processing Unit)** | Unidade para operações element-wise (ativações, normalizações) |
| **SparseCore** | Unidade aceleração de embeddings esparsos (modelos MoE, recomendação) |
| **Systolic Array** | Arquitetura onde dados fluem como sangue em um arranjo regular de PEs |
| **XLA (Accelerated Linear Algebra)** | Compilador que otimiza operações de ML para TPU |
| **ICI (Inter-Chip Interconnect)** | Rede de comunicação entre chips TPU dentro de um pod |
| **OCS (Optical Circuit Switch)** | Switch óptico reconfigurável que conecta cubes dentro do superpod |
| **3D Torus** | Topologia onde cada chip conecta-se a 6 vizinhos (X, Y, Z, ±) |
| **Cube** | Unidade básica de 64 chips TPU (1 rack) |
| **Pod** | Agrupamento de cubes (ex: 256 chips) |
| **Superpod** | Agrupamento máximo (ex: 9,216 chips Ironwood) |
| **BF16 (bfloat16)** | Formato de 16 bits com mesma faixa exponencial do FP32 |
| **FP8** | Formato de 8 bits para inferência (E4M3/E5M2) |
| **Pallas** | Linguagem para kernels customizados em TPU |
| **JAX** | Framework de alta performance recomendado para TPU |
| **PyTorch/XLA** | Backend PyTorch para execução em TPU |
| **Host-device balance** | Equilíbrio entre CPU host e acelerador TPU |

---

Este documento consolida todo o conhecimento técnico disponível sobre as Tensor Processing Units (TPUs) do Google, cobrindo desde os fundamentos arquiteturais (systolic array, MXU, VPU), o catálogo completo de gerações (v1 a Ironwood v7), o software stack (XLA, JAX, PyTorch), as tecnologias de interconexão (3D Torus, ICI, OCS), comparativos com CPU e GPU, e um roadmap para as próximas gerações.
