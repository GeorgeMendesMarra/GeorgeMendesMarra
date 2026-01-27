// Importa o módulo nativo 'readline' na versão de Promises (mais moderna)
// Isso permite usar o 'await' para esperar a resposta do utilizador
const readline = require('readline/promises');

// Configura a interface de Entrada (teclado) e Saída (ecrã/terminal)
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

/**
 * Função principal assíncrona para executar a calculadora
 */
async function executarCalculadora() {
  try {
    console.log("=== CALCULADORA NODE.JS PROFISSIONAL ===");

    // Solicita os dados ao utilizador de forma sequencial
    // parseFloat converte a string digitada num número decimal
    const n1 = parseFloat(await rl.question('Digite o primeiro número: '));
    const operador = await rl.question('Digite o operador (+, -, *, /): ');
    const n2 = parseFloat(await rl.question('Digite o segundo número: '));

    // Objeto literal que mapeia os operadores para funções específicas
    // Esta é uma alternativa mais limpa ao 'switch' ou 'if/else'
    const operacoes = {
      '+': (a, b) => a + b,
      '-': (a, b) => a - b,
      '*': (a, b) => a * b,
      '/': (a, b) => b !== 0 ? a / b : "Erro: Divisão por zero!"
    };

    // Verifica se o operador existe no nosso objeto e executa o cálculo
    let resultado;
    if (operacoes[operador]) {
        resultado = operacoes[operador](n1, n2);
    } else {
        resultado = "Operador Inválido!";
    }

    // Exibe o resultado final com formatação
    console.log(`\n--------------------------`);
    console.log(`> Cálculo: ${n1} ${operador} ${n2}`);
    console.log(`> Resultado: ${resultado}`);
    console.log(`--------------------------\n`);

  } catch (erro) {
    // Captura erros inesperados (ex: fechar o terminal bruscamente)
    console.error("Ocorreu um erro no processamento:", erro.message);
  } finally {
    // Garante que a interface do terminal seja fechada, libertando a memória
    rl.close();
  }
}

// Inicia a execução do programa
executarCalculadora();
