### 1. A Era do arquivo HOSTS.TXT (Anos 70 - 1983)

Nos primórdios da ARPANET (a avó da internet), para você acessar outro computador, precisava saber o endereço IP numérico dele.

* Como ninguém queria decorar números, o **SRI** (Stanford Research Institute) mantinha um arquivo chamado `HOSTS.TXT`.
* **O problema:** Sempre que um novo computador entrava na rede, o arquivo precisava ser atualizado manualmente. Os administradores tinham que baixar esse arquivo via FTP toda vez que ele mudava. Com o crescimento da rede, isso se tornou um **gargalo insustentável**.

### 2. O Nascimento do DNS (1983 - 1984)

Em 1983, **Paul Mockapetris** recebeu a tarefa de criar um sistema que fosse automático e distribuído. Ele publicou as RFCs 882 e 883, que definiram o que conhecemos hoje como DNS.

* **A inovação:** Em vez de um único arquivo central, o DNS usava uma **hierarquia**.
* Em 1984, foram criados os primeiros domínios de nível superior (TLDs): `.com`, `.edu`, `.gov`, `.mil`, `.org`, `.net` e `.arpa`.

### 3. A Comercialização e Expansão (Anos 90)

Com a explosão da *World Wide Web*, o registro de domínios deixou de ser acadêmico e tornou-se comercial.

* A **Network Solutions** deteve o monopólio dos registros até 1999, quando a **ICANN** foi formada para coordenar o sistema e permitir concorrência entre registradores.

### 4. Segurança e Modernização (Anos 2000 - Presente)

O DNS original não foi criado com foco em segurança, o que permitia ataques como o *DNS Spoofing* (envenenamento de cache).

* **DNSSEC:** Criado para adicionar assinaturas digitais e garantir que o IP que você recebe é realmente o legítimo.
* **DoH (DNS over HTTPS):** A tendência atual, que criptografa suas consultas DNS para que provedores e hackers não saibam quais sites você está visitando.

---

### Por que isso é importante para um Engenheiro de Software?

Saber como o DNS funciona é vital para:

1. **Configuração de Servidores:** Apontar registros `A`, `CNAME` e `MX`.
2. **Performance:** Entender o TTL (*Time to Live*) e como o cache afeta a propagação de mudanças no sistema.
3. **Segurança:** Proteger aplicações contra ataques de redirecionamento.

---
