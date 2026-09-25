# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra uma figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos**.

### 1.2. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo parecido:

```
Entradas → Multiplica por Pesos → Soma → Função de Ativação → Saída
```

Cada entrada tem um **peso** (um número que indica sua importância). O neurônio:

1. Multiplica cada entrada pelo seu peso
2. Soma tudo (incluindo um **bias** ou viés)
3. Passa o resultado por uma **função de ativação** que decide a saída

> 🎯 **Pense assim:** É como um sistema de "votação ponderada" onde cada entrada vota com um peso diferente, e no final decidimos se o resultado é "sim" ou "não".

---

## Módulo 2: O Perceptron - O Neurônio Mais Simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural. É um único neurônio que:

- Recebe várias entradas (0 ou 1)
- Calcula uma combinação com pesos
- Decide a saída (0 ou 1)

**Exemplo prático:** Classificar se algo é um "ser vivo" ou não com base em 3 bits representando palavras.

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
class Perceptron {
    // Cada posição do array guarda o "peso" (importância) de uma entrada
    private double[] pesos;

    // Viés (bias): desloca a soma, ajuda o neurônio a "decidir" mesmo com entradas zeradas
    private double bias;

    // Define o quão grande é o ajuste feito a cada erro (aprendizado mais rápido ou mais lento)
    private double taxaAprendizado = 0.1;

    public Perceptron(int numeroEntradas) {
        // Um peso para cada entrada que o perceptron vai receber
        pesos = new double[numeroEntradas];
        Random random = new Random();

        // Pesos e bias começam aleatórios entre -1 e 1 (ninguém "sabe nada" ainda)
        for (int i = 0; i < numeroEntradas; i++) {
            pesos[i] = random.nextDouble() * 2 - 1; // entre -1 e 1
        }
        bias = random.nextDouble() * 2 - 1;
    }

    // Função degrau (função de ativação): se a soma for >= 0, "dispara" (1); senão, fica em silêncio (0)
    private int funcaoAtivacao(double soma) {
        return soma >= 0 ? 1 : 0;
    }

    // Calcula a saída do perceptron para um conjunto de entradas (forward propagation)
    public int calcular(double[] entradas) {
        double soma = 0;

        // Multiplica cada entrada pelo seu peso e vai somando (combinação linear)
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        soma += bias; // adiciona o viés à soma total

        return funcaoAtivacao(soma); // decide a saída final
    }

    // Treina o perceptron com um único exemplo (entrada + resposta esperada)
    public void treinar(double[] entradas, int esperado) {
        int obtido = calcular(entradas);       // o que o perceptron respondeu agora
        double erro = esperado - obtido;       // diferença entre o certo e o obtido

        // Ajusta cada peso proporcionalmente ao erro e à própria entrada
        for (int i = 0; i < entradas.length; i++) {
            pesos[i] += taxaAprendizado * erro * entradas[i];
        }
        bias += taxaAprendizado * erro; // o bias também é corrigido
    }
}
```

> ⚠️ **Importante:** Este código é para aprendizado. Em projetos reais, use bibliotecas como Deeplearning4j.

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
- **Função de Ativação**: transforma a soma em uma saída (ex: Sigmoid, Tanh, ReLU)
- **Taxa de Aprendizado**: controla o quanto os pesos são ajustados por erro

### 3.3. Um MLP (Perceptron Multicamadas) Simplificado

```java
// Criando uma rede com 2 entradas, 1 camada oculta de 4 neurônios, e 1 saída
int[] arquitetura = {2, 4, 1};
RedeNeural rede = new RedeNeural(arquitetura);
```

---

## Módulo 4: Ferramentas e Bibliotecas em Java

### 4.1. Por que Java?

Java é uma das linguagens mais usadas no mundo corporativo, com performance superior a linguagens como Python para produção.

### 4.2. Bibliotecas Recomendadas

| Biblioteca               | Uso                           | Link                                                             |
| ------------------------ | ------------------------------ | ----------------------------------------------------------------- |
| **Deeplearning4j**       | A mais completa para Java     | [deeplearning4j.org](https://deeplearning4j.org)                 |
| **ND4J**                 | Computação numérica para Java | [nd4j.org](https://deeplearning4j.org/docs/latest/nd4j-overview) |
| **Basic Neural Network** | Para aprendizado, simples     | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork)       |

**Exemplo com Basic Neural Network Library:**

```java
import de.hatoka.basicneuralnetwork.NeuralNetwork;
import de.hatoka.basicneuralnetwork.NetworkBuilder;

