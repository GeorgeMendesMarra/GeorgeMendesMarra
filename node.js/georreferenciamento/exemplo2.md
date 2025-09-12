-----

### Exemplo de Análise Espacial com Turf.js

1.  **Instalação**: Primeiro, você precisa instalar a biblioteca Turf.js no seu projeto.

    ```bash
    npm install @turf/turf
    ```

2.  **Código**: Crie um arquivo chamado `analise.js`. Este código define uma área de entrega (um polígono) e um ponto de entrega, e então usa o Turf para verificar se o ponto está dentro da área.

    ```javascript
    // Importa o Turf.js
    const turf = require('@turf/turf');

    // 1. Defina a área de entrega como um polígono GeoJSON.
    // As coordenadas representam um quadrado imaginário (minhas latitudes e longitudes de exemplo).
    const zonaDeEntrega = turf.polygon([
        [
            [-46.66, -23.57], // Canto superior esquerdo
            [-46.65, -23.57], // Canto superior direito
            [-46.65, -23.56], // Canto inferior direito
            [-46.66, -23.56], // Canto inferior esquerdo
            [-46.66, -23.57]  // Volta para o ponto inicial para fechar o polígono
        ]
    ]);

    // 2. Defina os pontos de entrega (ponto de interesse).
    const pontoDentro = turf.point([-46.655, -23.565]);  // Este ponto está dentro da zona.
    const pontoFora = turf.point([-46.64, -23.56]);     // Este ponto está fora da zona.

    // 3. Use a função 'booleanPointInPolygon' para verificar a localização.
    const isDentro = turf.booleanPointInPolygon(pontoDentro, zonaDeEntrega);
    const isFora = turf.booleanPointInPolygon(pontoFora, zonaDeEntrega);

    // 4. Imprima os resultados
    console.log(`Coordenadas da Zona de Entrega: `);
    console.log(JSON.stringify(zonaDeEntrega.geometry.coordinates));

    console.log("\n--- Análise de Pontos ---");

    console.log(`O ponto ${JSON.stringify(pontoDentro.geometry.coordinates)} está na zona? ${isDentro ? 'Sim' : 'Não'}`);
    console.log(`O ponto ${JSON.stringify(pontoFora.geometry.coordinates)} está na zona? ${isFora ? 'Sim' : 'Não'}`);

    // Exemplo extra: Calcule a distância entre dois pontos (em quilômetros)
    const distancia = turf.distance(pontoDentro, pontoFora, { units: 'kilometers' });
    console.log(`\nDistância entre os pontos: ${distancia.toFixed(2)} km`);

    ```

3.  **Execução**:

    ```bash
    node analise.js
    ```

4.  **Saída Esperada**:

    ```
    Coordenadas da Zona de Entrega:
    [[[-46.66,-23.57],[-46.65,-23.57],[-46.65,-23.56],[-46.66,-23.56],[-46.66,-23.57]]]

    --- Análise de Pontos ---
    O ponto [-46.655,-23.565] está na zona? Sim
    O ponto [-46.64,-23.56] está na zona? Não

    Distância entre os pontos: 1.55 km
    ```

Este exemplo mostra como o Turf.js facilita a realização de **análises espaciais complexas**, indo além da simples conversão de endereço, e é ideal para lógica de negócio baseada em localização, como serviços de entrega, aplicativos de encontros ou rastreamento de ativos.
