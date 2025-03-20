const readline = require('readline').createInterface({
  input: process.stdin,
  output: process.stdout
});

readline.question('Digite um número: ', numero => {
  if (numero > 0) {
    console.log('O número é positivo.');
  } else if (numero < 0) {
    console.log('O número é negativo.');
  } else {
    console.log('O número é zero.');
  }
  readline.close();
});
