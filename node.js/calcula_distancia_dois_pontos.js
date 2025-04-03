const geolib = require("geolib");

app.get("/distancia", (req, res) => {
    // Obtém as coordenadas passadas via query string
    const { lat1, lon1, lat2, lon2 } = req.query;

    // Verifica se todas as coordenadas foram fornecidas
    if (!lat1 || !lon1 || !lat2 || !lon2) {
        return res.status(400).json({ error: "Todas as coordenadas são obrigatórias" });
    }

    // Calcula a distância entre os dois pontos (em metros)
    const distancia = geolib.getDistance(
        { latitude: lat1, longitude: lon1 },
        { latitude: lat2, longitude: lon2 }
    );

    // Retorna a distância em metros e em quilômetros
    res.json({ distancia_metros: distancia, distancia_km: (distancia / 1000).toFixed(2) });
});

// O servidor já está rodando na porta 3000 do primeiro exemplo
