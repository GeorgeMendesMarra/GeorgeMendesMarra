---

# 🛡️ Subcamada MAC: O Coração Operacional da Ethernet

A padronização do **IEEE 802** dividiu a Camada de Enlace em duas partes para permitir que um único protocolo de software (LLC) pudesse rodar sobre diferentes tipos de hardware (Ethernet, Wi-Fi, Token Ring). O MAC é a interface que lida diretamente com o sinal elétrico ou óptico.

---

## 🏗️ Evolução Histórica: Do Caos à Ordem

### 1. A Herança do ALOHA e o CSMA/CD

A Ethernet original operava em um **barramento compartilhado** (um único cabo coaxial onde todos os computadores estavam pendurados). Sem a subcamada MAC, todos falariam ao mesmo tempo, resultando em ruído.

* **O Algoritmo Backoff:** Quando o MAC detectava uma colisão, ele não apenas parava; ele executava o *Binary Exponential Backoff*. O dispositivo escolhia um número aleatório  e esperava  slots de tempo. Se colidisse de novo, o intervalo de escolha dobrava. Isso evitava que dois computadores entrassem em um ciclo infinito de colisões.

### 2. A Transição para Switches (Microsegmentação)

Com a chegada dos switches nos anos 90, a função principal do MAC mudou. O meio não era mais compartilhado.

* **Domínio de Colisão Unitário:** Cada porta do switch tornou-se um domínio de colisão próprio.
* **Full-Duplex:** O MAC passou a permitir transmissão e recepção simultâneas. O mecanismo de detecção de colisão (CD) tornou-se obsoleto, embora ainda esteja presente no código por questões de compatibilidade.

---

## 🛠️ Anatomia do Quadro Ethernet (Framing)

O MAC é responsável por encapsular os dados vindos da camada de rede (IP) em um **Quadro (Frame)**. Um quadro Ethernet padrão tem a seguinte estrutura:

1. **Preâmbulo (7 bytes):** Uma sequência de 0s e 1s para sincronizar o relógio do receptor.
2. **Delimitador de Início (SFD - 1 byte):** Indica que os dados reais começam agora.
3. **Endereço MAC de Destino (6 bytes):** Para quem o pacote vai.
4. **Endereço MAC de Origem (6 bytes):** Quem enviou o pacote.
5. **Tipo/Comprimento (2 bytes):** Indica qual protocolo está dentro (ex: IPv4 ou IPv6).
6. **Dados (Payload):** Onde vai o pacote IP (46 a 1500 bytes).
7. **FCS (Frame Check Sequence - 4 bytes):** O rodapé de verificação de erros.

---

## 🔍 O Endereço MAC em Detalhes

O endereço MAC é um identificador de 48 bits, geralmente escrito em hexadecimal (ex: `00:1A:2B:3C:4D:5E`).

* **OUI (Organizationally Unique Identifier):** Os primeiros 24 bits identificam o fabricante da placa (Intel, Realtek, Apple, etc.). O IEEE gerencia esses códigos.
* **NIC Specific:** Os últimos 24 bits são o "número de série" atribuído pelo fabricante.

### Funções de Filtragem:

O MAC opera em três modos de endereçamento:

* **Unicast:** Direcionado a uma única placa.
* **Multicast:** Direcionado a um grupo específico (ex: switches trocando informações).
* **Broadcast:** Direcionado a todos na rede (`FF:FF:FF:FF:FF:FF`).

---

## 🛡️ Integridade: O Cálculo de CRC

Uma das funções mais vitais do MAC é garantir que os dados não foram corrompidos por ruído elétrico.

* Antes de enviar, o transmissor aplica um algoritmo matemático (CRC-32) sobre o quadro e coloca o resultado no campo **FCS**.
* O receptor faz o mesmo cálculo. Se o resultado for diferente de 1 bit sequer, o MAC **descarta o quadro silenciosamente**. Ele não pede retransmissão; essa tarefa (se necessária) fica para as camadas superiores como o TCP.

---

## 📊 Comparativo Técnico: Evolução do Papel do MAC

| Era | Papel Principal | Tecnologia Chave |
| --- | --- | --- |
| **Anos 80 (Legado)** | Gestão de Colisões | CSMA/CD em Cabo Coaxial |
| **Anos 90 (Transição)** | Endereçamento e Comutação | Introdução de Switches e Bridge Tables |
| **Anos 2000+ (Moderna)** | Integridade e QoS | Full-Duplex e VLAN Tagging (802.1Q) |

---

## 🏁 O MAC Além da Ethernet

É importante notar que o histórico da subcamada MAC se estende ao **Wi-Fi (802.11)**. No rádio, o MAC usa o **CSMA/CA** (Collision Avoidance). Como não é possível detectar colisões no ar de forma eficiente, o MAC do Wi-Fi "pede permissão" antes de enviar e espera um "Aviso de Recebimento" (ACK).
