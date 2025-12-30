# 📶 Qualidade de Serviço (QoS): A Engenharia da Priorização

A **QoS** é o conjunto de tecnologias que permite à rede gerenciar o tráfego de dados para garantir o desempenho de aplicações críticas. Sem QoS, a internet operaria apenas no modelo *Best-Effort*, o que tornaria serviços como telemedicina ou jogos online inviáveis devido à instabilidade do meio.

---

## 1. O Problema: Latência, Jitter e Perda

Para entender a QoS, precisamos entender o que ela tenta combater:

* **Latência (Atraso):** O tempo total para um pacote ir da origem ao destino.
* **Jitter (Variação do Atraso):** A variação no tempo de chegada dos pacotes. É o que causa "travamentos" em chamadas de vídeo.
* **Packet Loss (Perda de Pacotes):** Ocorre quando os buffers dos roteadores lotam e novos pacotes são descartados.

---

## 2. IntServ e o Protocolo RSVP (A Abordagem Determinística)

Na década de 90, acreditava-se que a solução seria a reserva rigorosa de banda.

* **Lógica:** Antes de iniciar um fluxo (como uma chamada de vídeo), o dispositivo envia uma mensagem **RSVP** (*Resource Reservation Protocol*) ao longo do caminho.
* **Estado por Fluxo:** Cada roteador deve lembrar da reserva e garantir os bits por segundo solicitados.
* **O Declínio:** Em redes globais, um roteador de núcleo (*core*) teria que gerenciar milhões de reservas simultâneas, o que causava o esgotamento de sua memória e processamento.

---

## 3. DiffServ e a Marcação de Pacotes (A Abordagem Estatística)

O modelo **DiffServ** (Serviços Diferenciados) surgiu para ser escalável. Em vez de reservas, ele trabalha com "etiquetas".

* **Classificação e Marcação:** Na borda da rede, o tráfego é identificado. Um pacote de voz recebe uma marcação no campo **DSCP** (*Differentiated Services Code Point*) do cabeçalho IP.
* **PHB (Per-Hop Behavior):** Cada roteador olha para a etiqueta e decide como tratar o pacote.
* **EF (Expedited Forwarding):** Prioridade máxima (Voz).
* **AF (Assured Forwarding):** Entrega garantida, mas com menos prioridade que voz.
* **BE (Best Effort):** O "resto" do tráfego (E-mail, Web).



---

## 4. Algoritmos de Gerenciamento de Filas (O Coração da QoS)

Quando o tráfego chega ao roteador, ele deve ser organizado em filas antes de ser enviado. A evolução desses algoritmos é o que define a eficiência da QoS:

### Weighted Fair Queuing (WFQ)

Divide a largura de banda proporcionalmente. Se houver tráfego de vídeo e web, o algoritmo garante, por exemplo, 70% para vídeo e 30% para web, impedindo que um fluxo "atropele" o outro.

### Class-Based Weighted Fair Queuing (CBWFQ)

Permite que o administrador crie classes específicas (ex: "Classe Financeira", "Classe Entretenimento") e atribua garantias mínimas de banda para cada uma.

### Low Latency Queuing (LLQ)

É a combinação do CBWFQ com uma fila de prioridade estrita. Essencial para Voz sobre IP (VoIP), pois garante que os pacotes de áudio sempre "furem a fila" de qualquer outro dado.

---

## 5. Modelagem e Policiamento de Tráfego

* **Traffic Policing (Policiamento):** Quando o tráfego excede o limite contratado, o roteador simplesmente descarta o excedente. É comum em planos de internet de banda larga.
* **Traffic Shaping (Modelagem):** Em vez de descartar, o roteador armazena o excesso em um buffer e o envia gradualmente, criando um fluxo de saída constante e "suave".

---

## 📊 Comparativo: IntServ vs. DiffServ

| Característica | IntServ (Serviços Integrados) | DiffServ (Serviços Diferenciados) |
| --- | --- | --- |
| **Abordagem** | Reserva de recursos fim-a-fim. | Priorização baseada em classes (etiquetas). |
| **Escalabilidade** | Baixa (difícil em grandes redes). | Alta (padrão da Internet atual). |
| **Protocolo Chave** | RSVP. | DSCP (Cabeçalho IP). |
| **Garantia** | Determinística (Garantia absoluta). | Estatística (Alta probabilidade). |

---

## 🏁 O Estado da Arte: SDN e 5G

Hoje, a QoS é gerida por **SDN (Software Defined Networking)**, onde um software central ajusta as prioridades da rede em tempo real com base na demanda. No **5G**, isso atinge o ápice com o **Network Slicing**, onde a QoS é aplicada a "fatias" inteiras da infraestrutura física, garantindo que uma cirurgia robótica remota nunca compita pela mesma banda que um download de celular.

---
