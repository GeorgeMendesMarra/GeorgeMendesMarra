# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial, mas com noções básicas de Java (classes, objetos, arrays).

**Como usar este material:** cada módulo termina com um bloco **✅ Checkpoint**, com 1-2 perguntas rápidas para você confirmar que entendeu antes de seguir em frente. Não pule os checkpoints — eles existem porque redes neurais são um assunto que "empilha" conceitos.

---

## Módulo 1: O que é uma Rede Neural?

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

### 1.3. Exemplo Prático em Java

Veja como calcular a saída de um neurônio simples:

```java
public class NeurônioSimples {
    public static void main(String[] args) {
        // Entradas: três características (ex: tamanho, cor, velocidade)
        double[] entradas = {0.5, 0.8, 0.3};
        
        // Pesos: cada entrada tem um peso que indicava importância
        double[] pesos = {0.2, 0.7, 0.1};
        
        // Bias: um ajuste fino independente das entradas
        double bias = 0.5;
        
        // Passo 1: multiplicar cada entrada pelo seu peso
        double soma = 0.0;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
            System.out.println("Entrada " + i + ": " + entradas[i] + 
                             " × peso: " + pesos[i] + " = " + 
                             (entradas[i] * pesos[i]));
        }
        
        // Passo 2: adicionar o bias
        soma += bias;
        System.out.println("\nSoma total: " + soma);
        System.out.println("Soma com bias: " + soma);
        
        // Passo 3: aplicar a função de ativação (aqui, um simples degrau)
        int saida = (soma >= 0.0) ? 1 : 0;
        System.out.println("Saída (após ativação degrau): " + saida);
    }
}
```

**Saída esperada:**
```
Entrada 0: 0.5 × peso: 0.2 = 0.1
Entrada 1: 0.8 × peso: 0.7 = 0.56
Entrada 2: 0.3 × peso: 0.1 = 0.03

Soma total: 0.69
Soma com bias: 1.19
Saída (após ativação degrau): 1
```

**O que está acontecendo:**
- Cada entrada "vota" com peso diferente (entrada 1 tem peso 0.7, a mais importante)
- A soma total de 1.19 passa pela função de ativação (degrau)
- Como 1.19 ≥ 0, o resultado é 1 ("verdadeiro")

**✅ Checkpoint 1**
- O que diferencia um programa Java "tradicional" de uma rede neural treinada?
- Em Java, qual tipo de dado usaríamos para representar os pesos de um neurônio? E as entradas?

---

## Módulo 2: O Perceptron — O Neurônio Mais Simples

> **Ciclo de treino:** Calcular saída → Comparar com o esperado → Calcular o erro → Ajustar pesos → ↻ Repetir

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

/**
 * Perceptron: um neurônio artificial que aprende com exemplos.
 * 
 * Estrutura:
 *   entradas → [pesos e bias] → soma → [função de ativação] → saída (0 ou 1)
 * 
 * Aprendizado:
 *   erro = esperado − obtido
 *   novoPeso = peso + taxaAprendizado × erro × entrada
 */
class Perceptron {
    private double[] pesos;          // Um peso para cada entrada
    private double bias;              // Ajuste fino independente
    private double taxaAprendizado;   // Controla o tamanho do "passo" no aprendizado

    /**
     * Construtor: inicializa pesos aleatoriamente.
     * 
     * @param numeroEntradas quantidade de entradas que o perceptron receberá
     */
    public Perceptron(int numeroEntradas) {
        pesos = new double[numeroEntradas];
        Random random = new Random();
        
        // Inicializa pesos com valores aleatórios entre -1 e 1
        for (int i = 0; i < numeroEntradas; i++) {
            pesos[i] = random.nextDouble() * 2 - 1;
        }
        
        // Inicializa bias aleatoriamente
        bias = random.nextDouble() * 2 - 1;
        
        // Taxa de aprendizado: quanto maior, mais agressivas são as correções
        this.taxaAprendizado = 0.1;
    }

    /**
     * Função de ativação: degrau (step function).
     * Se a soma for >= 0, retorna 1; senão, retorna 0.
     * 
     * Isso simula uma decisão binária: "sim" (1) ou "não" (0).
     */
    private int funcaoAtivacao(double soma) {
        return soma >= 0.0 ? 1 : 0;
    }

