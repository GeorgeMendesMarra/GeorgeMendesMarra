const readline = require('readline'); // Importa o módulo readline para ler a entrada do usuário

const rl = readline.createInterface({ // Cria uma interface readline
  input: process.stdin, // Define a entrada como o teclado (stdin)
  output: process.stdout // Define a saída como o console (stdout)
});

function calculadora() { // Define a função calculadora
  rl.question('Digite o primeiro número: ', (num1) => { // Solicita o primeiro número
    rl.question('Digite o segundo número: ', (num2) => { // Solicita o segundo número
      rl.question('Digite a operação (+, -, *, /): ', (operacao) => { // Solicita a operação
        num1 = parseFloat(num1); // Converte a entrada para número de ponto flutuante
        num2 = parseFloat(num2); // Converte a entrada para número de ponto flutuante
        let resultado; // Declara a variável para armazenar o resultado

        switch (operacao) { // Inicia o bloco switch para realizar a operação
          case '+':
            resultado = num1 + num2; // Realiza a adição
            break;
          case '-':
            resultado = num1 - num2; // Realiza a subtração
            break;
          case '*':
            resultado = num1 * num2; // Realiza a multiplicação
            break;
          case '/':
            if (num2 === 0) { // Verifica se o segundo número é zero
              console.log('Erro: Divisão por zero!'); // Exibe mensagem de erro
              rl.close(); // Fecha a interface readline
              return; // Encerra a função
            }
            resultado = num1 / num2; // Realiza a divisão
            break;
          default:
            console.log('Operação inválida!'); // Exibe mensagem de erro para operação inválida
            rl.close(); // Fecha a interface readline
            return; // Encerra a função
        }

        console.log(`Resultado: ${resultado}`); // Exibe o resultado
        rl.close(); // Fecha a interface readline
      });
    });
  });
}

calculadora(); // Chama a função calculadora para iniciar o programa
