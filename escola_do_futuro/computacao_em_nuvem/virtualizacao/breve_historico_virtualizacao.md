---

### 1. O Início: Mainframes (Décadas de 60 e 70)

A virtualização não é uma ideia nova. Ela surgiu nos anos 60 com a **IBM**.

* **O Problema:** Naquela época, os computadores (Mainframes) custavam milhões de dólares e só podiam executar uma tarefa por vez para um único usuário.
* **A Solução:** Em 1964, a IBM desenvolveu o sistema **CP-40**, que evoluiu para o **CP-67**. Ele permitia "fatiar" o hardware em várias seções independentes. Cada seção parecia um computador completo para o usuário. Assim nasceu o conceito de **Máquina Virtual (VM)** e o primeiro **Hypervisor** (chamado na época de CP - *Control Program*).

### 2. O Período de Estagnação (Décadas de 80 e 90)

Com a chegada dos computadores pessoais (PCs) e servidores x86 baratos, a virtualização perdeu o foco.

* Como o hardware ficou barato e pequeno, a mentalidade mudou para: *"Se precisamos de um novo serviço, compramos um novo servidor físico"*.
* **Consequência:** Isso gerou o fenômeno do **Server Sprawl** (proliferação de servidores), onde as empresas tinham centenas de máquinas subutilizadas (usando apenas 5% da capacidade), gastando muita energia e espaço.

### 3. O Renascimento: A Era VMware (1998 - 2000)

Em 1998, a **VMware** revolucionou o mercado ao conseguir virtualizar a arquitetura **x86**, algo que muitos especialistas diziam ser impossível devido às limitações do conjunto de instruções dos processadores Intel/AMD da época.

* Em 1999, lançaram o *VMware Virtual Platform*, permitindo que usuários de Windows rodassem Linux (e vice-versa) dentro de uma janela, sem reiniciar a máquina.
* Isso permitiu que empresas consolidassem vários servidores antigos em um único hardware moderno e potente.

### 4. A Resposta do Hardware e o Código Aberto (Anos 2000)

Percebendo a tendência, as gigantes do hardware e software reagiram:

* **Intel e AMD:** Introduziram suporte à virtualização diretamente nos processadores (**Intel VT-x** e **AMD-V**) em 2005/2006, tornando as VMs muito mais rápidas.
* **Xen e KVM:** Surgiram projetos de código aberto que permitiram que o Linux se tornasse uma potência em virtualização. O **KVM** (Kernel-based Virtual Machine) acabou se tornando a base de quase toda a infraestrutura moderna.

### 5. A Nuvem e os Containers (2006 - Presente)

* **AWS (2006):** A Amazon lançou o EC2, permitindo que qualquer pessoa "alugasse" máquinas virtuais por hora. A computação em nuvem nada mais é do que virtualização em escala global.
* **Docker (2013):** Introduziu a **Virtualização de Sistema Operacional** (Containers). Em vez de simular um computador inteiro (hardware), o container compartilha o núcleo do sistema, sendo muito mais leve.

---

### Por que isso é relevante para você hoje?

Se você está desenvolvendo em **Node.js**, você raramente instala seu código direto em um hardware físico "pelado".

1. Você desenvolve no seu PC (usando talvez um container Docker).
2. Você envia o código para um repositório.
3. Um servidor na nuvem (uma VM virtualizada) recebe seu código e o executa.

Sem a virtualização, o custo de hospedar seus aplicativos seria proibitivo e a escalabilidade (aumentar o site quando há muitos acessos) seria impossível.
