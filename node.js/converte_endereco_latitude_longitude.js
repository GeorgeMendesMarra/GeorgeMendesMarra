// converter um endereço em latitude e longitude
// http://localhost:3000/geocode?address=São Paulo, Brasil

const express = require("express");
const axios = require("axios");
require("dotenv").config();

const app = express();
const PORT = 3000;

// Endpoint para obter coordenadas a partir de um endereço
app.get("/geocode", async (req, res) => {
    try {
        const address = req.query.address; // Obtém o endereço enviado via query string

        // Se o endereço não for fornecido, retorna erro
        if (!address) return res.status(400).json({ error: "Endereço é obrigatório" });

        // Faz a requisição para a API Nominatim do OpenStreetMap
        const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(address)}`;
        const response = await axios.get(url);

        // Se nenhum resultado for encontrado, retorna erro
        if (response.data.length === 0) {
            return res.status(404).json({ error: "Endereço não encontrado" });
        }

        // Extrai latitude e longitude do primeiro resultado da busca
        const { lat, lon } = response.data[0];
        res.json({ latitude: lat, longitude: lon }); // Retorna as coordenadas
    } catch (error) {
        res.status(500).json({ error: "Erro ao obter coordenadas" });
    }
});

// Inicia o servidor na porta definida
app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});
