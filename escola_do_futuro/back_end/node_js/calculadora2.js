// Importa o módulo nativo 'readline' do Node.js, usando a versão baseada em Promises.
// Isso permite que utilizemos 'await' para esperar a resposta do usuário sem travar o programa.
const readline = require('readline/promises');

// Cria a interface de comunicação. 
// 'input' recebe o que você digita (teclado) e 'output' exibe as mensagens no terminal.
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

/**
 * Função assíncrona que gerencia o fluxo da calculadora.
 */
async function calcular() {
  try {
    // 'await rl.question' pausa a execução até que o usuário responda.
    // 'parseFloat' converte o texto digitado (string) em um número decimal.
    const n1 = parseFloat(await rl.question('Primeiro número: '));
    
    // Captura o operador matemático como uma string.
    const op = await rl.question('Operador (+, -, *, /): ');
    
    const n2 = parseFloat(await rl.question('Segundo número: '));

    // DICIONÁRIO DE OPERAÇÕES:
    // Em vez de usar vários 'if' ou 'switch', usamos um objeto onde cada chave é um operador.
    // Os valores são "Arrow Functions" (funções anônimas curtas) que realizam o cálculo.
    const operacoes = {
      '+': (a, b) => a + b,
      '-': (a, b) => a - b,
      '*': (a, b) => a * b,
      // No operador '/', usamos um operador ternário para evitar a divisão por zero.
      '/': (a, b) => b !== 0 ? a / b : "Divisão por zero!"
    };

    // LÓGICA DE EXECUÇÃO:
    // 1. Verifica se o operador digitado (op) existe dentro do objeto 'operacoes'.
    // 2. Se existir, executa a função correspondente passando n1 e n2.
    // 3. Se não existir, retorna a mensagem "Operador inválido".
    const resultado = operacoes[op] ? operacoes[op](n1, n2) : "Operador inválido";
    
    // Exibe o resultado usando Template Literals (as crases `` permitem inserir variáveis com ${}).
    console.log(`\nResultado: ${resultado}`);

  } catch (err) {
    // Caso ocorra algum erro inesperado (como o usuário fechar o programa durante o input).
    console.error("Ocorreu um erro na leitura.");
  } finally {
    // O bloco 'finally' sempre executa. É fundamental para fechar a interface
    // do readline e liberar o terminal, caso contrário o programa nunca encerra.
    rl.close();
  }
}

// Chama a função para iniciar a calculadora.
calcular();
