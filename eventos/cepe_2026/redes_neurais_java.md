# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.

**Fio condutor do minicurso:** uma pergunta do dia a dia — **"Vou à praia?"** Em todos os módulos vamos voltar a ela: primeiro como analogia, depois como dados, depois como código.

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra uma figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos**.

### 1.2. O problema que vamos resolver: "Vou à praia?"

Pense em como você decide ir à praia num sábado. Normalmente você olha para poucos fatores:

| Fator | Pergunta | Valor |
|---|---|---|
| ☀️ **Sol** | Está fazendo sol? | 1 = sim, 0 = não |
| 👯 **Amigos** | Os amigos vão? | 1 = sim, 0 = não |
| 🚗 **Trânsito** | Está com trânsito pesado? | 1 = sim, 0 = não |

E a resposta é **1 = vou** ou **0 = não vou**.

Você faz isso sem pensar, mas note: nem todo fator pesa igual. Sol pesa muito. Amigos ajudam. Trânsito atrapalha. **Uma rede neural aprende exatamente esses "pesos" a partir de exemplos.**

### 1.3. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo parecido:

```
Entradas → Multiplica por Pesos → Soma → Função de Ativação → Saída
```

Aplicado à praia:

```
(sol, amigos, trânsito) → × pesos → soma + bias → decide → vou / não vou
```

Cada entrada tem um **peso** (um número que indica sua importância). O neurônio:

1. Multiplica cada entrada pelo seu peso
2. Soma tudo (incluindo um **bias** ou viés)
3. Passa o resultado por uma **função de ativação** que decide a saída

> 🎯 **Pense assim:** é um sistema de "votação ponderada". Cada entrada vota com uma força diferente (o sol vota forte, o trânsito vota contra), e no final decidimos se o resultado é "sim" ou "não".

> 💡 **E o bias?** É a "má vontade (ou boa vontade) de base" de quem decide. Um bias muito negativo descreve alguém preguiçoso, que só sai de casa se tudo ajudar. Um bias positivo descreve alguém que sempre topa.

### 🌎 No mundo real (Módulo 1)

**Exemplo cotidiano: aprovação de crédito.** Um banco quer decidir "aprovar ou negar". As entradas são renda, tempo de emprego e dívidas. Cada uma tem um peso (a renda pesa positivo, as dívidas pesam negativo), o neurônio soma tudo e decide. É exatamente a "votação ponderada" do Módulo 1.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** esses modelos são feitos de bilhões desses mesmos cálculos (multiplicar por pesos, somar, ativar), repetidos em paralelo. Os "parâmetros" citados quando se fala em "modelo de X bilhões de parâmetros" são, em grande parte, os pesos de neurônios como o que você acabou de ver. A diferença está na escala, não na ideia.

> ℹ️ A arquitetura completa desses produtos não é pública. O que é consolidado é que os grandes modelos de linguagem atuais são baseados em Transformers, e cada camada de um Transformer contém um bloco feed-forward (um MLP), além do mecanismo de atenção.

---

## Módulo 2: O Perceptron – O Neurônio Mais Simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural. É um único neurônio que:

- Recebe várias entradas (aqui, 0 ou 1)
- Calcula uma combinação com pesos
- Decide a saída (0 ou 1)

### 2.2. Fazendo a conta à mão

Suponha que alguém já tenha descoberto estes valores:

| | Sol | Amigos | Trânsito | Bias |
|---|---|---|---|---|
| **Peso** | +2 | +1 | −1 | −1,5 |

Regra: se `soma >= 0`, a saída é 1 (vou); senão, 0 (não vou).

**Caso 1:** sol, amigos vão, sem trânsito → (1, 1, 0)

```
soma = 2×1 + 1×1 + (−1)×0 − 1,5 = 1,5   → ≥ 0 → vou ✅
```

**Caso 2:** sol, sem amigos, com trânsito → (1, 0, 1)

```
soma = 2×1 + 1×0 + (−1)×1 − 1,5 = −0,5  → < 0 → não vou ❌
```

**Caso 3:** sem sol, amigos vão, sem trânsito → (0, 1, 0)

```
soma = 2×0 + 1×1 + (−1)×0 − 1,5 = −0,5  → < 0 → não vou ❌
```

