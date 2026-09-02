# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial, mas com noções básicas de Java (classes, objetos, arrays).

**Como usar este material:** cada módulo termina com um bloco **✅ Checkpoint**, com 1-2 perguntas rápidas para você confirmar que entendeu antes de seguir em frente. Não pule os checkpoints — eles existem porque redes neurais são um assunto que "empilha" conceitos.

---

## Módulo 1: O que é uma Rede Neural?

<svg width="100%" viewBox="0 0 680 320" role="img" style="max-width: 600px; margin: 20px auto; display: block;">
<title>Ilustração de um neurônio artificial</title>
<desc>Três entradas com pesos convergindo para uma soma, que passa por uma função de ativação e gera uma saída.</desc>
<defs>
<marker id="arrow1" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse"><path d="M2 1L8 5L2 9" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></marker>
</defs>
<g style="fill: #888780; stroke: #888780; stroke-width: 0.5;">
<circle cx="90" cy="80" r="26"/>
<circle cx="90" cy="160" r="26"/>
<circle cx="90" cy="240" r="26"/>
<text x="90" y="80" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #2C2C2A;">x1</text>
<text x="90" y="160" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #2C2C2A;">x2</text>
<text x="90" y="240" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #2C2C2A;">x3</text>
</g>
<line x1="116" y1="80" x2="300" y2="150" stroke="#7F77DD" stroke-width="3" opacity="0.9" marker-end="url(#arrow1)"/>
<line x1="116" y1="160" x2="300" y2="160" stroke="#7F77DD" stroke-width="2" opacity="0.6" marker-end="url(#arrow1)"/>
<line x1="116" y1="240" x2="300" y2="170" stroke="#7F77DD" stroke-width="1" opacity="0.35" marker-end="url(#arrow1)"/>
<text x="200" y="95" text-anchor="middle" style="font-size: 12px; fill: #5F5E5A;">w1</text>
<text x="200" y="150" text-anchor="middle" style="font-size: 12px; fill: #5F5E5A;">w2</text>
<text x="200" y="225" text-anchor="middle" style="font-size: 12px; fill: #5F5E5A;">w3</text>
<g style="fill: #AFA9EC; stroke: #534AB7; stroke-width: 0.5;">
<circle cx="340" cy="160" r="40"/>
<text x="340" y="160" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #26215C;">soma</text>
</g>
<line x1="380" y1="160" x2="470" y2="160" stroke="#534AB7" stroke-width="1.5" marker-end="url(#arrow1)"/>
<g style="fill: #EF9F27; stroke: #854F0B; stroke-width: 0.5;">
<rect x="470" y="120" width="130" height="80" rx="10"/>
<text x="535" y="152" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #412402;">ativação</text>
<text x="535" y="172" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #633806;">decide 0 ou 1</text>
</g>
<line x1="600" y1="160" x2="650" y2="160" stroke="#854F0B" stroke-width="1.5" marker-end="url(#arrow1)"/>
<text x="652" y="160" text-anchor="start" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #5F5E5A;">saída</text>
<text x="340" y="260" text-anchor="middle" style="font-size: 12px; fill: #888780;">quanto mais grosso o traço, maior o peso — mais influência sobre o resultado</text>
</svg>

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra uma figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha — sem que ninguém tenha lhe dado uma "regra" explícita do tipo *"se tem bigode E orelha triangular, então é gato"*.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos, ajustando números internos até acertar mais vezes**. Ela não é "programada" com regras — ela é *treinada*.

> 💡 **Por que isso importa para quem programa em Java:** um programa Java tradicional segue instruções fixas (`if`, `for`, `switch`). Uma rede neural treinada é, na prática, um **conjunto de números (pesos)** guardado em arrays ou objetos Java, e a "lógica" está nesses números, não no código em si. O código Java que você escreve faz sempre a mesma coisa: multiplica, soma e aplica uma função. Quem muda é o *dado* (os pesos).

### 1.2. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial imita essa ideia de forma bem mais simples:

```
Entradas → Multiplica por Pesos → Soma → Função de Ativação → Saída
```

Passo a passo:

