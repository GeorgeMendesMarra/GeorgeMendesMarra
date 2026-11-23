# Computação Gráfica – Engenharia de Software

## Ementa

Fundamentos de computação gráfica e do pipeline gráfico. Algoritmos de rasterização de primitivas (retas, circunferências, polígonos e preenchimento). Representação e estruturação de informação gráfica (formatos vetoriais e matriciais, modelos de cor, grafos de cena). Transformações geométricas 2D e 3D em coordenadas homogêneas (translação, escala, rotação), janelamento e recorte. Modelagem geométrica (curvas de Bézier e splines, malhas poligonais, noções de sólidos). Projeção, visibilidade, iluminação e sombreamento. Descrição, construção e utilização de um núcleo de sistema gráfico e de APIs gráficas (OpenGL/WebGL, Canvas, Java 2D/JavaFX). Técnicas de manipulação de janelas, viewports e interação (eventos de mouse e teclado, seleção de objetos). Noções de processamento digital de imagens e visão computacional. Aplicações em jogos, visualização de dados, interfaces gráficas, realidade aumentada e design de software.

## Objetivo geral

Capacitar o estudante a compreender os fundamentos matemáticos e algorítmicos da computação gráfica e a projetar e implementar aplicações gráficas interativas, aplicando boas práticas de engenharia de software.

## Objetivos específicos

- Implementar algoritmos clássicos de rasterização (Bresenham, ponto médio, scanline, flood fill).
- Aplicar transformações geométricas 2D/3D com matrizes e coordenadas homogêneas.
- Implementar recorte de linhas e polígonos (Cohen-Sutherland, Sutherland-Hodgman) e mapeamento janela-viewport.
- Modelar curvas, superfícies e objetos por malhas poligonais.
- Compreender o pipeline gráfico, projeções, iluminação e sombreamento.
- Desenvolver aplicações com uma API gráfica (OpenGL/WebGL, Java 2D/JavaFX ou Canvas).
- Aplicar operações básicas de processamento de imagens (filtros, histograma, limiarização, detecção de bordas).
- Estruturar o código gráfico com padrões de projeto, modularização e controle de versão.

## Conteúdo programático

### Unidade 1 – Fundamentos
1. Conceitos básicos, histórico e áreas de aplicação.
2. Dispositivos gráficos, resolução, pixel e framebuffer.
3. Modelos de cor (RGB, CMYK, HSV) e pipeline gráfico.

### Unidade 2 – Primitivas gráficas
1. Rasterização de retas (DDA, Bresenham).
2. Circunferências, elipses e polígonos.
3. Preenchimento de regiões e antialiasing.
4. Representação e estruturação da informação gráfica: formatos BMP, PNG, SVG; estruturas de dados para objetos gráficos.

### Unidade 3 – Transformações, escala e recorte
1. Transformações 2D e 3D, matrizes e coordenadas homogêneas.
2. Escala, rotação, translação e composição de transformações.
3. Janela, viewport e recorte 2D.

### Unidade 4 – Modelagem geométrica
1. Curvas de Bézier, B-splines e interpolação.
2. Malhas poligonais e modelagem de sólidos.
3. Projeções (paralela e perspectiva) e visibilidade (z-buffer).

### Unidade 5 – Iluminação e sistema gráfico
1. Modelos de iluminação e sombreamento (Phong, Gouraud).
2. Núcleo de um sistema gráfico: arquitetura, grafo de cena, primitivas e estados.
3. Uso de OpenGL/WebGL: shaders, buffers e texturas.

### Unidade 6 – Interação e manipulação de janelas
1. Sistemas de janelas, eventos e laço de renderização.
2. Seleção e manipulação de objetos (picking), câmera e navegação.

### Unidade 7 – Processamento de imagens e aplicações
1. Amostragem, quantização, histograma e filtros (convolução).
2. Segmentação e detecção de bordas; introdução à visão computacional.
3. Aplicações: jogos, visualização de dados, realidade aumentada e interfaces.

## Metodologia

Aulas expositivas dialogadas e aulas práticas em laboratório, com implementação incremental de um mini motor gráfico. Aprendizagem baseada em projetos, uso de Git e revisão de código em duplas.

## Avaliação

- **N1:** prova teórico-prática (primitivas, transformações e recorte) e exercícios de laboratório.
- **N2:** projeto integrador (aplicação gráfica interativa) com código-fonte, documentação, demonstração e apresentação.

## Bibliografia básica

- CONCI, A.; AZEVEDO, E. *Computação Gráfica: Geração de Imagens*. Rio de Janeiro: Campus, 2003.
- CONCI, A.; AZEVEDO, E.; LETA, F. R. *Computação Gráfica: volume 2 (Processamento e Análise de Imagens Digitais)*. Rio de Janeiro: Campus/Elsevier, 2008.
- GOMES, J. M.; VELHO, L. *Fundamentos da Computação Gráfica*. Rio de Janeiro: IMPA, 2003. (Série de Computação e Matemática)

## Bibliografia complementar

- BACKES, A. R.; MESQUITA, J. J. *Introdução à Visão Computacional*. Rio de Janeiro: Alta Books, 2016.
- COHEN, M.; MANSSOUR, I. H. *OpenGL: Uma Abordagem Prática e Objetiva*. São Paulo: Novatec, 2006.
- GOMES, J. M.; VELHO, L. *Computação Gráfica: Imagem*. Rio de Janeiro: IMPA/SBM, 2002.
- GONZALEZ, R. C.; WOODS, R. E. *Processamento de Imagens Digitais*. São Paulo: Edgard Blücher, 2000.
- SOLOMON, C.; BRECKON, T. *Fundamentos de Processamento Digital de Imagens*. Rio de Janeiro: LTC, 2013.
- MIRANDA, J. I. *Processamento de Imagens Digitais: Métodos Multivariados em Java*. Brasília: Embrapa, 2011.
