# Sistemas Distribuídos — Engenharia de Software

## Ementa

Fundamentos, modelos e paradigmas de sistemas distribuídos. Arquiteturas (cliente-servidor, em camadas, peer-to-peer, orientadas a serviços e a eventos, microsserviços). Comunicação entre processos (sockets, RPC/gRPC, REST, mensageria e publish/subscribe). Objetos e serviços distribuídos. Nomeação, sincronização, relógios lógicos e exclusão mútua. Replicação, consistência e consenso. Tolerância a falhas e disponibilidade. Heterogeneidade, interoperabilidade e integração de sistemas. Segurança em ambientes distribuídos. Programação concorrente e paralela. Implantação com contêineres e computação em nuvem.

## Objetivo geral

Capacitar o aluno a projetar, implementar, implantar e avaliar sistemas distribuídos confiáveis, escaláveis e seguros, aplicando princípios de engenharia de software.

## Objetivos específicos

- Compreender os modelos de sistema (síncrono, assíncrono, falhas) e as propriedades de transparência, escalabilidade e abertura.
- Comparar estilos arquiteturais e justificar escolhas de projeto (trade-offs).
- Implementar comunicação entre processos com sockets, REST, gRPC e filas de mensagens.
- Aplicar técnicas de sincronização, replicação e tolerância a falhas.
- Desenvolver programas concorrentes em Java, evitando condições de corrida e deadlocks.
- Entender o teorema CAP e os modelos de consistência (forte, eventual).
- Implantar e monitorar uma aplicação distribuída com Docker.

## Conteúdo programático

### Unidade 1 — Fundamentos e arquiteturas (N1)

1. Conceitos, características e desafios dos sistemas distribuídos; falácias da computação distribuída.
2. Modelos de sistema: interação, falhas e segurança.
3. Estilos arquiteturais: cliente-servidor, camadas, peer-to-peer, SOA, microsserviços, orientação a eventos.
4. Heterogeneidade, middleware e integração (APIs, JSON, Protocol Buffers, ESB).

### Unidade 2 — Comunicação e concorrência (N1)

5. Comunicação entre processos: sockets TCP/UDP, serialização.
6. RPC, gRPC e invocação de métodos remotos (Java RMI); objetos distribuídos.
7. APIs REST e comunicação assíncrona (RabbitMQ/Kafka, publish/subscribe).
8. Programação concorrente em Java: threads, sincronização, `java.util.concurrent`, executores e `CompletableFuture`.

### Unidade 3 — Coordenação e dados distribuídos (N2)

9. Tempo e relógios: Lamport, relógios vetoriais.
10. Exclusão mútua e eleição de líder.
11. Replicação, consistência e teorema CAP; consenso (Paxos e Raft, em nível conceitual).
12. Transações distribuídas, Saga e idempotência.

### Unidade 4 — Confiabilidade e operação (N2)

13. Tolerância a falhas: detecção, redundância, retry, timeout e circuit breaker.
14. Sistemas peer-to-peer: DHT, BitTorrent e blockchain como estudo de caso.
15. Segurança: autenticação, autorização (OAuth2/JWT) e TLS.
16. Contêineres, orquestração e observabilidade (logs, métricas e tracing).

## Metodologia

Aulas expositivas dialogadas, laboratórios práticos semanais, estudos de caso (Netflix, Google, Amazon, Uber), projeto integrador em equipe e uso de Git/GitHub.

## Avaliação sugerida

- **N1:** prova teórica e prática (comunicação, sockets, concorrência) + laboratórios.
- **N2:** projeto integrador (aplicação distribuída com ao menos 3 serviços comunicando entre si, containerizada, com README e apresentação) + prova.

## Projeto integrador (sugestão)

Sistema de pedidos com serviços independentes (catálogo, pedidos, pagamento), comunicação REST e por fila de mensagens, tratamento de falhas simulado (derrubar um serviço), Docker Compose e relatório de decisões arquiteturais.

## Bibliografia básica

- COULOURIS, George; DOLLIMORE, Jean; KINDBERG, Tim; BLAIR, Gordon. *Sistemas distribuídos: conceitos e projeto*. 5. ed. Porto Alegre: Bookman, 2013.
- TANENBAUM, Andrew S.; VAN STEEN, Maarten. *Sistemas distribuídos: princípios e paradigmas*. 2. ed. São Paulo: Pearson Prentice Hall, 2007.
- KUROSE, James F.; ROSS, Keith W. *Redes de computadores e a Internet: uma abordagem top-down*. Edição mais recente disponível na biblioteca.

## Bibliografia complementar

- KLEPPMANN, Martin. *Designing data-intensive applications*. O'Reilly, 2017.
- NEWMAN, Sam. *Building microservices*. 2. ed. O'Reilly, 2021.
- RICHARDSON, Chris. *Microservices patterns*. Manning, 2018.
- GOETZ, Brian et al. *Java concurrency in practice*. Addison-Wesley, 2006.
- DEITEL, P. J.; DEITEL, H. M. *Java: como programar*. Pearson (edição mais recente).
- TANENBAUM, Andrew S.; BOS, Herbert. *Sistemas operacionais modernos*. Pearson (edição mais recente).
- STALLINGS, William. *Redes e sistemas de comunicação de dados*. Elsevier, 2005.
- COMER, Douglas E. *Interligação em rede com TCP/IP*. Campus.
