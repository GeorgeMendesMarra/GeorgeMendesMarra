-----

### Exemplo de Manipulação de GeoJSON com SimpleFeature

Neste exemplo, você verá como:

1.  Criar um objeto de ponto GeoJSON.

2.  Criar um objeto de polígono GeoJSON.

3.  Imprimir a estrutura de ambos.

4.  **Instalação**: Primeiro, você precisa instalar a biblioteca `simplefeature`.

    ```bash
    npm install simplefeature
    ```

5.  **Código**: Crie um arquivo chamado `geojson_exemplo.js` e adicione o código a seguir.

    ```javascript
    // Importa a classe Feature do SimpleFeature
    const { Feature } = require('simplefeature');

    // --- Parte 1: Criando um Ponto GeoJSON ---

    // Coordenadas do ponto (Longitude, Latitude)
    const coordenadasPonto = [-46.6346, -23.5504];

    // Propriedades adicionais para o ponto
    const propriedadesPonto = {
        name: "Praça da Sé",
        type: "Ponto de Interesse"
    };

    // Cria um objeto de ponto usando a classe Feature
    const ponto = new Feature({
        type: "Point",
        coordinates: coordenadasPonto
    }, propriedadesPonto);

    console.log("--- Ponto GeoJSON Criado ---");
    // Imprime o objeto GeoJSON completo
    console.log(JSON.stringify(ponto, null, 2));


    // --- Parte 2: Criando um Polígono GeoJSON ---

    // Coordenadas do polígono (uma lista de pontos fechada)
    const coordenadasPoligono = [[
        [-46.66, -23.57],
        [-46.65, -23.57],
        [-46.65, -23.56],
        [-46.66, -23.56],
        [-46.66, -23.57] // O último ponto deve ser igual ao primeiro
    ]];

    // Propriedades para o polígono
    const propriedadesPoligono = {
        name: "Área de Entrega",
        type: "Zona de Serviço"
    };

    // Cria um objeto de polígono
    const poligono = new Feature({
        type: "Polygon",
        coordinates: coordenadasPoligono
    }, propriedadesPoligono);

    console.log("\n--- Polígono GeoJSON Criado ---");
    // Imprime o objeto GeoJSON completo
    console.log(JSON.stringify(poligono, null, 2));

    ```

6.  **Execução**: Salve o arquivo e, no terminal, execute-o com o Node.js.

    ```bash
    node geojson_exemplo.js
    ```

7.  **Saída Esperada**:

    ```json
    --- Ponto GeoJSON Criado ---
    {
      "type": "Feature",
      "geometry": {
        "type": "Point",
        "coordinates": [
          -46.6346,
          -23.5504
        ]
      },
      "properties": {
        "name": "Praça da Sé",
        "type": "Ponto de Interesse"
      }
    }

    --- Polígono GeoJSON Criado ---
    {
      "type": "Feature",
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [
              -46.66,
              -23.57
            ],
            [
              -46.65,
              -23.57
            ],
            [
              -46.65,
              -23.56
            ],
            [
              -46.66,
              -23.56
            ],
            [
              -46.66,
              -23.57
            ]
          ]
        ]
      },
      "properties": {
        "name": "Área de Entrega",
        "type": "Zona de Serviço"
      }
    }
    ```

Este exemplo mostra como a biblioteca `SimpleFeature` abstrai a complexidade do formato GeoJSON, permitindo que você crie e manipule facilmente objetos geográficos para uso em mapas, APIs ou para armazenamento em bancos de dados.
