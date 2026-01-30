// O módulo 'readline' permite ler o que o usuário digita no terminal (stdin) e escrever respostas (stdout)
const readline = require('readline').createInterface({
    input: process.stdin,   // Entrada de dados (teclado)
    output: process.stdout  // Saída de dados (tela)
});

/**
 * Criamos uma Promise para a pergunta.
 * O Node.js original usa callbacks para o readline, o que pode gerar um código "bagunçado" (callback hell).
 * Ao envolver em uma Promise, podemos usar o 'await' para o código esperar o usuário responder.
 */
const pergunta = (texto) => new Promise((resolve) => readline.question(texto, resolve));

// 'async' indica que esta função contém operações que levam tempo (esperar o usuário)
async function calcularPitagoras() {
    console.log("--- Calculadora de Hipotenusa ---");

    /**
     * O 'await' pausa a execução aqui. 
     * O programa só vai para a próxima linha depois que o usuário digitar algo e apertar ENTER.
     */
    const entradaA = await pergunta("Digite o valor do Cateto A: ");
    const entradaB = await pergunta("Digite o valor do Cateto B: ");

    /**
     * parseFloat: Transforma o texto digitado ("3") em um número decimal real (3.0).
     * Isso é necessário porque o terminal sempre envia dados no formato String (texto).
     */
    const a = parseFloat(entradaA);
    const b = parseFloat(entradaB);

    /**
     * Validação de Erro:
     * isNaN significa "Is Not a Number" (Não é um número).
     * Se o usuário digitar letras em vez de números, o programa avisa o erro.
     */
    if (isNaN(a) || isNaN(b)) {
        console.log("Erro: Por favor, insira apenas valores numéricos.");
    } else {
        /**
         * A Fórmula: h = √(a² + b²)
         * Math.hypot(a, b) é a forma mais moderna e precisa do JavaScript de calcular a hipotenusa.
         * Ela internamente eleva os números ao quadrado, soma-os e extrai a raiz quadrada.
         */
        const hipotenusa = Math.hypot(a, b);

        // .toFixed(2) limita o resultado a 2 casas após a vírgula (ex: 5.00)
        console.log(`\nCom catetos ${a} e ${b}, a hipotenusa é: ${hipotenusa.toFixed(2)}`);
    }

    // Importante: Sempre fechar o readline, senão o programa ficará "preso" e nunca encerrará no terminal.
    readline.close();
}

// Chamada inicial para disparar a lógica do programa
calcularPitagoras();