// Monta a arquitetura: 2 entradas, 2 camadas ocultas com 4 neurônios cada, 1 saída
NeuralNetwork rede = NetworkBuilder.create(2, 1)
    .setHiddenLayers(2, 4)
    .setActivationFunction(ActivationFunctions.TANH) // função de ativação das camadas ocultas
    .build();

// Treino: ajusta os pesos da rede repetidamente com base nos dados fornecidos
rede.train(dadosTreino, respostasEsperadas);

// Previsão: usa os pesos já treinados para calcular uma saída (forward propagation)
double[] resultado = rede.guess(dadosTeste);
```

### 4.3. Carregando Modelos Treinados em Java

É possível treinar modelos em Python com Keras/TensorFlow e importá-los para Java:

```java
// Localiza o arquivo do modelo (.h5) já treinado em Python, dentro do classpath do projeto Java
String caminhoModelo = new ClassPathResource("meu_modelo.h5").getFile().getPath();

// Importa a arquitetura e os pesos do modelo Keras para uso dentro da JVM
MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);
```

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
        // Cria um perceptron que recebe 2 entradas
        Perceptron p = new Perceptron(2);

        // Todas as combinações possíveis de entrada para uma porta lógica de 2 bits
        double[][] entradas = {{0,0}, {0,1}, {1,0}, {1,1}};

        // Saída esperada para cada combinação, segundo a tabela-verdade do AND
        int[] esperados = {0, 0, 0, 1};

        // Treina por 100 épocas (100 passagens completas pelos 4 exemplos)
        for (int epoca = 0; epoca < 100; epoca++) {
            for (int i = 0; i < entradas.length; i++) {
                p.treinar(entradas[i], esperados[i]);
            }
        }

        // Depois de treinado, testa se o perceptron aprendeu o AND corretamente
        for (double[] entrada : entradas) {
            System.out.println(entrada[0] + " AND " + entrada[1] +
                             " = " + p.calcular(entrada));
        }
    }
}
```

---

## Módulo 6: Essas Técnicas nos Grandes Modelos de IA (LLMs)

### 6.1. Do Perceptron ao ChatGPT: é a mesma ideia, só em escala gigante

Tudo que vimos nos Módulos 1 a 3 — entrada, peso, soma, bias, função de ativação, forward propagation, backpropagation — continua sendo o motor por baixo de ferramentas como **ChatGPT**, **Gemini**, **Claude**, **Copilot** e **DeepSeek**. A diferença não é o princípio, é a **escala**: em vez de um `Perceptron` com 2 ou 3 entradas, esses modelos têm bilhões (às vezes trilhões) de parâmetros organizados em uma arquitetura chamada **Transformer**, empilhada em dezenas ou centenas de camadas.

> 🎯 **Pense assim:** o `MLPSimples` que vocês implementaram é, conceitualmente, um "bebê" desses modelos. O que muda é o número de neurônios, o número de camadas e um mecanismo extra chamado **atenção (attention)**, que permite ao modelo "olhar" para todas as palavras da frase ao mesmo tempo e decidir quais são mais relevantes para prever a próxima palavra.

### 6.2. Panorama dos principais modelos

| Modelo | Empresa | Arquitetura de base | Observação didática |
| --- | --- | --- | --- |
| **ChatGPT (GPT)** | OpenAI | Transformer denso (todos os parâmetros ativos a cada previsão) | O exemplo mais próximo do "MLP gigante" clássico |
| **Gemini** | Google DeepMind | Transformer, com variantes esparsas (Mixture of Experts) em alguns modelos | Nasceu já pensando em texto, imagem e áudio juntos (multimodal) |
| **Claude** | Anthropic | Transformer | Foco declarado em segurança e alinhamento durante o treinamento |
| **Microsoft Copilot** | Microsoft | Usa modelos da OpenAI (GPT) por trás, integrados aos produtos Microsoft | Mostra como a mesma "rede neural" pode virar vários produtos diferentes |
| **DeepSeek** | DeepSeek AI | Transformer + **Mixture of Experts (MoE)** | Tem centenas de bilhões de parâmetros, mas ativa só uma fração deles por token processado |

### 6.3. O que é essa tal de "Mixture of Experts" (MoE)?

