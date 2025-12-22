---

# # 🏛️ Arquitetura SNA da IBM

A **Systems Network Architecture (SNA)** é uma das arquiteturas de rede mais importantes e influentes da história da computação. Lançada pela IBM em **1974**, ela serviu como um framework proprietário, hierárquico e extremamente detalhado para comunicação em redes corporativas, dominando o ambiente de *mainframes* por décadas antes da consolidação do TCP/IP.
Além de padronizar a comunicação em redes IBM, o SNA também influenciou conceitos posteriores como o modelo OSI e práticas de redes corporativas.

---

# ## 🕰️ 1. Histórico e Contexto

## ### 1.1. O Cenário Pré-SNA

Antes de 1974, as redes eram compostas por soluções desconexas, pouco padronizadas e altamente dependentes de hardware e software específicos. Isso produzia:

* Redes **ad-hoc** e incompatíveis.
* Terminais IBM que só funcionavam com protocolos próprios.
* Configurações complexas e caras para cada novo dispositivo.
* Falta de interoperabilidade e baixa escalabilidade.

A crescente necessidade de conectar **milhares de terminais remotos** (como a família **IBM 3270**) a *mainframes* centrais levou a IBM a criar uma arquitetura unificada para comunicação.

---

## ### 1.2. O Lançamento da SNA (1974)

A IBM lançou oficialmente a SNA para:

* **Padronizar** as comunicações entre terminais, controladores e mainframes.
* **Unificar** protocolos e metodologias.
* **Centralizar** a administração da rede.
* Oferecer **alta confiabilidade** — essencial para bancos, governo e grandes corporações.

### 🔑 Princípios-chave da SNA:

* Primeira arquitetura proprietária de rede baseada em um **modelo de camadas**, antecipando o modelo OSI.
* Separação entre funções de **aplicação**, **transporte**, **controle** e **link físico**.
* Controle centralizado pelo mainframe via **SSCP (System Services Control Point)**.

### 🖥️ Primeira implementação:

* O software **ACF/VTAM** (Advanced Communications Function / Virtual Telecommunications Access Method).
* Para OS/VS1 e MVS.

Assim, a SNA se tornou a base das redes corporativas IBM nos anos seguintes.

---

# ## 🧱 2. Arquitetura Aprofundada

A arquitetura SNA define com rigor:

* Como os dados são transmitidos;
* Como os recursos devem se comportar;
* Como as sessões são estabelecidas;
* Como a rede é administrada.

Sua estrutura é altamente padronizada e hierárquica.

---

## ### 2.1. Modelo de Camadas da SNA (7 níveis)

Apesar de não idêntica ao OSI, a SNA possui um modelo de camadas que cumpre funções equivalentes — tendo sido criada *antes* do OSI.

|          Camada SNA         | Função Principal                                                | Equiv. OSI |
| :-------------------------: | :-------------------------------------------------------------- | :--------: |
|        **Transação**        | Serviços de aplicação como arquivos, impressão, consultas, etc. |      7     |
|       **Apresentação**      | Conversão de formatos (como EBCDIC) e sintaxe de dados.         |      6     |
|    **Controle de Fluxo**    | Controle fim-a-fim, sincronização e gerenciamento de diálogo.   |      5     |
| **Controle de Transmissão** | Estabelecimento/interrupção de sessões LU-LU; criptografia.     |      4     |
|   **Controle de Caminho**   | Roteamento, endereçamento e controle de tráfego.                |      3     |
|   **Controle de Ligação**   | Comunicação no enlace físico, detecção/correção de erros.       |      2     |
|          **Física**         | Especificações elétricas e mecânicas.                           |      1     |

---

## ### 2.2. Componentes Principais

| Elemento                | Descrição                                                                                   |
| :---------------------- | :------------------------------------------------------------------------------------------ |
| **Node (Nó)**           | Dispositivo participante da rede SNA (host, controlador, gateway).                          |
| **LU (Logical Unit)**   | Ponto de acesso lógico para aplicações e terminais. Ex: LU 2 (3270), LU 6.2 (peer-to-peer). |
| **PU (Physical Unit)**  | Representa o hardware que controla a comunicação física (ex: controladores 37x5).           |
| **CD (Control Domain)** | Nó central gerenciado pelo mainframe via VTAM/NCP.                                          |
| **Sessões LU-LU**       | Diálogo lógico entre terminais e aplicações.                                                |

