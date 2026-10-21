# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra uma figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos**.

> 🏖️ **Fio condutor do minicurso:** vamos usar **um único exemplo do início ao fim**: decidir **"vou à praia?"**. No Módulo 1 o neurônio decide com pesos escolhidos à mão; no 2 ele aprende os pesos sozinho; no 3 chegamos ao limite dele (XOR); no 4 usamos bibliotecas; no 5 você implementa o desafio.

### 1.2. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo parecido:

```
Entradas → Multiplica por Pesos → Soma → Função de Ativação → Saída
```

Cada entrada tem um **peso** (um número que indica sua importância). O neurônio:

1. Multiplica cada entrada pelo seu peso
2. Soma tudo (incluindo um **bias**, ou viés)
3. Passa o resultado por uma **função de ativação** que decide a saída

> 🎯 **Pense assim:** é como um sistema de "votação ponderada", onde cada entrada vota com um peso diferente, e no final decidimos se o resultado é "sim" ou "não".

#### Exemplo em Java: o neurônio decide "vou à praia?"

Três entradas (1 = sim, 0 = não): **faz sol**, **os amigos vão** e **há trânsito**. O sol e os amigos pesam a favor; o trânsito pesa contra.

```java
public class NeuronioSimples {

    private double[] pesos;
    private double bias;

    public NeuronioSimples(double[] pesos, double bias) {
        this.pesos = pesos;
        this.bias = bias;
    }

    // Função de ativação Sigmoid: transforma qualquer número em um valor entre 0 e 1
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    // Entradas → multiplica pelos pesos → soma + bias → ativação
    public double calcular(double[] entradas) {
        double soma = bias;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        return sigmoid(soma);
    }

    public static void main(String[] args) {
        // Pesos: sol (+0.8), amigos (+0.5), trânsito (-0.6); bias = -0.4
        NeuronioSimples neuronio = new NeuronioSimples(new double[]{0.8, 0.5, -0.6}, -0.4);

        double[][] cenarios = {
            {1, 1, 0}, // sol, amigos vão, sem trânsito
            {0, 1, 1}, // sem sol, amigos vão, trânsito
            {1, 0, 1}  // sol, amigos não vão, trânsito
        };
        String[] nomes = {"Sol + amigos + sem trânsito",
                          "Sem sol + amigos + trânsito",
                          "Sol + sem amigos + trânsito"};

        for (int i = 0; i < cenarios.length; i++) {
            double saida = neuronio.calcular(cenarios[i]);
            String decisao = saida >= 0.5 ? "Vai à praia" : "Fica em casa";
            System.out.println(nomes[i] + " → " + Math.round(saida * 100) / 100.0
                    + " → " + decisao);
        }
    }
}
```

**Saída esperada:**

```
Sol + amigos + sem trânsito → 0.71 → Vai à praia
Sem sol + amigos + trânsito → 0.38 → Fica em casa
Sol + sem amigos + trânsito → 0.45 → Fica em casa
```

Repare que **o neurônio ainda não aprende nada**: os pesos foram escolhidos à mão. Aprender, ou seja, encontrar bons pesos sozinho, é o assunto dos próximos módulos.

### 🌍 No mundo real (Módulo 1)

**Exemplo cotidiano: aprovação de crédito.** Um banco quer decidir "aprovar ou negar". As entradas são renda, tempo de emprego e dívidas. Cada uma tem um peso (a renda pesa positivo, as dívidas pesam negativo), o neurônio soma tudo e decide. É exatamente a "votação ponderada" do Módulo 1.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** esses modelos são feitos de bilhões desses mesmos cálculos (multiplicar por pesos, somar, ativar), repetidos em paralelo. Os **"parâmetros"** citados quando se fala em "modelo de X bilhões de parâmetros" são, em grande parte, os **pesos** de neurônios como o que você acabou de ver. A diferença está na escala, não na ideia.

> ℹ️ A arquitetura completa desses produtos não é pública. O que é consolidado é que os grandes modelos de linguagem atuais são baseados em **Transformers**, e cada camada de um Transformer contém um bloco *feed-forward* (um MLP), além do mecanismo de atenção.

---

