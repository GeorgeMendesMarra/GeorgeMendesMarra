// Importa o módulo readline, usado para ler dados do teclado no terminal
const readline = require('readline');

// Cria a interface de leitura e escrita (entrada e saída padrão)
const rl = readline.createInterface({
  input: process.stdin,   // Entrada: teclado
  output: process.stdout  // Saída: terminal
});

// Exibe o título da calculadora
console.log("--- Calculadora Node.js ---");

// Solicita o primeiro número ao usuário
rl.question('Digite o primeiro número: ', (num1) => {

  // Solicita o operador matemático
  rl.question('Digite o operador (+, -, *, /): ', (operador) => {

    // Solicita o segundo número
    rl.question('Digite o segundo número: ', (num2) => {

      // Converte as entradas de texto para números reais
      const n1 = parseFloat(num1);
      const n2 = parseFloat(num2);

      // Variável que armazenará o resultado
      let resultado;

      // Verifica qual operador foi digitado
      switch (operador) {
        case '+':
          resultado = n1 + n2;
          break;

        case '-':
          resultado = n1 - n2;
          break;

        case '*':
          resultado = n1 * n2;
          break;

        case '/':
          // Evita divisão por zero
          resultado = n2 !== 0 ? n1 / n2 : "Erro: Divisão por zero!";
          break;

        default:
          // Caso o operador seja inválido
          resultado = "Operador inválido!";
      }

      // Exibe o resultado final da operação
      console.log(`\n> Resultado: ${n1} ${operador} ${n2} = ${resultado}`);

      // Fecha a interface de leitura
      rl.close();
    });
  });
});
