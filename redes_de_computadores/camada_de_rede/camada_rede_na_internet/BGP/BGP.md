O **BGP (Border Gateway Protocol)** é frequentemente chamado de "o protocolo que mantém a internet unida". Enquanto o OSPF cuida do tráfego dentro de uma empresa, o BGP é o responsável por decidir quais caminhos os dados devem percorrer entre diferentes países, continentes e provedores.

Abaixo, apresento a evolução histórica e técnica deste protocolo vital:

---

## 1. O Problema do Crescimento: Da EGP para o BGP

Nos anos 80, a internet (ainda ARPANET) utilizava um protocolo chamado **EGP (Exterior Gateway Protocol)**. O EGP tinha uma estrutura hierárquica rígida: ele assumia que a internet tinha um "núcleo" central e que todas as outras redes eram apenas ramificações.

Com a privatização da rede e o surgimento de múltiplos provedores independentes, essa estrutura quebrou. Era necessário um protocolo que permitisse uma malha descentralizada de conexões.

## 2. A "Ata do Guardanapo" (1989)

O BGP nasceu de forma lendária. Durante uma conferência do IETF em 1989, dois engenheiros, **Kirk Lougheed** (Cisco) e **Yakov Rekhter** (IBM), rascunharam a primeira versão do protocolo no verso de três guardanapos de papel manchados de ketchup.

Esse rascunho tornou-se o **BGP-1 (RFC 1105)**. Diferente de tudo o que existia, o BGP foi desenhado como um protocolo de **Vetor de Caminho (Path Vector)**.

## 3. As Versões e o Salto para a Modernidade

O protocolo evoluiu rapidamente para acompanhar o crescimento explosivo da rede mundial:

* **BGP-2 (1990) e BGP-3 (1991):** Introduziram melhorias na forma como o protocolo lidava com as atualizações de rotas, mas ainda sofriam com a falta de suporte para redes de tamanhos variáveis.
* **BGP-4 (1994 - RFC 1654):** Esta foi a versão revolucionária. Ela introduziu o suporte ao **CIDR (Classless Inter-Domain Routing)**. Sem o BGP-4, a tabela de roteamento da internet teria ficado tão grande que os roteadores da época teriam "travado" por falta de memória.
* **BGP-4 Multiprotocolo (MBGP):** Mais tarde, o protocolo foi estendido para suportar não apenas IPv4, mas também **IPv6**, MPLS e VPNs, tornando-se a ferramenta versátil que é hoje.

---

## 4. O Conceito de Sistemas Autônomos (AS)

A grande inovação do BGP foi tratar a internet não como uma rede de roteadores, mas como uma rede de **Sistemas Autônomos (AS)**.

* Cada grande entidade (Google, Netflix, operadoras de telefonia) possui um número de identificação único (ASN).
* O BGP não olha para a "distância" em quilômetros ou saltos de roteadores, mas sim para quantos **ASs** um pacote precisa atravessar.

## 5. Política acima da Tecnologia

Diferente do OSPF, onde o roteador escolhe o caminho mais rápido automaticamente, o BGP é baseado em **Políticas de Negócio**.
Um provedor pode configurar o BGP para enviar dados por um caminho mais longo só porque o contrato de conexão com aquela empresa é mais barato. O BGP permite que a economia e a política influenciem o roteamento técnico.

---

### Comparativo Histórico: BGP vs Protocolos Internos

| Característica | OSPF / RIP | BGP |
| --- | --- | --- |
| **Escopo** | Interno (IGP) | Externo (EGP) |
| **Métrica** | Custo / Saltos | Atributos (AS-Path, Local Preference) |
| **Foco** | Velocidade e eficiência técnica | Políticas comerciais e estabilidade |
| **Confiança** | Alta (dentro da mesma rede) | Baixa (exige filtros de segurança rígidos) |

---

## O Desafio Atual: Segurança

O maior problema histórico do BGP é a **confiança cega**. Originalmente, se um provedor anunciasse por erro que o tráfego do Google deveria passar por ele, a internet acreditaria (o famoso *BGP Hijacking*). Atualmente, a implementação do **RPKI** (uma forma de assinatura digital para rotas) é o grande marco histórico recente para tornar o BGP mais seguro.
