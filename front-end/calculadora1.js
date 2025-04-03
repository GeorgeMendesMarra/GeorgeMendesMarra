function calculadora1() {
  // Solicita ao usuário que escolha uma operação
  let operacao = prompt(
    "Escolha uma operação:\n 1 - Soma (+)\n 2 - Subtração (-)\n 3 - Multiplicação (*)\n 4 - Divisão (/)"
  );

  // Verifica se a operação escolhida é válida
  if (!operacao || operacao >= 5) {
    alert("Operação inválida!");
    calculadora(); // Chama a função calculadora() novamente para solicitar uma operação válida
  } else {
    // Solicita os números para realizar o cálculo
    let n1 = Number(prompt("Insira o primeiro valor:"));
    let n2 = Number(prompt("Insira o segundo valor:"));
    let resultado;

    // Verifica se os números inseridos são válidos
    if (!n1 || !n2) {
      alert("Parâmetros inválidos!");
      calculadora(); // Chama a função calculadora() novamente para solicitar números válidos
    } else {
      // Funções para realizar as operações matemáticas
      function soma() {
        resultado = n1 + n2;
        alert(`${n1} + ${n2} = ${resultado}`);
        novaOperacao(); // Pergunta se o usuário deseja fazer outra operação
      }

      function subtracao() {
        resultado = n1 - n2;
        alert(`${n1} - ${n2} = ${resultado}`);
        novaOperacao(); // Pergunta se o usuário deseja fazer outra operação
      }

      function multiplicacao() {
        resultado = n1 * n2;
        alert(`${n1} * ${n2} = ${resultado}`);
        novaOperacao(); // Pergunta se o usuário deseja fazer outra operação
      }

      function divisao() {
        resultado = n1 / n2;
        alert(`${n1} / ${n2} = ${resultado}`);
        novaOperacao(); // Pergunta se o usuário deseja fazer outra operação
      }

      // Função para perguntar se o usuário deseja fazer outra operação
      function novaOperacao() {
        let opcao = prompt("Deseja fazer outra operação?\n 1 - Sim\n 2 - Não");

        if (opcao == 1) {
          calculadora(); // Chama a função calculadora() novamente para iniciar uma nova operação
        } else if (opcao == 2) {
          alert("Até a próxima!");
        } else {
          alert("Digite uma opção válida!");
          novaOperacao(); // Chama a função novaOperacao() novamente para solicitar uma opção válida
        }
      }

      // Chama a função correspondente à operação escolhida
      if (operacao == 1) {
        soma();
      } else if (operacao == 2) {
        subtracao();
      } else if (operacao == 3) {
        multiplicacao();
      } else if (operacao == 4) {
        divisao();
      }
    }
  }
}

calculadora1(); // Inicia a calculadora
