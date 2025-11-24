---

## Histórico da Camada de Rede no Modelo TCP/IP

### 1. **Origem e Contexto**

* A **Camada de Rede** no TCP/IP, também chamada de **Camada de Internet**, surgiu com a necessidade de **interconectar redes heterogêneas**.
* Função principal: **endereçamento lógico, roteamento e entrega de pacotes entre redes diferentes**.
* O protocolo central desta camada é o **IP (Internet Protocol)**, que garante que pacotes possam viajar de um host a outro, independentemente do meio físico ou da LAN subjacente.

---

### 2. **Final da década de 1970: ARPANET**

* Projetos iniciais de redes de pesquisa nos EUA precisavam **interligar computadores de diferentes universidades e laboratórios**.
* Primeiros protocolos de roteamento foram experimentais:

  * **NCP (Network Control Protocol)**: protocolo precursor do TCP/IP usado na ARPANET.
  * Necessidade de **endereços universais**, que mais tarde evoluíram para **endereços IP**.

---

### 3. **Início dos anos 1980: Desenvolvimento do TCP/IP**

* TCP/IP foi definido oficialmente em 1981-1983:

  * **IP (Internet Protocol)**: define endereçamento e fragmentação de pacotes.
  * **ICMP (Internet Control Message Protocol)**: mensagens de erro e diagnóstico.
  * Protocolos de roteamento foram criados: **RIP (Routing Information Protocol), OSPF (mais tarde)**.
* A camada de rede permitiu que múltiplas redes distintas funcionassem como **uma única “internet”**.

---

### 4. **Década de 1990: Expansão da Internet**

* Crescimento explosivo da Internet e aumento do número de hosts.
* Evolução dos endereços IP:

  * IPv4: 32 bits, insuficiente a longo prazo.
  * Introdução de NAT (Network Address Translation) para economizar endereços.
* Protocolos de roteamento dinâmico e hierárquico para suportar redes maiores:

  * **BGP (Border Gateway Protocol)**
  * **OSPF (Open Shortest Path First)**
  * **RIP (Routing Information Protocol)**

---

### 5. **Século XXI: IPv6 e redes modernas**

* Com a explosão de dispositivos conectados (IoT, smartphones), foi criado o **IPv6** (128 bits) para resolver a limitação de endereços.
* A camada de rede mantém sua função central: **roteamento eficiente, endereçamento lógico e entrega de pacotes entre hosts em redes heterogêneas**.
* Protocolos auxiliares evoluíram:

  * ICMPv6 (diagnóstico e mensagens de erro no IPv6)
  * Protocolos de roteamento modernos (OSPFv3, BGP para IPv6)

---

### 6. **Resumo das Funções da Camada de Rede**

| Função                         | Descrição                                                                      |
| ------------------------------ | ------------------------------------------------------------------------------ |
| **Endereçamento lógico**       | IP (IPv4/IPv6) identifica hosts na rede global                                 |
| **Roteamento**                 | Seleção de caminhos entre redes distintas                                      |
| **Fragmentação/Reassemblagem** | Pacotes podem ser divididos para atravessar redes com MTU diferente            |
| **Mensagens de controle**      | ICMP fornece diagnóstico e detecção de erros                                   |
| **Transparência**              | Camada de transporte (TCP/UDP) opera sem saber sobre o tipo de rede subjacente |

---
