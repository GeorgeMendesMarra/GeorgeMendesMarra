# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.
**Pré-requisito:** noções básicas de Java (variáveis, `for`, `if`, classes e métodos).
**Como executar os exemplos:** com Java 11 ou superior, basta salvar cada exemplo em um arquivo `.java` e rodar `java NomeDoArquivo.java`. Não precisa de Maven nem de bibliotecas externas (exceto no Módulo 4).

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra uma figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos**, em vez de seguir regras escritas por um programador.

| Programação tradicional | Rede neural |
|-------------------------|-------------|
| Você escreve as regras | Você mostra exemplos |
| Dados + regras → resposta | Dados + respostas → regras (os pesos) |

### 1.2. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo parecido:

```
 entrada 1 ──(peso 1)──┐
 entrada 2 ──(peso 2)──┼──► [ Soma + bias ] ──► [ Função de ativação ] ──► saída
 entrada 3 ──(peso 3)──┘
```

#### As peças do neurônio

| Peça | O que é | Analogia |
|------|---------|----------|
| **Entrada** | Um dado que chega ao neurônio (um número) | Uma informação que você considera |
| **Peso** | Número que indica a importância da entrada. Quanto maior, mais ela influencia; se for negativo, ela "puxa contra" | O quanto você valoriza cada informação |
| **Bias (viés)** | Um ajuste fixo somado ao final. Define o quão fácil é o neurônio "disparar" | Sua predisposição: o quanto você é preguiçoso ou animado por natureza |
| **Função de ativação** | Regra que transforma a soma na saída final | A decisão: "sim" ou "não" |

#### A conta que o neurônio faz

```
saída = ativação( entrada1 × peso1 + entrada2 × peso2 + entrada3 × peso3 + bias )
```

Em três passos:

1. **Multiplica** cada entrada pelo seu peso.
2. **Soma** tudo, incluindo o bias.
3. **Passa** o resultado pela função de ativação, que decide a saída.

#### Exemplo: "Vou à praia hoje?"

Vamos decidir com um neurônio. Cada entrada vale **1** (sim) ou **0** (não):

| Entrada | Peso |
|---------|------|
| Está fazendo sol? | 0,6 |
| Tenho tempo livre? | 0,3 |
| Meus amigos vão? | 0,5 |

**Bias = -1,0** (só saio de casa se o conjunto for bem convincente).
**Função de ativação (degrau):** se a soma for ≥ 0, a saída é 1 (vou); senão, é 0 (fico).

**Caso A:** sol, tempo livre e amigos vão → entradas (1, 1, 1)

```
(1 × 0,6) + (1 × 0,3) + (1 × 0,5) + (-1,0) = 0,4   → ≥ 0 → saída 1 (vou à praia! 🏖️)
```

**Caso B:** sol, tempo livre, mas amigos não vão → entradas (1, 1, 0)

```
(1 × 0,6) + (1 × 0,3) + (0 × 0,5) + (-1,0) = -0,1  → < 0 → saída 0 (fico em casa)
```

> 💡 Repare: nada mudou no neurônio, só uma entrada. **Mudar os pesos e o bias muda o comportamento do neurônio.** É exatamente isso que a rede "aprende" a ajustar durante o treinamento.

#### Em Java, são poucas linhas

```java
public class NeuronioPraia {
    public static void main(String[] args) {
        double[] pesos = {0.6, 0.3, 0.5};
        double bias = -1.0;

        // sol, tempo livre, amigos vão
        double[][] casos = {{1, 1, 1}, {1, 1, 0}, {0, 1, 1}};

        for (double[] entradas : casos) {
            double soma = bias;
            for (int i = 0; i < entradas.length; i++) {
                soma += entradas[i] * pesos[i];
            }
            int saida = soma >= 0 ? 1 : 0; // função de ativação (degrau)
            System.out.printf("sol=%.0f tempo=%.0f amigos=%.0f -> soma=%.2f -> %s%n",
                entradas[0], entradas[1], entradas[2], soma, saida == 1 ? "vou" : "fico");
        }
    }
}
```

**Saída esperada:**

```
sol=1 tempo=1 amigos=1 -> soma=0.40 -> vou
sol=1 tempo=1 amigos=0 -> soma=-0.10 -> fico
sol=0 tempo=1 amigos=1 -> soma=-0.20 -> fico
```

> 🎯 **Pense assim:** é um sistema de "votação ponderada". Cada entrada vota com uma força diferente (o peso), o bias é o quórum mínimo exigido, e a função de ativação anuncia o resultado: "sim" ou "não".

### ✅ Checkpoint do Módulo 1

