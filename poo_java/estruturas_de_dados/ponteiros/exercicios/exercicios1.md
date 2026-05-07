# Exercícios sobre Ponteiros e Referências em Java

---

## Exercício 1 (Fácil) - Entendendo Referências vs Primitivos

O que o código abaixo imprime? Por quê?

```java
public class Exercicio1 {
    public static void main(String[] args) {
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```

**Perguntas:**
1. Qual o valor impresso para `a`?
2. Qual o valor impresso para `b`?
3. Por que `a` não mudou quando `b` foi alterado?

---

## Exercício 2 (Fácil) - Referências de Objetos

O que o código abaixo imprime? Por quê?

```java
class Pessoa {
    String nome;
}

public class Exercicio2 {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa();
        p1.nome = "João";
        
        Pessoa p2 = p1;
        p2.nome = "Maria";
        
        System.out.println("p1.nome = " + p1.nome);
        System.out.println("p2.nome = " + p2.nome);
    }
}
```

**Perguntas:**
1. Qual o valor impresso para `p1.nome`?
2. Qual o valor impresso para `p2.nome`?
3. Por que `p1.nome` mudou quando alteramos `p2.nome`?

---

## Exercício 3 (Fácil) - Passagem de Primitivos para Métodos

O que o código abaixo imprime? Por quê?

```java
public class Exercicio3 {
    public static void main(String[] args) {
        int valor = 5;
        System.out.println("Antes do método: " + valor);
        dobrar(valor);
        System.out.println("Depois do método: " + valor);
    }
    
    static void dobrar(int num) {
        num = num * 2;
        System.out.println("Dentro do método: " + num);
    }
}
```

**Perguntas:**
1. O valor de `valor` mudou após chamar `dobrar()`?
2. Por que isso acontece?

---

## Exercício 4 (Fácil) - Passagem de Objetos para Métodos

O que o código abaixo imprime? Por quê?

```java
class Contador {
    int valor;
}

public class Exercicio4 {
    public static void main(String[] args) {
        Contador c = new Contador();
        c.valor = 5;
        System.out.println("Antes do método: " + c.valor);
        incrementar(c);
        System.out.println("Depois do método: " + c.valor);
    }
    
    static void incrementar(Contador cont) {
        cont.valor = cont.valor + 1;
        System.out.println("Dentro do método: " + cont.valor);
    }
}
```

**Perguntas:**
1. O valor de `c.valor` mudou após chamar `incrementar()`?
2. Por que aqui o valor mudou, mas no Exercício 3 não?

---

## Exercício 5 (Médio) - Reatribuição de Referência

O que o código abaixo imprime? Por quê?

```java
class Carro {
    String modelo;
}

public class Exercicio5 {
    public static void main(String[] args) {
        Carro meuCarro = new Carro();
        meuCarro.modelo = "Fusca";
        
        System.out.println("Antes: " + meuCarro.modelo);
        trocarCarro(meuCarro);
        System.out.println("Depois: " + meuCarro.modelo);
    }
    
    static void trocarCarro(Carro car) {
        car = new Carro();
        car.modelo = "Ferrari";
        System.out.println("Dentro do método: " + car.modelo);
    }
}
```

**Perguntas:**
1. Dentro do método, qual modelo foi impresso?
2. Depois do método, o `meuCarro` virou uma Ferrari?
3. Por que o objeto original não foi alterado?

---

## Exercício 6 (Médio) - Simulando Ponteiro com Array

Complete o código abaixo para que o método `mudarValor` consiga alterar o valor original da variável `numero`, usando um array de 1 elemento como "ponteiro".

```java
public class Exercicio6 {
    public static void main(String[] args) {
        // Crie um array de 1 elemento para simular um ponteiro
        int[] ptr = new int[1];
        ptr[0] = 10;
        
        System.out.println("Antes: " + ptr[0]);  // Deve imprimir 10
        mudarValor(ptr);
        System.out.println("Depois: " + ptr[0]); // Deve imprimir 20
    }
    
    static void mudarValor(/* complete aqui */) {
        // Complete para alterar o valor para 20
    }
}
```