    /**
     * Forward propagation: calcula a saída do perceptron.
     * 
     * Etapas:
     *   1. Multiplica cada entrada pelo seu peso
     *   2. Soma todos os resultados
     *   3. Adiciona o bias
     *   4. Aplica a função de ativação
     * 
     * @param entradas array de valores de entrada
     * @return saída do perceptron (0 ou 1)
     */
    public int calcular(double[] entradas) {
        // Etapa 1 e 2: multiplicar e somar
        double soma = 0.0;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        
        // Etapa 3: adicionar bias
        soma += bias;
        
        // Etapa 4: aplicar função de ativação
        return funcaoAtivacao(soma);
    }

    /**
     * Treina o perceptron com UM exemplo de treino.
     * 
     * Algoritmo:
     *   1. Calcula a saída do perceptron (o que ele "chutou")
     *   2. Compara com o esperado
     *   3. Se errou (erro != 0), ajusta os pesos
     *   4. Repete muitas vezes
     * 
     * @param entradas valores de entrada
     * @param esperado resposta correta (0 ou 1)
     */
    public void treinar(double[] entradas, int esperado) {
        // Passo 1: calcula o que o perceptron "acha" (forward pass)
        int obtido = calcular(entradas);
        
        // Passo 2: calcula o erro
        // Se acertou (erro == 0), não faz nada
        // Se errou, erro será ±1 (pode ser -1 ou +1)
        double erro = esperado - obtido;

        // Passo 3: ajusta cada peso
        // A fórmula é: peso += taxaAprendizado × erro × entrada
        // 
        // Intuição:
        //   - Se erro é grande e entrada é grande, o peso muda muito
        //   - Se erro é pequeno ou entrada é zero, o peso muda pouco
        //   - O sinal (+ ou -) do erro determina a direção da mudança
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        
        // Também ajusta o bias
        bias += taxaAprendizado * erro;
    }

    /**
     * Retorna os pesos atuais (útil para debug).
     */
    public double[] getPesos() {
        return pesos.clone();
    }

    /**
     * Retorna o bias atual (útil para debug).
     */
    public double getBias() {
        return bias;
    }
}
```

> ⚠️ **Importante:** este código é didático, para você entender o mecanismo por dentro. Em projetos reais de produção, use bibliotecas Java especializadas como **Deeplearning4j** (veja o Módulo 4) — elas já resolvem questões de performance, paralelismo e estabilidade numérica que este exemplo não trata.

**✅ Checkpoint 2**
- Por que o método `calcular` não altera os pesos, mas o método `treinar` sim?
- Se `taxaAprendizado` fosse muito alta (por exemplo, `2.0`), o que você imagina que aconteceria com o aprendizado?

---

## Módulo 3: Aprendizado e Treinamento

**Visualização de um MLP (Multilayer Perceptron):**
```
Entradas         Camada Oculta      Saída
(2 neurônios)    (4 neurônios)      (1 neurônio)
    ⊗ ─────────────┐
                    ├──→ ⊗ ─┐
    ⊗ ─────────────┤    ⊗   ├──→ ⊗ (saída)
                    ├──→ ⊗ ─┤
                    │    ⊗ ─┘
                    └──→ ⊗

→ Forward propagation: o sinal avança da esquerda para a direita
← Backpropagation: o erro retorna corrigindo os pesos
```

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

Um único perceptron só resolve problemas simples (linearmente separáveis). Para problemas mais complexos, empilhamos neurônios em **camadas**:

```java
/**
 * MLP Simplificado: rede com 1 camada oculta.
 * 
 * Arquitetura: entradas → camada oculta → saída
 * 
 * Cada neurônio tem seus próprios pesos e bias,
 * e aprende ajustando-os gradualmente.
 */
public class MLPSimples {
    private Perceptron[] camadaOculta;  // Neurônios intermediários
    private Perceptron saidaNeural;      // Neurônio final
    
    /**
     * Construtor.
     * 
     * @param numEntradas quantidade de valores de entrada
     * @param numOcultos quantidade de neurônios na camada oculta
     */
    public MLPSimples(int numEntradas, int numOcultos) {
        // Cria a camada oculta
        camadaOculta = new Perceptron[numOcultos];
        for (int i = 0; i < numOcultos; i++) {
            // Cada neurônio da camada oculta recebe numEntradas
            camadaOculta[i] = new Perceptron(numEntradas);
        }
        
        // Cria o neurônio de saída
        // Ele recebe entrada da camada oculta (numOcultos valores)
        saidaNeural = new Perceptron(numOcultos);
    }
    