1. O que acontece com a saída se aumentarmos o peso de "amigos vão" para 1,0?
2. Se o bias for -2,0, o neurônio fica mais ou menos propenso a dizer "vou"? Por quê?
3. Nesse exemplo, quem **escolheu** os pesos? Como uma rede neural escolheria sozinha?

---

## Módulo 2: O Perceptron - O Neurônio Mais Simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural. É um único neurônio que:

- Recebe várias entradas (números, geralmente 0 ou 1 nos exemplos iniciais)
- Calcula a soma ponderada mais o bias
- Decide a saída (0 ou 1) usando a função degrau

**O neurônio da praia do Módulo 1 já é um Perceptron.** A diferença é que, lá, nós escolhemos os pesos e o bias à mão. Agora vamos fazer o Perceptron **descobrir esses valores sozinho**, a partir de exemplos.

### 2.2. Como um Perceptron Aprende?

1. Iniciamos com **pesos aleatórios**
2. Para cada exemplo de treino, calculamos a saída
3. Comparamos com a resposta correta
4. Se errou, **ajustamos os pesos** para melhorar
5. Repetimos muitas vezes

**Fórmulas:**

```
erro = valor_esperado - valor_obtido
peso_novo = peso_antigo + taxa_de_aprendizado × erro × entrada
bias_novo = bias_antigo + taxa_de_aprendizado × erro
```

**Exemplo numérico:** a resposta certa era 1 ("vou"), mas o neurônio respondeu 0 ("fico"). Então `erro = 1 - 0 = 1`. Para uma entrada que valia 1 e uma taxa de aprendizado de 0,1, o peso aumenta em `0,1 × 1 × 1 = 0,1`. O neurônio fica um pouquinho mais inclinado a responder "vou" na próxima vez.

Se a entrada valia 0, o peso **não muda**: aquela entrada não teve culpa do erro.

> 💡 Se o neurônio acertou, `erro = 0` e nada é alterado. Ele só aprende com os erros.

### 2.3. Implementando um Perceptron em Java

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
        double soma = bias;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        return funcaoAtivacao(soma);
    }

    // Treina o perceptron com um exemplo
    public void treinar(double[] entradas, int esperado) {
        int obtido = calcular(entradas);
        int erro = esperado - obtido;

        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        bias += taxaAprendizado * erro;
    }
}
```

> ⚠️ **Importante:** este código é para aprendizado. Em projetos reais, use bibliotecas como Deeplearning4j.

### ✅ Checkpoint do Módulo 2

1. Por que o Perceptron começa com pesos aleatórios?
2. O que acontece com os pesos quando o neurônio acerta tudo?
3. O que muda se a taxa de aprendizado for muito grande (por exemplo, 5,0)?

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
- **Taxa de Aprendizado**: controla o quanto os pesos são ajustados por erro. Muito baixa: aprende devagar. Muito alta: "pula" a solução e não converge
- **Função de Ativação**: transforma a soma em uma saída (veja abaixo)

### 3.3. Funções de Ativação

Até agora usamos a função **degrau**: ou 0, ou 1. Ela é simples, mas "brusca": não existe meio-termo. Redes mais poderosas usam funções suaves, que permitem dizer "tenho 80% de certeza".

| Função | O que faz | Faixa de saída | Onde é usada |
|--------|-----------|----------------|--------------|
| **Degrau** | Corta em 0: ou 0, ou 1 | {0, 1} | Perceptron clássico |
| **Sigmoid** | Curva em "S" suave | entre 0 e 1 | Saída de classificação (probabilidade) |
| **Tanh** | Parecida com a sigmoid, centrada no zero | entre -1 e 1 | Camadas ocultas |
| **ReLU** | Zera negativos, mantém positivos | de 0 a +∞ | Camadas ocultas (padrão atual) |

**Comparação com alguns valores de soma (z):**

| z | Degrau | Sigmoid | Tanh | ReLU |
|---|--------|---------|------|------|
| -2 | 0 | 0,12 | -0,96 | 0 |
| 0 | 1 | 0,50 | 0,00 | 0 |
| 2 | 1 | 0,88 | 0,96 | 2 |

```java
static double degrau(double z)  { return z >= 0 ? 1 : 0; }
static double sigmoid(double z) { return 1.0 / (1.0 + Math.exp(-z)); }
static double tanh(double z)    { return Math.tanh(z); }
static double relu(double z)    { return Math.max(0, z); }
```

> 💡 **Por que a suavidade importa?** O backpropagation precisa calcular "para que lado e quanto" ajustar cada peso. Isso só é possível com funções suaves (que têm inclinação). O degrau é uma "parede": não dá para saber o quanto ajustar.

### 3.4. O Limite do Perceptron: o Problema XOR

Um único Perceptron só resolve problemas em que uma **linha reta** separa as respostas (como AND e OR). O **XOR** ("um ou outro, mas não os dois") não pode ser separado por uma única linha:

| Entrada 1 | Entrada 2 | XOR |
|-----------|-----------|-----|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

A solução é usar **camadas**: vários neurônios combinados. Isso é o **MLP (Perceptron Multicamadas)**.

```
                 camada oculta
 entrada 1 ──┬──► ( neurônio 1 ) ──┐
             ├──► ( neurônio 2 ) ──┤
             │                     ├──► ( neurônio de saída ) ──► XOR
 entrada 2 ──┼──► ( neurônio 3 ) ──┤
             └──► ( neurônio 4 ) ──┘
