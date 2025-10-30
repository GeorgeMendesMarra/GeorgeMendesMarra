---

# 🍎 Breve Histórico do Sistema de Arquivos HFS (Hierarchical File System)

O **HFS (Hierarchical File System)** foi lançado pela **Apple em 1985**, substituindo o antigo **MFS (Macintosh File System)**.  
Ele marcou uma grande evolução no gerenciamento de arquivos dos computadores **Macintosh**, introduzindo **verdadeira hierarquia de diretórios** e suporte aprimorado a **discos rígidos**.

## 🔹 Contexto Histórico
Durante o início da década de 1980, os primeiros Macintosh utilizavam disquetes de 400 KB com o sistema MFS.  
Com a chegada dos **discos rígidos (HDs)** e o crescimento das aplicações, tornou-se necessário um sistema de arquivos mais **robusto e escalável**.

A Apple desenvolveu o HFS para atender essa nova demanda, lançando-o junto com o **Hard Disk 20** — o primeiro HD oficial do Macintosh.  
Essa mudança marcou o início de uma era de armazenamento hierárquico e organizado, essencial para os sistemas gráficos do Mac OS clássico.

## 🔹 Características Técnicas
- **Hierarquia real de diretórios:**  
  Diferente do MFS, o HFS implementou uma **estrutura em árvore**, permitindo a criação de pastas dentro de pastas.
- **Suporte a volumes maiores:**  
  Capaz de gerenciar discos de até **2 GB** (posteriormente expandido).
- **Sistema de blocos de alocação:**  
  Os dados eram armazenados em blocos, e o tamanho desses blocos variava conforme o tamanho do volume.
- **Catálogo B-Tree:**  
  O HFS utilizava uma **árvore B (B-Tree)** para indexar arquivos e diretórios, acelerando buscas e acesso.
- **Arquivos divididos em duas partes:**  
  Cada arquivo possuía um **data fork** (dados) e um **resource fork** (recursos, como ícones e metadados).
- **Compatibilidade com MFS:**  
  As primeiras versões do Mac OS podiam ler volumes formatados em MFS para facilitar a transição.

## 🔹 Evolução
Com o passar do tempo, o HFS mostrou limitações em volumes maiores e no gerenciamento de fragmentação.  
Esses problemas tornaram-se mais evidentes com o aumento da capacidade de armazenamento e o uso de sistemas multitarefa.

Em **1998**, a Apple lançou o **HFS+ (ou Mac OS Extended)**, que trouxe melhorias significativas:
- Suporte a **nomes de arquivos longos (até 255 caracteres)**;  
- **Unicode** para internacionalização;  
- Melhor **uso do espaço em disco**;  
- Compatibilidade retroativa com HFS.

## 🔹 Legado
O HFS foi o **primeiro sistema de arquivos hierárquico da Apple** e permaneceu como base do Mac OS por mais de uma década.  
Mesmo após sua substituição pelo HFS+ e, posteriormente, pelo **APFS (Apple File System)** em 2017, o HFS ainda é lembrado como um **marco de transição tecnológica**.

Ele consolidou conceitos fundamentais que influenciaram os sistemas de arquivos modernos — como hierarquia de diretórios, indexação eficiente e metadados ricos.

---

**Resumo:**  
O **HFS (1985)** substituiu o **MFS**, introduzindo hierarquia real de diretórios e suporte a discos rígidos.  
Foi essencial para a evolução do Mac OS e serviu de base para o **HFS+** e o **APFS**.  
Embora obsoleto, seu legado permanece como uma das maiores inovações da Apple na gestão de arquivos.

---
