## Questões sobre TPU (Tensor Processing Unit)

---

**Questão 1**
O que significa a sigla TPU?

a) Tensor Programming Unit

b) Text Processing Unit

c) Tensor Processing Unit

d) Transaction Processing Unit

e) Thread Processing Unit

**Resposta correta: c**

---

**Questão 2**
Qual empresa desenvolveu a TPU?

a) NVIDIA

b) AMD

c) Intel

d) Google

e) Microsoft

**Resposta correta: d**

---

**Questão 3**
Para qual propósito específico a TPU foi projetada?

a) Processamento gráfico 3D

b) Machine learning e operações com tensores

c) Processamento de banco de dados

d) Criptografia de dados

e) Compressão de vídeo

**Resposta correta: b**

---

**Questão 4**
Qual arquitetura de processamento é utilizada pela TPU para multiplicação de matrizes?

a) SIMD (Single Instruction, Multiple Data)

b) MIMD (Multiple Instruction, Multiple Data)

c) Systolic array (arranjo sistólico)

d) Von Neumann

e) Harvard modificada

**Resposta correta: c**

---

**Questão 5**
Qual é o formato de precisão otimizado utilizado pelas TPUs modernas?

a) FP32 (ponto flutuante 32 bits)

b) FP64 (ponto flutuante 64 bits)

c) BF16 (bfloat16)

d) INT32 (inteiro 32 bits)

e) FP16 (ponto flutuante 16 bits padrão)

**Resposta correta: c**

---

**Questão 6**
Qual foi o ano de lançamento da primeira TPU (TPU v1)?

a) 2013

b) 2016

c) 2018

d) 2020

e) 2022

**Resposta correta: b**

---

**Questão 7**
Qual era a principal aplicação da TPU v1?

a) Treinamento de redes neurais

b) Inferência de redes neurais

c) Renderização gráfica

d) Mineração de criptomoedas

e) Processamento de linguagem natural

**Resposta correta: b**

---

**Questão 8**
Qual componente da TPU é responsável pelas operações de multiplicação de matrizes?

a) VPU (Vector Processing Unit)

b) MXU (Matrix Multiply Unit)

c) SparseCore

d) HBM Controller

e) ICI Interface

**Resposta correta: b**

---

**Questão 9**
Qual unidade da TPU é especializada em operações element-wise como ativações e normalizações?

a) MXU (Matrix Multiply Unit)

b) SparseCore

c) VPU (Vector Processing Unit)

d) Tensor Core

e) L2 Cache

**Resposta correta: c**

---

**Questão 10**
O que são SparseCores nas TPUs modernas?

a) Unidades de compressão de dados esparsos

b) Unidades especializadas em embeddings e lookups para modelos MoE

c) Caches de baixa latência para matrizes esparsas

d) Núcleos de baixa potência para inferência

e) Controladores de memória para dados esparsos

**Resposta correta: b**

---

**Questão 11**
Qual tecnologia de interconexão as TPUs utilizam para comunicação entre chips?

a) NVLink

b) Infinity Fabric

c) ICI (Inter-Chip Interconnect)

d) PCIe 5.0

e) CXL (Compute Express Link)

**Resposta correta: c**

---

**Questão 12**
Qual topologia de rede as TPUs v5p utilizam para conectar chips em um pod?

a) Hypercube

b) 2D Mesh

c) 3D Torus

d) Fat Tree

e) Dragonfly

**Resposta correta: c**

---

**Questão 13**
O que é o OCS (Optical Circuit Switch) nas TPUs Ironwood?

a) Um switch de rede eletrônico de alta velocidade

b) Um switch óptico reconfigurável que conecta cubes dentro do superpod

c) Um protocolo de comunicação entre chips

d) Um tipo de memória óptica

e) Um compressor de dados óptico

**Resposta correta: b**

---

**Questão 14**
Qual é a largura de banda ICI por chip da TPU Ironwood (v7)?

a) 2.4 Tbps

b) 4.8 Tbps

c) 9.6 Tbps

d) 1.2 Tbps

e) 19.2 Tbps

**Resposta correta: c**

---

**Questão 15**
Qual compilador é utilizado pelo Google para otimizar operações de ML para TPU?

a) LLVM

b) GCC

c) XLA (Accelerated Linear Algebra)

d) CUDA Compiler (NVCC)

e) ROCm Compiler

**Resposta correta: c**

---

**Questão 16**
Qual framework de programação é recomendado pelo Google para obter máxima performance em TPU?

a) TensorFlow

b) PyTorch

c) JAX

d) Keras

e) Caffe

**Resposta correta: c**

---

**Questão 17**
Quantos chips TPU Ironwood (v7) existem em um superpod completo?

a) 4.096

b) 8.960

c) 9.216

d) 256

e) 1.024

**Resposta correta: c**

---

**Questão 18**
Qual é a capacidade de memória HBM3E por chip na TPU Ironwood (v7)?

a) 32 GB

b) 64 GB

c) 95 GB

d) 128 GB

e) 192 GB

**Resposta correta: e**

---

**Questão 19**
Qual a performance FP8 (pico) da TPU Ironwood (v7) por chip?

