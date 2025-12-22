# Camada Física e Análise de Fourier

## Conceito
A **Análise de Fourier** afirma que qualquer sinal periódico pode ser representado como a soma de senos e cossenos de diferentes frequências, amplitudes e fases. Na **camada física** das redes de computadores, essa análise é fundamental para compreender como os sinais que transportam dados se comportam nos meios físicos.

---

## Fórmula da Série de Fourier

Um sinal periódico \( f(t) \) com período \( T \) pode ser representado por:

\[
f(t) = a_0 + \sum_{n=1}^{\infty} \left( a_n \cos(2\pi n f_0 t) + b_n \sin(2\pi n f_0 t) \right)
\]

Onde:
- \( f_0 = \frac{1}{T} \) é a frequência fundamental  
- \( a_0 \) é o valor médio do sinal  
- \( a_n \) e \( b_n \) são os coeficientes de Fourier  
- \( n \) representa os harmônicos do sinal  

---

## Importância na Camada Física
A Análise de Fourier permite:
- Determinar a **largura de banda** necessária para transmissão
- Analisar **distorções e ruídos**
- Projetar **filtros**
- Entender limitações de velocidade dos meios físicos

---

## Relação com Redes de Computadores
- Pulsos digitais são compostos por várias frequências
- Meios físicos possuem largura de banda limitada
- A perda de componentes de alta frequência causa **distorção do sinal**
- Influencia diretamente a **taxa máxima de transmissão de dados**

---

## Conclusão
Na camada física, a Análise de Fourier é essencial para compreender a relação entre **tempo, frequência e largura de banda**, sendo base para técnicas de modulação e transmissão de dados em redes modernas.
