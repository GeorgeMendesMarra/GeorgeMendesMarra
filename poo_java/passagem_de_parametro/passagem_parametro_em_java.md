# Passagem de Parâmetros por Valor em Java

> **Resumo em uma frase:** em Java, **tudo é passado por valor**. O que muda é *qual* valor é copiado: nos tipos primitivos é o próprio dado; nos objetos e arrays é o **endereço (referência)** do objeto.

**Disciplina:** Programação Orientada a Objetos (Java)
**Pré-requisitos:** variáveis, tipos primitivos, métodos, arrays e classes
**Código executável:** pasta [`exemplos/`](exemplos/) — `Simples.java`, `Intermediario.java` e `Complexo.java`

---

## Sumário

1. [Teoria](#1-teoria)
2. [Prática — nível simples](#2-prática--nível-simples)
3. [Prática — nível intermediário](#3-prática--nível-intermediário)
4. [Prática — nível complexo](#4-prática--nível-complexo)
5. [Armadilhas e mitos comuns](#5-armadilhas-e-mitos-comuns)
6. [Perguntas frequentes (e de banca)](#6-perguntas-frequentes-e-de-banca)
7. [Exercícios propostos](#7-exercícios-propostos)
8. [Referências](#8-referências)

---

## 1. Teoria

### 1.1 O que é "passar um parâmetro"?

Quando chamamos um método, os valores informados na chamada (**argumentos**) são usados para inicializar as variáveis declaradas na assinatura do método (**parâmetros**).

```java
static int dobrar(int numero) {   // "numero" é o PARÂMETRO
    return numero * 2;
}

int resultado = dobrar(21);       // 21 é o ARGUMENTO
```

### 1.2 Os dois modelos clássicos

| Modelo | O que o método recebe | Consegue alterar a variável original? | Exemplos de linguagens |
|---|---|---|---|
| **Por valor** | Uma **cópia** do valor da variável | Não | Java, C |
| **Por referência** | Um **apelido (alias)** da própria variável | Sim, inclusive trocando o que ela guarda | C++ (`&`), C# (`ref`/`out`) |

**Java usa somente o primeiro modelo.** Não existe `ref`, `out` nem `&` em Java.

### 1.3 O que é copiado em cada caso

| Tipo da variável | O que a variável guarda | O que é copiado para o parâmetro |
|---|---|---|
| Primitivo (`int`, `double`, `boolean`, `char`...) | O próprio dado | O dado |
| Referência (objeto, array, `String`, `List`...) | O **endereço** de um objeto no *heap* | O **endereço** |

Consequência importante: com objetos, **duas variáveis passam a apontar para o mesmo objeto**, a original e o parâmetro. Mas são **duas variáveis diferentes**.

### 1.4 Diagrama de memória

Chamada `fazerAniversario(ana)`, em que `ana` aponta para um objeto `Pessoa`:

```
            PILHA (stack)                         HEAP
   ┌─────────────────────────────┐
   │ main()                      │            ┌────────────────────┐
   │   ana  [ 0x1A2B ] ──────────┼──────────► │ Pessoa             │
   ├─────────────────────────────┤            │   nome  = "Ana"    │
   │ fazerAniversario()          │            │   idade = 30       │
   │   p    [ 0x1A2B ] ──────────┼──────────► └────────────────────┘
   └─────────────────────────────┘
        "p" é uma CÓPIA do endereço guardado em "ana".
        Os dois apontam para o MESMO objeto.
```

Duas operações possíveis dentro do método:

| Operação | O que acontece | O chamador percebe? |
|---|---|---|
| `p.idade = 31;` | Segue o endereço e **altera o objeto** | **Sim** |
| `p = new Pessoa("Bia", 25);` | Troca o endereço guardado na **cópia** `p` | **Não** |

### 1.5 Analogia da chave

Passar um objeto para um método é como entregar a alguém uma **cópia da chave da sua casa**:

- Essa pessoa pode entrar e **mexer nos móveis** (alterar o objeto). Você verá a mudança.
- Se ela jogar a cópia fora e pegar **outra chave** (reatribuir o parâmetro), a **sua** chave continua abrindo a **sua** casa.

### 1.6 Comparação com C (ponteiros)

Quem já estudou ponteiros em C reconhece o mesmo comportamento: o ponteiro é passado **por valor**.

```c
void alterar(int *p) {
    *p = 10;      // altera o dado apontado  -> o chamador VÊ
    p = NULL;     // altera a cópia do ponteiro -> o chamador NÃO vê
}
```

Em Java, a variável de referência faz o papel desse ponteiro (sem a aritmética de ponteiros).

### 1.7 O "teste do swap"

Se a passagem fosse por referência, esta função trocaria as variáveis do chamador:

```java
static void trocar(Pessoa a, Pessoa b) {
    Pessoa temp = a;
    a = b;
    b = temp;
}
```

Em Java **não troca**, porque `a` e `b` são cópias dos endereços. Essa é a prova mais clara de que a passagem é por valor.

### 1.8 Tabela-resumo

| Situação dentro do método | Afeta a variável do chamador? |
|---|---|
| Reatribuir parâmetro primitivo (`n = 5`) | Não |
| Reatribuir parâmetro de referência (`p = new ...`) | Não |
| Alterar atributo de objeto recebido (`p.nome = "X"`) | **Sim** |
| Alterar posição de array recebido (`v[0] = 9`) | **Sim** |
| Chamar `add()` em lista recebida | **Sim** |
| "Alterar" `String` ou wrapper (`Integer`) | Não (são imutáveis) |

---

## 2. Prática — nível simples

Arquivo: [`exemplos/Simples.java`](exemplos/Simples.java) — execute com `java Simples.java`.

### Exemplo 1 — Primitivo: o método altera a cópia

```java
static void incrementar(int numero) {
    numero = numero + 10;
    System.out.println("  dentro do método: numero = " + numero);
}

int idade = 20;
incrementar(idade);
System.out.println("  fora do método:   idade = " + idade);
```

```
  dentro do método: numero = 30
  fora do método:   idade = 20
```

**Por quê?** `numero` recebeu uma cópia de `20`. Mexer na cópia não mexe em `idade`.

### Exemplo 2 — O swap que não funciona

```java
static void trocarQueNaoFunciona(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
}

int x = 1, y = 2;
trocarQueNaoFunciona(x, y);
```

```
  dentro do método: a = 2, b = 1
  fora do método:   x = 1, y = 2
```

### Exemplo 3 — `String` é imutável

```java
static void deixarMaiuscula(String texto) {
    texto = texto.toUpperCase();   // cria uma NOVA String e a atribui à cópia
}

String nome = "java";
deixarMaiuscula(nome);
```

```
  dentro do método: texto = JAVA
  fora do método:   nome = java
```

### Exemplo 4 — Wrapper `Integer` também é imutável

```java
static void somarUm(Integer valor) {
    valor = valor + 1;
}

Integer contador = 5;
somarUm(contador);
```

```
  dentro do método: valor = 6
  fora do método:   contador = 5
```

### Exemplo 5 — Array: alterar o **conteúdo** aparece fora

```java
static void zerarPrimeiro(int[] vetor) {
    vetor[0] = 0;
}

int[] numeros = {7, 8, 9};
zerarPrimeiro(numeros);
```

```
  fora do método:   numeros[0] = 0
```

### Exemplo 6 — Array: **reatribuir** a referência não aparece fora

```java
static void trocarArray(int[] vetor) {
    vetor = new int[]{99, 99, 99};
}

int[] outros = {1, 2, 3};
trocarArray(outros);
```

```
  dentro do método: vetor[0] = 99
  fora do método:   outros[0] = 1
```

> Compare os exemplos 5 e 6. É exatamente a diferença entre **alterar o objeto** e **trocar o endereço da cópia**.

### Exemplo 7 — Para "alterar" um primitivo, **retorne** o resultado

```java
static int dobrar(int numero) {
    return numero * 2;
}

int valor = 21;
valor = dobrar(valor);
```

```
  fora do método:   valor = 42
```

### Exemplo 8 — Média das notas (array como parâmetro, retorno `double`)

```java
static double calcularMedia(double[] notas) {
    double soma = 0;
    for (int i = 0; i < notas.length; i++) {
        soma += notas[i];
    }
    return soma / notas.length;
}

double[] notas = {7.5, 8.0, 9.5, 6.0};
System.out.println("  média = " + calcularMedia(notas));
```

```
  média = 7.75
```

---

## 3. Prática — nível intermediário

Arquivo: [`exemplos/Intermediario.java`](exemplos/Intermediario.java) — execute com `java Intermediario.java`.

Classe de apoio usada nos exemplos:

```java
static class Pessoa {
    String nome;
    int idade;
    Pessoa(String nome, int idade) { this.nome = nome; this.idade = idade; }
    @Override public String toString() { return nome + " (" + idade + ")"; }
}
```

### Exemplo 1 — Alterar o estado × reatribuir a referência

```java
static void fazerAniversario(Pessoa p) {
    p.idade = p.idade + 1;                  // altera o objeto
}

static void trocarPessoa(Pessoa p) {
    p = new Pessoa("Outra Pessoa", 99);     // altera só a cópia
}

Pessoa ana = new Pessoa("Ana", 30);
fazerAniversario(ana);
trocarPessoa(ana);
```

```
  após fazerAniversario: Ana (31)
  após trocarPessoa:     Ana (31)
```

### Exemplo 2 — Provando que a referência é copiada

`System.identityHashCode` identifica o objeto na memória (os números variam a cada execução; o que importa é compará-los).

```java
static void mostrarIdentidade(Pessoa p) {
    System.out.println("  parâmetro  -> objeto " + System.identityHashCode(p));
    p = new Pessoa("Novo", 1);
    System.out.println("  após new   -> objeto " + System.identityHashCode(p));
}
```

```
  variável   -> objeto 710708543
  parâmetro  -> objeto 710708543     <- o MESMO objeto
  após new   -> objeto 1965237677    <- outro objeto (só a cópia mudou)
  variável   -> objeto 710708543     <- a variável original não mudou
```

### Exemplo 3 — Coleções: `add()` altera, reatribuir não

```java
static void adicionar(List<String> lista) {
    lista.add("adicionado dentro do método");
}

static void reatribuir(List<String> lista) {
    lista = new ArrayList<>();
    lista.add("lista nova (só existe aqui dentro)");
}

List<String> itens = new ArrayList<>();
adicionar(itens);
reatribuir(itens);
System.out.println(itens);
```

```
  [adicionado dentro do método]
```

### Exemplo 4 — Um swap que funciona: trocar o **conteúdo** do array

```java
static void trocar(int[] v, int i, int j) {
    int temp = v[i];
    v[i] = v[j];
    v[j] = temp;
}

int[] v = {1, 2, 3};
trocar(v, 0, 2);
```

```
  v = [3, 2, 1]
```

> Em algoritmos de ordenação (Bubble Sort, Selection Sort...) é assim que a troca é feita.

### Exemplo 5 — `final` no parâmetro

`final` impede **reatribuir**, mas **não** impede **alterar o objeto**.

```java
static void comFinal(final Pessoa p) {
    // p = new Pessoa("X", 1);     // ERRO de compilação
    p.nome = "Alterado mesmo com final";
}
```

```
  Alterado mesmo com final (40)
```

### Exemplo 6 — Matriz (array de arrays)

```java
static void zerarDiagonal(int[][] matriz) {
    for (int i = 0; i < matriz.length; i++) {
        matriz[i][i] = 0;
    }
}

int[][] m = {{1, 2}, {3, 4}};
zerarDiagonal(m);
```

```
  [0,2] [3,0]
```

### Exemplo 7 — Cópia defensiva

Quando o método **não deve** alterar o original, trabalhe com uma cópia.

```java
static int somaSemRisco(int[] original) {
    int[] copia = original.clone();
    copia[0] = 1000;                 // altera só a cópia
    int soma = 0;
    for (int n : copia) soma += n;
    return soma;
}

static List<String> protegida(List<String> original) {
    return List.copyOf(original);    // lista imutável e independente
}
```

```
  soma na cópia = 1005
  dados[0] original = 1
  base = [a, b, c] | cópia = [a, b]
```

### Exemplo 8 — `record` é imutável: "alterar" é criar outro e retornar

```java
record Ponto(int x, int y) { }

static Ponto moverDireita(Ponto p) {
    return new Ponto(p.x() + 1, p.y());
}

Ponto p = new Ponto(0, 0);
moverDireita(p);              // retorno ignorado
p = moverDireita(p);          // retorno usado
```

```
  sem usar o retorno: Ponto[x=0, y=0]
  usando o retorno:   Ponto[x=1, y=0]
```

---

## 4. Prática — nível complexo

Arquivo: [`exemplos/Complexo.java`](exemplos/Complexo.java) — execute com `java Complexo.java`.

### Exemplo 1 — Lista encadeada: por que a inserção **retorna a cabeça**

```java
static class No {
    int valor;
    No proximo;
    No(int valor) { this.valor = valor; }
}

// ERRADO: reatribui a cópia local de "cabeca"
static void inserirNoInicioErrado(No cabeca, int valor) {
    No novo = new No(valor);
    novo.proximo = cabeca;
    cabeca = novo;                 // o chamador não vê
}

// CERTO: devolve a nova cabeça
static No inserirNoInicio(No cabeca, int valor) {
    No novo = new No(valor);
    novo.proximo = cabeca;
    return novo;
}

// Inserir no fim altera o objeto (proximo), mas precisa retornar
// quando a lista está vazia (cabeça nula)
static No inserirNoFim(No cabeca, int valor) {
    No novo = new No(valor);
    if (cabeca == null) return novo;
    No atual = cabeca;
    while (atual.proximo != null) atual = atual.proximo;
    atual.proximo = novo;
    return cabeca;
}
```

```java
No lista = null;
inserirNoInicioErrado(lista, 1);              // não funciona
lista = inserirNoInicio(lista, 2);
lista = inserirNoInicio(lista, 1);
lista = inserirNoFim(lista, 3);
```

```
  após método errado:  (vazia)
  após método correto: 1 -> 2 -> 3
```

**Por quê?** `cabeca` é uma cópia do endereço. Trocar o endereço da cópia não muda a variável `lista` do chamador. Esse é o erro mais comum de quem implementa estruturas de dados em Java.

### Exemplo 2 — Árvore binária de busca com inserção recursiva

```java
static NoArvore inserir(NoArvore raiz, int valor) {
    if (raiz == null) return new NoArvore(valor);
    if (valor < raiz.valor) raiz.esquerda = inserir(raiz.esquerda, valor);
    else                    raiz.direita  = inserir(raiz.direita, valor);
    return raiz;
}
```

```
  em ordem: 20 30 40 50 60 70 80
```

Cada chamada recursiva recebe **uma cópia** da referência e devolve a raiz da subárvore; quem chamou **atribui** o retorno ao campo correspondente. É o mesmo padrão do exemplo anterior.

### Exemplo 3 — Cópia rasa × cópia profunda

```java
Pedido copiaRasa() {                 // nova lista, MESMOS itens
    Pedido c = new Pedido(cliente);
    c.itens = new ArrayList<>(itens);
    return c;
}

Pedido copiaProfunda() {             // nova lista e NOVOS itens
    Pedido c = new Pedido(cliente);
    for (Item i : itens) c.itens.add(new Item(i.descricao));
    return c;
}

static void marcarComoEntregue(Pedido p) {
    p.itens.get(0).descricao += " [ENTREGUE]";
}
```

```
  original: Notebook [ENTREGUE]
  rasa:     Notebook [ENTREGUE]
  profunda: Notebook
```

A cópia rasa compartilha os objetos `Item` com o original; alterar um afeta o outro. A cópia profunda isola completamente.

### Exemplo 4 — Simulando "parâmetro de saída"

Java não tem `out`/`ref`. Há duas saídas comuns.

**Opção A — caixa mutável (`AtomicInteger`)**

```java
static void contarPares(int[] numeros, AtomicInteger resultado) {
    int total = 0;
    for (int n : numeros) if (n % 2 == 0) total++;
    resultado.set(total);            // altera o objeto, que o chamador enxerga
}
```

**Opção B — retornar um `record` com vários valores (preferível)**

```java
record Estatisticas(int minimo, int maximo, double media) { }

static Estatisticas calcular(int[] numeros) { /* ... */ }
```

```
  pares = 3
  Estatisticas[minimo=3, maximo=10, media=6.4]
```

### Exemplo 5 — Lambdas e variáveis capturadas

A lambda captura uma **cópia** do valor, por isso a variável precisa ser *efetivamente final*.

```java
String prefixo = "Olá, ";
executar(nome -> System.out.println("  " + prefixo + nome));
// prefixo = "Oi, ";   // ERRO: a lambda exige variável efetivamente final
```

```
  Olá, Java
```

### Exemplo 6 — Varargs

O parâmetro `int... valores` chega ao método como um **array**.

```java
static int somar(int... valores) {
    valores[0] = 0;                  // altera o array recebido
    int soma = 0;
    for (int v : valores) soma += v;
    return soma;
}

int[] vetor = {1, 2, 3};
somar(vetor);                        // array passado diretamente: é alterado
somar(1, 2, 3);                      // o compilador cria um array novo
```

```
  soma = 5
  vetor[0] = 0  (o array foi passado direto)
  soma = 5  (array criado na chamada)
```

---

## 5. Armadilhas e mitos comuns

| Mito | Realidade |
|---|---|
| "Objetos são passados por referência." | São passadas **cópias da referência**. Reatribuir o parâmetro não afeta o chamador. |
| "Primitivos por valor e objetos por referência." | **Tudo** é por valor. Só muda o que a variável guarda. |
| "`final` torna o objeto imutável." | `final` impede reatribuir a variável, não alterar o objeto. |
| "`String` é passada por referência, então muda fora." | `String` é **imutável**; qualquer "alteração" cria outra `String`. |
| "Se alterei o array dentro, é porque Java passa por referência." | Você alterou o objeto apontado; o endereço continua sendo cópia. |
| "Posso fazer um `swap(a, b)` com objetos." | Não. Troque o **conteúdo** (atributos ou posições) ou retorne os valores. |

---

## 6. Perguntas frequentes (e de banca)

**1. Java passa objetos por valor ou por referência?**
Por valor. O valor passado é a referência ao objeto; o método recebe uma cópia dela.

**2. Então por que consigo alterar um objeto dentro do método?**
Porque a cópia da referência aponta para o mesmo objeto do chamador. Você altera o objeto, não a variável do chamador.

**3. Como provar que não é por referência?**
Com o teste do swap (seção 1.7) ou reatribuindo o parâmetro (`p = new ...`): o chamador não percebe.

**4. Qual a diferença entre alterar o objeto e reatribuir o parâmetro?**
Alterar o objeto (`p.idade = 31`) segue o endereço e muda o dado no heap. Reatribuir (`p = new ...`) troca apenas o endereço guardado na cópia local.

**5. Por que `String` parece não mudar dentro do método?**
Porque é imutável: métodos como `toUpperCase()` retornam uma nova `String`, e a atribuição acontece só na cópia local.

**6. Como devolver mais de um resultado?**
Retornando um objeto (por exemplo, um `record`), um array, ou passando um objeto mutável que o método preencha.

**7. Qual a relação com ponteiros em C?**
A variável de referência em Java é semelhante a um ponteiro passado por valor: dá para alterar o dado apontado, mas não o ponteiro do chamador.

**Resposta-modelo (30 segundos):**
> "Java passa sempre uma cópia do valor da variável. Para primitivos, esse valor é o dado; para objetos e arrays, é a referência. Por isso o método consegue modificar o conteúdo do objeto, mas não consegue trocar qual objeto a variável original aponta."

---

## 7. Exercícios propostos

**Nível simples**
1. Escreva `triplicar(int n)` e mostre que o original não muda. Em seguida, corrija usando `return`.
2. Escreva `preencher(int[] v, int valor)` que preenche todo o array. Verifique o efeito no `main`.
3. Escreva `substituir(int[] v)` que faz `v = new int[10]`. Explique por que o `main` não enxerga o novo array.

**Nível intermediário**
4. Crie a classe `ContaBancaria` e o método `depositar(ContaBancaria c, double valor)`. Depois escreva `resetar(ContaBancaria c)` que faz `c = new ContaBancaria()` e explique o resultado.
5. Implemente `trocarNomes(Pessoa a, Pessoa b)` que **funcione**, trocando os atributos `nome`.
6. Escreva um método que receba `List<Integer>` e retorne **uma nova lista** com os dobros, sem alterar a original.

**Nível complexo**
7. Implemente `remover(No cabeca, int valor)` em uma lista encadeada e explique por que o método precisa **retornar** a cabeça.
8. Implemente `copiaProfunda()` para uma classe `Turma` que contém `List<Aluno>`.
9. Escreva uma função que retorne mínimo, máximo e média usando `record`, e outra versão usando `AtomicInteger`/`AtomicReference` como parâmetros de saída. Compare.
10. Dado o trecho abaixo, **preveja** a saída antes de executar:

```java
static void f(int[] a, int[] b) {
    a[0] = 10;
    a = b;
    a[1] = 20;
}

int[] x = {1, 2};
int[] y = {3, 4};
f(x, y);
System.out.println(x[0] + " " + x[1] + " " + y[0] + " " + y[1]);
```

<details>
<summary>Resposta do exercício 10</summary>

Saída: `10 2 3 20`.
`a[0] = 10` altera o array de `x`. Depois `a = b` faz a cópia local apontar para o array de `y`, então `a[1] = 20` altera o array de `y`.

</details>

---

## 8. Referências

- ORACLE. **The Java Language Specification** — §8.4.1 (Formal Parameters) e §15.12.4 (Run-Time Evaluation of Method Invocation). Disponível em: <https://docs.oracle.com/javase/specs/>.
- ORACLE. **The Java Tutorials** — *Passing Information to a Method or a Constructor*. Disponível em: <https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html>.
- HORSTMANN, Cay S. **Core Java** (Volume I – Fundamentos).
- DEITEL, Paul; DEITEL, Harvey. **Java: Como Programar**.
- SIERRA, Kathy; BATES, Bert. **Head First Java**.

---

## Como executar os exemplos

Requer JDK 17 ou superior (usa `record`).

```bash
cd poo_java/passagem_de_parametros/exemplos
java Simples.java
java Intermediario.java
java Complexo.java
```

Se os acentos aparecerem como `?` no terminal, execute com:

```bash
java -Dstdout.encoding=UTF-8 Simples.java
```