1. Cada entrada tem um **peso** — um número (em Java, um `double`) que indica o quanto aquela entrada importa.
2. O neurônio multiplica cada entrada pelo seu peso e soma tudo, incluindo um **bias** (viés) — um ajuste fino que desloca o resultado.
3. Esse total passa por uma **função de ativação**, que decide a saída final (por exemplo, "0" ou "1").

> 🎯 **Pense assim:** é como uma "votação ponderada" — cada entrada vota com um peso diferente, e no final decidimos se o resultado é "sim" ou "não".

Em Java, isso é literalmente um `double[]` de entradas, um `double[]` de pesos e um laço `for` que faz a soma — nada mais exótico que isso.

**✅ Checkpoint 1**
- O que diferencia um programa Java "tradicional" de uma rede neural treinada?
- Em Java, qual tipo de dado usaríamos para representar os pesos de um neurônio? E as entradas?

---

## Módulo 2: O Perceptron — O Neurônio Mais Simples

<svg width="100%" viewBox="0 0 680 260" role="img" style="max-width: 600px; margin: 20px auto; display: block;">
<title>Ciclo de treino de um Perceptron</title>
<desc>Quatro etapas em sequência: calcular saída, comparar com o esperado, calcular o erro e ajustar os pesos.</desc>
<defs>
<marker id="arrow2" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse"><path d="M2 1L8 5L2 9" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></marker>
</defs>
<g style="fill: #85B7EB; stroke: #185FA5; stroke-width: 0.5; cursor: pointer;">
<rect x="40" y="60" width="140" height="56" rx="8"/>
<text x="110" y="80" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #042C53;">calcular</text>
<text x="110" y="98" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #0C447C;">entradas × pesos</text>
</g>
<line x1="180" y1="88" x2="230" y2="88" stroke="#185FA5" stroke-width="1.5" marker-end="url(#arrow2)"/>
<g style="fill: #5DCAA5; stroke: #0F6E56; stroke-width: 0.5; cursor: pointer;">
<rect x="230" y="60" width="140" height="56" rx="8"/>
<text x="300" y="80" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #04342C;">comparar</text>
<text x="300" y="98" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #085041;">saída vs esperado</text>
</g>
<line x1="370" y1="88" x2="420" y2="88" stroke="#0F6E56" stroke-width="1.5" marker-end="url(#arrow2)"/>
<g style="fill: #F0997B; stroke: #993C1D; stroke-width: 0.5; cursor: pointer;">
<rect x="420" y="60" width="140" height="56" rx="8"/>
<text x="490" y="80" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #4A1B0C;">erro</text>
<text x="490" y="98" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #712B13;">esperado − obtido</text>
</g>
<path d="M560 88 L610 88 L610 180 L110 180 L110 116" fill="none" stroke="#5F5E5A" stroke-width="0.5" marker-end="url(#arrow2)"/>
<g style="fill: #AFA9EC; stroke: #534AB7; stroke-width: 0.5; cursor: pointer;">
<rect x="40" y="150" width="140" height="60" rx="8"/>
<text x="110" y="172" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #26215C;">ajustar pesos</text>
<text x="110" y="192" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #3C3489;">na direção do erro</text>
</g>
<text x="340" y="230" text-anchor="middle" style="font-size: 12px; fill: #888780;">↻ repete milhares de vezes, uma época por vez</text>
</svg>

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o **Perceptron** é o modelo mais básico de rede neural: um único neurônio que:

- Recebe várias entradas (0 ou 1);
- Calcula uma combinação ponderada (pesos × entradas);
- Decide a saída (0 ou 1).

**Exemplo prático:** classificar se algo é um "ser vivo" ou não, com base em 3 bits representando características.

### 2.2. Como um Perceptron Aprende?

1. Começamos com **pesos aleatórios** (em Java, geramos com `java.util.Random`).
2. Para cada exemplo de treino, calculamos a saída.
3. Comparamos com a resposta correta.
4. Se errou, **ajustamos os pesos** um pouquinho para melhorar.
5. Repetimos esse processo muitas vezes, com muitos exemplos.

**Fórmula do erro:**

```
erro = valor_esperado - valor_obtido
```

Se `erro` for zero, o perceptron acertou e os pesos não mudam. Se for diferente de zero, os pesos são corrigidos na direção que reduz esse erro.

