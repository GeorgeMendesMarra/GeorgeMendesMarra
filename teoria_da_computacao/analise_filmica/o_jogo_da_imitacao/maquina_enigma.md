# A Máquina Enigma

A **Enigma** era uma máquina eletromecânica de cifragem inventada pelo engenheiro alemão **Arthur Scherbius**, patenteada em 1918 e comercializada nos anos 1920 para empresas e bancos. A partir de 1928, o exército alemão adotou uma versão militar, reforçada com um **painel de conexões**, e a máquina se tornou o principal sistema de comunicação cifrada das forças armadas alemãs na Segunda Guerra.

Por fora, parecia uma máquina de escrever com uma fileira de lâmpadas. Por dentro, era um circuito elétrico que **mudava a cada tecla pressionada**.

---

## 1. Antecedentes: os poloneses chegaram primeiro

Antes de Bletchley Park, o **Biuro Szyfrów** (serviço de cifras polonês) já lia a Enigma. A partir de **1932**, o matemático **Marian Rejewski** usou teoria de permutações para reconstruir a fiação dos rotores e, depois, as chaves diárias. Em **julho de 1939**, às vésperas da guerra, os poloneses repassaram a franceses e britânicos seus métodos e réplicas da máquina. Foi essa base que Turing, Welchman e outros aprimoraram em Bletchley Park.

---

## 2. O mecanismo interno: como funcionava

A cada tecla pressionada, a corrente elétrica percorria este caminho:

1. **Teclado:** o operador pressiona uma letra (por exemplo, "A").
2. **Painel de conexões (*Steckerbrett*):** cabos trocam pares de letras (com 10 cabos, 20 letras eram permutadas). A corrente passa por aqui **duas vezes**: na ida e na volta.
3. **Rotores (3 ou 4):** discos que embaralham a letra, um após o outro, da direita para a esquerda.
4. **Refletor:** devolve a corrente por um caminho diferente.
5. **Rotores, de volta:** a corrente os atravessa novamente, agora da esquerda para a direita.
6. **Painel de conexões, novamente**, e então a **lâmpada** da letra cifrada acende (por exemplo, "G").

### O movimento dos rotores

A **cada tecla pressionada**, o rotor da direita avança uma posição *antes* de a corrente passar. Quando ele completa uma volta, o rotor seguinte avança uma posição, como num **odômetro de carro**. Por isso a mesma letra é cifrada de formas diferentes ao longo da mensagem: digitar "AAAAA" poderia gerar algo como "KJWQC".

### A propriedade recíproca

Graças ao refletor, a máquina era **recíproca**: se, numa dada configuração, "A" vira "G", então "G" vira "A". Bastava, portanto, digitar o texto cifrado **com a mesma configuração** para obter o texto original. Isso facilitava a vida do operador, mas, como veremos, também era uma fraqueza.

### O tamanho do desafio

A **chave diária** combinava:

| Elemento | O que era |
|----------|-----------|
| **Ordem dos rotores** | Quais rotores usar e em que posição |
| **Posição dos anéis** | Ajuste da posição de cada anel alfabético |
| **Conexões do painel** | Os pares de letras trocados pelos cabos |
| **Posição inicial** | Letras iniciais dos rotores em cada mensagem |

Considerando ordem dos rotores, posições iniciais e 10 cabos, a Enigma do Exército tinha cerca de **1,6 × 10²⁰ configurações** possíveis (aproximadamente **158 quintilhões**). Testar todas na força bruta era inviável, o que explica por que os alemães confiavam tanto nela.

---

## 3. As variantes e a complexidade militar

Diferentes ramos das forças alemãs usavam versões distintas, o que complicava o trabalho dos decifradores:

| Versão | Usuário | Características |
|--------|---------|-----------------|
| **Enigma I** | Wehrmacht (Exército) e Luftwaffe | 3 rotores escolhidos de um conjunto de 5, mais painel de conexões. |
| **Enigma M3** | Kriegsmarine (Marinha) | 3 rotores escolhidos de um conjunto maior (8), com códigos e procedimentos muito mais rígidos. |
| **Enigma M4** | Submarinos (U-Boats) | **4 rotores**. Introduzida em fevereiro de 1942, causou um "apagão" de inteligência dos Aliados por cerca de dez meses. |
| **Abwehr Enigma** | Inteligência alemã | Sem painel de conexões, mas com rotores que giravam de forma **irregular**. |

