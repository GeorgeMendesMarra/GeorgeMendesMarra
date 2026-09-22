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

### 2.3. Implementando um Perceptron em Java

Este exemplo usa **dois arquivos** (duas classes): `Perceptron.java`, com a lógica da rede, e `TestePerceptron.java`, com o `main` que a treina e testa. Basta colocar os dois arquivos na mesma pasta e compilar.

**Arquivo `Perceptron.java`:**

```java
import java.util.Random;

public class Perceptron {

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

    // Calcula a saída do perceptron para um conjunto de entradas
    public int calcular(double[] entradas) {
        double soma = 0;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        soma += bias;
        return funcaoAtivacao(soma);
    }

    // Treina o perceptron com um único exemplo (entradas + resposta esperada)
    public void treinar(double[] entradas, int esperado) {
        int obtido = calcular(entradas);
        double erro = esperado - obtido;

        // Ajusta cada peso proporcionalmente ao erro e à entrada correspondente
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        bias += taxaAprendizado * erro;
    }
}
```

**Arquivo `TestePerceptron.java`:**

```java
public class TestePerceptron {
    public static void main(String[] args) {
        // Um perceptron com 2 entradas
        Perceptron p = new Perceptron(2);

        // Tabela-verdade da operação AND
        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] esperados = {0, 0, 0, 1};

        // Treina por 100 épocas (100 vezes sobre todos os exemplos)
        for (int epoca = 0; epoca < 100; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        // Testa o perceptron já treinado
        for (double[] entrada : entradas) {
            int resultado = p.calcular(entrada);
            System.out.println((int) entrada[0] + " AND " + (int) entrada[1] + " = " + resultado);
        }
    }
}
```

**Para compilar e rodar (via terminal):**

```
javac Perceptron.java TestePerceptron.java
java TestePerceptron
```

**Saída esperada:**

```
0 AND 0 = 0
0 AND 1 = 0
1 AND 0 = 0
1 AND 1 = 1
```

> ⚠️ **Importante:** este código tem fins didáticos. Em projetos reais, prefira bibliotecas consolidadas, como o Deeplearning4j (veja o Módulo 4).

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

### 3.3. Um MLP (Perceptron Multicamadas) Completo

Um único Perceptron **não consegue** aprender a operação XOR (veja o desafio do Módulo 5) — ele só separa dados com uma linha reta. Para isso, precisamos de uma **camada oculta** entre a entrada e a saída: é o Perceptron Multicamadas (MLP).

Este exemplo implementa uma rede com 2 entradas, 1 camada oculta (com um número configurável de neurônios) e 1 saída, usando a função de ativação **Sigmoid** e treino por **backpropagation**.

**Arquivo `RedeNeural.java`:**

