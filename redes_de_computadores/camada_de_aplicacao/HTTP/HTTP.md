---

# A Evolução do HTTP: Do Texto Simples à Web Moderna

O **HTTP (Hypertext Transfer Protocol)** é a base de toda a troca de dados na Web. Sua história é marcada pela busca constante de reduzir a **latência** (o tempo de espera) e aumentar a eficiência na entrega de conteúdos cada vez mais complexos.

---

## 1. HTTP/0.9 – O Protocolo "One-Line" (1991)

Projetado por **Tim Berners-Lee**, o HTTP original era extremamente rudimentar, focado apenas na transferência de documentos de texto acadêmicos.

* **Simplicidade Extrema:** Não existiam cabeçalhos (headers). O cliente enviava uma única linha como `GET /index.html` e o servidor respondia apenas com o conteúdo do arquivo.
* **Somente HTML:** Não era possível transmitir imagens ou outros tipos de arquivos.
* **Sem Status:** Não existiam códigos como o famoso "404 Not Found". Se um arquivo não existisse, o servidor simplesmente não enviava nada ou enviava um texto explicativo dentro do HTML.

## 2. HTTP/1.0 – A Expansão Visual (1996)

Com o crescimento da Web para o público geral, surgiu a necessidade de transmitir mais do que apenas texto. O documento **RFC 1945** oficializou essa versão.

* **Cabeçalhos (Headers):** Introduziu metadados tanto na requisição quanto na resposta, permitindo o envio de informações sobre o navegador, tipo de codificação e cookies.
* **Content-Type:** Graças aos cabeçalhos, o protocolo passou a suportar outros tipos de mídia (imagens, vídeos, scripts) via extensões MIME.
* **Códigos de Status:** Surgiram os códigos de resposta para indicar sucesso (`200 OK`) ou erros (`404 Not Found`, `500 Internal Server Error`).
* **O Problema da Conexão Curta:** Para cada elemento de uma página (uma imagem, um ícone, um arquivo CSS), o navegador tinha que abrir e fechar uma conexão TCP nova, o que gerava um overhead enorme.

## 3. HTTP/1.1 – O Padrão de Ouro (1997 - 1999)

Esta versão (definida na **RFC 2068** e depois **RFC 2616**) corrigiu as deficiências de performance da versão 1.0 e reinou absoluta por quase duas décadas.

* **Keep-Alive (Conexões Persistentes):** A conexão TCP não é mais fechada imediatamente. O navegador pode reutilizar a mesma conexão para baixar vários arquivos seguidos.
* **Pipelining:** Tentativa de enviar várias solicitações antes mesmo de receber a primeira resposta (embora tenha tido problemas de implementação nos servidores).
* **Cache e Compressão:** Melhores mecanismos para armazenar dados localmente e comprimir arquivos para download mais rápido.
* **Host Header:** Permitiu que um único endereço IP pudesse hospedar vários domínios diferentes (Hospedagem Virtual).

## 4. HTTP/2 – Eficiência e Binário (2015)

À medida que as páginas web se tornaram pesadas (com mais de 100 requisições por carregamento), o HTTP/1.1 começou a "engasgar". O HTTP/2 surgiu para resolver o gargalo de performance.

* **Formato Binário:** Ao contrário das versões anteriores que usavam texto legível, o HTTP/2 é binário, o que o torna muito mais eficiente para compactar e transportar.
* **Multiplexação:** A maior mudança. Permite que centenas de requisições e respostas viajem **simultaneamente** por uma única conexão TCP, eliminando a fila de espera.
* **Compressão de Cabeçalho (HPACK):** Reduz o tamanho dos dados repetitivos enviados nos headers.
* **Server Push:** O servidor "adivinha" o que o navegador vai precisar (como um arquivo CSS) e envia antes mesmo do navegador pedir.

## 5. HTTP/3 – O Salto para o QUIC (2022)

Mesmo o HTTP/2 tinha um limite: se um único pacote de dados fosse perdido na rede, toda a conexão TCP travava (problema conhecido como *Head-of-Line Blocking*). O HTTP/3 resolve isso mudando a base da conexão.

* **Abandono do TCP:** O HTTP/3 utiliza o protocolo **QUIC**, que roda sobre **UDP**.
* **Conexão Instantânea:** O QUIC combina o estabelecimento da conexão e a criptografia (TLS 1.3) em um único passo, tornando o início do carregamento muito mais rápido.
* **Resiliência Móvel:** Permite que você mude do Wi-Fi para o 4G/5G sem que a conexão caia, pois a sessão é identificada por um ID e não pelo endereço IP.

---

### Tabela de Comparação Técnica

| Característica | HTTP/1.1 | HTTP/2 | HTTP/3 |
| --- | --- | --- | --- |
| **Transporte** | TCP | TCP | UDP (QUIC) |
| **Formato** | Texto Plano | Binário | Binário |
| **Multiplexação** | Não (Pipelining limitado) | Sim (Uma conexão) | Sim (Fluxos independentes) |
| **Criptografia** | Opcional (HTTPS) | Praticamente obrigatória | Nativa e obrigatória |
| **Latência** | Alta em sites complexos | Média/Baixa | Muito Baixa |

---

> **Dica de Estudo:** O HTTP é um protocolo **stateless** (sem estado). Isso significa que, por padrão, o servidor não "lembra" de você entre uma requisição e outra. É por isso que usamos **Cookies** e **Tokens JWT** na camada de aplicação para manter você logado em um site.