### 2.3. Implementando um Perceptron em Java (passo a passo)

Antes do código completo, veja o que cada parte da classe Java vai representar:

| Elemento Java              | O que representa no perceptron                          |
| --------------------------- | --------------------------------------------------------- |
| `double[] pesos`            | Um peso por entrada                                       |
| `double bias`                | O viés (ajuste independente das entradas)                 |
| `double taxaAprendizado`     | O "tamanho do passo" de cada correção                     |
| método `funcaoAtivacao`      | Decide a saída final (aqui, uma função degrau: 0 ou 1)     |
| método `calcular`            | Faz a "previsão" (forward)                                 |
| método `treinar`             | Ajusta os pesos com base no erro (aprendizado)             |

```java
import java.util.Random;

class Perceptron {
    private double[] pesos;
    private double bias;
    private double taxaAprendizado = 0.1;

    public Perceptron(int numeroEntradas) {
        pesos = new double[numeroEntradas];
        Random random = new Random();
        for (int i = 0; i < numeroEntradas; i++) {
            pesos[i] = random.nextDouble() * 2 - 1; // valor entre -1 e 1
        }
        bias = random.nextDouble() * 2 - 1;
    }

    // Função degrau: se a soma for >= 0, retorna 1; senão, retorna 0
    private int funcaoAtivacao(double soma) {
        return soma >= 0 ? 1 : 0;
    }

    // Calcula a saída do perceptron para um conjunto de entradas (forward propagation)
    public int calcular(double[] entradas) {
        double soma = 0;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        soma += bias;
        return funcaoAtivacao(soma);
    }

    // Treina o perceptron com UM exemplo (entrada + resposta esperada)
    public void treinar(double[] entradas, int esperado) {
        int obtido = calcular(entradas);
        double erro = esperado - obtido;

        // Ajusta cada peso proporcionalmente ao erro e à respectiva entrada
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        bias += taxaAprendizado * erro;
    }
}
```

> ⚠️ **Importante:** este código é didático, para você entender o mecanismo por dentro. Em projetos reais de produção, use bibliotecas Java especializadas como **Deeplearning4j** (veja o Módulo 4) — elas já resolvem questões de performance, paralelismo e estabilidade numérica que este exemplo não trata.

**✅ Checkpoint 2**
- Por que o método `calcular` não altera os pesos, mas o método `treinar` sim?
- Se `taxaAprendizado` fosse muito alta (por exemplo, `2.0`), o que você imagina que aconteceria com o aprendizado?

---

## Módulo 3: Aprendizado e Treinamento

<svg width="100%" viewBox="0 0 680 300" role="img" style="max-width: 600px; margin: 20px auto; display: block;">
<title>Forward propagation e backpropagation em um MLP</title>
<desc>Três camadas de neurônios com sinal avançando da esquerda para a direita e o erro retornando da direita para a esquerda.</desc>
<defs>
<marker id="arrow3" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse"><path d="M2 1L8 5L2 9" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></marker>
</defs>
<g style="fill: #B4B2A9; stroke: #5F5E5A; stroke-width: 0.5;">
<circle cx="90" cy="90" r="20"/>
<circle cx="90" cy="210" r="20"/>
</g>
<g style="fill: #5DCAA5; stroke: #0F6E56; stroke-width: 0.5;">
<circle cx="300" cy="60" r="20"/>
<circle cx="300" cy="130" r="20"/>
<circle cx="300" cy="200" r="20"/>
<circle cx="300" cy="260" r="20"/>
</g>
<g style="fill: #EF9F27; stroke: #854F0B; stroke-width: 0.5;">
<circle cx="520" cy="150" r="22"/>
</g>
<g stroke="#5DCAA5" opacity="0.5" stroke-width="1.5">
<line x1="108" y1="90" x2="282" y2="60" marker-end="url(#arrow3)"/>
<line x1="108" y1="90" x2="282" y2="130" marker-end="url(#arrow3)"/>
<line x1="108" y1="90" x2="282" y2="200" marker-end="url(#arrow3)"/>
<line x1="108" y1="90" x2="282" y2="260" marker-end="url(#arrow3)"/>
<line x1="108" y1="210" x2="282" y2="60" marker-end="url(#arrow3)"/>
<line x1="108" y1="210" x2="282" y2="130" marker-end="url(#arrow3)"/>
<line x1="108" y1="210" x2="282" y2="200" marker-end="url(#arrow3)"/>
<line x1="108" y1="210" x2="282" y2="260" marker-end="url(#arrow3)"/>
</g>
<g stroke="#EF9F27" opacity="0.6" stroke-width="1.5">
<line x1="320" y1="60" x2="500" y2="150" marker-end="url(#arrow3)"/>
<line x1="320" y1="130" x2="500" y2="150" marker-end="url(#arrow3)"/>
<line x1="320" y1="200" x2="500" y2="150" marker-end="url(#arrow3)"/>
<line x1="320" y1="260" x2="500" y2="150" marker-end="url(#arrow3)"/>
</g>
<text x="90" y="30" text-anchor="middle" style="font-size: 14px; font-weight: 500; fill: #5F5E5A;">entrada</text>
<text x="300" y="20" text-anchor="middle" style="font-size: 14px; font-weight: 500; fill: #5F5E5A;">camada oculta</text>
<text x="520" y="105" text-anchor="middle" style="font-size: 14px; font-weight: 500; fill: #5F5E5A;">saída</text>
<text x="200" y="290" text-anchor="start" style="font-size: 12px; fill: #888780;">→ forward propagation: o sinal avança</text>
<text x="440" y="290" text-anchor="end" style="font-size: 12px; fill: #888780;">← backpropagation: o erro corrige os pesos</text>
</svg>

