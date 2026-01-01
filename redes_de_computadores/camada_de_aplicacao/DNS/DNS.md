---

## 1. A Era do arquivo HOSTS.TXT (Anos 70)

Nos primórdios da ARPANET, os computadores eram poucos. Para conectar-se a outro computador, você precisava saber o endereço numérico dele.

* **O sistema manual:** O Stanford Research Institute (SRI) mantinha um único arquivo de texto chamado `HOSTS.TXT`.
* **O processo:** Sempre que um novo computador era adicionado à rede, o administrador do SRI atualizava o arquivo. Todos os outros usuários da rede tinham que baixar esse arquivo via FTP para atualizar seus próprios mapas de nomes e endereços.
* **O colapso:** Conforme a rede cresceu, tornou-se impossível para uma única organização gerenciar todas as atualizações, e o tráfego gerado apenas pelo download do arquivo `HOSTS.TXT` começou a sobrecarregar as conexões.

## 2. A Invenção de Paul Mockapetris (1983)

Em 1983, diante da necessidade de um sistema descentralizado e automático, **Paul Mockapetris** projetou o DNS.

* **RFC 882 e 883:** Publicados em 1983, definiram a arquitetura que usamos até hoje.
* **A inovação:** Em vez de um arquivo central, o DNS introduziu uma **estrutura hierárquica e distribuída**. A responsabilidade pelos nomes foi dividida em "zonas".
* **1987 (RFC 1034/1035):** Estas especificações atualizaram o protocolo, tornando-o o padrão estável que permitiu a explosão da Internet comercial.

---

## 3. A Estrutura Hierárquica

O DNS foi desenhado como uma árvore invertida:

* **Root Servers (Raiz):** O topo da hierarquia (representado por um ponto ".").
* **TLDs (Top-Level Domains):** As extensões como `.com`, `.org`, `.br`.
* **Domínios de Segundo Nível:** Como `google` em `google.com`.
* **Subdomínios:** Como `maps` em `maps.google.com`.

## 4. O Processo de Resolução (Como funciona)

Quando você digita um site, o DNS faz uma "perseguição" em milissegundos:

1. **Resolver:** Seu computador pergunta ao seu provedor (ISP).
2. **Root:** O provedor pergunta aos servidores raiz onde está o `.com`.
3. **TLD:** O servidor raiz aponta para o servidor que cuida do `.com`.
4. **Autoritativo:** O servidor do `.com` aponta para o servidor da empresa (ex: Google).
5. **IP:** O servidor da empresa entrega o endereço IP final ao seu navegador.

---

## 5. Evolução e Segurança: Do UDP ao DoH

Originalmente, o DNS foi projetado para ser rápido, usando o protocolo **UDP** na porta 53. No entanto, ele não tinha criptografia, o que permitia que governos ou hackers espionassem quais sites você visitava.

* **DNSSEC (2005):** Introduziu assinaturas digitais para garantir que a resposta do DNS não fosse falsificada.
* **DoH (DNS over HTTPS) e DoT (DNS over TLS):** Tecnologias recentes (pós-2018) que criptografam suas consultas DNS, escondendo-as dentro do tráfego web comum para garantir privacidade total.

### Resumo Técnico

| Característica | Detalhes |
| --- | --- |
| **Data de Criação** | 1983 |
| **Criador** | Paul Mockapetris |
| **Protocolo Base** | UDP (principalmente) e TCP na porta 53 |
| **Função** | Traduzir nomes (URLs) em endereços IP |

---

Sem o DNS, você teria que decorar números como `142.250.191.46` em vez de simplesmente digitar `google.com`.