> 🗣️ **Para discutir com a turma:** o que o peso −1 do trânsito significa? E se o trânsito pesasse −3? Em que situações a pessoa nunca mais iria à praia?

A tabela completa dos 8 casos possíveis (e que esses pesos reproduzem corretamente):

| Sol | Amigos | Trânsito | Vou? |
|:-:|:-:|:-:|:-:|
| 1 | 1 | 0 | **1** |
| 1 | 0 | 0 | **1** |
| 1 | 1 | 1 | **1** |
| 1 | 0 | 1 | 0 |
| 0 | 1 | 0 | 0 |
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 0 | 0 | 1 | 0 |

Em português: *"Sem sol, nem pensar. Com sol, eu vou, a não ser que esteja sem amigos e com trânsito."*

### 2.3. Como um Perceptron Aprende?

Na prática, **ninguém nos entrega os pesos**. Queremos que o Perceptron os descubra sozinho olhando a tabela acima.

1. Iniciamos com **pesos aleatórios**
2. Para cada exemplo de treino, calculamos a saída
3. Comparamos com a resposta correta
4. Se errou, **ajustamos os pesos** para melhorar
5. Repetimos muitas vezes

**Fórmula do erro:**

```
erro = valor_esperado - valor_obtido
```

O erro só pode ser `0` (acertou), `+1` (devia ir e não foi) ou `-1` (foi e não devia). A regra de ajuste é:

```
peso = peso + taxa_de_aprendizado × erro × entrada
bias = bias + taxa_de_aprendizado × erro
```

Repare que, se a **entrada era 0**, o peso dela não muda (ela não "participou" da decisão). Se o Perceptron errou por ficar em casa quando devia ir, os pesos das entradas ativas **aumentam**. Se foi quando não devia, eles **diminuem**.

### 2.4. Implementando um Perceptron em Java

Arquivo `Perceptron.java`:

```java
import java.util.Arrays;
import java.util.Random;

public class Perceptron {
    private final double[] pesos;
    private double bias;
    private final double taxaAprendizado = 0.1;

    public Perceptron(int numeroEntradas) {
        Random random = new Random();
        pesos = new double[numeroEntradas];
        for (int i = 0; i < numeroEntradas; i++) {
            pesos[i] = random.nextDouble() * 2 - 1; // entre -1 e 1
        }
        bias = random.nextDouble() * 2 - 1;
    }

    // Função degrau: se soma >= 0 retorna 1, senão 0
    private int funcaoAtivacao(double soma) {
        return soma >= 0 ? 1 : 0;
    }

    // Calcula a saída do perceptron
    public int calcular(double[] entradas) {
        double soma = bias;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        return funcaoAtivacao(soma);
    }

    // Treina com um exemplo e devolve o erro (esperado - obtido)
    public int treinar(double[] entradas, int esperado) {
        int erro = esperado - calcular(entradas);
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        bias += taxaAprendizado * erro;
        return erro;
    }

    // Para podermos "espiar" o que a rede aprendeu
    @Override
    public String toString() {
        return "pesos=" + Arrays.toString(pesos) + " bias=" + bias;
    }
}
```

### 2.5. Treinando o Perceptron para decidir sobre a praia

Arquivo `VouAPraia.java` (na mesma pasta do `Perceptron.java`):

```java
public class VouAPraia {
    public static void main(String[] args) {
        // Cada linha: {sol, amigos, transito}
        double[][] entradas = {
            {1, 1, 0}, {1, 0, 0}, {1, 1, 1}, {1, 0, 1},
            {0, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 1}
        };
        // Respostas esperadas: 1 = vou, 0 = não vou
        int[] esperados = {1, 1, 1, 0, 0, 0, 0, 0};

        Perceptron p = new Perceptron(3);

        for (int epoca = 1; epoca <= 100; epoca++) {
            int erros = 0;
            for (int i = 0; i < entradas.length; i++) {
                if (p.treinar(entradas[i], esperados[i]) != 0) erros++;
            }
            if (epoca <= 5 || erros == 0) {
                System.out.println("Época " + epoca + " | erros: " + erros + " | " + p);
            }
            if (erros == 0) break; // aprendeu tudo, pode parar
        }

        System.out.println();
        for (double[] e : entradas) {
            System.out.printf("sol=%.0f amigos=%.0f trânsito=%.0f -> vou? %d%n",
                e[0], e[1], e[2], p.calcular(e));
        }
    }
}
```

