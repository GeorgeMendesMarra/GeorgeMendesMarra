# Ementa da Disciplina

## 1. Identificação

| Campo | Informação |
|---|---|
| **Disciplina** | Computação Científica |
| **Curso** | Ciência da Computação |
| **Carga horária total** | 64 horas (32 h teóricas + 32 h práticas) |
| **Créditos** | 4 |
| **Natureza** | Obrigatória / Optativa (ajustar conforme o PPC) |
| **Período sugerido** | 5º ao 7º período |
| **Pré-requisitos** | Cálculo Diferencial e Integral I e II; Álgebra Linear; Algoritmos e Programação |
| **Co-requisitos** | Nenhum |

---

## 2. Ementa

Representação de números e aritmética de ponto flutuante. Análise de erros, condicionamento e estabilidade numérica. Resolução de equações não lineares. Resolução de sistemas de equações lineares (métodos diretos e iterativos). Interpolação e ajuste de curvas. Integração e diferenciação numéricas. Resolução numérica de equações diferenciais ordinárias. Introdução a métodos numéricos para equações diferenciais parciais. Autovalores e autovetores. Otimização numérica. Noções de computação de alto desempenho e paralelismo. Uso de bibliotecas científicas, visualização de resultados e boas práticas de reprodutibilidade.

---

## 3. Objetivos

### 3.1 Objetivo geral

Capacitar o estudante a modelar, implementar e analisar criticamente soluções computacionais para problemas científicos e de engenharia, compreendendo as limitações e o custo dos métodos numéricos.

### 3.2 Objetivos específicos

Ao final da disciplina, o estudante deverá ser capaz de:

- Compreender como números reais são representados em computadores e identificar fontes de erro em cálculos numéricos.
- Escolher e implementar métodos numéricos adequados a cada classe de problema.
- Avaliar precisão, estabilidade, convergência e complexidade computacional dos algoritmos.
- Utilizar bibliotecas científicas (como NumPy e SciPy) e reconhecer quando usá-las em vez de implementações próprias.
- Traduzir um problema físico, biológico ou de engenharia em um modelo matemático computável.
- Visualizar, interpretar e comunicar resultados de simulações.
- Aplicar noções básicas de desempenho e paralelismo em código científico.

---

## 4. Conteúdo programático

### Unidade 1 — Fundamentos de computação numérica (8 h)

1. Introdução à computação científica e ao processo de modelagem
2. Sistemas de numeração e representação em ponto flutuante (padrão IEEE 754)
3. Tipos de erro: absoluto, relativo, de arredondamento e de truncamento
4. Cancelamento catastrófico, overflow e underflow
5. Condicionamento de problemas e estabilidade de algoritmos

### Unidade 2 — Equações não lineares (6 h)

1. Localização de raízes e isolamento
2. Método da bisseção e método da posição falsa
3. Método de Newton-Raphson e método da secante
4. Iteração de ponto fixo
5. Ordem de convergência e critérios de parada
6. Sistemas não lineares: método de Newton multidimensional

### Unidade 3 — Sistemas de equações lineares (10 h)

1. Eliminação de Gauss e pivoteamento
2. Decomposição LU e fatoração de Cholesky
3. Normas de vetores e matrizes; número de condição
4. Métodos iterativos: Jacobi, Gauss-Seidel e SOR
5. Matrizes esparsas e formatos de armazenamento (CSR, CSC)
6. Introdução a métodos de subespaço de Krylov (gradiente conjugado)

### Unidade 4 — Interpolação e ajuste de curvas (6 h)

1. Interpolação polinomial: Lagrange e Newton
2. Fenômeno de Runge e escolha de nós
3. Splines lineares e cúbicas
4. Mínimos quadrados linear e não linear
5. Decomposição QR e introdução à SVD

### Unidade 5 — Integração e diferenciação numéricas (6 h)

1. Diferenças finitas e análise de erro
2. Regras do trapézio e de Simpson
3. Fórmulas de Newton-Cotes e quadratura de Gauss
4. Integração adaptativa
5. Introdução à integração de Monte Carlo

### Unidade 6 — Equações diferenciais ordinárias (10 h)

1. Problemas de valor inicial
2. Métodos de Euler e de Euler melhorado
3. Métodos de Runge-Kutta
4. Métodos de passo múltiplo
5. Estabilidade, rigidez (*stiffness*) e métodos implícitos
6. Passo adaptativo
7. Sistemas de EDOs e aplicações (dinâmica populacional, mecânica, epidemiologia)

### Unidade 7 — Introdução a equações diferenciais parciais (6 h)