    /**
     * Forward propagation: calcula a saída da rede.
     * 
     * @param entradas valores de entrada
     * @return saída final (0 ou 1)
     */
    public int calcular(double[] entradas) {
        // Passo 1: calcular saídas da camada oculta
        int[] saidasOcultas = new int[camadaOculta.length];
        for (int i = 0; i < camadaOculta.length; i++) {
            saidasOcultas[i] = camadaOculta[i].calcular(entradas);
        }
        
        // Passo 2: usar as saídas ocultas como entrada para o neurônio final
        // Converte int[] para double[] porque o método calcular espera double[]
        double[] entradasFinal = new double[saidasOcultas.length];
        for (int i = 0; i < saidasOcultas.length; i++) {
            entradasFinal[i] = (double) saidasOcultas[i];
        }
        
        // Passo 3: calcular saída final
        return saidaNeural.calcular(entradasFinal);
    }
    
    /**
     * Treina a rede com UM exemplo.
     * 
     * NOTA: Este treinamento é simplificado. Em redes reais,
     * o ajuste dos pesos usa backpropagation completo,
     * que distribui o erro para trás pela rede.
     * 
     * Para não complicar demais, aqui treinaremos apenas
     * o neurônio de saída (aproximação educacional).
     * 
     * @param entradas valores de entrada
     * @param esperado resposta correta (0 ou 1)
     */
    public void treinarSimples(double[] entradas, int esperado) {
        // Calcula saídas da camada oculta
        int[] saidasOcultas = new int[camadaOculta.length];
        for (int i = 0; i < camadaOculta.length; i++) {
            saidasOcultas[i] = camadaOculta[i].calcular(entradas);
        }
        
        // Converte para double para o neurônio final
        double[] entradasFinal = new double[saidasOcultas.length];
        for (int i = 0; i < saidasOcultas.length; i++) {
            entradasFinal[i] = (double) saidasOcultas[i];
        }
        
        // Treina apenas o neurônio de saída (aproximação simplificada)
        saidaNeural.treinar(entradasFinal, esperado);
    }
}
```

**Diferença-chave:**
- Um **Perceptron** simples: entradas → soma ponderada → ativação → saída
- Um **MLP**: entradas → camada oculta (vários neurônios em paralelo) → saída

O MLP consegue aprender padrões que o Perceptron isolado não consegue, porque a camada oculta extrai "features" intermediárias que facilitam a separação final.

**✅ Checkpoint 3**
- Qual a diferença entre uma "época" e um único "exemplo de treino"?
- Por que precisamos de camadas (MLP) e não apenas de um perceptron isolado?

---

## Módulo 4: Ferramentas e Bibliotecas em Java

**Pilha de tecnologia Java para IA:**
```
┌─────────────────────────────────┐
│  Sua Aplicação Java             │ ← Spring, backend web, API REST
├─────────────────────────────────┤
│  Deeplearning4j                 │ ← Framework de deep learning
├─────────────────────────────────┤
│  ND4J (Numerical Derivatives)   │ ← Computação numérica (como NumPy)
├─────────────────────────────────┤
│  JVM (Java Virtual Machine)     │ ← Motor otimizado, garbage collection
└─────────────────────────────────┘
```

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

#### Exemplo 1: Usando ND4J para Operações Numéricas

ND4J é o "NumPy do Java" — oferece matrizes n-dimensionais e operações eficientes:

```java
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.api.ndarray.INDArray;

public class ExemploND4J {
    public static void main(String[] args) {
        // Cria uma matriz de entradas (3 exemplos, 2 features cada)
        INDArray entradas = Nd4j.create(new double[][]{
            {0.1, 0.2},
            {0.5, 0.6},
            {0.9, 0.8}
        });
        
        // Cria uma matriz de pesos
        INDArray pesos = Nd4j.create(new double[]{0.3, 0.7});
        
        // Multiplica e soma (operação vetorizada)
        INDArray resultado = entradas.mmul(pesos.reshape(2, 1));
        
        System.out.println("Entradas:\n" + entradas);
        System.out.println("Pesos:\n" + pesos);
        System.out.println("Resultado (entradas × pesos):\n" + resultado);
        
        // ND4J calcula isso de forma muito eficiente
        // (usa BLAS subjacente, pode rodar em GPU)
    }
}
```

**Saída:**
```
Entradas:
[[0.10, 0.20],
 [0.50, 0.60],
 [0.90, 0.80]]

