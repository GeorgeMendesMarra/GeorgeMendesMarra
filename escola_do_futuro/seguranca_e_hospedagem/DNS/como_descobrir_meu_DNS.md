### 1. No Windows (via Prompt de Comando)

O comando `ipconfig` é o padrão para ver as configurações de rede, mas para ver o DNS você precisa do parâmetro `/all`.

1. Abra o **Prompt de Comando** (digite `cmd` no menu iniciar).
2. Digite: `ipconfig /all`
3. Procure pela linha **"Servidores DNS"**.
* *Dica:* Geralmente aparecem dois endereços (um primário e um secundário). Se aparecer algo como `192.168.1.1`, seu computador está usando o DNS do seu roteador.



### 2. No Linux ou macOS (via Terminal)

Nos sistemas baseados em Unix, a forma mais rápida é ler o arquivo de configuração de resolução.

1. Abra o **Terminal**.
2. Digite: `cat /etc/resolv.conf`
3. O endereço aparecerá ao lado da palavra **nameserver**.

Alternativamente, você pode usar o comando:
`nmcli dev show | grep DNS` (em distribuições Linux com NetworkManager).

### 3. Usando o comando `nslookup` (Universal)

Este comando é excelente porque ele não só mostra seu DNS, mas também testa se ele está respondendo.

1. Abra o terminal (Windows, Mac ou Linux).
2. Digite apenas: `nslookup`
3. A primeira linha de resposta será o **Default Server** (seu servidor DNS atual) e o **Address** (o IP dele).

---

### O que os números significam?

Ao descobrir o IP, você pode identificar quem é o seu provedor de DNS:

* **8.8.8.8** ou **8.8.4.4**: Google Public DNS.
* **1.1.1.1** ou **1.0.0.1**: Cloudflare (focado em privacidade).
* **208.67.222.222**: OpenDNS (Cisco).
* **IPs que começam com 192.168.x.x ou 10.x.x.x**: É o seu próprio roteador fazendo a ponte.

---

### Por que verificar isso?

Como engenheiro de software ou profissional de redes, saber o seu DNS ajuda a diagnosticar:

1. **Lentidão na navegação:** Às vezes o DNS do provedor local é lento para resolver nomes.
2. **Propagação de domínio:** Quando você muda o IP de um site e ele "não entra", pode ser que seu DNS ainda tenha a versão antiga em cache.