Compile e execute:

```bash
javac Perceptron.java VouAPraia.java
java VouAPraia
```

**Exemplo de saída** (os números mudam a cada execução, porque os pesos iniciais são aleatórios):

```
Época 1 | erros: 5 | pesos=[-0.21, -0.09, -0.76] bias=0.10
Época 2 | erros: 4 | pesos=[-0.01, 0.01, -0.66] bias=0.10
Época 3 | erros: 3 | pesos=[0.09, 0.01, -0.56] bias=0.00
...
Época 13 | erros: 0 | pesos=[0.49, 0.21, -0.36] bias=-0.30
```

Observe: o número de **erros cai** a cada época, o peso do **sol sobe**, o dos **amigos sobe um pouco** e o do **trânsito fica negativo**. Os valores exatos são diferentes dos nossos (+2, +1, −1, −1,5), mas **o comportamento é o mesmo**. Existem muitos conjuntos de pesos que resolvem o mesmo problema.

> ⚠️ **Importante:** este código é para aprendizado. Em projetos reais, use bibliotecas como Deeplearning4j.

### 🌎 No mundo real (Módulo 2)

**Exemplo cotidiano: filtro de spam.** Imagine um filtro simples com três entradas (0 ou 1): a mensagem contém a palavra "grátis"? O remetente é desconhecido? Há um link encurtado? Cada sinal suspeito tem um peso positivo, e a saída é 1 (spam) ou 0 (não spam). Cada vez que você aperta "marcar como spam", gera um exemplo etiquetado, como as linhas da tabela da praia. Filtros reais usam modelos bem mais sofisticados, mas a lógica de "somar sinais com pesos" é a mesma.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** cada neurônio dentro desses modelos é um parente próximo do Perceptron: soma ponderada das entradas, mais um bias, seguida de uma função de ativação. Há duas diferenças importantes. Em vez do degrau (0 ou 1), muitos modelos usam ativações suaves, como o GELU ou variações do ReLU, que permitem o treino que veremos no Módulo 3. E as entradas não são "sol" ou "trânsito", mas listas de números (vetores) que representam pedaços de texto.

> ℹ️ O Perceptron de 1958 é o ancestral dessas redes. O que mudou desde então foi a função de ativação, a escala e a forma de treinar.

---

## Módulo 3: Aprendizado e Treinamento

### 3.1. Como a Rede "Aprende"?

O aprendizado em redes neurais é um processo de **tentativa e erro**:

1. **Forward Propagation**: os dados passam pela rede gerando uma saída ("com sol, sem amigos e com trânsito, a rede diz 0,8: provavelmente vou")
2. **Cálculo do erro**: comparamos a saída com o esperado (o correto era 0, então o erro é grande)
3. **Backpropagation**: o erro volta pela rede ajustando os pesos de acordo com a "culpa" de cada um
4. **Repetição**: fazemos isso milhares de vezes

### 3.2. Conceitos Importantes

- **Época (Epoch):** uma rodada completa de treino com todos os dados (no nosso caso, as 8 linhas da tabela)
- **Função de Ativação:** transforma a soma em uma saída (ex.: Degrau, Sigmoid, Tanh, ReLU)
- **Taxa de Aprendizado:** controla o quanto os pesos são ajustados a cada erro. Alta demais, a rede "oscila" e não se estabiliza; baixa demais, demora muito.

**Sigmoid:** diferente do degrau (que só diz 0 ou 1), a sigmoid devolve um número **entre 0 e 1**, que podemos ler como *"quão provável é eu ir à praia"*: 0,95 é "quase certeza que vou", 0,5 é "indeciso", 0,05 é "nem pensar".

### 3.3. Uma limitação do Perceptron: o "Zé do Contra"

Existem decisões que um único neurônio **não consegue** aprender. Conheça o **Zé do Contra**. Ele só vai à praia se tiver sol, e além disso:

- Se os **amigos vão** e a **estrada está livre** → vai (o programa clássico).
- Se os **amigos desistiram** por causa do **trânsito** → vai também, aproveitar a praia vazia.
- Se os amigos vão **e** tem trânsito → desiste: "vai estar tudo cheio".
- Se não tem amigos **e** a estrada está livre → fica em casa: "sozinho, não tem graça".