---

## 4. O fator humano: onde a Enigma falhou

Matematicamente, a Enigma era muito forte para a época. Os problemas estavam nos **procedimentos** e nos **operadores**. Os decifradores exploravam os ***cribs***, trechos de texto original que se sabia ou se suspeitava estarem na mensagem:

- **Padrões preguiçosos:** operadores escolhiam posições iniciais óbvias, como três letras seguidas no teclado (**QWE**) ou iniciais de pessoas queridas.
- **Mensagens previsíveis:** relatórios meteorológicos, fórmulas de abertura e frases de rotina, como *Keine besonderen Ereignisse* ("nada de especial a relatar"), apareciam com frequência, e os britânicos sabiam que certas palavras estariam lá.
- **A falha de design:** como consequência do refletor, **uma letra nunca podia ser cifrada como ela mesma**. Se uma letra do texto cifrado fosse "A", a letra original **não** era "A". Essa limitação permitia descartar posições impossíveis para um *crib* e **reduzia drasticamente o espaço de busca das Bombes**, as máquinas de Bletchley Park.

### Exemplo: descartando posições de um *crib*

Suponha o texto cifrado **ZWNTLKERF** e o *crib* **WETTER** (fictício, só para ilustrar). Deslizamos o *crib* ao longo do texto e eliminamos qualquer posição em que uma letra coincida com ela mesma:

| Posição | Alinhamento | Resultado |
|---------|-------------|-----------|
| 0 | WETTER sobre ZWNTLK | **Impossível**: a 4ª letra é T com T |
| 1 | WETTER sobre WNTLKE | **Impossível**: a 1ª letra é W com W |
| 2 | WETTER sobre NTLKER | **Impossível**: a 5ª letra é E com E |
| 3 | WETTER sobre TLKERF | **Possível**: nenhuma coincidência |

Das quatro posições testadas, três caem de imediato; só a posição 3 segue para a análise das Bombes.

---

## 5. "Ultra" e o pós-guerra: o grande segredo

A inteligência obtida com a quebra da Enigma e de outras cifras recebeu o codinome **Ultra** e foi guardada com extremo rigor.

1. **Proteger a fonte:** os Aliados evitavam agir sempre que isso revelasse a quebra do código. Muitas vezes mandavam um avião de reconhecimento passar pela área antes de um ataque, para que os alemães atribuíssem a descoberta a esse voo. A ideia de que Churchill teria sacrificado alvos de propósito (como no famoso "mito de Coventry") é **amplamente contestada** por historiadores.
2. **O silêncio de décadas:** a existência do Ultra só se tornou pública em **1974**, com o livro de F. W. Winterbotham. O papel específico de Alan Turing se difundiu ainda mais tarde, sobretudo com a biografia de Andrew Hodges (1983). Até então, a narrativa dominante da guerra ignorava a contribuição da criptoanálise.
3. **Máquinas capturadas após a guerra:** circulam relatos de que máquinas Enigma foram repassadas a outros países sem que se revelasse que as mensagens podiam ser lidas. A história é frequentemente repetida, mas a comprovação documental é limitada; ao citá-la, convém indicar a fonte.

---

## 6. Conexão com o filme *O Jogo da Imitação*

- **O "momento eureca":** no filme, a ideia de usar trechos previsíveis (como "Heil Hitler") surge como uma descoberta súbita. Na realidade, o uso de *cribs* era uma prática já incorporada ao método de trabalho e ao próprio projeto das Bombes.
- **Os poloneses:** o filme quase não menciona o trabalho de Rejewski e seus colegas, que foi decisivo.
- **Ciência x segredo:** a tensão do filme sobre *quando agir* com base nas informações decifradas reflete um dilema real do Ultra, mas, na prática, essas decisões eram coletivas e institucionais.

> Veja também: [Bletchley Park](bletchley_park.md) e [Máquina de Turing](maquina_de_Turing.md).