Resultado:
[[0.17],   // 0.1*0.3 + 0.2*0.7 = 0.17
 [0.57],   // 0.5*0.3 + 0.6*0.7 = 0.57
 [0.83]]   // 0.9*0.3 + 0.8*0.7 = 0.83
```

#### Exemplo 2: Usando Deeplearning4j para Redes Neurais

DL4J oferece uma API de alto nível para construir e treinar redes:

```java
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.api.ndarray.INDArray;

public class ExemploDL4J {
    public static void main(String[] args) {
        // Dados de treino para a operação AND
        // Entradas: pares de valores [0-1, 0-1]
        // Saída: resultado do AND
        INDArray entradas = Nd4j.create(new double[][]{
            {0.0, 0.0},
            {0.0, 1.0},
            {1.0, 0.0},
            {1.0, 1.0}
        });
        
        INDArray saidas = Nd4j.create(new double[][]{
            {0.0},
            {0.0},
            {0.0},
            {1.0}
        });
        
        // Configura a rede neural
        // Arquitetura: 2 entradas → 4 neurônios ocultos → 1 saída
        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
            .seed(123)                           // Seed para reprodutibilidade
            .activation(Activation.RELU)         // ReLU na camada oculta
            .weightInit(WeightInit.XAVIER)       // Inicialização Xavier
            .updater(new Adam(0.001))            // Otimizador Adam
            .list()
            .layer(0, new org.deeplearning4j.nn.conf.layers.DenseLayer.Builder()
                .nIn(2)                          // 2 entradas
                .nOut(4)                         // 4 neurônios ocultos
                .build())
            .layer(1, new org.deeplearning4j.nn.conf.layers.OutputLayer.Builder()
                .nIn(4)
                .nOut(1)                         // 1 saída
                .activation(Activation.SIGMOID)  // Sigmoid para classificação
                .build())
            .build();
        
        // Cria a rede
        MultiLayerNetwork model = new MultiLayerNetwork(config);
        model.init();
        
        // Prepara o dataset
        DataSet dataset = new DataSet(entradas, saidas);
        
        // Treina por 1000 épocas
        for (int epoca = 0; epoca < 1000; epoca++) {
            model.fit(dataset);
            
            if (epoca % 250 == 0) {
                double score = model.score(dataset);
                System.out.println("Época " + epoca + " - Loss: " + score);
            }
        }
        
        // Teste: faz previsões
        System.out.println("\nPrevisões após treino:");
        for (int i = 0; i < entradas.rows(); i++) {
            INDArray input = entradas.getRow(i);
            INDArray output = model.output(input);
            System.out.println("Entrada " + input + " → Saída: " + 
                             output.getDouble(0));
        }
    }
}
```

**O que está acontecendo:**
- Configura a rede com 2 entradas, 4 neurônios ocultos e 1 saída
- Usa ReLU para extração de features, SIGMOID para a saída (classificação)
- Treina com Adam (otimizador moderno que ajusta a taxa de aprendizado automaticamente)
- Após treino, faz previsões em novos dados

**Diferença-chave: do código manual para a biblioteca**
- Módulo 2 (manual): você escreve `for` loops e ajustes de peso
- Módulo 4 (DL4J): você descreve a arquitetura, DL4J cuida de GPU, paralelismo, otimizações

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

**Visualização: os 4 pontos do AND em 2D**
```
entrada 2
    1  │  (0,1)        (1,1) ✓
       │   ✗            ●
       │ 
    0  │  (0,0)        (1,0)
       │   ✗            ✗
       └──────────────────── entrada 1
          0           1

✗ = saída 0 (AND falso)
● = saída 1 (AND verdadeiro — único ponto onde ambas entradas são 1)

Linha tracejada: fronteira que o perceptron aprende
```

### Desafio: Classificador AND Simples

Vamos usar o `Perceptron` que implementamos no Módulo 2 para aprender a operação lógica **AND**:

| Entrada 1 | Entrada 2 | Saída esperada |
| :-------: | :-------: | :------------: |
| 0         | 0         | 0               |
| 0         | 1         | 0               |
| 1         | 0         | 0               |
| 1         | 1         | 1               |

**Código completo e funcional:**

```java
/**
 * Desafio: Treinar um Perceptron para aprender a operação lógica AND.
 * 
 * Tabela verdade do AND:
 *   entrada1  entrada2  saída
 *      0         0        0
 *      0         1        0
 *      1         0        0
 *      1         1        1  ← único caso onde a saída é 1
 * 
 * O Perceptron começa com pesos aleatórios.
 * Após treino, deve conseguir classificar corretamente os 4 exemplos.
 */
