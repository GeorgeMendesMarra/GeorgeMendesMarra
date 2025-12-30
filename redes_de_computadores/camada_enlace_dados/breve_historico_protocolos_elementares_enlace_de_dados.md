---

# 📜 Protocolos Elementares de Enlace: A Fundação da Confiabilidade

A camada de enlace de dados transforma o fluxo bruto de bits da camada física em um canal de comunicação estruturado. Historicamente, o desenvolvimento desses protocolos seguiu uma escala de complexidade para resolver três problemas: **Sincronização**, **Controle de Fluxo** e **Controle de Erros**.

---

## 1. Protocolo Simplex Sem Restrições (Utopia)

Este é o modelo mais básico e teórico, assumindo um cenário perfeito.

* **Premissa:** O canal de comunicação é livre de erros e o receptor tem capacidade de processamento infinita.
* **Funcionamento:** O transmissor apenas envia dados em um loop contínuo. Não há necessidade de feedback.
* **Histórico:** Serve apenas como ponto de partida acadêmico para entender o custo de processamento nas camadas de rede.

---

## 2. Protocolo Simplex Stop-and-Wait (Pare e Espere)

O primeiro grande desafio real foi o **Controle de Fluxo**. Se o transmissor for um servidor potente e o receptor um terminal lento, o receptor será inundado.

* **A Inovação:** Introduziu o feedback. O transmissor envia um quadro e **bloqueia** sua execução.
* **Mecanismo:** Ele só retoma a transmissão após receber um pequeno quadro de confirmação (**ACK - Acknowledgement**) do receptor.
* **Limitação:** Assume que o canal ainda é perfeito (sem perda de quadros).

---

## 3. Protocolo Simplex para Canais Ruidosos (PAR)

Também conhecido como **ARQ (Automatic Repeat Request)**, este protocolo resolve a perda de dados e corrupção de bits.

* **Temporizadores (Timers):** Se o transmissor enviar um dado e o ACK não chegar em um tempo , ele assume que o quadro (ou o próprio ACK) foi perdido e retransmite.
* **Números de Sequência:** Resolve o problema da duplicidade. Se o receptor recebe o Quadro 0, envia o ACK, mas o ACK se perde, o transmissor enviará o Quadro 0 novamente. Sem números de sequência, o receptor acharia que é um dado novo. Aqui, ele percebe que já recebeu o "0" e apenas descarta a duplicata, reenviando o ACK.

---

## 📈 A Evolução da Eficiência: Janelas Deslizantes (Sliding Windows)

Os protocolos "Pare e Espere" são ineficientes em links de longa distância (como satélites), pois o transmissor passa a maior parte do tempo ocioso esperando o sinal viajar. A solução histórica foi o **Pipelining** (envio múltiplo).

### A. Go-Back-N (Retroceder N)

* **Lógica:** O transmissor pode enviar até  quadros antes de receber o primeiro ACK.
* **Desafio:** O receptor é simples e não tem buffer para quadros fora de ordem. Se o quadro 2 falhar, mas o 3, 4 e 5 chegarem, o receptor descarta o 3, 4 e 5.
* **Recuperação:** O transmissor deve voltar e retransmitir **todos** a partir do 2. É eficiente apenas quando a taxa de erro é muito baixa.

### B. Selective Repeat (Repetição Seletiva)

* **Lógica:** O receptor possui um buffer e aceita quadros fora de ordem.
* **Recuperação:** Se o quadro 2 falhar, o receptor armazena o 3, 4 e 5 e envia um NACK (Negative ACK) para o 2. O transmissor retransmite **apenas o quadro que faltou**.
* **Impacto:** É a base do funcionamento do **TCP** na internet moderna.

---

## 🛠️ O Conceito de Piggybacking (Carona)

Em sistemas bidirecionais (Full-Duplex), enviar um quadro separado apenas para o ACK consome banda valiosa.

* **Histórico:** Desenvolveu-se a técnica de **Piggybacking**, onde o campo de ACK é inserido no cabeçalho de um quadro de dados que está indo na direção oposta.
* **Vantagem:** Reduz drasticamente o overhead da rede e o número de interrupções no hardware.

---

## 📊 Tabela Comparativa de Evolução Técnica

| Protocolo | Problema Resolvido | Eficiência em Links Longos | Complexidade do Receptor |
| --- | --- | --- | --- |
| **Utopia** | Nenhum | Baixa | Nula |
| **Stop-and-Wait** | Transbordamento de Buffer | Muito Baixa | Baixa |
| **PAR (ARQ)** | Perda de Dados e Ruído | Baixa | Média |
| **Go-Back-N** | Utilização do Canal | Alta | Baixa |
| **Selective Repeat** | Utilização e Erros | Máxima | Alta (exige buffer) |

---

## 🏁 Legado

Esses protocolos elementares saíram dos livros de teoria para as implementações reais como o **HDLC (High-level Data Link Control)** e o **PPP (Point-to-Point Protocol)**, que foram fundamentais na construção da infraestrutura de rede que conecta provedores de internet e empresas até hoje.
