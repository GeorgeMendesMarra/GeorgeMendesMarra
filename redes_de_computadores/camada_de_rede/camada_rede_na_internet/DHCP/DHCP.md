---

## 1. A Necessidade de Dinamismo (Início dos anos 90)

No início da década de 90, com a popularização dos computadores portáteis e o crescimento das redes corporativas, o protocolo **BOOTP** tornou-se um gargalo.

O problema era que o BOOTP exigia que cada endereço MAC fosse previamente cadastrado em uma tabela vinculada a um IP fixo. Se um funcionário mudasse de mesa ou um visitante conectasse seu laptop, o administrador de rede precisava editar arquivos de configuração manualmente. Era necessário um protocolo que pudesse "emprestar" endereços de um estoque (pool) de forma automática.

## 2. O Nascimento e a Padronização (1993 - 1997)

O DHCP foi desenvolvido pelo IETF como uma extensão do BOOTP, mantendo a compatibilidade de formato de pacotes para facilitar a transição.

* **1993 (RFC 1531/1541):** Ralph Droms publicou as primeiras especificações do DHCP. O grande diferencial foi a introdução do conceito de **Lease (Aluguel)**: o endereço IP não era mais permanente, mas sim emprestado por um tempo determinado.
* **1997 (RFC 2131):** Esta versão tornou-se o padrão definitivo para redes IPv4 que utilizamos até hoje. Ela refinou o processo de troca de mensagens e a recuperação de endereços após a expiração do aluguel.

## 3. O Funcionamento: O Ciclo DORA

A maior inovação histórica do DHCP foi o processo de quatro etapas para a obtenção de um IP, conhecido pela sigla **DORA**:

1. **D**iscover: O cliente grita na rede: "Alguém pode me dar um IP?".
2. **O**ffer: O servidor responde: "Eu tenho o endereço 192.168.1.10 disponível".
3. **R**equest: O cliente diz: "Legal, eu aceito esse endereço!".
4. **A**cknowledge: O servidor confirma: "Combinado, use-o por 24 horas".

Esse mecanismo permitiu que as redes Wi-Fi públicas (em cafés, aeroportos, etc.) fossem possíveis, já que o IP é devolvido ao servidor assim que o usuário vai embora.

## 4. Evolução para o IPv6 (DHCPv6)

Com a chegada do IPv6, houve uma discussão sobre a necessidade do DHCP, já que o IPv6 possui a autoconfiguração nativa (SLAAC). No entanto, o **DHCPv6** foi padronizado em **2003 (RFC 3315)** para oferecer um controle mais rígido.

* **Stateful DHCPv6:** O servidor mantém o controle de quem recebeu qual IP (igual ao IPv4).
* **Stateless DHCPv6:** O dispositivo gera seu próprio IP via SLAAC, mas usa o DHCPv6 apenas para obter informações adicionais, como o endereço do servidor DNS.

---

## 5. Impacto e Segurança

O DHCP mudou a Internet de uma rede estática para uma rede móvel e flexível. No entanto, sua simplicidade trouxe desafios de segurança, como o **DHCP Spoofing** (quando um servidor falso entrega IPs errados para capturar dados). Isso levou à criação de recursos modernos em switches, como o **DHCP Snooping**, que monitora e valida as mensagens do protocolo.

### Comparativo Histórico: BOOTP vs DHCP

| Característica | BOOTP | DHCP |
| --- | --- | --- |
| **Atribuição** | Estática (Manual) | Dinâmica (Automática) |
| **Conceito de Aluguel** | Não existe | Central (Lease Time) |
| **Configuração** | Exige MAC do cliente | Usa um Pool de IPs |
| **Uso Atual** | Boot remoto (PXE) | Praticamente todas as redes IP |

---

O DHCP é o que permite que você chegue em casa e seu celular se conecte ao Wi-Fi instantaneamente, sem que você precise saber o que é uma máscara de sub-rede ou um gateway.
