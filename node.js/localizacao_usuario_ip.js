// Localização do Usuário pelo IP
// API ip-api.com para determinar a localização do usuário com base no IP.
// http://localhost:3000/localizacao


app.get("/localizacao", async (req, res) => {
    try {
        const ip = req.ip || "8.8.8.8"; // Obtém o IP do usuário (ou usa um IP de teste)

        // Faz a requisição para a API do ip-api.com
        const response = await axios.get(`http://ip-api.com/json/${ip}`);

        // Retorna as informações da localização
        res.json(response.data);
    } catch (error) {
        res.status(500).json({ error: "Erro ao obter localização" });
    }
});
