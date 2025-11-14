---
### Da origem nos anos 90 à era das imagens nativas e da computação em nuvem

---

# 🟩 1. **Origens da JVM (1991–1995)**

## **1.1 Projeto Green (Sun Microsystems – 1991)**

A história da JVM começa no início dos anos 1990 dentro da Sun Microsystems, com um grupo de engenheiros liderados por **James Gosling**, **Patrick Naughton** e **Mike Sheridan**.
O objetivo original não era criar uma linguagem para computadores, mas sim uma plataforma para **dispositivos embarcados**, como decodificadores de TV.

Os requisitos iniciais já exigiam:

* Portabilidade entre dispositivos.
* Segurança contra execução de código malicioso.
* Interpretação para independência da arquitetura.
* Uma máquina virtual que transformasse código portável em *bytecode*.

Essa máquina seria a semente do que depois se tornaria a JVM.

---

# 🟩 2. **Java 1.0 e a primeira JVM (1995)**

Lançado oficialmente em **23 de maio de 1995**, Java trouxe:

* Bytecode independente de hardware.
* Primeira JVM, baseada em interpretação pura.
* Segurança via sandbox (restrições rígidas no acesso à memória, disco e rede).
* Suporte ao modelo applet, embutido em navegadores.

### **Limitações da primeira JVM**

* Desempenho baixo devido à ausência de otimização.
* Simples e com GC de marcação e varredura.
* Sem *HotSpot*, JIT ou compilação adaptativa.

Apesar disso, a independência de plataforma revolucionou o mercado.

---

# 🟩 3. **Evolução dos anos 2000: HotSpot e JIT (1999–2006)**

## **3.1 Aquisição da Longview e criação da HotSpot JVM**

A Sun adquiriu os direitos da tecnologia **HotSpot** desenvolvida pela Longview Technologies.

A HotSpot introduziu:

* **Adaptive Optimization** (otimização adaptativa).
* **JIT (Just-in-Time Compiler)**.
* **Perfis de execução** coletados em tempo real.

### Como funciona o HotSpot?

1. O código começa interpretado.
2. Trechos “quentes” (executados com frequência) são detectados.
3. Esses trechos são compilados para código nativo.
4. Otimizações são aplicadas (inlining, eliminação de bounds-checks, escape analysis etc.).

Isso fez o Java competir com C/C++ em desempenho.

---

# 🟩 4. **A JVM como plataforma poliglota (2006–2011)**

Com o crescimento de linguagens JVM como:

* **Scala**
* **Groovy**
* **Clojure**
* **JRuby**
* **Jython**

A JVM passou a ser considerada uma **plataforma universal**, e não apenas o runtime da linguagem Java.

### Problema pré-2011:

Linguagens dinâmicas precisavam de *workarounds* porque o bytecode não suportava métodos resolvidos dinamicamente.

---

# 🟩 5. **Java 7 e o marco Invokedynamic (2011)**

Introdução do **bytecode `invokedynamic`**, considerado uma das maiores evoluções da história da JVM.

Benefícios:

* Execução eficiente de linguagens dinâmicas.
* Criação de sistemas de despacho dinâmico otimizados.
* Redução drástica de overhead em linguagens não Java.

Com isso, a JVM se consolidou como runtime universal.

---

# 🟩 6. **Java 8: Lambdas, Streams e Metaspace (2014)**

Java 8 adicionou recursos que modificaram profundamente a JVM:

## **6.1 Lambdas e melhorias no bytecode**

* Uso de `invokedynamic` para implementar lambdas.
* Geração de classes auxiliares em tempo de execução via `LambdaMetafactory`.
* Otimizações pesadas de inlining.

## **6.2 Metaspace substitui PermGen**

* Crescimento automático e gerenciado pelo SO.
* Melhor uso da memória nativa.
* Redução de erros clássicos como *OutOfMemoryError: PermGen space*.

---

# 🟩 7. **Modularização e o projeto Jigsaw (Java 9 – 2017)**

A JVM passou a ser **modular**, com:

* Divisão da JDK em módulos (`java.base`, `java.sql`, etc.).
* Possibilidade de criar runtimes reduzidos via **jlink**.

Benefícios:

