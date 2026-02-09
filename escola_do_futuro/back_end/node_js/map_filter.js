const produtosDolar = [10, 25, 50, 5]; // Preços em USD

const carosEmReal = produtosDolar
    .map(p => p * 5.5)         // Passo 1: Converte tudo
    .filter(p => p > 100);     // Passo 2: Filtra o resultado do passo 1

console.log(carosEmReal); // [137.5, 275]
