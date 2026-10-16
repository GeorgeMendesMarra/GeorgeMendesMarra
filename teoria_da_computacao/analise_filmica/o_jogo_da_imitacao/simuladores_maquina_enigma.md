# Simuladores da Máquina Enigma

Entender a Enigma lendo sobre ela é uma coisa; **digitar uma mensagem e ver as lâmpadas acenderem** é outra. Os simuladores permitem testar, na prática, os conceitos vistos em [Máquina Enigma](maquina_enigma.md): rotores, painel de conexões, refletor, posição dos anéis e a propriedade de que a mesma configuração cifra e decifra.

---

## 1. Simulador indicado originalmente

**Enigma Cipher (NTU Singapura):** <https://piotte13.github.io/enigma-cipher/>

Desenvolvido por Leelar Thaophialuang como projeto de conclusão de curso na *School of Computer Science and Engineering* da NTU, dentro da iniciativa de demonstradores de criptografia coordenada por Anwitaman Datta (2016).

**O que oferece:**
- Simula a **Enigma M3** (3 rotores, usada pelo Exército e pela Marinha).
- Rotores **I a VIII**, refletores **B** e **C**, ajuste dos anéis (*Ringstellung*), posições iniciais (*Grundstellung*) e painel de conexões com até **10 pares**.
- A página também funciona como material de estudo: traz um passo a passo do caminho de uma letra pela máquina, tabelas de fiação dos rotores e das posições de *notch* (entalhe de avanço) e um guia de uso do emulador.

**Atenção:** a página informa que o emulador funciona no **Firefox e no Opera**; trata-se de um projeto de 2016, e não testei se ele ainda funciona nos navegadores atuais. Se não carregar, use uma das alternativas abaixo.

---

## 2. Alternativas

| Simulador | Tipo | Modelos | Observações |
|-----------|------|---------|-------------|
| [Ciphers Online: Enigma](https://ciphersonline.com/classical-ciphers/enigma) | Web | Enigma I / M3 | Rotores I a V, refletores B e C, painel de conexões. Reproduz o **passo duplo** do rotor do meio. Não cobre a M4. |
| [Universal Enigma (Daniel Palloks)](https://palloks.2ix.de/enigma/index_en.html) | Web (JavaScript) | Vários modelos | Citado na lista da Wikipédia de simuladores; foi usado como referência para validar outros simuladores. |
| [Enigma G-312 (Anton Haddeman, PA3DBJ)](https://www.cryptomuseum.com/crypto/enigma/sim/pa3dbj) | Web (JavaScript) | Enigma G-312 (**Abwehr**) | Mesmo modelo exibido no museu de Bletchley Park; hospedado pelo Crypto Museum. |
| [EnigmaZone](https://apps.microsoft.com/detail/9p8jmrc1qbcg) | Aplicativo Windows | I, M3 e M4 | Gratuito, tem tutorial e permite tentar **quebrar mensagens automaticamente**. |
| [crypto-enigma](https://crypto-enigma.readthedocs.io/) | Biblioteca Python e linha de comando | I, M3 e M4 | A documentação informa que é voltada ao Python 2.7. |
| [py-enigma](https://pypi.org/project/py-enigma/) | Biblioteca Python | Modelos militares comuns | Foi a base do simulador da NTU. Testei a instalação em Python 3 e os exemplos da seção 4. |
| [Lista de simuladores (Wikipédia)](https://en.wikipedia.org/wiki/List_of_Enigma_machine_simulators) | Catálogo | Diversos | Compara plataformas, modelos e recursos. |

> **Dica:** para estudar a **M4** (submarinos), escolha um simulador que a liste explicitamente, como o EnigmaZone ou a crypto-enigma. O simulador da NTU e o Ciphers Online não a incluem.

---

## 3. Atividades práticas

Use, em qualquer simulador de **Enigma I / M3**, esta configuração de referência:

| Parâmetro | Valor |
|-----------|-------|
| Refletor | B |
| Rotores (esquerda, meio, direita) | I, II, III |
| Anéis (*Ringstellung*) | A, A, A (ou 01, 01, 01) |
| Posição inicial (*Grundstellung*) | A, A, A |
| Painel de conexões | vazio |

**Atividade 1: conferir o simulador.** Digite `AAAAA`. O resultado esperado é **`BDZGO`**. Se o seu simulador der outra resposta, revise a configuração.

**Atividade 2: reciprocidade.** Digite `HELLOWORLD`; o resultado esperado é **`ILBDAAMTAZ`**. Volte os rotores à posição `AAA` e digite o texto cifrado: deve aparecer `HELLOWORLD` de novo. Isso mostra que a máquina é **recíproca**.

**Atividade 3: painel de conexões.** Conecte os pares `AB CD EF GH IJ` e cifre `HELLOWORLD` novamente. O resultado esperado é **`XKACBBMTBF`**. Compare com a atividade 2: o que mudou, mantendo o resto igual?

**Atividade 4: uma letra nunca vira ela mesma.** Digite uma longa sequência de letras `A`. Em nenhum momento a letra `A` deve aparecer na saída. Por quê? (Dica: pense no refletor.) Relacione com a técnica dos *cribs* vista em [Máquina Enigma](maquina_enigma.md).

**Atividade 5: efeito das posições iniciais.** Cifre `HELLOWORLD` com a posição inicial `AAA` e depois com `QWE`. O mesmo texto gera resultados completamente diferentes? O que isso diz sobre a importância de **proteger a chave diária**?

**Atividade 6: passo dos rotores.** Observe o rotor da direita avançando a cada tecla e, de tempos em tempos, o rotor do meio. Em simuladores que reproduzem o **passo duplo**, procure a situação em que o rotor do meio avança duas vezes seguidas.

---

## 4. Para programadores: simulando em Python

Com a biblioteca `py-enigma` (`pip install py-enigma`), as atividades 2 e 4 ficam assim:

```python
from enigma.machine import EnigmaMachine

def maquina(plugboard=None, inicio="AAA"):
    m = EnigmaMachine.from_key_sheet(
        rotors="I II III",
        reflector="B",
        ring_settings=[0, 0, 0],
        plugboard_settings=plugboard,
    )
    m.set_display(inicio)
    return m

# Atividade 2: reciprocidade
cifrado = maquina().process_text("HELLOWORLD")
print(cifrado)                              # ILBDAAMTAZ
print(maquina().process_text(cifrado))      # HELLOWORLD

# Atividade 4: nenhuma letra é cifrada como ela mesma
saida = maquina().process_text("A" * 2000)
print("A" in saida)                         # False
```

**Desafio para a turma:** implementar a própria Enigma, com três rotores, refletor e painel de conexões, e comparar os resultados com os do simulador. É um ótimo exercício de vetores, funções e modularização.

---

## 5. Leituras relacionadas

- [A Máquina Enigma](maquina_enigma.md): mecanismo, variantes e falhas.
- [Bletchley Park](bletchley_park.md): onde a Enigma foi quebrada.
- [Máquina de Turing](maquina_de_Turing.md): o modelo teórico de computação.

> **Nota:** links externos mudam ou saem do ar com o tempo. Teste-os antes de usá-los em aula.
