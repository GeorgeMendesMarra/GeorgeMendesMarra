---

# 🌐 Protocolos de Enlace de Dados: Exemplos e Aplicações

A camada de enlace é responsável por organizar a transmissão de dados "ponto a ponto" ou em meios compartilhados. Ela é dividida em duas subcamadas: **LLC** (Logical Link Control), que faz a interface com o software, e **MAC** (Media Access Control), que lida com o hardware.

---

## 1. Ethernet (IEEE 802.3)

O protocolo de rede local (LAN) mais bem-sucedido da história. Ele evoluiu de um barramento simples com colisões para uma infraestrutura de alta velocidade baseada em switches.

* **Arquitetura:** Baseada em quadros (frames) que utilizam o **Endereço MAC** (48 bits) para identificar origem e destino.
* **Mecanismo:** Em redes modernas, opera em **Full-Duplex**, eliminando colisões.
* **Uso:** Praticamente toda infraestrutura de rede cabeada no mundo (residencial, empresarial e data centers).

---

## 2. Wi-Fi (IEEE 802.11)

Embora compartilhe o formato de endereçamento com a Ethernet, o protocolo de enlace do Wi-Fi é muito mais robusto para lidar com o "caos" das transmissões via rádio.

* **Diferencial:** Utiliza o **CSMA/CA** (Collision Avoidance). Como o rádio não detecta colisões facilmente, ele tenta evitá-las e exige um quadro de confirmação (**ACK**) para cada pacote enviado.
* **Segurança:** Implementa criptografia diretamente na camada de enlace (WPA2/WPA3).
* **Uso:** Dispositivos móveis, automação residencial e redes sem fio em geral.

---

## 3. PPP (Point-to-Point Protocol)

Um protocolo fundamental para conexões diretas entre dois nós, sem a necessidade de endereçamento MAC complexo.

* **Estrutura:** Utiliza enquadramento para delimitar onde começa e termina um pacote sobre linhas seriais.
* **Funcionalidades:** Suporta autenticação (PAP/CHAP), compressão de cabeçalho e configuração dinâmica de IPs.
* **Variante PPPoE:** O *PPP over Ethernet* é amplamente usado por provedores de internet (ISPs) para autenticar usuários residenciais sobre infraestrutura Ethernet ou Fibra.

---

## 4. Bluetooth (IEEE 802.15.1)

Um protocolo de enlace focado em **WPAN** (Redes de Área Pessoal) com baixa potência.

* **Frequência:** Opera em 2.4 GHz com **Adaptive Frequency Hopping (AFH)**, saltando de canal 1.600 vezes por segundo para evitar interferências.
* **Modos:** Suporta tanto o Bluetooth Classic quanto o BLE (Low Energy).
* **Uso:** Periféricos (mouses, teclados) e dispositivos vestíveis (wearables).

---

## 5. HDLC (High-Level Data Link Control)

Um protocolo de enlace síncrono que serviu de base para muitos outros (incluindo o PPP e o LLC da Ethernet).

* **Controle:** Implementa janelas deslizantes (**Sliding Windows**) para controle de fluxo e erro.
* **Bit-Stuffing:** Utiliza uma técnica de inserção de bits para garantir que a sequência de sincronização (01111110) não apareça dentro dos dados, evitando erros de leitura.
* **Uso:** Links de longa distância (WAN) em roteadores de borda.

---

## 6. ARP (Address Resolution Protocol)

Embora frequentemente discutido entre as camadas 2 e 3, o ARP é o "protocolo de cola" essencial para o funcionamento do enlace na suíte TCP/IP.

* **Função:** Traduz um endereço lógico (IP) em um endereço físico (MAC).
* **Processo:** Quando um computador sabe o IP de destino, mas não o MAC, ele envia um **ARP Request** em broadcast. O dono do IP responde com seu endereço MAC.

---

## 📊 Comparativo de Características Técnicas

| Protocolo | Topologia Típica | Endereçamento | Controle de Erro |
| --- | --- | --- | --- |
| **Ethernet** | Estrela (Switch) | MAC (48 bits) | CRC-32 (FCS) |
| **Wi-Fi** | Estrela (AP) | MAC (48 bits) | CRC-32 + ACKs |
| **PPP** | Ponto-a-Ponto | Nulo/Fixo | CRC / Checksum |
| **Bluetooth** | Estrela / Mesh | BD_ADDR (48 bits) | FEC + CRC |
| **HDLC** | Ponto-a-Ponto | Endereço de Nó | CRC |

---

## 🏁 Protocolos de Enlace Legados (Histórico)

Para fins de estudo, vale mencionar protocolos que foram pilares da internet, mas hoje são raros:

* **Frame Relay:** Comutação de pacotes de baixo custo para WANs.
* **ATM (Asynchronous Transfer Mode):** Usava células fixas de 53 bytes para garantir latência baixa em voz e vídeo.
* **Token Ring:** Protocolo da IBM onde um "token" circulava no anel; apenas quem tinha o token podia falar.

---
