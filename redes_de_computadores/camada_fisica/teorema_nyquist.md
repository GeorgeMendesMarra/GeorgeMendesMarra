Perfeito, aninha 😊
Segue **o arquivo completo em Markdown (.md)** usando a **Opção 1 (100% compatível, sem LaTeX)**.
Você pode **copiar e salvar diretamente** como, por exemplo:
`teorema-nyquist-canais-sem-ruido.md`

---

```md
# Teorema de Nyquist (Canais sem Ruído)

O **Teorema de Nyquist** é um dos fundamentos da comunicação de dados e das redes de computadores. Ele estabelece o **limite máximo teórico de transmissão de dados** em um **canal ideal (sem ruído)**, considerando apenas a **largura de banda do canal** e o **número de níveis de sinal** utilizados.

---

## Contexto Histórico

O teorema foi proposto por **Harry Nyquist** na década de 1920, inicialmente aplicado a sistemas de **telegrafia**. Com o avanço das telecomunicações, seus princípios passaram a ser amplamente utilizados em:

- Telefonia analógica e digital  
- Sistemas de modulação  
- Processamento digital de sinais  
- Redes de computadores  

---

## Fórmula de Nyquist

A capacidade máxima de transmissão de um canal sem ruído é dada por:

C = 2 × B × log2(M)

Onde:
- **C** → Capacidade do canal (bits por segundo – bps)  
- **B** → Largura de banda do canal (Hertz – Hz)  
- **M** → Número de níveis distintos de sinal  

---

## Interpretação do Teorema

- O fator **2 × B** representa o número máximo de **símbolos por segundo** que podem ser transmitidos sem interferência.
- O termo **log2(M)** indica quantos **bits** podem ser codificados em cada símbolo.
- Quanto maior a **largura de banda**, maior a taxa de transmissão.
- Quanto maior o **número de níveis de sinal**, mais bits podem ser representados por símbolo.

Assim, a taxa de transmissão cresce com o aumento da largura de banda e/ou do número de níveis do sinal.

---

## Exemplo Prático

Considere um canal com as seguintes características:
- Largura de banda (B): 4000 Hz  
- Número de níveis de sinal (M): 16  

Aplicando a fórmula:

C = 2 × 4000 × log2(16)

Sabendo que:

log2(16) = 4

Temos:

C = 2 × 4000 × 4  
C = 32000 bps  

**Capacidade máxima do canal:** **32 kbps**

---

## Limitações do Teorema de Nyquist

- Considera um **canal ideal**, sem ruído.
- Não leva em conta interferências, distorções ou perdas do meio físico.
- Em canais reais, o aumento do número de níveis de sinal pode elevar a taxa de erros.

Por isso, para canais com ruído, utiliza-se o **Teorema de Shannon**, que considera a relação sinal-ruído.

---

## Comparação: Nyquist × Shannon

| Característica | Nyquist | Shannon |
|---------------|--------|----------|
| Considera ruído | Não | Sim |
| Tipo de canal | Ideal | Real |
| Parâmetros principais | Banda e níveis | Banda e SNR |
| Uso principal | Limite teórico | Limite prático |

---

## Aplicação em Redes de Computadores

O Teorema de Nyquist é amplamente aplicado na **camada física** das redes de computadores, auxiliando no projeto e análise de sistemas de transmissão.

### 1. Meios de Transmissão
- Auxilia no dimensionamento de enlaces físicos como **par trançado**, **cabo coaxial** e **fibra óptica**.
- Define limites teóricos de capacidade dos meios de transmissão.

### 2. Técnicas de Modulação
- Fundamenta o uso de modulações digitais como **PAM**, **PSK** e **QAM**.
- Define quantos bits podem ser transmitidos por símbolo.

### 3. Redes Ethernet
- Tecnologias como **Fast Ethernet** e **Gigabit Ethernet** utilizam múltiplos níveis de sinal para alcançar altas taxas de transmissão sem ampliar excessivamente a largura de banda.

### 4. Redes Sem Fio (Wi-Fi)
- Modulações como **64-QAM** e **256-QAM** baseiam-se nos limites estabelecidos pelo Teorema de Nyquist.
- Quanto melhor a qualidade do canal, maior o número de níveis de sinal utilizáveis.

### 5. Codificação e Transmissão Digital
- Influencia decisões sobre **amostragem**, **quantização** e **codificação de dados**.
- Auxilia no desenvolvimento de protocolos e padrões de comunicação.

---

## Resumo

- O Teorema de Nyquist define o **limite máximo de transmissão em canais sem ruído**.
- A capacidade depende da **largura de banda** e do **número de níveis de sinal**.
- É um pilar teórico essencial para o desenvolvimento das **redes de computadores modernas**.
- Para canais reais, deve ser analisado em conjunto com o **Teorema de Shannon**.
```

---