### 3.1. Como a Rede "Aprende"?

O aprendizado em redes neurais é um processo de **tentativa e erro em escala**:

1. **Forward Propagation**: os dados entram e "atravessam" a rede, gerando uma saída.
2. **Cálculo do erro**: comparamos a saída obtida com a esperada.
3. **Backpropagation**: o erro "volta" pela rede, ajustando os pesos de cada camada.
4. **Repetição**: esse ciclo se repete milhares de vezes, até o erro ficar pequeno o suficiente.

> 💡 **Do ponto de vista de Java:** cada uma dessas etapas é um método separado em uma classe de rede — algo como `forward()`, `calcularErro()` e `backpropagate()`. Pensar assim ajuda a organizar seu código quando a rede cresce além de um único perceptron.

### 3.2. Conceitos Importantes

- **Época (Epoch)**: uma rodada completa de treino passando por *todos* os exemplos disponíveis.
- **Função de Ativação**: transforma a soma ponderada em uma saída. As mais comuns são Sigmoid, Tanh e ReLU — em Java, cada uma é simplesmente um método que recebe um `double` e devolve outro `double`.
- **Taxa de Aprendizado**: controla o "tamanho do passo" de cada ajuste de peso. Muito alta, a rede "pula" a solução; muito baixa, ela demora demais para aprender.

### 3.3. Um MLP (Perceptron Multicamadas) Simplificado

Um único perceptron só resolve problemas simples. Para problemas mais complexos, empilhamos neurônios em **camadas**:

```java
// Criando uma rede com 2 entradas, 1 camada oculta de 4 neurônios, e 1 saída
int[] arquitetura = {2, 4, 1};
RedeNeural rede = new RedeNeural(arquitetura);
```

Em Java, a forma mais natural de representar isso é com uma classe `RedeNeural` que guarda uma lista de camadas (`List<Camada>`), e cada camada guarda sua própria matriz de pesos (`double[][]`). Se quiser aprofundar essa implementação, o projeto `neuraljava` (Módulo 6) mostra esse desenho completo em Java puro.

**✅ Checkpoint 3**
- Qual a diferença entre uma "época" e um único "exemplo de treino"?
- Por que precisamos de camadas (MLP) e não apenas de um perceptron isolado?

---

## Módulo 4: Ferramentas e Bibliotecas em Java

