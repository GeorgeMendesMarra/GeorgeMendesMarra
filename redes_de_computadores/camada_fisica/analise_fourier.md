## Camada Física e Análise de Fourier

A **Camada Física** tem como função principal transformar bits (0s e 1s) em **sinais eletromagnéticos** que podem viajar por um meio físico, como **cabos de cobre**, **fibra óptica** ou **o ar** (redes sem fio).  

A **Análise de Fourier** é a ferramenta matemática que permite compreender como esses sinais se comportam no domínio da frequência e por que a **largura de banda** de um canal é limitada.

Segundo o **Teorema de Fourier**, qualquer função periódica razoavelmente bem comportada, com período **T**, pode ser representada como uma soma infinita de senos e cossenos:

f(t) = a0  
      + Σ [ an · cos(2πn f0 t) + bn · sen(2πn f0 t) ]

Onde:
- f0 = 1 / T → frequência fundamental
- n → número do harmônico
- an e bn → coeficientes da série de Fourier

---

## 1. O Sinal na Prática

Quando enviamos dados digitais em uma rede, estamos essencialmente transmitindo uma **onda quadrada**:
- nível alto representa o bit **1**
- nível baixo representa o bit **0**

A Análise de Fourier demonstra que uma onda quadrada **não é composta por uma única frequência**, mas sim por:
- uma **frequência fundamental**
- uma série de **harmônicos ímpares**, que são múltiplos da frequência fundamental

Exemplo simplificado de uma onda quadrada:

s(t) = sen(2πf0 t)  
      + (1/3) sen(2π·3f0 t)  
      + (1/5) sen(2π·5f0 t)  
      + ...

Quanto mais harmônicos são incluídos, mais “quadrada” e nítida é a forma de onda.

---

## 2. Largura de Banda e Distorção

A **Largura de Banda (Bandwidth)** de um canal físico é o intervalo de frequências que ele consegue transmitir com baixa atenuação.

Bandwidth = fmax − fmin

Onde:
- fmax → maior frequência transmitida
- fmin → menor frequência transmitida

### Efeitos práticos:

- **Corte de harmônicos:** cabos com largura de banda limitada eliminam harmônicos de alta frequência.
- **Consequência:** a onda quadrada chega ao receptor arredondada e distorcida.
- Se muitos harmônicos forem perdidos, o receptor pode não conseguir distinguir corretamente os bits **0** e **1**.

---

## 3. Relação com a Taxa de Dados

A Análise de Fourier está diretamente ligada aos **limites teóricos de transmissão**, expressos por dois teoremas fundamentais.

### Teorema de Nyquist (Canais sem ruído)

Para um canal **sem ruído**, a taxa máxima de transmissão é dada por:

C = 2 × B × log2(M)

Onde:
- C → capacidade do canal (bits por segundo)
- B → largura de banda do canal (Hz)
- M → número de níveis discretos do sinal

Esse teorema mostra que a taxa de dados pode ser aumentada:
- ampliando a largura de banda
- aumentando o número de níveis do sinal

---

### Teorema de Shannon (Canais com ruído)

Em canais reais, que possuem ruído, a capacidade máxima é limitada pela relação sinal-ruído (SNR):

C = B × log2(1 + S/N)

Onde:
- C → capacidade do canal (bps)
- B → largura de banda (Hz)
- S → potência do sinal
- N → potência do ruído

Esse teorema estabelece o **limite absoluto de transmissão confiável**, mesmo com modulações avançadas.

---

## 4. Importância para a Engenharia de Redes

Compreender a Análise de Fourier na Camada Física permite aos engenheiros de redes:

1. **Escolher o meio de transmissão**  
   - Par trançado: menor largura de banda  
   - Fibra óptica: altíssima largura de banda  

2. **Desenvolver técnicas de modulação**  
   - QAM, PSK, OFDM  
   - Utilizadas em Ethernet, Wi-Fi, 4G e 5G  

3. **Projetar filtros e equipamentos**  
   - Remover ruídos
   - Preservar harmônicos essenciais para reconstrução do sinal

---

## Conclusão

A Análise de Fourier explica por que:
- sinais digitais ocupam uma faixa de frequências
- a largura de banda limita a taxa de transmissão
- não é possível transmitir dados infinitamente rápidos em um canal físico

Ela é a base matemática que conecta a **Camada Física**, o **Teorema de Nyquist** e o **Teorema de Shannon**, sustentando todo o funcionamento das **redes de computadores modernas**.
```

---
