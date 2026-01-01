## 1. O Sucessor do RARP (1985)

Em meados dos anos 80, o protocolo RARP era usado para dar endereços IP a máquinas sem disco, mas ele tinha um problema grave: não funcionava através de roteadores (limitação de Camada 2).

Em **setembro de 1985**, Bill Croft e John Gilmore publicaram o **RFC 951**, definindo o **BOOTP**. A ideia era permitir que uma estação de trabalho descobrisse não apenas seu endereço IP, mas também o endereço do servidor que continha o arquivo de boot (sistema operacional) para que ela pudesse carregar via rede.

## 2. Inovações Técnicas: Camada de Transporte

Diferente do seu antecessor, o BOOTP introduziu mudanças arquiteturais que mudaram o jogo:

* **Uso de UDP/IP:** O BOOTP opera na Camada de Aplicação, utilizando **UDP** (portas 67 para o servidor e 68 para o cliente). Por rodar sobre o protocolo IP, as mensagens BOOTP podiam ser roteadas para servidores em outras sub-redes.
* **Agentes de Retransmissão (Relay Agents):** Com o BOOTP, surgiu o conceito de que um roteador poderia "ajudar" um cliente, pegando o pedido de configuração e enviando para um servidor centralizado em outra parte da rede.
* **Informações Completas:** Além do IP, o BOOTP podia entregar o endereço do **Gateway Padrão** e o endereço do servidor de arquivos (via TFTP).

## 3. O Surgimento das Vendor Extensions (1988)

Com o tempo, percebeu-se que o BOOTP precisava entregar ainda mais dados (como o nome do domínio ou servidores DNS). Em 1988, o **RFC 1048** introduziu as "Vendor Information Extensions", que permitiam adicionar campos extras ao pacote BOOTP.

Esses campos extras foram a semente do que mais tarde se tornaria as "Opções" do DHCP.

## 4. Limitações e a Transição para o DHCP

Apesar de ser muito mais robusto que o RARP, o BOOTP ainda tinha uma característica que o tornava difícil de gerenciar em redes móveis:

* **Configuração Estática:** No BOOTP, o administrador ainda precisava configurar manualmente no servidor uma tabela que ligava cada endereço MAC a um endereço IP específico. Se um novo computador chegasse, o servidor não daria um IP a ele automaticamente até que o administrador editasse a tabela.
* **Falta de "Aluguel" (Lease):** Uma vez que um IP era atribuído a uma máquina no BOOTP, ele era dela para sempre (ou até o administrador mudar). Não havia o conceito de devolver o IP para o "estoque" quando a máquina era desligada.

Em **1993**, essas limitações levaram à criação do **DHCP (Dynamic Host Configuration Protocol)**, que foi construído diretamente sobre a estrutura de pacotes do BOOTP, mas adicionou a capacidade de gerenciar "pools" de endereços dinâmicos.

---

### Resumo Evolutivo

| Protocolo | Camada | Roteável? | Atribuição Automática? | Status |
| --- | --- | --- | --- | --- |
| **RARP** | Enlace (L2) | Não | Sim (por tabela) | Obsoleto |
| **BOOTP** | Aplicação (UDP) | Sim | Não (exige tabela fixa) | Legado (usado em Boot via Rede/PXE) |
| **DHCP** | Aplicação (UDP) | Sim | Sim (dinâmica e com aluguel) | Atual |

---

### Onde o BOOTP ainda vive?

Hoje, você dificilmente verá um servidor "puro" BOOTP, mas o protocolo ainda existe de forma invisível. O processo de **PXE Boot** (quando você formata um computador via rede em uma empresa) utiliza o formato de pacote do BOOTP para obter o primeiro contato com o servidor de imagens.
