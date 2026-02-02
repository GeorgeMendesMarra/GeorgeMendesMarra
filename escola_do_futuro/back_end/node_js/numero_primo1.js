// Define o número que será verificado
const numero = 29;

// Variável que indica se o número é primo (começa como verdadeiro)
let ePrimo = true;

// Números menores ou iguais a 1 não são primos
if (numero <= 1) ePrimo = false;

// Laço que verifica se o número tem divisores
// Vai de 2 até a raiz quadrada do número
for (let i = 2; i <= Math.sqrt(numero); i++) {

    // Verifica se o número é divisível por i
    if (numero % i === 0) {
        // Se for divisível, não é primo
        ePrimo = false;

        // Interrompe o laço, pois já sabemos que não é primo
        break;
    }
}

// Exibe o resultado no console
console.log(`O número ${numero} é primo? ${ePrimo ? "Sim" : "Não"}`);