<svg width="100%" viewBox="0 0 680 300" role="img" style="max-width: 600px; margin: 20px auto; display: block;">
<title>Ecossistema Java para redes neurais</title>
<desc>A JVM na base sustentando as bibliotecas ND4J e Deeplearning4j, que alimentam uma aplicação Java em produção.</desc>
<defs>
<marker id="arrow4" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse"><path d="M2 1L8 5L2 9" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></marker>
</defs>
<g style="fill: #F0997B; stroke: #993C1D; stroke-width: 0.5;">
<rect x="140" y="220" width="400" height="50" rx="10"/>
<text x="340" y="245" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #4A1B0C;">JVM — máquina virtual Java</text>
</g>
<line x1="240" y1="220" x2="240" y2="170" stroke="#993C1D" stroke-width="1.5" marker-end="url(#arrow4)"/>
<line x1="440" y1="220" x2="440" y2="170" stroke="#993C1D" stroke-width="1.5" marker-end="url(#arrow4)"/>
<g style="fill: #5DCAA5; stroke: #0F6E56; stroke-width: 0.5;">
<rect x="150" y="110" width="180" height="60" rx="10"/>
<text x="240" y="132" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #04342C;">ND4J</text>
<text x="240" y="152" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #085041;">computação numérica</text>
</g>
<g style="fill: #AFA9EC; stroke: #534AB7; stroke-width: 0.5;">
<rect x="350" y="110" width="180" height="60" rx="10"/>
<text x="440" y="132" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #26215C;">Deeplearning4j</text>
<text x="440" y="152" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #3C3489;">treino e modelos</text>
</g>
<line x1="340" y1="110" x2="340" y2="70" stroke="#534AB7" stroke-width="1.5" marker-end="url(#arrow4)"/>
<g style="fill: #EF9F27; stroke: #854F0B; stroke-width: 0.5;">
<rect x="240" y="30" width="200" height="42" rx="8"/>
<text x="340" y="51" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #412402;">sua aplicação Java</text>
</g>
<text x="340" y="284" text-anchor="middle" style="font-size: 12px; fill: #888780;">a mesma base que roda seus sistemas corporativos treina e serve os modelos</text>
</svg>

### 4.1. Por que Usar Java para Redes Neurais?

Python domina o ecossistema de IA, mas Java tem vantagens concretas quando o objetivo é **colocar o modelo em produção**, especialmente em ambientes corporativos:

- **JVM madura e otimizada**, com décadas de trabalho em performance e gerenciamento de memória;
- **Tipagem estática**, que reduz erros em tempo de execução — problema comum em pipelines de dados grandes;
- **Integração natural** com sistemas corporativos já escritos em Java (bancos, ERPs, backends Spring);
- **Multithreading nativo**, útil para treinar ou servir modelos em paralelo sem depender de bibliotecas externas.

Isso não significa que Java substitui Python — na prática, é comum **treinar em Python e servir (rodar em produção) em Java**, como você verá na seção 4.3.

### 4.2. Bibliotecas Recomendadas

