// Define os coeficientes da equação do segundo grau
// Equação: x² - 5x + 6 = 0
const a = 1, b = -5, c = 6;

// Calcula o valor de delta (b² - 4ac)
const delta = Math.pow(b, 2) - (4 * a * c);

// Verifica se o delta é negativo
if (delta < 0) {
    // Se delta < 0, a equação não possui raízes reais
    console.log("A equação não possui raízes reais.");
} else {
    // Calcula a primeira raiz usando a fórmula de Bhaskara
    const x1 = (-b + Math.sqrt(delta)) / (2 * a);
    
    // Calcula a segunda raiz usando a fórmula de Bhaskara
    const x2 = (-b - Math.sqrt(delta)) / (2 * a);
    
    // Exibe as duas raízes no console
    console.log(`As raízes são: x1 = ${x1} e x2 = ${x2}`);
}
