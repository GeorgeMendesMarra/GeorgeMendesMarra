---

## 1. O Início: O Protocolo Unificado (1973–1974)

Nos primeiros dias da ARPANET, o protocolo utilizado era o NCP (Network Control Program). No entanto, ele era limitado e não lidava bem com redes diferentes interconectadas.

* **1974:** Vint Cerf e Bob Kahn publicaram o artigo *"A Protocol for Packet Network Intercommunication"*. Nele, eles descreveram o **TCP** original.
* **A Ideia:** Naquela época, o TCP fazia tudo: cuidava do endereçamento (o que hoje é o IP) e da confiabilidade da transmissão.

## 2. A Grande Cisão: TCP vs. IP (1978)

Com o tempo, os engenheiros perceberam que misturar o roteamento com o controle de transmissão tornava o sistema rígido. Danny Cohen, um pesquisador, argumentou que aplicações de tempo real (como voz) não precisavam da "perfeição" do TCP, mas sim de velocidade.

* **A Divisão:** O protocolo foi dividido em dois. O **IP** (Camada 3) ficou com a tarefa de mover os pacotes, e o **TCP** (Camada 4) ficou com a responsabilidade de verificar se houve perda de dados.
* **1981 (RFC 793):** Foi publicada a especificação definitiva do TCP que usamos até hoje.

---

## 3. O "Aperto de Mão" e a Confiabilidade

A maior inovação histórica do TCP foi o mecanismo de conexão, essencial para que a Internet fosse confiável o suficiente para transações bancárias e e-mails.

* **Three-Way Handshake (Aperto de mão de 3 vias):** Para iniciar uma conversa, o TCP realiza três passos:
1. **SYN:** "Oi, quer conversar?"
2. **SYN-ACK:** "Oi! Sim, eu quero. Você me ouve?"
3. **ACK:** "Sim, estou te ouvindo. Vamos começar."


* **Sequenciamento:** O TCP numera cada pedaço de dado. Se o pacote 3 chegar antes do 2, o TCP os organiza antes de entregar ao navegador ou aplicativo.

---

## 4. Evolução: O Controle de Congestionamento (1988)

Em 1986, a Internet sofreu o que foi chamado de "colapso de congestionamento". A rede ficou tão lenta que a velocidade caiu de 32 Kbps para 40 bps.

* **Van Jacobson:** Em 1988, ele introduziu algoritmos de **Controle de Congestionamento** no TCP (como o *Slow Start*).
* **A Lógica:** Se o TCP percebe que a rede está lenta ou perdendo pacotes, ele diminui automaticamente a velocidade de envio para não "entupir" os roteadores no caminho. Isso salvou a Internet de colapsar sob o seu próprio peso.

## 5. O TCP na Era Moderna

Embora o TCP seja extremamente robusto, ele tem desafios em redes modernas de altíssima velocidade (como o 5G) devido à sua latência inerente.

* **SACK (Selective Acknowledgment):** Uma evolução que permite ao receptor dizer exatamente quais pacotes perdeu, em vez de pedir para retransmitir tudo a partir de um ponto.
* **Concorrência do QUIC:** Recentemente, o Google e a IETF criaram o protocolo QUIC (baseado em UDP), que tenta resolver a lentidão do "aperto de mão" do TCP para carregar sites ainda mais rápido.

---

### Resumo Técnico

| Característica | Detalhes |
| --- | --- |
| **Camada OSI** | Camada 4 (Transporte) |
| **Principal RFC** | RFC 793 (1981) |
| **Função** | Entrega garantida, ordenada e sem erros |
| **Orientação** | Orientado à conexão (exige o aperto de mão) |

---

O TCP é a razão pela qual, quando você baixa um arquivo, ele não chega corrompido. Ele sacrifica um pouco de velocidade em troca de uma integridade absoluta dos dados.
