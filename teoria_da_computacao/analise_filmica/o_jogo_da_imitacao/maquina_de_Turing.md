A **Máquina de Turing** é um modelo matemático teórico proposto por Alan Turing em 1936 que define os limites do que pode ser calculado. Ela não é uma "máquina" física com engrenagens, mas sim um conceito que serve como a fundação de toda a ciência da computação moderna.

Basicamente, se um problema pode ser resolvido por um algoritmo, uma Máquina de Turing consegue resolvê-lo.

### Como ela funciona?

Imagine um sistema composto por quatro partes principais:

1. **Uma Fita Infinita:** Dividida em quadrados (células), cada uma contendo um símbolo (como 0, 1 ou vazio). Ela serve como a "memória" da máquina.
2. **Cabeça de Leitura e Gravação:** Um dispositivo que se move para a esquerda ou para a direita sobre a fita, lendo o que está escrito e escrevendo novos símbolos.
3. **Registro de Estado:** Armazena o "estado" atual da máquina (por exemplo: "Estado A" ou "Estado B").
4. **Tabela de Instruções (Regras):** O "programa" que diz à máquina o que fazer. Exemplo: *"Se você ler '1' no 'Estado A', mude para '0', mova-se para a direita e mude para o 'Estado B'"*.

---

### Por que ela é tão importante?

* **Universalidade:** Turing provou que uma "Máquina de Turing Universal" poderia simular qualquer outra máquina de Turing. Isso é exatamente o que um computador moderno faz: ele roda qualquer programa (software) que você fornecer.
* **O Problema da Parada:** Turing usou esse modelo para provar que existem problemas que os computadores **nunca** conseguirão resolver (como determinar se um programa qualquer vai rodar para sempre ou parar em algum momento).
* **Tese de Church-Turing:** Estabelece que qualquer coisa que consideremos "computável" por meios mecânicos pode ser feita por uma Máquina de Turing.

Apesar de ser um conceito de quase um século atrás, ela ainda é usada para classificar a complexidade de algoritmos e entender a lógica por trás das IAs mais avançadas de hoje.
