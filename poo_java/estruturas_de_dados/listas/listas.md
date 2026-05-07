# Listas em Java para Iniciantes

## Sumário
1. [O que são Listas?](#1-o-que-são-listas)
2. [Array vs ArrayList - A Diferença Crucial](#2-array-vs-arraylist---a-diferença-crucial)
3. [Sintaxe Básica](#3-sintaxe-básica)
4. [Operações Fundamentais (CRUD)](#4-operações-fundamentais-crud)
5. [Percorrendo Listas](#5-percorrendo-listas)
6. [Tipos de Listas](#6-tipos-de-listas)
7. [Métodos Úteis](#7-métodos-úteis)
8. [Exemplos Completos](#8-exemplos-completos)
9. [Exercícios Práticos](#9-exercícios-práticos)

---

## 1. O que são Listas?

### 1.1 Conceito Simples

Uma **lista** é como uma **caixa organizada** onde você pode guardar vários itens em sequência.

```
Lista de compras:
┌─────────┐
│   1. Arroz     │
│   2. Feijão    │
│   3. Macarrão  │
│   4. Tomate    │
└─────────┘
```

### 1.2 Analogia do Mundo Real

| Analogia | Explicação |
|----------|------------|
| **Lista de compras** | Você adiciona itens, remove, consulta o que tem |
| **Fila do banco** | O primeiro que entra é o primeiro que sai |
| **Pilha de pratos** | O último que entra é o primeiro que sai |
| **Álbum de fotos** | Você acessa qualquer foto pela posição |

---

## 2. Array vs ArrayList - A Diferença Crucial

### 2.1 Array (Vetor Tradicional)

O **array** é uma estrutura de tamanho **fixo**. Como uma estante com espaços pré-definidos.

```java
// Array - tamanho FIXO
int[] numeros = new int[5];  // Só cabe 5 números

numeros[0] = 10;
numeros[1] = 20;
numeros[2] = 30;
numeros[3] = 40;
numeros[4] = 50;
// numeros[5] = 60;  // ERRO! Não cabe mais
```

**Problemas do Array:**
- Tamanho definido na criação, não muda
- Se quiser adicionar um 6º elemento, não pode
- Dificuldade para remover itens do meio

### 2.2 ArrayList (Lista Dinâmica)

O **ArrayList** é uma lista que **cresce e diminui** automaticamente.

```java
// ArrayList - tamanho DINÂMICO
ArrayList<Integer> numeros = new ArrayList<>();

numeros.add(10);   // Adiciona
numeros.add(20);   // Adiciona
numeros.add(30);   // Adiciona
numeros.add(40);   // Adiciona
numeros.add(50);   // Adiciona
numeros.add(60);   // Adiciona SEM erro!
```

### 2.3 Quadro Comparativo

| Característica | Array | ArrayList |
|----------------|-------|-----------|
| **Tamanho** | Fixo | Dinâmico |
| **Adicionar** | índice fixo | `add()` cresce automaticamente |
| **Remover** | Não pode | `remove()` remove e reorganiza |
| **Tipos** | Primitivos (`int`, `double`) | Objetos (`Integer`, `Double`) |
| **Velocidade** | Muito rápida | Rápida (ligeiramente mais lenta) |

---

## 3. Sintaxe Básica

### 3.1 Importação

Antes de usar ArrayList, você precisa importá-lo:

```java
import java.util.ArrayList;  // Coloque no topo do arquivo
```

### 3.2 Declaração e Criação

```java
// Sintaxe completa
ArrayList<Tipo> nomeDaLista = new ArrayList<Tipo>();

// Exemplos:
ArrayList<String> nomes = new ArrayList<String>();
ArrayList<Integer> idades = new ArrayList<Integer>();
ArrayList<Double> alturas = new ArrayList<Double>();
```

### 3.3 Tipos Especiais

| Tipo primitivo | Tipo para Lista |
|----------------|-----------------|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |
| `float` | `Float` |
| `long` | `Long` |

**Por quê?** ArrayList só aceita **objetos**, não tipos primitivos.

```java
// ERRADO ❌
ArrayList<int> idades = new ArrayList<int>();

// CORRETO ✅
ArrayList<Integer> idades = new ArrayList<Integer>();
```

### 3.4 Simplificando (Java 7+)

A partir do Java 7, você pode simplificar:

```java
ArrayList<String> nomes = new ArrayList<>();  // O tipo entre <> é opcional
```

---

## 4. Operações Fundamentais (CRUD)

CRUD = **C**reate (Criar), **R**ead (Ler), **U**pdate (Atualizar), **D**elete (Deletar)

### 4.1 Adicionar (Create)

```java
ArrayList<String> frutas = new ArrayList<>();

// Adicionar no final
frutas.add("Maçã");
frutas.add("Banana");
frutas.add("Laranja");

// Adicionar em posição específica (índice, elemento)
frutas.add(1, "Uva");  // Insere na posição 1 (entre Maçã e Banana)

System.out.println(frutas);
// Saída: [Maçã, Uva, Banana, Laranja]
```

### 4.2 Acessar (Read)

```java
ArrayList<String> frutas = new ArrayList<>();
frutas.add("Maçã");
frutas.add("Banana");
frutas.add("Laranja");

// Acessar por índice (começa em 0)
String primeiraFruta = frutas.get(0);  // "Maçã"
String segundaFruta = frutas.get(1);   // "Banana"
String terceiraFruta = frutas.get(2);  // "Laranja"

System.out.println("Primeira: " + primeiraFruta);
```

### 4.3 Atualizar (Update)

```java
ArrayList<String> frutas = new ArrayList<>();
frutas.add("Maçã");
frutas.add("Banana");
frutas.add("Laranja");

// Substituir elemento na posição 1
frutas.set(1, "Morango");  // "Banana" vira "Morango"

System.out.println(frutas);
// Saída: [Maçã, Morango, Laranja]
```

### 4.4 Remover (Delete)

```java
ArrayList<String> frutas = new ArrayList<>();
frutas.add("Maçã");
frutas.add("Banana");
frutas.add("Laranja");

// Remover por índice
frutas.remove(1);  // Remove "Banana"

// Remover por elemento (primeira ocorrência)
frutas.remove("Laranja");

System.out.println(frutas);
// Saída: [Maçã]
```

### 4.5 Limpar tudo

```java
frutas.clear();  // Remove todos os elementos
```

---

## 5. Percorrendo Listas

### 5.1 Usando `for` tradicional (com índice)

```java
ArrayList<String> cores = new ArrayList<>();
cores.add("Vermelho");
cores.add("Azul");
cores.add("Verde");

for (int i = 0; i < cores.size(); i++) {
    System.out.println("Posição " + i + ": " + cores.get(i));
}
```

**Saída:**
```
Posição 0: Vermelho
Posição 1: Azul
Posição 2: Verde
```

### 5.2 Usando `for-each` (recomendado para iniciantes)

```java
ArrayList<String> cores = new ArrayList<>();
cores.add("Vermelho");
cores.add("Azul");
cores.add("Verde");

for (String cor : cores) {
    System.out.println(cor);
}
```

**Saída:**
```
Vermelho
Azul
Verde
```

### 5.3 Usando `forEach` com lambda (Java 8+)

```java
cores.forEach(cor -> System.out.println(cor));
```

---

## 6. Tipos de Listas

### 6.1 ArrayList (A mais usada)

```java
ArrayList<String> lista = new ArrayList<>();
lista.add("Item");
```

**Características:**
- Acesso rápido por índice (get/set)
- Adicionar/remover no final é rápido
- Adicionar/remover no meio é mais lento

### 6.2 LinkedList

```java
import java.util.LinkedList;

LinkedList<String> lista = new LinkedList<>();
lista.add("Item");
```

**Características:**
- Adicionar/remover no início é rápido
- Acesso por índice é mais lento
- Pode ser usada como fila ou pilha

### 6.3 Comparativo

| Operação | ArrayList | LinkedList |
|----------|-----------|------------|
| Acessar elemento (`get`) | Muito rápido ⚡ | Lento 🐢 |
| Adicionar no final | Rápido ⚡ | Rápido ⚡ |
| Adicionar no início | Lento 🐢 | Muito rápido ⚡ |
| Remover do meio | Lento 🐢 | Médio |

**Para iniciantes:** Use **ArrayList** em 90% dos casos.

---

## 7. Métodos Úteis

### 7.1 Métodos Essenciais

| Método | O que faz | Exemplo |
|--------|-----------|---------|
| `add(elemento)` | Adiciona no final | `lista.add("João")` |
| `add(posição, elemento)` | Adiciona em posição | `lista.add(0, "João")` |
| `get(posição)` | Pega elemento | `String nome = lista.get(0)` |
| `set(posição, elemento)` | Substitui | `lista.set(0, "Maria")` |
| `remove(posição)` | Remove por índice | `lista.remove(0)` |
| `remove(elemento)` | Remove por valor | `lista.remove("João")` |
| `size()` | Tamanho da lista | `int total = lista.size()` |
| `clear()` | Remove tudo | `lista.clear()` |
| `contains(elemento)` | Verifica se existe | `boolean existe = lista.contains("João")` |
| `indexOf(elemento)` | Posição do elemento | `int pos = lista.indexOf("João")` |
| `isEmpty()` | Verifica se está vazia | `boolean vazia = lista.isEmpty()` |

### 7.2 Exemplos dos métodos úteis

```java
import java.util.ArrayList;

public class ExemploMetodos {
    public static void main(String[] args) {
        ArrayList<String> cidades = new ArrayList<>();
        
        // Adicionando
        cidades.add("Goiânia");
        cidades.add("Anápolis");
        cidades.add("Brasília");
        
        // Verificando tamanho
        System.out.println("Tamanho: " + cidades.size());  // 3
        
        // Verificando se contém
        System.out.println("Tem Goiânia? " + cidades.contains("Goiânia"));  // true
        System.out.println("Tem Rio? " + cidades.contains("Rio"));  // false
        
        // Encontrando posição
        System.out.println("Posição de Anápolis: " + cidades.indexOf("Anápolis"));  // 1
        
        // Verificando se está vazia
        System.out.println("Está vazia? " + cidades.isEmpty());  // false
        
        // Limpando
        cidades.clear();
        System.out.println("Após clear, tamanho: " + cidades.size());  // 0
        System.out.println("Está vazia? " + cidades.isEmpty());  // true
    }
}
```

---

## 8. Exemplos Completos

### 8.1 Lista de Tarefas (To-Do List)

```java
import java.util.ArrayList;
import java.util.Scanner;

public class ListaTarefas {
    public static void main(String[] args) {
        ArrayList<String> tarefas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n--- MINHA LISTA DE TAREFAS ---");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Remover tarefa");
            System.out.println("3 - Ver todas as tarefas");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // limpar buffer
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite a tarefa: ");
                    String novaTarefa = scanner.nextLine();
                    tarefas.add(novaTarefa);
                    System.out.println("Tarefa adicionada!");
                    break;
                    
                case 2:
                    System.out.print("Número da tarefa a remover: ");
                    int indice = scanner.nextInt();
                    if (indice >= 1 && indice <= tarefas.size()) {
                        String removida = tarefas.remove(indice - 1);
                        System.out.println("Removida: " + removida);
                    } else {
                        System.out.println("Número inválido!");
                    }
                    break;
                    
                case 3:
                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada!");
                    } else {
                        System.out.println("\n--- SUAS TAREFAS ---");
                        for (int i = 0; i < tarefas.size(); i++) {
                            System.out.println((i + 1) + " - " + tarefas.get(i));
                        }
                    }
                    break;
                    
                case 4:
                    System.out.println("Até logo!");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
        
        scanner.close();
    }
}
```

### 8.2 Cadastro de Alunos com Notas

```java
import java.util.ArrayList;

public class CadastroAlunos {
    public static void main(String[] args) {
        // Criando listas
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Double> notas = new ArrayList<>();
        
        // Cadastrando alunos
        nomes.add("Ana Paula");
        notas.add(8.5);
        
        nomes.add("Bruno Silva");
        notas.add(7.0);
        
        nomes.add("Carla Mendes");
        notas.add(9.5);
        
        nomes.add("Daniel Souza");
        notas.add(6.5);
        
        // Exibindo relatório
        System.out.println("=== BOLETIM ESCOLAR ===");
        double soma = 0;
        
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println("Aluno: " + nomes.get(i));
            System.out.println("Nota: " + notas.get(i));
            
            // Situação
            if (notas.get(i) >= 7.0) {
                System.out.println("Situação: APROVADO");
            } else {
                System.out.println("Situação: RECUPERAÇÃO");
            }
            System.out.println("------------------------");
            
            soma += notas.get(i);
        }
        
        double media = soma / notas.size();
        System.out.println("Média da turma: " + String.format("%.2f", media));
    }
}
```

### 8.3 Removendo elementos durante iteração (cuidado!)

```java
import java.util.ArrayList;

public class RemovendoSeguro {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(10);
        numeros.add(20);
        numeros.add(30);
        numeros.add(40);
        numeros.add(50);
        
        System.out.println("Lista original: " + numeros);
        
        // Remover números pares (forma SEGURA)
        // Usar for com índice indo de trás para frente
        for (int i = numeros.size() - 1; i >= 0; i--) {
            if (numeros.get(i) % 2 == 0) {  // se for par
                numeros.remove(i);
            }
        }
        
        System.out.println("Após remover pares: " + numeros);
        
        // ⚠️ ATENÇÃO: Não faça assim!
        // for (int i = 0; i < numeros.size(); i++) {
        //     if (numeros.get(i) % 2 == 0) {
        //         numeros.remove(i);  // PULA elementos!
        //     }
        // }
    }
}
```

---

## 9. Exercícios Práticos

### Exercício 1 (Fácil)
Crie uma lista de 5 cores e exiba todas usando for-each.

### Exercício 2 (Fácil)
Crie um programa que adiciona números de 1 a 10 em uma lista e depois calcula a soma.

### Exercício 3 (Médio)
Faça um programa que recebe nomes do usuário até que ele digite "fim". Depois exiba todos os nomes.

### Exercício 4 (Médio)
Crie uma lista de números e remova todos os números menores que 10.

### Exercício 5 (Difícil)
Faça um cadastro de produtos com nome e preço. Depois calcule o valor total do carrinho.

---

## 10. Resumo para Iniciantes

### Passo a passo para usar ArrayList:

```java
// 1. IMPORTAR
import java.util.ArrayList;

// 2. DECLARAR E CRIAR
ArrayList<String> minhaLista = new ArrayList<>();

// 3. ADICIONAR
minhaLista.add("Primeiro item");

// 4. ACESSAR
String item = minhaLista.get(0);

// 5. PERCORRER
for (String elemento : minhaLista) {
    System.out.println(elemento);
}

// 6. REMOVER
minhaLista.remove(0);
```

### Dicas importantes:

| Dica | Explicação |
|------|------------|
| 📌 **Índices começam em 0** | O primeiro elemento está na posição 0 |
| 📌 **Use `size()`** | Para saber quantos elementos tem |
| 📌 **Use `isEmpty()`** | Para verificar se está vazia |
| 📌 **Cuidado ao remover** | Remover durante iteração pode pular elementos |
| 📌 **ArrayList é o mais comum** | Use ArrayList a menos que precise de algo específico |

### Erros comuns de iniciantes:

```java
// ❌ ERRADO: Esquecer import
ArrayList<String> lista = new ArrayList<>();  // ERRO!

// ✅ CORRETO:
import java.util.ArrayList;
ArrayList<String> lista = new ArrayList<>();

// ❌ ERRADO: Usar tipo primitivo
ArrayList<int> numeros = new ArrayList<>();  // ERRO!

// ✅ CORRETO:
ArrayList<Integer> numeros = new ArrayList<>();

// ❌ ERRADO: Acessar índice inexistente
ArrayList<String> lista = new ArrayList<>();
String item = lista.get(0);  // ERRO! lista está vazia!

// ✅ CORRETO:
if (!lista.isEmpty()) {
    String item = lista.get(0);
}
```

---

## 11. Próximos Passos

Depois de dominar ArrayList, estude:

1. **LinkedList** - Para quando precisar adicionar/remover do início
2. **HashMap** - Para armazenar pares (chave, valor)
3. **HashSet** - Para listas sem elementos duplicados
4. **Ordenação** - `Collections.sort(lista)`
5. **Streams** - Para operações avançadas (filter, map, reduce)

---

Este guia cobre tudo que você precisa para começar com listas em Java. Pratique os exercícios e, se tiver dúvidas, volte a este material!