As LUs foram um conceito inovador ao permitir diferentes tipos de interação sobre a mesma arquitetura.

---

# ## 📈 3. A Consolidação da SNA (anos 1970–1980)

Após seu lançamento, a SNA rapidamente se tornou o padrão dominante para redes corporativas IBM. Os principais fatores para isso foram:

* **Confiabilidade extrema** para operações críticas.
* **Padronização completa** entre todos os dispositivos IBM.
* **Controle centralizado**, adequando-se ao modelo de computação com *mainframes*.

### Ampliação tecnológica:

* Definição das **LUs**, incluindo o revolucionário **LU6.2**, que permitiu comunicação peer-to-peer entre aplicações.
* Crescimento das redes de grande porte, exigindo melhores métodos de roteamento.

Assim, durante os anos 1980, a SNA era praticamente onipresente em bancos, seguradoras, órgãos governamentais e grandes indústrias.

---

# ## 🧬 4. Evolução: APPN e HPR (1980–1990)

## ### 4.1. APPN (Advanced Peer-to-Peer Networking)

Nos anos 80, as redes se tornaram mais distribuídas, afastando-se da rigidez hierárquica da SNA Subarea. Para acompanhar essa modernização, a IBM criou a arquitetura **APPN (1986)**.

### Melhorias:

* Comunicação **peer-to-peer**.
* Descoberta automática de recursos.
* Roteamento dinâmico (sem tabelas estáticas).
* Menor dependência do mainframe.

APPN modernizou o SNA e permitiu topologias mais flexíveis.

---

## ### 4.2. HPR (High Performance Routing)

Introduzido nos anos 90, o HPR elevou o desempenho da SNA em redes WAN:

* Roteamento baseado na origem (*source routing*).
* Controle de congestionamento (ARBC).
* Recuperação rápida de falhas.
* Alta eficiência em redes corporativas espalhadas geograficamente.

---

# ## 🌐 5. O Declínio da SNA e Ascensão do TCP/IP (anos 1990)

Com o avanço da Internet e o crescimento das LANs heterogêneas, o **TCP/IP**, aberto e flexível, tornou-se o padrão mundial.

### Problemas para a SNA:

* Arquitetura rígida.
* Dependência de hardware IBM.
* Modelo centralizado.

### Reação da IBM:

A IBM não abandonou o SNA, mas criou **métodos de integração**:

* **SNA over IP (túneis)**.
* **Encapsulamento via DLSw**.
* **TN3270** (terminals 3270 via Telnet).
* **Enterprise Extender (EE)** — solução moderna que transporta SNA sobre IP nativo.

Isso permitiu que corporações mantivessem aplicações críticas, enquanto a infraestrutura migrava para TCP/IP.

---

# ## 🖥️ 6. SNA atualmente

Hoje, a SNA não é mais usada para novos projetos, mas ainda é fundamental em sistemas legados:

* Bancos
* Seguradoras
* Governo
* Grandes indústrias
* Ambientes z/OS e mainframe IBM

Muitos sistemas corporativos críticos dependem de LU6.2, transações 3270 e integrações com VTAM.

---

# ## 🧩 7. Contribuições e Legado Tecnológico da SNA

A arquitetura SNA deixou um impacto duradouro:

✔ Influenciou o **modelo OSI**.
✔ Padronizou comunicação em redes de grande porte como nenhuma outra na época.
✔ Criou conceitos modernos como sessões, controle de fluxo e roteamento estruturado.
✔ Estabeleceu práticas de confiabilidade e governança que ainda são usadas em mainframes.
✔ Permitiu a evolução gradual para TCP/IP sem perder aplicações legadas.

---

# ## 📝 8. Resumo Final

A **Systems Network Architecture (SNA)** foi um marco das redes corporativas e moldou décadas de tecnologia. Ela:

* Surgiu em **1974** para unificar redes IBM.
* Dominou os anos 70 e 80 com sua estrutura hierárquica.
* Evoluiu com **APPN** e **HPR**.
* Foi gradualmente substituída pelo **TCP/IP** nos anos 90.
* Ainda sustenta aplicações legadas críticas em bancos e governos.

Seu legado permanece vivo em conceitos, práticas e tecnologias que continuamos usando hoje.

---
