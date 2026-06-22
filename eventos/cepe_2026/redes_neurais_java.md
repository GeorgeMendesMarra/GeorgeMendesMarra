# 🧠 Minicurso: Redes Neurais em Java para Iniciantes

**Público-alvo:** Pessoas sem conhecimento prévio em redes neurais ou inteligência artificial.

---

## Módulo 1: O que é uma Rede Neural?

### 1.1. Uma Analogia Simples

Imagine que você está ensinando uma criança a reconhecer animais. Você mostra uma figura de um gato e diz "gato". Mostra um cachorro e diz "cachorro". Depois de muitos exemplos, a criança começa a identificar sozinha.

Uma rede neural artificial funciona de forma parecida: **ela aprende com exemplos**.

### 1.2. O Neurônio Artificial

O cérebro humano tem neurônios que recebem sinais, processam e transmitem respostas. Um neurônio artificial faz algo parecido :

```
Entradas → Multiplica por Pesos → Soma → Função de Ativação → Saída
```

Cada entrada tem um **peso** (um número que indica sua importância). O neurônio:
1. Multiplica cada entrada pelo seu peso
2. Soma tudo (incluindo um **bias** ou viés)
3. Passa o resultado por uma **função de ativação** que decide a saída

> 🎯 **Pense assim:** É como um sistema de "votação ponderada" onde cada entrada vota com um peso diferente, e no final decidimos se o resultado é "sim" ou "não" .

---

## Módulo 2: O Perceptron - O Neurônio Mais Simples

### 2.1. O que é um Perceptron?

Proposto em 1958 por Frank Rosenblatt, o Perceptron é o modelo mais básico de rede neural . É um único neurônio que:
- Recebe várias entradas (0 ou 1)
- Calcula uma combinação com pesos
- Decide a saída (0 ou 1)

**Exemplo prático:** Classificar se algo é um "ser vivo" ou não com base em 3 bits representando palavras .

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

> ⚠️ **Importante:** Este código é para aprendizado. Em projetos reais, use bibliotecas como Deeplearning4j .

---

## Módulo 3: Aprendizado e Treinamento

### 3.1. Como a Rede "Aprende"?

O aprendizado em redes neurais é um processo de **tentativa e erro** :

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

## Módulo 4: Ferramentas e Bibliotecas em Java

### 4.1. Por que Java?

Java é uma das linguagens mais usadas no mundo corporativo, com performance superior a linguagens como Python para produção .

### 4.2. Bibliotecas Recomendadas

| Biblioteca | Uso | Link |
|------------|-----|------|
| **Deeplearning4j** | A mais completa para Java | [deeplearning4j.org](https://deeplearning4j.org) |
| **ND4J** | Computação numérica para Java | [nd4j.org](https://deeplearning4j.org/docs/latest/nd4j-overview) |
| **Basic Neural Network** | Para aprendizado, simples | [GitHub](https://github.com/Thomas-Bergmann/neuralnetwork) |

**Exemplo com Basic Neural Network Library:**
```java
import de.hatoka.basicneuralnetwork.NeuralNetwork;
import de.hatoka.basicneuralnetwork.NetworkBuilder;

// Rede com 2 entradas, 2 camadas ocultas com 4 neurônios, 1 saída
NeuralNetwork rede = NetworkBuilder.create(2,1)
    .setHiddenLayers(2, 4)
    .setActivationFunction(ActivationFunctions.TANH)
    .build();

// Treino
rede.train(dadosTreino, respostasEsperadas);

// Previsão
double[] resultado = rede.guess(dadosTeste);
```

### 4.3. Carregando Modelos Treinados em Java

É possível treinar modelos em Python com Keras/TensorFlow e importá-los para Java :

```java
// Importando modelo Keras para Java
String caminhoModelo = new ClassPathResource("meu_modelo.h5").getFile().getPath();
MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(caminhoModelo);
```

---

## Módulo 5: Exercício Prático - Rede Neural do Zero

### Desafio: Classificador AND Simples

Crie uma rede neural que aprenda a operação lógica AND:

| Entrada 1 | Entrada 2 | Saída |
|-----------|-----------|-------|
| 0 | 0 | 0 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

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

---

## 📚 Recursos para Aprofundamento

1. **Livro**: *Neural Network Programming with Java* - Fabio Soares, Alan M.F. Souza 
2. **Projeto de exemplo**: [neuraljava no GitHub](https://github.com/cleuton/neuraljava) - exemplos de Perceptron e MLP em Java 
3. **micrograd-java**: implementação completa de redes neurais do zero 

---
