// Definimos o capital inicial (o valor que está sendo investido ou emprestado)
const capital = 1000;

// Definimos a taxa de juros. 
// Importante: 0.05 representa 5%, pois em cálculos matemáticos usamos a forma decimal (5 / 100)
const taxa = 0.05; 

// Definimos o período de tempo (neste caso, 12 meses ou 1 ano)
const tempo = 12; 

/**
 * Cálculo dos Juros Simples
 * A fórmula é: J = C * i * t
 * Onde: J (Juros), C (Capital), i (Taxa) e t (Tempo)
 */
const juros = capital * taxa * tempo;

/**
 * Cálculo do Montante Total
 * O total é a soma do capital que você já tinha com os juros que renderam
 */
const total = capital + juros;

// Exibimos os resultados no console
// Usamos as crases (Template Strings) para facilitar a exibição das variáveis dentro do texto
console.log(`Juros: R$${juros} | Total após 1 ano: R$${total}`);