```java
import java.util.Random;

public class RedeNeural {

    private int numEntradas;
    private int numOcultos;
    private double taxaAprendizado = 0.5;

    // Pesos da camada de entrada para a camada oculta: pesosEntradaOculta[neuronioOculto][entrada]
    private double[][] pesosEntradaOculta;
    private double[] biasOculta;

    // Pesos da camada oculta para a saída (rede com 1 único neurônio de saída)
    private double[] pesosOcultaSaida;
    private double biasSaida;

    // Guardados aqui após o forward, para serem reutilizados no backpropagation
    private double[] ativacoesOcultas;
    private double ativacaoSaida;

    public RedeNeural(int numEntradas, int numOcultos) {
        this.numEntradas = numEntradas;
        this.numOcultos = numOcultos;

        Random random = new Random();

        pesosEntradaOculta = new double[numOcultos][numEntradas];
        biasOculta = new double[numOcultos];
        for (int j = 0; j < numOcultos; j++) {
            for (int i = 0; i < numEntradas; i++) {
                pesosEntradaOculta[j][i] = random.nextDouble() * 2 - 1;
            }
            biasOculta[j] = random.nextDouble() * 2 - 1;
        }

        pesosOcultaSaida = new double[numOcultos];
        for (int j = 0; j < numOcultos; j++) {
            pesosOcultaSaida[j] = random.nextDouble() * 2 - 1;
        }
        biasSaida = random.nextDouble() * 2 - 1;
    }

    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    // Derivada da sigmoid, calculada a partir do próprio valor já ativado (a),
    // já que sigmoid'(x) = sigmoid(x) * (1 - sigmoid(x))
    private double derivadaSigmoid(double a) {
        return a * (1 - a);
    }

    // Propagação para frente: calcula a saída da rede para um exemplo de entrada
    public double calcular(double[] entradas) {
        ativacoesOcultas = new double[numOcultos];

        for (int j = 0; j < numOcultos; j++) {
            double soma = biasOculta[j];
            for (int i = 0; i < numEntradas; i++) {
                soma += entradas[i] * pesosEntradaOculta[j][i];
            }
            ativacoesOcultas[j] = sigmoid(soma);
        }

        double somaSaida = biasSaida;
        for (int j = 0; j < numOcultos; j++) {
            somaSaida += ativacoesOcultas[j] * pesosOcultaSaida[j];
        }
        ativacaoSaida = sigmoid(somaSaida);

        return ativacaoSaida;
    }

    // Treina a rede com um exemplo: faz o forward, calcula os erros
    // e ajusta os pesos das duas camadas (backpropagation)
    public void treinar(double[] entradas, double esperado) {
        double obtido = calcular(entradas);

        // Erro e gradiente na camada de saída
        double erroSaida = esperado - obtido;
        double deltaSaida = erroSaida * derivadaSigmoid(obtido);

        // Erro e gradiente de cada neurônio da camada oculta
        double[] deltaOculta = new double[numOcultos];
        for (int j = 0; j < numOcultos; j++) {
            double erroOculto = deltaSaida * pesosOcultaSaida[j];
            deltaOculta[j] = erroOculto * derivadaSigmoid(ativacoesOcultas[j]);
        }

        // Atualiza pesos e bias da camada oculta -> saída
        for (int j = 0; j < numOcultos; j++) {
            pesosOcultaSaida[j] += taxaAprendizado * deltaSaida * ativacoesOcultas[j];
        }
        biasSaida += taxaAprendizado * deltaSaida;

        // Atualiza pesos e bias da camada entrada -> oculta
        for (int j = 0; j < numOcultos; j++) {
            for (int i = 0; i < numEntradas; i++) {
                pesosEntradaOculta[j][i] += taxaAprendizado * deltaOculta[j] * entradas[i];
            }
            biasOculta[j] += taxaAprendizado * deltaOculta[j];
        }
    }
}
```

**Arquivo `TesteRedeNeural.java`** — treinando a rede para aprender o XOR (o mesmo problema em que o Perceptron simples falha):

```java
public class TesteRedeNeural {
    public static void main(String[] args) {
        // 2 entradas, 4 neurônios na camada oculta
        RedeNeural rede = new RedeNeural(2, 4);

        double[][] entradas = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] esperados = {0, 1, 1, 0}; // tabela-verdade do XOR

        // XOR precisa de bem mais épocas que o AND, pois não é linearmente separável
        int epocas = 10000;
        for (int epoca = 0; epoca < epocas; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                rede.treinar(entradas[i], esperados[i]);
            }
        }

        System.out.println("Resultados após " + epocas + " épocas de treino:");
        for (double[] entrada : entradas) {
            double resultado = rede.calcular(entrada);
            System.out.printf("%.0f XOR %.0f = %.4f (arredondado: %d)%n",
                    entrada[0], entrada[1], resultado, Math.round(resultado));
        }
    }
}
```

**Para compilar e rodar:**

```
javac RedeNeural.java TesteRedeNeural.java
java TesteRedeNeural
```

**Saída esperada (os valores exatos variam a cada execução, por causa dos pesos aleatórios iniciais):**

```
Resultados após 10000 épocas de treino:
0 XOR 0 = 0.0123 (arredondado: 0)
0 XOR 1 = 0.9847 (arredondado: 1)
1 XOR 0 = 0.9812 (arredondado: 1)
1 XOR 1 = 0.0176 (arredondado: 0)
```

