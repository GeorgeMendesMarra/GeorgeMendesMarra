// Objeto do jogador 1 com seus atributos de corrida
// NOME, VELOCIDADE, MANOBRABILIDADE, PODER, PONTOS
const player1 = {
  NOME: "Mario",
  VELOCIDADE: 4,      // Velocidade alta favorece retas
  MANOBRABILIDADE: 3, // Manobrabilidade média
  PODER: 3,           // Poder médio para confrontos
  PONTOS: 0,          // Pontuação inicial zero
};

// Objeto do jogador 2 com distribuição de atributos diferente
// Luigi é mais lento, mas tem melhor manobrabilidade e poder
const player2 = {
  NOME: "Luigi",
  VELOCIDADE: 3,
  MANOBRABILIDADE: 4,
  PODER: 4,
  PONTOS: 0,
};

/**
 * Simula o lançamento de um dado de 6 faces
 * @returns {Promise<number>} Número aleatório entre 1 e 6
 * 
 * LÓGICA: Math.random() gera número entre 0 e 1
 * Multiplicamos por 6, usamos Math.floor para arredondar para baixo
 * e somamos 1 para garantir intervalo 1-6
 */
async function rollDice() {
  return Math.floor(Math.random() * 6) + 1;
}

/**
 * Seleciona aleatoriamente o tipo de bloco da pista
 * @returns {Promise<string>} "RETA", "CURVA" ou "CONFRONTO"
 * 
 * LÓGICA: Usamos probabilidade de 33.33% para cada tipo
 * - random < 0.33 → RETA (33% das vezes)
 * - random < 0.66 → CURVA (mais 33%)
 * - resto (default) → CONFRONTO (34%)
 */
async function getRandomBlock() {
  let random = Math.random(); // Gera número entre 0 e 1
  let result;

  switch (true) {
    case random < 0.33:      // Primeiro terço: RETA
      result = "RETA";
      break;
    case random < 0.66:      // Segundo terço: CURVA
      result = "CURVA";
      break;
    default:                 // Terço final: CONFRONTO
      result = "CONFRONTO";
  }

  return result;
}

/**
 * Exibe o resultado de uma rolagem de dado de forma formatada
 * @param {string} characterName - Nome do personagem
 * @param {string} block - Tipo de habilidade testada
 * @param {number} diceResult - Valor do dado rolado
 * @param {number} attribute - Atributo do personagem
 * 
 * LÓGICA: Mostra o cálculo completo: dado + atributo = total
 * Exemplo: "Mario rolou um dado de velocidade 4 + 3 = 7"
 */
async function logRollResult(characterName, block, diceResult, attribute) {
  console.log(
    `${characterName} 🎲 rolou um dado de ${block} ${diceResult} + ${attribute} = ${
      diceResult + attribute
    }`
  );
}

/**
 * Motor principal do jogo - executa 5 rodadas da corrida
 * @param {object} character1 - Primeiro corredor
 * @param {object} character2 - Segundo corredor
 * 
 * LÓGICA GERAL:
 * 1. Cada rodada tem um tipo de bloco aleatório
 * 2. Cada tipo testa um atributo diferente
 * 3. Retas e curvas: maior total ganha 1 ponto
 * 4. Confrontos: perdedor PERDE 1 ponto (não ganha)
 * 5. Após 5 rodadas, declaramos o vencedor
 */