## Módulo 2: O Perceptron - O Neurônio Mais Simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural. É um único neurônio que:

- Recebe várias entradas (0 ou 1)
- Calcula uma combinação com pesos
- Decide a saída (0 ou 1)

**Exemplo prático (continuando a praia):** as mesmas 3 entradas do Módulo 1 (sol, amigos, trânsito), cada uma 0 ou 1, e a saída é 1 ("vai à praia") ou 0 ("fica em casa"). A diferença: agora usamos a função degrau, em vez da Sigmoid, e o Perceptron vai **descobrir os pesos sozinho**.

### 2.2. Como um Perceptron Aprende?

1. Iniciamos com **pesos aleatórios**
2. Para cada exemplo de treino, calculamos a saída
3. Comparamos com a resposta correta
4. Se errou, **ajustamos os pesos** para melhorar
5. Repetimos muitas vezes

**Fórmula do erro:**

```
erro = valor_esperado - valor_obtido
```

### 2.3. Implementando um Perceptron em Java (Simplificado)

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
            pesos[i] = random.nextDouble() * 2 - 1; // entre -1 e 1
        }
        bias = random.nextDouble() * 2 - 1;
    }

    // Função degrau: se >= 0 retorna 1, senão 0
    private int funcaoAtivacao(double soma) {
        return soma >= 0 ? 1 : 0;
    }

    // Calcula a saída do perceptron
    public int calcular(double[] entradas) {
        double soma = 0;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        soma += bias;
        return funcaoAtivacao(soma);
    }

    // Treina o perceptron com um exemplo
    public void treinar(double[] entradas, int esperado) {
        int obtido = calcular(entradas);
        double erro = esperado - obtido;

        // Ajusta os pesos
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        bias += taxaAprendizado * erro;
    }
}
```

> ⚠️ **Importante:** este código é para aprendizado. Em projetos reais, use bibliotecas como Deeplearning4j.

#### Ensinando o Perceptron a decidir "vou à praia?"

No Módulo 1 escolhemos os pesos à mão. Agora damos ao Perceptron a **tabela de decisões** (as 8 combinações possíveis) e deixamos que ele encontre os pesos:

| Sol | Amigos | Trânsito | Decisão |
| --- | ------ | -------- | ------- |
| 0   | 0      | 0        | 0 (casa)  |
| 0   | 0      | 1        | 0 (casa)  |
| 0   | 1      | 0        | 1 (praia) |
| 0   | 1      | 1        | 0 (casa)  |
| 1   | 0      | 0        | 1 (praia) |
| 1   | 0      | 1        | 0 (casa)  |
| 1   | 1      | 0        | 1 (praia) |
| 1   | 1      | 1        | 1 (praia) |

```java
public class TreinaPraia {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(3);

        // Colunas: sol, amigos, trânsito
        double[][] entradas = {
            {0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1},
            {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}
        };
        int[] esperados = {0, 0, 1, 0, 1, 0, 1, 1};

