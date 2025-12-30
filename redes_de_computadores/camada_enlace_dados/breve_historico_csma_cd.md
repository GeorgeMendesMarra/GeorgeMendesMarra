---

# 🛠️ CSMA/CD: O Maestro da Ethernet Legada

O **CSMA/CD** (*Carrier Sense Multiple Access with Collision Detection*) é um protocolo de acesso ao meio definido na especificação original **IEEE 802.3**. Sua função é gerenciar como os dispositivos compartilham um único canal de comunicação, garantindo que os dados cheguem ao destino mesmo quando vários computadores tentam "falar" ao mesmo tempo.

---

## 🏗️ Anatomia do Protocolo: As Três Regras

O nome do protocolo descreve exatamente os três passos que a placa de rede (NIC) executa:

### 1. Carrier Sense (Sensoriamento de Portadora)

Antes de transmitir, a placa de rede "escuta" o cabo para detectar se há um sinal elétrico presente.

* Se o cabo estiver ocupado, ela aguarda.
* Se o cabo estiver livre por um período mínimo (Interpacket Gap), ela inicia a transmissão.

### 2. Multiple Access (Acesso Múltiplo)

Vários dispositivos estão conectados ao mesmo segmento físico (barramento) e todos têm o mesmo direito de tentar acessar o meio assim que ele estiver livre.

### 3. Collision Detection (Detecção de Colisão)

Enquanto transmite, a placa continua monitorando o cabo.

* Se ela detectar um nível de voltagem acima do normal, significa que outro dispositivo também começou a transmitir.
* Uma **colisão** ocorreu, e os dados de ambos foram corrompidos.

---

## ⚠️ O Processo de Recuperação: Jam Signal e Backoff

Quando uma colisão é detectada, o protocolo não apenas para a transmissão; ele executa um procedimento de emergência para limpar o canal:

1. **Sinal de Reforço (Jam Signal):** A placa envia um sinal especial de 32 bits. Isso garante que todos os outros dispositivos na rede percebam a colisão e descartem qualquer fragmento de quadro que tenham recebido.
2. **Algoritmo de Backoff Exponencial Binário:** Para evitar que os mesmos computadores colidam novamente ao tentar retransmitir, cada um escolhe um tempo de espera aleatório.
* O tempo de espera é calculado pela fórmula , onde  é um número aleatório entre  e  (sendo  o número de colisões consecutivas).
* Quanto mais colisões ocorrem, maior fica o intervalo potencial de espera, reduzindo o congestionamento.



---

## 📉 Domínios de Colisão e a Transição para Switches

Historicamente, o desempenho do CSMA/CD era inversamente proporcional ao número de usuários.

* **Redes com Hubs:** Todos os dispositivos pertenciam ao mesmo **Domínio de Colisão**. Se muitos computadores tentassem transmitir, o número de colisões aumentava exponencialmente, "derrubando" a performance da rede.
* **Redes com Switches (Microsegmentação):** Os switches criam caminhos dedicados para cada porta. Isso isola o tráfego.
* **Full-Duplex:** Com o switch, a placa pode enviar e receber em fios separados simultaneamente. Como não há mais disputa pelo mesmo fio, **o CSMA/CD é desativado**, permitindo velocidades de 1 Gbps, 10 Gbps e superiores.

---

## 📊 Comparativo: CSMA/CD vs. CSMA/CA

| Funcionalidade | CSMA/CD (Ethernet) | CSMA/CA (Wi-Fi) |
| --- | --- | --- |
| **Ação na Colisão** | Detecta e interrompe. | Tenta evitar (Prevenção). |
| **Feedback** | Baseado na voltagem elétrica. | Baseado em quadros de confirmação (ACK). |
| **Eficiência** | Muito alta em meios cabeados. | Menor devido ao alto tempo de espera. |
| **Uso Atual** | Apenas para compatibilidade (Legacy). | Essencial para o funcionamento do Wi-Fi. |

---

## 🏁 Legado

Embora raramente "trabalhe" nas redes modernas de fibra e par trançado com switches, o CSMA/CD é o motivo pelo qual a Ethernet conseguiu escalar de 2.94 Mbps para 100 Mbps de forma barata. Ele permitiu que a inteligência da rede ficasse nas extremidades (nas placas de rede), mantendo o cabeamento simples e passivo.