---

## Exercício 7 (Médio) - Criando Classe Wrapper

Crie uma classe `IntWrapper` que encapsula um valor `int` e permite que ele seja alterado através de métodos. Depois, implemente um método `trocar` que recebe dois `IntWrapper` e troca seus valores.

```java
// Crie a classe IntWrapper aqui

public class Exercicio7 {
    public static void main(String[] args) {
        IntWrapper a = new IntWrapper(10);
        IntWrapper b = new IntWrapper(20);
        
        System.out.println("Antes: a=" + a.valor + ", b=" + b.valor);
        trocar(a, b);
        System.out.println("Depois: a=" + a.valor + ", b=" + b.valor);
        // Deve imprimir: a=20, b=10
    }
    
    static void trocar(IntWrapper x, IntWrapper y) {
        // Implemente a troca dos valores
    }
}
```

---

## Exercício 8 (Médio) - Lista Encadeada Simples

Implemente uma lista encadeada simples onde cada nó tem um valor e uma referência para o próximo nó (simulando ponteiro). Adicione métodos para:
- Inserir no início
- Inserir no final
- Exibir todos os elementos
- Remover um elemento

```java
class No {
    int valor;
    No proximo;  // Isso é um "ponteiro" em Java!
    
    No(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class Exercicio8 {
    private No cabeca;
    
    public void inserirInicio(int valor) {
        // Implemente
    }
    
    public void inserirFinal(int valor) {
        // Implemente
    }
    
    public void exibir() {
        // Implemente: percorrer e imprimir todos os valores
    }
    
    public void remover(int valor) {
        // Implemente: remover o primeiro nó com o valor especificado
    }
    
    public static void main(String[] args) {
        Exercicio8 lista = new Exercicio8();
        lista.inserirInicio(10);
        lista.inserirInicio(20);
        lista.inserirFinal(30);
        lista.exibir();  // Deve imprimir: 20 10 30
        lista.remover(10);
        lista.exibir();  // Deve imprimir: 20 30
    }
}
```

---

## Exercício 9 (Difícil) - Simulando Ponteiro para Ponteiro

Implemente uma função que insere um elemento no início de uma lista encadeada, mas usando um **array de referências** para simular "ponteiro para ponteiro" (algo similar a `No** cabeca` em C).

```java
class NoLista {
    int valor;
    NoLista proximo;
    
    NoLista(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class Exercicio9 {
    
    // Método que recebe um array de 1 elemento contendo a cabeça da lista
    // Isso simula "ponteiro para ponteiro"
    static void inserirInicio(NoLista[] cabeca, int valor) {
        // Implemente: criar novo nó e inseri-lo no início
    }
    
    static void exibirLista(NoLista cabeca) {
        NoLista atual = cabeca;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        NoLista[] cabeca = new NoLista[1];
        cabeca[0] = null;
        
        inserirInicio(cabeca, 10);
        inserirInicio(cabeca, 20);
        inserirInicio(cabeca, 30);
        
        exibirLista(cabeca[0]);  // Deve imprimir: 30 20 10
    }
}
```

---

## Exercício 10 (Difícil) - Sistema de Banco com Referências

Crie um sistema simples de banco onde:
- Uma `Conta` tem saldo e titular
- Um `Cliente` pode ter várias contas
- Um método `transferir(Conta origem, Conta destino, double valor)` que transfere dinheiro entre contas

O desafio é entender que `origem` e `destino` são referências para os mesmos objetos, e as alterações dentro do método afetam os objetos originais.

