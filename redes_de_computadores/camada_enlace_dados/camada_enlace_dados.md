## Histórico da Camada de Enlace de Dados no Modelo TCP/IP

### 1. **Origem e Contexto**

* No modelo **TCP/IP**, a camada de enlace de dados é parte da **Camada de Acesso à Rede** (ou Network Interface Layer), que combina funções de **enlace e física**.
* Sua função principal é **garantir a transmissão confiável de quadros (frames) entre nós diretamente conectados**, controlando erros, fluxo e endereçamento físico (MAC).

---

### 2. **Década de 1960-1970: Redes iniciais**

* Redes como **ARPANET** utilizavam links ponto a ponto com protocolos proprietários.
* A transmissão era confiável de forma simples: o hardware controlava a passagem de bits, mas não havia padronização de **endereçamento ou detecção de erros**.
* Protocolos de enlace eram incorporados ao hardware, limitando a interoperabilidade.

---

### 3. **Década de 1980: Padrões e protocolos de enlace**

* Com a expansão das LANs, surgiram padrões de enlace independentes da camada física:

  * **Ethernet (IEEE 802.3)**: usa CSMA/CD, quadros padronizados, endereçamento MAC.
  * **Token Ring (IEEE 802.5)**: controle de acesso por token, prevenção de colisões.
  * **FDDI**: anel de fibra óptica com detecção de erros e redundância.
* A camada de enlace passou a **incluir controle de acesso ao meio (MAC), detecção de erros (CRC) e formatação de quadros**.

---

### 4. **Década de 1990: Expansão da Internet**

* Redes TCP/IP começaram a operar em múltiplos tipos de LAN e WAN:

  * **Ethernet, Token Ring, FDDI** para LANs.
  * **PPP (Point-to-Point Protocol)** para conexões ponto a ponto em redes WAN.
* Introdução de **ARP (Address Resolution Protocol)**: mapeamento de endereços IP para MAC.

---

### 5. **Século XXI: Redes modernas**

* A camada de enlace evoluiu com o aumento da velocidade e diversidade de redes:

  * **Gigabit e 10 Gigabit Ethernet**
  * **Wi-Fi (IEEE 802.11)** com controle de acesso sem fio e retransmissão
  * **VLANs e switches** que segmentam e gerenciam a rede na camada de enlace
* Continua responsável por **endereçamento físico, detecção e correção de erros locais**, enquanto a camada IP cuida do roteamento global.

---

### 6. **Resumo das Funções da Camada de Enlace de Dados**

| Função                         | Descrição                                          |
| ------------------------------ | -------------------------------------------------- |
| **Endereçamento físico**       | Identificação dos dispositivos na mesma rede (MAC) |
| **Detecção de erros**          | CRC, checksum ou técnicas similares                |
| **Controle de acesso ao meio** | CSMA/CD, token, TDMA, Wi-Fi backoff                |
| **Formatação de quadros**      | Organização dos dados em pacotes transmissíveis    |
| **Transparência para IP**      | IP/TCP/UDP operam sem depender do tipo de enlace   |

---