        // Treina por 1000 épocas
        for (int epoca = 0; epoca < 1000; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        int acertos = 0;
        for (int i = 0; i < entradas.length; i++) {
            int saida = p.calcular(entradas[i]);
            if (saida == esperados[i]) acertos++;
            System.out.println("Sol=" + (int) entradas[i][0]
                    + " Amigos=" + (int) entradas[i][1]
                    + " Trânsito=" + (int) entradas[i][2]
                    + " → " + (saida == 1 ? "Vai à praia" : "Fica em casa"));
        }
        System.out.println("Acertos: " + acertos + "/8");
    }
}
```

**Saída esperada** (os pesos finais mudam a cada execução porque começam aleatórios, mas as decisões devem bater com a tabela):

```
Sol=0 Amigos=0 Trânsito=0 → Fica em casa
Sol=0 Amigos=0 Trânsito=1 → Fica em casa
Sol=0 Amigos=1 Trânsito=0 → Vai à praia
Sol=0 Amigos=1 Trânsito=1 → Fica em casa
Sol=1 Amigos=0 Trânsito=0 → Vai à praia
Sol=1 Amigos=0 Trânsito=1 → Fica em casa
Sol=1 Amigos=1 Trânsito=0 → Vai à praia
Sol=1 Amigos=1 Trânsito=1 → Vai à praia
Acertos: 8/8
```

Isso funciona porque essa tabela pode ser separada por **um único plano** (a mesma regra do neurônio do Módulo 1). Guarde essa ideia: ela volta na seção 3.4.

### 🌍 No mundo real (Módulo 2)

**Exemplo histórico e atual: decisão sim/não.** O Mark I Perceptron (final dos anos 1950) usava uma câmera de baixa resolução para distinguir formas simples. Hoje, a mesma lógica de "um neurônio que decide sim/não" aparece em filtro de spam, detecção de fraude em cartão e classificação "conteúdo permitido / não permitido".

**Onde isso aparece nos assistentes de IA:** a última etapa de um modelo de linguagem é uma camada linear que dá uma **nota para cada palavra (token) possível** do vocabulário, e a seguir se escolhe a próxima palavra. É uma versão em larga escala do Perceptron: pesos × entradas → pontuação. Além disso, sistemas de moderação e de segurança que filtram pedidos e respostas costumam incluir classificadores que, no fim, dão uma resposta do tipo "sim/não" ou uma probabilidade.

---

## Módulo 3: Aprendizado e Treinamento

### 3.1. Como a Rede "Aprende"?

O aprendizado em redes neurais é um processo de **tentativa e erro**:

1. **Forward Propagation**: os dados passam pela rede gerando uma saída
2. **Cálculo do erro**: comparamos a saída com o esperado
3. **Backpropagation**: o erro volta pela rede ajustando os pesos
4. **Repetição**: fazemos isso milhares de vezes

### 3.2. Conceitos Importantes

- **Época (Epoch)**: uma rodada completa de treino com todos os dados
- **Função de Ativação**: transforma a soma em uma saída (ex.: Sigmoid, Tanh, ReLU)
- **Taxa de Aprendizado**: controla o quanto os pesos são ajustados por erro

### 3.3. Um MLP (Perceptron Multicamadas) Simplificado

Um MLP é um conjunto de neurônios organizados em **camadas**: entrada, uma ou mais camadas ocultas e saída. Em código (pseudocódigo ilustrativo):

```java
// Criando uma rede para a praia: 3 entradas (sol, amigos, trânsito),
// 1 camada oculta de 4 neurônios e 1 saída ("vai à praia?")
int[] arquitetura = {3, 4, 1};
RedeNeural rede = new RedeNeural(arquitetura);
```

### 3.4. O Limite do Perceptron: o Problema XOR

#### 3.4.1. Começando por uma situação do dia a dia

Voltemos à praia. Até agora, todas as regras eram do tipo "quanto mais sol e mais amigos, melhor". Agora, um cenário diferente: **dois amigos, Ana e Bruno, podem me convidar**, cada um para um programa diferente.

- Ninguém me chamou: **fico em casa**.
- Só a Ana me chamou: **vou à praia**.
- Só o Bruno me chamou: **vou à praia**.
- Os dois me chamaram: os programas conflitam e **acabo ficando em casa**.

Isso é a operação **XOR** (OU exclusivo): a saída é 1 quando as entradas são **diferentes**.

> 💡 Outra imagem clássica do XOR: uma escada com **dois interruptores** (um embaixo, outro em cima). Com os dois desligados ou os dois ligados, a lâmpada fica apagada; só acende quando **um** deles está ligado.

| Entrada 1 | Entrada 2 | AND | OR | **XOR** |
| --------- | --------- | --- | -- | ------- |
| 0         | 0         | 0   | 0  | **0**   |
| 0         | 1         | 0   | 1  | **1**   |
| 1         | 0         | 0   | 1  | **1**   |
| 1         | 1         | 1   | 1  | **0**   |

No exercício do Módulo 5, o Perceptron aprende o AND sem dificuldade. Com o XOR, ele **nunca** acerta as quatro linhas, por mais que treine.

#### 3.4.2. Por quê? O Perceptron só sabe traçar UMA reta

Um Perceptron calcula `w1·x1 + w2·x2 + bias` e compara com zero. Geometricamente, isso é **uma reta** dividindo o plano em dois lados: de um lado ele responde 0, do outro responde 1.

Vamos colocar as quatro entradas num gráfico (`o` = saída 0, `X` = saída 1):

```
AND (separável)           OR (separável)           XOR (NÃO separável)

