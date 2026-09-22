# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra a foto de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança passa a identificar os animais sozinha.

Uma rede neural artificial aprende de forma parecida: **ela generaliza a partir de exemplos**.

### 1.2. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo semelhante:

```
Entradas → Multiplica por Pesos → Soma → Função de Ativação → Saída
```

Cada entrada tem um **peso**, um número que indica sua importância. O neurônio:

1. Multiplica cada entrada pelo seu peso.
2. Soma tudo, incluindo um **bias** (ou viés).
3. Passa o resultado por uma **função de ativação**, que decide a saída.

> 🎯 **Pense assim:** é como uma "votação ponderada", em que cada entrada vota com um peso diferente e, no final, decidimos se o resultado é "sim" ou "não".

---

## Módulo 2: O Perceptron — O Neurônio Mais Simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural: um único neurônio que

- recebe várias entradas (0 ou 1);
- calcula uma combinação ponderada dessas entradas;
- decide a saída (0 ou 1).

**Exemplo prático:** classificar se algo é um "ser vivo" ou não a partir de 3 bits, cada um representando uma característica (por exemplo, "respira", "se move", "se reproduz").

### 2.2. Como um Perceptron Aprende?

1. Começamos com **pesos aleatórios**.
2. Para cada exemplo de treino, calculamos a saída.
3. Comparamos o resultado com a resposta correta.
4. Se houve erro, **ajustamos os pesos** para melhorar.
5. Repetimos o processo muitas vezes.

**Fórmula do erro:**

```
erro = valor_esperado - valor_obtido
```

### 2.3. Implementando um Perceptron em Java (Simplificado)

```java
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

> ⚠️ **Importante:** este código tem fins didáticos. Em projetos reais, prefira bibliotecas consolidadas, como o Deeplearning4j.

---

## Módulo 3: Aprendizado e Treinamento

### 3.1. Como a Rede "Aprende"?

O aprendizado em redes neurais é, essencialmente, um processo de **tentativa e erro**:

1. **Forward Propagation**: os dados passam pela rede, gerando uma saída.
2. **Cálculo do erro**: comparamos a saída obtida com a esperada.
3. **Backpropagation**: o erro é propagado de volta pela rede, ajustando os pesos.
4. **Repetição**: o ciclo se repete milhares de vezes, até o erro diminuir o suficiente.

### 3.2. Conceitos Importantes

- **Época (Epoch)**: uma rodada completa de treino, passando por todos os dados.
- **Função de Ativação**: transforma a soma ponderada em uma saída (ex.: Sigmoid, Tanh, ReLU).
- **Taxa de Aprendizado**: controla o quanto os pesos são ajustados a cada erro.

### 3.3. Um MLP (Perceptron Multicamadas) Simplificado

Diferente do Perceptron simples, um MLP organiza vários neurônios em camadas — uma ou mais **camadas ocultas** entre a entrada e a saída —, o que permite à rede aprender padrões não lineares:

```java
// Criando uma rede com 2 entradas, 1 camada oculta de 4 neurônios e 1 saída
int[] arquitetura = {2, 4, 1};
RedeNeural rede = new RedeNeural(arquitetura);
```

*(A implementação completa de uma classe `RedeNeural` com backpropagation fica como exercício mais avançado — veja as sugestões do Módulo 4 para bibliotecas prontas.)*

---

## Módulo 4: Ferramentas e Bibliotecas em Java

### 4.1. Por que Java?

Java é uma das linguagens mais usadas no mercado corporativo e oferece bom desempenho em produção, o que a torna uma opção sólida para colocar modelos de IA em sistemas já existentes.

### 4.2. Bibliotecas Recomendadas

| Biblioteca               | Uso                             | Link                                                              |
| ------------------------- | -------------------------------- | ------------------------------------------------------------------ |
| **Deeplearning4j**       | A mais completa para Java        | [deeplearning4j.org](https://deeplearning4j.org)                  |
| **ND4J**                 | Computação numérica para Java    | [nd4j.org](https://deeplearning4j.org/docs/latest/nd4j-overview)  |
| **Basic Neural Network** | Simples, ótima para aprendizado  | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork)        |

**Exemplo com a Basic Neural Network Library:**

```java
import de.hatoka.basicneuralnetwork.NeuralNetwork;
import de.hatoka.basicneuralnetwork.NetworkBuilder;

// Rede com 2 entradas, 2 camadas ocultas com 4 neurônios cada, e 1 saída
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

Também é possível treinar um modelo em Python com Keras/TensorFlow e depois importá-lo para uma aplicação Java:

```java
// Importando modelo Keras para Java
String caminhoModelo = new ClassPathResource("meu_modelo.h5").getFile().getPath();
MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);
```

---

## Módulo 5: Exercício Prático — Rede Neural do Zero

### Desafio: Classificador AND Simples

Usando o Perceptron implementado no Módulo 2, treine uma rede que aprenda a operação lógica AND:

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
            System.out.println(entrada[0] + " AND " + entrada[1] +
                             " = " + p.calcular(entrada));
        }
    }
}
```

> 💡 **Desafio extra:** tente adaptar o código para aprender a operação OR. Depois, tente XOR — e descubra por que um único Perceptron não consegue resolvê-lo (essa é, historicamente, a limitação que motivou a criação das redes multicamadas).

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* — Fabio Soares, Alan M.F. Souza.
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) — exemplos de Perceptron e MLP em Java.
3. **micrograd-java**: implementação completa de redes neurais do zero.
