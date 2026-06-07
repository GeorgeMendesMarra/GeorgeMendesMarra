# Podcast Manager

## Descrição

O Podcast Manager é uma aplicação inspirada no estilo da Netflix, desenvolvida para centralizar episódios de podcasts organizados por categorias. Com um design focado na experiência do usuário, a plataforma permite navegar de forma intuitiva por diferentes conteúdos em formato de vídeo, facilitando o acesso e a organização dos episódios.

## Funcionalidades

- **Listagem de episódios por categoria:** Os episódios são agrupados em categorias como saúde, bodybuilder, mentalidade e humor, permitindo que os usuários descubram novos conteúdos de forma organizada.
- **Busca por nome de podcast:** Os usuários podem filtrar episódios específicos utilizando o nome do podcast, agilizando o acesso ao conteúdo desejado.

## Implementação

### Listagem de episódios por categoria

- **Endpoint:** `GET /list`
- **Descrição:** Retorna uma lista de episódios de podcasts agrupados por suas respectivas categorias.
- **Exemplo de resposta:**

```json
[
  {
    "podcastName": "flow",
    "episode": "CBUM - Flow #319",
    "videoId": "pQSuQmUfS30",
    "cover": "https://i.ytimg.com/vi/pQSuQmUfS30/maxresdefault.jpg",
    "link": "https://www.youtube.com/watch?v=pQSuQmUfS30",
    "categories": ["saúde", "esporte", "bodybuilder"]
  },
  {
    "podcastName": "flow",
    "episode": "RUBENS BARRICHELLO - Flow #339",
    "videoId": "4KDGTdiOV4I",
    "cover": "https://i.ytimg.com/vi/4KDGTdiOV4I/maxresdefault.jpg",
    "link": "https://www.youtube.com/watch?v=4KDGTdiOV4I",
    "categories": ["esporte", "corrida"]
  }
]
```

### Busca por nome de podcast

- **Endpoint:** `GET /episode?podcastName={nome}`
- **Descrição:** Retorna uma lista de episódios correspondentes ao nome do podcast informado como parâmetro.
- **Exemplo de requisição:** `GET /episode?podcastName=flow`

## Tecnologias Utilizadas

- **[TypeScript](https://www.typescriptlang.org/):** Linguagem principal que adiciona tipagem estática ao JavaScript, aumentando a robustez e a manutenibilidade do código.
- **[Tsup](https://github.com/egoist/tsup):** Ferramenta rápida e sem configuração para empacotar projetos TypeScript.
- **[Tsx](https://github.com/egoist/tsx):** Executor TypeScript que permite rodar arquivos `.ts` diretamente sem compilação manual.
- **[Node.js](https://nodejs.org/):** Ambiente de execução JavaScript back-end que possibilita a criação da API.
- **[@types/node](https://www.npmjs.com/package/@types/node):** Definições de tipos oficiais para Node.js, garantindo maior segurança e produtividade no desenvolvimento com TypeScript.

## Como Utilizar

1. Clone este repositório.
2. Instale as dependências com o comando `npm install`.
3. Inicie o servidor em modo de desenvolvimento executando `npm run start:dev`.
4. Acesse os endpoints disponíveis para listar episódios ou filtrar por nome de podcast.

## Contribuição

Contribuições são muito bem-vindas! Sinta-se à vontade para abrir *issues* relatar melhorias ou enviar *pull requests* com sugestões e correções.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