Ou seja: com sol, ele vai quando **exatamente um** dos dois (amigos ou trânsito) acontece. É o famoso **XOR** ("ou exclusivo"), disfarçado de praia.

| Sol | Amigos | Trânsito | Zé vai? |
|:-:|:-:|:-:|:-:|
| 1 | 1 | 0 | **1** |
| 1 | 0 | 1 | **1** |
| 1 | 0 | 0 | 0 |
| 1 | 1 | 1 | 0 |
| 0 | 1 | 0 | 0 |
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 0 | 0 | 1 | 0 |

**Nenhuma combinação de pesos e bias** acerta as 8 linhas com um único Perceptron. Se você trocar o `esperados` do exemplo anterior por `{1, 0, 0, 1, 0, 0, 0, 0}` (mantendo a ordem das `entradas` do código) e rodar, o número de erros nunca chega a zero, por mais épocas que você use.

> 🗣️ **Pergunta para a turma:** por que um único neurônio não consegue? *Dica:* para o neurônio, "amigos" sempre empurra para o mesmo lado (sempre ajuda, ou sempre atrapalha). Mas para o Zé, amigos ajudam **quando não há trânsito** e atrapalham **quando há**.

### 3.4. A solução: camadas ocultas (MLP)

A saída é usar **mais de um neurônio**, organizados em camadas. Esse é o **Perceptron Multicamadas (MLP)**:

```
 Entradas          Camada oculta         Saída
 (sol)     ──┐
 (amigos)  ──┼──►  neurônio 1  ──┐
 (trânsito)──┤     neurônio 2  ──┼──►  neurônio final  →  vou / não vou
             └──►  neurônio 3  ──┤
                   neurônio 4  ──┘
```

Intuição para o Zé do Contra: a camada oculta pode aprender "mini-decisões", como *"amigos e estrada livre"* e *"sem amigos e com trânsito"*, e o neurônio final combina as duas com um "ou". Cada neurônio é simples, mas **juntos** resolvem o que um sozinho não resolve.

### 3.5. Implementando um MLP em Java

Esta rede tem 1 camada oculta, ativação sigmoid e aprende por **backpropagation**. O código é mais longo, mas cada parte corresponde a um passo da seção 3.1. Arquivo `RedeNeural.java`:

```java
import java.util.Random;

public class RedeNeural {
    private final int nEntradas, nOcultos;
    private final double[][] w1;   // pesos entrada -> oculta: [oculto][entrada]
    private final double[] b1;     // bias de cada neurônio oculto
    private final double[] w2;     // pesos oculta -> saída
    private double b2;             // bias da saída
    private final double taxa;

    public RedeNeural(int nEntradas, int nOcultos, double taxa, long semente) {
        this.nEntradas = nEntradas;
        this.nOcultos = nOcultos;
        this.taxa = taxa;
        Random r = new Random(semente);
        w1 = new double[nOcultos][nEntradas];
        b1 = new double[nOcultos];
        w2 = new double[nOcultos];
        for (int j = 0; j < nOcultos; j++) {
            for (int i = 0; i < nEntradas; i++) w1[j][i] = r.nextGaussian();
            w2[j] = r.nextGaussian();
        }
    }

    private static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    // Saída de cada neurônio da camada oculta
    private double[] camadaOculta(double[] x) {
        double[] h = new double[nOcultos];
        for (int j = 0; j < nOcultos; j++) {
            double soma = b1[j];
            for (int i = 0; i < nEntradas; i++) soma += w1[j][i] * x[i];
            h[j] = sigmoid(soma);
        }
        return h;
    }

    // FORWARD: devolve um valor entre 0 e 1 ("probabilidade" de ir à praia)
    public double prever(double[] x) {
        double[] h = camadaOculta(x);
        double soma = b2;
        for (int j = 0; j < nOcultos; j++) soma += w2[j] * h[j];
        return sigmoid(soma);
    }

    // Um passo de treino com um exemplo: forward + erro + backpropagation
    public double treinar(double[] x, double esperado) {
        // 1) Forward
        double[] h = camadaOculta(x);
        double soma = b2;
        for (int j = 0; j < nOcultos; j++) soma += w2[j] * h[j];
        double saida = sigmoid(soma);

        // 2) Erro
        double erro = saida - esperado;

        // 3) Backpropagation: de quem é a "culpa"?
        double dSaida = erro * saida * (1 - saida);
        for (int j = 0; j < nOcultos; j++) {
            double dOculto = dSaida * w2[j] * h[j] * (1 - h[j]);
            w2[j] -= taxa * dSaida * h[j];
            for (int i = 0; i < nEntradas; i++) {
                w1[j][i] -= taxa * dOculto * x[i];
            }
            b1[j] -= taxa * dOculto;
        }
        b2 -= taxa * dSaida;

        return erro * erro; // erro quadrático, para acompanharmos o treino
    }
}
```

