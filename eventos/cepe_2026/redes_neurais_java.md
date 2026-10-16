# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.

**Biblioteca utilizada:** [Deeplearning4j (DL4J)](https://deeplearning4j.konduit.ai/) + ND4J.

**Pré-requisito técnico:** saber compilar e executar um programa Java simples.

Neste minicurso você primeiro **constrói uma rede neural do zero** (Java puro) para entender o que acontece por baixo dos panos. Depois, refaz os mesmos problemas com o **Deeplearning4j**, a biblioteca que se usa na prática.

---

## Índice

1. [Preparando o ambiente (sem Maven)](#módulo-0-preparando-o-ambiente-sem-maven)
2. [O que é uma Rede Neural?](#módulo-1-o-que-é-uma-rede-neural)
3. [O Perceptron](#módulo-2-o-perceptron---o-neurônio-mais-simples)
4. [Aprendizado e Treinamento (MLP do zero)](#módulo-3-aprendizado-e-treinamento)
5. [Deeplearning4j na prática](#módulo-4-deeplearning4j-na-prática)
6. [Exercícios](#módulo-5-exercícios)
7. [Recursos para aprofundamento](#recursos-para-aprofundamento)

---

## Módulo 0: Preparando o ambiente (sem Maven)

### 0.1. O que você precisa

| Item | Versão | Para quê |
| ---- | ------ | -------- |
| JDK  | 17 (ou 11) | compilar e executar os exemplos |
| JBang | qualquer recente | baixar as bibliotecas do DL4J automaticamente |

Os exemplos dos **Módulos 1, 2 e 3** usam apenas Java puro e rodam só com o JDK:

```bash
java NomeDoArquivo.java
```

Os exemplos do **Módulo 4** usam o DL4J, que depende de dezenas de arquivos `.jar` (o DL4J, o ND4J, bibliotecas nativas, etc.). Baixar tudo isso manualmente é inviável, então usaremos o **JBang**: uma ferramenta que executa um único arquivo `.java` e baixa sozinha as dependências declaradas no topo do arquivo. Não é necessário criar projeto, `pom.xml` ou `build.gradle`.

### 0.2. Instalando o JBang

**Linux / macOS:**

```bash
curl -Ls https://sh.jbang.dev | bash -s - app setup
```

**Windows (PowerShell):**

```powershell
iex "& { $(iwr https://ps.jbang.dev) } app setup"
```

Feche e abra o terminal e teste:

```bash
jbang --version
```

### 0.3. Como os exemplos do DL4J são executados

Cada exemplo do Módulo 4 é um arquivo `.java` completo. As linhas `//DEPS` no topo dizem ao JBang quais bibliotecas baixar:

```java
//DEPS org.deeplearning4j:deeplearning4j-core:1.0.0-M2.1
//DEPS org.nd4j:nd4j-native-platform:1.0.0-M2.1
//DEPS org.slf4j:slf4j-simple:1.7.36
```

Para executar:

```bash
jbang NomeDoArquivo.java
```

> ⚠️ **Na primeira execução** o JBang baixa as bibliotecas, incluindo as nativas do ND4J, que são grandes (centenas de MB). **Faça isso antes do evento, com internet boa.** Nas execuções seguintes tudo fica em cache.

### ✅ Checkpoint do Módulo 0

- [ ] `java -version` mostra Java 11 ou 17.
- [ ] `jbang --version` funciona.

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma analogia simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra a figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos**.

### 1.2. O neurônio artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo parecido:

```
 entrada 1 ──(peso 1)──┐
 entrada 2 ──(peso 2)──┼──► [ Soma + bias ] ──► [ Função de ativação ] ──► saída
 entrada 3 ──(peso 3)──┘
```

Cada entrada tem um **peso**, um número que indica sua importância. O neurônio:

1. Multiplica cada entrada pelo seu peso.
2. Soma tudo, incluindo um **bias** (viés).
3. Passa o resultado por uma **função de ativação**, que decide a saída.

> 🎯 **Pense assim:** é uma votação ponderada. Cada entrada vota com um peso diferente e, no final, decidimos se a resposta é "sim" ou "não".

### 1.3. Primeiro código: um neurônio com pesos fixos

Arquivo `NeuronioSimples.java`:

```java
public class NeuronioSimples {
    public static void main(String[] args) {
        double[] entradas = {1.0, 0.0, 1.0};
        double[] pesos    = {0.5, -0.6, 0.8};
        double bias = -0.5;

        double soma = bias;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        int saida = soma >= 0 ? 1 : 0; // função degrau

        System.out.println("Soma ponderada: " + soma);
        System.out.println("Saída do neurônio: " + saida);
    }
}
```

Execute: `java NeuronioSimples.java`

Saída esperada:

```
Soma ponderada: 0.8
Saída do neurônio: 1
```

Conta feita à mão: `-0,5 + (1 × 0,5) + (0 × -0,6) + (1 × 0,8) = 0,8`. Como `0,8 ≥ 0`, a saída é 1.

### ✅ Checkpoint do Módulo 1

1. O que acontece com a saída se o bias for `-2.0`? (Teste e confira.)
2. Qual entrada tem maior influência na soma? Por quê?

---

## Módulo 2: O Perceptron - O neurônio mais simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural: **um único neurônio que aprende seus próprios pesos**. Ele:

- recebe várias entradas (aqui, 0 ou 1);
- calcula a soma ponderada mais o bias;
- decide a saída (0 ou 1).

### 2.2. Como um Perceptron aprende?

1. Começamos com **pesos aleatórios**.
2. Para cada exemplo de treino, calculamos a saída.
3. Comparamos com a resposta correta.
4. Se errou, **ajustamos os pesos** na direção certa.
5. Repetimos muitas vezes.

```
erro = valor_esperado - valor_obtido
peso_novo = peso_antigo + taxa_aprendizado × erro × entrada
```

### 2.3. Perceptron completo em Java

Arquivo `PerceptronPortas.java`. Ele treina o mesmo Perceptron para três portas lógicas: **AND**, **OR** e **XOR**.

```java
import java.util.Random;

public class PerceptronPortas {

    public static void main(String[] args) {
        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        testar("AND", entradas, new int[]{0, 0, 0, 1});
        testar("OR",  entradas, new int[]{0, 1, 1, 1});
        testar("XOR", entradas, new int[]{0, 1, 1, 0});
    }

    static void testar(String nome, double[][] entradas, int[] esperados) {
        Perceptron p = new Perceptron(2);

        // Treina por 100 épocas
        for (int epoca = 0; epoca < 100; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        System.out.println("=== " + nome + " ===");
        int acertos = 0;
        for (int i = 0; i < entradas.length; i++) {
            int saida = p.calcular(entradas[i]);
            if (saida == esperados[i]) acertos++;
            System.out.println((int) entradas[i][0] + " " + nome + " " + (int) entradas[i][1]
                    + " = " + saida + "  (esperado: " + esperados[i] + ")");
        }
        System.out.println("Acertos: " + acertos + "/4\n");
    }

    static class Perceptron {
        private final double[] pesos;
        private double bias;
        private final double taxaAprendizado = 0.1;

        Perceptron(int numeroEntradas) {
            Random random = new Random(42);
            pesos = new double[numeroEntradas];
            for (int i = 0; i < numeroEntradas; i++) {
                pesos[i] = random.nextDouble() * 2 - 1; // entre -1 e 1
            }
            bias = random.nextDouble() * 2 - 1;
        }

        // Função degrau: se soma >= 0 retorna 1, senão 0
        int calcular(double[] entradas) {
            double soma = bias;
            for (int i = 0; i < entradas.length; i++) {
                soma += entradas[i] * pesos[i];
            }
            return soma >= 0 ? 1 : 0;
        }

        // Treina com um exemplo, ajustando pesos e bias
        void treinar(double[] entradas, int esperado) {
            double erro = esperado - calcular(entradas);
            for (int i = 0; i < entradas.length; i++) {
                pesos[i] += taxaAprendizado * erro * entradas[i];
            }
            bias += taxaAprendizado * erro;
        }
    }
}
```

Execute: `java PerceptronPortas.java`

Saída esperada (resumida):

```
=== AND ===
... Acertos: 4/4

=== OR ===
... Acertos: 4/4

=== XOR ===
0 XOR 0 = 1  (esperado: 0)
0 XOR 1 = 1  (esperado: 1)
1 XOR 0 = 0  (esperado: 1)
1 XOR 1 = 1  (esperado: 0)
Acertos: 1/4
```

### 2.4. O limite do Perceptron

O Perceptron aprende AND e OR sem problemas, mas **nunca acerta o XOR**, não importa quantas épocas você use. Um único neurônio só consegue separar os dados com **uma linha reta**, e o XOR não pode ser separado assim:

```
  entrada 2
     1 │  1 (XOR=1)     0 (XOR=0)
       │
     0 │  0 (XOR=0)     1 (XOR=1)
       └──────────────────────────  entrada 1
          0                1
```

A solução é usar **mais de um neurônio, organizados em camadas**. É o que veremos a seguir.

### ✅ Checkpoint do Módulo 2

1. Aumente as épocas de 100 para 10.000. O XOR passa a acertar?
2. Mude a taxa de aprendizado para `1.0` e para `0.001`. O que muda no AND?

---

## Módulo 3: Aprendizado e Treinamento

### 3.1. Como a rede "aprende"?

O aprendizado é um processo de **tentativa e erro** em quatro passos:

1. **Forward propagation:** os dados passam pela rede e geram uma saída.
2. **Cálculo do erro:** comparamos a saída com o valor esperado.
3. **Backpropagation:** o erro "volta" pela rede, ajustando os pesos de cada camada.
4. **Repetição:** fazemos isso milhares de vezes.

### 3.2. Conceitos importantes

- **Época (epoch):** uma rodada completa de treino com todos os dados.
- **Função de ativação:** transforma a soma em uma saída (Sigmoid, Tanh, ReLU...).
- **Taxa de aprendizado:** controla o quanto os pesos mudam a cada ajuste.
- **Camada oculta:** camada entre a entrada e a saída que permite aprender padrões não lineares.

### 3.3. Arquitetura de um MLP (Perceptron Multicamadas)

Vamos resolver o XOR com uma rede **2 → 4 → 1**: 2 entradas, 1 camada oculta com 4 neurônios e 1 saída.

```
 Entrada        Camada oculta         Saída
 (2 valores)    (4 neurônios)      (1 neurônio)

   x1 ───────┬──► ( h1 ) ──┐
             ├──► ( h2 ) ──┤
   x2 ───────┼──► ( h3 ) ──┼──────► ( y )
             └──► ( h4 ) ──┘
```

### 3.4. MLP do zero em Java

Arquivo `MLPXor.java`. Aqui o forward e o backpropagation são escritos à mão, usando a função **sigmoide**:

```
sigmoide(x) = 1 / (1 + e^(-x))      (transforma qualquer número em algo entre 0 e 1)
```

```java
import java.util.Random;

public class MLPXor {

    static double sigmoide(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    public static void main(String[] args) {
        double[][] X = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] Y   = {0, 1, 1, 0};

        int nEntradas = 2, nOculta = 4;
        double taxa = 0.5;
        Random random = new Random(42);

        // Pesos e biases iniciais aleatórios
        double[][] w1 = new double[nEntradas][nOculta];
        double[] b1 = new double[nOculta];
        double[] w2 = new double[nOculta];
        double b2 = random.nextDouble() * 2 - 1;

        for (int j = 0; j < nOculta; j++) {
            for (int i = 0; i < nEntradas; i++) w1[i][j] = random.nextDouble() * 2 - 1;
            b1[j] = random.nextDouble() * 2 - 1;
            w2[j] = random.nextDouble() * 2 - 1;
        }

        for (int epoca = 1; epoca <= 10000; epoca++) {
            double erroTotal = 0;

            for (int k = 0; k < X.length; k++) {
                // 1) Forward propagation
                double[] h = new double[nOculta];
                for (int j = 0; j < nOculta; j++) {
                    double soma = b1[j];
                    for (int i = 0; i < nEntradas; i++) soma += X[k][i] * w1[i][j];
                    h[j] = sigmoide(soma);
                }
                double s = b2;
                for (int j = 0; j < nOculta; j++) s += h[j] * w2[j];
                double y = sigmoide(s);

                // 2) Erro
                double erro = Y[k] - y;
                erroTotal += erro * erro;

                // 3) Backpropagation
                double deltaSaida = erro * y * (1 - y);
                for (int j = 0; j < nOculta; j++) {
                    double deltaOculta = deltaSaida * w2[j] * h[j] * (1 - h[j]);
                    w2[j] += taxa * deltaSaida * h[j];
                    for (int i = 0; i < nEntradas; i++) w1[i][j] += taxa * deltaOculta * X[k][i];
                    b1[j] += taxa * deltaOculta;
                }
                b2 += taxa * deltaSaida;
            }

            if (epoca % 2000 == 0 || epoca == 1) {
                System.out.printf("Época %5d | erro total = %.6f%n", epoca, erroTotal);
            }
        }

        // Resultado final
        System.out.println("\nResultado final:");
        for (int k = 0; k < X.length; k++) {
            double[] h = new double[nOculta];
            for (int j = 0; j < nOculta; j++) {
                double soma = b1[j];
                for (int i = 0; i < nEntradas; i++) soma += X[k][i] * w1[i][j];
                h[j] = sigmoide(soma);
            }
            double s = b2;
            for (int j = 0; j < nOculta; j++) s += h[j] * w2[j];
            double y = sigmoide(s);
            System.out.printf("%d XOR %d = %.4f -> %d%n",
                    (int) X[k][0], (int) X[k][1], y, y >= 0.5 ? 1 : 0);
        }
    }
}
```

Execute: `java MLPXor.java`

Saída esperada (os valores exatos podem variar levemente conforme a versão do Java):

```
Época     1 | erro total = 1.128542
Época  2000 | erro total = 0.015329
Época  4000 | erro total = 0.004874
Época  6000 | erro total = 0.002753
Época  8000 | erro total = 0.001874
Época 10000 | erro total = 0.001402

Resultado final:
0 XOR 0 = 0.0155 -> 0
0 XOR 1 = 0.9759 -> 1
1 XOR 0 = 0.9865 -> 1
1 XOR 1 = 0.0200 -> 0
```

Repare como o **erro total diminui** a cada época: é a rede aprendendo. Com uma camada oculta, ela resolve o problema que o Perceptron simples não resolvia.

### ✅ Checkpoint do Módulo 3

1. Mude `nOculta` para 2. A rede ainda aprende o XOR?
2. Mude a semente `new Random(42)` para outro número. O erro final muda?
3. Diminua a taxa de aprendizado para `0.05`. O que acontece com a velocidade do aprendizado?

---

## Módulo 4: Deeplearning4j na prática

No Módulo 3 escrevemos cerca de 70 linhas para treinar uma rede minúscula. O **Deeplearning4j (DL4J)** faz o mesmo declarando a **arquitetura** da rede e chamando `fit()`. O ND4J, biblioteca numérica usada pelo DL4J, fornece o `INDArray` (equivalente a um array/matriz).

> Lembre-se: os arquivos deste módulo são executados com `jbang NomeDoArquivo.java` (veja o [Módulo 0](#módulo-0-preparando-o-ambiente-sem-maven)).

### 4.1. Por que Java?

Java é uma das linguagens mais usadas no mundo corporativo. Para colocar um modelo em produção em sistemas já existentes (bancos, ERPs, serviços web), usar a JVM evita ter que integrar outra stack só para a inferência.

### 4.2. Bibliotecas do ecossistema

| Biblioteca | Uso | Link |
| ---------- | --- | ---- |
| **Deeplearning4j** | Construção e treino de redes neurais em Java | [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai/) |
| **ND4J** | Computação numérica (arrays n-dimensionais) usada pelo DL4J | [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai/) |
| **deeplearning4j-modelimport** | Importa modelos treinados em Keras | [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai/) |

> ℹ️ A última versão estável do DL4J (`1.0.0-M2.1`) é de 2022 e o projeto tem pouca atividade atualmente. Para aprender os conceitos e para muitos casos de uso, ela atende bem. Em projetos novos, avalie também o **DJL (Deep Java Library)**.

### 4.3. Exemplo 1: porta AND com um único neurônio

É o mesmo problema do Módulo 2, agora com o DL4J. A rede tem só uma camada de saída: 2 entradas e 1 neurônio com ativação sigmoide.

Arquivo `AndDL4J.java`:

```java
///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.deeplearning4j:deeplearning4j-core:1.0.0-M2.1
//DEPS org.nd4j:nd4j-native-platform:1.0.0-M2.1
//DEPS org.slf4j:slf4j-simple:1.7.36

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class AndDL4J {
    public static void main(String[] args) {

        // 1) Dados de treino: tabela verdade do AND
        INDArray entradas = Nd4j.create(new double[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
        INDArray saidas   = Nd4j.create(new double[][]{{0}, {0}, {0}, {1}});
        DataSet dados = new DataSet(entradas, saidas);

        // 2) Arquitetura: 2 entradas -> 1 neurônio de saída (sigmoide)
        MultiLayerConfiguration configuracao = new NeuralNetConfiguration.Builder()
                .seed(123)                 // semente: resultados reproduzíveis
                .updater(new Adam(0.1))    // algoritmo que ajusta os pesos
                .list()
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
                        .nIn(2)
                        .nOut(1)
                        .activation(Activation.SIGMOID)
                        .build())
                .build();

        // 3) Cria e inicializa a rede
        MultiLayerNetwork rede = new MultiLayerNetwork(configuracao);
        rede.init();
        rede.setListeners(new ScoreIterationListener(100)); // mostra o erro a cada 100 iterações

        // 4) Treina por 500 épocas
        for (int epoca = 0; epoca < 500; epoca++) {
            rede.fit(dados);
        }

        // 5) Testa
        INDArray previsao = rede.output(entradas);
        System.out.println("\nResultado:");
        for (int i = 0; i < 4; i++) {
            double p = previsao.getDouble(i, 0);
            System.out.printf("%d AND %d = %.3f -> %d%n",
                    (int) entradas.getDouble(i, 0),
                    (int) entradas.getDouble(i, 1),
                    p, p >= 0.5 ? 1 : 0);
        }
    }
}
```

Execute: `jbang AndDL4J.java`

Saída esperada (os números exatos variam, mas a classificação final deve ser 0, 0, 0, 1):

```
Resultado:
0 AND 0 = 0.0xx -> 0
0 AND 1 = 0.0xx -> 0
1 AND 0 = 0.0xx -> 0
1 AND 1 = 0.9xx -> 1
```

**Leia o código como uma receita:**

| Passo | O que faz |
| ----- | --------- |
| `Nd4j.create(...)` | transforma arrays Java em `INDArray` |
| `NeuralNetConfiguration.Builder()` | descreve como a rede é montada |
| `OutputLayer` com `XENT` | camada final; `XENT` é a função de erro para classificação 0/1 |
| `rede.init()` | cria os pesos iniciais |
| `rede.fit(dados)` | uma passada de treino |
| `rede.output(entradas)` | previsão da rede |

### 4.4. Exemplo 2: XOR com camada oculta (e salvando o modelo)

Aqui está a mesma rede 2 → 4 → 1 do Módulo 3, agora em poucas linhas. Ao final, o modelo treinado é **salvo em disco e carregado de volta**, o que é o primeiro passo para usá-lo em uma aplicação real.

Arquivo `XorDL4J.java`:

```java
///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.deeplearning4j:deeplearning4j-core:1.0.0-M2.1
//DEPS org.nd4j:nd4j-native-platform:1.0.0-M2.1
//DEPS org.slf4j:slf4j-simple:1.7.36

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;

public class XorDL4J {
    public static void main(String[] args) throws Exception {

        // 1) Dados de treino: tabela verdade do XOR
        INDArray entradas = Nd4j.create(new double[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
        INDArray saidas   = Nd4j.create(new double[][]{{0}, {1}, {1}, {0}});
        DataSet dados = new DataSet(entradas, saidas);

        // 2) Arquitetura 2 -> 4 -> 1
        MultiLayerConfiguration configuracao = new NeuralNetConfiguration.Builder()
                .seed(123)
                .updater(new Adam(0.1))
                .list()
                .layer(new DenseLayer.Builder()          // camada oculta
                        .nIn(2)
                        .nOut(4)
                        .activation(Activation.TANH)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
                        .nIn(4)
                        .nOut(1)
                        .activation(Activation.SIGMOID)
                        .build())
                .build();

        MultiLayerNetwork rede = new MultiLayerNetwork(configuracao);
        rede.init();
        rede.setListeners(new ScoreIterationListener(200));

        // 3) Treino
        for (int epoca = 0; epoca < 2000; epoca++) {
            rede.fit(dados);
        }

        // 4) Teste
        System.out.println("\nResultado após o treino:");
        imprimir(rede, entradas);

        // 5) Salva o modelo treinado em um arquivo
        File arquivo = new File("xor_modelo.zip");
        ModelSerializer.writeModel(rede, arquivo, true);
        System.out.println("\nModelo salvo em: " + arquivo.getAbsolutePath());

        // 6) Carrega o modelo de volta (simula outra aplicação usando o modelo pronto)
        MultiLayerNetwork carregada = ModelSerializer.restoreMultiLayerNetwork(arquivo);
        System.out.println("\nResultado usando o modelo carregado do arquivo:");
        imprimir(carregada, entradas);
    }

    static void imprimir(MultiLayerNetwork rede, INDArray entradas) {
        INDArray previsao = rede.output(entradas);
        for (int i = 0; i < 4; i++) {
            double p = previsao.getDouble(i, 0);
            System.out.printf("%d XOR %d = %.3f -> %d%n",
                    (int) entradas.getDouble(i, 0),
                    (int) entradas.getDouble(i, 1),
                    p, p >= 0.5 ? 1 : 0);
        }
    }
}
```

Execute: `jbang XorDL4J.java`

Saída esperada (valores aproximados; a classificação final deve ser 0, 1, 1, 0 nas duas listas):

```
Resultado após o treino:
0 XOR 0 = 0.00x -> 0
0 XOR 1 = 0.99x -> 1
1 XOR 0 = 0.99x -> 1
1 XOR 1 = 0.00x -> 0
```

> 💡 Se a rede ficar "presa" e não acertar o XOR, mude `.seed(123)` para outro número e rode de novo. Pesos iniciais diferentes levam a caminhos de treino diferentes.

### 4.5. Exemplo 3: um problema mais realista (aprovado ou reprovado)

Agora um classificador com dados mais próximos do dia a dia: dada a **nota** (0 a 10) e a **frequência** (0 a 100%) de um aluno, prever se ele foi **aprovado (1)** ou **reprovado (0)**. A regra usada para gerar os dados de treino é: nota ≥ 6 **e** frequência ≥ 75%.

Um ponto novo e muito importante: **normalização**. Redes neurais aprendem melhor quando as entradas estão em escalas parecidas. Por isso dividimos a nota por 10 e a frequência por 100, deixando tudo entre 0 e 1.

Arquivo `AprovadoDL4J.java`:

```java
///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.deeplearning4j:deeplearning4j-core:1.0.0-M2.1
//DEPS org.nd4j:nd4j-native-platform:1.0.0-M2.1
//DEPS org.slf4j:slf4j-simple:1.7.36

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class AprovadoDL4J {

    // Normaliza: nota (0-10) e frequência (0-100) viram valores entre 0 e 1
    static double[] normalizar(double nota, double frequencia) {
        return new double[]{nota / 10.0, frequencia / 100.0};
    }

    public static void main(String[] args) {

        // {nota, frequência, resultado (1 = aprovado, 0 = reprovado)}
        double[][] alunos = {
                {8.0, 95, 1}, {7.0, 80, 1}, {6.0, 76, 1}, {9.0, 100, 1},
                {6.5, 90, 1}, {10.0, 75, 1}, {6.0, 100, 1}, {7.5, 88, 1},
                {5.0, 90, 0}, {4.0, 95, 0}, {3.0, 60, 0}, {8.0, 60, 0},
                {9.0, 70, 0}, {5.5, 80, 0}, {2.0, 50, 0}, {7.0, 74, 0},
                {5.0, 100, 0}, {8.5, 65, 0}
        };

        // Monta as matrizes de treino já normalizadas
        double[][] x = new double[alunos.length][];
        double[][] y = new double[alunos.length][1];
        for (int i = 0; i < alunos.length; i++) {
            x[i] = normalizar(alunos[i][0], alunos[i][1]);
            y[i][0] = alunos[i][2];
        }
        DataSet dados = new DataSet(Nd4j.create(x), Nd4j.create(y));

        // Arquitetura 2 -> 8 -> 1
        MultiLayerConfiguration configuracao = new NeuralNetConfiguration.Builder()
                .seed(42)
                .updater(new Adam(0.05))
                .list()
                .layer(new DenseLayer.Builder().nIn(2).nOut(8)
                        .activation(Activation.TANH).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.XENT)
                        .nIn(8).nOut(1)
                        .activation(Activation.SIGMOID).build())
                .build();

        MultiLayerNetwork rede = new MultiLayerNetwork(configuracao);
        rede.init();

        for (int epoca = 0; epoca < 3000; epoca++) {
            rede.fit(dados);
        }

        // Testa com alunos que a rede NUNCA viu no treino
        double[][] novos = {
                {7.5, 85},   // esperado: aprovado
                {4.0, 100},  // esperado: reprovado (nota baixa)
                {9.0, 60},   // esperado: reprovado (frequência baixa)
                {6.0, 75}    // esperado: aprovado (no limite)
        };

        System.out.println("Previsões para alunos novos:");
        for (double[] aluno : novos) {
            INDArray entrada = Nd4j.create(new double[][]{normalizar(aluno[0], aluno[1])});
            double prob = rede.output(entrada).getDouble(0, 0);
            System.out.printf("Nota %.1f, frequência %.0f%% -> probabilidade de aprovação: %.1f%% -> %s%n",
                    aluno[0], aluno[1], prob * 100, prob >= 0.5 ? "APROVADO" : "REPROVADO");
        }
    }
}
```

Execute: `jbang AprovadoDL4J.java`

Saída esperada (as porcentagens variam; observe os veredictos):

```
Previsões para alunos novos:
Nota 7.5, frequência 85% -> probabilidade de aprovação: ...% -> APROVADO
Nota 4.0, frequência 100% -> probabilidade de aprovação: ...% -> REPROVADO
Nota 9.0, frequência 60% -> probabilidade de aprovação: ...% -> REPROVADO
Nota 6.0, frequência 75% -> probabilidade de aprovação: ...% -> APROVADO
```

> ⚠️ **Atenção ao último caso (6.0 e 75%)**: ele está exatamente na fronteira da regra, e como a rede aprende de exemplos (e não da regra), ela pode errar casos de fronteira. Isso é normal e é uma boa discussão em sala: **mais dados de treino perto da fronteira melhoram o resultado**.

### 4.6. Carregando modelos treinados em Python (Keras) no Java

Um fluxo muito comum: **treinar em Python** (onde há mais ferramentas) e **usar o modelo em Java** (onde está a aplicação). O DL4J importa modelos Keras.

**Passo 1: treine e salve o modelo em Python** (`treinar_xor.py`):

```python
import numpy as np
from tensorflow import keras

X = np.array([[0, 0], [0, 1], [1, 0], [1, 1]], dtype="float32")
Y = np.array([[0], [1], [1], [0]], dtype="float32")

model = keras.Sequential([
    keras.layers.Dense(4, activation="tanh", input_shape=(2,)),
    keras.layers.Dense(1, activation="sigmoid"),
])
model.compile(optimizer=keras.optimizers.Adam(0.1), loss="binary_crossentropy")
model.fit(X, Y, epochs=500, verbose=0)

model.save("xor.h5")   # formato HDF5
print(model.predict(X))
```

> ⚠️ O DL4J lê arquivos `.h5` gerados por versões do Keras da série 2.x (TensorFlow 2.x). Modelos salvos em formatos mais novos (como `.keras` do Keras 3) **não são suportados**. Se der erro de importação, gere o modelo com `tensorflow==2.12`.

**Passo 2: use o modelo em Java.** Arquivo `ImportaKeras.java`, na mesma pasta que o `xor.h5`:

```java
///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.deeplearning4j:deeplearning4j-core:1.0.0-M2.1
//DEPS org.deeplearning4j:deeplearning4j-modelimport:1.0.0-M2.1
//DEPS org.nd4j:nd4j-native-platform:1.0.0-M2.1
//DEPS org.slf4j:slf4j-simple:1.7.36

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class ImportaKeras {
    public static void main(String[] args) throws Exception {
        String caminho = args.length > 0 ? args[0] : "xor.h5";

        // Importa a arquitetura E os pesos treinados no Python
        MultiLayerNetwork modelo =
                KerasModelImport.importKerasSequentialModelAndWeights(caminho);

        INDArray entradas = Nd4j.create(new double[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
        INDArray previsao = modelo.output(entradas);

        for (int i = 0; i < 4; i++) {
            double p = previsao.getDouble(i, 0);
            System.out.printf("%d XOR %d = %.3f -> %d%n",
                    (int) entradas.getDouble(i, 0),
                    (int) entradas.getDouble(i, 1),
                    p, p >= 0.5 ? 1 : 0);
        }
    }
}
```

Execute: `jbang ImportaKeras.java` (ou `jbang ImportaKeras.java caminho/do/modelo.h5`).

### ✅ Checkpoint do Módulo 4

1. No `XorDL4J`, troque `Activation.TANH` por `Activation.RELU`. O que muda?
2. No `AprovadoDL4J`, **remova a normalização** (passe nota e frequência diretamente). A rede ainda aprende? Compare.
3. Qual a vantagem de salvar o modelo com `ModelSerializer`?

---

## Módulo 5: Exercícios

### Exercício 1: porta OR com DL4J

Copie o `AndDL4J.java`, renomeie para `OrDL4J.java` e altere apenas as saídas esperadas:

| Entrada 1 | Entrada 2 | Saída |
| --------- | --------- | ----- |
| 0         | 0         | 0     |
| 0         | 1         | 1     |
| 1         | 0         | 1     |
| 1         | 1         | 1     |

### Exercício 2: a dimensão da camada oculta

No `XorDL4J.java`, teste `nOut` da camada oculta com 1, 2, 4 e 16. Anote quantas vezes a rede acerta o XOR com cada tamanho. Qual o menor que funciona de forma confiável?

### Exercício 3: seu próprio classificador

Inspirado no `AprovadoDL4J.java`, crie um classificador com 2 ou 3 entradas e uma regra sua. Sugestões:

- **Levar guarda-chuva?** entradas: probabilidade de chuva e se o céu está nublado;
- **Comprar o lanche?** entradas: fome (0-10) e dinheiro disponível;
- **Aprovar empréstimo?** entradas: renda e valor pedido.

Gere uns 15 a 20 exemplos de treino e teste com casos novos.

### Exercício 4 (desafio): do Python para o Java

Treine em Python um modelo Keras para o problema do Exercício 3, salve como `.h5` e carregue em Java com o `ImportaKeras.java`.

---

## Recursos para aprofundamento

1. **Documentação do DL4J:** [deeplearning4j.konduit.ai](https://deeplearning4j.konduit.ai/)
2. **JBang:** [jbang.dev](https://www.jbang.dev/)
3. **Livro:** *Neural Network Programming with Java*, de Fabio Soares e Alan M. F. Souza.
4. **Projeto de exemplo:** [neuraljava no GitHub](https://github.com/cleuton/neuraljava), com exemplos de Perceptron e MLP em Java.
5. **Alternativa moderna:** [DJL (Deep Java Library)](https://djl.ai/).

---

## Resumo do minicurso

| Módulo | Aprendemos | Arquivo |
| ------ | ---------- | ------- |
| 0 | Preparar JDK e JBang | |
| 1 | O neurônio artificial | `NeuronioSimples.java` |
| 2 | Perceptron, AND/OR e o limite do XOR | `PerceptronPortas.java` |
| 3 | MLP, forward e backpropagation do zero | `MLPXor.java` |
| 4 | Redes com DL4J, normalização, salvar modelo e importar do Keras | `AndDL4J.java`, `XorDL4J.java`, `AprovadoDL4J.java`, `ImportaKeras.java` |
| 5 | Exercícios | |
