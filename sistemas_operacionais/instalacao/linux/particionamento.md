# 🧩 Tabela Comparativa — Partições do Linux

| **Partição** | **Função Principal** | **Tamanho Recomendado** | **Uso Típico / Observações** |
|---------------|----------------------|--------------------------|-------------------------------|
| `/` (raiz) | Contém todo o sistema operacional e diretórios principais. | 20–60 GB | Sempre necessária; contém `/bin`, `/etc`, `/lib`, `/usr`. |
| `/home` | Armazena arquivos e configurações dos usuários. | Restante do disco (desktop) / 10–50 GB (servidor) | Facilita reinstalações sem perda de dados pessoais. |
| `/boot` | Contém o kernel e arquivos de inicialização (GRUB). | 1 GB | Necessária em sistemas com UEFI, LVM ou criptografia. |
| `swap` | Memória virtual usada quando a RAM acaba. | 2–8 GB (depende da RAM) | Importante para evitar travamentos e permitir hibernação. |
| `/var` | Armazena logs, cache e dados variáveis do sistema. | 10–200 GB | Isola logs e evita que ocupem o espaço do sistema raiz. |
| `/tmp` | Guarda arquivos temporários de usuários e programas. | 2–10 GB | Pode ser montada com opções de segurança (`noexec`, `nosuid`). |
| `/opt` | Instalação de softwares opcionais ou de terceiros. | 20–100 GB | Usada para programas externos ao gerenciador de pacotes. |
| `/srv` | Dados de serviços oferecidos (sites, FTP, etc). | 100–500 GB | Essencial em servidores web, FTP e repositórios. |
| `/backup` | Área para cópias de segurança locais. | Variável conforme volume de dados | Recomendável em servidores e ambientes corporativos. |
| `/usr` | Programas e bibliotecas do sistema. | 10–20 GB (caso separada) | Pode ser isolada em sistemas corporativos modulares. |

---

## 💡 Recomendações Profissionais

| **Cenário** | **Partições Mínimas** | **Extras Recomendadas** | **Sistema de Arquivos Ideal** |
|--------------|----------------------|--------------------------|-------------------------------|
| **Desktop Pessoal** | `/`, `/home`, `swap` | `/boot` (UEFI) | ext4 |
| **Servidor Simples** | `/`, `/home`, `/var`, `/tmp`, `swap` | `/boot`, `/srv` | ext4 / XFS |
| **Servidor Profissional** | `/`, `/home`, `/var`, `/srv`, `/tmp`, `/opt`, `swap`, `/boot`, `/backup` | Criptografia (LUKS), LVM | XFS (para `/var`, `/srv`), ext4 (demais) |

---

## 🔒 Boas Práticas

- Use **LVM** para redimensionar partições dinamicamente.  
- Monte `/tmp` e `/var/tmp` com opções de segurança:
  ```bash
  noexec, nosuid, nodev