```

### 3.5. Um MLP Completo em Java (resolve o XOR)

Rede 2 → 4 → 1, com função sigmoid e backpropagation. Código completo, sem bibliotecas:

```java
import java.util.Random;

public class MLPXor {
    static final int ENTRADAS = 2, OCULTOS = 4;
    static final double TAXA = 0.5;

    double[][] w1 = new double[OCULTOS][ENTRADAS]; // pesos entrada -> oculta
    double[] b1 = new double[OCULTOS];             // bias da camada oculta
    double[] w2 = new double[OCULTOS];             // pesos oculta -> saída
    double b2;                                     // bias da saída
    double[] h = new double[OCULTOS];              // saídas da camada oculta

    MLPXor(long seed) {
        Random r = new Random(seed);
        for (int j = 0; j < OCULTOS; j++) {
            for (int i = 0; i < ENTRADAS; i++) w1[j][i] = r.nextDouble() * 2 - 1;
            b1[j] = r.nextDouble() * 2 - 1;
            w2[j] = r.nextDouble() * 2 - 1;
        }
        b2 = r.nextDouble() * 2 - 1;
    }

    static double sigmoid(double z) { return 1.0 / (1.0 + Math.exp(-z)); }

    // Forward propagation
    double prever(double[] x) {
        for (int j = 0; j < OCULTOS; j++) {
            double z = b1[j];
            for (int i = 0; i < ENTRADAS; i++) z += w1[j][i] * x[i];
            h[j] = sigmoid(z);
        }
        double z = b2;
        for (int j = 0; j < OCULTOS; j++) z += w2[j] * h[j];
        return sigmoid(z);
    }

    // Backpropagation (um exemplo)
    void treinar(double[] x, double esperado) {
        double y = prever(x);
        double deltaSaida = (esperado - y) * y * (1 - y);

        for (int j = 0; j < OCULTOS; j++) {
            double deltaOculta = deltaSaida * w2[j] * h[j] * (1 - h[j]);
            w2[j] += TAXA * deltaSaida * h[j];
            for (int i = 0; i < ENTRADAS; i++) w1[j][i] += TAXA * deltaOculta * x[i];
            b1[j] += TAXA * deltaOculta;
        }
        b2 += TAXA * deltaSaida;
    }

    public static void main(String[] args) {
        double[][] x = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] y = {0, 1, 1, 0};

        MLPXor rede = new MLPXor(42);
        for (int epoca = 0; epoca < 10000; epoca++) {
            for (int i = 0; i < x.length; i++) rede.treinar(x[i], y[i]);
        }