```java
class Conta {
    String numero;
    double saldo;
    String titular;
    
    Conta(String numero, String titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }
    
    void depositar(double valor) {
        this.saldo += valor;
    }
    
    boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}

class Cliente {
    String nome;
    List<Conta> contas;
    
    Cliente(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }
    
    void adicionarConta(Conta conta) {
        contas.add(conta);
    }
}

public class Exercicio10 {
    
    // Implemente o método transferir
    static boolean transferir(Conta origem, Conta destino, double valor) {
        // deve sacar da origem e depositar no destino
        // retorna true se a transferência foi bem-sucedida
        // return ...
    }
    
    public static void main(String[] args) {
        Cliente joao = new Cliente("João");
        Cliente maria = new Cliente("Maria");
        
        Conta contaJoao = new Conta("001", joao.nome, 1000);
        Conta contaMaria = new Conta("002", maria.nome, 500);
        
        joao.adicionarConta(contaJoao);
        maria.adicionarConta(contaMaria);
        
        System.out.println("Antes da transferência:");
        System.out.println("João: " + contaJoao.saldo);
        System.out.println("Maria: " + contaMaria.saldo);
        
        // Transferir R$ 200 de João para Maria
        boolean sucesso = transferir(contaJoao, contaMaria, 200);
        
        System.out.println("\nDepois da transferência:");
        System.out.println("João: " + contaJoao.saldo);   // Deve ser 800
        System.out.println("Maria: " + contaMaria.saldo); // Deve ser 700
        System.out.println("Transferência bem-sucedida: " + sucesso);
    }
}
```

---

## Exercício 11 (Desafio) - Árvore Binária com Referências

Implemente uma árvore binária de busca onde cada nó tem referências para os filhos esquerdo e direito (simulando ponteiros).

```java
class NoArvore {
    int valor;
    NoArvore esquerdo;
    NoArvore direito;
    
    NoArvore(int valor) {
        this.valor = valor;
        this.esquerdo = null;
        this.direito = null;
    }
}

public class Exercicio11 {
    private NoArvore raiz;
    
    public void inserir(int valor) {
        // Implemente inserção na árvore binária de busca
    }
    
    public boolean buscar(int valor) {
        // Implemente busca na árvore
        // return ...
    }
    
    public void exibirEmOrdem() {
        // Implemente percurso em ordem (esquerdo → raiz → direito)
    }
    
    public void exibirPreOrdem() {
        // Implemente percurso pré-ordem (raiz → esquerdo → direito)
    }
    
    public void exibirPosOrdem() {
        // Implemente percurso pós-ordem (esquerdo → direito → raiz)
    }
    
    public static void main(String[] args) {
        Exercicio11 arvore = new Exercicio11();
        
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);
        
        System.out.print("Em ordem: ");
        arvore.exibirEmOrdem();   // 20 30 40 50 60 70 80
        
        System.out.print("\nBuscar 40: " + arvore.buscar(40));  // true
        System.out.print("\nBuscar 100: " + arvore.buscar(100)); // false
    }
}
```

---

## Dicas para os Exercícios

| Exercício | Dicas importantes |
|-----------|-------------------|
| **1 a 5** | Entenda a diferença entre tipos primitivos (copiam valor) e tipos por referência (copiam referência) |
| **6** | O array de 1 elemento funciona como um "container" que pode ser modificado de dentro do método |
| **7** | A classe wrapper encapsula o valor e permite que ele seja alterado indiretamente |
| **8** | O campo `proximo` é o "ponteiro" em Java - use `while` para percorrer |
| **9** | O array de referências simula "ponteiro para ponteiro" (algo como `No**` em C) |
| **10** | As referências para as contas permitem alterar os objetos originais dentro do método |
| **11** | Cada nó tem dois "ponteiros" (`esquerdo` e `direito`) para os filhos |

---

## Resumo dos Conceitos Praticados

| Exercício | Conceito principal |
|-----------|-------------------|
| 1, 2 | Diferença entre primitivos e referências |
| 3 | Passagem por valor com primitivos |
| 4 | Passagem por referência (cópia da referência) com objetos |
| 5 | Reatribuição de referência não afeta o original |
| 6 | Simulando ponteiro com array |
| 7 | Criando wrapper mutável |
| 8 | Lista encadeada (uso natural de referências como ponteiros) |
| 9 | Simulando ponteiro para ponteiro |
| 10 | Sistema bancário com transferência entre objetos |
| 11 | Árvore binária (dois ponteiros por nó) |

---

Bons estudos! Se precisar das soluções dos exercícios, é só pedir.
