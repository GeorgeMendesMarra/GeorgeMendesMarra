## 1. O Problema da Identidade (Início dos anos 80)

Nos primórdios da rede, os computadores já conseguiam se comunicar via **Internet Protocol (IP)**, mas havia um problema prático: as placas de rede Ethernet não entendem endereços IP (como `192.168.1.1`). Elas só entendem endereços físicos gravados na fábrica, os **MAC Addresses** (como `00:0A:95:9D:68:16`).

Era necessário um protocolo que perguntasse na rede: *"Quem tem o IP X? Por favor, me diga o seu endereço físico"*.

## 2. A Padronização (1982)

O ARP foi formalizado em **novembro de 1982** através do **RFC 826**, escrito por **David C. Plummer**.

Diferente de muitos protocolos que sofreram várias revisões, a especificação original do ARP foi tão bem desenhada para a época que permanece praticamente inalterada para redes IPv4 até hoje. Ele foi projetado para ser genérico, podendo teoricamente funcionar com outros protocolos de rede além do IP, embora tenha se tornado sinônimo de resolução de endereços IP.

## 3. Como o ARP moldou o funcionamento das redes

O ARP introduziu conceitos fundamentais para a eficiência das redes locais:

* **ARP Cache:** Para evitar perguntar o endereço físico a cada pacote enviado, os computadores armazenam as respostas em uma tabela temporária (cache). Isso reduz drasticamente o tráfego na rede.
* **Mensagens de Broadcast:** O ARP utiliza pacotes de "difusão" (broadcast) para gritar para todos os computadores da rede local quando precisa de uma informação.
* **Gratuitous ARP:** Um computador pode anunciar seu próprio endereço na rede sem ser solicitado. Isso é usado para detectar conflitos de IP (quando dois PCs tentam usar o mesmo endereço).

## 4. Evolução e Variantes

Ao longo dos anos, o ARP ganhou "primos" para funções específicas:

* **RARP (Reverse ARP):** Usado antigamente por estações de trabalho sem disco para descobrir seu próprio IP a partir do seu endereço físico.
* **Proxy ARP:** Técnica onde um roteador responde a uma solicitação ARP em nome de outro dispositivo, ajudando a unir redes diferentes.

## 5. O Declínio e a Substituição pelo NDP (IPv6)

Apesar de sua importância, o ARP possui uma falha crítica de segurança: ele é **inerentemente inseguro**. Ele confia em qualquer resposta que recebe (o que permite ataques de *ARP Spoofing* ou "envenenamento de cache").

Por isso, na transição para o **IPv6**, o ARP foi oficialmente aposentado. Suas funções foram integradas ao **ICMPv6** através do protocolo **NDP (Neighbor Discovery Protocol)**, que é mais seguro, eficiente e não utiliza o barulhento tráfego de broadcast.

---

### Resumo Técnico

| Característica | Detalhes |
| --- | --- |
| **Ano de Criação** | 1982 |
| **RFC Principal** | RFC 826 |
| **Camada OSI** | Camada 2 (Enlace) / Camada 3 (Rede) |
| **Função** | Mapear IP (Lógico) para MAC (Físico) |
| **Protocolo Sucessor** | NDP (no IPv6) |

---
