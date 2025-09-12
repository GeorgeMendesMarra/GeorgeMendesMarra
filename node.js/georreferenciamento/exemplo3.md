-----

### Exemplo de Geocodificação e Geocodificação Reversa

1.  **Instalação**: Primeiro, você precisa instalar a biblioteca `node-geocoder`.

    ```bash
    npm install node-geocoder
    ```

2.  **Código**: Crie um arquivo chamado `geocoder.js` e adicione o código abaixo. Ele demonstra como converter um endereço em coordenadas e, em seguida, como converter essas coordenadas de volta para um endereço.

    ```javascript
    const NodeGeocoder = require('node-geocoder');

    // Configuração do provedor de geocodificação.
    // Usamos OpenStreetMap, que é gratuito e não precisa de chave de API.
    const options = {
        provider: 'openstreetmap'
    };

    const geocoder = NodeGeocoder(options);

    // Endereço para geocodificação (converter para coordenadas).
    const endereco = 'Rua dos Pinheiros, 1200, São Paulo, SP';

    // Coordenadas para geocodificação reversa (converter para endereço).
    const coordenadas = {
        lat: -23.5675,
        lon: -46.6698
    };

    async function executarGeocodificacao() {
        try {
            // --- Parte 1: Geocodificação (Endereço para Coordenadas) ---
            console.log(`Buscando coordenadas para o endereço: "${endereco}"...`);
            const resultadoGeocode = await geocoder.geocode(endereco);
            
            if (resultadoGeocode.length > 0) {
                const { latitude, longitude } = resultadoGeocode[0];
                console.log(`  Coordenadas encontradas: Latitude: ${latitude}, Longitude: ${longitude}`);
            } else {
                console.log('  Endereço não encontrado.');
            }

            console.log('\n----------------------------------------\n');

            // --- Parte 2: Geocodificação Reversa (Coordenadas para Endereço) ---
            console.log(`Buscando endereço para as coordenadas: (${coordenadas.lat}, ${coordenadas.lon})...`);
            const resultadoReverse = await geocoder.reverse(coordenadas);

            if (resultadoReverse.length > 0) {
                const { formattedAddress } = resultadoReverse[0];
                console.log(`  Endereço encontrado: "${formattedAddress}"`);
            } else {
                console.log('  Nenhum endereço encontrado para as coordenadas.');
            }
        
        } catch (err) {
            console.error('Ocorreu um erro:', err);
        }
    }

    executarGeocodificacao();
    ```

3.  **Execução**: Salve o arquivo e, no terminal, execute-o com o Node.js.

    ```bash
    node geocoder.js
    ```

4.  **Saída Esperada**:

    ```
    Buscando coordenadas para o endereço: "Rua dos Pinheiros, 1200, São Paulo, SP"...
      Coordenadas encontradas: Latitude: -23.5675, Longitude: -46.6698

    ----------------------------------------

    Buscando endereço para as coordenadas: (-23.5675, -46.6698)...
      Endereço encontrado: "Rua dos Pinheiros, 1200, São Paulo, SP, Brasil"
    ```

Este exemplo demonstra como a mesma biblioteca pode ser usada para resolver os dois problemas mais comuns em georreferenciamento de forma simples e direta, conectando-se facilmente a diferentes serviços de mapas.
