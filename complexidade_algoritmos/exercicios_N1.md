# 🧠 Questionário — Complexidade de Algoritmos

---

## 📘 Versão Fácil

**Objetivo:** Avaliar a compreensão dos conceitos básicos de complexidade de algoritmos, notações e exemplos simples.

1. O que significa a palavra **complexidade** quando falamos de um algoritmo?  
2. Qual é a diferença entre **tempo de execução** e **uso de memória** em um algoritmo?  
3. Para que serve a **notação Big O (O-grande)**?  
4. O que representa o termo **n** dentro da notação O(n)?  
5. Cite um exemplo de algoritmo com complexidade **O(1)**.  
6. Cite um exemplo de algoritmo com complexidade **O(n)**.  
7. Qual a complexidade de tempo do algoritmo de **Bubble Sort**?  
8. Qual a complexidade de tempo do algoritmo de **Busca Linear**?  
9. O que significa dizer que um algoritmo tem complexidade **O(log n)**?  
10. Qual a diferença entre **melhor caso** e **pior caso** de um algoritmo?  
11. Em qual tipo de problema geralmente encontramos complexidades **exponenciais (O(2ⁿ))**?  
12. O que é **complexidade polinomial**?  
13. Qual a complexidade do **Merge Sort** no pior caso?  
14. Em um algoritmo que possui **dois loops for aninhados**, qual costuma ser sua complexidade?  
15. Por que os programadores buscam algoritmos com menor complexidade?  
16. Um algoritmo que analisa cada elemento de uma lista apenas uma vez é O(n). Isso é considerado eficiente?  
17. A notação **Θ (teta)** indica o quê em relação ao crescimento da função?  
18. Qual é a complexidade de tempo de um algoritmo que executa **três loops aninhados** sobre *n* elementos?  
19. O que significa dizer que um algoritmo é **in-place**?  
20. Cite um exemplo de **estrutura de dados** que pode melhorar a eficiência de um algoritmo.

---

## 🔬 Versão Difícil

**Objetivo:** Avaliar a aplicação prática e análise crítica de complexidades, relações de recorrência e otimização.

1. Explique por que analisamos a complexidade de tempo **em função do tamanho da entrada (n)**.  
2. Dê um exemplo de algoritmo cuja complexidade muda dependendo da escolha da estrutura de dados.  
3. Mostre como se calcula a complexidade assintótica da função **f(n) = 4n² + 3n + 10**.  
4. Prove que **O(n² + n) = O(n²)**.  
5. Qual é a diferença entre as notações **O(n)** e **Ω(n)** em termos de limites de crescimento?  
6. Escreva a relação de recorrência do algoritmo **Merge Sort** e resolva-a usando o **Teorema Mestre**.  
7. Mostre um exemplo de algoritmo cuja complexidade é **O(n log n)** e explique por que esse comportamento ocorre.  
8. Explique o que significa o **custo amortizado** em operações de uma **tabela hash**.  
9. Dado um algoritmo com complexidade **T(n) = 2T(n/2) + n**, determine sua ordem de crescimento.  
10. Compare o desempenho de algoritmos com complexidade **O(n log n)** e **O(n²)** para valores grandes de *n*.  
11. Um algoritmo recursivo é definido por **T(n) = T(n-1) + n**. Qual é sua complexidade aproximada?  
12. Qual o impacto da escolha de pivô na complexidade média do **Quick Sort**?  
13. Explique o conceito de **limite inferior (lower bound)** para algoritmos de ordenação.  
14. Dê um exemplo de problema **NP-difícil** e explique o que isso significa em termos de complexidade.  
15. Por que não é possível encontrar algoritmos polinomiais conhecidos para resolver todos os problemas **NP-completos**?  
16. Mostre um exemplo prático em que a análise de complexidade **pode orientar o design de software**.  
17. Explique a diferença entre **complexidade no caso médio** e **complexidade no pior caso**.  
18. Um algoritmo que compara todos os pares de elementos em uma lista de tamanho *n* possui qual complexidade?  
19. Mostre por que **f(n) = n!** cresce mais rapidamente do que **f(n) = 2ⁿ**.  
20. Se um algoritmo tem tempo de execução **T(n) = n² log n + 5n**, qual é sua complexidade em notação O?

---
# 🧠 Gabarito — Complexidade de Algoritmos

---

## 📘 Versão Fácil

