---

## 1. Os Primórdios: Sistemas Compartilhados (Anos 60)

Antes da rede, o "e-mail" era apenas um método de deixar mensagens para outros usuários que utilizavam o **mesmo computador central** (mainframe).

* **1965:** O sistema **MAILBOX**, do MIT, permitia que usuários de terminais burros deixassem mensagens uns para os outros no disco rígido do computador central.
* **Limitação:** Não era possível enviar uma mensagem para alguém em outro prédio ou outra cidade; o destinatário precisava estar logado na mesma máquina física.

## 2. Ray Tomlinson e o Nascimento da Rede (1971)

O grande salto ocorreu na **ARPANET**. O engenheiro **Ray Tomlinson** trabalhava na BBN Technologies quando decidiu combinar um programa de transferência de arquivos (CPYNET) com um programa de mensagens internas (SNDMSG).

* **A invenção do "@" (Arroba):** Tomlinson precisava de uma forma de separar o nome do usuário do nome do computador onde ele estava. Ele escolheu o símbolo `@` porque, em inglês, soa como "at" (em), indicando que o usuário "fulano" estava "no computador tal".
* **O primeiro e-mail:** Foi enviado entre dois computadores que estavam lado a lado em 1971. Tomlinson afirmou mais tarde que o conteúdo era algo esquecível, como "QWERTYUIOP".

---

## 3. A Padronização: SMTP (1982)

Com o crescimento da rede, surgiu a necessidade de uma linguagem comum para que diferentes tipos de computadores pudessem trocar e-mails.

* **1982 (RFC 821):** Jonathan Postel definiu o **SMTP (Simple Mail Transfer Protocol)**. Ele estabeleceu as regras de como um servidor de e-mail deve "empurrar" uma mensagem para outro.
* **RFC 822:** Padronizou o formato das mensagens (campos "De:", "Para:", "Assunto:"). Até hoje, a estrutura básica de um e-mail segue este padrão de texto simples.

## 4. Acessando as Mensagens: POP3 e IMAP

Originalmente, o e-mail era lido diretamente no servidor. Conforme os PCs se tornaram populares, novos protocolos surgiram para "baixar" as mensagens:

* **POP3 (1988):** Desenhado para baixar o e-mail do servidor para o computador e depois apagá-lo do servidor. Ideal para uma era de conexões discadas e pouco espaço em disco.
* **IMAP (1986/2003):** Permite que as mensagens fiquem no servidor e sejam sincronizadas entre vários dispositivos (celular, tablet, PC). É o padrão que permite que você veja a mesma caixa de entrada em qualquer lugar.

---

## 5. A Era do Webmail e Spam (Anos 90 - Hoje)

* **1996:** O lançamento do **HoTMaiL** (um dos primeiros serviços gratuitos de webmail) permitiu que qualquer pessoa com um navegador tivesse um e-mail, sem precisar configurar softwares complexos.
* **1978/1990s:** O primeiro **Spam** foi enviado em 1978 para 400 usuários da ARPANET, mas tornou-se um problema global nos anos 90, forçando a criação de filtros e protocolos de segurança como **SPF**, **DKIM** e **DMARC** para validar a identidade do remetente.
* **2004:** O lançamento do **Gmail** mudou o mercado ao oferecer 1GB de armazenamento (na época, o Hotmail oferecia apenas 2MB), forçando a indústria a tratar o e-mail como um arquivo digital permanente.

### Resumo Técnico dos Protocolos

| Protocolo | Função | Porta Padrão |
| --- | --- | --- |
| **SMTP** | Enviar e-mail | 25, 465 ou 587 |
| **POP3** | Baixar e-mail (local) | 110 ou 995 |
| **IMAP** | Sincronizar e-mail (nuvem) | 143 ou 993 |

---

O e-mail continua sendo um dos poucos protocolos da Internet que é verdadeiramente **federado**: você pode ter um e-mail no Gmail e enviar para alguém no Outlook ou em um servidor privado, e eles se entenderão perfeitamente graças aos padrões definidos décadas atrás.