public class DesafioAND {
    public static void main(String[] args) {
        System.out.println("=== Treinando Perceptron para AND ===\n");
        
        // Cria um perceptron com 2 entradas
        Perceptron p = new Perceptron(2);
        
        // Dados de treino: os 4 exemplos do AND
        double[][] entradas = {
            {0, 0},  // 0 AND 0 = 0
            {0, 1},  // 0 AND 1 = 0
            {1, 0},  // 1 AND 0 = 0
            {1, 1}   // 1 AND 1 = 1
        };
        
        int[] esperados = {0, 0, 0, 1};
        
        System.out.println("Pesos ANTES do treino:");
        System.out.println("  w1 = " + String.format("%.4f", p.getPesos()[0]));
        System.out.println("  w2 = " + String.format("%.4f", p.getPesos()[1]));
        System.out.println("  bias = " + String.format("%.4f", p.getBias()));
        System.out.println();
        
        // Treina por 100 épocas
        // Cada época passa por todos os 4 exemplos uma vez
        int numEpocas = 100;
        for (int epoca = 0; epoca < numEpocas; epoca++) {
            int acertos = 0;
            
            // Para cada exemplo de treino
            for (int i = 0; i < entradas.length; i++) {
                int obtido = p.calcular(entradas[i]);
                int esperado = esperados[i];
                
                // Treina o perceptron
                p.treinar(entradas[i], esperado);
                
                // Conta se acertou
                if (obtido == esperado) {
                    acertos++;
                }
            }
            
            // A cada 25 épocas, mostra o progresso
            if ((epoca + 1) % 25 == 0) {
                System.out.println("Época " + (epoca + 1) + ": " + 
                                 acertos + "/4 acertos");
            }
        }
        
        System.out.println("\nPesos DEPOIS do treino:");
        System.out.println("  w1 = " + String.format("%.4f", p.getPesos()[0]));
        System.out.println("  w2 = " + String.format("%.4f", p.getPesos()[1]));
        System.out.println("  bias = " + String.format("%.4f", p.getBias()));
        System.out.println();
        
        // ============ TESTE ============
        System.out.println("=== Testando o Perceptron Treinado ===\n");
        System.out.println("Entrada1  Entrada2  Esperado  Obtido  Correto?");
        System.out.println("─────────────────────────────────────────────");
        
        int acertosTotal = 0;
        for (int i = 0; i < entradas.length; i++) {
            int obtido = p.calcular(entradas[i]);
            int esperado = esperados[i];
            boolean acertou = (obtido == esperado);
            
            if (acertou) acertosTotal++;
            
            System.out.printf("   %.0f        %.0f        %d          %d        %s\n",
                entradas[i][0], 
                entradas[i][1],
                esperado,
                obtido,
                acertou ? "✓ SIM" : "✗ NÃO");
        }
        
        System.out.println("─────────────────────────────────────────────");
        System.out.println("Taxa de acurácia: " + acertosTotal + "/4 (" + 
                         (acertosTotal * 25) + "%)");
        System.out.println();
        
        // Tenta um novo exemplo (interpolação)
        System.out.println("Teste extra:");
        double[] novaEntrada = {0.8, 0.9};
        int resultado = p.calcular(novaEntrada);
        System.out.println("Entrada: " + novaEntrada[0] + " AND " + novaEntrada[1] + 
                         " → Perceptron prediz: " + resultado);
    }
}
```

**Saída esperada (exemplo):**
```
=== Treinando Perceptron para AND ===

Pesos ANTES do treino:
  w1 = 0.7234
  w2 = -0.4891
  bias = 0.1256

Época 25: 4/4 acertos
Época 50: 4/4 acertos
Época 75: 4/4 acertos
Época 100: 4/4 acertos

Pesos DEPOIS do treino:
  w1 = 0.8924
  w2 = 0.8156
  bias = -0.6234

=== Testando o Perceptron Treinado ===

Entrada1  Entrada2  Esperado  Obtido  Correto?
─────────────────────────────────────────────
   0        0        0          0        ✓ SIM
   0        1        0          0        ✓ SIM
   1        0        0          0        ✓ SIM
   1        1        1          1        ✓ SIM
─────────────────────────────────────────────
Taxa de acurácia: 4/4 (100%)

