A **Camada Física** tem como função principal transformar bits (0s e 1s) em sinais eletromagnéticos que podem viajar por um meio físico (cobre, fibra ou ar). A **Análise de Fourier** é a ferramenta matemática que nos permite entender como esses sinais se comportam e por que a largura de banda de um canal é limitada.

Segundo o teorema de Fourier, qualquer função periódica razoavelmente bem comportada  com período  pode ser representada como uma soma infinita de senos e cossenos:

Onde  é a **frequência fundamental**.

---

## 1. O Sinal na Prática

Quando enviamos dados digitais, estamos essencialmente enviando uma **onda quadrada** (nível alto para 1, nível baixo para 0). A Análise de Fourier nos mostra que uma onda quadrada é composta por uma frequência fundamental somada a uma série de **harmônicos** (frequências múltiplas da fundamental).

* Para que a onda quadrada seja "perfeita" e nítida no destino, seriam necessários infinitos harmônicos.
* No entanto, os meios físicos (cabos) atuam como filtros que atenuam ou bloqueiam frequências altas.

---

## 2. Largura de Banda e Distorção

A **Largura de Banda (Bandwidth)** de um canal físico é o intervalo de frequências que ele consegue transmitir sem uma perda significativa de energia.

* **Corte de Harmônicos:** Se um cabo tem uma largura de banda pequena, ele "corta" os harmônicos de alta frequência do sinal.
* **Consequência:** A onda quadrada chega ao destino arredondada e distorcida. Se cortarmos harmônicos demais, o receptor não conseguirá mais distinguir se o sinal representa um `0` ou um `1`.

---

## 3. A Relação com a Taxa de Dados

A Análise de Fourier estabelece o limite teórico de transmissão através de dois teoremas fundamentais baseados na frequência:

### Teorema de Nyquist (Canais sem ruído)

Para um canal sem ruído com largura de banda , a taxa máxima de dados (em bits por segundo) é:



*(Onde  é o número de níveis discretos do sinal).*

### Teorema de Shannon (Canais com ruído)

Como todo canal real possui ruído, a capacidade máxima  é limitada pela relação Sinal-Ruído ():


---

## 4. Importância para a Engenharia de Redes

Entender Fourier na Camada Física permite aos engenheiros:

1. **Escolher o Meio:** Decidir entre par trançado (baixa largura de banda) ou fibra ótica (altíssima largura de banda).
2. **Modulação:** Criar técnicas como QAM ou OFDM (usadas no Wi-Fi e 4G/5G) que utilizam múltiplas frequências para transmitir mais bits no mesmo intervalo de tempo.
3. **Filtragem:** Projetar equipamentos que limpem o ruído sem destruir a informação essencial contida nos harmônicos fundamentais.
