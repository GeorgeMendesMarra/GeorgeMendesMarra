// Define o peso da pessoa em quilogramas (kg)
const peso = 80;

// Define a altura da pessoa em metros
const altura = 1.80;

// Calcula o IMC usando a fórmula: peso / (altura²)
const imc = peso / Math.pow(altura, 2);

// Exibe o valor do IMC com duas casas decimais
console.log(`Seu IMC é: ${imc.toFixed(2)}`);

// Verifica a classificação do IMC
if (imc < 18.5) {
    // IMC menor que 18.5 indica abaixo do peso
    console.log("Classificação: Abaixo do peso");
} else if (imc < 25) {
    // IMC entre 18.5 e 24.9 indica peso normal
    console.log("Classificação: Peso normal");
} else {
    // IMC igual ou maior que 25 indica sobrepeso
    console.log("Classificação: Sobrepeso");
}