> 💡 **Para a turma:** vale rodar o `TestePerceptron` (Módulo 2) tentando treinar com a tabela do XOR em vez do AND — ele nunca converge. Depois, mostrar que o `RedeNeural` (com camada oculta) resolve. É a forma mais direta de os alunos sentirem, na prática, por que a camada oculta existe.

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

> ⚠️ **Nota para quem for usar em aula:** o código abaixo é ilustrativo, baseado na documentação pública da biblioteca. APIs de bibliotecas de terceiros mudam entre versões — antes de apresentar aos alunos, adicione a dependência ao `pom.xml` (ou `build.gradle`) do seu projeto e confira, no README do repositório linkado, se os nomes de métodos e classes ainda correspondem à versão instalada. Os exemplos com Java "puro" dos Módulos 2 e 3 (Perceptron e RedeNeural) não têm essa dependência e podem ser usados em aula sem nenhuma instalação extra.

**Exemplo com a Basic Neural Network Library:**

```java
import de.hatoka.basicneuralnetwork.NeuralNetwork;
import de.hatoka.basicneuralnetwork.NetworkBuilder;
import de.hatoka.basicneuralnetwork.ActivationFunctions;

public class TesteBibliotecaExterna {
    public static void main(String[] args) {
        // Rede com 2 entradas, 2 camadas ocultas com 4 neurônios cada, e 1 saída
        NeuralNetwork rede = NetworkBuilder.create(2, 1)
                .setHiddenLayers(2, 4)
                .setActivationFunction(ActivationFunctions.TANH)
                .build();

        double[][] dadosTreino = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] respostasEsperadas = {{0}, {1}, {1}, {0}};

        // Treino
        rede.train(dadosTreino, respostasEsperadas);

        // Previsão
        double[] dadosTeste = {1, 0};
        double[] resultado = rede.guess(dadosTeste);
        System.out.println("Resultado: " + resultado[0]);
    }
}
```

### 4.3. Carregando Modelos Treinados em Java

Também é possível treinar um modelo em Python com Keras/TensorFlow e depois importá-lo para uma aplicação Java — útil quando a equipe de ciência de dados treina em Python, mas o sistema em produção é em Java. Isso exige a dependência `deeplearning4j-modelimport` no projeto.

```java
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.springframework.core.io.ClassPathResource;

public class TesteImportacaoKeras {
    public static void main(String[] args) throws Exception {
        // O arquivo .h5 deve estar na pasta de recursos do projeto (ex.: src/main/resources)
        String caminhoModelo = new ClassPathResource("meu_modelo.h5").getFile().getPath();
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);

        System.out.println(model.summary());
    }
}
```

---

## Módulo 5: Exercício Prático — Rede Neural do Zero

### Desafio: Classificador AND Simples

Usando o Perceptron implementado no Módulo 2 (código completo em `Perceptron.java` e `TestePerceptron.java`), a rede já foi treinada para aprender a operação lógica AND:

| Entrada 1 | Entrada 2 | Saída |
| --------- | --------- | ----- |
| 0         | 0         | 0     |
| 0         | 1         | 0     |
| 1         | 0         | 0     |
| 1         | 1         | 1     |

> 💡 **Desafios extras para os alunos:**
> 1. Troque a tabela de `esperados` no `TestePerceptron` para a operação **OR** (`{0, 1, 1, 1}`) e confirme que o Perceptron também aprende.
> 2. Troque para **XOR** (`{0, 1, 1, 0}`) e observe que, por mais épocas que rode, o Perceptron nunca acerta todos os casos — ele só consegue separar dados com uma reta, e o XOR não é linearmente separável.
> 3. Use o `RedeNeural` do Módulo 3 (que já resolve o XOR) e experimente mudar o número de neurônios da camada oculta (de 2 para 8, por exemplo) ou a `taxaAprendizado`, observando o efeito no número de épocas necessário para convergir.

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* — Fabio Soares, Alan M.F. Souza.
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) — exemplos de Perceptron e MLP em Java.
3. **micrograd-java**: implementação completa de redes neurais do zero.