a) 459 TFLOPS

b) 918 TFLOPS

c) 2.300 TFLOPS

d) 4.614 TFLOPS

e) 9.228 TFLOPS

**Resposta correta: d**

---

**Questão 20**
Qual geração da TPU foi projetada especificamente para a "Age of Inference" (Era da Inferência)?

a) TPU v4

b) TPU v5e

c) TPU v5p

d) Trillium (v6e)

e) Ironwood (v7)

**Resposta correta: e**

---

**Questão 21**
Qual foi a primeira TPU a oferecer suporte a treinamento de redes neurais?

a) TPU v1

b) TPU v2

c) TPU v3

d) TPU v4

e) TPU v5e

**Resposta correta: b**

---

**Questão 22**
Qual a principal vantagem da arquitetura systolic array sobre GPUs para multiplicação de matrizes?

a) Maior frequência de clock

b) Reutilização de dados e menor acesso à memória

c) Mais núcleos de processamento

d) Suporte a mais precisões numéricas

e) Menor custo de fabricação

**Resposta correta: b**

---

**Questão 23**
Qual geração da TPU introduziu os SparseCores (2ª geração) e topologia 3D Torus?

a) TPU v4

b) TPU v5e

c) TPU v5p

d) Trillium (v6e)

e) Ironwood (v7)

**Resposta correta: c**

---

**Questão 24**
Além do Google Cloud, onde mais é possível executar workloads em TPU?

a) AWS

b) Microsoft Azure

c) Oracle Cloud

d) Apenas Google Cloud

e) IBM Cloud

**Resposta correta: d**

---

**Questão 25**
Qual a eficiência energética da TPU Ironwood (v7) comparada à TPU v1?

a) 2× melhor

b) 5× melhor

c) 10× melhor

d) 20× melhor

e) 30× melhor

**Resposta correta: e**

---

**Questão 26**
Qual dessas empresas é cliente conhecida do Google Cloud TPU para treinar seus modelos de IA?

a) OpenAI

b) Anthropic

c) Meta

d) Tesla

e) Apple

**Resposta correta: b**

---

**Questão 27**
Qual é o formato de precisão de 8 bits suportado pelas TPUs modernas para inferência?

a) INT8

b) FP8 (E4M3/E5M2)

c) BF8

d) UINT8

e) FLOAT8

**Resposta correta: b**

---

**Questão 28**
Quantos links ICI cada chip TPU Ironwood possui para conexão com vizinhos?

a) 2 links

b) 4 links

c) 6 links

d) 8 links

e) 12 links

**Resposta correta: c**

---

**Questão 29**
Qual das seguintes operações é MAIS adequada para execução em TPU (vs GPU)?

a) Modelos com batches pequenos (batch size = 1)

b) Modelos com muitas operações sequenciais

c) Treinamento de LLMs com batches grandes

d) Renderização gráfica em tempo real

e) Processamento de áudio com baixa latência

**Resposta correta: c**

---

**Questão 30**
Qual é a diferença fundamental entre uma TPU e uma GPU?

a) GPU é ASIC; TPU é propósito geral

b) TPU é ASIC otimizado para ML; GPU é propósito geral paralelo

c) TPU é mais flexível que GPU

d) GPU não suporta FP8

e) TPU não suporta deep learning

**Resposta correta: b**

---

## Gabarito Oficial

| Q | Resposta | Q | Resposta | Q | Resposta |
|---|----------|---|----------|---|----------|
| 1 | c | 11 | c | 21 | b |
| 2 | d | 12 | c | 22 | b |
| 3 | b | 13 | b | 23 | c |
| 4 | c | 14 | c | 24 | d |
| 5 | c | 15 | c | 25 | e |
| 6 | b | 16 | c | 26 | b |
| 7 | b | 17 | c | 27 | b |
| 8 | b | 18 | e | 28 | c |
| 9 | c | 19 | d | 29 | c |
| 10 | b | 20 | e | 30 | b |

---

## Resumo das Alternativas Corretas por Letra

| Alternativa | Quantidade |
|-------------|------------|
| a | 0 |
| b | 13 |
| c | 12 |
| d | 3 |
| e | 2 |

---

## Distribuição dos Temas

| Tema | Questões |
|------|----------|
| Conceitos básicos e definições | 1, 2, 3, 30 |
| Arquitetura e componentes internos | 4, 5, 8, 9, 10, 22 |
| Gerações e histórico | 6, 7, 20, 21, 23 |
| Interconexão e escalabilidade | 11, 12, 13, 14, 17, 28 |
| Memória e performance | 18, 19, 25 |
| Software e frameworks | 15, 16 |
| Disponibilidade e casos de uso | 24, 26, 27, 29 |

---

## Nível de Dificuldade das Questões

| Nível | Quantidade | Questões |
|-------|------------|----------|
| **Básico** | 8 | 1, 2, 3, 6, 7, 24, 30 |
| **Intermediário** | 12 | 4, 5, 8, 9, 10, 11, 15, 16, 21, 22, 27, 29 |
| **Avançado** | 10 | 12, 13, 14, 17, 18, 19, 20, 23, 25, 26, 28 |

---
