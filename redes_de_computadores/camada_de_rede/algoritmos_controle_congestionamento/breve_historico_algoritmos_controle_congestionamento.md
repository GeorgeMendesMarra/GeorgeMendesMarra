---

# 📉 Histórico: O Combate ao Congestionamento de Rede

O problema do congestionamento surge quando a carga oferecida à rede é maior do que a capacidade dos recursos (buffers dos roteadores e largura de banda dos links).

## 1. O Colapso de 1986 e o Surgimento do TCP Tahoe

Até meados dos anos 80, o TCP não possuía mecanismos eficazes de controle. Em 1986, a NSFNET (precursora da Internet) sofreu uma queda de performance de **32 kbps para 40 bps** devido ao congestionamento.

**Van Jacobson** salvou a rede introduzindo algoritmos baseados na "Janela de Congestionamento" ():

* **Slow Start (Partida Lenta):** Começa enviando pouco e dobra a quantidade a cada resposta (ACK).
* **Congestion Avoidance:** Quando atinge um limite, passa a crescer de forma linear.
* **Multiplicative Decrease:** Se um pacote for perdido, a janela é cortada drasticamente.

---

## 2. A Evolução Reativa: TCP Reno e NewReno (Anos 90)

O TCP Tahoe era muito agressivo ao detectar perdas (ele voltava ao início). O **TCP Reno** introduziu o:

* **Fast Recovery:** Se o receptor receber pacotes fora de ordem, ele envia "ACKs duplicados". O transmissor percebe que a rede ainda está funcionando (apenas um pacote caiu) e não volta ao início, apenas reduz a janela pela metade.

---

## 3. Controle Baseado em Atraso: TCP Vegas (1994)

Até então, todos os algoritmos esperavam o pacote "cair" (perda) para agir. O **TCP Vegas** trouxe uma ideia inovadora:

* **Proatividade:** Ele mede o tempo de ida e volta (**RTT**). Se o tempo começar a aumentar, o algoritmo entende que os buffers dos roteadores estão enchendo e diminui a velocidade *antes* que o pacote seja descartado.

---

## 4. O Domínio do TCP CUBIC (Anos 2000 - Atualidade)

Com o surgimento de links de alta velocidade (Gigabit Ethernet e Fibra), os algoritmos antigos demoravam muito para ocupar toda a banda disponível.

* **CUBIC:** Usa uma função cúbica para crescer a janela de dados. Ele sobe rápido, desacelera perto do limite anterior e volta a subir agressivamente se o canal continuar livre. É o padrão atual no **Linux, Android e Windows**.

---

## 5. A Nova Era: Google BBR (2016)

O Google percebeu que a perda de pacotes nem sempre significa congestionamento (pode ser ruído no Wi-Fi). Eles criaram o **BBR (Bottleneck Bandwidth and Round-trip propagation time)**.

* **Lógica:** Ele não olha para perdas. Ele tenta manter a rede operando no "ponto ideal": a taxa máxima de dados com o menor atraso possível. É o que torna o YouTube e a busca do Google rápidos mesmo em redes instáveis.

---

## 📊 Comparativo de Estratégias

| Algoritmo | Indicador de Crise | Comportamento | Uso Principal |
| --- | --- | --- | --- |
| **Tahoe/Reno** | Perda de Pacote | Reativo (Corta e recomeça) | Histórico |
| **CUBIC** | Perda de Pacote | Otimizado para banda larga | Linux / Internet Geral |
| **BBR** | Aumento de Latência | Proativo (Modelagem do canal) | Serviços Google / Quic |
| **ECN** | Bit de Aviso | Notificação explícita pelo roteador | Redes de Data Center |

---

## 🏁 Mecanismos de Suporte: O Papel dos Roteadores

O histórico não é feito apenas de software nas pontas. Os roteadores evoluíram com:

* **RED (Random Early Detection):** O roteador começa a descartar alguns pacotes aleatoriamente antes que o buffer lote, "avisando" os computadores para reduzirem a velocidade.
* **ECN (Explicit Congestion Notification):** Em vez de descartar o pacote, o roteador marca um bit no cabeçalho IP dizendo: "Estou ficando cheio, por favor, diminua".

---
