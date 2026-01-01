## 1. O Problema das Estações Sem Disco (Anos 80)

No início da década de 80, o hardware era extremamente caro. Muitas empresas utilizavam "estações de trabalho sem disco" (*diskless workstations*).

* Esses computadores não tinham HD ou disquetes para armazenar um sistema operacional ou arquivos de configuração.
* Quando eram ligados, eles não sabiam qual era o seu próprio endereço IP, pois não havia onde gravar essa informação localmente.
* No entanto, a placa de rede já vinha com o endereço físico (MAC) gravado de fábrica.

## 2. A Padronização (1984)

Para resolver esse impasse, o RARP foi formalizado em **junho de 1984** através do **RFC 903**, escrito por **Ross Finlayson**, **Timothy Mann**, **Jeffrey Mogul** e **Marvin Theimer**.

A lógica era simples: ao ligar, o computador enviava um "grito" (broadcast) na rede dizendo: *"Meu endereço físico é XX:XX. Alguém aí sabe qual é o meu IP?"*. Um servidor configurado especificamente para isso (o **Servidor RARP**) consultava uma tabela e respondia com o endereço IP correspondente.

## 3. Limitações Técnicas

Apesar de genial para a época, o RARP tinha limitações que impediram sua longevidade:

* **Dependência da Camada de Enlace:** O RARP operava diretamente na camada 2 (como o ARP). Isso significava que o servidor RARP precisava estar na **mesma rede local** que o cliente; ele não conseguia atravessar roteadores.
* **Informação Mínima:** O RARP só fornecia o endereço IP. Ele não conseguia informar a máscara de sub-rede, o gateway padrão ou o servidor DNS — informações que se tornaram essenciais conforme a internet crescia.
* **Complexidade de Implementação:** Exigia um software de servidor muito específico que falasse diretamente com a placa de rede, o que era difícil de manter em diferentes sistemas operacionais.

## 4. O Sucessor e a Aposentadoria

Devido a essas falhas, o RARP começou a ser substituído rapidamente:

1. **BOOTP (1985):** Um protocolo que rodava acima do IP (camada 3), conseguia atravessar roteadores e fornecia mais informações (como o nome do arquivo para o boot via rede).
2. **DHCP (1993):** O sucessor definitivo do BOOTP, que automatizou completamente a atribuição de IPs e é o que usamos até hoje em nossas casas e empresas.

---

### Resumo Comparativo

| Característica | ARP | RARP |
| --- | --- | --- |
| **Lógica** | "Eu tenho o IP, quem tem o MAC?" | "Eu tenho o MAC, quem me dá um IP?" |
| **Uso Principal** | Comunicação normal entre PCs | Inicialização de máquinas sem disco |
| **Status Atual** | Ainda essencial no IPv4 | **Obsoleto** (substituído pelo DHCP) |

---

### Por que o RARP morreu e o ARP não?

O ARP ainda é necessário porque a comunicação final entre dois cabos sempre precisa de um endereço físico. Já o RARP morreu porque o processo de "pedir um IP" tornou-se muito mais complexo do que uma simples tabela de conversão MAC-IP, exigindo as funcionalidades ricas que apenas o **DHCP** oferece.
