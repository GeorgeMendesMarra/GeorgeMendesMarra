---

## 1. O Contexto Pré-IPv4: O Protocolo Unificado

Antes do IPv4 ser o que conhecemos, as funções de transporte (garantia de entrega) e rede (endereçamento) eram fundidas em um único protocolo dentro da ARPANET.

* **TCP Primitivo:** Entre 1973 e 1977, existia apenas o "TCP". Os pesquisadores perceberam que nem todo tráfego precisava de entrega garantida (como voz e vídeo em tempo real, que começavam a ser testados).
* **A Cisão:** Para tornar a rede mais modular, o protocolo foi dividido. O **IP** ficou responsável por mover os pacotes do ponto A ao ponto B (sem garantias), enquanto o **TCP** cuidava da integridade.
* **Versões 1, 2 e 3:** Foram versões experimentais utilizadas em testes de laboratório entre 1977 e 1979. O IPv4 foi a primeira versão considerada estável o suficiente para produção global.

## 2. A Estrutura Técnica Definida em 1981

O **RFC 791**, escrito por Jon Postel, estabeleceu as regras que ainda usamos hoje. A decisão mais crítica foi o uso de endereços de **32 bits**.

* **Matemática do Endereçamento:**  endereços possíveis. Na época, com a população mundial em cerca de 4,5 bilhões e pouquíssimos computadores, parecia impossível esgotar essa capacidade.
* **Notação Decimal:** Para facilitar a leitura humana, os 32 bits foram divididos em 4 grupos de 8 bits (octetos), separados por pontos: `192.168.1.1`.

## 3. O Sistema de Classes e o Início do Desperdício

Nos primeiros anos, o espaço de endereçamento foi distribuído de forma generosa através do sistema de **Classes**:

* **Classe A (0.0.0.0 a 127.255.255.255):** Apenas 128 redes possíveis, mas cada uma com 16,7 milhões de endereços. Grandes instituições como a Apple, Ford e o Exército dos EUA receberam esses blocos.
* **Classe B (128.0.0.0 a 191.255.255.255):** 16 mil redes com 65 mil endereços cada.
* **Classe C (192.0.0.0 a 223.255.255.255):** 2 milhões de redes com apenas 254 endereços cada.

> **Nota:** Se uma empresa precisasse de 300 endereços, ela não podia usar a Classe C. Recebia um bloco Classe B, desperdiçando 65.000 endereços que ficavam inutilizados.

## 4. O "Salvamento" do IPv4 nos Anos 90

Com a comercialização da Internet, o IETF (Internet Engineering Task Force) percebeu que o IPv4 entraria em colapso antes do ano 2000 se nada fosse feito. Duas soluções principais estenderam sua vida:

### CIDR (Classless Inter-Domain Routing) - 1993

O CIDR eliminou as classes. Em vez de blocos fixos (A, B ou C), os provedores puderam alocar blocos de qualquer tamanho usando a "máscara de sub-rede" (ex: `/21`, `/29`). Isso permitiu uma distribuição muito mais cirúrgica dos IPs restantes.

### NAT (Network Address Translation) - 1994

O NAT foi a "sobrevida" definitiva. Ele permitiu que um roteador doméstico usasse apenas **um** IP público para representar dezenas de dispositivos internos (celulares, notebooks, TVs). Sem o NAT, o IPv4 teria acabado antes mesmo do ano 2000.

## 5. O Esgotamento e o Mercado Negro de IPs

Em 3 de fevereiro de 2011, a **IANA** (autoridade mundial) entregou os últimos cinco blocos `/8` de IPv4 para os registros regionais (RIRs).

* **Consequência:** Os endereços IPv4 tornaram-se ativos valiosos. Empresas que possuem grandes blocos não utilizados (como a Microsoft e a Amazon) agora compram e vendem esses endereços em um mercado secundário que movimenta milhões de dólares.
* **CGNAT (Carrier-Grade NAT):** Para lidar com a escassez, provedores de internet móvel e banda larga começaram a colocar milhares de clientes atrás de um único IP público, o que às vezes causa problemas em jogos online e câmeras de segurança.

---

### Tabela de Comparação de Escopo

| Era | Tecnologia Principal | Objetivo |
| --- | --- | --- |
| **Experimental (1974-1981)** | TCP Unificado | Conectar redes militares e acadêmicas. |
| **Expansão (1981-1993)** | Endereçamento em Classes | Facilitar o roteamento inicial com tabelas simples. |
| **Crise/Sobrevivência (1993-2011)** | CIDR e NAT | Maximizar a eficiência e evitar o fim dos IPs. |
| **Legado/Transição (2011-Hoje)** | Dual Stack (IPv4 + IPv6) | Manter a compatibilidade enquanto o mundo migra para o IPv6. |

---
