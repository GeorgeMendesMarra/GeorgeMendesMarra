---

```md
# 🧩 Tabela Comparativa — Partições do FreeBSD

| **Partição** | **Função Principal** | **Tamanho Recomendado** | **Uso Típico / Observações** |
|---------------|----------------------|--------------------------|-------------------------------|
| `/` (raiz) | Contém os diretórios essenciais do sistema (binários e scripts básicos). | 2–4 GB | Necessária para inicialização e utilitários principais. |
| `/boot` | Contém o kernel, loader e arquivos de boot. | 1 GB | Importante para inicialização, especialmente em sistemas UEFI. |
| `swap` | Área de troca (memória virtual). | 2× a RAM (ou mínimo de 2 GB) | Usada para hibernação e como memória adicional. |
| `/usr` | Armazena programas, bibliotecas e documentação. | 10–40 GB | Contém a maior parte dos binários do sistema. |
| `/var` | Guarda logs, spool, cache, emails e bancos de dados temporários. | 5–20 GB | Pode crescer rapidamente em servidores com muitos logs. |
| `/tmp` | Diretório temporário usado por aplicações. | 2–5 GB | Pode ser montado como tmpfs (memória) para desempenho. |
| `/home` | Diretórios pessoais dos usuários. | Restante do disco | Permite reinstalar o sistema sem perder dados. |
| `/usr/local` | Programas instalados por terceiros (ports ou pkg). | 10–50 GB | Mantém separado o que não faz parte do sistema base. |
| `/usr/src` | Código-fonte do sistema FreeBSD. | 2–4 GB | Necessário apenas se recompilar o kernel ou o sistema. |
| `/data` (opcional) | Armazenamento de dados e backups. | Variável | Útil em servidores para armazenar arquivos, bancos, etc. |

---

## 💡 Recomendações Profissionais

| **Cenário** | **Partições Mínimas** | **Extras Recomendadas** | **Sistema de Arquivos Ideal** |
|--------------|----------------------|--------------------------|-------------------------------|
| **Desktop Pessoal** | `/`, `swap`, `/home` | `/boot` | UFS2 ou ZFS |
| **Servidor Simples** | `/`, `swap`, `/usr`, `/var`, `/tmp`, `/home` | `/usr/local` | UFS2 ou ZFS |
| **Servidor Profissional** | `/`, `/boot`, `/usr`, `/var`, `/tmp`, `/usr/local`, `/home`, `/data`, `swap` | `/usr/src` (se recompilar kernel) | ZFS (com espelhamento RAIDZ ou mirror) |

---

## 🔒 Boas Práticas

- Use **ZFS** em servidores profissionais: permite **snapshots**, **compressão**, **verificação de integridade** e **RAID integrado**.  
- Monte `/tmp` com `noexec` e `nosuid` para aumentar a segurança.  
- Em sistemas com pouco espaço, combine `/usr` e `/var` dentro da raiz `/`.  
- Mantenha `/home` e `/usr/local` separados para simplificar reinstalações.  
- Crie partições maiores para `/var` em servidores com muitos logs, e para `/usr/local` em servidores de aplicações.  
- Utilize **swap** em discos SSD apenas se houver boa quantidade de RAM (para evitar desgaste).

---

## 📦 Estrutura típica de instalação (FreeBSD moderno com ZFS)

```

GPT (GUID Partition Table)
├── freebsd-boot   (512K - loader)
├── freebsd-swap   (4G - swap)
└── freebsd-zfs    (restante do disco - pool ZFS)
├── zroot/ROOT/default   (/)
├── zroot/usr/home       (/home)
├── zroot/usr/local      (/usr/local)
├── zroot/var            (/var)
├── zroot/tmp            (/tmp)

```

---

## 🧠 Dicas para uso em ambientes profissionais

- **ZFS com RAIDZ1/2/3** → ideal para servidores com múltiplos discos.  
- **UFS2 com Soft Updates Journaled** → mais leve e rápido para desktops.  
- **Snapshots automáticos do ZFS** → restauração instantânea de versões antigas.  
- **Separar `/var` e `/usr/local`** evita que logs ou pacotes ocupem o espaço do sistema.  
- **Usar GPT + UEFI** garante compatibilidade com hardware moderno.

---

```

---

Deseja que eu gere agora a **versão gráfica (diagrama visual)** das partições do FreeBSD — semelhante ao esquema visual que criei para o Linux — mostrando como o **layout em ZFS e UFS** é estruturado?
Fica ótimo para ilustrar em slides de aulas de **Administração de Sistemas Unix-like**.
