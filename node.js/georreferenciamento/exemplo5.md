Usar **PostGIS** com Node.js envolve a conexão a um banco de dados PostgreSQL com a extensão PostGIS ativada e o uso de uma biblioteca Node.js para interagir com os dados espaciais. A melhor abordagem é usar um ORM (Object-Relational Mapper) como o **Sequelize**, que tem suporte para os tipos de dados do PostGIS.

-----

### Passo 1: Configurar o Ambiente

1.  **Instale o PostgreSQL com PostGIS**: Certifique-se de ter o PostgreSQL instalado e a extensão PostGIS habilitada no seu banco de dados. Você pode habilitá-la com o comando SQL:
    ```sql
    CREATE EXTENSION postgis;
    ```
2.  **Crie um Projeto Node.js**: Crie uma pasta para o seu projeto e inicialize-o:
    ```bash
    mkdir postgis-exemplo
    cd postgis-exemplo
    npm init -y
    ```
3.  **Instale as Dependências**: Instale o Sequelize (o ORM), o driver do PostgreSQL (`pg`) e o `Sequelize-PostGIS`, que fornece suporte adicional aos tipos de dados geoespaciais.
    ```bash
    npm install sequelize pg sequelize-postgis
    ```

-----

### Passo 2: Definir o Modelo de Dados

Vamos criar um modelo para representar locais, como pontos de interesse, que terão uma coluna de localização espacial. Crie um arquivo chamado `models.js`.

```javascript
const { Sequelize, DataTypes } = require('sequelize');
const { Geometry, Point } = require('sequelize-postgis');

// Conecte ao seu banco de dados PostgreSQL com PostGIS
const sequelize = new Sequelize('seu_banco', 'seu_usuario', 'sua_senha', {
    host: 'localhost',
    dialect: 'postgres'
});

// Defina o modelo 'Local' com uma coluna de localização do tipo 'Point'
const Local = sequelize.define('Local', {
    nome: {
        type: DataTypes.STRING,
        allowNull: false
    },
    // Coluna de geolocalização. O tipo 'Point' é do Sequelize-PostGIS.
    localizacao: {
        type: Point,
        allowNull: false
    }
}, {
    tableName: 'locais'
});

// Sincronize o modelo com o banco de dados (isso cria a tabela)
async function sincronizar() {
    await sequelize.sync({ force: true });
    console.log("Tabela 'locais' criada/sincronizada.");
}

module.exports = {
    sequelize,
    Local,
    sincronizar
};
```

⚠️ **Lembre-se de substituir** `'seu_banco'`, `'seu_usuario'` e `'sua_senha'` pelos seus dados de conexão.

-----

### Passo 3: Realizar Operações com Dados Espaciais

Agora, vamos criar um arquivo principal (`app.js`) para interagir com o modelo e realizar operações comuns do PostGIS.

```javascript
const { sequelize, Local, sincronizar } = require('./models');
const { fn, col } = require('sequelize');

async function main() {
    try {
        await sincronizar();

        // Insere alguns dados com coordenadas
        await Local.bulkCreate([
            { nome: 'Museu de Arte de São Paulo', localizacao: { type: 'Point', coordinates: [-46.6562, -23.5613] } },
            { nome: 'Parque Ibirapuera', localizacao: { type: 'Point', coordinates: [-46.6575, -23.5888] } },
            { nome: 'Catedral da Sé', localizacao: { type: 'Point', coordinates: [-46.6346, -23.5504] } }
        ]);
        console.log("Locais inseridos com sucesso!");

        // --- Exemplo 1: Encontrar locais próximos a um ponto ---
        const pontoDeReferencia = {
            type: 'Point',
            coordinates: [-46.658, -23.57] // Um ponto na Av. Paulista
        };

        const locaisProximos = await Local.findAll({
            // Usa a função ST_DWithin do PostGIS
            where: fn('ST_DWithin', col('localizacao'), fn('ST_GeomFromGeoJSON', JSON.stringify(pontoDeReferencia)), 1500),
            // Adiciona a distância ao resultado (em metros)
            attributes: {
                include: [[fn('ST_Distance', col('localizacao'), fn('ST_GeomFromGeoJSON', JSON.stringify(pontoDeReferencia))), 'distancia_metros']]
            }
        });

        console.log("\n--- Locais a até 1.5km de um ponto na Av. Paulista ---");
        locaisProximos.forEach(local => {
            console.log(`  - ${local.nome} (${local.get('distancia_metros').toFixed(2)}m de distância)`);
        });

        // --- Exemplo 2: Ordenar locais por proximidade ---
        const locaisOrdenados = await Local.findAll({
            order: fn('ST_Distance', col('localizacao'), fn('ST_GeomFromGeoJSON', JSON.stringify(pontoDeReferencia)))
        });

        console.log("\n--- Locais ordenados por proximidade ---");
        locaisOrdenados.forEach(local => {
            console.log(`  - ${local.nome}`);
        });

    } catch (error) {
        console.error("Erro ao executar a aplicação:", error);
    } finally {
        await sequelize.close();
    }
}

main();
```

### Passo 4: Executar o Programa

No terminal, execute o arquivo:

```bash
node app.js
```

A saída mostrará os locais que foram inseridos, a lista de locais próximos ao ponto de referência e a lista ordenada por proximidade, demonstrando o poder do PostGIS para realizar consultas espaciais complexas de forma eficiente diretamente do Node.js.
