# Tutorial de Docker para Desenvolvedores Web

> Guia introdutório sobre contêineres e Docker: conceitos, comandos essenciais, Dockerfile, volumes, redes, Docker Compose e como containerizar uma API Spring Boot com banco de dados.

## Sumário

1. [O que é Docker](#1-o-que-é-docker)
2. [Contêineres × máquinas virtuais](#2-contêineres--máquinas-virtuais)
3. [Conceitos fundamentais](#3-conceitos-fundamentais)
4. [Instalação](#4-instalação)
5. [Comandos essenciais](#5-comandos-essenciais)
6. [Dockerfile](#6-dockerfile)
7. [Containerizando uma API Spring Boot](#7-containerizando-uma-api-spring-boot)
8. [Volumes e persistência](#8-volumes-e-persistência)
9. [Redes](#9-redes)
10. [Docker Compose](#10-docker-compose)
11. [Variáveis de ambiente e segredos](#11-variáveis-de-ambiente-e-segredos)
12. [Docker Hub e publicação de imagens](#12-docker-hub-e-publicação-de-imagens)
13. [Docker e nuvem (IaaS, PaaS, CaaS)](#13-docker-e-nuvem-iaas-paas-caas)
14. [Boas práticas](#14-boas-práticas)
15. [Problemas comuns](#15-problemas-comuns)
16. [Exercícios propostos](#16-exercícios-propostos)

---

## 1. O que é Docker

**Docker** é uma plataforma para **empacotar, distribuir e executar aplicações em contêineres**. Um contêiner reúne a aplicação e tudo de que ela precisa (runtime, bibliotecas, configurações) em uma unidade isolada e portável.

> **O problema clássico:** "na minha máquina funciona!". Com Docker, o mesmo contêiner roda de forma idêntica no notebook do aluno, no servidor de testes e na nuvem.

Benefícios:

- **Portabilidade** e **reprodutibilidade** do ambiente.
- **Isolamento** entre aplicações.
- **Início rápido** e uso eficiente de recursos.
- Base para **microsserviços**, **CI/CD** e **orquestração** (Kubernetes).
- Ambiente de desenvolvimento padronizado: API + banco + ferramentas com **um comando**.

---

## 2. Contêineres × máquinas virtuais

| Critério | Máquina virtual | Contêiner |
|---|---|---|
| Isolamento | Sistema operacional completo | Processo isolado (compartilha o kernel do host) |
| Tamanho | GBs | MBs |
| Inicialização | Minutos | Segundos (ou menos) |
| Densidade | Poucas por servidor | Muitas por servidor |
| Uso típico | Isolamento forte, SOs diferentes | Aplicações, microsserviços |

```
 Máquina virtual              Contêiner
┌─────────────────┐        ┌─────────────────┐
│ App A │ App B   │        │ App A │ App B   │
│ Libs  │ Libs    │        │ Libs  │ Libs    │
│ SO    │ SO      │        ├─────────────────┤
├─────────────────┤        │ Docker Engine   │
│ Hipervisor      │        ├─────────────────┤
├─────────────────┤        │ SO do host      │
│ SO do host      │        ├─────────────────┤
└─────────────────┘        │ Hardware        │
                           └─────────────────┘
```

---

## 3. Conceitos fundamentais

| Conceito | Descrição |
|---|---|
| **Imagem** | "Receita" imutável, em camadas, com tudo para executar a aplicação |
| **Contêiner** | Instância em execução de uma imagem |
| **Dockerfile** | Arquivo de texto com as instruções para construir uma imagem |
| **Registry** | Repositório de imagens (Docker Hub, GitHub Container Registry, etc.) |
| **Volume** | Armazenamento persistente fora do ciclo de vida do contêiner |
| **Rede** | Comunicação entre contêineres |
| **Docker Compose** | Define e executa aplicações com vários contêineres |

Analogia de programação: **imagem** é como uma **classe**; **contêiner** é como um **objeto** (instância).

---

## 4. Instalação

- **Windows/macOS:** [Docker Desktop](https://www.docker.com/products/docker-desktop/) (no Windows, usa o WSL 2).
- **Linux:** Docker Engine pelo repositório oficial da sua distribuição ([docs.docker.com/engine/install](https://docs.docker.com/engine/install/)).

Verificar:

```bash
docker --version
docker compose version
docker run hello-world
```

> **Licenciamento:** o Docker Desktop possui condições de uso específicas para empresas de grande porte. Para estudo e uso educacional, consulte os termos atuais no site oficial.

---

## 5. Comandos essenciais

### Imagens

```bash
docker pull nginx:latest        # baixar imagem
docker images                   # listar imagens locais
docker rmi nginx:latest         # remover imagem
docker build -t minha-api:1.0 . # construir imagem a partir do Dockerfile
```

### Contêineres

```bash
docker run nginx                         # executa (em primeiro plano)
docker run -d --name web -p 8081:80 nginx # em segundo plano, mapeando porta host:contêiner
docker ps                                # contêineres em execução
docker ps -a                             # todos (inclusive parados)
docker stop web                          # parar
docker start web                         # iniciar novamente
docker rm web                            # remover
docker logs -f web                       # acompanhar logs
docker exec -it web sh                   # abrir terminal dentro do contêiner
docker inspect web                       # detalhes (IP, volumes, etc.)
```

### Limpeza

```bash
docker system prune        # remove recursos não usados (cuidado!)
docker volume prune        # remove volumes sem uso (cuidado: apaga dados)
```

### Parâmetros úteis do `docker run`

| Opção | Função |
|---|---|
| `-d` | Executa em segundo plano |
| `-p 8080:80` | Mapeia porta do host para a do contêiner |
| `--name` | Nome do contêiner |
| `-e VAR=valor` | Define variável de ambiente |
| `-v vol:/caminho` | Monta volume |
| `--rm` | Remove o contêiner ao finalizar |
| `--network` | Conecta a uma rede |

### Primeiro teste

```bash
docker run -d --name site -p 8081:80 nginx
# abra http://localhost:8081
docker stop site && docker rm site
```

---

## 6. Dockerfile

### Instruções principais

| Instrução | Função |
|---|---|
| `FROM` | Imagem base |
| `WORKDIR` | Diretório de trabalho |
| `COPY` / `ADD` | Copia arquivos para a imagem |
| `RUN` | Executa comando **durante a construção** |
| `ENV` | Define variável de ambiente |
| `EXPOSE` | Documenta a porta usada |
| `CMD` / `ENTRYPOINT` | Comando executado **ao iniciar** o contêiner |
| `ARG` | Argumento disponível só no *build* |

### Exemplo: site estático com Nginx (útil para o front-end da disciplina anterior)

```dockerfile
FROM nginx:alpine
COPY ./site /usr/share/nginx/html
EXPOSE 80
```

```bash
docker build -t meu-site .
docker run -d -p 8080:80 meu-site
```

### Arquivo `.dockerignore`

Evita enviar arquivos desnecessários ao *build*:

```
target/
.git/
.idea/
*.log
.env
```

---

## 7. Containerizando uma API Spring Boot

### 7.1 Dockerfile multi-stage (recomendado)

```dockerfile
# ---------- Estágio 1: build ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q clean package -DskipTests

# ---------- Estágio 2: execução ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Vantagens do **multi-stage**: a imagem final contém só o JRE e o `.jar` (bem menor), sem Maven nem código-fonte. Copiar o `pom.xml` antes do `src` aproveita o **cache de camadas** ao recompilar.

### 7.2 Construir e executar

```bash
docker build -t biblioteca-api:1.0 .
docker run -d --name api -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  biblioteca-api:1.0
```

---

## 8. Volumes e persistência

Os dados dentro de um contêiner **são perdidos** quando ele é removido. Para persistir (ex.: banco de dados), use **volumes**.

```bash
docker volume create dados-pg
docker run -d --name pg \
  -e POSTGRES_PASSWORD=senha123 -e POSTGRES_DB=biblioteca \
  -v dados-pg:/var/lib/postgresql/data \
  -p 5432:5432 postgres:16
```

| Tipo | Exemplo | Uso |
|---|---|---|
| **Volume nomeado** | `-v dados-pg:/var/lib/postgresql/data` | Dados gerenciados pelo Docker (recomendado para bancos) |
| **Bind mount** | `-v ./config:/app/config` | Pasta do host (bom no desenvolvimento) |
| **tmpfs** | `--tmpfs /tmp` | Memória, dados temporários |

---

## 9. Redes

Contêineres na **mesma rede** se comunicam pelo **nome do serviço/contêiner** (DNS interno do Docker).

```bash
docker network create rede-app
docker run -d --name pg --network rede-app -e POSTGRES_PASSWORD=senha123 postgres:16
docker run -d --name api --network rede-app -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://pg:5432/biblioteca biblioteca-api:1.0
```

> Dentro da rede, a API acessa o banco em `pg:5432`, **não** em `localhost` (dentro do contêiner, `localhost` é o próprio contêiner). Este é um dos erros mais comuns de iniciantes.

Tipos de rede: `bridge` (padrão), `host`, `none` e `overlay` (orquestração).

---

## 10. Docker Compose

O **Compose** descreve toda a aplicação (API + banco + outros) em um arquivo YAML e sobe tudo com **um comando**.

### `docker-compose.yml` (API + PostgreSQL)

```yaml
services:
  db:
    image: postgres:16
    container_name: biblioteca-db
    environment:
      POSTGRES_DB: biblioteca
      POSTGRES_USER: ${DB_USER}
      POSTGRES_PASSWORD: ${DB_PASSWORD}
    volumes:
      - dados-pg:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${DB_USER} -d biblioteca"]
      interval: 5s
      timeout: 5s
      retries: 10

  api:
    build: .
    container_name: biblioteca-api
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: prod
      DB_URL: jdbc:postgresql://db:5432/biblioteca
      DB_USER: ${DB_USER}
      DB_PASSWORD: ${DB_PASSWORD}
    depends_on:
      db:
        condition: service_healthy

volumes:
  dados-pg:
```

### Arquivo `.env` (na mesma pasta; **não versionar**)

```
DB_USER=biblioteca
DB_PASSWORD=troque_esta_senha
```

### Comandos do Compose

```bash
docker compose up -d --build   # constrói e sobe tudo em segundo plano
docker compose ps              # status dos serviços
docker compose logs -f api     # logs da API
docker compose exec db psql -U biblioteca   # terminal do banco
docker compose down            # para e remove contêineres e rede
docker compose down -v         # idem, e remove os volumes (apaga dados!)
```

### Adicionando o front-end (Nginx)

```yaml
  web:
    image: nginx:alpine
    ports:
      - "80:80"
    volumes:
      - ./site:/usr/share/nginx/html:ro
    depends_on:
      - api
```

Assim, o projeto inteiro (front-end + API + banco) sobe com `docker compose up`.

---

## 11. Variáveis de ambiente e segredos

- **Nunca** coloque senhas no `Dockerfile` nem no código-fonte.
- Use `.env` (no `.gitignore`) para desenvolvimento; em produção, use o mecanismo de **secrets** da plataforma (Docker Secrets, Kubernetes Secrets, cofres de segredos).
- Versione um arquivo `.env.example` apenas com os **nomes** das variáveis:

```
DB_USER=
DB_PASSWORD=
```

- Execute como usuário **não-root** quando possível:

```dockerfile
RUN addgroup --system app && adduser --system --ingroup app app
USER app
```

---

## 12. Docker Hub e publicação de imagens

```bash
docker login
docker tag biblioteca-api:1.0 seuusuario/biblioteca-api:1.0
docker push seuusuario/biblioteca-api:1.0
```

Para baixar em outro computador/servidor:

```bash
docker pull seuusuario/biblioteca-api:1.0
```

Alternativas ao Docker Hub: **GitHub Container Registry (ghcr.io)**, GitLab Registry e registries dos provedores de nuvem. Use **tags de versão** (`1.0`, `1.1`) em vez de depender apenas de `latest`.

---

## 13. Docker e nuvem (IaaS, PaaS, CaaS)

Relação com o conteúdo de WebServices e nuvem:

| Modelo | Como o Docker se encaixa |
|---|---|
| **IaaS** | Instalar o Docker em uma VM (ex.: EC2, Droplet) e executar `docker compose up` |
| **PaaS** | Plataformas que aceitam sua imagem/Dockerfile (ex.: Render, Railway, Google Cloud Run, Azure App Service) |
| **CaaS** | Orquestração de contêineres gerenciada (Kubernetes: EKS, GKE, AKS) |

**Kubernetes** (K8s) é o orquestrador mais usado: agenda contêineres em vários servidores, escala, reinicia falhas e faz *deploys* graduais. É assunto de estudo posterior; aqui o objetivo é dominar imagens, Compose e boas práticas.

---

## 14. Boas práticas

- Use imagens base **oficiais e enxutas** (`-alpine`, `-slim`, `-jre`).
- **Multi-stage build** para reduzir tamanho e superfície de ataque.
- Um **processo principal por contêiner** (API em um, banco em outro).
- Ordene o Dockerfile para aproveitar **cache** (dependências antes do código).
- Sempre use `.dockerignore`.
- **Fixe versões** das imagens (`postgres:16`, não `postgres:latest`).
- Configuração via **variáveis de ambiente**; segredos fora da imagem.
- Contêineres são **efêmeros**: dados importantes em **volumes**.
- Adicione **healthchecks** e *logs* na saída padrão (stdout).
- Rode como usuário **não-root**; atualize imagens regularmente e escaneie vulnerabilidades (ex.: `docker scout`, Trivy).

---

## 15. Problemas comuns

| Sintoma | Causa provável | Solução |
|---|---|---|
| `port is already allocated` | Porta do host em uso | Mudar o mapeamento (`-p 8081:8080`) ou parar o processo |
| API não conecta ao banco | Usando `localhost` em vez do nome do serviço | Usar `db:5432` (nome do serviço no Compose) |
| API sobe antes do banco | Banco ainda iniciando | `depends_on` com `condition: service_healthy` e *healthcheck* |
| Dados sumiram | Volume removido (`down -v`) ou sem volume | Usar volume nomeado e cuidado com `-v` |
| Alteração no código não aparece | Imagem antiga | `docker compose up -d --build` |
| `permission denied` no Linux | Usuário fora do grupo `docker` | `sudo usermod -aG docker $USER` e reiniciar a sessão |
| Build muito lento | Sem cache/`.dockerignore` | Reordenar o Dockerfile e criar `.dockerignore` |

---

## 16. Exercícios propostos

1. Execute um contêiner Nginx na porta 8081 e altere a página inicial usando um *bind mount*.
2. Crie um Dockerfile para servir o site do projeto de Programação Web I com Nginx.
3. Escreva um Dockerfile **multi-stage** para a API Spring Boot da disciplina e compare o tamanho da imagem com um Dockerfile de estágio único.
4. Suba um PostgreSQL com volume nomeado; remova o contêiner, recrie-o e confirme que os dados persistiram.
5. Escreva um `docker-compose.yml` com API + PostgreSQL usando `.env` e *healthcheck*.
6. Acrescente o front-end (Nginx) ao Compose e faça-o consumir a API.
7. Publique a imagem da API no Docker Hub (ou no ghcr.io) com uma tag de versão.
8. Explique, com suas palavras, a diferença entre imagem e contêiner e entre volume e *bind mount*.

---

## Referências

- Documentação oficial: [docs.docker.com](https://docs.docker.com)
- Docker Hub: [hub.docker.com](https://hub.docker.com)
- Play with Docker (laboratório online): [labs.play-with-docker.com](https://labs.play-with-docker.com)
- Docker Compose: [docs.docker.com/compose](https://docs.docker.com/compose/)
- Kubernetes: [kubernetes.io](https://kubernetes.io)