Arquivo `ZeDoContra.java`:

```java
public class ZeDoContra {
    public static void main(String[] args) {
        // {sol, amigos, transito}
        double[][] x = {
            {1, 1, 0}, {1, 0, 1}, {1, 0, 0}, {1, 1, 1},
            {0, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 1}
        };
        double[] y = {1, 1, 0, 0, 0, 0, 0, 0};

        // 3 entradas, 4 neurônios na camada oculta, taxa 0.5, semente 1
        int[] arquitetura = {3, 4};
        RedeNeural rede = new RedeNeural(arquitetura[0], arquitetura[1], 0.5, 1);

        for (int epoca = 1; epoca <= 10000; epoca++) {
            double erroTotal = 0;
            for (int i = 0; i < x.length; i++) {
                erroTotal += rede.treinar(x[i], y[i]);
            }
            if (epoca == 1 || epoca % 2000 == 0) {
                System.out.printf("Época %5d | erro total: %.4f%n", epoca, erroTotal);
            }
        }

        System.out.println();
        for (int i = 0; i < x.length; i++) {
            double p = rede.prever(x[i]);
            System.out.printf("sol=%.0f amigos=%.0f trânsito=%.0f -> %.3f (%s)%n",
                x[i][0], x[i][1], x[i][2], p, p >= 0.5 ? "vai" : "fica");
        }
    }
}
```

```bash
javac RedeNeural.java ZeDoContra.java
java ZeDoContra
```

Ao final, as 8 linhas devem ser acertadas, com saídas próximas de 0 ou de 1 (valores acima de 0,95 para os dois casos de "vai" e abaixo de 0,05 para os demais).

> 🧪 **Experimente:**
> - Mude `4` neurônios ocultos para `1`. O que acontece? (*Dica:* com poucos neurônios, a rede pode não ter capacidade de aprender o Zé.)
> - Mude a taxa de `0.5` para `5.0` e para `0.001`. Compare o erro ao longo das épocas.
> - Mude a semente (último argumento do construtor). Os pesos iniciais mudam, mas o resultado final deveria ser parecido.

### 🌎 No mundo real (Módulo 3)

**Exemplo cotidiano: ler números escritos à mão.** Reconhecer dígitos em cheques ou CEPs em envelopes é um problema clássico para redes MLP. As entradas são os pixels da imagem, a camada oculta aprende "mini-decisões" (uma curva aqui, uma linha reta ali) e a saída diz qual dígito, de 0 a 9, a imagem representa. É a mesma ideia do Zé do Contra: a camada oculta combina pequenas decisões que um neurônio sozinho não conseguiria tomar.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** o ciclo de treino é o mesmo da seção 3.1. Na fase de pré-treinamento, o modelo recebe um trecho de texto e tenta prever a próxima palavra (na prática, o próximo "token"). O erro mede o quanto ele errou, o backpropagation ajusta os pesos, e isso se repete um número enorme de vezes sobre volumes gigantes de texto, em grandes conjuntos de GPUs ou TPUs. Depois disso, costuma haver etapas de ajuste adicionais (como fine-tuning e treino com feedback humano) para que o modelo siga instruções e converse melhor. Conceitos como época e taxa de aprendizado continuam valendo, com variações do algoritmo de ajuste.

> ℹ️ A receita exata de dados e treino de cada produto não é pública. O ciclo geral (prever, medir o erro, ajustar os pesos) está descrito na literatura científica.

---

## Módulo 4: Ferramentas e Bibliotecas em Java

