# Cabos Coaxiais na Camada Física das Redes de Computadores

Os **cabos coaxiais** foram um dos primeiros meios físicos amplamente utilizados na transmissão de sinais elétricos e no desenvolvimento das redes de computadores. Seu funcionamento e limitações estão diretamente relacionados aos conceitos da **Camada Física**, da **Análise de Fourier** e do **Teorema de Nyquist**.

---

## 1. Cabos Coaxiais e a Camada Física

Na **Camada Física** do modelo OSI, os cabos coaxiais são responsáveis por transportar **sinais elétricos** que representam bits (0 e 1) ao longo de um meio guiado.

As principais funções exercidas pela Camada Física nesse contexto são:
- Representação física dos bits
- Definição dos níveis elétricos
- Taxa de transmissão
- Sincronização do sinal
- Atenuação e interferência eletromagnética

---

## 2. Histórico dos Cabos Coaxiais

O conceito de cabo coaxial surgiu no final do século XIX, a partir dos estudos de **Oliver Heaviside**, baseados nas equações do eletromagnetismo formuladas por **James Clerk Maxwell**.

Com o avanço das telecomunicações no século XX, os cabos coaxiais passaram a ser utilizados em:
- Sistemas de rádio e televisão
- Telefonia de longa distância
- Primeiras redes de computadores

---

## 3. Estrutura do Cabo Coaxial

O cabo coaxial possui uma estrutura concêntrica que reduz interferências externas:

- **Condutor central:** transporta o sinal elétrico
- **Isolante dielétrico:** separa os condutores
- **Malha metálica (blindagem):** reduz ruídos e interferências
- **Capa externa:** proteção mecânica

Essa estrutura garante maior imunidade a ruídos quando comparada a cabos não blindados.

---

## 4. Cabos Coaxiais e a Análise de Fourier

A **Análise de Fourier** permite compreender como os sinais elétricos se comportam dentro do cabo coaxial.

Segundo o Teorema de Fourier, qualquer sinal periódico pode ser representado como uma soma de senos e cossenos:

f(t) = a0  
      + Σ [ an · cos(2πn f0 t) + bn · sen(2πn f0 t) ]

Onde:
- f0 → frequência fundamental
- n → harmônicos do sinal

---

### 4.1 O Sinal Digital no Cabo Coaxial

Na transmissão digital, os bits são representados por uma **onda quadrada**. A Análise de Fourier mostra que essa onda é composta por:
- Frequência fundamental
- Harmônicos de alta frequência

No cabo coaxial:
- Os harmônicos de alta frequência sofrem atenuação
- O sinal chega ao receptor distorcido
- A forma de onda torna-se arredondada

---

## 5. Largura de Banda no Cabo Coaxial

A **largura de banda** define o intervalo de frequências que o cabo coaxial consegue transmitir com eficiência:

Bandwidth = fmax − fmin

Como todo meio físico real:
- O cabo coaxial atua como um filtro passa-baixa
- Frequências elevadas são atenuadas
- A distorção do sinal aumenta com a distância

---

## 6. Teorema de Nyquist e Cabos Coaxiais (Canais sem Ruído)

O **Teorema de Nyquist** estabelece o limite máximo teórico de transmissão em um canal sem ruído:

C = 2 × B × log2(M)

Onde:
- C → capacidade máxima do canal (bps)
- B → largura de banda do cabo coaxial (Hz)
- M → número de níveis de sinal

No contexto dos cabos coaxiais:
- A largura de banda é limitada fisicamente
- Isso impõe um teto à taxa de transmissão
- O aumento da velocidade exige melhor qualidade do cabo ou modulação mais eficiente

---

## 7. Cabos Coaxiais nas Redes de Computadores

Os cabos coaxiais foram amplamente utilizados nas primeiras implementações da Ethernet:

- **10BASE5 (Ethernet grosso):**
  - Até 500 metros
  - Difícil instalação

- **10BASE2 (Ethernet fino):**
  - Até 185 metros
  - Mais flexível, porém sensível a falhas

Essas redes utilizavam topologia de barramento, o que tornava o sistema vulnerável a falhas físicas no cabo.

---

## 8. Limitações dos Cabos Coaxiais

Com base na Camada Física, na Análise de Fourier e no Teorema de Nyquist, as principais limitações dos cabos coaxiais são:

- Largura de banda limitada
- Atenuação significativa em longas distâncias
- Distorção do sinal
- Baixa escalabilidade
- Dificuldade de manutenção

Esses fatores levaram à substituição progressiva dos cabos coaxiais por **par trançado** e **fibra óptica**.

---

## Conclusão

Os cabos coaxiais desempenharam um papel fundamental na evolução das redes de computadores. Sua análise à luz da **Camada Física**, da **Análise de Fourier** e do **Teorema de Nyquist** explica claramente seus limites técnicos e históricos.

Embora atualmente tenham sido substituídos em grande parte, os cabos coaxiais permanecem como um importante marco no desenvolvimento das tecnologias de comunicação de dados.
```
