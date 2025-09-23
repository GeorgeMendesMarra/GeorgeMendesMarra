## Questionário: O Jogo da Imitação e a Teoria da Computação

---

1.  No filme "O Jogo da Imitação", a máquina Enigma é apresentada como um sistema de criptografia que transforma uma mensagem de texto simples em um texto cifrado. De uma perspectiva da teoria da computação, como a Enigma pode ser vista?
    * a) Como uma linguagem formal, pois ela define um conjunto de strings válidas.
    * b) Como um autômato finito (AF), pois ela tem um número limitado de estados internos e transições.
    * c) Como uma gramática livre de contexto, porque a criptografia tem uma estrutura hierárquica.
    * d) Como uma máquina de Turing universal, pois ela pode simular qualquer outro computador.
    * **Resposta correta: b)**

2.  Alan Turing e sua equipe construíram a "Bombe", uma máquina eletromecânica projetada para decifrar as mensagens da Enigma. De uma perspectiva computacional, qual era o objetivo principal da Bombe?
    * a) Simular todas as possíveis linguagens formais para encontrar a correta.
    * b) Aumentar a complexidade do problema de criptografia da Enigma.
    * c) Encontrar a configuração inicial da Enigma (a 'chave') testando combinações de forma eficiente.
    * d) Tornar a Enigma mais difícil de ser usada pelos alemães, gerando mensagens com erros.
    * **Resposta correta: c)**

3.  A máquina de Turing é um conceito teórico fundamental na ciência da computação. Qual das seguintes opções melhor descreve a principal diferença entre uma máquina de Turing e o autômato finito que representa a Enigma?
    * a) A máquina de Turing tem uma fita de memória infinita, enquanto o AF tem memória finita.
    * b) A máquina de Turing usa alfabetos binários, enquanto a Enigma usa o alfabeto latino.
    * c) A máquina de Turing pode resolver o Problema da Parada, mas o AF não.
    * d) A máquina de Turing é mais lenta que a Enigma, pois precisa de mais tempo para processar.
    * **Resposta correta: a)**

4.  Se Alan Turing imaginou sua "Máquina de Turing" como um modelo para todo computador, o que o filme sugere sobre a Enigma em termos de computabilidade?
    * a) A Enigma era uma máquina de Turing universal, mas o Exército alemão não sabia.
    * b) A Enigma representava um problema indecidível, ou seja, sem solução.
    * c) A Enigma era, em sua essência, um autômato finito com um número vasto de estados possíveis.
    * d) A Enigma era um modelo de computação mais avançado que a máquina de Turing.
    * **Resposta correta: c)**

5.  A criptografia da Enigma dependia de um "crib" (gíria para uma palavra ou frase conhecida na mensagem). Em termos de autômatos, o que o uso de um "crib" permitiu que a Bombe de Turing fizesse?
    * a) Criar novas linguagens formais para decifrar as mensagens.
    * b) Reduzir o número de estados do autômato de busca, tornando o problema mais simples.
    * c) Transformar a Enigma em uma máquina de Turing universal.
    * d) Ignorar completamente o conceito de autômatos finitos.
    * **Resposta correta: b)**

6.  No filme, Turing busca uma máquina para decifrar a Enigma que, para cada mensagem, tenha um número específico de passos. A busca pelo "computador universal" de Turing se baseia em qual princípio da teoria da computação?
    * a) Na ideia de que o Problema da Parada é resolvível para qualquer programa.
    * b) Na capacidade de um único algoritmo de simular qualquer outro algoritmo.
    * c) No princípio de que um autômato finito pode simular qualquer gramática livre de contexto.
    * d) Na demonstração de que a Enigma era inquebrável.
    * **Resposta correta: b)**