Teste extra:
Entrada: 0.8 AND 0.9 → Perceptron prediz: 1
```

**O que acontece passo a passo:**

1. **Inicialização:** pesos aleatórios
2. **Época 1:** Perceptron faz previsões ruins, ajusta os pesos
3. **Épocas 2-50:** Continua refinando, cada erro gera correção
4. **Converge:** Em torno de época 50, já acerta todos os 4 exemplos
5. **Teste:** Verifica se realmente aprendeu

### 5.1. Exploração: O Limite do Perceptron (XOR)

Para entender por que precisamos do MLP, tente treinar um Perceptron para aprender **XOR** (ou exclusivo):

| Entrada1 | Entrada2 | XOR (saída) |
| :-------: | :-------: | :---------: |
| 0         | 0         | 0            |
| 0         | 1         | 1            |
| 1         | 0         | 1            |
| 1         | 1         | 0            |

**Código para testar XOR:**

```java
public class DesafioXOR {
    public static void main(String[] args) {
        System.out.println("=== Tentando treinar Perceptron para XOR ===\n");
        
        Perceptron p = new Perceptron(2);
        
        double[][] entradas = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        
        int[] esperados = {0, 1, 1, 0};  // XOR: verdadeiro quando as entradas diferem
        
        // Treina por 100 épocas
        int numEpocas = 100;
        for (int epoca = 0; epoca < numEpocas; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }
        
        // Testa
        System.out.println("Resultado após 100 épocas:");
        System.out.println("Entrada1  Entrada2  Esperado (XOR)  Obtido  Correto?");
        System.out.println("──────────────────────────────────────────────────");
        
        int acertos = 0;
        for (int i = 0; i < entradas.length; i++) {
            int obtido = p.calcular(entradas[i]);
            int esperado = esperados[i];
            boolean acertou = (obtido == esperado);
            
            if (acertou) acertos++;
            
            System.out.printf("   %.0f        %.0f           %d              %d        %s\n",
                entradas[i][0], 
                entradas[i][1],
                esperado,
                obtido,
                acertou ? "✓" : "✗");
        }
        
        System.out.println("──────────────────────────────────────────────────");
        System.out.println("Acurácia: " + acertos + "/4 (" + (acertos * 25) + "%)\n");
        
        if (acertos < 4) {
            System.out.println("❌ O Perceptron NÃO conseguiu aprender XOR!");
            System.out.println("   Isso é esperado — XOR é não-linearmente separável.");
            System.out.println("   Para resolver XOR, você precisa de um MLP (Módulo 3.3).");
        }
    }
}
```

**Saída esperada:**
```
=== Tentando treinar Perceptron para XOR ===

Resultado após 100 épocas:
Entrada1  Entrada2  Esperado (XOR)  Obtido  Correto?
──────────────────────────────────────────────────
   0        0           0              0        ✓
   0        1           1              0        ✗  ← ERRO!
   1        0           1              0        ✗  ← ERRO!
   1        1           0              1        ✗  ← ERRO!
──────────────────────────────────────────────────
Acurácia: 1/4 (25%)

❌ O Perceptron NÃO conseguiu aprender XOR!
   Isso é esperado — XOR é não-linearmente separável.
   Para resolver XOR, você precisa de um MLP (Módulo 3.3).
```

**Por que o Perceptron falha em XOR?**

Um Perceptron só consegue aprender a separar dados com uma **linha reta** (em 2D) ou um **hiperplano** (em dimensões maiores). Veja visualmente:

```
Dado AND (funciona):         Dado XOR (não funciona):
entrada 2                    entrada 2
    1  │ X  . ✓               1  │ . ✓   ✓ .
       │                        │ 
    0  │ X  . X               0  │ ✓ .   . ✓
       └──────────               └──────────
          entrada 1                entrada 1

X = classe 0                 . = classe 0
✓ = classe 1                 ✓ = classe 1

AND: uma linha reta separa    XOR: precisa de um "L" ou uma curva
(Perceptron consegue)         (apenas MLP consegue)
```

Um Perceptron traça uma reta. Para XOR, você precisa de uma curva ou de duas retas em ângulo — isso requer múltiplas camadas (MLP).

**✅ Checkpoint 5**
- O que aconteceu quando você tentou treinar o perceptron para resolver XOR? Por quê, na sua opinião, isso acontece?

---

## Módulo 6: 📚 Recursos para Aprofundamento em Java

**Trilha de aprofundamento:**
```
Livro → neuraljava → micrograd-java → DL4J
  ↓          ↓            ↓           ↓
Fundamentos Código Real  Do Zero    Produção
```

### Recursos Recomendados

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