async function playRaceEngine(character1, character2) {
  // A corrida tem exatamente 5 rodadas
  for (let round = 1; round <= 5; round++) {
    console.log(`🏁 Rodada ${round}`);

    // PASSO 1: Sortear o tipo de desafio desta rodada
    let block = await getRandomBlock();
    console.log(`Bloco: ${block}`);

    // PASSO 2: Ambos os personagens rolam o dado
    let diceResult1 = await rollDice();
    let diceResult2 = await rollDice();

    // PASSO 3: Calcular os totais dos testes de habilidade
    let totalTestSkill1 = 0;
    let totalTestSkill2 = 0;

    // CASO 1: RETA - Testa VELOCIDADE
    if (block === "RETA") {
      // Total = dado rolado + atributo de velocidade
      totalTestSkill1 = diceResult1 + character1.VELOCIDADE;
      totalTestSkill2 = diceResult2 + character2.VELOCIDADE;

      await logRollResult(
        character1.NOME,
        "velocidade",
        diceResult1,
        character1.VELOCIDADE
      );

      await logRollResult(
        character2.NOME,
        "velocidade",
        diceResult2,
        character2.VELOCIDADE
      );
    }

    // CASO 2: CURVA - Testa MANOBRABILIDADE
    if (block === "CURVA") {
      // Total = dado rolado + atributo de manobrabilidade
      totalTestSkill1 = diceResult1 + character1.MANOBRABILIDADE;
      totalTestSkill2 = diceResult2 + character2.MANOBRABILIDADE;

      await logRollResult(
        character1.NOME,
        "manobrabilidade",
        diceResult1,
        character1.MANOBRABILIDADE
      );

      await logRollResult(
        character2.NOME,
        "manobrabilidade",
        diceResult2,
        character2.MANOBRABILIDADE
      );
    }

    // CASO 3: CONFRONTO - Testa PODER e pode DEDUZIR pontos
    if (block === "CONFRONTO") {
      // Calcula os totais de poder
      let powerResult1 = diceResult1 + character1.PODER;
      let powerResult2 = diceResult2 + character2.PODER;

      console.log(`${character1.NOME} confrontou com ${character2.NOME}! 🥊`);

      await logRollResult(
        character1.NOME,
        "poder",
        diceResult1,
        character1.PODER
      );

      await logRollResult(
        character2.NOME,
        "poder",
        diceResult2,
        character2.PODER
      );

      // REGRA IMPORTANTE: Em confrontos, o PERDEDOR perde 1 ponto
      // O vencedor NÃO ganha ponto nenhum
      
      // Se jogador 1 vence e jogador 2 tem pontos para perder
      if (powerResult1 > powerResult2 && character2.PONTOS > 0) {
        console.log(
          `${character1.NOME} venceu o confronto! ${character2.NOME} perdeu 1 ponto 🐢`
        );
        character2.PONTOS--; // Deduz ponto do perdedor
      }

      // Se jogador 2 vence e jogador 1 tem pontos para perder
      if (powerResult2 > powerResult1 && character1.PONTOS > 0) {
        console.log(
          `${character2.NOME} venceu o confronto! ${character1.NOME} perdeu 1 ponto 🐢`
        );
        character1.PONTOS--; // Deduz ponto do perdedor
      }

      // Em caso de empate, ninguém perde ponto
      console.log(
        powerResult2 === powerResult1
          ? "Confronto empatado! Nenhum ponto foi perdido"
          : ""
      );
      
      // IMPORTANTE: Em confrontos, totalTestSkill1/2 permanecem 0
      // Isso faz com que o código de premiação de pontos abaixo seja PULADO
    }

    // PASSO 4: Premiar o vencedor da rodada (somente para RETA e CURVA)
    // Este código SÓ executa se totalTestSkill foi definido (não é confronto)
    if (totalTestSkill1 > totalTestSkill2) {
      console.log(`${character1.NOME} marcou um ponto!`);
      character1.PONTOS++;
    } else if (totalTestSkill2 > totalTestSkill1) {
      console.log(`${character2.NOME} marcou um ponto!`);
      character2.PONTOS++;
    }
    // Se houve empate em RETA/CURVA, nenhum ponto é concedido

    console.log("-----------------------------");
  }
}

/**
 * Declara o vencedor final baseado nos pontos acumulados
 * @param {object} character1 - Primeiro corredor
 * @param {object} character2 - Segundo corredor
 * 
 * LÓGICA: Comparação simples de pontos totais após 5 rodadas
 * Pode resultar em vitória do jogador 1, vitória do jogador 2 ou empate
 */
async function declareWinner(character1, character2) {
  console.log("Resultado final:");
  console.log(`${character1.NOME}: ${character1.PONTOS} ponto(s)`);
  console.log(`${character2.NOME}: ${character2.PONTOS} ponto(s)`);

  if (character1.PONTOS > character2.PONTOS)
    console.log(`\n${character1.NOME} venceu a corrida! Parabéns! 🏆`);
  else if (character2.PONTOS > character1.PONTOS)
    console.log(`\n${character2.NOME} venceu a corrida! Parabéns! 🏆`);
  else console.log("A corrida terminou em empate");
}

/**
 * Função principal imediatamente invocada (IIFE)
 * Ponto de entrada do programa
 * 
 * FLUXO DO PROGRAMA:
 * 1. Exibe mensagem de início da corrida
 * 2. Executa o motor do jogo (5 rodadas)
 * 3. Calcula e exibe o resultado final
 * 
 * O async permite usar await dentro da função
 */
(async function main() {
  console.log(
    `🏁🚨 Corrida entre ${player1.NOME} e ${player2.NOME} começando...\n`
  );

  await playRaceEngine(player1, player2);
  await declareWinner(player1, player2);
})();