### 4.1. Por que Java?

Java é uma das linguagens mais usadas no mundo corporativo. Muitas empresas já têm sistemas em Java e querem **incluir** inteligência neles sem trocar de tecnologia. Na prática, é comum **treinar** modelos em Python e **executá-los** dentro de aplicações Java (veja 4.3).

### 4.2. Bibliotecas Recomendadas

| Biblioteca | Uso | Link |
|---|---|---|
| **Deeplearning4j** | A mais completa para Java | [deeplearning4j.org](https://deeplearning4j.org) |
| **ND4J** | Computação numérica para Java | [nd4j.org](https://deeplearning4j.org/docs/latest/nd4j-overview) |
| **Basic Neural Network** | Para aprendizado, simples | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork) |

**O "Vou à praia?" com Deeplearning4j** (3 entradas, 4 neurônios ocultos, 1 saída). Em vez de escrever backpropagation, só descrevemos a arquitetura:

```java
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

// Arquitetura: 3 entradas -> 4 neurônios ocultos -> 1 saída
MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
    .seed(123)
    .updater(new Sgd(0.5))
    .list()
    .layer(new DenseLayer.Builder().nIn(3).nOut(4)
        .activation(Activation.TANH).build())
    .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
        .nIn(4).nOut(1).activation(Activation.SIGMOID).build())
    .build();

MultiLayerNetwork rede = new MultiLayerNetwork(conf);
rede.init();

// Dados: {sol, amigos, transito} e a resposta esperada (Zé do Contra)
INDArray x = Nd4j.create(new double[][] {
    {1,1,0}, {1,0,1}, {1,0,0}, {1,1,1},
    {0,1,0}, {0,0,0}, {0,1,1}, {0,0,1}
});
INDArray y = Nd4j.create(new double[][] {{1},{1},{0},{0},{0},{0},{0},{0}});

// Treino
for (int epoca = 0; epoca < 5000; epoca++) {
    rede.fit(x, y);
}

// Previsão: sol=1, amigos=1, trânsito=0
INDArray saida = rede.output(Nd4j.create(new double[][] {{1, 1, 0}}));
System.out.println("Chance de ir: " + saida.getDouble(0));
```

> ⚠️ As APIs do Deeplearning4j mudam entre versões. Confira a documentação da versão que você colocar no `pom.xml` ou no `build.gradle` antes de usar este trecho em projetos.

Compare com o Módulo 3: o conceito é **o mesmo** (camadas, pesos, ativação, épocas), mas a biblioteca cuida do backpropagation e é otimizada para dados grandes.

### 4.3. Carregando Modelos Treinados em Java

É possível treinar modelos em Python com Keras/TensorFlow e importá-los para Java. Imagine que um modelo "vou à praia?" foi treinado com milhares de respostas reais de pessoas e salvo como `praia.h5`:

```java
// Importando modelo Keras para Java
String caminhoModelo = new ClassPathResource("praia.h5").getFile().getPath();
MultiLayerNetwork modelo = KerasModelImport
    .importKerasSequentialModelAndWeights(caminhoModelo);

// Usando o modelo importado: sol=1, amigos=1, trânsito=0
INDArray entrada = Nd4j.create(new double[][] {{1, 1, 0}});
INDArray resposta = modelo.output(entrada);
```

### 🌎 No mundo real (Módulo 4)

**Exemplo cotidiano: detecção de fraude em cartão.** Quando você paga algo, o sistema do banco precisa responder em instantes se aprova ou bloqueia a compra. As entradas são valor, local, horário e seu histórico de gastos. É comum que esse tipo de sistema corporativo seja escrito em Java, enquanto o modelo é treinado em Python e importado ou chamado pela aplicação Java, como no item 4.3.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** as bibliotecas deste módulo são versões em pequena escala do que existe por trás desses produtos. Os grandes modelos são treinados com frameworks como PyTorch ou JAX, que fazem o backpropagation automaticamente e rodam em GPUs ou TPUs, o mesmo papel que o Deeplearning4j e o ND4J cumprem no nosso exemplo. Já em aplicações Java do dia a dia, o mais comum é **usar** um modelo desses por API: o programa envia texto por HTTP e recebe a resposta, sem treinar nada. Também existem modelos abertos que podem ser executados localmente.

> ℹ️ Nem sempre as empresas divulgam quais ferramentas usam internamente para treinar seus modelos.

---

## Módulo 5: Exercício Prático – Rede Neural do Zero

### 5.1. Aquecimento (5 minutos): o AND

Antes da praia, um problema bem pequeno. Crie uma rede neural que aprenda a operação lógica AND:

| Entrada 1 | Entrada 2 | Saída |
|:-:|:-:|:-:|
| 0 | 0 | 0 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

```java
public class TestePerceptron {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(2);

        double[][] entradas = {{0,0}, {0,1}, {1,0}, {1,1}};
        int[] esperados = {0, 0, 0, 1};

        // Treina por 100 épocas
        for (int epoca = 0; epoca < 100; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        // Testa
        for (double[] entrada : entradas) {
            System.out.println((int) entrada[0] + " AND " + (int) entrada[1] +
                               " = " + p.calcular(entrada));
        }
    }
}
```

> 💬 Reparem: o AND é "vou à praia" com duas entradas: **só vou se tiver sol E amigos.**

### 5.2. Desafio principal: a SUA praia

Agora cada pessoa treina **a sua própria** rede.

1. Copie a tabela de 8 linhas do Módulo 2.
2. Preencha a coluna **"Vou?"** com a **sua** vontade real (seja sincero(a)!). Por exemplo: "Eu odeio trânsito: se tiver trânsito, não vou de jeito nenhum, nem com sol e amigos."
3. Coloque suas respostas no vetor `esperados` do `VouAPraia.java` e treine.
4. Anote os pesos finais e responda:
   - Qual fator tem o **maior peso** (em valor absoluto) para você?
   - Algum peso ficou **negativo**? Qual?
   - O Perceptron **conseguiu** acertar todas as linhas? Se não, o que sua regra tem de "estranho"?
5. Compare seus pesos com os do colega ao lado. **A rede aprendeu o que o dado ensinou:** pessoas diferentes geram redes diferentes.

### 5.3. Desafio extra: use o MLP

Se o seu Perceptron **não conseguiu** aprender suas respostas (como aconteceu com o Zé do Contra), troque o `Perceptron` pela `RedeNeural` do Módulo 3 e treine de novo com os seus dados. Agora ela acerta? Quantos neurônios ocultos foram necessários?

### 5.4. Desafio bônus (para quem terminar antes)

- Adicione uma **4ª entrada**: *"Tem dinheiro para o lanche?"* Reescreva a tabela (agora com 16 linhas!) e treine novamente. O que muda nos pesos?
- Faça o programa **perguntar** ao usuário pelo teclado (`Scanner`) se tem sol, se os amigos vão e se tem trânsito, e **responder** "Vou à praia!" ou "Fico em casa" com base na rede treinada.

### 🌎 No mundo real (Módulo 5)

**Exemplo cotidiano: recomendações de música e vídeo.** No desafio 5.2 você treinou uma rede com as **suas** respostas e viu que cada pessoa gera pesos diferentes. Plataformas de streaming fazem algo parecido em escala: aprendem com o histórico de cada usuário, e quem só ouve rock tende a receber mais rock. Isso também mostra um risco: se os dados de treino forem enviesados, a rede aprende o viés.

Há ainda uma diferença importante. As nossas 8 linhas cobrem **todos** os casos possíveis, então a rede só precisou "decorar" a tabela. No mundo real, o desafio é acertar situações que a rede **nunca viu**. Por isso, em projetos de verdade, separa-se uma parte dos dados para treino e outra para teste.

**Onde isso aparece em Claude, ChatGPT, Gemini e similares:** o princípio é o mesmo, "a rede aprende o que os dados ensinam". O conhecimento, o estilo e também as limitações e os vieses desses modelos vêm em grande parte dos dados de treino. E eles são avaliados com exemplos que não fizeram parte do treino, justamente para medir se generalizam. Quanto ao desafio 5.3 (mais neurônios, mais capacidade de aprender padrões complexos), ele ajuda a entender por que esses modelos cresceram tanto em escala.

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* – Fabio Soares, Alan M.F. Souza
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) – exemplos de Perceptron e MLP em Java
3. **micrograd-java**: implementação completa de redes neurais do zero
