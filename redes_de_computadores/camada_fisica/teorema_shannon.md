## Teorema de Shannon (Canais com Ruído)

O **Teorema de Shannon**, formulado por **Claude E. Shannon** em 1948 no artigo *A Mathematical Theory of Communication*, estabelece o **limite máximo teórico de transmissão de informação em um canal com ruído**, garantindo comunicação **sem erro**, desde que a taxa de transmissão não ultrapasse esse limite.

Esse teorema é um dos pilares da **Teoria da Informação** e fundamenta toda a engenharia moderna de telecomunicações, redes de computadores e sistemas digitais.

---

## Contexto Histórico e Importância

Antes de Shannon, não existia uma formulação matemática clara que respondesse à pergunta:

> “Qual é a quantidade máxima de informação que um canal físico pode transportar?”

Shannon demonstrou que:

* O ruído **não impede** a comunicação
* Ele apenas **limita a taxa máxima**
* A comunicação confiável é possível **até um certo limite**

Esse limite é **absoluto** e independe da tecnologia utilizada.

---

## Formulação Matemática de Shannon

A capacidade máxima de um canal com ruído é dada por:

C = B × log2(1 + S/N)

Onde:

* **C** → capacidade máxima do canal (bps)
* **B** → largura de banda do canal (Hz)
* **S** → potência média do sinal
* **N** → potência média do ruído
* **S/N** → relação sinal-ruído (SNR)

---

## Interpretação Detalhada da Fórmula

### 1. Largura de Banda (B)

* Define a **faixa de frequências disponível**
* Determinada pela Camada Física e pelo meio de transmissão
* Cabos, fibras e canais sem fio possuem valores diferentes de B

📌 Dobrar a largura de banda **dobra a capacidade**, mantendo S/N constante.

---

### 2. Relação Sinal-Ruído (S/N)

* Mede a qualidade do canal
* Quanto maior o ruído, menor a capacidade
* Geralmente expressa em decibéis (dB)

Conversão comum:

* S/N = 10^(SNR_dB / 10)

📌 Aumentar potência melhora a capacidade, mas com **ganhos cada vez menores** (crescimento logarítmico).

---

## Relação com a Camada Física

Na **Camada Física**, o Teorema de Shannon explica:

* Por que cabos melhores suportam maiores velocidades
* Por que enlaces sem fio sofrem mais limitações
* Por que não adianta apenas aumentar potência indefinidamente
* Por que a fibra óptica domina enlaces de alta capacidade

A Camada Física atua para:

* Maximizar B (meios de alta largura de banda)
* Maximizar S/N (blindagem, filtragem, controle de potência)

---

## Relação com a Análise de Fourier

A **Análise de Fourier** mostra que:

* Todo sinal ocupa um espectro de frequências
* O ruído também ocupa o espectro
* Sinal e ruído coexistem no domínio da frequência

Shannon assume:

* Todo o ruído presente na banda B
* Um canal limitado espectralmente

Ou seja:

* **Fourier explica como o espectro é ocupado**
* **Shannon define quanto de informação cabe nesse espectro**

---

## Relação com o Teorema de Nyquist

| Aspecto         | Nyquist | Shannon  |
| --------------- | ------- | -------- |
| Considera ruído | Não     | Sim      |
| Tipo de canal   | Ideal   | Real     |
| Limite          | Teórico | Absoluto |
| Variáveis       | B e M   | B e S/N  |

Na prática:

* Nyquist define o limite **superior ideal**
* Shannon define o limite **real intransponível**

Nenhum sistema físico pode ultrapassar Shannon.

---

## Shannon e Modulação Digital

O Teorema de Shannon explica por que:

* Modulações de alta ordem (QAM-256, QAM-1024) exigem SNR elevado
* Ambientes ruidosos usam modulações simples
* Sistemas modernos adaptam modulação dinamicamente

Exemplo:

* Wi-Fi e 4G/5G alteram M conforme o canal
* Quando S/N cai, a taxa diminui automaticamente

---

## Exemplo Numérico Detalhado

Considere:

* B = 3.000 Hz
* SNR = 30 dB

Convertendo:

* S/N = 10^(30/10) = 1000

Aplicando Shannon:

C = 3000 × log2(1 + 1000)
C ≈ 3000 × 9,97
C ≈ 29.900 bps

➡️ Resultado compatível com os limites históricos da telefonia e dos modems analógicos.

---

## Aplicações Práticas em Redes de Computadores

O Teorema de Shannon é fundamental para:

* Dimensionamento de enlaces
* Planejamento de redes sem fio
* Engenharia de satélites
* DOCSIS (internet por cabo)
* ADSL e VDSL
* LTE, 5G e Wi-Fi
* IPTV e streaming

Ele explica **por que a capacidade varia** mesmo usando o mesmo protocolo.

---

## Limitações Impostas por Shannon

* Não existe transmissão infinita
* Não existe canal perfeito
* Não existe ganho ilimitado por potência
* Toda tecnologia está limitada pela física

A inovação ocorre por:

* Melhor uso do espectro
* Codificação eficiente
* Redução de ruído
* Uso inteligente da largura de banda

---

## Conclusão

O **Teorema de Shannon** estabelece o limite máximo absoluto da comunicação em canais reais. Integrado à **Análise de Fourier** e ao **Teorema de Nyquist**, ele forma a base teórica da **Camada Física**, demonstrando que velocidade, qualidade e confiabilidade são consequências diretas das leis matemáticas e físicas.

Nenhuma tecnologia moderna supera Shannon — apenas se aproxima dele de forma cada vez mais eficiente.

---
