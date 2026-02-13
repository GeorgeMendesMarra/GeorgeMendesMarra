### 1. Dispositivos de Entrada e Saída (Periféricos)

Os dispositivos são as ferramentas físicas que permitem a interação com o sistema computacional:

* **Entrada (Input):** Capturam dados externos para processamento. Exemplos: teclado, mouse, scanners, microfones e sensores biométricos (fundamentais para a **Confidencialidade** e controle de acesso).


* **Saída (Output):** Traduzem os dados processados em informação inteligível ou ações. Exemplos: monitores, impressoras, atuadores e caixas de som.
* **Híbridos (Entrada/Saída):** Dispositivos que realizam ambas as funções, como telas touch, modems e unidades de armazenamento (pendrives, HDs externos).

### 2. Interfaces de I/O e Controladores

O processador não conversa diretamente com os dispositivos; ele utiliza **Interfaces e Controladores** para mediar essa comunicação:

* **Controladores de Dispositivo:** Circuitos eletrônicos (ou chips dedicados) que entendem os sinais específicos de cada hardware e os traduzem para o barramento do sistema.
* **Interfaces Padrão:** Portas de conexão que seguem protocolos universais, como **USB, HDMI, SATA e Ethernet**.
* **Drivers de Dispositivo:** A camada de software que permite ao Sistema Operacional comandar o hardware através da interface.

---

### 3. Técnicas de Comunicação de I/O

Existem diferentes formas de o processador gerenciar a entrada e saída de dados, impactando diretamente na **Disponibilidade** do sistema:

* **I/O Programada:** O processador fica em um laço de espera (*polling*) verificando se o dispositivo terminou a tarefa. É ineficiente pois "prende" a CPU.
* **I/O por Interrupção:** O dispositivo avisa ao processador quando está pronto, permitindo que a CPU execute outras tarefas enquanto o hardware trabalha (conforme vimos no tópico de **Mecanismos de Interrupção**).
* **Acesso Direto à Memória (DMA):** Permite que dispositivos de alta velocidade (como SSDs e placas de rede) transfiram dados diretamente para a RAM sem sobrecarregar a CPU.

### 4. Conexão com a Cibersegurança e o Back-End

Estes conceitos se aplicam da seguinte forma:

* **Segurança de Interface :** Portas de I/O físicas (como USB) são vetores de risco para a introdução de malwares. O controle dessas interfaces é parte da proteção da **Integridade**.


* **Comunicação de Rede :** No desenvolvimento **Back-End**, a placa de rede é a interface de I/O mais crítica. Compreender como os dados fluem por essa interface é o primeiro passo para garantir a segurança dos sistemas protegidos pelo Back-End.



---