7.  A máquina Enigma pode ser vista como um autômato finito que aceita uma linguagem. Qual é o **alfabeto de entrada** para este autômato?
    * a) Os rotores e o plugboard, pois são os componentes da máquina.
    * b) As mensagens cifradas geradas pela máquina.
    * c) As letras do alfabeto latino (A-Z) para cada pressionamento de tecla.
    * d) O conjunto de todas as chaves diárias possíveis para a Enigma.
    * **Resposta correta: c)**

8.  A máquina de Turing universal de Alan Turing é um conceito teórico que se encaixa na categoria de qual tipo de máquina?
    * a) Autômato Finito (AF).
    * b) Autômato com Pilha (AP).
    * c) Máquina de Turing.
    * d) Autômato Linearmente Limitado (ALL).
    * **Resposta correta: c)**

9.  Qual dos seguintes conceitos da teoria da computação é mais relevante para o problema de decifrar as mensagens da Enigma por força bruta, antes da invenção da Bombe?
    * a) O Problema da Parada, pois as chaves nunca paravam de ser geradas.
    * b) A complexidade de tempo exponencial.
    * c) A computabilidade de funções recursivas.
    * d) O conceito de linguagens regulares.
    * **Resposta correta: b)**

10. A máquina Bombe de Turing é um exemplo prático de um autômato que aceita uma linguagem, no sentido de que ele "aceita" a configuração correta da Enigma. Se a Bombe fosse um autômato, o que seria sua **cadeia de entrada**?
    * a) O texto cifrado da Enigma.
    * b) As diferentes configurações de rotores e plugboard que a Bombe testava.
    * c) As mensagens originais em alemão antes de serem criptografadas.
    * d) O código de cores das luzes que acendiam na Bombe.
    * **Resposta correta: b)**

11. Cada vez que uma letra é pressionada na máquina Enigma, as posições dos rotores e do plugboard mudam, alterando o resultado da criptografia. O que essa mudança representa em termos de autômatos finitos?
    * a) Uma nova linguagem formal.
    * b) Uma transição de estado.
    * c) O resultado do Problema da Parada.
    * d) Uma função de aceitação.
    * **Resposta correta: b)**

12. A "chave diária" da Enigma era uma configuração secreta que incluía a ordem dos rotores, suas posições iniciais e as conexões do plugboard. Em termos de autômatos, o que a chave diária estabelecia para a máquina Enigma?
    * a) A linguagem que a máquina aceitaria.
    * b) A quantidade de memória disponível.
    * c) O estado inicial do autômato.
    * d) O alfabeto de saída.
    * **Resposta correta: c)**

13. A máquina Bombe de Turing foi construída para decifrar a Enigma, mas ela não era um computador de propósito geral. Por que a Bombe, apesar de sua complexidade, não é considerada uma máquina de Turing universal?
    * a) Porque ela usava eletricidade em vez de fitas de papel.
    * b) Porque ela não podia simular qualquer algoritmo, apenas o processo reverso da Enigma.
    * c) Porque ela era muito grande e pesada para ser universal.
    * d) Porque ela não tinha uma fita de memória infinita.
    * **Resposta correta: b)**

14. O esforço para decifrar a Enigma era, em grande parte, um problema de busca de um vasto espaço de soluções. A equipe de Turing sabia que, sem a ajuda da máquina, essa busca manual era:
    * a) Computável em tempo polinomial.
    * b) Indecidível.
    * c) Um problema P-Completo.
    * d) Computacionalmente inviável.
    * **Resposta correta: d)**

15. O "teste da imitação", proposto por Alan Turing em 1950, é um conceito central para a Inteligência Artificial. No filme, o teste é mencionado de forma sutil, mas sua essência se relaciona à ideia de que:
    * a) Um humano e uma máquina podem ser indistinguíveis em termos de comportamento.
    * b) Todas as máquinas devem imitar o comportamento humano.
    * c) A imitação é uma forma de linguagem formal.
    * d) Autômatos finitos podem passar em qualquer teste de imitação.
    * **Resposta correta: a)**
