### 1. O que é, tecnicamente, uma Máquina Virtual (VM)?

Uma Máquina Virtual é uma implementação de software de um computador que executa programas como se fosse uma máquina física. Ela opera baseada na arquitetura de um computador real, utilizando uma fatia isolada dos recursos de hardware (CPU, memória RAM, armazenamento) do hospedeiro (*Host*). Para o software que está rodando dentro dela (o *Guest*), não há diferença perceptível entre estar em um hardware real ou virtualizado; a VM possui sua própria BIOS, sistema de arquivos e drivers de rede virtuais.

### 2. Qual é o papel fundamental do Hypervisor (VMM)?

O Hypervisor, ou *Virtual Machine Monitor*, é a camada de software que permite a existência da virtualização. Sua função é abstrair os recursos físicos e distribuí-los entre as várias VMs. Ele atua como um "policial de trânsito": garante que uma VM não acesse a memória da outra e que o tempo de processamento da CPU seja dividido de forma justa e eficiente. Sem ele, o sistema operacional tentaria controlar o hardware diretamente, impossibilitando a execução de múltiplos SOs simultâneos.

### 3. Explique a diferença entre Hypervisor Tipo 1 e Tipo 2.

* **Tipo 1 (Bare-Metal):** É instalado diretamente sobre o hardware físico. Como não há um sistema operacional intermediário (como Windows ou Linux) para "roubar" recursos, ele oferece alta performance e latência mínima. É o padrão em Data Centers e Nuvens (como AWS e Azure). Exemplos: **VMware ESXi, Microsoft Hyper-V e Xen**.
* **Tipo 2 (Hosted):** Roda como um aplicativo dentro de um sistema operacional já existente. É muito utilizado por desenvolvedores para testar ambientes localmente. O desempenho é menor, pois as instruções precisam passar pelo Hypervisor, depois pelo SO hospedeiro e só então chegar ao hardware. Exemplos: **Oracle VirtualBox e VMware Workstation**.

### 4. Por que o "Isolamento" é considerado o pilar da segurança na nuvem?

O isolamento garante que cada VM opere em uma "caixa" lógica separada. Se um servidor web dentro de uma VM for invadido ou sofrer um ataque de *ransomware*, o atacante ficará preso dentro daquele ambiente virtual. Ele não consegue saltar para o hardware físico ou para outras máquinas virtuais do mesmo servidor através da memória. Isso permite que provedores de nuvem coloquem competidores (como Coca-Cola e Pepsi) no mesmo servidor físico sem riscos de espionagem de dados.

### 5. Qual a diferença conceitual entre Virtualização e Emulação?

A **Virtualização** utiliza o hardware real para executar o código. Se a sua CPU física é Intel, a VM executa instruções Intel diretamente, o que é extremamente rápido. Já a **Emulação** traduz instruções de um hardware para outro. Por exemplo, rodar um app de Android (ARM) no seu PC (x86). O emulador precisa ler cada linha de código e "explicar" para o processador como executá-la em uma arquitetura diferente, o que consome muito mais processamento.

### 6. Como os Containers (Docker) revolucionaram a virtualização?

Diferente das VMs, que carregam um sistema operacional inteiro (gigabytes de tamanho), os **Containers** virtualizam apenas o sistema operacional ao nível de processo. Eles compartilham o núcleo (*Kernel*) do sistema hospedeiro. Isso significa que você pode subir um container com Node.js em milissegundos e ele ocupará apenas alguns megabytes. Em termos de Engenharia de Software, isso permite a arquitetura de **Microserviços**, onde cada pequena parte de um sistema roda em seu próprio container leve.

### 7. O que é um Snapshot e como ele auxilia no ciclo de vida do software?

Um *Snapshot* é um registro do estado de uma máquina virtual em um ponto específico no tempo, incluindo o conteúdo do disco, da memória RAM e a configuração dos dispositivos. Para um desenvolvedor, isso é vital: antes de realizar uma atualização crítica de banco de dados ou instalar um novo software, você tira um snapshot. Se algo der errado e o sistema "quebrar", o comando *Revert* restaura a máquina ao estado exato anterior em segundos, evitando horas de reconfiguração.

### 8. Como a virtualização resolve o problema de subutilização de hardware?

Antes da virtualização, era comum servidores físicos operarem com apenas **10% ou 15%** de sua capacidade, pois as empresas compravam hardware potente para um único serviço (como um servidor de e-mail). Com a virtualização, você pode consolidar 10 servidores de 10% em um único hardware físico operando a 90% de sua carga. Isso reduz drasticamente custos de hardware, espaço físico em data centers, manutenção e, principalmente, consumo de energia elétrica e refrigeração.

### 9. O que envolve o processo de "Provisionamento Dinâmico"?

Provisionar na virtualização significa criar e configurar uma VM de forma automatizada. Em sistemas modernos de computação em nuvem, usamos **Infraestrutura como Código (IaC)**. Em vez de clicar em botões para criar um servidor, escrevemos um script. Quando o sistema detecta carga alta, o hypervisor ou a plataforma de nuvem "instancia" (provisiona) automaticamente novas máquinas virtuais para dar conta da demanda, sem intervenção humana.

### 10. O que acontece em um cenário de falha de Host (Hardware)?

Se o hardware físico (placa-mãe, fonte, etc.) queimar, todas as VMs rodando nele param. Para evitar desastres, utiliza-se o conceito de **Clustering e Live Migration**. Se o Hypervisor detectar que o hardware está falhando, ele pode "mover" a VM ligada para outro servidor físico através da rede, sem que o usuário perceba uma queda. Se o servidor morrer subitamente, o sistema de **Alta Disponibilidade (HA)** reinicia essas VMs automaticamente em outro nó do cluster que esteja saudável.

---