* Portabilidade para dispositivos embarcados.
* Melhor segurança e isolamento.
* Inicialização mais rápida.

---

# 🟩 8. **Nova Era dos Garbage Collectors (2017–2024)**

A evolução do GC é a parte mais avançada da história da JVM.

## **8.1 G1GC se torna o padrão**

* GC incremental.
* Pausas previsíveis.
* Ideal para servidores grandes.

## **8.2 ZGC (Z Garbage Collector)**

* Pausas < 1 ms.
* Suporte a heaps **acima de 16 TB**.
* Operações majoritariamente concorrentes.

## **8.3 Shenandoah**

* Coalescimento paralelo.
* Pausas inferiores a 10 ms.
* Usado pela Red Hat e Amazon.

## **8.4 Epsilon GC**

* GC "no-op", usado para testes de performance.

---

# 🟩 9. **GraalVM: A segunda revolução da JVM (2017–hoje)**

A **GraalVM** mudou a percepção da JVM:

### Modos de execução:

* **JIT otimizado** usando o compilador Graal.
* **Native Image**: compila código Java para binário nativo.

### Benefícios:

* Menor tempo de inicialização.
* Menor footprint de memória.
* Ideal para microserviços e *serverless*.

### Recursos adicionais:

* Interoperabilidade polyglot (Java, JS, Ruby, R, Python, WASM).
* Suporte a Truffle para interpretadores customizados.

A GraalVM tornou-se essencial em nuvem e ambientes de altíssimo desempenho.

---

# 🟩 10. **Projeto Loom (Virtual Threads)**

Introdução das **Virtual Threads**:

* Milhões de threads simultâneos.
* Com base em continuations.
* Simplifica programação concorrente.
* Substitui modelos complexos de async/await.

Isso coloca Java como uma das melhores linguagens para servidores web modernos.

---

# 🟩 11. **Projeto Panama**

Melhora a integração com bibliotecas nativas:

* Foreign Memory Access API (substitui `Unsafe`).
* Foreign Linker API (chamada direta a funções C).
* Mais eficiência que JNI.

---

# 🟩 12. **JVMs alternativas importantes**

## **HotSpot (Oracle)**

* Padrão oficial.

## **OpenJ9 (IBM/Eclipse)**

* Baixo consumo de memória.
* Melhor tempo de inicialização.

## **Azul Zing/Zulu**

* GC C4 pauseless.
* Usado em sistemas financeiros de alta criticidade.

## **Dalvik/ART (Android)**

* Não é JVM padrão, mas derivada da arquitetura Java.
* ART usa AOT (Ahead-of-time compilation).

---

# 🟩 13. **JVM na Nuvem e Contêineres (2018–2025)**

## Otimizações específicas incluem:

* Iniciar rápido (GraalVM Native Image).
* Reduzir memória (OpenJ9).
* GC com pausas curtas (ZGC/Shenandoah).
* Monitoramento com JFR (Java Flight Recorder).

---

# 🟦 14. Linha do tempo detalhada

| Ano           | Marco                                                 |
| ------------- | ----------------------------------------------------- |
| **1991**      | Início do Projeto Green                               |
| **1995**      | Java 1.0 e primeira JVM                               |
| **1999**      | HotSpot JVM                                           |
| **2004**      | Java 5: Generics                                      |
| **2006**      | Linguagens JVM começam a crescer                      |
| **2011**      | Java 7: invokedynamic                                 |
| **2014**      | Java 8: lambdas, streams, metaspace                   |
| **2017**      | Java 9: modularização                                 |
| **2017**      | GraalVM surge como experimental                       |
| **2019**      | ZGC estabiliza                                        |
| **2021**      | Virtual Threads (Loom)                                |
| **2022–2024** | Panana estabiliza; GraalVM Native Image se populariza |

---

# 🟦 15. Conclusão

A JVM iniciou como uma máquina interpretada simples e se transformou em uma das plataformas de software mais avançadas do mundo, oferecendo:

* Alto desempenho.
* Modularidade.
* Execução poliglota.
* Imagens nativas.
* Baixas pausas de GC.
* Threads virtualizadas de escalabilidade massiva.

Ela permanece, em 2025, como uma das bases mais sólidas para *enterprise computing*, cloud-native e computação de alto desempenho.

---
