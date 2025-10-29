## 📜 Breve Histórico de Sistemas de Arquivos

Um **sistema de arquivos** é a forma como o sistema operacional organiza, armazena e gerencia os dados em um meio de armazenamento (como um disco rígido ou SSD), permitindo a criação, nomeação, movimentação e recuperação de "arquivos".

O termo, que antes descrevia o método de arquivamento de documentos em papel, foi aplicado à computação a partir de **1961**. A sua evolução está intrinsecamente ligada ao desenvolvimento dos dispositivos de armazenamento:

* **Início (Décadas de 1970/1980):** Os primeiros sistemas eram mais simples, muitas vezes ligados a sistemas operacionais específicos, como os utilizados em mainframes e minicomputadores. A necessidade de gerenciar o crescente volume de dados de forma mais eficiente levou à evolução.
* **FAT (File Allocation Table):** Um dos sistemas de arquivos mais antigos e influentes, desenvolvido pela Microsoft e usado inicialmente no DOS.
    * **FAT16:** Introduzido em **1987** com o DOS 3.31. Simples e compatível, mas com limitações de tamanho de partição e arquivo.
    * **FAT32:** Introduzido em **1996** com o Windows 95 OSR2, superando as limitações de tamanho do FAT16. Ainda é amplamente utilizado em *pendrives* e cartões de memória por sua compatibilidade.
* **Sistemas UNIX/Linux:**
    * **Minix File System (Minix FS):** Usado inicialmente em distribuições Linux.
    * **Ext (Extended File System):** O sistema de arquivos nativo mais comum no Linux.
        * **Ext2, Ext3:** Evoluções que trouxeram recursos como *journaling* (registro de transações para recuperação rápida após falhas), um recurso crucial para a confiabilidade.
        * **Ext4:** Introduzido por volta de **2003** (e amplamente adotado após 2010), é o padrão para muitas distribuições Linux, oferecendo melhor desempenho e escalabilidade.
* **NTFS (New Technology File System):** Introduzido pela Microsoft com o Windows NT (início dos anos 90) e ainda em uso. Foi um grande avanço em relação ao FAT, introduzindo:
    * Suporte a **nomes de arquivo longos**.
    * **Journaling**.
    * **Permissões de arquivo** (segurança).
    * Suporte a volumes e arquivos muito maiores.
* **Sistemas Apple:**
    * **MFS (Macintosh File System):** Inicial, simples.
    * **HFS (Hierarchical File System):** Introduzido em **1986**.
    * **HFS+:** Introduzido em **1998**, atualizou estruturas para 32 bits e adicionou *journaling*.
    * **APFS (Apple File System):** Introduzido em **2017** com o macOS 10.13, otimizado para unidades SSD, com foco em clonagem, criptografia nativa e desempenho.
* **Outras Inovações:**
    * Surgimento de sistemas com recursos avançados como *copy-on-write*, gerenciamento de *snapshots* e proteção de dados, como **ZFS** e **Btrfs**, focados em alta integridade e gerenciamento de grandes volumes.
    * **Sistemas de Arquivos de Rede (NFS, SMB)**, que permitem acesso a arquivos em computadores remotos.

A história dos sistemas de arquivos é uma jornada contínua para otimizar a velocidade, flexibilidade, segurança e integridade dos dados, adaptando-se às novas tecnologias de hardware (como SSDs) e às demandas de grandes volumes de informação.

---