Lembra que no Módulo 3 vimos que uma rede com uma camada oculta de 4 neurônios usa **todos os 4 neurônios** para calcular qualquer saída? Isso é uma rede **densa**.

O MoE, usado pelo **DeepSeek**, funciona diferente: em vez de uma camada oculta única, existem **vários "blocos especialistas"** em paralelo, e uma pequena rede chamada **gate (portão)** decide, para cada entrada, quais especialistas devem ser ativados. Esse portão dinâmico ativa só um subconjunto de sub-redes especializadas, o que reduz o custo computacional e permite maior especialização.

```
Entrada → Gate decide quais "especialistas" usar → Só esses são ativados → Saída
```

> 🎯 **Analogia:** é como levar uma dúvida de matemática para a faculdade inteira. Em vez de perguntar para todos os professores (rede densa), a "portaria" (gate) te encaminha direto para o departamento de matemática (os especialistas certos).

### 6.4. Onde entram forward e backpropagation nesses modelos?

- **Treinamento (backpropagation em escala industrial):** acontece uma única vez, em datacenters, usando milhares de GPUs e trilhões de palavras de texto. É aqui que os pesos da rede são ajustados — exatamente como no `Perceptron.treinar()` do Módulo 2, só que repetido bilhões de vezes.
- **Inferência (forward propagation):** é o que acontece quando você manda uma mensagem para o ChatGPT, Claude ou Gemini. Os pesos já estão **fixos** (não mudam mais); o modelo só faz o cálculo de entrada → pesos → soma → ativação → saída, camada após camada, para prever a próxima palavra.

Ou seja: quando vocês usam essas ferramentas no dia a dia, estão vendo só a fase de **forward propagation** — o "aprendizado" já aconteceu antes, no treinamento.

### 6.5. E o Java, onde entra nisso tudo? (reforçando a dupla Python + Java)

Como já vimos no Módulo 4, o **treinamento** de todos esses modelos — GPT, Gemini, Claude, DeepSeek — é feito quase exclusivamente em **Python**, com frameworks como PyTorch e TensorFlow. Isso não é coincidência: o ecossistema de pesquisa em IA (papers, bibliotecas, comunidade, GPUs otimizadas) foi construído em cima do Python, e é lá que a inovação em arquitetura de redes acontece primeiro.

O **Java entra depois, na hora de colocar isso para funcionar dentro de sistemas do mundo real**:

- **Integração corporativa**: a maioria dos sistemas de grandes empresas — bancos, ERPs, sistemas acadêmicos como os que vocês vão construir — já é escrita em Java. Em vez de reescrever esses sistemas em Python, o Java consome os modelos prontos via **API REST** (chamando ChatGPT, Claude, Gemini ou Copilot como serviços externos).
- **SDKs oficiais para Java**: OpenAI, Google e Anthropic oferecem bibliotecas cliente específicas para Java, o que evita que o desenvolvedor precise montar as requisições HTTP "na mão".
- **Robustez e escala em produção**: a JVM (máquina virtual do Java) tem décadas de otimização para aplicações que precisam rodar 24/7, com muitos usuários simultâneos e forte controle de tipos — pontos fortes para *servir* um modelo de IA em produção, mesmo que ele não tenha nascido em Java.
- **Modelos treinados em Python, carregados em Java**: como vimos na seção 4.3, é possível treinar uma rede em Python (Keras/TensorFlow) e importar o modelo já pronto para dentro de uma aplicação Java com o Deeplearning4j — sem precisar treinar nada dentro da JVM.

> 🎯 **Resumindo a parceria:** o **Python treina** (é onde a rede aprende, com toda a flexibilidade de pesquisa), e o **Java entrega** (é onde o modelo já treinado roda dentro de sistemas robustos, seguros e prontos para produção). Um não substitui o outro — eles se complementam.

### ✅ Checkpoint de Compreensão

1. O que muda entre o `Perceptron` que vocês implementaram e um modelo como o ChatGPT: o princípio ou a escala?
2. Por que o DeepSeek consegue ser mais barato de rodar mesmo tendo um número enorme de parâmetros?
3. Quando você conversa com o Claude ou o Gemini, você está vendo a fase de treinamento ou a fase de inferência (forward propagation)?
4. Por que faz sentido treinar em Python e servir/integrar em Java, em vez de fazer tudo em uma linguagem só?

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* - Fabio Soares, Alan M.F. Souza
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) - exemplos de Perceptron e MLP em Java
3. **micrograd-java**: implementação completa de redes neurais do zero
