const precosDolar = [10, 20, 30];

// O .map cria o novo array, mas ele fica "guardado" na variável precosReal
const precosReal = precosDolar.map(preco => preco * 5.5);

// Agora pedimos para o Node mostrar o que está guardado
console.log(precosReal);
