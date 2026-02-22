# 📌 1)

```javascript
// 1. Contagem de 6 até 11
for (let i = 6; i <= 11; i++) {
    process.stdout.write(i + " ");
}
console.log("\nAcabou!");
```

---

# 📌 2)

```javascript
// 2. Contagem de 10 até 3 (regressiva)
for (let i = 10; i >= 3; i--) {
    process.stdout.write(i + " ");
}
console.log("\nAcabou!");
```

---

# 📌 3)

```javascript
// 3. Contagem de 0 até 18 pulando de 3 em 3
for (let i = 0; i <= 18; i += 3) {
    process.stdout.write(i + " ");
}
console.log("\nAcabou!");
```

---

# 📌 4)

```javascript
// 4. Contagem de 100 até 0 pulando de 5 em 5
for (let i = 100; i >= 0; i -= 5) {
    process.stdout.write(i + " ");
}
console.log("Acabou!");
```

---

# 📌 5)

```javascript
// 5. Contagem até número digitado
const prompt = require("prompt-sync")();
let valor = Number(prompt("Digite um valor inteiro positivo: "));

for (let i = 1; i <= valor; i++) {
    process.stdout.write(i + " ");
}
console.log("\nAcabou!");
```

---

# 📌 6)

```javascript
// 6. Contagem regressiva de 30 até 1 marcando divisíveis por 4
for (let i = 30; i >= 1; i--) {
    if (i % 4 === 0) {
        process.stdout.write("[" + i + "] ");
    } else {
        process.stdout.write(i + " ");
    }
}
```

---

# 📌 7)

```javascript
// 7. Contagem com valor inicial, final e incremento
let inicio = Number(prompt("Digite o primeiro valor: "));
let fim = Number(prompt("Digite o último valor: "));
let incremento = Number(prompt("Digite o incremento: "));

for (let i = inicio; i <= fim; i += incremento) {
    process.stdout.write(i + " ");
}
console.log("\nAcabou!");
```

---

# 📌 8)

```javascript
// 8. Funciona tanto crescente quanto decrescente
inicio = Number(prompt("Digite o primeiro valor: "));
fim = Number(prompt("Digite o último valor: "));
incremento = Number(prompt("Digite o incremento: "));

if (inicio < fim) {
    for (let i = inicio; i <= fim; i += incremento) {
        process.stdout.write(i + " ");
    }
} else {
    for (let i = inicio; i >= fim; i -= incremento) {
        process.stdout.write(i + " ");
    }
}
console.log("\nAcabou!");
```

---

# 📌 9)

```javascript
// 9. Soma de 6 até 100 (pares)
let soma = 0;
for (let i = 6; i <= 100; i += 2) {
    soma += i;
}
console.log("Resultado:", soma);
```

---

# 📌 10)

```javascript
// 10. Soma regressiva de 500 até 0 pulando de 50
soma = 0;
for (let i = 500; i >= 0; i -= 50) {
    soma += i;
}
console.log("Resultado:", soma);
```

---

# 📌 11)

```javascript
// 11. Soma de 7 números
soma = 0;
for (let i = 1; i <= 7; i++) {
    let num = Number(prompt("Digite um número: "));
    soma += num;
}
console.log("Somatório:", soma);
```

---

# 📌 12)

```javascript
// 12. Contar pares e ímpares
let pares = 0, impares = 0;

for (let i = 1; i <= 6; i++) {
    let num = Number(prompt("Digite um número: "));
    if (num % 2 === 0) pares++;
    else impares++;
}

console.log("Pares:", pares);
console.log("Ímpares:", impares);
```

---

# 📌 13)

```javascript
// 13. Sorteio de 20 números entre 0 e 10
let acima5 = 0, divisivel3 = 0;

for (let i = 1; i <= 20; i++) {
    let num = Math.floor(Math.random() * 11);
    process.stdout.write(num + " ");

    if (num > 5) acima5++;
    if (num % 3 === 0) divisivel3++;
}

console.log("\nAcima de 5:", acima5);
console.log("Divisíveis por 3:", divisivel3);
```

---

# 📌 14)

```javascript
// 14. Maior e menor preço
let maior = 0;
let menor = Infinity;

for (let i = 1; i <= 8; i++) {
    let preco = Number(prompt("Digite o preço: "));
    if (preco > maior) maior = preco;
    if (preco < menor) menor = preco;
}

console.log("Maior preço:", maior);
console.log("Menor preço:", menor);
```

---

# 📌 15)

```javascript
// 15. Idades
let somaIdade = 0, maiorIdade = 0, mais18 = 0, menos5 = 0;

for (let i = 1; i <= 10; i++) {
    let idade = Number(prompt("Digite a idade: "));
    somaIdade += idade;

    if (idade > maiorIdade) maiorIdade = idade;
    if (idade > 18) mais18++;
    if (idade < 5) menos5++;
}

console.log("Média:", somaIdade / 10);
console.log("Maiores de 18:", mais18);
console.log("Menores de 5:", menos5);
console.log("Maior idade:", maiorIdade);
```

---

# 📌 16)

```javascript
// 16. Idade e sexo
let homens = 0, mulheres = 0, somaGrupo = 0, somaHomens = 0, mulheres20 = 0;

for (let i = 1; i <= 5; i++) {
    let idade = Number(prompt("Idade: "));
    let sexo = prompt("Sexo (M/F): ").toUpperCase();

    somaGrupo += idade;

    if (sexo === "M") {
        homens++;
        somaHomens += idade;
    } else {
        mulheres++;
        if (idade > 20) mulheres20++;
    }
}

console.log("Homens:", homens);
console.log("Mulheres:", mulheres);
console.log("Média grupo:", somaGrupo / 5);
console.log("Média homens:", homens > 0 ? somaHomens / homens : 0);
console.log("Mulheres > 20:", mulheres20);
```

---

# 📌 17)

```javascript
// 17. Peso e altura
let somaAltura = 0, mais90 = 0, menos50e160 = 0, mais190e100 = 0;

for (let i = 1; i <= 7; i++) {
    let peso = Number(prompt("Peso: "));
    let altura = Number(prompt("Altura: "));

    somaAltura += altura;

    if (peso > 90) mais90++;
    if (peso < 50 && altura < 1.60) menos50e160++;
    if (altura > 1.90 && peso > 100) mais190e100++;
}

console.log("Média altura:", somaAltura / 7);
console.log("Mais de 90kg:", mais90);
console.log("Menos 50kg e <1.60:", menos50e160);
console.log("Altura >1.90 e peso >100:", mais190e100);
```

---

# 🎯 18) DESAFIO – Jogo com 4 tentativas

```javascript
// 18. Jogo de adivinhação
let numeroSecreto = Math.floor(Math.random() * 10) + 1;
let tentativas = 4;
let acertou = false;

console.log("Tente adivinhar o número entre 1 e 10!");
for (let i = 1; i <= tentativas; i++) {
    let palpite = Number(prompt("Tentativa " + i + ": "));

    if (palpite === numeroSecreto) {
        console.log("Parabéns! Você acertou!");
        acertou = true;
        break;
    } else {
        console.log("Errou!");
    }
}

if (!acertou) {
    console.log("Suas tentativas acabaram. O número era:", numeroSecreto);
}
```
