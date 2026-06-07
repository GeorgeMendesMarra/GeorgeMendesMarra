# Podcast Menager

### Descrição

Um app ao estilo netflix, aonde possa centralizar diferentes episódio podcasts
separados por categoria

### Domínio

Podcasts feitos em vídeo

### Features

- Listar os episódios podcasts em sessões de categorias
  - [saúde, bodybuilder, mentalidade, humor]
- Filtrar episódios por nome de podcast

## Como

#### Feature:

Listar os episódios podcasts em sessões de categorias

### Como vou implementar:

GET: retorna lista de episódios

response:

```js
[
  {
    podcastName: "flow",
    episode: "CBUM - Flow #319",
    videoId:"pQSuQmUfS30"
    cover: "https://i.ytimg.com/vi/pQSuQmUfS30/maxresdefault.jpg",
    link: "https://www.youtube.com/watch?v=pQSuQmUfS30",
    categories: ["saúde", "esporte","bodybuilder"]
  },
  {
    podcastName: "flow",
    episode: "RUBENS BARRICHELLO - Flow #339",
    videoId:"4KDGTdiOV4I",
    cover: "https://i.ytimg.com/vi/4KDGTdiOV4I/maxresdefault.jpg",
    link: "https://www.youtube.com/watch?v=4KDGTdiOV4I",
    categories: ["esporte", "corrida"]
  },
]
```

GET: retorna lista de episódios baseado em um parametro enviado pelo cliente do nome do podcast
