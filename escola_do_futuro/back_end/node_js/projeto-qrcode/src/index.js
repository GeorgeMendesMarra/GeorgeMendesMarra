// Importa a biblioteca 'prompt' para capturar entradas do usuário no terminal
import prompt from "prompt";

// Importa o esquema de validação para as opções do menu principal
import promptSchemaMain from "./prompts-schema/prompt-schema-main.js";

// Importa a função responsável por criar QR Codes
import createQRCode from "./services/qr-code/create.js";
// Importa a função responsável por criar senhas
import createPassword from "./services/password/create.js";

/**
 * Função principal que inicia a aplicação
 * Apresenta um menu interativo para o usuário escolher entre:
 * 1 - Criar QR Code
 * 2 - Criar senha
 */
async function main() {
  // Obtém a escolha do usuário com base no esquema definido
  prompt.get(promptSchemaMain, async (err, choose) => {
    // Se houver erro na entrada, exibe no console
    if (err) console.log(err);

    // Se o usuário escolheu a opção 1, chama a função para criar QR Code
    if (choose.select == 1) await createQRCode();
    
    // Se o usuário escolheu a opção 2, chama a função para criar senha
    if (choose.select == 2) await createPassword();
  });

  // Inicia o prompt para capturar as entradas do usuário
  prompt.start();
}

// Executa a função principal
main();
