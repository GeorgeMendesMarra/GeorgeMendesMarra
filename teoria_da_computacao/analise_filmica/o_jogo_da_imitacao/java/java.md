Para aprofundarmos a relação entre **Java e a Máquina Enigma**, vamos além da superfície e explorar como implementar os detalhes mais "traiçoeiros" da máquina física (como o *Ring Setting* e o *Double Stepping*) e como a arquitetura do Java moderno facilita essa simulação.

---

## 1. O Desafio da Aritmética Circular

Na Enigma, os rotores não apenas substituem letras; eles as deslocam com base na sua rotação atual. Em Java, o maior erro de iniciantes é esquecer que o resto da divisão de números negativos funciona de forma diferente.

Ao calcular o deslocamento inverso (do refletor de volta para o teclado), usamos:



Em Java, garantimos que o resultado seja sempre positivo para evitar o `ArrayIndexOutOfBoundsException`:

```java
int index = (inputChar - 'A' + position - ringSetting + 26) % 26;

```

---

## 2. Implementando o "Double Stepping"

A Enigma tinha uma anomalia mecânica famosa: o rotor do meio avançava duas vezes em certas posições devido à forma como os dentes das engrenagens (notches) eram cortados. Simular isso em Java exige uma lógica específica de controle:

```java
public void step(Rotor r1, Rotor r2, Rotor r3) {
    // Se o rotor 2 estiver na posição de entalhe (notch), ele e o rotor 3 giram
    if (r2.isAtNotch()) {
        r2.rotate();
        r3.rotate();
    } else if (r1.isAtNotch()) {
        // Se o rotor 1 atingir o entalhe, o rotor 2 gira
        r2.rotate();
    }
    // O rotor 1 (da direita) sempre gira a cada tecla
    r1.rotate();
}

```

---

## 3. Arquitetura Avançada: Padrão Command ou Stream

Para um sistema mais profissional em Java, podemos usar **Java Streams** para processar frases inteiras e o padrão **Strategy** para trocar os tipos de rotores (I, II, III, IV, V) dinamicamente.

### Exemplo de Processamento com Streams:

```java
String encrypted = input.chars()
    .filter(Character::isLetter)
    .mapToObj(c -> (char) c)
    .map(enigmaMachine::encryptChar) // O método que faz todo o trajeto físico
    .map(Object::toString)
    .collect(Collectors.joining());

```

---

## 4. O Ring Setting (Ringstellung)

Este era um ajuste adicional onde o alfabeto interno do rotor era girado *em relação* ao entalhe externo. No código, isso significa adicionar uma camada extra de deslocamento antes de processar a substituição. É aqui que a maioria das simulações amadoras falha, pois o "ponto de giro" (notch) permanece fixo na carcaça, enquanto o alfabeto interno se move.

---

## 5. Por que usar Java para quebrar a Enigma (Brute Force)?

Se você quiser criar um programa em Java para *quebrar* uma mensagem da Enigma sem saber a chave, a linguagem oferece vantagens:

* **Multithreading (ExecutorService):** A Enigma possui bilhões de combinações. Em Java, você pode dividir o espaço de busca entre os núcleos do seu processador, testando milhares de combinações de rotores em paralelo.
* **Análise de Frequência:** Você pode criar uma classe `FrequencyAnalyzer` que usa um `Map<Character, Double>` para comparar o texto decifrado com as frequências da língua alemã, identificando automaticamente quando a chave correta for encontrada.

---

## Resumo da Estrutura de Classes Sugerida

Se você fosse abrir seu IDE (como IntelliJ ou VS Code) agora, esta seria a arquitetura ideal:

1. **`EnigmaModel` (Interface):** Define os métodos básicos de criptografia.
2. **`Rotor` (Classe):** Contém o `array` de mapeamento e a lógica de rotação.
3. **`Plugboard` (Classe):** Gerencia as trocas de pares de letras.
4. **`Reflector` (Enum ou Classe):** Implementa as configurações fixas (A, B ou C).
5. **`EnigmaUI` (Main):** Uma interface (pode ser via Console) para entrada de texto.

---