x2                        x2                       x2
1 |  o     X              1 |  X     X             1 |  X     o
  |                         |                        |
0 |  o     o              0 |  o     X             0 |  o     X
  +-------------- x1        +-------------- x1       +-------------- x1
     0     1                   0     1                  0     1

Uma reta separa             Uma reta separa          Nenhuma reta separa!
os X dos o                  os X dos o               Os X estão em diagonal
```

No XOR, os dois `X` ficam em cantos **opostos**, e os dois `o` nos outros dois cantos. Por mais que você incline ou mova a reta, sempre sobra um ponto do lado errado.

> 🎯 **Pense assim:** tente separar duas moedas e duas bolinhas, dispostas em xadrez, com **um único risco de caneta** reto. Não dá.

#### 3.4.3. A prova em 4 linhas (opcional, para quem gosta de matemática)

Suponha que existam pesos `w1`, `w2` e `bias` que resolvam o XOR (saída 1 quando `soma >= 0`):

```
(0,0) → saída 0:   bias < 0
(0,1) → saída 1:   w2 + bias >= 0
(1,0) → saída 1:   w1 + bias >= 0
(1,1) → saída 0:   w1 + w2 + bias < 0
```

Some as duas linhas do meio: `w1 + w2 + 2·bias >= 0`. Isso significa `w1 + w2 + bias >= -bias`. Como `bias < 0`, o lado direito é positivo, então `w1 + w2 + bias > 0`. Mas a quarta linha exige `w1 + w2 + bias < 0`. **Contradição.** Logo, não existem pesos que resolvam o XOR com um único Perceptron.

#### 3.4.4. Veja o fracasso acontecer (código)

Usando a classe `Perceptron` do Módulo 2:

```java
public class TesteXorPerceptronSimples {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(2);

        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] esperados = {0, 1, 1, 0}; // XOR

        // Treina por 10.000 épocas (muito mais do que o AND precisou)
        for (int epoca = 0; epoca < 10000; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        int acertos = 0;
        for (int i = 0; i < entradas.length; i++) {
            int saida = p.calcular(entradas[i]);
            boolean ok = saida == esperados[i];
            if (ok) acertos++;
            System.out.println((int) entradas[i][0] + " XOR " + (int) entradas[i][1]
                    + " = " + saida + " (esperado " + esperados[i] + ") "
                    + (ok ? "✔" : "✘"));
        }
        System.out.println("Acertos: " + acertos + "/4");
    }
}
```

**Saída típica** (varia com os pesos aleatórios iniciais, mas **nunca** chega a 4/4):

```
0 XOR 0 = 0 (esperado 0) ✔
0 XOR 1 = 1 (esperado 1) ✔
1 XOR 0 = 0 (esperado 1) ✘
1 XOR 1 = 0 (esperado 0) ✔
Acertos: 3/4
```

Dica para a aula: rode duas ou três vezes e mostre que o resultado muda, mas o 4/4 nunca aparece.

#### 3.4.5. A solução: adicionar uma camada oculta

O XOR pode ser reescrito com operações que o Perceptron **já sabe** fazer:

```
XOR(a, b) = (a OR b) AND NOT (a AND b)
            "pelo menos um"  "mas não os dois"
```

Então usamos **três neurônios em duas camadas**:

```
                 ┌───────────┐
 a ──────────────┤ OR  (h1)  ├──┐
      ╲        ╱ └───────────┘  │  +1
       ╲      ╱                  ├────► [ saída ] ──► XOR
       ╱      ╲                  │  -1
      ╱        ╲ ┌───────────┐  │
 b ──────────────┤ AND (h2)  ├──┘
                 └───────────┘
   entrada       camada oculta     saída
```

Pesos escolhidos à mão (sem treino):

| Neurônio | Peso a  | Peso b  | Bias | Faz o quê     |
| -------- | ------- | ------- | ---- | ------------- |
| h1       | 1       | 1       | -0.5 | OR            |
| h2       | 1       | 1       | -1.5 | AND           |
| saída    | +1 (h1) | -1 (h2) | -0.5 | "h1 e não h2" |

```java
public class XorComCamadaOculta {

