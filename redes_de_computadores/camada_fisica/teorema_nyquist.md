```md
# Teorema de Nyquist (Canais sem Ruído)

O **Teorema de Nyquist** é um dos fundamentos da **comunicação de dados** e da **teoria das redes de computadores**. Ele estabelece o **limite máximo teórico de transmissão de dados** em um **canal ideal (sem ruído)**, considerando apenas a **largura de banda** do canal e o **número de níveis de sinal** utilizados.

---

## Contexto Histórico

O teorema foi formulado por **Harry Nyquist** na década de 1920, inicialmente aplicado a sistemas de **telegrafia**. Com o avanço das telecomunicações, seus princípios passaram a ser amplamente utilizados em:

- Telefonia analógica e digital  
- Sistemas de modulação  
- Processamento digital de sinais  
- Redes de computadores  

---

## Formulação Matemática

A capacidade máxima de transmissão de um canal sem ruído é dada por:

\[
C = 2 \cdot B \cdot \log_2(M)
\]

### Onde:
- **C** – Capacidade do canal (bits por segundo – bps)  
- **B** – Largura de banda do canal (Hertz – Hz)  
- **M** – Número de níveis distintos de sinal  

---

## Interpretação do Teorema

- O fator **2 · B** representa o número máximo de **símbolos por segundo** que podem ser transmitidos sem interferência.
- O termo **log₂(M)** indica quantos **bits** cada símbolo pode transportar.
- O aumento da **largura de banda** permite mais transmissões por segundo.
- O aumento do **número de níveis de sinal** permite mais bits por símbolo.

➡️ Assim, a taxa de transmissão cresce com o aumento da largura de banda e/ou do número de níveis do sinal.

---

## Exemplo Prático

Considere um canal com:
- Largura de banda: **4.000 Hz**
- Número de níveis de sinal: **16**

\[
C = 2 \cdot 4000 \cdot \log_2(16)
\]

Como \(\log_2(16) = 4\):

\[
C = 32.000 \text{ bps}
\]

**Capacidade máxima:** **32 kbps**

---

## Limitações do Teorema de Nyquist

- Considera um **canal ideal**, sem ruídos ou interferências.
- Não leva em conta atenuação, distorção ou perdas do meio físico.
- Em canais reais, aumentar o número de níveis de sinal pode aumentar a taxa de erros.

Por isso, para canais reais, utiliza-se o **Teorema de Shannon**, que considera a relação sinal-ruído (SNR).

---

## Comparação: Nyquist × Shannon

| Característica | Nyquist | Shannon |
|---------------|--------|----------|
| Considera ruído | Não | Sim |
| Tipo de canal | Ideal | Real |
| Parâmetros | Banda e níveis | Banda e SNR |
| Aplicação | Limite teórico | Limite prático |

---

## Aplicação em Redes de Computadores

O Teorema de Nyquist é amplamente aplicado na **camada física** das redes de computadores, auxiliando no projeto e na análise dos sistemas de transmissão.

### 1. Meios de Transmissão
- Determina a capacidade máxima de enlaces físicos como **par trançado**, **coaxial** e **fibra óptica**.
- Auxilia no dimensionamento de redes cabeadas.

### 2. Técnicas de Modulação
- Fundamenta o uso de modulações como **PAM**, **PSK** e **QAM**.
- Define quantos bits podem ser transmitidos por símbolo.

### 3. Redes Ethernet
- Tecnologias como **Fast Ethernet** e **Gigabit Ethernet** utilizam múltiplos níveis de sinal para alcançar altas taxas de transmissão sem ampliar excessivamente a largura de banda.

### 4. Redes Sem Fio (Wi-Fi)
- Modulações como **64-QAM** e **256-QAM** são baseadas nos limites estabelecidos por Nyquist.
- Quanto melhor a qualidade do canal, maior o número de níveis de sinal utilizáveis.

### 5. Codificação e Transmissão Digital
- Influencia decisões sobre **amostragem**, **quantização** e **codificação de dados**.
- Auxilia no desenvolvimento de protocolos e padrões de comunicação.

---

## Resumo

- O Teorema de Nyquist define o **limite máximo de transmissão em canais sem ruído**.
- A capacidade depende da **largura de banda** e do **número de níveis de sinal**.
- É um pilar teórico essencial para o funcionamento das **redes de computadores modernas**.
- Em ambientes reais, deve ser analisado em conjunto com o **Teorema de Shannon**.
```
