Para aprofundar o entendimento sobre a **SNA (Systems Network Architecture)**, é preciso visualizar como a IBM desenhou uma rede capaz de suportar as operações críticas de bancos e governos em uma era onde não existia a Internet. A SNA não era apenas um protocolo, mas uma estratégia completa de gerenciamento de dados corporativos.

Abaixo, detalho os componentes avançados, a filosofia de controle e o legado técnico dessa arquitetura.

---

## 1. A Filosofia do Controle Hierárquico

Diferente do modelo TCP/IP, onde cada computador é um "par" (*peer*) com autonomia, o SNA clássico operava em uma estrutura de **Mestre/Escravo**.

* **Centralização Extrema:** No topo da pirâmide estava o Mainframe (geralmente rodando o sistema VTAM - *Virtual Telecommunications Access Method*). Ele era o único que conhecia a topologia completa da rede.
* **Previsibilidade:** Por ser centralizado, o administrador da rede sabia exatamente qual era a carga de cada cabo e dispositivo. Isso permitia uma estabilidade que o TCP/IP demorou décadas para alcançar.
* **Determinismo:** No SNA, não havia "colisão" de dados. O controle de tráfego (pacing) era tão rígido que era impossível um dispositivo congestionar a rede sem autorização do mestre.

---

## 2. Componentes Estruturais (Nós e Unidades)

A arquitetura SNA define tipos específicos de "Nós" para organizar a hierarquia física:

### Tipos de Nós (Node Types)

* **Nó Tipo 5:** O Mainframe. Contém o **SSCP** (Ponto de Controle de Serviços do Sistema), que ativa e desativa a rede.
* **Nó Tipo 4:** Processadores de Comunicação (FEP - *Front End Processors*). Eram hardwares dedicados apenas a gerenciar as linhas de transmissão, aliviando o processador principal do Mainframe.
* **Nó Tipo 2.0 / 2.1:** Controladores de terminais ou PCs. O Tipo 2.1 foi um marco, pois permitiu o início da comunicação sem a intervenção direta do Mainframe (o nascimento do APPN).

### Unidades Lógicas (LU) e Sessões

A comunicação no SNA ocorre através de **Sessões entre LUs**. As LUs são "portas" de software:

* **LU 2:** Usada para terminais de vídeo (as famosas telas verdes 3270).
* **LU 3:** Usada para impressoras.
* **LU 6.2 (APPC):** A mais avançada. Permitia que dois programas conversassem entre si (programa-a-programa). Foi a base para o processamento distribuído muito antes dos Web Services modernos.

---

## 3. O Protocolo SDLC (O Coração da Camada Física/Enlace)

O **SDLC (Synchronous Data Link Control)** foi o protocolo de baixo nível da SNA. Ele foi tão eficiente que serviu de base para o padrão internacional **HDLC**, usado em redes X.25 e Frame Relay.

* Ele permitia o uso de linhas telefônicas de longa distância com alta confiabilidade.
* Introduziu o conceito de "verificação de redundância cíclica" (CRC) para garantir que os dados não sofressem interferência eletromagnética durante o trajeto.

---

## 4. APPN e HPR: A Resposta à Descentralização

Nos anos 80 e 90, a IBM percebeu que a hierarquia rígida estava morrendo. Ela introduziu:

* **APPN (Advanced Peer-to-Peer Networking):** Permitiu o roteamento dinâmico. Se um caminho caísse, o APPN encontrava outro automaticamente, eliminando a necessidade de o administrador definir cada rota manualmente.
* **HPR (High Performance Routing):** Uma evolução que permitia velocidades muito maiores e priorização de tráfego, competindo diretamente com a eficiência do roteamento IP.

---

## 5. Legado e Coexistência (AnyNet e Enterprise Extender)

Quando o TCP/IP "venceu" a guerra das redes, milhares de empresas ainda tinham bilhões de linhas de código rodando em SNA. A solução não foi substituir tudo, mas integrar:

1. **Encapsulamento (Tunneling):** Os pacotes SNA são colocados "dentro" de pacotes TCP/IP. Para o Mainframe, a rede ainda parece SNA; para os roteadores da Cisco/Juniper, parece apenas tráfego de Internet comum.
2. **Enterprise Extender (EE):** É a tecnologia atual. Ela mapeia os níveis de prioridade do SNA diretamente nos campos de QoS (Qualidade de Serviço) do protocolo IP.

---

## Comparação Técnica de Resumo

| Característica | SNA Clássico | TCP/IP |
| --- | --- | --- |
| **Administração** | Centralizada (VTAM) | Descentralizada |
| **Endereçamento** | Dependente da topologia física | Endereços IP lógicos e flexíveis |
| **Confiabilidade** | Extremamente Alta (Garantida em HW/SW) | Melhor Esforço (Best Effort) |
| **Configuração** | Estática e Manual | Dinâmica (DHCP/OSPF/BGP) |
| **Propriedade** | Proprietário (IBM) | Aberto (RFCs/IETF) |

O SNA é o "dinossauro" mais bem-sucedido da computação: embora raramente visto na superfície, ele ainda processa a maioria das transações de cartão de crédito e reservas de voos que você faz hoje.

**Você gostaria de ver um exemplo de como uma "tela verde" de terminal 3270 era mapeada dentro das camadas do SNA?**
