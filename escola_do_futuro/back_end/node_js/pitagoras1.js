// Definimos os valores dos catetos (os lados menores do triângulo retângulo)
const catetoA = 3;
const catetoB = 4;

/**
 * A fórmula de Pitágoras é: a² + b² = h²
 * Para encontrar a hipotenusa (h), isolamos ela: h = √(a² + b²)
 */

// Math.pow(base, expoente) eleva o número ao quadrado
const somaDosQuadrados = Math.pow(catetoA, 2) + Math.pow(catetoB, 2);

// Math.sqrt(numero) calcula a raiz quadrada (Square Root) do resultado
const hipotenusa = Math.sqrt(somaDosQuadrados);

// Exibimos o resultado formatado no console
console.log(`A hipotenusa para os catetos ${catetoA} e ${catetoB} é: ${hipotenusa}`);