1. **Complexidade** mede o quanto um algoritmo consome de **tempo** e/ou **memória** conforme o tamanho da entrada aumenta.  
2. **Tempo de execução** é quanto o algoritmo demora para terminar; **uso de memória** é o quanto de espaço ele ocupa durante a execução.  
3. A **notação Big O** descreve o **comportamento assintótico**, ou seja, como o tempo de execução cresce em relação ao tamanho da entrada.  
4. O termo **n** representa o **tamanho da entrada** (ex: número de elementos em uma lista).  
5. Exemplo de **O(1)**: acesso direto a um elemento de um vetor (`array[i]`).  
6. Exemplo de **O(n)**: percorrer todos os elementos de uma lista (busca linear).  
7. **Bubble Sort** tem complexidade **O(n²)**.  
8. **Busca Linear** também é **O(n)**.  
9. **O(log n)** indica que o tempo cresce lentamente — típico de algoritmos que **dividem o problema pela metade**, como a **busca binária**.  
10. **Melhor caso:** execução mais rápida possível; **pior caso:** execução mais lenta possível.  
11. Complexidades **exponenciais (O(2ⁿ))** aparecem em problemas de **força bruta** ou **combinatórios**, como o *caixeiro-viajante*.  
12. **Complexidade polinomial** é quando o tempo cresce como uma potência de *n* (ex: n², n³).  
13. **Merge Sort** no pior caso tem **O(n log n)**.  
14. Dois **loops aninhados** → **O(n²)**.  
15. Porque algoritmos mais eficientes **economizam tempo e recursos**, especialmente em entradas grandes.  
16. Sim, **O(n)** é considerado **eficiente**, pois cresce linearmente.  
17. A notação **Θ (teta)** indica o **crescimento exato** da função — tanto limite superior quanto inferior.  
18. Três loops aninhados → **O(n³)**.  
19. Um algoritmo **in-place** não usa memória extra significativa (ex: ordenação feita no próprio vetor).  
20. Estruturas como **árvores balanceadas**, **tabelas hash** e **heaps** aumentam a eficiência de busca e ordenação.

---

## 🔬 Versão Difícil

1. Porque o **tamanho da entrada (n)** afeta diretamente o número de operações e o desempenho do algoritmo.  
2. Exemplo: **busca de elemento** — em uma **lista** é O(n), mas em uma **tabela hash** é O(1).  
3. **f(n) = 4n² + 3n + 10 → O(n²)**, pois o termo de maior grau domina o crescimento.  
4. Como **n²** cresce mais rápido que **n**, o termo **n** é desprezável — logo, O(n² + n) = O(n²).  
5. **O(n)** é o limite superior (pior caso), **Ω(n)** é o limite inferior (melhor caso).  
6. Relação: **T(n) = 2T(n/2) + n** → pelo **Teorema Mestre**, temos **O(n log n)**.  
7. Exemplo: **Merge Sort** ou **Quick Sort (caso médio)** — ocorre porque o problema é dividido em partes menores e os resultados são combinados.  
8. **Custo amortizado** é o custo médio por operação após várias execuções — usado em **estruturas dinâmicas** como **vetores redimensionáveis** e **tabelas hash**.  
9. **T(n) = 2T(n/2) + n → O(n log n)** (mesmo resultado do Merge Sort).  
10. Para valores grandes de *n*, **O(n log n)** cresce muito mais lentamente que **O(n²)** — logo, é mais eficiente.  
11. **T(n) = T(n-1) + n → O(n²)** (soma aritmética de 1 a n).  
12. A escolha ruim do pivô pode gerar partições desbalanceadas, elevando o Quick Sort de **O(n log n)** para **O(n²)**.  
13. O **limite inferior** define o menor tempo teórico possível — por exemplo, **O(n log n)** é o limite inferior para algoritmos de ordenação por comparação.  
14. Exemplo: **Problema do Caixeiro-Viajante (TSP)** — NP-difícil significa que **não há algoritmo polinomial conhecido** e que resolver o problema é tão difícil quanto os mais difíceis da classe NP.  
15. Porque **ninguém encontrou (nem provou existir)** um algoritmo polinomial para todos os problemas NP-completos; é a base do **Problema P vs NP**.  
16. Exemplo: escolher **árvores balanceadas (O(log n))** em vez de **listas (O(n))** para buscas rápidas em um sistema de cadastro.  
17. **Caso médio:** comportamento típico; **pior caso:** execução mais demorada possível.  
18. Comparar todos os pares → **O(n²)**.  
19. **n!** cresce mais rápido porque **multiplica por números cada vez maiores**, enquanto **2ⁿ** apenas dobra a cada passo.  
20. **T(n) = n² log n + 5n → O(n² log n)** (termo dominante).

---