    // Função degrau: >= 0 vira 1, senão 0
    static int degrau(double soma) {
        return soma >= 0 ? 1 : 0;
    }

    static int xor(int a, int b) {
        // Camada oculta
        int h1 = degrau(1.0 * a + 1.0 * b - 0.5); // OR
        int h2 = degrau(1.0 * a + 1.0 * b - 1.5); // AND

        // Camada de saída: "h1 E NÃO h2"
        return degrau(1.0 * h1 - 1.0 * h2 - 0.5);
    }

    public static void main(String[] args) {
        int[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        for (int[] e : entradas) {
            System.out.println(e[0] + " XOR " + e[1] + " = " + xor(e[0], e[1]));
        }
    }
}
```

**Saída:**

```
0 XOR 0 = 0
0 XOR 1 = 1
1 XOR 0 = 1
1 XOR 1 = 0
```

Confira na lousa, linha por linha: para `(1,1)`, h1 = 1 e h2 = 1, então a saída é `1 - 1 - 0.5 = -0.5`, que vira 0. ✔

**Em palavras:** a camada oculta **transforma o problema**. Os neurônios h1 e h2 reorganizam os pontos de tal forma que, no novo espaço (h1, h2), uma única reta já resolve. Redes multicamadas "dobram" o espaço até que o problema fique simples.

#### 3.4.6. Detalhe importante: a ativação precisa ser não linear

Se você empilhar camadas **sem função de ativação** (ou com ativação linear), o resultado continua sendo uma única reta. Duas contas lineares seguidas equivalem a uma só. É a **não linearidade** (degrau, Sigmoid, Tanh, ReLU) que dá poder às camadas ocultas.

#### 3.4.7. Um pouco de história

- **1958:** Rosenblatt apresenta o Perceptron e a imprensa se empolga.
- **1969:** Minsky e Papert publicam *Perceptrons*, mostrando formalmente limites como o do XOR. O financiamento em redes neurais despenca (o primeiro "inverno da IA").
- **1986:** Rumelhart, Hinton e Williams popularizam o **backpropagation**, que permite treinar redes com camadas ocultas. O XOR deixa de ser problema.

#### 3.4.8. Checkpoint ✅

1. Por que um único Perceptron consegue o AND e o OR, mas não o XOR?
2. O que a camada oculta faz que o Perceptron simples não faz?
3. O que acontece se todas as ativações forem lineares?

### 🌍 No mundo real (Módulo 3)

**Exemplo cotidiano: desbloqueio facial e recomendação de vídeos.** Esses sistemas começam com pesos ruins, erram muito e vão sendo corrigidos milhões de vezes por **backpropagation** até acertarem. É o ciclo: forward → erro → backpropagation → repetir. E o XOR mostra por que isso exigiu camadas ocultas: o mundo real está cheio de situações do tipo "uma coisa **ou** outra, mas não as duas" (por exemplo, "promoção **ou** frete grátis, não os dois juntos"), que nenhuma reta resolve.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** o treinamento desses modelos é, em essência, o mesmo laço do Módulo 3 em escala gigante. O modelo lê um texto, tenta **prever a próxima palavra**, mede o erro e ajusta os pesos por backpropagation (com variantes modernas do gradiente descendente). Isso se repete sobre quantidades enormes de texto, em milhares de aceleradores. Depois há etapas adicionais de ajuste fino, inclusive com feedback humano, mas a base é este ciclo. **Taxa de aprendizado** e **épocas** também existem lá, com muito cuidado de ajuste.

---

## Módulo 4: Ferramentas e Bibliotecas em Java

### 4.1. Por que Java?

Java é uma das linguagens mais usadas no mundo corporativo, com bom desempenho e um ecossistema maduro para colocar sistemas em produção.

### 4.2. Bibliotecas Recomendadas

| Biblioteca               | Uso                           | Link                                                             |
| ------------------------ | ----------------------------- | ---------------------------------------------------------------- |
| **Deeplearning4j**       | A mais completa para Java     | [deeplearning4j.org](https://deeplearning4j.org)                 |
| **ND4J**                 | Computação numérica para Java | [nd4j.org](https://deeplearning4j.org/docs/latest/nd4j-overview) |
| **Basic Neural Network** | Para aprendizado, simples     | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork)       |

**Exemplo com Basic Neural Network Library:**

```java
import de.hatoka.basicneuralnetwork.NeuralNetwork;
import de.hatoka.basicneuralnetwork.NetworkBuilder;

// Rede com 2 entradas, 2 camadas ocultas com 4 neurônios, 1 saída
NeuralNetwork rede = NetworkBuilder.create(2, 1)
    .setHiddenLayers(2, 4)
    .setActivationFunction(ActivationFunctions.TANH)
    .build();

// Treino
rede.train(dadosTreino, respostasEsperadas);

// Previsão
double[] resultado = rede.guess(dadosTeste);
```

### 4.3. Carregando Modelos Treinados em Java

É possível treinar modelos em Python com Keras/TensorFlow e importá-los para Java:

```java
// Importando modelo Keras para Java
String caminhoModelo = new ClassPathResource("meu_modelo.h5").getFile().getPath();
MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);
```

### 🌍 No mundo real (Módulo 4)

**Exemplo corporativo: Java em produção.** Bancos, seguradoras e grandes empresas têm sistemas inteiros em Java (back-end, filas, APIs). Um fluxo comum: o time de dados **treina** o modelo em Python e o time de back-end o **serve** em Java (por exemplo, importando o modelo com Deeplearning4j, como na seção 4.3, ou usando runtimes de inferência com API Java). Vantagem: o modelo entra no mesmo sistema, com o mesmo monitoramento e a mesma segurança que o restante da empresa.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** você não precisa treinar nada para usá-los em Java. Esses serviços são consumidos por **API**, e há SDKs para Java (consulte a documentação oficial de cada fornecedor para a versão atual). Um sistema Java pode, por exemplo, enviar o e-mail de um cliente para um desses modelos e receber de volta um resumo ou a categoria do chamado. Na prática, muitos sistemas combinam as duas coisas: uma rede neural pequena e rápida em Java para decisões simples (fraude, recomendação) e um modelo de linguagem grande via API para tarefas com texto.

---

## Módulo 5: Exercício Prático - Rede Neural do Zero

### Desafio: Classificador AND Simples

Crie uma rede neural que aprenda a operação lógica AND:

| Entrada 1 | Entrada 2 | Saída |
| --------- | --------- | ----- |
| 0         | 0         | 0     |
| 0         | 1         | 0     |
| 1         | 0         | 0     |
| 1         | 1         | 1     |

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

        // Testa
        for (double[] entrada : entradas) {
            System.out.println(entrada[0] + " AND " + entrada[1] +
                               " = " + p.calcular(entrada));
        }
    }
}
```