1. Classificação das EDPs: elíptica, parabólica e hiperbólica
2. Método de diferenças finitas em 1D e 2D
3. Condições de contorno
4. Consistência, estabilidade e convergência (condição CFL)
5. Visão geral dos métodos de elementos finitos e volumes finitos

### Unidade 8 — Autovalores e otimização (6 h)

1. Método das potências e potência inversa
2. Algoritmo QR para autovalores
3. Otimização sem restrições: gradiente descendente e método de Newton
4. Introdução à otimização com restrições
5. Aplicações em aprendizado de máquina e análise de dados

### Unidade 9 — Computação de alto desempenho e boas práticas (6 h)

1. Vetorização e uso eficiente de memória e cache
2. Perfilamento (*profiling*) de código científico
3. Paralelismo em memória compartilhada e distribuída (OpenMP, MPI)
4. Noções de computação em GPU
5. Reprodutibilidade: controle de versão, ambientes, testes e documentação de experimentos

---

## 5. Metodologia

- Aulas expositivas dialogadas, com demonstração de algoritmos.
- Aulas práticas em laboratório com implementação dos métodos em **Python** (NumPy, SciPy, Matplotlib) ou **Julia**.
- Resolução de listas de exercícios teóricos e computacionais.
- Projetos aplicados com modelagem de um problema real.
- Uso de *notebooks* (Jupyter) para unir código, resultados e discussão.
- Estudo dirigido e discussão de artigos ou estudos de caso.

---

## 6. Avaliação

| Instrumento | Peso |
|---|---|
| Provas escritas (2) | 40% |
| Listas e atividades práticas de laboratório | 20% |
| Projeto final (código, relatório e apresentação) | 30% |
| Participação e seminários | 10% |

**Critérios do projeto final:** correção da implementação, análise de erro e convergência, qualidade da visualização, clareza do relatório e reprodutibilidade do código.

A aprovação segue o regimento da instituição (média mínima e frequência mínima de 75%).

---

## 7. Cronograma sugerido (16 semanas)

| Semanas | Conteúdo |
|---|---|
| 1–2 | Unidade 1: Fundamentos de computação numérica |
| 3–4 | Unidade 2: Equações não lineares |
| 5–7 | Unidade 3: Sistemas lineares |
| 7–8 | Unidade 4: Interpolação e ajuste de curvas |
| 8 | **Primeira prova** |
| 9–10 | Unidade 5: Integração e diferenciação |
| 11–12 | Unidade 6: EDOs |
| 13 | Unidade 7: Introdução às EDPs |
| 14 | Unidade 8: Autovalores e otimização |
| 15 | Unidade 9: HPC e boas práticas |
| 16 | **Segunda prova** e apresentação dos projetos |

---

## 8. Bibliografia

### 8.1 Básica

- BURDEN, R. L.; FAIRES, J. D. **Análise Numérica**. São Paulo: Cengage Learning.
- CHAPRA, S. C.; CANALE, R. P. **Métodos Numéricos para Engenharia**. Porto Alegre: AMGH / McGraw-Hill.
- RUGGIERO, M. A. G.; LOPES, V. L. R. **Cálculo Numérico: Aspectos Teóricos e Computacionais**. São Paulo: Pearson Makron Books.
- HEATH, M. T. **Scientific Computing: An Introductory Survey**. Philadelphia: SIAM.

### 8.2 Complementar

- FRANCO, N. B. **Cálculo Numérico**. São Paulo: Pearson Prentice Hall.
- SAUER, T. **Numerical Analysis**. Boston: Pearson.
- TREFETHEN, L. N.; BAU, D. **Numerical Linear Algebra**. Philadelphia: SIAM.
- QUARTERONI, A.; SACCO, R.; SALERI, F. **Numerical Mathematics**. New York: Springer.
- GOLUB, G. H.; VAN LOAN, C. F. **Matrix Computations**. Baltimore: Johns Hopkins University Press.
- HIGHAM, N. J. **Accuracy and Stability of Numerical Algorithms**. Philadelphia: SIAM.
- PRESS, W. H. et al. **Numerical Recipes: The Art of Scientific Computing**. Cambridge: Cambridge University Press.
- LANGTANGEN, H. P. **A Primer on Scientific Programming with Python**. Berlin: Springer.

### 8.3 Recursos online

- Documentação oficial do **NumPy**, **SciPy** e **Matplotlib**
- Documentação da linguagem **Julia**
- Materiais abertos de cursos de métodos numéricos e computação científica (MIT OpenCourseWare, entre outros)

---

## 9. Observações

- Edições, anos e editoras da bibliografia devem ser atualizados conforme o acervo da biblioteca da instituição.
- Carga horária, créditos, pré-requisitos e pesos de avaliação devem ser ajustados ao Projeto Pedagógico do Curso (PPC).
