// Importamos o módulo 'readline' para permitir a interação via terminal
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

/**
 * Criamos uma função 'pergunta' que utiliza Promises.
 * Isso permite que usemos o 'await', fazendo o código esperar a resposta do usuário
 * de uma forma muito mais legível e organizada.
 */
const pergunta = (texto) => new Promise((resolve) => readline.question(texto, resolve));

async function calcularJurosSimples() {
    console.log("--- Calculadora de Juros Simples ---");

    // Coleta dos dados do usuário
    const entradaCapital = await pergunta("Digite o Capital inicial (R$): ");
    const entradaTaxa = await pergunta("Digite a taxa de juros mensal (em %): ");
    const entradaTempo = await pergunta("Digite o tempo da aplicação (em meses): ");

    // Conversão de Strings para Números decimais (float)
    const capital = parseFloat(entradaCapital);
    const taxaPorcentagem = parseFloat(entradaTaxa);
    const tempo = parseFloat(entradaTempo);

    // Validação: verificamos se todos os campos são números válidos
    if (isNaN(capital) || isNaN(taxaPorcentagem) || isNaN(tempo)) {
        console.log("Erro: Por favor, insira valores numéricos válidos.");
    } else {
        /**
         * A matemática por trás:
         * 1. A taxa deve ser dividida por 100 (ex: 5% vira 0.05).
         * 2. Fórmula dos Juros: J = C * i * t (Capital * taxa * tempo).
         * 3. Fórmula do Montante: M = C + J (Capital + Juros).
         */
        const taxaDecimal = taxaPorcentagem / 100;
        const juros = capital * taxaDecimal * tempo;
        const montanteTotal = capital + juros;

        // Exibição dos resultados com formatação de moeda
        console.log("\n--- Resultado ---");
        console.log(`Capital Inicial: R$ ${capital.toFixed(2)}`);
        console.log(`Juros Acumulados: R$ ${juros.toFixed(2)}`);
        console.log(`Valor Total (Montante): R$ ${montanteTotal.toFixed(2)}`);
    }

    // Encerramos a interface para o programa não ficar rodando infinitamente
    readline.close();
}

// Iniciamos o programa
calcularJurosSimples();
