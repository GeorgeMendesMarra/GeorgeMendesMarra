import createApp from "./app";

const app = createApp();
const port = process.env.PORT; // indica a porta para o servidor

app.listen(port, ()=> {
    console.log(`Aplicação rodando em: http://localhost:${port}/api`)
});