| Biblioteca               | Uso                                       | Link                                                             |
| ------------------------- | ------------------------------------------ | ------------------------------------------------------------------ |
| **Deeplearning4j (DL4J)** | Framework completo de deep learning em Java, com suporte a GPU | [deeplearning4j.org](https://deeplearning4j.org) |
| **ND4J**                  | Biblioteca de computação numérica (equivalente ao NumPy, mas para a JVM) | [nd4j.org](https://deeplearning4j.org/docs/latest/nd4j-overview) |
| **Basic Neural Network**  | Biblioteca simples, ótima para estudo e prototipagem rápida | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork) |

**Exemplo com a Basic Neural Network Library:**

```java
import de.hatoka.basicneuralnetwork.NeuralNetwork;
import de.hatoka.basicneuralnetwork.NetworkBuilder;

// Rede com 2 entradas, 2 camadas ocultas com 4 neurônios cada, 1 saída
NeuralNetwork rede = NetworkBuilder.create(2, 1)
    .setHiddenLayers(2, 4)
    .setActivationFunction(ActivationFunctions.TANH)
    .build();

// Treino
rede.train(dadosTreino, respostasEsperadas);

// Previsão
double[] resultado = rede.guess(dadosTeste);
```

Repare como essa API já esconde os detalhes de `for`, pesos e backpropagation que vimos "na mão" no Módulo 2 — é exatamente isso que uma biblioteca madura como DL4J faz em escala muito maior.

### 4.3. Carregando em Java um Modelo Treinado em Python

Um fluxo muito comum em empresas: cientistas de dados treinam o modelo em Python (Keras/TensorFlow), e o time de backend Java importa esse modelo já pronto para rodar em produção:

```java
// Importando um modelo Keras (.h5) para dentro de uma aplicação Java
String caminhoModelo = new ClassPathResource("meu_modelo.h5").getFile().getPath();
MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);
```

Isso é possível graças ao módulo de importação de modelos do Deeplearning4j, que entende o formato salvo pelo Keras.

**✅ Checkpoint 4**
- Cite duas vantagens de usar Java para *servir* (rodar em produção) um modelo já treinado.
- Qual biblioteca Java faz o papel equivalente ao NumPy do Python?

---

## Módulo 5: Exercício Prático — Rede Neural do Zero em Java

<svg width="100%" viewBox="0 0 680 340" role="img" style="max-width: 600px; margin: 20px auto; display: block;">
<title>Perceptron separando os pontos da operação lógica AND</title>
<desc>Gráfico com quatro pontos representando as combinações de entrada da porta AND e uma linha diagonal separando o ponto positivo dos demais.</desc>
<defs>
<marker id="arrow5" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse"><path d="M2 1L8 5L2 9" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></marker>
</defs>
<line x1="140" y1="260" x2="540" y2="260" stroke="#5F5E5A" stroke-width="0.5" marker-end="url(#arrow5)"/>
<line x1="140" y1="260" x2="140" y2="60" stroke="#5F5E5A" stroke-width="0.5" marker-end="url(#arrow5)"/>
<text x="545" y="264" style="font-size: 12px; fill: #888780;">entrada 1</text>
<text x="140" y="48" text-anchor="middle" style="font-size: 12px; fill: #888780;">entrada 2</text>
<text x="130" y="260" text-anchor="end" style="font-size: 12px; fill: #888780;">0</text>
<text x="130" y="100" text-anchor="end" style="font-size: 12px; fill: #888780;">1</text>
<text x="140" y="278" text-anchor="middle" style="font-size: 12px; fill: #888780;">0</text>
<text x="480" y="278" text-anchor="middle" style="font-size: 12px; fill: #888780;">1</text>
<line x1="160" y1="220" x2="500" y2="80" stroke="#D85A30" stroke-width="1.5" stroke-dasharray="6 5"/>
<text x="500" y="70" text-anchor="end" style="font-size: 12px; fill: #993C1D;">fronteira aprendida</text>
<g style="fill: #B4B2A9; stroke: #5F5E5A; stroke-width: 0.5;">
<circle cx="140" cy="260" r="16"/>
<circle cx="140" cy="100" r="16"/>
<circle cx="480" cy="260" r="16"/>
</g>
<g style="fill: #97C459; stroke: #3B6D11; stroke-width: 1;">
<circle cx="480" cy="100" r="16"/>
</g>
<text x="140" y="260" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #2C2C2A;">0,0</text>
<text x="140" y="100" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #2C2C2A;">0,1</text>
<text x="480" y="260" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #2C2C2A;">1,0</text>
<text x="480" y="100" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #173404;">1,1</text>
<text x="340" y="320" text-anchor="middle" style="font-size: 12px; fill: #888780;">apenas o ponto (1,1) fica do lado "verdadeiro" da linha — como no AND</text>
</svg>

### Desafio: Classificador AND Simples

Vamos usar o `Perceptron` que implementamos no Módulo 2 para aprender a operação lógica **AND**:

| Entrada 1 | Entrada 2 | Saída esperada |
| :-------: | :-------: | :------------: |
| 0         | 0         | 0               |
| 0         | 1         | 0               |
| 1         | 0         | 0               |
| 1         | 1         | 1               |

**Código para testar:**

```java
public class TestePerceptron {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(2);

        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] esperados = {0, 0, 0, 1};

        // Treina por 100 épocas
        for (int epoca = 0; epoca < 100; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        // Testa o resultado final
        for (double[] entrada : entradas) {
            System.out.println(entrada[0] + " AND " + entrada[1] +
                             " = " + p.calcular(entrada));
        }
    }
}
```

**🔎 Sugestão de exploração (para quem quer ir além):** troque a tabela de saídas esperadas para simular um **OR** em vez de um **AND**, rode de novo e observe se o perceptron ainda converge. Depois, tente montar uma tabela de **XOR** — e observe que, por mais épocas que você treine, um único perceptron *não consegue* aprender XOR. Esse é um limite histórico e conhecido do modelo, e é justamente o motivo pelo qual o MLP (Módulo 3.3) foi criado.

**✅ Checkpoint 5**
- O que aconteceu quando você tentou treinar o perceptron para resolver XOR? Por quê, na sua opinião, isso acontece?

---

## Módulo 6: 📚 Recursos para Aprofundamento em Java

<svg width="100%" viewBox="0 0 680 220" role="img" style="max-width: 600px; margin: 20px auto; display: block;">
<title>Trilha de aprofundamento após o minicurso</title>
<desc>Quatro passos em sequência: livro, projeto de exemplo, implementação do zero e documentação oficial.</desc>
<defs>
<marker id="arrow6" viewBox="0 0 10 10" refX="8" refY="5" markerWidth="6" markerHeight="6" orient="auto-start-reverse"><path d="M2 1L8 5L2 9" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/></marker>
</defs>
<g style="fill: #AFA9EC; stroke: #534AB7; stroke-width: 0.5; cursor: pointer;">
<rect x="30" y="80" width="140" height="56" rx="8"/>
<text x="100" y="100" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #26215C;">livro</text>
<text x="100" y="118" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #3C3489;">fundamentos</text>
</g>
<line x1="170" y1="108" x2="205" y2="108" stroke="#534AB7" stroke-width="1.5" marker-end="url(#arrow6)"/>
<g style="fill: #5DCAA5; stroke: #0F6E56; stroke-width: 0.5; cursor: pointer;">
<rect x="205" y="80" width="140" height="56" rx="8"/>
<text x="275" y="100" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #04342C;">neuraljava</text>
<text x="275" y="118" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #085041;">código real, aberto</text>
</g>
<line x1="345" y1="108" x2="380" y2="108" stroke="#0F6E56" stroke-width="1.5" marker-end="url(#arrow6)"/>
<g style="fill: #F0997B; stroke: #993C1D; stroke-width: 0.5; cursor: pointer;">
<rect x="380" y="80" width="140" height="56" rx="8"/>
<text x="450" y="100" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #4A1B0C;">micrograd-java</text>
<text x="450" y="118" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #712B13;">implementar do zero</text>
</g>
<line x1="520" y1="108" x2="555" y2="108" stroke="#993C1D" stroke-width="1.5" marker-end="url(#arrow6)"/>
<g style="fill: #EF9F27; stroke: #854F0B; stroke-width: 0.5; cursor: pointer;">
<rect x="555" y="80" width="95" height="56" rx="8"/>
<text x="602" y="100" text-anchor="middle" dominant-baseline="central" style="font-size: 14px; font-weight: 500; fill: #412402;">DL4J</text>
<text x="602" y="118" text-anchor="middle" dominant-baseline="central" style="font-size: 12px; fill: #633806;">produção</text>
</g>
<text x="340" y="180" text-anchor="middle" style="font-size: 12px; fill: #888780;">da teoria ao deploy — sempre dentro do ecossistema Java</text>
</svg>

1. **Livro:** *Neural Network Programming with Java* — Fabio Soares, Alan M.F. Souza.
2. **Projeto de exemplo:** [neuraljava no GitHub](https://github.com/cleuton/neuraljava) — implementações de Perceptron e MLP em Java puro, ótimo para estudar o código por dentro.
3. **micrograd-java:** implementação completa de redes neurais do zero, em Java, inspirada no projeto educacional `micrograd` (originalmente em Python).
4. **Deeplearning4j Docs:** documentação oficial, com tutoriais de treinamento e deploy em produção na JVM.

---

### Resumo do minicurso

| Módulo | O que você aprendeu |
| ------ | --------------------- |
| 1      | O que é um neurônio artificial e a analogia com aprendizado |
| 2      | Como funciona e como implementar um Perceptron em Java |
| 3      | Os conceitos de treinamento: forward, erro, backpropagation, épocas |
| 4      | Bibliotecas Java para redes neurais (DL4J, ND4J) e integração com modelos Python |
| 5      | Um exercício prático completo, rodando do zero em Java |
| 6      | Onde continuar estudando |