**Desafio extra:** troque `esperados` por `{0, 1, 1, 1}` (OR) e depois por `{0, 1, 1, 0}` (XOR). O que acontece em cada caso? Relacione com a seção 3.4.

### 🌍 No mundo real (Módulo 5)

**Exemplo cotidiano: regra "E" no negócio.** "Liberar o empréstimo **somente se** a renda for suficiente **E** não houver dívida em atraso." É um AND: só dispara quando as duas condições se verificam. O exercício do módulo é o mesmo princípio em miniatura, mas com os pesos **aprendidos** a partir de exemplos, em vez de escritos à mão por um programador.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** muitos modelos de linguagem recentes usam, dentro dos blocos feed-forward, ativações com **portas (gates)**, em que um sinal é **multiplicado** por outro para decidir o quanto passa adiante (variantes como GLU, GeGLU e SwiGLU aparecem em modelos abertos como Llama e Gemma). Essa multiplicação lembra um AND "suave": o sinal só passa forte quando os dois fatores são fortes. Os detalhes internos dos modelos fechados não são públicos, então apresente este ponto como "padrão comum em modelos modernos", e não como fato confirmado sobre cada produto.

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* - Fabio Soares, Alan M.F. Souza
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) - exemplos de Perceptron e MLP em Java
3. **micrograd-java**: implementação completa de redes neurais do zero
