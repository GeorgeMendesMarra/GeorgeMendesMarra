// Define o valor inicial investido (capital), em reais
const capital = 1000; // R$

// Define a taxa de juros mensal (5% ao mês)
const taxa = 0.05;

// Define o tempo do investimento em meses
const tempo = 12; // 12 meses

// Calcula o montante usando a fórmula de juros compostos:
// montante = capital * (1 + taxa) ^ tempo
const montante = capital * Math.pow((1 + taxa), tempo);

// Exibe o valor inicial do investimento
console.log(`Valor inicial: R$ ${capital}`);

// Exibe o montante final após o período informado
console.log(`Montante final após ${tempo} meses: R$ ${montante.toFixed(2)}`);

// Calcula e exibe apenas o lucro obtido com os juros
console.log(`Lucro apenas em juros: R$ ${(montante - capital).toFixed(2)}`);
