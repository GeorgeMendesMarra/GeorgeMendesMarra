# Filas (Queue) em Java para Iniciantes

## Sumário
1. [O que é uma Fila?](#1-o-que-é-uma-fila)
2. [Analogias do Mundo Real](#2-analogias-do-mundo-real)
3. [Conceito FIFO (First In, First Out)](#3-conceito-fifo-first-in-first-out)
4. [Tipos de Filas em Java](#4-tipos-de-filas-em-java)
5. [Sintaxe Básica](#5-sintaxe-básica)
6. [Operações Fundamentais](#6-operações-fundamentais)
7. [Percorrendo uma Fila](#7-percorrendo-uma-fila)
8. [LinkedList como Fila](#8-linkedlist-como-fila)
9. [PriorityQueue (Fila com Prioridade)](#9-priorityqueue-fila-com-prioridade)
10. [ArrayDeque (Fila de Alto Desempenho)](#10-arraydeque-fila-de-alto-desempenho)
11. [Exemplos Completos](#11-exemplos-completos)
12. [Comparativo: Fila vs Pilha vs Lista](#12-comparativo-fila-vs-pilha-vs-lista)
13. [Erros Comuns e Dicas](#13-erros-comuns-e-dicas)
14. [Exercícios](#14-exercícios)

---

## 1. O que é uma Fila?

Uma **fila (queue)** é uma estrutura de dados que organiza os elementos em **ordem sequencial**, onde o primeiro elemento que entra é o primeiro que sai.

### Representação Visual

```
        ┌─────────────────────────────────────────┐
        │                 FILA                     │
        │                                          │
   ENTRADA ──► ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ──► SAÍDA
   (adiciona)  │  3º │ │  2º │ │  1º │ │  0º │   (remove)
               └─────┘ └─────┘ └─────┘ └─────┘
               Último              Primeiro
               a entrar            a sair
```

---

## 2. Analogias do Mundo Real

| Analogia | Explicação |
|----------|------------|
| **Fila de banco** | A primeira pessoa que chegou é a primeira a ser atendida |
| **Fila do supermercado** | Quem chega primeiro no caixa, paga primeiro |
| **Fila do ônibus** | As primeiras pessoas a embarcar desembarcam primeiro? (não nesse caso, mas a fila é a mesma) |
| **Impressora compartilhada** | Os documentos são impressos na ordem em que foram enviados |
| **Drive-thru do McDonald's** | O primeiro carro que chega é o primeiro atendido e sai primeiro |

---

## 3. Conceito FIFO (First In, First Out)

**FIFO** = **F**irst **I**n, **F**irst **O**ut (Primeiro que entra, primeiro que sai)

```
Passo a passo visual:

1. Fila vazia:
   [ ]

2. Adicionar "João":
   [ João ]

3. Adicionar "Maria" (entra no final):
   [ João , Maria ]

4. Adicionar "Pedro" (entra no final):
   [ João , Maria , Pedro ]

5. Remover (sai o primeiro - João):
   [ Maria , Pedro ]

6. Remover (sai o primeiro - Maria):
   [ Pedro ]
```

---

## 4. Tipos de Filas em Java

| Tipo | Classe | Descrição | Quando usar |
|------|--------|-----------|-------------|
| **Fila simples** | `LinkedList` | Fila FIFO básica | Casos gerais |
| **Fila com prioridade** | `PriorityQueue` | Elementos saem por ordem de prioridade | Tarefas com urgência |
| **Fila de alto desempenho** | `ArrayDeque` | Mais rápida que LinkedList | Quando performance é crucial |
| **Fila bloqueante** | `LinkedBlockingQueue` | Para threads (programação concorrente) | Aplicações multi-thread |

> **Para iniciantes:** Comece com `LinkedList` ou `ArrayDeque`

---

## 5. Sintaxe Básica

### 5.1 Importação

```java
import java.util.Queue;        // Interface Queue
import java.util.LinkedList;   // Implementação LinkedList
import java.util.ArrayDeque;   // Implementação ArrayDeque
```

### 5.2 Declaração e Criação

```java
// Usando LinkedList
Queue<String> fila = new LinkedList<>();

// Usando ArrayDeque (recomendado para performance)
Queue<String> fila2 = new ArrayDeque<>();

// Usando PriorityQueue (fila com prioridade)
Queue<String> filaPrioridade = new PriorityQueue<>();
```

### 5.3 Tipos de dados

```java
// Fila de Strings
Queue<String> nomes = new LinkedList<>();

// Fila de Integers
Queue<Integer> numeros = new LinkedList<>();

// Fila de Doubles
Queue<Double> precos = new LinkedList<>();

// Fila de objetos personalizados
Queue<Pessoa> pessoas = new LinkedList<>();
```

---

## 6. Operações Fundamentais

### 6.1 Métodos Principais

| Método | Descrição | Retorna | Lança exceção |
|--------|-----------|---------|---------------|
| `add(elemento)` | Adiciona no final | `true` | Sim (se cheio) |
| `offer(elemento)` | Adiciona no final | `true/false` | Não |
| `remove()` | Remove e retorna o primeiro | Elemento | Sim (se vazio) |
| `poll()` | Remove e retorna o primeiro | Elemento ou `null` | Não |
| `element()` | Retorna o primeiro (sem remover) | Elemento | Sim (se vazio) |
| `peek()` | Retorna o primeiro (sem remover) | Elemento ou `null` | Não |
| `size()` | Retorna o tamanho | `int` | Não |
| `isEmpty()` | Verifica se está vazia | `boolean` | Não |
| `clear()` | Remove todos os elementos | `void` | Não |

### 6.2 Qual método usar?

| Situação | Método recomendado |
|----------|-------------------|
| **Adicionar** (certo que tem espaço) | `add()` |
| **Adicionar** (pode estar cheia) | `offer()` |
| **Remover** (certo que tem elemento) | `remove()` |
| **Remover** (pode estar vazia) | `poll()` |
| **Consultar** (certo que tem elemento) | `element()` |
| **Consultar** (pode estar vazia) | `peek()` |

> ⚠️ **Para iniciantes:** Use `offer()` para adicionar e `poll()` para remover - eles não lançam exceções e são mais seguros.

---

## 7. Exemplos Práticos dos Métodos

```java
import java.util.Queue;
import java.util.LinkedList;

public class ExemploFila {
    public static void main(String[] args) {
        // Criando a fila
        Queue<String> fila = new LinkedList<>();
        
        // 1. Adicionando elementos (offer)
        fila.offer("João");
        fila.offer("Maria");
        fila.offer("Pedro");
        fila.offer("Ana");
        
        System.out.println("Fila: " + fila);
        // Saída: Fila: [João, Maria, Pedro, Ana]
        
        // 2. Verificando o primeiro (peek)
        String primeiro = fila.peek();
        System.out.println("Primeiro da fila: " + primeiro);
        // Saída: Primeiro da fila: João
        
        // 3. Removendo o primeiro (poll)
        String atendido = fila.poll();
        System.out.println("Atendido: " + atendido);
        // Saída: Atendido: João
        
        System.out.println("Fila após atendimento: " + fila);
        // Saída: Fila após atendimento: [Maria, Pedro, Ana]
        
        // 4. Tamanho da fila
        System.out.println("Pessoas na fila: " + fila.size());
        // Saída: Pessoas na fila: 3
        
        // 5. Verificando se está vazia
        System.out.println("Fila está vazia? " + fila.isEmpty());
        // Saída: Fila está vazia? false
        
        // 6. Removendo todos
        while (!fila.isEmpty()) {
            System.out.println("Atendendo: " + fila.poll());
        }
        
        System.out.println("Fila após todos atendidos: " + fila);
        // Saída: Fila após todos atendidos: []
        System.out.println("Fila está vazia? " + fila.isEmpty());
        // Saída: Fila está vazia? true
    }
}
```

---

## 8. Percorrendo uma Fila

### 8.1 Usando `for-each` (apenas para consulta)

```java
Queue<String> fila = new LinkedList<>();
fila.offer("A");
fila.offer("B");
fila.offer("C");

// PERCORRER SEM REMOVER
for (String elemento : fila) {
    System.out.println("Elemento: " + elemento);
}
// Saída: A, B, C (ainda estão na fila)
```

### 8.2 Usando `while` com `poll()` (remove ao percorrer)

```java
Queue<String> fila = new LinkedList<>();
fila.offer("A");
fila.offer("B");
fila.offer("C");

// PERCORRER REMOVENDO (esvazia a fila)
while (!fila.isEmpty()) {
    String elemento = fila.poll();
    System.out.println("Processando: " + elemento);
}
// Saída: A, B, C
// Após isso, a fila fica vazia
```

### 8.3 Usando `iterator()` (avançado)

```java
import java.util.Iterator;

Queue<String> fila = new LinkedList<>();
fila.offer("A");
fila.offer("B");
fila.offer("C");

Iterator<String> iterator = fila.iterator();
while (iterator.hasNext()) {
    String elemento = iterator.next();
    System.out.println(elemento);
}
```

---

## 9. LinkedList como Fila

### 9.1 Por que LinkedList funciona como Fila?

A classe `LinkedList` em Java implementa a interface `Queue`, então você pode usá-la diretamente.

```java
import java.util.Queue;
import java.util.LinkedList;

public class FilaComLinkedList {
    public static void main(String[] args) {
        Queue<Integer> fila = new LinkedList<>();
        
        // Adicionando
        fila.offer(10);
        fila.offer(20);
        fila.offer(30);
        
        System.out.println("Fila: " + fila);  // [10, 20, 30]
        
        // Removendo
        System.out.println("Removeu: " + fila.poll());  // 10
        System.out.println("Fila: " + fila);  // [20, 30]
        
        // Consultando
        System.out.println("Primeiro: " + fila.peek());  // 20
    }
}
```

### 9.2 Métodos específicos da LinkedList (use com cuidado)

```java
LinkedList<String> lista = new LinkedList<>();

// Métodos de fila
lista.offer("João");    // Adiciona no final
lista.poll();           // Remove do início
lista.peek();           // Consulta o início

// Métodos de pilha (não usar numa fila!)
lista.push("Maria");    // Adiciona no início (comportamento de pilha!)
lista.pop();            // Remove do início
```

> ⚠️ **Cuidado:** Não use `push()` e `pop()` se você quer uma fila FIFO. Use sempre `offer()`, `poll()` e `peek()`.

---

## 10. PriorityQueue (Fila com Prioridade)

### 10.1 O que é?

Uma `PriorityQueue` não segue a ordem FIFO. Em vez disso, os elementos saem em **ordem de prioridade** (menor valor primeiro).

```java
import java.util.PriorityQueue;

public class FilaPrioridade {
    public static void main(String[] args) {
        PriorityQueue<Integer> fila = new PriorityQueue<>();
        
        // Adicionando fora de ordem
        fila.offer(30);
        fila.offer(10);
        fila.offer(50);
        fila.offer(20);
        fila.offer(40);
        
        System.out.println("Fila: " + fila);
        // Internamente: [10, 20, 50, 30, 40] (Heap)
        
        // Removendo - sai o MENOR primeiro
        while (!fila.isEmpty()) {
            System.out.print(fila.poll() + " ");
        }
        // Saída: 10 20 30 40 50
    }
}
```

### 10.2 Prioridade com Strings (ordem alfabética)

```java
PriorityQueue<String> fila = new PriorityQueue<>();
fila.offer("Zebra");
fila.offer("Ana");
fila.offer("Maria");
fila.offer("Bruno");

while (!fila.isEmpty()) {
    System.out.print(fila.poll() + " ");
}
// Saída: Ana Bruno Maria Zebra
```

### 10.3 Prioridade personalizada (ordem decrescente)

```java
import java.util.PriorityQueue;
import java.util.Collections;

public class FilaPrioridadeReversa {
    public static void main(String[] args) {
        // Ordem reversa (maior primeiro)
        PriorityQueue<Integer> fila = new PriorityQueue<>(Collections.reverseOrder());
        
        fila.offer(30);
        fila.offer(10);
        fila.offer(50);
        fila.offer(20);
        fila.offer(40);
        
        while (!fila.isEmpty()) {
            System.out.print(fila.poll() + " ");
        }
        // Saída: 50 40 30 20 10
    }
}
```

---

## 11. ArrayDeque (Fila de Alto Desempenho)

### 11.1 O que é?

`ArrayDeque` é mais rápido que `LinkedList` para operações de fila. Deve ser sua escolha padrão para filas.

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class FilaArrayDeque {
    public static void main(String[] args) {
        // Como fila (Queue)
        Queue<String> fila = new ArrayDeque<>();
        
        fila.offer("João");
        fila.offer("Maria");
        fila.offer("Pedro");
        
        System.out.println(fila.poll());  // João
        System.out.println(fila.peek());  // Maria
    }
}
```

### 11.2 Comparativo de Performance

| Operação | LinkedList | ArrayDeque |
|----------|------------|------------|
| `offer()` | Rápido | Muito rápido ⚡ |
| `poll()` | Rápido | Muito rápido ⚡ |
| `peek()` | Rápido | Muito rápido ⚡ |
| Memória | Mais overhead | Menos overhead |

> **Recomendação:** Use `ArrayDeque` para filas, a menos que precise de recursos específicos da `LinkedList`.

---

## 12. Exemplos Completos

### 12.1 Simulador de Fila de Banco

```java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SimuladorFilaBanco {
    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        int contadorSenha = 1;
        
        do {
            System.out.println("\n=== SIMULADOR DE FILA DE BANCO ===");
            System.out.println("1 - Retirar senha");
            System.out.println("2 - Chamar próximo");
            System.out.println("3 - Ver fila");
            System.out.println("4 - Quantos estão na fila");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // limpar buffer
            
            switch (opcao) {
                case 1:
                    String senha = "S" + contadorSenha;
                    fila.offer(senha);
                    System.out.println("Senha retirada: " + senha);
                    contadorSenha++;
                    break;
                    
                case 2:
                    if (fila.isEmpty()) {
                        System.out.println("Ninguém na fila!");
                    } else {
                        String chamado = fila.poll();
                        System.out.println("Chamando: " + chamado);
                    }
                    break;
                    
                case 3:
                    if (fila.isEmpty()) {
                        System.out.println("Fila vazia!");
                    } else {
                        System.out.println("Fila atual: " + fila);
                    }
                    break;
                    
                case 4:
                    System.out.println("Pessoas na fila: " + fila.size());
                    break;
                    
                case 5:
                    System.out.println("Encerrando...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
        
        scanner.close();
    }
}
```

### 12.2 Processamento de Tarefas com Prioridade

```java
import java.util.PriorityQueue;

public class ProcessadorTarefas {
    public static void main(String[] args) {
        // Tarefas com prioridade (número menor = maior prioridade)
        PriorityQueue<Tarefa> tarefas = new PriorityQueue<>();
        
        // Adicionando tarefas
        tarefas.offer(new Tarefa("Fazer backup", 2));
        tarefas.offer(new Tarefa("Reiniciar servidor", 1));  // Urgente!
        tarefas.offer(new Tarefa("Responder emails", 3));
        tarefas.offer(new Tarefa("Atualizar sistema", 1));   // Urgente!
        tarefas.offer(new Tarefa("Limpar logs", 4));
        
        System.out.println("=== PROCESSANDO TAREFAS ===\n");
        
        int ordem = 1;
        while (!tarefas.isEmpty()) {
            Tarefa t = tarefas.poll();
            System.out.println(ordem + "ª tarefa: " + t);
            ordem++;
        }
    }
}

class Tarefa implements Comparable<Tarefa> {
    String descricao;
    int prioridade;  // 1 = mais urgente, 5 = menos urgente
    
    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
    }
    
    @Override
    public int compareTo(Tarefa outra) {
        return Integer.compare(this.prioridade, outra.prioridade);
    }
    
    @Override
    public String toString() {
        String prioridadeTexto;
        switch (prioridade) {
            case 1: prioridadeTexto = "URGENTE"; break;
            case 2: prioridadeTexto = "ALTA"; break;
            case 3: prioridadeTexto = "MÉDIA"; break;
            default: prioridadeTexto = "BAIXA";
        }
        return descricao + " [Prioridade: " + prioridadeTexto + "]";
    }
}
```

**Saída:**
```
=== PROCESSANDO TAREFAS ===

1ª tarefa: Reiniciar servidor [Prioridade: URGENTE]
2ª tarefa: Atualizar sistema [Prioridade: URGENTE]
3ª tarefa: Fazer backup [Prioridade: ALTA]
4ª tarefa: Responder emails [Prioridade: MÉDIA]
5ª tarefa: Limpar logs [Prioridade: BAIXA]
```

### 12.3 Impressora Compartilhada (FIFO)

```java
import java.util.LinkedList;
import java.util.Queue;

public class SimuladorImpressora {
    public static void main(String[] args) {
        Queue<String> filaImpressao = new LinkedList<>();
        
        // Usuários enviando documentos
        System.out.println("Usuários enviando documentos...");
        filaImpressao.offer("João - Relatório.pdf");
        filaImpressao.offer("Maria - Planilha.xlsx");
        filaImpressao.offer("Pedro - Contrato.docx");
        filaImpressao.offer("Ana - Apresentação.ppt");
        filaImpressao.offer("Carlos - Nota fiscal.pdf");
        
        System.out.println("\n=== IMPRESSORA PROCESSANDO ===\n");
        
        int documento = 1;
        while (!filaImpressao.isEmpty()) {
            String imprimindo = filaImpressao.poll();
            System.out.println("Imprimindo documento " + documento + ": " + imprimindo);
            
            // Simulando tempo de impressão
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            documento++;
        }
        
        System.out.println("\n=== TODOS OS DOCUMENTOS FORAM IMPRESSOS ===");
        System.out.println("Total de documentos: " + (documento - 1));
    }
}
```

---

## 13. Comparativo: Fila vs Pilha vs Lista

| Característica | Fila (Queue) | Pilha (Stack) | Lista (ArrayList) |
|----------------|--------------|---------------|-------------------|
| **Ordem** | FIFO (primeiro a entrar, primeiro a sair) | LIFO (último a entrar, primeiro a sair) | Acesso por índice |
| **Inserção** | No final | No topo | Em qualquer posição |
| **Remoção** | Do início | Do topo | De qualquer posição |
| **Acesso** | Apenas ao primeiro | Apenas ao topo | Direto por índice |
| **Quando usar** | Senhas, filas de atendimento | Desfazer (Ctrl+Z), voltar em navegador | Listas com acesso aleatório |

```
Visualização das diferenças:

FILA (FIFO)         PILHA (LIFO)        LISTA (Índice)
                                        
 Entrada            Entrada              Posições:
   │                   │                  ┌─────┐
   ▼                   ▼                  │  0  │
┌─────┐             ┌─────┐               ├─────┤
│  3º │             │  3º │ ← último      │  1  │
├─────┤             ├─────┤               ├─────┤
│  2º │             │  2º │               │  2  │
├─────┤             ├─────┤               └─────┘
│  1º │ ← primeiro  │  1º │               Acesso direto
└─────┘             └─────┘               por posição
 Saída              Saída
   │                   │
   ▼                   ▼
```

---

## 14. Erros Comuns e Dicas

### Erro 1: Usar `add()` e `remove()` sem verificação

```java
// ❌ ERRADO - Pode lançar exceção
Queue<String> fila = new LinkedList<>();
fila.remove();  // Lança NoSuchElementException se vazia

// ✅ CORRETO
if (!fila.isEmpty()) {
    fila.remove();
}
// OU use poll()
String elemento = fila.poll();  // Retorna null se vazia
```

### Erro 2: Confundir fila com pilha

```java
// ❌ ERRADO - Queremos fila mas estamos usando push/pop
LinkedList<String> lista = new LinkedList<>();
lista.push("A");  // Adiciona no início (pilha!)
lista.push("B");
lista.pop();      // Remove do início

// ✅ CORRETO - Para fila FIFO
Queue<String> fila = new LinkedList<>();
fila.offer("A");  // Adiciona no final
fila.offer("B");
fila.poll();      // Remove do início
```

### Erro 3: Achar que PriorityQueue é FIFO

```java
// ⚠️ ATENÇÃO - PriorityQueue NÃO é FIFO!
PriorityQueue<Integer> fila = new PriorityQueue<>();
fila.offer(30);
fila.offer(10);
fila.offer(20);

System.out.println(fila.poll());  // Saída: 10 (não 30!)
// Ela retorna o MENOR elemento, não o primeiro que entrou
```

### Erro 4: Modificar a fila durante iteração

```java
// ❌ ERRADO - Pode causar ConcurrentModificationException
for (String s : fila) {
    if (s.equals("Maria")) {
        fila.remove(s);  // ERRO!
    }
}

// ✅ CORRETO - Use poll() em loop
while (!fila.isEmpty()) {
    String s = fila.poll();
    if (s.equals("Maria")) {
        // Processa sem problemas
    }
}
```

---

## 15. Dicas Rápidas

| Dica | Explicação |
|------|------------|
| 📌 **Escolha a implementação certa** | `ArrayDeque` para FIFO geral, `PriorityQueue` para prioridade |
| 📌 **Prefira `offer()` e `poll()`** | Não lançam exceções, código mais seguro |
| 📌 **Sempre verifique `isEmpty()`** | Antes de `peek()` ou `remove()` em código crítico |
| 📌 **Queue é interface, não classe** | Você instancia `LinkedList`, `ArrayDeque` ou `PriorityQueue` |
| 📌 **PriorityQueue usa ordem natural** | Números: menor primeiro; Strings: ordem alfabética |
| 📌 **Para inverter PriorityQueue** | Use `Collections.reverseOrder()` |

---

## 16. Resumo para Iniciantes

### Passo a passo para usar uma Fila:

```java
// 1. IMPORTAR
import java.util.Queue;
import java.util.ArrayDeque;  // ou LinkedList

// 2. DECLARAR E CRIAR
Queue<String> minhaFila = new ArrayDeque<>();

// 3. ADICIONAR
minhaFila.offer("Primeiro");
minhaFila.offer("Segundo");
minhaFila.offer("Terceiro");

// 4. CONSULTAR (vê quem é o primeiro sem remover)
String primeiro = minhaFila.peek();

// 5. REMOVER (atende o primeiro)
String atendido = minhaFila.poll();

// 6. PERCORRER (sem remover)
for (String elemento : minhaFila) {
    System.out.println(elemento);
}

// 7. PERCORRER REMOVENDO (esvazia a fila)
while (!minhaFila.isEmpty()) {
    String elemento = minhaFila.poll();
    System.out.println("Processando: " + elemento);
}
```

---

## 17. Exercícios

### Exercício 1 (Fácil)
Crie uma fila de números inteiros, adicione os números de 1 a 5 e depois remova todos exibindo cada um.

### Exercício 2 (Fácil)
Simule uma fila de supermercado onde 5 clientes entram na fila (use nomes) e depois são atendidos um a um.

### Exercício 3 (Médio)
Crie um programa que recebe nomes do usuário e os coloca em uma fila. Quando o usuário digitar "atender", o programa remove e exibe o primeiro da fila. Quando digitar "sair", o programa encerra.

### Exercício 4 (Médio)
Use `PriorityQueue` com números inteiros. Adicione 10 números aleatórios e depois remova todos. O que acontece com a ordem?

### Exercício 5 (Difícil)
Crie um sistema de atendimento com **duas filas**: 
- Fila prioritária (idosos, gestantes)
- Fila normal
- O atendimento deve priorizar a fila prioritária (atende 2 da prioritária, depois 1 da normal)

---

## 18. Próximos Passos

Depois de dominar Filas (Queue), estude:

1. **Deque (Double Ended Queue)** - Fila que permite adicionar/remover nas duas pontas
2. **BlockingQueue** - Para programação concorrente (threads)
3. **PriorityBlockingQueue** - Fila com prioridade para threads
4. **Comparators** - Para ordenações personalizadas em PriorityQueue

---
