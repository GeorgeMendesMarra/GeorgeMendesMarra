---

# 🛣️ Algoritmos de Roteamento: O Cérebro da Camada de Rede

Os algoritmos de roteamento são responsáveis pela principal função da Camada de Rede: determinar o caminho que um pacote deve seguir da origem ao destino através de uma topologia de rede complexa. Historicamente, essa evolução saiu de tabelas estáticas e manuais para sistemas inteligentes e autônomos.

---

## 🏗️ 1. O Início: Inundação e Roteamento Estático

Nos primórdios das redes, como a ARPANET, as opções eram limitadas pela baixa capacidade de processamento dos roteadores (chamados na época de IMPs).

* **Roteamento Estático:** As rotas eram inseridas manualmente por administradores. Embora simples e seguro contra oscilações, era incapaz de reagir a falhas de links ou congestionamentos.
* **Inundação (Flooding):** Cada pacote recebido era replicado e enviado para todas as interfaces, exceto aquela por onde chegou.
* *Vantagem:* Robustez extrema (se houver um caminho, o pacote chegará).
* *Desvantagem:* Desperdício massivo de largura de banda e recursos de processamento.



---

## 📉 2. Vetor de Distância (Distance Vector)

Este foi o primeiro grande salto para o roteamento dinâmico, baseado no trabalho de **Bellman-Ford**.

* **Lógica:** Cada roteador mantém uma tabela informando a distância mínima até cada destino conhecido. Periodicamente, ele envia sua tabela **apenas para seus vizinhos diretos**.
* **Protocolo Famoso:** **RIP (Routing Information Protocol)**.
* **O Problema da Convergência:** O algoritmo sofre do problema de "boas notícias viajam rápido, más notícias viajam devagar". Quando um link cai, pode ocorrer a **Contagem ao Infinito**, onde roteadores ficam trocando informações obsoletas e incrementando a distância infinitamente.

---

## 🗺️ 3. Estado de Link (Link State)

Para resolver a lentidão de convergência do Vetor de Distância, o cientista **Edsger Dijkstra** propôs uma abordagem onde cada nó tem uma visão global da rede.

* **Lógica:** Em vez de compartilhar a tabela de rotas, o roteador compartilha o estado de seus links (quem são seus vizinhos e qual o custo do link). Essa informação é espalhada por toda a rede (LSA - Link State Advertisements).
* **O Algoritmo de Dijkstra:** Com o banco de dados completo da topologia, cada roteador executa o algoritmo **SPF (Shortest Path First)** localmente para construir uma árvore de caminhos mais curtos.
* **Protocolos Famosos:** **OSPF (Open Shortest Path First)** e **IS-IS**.

---

## 🌍 4. Roteamento Hierárquico e Vetor de Caminho (BGP)

Com o crescimento da Internet, tornou-se impossível para um roteador conhecer todos os caminhos de todos os dispositivos do mundo. A solução foi a divisão em **Sistemas Autônomos (AS)**.

* **Interior Gateway Protocols (IGP):** Algoritmos como OSPF ou RIP que rodam dentro de uma única organização.
* **Exterior Gateway Protocols (EGP):** Algoritmos que conectam diferentes AS. O padrão mundial é o **BGP (Border Gateway Protocol)**.
* **Vetor de Caminho (Path Vector):** O BGP não olha apenas para a distância. Ele registra a lista completa de AS pelos quais o pacote passará. Isso evita loops e permite o **Roteamento Baseado em Políticas** (decidir caminhos por razões comerciais ou de segurança, e não apenas velocidade).

---

## 📊 Comparativo Técnico de Evolução

| Categoria | Algoritmo Base | Visão da Rede | Velocidade de Convergência | Exemplo de Protocolo |
| --- | --- | --- | --- | --- |
| **Vetor de Distância** | Bellman-Ford | Apenas vizinhos | Lenta | RIP, IGRP |
| **Estado de Link** | Dijkstra | Mapa Global | Muito Rápida | OSPF, IS-IS |
| **Vetor de Caminho** | Customizado | Lista de AS | Moderada | BGP |
| **Híbrido** | DUAL | Vizinhos (com memória) | Muito Rápida | EIGRP (Cisco) |

---

## 🏁 O Futuro: SDN e Segment Routing

Atualmente, o roteamento está se movendo para fora do hardware individual.

* **SDN (Software Defined Networking):** O plano de controle é centralizado em um software que dita as rotas para todos os switches da rede, permitindo uma gestão muito mais ágil.
* **Roteamento Baseado em Intenção:** A rede ajusta os algoritmos automaticamente para priorizar aplicações específicas (como baixa latência para jogos ou alta vazão para backups).

---
