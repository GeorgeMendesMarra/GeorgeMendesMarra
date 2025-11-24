---

## Histórico da Camada de Transporte no Modelo TCP/IP

### 1. **Origem e Contexto**

* A **Camada de Transporte** surgiu para **fornecer comunicação confiável entre processos em máquinas diferentes**.
* Sua função é **transportar dados de uma aplicação para outra**, garantindo entrega correta, ordenada e, em alguns casos, confiável.
* Protocolos centrais:

  * **TCP (Transmission Control Protocol)** – orientado à conexão, confiável
  * **UDP (User Datagram Protocol)** – sem conexão, não confiável, rápido

---

### 2. **Final da década de 1970: Experimentos iniciais**

* Durante a ARPANET, pesquisadores perceberam a necessidade de **isolar a transmissão de dados das aplicações**.
* Surgem os **protocolos NCP (Network Control Protocol)**, que ofereciam funcionalidades de transporte, mas eram limitados.
* Experimentos mostraram a necessidade de **multiplexação e confiabilidade**, levando ao desenvolvimento do TCP.

---

### 3. **Início dos anos 1980: Definição do TCP/IP**

* **RFC 675 (1974)**: primeiras definições do TCP.
* **RFC 793 (1981)**: TCP formalizado:

  * Conexão confiável de ponta a ponta
  * Controle de fluxo e congestionamento
  * Sequenciamento de segmentos
* **UDP definido em RFC 768 (1980)**:

  * Protocolo simples, sem conexão
  * Ideal para aplicações que priorizam velocidade (voz, vídeo, DNS)

---

### 4. **Década de 1980-1990: Consolidação**

* TCP/IP substituiu NCP na ARPANET (1983), estabelecendo o **modelo de transporte moderno**.
* TCP tornou-se padrão para protocolos orientados a conexão: FTP, Telnet, SMTP.
* UDP foi usado para aplicações em tempo real e broadcast: DNS, SNMP, streaming.

---

### 5. **Década de 1990-2000: Expansão da Internet**

* Crescimento da Internet aumentou a necessidade de **controle de congestionamento e eficiência**:

  * **Algoritmos de TCP**: Slow Start, Congestion Avoidance (RFC 2001, RFC 2581)
  * UDP permaneceu essencial para **aplicações multimídia e jogos online**.
* Camada de transporte tornou-se **independente da camada de rede**, funcionando em qualquer rede TCP/IP.

---

### 6. **Século XXI: Transporte moderno**

* Surgem protocolos alternativos e melhorias:

  * **SCTP (Stream Control Transmission Protocol)**: confiável, múltiplos fluxos
  * **QUIC (UDP + TLS/HTTP3)**: transporte rápido e seguro sobre UDP
* TCP e UDP continuam **dominando a comunicação na Internet**, sendo a base para praticamente todos os serviços online.

---

### 7. **Resumo das Funções da Camada de Transporte**

| Função                            | Descrição                                                             |
| --------------------------------- | --------------------------------------------------------------------- |
| **Multiplexação**                 | Diferencia processos usando portas TCP/UDP                            |
| **Entrega confiável**             | TCP garante entrega ordenada e sem perdas                             |
| **Controle de fluxo**             | TCP evita sobrecarregar o receptor                                    |
| **Controle de congestionamento**  | Ajusta envio de dados conforme a rede                                 |
| **Transparência para aplicações** | Aplicações não precisam se preocupar com roteamento ou falhas na rede |

---
