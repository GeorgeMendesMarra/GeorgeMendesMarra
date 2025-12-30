---

# ⚡ Fast Ethernet: A Evolução para os 100 Mbps

O padrão **Fast Ethernet** foi um divisor de águas na computação. Ratificado em **1995** sob a especificação **IEEE 802.3u**, ele aumentou a velocidade de transmissão das redes locais de 10 Mbps para **100 Mbps**, mantendo a estrutura básica da Ethernet original.

---

## 🏗️ O Cenário de Disputa: Fast Ethernet vs. 100VG-AnyLAN

Nos anos 90, ficou claro que os 10 Mbps da Ethernet tradicional (10Base-T) não suportariam o futuro. Dois grupos propuseram soluções diferentes:

1. **O Grupo 100VG-AnyLAN (IEEE 802.12):** Liderado pela HP e IBM, propunha um método chamado *Demand Priority*. Ele eliminava as colisões de dados, mas exigia a troca de toda a infraestrutura e não era baseado no protocolo Ethernet clássico.
2. **O Grupo Fast Ethernet (IEEE 802.3u):** Liderado pela Grand Junction e 3Com, defendia manter o protocolo **CSMA/CD** original. A ideia era: "é a mesma Ethernet, só que 10 vezes mais rápida".

**Resultado:** O Fast Ethernet venceu devido ao menor custo de implementação e à familiaridade dos administradores de rede com a tecnologia anterior.

---

## 🛠️ Inovações Técnicas Principais

O salto de 10 para 100 Mbps não foi apenas uma mudança de velocidade; exigiu adaptações no tempo de resposta e no hardware.

### 1. Autonegociação (NWay)

Uma das maiores heranças do Fast Ethernet foi a introdução da **Autonegociação**. Isso permitiu que placas de rede e switches conversassem entre si para decidir a melhor velocidade (10 ou 100 Mbps) e o modo de transmissão (Half ou Full Duplex) automaticamente.

### 2. Mudança no Tempo de Slot

No CSMA/CD, para detectar colisões, o transmissor deve continuar enviando dados até que o sinal percorra todo o cabo e volte. Como o Fast Ethernet envia dados 10 vezes mais rápido, o "tempo de bit" encolheu. Para compensar, o limite máximo de distância entre dispositivos em uma rede com Hubs caiu drasticamente (de 2500m na Ethernet original para apenas **205 metros** no Fast Ethernet).

---

## 📋 Variantes do Padrão 802.3u

Para atender diferentes infraestruturas de cabos existentes na época, o Fast Ethernet foi dividido em sub-padrões:

| Sub-padrão | Meio de Transmissão | Distância Máxima | Descrição Técnica |
| --- | --- | --- | --- |
| **100Base-TX** | Cabo Par Trançado (Cat 5 ou superior) | 100 metros | Usa 2 pares de fios. Tornou-se o padrão universal para redes domésticas e empresariais. |
| **100Base-T4** | Cabo Par Trançado (Cat 3) | 100 metros | Desenvolvido para rodar em cabos telefônicos antigos, usando todos os 4 pares de fios. |
| **100Base-FX** | Fibra Óptica Multi-modo | 2 km (Full Duplex) | Usado para interligar prédios ou ambientes com muita interferência eletromagnética. |

---

## 🔄 A Transição de Hubs para Switches

O Fast Ethernet coincidiu com a popularização dos **Switches**. Diferente dos Hubs, que replicam o sinal para todas as portas, o Switch cria um caminho dedicado entre a origem e o destino.

* **Half-Duplex:** O dispositivo ou envia ou recebe (como um Walkie-Talkie). Comum em redes com Hubs.
* **Full-Duplex:** O dispositivo envia e recebe simultaneamente (como um telefone). Isso tornou o Fast Ethernet capaz de entregar **200 Mbps** de taxa agregada.

---

## 🏅 Legado e Uso Atual

Embora o Gigabit (1000 Mbps) e o 10G tenham assumido o controle dos servidores e backbones, o Fast Ethernet ainda é o "trabalhador silencioso":

* **Custo-benefício:** Chips 10/100 são extremamente baratos.
* **IoT e Automação:** Dispositivos como lâmpadas inteligentes, termostatos e câmeras de segurança não precisam de mais de 100 Mbps.
* **Eficiência Energética:** O Fast Ethernet consome menos energia que o Gigabit, sendo ideal para dispositivos alimentados por bateria ou sensores simples.

---

**Deseja que eu aprofunde na pinagem dos cabos (diferença entre cabo direto e cruzado) ou gostaria de avançar para a história do Gigabit Ethernet?**
