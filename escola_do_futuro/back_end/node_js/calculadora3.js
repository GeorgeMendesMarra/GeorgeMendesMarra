// Importa o módulo readline para leitura de dados no terminal
const readline = require('readline');

// Cria a interface de entrada e saída
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

// Exibe o título do programa
console.log("--- Calculadora Node.js ---");

// Solicita o primeiro número
rl.question('Digite o primeiro número: ', (num1) => {

  // Converte para número
  const n1 = parseFloat(num1);

  // Valida se o primeiro valor é um número válido
  if (isNaN(n1)) {
    console.log("❌ Erro: O primeiro valor não é um número válido.");
    rl.close();
    return;
  }

  // Solicita o operador
  rl.question('Digite o operador (+, -, *, /): ', (operador) => {

    // Solicita o segundo número
    rl.question('Digite o segundo número: ', (num2) => {

      // Converte para número
      const n2 = parseFloat(num2);

      // Valida se o segundo valor é um número válido
      if (isNaN(n2)) {
        console.log("❌ Erro: O segundo valor não é um número válido.");
        rl.close();
        return;
      }

      // Variável para armazenar o resultado
      let resultado;

      // Estrutura de decisão para o operador
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
          // Verifica divisão por zero
          resultado = n2 !== 0 ? n1 / n2 : "Erro: Divisão por zero!";
          break;

        default:
          resultado = "Operador inválido!";
      }

      // Exibe o resultado
      console.log(`\n> Resultado: ${n1} ${operador} ${n2} = ${resultado}`);

      // Fecha a interface
      rl.close();
    });
  });
});