        for (double[] e : x) {
            System.out.printf("%.0f XOR %.0f = %.3f%n", e[0], e[1], rede.prever(e));
        }
    }
}
```

**Saída esperada** (valores próximos de 0 ou 1):

```
0 XOR 0 = 0.015
0 XOR 1 = 0.986
1 XOR 0 = 0.984
1 XOR 1 = 0.013
```

> 🎯 **Experimente:** mude `OCULTOS` para 1 e treine de novo. A rede não consegue mais resolver o XOR. Camadas e neurônios extras dão a ela "capacidade" de aprender padrões mais complexos.

### ✅ Checkpoint do Módulo 3

1. Qual a diferença entre uma época e um exemplo de treino?
2. Por que o XOR não pode ser resolvido por um único Perceptron?
3. Por que o backpropagation não funciona bem com a função degrau?

---

## Módulo 4: Ferramentas e Bibliotecas em Java

### 4.1. Por que Java?

O treinamento de modelos costuma ser feito em Python, que tem o ecossistema mais popular para pesquisa e experimentação. Já o Java é uma das linguagens mais usadas no mundo corporativo, com forte presença em sistemas de produção, integração com bancos de dados, serviços web e aplicações que já rodam na JVM.

Por isso, uma estratégia comum é **treinar em Python e servir em Java**, ou usar bibliotecas Java para fazer tudo dentro do mesmo ecossistema.

### 4.2. Bibliotecas Recomendadas

| Biblioteca | Uso | Link |
|------------|-----|------|
| **Deeplearning4j (DL4J)** | A mais completa para deep learning em Java | [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai) |
| **ND4J** | Computação numérica (arrays n-dimensionais), base do DL4J | [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai) |
| **Basic Neural Network** | Biblioteca simples, para aprendizado | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork) |

**Exemplo ilustrativo com Deeplearning4j** (a mesma rede do XOR, em 2 camadas):

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

MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
    .seed(123)
    .updater(new Sgd(0.1))
    .list()
    .layer(new DenseLayer.Builder().nIn(2).nOut(4)
            .activation(Activation.TANH).build())
    .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
            .nIn(4).nOut(1).activation(Activation.SIGMOID).build())
    .build();

MultiLayerNetwork modelo = new MultiLayerNetwork(conf);
modelo.init();

INDArray entradas = Nd4j.create(new double[][]{{0,0},{0,1},{1,0},{1,1}});
INDArray saidas   = Nd4j.create(new double[][]{{0},{1},{1},{0}});

for (int i = 0; i < 5000; i++) modelo.fit(entradas, saidas);

System.out.println(modelo.output(entradas));
```

> ⚠️ Os pacotes e a API podem variar entre versões do DL4J. Consulte a documentação da versão que você usar.

Compare com o Módulo 3: o que fizemos em ~70 linhas aqui cabe em ~20. A biblioteca cuida do backpropagation, da otimização e do desempenho.

### 4.3. Carregando Modelos Treinados em Java

É possível treinar modelos em Python com Keras/TensorFlow e importá-los para Java:

```java
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

// Importando um modelo Keras (.h5) treinado em Python
String caminhoModelo = "meu_modelo.h5";
MultiLayerNetwork modelo =
    KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);
```

### ✅ Checkpoint do Módulo 4

1. Em que situação faz sentido treinar em Python e usar o modelo em Java?
2. O que a biblioteca faz por você que, no Módulo 3, tivemos que programar à mão?

---

## Módulo 5: Exercícios Práticos

### Desafio 1: Classificador AND

Faça o Perceptron aprender a operação lógica AND:

| Entrada 1 | Entrada 2 | Saída |
|-----------|-----------|-------|
| 0 | 0 | 0 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

**Código para testar** (usa a classe `Perceptron` do Módulo 2.3):

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
            System.out.println((int) entrada[0] + " AND " + (int) entrada[1]
                + " = " + p.calcular(entrada));
        }
    }
}
```

**Saída esperada:**

```
0 AND 0 = 0
0 AND 1 = 0
1 AND 0 = 0
1 AND 1 = 1
```

### Desafio 2: Treine o neurônio da praia

Em vez de escolher os pesos à mão, faça o Perceptron descobrir sozinho a regra do Módulo 1. Use as 8 combinações de (sol, tempo livre, amigos vão) e as respostas que o neurônio da praia daria:

| Sol | Tempo | Amigos | Vou? |
|-----|-------|--------|------|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 0 |
| 0 | 1 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 0 |
| 1 | 1 | 1 | 1 |

Dica: crie `new Perceptron(3)`, treine por 100 épocas e teste as 8 combinações. Os pesos finais podem ser diferentes dos originais (0,6 / 0,3 / 0,5 / -1,0), mas o **comportamento** será o mesmo.

### Desafio 3 (bônus): XOR

1. Rode o `MLPXor` do Módulo 3.5 e confira o resultado.
2. Troque `OCULTOS` para 1 e observe o que acontece.
3. Tente treinar o `Perceptron` do Módulo 2 no XOR. Ele consegue? Por quê?

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* - Fábio M. Soares, Alan M. F. Souza
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) - exemplos de Perceptron e MLP em Java
3. **Documentação do Deeplearning4j**: [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai)

---

## 📝 Resumo do Minicurso

| Conceito | Em uma frase |
|----------|--------------|
| Neurônio | Soma ponderada das entradas + bias, passada por uma função de ativação |
| Perceptron | Neurônio com função degrau que aprende ajustando pesos a partir dos erros |
| Função de ativação | Define a saída do neurônio (degrau, sigmoid, tanh, ReLU) |
| MLP | Várias camadas de neurônios, capazes de resolver problemas como o XOR |
| Backpropagation | O erro volta pela rede, ajustando os pesos |
| DL4J | Biblioteca Java que automatiza tudo isso |
